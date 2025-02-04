package User.Android;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.BaseClass;

public class userChat extends BaseClass {
	
    WebElement chatCallButton = driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Chat and Call : Button\"]/android.widget.ImageView"));
    WebElement chatWidget = driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Quick Chat : Widget\"]"));
    WebElement chatbox = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Quick Chats\"]"));
    //WebElement driverSideMessage = driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Message From Driver : Hi DriverAutomation\"]"));
    WebElement UserMessage = driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Call me, You're unreachable: Button : Select to send message to driver\"]"));

    public void inAppChats() throws InterruptedException {
        System.out.println("Checking User chat");
        
        // Check if the chatCallButton is displayed and enabled
        if (chatCallButton.isDisplayed() && chatCallButton.isEnabled()) {
            System.out.println("Chat Call Button is visible and enabled!");
        } else {
            System.out.println("Chat Call Button is either not visible or not enabled.");
        }

        if (chatWidget.isDisplayed() && chatWidget.isEnabled()) {
            System.out.println("chatWidget is visible and enabled!");
        } else {
            System.out.println("chatWidget is either not visible or not enabled.");
        }
        
        if (chatbox.isDisplayed() && chatbox.isEnabled()) {
            System.out.println("chatbox is visible and enabled!");
        } else {
            System.out.println("chatbox is either not visible or not enabled.");
           
        }
        
        
        WebElement driverSideMessage = driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Message From Driver : Hi DriverAutomation\"]"));

        if (driverSideMessage.isDisplayed() && driverSideMessage.isEnabled()) {
            System.out.println("driverSideMessage is visible !");
        } else {
            System.out.println("driverSideMessage is visible or not enabled.");
           
        }
        
        UserMessage.click();
    }
    
}
