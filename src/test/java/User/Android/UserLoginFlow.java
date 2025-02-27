package User.Android;


import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;


import Utils.ConfigLoader;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import base.BaseClass;

public class UserLoginFlow extends BaseClass {

	DevicePermission devicePermission = new DevicePermission();
	PopUpsHandling popupshandling = new PopUpsHandling();
	long currentMobileNumber;
	String enteredMobileString;

	// Method to read the mobile number from a file and increment it
	public String getNextMobileNumber() throws IOException {
		String mobileFilePath = System.getProperty("user.dir") + "/src/test/resources/Resources/mobile_number.txt"; // Path to a file where the number is stored
		FileReader reader = new FileReader(mobileFilePath);
		StringBuilder number = new StringBuilder();
		int i;
		while ((i = reader.read()) != -1) {
			number.append((char) i);
		}
		reader.close();
		// Increment the mobile number
		currentMobileNumber = Long.parseLong(number.toString().trim());
		currentMobileNumber++;

		// Write the incremented mobile number back to the file
		FileWriter writer = new FileWriter(mobileFilePath);
		writer.write(Long.toString(currentMobileNumber));
		writer.close();
		return Long.toString(currentMobileNumber);

	}

	@Test
	public void successfulUserLogin() throws Exception {
		implicitWaitMethod(driver1,10);
		devicePermission.notification();
		implicitWaitMethod(driver1,60);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Get Started']")).click();
		Thread.sleep(2000);
		//    devicePermission.autoSuggestionDecline();
		Thread.sleep(1000);
		popupshandling.googleServicePhoneNumberAutofill();
		// Get dynamically incremented mobile number
		String mobileNumber = getNextMobileNumber();

		//		String mobileNumber = ConfigLoader.getProperty("customer.mobile.number");

		driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='10-digit mobile number']")).sendKeys(mobileNumber);
		enteredMobileString = driver1.findElement(AppiumBy.xpath("//android.widget.EditText")).getText();
		System.out.println(enteredMobileString +" : Mobile number");
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

		implicitWaitMethod(driver1,5);
		try {
			WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
			WebElement registrationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Full Name (Helps driver confirm it is you)']")));
			if (registrationElement.isDisplayed()) {
				System.out.println("New number, proceeding registration.");
				registration();
			}
		} catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
			System.out.println("Registration screen not found, checking for 'Grant Permissions' screen.");
			devicePermission.grantLocationAccess();
			Thread.sleep(3000);
			devicePermission.locationPermissionsConfirmation();

		}
		implicitWaitMethod(driver1,60);
	}


	public void registration() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver1 , Duration.ofSeconds(60));
		try {
			System.out.println("Entered into registration Screen");
			WebElement registrationScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Full Name (Helps driver confirm it is you)']/../android.view.ViewGroup/android.widget.EditText")));
			registrationScreen.click();
			registrationScreen.sendKeys("Automation");
			WebElement registrationEmail=driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Email']/../android.view.ViewGroup/android.widget.EditText"));
			registrationEmail.click();
			registrationEmail.sendKeys(enteredMobileString+"automation@gmail.com");
			//		registrationEmailElement.sendKeys("automation@gmail.com");
			//		((AndroidDriver) driver1).hideKeyboard();
			//		WebElement continuebuttonElement =driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']"));
			//		continuebuttonElement.click();
			//		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Gender is required']"));
			//		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Please choose one option']"));
			//		System.out.println("Validated error message for 'Continue' without gender and disability");
			//
			//		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Email']/../android.view.ViewGroup/android.widget.ImageButton")).click();
			//		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Email']/../android.view.ViewGroup/android.widget.EditText")).sendKeys(enteredMobileString+"automation@gmail.com");
			((AndroidDriver) driver1).hideKeyboard();
			Thread.sleep(2000);
			WebElement genderDropdown= driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select Your Gender']/../android.view.ViewGroup/com.horcrux.svg.SvgView"));
			genderDropdown.click();	
			Thread.sleep(2000);
//			genderDropdown.click();	
			//		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Other']")).click();
			//		genderDropdown.click();	
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Female']")).click();
			genderDropdown.click();
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Male']")).click();
			WebElement noElement = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='No']/../../../android.view.ViewGroup/android.view.ViewGroup/com.horcrux.svg.SvgView"));
			noElement.click();
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes']/../../../android.view.ViewGroup/android.view.ViewGroup/com.horcrux.svg.SvgView")).click();
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Special Assistance']"));
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select the condition applicable to you (As per RPWD act 2016)']"));
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Blind/Low Vision']")).click();
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Hearing Impairment (Deaf/Mute)']")).click();
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Locomotor Disability']")).click();
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='By continuing, you are declaring your status as a person with a disability under the RPWD act of 2016']"));
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='My disability is not listed here']")).click();
			Thread.sleep(2000);
			//		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Your Disability (helps us optimise the app for you)']/../android.widget.EditText")).sendKeys("NA");
			((AndroidDriver) driver1).hideKeyboard();
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Submit']")).click();	
			Thread.sleep(3000); 
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='No']/../com.horcrux.svg.SvgView")).click();
			System.out.println("New user registration completed");
			WebElement continuebuttonElement =driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']"));
			//		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Email is already registered.']"));
			continuebuttonElement.click();
			new WebDriverWait(driver1, Duration.ofSeconds(8));
			devicePermission.grantLocationAccess();
			Thread.sleep(8000);
			devicePermission.locationPermissionsConfirmation();
		}
		catch (Exception e) {
			System.out.println("Screen not loaded and new user Registration test failed.");
		}
		implicitWaitMethod(driver1,60);
	}

	public void grantPermissions() throws InterruptedException {
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Allow Location Access']")).click();
		new WebDriverWait(driver1, Duration.ofSeconds(2));
		devicePermission.locationPermissionsConfirmation();
		Thread.sleep(5000);
	}
}
