package Driver;

import org.openqa.selenium.html5.Location;
import org.testng.annotations.Test;

import base.BaseClass;
import io.appium.java_client.remote.SupportsLocation;


public class Simulation extends BaseClass{


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
		Thread.sleep(10000);	
		simulateLocation(13.213058883255744, 78.5149816270287, 0);  
		Thread.sleep(10000);

	}
	@Test	public void updateUserLocationForCUG() throws InterruptedException {
		Thread.sleep(10000);	
		usersimulateLocation(13.208538835646266, 78.5150125343188, 0);  
		Thread.sleep(10000);

	}
	@Test
	public void pickupSimulation() throws InterruptedException {


		simulateLocation(13.212003672506798, 78.51468158652307, 0);  
		Thread.sleep(10000);
//		simulateLocation(13.211503161286037, 78.5142735556626, 0);  
//		Thread.sleep(10000);
		simulateLocation(13.210494191101796, 78.51442044677238, 0);  
		Thread.sleep(10000);
//		simulateLocation(13.210073123570936, 78.51456733788214, 0);  
//		Thread.sleep(10000);
		simulateLocation(13.209215096920298, 78.51456733788214, 0);  
		Thread.sleep(10000);
		simulateLocation(13.208372956719378, 78.51463262281982, 0);  		
	}
	@Test
	public void dropSimulation() throws InterruptedException {
		simulateLocation(13.212003672506798, 78.51468158652307, 0);  
		Thread.sleep(10000);
		simulateLocation(13.21254173637542, 78.51482159726042, 0);  
		Thread.sleep(10000);
		simulateLocation(13.216761411900517, 78.51456410520768, 0);  
		Thread.sleep(10000);
		simulateLocation(13.220187233399043, 78.5151649199974, 0);  
		Thread.sleep(10000);
		simulateLocation(13.224281444752773, 78.51739651778783, 0);  
		Thread.sleep(10000);
		simulateLocation(13.226328524649837, 78.51949936955188, 0);  
		Thread.sleep(10000);
		simulateLocation(13.230295411858181, 78.52188404101157, 0);  
		Thread.sleep(10000);
		simulateLocation(13.23096572369404, 78.51722485641935, 0);  
		Thread.sleep(10000);
		simulateLocation(13.232010125719185, 78.51138836989054, 0); 
		Thread.sleep(10000);
		simulateLocation(13.231383485041258, 78.50563771404597, 0);  
		Thread.sleep(10000);
		simulateLocation(13.232302557484072, 78.50117451846513, 0);  
		Thread.sleep(10000);	
		simulateLocation(13.232051901707084, 78.50147492586001, 0);  
		Thread.sleep(10000);
		simulateLocation(13.23305452326789, 78.49735505301615, 0);  
		Thread.sleep(10000);
		simulateLocation(13.236145913773374, 78.49735505301615, 0);  
		Thread.sleep(10000);
		simulateLocation(13.237900469311118, 78.49550969330483, 0);  
		Thread.sleep(10000);
		simulateLocation(13.236145913773374, 78.49735505301615, 0);  
		Thread.sleep(10000);
		simulateLocation(13.236145913773374, 78.49735505301615, 0);  
		Thread.sleep(10000);
	}

}
