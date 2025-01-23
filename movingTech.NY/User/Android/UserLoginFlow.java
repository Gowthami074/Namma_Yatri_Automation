package User.Android;


import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;


import Utils.ConfigLoader;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import base.BaseClass;

public class UserLoginFlow extends BaseClass {

	DevicePermission devicePermission = new DevicePermission();
	PopUpsHandling popupshandling = new PopUpsHandling();


	// Method to read the mobile number from a file and increment it
	public String getNextMobileNumber() throws IOException {
		String mobileFilePath = System.getProperty("user.dir") + "/movingTech.NY/Resources/mobile_number.txt"; // Path to a file where the number is stored
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
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Get Started']")).click();
		Thread.sleep(2000);
		//    devicePermission.autoSuggestionDecline();
		Thread.sleep(1000);
		popupshandling.googleServicePhoneNumberAutofill();
		// Get dynamically incremented mobile number
		//String mobileNumber = getNextMobileNumber();

		String mobileNumber = ConfigLoader.getProperty("customer.mobile.number");

		driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='10-digit mobile number']")).sendKeys(mobileNumber);
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		Thread.sleep(2000);
		List<KeyEvent> keyEvents = List.of(
				new KeyEvent(AndroidKey.DIGIT_7),
				new KeyEvent(AndroidKey.DIGIT_8),
				new KeyEvent(AndroidKey.DIGIT_9),
				new KeyEvent(AndroidKey.DIGIT_1)
				);
		for (KeyEvent event : keyEvents) {
			((PressesKey) driver1).pressKey(event);
		}
		implicitWaitMethod(driver,5);
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
		implicitWaitMethod(driver1,60);
	}
	public void registration() {
		driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter Your Name']")).sendKeys("Vinod");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select Your Gender']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Male']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Let’s go!']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Allow Location Access']")).click();
		devicePermission.locationPermissionsConfirmation();
	}
	public void grantPermissions() throws InterruptedException {
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Allow Location Access']")).click();
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(2));
		devicePermission.locationPermissionsConfirmation();
		Thread.sleep(5000);
	}
}
