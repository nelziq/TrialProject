import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignUpTest extends WeeblyTest{

    private final String SIGN_UP_BUTTON = "//*[@id='sign-up-button']";
    private final String SIGN_UP_FORM_NAME = "//*[@id='overlay-signup-form-name']";
    private final String SIGN_UP_FORM_EMAIL = "//*[@id='overlay-signup-form-email']";
    private final String SIGN_UP_FORM_PASSWORD = "//*[@id='overlay-signup-form-pass']";
    private final String SIGN_UP_FORM_SUBMIT = "//*[contains(@class, 'signup-form__submit signup-btn submit-btn')]";

    @Test
    public void testSignUp() {

        intitialize();
        driver.get("https://bre-tp.beta.weebly.com/");

        getElement(SIGN_UP_BUTTON).click();
        getElement(SIGN_UP_FORM_NAME).sendKeys(USER_NAME);
        getElement(SIGN_UP_FORM_EMAIL).sendKeys(EMAIL);
        getElement(SIGN_UP_FORM_PASSWORD).sendKeys(PASSWORD);
        getElement(SIGN_UP_FORM_SUBMIT).click();

        // if login screen appears then this user already exists
        getElement(LOG_IN_FORM_EMAIL);
        if (driver.findElements(By.xpath(LOG_IN_FORM_EMAIL)).size() > 0){
            logger.debug("Account already exists. Logging in...");
        } else {
            logger.debug("New account...");
        }

        Assert.assertTrue(true);

        if (!DEBUG_MODE) driver.quit();

    }
}
