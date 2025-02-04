package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

public class BaseWebClass {

    protected WebDriver webDriver;

    @BeforeSuite
    public void setup() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Initialize ChromeOptions
        ChromeOptions options = new ChromeOptions();
     //   options.addArguments("--headless"); // Uncomment if you want to run in headless mode

        // Initialize the WebDriver
        webDriver = new ChromeDriver(options);

        // Additional setup tasks can be done here
        System.out.println("Driver Initialized and Setup Completed");
    }

    public WebDriver getDriver() {
        return webDriver;
    }
}
