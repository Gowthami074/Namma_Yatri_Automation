package User.Android;

import base.BaseClass;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;

public class UserCancellation extends BaseClass{


    @Test
	public void userCancellation() throws InterruptedException {
		
		System.out.println("Entered into cancellation class");
		
		WebElement element1 = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Pay by Cash / UPI ']"));
		Thread.sleep(5000);
		scroll(element1);
		System.out.println("Scroll completed");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel Ride']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc = 'Cancel Ride : Button']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Driver is not moving']/../..//android.widget.LinearLayout[1]")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc = 'Cancel Ride : Button']")).click();
		Thread.sleep(8000);		
  
	}
	
	public static void scroll(WebElement quotesElement) {
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
        Point elementLocation = quotesElement.getLocation();
        int elementX = elementLocation.getX();
        int elementY = elementLocation.getY();
        // Perform the scroll action using JavaScript
        ((JavascriptExecutor) driver1).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) quotesElement).getId(),
                "endX", elementX,
                "endY", scrollToY
        ));
		
		}

}
