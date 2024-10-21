package TestSuite;

import org.testng.annotations.Test;

import Driver.AndroidBackButton;
import Driver.DriverLoginFlow;
import Driver.EndRideScreen;
import Driver.EnterOTPPopUp;
import Driver.PopUpScreen;
import Driver.StartRideScreen;
import Driver.DriverCancellation;
import User.Android.EstimateScreen;
import User.Android.InterCity;
import User.Android.OTPScreen;
import User.Android.Rentals;
import User.Android.RideSearch;
import User.Android.UserCancellation;
import User.Android.UserLoginFlow;
import User.Android.UserRatingScreen;
import base.BaseClass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;

public class Android extends BaseClass {

	@Test
	public void sanityrideFlow() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		CyclicBarrier barrier = new CyclicBarrier(2);
		
		        Thread driverThread = new Thread(() -> {
		            try {
		                new DriverLoginFlow().successfulDriverLogin();
		                barrier.await(); // 1st barrier: Wait for user login flow to complete
		                barrier.await(); // 2nd barrier: Wait for user ride search and auto-assign driver to complete
		                new PopUpScreen().acceptOffer();
		                barrier.await(); // 3rd barrier: Wait for user to read OTP
		                new EnterOTPPopUp().cancelRide();
		                new StartRideScreen().startRide();
		                new EnterOTPPopUp().enterCorrectOTP();
		                new AndroidBackButton().tapBack();
		                new EndRideScreen().clickEndRide();
		                
		                //For Cancellation
		  barrier.await(); //4th wait
		                barrier.await(); //5th wait
		                new PopUpScreen().acceptOffer();
		                new DriverCancellation().cancelRide();




		            } catch (InterruptedException | BrokenBarrierException e) {
		                e.printStackTrace();
		            } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
		                latch.countDown();
		                
		            }
		        }); 

		// Run user login flow in a separate thread
		Thread userThread = new Thread(() -> {
			try {
				new UserLoginFlow().successfulUserLogin();
				                barrier.await(); // 1st barrier: Wait for driver login flow to complete
				                new RideSearch().SearchForRideFromSuggestion();
				                new EstimateScreen().autoAssignDriver();
				                barrier.await(); // 2nd barrier: Wait for driver to reach the 2nd barrier
				                new OTPScreen().readOTP();
				                barrier.await(); // 3rd barrier: Wait for driver to accept the offer
				                new UserRatingScreen().ratingScreen();
				                
				                new UserCancellation().userCancellation();

				new Rentals().rentalbooking();
				new InterCity().interCityBooking();
				
				
				                //When driver is doing cancellation
				                barrier.await(); //4th wait
				                new RideSearch().SearchForRideFromSuggestion();
				                new EstimateScreen().autoAssignDriver();
				                barrier.await(); //5th wait


			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				latch.countDown();
			}


		});



		// Start both threads
		 driverThread.start();
		userThread.start();


		// Wait for both threads to finish
		latch.await();
	}
}
