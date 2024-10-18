package User.Android;

import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class UserRatingScreen extends BaseClass {
    @Test
    public void ratingScreen() throws InterruptedException {
        System.out.println("Entering UserRatingScreen method"); 
        try {
            Thread.sleep(2000);
//            driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Yes\"]")).click();
//            Thread.sleep(2000);
//            driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Proceed Button with : Yes option selected\"]")).click();
            System.out.println("Coming to select the unselected Star");
            String starXPath = "//android.widget.ImageView[@content-desc='4star : Un Selected ']";
            driver1.findElement(AppiumBy.xpath(starXPath)).click();
            Thread.sleep(2000);
//            System.out.println("Coming to select the selected Star");
//            String starXPath1 = "//android.widget.ImageView[@content-desc='4 Star : Selected']";
//            driver1.findElement(AppiumBy.xpath(starXPath1)).click();
//            Thread.sleep(2000);
            System.out.println("Coming to select the Feedback");
            String proceedButtonXPath = "//android.widget.TextView[@content-desc='Submit Feedback Button']";
            driver1.findElement(AppiumBy.xpath(proceedButtonXPath)).click();
            System.out.println("Feedback submitted");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in UserRatingScreen method: " + e.getMessage());
        }
        System.out.println("Exiting UserRatingScreen method");
    }
}