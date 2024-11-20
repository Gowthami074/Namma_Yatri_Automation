package Driver;

import java.time.Duration;
import java.util.Iterator;
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
	@Test
	public void clickEndRide() throws InterruptedException {

		System.out.println("Coming to tap on the EndRide");
		int maxRetries = 3;
		int retryCount = 0;

		while (!endRideSuccess && retryCount < maxRetries) { // Add a retry limit
			implicitWaitMethod(driver, 5);
			try {
				// Wait for the "End Ride" button to appear
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				endRideButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='End Ride']")));
				endRideButton.click();

				// Wait for and click the confirmation button
				WebElement confirmEndRideButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
						AppiumBy.xpath("//android.widget.TextView[@content-desc='End Ride : Button']")
						));
				confirmEndRideButton.click();

				System.out.println("Clicked on 'End Ride : Button'");
				endRideSuccess = true; // Mark success to exit loop

			} catch (Exception e) {
				System.out.println("Retrying 'End Ride' button click...");
				retryCount++; // Increment retry count on failure
				if (retryCount >= maxRetries) {
					System.out.println("Exceeded maximum retry attempts.");
					break; // Exit loop if max retries reached
				}
			}
		}

		if (!endRideSuccess) {
			System.out.println("Failed to end ride after maximum retries.");
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