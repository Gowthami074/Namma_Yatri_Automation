package User.Android;

import java.util.Arrays;

import org.testng.annotations.IFactoryAnnotation;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class DevicePermission extends BaseClass {

	public void autoSuggestionDecline() {
		if (Arrays.asList("15913008960024W", "RFCT42T5N9D", "SONJFMDUKV65OJV4", "28301JEGR03129", "1371785921000CT", "RZ8R72RE6AB", "9618349153000CS", "eb23ba").contains(userUdid)) {

			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='NONE OF THE ABOVE']")).click();

		} else if (Arrays.asList("", "", "").contains(userUdid)) {


			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='None of the above']")).click();

		} else {
			System.out.println("No action required for this Udid: " + userUdid);
		}
	} 

	public void locationPermissionsConfirmation() {


		if(Arrays.asList("15913008960024W", "1377682723004YR", "1371785921000CT",  "9618349153000CS", "eb23ba").contains(userUdid))
		{
			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='WHILE USING THE APP']")).click();

		}
		else if (Arrays.asList("8cb2f113", "RFCT42T5N9D", "SONJFMDUKV65OJV4", "28301JEGR03129", "R9ZTB0KD0FA", "RZ8N91FWYXT","RZ8R72RE6AB","85LRIFMFM7SCSS7D").contains(userUdid)) {

			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='While using the app']")).click();

		}
		else if("062c68090409".contains(userUdid)) {

			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW ONLY WHILE USING THE APP']")).click();
		}

		else {
			System.out.println("No action required for this Udid: " + userUdid);
		}

	}

	public void notification() {
		if(Arrays.asList("1371785921000CT", "15913008960024W", "9618349153000CS", "eb23ba").contains(userUdid))
		{

			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW']")).click();

		}
		else if (Arrays.asList("RZ8N91FWYXT", "R9ZTB0KD0FA", "RFCT42T5N9D").contains(userUdid))
		{
			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();

		}
		else {
			System.out.println("No action required for this Udid: " + userUdid);

		}
	}
	public void grantLocationAccess() {
		if (Arrays.asList("15913008960024W", "RFCT42T5N9D", "SONJFMDUKV65OJV4", "28301JEGR03129", "1371785921000CT", "RZ8R72RE6AB", "9618349153000CS", "eb23ba","85LRIFMFM7SCSS7D","RZ8N91FWYXT").contains(userUdid)) {

			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Grant Location Access']")).click();

		} else if (Arrays.asList("", "", "").contains(userUdid)) {


			driver1.findElement(AppiumBy.xpath("")).click();

		} else {
			System.out.println("No action required for this Udid: " + userUdid);
		}
		
		
	}

}