package User.Android;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import Driver.DriverCancellation;
import base.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class RideSearch extends BaseClass {

    @Test    
    public void SearchForRideFromSuggestion() throws InterruptedException {
        System.out.println("Ride Search about to start");
        Thread.sleep(3000);
        
        // Initialize WebElements after driver1 is initialized
        WebElement HomeWhereToButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where are you going?']"));
        HomeWhereToButton.click();
        Thread.sleep(2000);

        WebElement RideSearchScreenDestinationBox = driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Where to?']"));
        RideSearchScreenDestinationBox.sendKeys("Koram");
        Thread.sleep(2000);
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Koramangala']")).click();



    
        
        // this is notification we are getting only in vivo device, If we are not getting in other devices we can comment this x-path
        // driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW']")).click();
        
        WebElement ConfirmPickUpLocation = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']"));

        Thread.sleep(2000);
        ConfirmPickUpLocation.click();
        Thread.sleep(4000);
    }

    @Test
    public void TollRouteRideSearch() throws InterruptedException {
        System.out.println("Ride Search about to start for the Toll Route/Bangalore Airport");
        Thread.sleep(3000);

        // Initialize WebElements after driver1 is initialized
        WebElement HomeWhereToButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where are you going?']"));
        HomeWhereToButton.click();
        Thread.sleep(2000);

        WebElement RideSearchScreenDestinationBox = driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Where to?']"));
        RideSearchScreenDestinationBox.sendKeys("Airport");
        Thread.sleep(4000);
        driver1.navigate().back();
        
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Kempegowda International Airport Bengaluru (BLR)']")).click();
        Thread.sleep(2000);
        
        WebElement ConfirmPickUpLocation = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']"));
        ConfirmPickUpLocation.click();
        Thread.sleep(4000);
        
        WebElement TollTextAtEstimateScreen = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Toll & Parking charges are included']"));
        // validating if estimate Screen have Toll Text
        if (TollTextAtEstimateScreen.isDisplayed()) {
            System.out.println("User Estimate Screen have Toll Text info **Toll & Parking charges are included** Validated ");
        } else {
            System.out.println("User Estimate Screen do not have  Text **Toll & Parking charges are included**");
        }
        
        
    }

    @Test
    public void SearchForRideForIntercity() throws InterruptedException {
        System.out.println("Ride Search for Intercity");
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where to?']")).click();
        Thread.sleep(2000);
        driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Where to?']")).sendKeys("Tumu");
        Thread.sleep(2000);
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Tumukuru']")).click();
        Thread.sleep(2000);
        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();
    }

    
//    public void TollRouteRideSearch() throws InterruptedException {
//        System.out.println("Ride Search about to start for the Toll Route/Bangalore Airport");
//        Thread.sleep(3000);
//
//        // Initialize WebElements after driver1 is initialized
//        WebElement HomeWhereToButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Where are you going?']"));
//        HomeWhereToButton.click();
//        Thread.sleep(2000);
//
//        WebElement RideSearchScreenDestinationBox = driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Where to?']"));
//        RideSearchScreenDestinationBox.sendKeys("Airport");
//        Thread.sleep(4000);
//        driver1.navigate().back();
//        
//        driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Kempegowda International Airport Bengaluru (BLR)']")).click();
//        Thread.sleep(2000);
//        
//        WebElement ConfirmPickUpLocation = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']"));
//        ConfirmPickUpLocation.click();
//        Thread.sleep(4000);
//        
//        WebElement TollTextAtEstimateScreen = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Toll & Parking charges are included']"));
//        // validating if estimate Screen have Toll Text
//        if (TollTextAtEstimateScreen.isDisplayed()) {
//            System.out.println("User Estimate Screen have Toll Text info **Toll & Parking charges are included** Validated ");
//        } else {
//            System.out.println("User Estimate Screen do not have  Text **Toll & Parking charges are included**");
//        }
//        
//        
//    }
    
    public void scroll(int a) throws InterruptedException
    {
    for (int i=0; i<=a; i++) {

	boolean canScrollMore = (Boolean)driver1.executeScript("mobile: scrollGesture", ImmutableMap.of(
			"left", 100, "top", 100, "width", 900, "height", 900,
			"direction", "down",
			"percent", 3.0
			));
	Thread.sleep(3000);
    }
    }
}



