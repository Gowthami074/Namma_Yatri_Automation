package User.Android;
import io.appium.java_client.AppiumBy;
import io.netty.handler.timeout.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import base.BaseClass;
import Driver.DriverprofileScreen;
import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
public class EstimateScreen extends BaseClass {
	@Test
	public void autoAssignDriver() throws InterruptedException {
		Thread.sleep(7000);
		System.out.println("Getting in for estimate and select variant");
		// Check for "Book Any" presence
		if (!isBookAnyPresent()) {
			System.out.println("'Book Any' option not found. Skipping to the final element.");
			tapFinalXpath();  // Skip to final tap when "Book Any" is not present
		} else {
			System.out.println("'Book Any' option found. Proceeding...");
			Thread.sleep(2000); // Handle UI transition delay
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Book Any']")).click();
			getVehicleVariantFromDriverProfile();
			variantSelectedOrNot();
			tapFinalXpath();  // Tap final element after vehicle selection
		}
	}
	// Method to check if "Book Any" is present
	private boolean isBookAnyPresent() {
		// This method returns true if "Book Any" is found, false otherwise
		return driver1.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Book Any']")).size() > 0;
	}   
	private void getVehicleVariantFromDriverProfile() throws InterruptedException {
		DriverprofileScreen driverProfile = new DriverprofileScreen();
		driverProfile.getDriverVehicleVariant();
	}
	private void tapFinalXpath() {
		// Tapping on the final XPath element
		try {

			driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Back : Button']/../../../android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]")).click();
			//        	driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Confirm Button to Proceed With Book Any']")).click(); 
			System.out.println("Tapped on the final element.");

		} catch (NoSuchElementException e) {
			System.out.println("Final element not found.");
		}
	}
	public void variantSelectedOrNot()  {
		implicitWaitMethod(driver1,5); //This method is defined in BaseClass
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5)); // Set a small explicit wait time
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Inside Book Any : " + vehicleVariantText + " Checkbox : selected ']")));
			System.out.println(vehicleVariantText +" Variant already selected");

		} catch (Exception e3) {
			System.out.println(vehicleVariantText +" Variant Unselected");
			WebElement elementUnSelectedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Inside Book Any : " + vehicleVariantText + " Checkbox : Un Selected']")));
			System.out.println(vehicleVariantText +" Variant selected Now");
			elementUnSelectedElement.click();
		}
		implicitWaitMethod(driver1,60);
	}

	@Test
	public void TollTextOnRideSearchScreen() {
		//Validated if User search Screen Have text "Toll Included in your fare"

		WebElement TollTextAtDriverSearchScreen = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Toll Charges are included in your fare']"));

		if (TollTextAtDriverSearchScreen.isDisplayed()) {
			System.out.println("'Toll Charges are included in your fare' text is validated in RideSearchScreen ");
		} else {
			System.out.println("'Toll Charges are included in your fare' text is validated in RideSearchScreen");
		}

	}

	@Test
	public void chooseBetweenMultipleDrivers() throws InterruptedException {
		//Choose between multiple drivers
		driver1.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout")).click();        
		Thread.sleep(3000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Choose between multiple drivers']")).click();
		System.out.println("User selected choose between the drievrs");
		autoAssignDriver();

		//if chosen this option automatically it will assign hence give sleep of 12-15 sec
	}

	public void confirmRideByChoosingBetweenMultipleDriverOffers() {

	}
	public void autoConfirmFirstDriverOffer() {
	}
	public void cancelAutoConfirm() {
	}
	@Test
	public void react_variantSelection() throws InterruptedException {

		String rideDistance = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Rides for you']/../android.view.ViewGroup/android.widget.TextView[1]")).getText();
		String rideDuration = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Rides for you']/../android.view.ViewGroup/android.widget.TextView[2]")).getText();
		System.out.println("Ride Distance "+rideDistance+", Ride Duration "+rideDuration);

		if (!isBookAnyPresent()) {
			System.out.println("'Book Any' option not found. Skipping to the final element.");
			slideToBook();
		} else {
			WebElement bookanyElement= driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Book Any']"));
			bookanyElement.click();
			//			getVehicleVariantFromDriverProfile();
			int startX1 = bookanyElement.getLocation().getX();
			int startY1 = bookanyElement.getLocation().getY();
			int endX1 = startX1;
			int endY1 = 0;
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");   
			Sequence lift = new Sequence(finger, 1);
			lift.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX1, startY1)); // Start position
			lift.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())); // Press
			lift.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX1, endY1)); // Move to top
			lift.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Release

			driver1.perform(Arrays.asList(lift));
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Book Any']/../android.view.ViewGroup")).click();

			WebElement variant= driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + vehicleVariantText + "']"));			
			variant.click();
			System.out.println(vehicleVariantText + " variant slected ");
			Thread.sleep(3000);
			slideToBook();
			System.out.println("Searching initiated: Looking for an awesome ride.");

		}


	}
	@Test
	public void slideToBook() throws InterruptedException {
		WebElement sliderBar;
		WebElement orangeButton;

		if (vehicleVariantText.equals("Auto")) {
			sliderBar = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Due to high demand, suggesting a tip']/../../../../android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup"));
			orangeButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Due to high demand, suggesting a tip']/../../../../android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]"));

		}
		else {
			sliderBar = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add Tip']/../../../android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup"));
			orangeButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add Tip']/../../../android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]"));

		}
		// Locate the orange car button (element1)
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Get the start and end coordinates
		int startX1 = orangeButton.getLocation().getX();
		int startY1 = orangeButton.getLocation().getY();
		int endX1 = sliderBar.getLocation().getX() + sliderBar.getSize().getWidth();
		int endY1 = sliderBar.getLocation().getY() + sliderBar.getSize().getWidth();
		// Create the sequence for the drag and drop action
		Sequence dragAndDrop = new Sequence(finger, 0);
		dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX1, startY1));
		dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX1, endY1));
		dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the action
		driver1.perform(Arrays.asList(dragAndDrop));
		Thread.sleep(5000);

	}
}
