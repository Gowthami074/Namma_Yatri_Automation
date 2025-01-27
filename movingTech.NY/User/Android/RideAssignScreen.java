package User.Android;

import io.appium.java_client.AppiumBy;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import base.BaseClass;

public class RideAssignScreen extends BaseClass {

	//about chat or messaging feature 
	// also about cancel ride 
	// write about all possible ways like contct customer /sos


	public void cancelRide() {



	}

	public void clickEmergencyHelp() {


	}

	public void clickCustomerSupportCall() {


	}

	@Test
	public void TollTextAtOtpSlider() {
		WebElement OtpSheetTollText = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Toll Charges Included']"));

		if (OtpSheetTollText.isDisplayed()) {
			System.out.println("'User Side Otp Sheet have text 'Toll Charges included ");
		} else {
			System.out.println("'Text is not validated on OTP Sheet");
		}

	}

	@Test
	public void verifyTollOverlayText() {

		WebElement OverlayText1 = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Toll Charges included in Fare']"));

		WebElement OverlayText2 = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Please do not pay extra to the driver']"));

		WebElement OverlayButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ok, Got it']"));


		if (OverlayText1.isDisplayed()) {
			System.out.println("'Overlay is validated at User Side and text **Toll Charges included in Fare** is validated ");
		} else {
			System.out.println("'Overlay is not validated");
		}

		if (OverlayText2.isDisplayed()) {
			System.out.println("'Overlay is validated at User Side and text **Please do not pay extra to the driver** is validated ");
		} else {
			System.out.println("'Overlay is not validated");
		}

		OverlayButton.click();

		TollTextAtOtpSlider(); //Validating TOll Text at the OTP Screen

	}




	@Test
	public void readOTP() throws InterruptedException {

		parkingChargesPopUp();
		String OTP =  driver1.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='Hamburger Menu']/ancestor::android.view.ViewGroup//android.widget.TextView[contains(@text, 'OTP')]")).getText();
		rideOTP = OTP.replace("OTP •", "").trim();
		System.out.println("OTP " + rideOTP);
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
	public void parkingChargesPopUp() {
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Got it']")).click();
	}
	public void componentsOnRideAssignScreen() {

		WebElement googleElement = driver1.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Google Map']"));
		WebElement pickupIcon = driver1.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Google Map']/android.view.View[1]"));
		WebElement vehicleIcon = driver1.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Google Map']/android.view.View[2]"));
		WebElement distanceLabel = driver1.findElement(AppiumBy.xpath("//android.view.View[@content-desc='Google Map']/android.view.View[3]"));
		
		System.out.println("Google map, Pickup location Icon, Vehicle icon and distance lables are visible");
		
		WebElement viewMap = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='View Map']"));
		WebElement viewDetails = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='View Details']"));
		viewMap.click();
		viewDetails.click(); 



	}
}
