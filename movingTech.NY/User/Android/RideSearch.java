package User.Android;

import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class RideSearch extends BaseClass{
    @Test	
	public void SearchForRideFromSuggestion() throws InterruptedException {
		System.out.println("Ride Search about to start");
		Thread.sleep(3000);
//		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where to?']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where are you going?']")).click();
		Thread.sleep(2000);
		// this is notification we are getting only in vivo device, If we are not getting in other devices we can comment this x-path
//		driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW']")).click();
		if("9618349153000CS".contains(userUdid))
		{
			driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();
		}
		
		driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Where to?']")).sendKeys("Koram");
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Koramangala']")).click();
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();
		Thread.sleep(4000);
 
		
	}

	public void SearchForRideForIntercity() throws InterruptedException {
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where to?']")).click();
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Where to?']")).sendKeys("Tumu");
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Tumukuru']")).click();
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();
	}
}
