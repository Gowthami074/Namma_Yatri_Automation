package User.Android;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.netty.handler.timeout.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import base.BaseClass;
import Driver.DriverprofileScreen;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import User.Android.EstimateScreen;
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
			driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Book Any']/../android.view.ViewGroup")).click();
			getVehicleVariantFromDriverProfile();
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

			
			WebElement topElement = driver1.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"" + vehicleVariantText + "\"));"
			));
			WebElement variant= driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + vehicleVariantText + "']"));			
			variant.click();
			System.out.println(vehicleVariantText + " variant slected ");
			Thread.sleep(3000);
		}


	}
	@Test
	public void slideToBook() throws InterruptedException {
		WebElement sliderBar;
		WebElement orangeButton;
		orangeButton = driver1.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Go back']/../../../android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]"));
		sliderBar = driver1.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Go back']/../../../android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
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
		System.out.println("Searching initiated: Looking for an awesome ride.");
	}
	
	//slide to book for intercity
	public void slideToBookIntercity() throws InterruptedException {
		getVehicleVariantFromDriverProfile();
		WebElement variant= driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + vehicleVariantText + "']"));			
		variant.click();
		implicitWaitMethod(driver1 , 5);
		try {
		
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Got it']")).click();
		
		}
		catch(Exception e) {
		System.out.println("No rate card popup while selecting estimates");
			
		}
		finally
		{
			WebElement sliderBar = driver1.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[3]"));

			// Locate the orange car button (element1)
			WebElement orangeButton = driver1.findElement(AppiumBy.xpath("(//android.widget.ImageView[@content-desc='Icon'])[5]/com.horcrux.svg.SvgView"));
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
		System.out.println("Done with slide to book for intercity ride");
		implicitWaitMethod(driver1 ,60);

	}
	
	
	
	public void slideToBookRental() throws InterruptedException {
		getVehicleVariantFromDriverProfile();
		WebElement variant= driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + vehicleVariantText + "']"));			
		variant.click();
		implicitWaitMethod(driver1 , 5);
		try {
		
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Got it']")).click();
		
		}
		catch(Exception e) {
		System.out.println("No rate card popup while selecting estimates");
			
		}
		finally
		{
			WebElement sliderBar = driver1.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"));
			WebElement orangeButton = driver1.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]"));
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
		System.out.println("Done with slide to book for Rental ride");
		implicitWaitMethod(driver1 ,60);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//For Tip Add cases 
    
    @Test
    public void AddingTipFromEstimateScreen() throws InterruptedException {
    	
    	System.out.println("Geeting In For Tip Check at Estimate Screen");
        WebElement WishToAddTipText;
        WebElement AddTipText;

        try {
            WishToAddTipText = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text = 'Wish to add a tip?']"));
            System.out.println("**Tip Test case PASS::WishToAddTipText element found.**");
        } catch (NoSuchElementException e) {
            System.out.println("**Tip Test case FAIL:: WishToAddTipText element not found: " + e.getMessage());
        }

        try {
            AddTipText = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text = 'Add Tip']"));
            System.out.println("**Tip Test case PASS::AddTipText element found.**");
        } catch (NoSuchElementException e) {
            System.out.println("**Tip Test case FAIL::AddTipText element not found: " + e.getMessage());
        }

        // Tapping on 'Add Tip' and accessing the tips layout
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text = 'Add Tip']")).click();

        WebElement TipsLayOut = driver1.findElement(AppiumBy.xpath(
            "//android.widget.TextView[@text = 'Select tip']/../..//following-sibling::android.widget.HorizontalScrollView/android.view.ViewGroup"));

        // Find all the child TextViews within the ViewGroup
        List<WebElement> tipTexts = TipsLayOut.findElements(AppiumBy.className("android.widget.TextView"));

        // StringBuilder to format the output
        StringBuilder formattedOutput = new StringBuilder();
        System.out.println("Below are the options displayed after tapping on 'Add Tip':");

        // Iterate through the TextViews and build the formatted string
        for (WebElement tipText : tipTexts) {
            String text = tipText.getText();
            if (!text.isEmpty()) { // Ignore empty texts
                formattedOutput.append(text).append(" ");
            }
        }

        // Print the formatted output, trimming the trailing space
        System.out.println(formattedOutput.toString().trim());

     // Define XPaths
        String tipOptionsXPath = "//android.widget.ImageButton[@content-desc='Go back']/../../following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.HorizontalScrollView/android.view.ViewGroup/android.widget.Button";
        String addChangeTipButtonXPath = "//android.widget.ImageButton[@content-desc='Go back']/../../../android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup[2]";
        String gettingTextAfterTipSelectedXPath = "//android.widget.ImageButton[@content-desc='Go back']/../../../android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.TextView";

        //  explicit wait
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));

        // Get the initial list of tip options
        List<WebElement> tipOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.xpath(tipOptionsXPath)));

     // Print the initial count of tip options
     System.out.println("Number of tip options available: " + tipOptions.size());

     // Iterate through the options
     for (int i = 0; i < tipOptions.size(); i++) {
         // Print the count again during iteration if needed
         System.out.println("Processing tip option " + (i + 1) + " of " + tipOptions.size());

         // Re-fetch the list dynamically in each iteration to avoid stale elements
         tipOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.xpath(tipOptionsXPath)));

         // Select the current tip option
         WebElement tipOption = tipOptions.get(i);
         wait.until(ExpectedConditions.elementToBeClickable(tipOption)).click(); // Tap the current tip option

         // Retrieve and print the selected tip text
         WebElement gettingTextAfterTipSelected = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath(gettingTextAfterTipSelectedXPath)));
         System.out.println("Selected Tip: " + gettingTextAfterTipSelected.getText());

         // Tap the "Add/Change Tip" button
         WebElement addChangeTipButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(addChangeTipButtonXPath)));
         addChangeTipButton.click();

         // Optional delay to handle UI transitions
         Thread.sleep(1000); // Adjust based on app responsiveness
        
        }

        System.out.println("Finished testing all tip options before select of Variant.");
    }
	

    @Test
    public void AddingTipAfterEstimateScreen() throws InterruptedException {
    	implicitWaitMethod(driver,110);// We are making driver side object to be waited for 110 Sec until tip cases are validated
    	System.out.println("Getting Inside Tip After Estimate Screen -> " + vehicleVariantText);
        try {
            WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(60));
            
            // Check if vehicle variant is "Auto" before looking for the tip selection element
            if (vehicleVariantText.equals("Auto")) {
                try {
                    WebElement tipButtonNone = driver1.findElement(AppiumBy.xpath(
                        "//android.widget.TextView[@text='None']"
                    ));
                    tipButtonNone.click();
                    System.out.println("Tip selection set to 'None'.");
                } catch (NoSuchElementException e) {
                    System.out.println("Tip selection element not found, skipping tip selection.");
                }
                
            }
            
           // Call slideToBook() method from EstimateScreen
            EstimateScreen estimateScreen = new EstimateScreen();
            estimateScreen.slideToBook();
            System.out.println("slideToBook() method executed.");
            
            // Validate the ride search screen (AFTER slideToBook action)
            WebElement rideScreenValidation = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.Button[@content-desc='Trip Details']")
            ));

            if (rideScreenValidation != null) {
                System.out.println("Ride search started.");
            } else {
                System.out.println("Ride screen validation failed: Element not found.");
                Assert.fail("Ride screen validation failed.");
            }

            // Start tracking time
            long startTime = System.currentTimeMillis();

            // Wait for "Boost Search" element
            WebDriverWait waitBoostSearch = new WebDriverWait(driver1, Duration.ofSeconds(32));
            WebElement boostSearch = waitBoostSearch.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@text='Boost Search']")
            ));

            // Assert that boostSearch is not null
            Assert.assertNotNull(boostSearch, "Boost Search element was not found!");

            // End tracking time
            long endTime = System.currentTimeMillis();
            double durationInSeconds = (endTime - startTime) / 1000.0;

            // Print duration
            System.out.println("Time taken between 'Ride search started' and 'Boost Search': " + durationInSeconds + " seconds");
            System.out.println("Boost Loader is displayed");
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred while waiting for elements.");
            System.out.println("Captured page source:\n" + driver1.getPageSource());
            Assert.fail("Timeout while waiting for elements.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception occurred: " + e.getMessage());
        }
        
       // Closing on Boost Search loader
        WebElement CrossBoostLoader = driver1.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='Icon']"));
        CrossBoostLoader.click();

        // Opening 'Trip Details' bottom sheet
        WebElement TripDetails = driver1.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='Trip Details']"));
        TripDetails.click();

        // Validate if details displayed
        WebElement FareEstimate = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Fare estimate']"));
        Assert.assertTrue(FareEstimate.isDisplayed(), "Trip details Bottom Sheet is not visible!");

        // validating and storing base fare estimate before adding a tip in number format
        WebElement BaseFareValue = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Fare estimate']/../following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")); 
        String baseFareText = BaseFareValue.getText().replaceAll("[^0-9]", ""); 
        int baseFare = Integer.parseInt(baseFareText); 
        System.out.println("Base Fare Estimate price without tip: " + baseFare);

        // Close Trip Details Bottom Sheet
        WebElement TripDetailCross = driver1.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Cancel search']"));
        TripDetailCross.click();

        // Click on 'Add' tip option from ride search screen
        WebElement AddTipOption = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add']"));
        AddTipOption.click();

        // Select a tip amount and extract only its numeric value
        WebElement SelectTipOption = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add a tip to driver']/../../following-sibling::android.view.ViewGroup[2]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.View[2]/android.view.ViewGroup/android.widget.TextView"));
        String selectedTipText = SelectTipOption.getText().replaceAll("[^0-9]", ""); 
        int selectedTip = Integer.parseInt(selectedTipText); 
        System.out.println("Selected Tip Amount: " + selectedTip);
        SelectTipOption.click();

        // Apply 'Boost Search' with tip
        WebElement BoostSearchButton = driver1.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='Boost Search, Icon']"));
        BoostSearchButton.click();

        // Check selected tip value on the screen
        WebElement SelectedTipValidation = driver1.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Change']/../../../android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView"));
        String displayedTipText = SelectedTipValidation.getText().replaceAll("[^0-9]", ""); 
        int displayedTip = Integer.parseInt(displayedTipText); 
        System.out.println("Displayed Tip Amount: " + displayedTip);

        // Validate if the selected tip matches the displayed tip
        if (selectedTip == displayedTip) {
            System.out.println("Test Passed: Selected tip is correctly displayed.");
        } else {
            System.out.println("Test Failed: Selected tip (" + selectedTip + ") does not match displayed value (" + displayedTip + ").");
        }

        // Re-open 'Trip Details' to check if the fare is updated
        TripDetails.click();

        // validating if applied tip is added in base estimate Fare
        WebElement RevisedBaseFareValue = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Fare estimate']/../following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView"));
        String revisedFareText = RevisedBaseFareValue.getText().replaceAll("[^0-9]", ""); 
        int revisedFare = Integer.parseInt(revisedFareText); 
        System.out.println("Revised Base Fare Estimate price: " + revisedFare);

        // Validate if the revised base fare is equal to the initial base fare + selected tip
        if (revisedFare == (baseFare + selectedTip)) {
            System.out.println("Test Passed: Revised base fare is correctly updated.");
        } else {
            System.out.println("Test Failed: Expected revised fare (" + (baseFare + selectedTip) + "), but found (" + revisedFare + ").");
        }
        
    
        System.out.println("Killing the app...");
        ((AndroidDriver) driver1).terminateApp("in.juspay.nammayatri.debug"); // Replace with actual package name
        Thread.sleep(3000); // Wait for app to close completely

        System.out.println("Relaunching the app...");
        ((AndroidDriver) driver1).activateApp("in.juspay.nammayatri.debug"); // Relaunch the app
        Thread.sleep(5000); // Wait for app to load fully

        

        // Try to find Selected Tip again after relaunch
        try {
            SelectedTipValidation = driver1.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Change']/../../../android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView"));
            String relaunchedDisplayedTipText = SelectedTipValidation.getText().replaceAll("[^0-9]", ""); 
            int relaunchedDisplayedTip = Integer.parseInt(relaunchedDisplayedTipText); 

            System.out.println("After relaunch, displayed tip amount: " + relaunchedDisplayedTip);

            // Validate if the selected tip persists after relaunch
            if (selectedTip == relaunchedDisplayedTip) {
                System.out.println("Test Passed: Selected tip is correctly displayed after relaunch.");
            } else {
                System.out.println("Test Failed: After relaunch, selected tip (" + selectedTip + ") does not match displayed value (" + relaunchedDisplayedTip + ").");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Test Failed: Selected Tip validation element is not displayed after relaunch.");
        }
    }
    
    }