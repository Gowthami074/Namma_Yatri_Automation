package User.Android;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Driver.DriverCancellation;
import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class EditPickupAndDest extends BaseClass {
	
	  DriverCancellation Drivercancel = new DriverCancellation();
	  RideSearch ridesearch = new RideSearch();
	  UserCancellation usercancl= new UserCancellation();
	  
	 @Test
	public void EditPickup() throws InterruptedException {
		System.out.println("User started to edit the pickup");
		WebElement upi = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Pay by Cash / UPI ']"));
		Thread.sleep(5000);
		usercancl.scroll(upi);
		System.out.println("Scroll completed");
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Edit']")).click();
		Thread.sleep(2000);
		ridesearch.scroll(1);
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();
		System.out.println("Source location edited sucessfully");
}
	@Test
	public void EditDestination() throws InterruptedException {
		WebElement upi = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Pay by Cash / UPI ']"));
		Thread.sleep(2000);
		usercancl.scroll(upi);
		System.out.println("Scroll completed");
			Thread.sleep(2000);
		 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Edit']")).click();
		 System.out.println("User editing to the destination");
		 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select location on map']")).click();
			Thread.sleep(2000);
		 ridesearch.scroll(6);
			Thread.sleep(2000);
		 System.out.println("Destination edited sucessfully");
		 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Drop Location']")).click();
			Thread.sleep(2000);
		 System.out.println("User got the revised estimates");
		 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Details']")).click();
			Thread.sleep(2000);
		 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Request Change']")).click();
	}
}