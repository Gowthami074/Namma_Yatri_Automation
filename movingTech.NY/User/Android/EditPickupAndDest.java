package User.Android;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Driver.DriverCancellation;
import base.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class EditPickupAndDest extends BaseClass {
	
	  DriverCancellation Drivercancel = new DriverCancellation();
	  RideSearch ridesearch = new RideSearch();
	  UserCancellation usercancl= new UserCancellation();
	  
	 @Test
	public void EditPickup() throws InterruptedException {
		System.out.println("User started to edit the pickup");
	
		WebElement upi = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Tool center']"));
		System.out.println(upi);
		Thread.sleep(5000);
		usercancl.scroll(upi);
		System.out.println("Scroll completed");
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Edit']")).click();
		Thread.sleep(2000);
		ridesearch.scroll(0);
		 System.out.println("User wants to edit the poickup outside the threshold");
		Thread.sleep(2000);
		driver1.navigate().back();
//		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm New Pickup']")).click();
//		System.out.println("Pickup location edited sucessfully");
}
	@Test
	public void EditDestination() throws InterruptedException {
		WebElement upi = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Tool center']"));
		Thread.sleep(5000);
		usercancl.scroll(upi);
		System.out.println("Scroll completed");
			Thread.sleep(2000);
		 driver1.findElement(AppiumBy.xpath("(//android.widget.TextView[@text=\"Edit\"])[2]")).click();
		 System.out.println("User editing to the destination");
		 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Map']")).click();
			Thread.sleep(2000);
		 ridesearch.scroll(6);
			Thread.sleep(2000);
		 System.out.println("Destination edited sucessfully");
		 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Drop Location']")).click();
			Thread.sleep(2000);
		 System.out.println("User got the revised estimates");
		 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Request Drop Change']")).click();
		 System.out.println("User requested for the edit destination..... waiting for driver response");
			Thread.sleep(2000);
	}
}