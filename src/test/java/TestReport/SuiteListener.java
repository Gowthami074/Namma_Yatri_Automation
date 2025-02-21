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
        System.out.println("Suite execution started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Suite execution finished: " + suite.getName());
    }

    public static void attachConsoleLogs() {
        File logFile = new File(CONSOLE_LOG_FILE);
        if (!logFile.exists() || logFile.length() == 0) {
            System.err.println("❌ [ERROR] Console log file does not exist or is empty. Skipping attachment.");
            return;
        }
        try (FileInputStream logStream = new FileInputStream(logFile)) {
            Allure.addAttachment("Suite Console Logs",  logStream);
            System.out.println("✅ [INFO] Attached console logs to the Allure report.");
        } catch (IOException e) {
            System.err.println("❌ [ERROR] Failed to attach console logs: " + e.getMessage());
        }
}}
