package Driver;

import io.appium.java_client.AppiumBy;
import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class chatMessaging extends BaseClass {

		
		
		public void clickChatMessaging() throws InterruptedException {
			
			System.out.println("Driver chatbox button about to be clicked");
			Thread.sleep(15000);
			WebElement Chatbox = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Maps']/../../../android.widget.LinearLayout[1]"));
			Chatbox.click();
			
			WebElement calling = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Call : Button\"]"));
			WebElement maps = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Call : Button\"]/../../android.widget.LinearLayout[2]/android.widget.TextView"));

		            // Assertions to validate the presence of both buttons
		            Assert.assertNotNull(calling, "Call button is not present");
		            Assert.assertNotNull(maps, "Maps button is not present");

		            // Print validation message
		            System.out.println("Both Call and Maps buttons are validated and Chat sheet is open");
		            
			WebElement chat = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Message Gowthami...\"]"));
			chat.sendKeys("Hi DriverAutomation");
			
			WebElement chatSend = driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"Send Message : Button\"]"));
			chatSend.click();
			Thread.sleep(2000);
        	 
		 // Locate the sent message
            WebElement sentMessage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Hi DriverAutomation']"));

            // Assertion to validate the sent message
            Assert.assertNotNull(sentMessage, "Sent message 'Hi DriverAutomation' is not present");

            // Print validation message for sent message
            System.out.println("Sent message 'Hi DriverAutomation' is validated in the ChatBox");
            driver.navigate().back();  
            Thread.sleep(2000);  
            
            
            
		
	}
		
	}


