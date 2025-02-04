package Driver;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import base.BaseClass;

public class DriverUserAppMinimiseChat extends BaseClass {

    public void DriverUserAppMinimiseChatValidation() throws InterruptedException {
        System.out.println("Starting the validation of Chat notification for both driver and User");
        Thread.sleep(2000);
        // Minimize the app and continue with the execution
        new Thread(() -> {
            ((AndroidDriver)driver).runAppInBackground(Duration.ofSeconds(18)); 
            

            System.out.println("App has been in background for 20 seconds");
        }).start();

        System.out.println("Driver app is minimised, going to validate userchat notification at driver side in minimised state");
        Thread.sleep(4000);
        driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Please wait: Button : Select to send message to driver\"]")).click();
        
         
        // Execution will continue without waiting for the 10 seconds
        // You can add other operations here if needed

        // Later, bring the app back to the foreground when you want
        System.out.println("going for sleep for 5 sec");
        
        
        //Thread.sleep(5000);  // Simulating some other operation for 5 seconds
        //((AndroidDriver) driver1).activateApp("in.juspay.nammayatri.debug");
    }
}
