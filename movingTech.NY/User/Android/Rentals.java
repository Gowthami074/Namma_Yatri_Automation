package User.Android;
import java.awt.Point;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class Rentals extends BaseClass {
	@Test
	public void rentalbooking() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(30));
		// Wait for "Rentals" section to appear
		WebElement rentalsText = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.TextView[@text='Rentals']")
				));
		System.out.println("Rentals section is now visible.");
		rentalsText.click();    
		System.out.println("Tapping on ++");
		for (int i = 1; i < 4; i++) {
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='+']")).click();
		}
		System.out.println("Tapping on --");
		for (int i = 4; i > 1; i--) {
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='-']")).click();
		}
		System.out.println("Tapping on Continue location");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		Thread.sleep(8000);

		System.out.println("Tapping on Confirm Location");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();
		Thread.sleep(18000);

		WebElement chooseRide = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Rides for you']"));
		scroll(chooseRide);
		System.out.println("Scrolling completed");
		// Dynamically locate all variants
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Non-AC Mini']")).click();
		System.out.println("Clicked on Non ac mini");

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sedan']")).click();
		System.out.println("Clicked on Sedan");

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
		org.openqa.selenium.Point elementLocation = element.getLocation();
		int elementX = (int) elementLocation.getX();
		double elementY = elementLocation.getY();
		// Perform the scroll action using JavaScript
		((JavascriptExecutor) driver1).executeScript("mobile: dragGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"endX", elementX,
				"endY", scrollToY
				));
	}
}

