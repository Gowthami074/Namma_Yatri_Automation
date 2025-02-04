package Driver;

import org.openqa.selenium.html5.Location;
import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.remote.SupportsLocation;


public class Reallocation_DriverSimulation extends BaseClass{


	public void simulateLocation(double lat, double lon, double alt) {
		// Create a Location object with the provided latitude, longitude, and altitude
		Location location = new Location(lat, lon, alt);

		// Use the driver directly to set the location
		((SupportsLocation) driver).setLocation(location);  // Ensure driver is of type AndroidDriver

		// Print out the location to verify it's been set
		System.out.println("Driver Location set to: Latitude: " + lat + ", Longitude: " + lon + ", Altitude: " + alt);
	}

	public void usersimulateLocation(double userlat, double userlon, double useralt) {
		// Create a Location object with the provided latitude, longitude, and altitude
		Location location = new Location(userlat, userlon, useralt);

		// Use the driver directly to set the location
		((SupportsLocation) driver1).setLocation(location);  // Ensure driver is of type AndroidDriver

		// Print out the location to verify it's been set
		System.out.println("User Location set to: Latitude: " + userlat + ", Longitude: " + userlon + ", Altitude: " + useralt);
	}
	@Test
	public void SimulateDriverAfterRideAssign() throws InterruptedException {

		Thread.sleep(10000);
		simulateLocation(12.942241970127588, 77.62198924120077, 0);  
		Thread.sleep(10000);
		simulateLocation(12.942470854698659, 77.62272425885241, 0);  
		Thread.sleep(10000);
		
		

		System.out.println("##Simulation done after Ride Assign for Reallocation##");

	}
	
	@Test
	public void SimulateDriverAtPreviousLocation() throws InterruptedException {

		Thread.sleep(8000);
		simulateLocation(12.942241970127588, 77.62198924120077, 0);  
		Thread.sleep(8000);

		

		System.out.println("##Simulation back to Previous Location done after Reallocation to Validate Other Ride Booking##");

	}
	
	
}
