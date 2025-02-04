package Driver;

import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class DriverprofileScreen extends BaseClass {
	@Test
	public void getDriverVehicleVariant() throws InterruptedException{
		Thread.sleep(8000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text= 'Offline']/../../../../android.widget.LinearLayout[1]")).click(); //profileAvator
		Thread.sleep(5000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text= 'Settings']")).click();
		Thread.sleep(2000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text= 'Booking Preferences']")).click(); //BookingPreference
		vehicleVariantText = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text= 'Your Vehicle']/../android.widget.TextView[2]")).getText();
		System.out.println("Logged In Driver Vehicle variant is: " + vehicleVariantText);
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();       

	}
}
