package User.Android;
import io.appium.java_client.AppiumBy;
import io.netty.handler.timeout.TimeoutException;
import org.openqa.selenium.WebElement;
import base.BaseClass;
import Driver.DriverprofileScreen;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
public class EstimateScreen extends BaseClass {
//    String vehicleVariantText;
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
//            driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Back : Button']/../../../android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]")).click();
        	driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Confirm Button to Proceed With Book Any']")).click(); 
          //android.widget.TextView[@content-desc="Confirm Button to Proceed With Book Any"]
        	System.out.println("Tapped on the final element.");;
        	
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
}