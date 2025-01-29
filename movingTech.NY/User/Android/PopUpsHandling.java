package User.Android;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import io.appium.java_client.AppiumBy;
import io.netty.handler.timeout.TimeoutException;

public class PopUpsHandling extends BaseClass{

	public void googleServicePhoneNumberAutofill() {
		implicitWaitMethod(driver1,5);
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));

		try {
			// Check if the Google autofill overlay pop-up is displayed
			WebElement googleServicePopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(
					AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='com.google.android.gms:id/design_bottom_sheet']/android.widget.LinearLayout")
					));

			if (googleServicePopUp.isDisplayed()) {
				System.out.println("Autofill overlay is displayed. Closing it now.");

				// Tap the "X" button to close the pop-up
				WebElement closeButton = driver1.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Cancel']"));
				closeButton.click();

				System.out.println("Autofill overlay closed successfully.");
			}
		} catch (Exception e) {
			System.out.println("Autofill overlay did not appear, continuing test.");
		}
		implicitWaitMethod(driver1,60);
	}
	
	public void AC_PopUp() {
		implicitWaitMethod(driver1,5);
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
		try {
			WebElement acPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Has your driver set the AC as per your preference? ']")));
			if(acPopUp.isDisplayed()) {
				driver1.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='Got it']")).click();
				System.out.println("Tapped on Yes in the AC Pop Up");
			}
			
		} catch (Exception e) {
			System.out.println("AC overlay did not appear, continuing test.");
		}
		
		implicitWaitMethod(driver1,60);
	}
	
	public void parkingChargesPopUp() {
		implicitWaitMethod(driver1,5);
		try {
			driver1.findElement(AppiumBy.xpath("//android.Button.TextView[@text='Got it']"));
			driver1.findElement(AppiumBy.xpath("//android.Button.TextView[@text='Got it']")).click();
			System.out.println("Parking Charges Pop Up is displaying");
		}catch(Exception e) {
			System.out.println("Parking Charges Pop Up is not displaying");
		}
		implicitWaitMethod(driver1,60);
	}
}

