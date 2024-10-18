package User.Android;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import Driver.DriverCancellation;
import base.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

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
		
		driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Where to?']")).sendKeys("Koram");
		Thread.sleep(3000);
		((AndroidDriver) driver1).pressKey(new KeyEvent(AndroidKey.BACK));
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
	@Test
	public void SelectDestThroughMap() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Getting in for Locate on Map RideSearch");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where are you going?']")).click();
	//	driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Where to?']")).click();
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select location on map']")).click();
		scroll();
		Thread.sleep(5000);
		System.out.print("Scrolling is done");
//		estimateScroll();
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Drop Location']")).click();
		Thread.sleep(1000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();
		//driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Book Ride']")).click();
	}
	
	public void scroll(){
	for(int i=0;i<6;i++) {
		{	
			boolean canScrollMore = (Boolean)driver1.executeScript("mobile: scrollGesture", ImmutableMap.of(
						"left", 100, "top", 100, "width", 900, "height", 900,
						"direction", "down",
						"percent", 3.0
						));	
		}	
		}
	}
}



