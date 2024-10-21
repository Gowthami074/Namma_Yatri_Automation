package Driver;

import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class EditPickupDestRequest extends BaseClass {
	AndroidBackButton Backbutton= new AndroidBackButton();
	@Test
	public void handleeditpickuppopup() throws InterruptedException {
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Update Navigation']")).click();
		Thread.sleep(3000);
		System.out.println("Driver updated the edit pickup location");
		Backbutton.tapBack();
	}
	@Test
	public void handleeditdestpopup() throws InterruptedException {
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Accept & Navigate']")).click();
		Thread.sleep(3000);
		System.out.println("Driver accepeted the edit destination request");
		Backbutton.tapBack();	
	}

}
