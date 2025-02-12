package Driver;

import java.util.List;

import org.openqa.selenium.WebElement;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class DevicePermission extends BaseClass {
	public String driverApp = "Namma Yatri Partner";


	public void handlePermissions() throws InterruptedException {
		System.out.println("Setting Up the permissions");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Draw over applications']")).click();
		System.out.println("Draw Over Applications permission");
		Thread.sleep(2000);
		drawOverApplicationsFlow();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Autostart in background']")).click();
		Thread.sleep(2000);	 
		autoStartPermission();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Battery Optimization']")).click();
		Thread.sleep(1000);
		batteryOptimizationPopUp();
		switch(driverUdid) {
		case "8cb2f113":
		case "SONJFMDUKV65OJV4":
		case "1377682723004YR":
		case "RZ8R72RE6AB":
		case "062c68090409":
		case "eb23ba":
		case "4e27c0e2":
		case"emulator-5554":
		case"emulator-5556":
		case"emulator-5558":
			break;
		default:
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Notification Access']")).click();
			Thread.sleep(1000);
			notificationAccessPopUp();
		}
		System.out.println("Permissions are setted Up successfully");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		Thread.sleep(2000);
		if("28301JEGR03129".contains(driverUdid))
		{
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Enable Permission']")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Allow all the time']")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();

		}
	}


	private void permissionForSamsungGalaxyA03Core() throws InterruptedException {

		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Grant Permissions']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Draw over applications']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Autostart in background']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Battery Optimization']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Notification Access']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Okay']")).click();
		Thread.sleep(2000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Check Now']")).click();
		Thread.sleep(2000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Home']")).click();
		Thread.sleep(5000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='GO!']")).click();
		Thread.sleep(2000);
	}



	private void drawOverApplicationsFlow() throws InterruptedException {
		switch(driverUdid) {
		case "1371785921000CT":
		case "1377682723004YR":
		case "15913008960024W":
		case "9618349153000CS":
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Search']")).click();
			Thread.sleep(2000);
			driver.findElement(AppiumBy.xpath("//android.widget.AutoCompleteTextView[@text='Search']")).sendKeys("Namma");

			implicitWaitMethod(driver,5);
			List<WebElement> appName = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Namma Yatri Partner']"));

			if(!appName.isEmpty() && appName.get(0).isDisplayed()) {
				System.out.println("App name displayed");
				appName.get(0).click();
			}
			else {
				driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='in.juspay.nammayatripartner.debug']")).click();
				System.out.println("App Package name displayed");
			}
			implicitWaitMethod(driver,60);
			Thread.sleep(3000);

			Thread.sleep(2000);
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Allow display over other apps\"]")).click();
			Thread.sleep(2000);
			driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc=\"Back\"]")).click();
			Thread.sleep(2000);
			break;
		case "28301JEGR03129":
			driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Search']")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Search…']/..")).sendKeys("Namma");
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Namma Yatri Partner']")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Allow display over other apps']/../android.view.View")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.Button")).click();
			driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Collapse']")).click();
			driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Navigate up']")).click();
			break;
		case "RZ8N91FWYXT": 
		case "RFCT42T5N9D":
		case "RZ8R72RE6AB":
		case "8cb2f113":

			//		case "SONJFMDUKV65OJV4":
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Namma Yatri Partner']")).click();
			Thread.sleep(4000);
			driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
			Thread.sleep(1000);
			break;
		case "SONJFMDUKV65OJV4":
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Namma Yatri Partner']")).click();
			Thread.sleep(4000);
			driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@text='Always']")).click();
			Thread.sleep(1000);
			driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
			Thread.sleep(1000);
			break;
		case "062c68090409":
			driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@resource-id='android:id/checkbox']")).click();
			Thread.sleep(2000);
			driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Back']")).click();
			break;
		case "eb23ba":
			driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
					+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" +driverApp+ "\").instance(0))"));
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Namma Yatri Partner']/../../android.widget.ImageView")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.Switch[@resource-id='android:id/switch_widget']")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Back']")).click();
			break;
		case "4e27c0e2":
			driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@resource-id='android:id/checkbox']")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Back']")).click();
			break;
		case"emulator-5554":
		case"emulator-5556":
		case"emulator-5558":
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Allow display over other apps']")).click();
			Thread.sleep(2000);
			driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
			AndroidBackButton back = new AndroidBackButton();
			back.tapBack();
			Thread.sleep(2000);
			break;
		default: 
			break;
		}
	}

	private void batteryOptimizationPopUp() throws InterruptedException{
		switch(driverUdid) {
		case "1371785921000CT":
		case "1377682723004YR":
		case "15913008960024W": 
		case "9618349153000CS":
		case "8cb2f113":
		case "SONJFMDUKV65OJV4":
		case "062c68090409":
		case "4e27c0e2":
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW']")).click();
			break;
		case "RZ8N91FWYXT":
		case "RFCT42T5N9D":
		case "R9ZTB0KD0FA":
		case "RZ8R72RE6AB":
		case "28301JEGR03129":
		case"emulator-5554":
		case"emulator-5556":
		case"emulator-5558":
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();
			break;
		case "eb23ba":
			driver.findElement(AppiumBy.xpath("//android.widget.CheckedTextView[@text='No restrictions']")).click();
			//	driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Back']")).click();
			break;
		default:
			break;
		}
	}

	private void notificationAccessPopUp() {
		switch(driverUdid) {
		case "1371785921000CT":
		case "1377682723004YR":
		case "15913008960024W": 
		case "9618349153000CS":
		case "4e27c0e2":
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW']")).click();
			break;
		case "RZ8N91FWYXT":
		case "RFCT42T5N9D":
		case "R9ZTB0KD0FA":
		case "28301JEGR03129":
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();
			break;
		default:
			break;

		}
	}
	private void autoStartPermission() {

		switch(driverUdid) {
		case "062c68090409":
			driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
					+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" +driverApp+ "\").instance(0))"));
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Namma Yatri Partner']/../../android.widget.CheckBox")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Back']/android.widget.ImageView")).click();
			break;
		case "eb23ba":
			driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
					+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" +driverApp+ "\").instance(0))"));
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Namma Yatri Partner']/../../android.widget.CheckBox")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Back']/android.widget.ImageView")).click();
			//need to add one more back x-path

			break;
		case "4e27c0e2":
			driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Back']")).click();
			break;
		default:
			break;

		}}

	public void locationPermission() throws InterruptedException
	{
		switch(driverUdid) {
		case "RFCT42T5N9D":
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow all the time']")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
			Thread.sleep(1000);
		}
	}


}