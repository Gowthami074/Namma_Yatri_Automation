package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import TestReport.AppReport;
import TestReport.TestListener;
import Utils.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class BaseClass {
	protected static AppiumDriver driver; 
	protected static AppiumDriver driver1;
	public static String rideOTP = null;
	public static String vehicleVariantText;
	public int flowOption;
	public static List<String> udids;
	protected static String driverUdid, userUdid;
	AppReport appreport =new AppReport();

	@BeforeSuite
	@Parameters({"flowOption","userApp","driverApp"})
	public void setUp(int flowOption,String userApp, String driverApp) throws MalformedURLException {
		try {
			this.flowOption = flowOption;
			udids = getDeviceUDIDs();
			System.out.println(udids);
			System.out.println("Available devices - "+(udids.size()-1));
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
			
			appreport.clearExistingJSONFiles();
			if (flowOption == 2 || flowOption == 3) {
				// Driver
				System.out.println("Setting the capabilities of the Driver Application and Driver app Installation is in Progress");
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("platformName", "Android");
				cap.setCapability("platformVersion", "13");
				cap.setCapability("udid", driverUdid);
				cap.setCapability("automationName", "UiAutomator2");
				cap.setCapability("newCommandTimeout", 300);
				cap.setCapability("uiautomator2ServerLaunchTimeout", 180000);
//				cap.setCapability("appPackage", ConfigLoader.getProperty(driverApp + ".appPackage"));
//				cap.setCapability("appActivity", ConfigLoader.getProperty(driverApp + ".appActivity"));
//				cap.setCapability("noReset", true);//debug
				//cap.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/Resources/app-nyDriver-prod-debug.apk");
				cap.setCapability("fullReset", true);
				cap.setCapability("app", "https://github.com/Gowthami074/Namma_Yatri_Automation/releases/download/v1.0.0/app-nyDriver-prod-debug.apk");
				
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
				cap1.setCapability("uiautomator2ServerLaunchTimeout", 180000);
//				cap1.setCapability("appPackage", ConfigLoader.getProperty(userApp + ".appPackage"));
//			    cap1.setCapability("appActivity",ConfigLoader.getProperty(userApp + ".appActivity"));
//				cap1.setCapability("noReset", true);
//         		cap1.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/Resources/app-nammaYatri-prod-debug.apk");
				cap1.setCapability("fullReset", true);
				cap1.setCapability("app", "https://github.com/Gowthami074/Namma_Yatri_Automation/releases/download/v1.0.0/app-nammaYatri-prod-debug.apk");
				

				driver1 = new AndroidDriver(url, cap1);
				implicitWaitMethod(driver1,100);
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
		String commandToFind = "adb";
		String commandPath = findCommandPath(commandToFind);
		System.out.println(commandToFind.toUpperCase() + " Path: " + (commandPath != null ? commandPath : commandToFind + " not found"));
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(commandPath, "devices", "-l");   
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
		//  Quit the Appium drivers
		if (driver != null) {
			driver.quit();
		}
		if (driver1 != null) {
			driver1.quit();
		}
		appreport.generateReport();
	}

	public static String findCommandPath(String command) {
		// First try to find the command using `which`
		String commandPath = getCommandOutput("which " + command);
		if (commandPath != null && !commandPath.contains("not found")) {
			return commandPath;
		}

		// Check common predefined paths
		List<String> possiblePaths = Arrays.asList(
				System.getenv("HOME") + "/Library/Android/sdk/platform-tools/" + command,
				"/opt/homebrew/bin/" + command,
				"/usr/local/bin/" + command
				);

		for (String path : possiblePaths) {
			if (new File(path).exists()) {
				return path;
			}
		}

		return null; // Command not found
	}

	private static String getCommandOutput(String command) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", command);

			// Fetch existing PATH
			String currentPath = System.getenv("PATH");

			// Define additional paths
			String sdkPath = System.getenv("HOME") + "/Library/Android/sdk/platform-tools";
			String brewPath = "/opt/homebrew/bin";
			String usrLocalPath = "/usr/local/bin";
			String updatedPath = brewPath + ":" + usrLocalPath + ":" + sdkPath + ":" + currentPath;

			// Update process environment
			processBuilder.environment().put("PATH", updatedPath);

			// Execute the process
			Process process = processBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			StringBuilder output = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line).append("\n");
			}

			process.waitFor();
			return output.length() > 0 ? output.toString().trim() : null;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
}