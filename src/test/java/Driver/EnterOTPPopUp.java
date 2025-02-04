package Driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import base.BaseClass;
import io.appium.java_client.AppiumBy;


public class EnterOTPPopUp extends BaseClass {
	
	//try for right/wrong otp and various edge cases
	@Test
	public void enterCorrectOTP() throws InterruptedException {
		
		System.out.println("Ride OTP is transferred to Driver to Start Ride = "+rideOTP);
        Thread.sleep(3000);
    	driver.findElement(AppiumBy.xpath("//android.widget.EditText")).sendKeys(rideOTP);
		System.out.println("Driver entered the right otp");
		Thread.sleep(3000);
	} 
	 
	public void clickBackAfterEnteringOTP() {
		
		
	}
	
	public void enterWrongOTP() {
		
		
	}
	
	public void cancelRide() throws Exception {
		System.out.println("Entered into cancellation method");
		WebElement source = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ride Fare']"));
		
//		System.out.println("x-path located");
//				
				((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
					    "elementId", ((RemoteWebElement) source).getId(),
					    "endX", 178,
					    "endY", 1245
					));
				
//				boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: flingGesture", ImmutableMap.of(
//					    "elementId", ((RemoteWebElement) source).getId(),
//					    "direction", "up",
//					    "speed", 500
//					));
//				System.out.println("scroll completed");
				
				
				driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel Ride']")).click();
				
				Thread.sleep(8000);	
			    
			    driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
				driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Vehicle issue']")).click();
//				driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel Ride']")).click();

				}
	



}
