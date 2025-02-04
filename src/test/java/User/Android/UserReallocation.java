package User.Android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import TestSuite.Android;
import base.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;


import java.net.MalformedURLException;
import java.net.URL;

public class UserReallocation extends BaseClass {

	@Test    
    public void UserCancel_Reallocation() throws InterruptedException{		
		System.out.println("User is about to start Cancel the RideSearch");
		Thread.sleep(3000);
		driver1.navigate().back();
		
		WebElement CancelCrossButton = driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Cancel Search : Button']"));
		CancelCrossButton.click();
		
		WebElement CancelButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Cancel Search']"));
		CancelButton.click();
		
		WebElement AndroidbackButton = driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Back : Button']"));
		AndroidbackButton.click();
		
		
		WebElement BacktoHomeButton = driver1.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Back : Button']"));
		BacktoHomeButton.click();
		
		
		// Find element on the home screen
        WebElement HomeWhereToButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where are you going?']"));

        if (HomeWhereToButton.isDisplayed()) {
            System.out.println("***USER IS NAVIGATED TO HOME SCREEN AFTER REALLOCATED RIDE SEARCH CANCEL SCREEN SUCCESSFULLY ***");
        } else {
            System.out.println("USER IS NOT NAVIGATED TO HOME SCREEN**");
        }
        
        

        
        // Open the notification tray
        ((AndroidDriver) driver1).openNotifications();  // This method works with AndroidDriver
        Thread.sleep(2000);  // Wait for notifications to load

        // Locate and print notification details
        List<WebElement> notifications = driver1.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Searching for a New Driver!']"));
        for (WebElement notification : notifications) {
            System.out.println("Notification Title: " + notification.getText());
        }

        // Validate a specific notification
        WebElement notificationTitle = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Searching for a New Driver!']"));
        if (notificationTitle.getText().equals("Searching for a New Driver!")) {
            System.out.println("Reallocation Notification validated successfully at User Side");
        } else {
            System.out.println("Reallocation Notification validation failed at User Side");
        }
        
        driver1.navigate().back();
    }
	
	}
	
	

