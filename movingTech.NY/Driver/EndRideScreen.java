package Driver;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class EndRideScreen extends BaseClass {
	
	PopUpsHandling popUpsHandling = new PopUpsHandling();
	@Test
	public void clickEndRide() throws InterruptedException {
		System.out.println("Checking Whether Points Overlay is displaying");
		popUpsHandling.pointsOverLay();
		System.out.println("Coming to tap on the EndRide");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='End Ride']")).click();
		System.out.println("Waiting to tap on the Endride in the pop up");
		Thread.sleep(3000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='End Ride : Button']")).click();
		Thread.sleep(3000);
		popUpsHandling.takeUnlimitedRidesPopUp();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Fare Collected']")).click();
		Thread.sleep(2000);
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