package Driver;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class PopUpsHandling extends BaseClass{
    
	DevicePermission devicePermission = new DevicePermission();
	
	public void upiOverlayPopUp() {
		// Temporarily reduce implicit wait before using explicit wait
		implicitWaitMethod(driver,5); //This method is defined in BaseClass
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Set a small explicit wait time
		
		try {
			// Wait for 'Later' button (UPI Overlay)
			WebElement laterButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Later']")));
			System.out.println("Skipping UPI Overlay");
			laterButton.click();


		} catch (TimeoutException e) {
			System.out.println("No UPI Overlay found");
		}
		// Temporarily reduce implicit wait before using explicit wait
		implicitWaitMethod(driver,60);
	}

	public void yatriCoinsOverlayPopUp() {
		implicitWaitMethod(driver,5); //This method is defined in BaseClass
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			// Wait for 'Later' button (UPI Overlay)
			WebElement yatriCoinsOkayButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Okay']")));
			System.out.println("Skipping Yatri Coins Overlay");
			yatriCoinsOkayButton.click();


		} catch (TimeoutException e) {
			System.out.println("No Yatricoins Overlay found");
		}
		implicitWaitMethod(driver,60);
		
	}
	
	public void pointsOverLay() {
		implicitWaitMethod(driver,5); //This method is defined in BaseClass
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			// Wait for 'Maybe Later' button (Points Overlay)
			WebElement maybeLaterButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Maybe Later']")));
			System.out.println("Clicking on may be later of Points Overlay");
			maybeLaterButton.click();
		} catch (TimeoutException e) {
			System.out.println("No Points Overlay found");
		}
		// Temporarily reduce implicit wait before using explicit wait
		implicitWaitMethod(driver,60);
	}
	
	public void locationPermissionPopUp() throws InterruptedException {
		implicitWaitMethod(driver,5);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			WebElement enablePermission = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Maybe Later']")));
			System.out.println("Enabling Location Permission");
			enablePermission.click();
			devicePermission.locationPermission();
            
		} catch(TimeoutException e) {
			System.out.println("No need to give the Location permission seperately for this device");
		}
		implicitWaitMethod(driver,60);
		
	}

	public void driverModeValidation() throws InterruptedException {
		implicitWaitMethod(driver,5);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Adjust the timeout as needed

		try {
			// Wait for 'GO!' button to appear (Driver is offline)
			WebElement goButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='GO!']")));
			System.out.println("The driver is offline");
			goButton.click();

		} catch (TimeoutException e1) {
			try {
				// Wait for 'Silent' button (Driver is silent)
				WebElement silentButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Silent']/../android.widget.ImageView")));
				System.out.println("The Driver is Silent");

				// Now wait for 'Online' button and click it
				WebElement onlineButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Online']")));
				onlineButton.click();

			} catch (TimeoutException e2) {
				System.out.println("The driver is online");
			}
			implicitWaitMethod(driver,60);
		}
	}
	
	public void takeUnlimitedRidesPopUp() {
		implicitWaitMethod(driver,5);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			WebElement enablePermission = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Got it']")));
			enablePermission.click();
			System.out.println("Tapping on GotIt option in Take unlimited rides PopUp screen");
            
		} catch(TimeoutException e) {
			System.out.println("UnlimitedRidesPopup is not displaying");
		}
		implicitWaitMethod(driver,60);
		
	}
	public void DueHandlingPopUp() {
	    implicitWaitMethod(driver, 5);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    int retryCount = 0;
	    int maxRetry = 3; // Maximum number of retries

	    try {
	        
	        WebElement ClearDueText = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                AppiumBy.xpath("//android.widget.TextView[@text='Clear your dues now to enjoy non-stop rides.']")));

	        if (ClearDueText.isDisplayed()) {
	            System.out.println("Due pop-up is validated on Home Screen");

	            // Get the due of the Due amount or message
	            WebElement GetTextOfDue = driver.findElement(AppiumBy.xpath(
	                    "//android.widget.TextView[@text='Clear your dues now to enjoy non-stop rides.']/../../android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView"));
	            String dueText = GetTextOfDue.getText();
	            System.out.println("Due info on Pop Up Screen--> " + dueText);

	            // Retry loop for clicking Home and checking if the pop-up is removed
	            while (retryCount < maxRetry) {
	                // Click on the Home button
	                WebElement Home = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Home']"));
	                Home.click();
	                Thread.sleep(2000); // Wait for the pop-up to disappear

	                // Checking if the Due Pop-Up is removed
	                boolean isPopUpRemoved = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Clear your dues now to enjoy non-stop rides.']")).isEmpty();

	                if (isPopUpRemoved) {
	                    System.out.println("Due pop-up is removed");
	                    break;
	                } else {
	                    System.out.println("Due pop-up is still present. Retrying... (" + (retryCount + 1) + ")");
	                    retryCount++;
	                }
	            }

	            // If maximum retries reached and pop-up is still present
	            if (retryCount == maxRetry) {
	                System.out.println("Due pop-up is still present after maximum retries.");
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("No DUE pop-up is validated. Moving to the next method.");
	    }

	    implicitWaitMethod(driver, 60);
	}
	
	public void AddUpI() {
		implicitWaitMethod(driver, 5);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    int retryCount = 0;
	    int maxRetry = 3; // Maximum number of retries

	    try {
	    	 WebElement AddUpi = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                AppiumBy.xpath("//android.widget.TextView[@text='Add UPI detail to receive referral reward amount']")));

		        if (AddUpi.isDisplayed()) {
		            System.out.println("Add UPI pop-up is validated on Home Screen");
		            
		            while (retryCount < maxRetry) {
		                // Click on the AddUPILater button
		                WebElement AddUpiLater = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Later']"));
		                AddUpiLater.click();
		                Thread.sleep(2000);
		                
		                boolean isPopUpRemoved = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Add UPI detail to receive referral reward amount']")).isEmpty();
		                
		                if (isPopUpRemoved) {
		                    System.out.println("Add UPI pop-up is removed");
		                    break;
		                } else {
		                    System.out.println("Add UPI pop-up is still present. Retrying... (" + (retryCount + 1) + ")");
		                    retryCount++;
		                }
		            }

		            // If maximum retries reached and pop-up is still present
		            if (retryCount == maxRetry) {
		                System.out.println("Add UPI pop-up is still present after maximum retries.");
		            }
		        }
		    } catch (Exception e) {
		        System.out.println("No Add UPI pop-up is validated. Moving to the next method.");
		    }

		    implicitWaitMethod(driver, 60);

		                
		                
		                
		                
	}
	
    public void DisablePopUp() {
    	implicitWaitMethod(driver, 5);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    int retryCount = 0;
	    int maxRetry = 3; // Maximum number of retries

	    try {
	    	 WebElement AssistancePopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                AppiumBy.xpath("//android.widget.TextView[@text='Customer may need assistance']")));

		        if (AssistancePopUp.isDisplayed()) {
		            System.out.println("Disable pop-up is validated on Home Screen");
		            
		            while (retryCount < maxRetry) {
		                // Click on the AddUPILater button
		                WebElement GotIt = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Got It!']"));
		                GotIt.click();
		                Thread.sleep(2000);
		                
		                boolean isPopUpRemoved = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Customer may need assistance']")).isEmpty();
		                
		                if (isPopUpRemoved) {
		                    System.out.println("Disable pop-up is removed");
		                    break;
		                } else {
		                    System.out.println("Disable pop-up is still present. Retrying... (" + (retryCount + 1) + ")");
		                    retryCount++;
		                }
		            }

		            // If maximum retries reached and pop-up is still present
		            if (retryCount == maxRetry) {
		                System.out.println("Disable pop-up is still present after maximum retries.");
		            }
		        }
		    } catch (Exception e) {
		        System.out.println("No Disable pop-up is validated. Moving to the next method.");
		    }

		    implicitWaitMethod(driver, 60);
		
	}

	}


