2package TestReport;

import java.io.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener extends BaseClass implements ITestListener {
    private static final String ALLURE_LOGS_DIR = "allure-results/Logs";
    private static final String ALLURE_IMAGES_DIR = "allure-results/Images";
    private static final String CONSOLE_LOG_FILE = "allure-results/Logs/console_output.log";

    static {
        createDirectories();
        setupConsoleLogging();
    }

    private static void createDirectories() {
        new File(ALLURE_LOGS_DIR).mkdirs();
        new File(ALLURE_IMAGES_DIR).mkdirs();
        new File("logs").mkdirs();
    }

    private static void setupConsoleLogging() {
        try {
            FileOutputStream fos = new FileOutputStream(CONSOLE_LOG_FILE, true);
            PrintStream fileOut = new PrintStream(fos, true);
            PrintStream consoleOut = new PrintStream(new TeeOutputStream(System.out, fileOut), true);
            PrintStream consoleErr = new PrintStream(new TeeOutputStream(System.err, fileOut), true);
            System.setOut(consoleOut);
            System.setErr(consoleErr);
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to setup console logging: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] " + result.getName());
        captureScreenshot(result.getName() + "_driver", driver);
        captureScreenshot(result.getName() + "_user", driver1);
        attachTestLogs(result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("[START] Test name: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("[END] Test name: " + context.getName());
        attachConsoleLogs();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[RUNNING] [Method Name - " + result.getName() + "]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] [Method Name - " + result.getName() + "]");
        attachTestLogs(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIPPED] [Method Name - " + result.getName() + "]");
    }

    public static void attachTestLogs(String testName) {
        File logFile = new File(CONSOLE_LOG_FILE);
        if (!logFile.exists() || logFile.length() == 0) return;
        try (InputStream inputStream = new FileInputStream(logFile)) {
            Allure.addAttachment(testName + " Logs", "text/plain", inputStream, ".txt");
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to attach logs for " + testName + ": " + e.getMessage());
        }
    }

    public static void attachConsoleLogs() {
        File logFile = new File(CONSOLE_LOG_FILE);
        if (!logFile.exists() || logFile.length() == 0) return;
        try (InputStream inputStream = new FileInputStream(logFile)) {
            Allure.addAttachment("Console Logs", "text/plain", inputStream, ".txt");
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to attach console logs: " + e.getMessage());
        }
    }

    public static void captureScreenshot(String testName, AppiumDriver driver) {
        if (driver == null) return;
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(ALLURE_IMAGES_DIR, testName + "_failed.png");
            screenshotFile.renameTo(destinationFile);
            try (InputStream inputStream = new FileInputStream(destinationFile)) {
                Allure.addAttachment("Screenshot - " + testName, inputStream);
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to capture screenshot for " + testName + " - " + e.getMessage());
        }
    }
}

class TeeOutputStream extends OutputStream {
    private final OutputStream out1;
    private final OutputStream out2;

    public TeeOutputStream(OutputStream out1, OutputStream out2) {
        this.out1 = out1;
        this.out2 = out2;
    }

    @Override
    public void write(int b) throws IOException {
        out1.write(b);
        out2.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out1.write(b, off, len);
        out2.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        out1.flush();
        out2.flush();
    }

    @Override
    public void close() throws IOException {
        out1.close();
        out2.close();
    }
}
