package User.Android;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import Main.TestingFlowInputOptions;
import base.BaseClass;
import io.appium.java_client.AppiumBy;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;

public class Rentals extends BaseClass{


	public void rentalbooking() throws InterruptedException {
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Rentals']")).click();
		String sourceAddress =	driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Trip Details']/../../android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]")).getText();
		String destinationAddress =	driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Trip Details']/../../android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]")).getText();
		System.out.println("The source and destination address are"+sourceAddress+" "+destinationAddress);

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select Package']/../android.widget.ImageView")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Got It!']")).click();


		for (int i = 1; i < 12; i++) {
			//		  Tapping on +
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select Package']/../../android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.TextView[2]")).click();
			String hours=driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select Package']/../../android.widget.LinearLayout[2]/android.widget.TextView")).getText();
			String distance= driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='-']/../../../android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.TextView[2]")).getText();
			System.out.println("For "+i+"st Tap"+" "+hours+" "+distance+" Selected.");


		}
		System.out.println("Tapping on --");
		for (int i = 4; i > 1; i--) {
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='-']")).click();
		}
		System.out.println("Tapping on Continue location");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		Thread.sleep(2000);

		System.out.println("Tapping on Confirm Location");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();
		Thread.sleep(2000);

		WebElement chooseRide = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Rides for you']"));
		scroll(chooseRide);
		System.out.println("Scrolling completed");
		// locate all variants
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Non-AC Mini']")).click();

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sedan']")).click();
		System.out.println("Clicked on Sedan");
		
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='XL Cab']")).click();
		System.out.println("Clicked on XL");

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='AC Mini']")).click();
		System.out.println("Clicked on Ac mini");	
		
		
		System.out.println("Clicked on all variants");
		EstimateScreen obj= new EstimateScreen();	
		obj.slideToBookRental();
	
		// performing slide to book
		System.out.println("performing slide to booking function");             
	}
	public static void scroll(WebElement element) {
		// Get the screen size
		Dimension screenSize = driver1.manage().window().getSize();
		int screenHeight = screenSize.getHeight();
		int screenWidth = screenSize.getWidth();
		System.out.println(screenHeight);
		System.out.println(screenWidth);
		// Calculate the y-coordinate for 50% of the screen height
		int scrollToY = screenHeight / 2;
		System.out.println(scrollToY);
		// Get the element's location
		Point elementLocation = element.getLocation();
		int elementX = elementLocation.getX();
		int elementY = elementLocation.getY();
		// Perform the scroll action using JavaScript
		((JavascriptExecutor) driver1).executeScript("mobile: dragGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"endX", elementX,
				"endY", scrollToY
				));

	}

}
