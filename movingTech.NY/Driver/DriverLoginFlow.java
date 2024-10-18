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

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class DriverLoginFlow extends BaseClass{
    PopUpsHandling popUpsHandling = new PopUpsHandling();
	@Test
	public void successfulDriverLogin() throws InterruptedException {
		System.out.println("Driver login begins");
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
		default:
			throw new NoSuchElementException("Invalid Selectors");

		}

		Thread.sleep(5000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm']")).click();
		Thread.sleep(2000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Get Started']")).click();
		Thread.sleep(2000);
		System.out.println("Entering Mobile Number");

		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='10-digit mobile number']")).sendKeys("8337860003"); 

		Thread.sleep(2000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		Thread.sleep(2000);
		System.out.println("Entering OTP");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Auto Reading OTP...']")).sendKeys("7891");
		Thread.sleep(2000);
		DevicePermission devicePermissions = new DevicePermission();
		devicePermissions.handlePermissions();

		//		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Maybe Later']"));
		//		driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='GO!']"));
		popUpsHandling.locationPermissionPopUp();
		popUpsHandling.upiOverlayPopUp();
		popUpsHandling.yatriCoinsOverlayPopUp();
		popUpsHandling.pointsOverLay();
		popUpsHandling.driverModeValidation();		
	}
}

