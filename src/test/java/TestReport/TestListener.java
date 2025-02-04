package TestReport;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseClass;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

public class TestListener extends BaseClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());

        // Capture screenshots for both driver & user if available
        if (driver != null) {
            captureScreenshot(result.getName() + "_driver", driver);
        } else {
            System.out.println("⚠️ Driver is null, skipping driver screenshot.");
        }

        if (driver1 != null) {
            captureScreenshot(result.getName() + "_user", driver1);
        } else {
            System.out.println("⚠️ User driver is null, skipping user screenshot.");
        }
    }

    public static void captureScreenshot(String testName, AppiumDriver driver) {
        if (driver == null) {
            System.out.println("⚠️ Driver is null, cannot capture screenshot!");
            return;
        }

        try {
            // Ensure directory exists
            File screenshotDir = new File("allure-results/Images/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Capture screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotDir, testName + "_failed.png");

            FileUtils.copyFile(screenshotFile, destinationFile);
            System.out.println("✅ Screenshot saved: " + destinationFile.getAbsolutePath());

            // Ensure file exists before attaching
            if (destinationFile.exists()) {
                Allure.addAttachment("Screenshot - " + testName, "image/png",
                        new ByteArrayInputStream(Files.readAllBytes(destinationFile.toPath())), ".png");
            } else {
                System.out.println("❌ Screenshot file not found: " + destinationFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Failed to capture screenshot for " + testName);
        }
    }
}
