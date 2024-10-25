package User.Android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class RideFlowFromUserFvrtLocation extends BaseClass {
	 RideSearch ridesearch = new RideSearch();
	 
	 
	@Test
	public void Favourite() throws InterruptedException {
		driver1.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Navigation : Button']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Favourites']")).click();
		//driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Locations']")).click();
		WebElement NofavouriteLocations = driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='No favourites saved yet']"));
		if(NofavouriteLocations.isDisplayed()){
			System.out.println("User not saved any favourite locations");	
			System.out.println("User started to favourite the locations");
			AddingFavouriteLocation();
		}
			else {
				System.out.println("User already added favourite location");	
				driver1.navigate().back();
				driver1.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Close Menu Bar']")).click();	
			}	
		}
	
	
	public void AddingFavouriteLocation() throws InterruptedException {
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add New Favourite']")).click();
	 driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter a location']")).sendKeys("koram");
	 Thread.sleep(2000);
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Koramangala']")).click();
	 driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Home: Un Selected']")).click();
	 Thread.sleep(2000);
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm & Save']")).click();
	 System.out.println("User saved a location with Home tag");
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add New Favourite']")).click();
	 Thread.sleep(2000);
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Choose on map']")).click();
	 ridesearch.scroll(10);
	 Thread.sleep(2000);
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm Location']")).click();
	 Thread.sleep(4000);
	 driver1.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Work: Un Selected']")).click();
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm & Save']")).click();
	 System.out.println("User saved a location with Work tag");
	 Thread.sleep(2000);
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add New Favourite']")).click();
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Use current location']")).click();
	 driver1.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Give this location a name']")).sendKeys("Current Location");
	 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm & Save']")).click();
	 System.out.println("User saved current location as Favourites");
	 driver1.navigate().back();
	driver1.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Close Menu Bar']")).click();	
	Thread.sleep(5000);
	}
	 

	@Test		
	public void RemovingFavouriteLocation() {
		driver1.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Navigation : Button']")).click();
		driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Favourites']")).click();

		 List<String> xpaths = new ArrayList<>();
		 xpaths.add("//android.widget.TextView[@text='Home']");
		 xpaths.add("//android.widget.TextView[@text='Work']");
		 xpaths.add("//android.widget.TextView[@text='Current Location']");
		 
		 
		 for (int i=0; i<3; i++) {
			 WebElement Fvrt=driver1.findElement(AppiumBy.xpath(xpaths.get(i)));
			 if(Fvrt.isDisplayed()){
				 driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Remove']")).click();
					driver1.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Remove']")).click();
					System.out.print("User Favourite location deleted sucessfully"); 		
			 }
		 }
		    driver1.navigate().back();
			driver1.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Close Menu Bar']")).click();	
		 
	}
	
	}