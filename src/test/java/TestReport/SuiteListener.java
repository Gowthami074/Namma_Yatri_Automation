package TestReport;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import io.qameta.allure.Allure;

public class SuiteListener implements ISuiteListener {

    private static final String CONSOLE_LOG_FILE = "allure-results/Logs/console_output.log";

    @Override
    public void onStart(ISuite suite) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🚀 [START] Suite execution started: " + suite.getName());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Reset the test failure flag at the start of each suite
        TestListener.resetTestFailure();
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🏁 [FINISH] Suite execution finished: " + suite.getName());

        // Report suite status based on test failure flag
        if (TestListener.hasTestFailed()) {
            System.out.println("⚠️ [SUITE STATUS] Suite completed with failures detected");
        } else {
            System.out.println("✅ [SUITE STATUS] Suite completed successfully");
        }

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Attach the console logs to the report
   //     attachConsoleLogs();
    }

    public static void attachConsoleLogs() {
        File logFile = new File(CONSOLE_LOG_FILE);
        if (!logFile.exists() || logFile.length() == 0) {
            System.err.println("❌ [ERROR] Console log file does not exist or is empty. Skipping attachment.");
            return;
        }
        try (FileInputStream logStream = new FileInputStream(logFile)) {
            Allure.addAttachment("Suite Console Logs", "text/plain", logStream, ".txt");
            System.out.println("✅ [INFO] Attached console logs to the Allure report.");
        } catch (IOException e) {
            System.err.println("❌ [ERROR] Failed to attach console logs: " + e.getMessage());
        }
    }
}