package Driver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.HidesKeyboard;
import base.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DriverOnboarding extends BaseClass {
	
	
	
	public void loginFlow() throws InterruptedException {
		

	Thread.sleep(15000);

		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Enable Location']")).click();
		//		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='WHILE USING THE APP']")).click();
		driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
		Thread.sleep(10000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Get Started']")).click();
		driver.findElement(AppiumBy.accessibilityId("10-digit mobile number")).sendKeys("8061032896");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Auto Reading OTP...']")).sendKeys("7891");
//		
//	    try {
//	        // Attempt to find the element with the XPath
////	        WebElement element = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Registration']"));
//	        
//	        List<WebElement> elements = driver.findElements(By.xpath("//android.widget.TextView[@text='Registration']"));
//	        assert !elements.isEmpty() : "Element not found!";
//	        System.out.println("New number_48");
//	        // Proceed with the next block if the element is found
//	        elements.get(0).click();
////	        if (element.isDisplayed()) {
////	            System.out.println("New number");
////	            autoOnboarding();
////	        }
//	    } catch (NoSuchElementException e) {
//	        // If element is not found, execute this block of code
//	        System.out.println("This is old number need to call login flow.");
//	        try {
//	            // Try to find the 'Grant Permissions' screen element
//	            WebElement permissionsElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Grant Permissions']"));
//
//	            if (permissionsElement.isDisplayed()) {
//	                System.out.println("Navigating to 'Grant Permissions' screen.");
//	                // Call your method to handle grant permissions screen
//	                grantPermissions();
//	            }
//	        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException ex) {
//	            // Handle the case where 'Grant Permissions' screen is also not found
//	            System.out.println("Neither 'Registration' nor 'Grant Permissions' screen found.");
//	        }}
//	        
	        
	        
	        handleRegistrationOrPermissions();
	    }

	    private void handleRegistrationOrPermissions() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

	        try {
	            WebElement registrationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Registration']")));
	            if (registrationElement.isDisplayed()) {
	                System.out.println("New number, proceeding with autoOnboarding.");
	                autoOnboarding();
	            }
	        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
	            System.out.println("Registration screen not found, checking for 'Grant Permissions' screen.");

	            try {
	                WebElement permissionsElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Grant Permissions']"));
	                if (permissionsElement.isDisplayed()) {
	                    System.out.println("Navigating to 'Grant Permissions' screen.");
	                    grantPermissions();
	                }
	            } catch (NoSuchElementException ex) {
	                System.out.println("Neither 'Registration' nor 'Grant Permissions' screen found.");
	            }
	        }
	        
	        
	    }
	

	public void autoOnboarding() throws InterruptedException {
		
		//		Variant selection


		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Auto Rickshaw']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();

		////      DL code
	   
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Driving License']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter DL Number']")).sendKeys("KA2820190013762");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select date of birth']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='2006']")).click();
		Thread.sleep(1000);
//		
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
		((HidesKeyboard) driver).hideKeyboard();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='"+currentMonth()+"']")).click();
		Thread.sleep(1000);
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='"+currentMonth()+"']")).click();
		Thread.sleep(1000);
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.J));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.U));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.L));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.Y));

// 
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='"+todaysDate()+"']")).click();
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_3));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
		((HidesKeyboard) driver).hideKeyboard();
		Thread.sleep(1000);
//	
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='OK']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Upload Driving Licence']")).click();

		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Upload Photo']")).click();
		driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();

		driver.findElement(AppiumBy.id("android:id/icon")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Gallery']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Download']")).click();	
		driver.findElement(AppiumBy.accessibilityId("Photo taken at 2 Jul 2024 15:13:15.")).click();	//3762 DL
		Thread.sleep(10000);
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='CROP']")).click();
		Thread.sleep(5000);

//		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Refresh']")).click()
		
		////		RC
	
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Vehicle Registration Certificate']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter Vehicle No.']")).sendKeys("KA53A6428");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter Vehicle No.']")).sendKeys("KA53A6428");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Upload Registration Certificate (RC)']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Upload Photo']")).click();		
//		driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
//
		Thread.sleep(1000);
		driver.findElement(AppiumBy.id("android:id/icon")).click();
		// Find images in the gallery
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Gallery']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Download']")).click();		
		driver.findElement(AppiumBy.accessibilityId("Photo taken at 5 Aug 2024 07:02:10.")).click();	//6428 auto
//
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='CROP']")).click();
//		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm']")).click();
		Thread.sleep(10000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Complete Registration']")).click();
		Thread.sleep(5000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		grantPermissions();
		

		
//		driver.findElement(AppiumBy.xpath(
//				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout"))
//		.click();

		//		continue- /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout
//		 Locate the "Skip" button
//		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Skip']")).click();
//		Thread.sleep(10000);
//		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Complete Registration']")).click();
//		Thread.sleep(5000);
//		
//		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
//		Thread.sleep(2000);
////
//		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Okay']")).click();
//		
////		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='GO!']")).click();
//		Thread.sleep(10000);
//		unlinkDL();
//		unlinkRc();
		Thread.sleep(4000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Offline']")).click();
		Thread.sleep(2000);

		driver.findElement(AppiumBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout[1]"))
		.click();
		Thread.sleep(3000);

		driver.findElement(AppiumBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.ImageView']"))
		.click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Vehicle Details']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Manage Vehicles']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Deactivate']")).click();
		driver.findElement(AppiumBy.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout"))
		.click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Manage Vehicles']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Manage Vehicles']")).click();
	}
		
	//		Grant Permission
	
	public void grantPermissions() throws InterruptedException {
		
	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Grant Permissions']")).click();
	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Draw over applications']")).click();
	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Autostart in background']")).click();
	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Battery Optimization']")).click();
	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();
	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Notification Access']")).click();
	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();
	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
	Thread.sleep(1000);
	}
	
	
	public void cabOnboarding() throws InterruptedException {
		
		Thread.sleep(15000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Enable Location']")).click();
		//		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='WHILE USING THE APP']")).click();
		driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))
		.click();
		Thread.sleep(10000);

		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Get Started']")).click();


		driver.findElement(AppiumBy.accessibilityId("10-digit mobile number")).sendKeys("8081092896");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Auto Reading OTP...']")).sendKeys("7891");

		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Car']")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
        //DL
        
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Driving License']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter DL Number']")).sendKeys("KA0320230011673");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select date of birth']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='2006']")).click();
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_9));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_9));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_5));
		((HidesKeyboard) driver).hideKeyboard();
        Thread.sleep(1000);
        
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='"+currentMonth()+"']")).click();
		Thread.sleep(1000);
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='"+currentMonth()+"']")).click();
		Thread.sleep(1000);
		
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.M));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.A));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.Y));

		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='"+todaysDate()+"']")).click();
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
		((HidesKeyboard) driver).hideKeyboard();
        Thread.sleep(1000);

    
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='OK']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Upload Driving Licence']")).click();
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Upload Photo']")).click();
		driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();

		driver.findElement(AppiumBy.id("android:id/icon")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Gallery']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Download']")).click();	
		driver.findElement(AppiumBy.accessibilityId("Photo taken at 6 Aug 2024 16:52:07.")).click();	//2395 cab
		Thread.sleep(10000);
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='CROP']")).click();
		Thread.sleep(5000);
        
//        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Refresh']")).click()

		////		RC
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Vehicle Registration Certificate']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter Vehicle No.']")).sendKeys("KA05AK8325");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter Vehicle No.']")).sendKeys("KA05AK8325");
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes']")).click();	
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Upload Registration Certificate (RC)']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Upload Photo']")).click();		
//		driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();

		Thread.sleep(1000);
		driver.findElement(AppiumBy.id("android:id/icon")).click();
		// Find images in the gallery
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Gallery']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Download']")).click();		
		driver.findElement(AppiumBy.accessibilityId("Photo taken at 6 Aug 2024 15:49:24.")).click();   //8325 cab
		Thread.sleep(10000);
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='CROP']")).click();
		Thread.sleep(5000);

		//		Grant Permission
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Grant Permissions']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Draw over applications']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Autostart in background']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Battery Optimization']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Notification Access']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Allow']")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")).click();
		Thread.sleep(1000);
	
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Complete Registration']")).click();
		Thread.sleep(5000);
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Continue']")).click(); 
		Thread.sleep(2000);
		driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout[2]']")).click();

		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Okay']")).click();
		
	}
		
public String todaysDate() {


		
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
        return currentDate.format(dayFormatter);
	}

	
	public static String currentMonth() {
        LocalDate currentDate = LocalDate.now();
        
        // Formatter to get full month name (e.g., September)
        DateTimeFormatter fullMonthFormatter = new DateTimeFormatterBuilder()
            .appendPattern("MMMM")
            .toFormatter();
        // Get the full month name
        String fullMonthName = currentDate.format(fullMonthFormatter);
        // Create a map to handle custom month abbreviations
        String abbreviatedMonthName = switch (fullMonthName) {
            case "January" -> "Jan";
            case "February" -> "Feb";
            case "March" -> "Mar";
            case "April" -> "April";
            case "May" -> "May";
            case "June" -> "June";
            case "July" -> "July";
            case "August" -> "Aug";
            case "September" -> "Sept";
            case "October" -> "Oct";
            case "November" -> "Nov";
            case "December" -> "Dec";
            default -> fullMonthName;
        };
        // Formatter to get two-digit month number
        DateTimeFormatter monthNumberFormatter = DateTimeFormatter.ofPattern("(MM)");
        // Get the two-digit month number
        String monthNumber = currentDate.format(monthNumberFormatter);
        // Combine to get the desired format
        return abbreviatedMonthName + " " + monthNumber;
    }
	
	



	
	
	}

