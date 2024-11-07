package Driver;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class PopUpScreen extends BaseClass {
	WebElement acceptRideButton;
	

    // Method to accept the ride request
    @Test
    public void acceptOffer() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        acceptRideButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Accept Offer']")));
        System.out.println("Waiting for 'Accept Offer' button to be present");
        acceptRideButton.click();
        System.out.println("Driver accepted the ride request");
        Thread.sleep(3000);
    }
    
    @Test
    public void tollAcceptOffer() throws InterruptedException {
    	
    	Thread.sleep(3000);
        WebElement tollIncluded = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Toll included']"));
        
        if (tollIncluded.isDisplayed()) {
            System.out.println("Driver Side Pop Up Screen, Toll Included text is validated on the Ride Request Pop Up ");
        } else {
            System.out.println("Driver Side Pop Up Screen,Toll Included text is not validated on the Ride Request Pop up");
        }
        
        acceptOffer();
        System.out.println("Driver accepted the ride request");
        Thread.sleep(3000);
        
        WebElement DriverTollOverlayText1 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Toll Charges included in Fare']"));
        WebElement DriverTollOverlayText2 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Please do not demand extra from the customer']"));
        WebElement DriverTollButton = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ok, Got it']"));
        
        if (DriverTollOverlayText1.isDisplayed()) {
            System.out.println("'Overlay is validated at Driver Side Side and text **Toll Charges included in Fare** is validated ");
        } else {
            System.out.println("'Overlay is not validated");
        }
        
        if (DriverTollOverlayText2.isDisplayed()) {
            System.out.println("'Overlay is validated at Driver Side and text **Please do not demand extra from the customer** is validated ");
        } else {
            System.out.println("'Overlay is not validated");
        }
        
        DriverTollButton.click();
        
      //android.widget.TextView[@text='₹110 Toll Charges included']
        
        WebElement StartRideTollText = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='₹110 Toll Charges included']"));
        
        if (StartRideTollText.isDisplayed()) {
            System.out.println("'Overlay is validated at Driver Side and text **₹110 Toll Charges included** is validated ");
        } else {
            System.out.println("Start Ride Slider text is not validated");
        }
    }

        @Test
        public void GoToAcceptOffer() throws InterruptedException {
        	Thread.sleep(2000);
            WebElement acceptButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Accept “Go To”']"));
            acceptButton.click();
            System.out.println("Driver accepted the ride request");
            Thread.sleep(3000);
            
        }

    
 
    // Method to decline the ride offer (placeholder, not implemented)
    public void declineOffer() {
        // To be implemented
    }

    // Method to add extra charges (example implementation)
    public void addExtraCharges() {
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='+ ']")).click();
    }

    // Method to remove added extra charges (example implementation)
    public void removeExtraAddedCharges() {
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='- ']")).click();
    }

    // Placeholder methods for swiping to second or third customer offer
    public void swipeToSecondCustomerOffer() {
        // To be implemented
    }

    public void swipeToThirdCustomerOffer() {
        // To be implemented
    }
}
