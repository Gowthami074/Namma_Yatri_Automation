package Driver;

import base.BaseClass;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import User.Android.PopUpsHandling;
import com.google.common.collect.ImmutableMap;

public class DriverCancellation extends BaseClass {
	PopUpsHandling popUpsHandling = new PopUpsHandling();
	int iterationCount = 0;

	@Test
	public void cancelRide() throws InterruptedException {
		Thread.sleep(5000);
		popUpsHandling.parkingChargesPopUp();
		System.out.println("Driver cancel about to start");
		WebElement scrollableElement = driver
				.findElement(AppiumBy.xpath("//android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]"));

		scroll(scrollableElement, true);

		// Ride Cancellation Action
		WebElement cancelRideButton = driver
				.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel Ride']"));
		cancelRideButton.click();

		Thread.sleep(5000);

		WebElement continueButton = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']"));
		continueButton.click();

		Thread.sleep(2000);

		WebElement vehicleIssueOption = driver
				.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Vehicle issue']"));
		vehicleIssueOption.click();

		Thread.sleep(2000);

		WebElement confirmCancelButton = driver
				.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Cancel Ride : Button\"]"));
		confirmCancelButton.click();

		WebElement homeButton = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Home']"));
		Assert.assertTrue(homeButton.isDisplayed(), "Home button is not displayed.");
		homeButton.click();

		// Print confirmation message
		System.out.println("Ride is cancelled and driver is navigated to Home Screen");
	}

	public void scroll(WebElement quotesElement, Boolean cancelRideCheck) {
		// Get the screen size
		Dimension screenSize = driver.manage().window().getSize();
		int screenHeight = screenSize.getHeight();
		int screenWidth = screenSize.getWidth();
		System.out.println(screenHeight);
		System.out.println(screenWidth);
		// Calculate the y-coordinate for 50% of the screen height
		int scrollToY = screenHeight / 12;
		System.out.println(scrollToY);
		// Get the element's location
		Point elementLocation = quotesElement.getLocation();
		int elementX = elementLocation.getX();
		int elementY = elementLocation.getY();
		// Perform the scroll action using JavaScript
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) quotesElement).getId(), "endX", elementX, "endY", scrollToY));
		if (cancelRideCheck == true) {
			implicitWaitMethod(driver, 10);
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				WebElement cancelRide = wait.until(ExpectedConditions
						.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Cancel Ride']")));
				System.out.println("Cancel ride button is displaying");
				implicitWaitMethod(driver, 60);
			} catch (Exception e) {
				System.err.println("Cancel ride is not displaying");
				if (iterationCount < 3) {
					iterationCount++;
				} else {
					throw e;
				}
			}
		}
	}
}
