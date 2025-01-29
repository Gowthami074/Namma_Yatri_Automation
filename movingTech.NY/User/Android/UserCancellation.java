package User.Android;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebElement;


public class UserCancellation extends BaseClass{

	int iterationCount = 0;

	@Test
	public void userCancellation() throws InterruptedException {

		System.out.println("Trying to cancel from user side");

		WebElement element1 = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Chatting with \"]"));
		Thread.sleep(5000);
		scroll(element1,true);
		System.out.println("Scroll completed");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel ride']")).click();
		cancelRideConfirmationPopUp();
		popUpDismisser();
		Thread.sleep(5000);
		System.out.println("Trying to tap on Cancel Ride");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel ride']")).click();
		System.out.println("Trying to tap on Cancel Anyway");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Cancel Anyway\"]")).click();	
		cancellationReasonPopUp(true);
		System.out.println("Trying to tap on Go Back");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Go Back\"]")).click();
		System.out.println("Trying to tap on Cancel Ride");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel ride']")).click();
		System.out.println("Trying to tap on Cancel Anyway");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Cancel Anyway\"]")).click();	
		cancellationReasonPopUp(false);
	}

	public void scroll(WebElement quotesElement,Boolean cancelRideCheck) {
		// Get the screen size
		Dimension screenSize = driver1.manage().window().getSize();
		int screenHeight = screenSize.getHeight();
		int screenWidth = screenSize.getWidth();
		System.out.println(screenHeight);
		System.out.println(screenWidth);
		// Calculate the y-coordinate for 50% of the screen height
		int scrollToY = screenHeight/12;
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
		if(cancelRideCheck ==true)
		{implicitWaitMethod(driver1,10);
		try {
			WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
			WebElement cancelRide = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Cancel ride']")));
			System.out.println("Cancel ride button is displaying");
			implicitWaitMethod(driver1,60);
		}	
		catch(Exception e) {
			System.err.println("Cancel ride is not displaying");
			WebElement fareEstimate =  driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Fare estimate\"]"));
			scroll(fareEstimate,true);
			if(iterationCount<3) {
				iterationCount++;
			}
			else {
				throw e;
			}
		}
	}}


	public void popUpDismisser() {
		implicitWaitMethod(driver1,10);

		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
		try
		{WebElement dismissCancelRideConfirmationPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"Icon\"])[9]/"
				+ "com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")));

		System.out.println("Cancelling the popUp by X Icon");
		dismissCancelRideConfirmationPopUp.click();
		}catch(Exception e) {
			try {
				WebElement dismissCancelRideConfirmationPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"Icon\"])[8]/"
						+ "com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")));
				dismissCancelRideConfirmationPopUp.click();

			}catch(Exception e1) {
				throw e1;

			}
		}
		implicitWaitMethod(driver1,60);
	}

	public void cancelRideConfirmationPopUp() {
		try
		{WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
		WebElement cancelRideConfirmationPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Do you really wish to cancel "
				+ "or would you like us to find another driver instead?\"]")));
		System.out.println("Cancel Ride Confirmation Pop Up is displaying");
		}catch(Exception e) {
			throw e;
		}

	}

	public void cancellationReasonPopUp(Boolean validatingReasonsAreDisplaying) throws InterruptedException {
		if(validatingReasonsAreDisplaying==true) {
			try {
				WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
				WebElement cancellationReasonPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Let us know "
						+ "the reason for cancellation\"]")));
				System.out.println("Cancellation Reason PopUp is displaying");
			}catch(TimeoutException e) {
				System.err.println("Cancellation Reason PopUp screen is not displaying");
			}
		}
		else 
		{
			String parentXPath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]"
					+ "/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup"
					+ "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
					+ "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
					+ "/android.view.ViewGroup[4]/android.view.ViewGroup/android.view.ViewGroup[2]"
					+ "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup";

			// Find all cancellation reasons dynamically
			List<WebElement> reasons = driver1.findElements(AppiumBy.xpath(parentXPath + "/*"));
			System.out.println("Total cancellation reasons found: " + (reasons.size()-2));

			// Iterate through all reasons (just highlighting without selection)
			for (int i = 2; i <=reasons.size(); i++) {
				String reasonXPath = parentXPath + "/android.view.ViewGroup[" + (i-1) + "]";
				WebElement reason = driver1.findElement(AppiumBy.xpath(reasonXPath));
				System.out.println("Iterating through reason " + i);
				reason.click();
				if(i==reasons.size()) {
					try {
						WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));
						WebElement reasonNotListed = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Please elaborate "
								+ "on your issue\"]")));
						if(reasonNotListed.isDisplayed())
						{	

							try
							{	
								WebElement goBack = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Go Back\"]")));
								goBack.click();

							}catch(NoSuchElementException e) {
								try
								{System.out.println("Go Back option is not displaying and Trying to cancel the pop up By X icon");
								popUpDismisser();
								cancelRideConfirmationPopUp();
								driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel ride']")).click();

								}catch(NoSuchElementException e1) {
									throw e1;
								}

							}					
						}
					}catch(Exception e) {
						continue;
					}
				}
			}

			// Select the first reason
			if (!reasons.isEmpty()) {
				System.out.println("Control enters here11");
				WebElement reasonXPath = driver1.findElement(AppiumBy.xpath(parentXPath + "/android.view.ViewGroup["+2+"]"));
				reasonXPath.click();
				System.out.println("First cancellation reason selected.");
			} else {
				System.out.println("No cancellation reasons found.");
				return;
			}

			// Tap on the "Submit" button
			try {
				WebElement submitButton = driver1.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Submit\"]"));
				if (submitButton.isEnabled()) {
					submitButton.click();
					System.out.println("Submit button clicked.");
				} else {
					System.out.println("Submit button is disabled.");
				}
			} catch (Exception e){
				System.out.println("Submit button not found: " + e.getMessage());
			}
		}

	}}
