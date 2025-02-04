package Driver;

import org.testng.annotations.Test;

import base.BaseClass;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.nativekey.AndroidKey;
//import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;


public class AndroidBackButton extends BaseClass {

	@Test
    public void tapBack() throws InterruptedException {
    	for(int i =0;i<6;i++)
		{   
			Thread.sleep(3000);
			driver.navigate().back();
		}
		((AndroidDriver) driver).startActivity(new Activity("in.juspay.nammayatripartner.debug", "in.juspay.mobility.MainActivity"));
//		((AndroidDriver) driver).startActivity(new Activity("in.mobility.odishayatripartner", "in.juspay.mobility.MainActivity"));
    }
}