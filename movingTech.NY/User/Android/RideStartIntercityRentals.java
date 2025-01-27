package User.Android;

import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class RideStartIntercityRentals extends BaseClass {
	
	public static String endOtp = "";
	@Test
	public void copyEndRideOtp() {
		String dynamicEndOtp = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'End OTP •')]")).getText();
		System.out.println("End ride OTP:"+ dynamicEndOtp);
		String[] splitOTP = dynamicEndOtp.split("\\•");
		endOtp = splitOTP[1].trim();
	}
	
	

}
