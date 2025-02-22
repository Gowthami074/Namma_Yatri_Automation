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
import org.testng.SkipException;
import base.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;

public class TestListener extends BaseClass implements ITestListener {

    private static final String ALLURE_LOGS_DIR = "allure-results/Logs";
    private static final String ALLURE_IMAGES_DIR = "allure-results/Images";
    private static final String CONSOLE_LOG_FILE = "allure-results/Logs/console_output.log";
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Flag to track if any test has failed
    private static boolean hasFailed = false;

    static {
        createDirectories();
        clearPreviousLogsAndImages(); // Renamed method here
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

    private static void clearPreviousLogsAndImages() { // Renamed method implementation
        File logFile = new File(CONSOLE_LOG_FILE);
        if (logFile.exists()) {
            if (logFile.delete()) {
                System.out.println("🧹 [INFO] Previous console log file deleted successfully.");
            } else {
                System.err.println("❌ [ERROR] Failed to delete previous console log file.");
            }
        }

        // Optionally clear images as well
        File imagesDir = new File(ALLURE_IMAGES_DIR);
        if (imagesDir.exists()) {
            File[] images = imagesDir.listFiles();
            if (images != null) {
                for (File image : images) {
                    if (image.delete()) {
                      //  System.out.println("🧹 [INFO] Deleted image: " + image.getName());
                    } else {
                        System.err.println("❌ [ERROR] Failed to delete previous test run image: " + image.getName());
                    }
                }
            }
            System.out.println("🧹 [INFO] Previous test run images cleared from: " + ALLURE_IMAGES_DIR);

        } else {
            System.out.println("📁 [INFO] Images directory does not exist or is empty: " + ALLURE_IMAGES_DIR);
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

    public static void resetTestFailure() {
        hasFailed = false;
    }

    public static boolean hasTestFailed() {
        return hasFailed; // Return current status of failure flag
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🟢 [START] Test Name: " + context.getName() + " at " + timestamp());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🔵 [END] Test Name: " + context.getName() + " at " + timestamp());
//        System.out.println("📊 Summary: Passed: " + context.getPassedTests().size() +
//                ", Failed: " + context.getFailedTests().size() +
//                ", Skipped: " + context.getSkippedTests().size());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (hasFailed) {
            String testName = result.getName();
            System.out.println("⏩ [SKIPPING] Test " + testName + " due to previous test failure");
            throw new SkipException("Test skipped due to previous failure in the suite");
        }
        System.out.println("▶️ [RUNNING] Test: " + result.getName() + " at " + timestamp());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ [PASS] Test: " + result.getName() + " at " + timestamp());
        attachTestLogs(result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        System.out.println("❌ [FAIL] Test: " + testName + " at " + timestamp());
        System.out.println("⚠️ [ERROR MESSAGE]: " + result.getThrowable().getMessage());
        hasFailed = true;

        System.out.println("\n##############################################################################");
        System.out.println("##                                                                            ##");
        System.out.println("##                      🛑 TEST FAILURE DETECTED 🛑                          ##");
        System.out.println("##                  REMAINING TESTS WILL BE SKIPPED                          ##");
        System.out.println("##                                                                           ##");
        System.out.println("##############################################################################\n");

        if (driver != null) {
            captureScreenshot(testName + "_driver", driver);
        }

        if (driver1 != null) {
            captureScreenshot(testName + "_user", driver1);
        }
        attachTestLogs(testName);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ [SKIPPED] Test: " + result.getName() + " at " + timestamp());
        attachTestLogs(result.getName());
    }

    public static void captureScreenshot(String testName, AppiumDriver driver) {
        if (driver == null) {
            System.err.println("🚫 [ERROR] Driver is null, cannot capture screenshot.");
            return;
        }

        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(ALLURE_IMAGES_DIR, testName + "_failed.png");

            Files.move(screenshotFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("📸 [INFO] Screenshot saved for: " + testName);

            if (destinationFile.exists()) {
                try (FileInputStream fis = new FileInputStream(destinationFile)) {
                    Allure.getLifecycle().addAttachment("Screenshot - " + testName, "image/png", "png", fis);
                }
            } else {
                System.err.println("❌ [ERROR] Screenshot file does not exist after moving for: " + testName);
            }

        } catch (IOException e) {
            System.err.println("❌ [ERROR] Failed to capture screenshot for " + testName + ": " + e.getMessage());
        }
    }

    public static void attachTestLogs(String testName) {
        File logFile = new File(CONSOLE_LOG_FILE);

        if (!logFile.exists() || logFile.length() == 0) {
            System.err.println("❌ [ERROR] Console log file does not exist or is empty. Skipping attachment.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(logFile)) {
            Allure.addAttachment(testName + " Logs", "text/plain", fis, ".txt");
            System.out.println("✅ [INFO] Attached logs for: " + testName);

        } catch (IOException e) {
            System.err.println("❌ [ERROR] Failed to attach logs for " + testName + ": " + e.getMessage());
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
