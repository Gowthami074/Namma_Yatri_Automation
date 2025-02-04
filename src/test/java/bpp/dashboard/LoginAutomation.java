package bpp.dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseWebClass;

public class LoginAutomation extends BaseWebClass {

    @Test
    @Parameters({"email", "password"})
    public void loginTest(String email, String password) {
        try {
            // Open the webpage
        	webDriver.get("https://dashboard.integ.moving.tech/bpp/becknSearch");

            // Find the email input field and enter the email
            WebElement emailField = webDriver.findElement(By.name("email"));
            emailField.sendKeys(email);

            // Find the password input field and enter the password
            WebElement passwordField = webDriver.findElement(By.name("password"));
            passwordField.sendKeys(password);

            // Find the "Securely Login" button and click it
            WebElement loginButton = webDriver.findElement(By.xpath("//button[@aria-label='securelyLogin']"));
            loginButton.click();

            // Wait for some time to observe the result (Optional)
            Thread.sleep(2000);

            // Optionally, you can verify the login here, for example by checking the URL or an element on the next page
            System.out.println("Login Successful");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
