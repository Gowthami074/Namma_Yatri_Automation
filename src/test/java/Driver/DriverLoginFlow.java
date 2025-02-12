package Driver;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Utils.ConfigLoader;
import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class DriverLoginFlow extends BaseClass{
	PopUpsHandling popUpsHandling = new PopUpsHandling();
	
	@Test
	public void successfulDriverLogin() throws InterruptedException {
		Boolean loginFlow = false;
		Boolean locDetection = false;
		implicitWaitMethod(driver,15);
		
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(300));
		
		try {
			System.out.println("Checking whether we are getting Enable Location screen");
			WebElement enableLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Enable Location']")));
			enableLocation.click();
			locDetection = true;
		}
		catch(Exception e)
		{
			System.out.println("Location Detection is already happened");
		}
		if(locDetection)
		{
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Enable Location']")).click();
			Thread.sleep(2000);
			System.out.println(driverUdid);
			System.out.println("Trying to Tap While Using the app");
			switch(driverUdid) {
			case "RZ8N91FWYXT": 
			case "R9ZTB0KD0FA":
			case "RFCT42T5N9D":
			case "28301JEGR03129":
			case "RZ8R72RE6AB":
			case "8cb2f113":
			case "SONJFMDUKV65OJV4":
			case "M7JZ7T8P5TQSQ4KR":	
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='While using the app']")).click();
				break;
			case "1377682723004YR":
			case "1371785921000CT":
			case "15913008960024W":
			case "9618349153000CS":
			case "eb23ba":
				driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='WHILE USING THE APP']")).click();
				break;
			case "062c68090409":
				driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW ONLY WHILE USING THE APP']")).click();
				break;
			case "4e27c0e2":
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@text ='ALLOW']")).click();
			break;
			case "emulator-5554":
			case "emulator-5556":
				driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow only while using the app']")).click();
				break;
			
		default:
				throw new NoSuchElementException("Invalid Selectors");

			}
			Thread.sleep(5000);
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm']")).click();
			Thread.sleep(2000);
		}
		implicitWaitMethod(driver,10);
		try {
			System.out.println("Validating whether we are getting Get Started Screen");
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Get Started\"]"));
			System.out.println("Getting Get Started screen");
			loginFlow = true;
		}
		catch(Exception e) {
			System.out.println("Not Getting Get Started screen");
		}


		if(loginFlow)
		{	
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Get Started']")).click();
			Thread.sleep(2000);
			String mobileNumber = ConfigLoader.getProperty("driver.mobile.number");
			System.out.println("Driver login begins");
			System.out.println("Entering Mobile Number");
			driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='10-digit mobile number']")).sendKeys(mobileNumber); 
			Thread.sleep(2000);
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
			Thread.sleep(2000);
			System.out.println("Entering OTP");
			driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Auto Reading OTP...']")).sendKeys("7891");
			Thread.sleep(2000);
		}
		try
		{	
			System.out.println("Validating whether Grant Permissions screen is displaying");
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Grant Permissions']"));
			DevicePermission devicePermissions = new DevicePermission();
			devicePermissions.handlePermissions();

		}catch(Exception e) {
			System.out.println("Permissions are already given.");
		}
		try {
			System.out.println("Validating whether we are getting HomeScreen");
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Today's Earnings\"]"));
			System.out.println("Getting Homescreen");
		}catch(Exception e) {
			throw e;
		}
		implicitWaitMethod(driver,60);
		popUpsHandling.locationPermissionPopUp();
		popUpsHandling.upiOverlayPopUp();
		popUpsHandling.yatriCoinsOverlayPopUp();
		popUpsHandling.pointsOverLay();
		popUpsHandling.driverModeValidation();		
	}
}

