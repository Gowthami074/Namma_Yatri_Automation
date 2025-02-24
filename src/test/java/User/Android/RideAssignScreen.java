package User.Android;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.internal.shadowed.jackson.annotation.JacksonInject.Value;

import java.awt.Point;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import org.testng.Assert;
import org.openqa.selenium.TimeoutException;

import Utils.AssertionBase;
import base.BaseClass;

public class RideAssignScreen extends BaseClass {
	AssertionBase assertionBase = new AssertionBase();

	PopUpsHandling popUpsHandling = new PopUpsHandling();
	WebElement otpElement;
	WebElement vehicleIcon;

	@Test
	public void TollTextAtOtpSlider() {
		WebElement OtpSheetTollText = driver1
				.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Toll Charges Included']"));

		if (OtpSheetTollText.isDisplayed()) {
			System.out.println("'User Side Otp Sheet have text 'Toll Charges included ");
		} else {
			System.out.println("'Text is not validated on OTP Sheet");
		}

	}

	@Test
	public void verifyTollOverlayText() {

		WebElement OverlayText1 = driver1
				.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Toll Charges included in Fare']"));

		WebElement OverlayText2 = driver1.findElement(
				AppiumBy.xpath("//android.widget.TextView[@text='Please do not pay extra to the driver']"));

		WebElement OverlayButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ok, Got it']"));

		if (OverlayText1.isDisplayed()) {
			System.out.println(
					"'Overlay is validated at User Side and text **Toll Charges included in Fare** is validated ");
		} else {
			System.out.println("'Overlay is not validated");
		}

		if (OverlayText2.isDisplayed()) {
			System.out.println(
					"'Overlay is validated at User Side and text **Please do not pay extra to the driver** is validated ");
		} else {
			System.out.println("'Overlay is not validated");
		}

		OverlayButton.click();

		TollTextAtOtpSlider(); // Validating TOll Text at the OTP Screen

	}

	@Test
	public void readOTP() throws InterruptedException {
		implicitWaitMethod(driver1, 60);
		
	    // Use base class method to check if OTP element is visible within 30 seconds
	    String otpXpath = "//android.view.ViewGroup[contains(@content-desc, 'OTP')]";
	    assertionBase.assertElementVisible(otpXpath, "ERROR: OTP element not found within 30 seconds!", false, 30);

	    // Extract the element after assertion
	    WebElement otpElement = driver1.findElement(AppiumBy.xpath(otpXpath));
		

		
		// Extract the 'content-desc' attribute
		String contentDesc = otpElement.getAttribute("content-desc");

		// Extract only the numeric OTP using regex
		rideOTP = contentDesc.replaceAll("\\D", ""); // Removes all non-digit characters
		
	    // ** Hard assertion to ensure OTP is fetched and not empty **
		assertionBase.assertCondition(rideOTP != null && !rideOTP.isEmpty(), "ERROR: OTP was not extracted correctly!", false);


		System.out.println("Extracted OTP: " + rideOTP);

	}

	private String extractNumbers(String contentDesc) {

		// TODO Auto-generated method stub
		// Remove non-numeric characters and spaces
		return contentDesc.replaceAll("[^0-9]", "");
	}

	@Test
	public void AcceptThequote() throws InterruptedException {
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Ride']")).click();
		System.out.println("user clicked on confirmed ride");
	}

	public void componentsOnRideAssignScreen() {

		WebElement googleElement = driver1
				.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Google Map']"));
		WebElement pickupIcon = driver1
				.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Google Map']/android.view.View[1]"));
		vehicleIcon = driver1
				.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Google Map']/android.view.View[2]"));
		WebElement distanceLabel = driver1
				.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Google Map']/android.view.View[3]"));

		System.out.println("Google map, Pickup location Icon, Vehicle icon and distance lables are visible");

		WebElement viewMap = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='View Map']"));
		WebElement viewDetails = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='View Details']"));
		viewMap.click();
		viewDetails.click();
		// android.view.View[@content-desc='Google
		// Map']/../android.view.View/android.view.View[2]

	}

	private int prevX = -1; // Store previous X coordinate
	private int prevY = -1; // Store previous Y coordinate
	private static final int TOLERANCE = 5;

	@Test
	public void VehiclePosition() {

		implicitWaitMethod(driver1, 5);
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
		try {

			vehicleIcon = driver1.findElement(
					AppiumBy.xpath("//android.view.View[@content-desc='Google Map']/android.view.View[2]"));

			org.openqa.selenium.Point location = vehicleIcon.getLocation();
			int currentX = location.getX();
			int currentY = location.getY();

			// Compare current position with previous position
			if (Math.abs(prevX - currentX) == TOLERANCE && Math.abs(prevY - currentY) == TOLERANCE) {
				System.out.println("Vehicle has NOT moved.");
			} else {
				System.out.println("Vehicle moved to - X: " + currentX + ", Y: " + currentY);
			}

			// Update previous position for next comparison
			prevX = currentX;
			prevY = currentY;
		} catch (Exception e) {
			System.out.println("Vehicle icon not found on user screen");
		}
	}

}
