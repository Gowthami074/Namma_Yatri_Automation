package User.Android;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class RideStartIntercityRentals extends BaseClass {
	
	public static String endOtp = "";
	PopUpsHandling popup= new PopUpsHandling();
	
	@Test
	public void copyEndRideOtp() {
		popup.AC_PopUp();
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'End OTP •')]")));
		String dynamicEndOtp = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'End OTP •')]")).getText();
		String[] splitOTP = dynamicEndOtp.split("\\•");
		endOtp = splitOTP[1].trim();
		System.out.println("End ride OTP:"+ endOtp);
		
	}
}
