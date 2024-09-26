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

public class OTPScreen extends BaseClass {

	//about chat or messaging feature 
	// also about cancel ride 
	// write about all possible ways like contct customer /sos


	public void cancelRide() {



	}

	public void clickEmergencyHelp() {


	}

	public void clickCustomerSupportCall() {


	}

	public void verifyRideFare() {

		//using assertions
	}

	@Test
	public void readOTP() {
		// XPath to locate the TextView with the text "OTP"
		//        String xpath = "//android.widget.TextView[@text='OTP']";
		//
		//        // Find the element using XPath
		//        WebElement element = driver1.findElement(By.xpath(xpath));
		//
		//        // Get the content-desc attribute value
		//        String contentDesc = element.getAttribute("content-desc");
		//
		//        // Extract numbers from content-desc attribute
		//        rideOTP = extractNumbers(contentDesc);
		//
		//        System.out.println("OTP " + rideOTP);

		rideOTP =  driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='OTP']/../android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView")).getText();
		System.out.println("OTP " + rideOTP);
	}

	private String extractNumbers(String contentDesc) {

		// TODO Auto-generated method stub
		// Remove non-numeric characters and spaces
		return contentDesc.replaceAll("[^0-9]", "");
	}

}
