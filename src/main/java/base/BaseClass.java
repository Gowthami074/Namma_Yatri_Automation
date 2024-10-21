 package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.qameta.allure.Attachment;


public class BaseClass {
	protected static AppiumDriver driver; 
	protected static AppiumDriver driver1;
	public static String rideOTP = null;
	public static String vehicleVariantText;
	public int flowOption;
	public static List<String> udids;
	protected static String driverUdid, userUdid;

	@BeforeSuite
	@Parameters("flowOption")
	public void setUp(int flowOption) throws MalformedURLException {

		try {
			this.flowOption = flowOption;
			udids = getDeviceUDIDs();
			System.out.println(udids);
			System.out.println(udids.size());
			if(udids.size()==2 && flowOption ==1) {
				System.setProperty("userUdid", udids.get(1));
			}
			else if(udids.size()==2 && flowOption ==2) {
				System.setProperty("driverUdid", udids.get(1));
			}
			else if(udids.size()==3 && flowOption ==3) {
				System.setProperty("driverUdid", udids.get(1));
				System.setProperty("userUdid", udids.get(2));
			}
			else {
				System.out.println("NotEnoughInput - Either FlowInput in xml is wrong or adb devices are not connected");
			}
			driverUdid = System.getProperty("driverUdid");
			userUdid = System.getProperty("userUdid");

			URL url = new URL("http://0.0.0.0:4723/wd/hub/");

			if (flowOption == 2 || flowOption == 3) {
				// Driver
				System.out.println("Setting the capabilities of the Driver Application and Driver app Installation is in Progress");
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("platformName", "Android");
				cap.setCapability("platformVersion", "13");
				cap.setCapability("udid", driverUdid);
				cap.setCapability("automationName", "UiAutomator2");
				cap.setCapability("newCommandTimeout", 300);
//				cap.setCapability("appPackage", "in.juspay.nammayatripartner");//CUG
//				cap.setCapability("appActivity", "in.juspay.mobility.MainActivity");//CUG
//				cap.setCapability("noReset", true);//CUG
//				cap.setCapability("appPackage", "in.juspay.nammayatripartner.debug");//debug
//				cap.setCapability("appActivity", "in.juspay.mobility.MainActivity");//debug
//				cap.setCapability("noReset", true);//debug
      			cap.setCapability("app", "");//Driver apk path
				driver = new AndroidDriver(url, cap);

				implicitWaitMethod(driver,60);
				System.out.println("Launched the Driver Application");
			}

			if (flowOption == 1 || flowOption == 3) {
				// User
				System.out.println("Setting the capabilities of the User Application and User app Installation is in Progress");
				DesiredCapabilities cap1 = new DesiredCapabilities();
				cap1.setCapability("platformName", "Android");
				cap1.setCapability("platformVersion", "13");
				cap1.setCapability("udid", userUdid);
				cap1.setCapability("automationName", "UiAutomator2");
				cap1.setCapability("newCommandTimeout", 300);
//				cap1.setCapability("appPackage", "in.juspay.nammayatri");//CUG  
//				cap1.setCapability("appActivity", "in.juspay.mobility.MainActivity");//CUG
//				cap1.setCapability("noReset", true);//CUG
//				cap1.setCapability("appPackage", "in.juspay.nammayatri.debug");//debug
//				cap1.setCapability("appActivity", "in.juspay.mobility.MainActivity");//debug
//				cap1.setCapability("noReset", true);//debug
				cap1.setCapability("app", "");//User apk path
//				driver1 = new AppiumDriver(url, cap1);
				driver1 = new AndroidDriver(url, cap1);
				implicitWaitMethod(driver1,60);
				System.out.println("Launched the User Application");
			}
		} catch (Exception exp) {
			System.out.println("Cause is: " + exp.getCause());
			System.out.println("Message is: " + exp.getMessage());
			exp.printStackTrace();
		}
	}


	public static List<String> getDeviceUDIDs() {
		List<String> udids = new ArrayList<>();
		try {

			ProcessBuilder processBuilder = new ProcessBuilder("/usr/local/bin/adb", "devices", "-l");   
			Process process = processBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("device")) {
					String[] parts = line.split("\\s+");
					if (parts.length > 0) {
						udids.add(parts[0]);


					}
				} 
			}
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return udids;
	}    

	public void implicitWaitMethod(Object obj,int durationInSeconds) {
		((RemoteWebDriver) obj).manage().timeouts().implicitlyWait(durationInSeconds, TimeUnit.SECONDS);
	}


	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		if (driver1 != null) {
			driver1.quit();
		}
	}
	
}