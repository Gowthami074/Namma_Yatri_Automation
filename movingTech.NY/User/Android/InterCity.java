package User.Android;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseClass;

import io.appium.java_client.AppiumBy;

public class InterCity extends BaseClass{


	WebElement intercityBookingDetails;
	
	@Test
	public void interCityBooking() throws InterruptedException, MalformedURLException {

		Thread.sleep(5000);
         System.out.println("Searching for intercity button");
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Intercity']")).click();
//		driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='ALLOW']")).click();


		SearchForRideFromSuggestion();
		oneWay();
		//roundTrip();



	}

	public void SearchForRideFromSuggestion() throws InterruptedException {

		driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='Where are you going?']")).click();
		System.out.println("Tapped on Where are you going?");
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='Where are you going?']")).sendKeys("Tumu");
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Tumukuru']")).click();

		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();

	}



	public void oneWay() throws InterruptedException {
		
	System.out.println("Entered in oneway function");
		
	driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='View Fares']")).click();
	Thread.sleep(2000);
	EstimateScreen slideObj = new EstimateScreen();
	slideObj.slideToBookIntercity();
	Thread.sleep(2000);
	driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Booking']")).click();
	}
	
	
	

	public void roundTrip() {

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Round Trip']")).click();

	}
	
	public void  cancelIntercityRide() {
		
	}

	
	
	
	
	
	
	
	
	
	///////---------------------Old code. use or modify it according to flow-------------------/////////////////////
	public void leaveNow() {

		driver1.findElement(AppiumBy.xpath("")).click();

	}

	public void reserve() throws InterruptedException {

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Reserve']")).click();
		dateAndTime(6);
		String ToastMessage1 = driver1.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		assertEquals(ToastMessage1, "Date invalid. Booking available only up to 5 days in advance");
		System.out.println("Validated Toast:"+ ToastMessage1);
		dateAndTime(1);
		String interCityBookingSlot = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Pickup']/../android.widget.LinearLayout[2]/android.widget.TextView")).getText();
		System.out.println("InterCity Booking Time and date is "+interCityBookingSlot);

		String interCityDropSlot= driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Drop']/../android.widget.LinearLayout/android.widget.TextView")).getText();
		System.out.println("InterCity drop slot"+interCityDropSlot);

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='View Fares']")).click();
		Thread.sleep(4000);

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Non-AC Mini']")).click();

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sedan']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='XL Cab']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='AC Mini']")).click();

		String non_AC = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Non-AC Mini']/../../../../android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")).getText();

		String sedan = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sedan']/../../../../android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")).getText();

		String xl_Cab= driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='XL Cab']/../../../../android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")).getText();
		String ac_mini = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='AC Mini']/../../../../android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")).getText();

		System.out.println("Non_Ac fare "+non_AC+" Sedan fare "+sedan+" Xl Cab "+xl_Cab);

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Book  AC Mini']")).click();
		Thread.sleep(2000);
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Pickup & Drop']/../android.widget.ImageView")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Included charges']/../android.widget.ImageView")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Excluded charges']/../android.widget.ImageView")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Terms And Conditions']/../android.widget.ImageView")).click();

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Booking']")).click();
		String interCityBookingStatus = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Ride Summary']/../../../android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]")).getText();

		if(interCityBookingStatus.equals("Ride Scheduled"))
		{
			System.out.println("Ride Scheduled");
		}
		else {

			System.out.println("Ride not Scheduled");
		}
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Done']")).click();
		Thread.sleep(3000);
		intercityBookingDetails =driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Intercity']/../../../../../../android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.TextView"));
		String interCityRideDetails= intercityBookingDetails.getText();
		System.out.println("InterCityBookingDetails"+interCityRideDetails);
		Thread.sleep(4000);
		cancelIntercityRideFromHomeScreen();

	}


	public void dateAndTime(int dateClicks) {

		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Pickup']/../android.widget.LinearLayout[2]/android.widget.ImageView")).click();

		WebElement datElement = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select Date']/../../../android.widget.FrameLayout/android.widget.FrameLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.Button[2]"));

		for(int j=0; j<dateClicks; j++)
		{

			datElement.click();
		}
		driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='OK']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc='3']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='PM']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.Button[@text='OK']")).click();

	}
	public void cancelIntercityRideFromHomeScreen() throws InterruptedException {

		intercityBookingDetails.click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel Intercity Booking']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Cancellation']")).click();
		System.out.println("InterCity Booking cancelled");
		Thread.sleep(3000);
		
	}

	

}
