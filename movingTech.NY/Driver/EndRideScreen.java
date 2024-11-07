package Driver;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class EndRideScreen extends BaseClass {
	WebElement endRideButton;
	boolean endRideSuccess = false;

	PopUpsHandling popUpsHandling = new PopUpsHandling();
	// @Test
	// public void clickEndRide() throws InterruptedException {
	// 	System.out.println("Checking Whether Points Overlay is displaying");
	// 	popUpsHandling.pointsOverLay();
	// 	System.out.println("Coming to tap on the EndRide");
	// 	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='End Ride']")).click();
	// 	System.out.println("Waiting to tap on the Endride in the pop up");
	// 	Thread.sleep(3000);
	// 	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='End Ride : Button']")).click();
	// 	Thread.sleep(3000);
	// 	popUpsHandling.takeUnlimitedRidesPopUp();
	// 	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Fare Collected']")).click();
	// 	Thread.sleep(2000);
	//	}
	@Test
	public void clickEndRide() throws InterruptedException {

		System.out.println("Coming to tap on the EndRide");
		while (!endRideSuccess) {
			implicitWaitMethod(driver,5);
		    try {
		        // Wait for up to 5 seconds for the "End Ride" button to appear
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		        endRideButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='End Ride']")));
		        
		        // Attempt to click the "End Ride" button
		        endRideButton.click();
		        
		        // If an alternate "End Ride" confirmation is needed, click it
		        WebElement confirmEndRideButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@content-desc='End Ride : Button']")));
		        confirmEndRideButton.click();
		        
		        System.out.println("Clicked on 'End Ride : Button'");
		        endRideSuccess = true;  // Break loop if successful

		    } catch (Exception e) {
		        System.out.println("Retrying 'End Ride' button click...");
		    }
		}
		implicitWaitMethod(driver,60);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Fare Collected']")).click();

	}



	public void clickMaps() { 


	}

	public void verifyRideFareAndRideDistance() {


	}

	public void clickEndRideAndGoBack() throws InterruptedException {
		//End Ride from Go back option
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Go Back']/../following-sibling::android.widget.LinearLayout/android.widget.TextView")).click();		
		System.out.println("The ride is completed and ended");
		Thread.sleep(4000);
	}
}