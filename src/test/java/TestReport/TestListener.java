package TestReport;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;

public class TestListener extends BaseClass implements ITestListener {
    private static final String ALLURE_LOGS_DIR = "allure-results/Logs";
    private static final String ALLURE_IMAGES_DIR = "allure-results/Images";
    private static final String CONSOLE_LOG_FILE = "allure-results/Logs/console_output.log";
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        createDirectories();
        clearPreviousLogs();
        setupConsoleLogging();
    }

    private static void createDirectories() {
        new File(ALLURE_LOGS_DIR).mkdirs();
        new File(ALLURE_IMAGES_DIR).mkdirs();
        new File("logs").mkdirs();
        
        File logFile = new File(CONSOLE_LOG_FILE);
        logFile.getParentFile().mkdirs();
        System.out.println("📁 Directories Created Successfully!");
    }

    private static void clearPreviousLogs() {
        File logFile = new File(CONSOLE_LOG_FILE);
        if (logFile.exists()) {
            logFile.delete();  // Remove previous logs before writing new ones
        }
    }

    private static void setupConsoleLogging() {
        File logsDir = new File(ALLURE_LOGS_DIR);
        if (!logsDir.exists()) {
            logsDir.mkdirs();  // Ensure logs directory exists
        }

        try {
            FileOutputStream fos = new FileOutputStream(CONSOLE_LOG_FILE, true);
            PrintStream fileOut = new PrintStream(fos, true);
            TeeOutputStream teeStream = new TeeOutputStream(System.out, fileOut);
            PrintStream consoleOut = new PrintStream(teeStream, true);
            PrintStream consoleErr = new PrintStream(teeStream, true);
            System.setOut(consoleOut);
            System.setErr(consoleErr);
        } catch (IOException e) {
            System.err.println("[❌ ERROR] Failed to setup console logging: " + e.getMessage());
        }
    }

    private static String timestamp() {
        return TIMESTAMP_FORMAT.format(new Date());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🟢 [START] Test Suite: " + context.getName() + " at " + timestamp());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🔵 [END] Test Suite: " + context.getName() + " at " + timestamp());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        attachConsoleLogs();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("▶️ [RUNNING] Test: " + result.getName() + " at " + timestamp());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ [PASS] Test: " + result.getName() + " at " + timestamp());
        attachTestLogs(result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ [FAIL] Test: " + result.getName() + " at " + timestamp());
        System.out.println("⚠️ [ERROR MESSAGE]: " + result.getThrowable().getMessage());

        if (driver != null) {
            captureScreenshot(result.getName() + "_driver", driver);
        } else {
            System.err.println("🚫 [ERROR] Driver is null, cannot capture driver screenshot.");
        }

        if (driver1 != null) {
            captureScreenshot(result.getName() + "_user", driver1);
        } else {
            System.err.println("🚫 [ERROR] Driver1 is null, cannot capture user screenshot.");
        }

        attachTestLogs(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ [SKIPPED] Test: " + result.getName() + " at " + timestamp());
    }


    public static void captureScreenshot(String testName, AppiumDriver driver) {
        if (driver == null) {
            System.err.println("🚫 [ERROR] Driver is null, cannot capture screenshot.");
            return;
        }

        try {
            // Take screenshot and store it temporarily
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(ALLURE_IMAGES_DIR, testName + "_failed.png");

            // Move the file safely
            Files.move(screenshotFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("📸 [INFO] Screenshot saved for: " + testName);

            // Attach screenshot to Allure only if the file exists
            if (destinationFile.exists()) {
                try (FileInputStream fis = new FileInputStream(destinationFile)) {
                    Allure.getLifecycle().addAttachment("Screenshot - " + testName, "image/png", "png", fis);
                }
            } else {
                System.err.println("❌ [ERROR] Screenshot file does not exist after moving for: " + testName);
            }
        } catch (IOException e) {
            System.err.println("❌ [ERROR] Failed to capture screenshot for " + testName + " - " + e.getMessage());
        }
    }


    public static void attachTestLogs(String testName) {
        File logFile = new File(CONSOLE_LOG_FILE);
        if (!logFile.exists() || logFile.length() == 0) return;

        try (FileInputStream fis = new FileInputStream(logFile)) {
            if (Allure.getLifecycle().getCurrentTestCase().isPresent()) {
                Allure.addAttachment(testName + " Logs", "text/plain", fis, ".txt");
            } else {
                System.err.println("❌ [ERROR] No test is running. Skipping log attachment: " + testName);
            }
        } catch (IOException e) {
            System.err.println("❌ [ERROR] Failed to attach logs for " + testName + ": " + e.getMessage());
        }
    }

    public static void attachConsoleLogs() {
        File logFile = new File(CONSOLE_LOG_FILE);
        if (!logFile.exists() || logFile.length() == 0) return;

        try (FileInputStream fis = new FileInputStream(logFile)) {
            if (Allure.getLifecycle().getCurrentTestCase().isPresent()) {
                Allure.addAttachment("Console Logs", "text/plain", fis, ".txt");
            } else {
                System.err.println("❌ [ERROR] No test is running. Skipping console log attachment.");
            }
        } catch (IOException e) {
            System.err.println("❌ [ERROR] Failed to attach console logs: " + e.getMessage());
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
