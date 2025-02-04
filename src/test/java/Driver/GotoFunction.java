package Driver;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import base.BaseClass;
import io.appium.java_client.AppiumBy;

public class GotoFunction extends BaseClass {

	@Test
	public void AddGoToLocation() throws InterruptedException  {

			System.out.println("/n Home screen");


			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Go-To']")).click();
			Thread.sleep(2000);
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Know More']")).click();
			Thread.sleep(5000);
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Go Back']")).click();
			Thread.sleep(2000);
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add New Go To Location']")).click();
			Thread.sleep(2000);
			driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='']")).click();
			Thread.sleep(3000);
			driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='']")).sendKeys("jay");
			Thread.sleep(3000);
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Jayanagar']")).click();
			Thread.sleep(3000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select on Map']")).click();
			Thread.sleep(3000);
			driver.findElement(AppiumBy.xpath("//android.widget.ImageView")).click();
			Thread.sleep(2000);	
	

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select on Map']")).click();
			Thread.sleep(3000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Edit']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select on Map']")).click();
			Thread.sleep(3000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Save Location']")).click();
			Thread.sleep(2000);
			System.out.println("\n Location Added");

			/*driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add another location']")).click();
			Thread.sleep(2000);*/

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add a Go-To Location']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Current Location']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select on Map']")).click();
			Thread.sleep(2000);

			/*driver.findElement(AppiumBy.xpath("//android.widget.EditText")).click();
			Thread.sleep(2000);*/

			driver.findElement(AppiumBy.xpath("//android.widget.EditText")).sendKeys("Work");
			Thread.sleep(4000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Save Location']")).click();
			Thread.sleep(4000);
			System.out.println("Location added");

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Jayanagar, Bengaluru, Karnataka, India']")).click();
			Thread.sleep(2000);


			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Enable']")).click();
			Thread.sleep(2000);

			System.out.println("Clicked on enable button");

			//driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Got It!']")).click();
			//Thread.sleep(5000);

			System.out.println("Go To Enabled");
			Thread.sleep(3000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Report Issue']/../../android.widget.FrameLayout")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Disable']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Go-To']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add a Go-To Location']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='']")).sendKeys("aadugo");
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Adugodi']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select on Map']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='']")).sendKeys("police station");
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Save Location']")).click();
			Thread.sleep(2000);

			System.out.println("Adugodi selected");
			
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add a Go-To Location']")).click();
			Thread.sleep(2000);
			
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select on Map']")).click();
			Thread.sleep(2000);
			
			System.out.println("Locate on Map tapped");
			
			
			
			scrolling();			
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Select on Map']")).click();
			Thread.sleep(2000);
			
			driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='']")).sendKeys("Random Location");
			Thread.sleep(2000);
			
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Save Location']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='police station']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Enable']")).click();
			Thread.sleep(2000);

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Got It!']")).click();
			Thread.sleep(2000);
			
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Jayanagar, Bengaluru, Karnataka, India']")).click();
			Thread.sleep(2000);

			System.out.println("Home selected");

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Enable']")).click();
			Thread.sleep(5000);

			System.out.println("Home Enabled");

		}
	@Test
	public void DisableGoTo() throws InterruptedException
	{
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Okay']")).click();
		Thread.sleep(2000);
		System.out.println("Disabling GoTo");
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Report Issue']/../../android.widget.FrameLayout")).click();
		Thread.sleep(2000);
		System.out.println("Disable GoTo Popup");

		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Disable']")).click();
		Thread.sleep(5000);
	}
	
	@Test
	public void DeleteGoToFlow() throws InterruptedException {
		
		System.out.println("Deleting GoTo Locations");
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Offline']/../../../../android.widget.LinearLayout[1]")).click();
		System.out.println("Clicked profile");
		Thread.sleep(2000);
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Settings']")).click();
		Thread.sleep(2000);
		System.out.println("Tapped on settings");
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Go To Locations']")).click();
		Thread.sleep(2000);	
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Remove']")).click();
		Thread.sleep(2000);
		
		System.out.println("Tapped on Remove");
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cancel']")).click();
		Thread.sleep(2000);
		
		System.out.println("Popup cancelled");
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Remove']")).click();
		Thread.sleep(2000);

		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Remove']")).click();
		Thread.sleep(2000);
		
		System.out.println("One go to removed");
		
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Remove']")).click();
		Thread.sleep(2000);
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Remove']")).click();
		Thread.sleep(2000);
		
		System.out.println("Second go to removed");
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Remove']")).click();
		Thread.sleep(2000);
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Remove']")).click();
		Thread.sleep(2000);
		
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Remove']")).click();
		Thread.sleep(2000);
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Yes, Remove']")).click();
		Thread.sleep(2000);
		
		System.out.println("Saved Goto Location Removed");
		
		driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@text='']")).click();
		Thread.sleep(2000);
		
		driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@text='']")).click();
		Thread.sleep(2000);
		
		driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@text='']")).click();
		Thread.sleep(5000);
		
		System.out.println("Driver is in homescreen ");
		
	}
	
	public void scrolling() {
		for (int i = 0; i < 5; i++) 
		{		
		boolean canScrollMore = (Boolean)driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
				"left", 100, "top", 100, "width", 900, "height", 900,
				"direction", "down",
				"percent", 3.0
				));
		}

	}
}

//driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout[4]/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView")).click();
//Thread.sleep(2000);

//	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='GO!']")).click();
//Thread.sleep(9000);
