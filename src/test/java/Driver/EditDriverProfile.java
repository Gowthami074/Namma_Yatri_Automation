package Driver;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import base.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebElement;
public class EditDriverProfile extends BaseClass {
    private WebDriverWait wait;
    private void clickElement(String xpath, String message) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath)));
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(xpath))).click();
            System.out.println("Clicked on " + message);
        } catch (Exception e) {
            System.out.println("Failed to click on " + message + ". Exception: " + e.getMessage());
        }
    }
    private void enterText(String xpath, String text, String message) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath)));
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(xpath))).sendKeys(text);
            System.out.println(message + ": " + text);
        } catch (Exception e) {
            System.out.println("Failed to enter text in " + message + ". Exception: " + e.getMessage());
        }
    }
    private void scrollUntilSubmitButtonVisible() {
        try {
            WebElement scrollView = driver.findElement(AppiumBy.xpath("//android.widget.ScrollView/android.widget.LinearLayout"));
            while (true) {
                try {
                    if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Submit']")).isDisplayed()) {
                        System.out.println("Submit Button is visible.");
                        return;
                    }
                } catch (Exception e) {
                    // Continue scrolling
                }
                ((AndroidDriver) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                    "elementId", ((RemoteWebElement) scrollView).getId(),
                    "direction", "up",
                    "percent", 0.75
                ));
            }
        } catch (Exception e) {
            System.out.println("Failed to scroll until Submit button is visible. Exception: " + e.getMessage());
        }
    }
    private void selectYear(String targetYear) {
        try {
            clickElement("//android.widget.FrameLayout[@resource-id='in.juspay.nammayatripartner.debug:id/cl_dui_container']"
                    + "/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout"
                    + "/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout"
                    + "/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.ScrollView"
                    + "/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]", "Driving Since Section");
            ((AndroidDriver) driver).findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"" + targetYear + "\"));"));
            clickElement("//android.widget.TextView[@text='" + targetYear + "']", "Selected Year " + targetYear);
        } catch (Exception e) {
            System.out.println("Failed to select the year " + targetYear + ". Exception: " + e.getMessage());
        }
    }
    @Test
    public void editDriverProfile() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(5000);
        clickElement("//android.widget.FrameLayout[@resource-id=\"in.juspay.nammayatripartner.debug:id/cl_dui_container\"]"
                     + "/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout"
                     + "/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout"
                     + "/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout[3]"
                     + "/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]"
                     + "/android.widget.RelativeLayout/android.widget.ImageView", "Driver Profile");
        Thread.sleep(2000);
        clickElement("//android.widget.TextView[@text=\"Complete Profile\"]", "Complete Profile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Safe Journey\"]")));
        System.out.println("Safe Journey button is visible and clickable");
        String[] pledges = { "Safe Journey", "Clean Vehicle", "On-time Pick up", "Regular Maintenance", "Good Service", "Smooth Driving", "No Cancellation", "Other" };
        for (String pledge : pledges) {
            clickElement("//android.widget.TextView[@text=\"" + pledge + "\"]", pledge);
        }
        enterText("//android.widget.EditText[@text=\"Please Write Something\"]", "I Will Drive Slowly", "Entered pledge reason");
        clickElement("//android.widget.TextView[@content-desc=\"Submit Other\"]", "Save Button");
        String[] vehicleOffers = { "Radio", "Device Charging", "Boot Space", "Pet Friendly" };
        for (String offer : vehicleOffers) {
            clickElement("//android.widget.TextView[@text=\"" + offer + "\"]", offer);
        }
        scrollUntilSubmitButtonVisible();
        clickElement("//android.widget.EditText", "Home Town Text Box");
        enterText("//android.widget.EditText", "Davangere", "Typed Home Town");
        try {
            ((HidesKeyboard) driver).hideKeyboard();
            System.out.println("Keyboard closed after entering Home Town.");
        } catch (Exception e) {
            System.out.println("No keyboard to close.");
        }
        selectYear("1997");
        clickElement("//android.widget.TextView[@text='Buy New Home']", "Buy New Home");
        clickElement("//android.widget.TextView[@text='Kid's Education']", "Kids Education");
        clickElement("//android.widget.TextView[@text='Buy New Vehicle']", "Buy New Vehicle");
        clickElement("//android.widget.TextView[@text='Other']", "Other");
        enterText("//android.widget.EditText[@text='Please Write Something']", "Auto", "Entered Auto in text box");
        clickElement("//android.widget.TextView[@content-desc='Submit Other']", "Save Button");
        clickElement("//android.widget.TextView[@text='Submit']", "Submit Button");
    }
}