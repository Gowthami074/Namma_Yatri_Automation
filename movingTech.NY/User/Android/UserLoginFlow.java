package User.Android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import base.BaseClass;

public class UserLoginFlow extends BaseClass {

    // Method to read the mobile number from a file and increment it
    public String getNextMobileNumber() throws IOException {
        String mobileFilePath = "/Users/vinodkumar/Desktop/namma_yatri_automation_allu/movingTech.NY/Resources/mobile_number.txt"; // Path to a file where the number is stored
        FileReader reader = new FileReader(mobileFilePath);
        StringBuilder number = new StringBuilder();
        int i;
        while ((i = reader.read()) != -1) {
            number.append((char) i);
        }
        reader.close();

        // Increment the mobile number
        long currentMobileNumber = Long.parseLong(number.toString().trim());
        currentMobileNumber++;

        // Write the incremented mobile number back to the file
        FileWriter writer = new FileWriter(mobileFilePath);
        writer.write(Long.toString(currentMobileNumber));
        writer.close();

        return Long.toString(currentMobileNumber);
    }

    @Test
    public void successfulUserLogin() throws InterruptedException, IOException {
        DevicePermission devicePermission = new DevicePermission();
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Get Started']")).click();
        Thread.sleep(2000);
        devicePermission.autoSuggestionDecline();
        Thread.sleep(1000);

        // Get dynamically incremented mobile number
        String mobileNumber = getNextMobileNumber();
        driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter Mobile number']")).sendKeys(mobileNumber);
        Thread.sleep(2000);
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
        Thread.sleep(2000);
        driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter 4 digit OTP']")).sendKeys("7891");
        Thread.sleep(3000);

        try {
            WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
            WebElement registrationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Just one last thing']")));
            if (registrationElement.isDisplayed()) {
                System.out.println("New number, proceeding registration.");
                registration();
            }
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Registration screen not found, checking for 'Grant Permissions' screen.");

            try {
                WebElement permissionsElement = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Allow Location Access']"));
                if (permissionsElement.isDisplayed()) {
                    System.out.println("Navigating to 'Grant Permissions' screen.");
                    grantPermissions();
                }
            } catch (NoSuchElementException ex) {
                System.out.println("Neither 'Registration' nor 'Grant Permissions' screen found.");
            }
        }
    }

    public void registration() {
        driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter Your Name']")).sendKeys("Vinod");
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select Your Gender']")).click();
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Male']")).click();
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Let’s go!']")).click();
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Allow Location Access']")).click();
        DevicePermission devicePermission = new DevicePermission();
        devicePermission.locationPermissionsConfirmation();
    }

    public void grantPermissions() throws InterruptedException {
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Allow Location Access']")).click();
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Allow Location Access']")));
        DevicePermission devicePermission = new DevicePermission();
        devicePermission.locationPermissionsConfirmation();
        Thread.sleep(5000);
    }
}
