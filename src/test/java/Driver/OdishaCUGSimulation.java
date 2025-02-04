package Driver;

import org.openqa.selenium.html5.Location;
import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.remote.SupportsLocation;


public class OdishaCUGSimulation extends BaseClass{


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
	public void normalRideSimulationAfterRideStarts() throws InterruptedException {

		Thread.sleep(10000);
		simulateLocation(12.942612958722645, 77.62272016791012, 0);  
		Thread.sleep(10000);

		simulateLocation(12.942073496553679, 77.62333051747099, 0);  
		Thread.sleep(10000);

		simulateLocation(12.94165514617655, 77.6237570750708, 0);  
		Thread.sleep(10000);

		simulateLocation(12.941091645720851, 77.6243474887366, 0);  
		Thread.sleep(10000);

		simulateLocation(12.940580707131703, 77.62486869440735, 0);  
		Thread.sleep(10000);

		simulateLocation(12.94042326654149, 77.62501194976716, 0);  
		Thread.sleep(10000);

		simulateLocation(12.940021450784625, 77.62533265338162, 0);  
		Thread.sleep(10000);

		simulateLocation(12.93959303263486, 77.62567160734393, 0);  
		Thread.sleep(10000);

		simulateLocation(12.938798134359137, 77.62589404588171, 0);  
		Thread.sleep(10000);

		simulateLocation(12.93812195266275, 77.62637069989124, 0);  
		Thread.sleep(10000);

		simulateLocation(12.93767288438645, 77.6266672846631, 0);  
		Thread.sleep(10000);

		simulateLocation(12.937248968798272, 77.6270351108965, 0);  
		Thread.sleep(10000);

		simulateLocation(12.936714591613748, 77.62634974766087, 0);  
		Thread.sleep(10000);

		simulateLocation(12.935242224109006, 77.62454031352402, 0);  
		Thread.sleep(10000);

		System.out.println("Simulation done");

	}
	
	@Test
	public void updateDriverLocationForCUG() throws InterruptedException {
//		Thread.sleep(10000);	
		simulateLocation(20.314775200621153, 85.81405436709993, 0);  
//		Thread.sleep(10000);

	}
	@Test	public void updateUserLocationForCUG() throws InterruptedException {
//		Thread.sleep(10000);	
		usersimulateLocation(20.31337096367101, 85.81309479529017, 0);  
		

	}
	@Test
	public void pickupSimulation() throws InterruptedException {


		simulateLocation(20.31446072574693, 85.8140393522159, 0);  
		Thread.sleep(10000);
		simulateLocation(20.313766062566092, 85.81401432740918, 0);  
		Thread.sleep(10000);
		simulateLocation(20.313362405501763, 85.81398930260245, 0);  
		Thread.sleep(10000);
		simulateLocation(20.313403469724115, 85.81313756458016 , 0);  
		Thread.sleep(10000);
 		
	}
	@Test
	public void dropSimulation() throws InterruptedException {
		simulateLocation(20.313368284401616, 85.81398164551088, 0);  
		Thread.sleep(10000);
		simulateLocation(20.314868870652578, 85.81415752753247, 0);  
		Thread.sleep(10000);
		simulateLocation(20.31480447583221, 85.81641205797358, 0);  
		Thread.sleep(10000);
		simulateLocation(20.314611291210376, 85.81904425087436, 0);  
		Thread.sleep(10000);
		simulateLocation(20.31478301088614, 85.82042901322652, 0);  
		Thread.sleep(10000);
		simulateLocation(20.313141283783818, 85.82023405335312, 0);  
		Thread.sleep(10000);
		simulateLocation(20.30998073894502, 85.82028473156835, 0);  
		Thread.sleep(10000);
		simulateLocation(20.307960808069744, 85.82033540978357, 0);  
		Thread.sleep(10000);
		simulateLocation(20.30373074980241, 85.82248923393065, 0); 
		Thread.sleep(10000);
		simulateLocation(20.300498717301064, 85.82345212001995, 0);  
		Thread.sleep(10000);
		simulateLocation(20.298074648664066, 85.82418695414073, 0);  
		Thread.sleep(10000);	
		simulateLocation(20.295959498961395, 85.82469373629299, 0);  
		Thread.sleep(10000);
		
	}

}
