package Utils;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AssertionBase {
    protected SoftAssert softAssert;
    protected AndroidDriver driver1;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        softAssert.assertAll();  // Collect all soft assertions at the end
    }


     //* @param useSoft    Pass `true` for Soft Assertion, `false` for Hard Assertion
 
    public void assertCondition(boolean condition, String message, boolean useSoft) {
        if (useSoft) {
            softAssert.assertTrue(condition, message);
        } else {
            Assert.assertTrue(condition, message);
        }
    }

    /**
     * Generalized equality check with Soft or Hard assertion.
     */
    
    public void assertEqual(Object actual, Object expected, String message, boolean useSoft) {
        if (useSoft) {
            softAssert.assertEquals(actual, expected, message);
        } else {
            Assert.assertEquals(actual, expected, message);
        }
    }

    /**
     * Generalized inequality check with Soft or Hard assertion.
     */
    
    public void assertNotEqual(Object actual, Object expected, String message, boolean useSoft) {
        if (useSoft) {
            softAssert.assertNotEquals(actual, expected, message);
        } else {
            Assert.assertNotEquals(actual, expected, message);
        }
    }

    /**
     * Generalized null check with Soft or Hard assertion.
     */
    public void assertNull(Object object, String message, boolean useSoft) {
        if (useSoft) {
            softAssert.assertNull(object, message);
        } else {
            Assert.assertNull(object, message);
        }
    }

    /**
     * Generalized not-null check with Soft or Hard assertion.
     */
    public void assertNotNull(Object object, String message, boolean useSoft) {
        if (useSoft) {
            softAssert.assertNotNull(object, message);
        } else {
            Assert.assertNotNull(object, message);
        }
    }
    /**
   	* Method to check if an element is present (returns true/false)
    */
    public boolean isElementPresent(String xpath) {
        try {
            WebElement element = driver1.findElement(AppiumBy.xpath(xpath));
            return element.isDisplayed(); // Return true if displayed
        } catch (NoSuchElementException e) {
            return false; // Return false if element is NOT found
        }
    }
    public void assertElementVisible(String xpath, String message, boolean useSoft, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath)));
            assertNotNull(element, message, useSoft);
        } catch (Exception e) {
            assertCondition(false, message, useSoft);
        }
    }
    
}
