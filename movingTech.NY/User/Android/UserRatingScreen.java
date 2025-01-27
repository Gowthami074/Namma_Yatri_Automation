package User.Android;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class UserRatingScreen extends BaseClass {
	PopUpsHandling popup= new PopUpsHandling();
	
	@Test
	public void TollRideRatingScreen() throws InterruptedException{
		
		WebElement ToolFeedbackOverlayText1 = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Was toll experience smooth?']"));

		
		if (ToolFeedbackOverlayText1.isDisplayed()) {
            System.out.println("**Was toll experience smooth?** Text validated in FeedbackScreen of User ");
        } else {
            System.out.println("**Was toll experience smooth?** Text NOT validated in FeedbackScreent");
        }
		
		WebElement ToolFeedbackOverlayText2 = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Were the toll charges calculated and\n"
				+ "collected correctly ?']"));

		if (ToolFeedbackOverlayText2.isDisplayed()) {
            System.out.println("**Were the toll charges calculated and collected correctly ?** Text validated in FeedbackScreen of User");
        } else {
            System.out.println("**Were the toll charges calculated and collected correctly ?** Text NOT validated in FeedbackScreent");
        }
		
		WebElement ToolFeedbackOverlayButton = driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Yes : unselected ']"));

		ToolFeedbackOverlayButton.click();
		
		WebElement FeedbackDoneButton = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Done']"));

		FeedbackDoneButton.click();
		
		System.out.println("Coming to select the unselected Star");
        String starXPath = "//android.widget.ImageView[@content-desc='4star : Un Selected ']";
        driver1.findElement(AppiumBy.xpath(starXPath)).click();
        Thread.sleep(2000);
        
        System.out.println("Coming to select the Feedback");
        String proceedButtonXPath = "//android.widget.TextView[@content-desc='Submit Feedback Button']";
        driver1.findElement(AppiumBy.xpath(proceedButtonXPath)).click();
        System.out.println("Feedback submitted");
        
	}

		
		
		
	
	
	
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
    @Test
    public void ratingScreen2() throws InterruptedException {
    	popup.AC_PopUp();
    	String rideFare = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ride completed']/../android.view.ViewGroup[1]/android.widget.TextView")).getText();
		System.out.println("Ride Completed With "+rideFare);
		
		driver1.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='View ride details']")).click();
		
		String vehicleModel = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ride Time']/../android.widget.TextView[3]")).getText();
		String driverName = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ride Time']/../android.widget.TextView[4]")).getText();
		String rideDistance = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ride Time']/../android.widget.TextView[7]")).getText();
		String rideTime = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ride Time']/../android.widget.TextView[8]")).getText();
		String rideId = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ride Time']/../android.widget.TextView[11]")).getText();
		
		System.out.println("DATA FROM THE BOOKING DETAILS:: VehicleModel: "+vehicleModel+"Driver Name: "+driverName+"Ride Distance: "+rideDistance+"Ride Time: "+rideTime+"Ride Id: "+rideId);
		
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='View Driver Receipt']")).click();
		System.out.println("Driver Receipt Opend");
		String totalPaidAmount = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Total Paid']/../android.widget.TextView[2]")).getText();
		String tripTime = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Total Paid']/../android.widget.TextView[3]")).getText();
		
		System.out.println("Details From Receipt:: Total Paid Ammount: "+totalPaidAmount+"Trip Time: "+tripTime);
		
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Download PDF']")).click();
		System.out.println("Driver Receipt downloaded successfully");
		Thread.sleep(7000);
		driver1.findElement(AppiumBy.xpath("//com.horcrux.svg.SvgView[@content-desc='Go back']")).click();
		
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//com.horcrux.svg.SvgView[@content-desc='Go back']")).click();
		
		driver1.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='5 Star']")).click();
		System.out.println("5 start rating given");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Submit & Continue']")).click();
		Thread.sleep(2000);
		
		
	}
}