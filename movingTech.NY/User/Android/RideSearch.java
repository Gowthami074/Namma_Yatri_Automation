package User.Android;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class RideSearch extends BaseClass{

	public void SearchForRideFromSuggestion() throws InterruptedException {
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where to?']")).click();
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Where to?']")).sendKeys("Tumu");
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Tumukuru']")).click();
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();

	}


}