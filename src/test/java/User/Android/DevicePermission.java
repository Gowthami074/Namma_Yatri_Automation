package User.Android;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.IFactoryAnnotation;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class DevicePermission extends BaseClass {

	public void autoSuggestionDecline() {

		if (Arrays.asList("15913008960024W", "RFCT42T5N9D", "SONJFMDUKV65OJV4", "28301JEGR03129", "1371785921000CT", "RZ8R72RE6AB", "9618349153000CS","192.168.10.200:5555", "192.168.10.199:5555", "eb23ba", "192.168.10.172:5555").contains(userUdid)) {

			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='NONE OF THE ABOVE']")).click();

		} else if (Arrays.asList("", "", "").contains(userUdid)) {


			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='None of the above']")).click();

		} else {
			System.out.println("No action required for this Udid: " + userUdid);
		}
	} 

	public void locationPermissionsConfirmation() {


		if(Arrays.asList("15913008960024W", "1377682723004YR", "1371785921000CT",  "9618349153000CS","192.168.10.200:5555", "192.168.10.199:5555", "eb23ba").contains(userUdid))
		{
			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='WHILE USING THE APP']")).click();

		}
		else if (Arrays.asList("8cb2f113", "RFCT42T5N9D","192.168.10.172:5555", "SONJFMDUKV65OJV4", "28301JEGR03129", "R9ZTB0KD0FA", "RZ8N91FWYXT","RZ8R72RE6AB","85LRIFMFM7SCSS7D").contains(userUdid)) {

			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='While using the app']")).click();

		}
		else if("062c68090409".contains(userUdid)) {

			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW ONLY WHILE USING THE APP']")).click();
		}
		else if (Arrays.asList("emulator-5556","emulator-5554","emulator-5558").contains(userUdid)) {
			WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(60));
			WebElement locPermission = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Allow only while using the app']")));
			locPermission.click();
		}


		else {
			System.out.println("No action required for this Udid: " + userUdid);
		}

	}

	public void notification() {
		if(Arrays.asList("1371785921000CT", "15913008960024W", "9618349153000CS", "192.168.10.199:5555").contains(userUdid))
		{

			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW']")).click();

		}
		else if (Arrays.asList("RZ8N91FWYXT", "R9ZTB0KD0FA").contains(userUdid))
		{
			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();

		}
		else {
			System.out.println("No action required for this Udid: " + userUdid);

		}
	}
	public void grantLocationAccess() {
		if (Arrays.asList("15913008960024W", "RFCT42T5N9D", "SONJFMDUKV65OJV4", "28301JEGR03129","062c68090409", "1371785921000CT", "RZ8R72RE6AB", "9618349153000CS","192.168.10.200:5555", "192.168.10.199:5555", "eb23ba","85LRIFMFM7SCSS7D","RZ8N91FWYXT","emulator-5556","emulator-5554","emulator-5558", "192.168.10.172:5555").contains(userUdid)) {

			WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(60));
			WebElement grantAccess = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Grant Location Access']")));
			grantAccess.click();

		} else if (Arrays.asList("", "", "").contains(userUdid)) {


			driver1.findElement(AppiumBy.xpath("")).click();

		} else {
			System.out.println("No action required for this Udid: " + userUdid);
		}
		
		
	}

}