package Driver;

import io.appium.java_client.AppiumBy;
import base.BaseClass;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StartRideScreen extends BaseClass { 
    
    //can write about start or cancel ride
    //also messaging, calling, opening maps, click I've arrived
	@Test
    public void startRide() throws InterruptedException {
        // Set Implicit Wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        
        // Tap the element
//        WebElement elementToTap = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Wait Time']/../android.widget.ImageView"));
//        elementToTap.click();
//        
//        // Since Implicit Wait is set, you don't need explicit waits here
//        // The driver will wait up to 10 seconds for each element to be found
//
//        // Locate the element containing the text to validate
//        WebElement textElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='The customer will pay ₹1.5 for every minute of wait time after first 3 minutes']"));
//        
//        // Get the text from the element
//        String actualText = textElement.getText();
//        
//        // Expected text
//        String expectedText = "The customer will pay ₹1.5 for every minute of wait time after first 3 minutes";
//        
//        // Validate the text
//        Assert.assertEquals(actualText, expectedText, "The text does not match the expected value.");
//        
//        // Print confirmation message
//        System.out.println("Text validation successful: " + actualText);
//
//        // Tapping on Got it
//        WebElement GotIt = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Got It!']"));
//        GotIt.click();
        
        // Starting Ride
        System.out.println("Ride OTP is transferred to Driver to Start Ride = " + rideOTP);
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Start Ride']")).click();
        Thread.sleep(4000);
    }
	
	public void tollRideStartRideScreen() {
		
        
    }
    
    public void cancelRide() {
        // Add cancel ride logic
    }
    
    public void clickIHaveArrived() {
        // Add logic for clicking 'I Have Arrived'
    }
    
    public void iHaveArriv() {
        // Add logic for 'I Have Arrived'
    }
    
    public void clickOnCall() {
        // Add logic for clicking on call
    }
    
    public void verifyRideFareAndRideDistance() {
        // Add logic for verifying ride fare and distance
    }
}
