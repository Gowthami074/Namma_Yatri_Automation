package Driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.openqa.selenium.html5.Location;
import org.testng.annotations.Test;

import User.Android.RideAssignScreen;
import base.BaseClass;
import io.appium.java_client.remote.SupportsLocation;

public class Simulation extends BaseClass{

	
	String NY_BLR_DRIVER_LOCATION= "https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=1112824985&single=true&output=csv";
	String NY_BLR_DROP_link = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=0&single=true&output=csv";
	String NY_BLR_PICK_UP_link ="https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=660481894&single=true&output=csv";
	String NY_BLR_CUG_DROP_link = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=593925876&single=true&output=csv";
	String NY_BLR_CUG_PICK_UP_link="https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=1436592205&single=true&output=csv";
	String OD_CUG_PICK_UP_link = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=812261303&single=true&output=csv";
	String OD_CUG_DROP_link="https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=346294677&single=true&output=csv";

	public void userLocationSimulation(double user_lat, double user_lon, double user_alt) throws InterruptedException {

		Location user_location = new Location(user_lat, user_lon, user_alt);
		((SupportsLocation) driver1).setLocation(user_location);
		Thread.sleep(8000);
		System.out.println("User Location set to: Latitude: " + user_lat + ", Longitude: " + user_lon + ", Altitude: " + user_alt);

	}
	public void driverLocationSimulation(double driver_lat, double driver_lon, double driver_alt) throws InterruptedException {

		Location driver_location = new Location(driver_lat, driver_lon, driver_alt);
		((SupportsLocation)driver).setLocation(driver_location);
		Thread.sleep(8000);
		System.out.println("Driver Location set to: Latitude: " + driver_lat + ", Longitude: " + driver_lon + ", Altitude: " + driver_alt);


	}
	@Test
	public void ny_userLocationForCUG() throws InterruptedException {
		Thread.sleep(5000);	
		userLocationSimulation(13.213058883255744, 78.5149816270287, 0);  
		

	}
	@Test
	public void ny_driverLocationForCUG() throws InterruptedException {
		Thread.sleep(5000);	
		driverLocationSimulation(13.208538835646266, 78.5150125343188, 0);  
		Thread.sleep(5000);


	}
	@Test
	public void od_userLocationForCUG() throws InterruptedException {
		Thread.sleep(5000);	
		userLocationSimulation(20.31337096367101, 85.81309479529017, 0);  
		

	}
	@Test
	public void od_driverLocationForCUG() throws InterruptedException {
		Thread.sleep(5000);	
		driverLocationSimulation(20.314775200621153, 85.81405436709993, 0);  
		Thread.sleep(5000);

	}
	@Test
	public void masterDriverLocation() throws InterruptedException {
		fetchAndPrintCSVData(NY_BLR_DRIVER_LOCATION);
	}

	@Test
	public void masterPickupSimualtion() throws InterruptedException {
		fetchAndPrintCSVData(NY_BLR_PICK_UP_link);
	}

	@Test
	public void masterDropSimualtion() throws InterruptedException {
		fetchAndPrintCSVData(NY_BLR_DROP_link);
		
	}
	@Test
	public void CUGPickupSimualtion() throws InterruptedException {
		fetchAndPrintCSVData(NY_BLR_CUG_PICK_UP_link);
//		fetchAndPrintCSVData(OD_CUG_PICK_UP_link);

	}

	@Test
	public void CUGDropSimualtion() throws InterruptedException {
		fetchAndPrintCSVData(NY_BLR_CUG_DROP_link);
//		fetchAndPrintCSVData(OD_CUG_DROP_link);

	}

//	public void dropSimulation(double drop_lat, double drop_lon, double drop_alt) throws InterruptedException {
//
//		Location drop_Location = new Location(drop_lat, drop_lon, drop_alt);
//		((SupportsLocation)driver).setLocation(drop_Location);
//		Thread.sleep(8000);
//		System.out.println("Drop Location: Latitude: " + drop_lat + ", Longitude: " + drop_lon + ", Altitude: " + drop_alt);
//
//	}

	public static void fetchAndPrintCSVData(String sheetUrl) {
		try {
			URL url = new URL(sheetUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			while ((line = in.readLine()) != null) {
				if (line.trim().isEmpty()) continue;
				String[] row = line.split(",");
				if (row.length >= 3 && isNumeric(row[0]) && isNumeric(row[1])) {
					double latitude = Double.parseDouble(row[0].trim());
					double longitude = Double.parseDouble(row[1].trim());
					String altitude = row[2].trim();
					double altitude2 = Double.parseDouble(altitude);

				
					Location pick_location = new Location(latitude, longitude, altitude2);
					((SupportsLocation)driver).setLocation(pick_location);
					simulateLocation(latitude, longitude, altitude);
					Thread.sleep(8000);
				} else {
					System.out.println("Skipping invalid or incomplete data row: " + line);
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	public static void simulateLocation(double latitude, double longitude, String altitude) throws Exception {
		System.out.println("Simulating location:   at [" + latitude + ", " + longitude + "," + altitude +"]");
		 Thread.sleep(2000); 
		RideAssignScreen vehivcleIcon = new RideAssignScreen();
		vehivcleIcon.VehiclePosition();

		
	}


}
