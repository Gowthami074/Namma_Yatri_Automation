package User.Android;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import base.BaseClass;
import io.appium.java_client.AppiumBy;

import java.time.Duration;

public class SwitchLanguage extends BaseClass {

    @Test
    public void switchLanguageTest() {
        String targetLanguage = "kannada"; // Change to "telugu", "hindi", etc., as needed
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(5));

        // Click on Hamburger Menu
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageButton[@content-desc=\"Hamburger menu\"]"))).click();
        System.out.println("Clicked on Hamburger Menu");

        // Click on 'App Language' option
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='App Language']"))).click();
        System.out.println("Clicked on App Language");

        // Use switch statement to select the target language
        switch (targetLanguage.toLowerCase()) {
            case "english":
                wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"English, English\"]/android.view.ViewGroup"))).click();
                System.out.println("Selected English Language");
                break;

            case "kannada":
                wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"ಕನ್ನಡ, Kannada\"]/android.view.ViewGroup"))).click();
                System.out.println("Selected Kannada Language");
                break;

            case "hindi":
                wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"हिन्दी, Hindi\"]/android.view.ViewGroup"))).click();
                System.out.println("Selected Hindi Language");
                break;

            case "tamil":
                wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"தமிழ், Tamil\"]/android.view.ViewGroup"))).click();
                System.out.println("Selected Tamil Language");
                break;

            case "telugu":
                wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"తెలుగు, Telugu\"]/android.view.ViewGroup"))).click();
                System.out.println("Selected Telugu Language");
                break;

            case "bengali":
                wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"বাংলা, Bengali\"]/android.view.ViewGroup"))).click();
                System.out.println("Selected Bengali Language");
                break;

            default:
                System.out.println("Invalid language selection. Please choose a valid language.");
                return;
        }

        // Click on 'Confirm App Language' button
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Confirm App Language']"))).click();
        System.out.println("Clicked on Confirm App Language");

        // After clicking Confirm button, click on "App Language" again with the updated language's XPath
        String appLanguageXpath = getAppLanguageXpath(targetLanguage);
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(appLanguageXpath))).click();
        System.out.println("Clicked on App Language again");

        // Validate the Confirm button text with the expected text for the selected target language
        String confirmButtonXpath = getConfirmButtonXpath(targetLanguage);
        String confirmButtonText = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(confirmButtonXpath))).getText();
        System.out.println("Validated Confirm button text: " + confirmButtonText);
    }

    // Method to return the App Language XPath based on selected language
    private String getAppLanguageXpath(String targetLanguage) {
        switch (targetLanguage.toLowerCase()) {
            case "english":
                return "//android.widget.TextView[@text='App Language']";
            case "kannada":
                return "//android.widget.TextView[@text='ಅಪ್ಲಿಕೇಶನ್ ಭಾಷೆ']";
            case "hindi":
                return "//android.widget.TextView[@text='ऐप भाषा']";
            case "tamil":
                return "//android.widget.TextView[@text='பயன்பாட்டு மொழி']";
            case "telugu":
                return "//android.widget.TextView[@text='అనువర్తన భాష']";
            case "bengali":
                return "//android.widget.TextView[@text='অ্যাপ্লিকেশন ভাষা']";
            default:
                return "//android.widget.TextView[@text='App Language']"; // Default case
        }
    }

    // Method to return the Confirm button XPath based on selected language
    private String getConfirmButtonXpath(String targetLanguage) {
        switch (targetLanguage.toLowerCase()) {
            case "english":
                return "//android.widget.TextView[@text='Confirm App Language']";
            case "kannada":
                return "//android.widget.TextView[@text='ಅಪ್ಲಿಕೇಶನ್ ಭಾಷೆಯನ್ನು ದೃಢೀಕರಿಸಿ']";
            case "hindi":
                return "//android.widget.TextView[@text='ऐप भाषा की पुष्टि करें']";
            case "tamil":
                return "//android.widget.TextView[@text='பயன்பாட்டு மொழியை உறுதிப்படுத்தவும்']";
            case "telugu":
                return "//android.widget.TextView[@text='అనువర్తన భాషను నిర్ధారించండి']";
            case "bengali":
                return "//android.widget.TextView[@text='অ্যাপ্লিকেশন ভাষা নিশ্চিত করুন']";
            default:
                return "//android.widget.TextView[@text='Confirm App Language']"; // Default case
        }
    }
}
