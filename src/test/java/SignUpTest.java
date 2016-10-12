import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SignUpTest extends WeeblyTest{

    private final String SIGN_UP_BUTTON = "//*[@id='sign-up-button']";
    private final String SIGN_UP_FORM_NAME = "//*[@id='overlay-signup-form-name']";
    private final String SIGN_UP_FORM_EMAIL = "//*[@id='overlay-signup-form-email']";
    private final String SIGN_UP_FORM_PASSWORD = "//*[@id='overlay-signup-form-pass']";
    private final String SIGN_UP_FORM_SUBMIT = "//*[contains(@class, 'signup-form__submit signup-btn submit-btn')]";

    private final String CHOOSE_THEME_SCREEN = "//*[@id='choose-theme-heading']";
    private final String CHOOSE_THEME_BUTTON = "//*[@id=\"w-theme-list\"]/li[1]/div/div[2]/div/div[2]/a";
    private final String CHOOSE_THEME_IMAGE =  "//*[@id='w-theme-list']/li[1]/div/div[1]";

    @Test
    public void testSignUp() {

        intitialize();
        driver.get("https://bre-tp.beta.weebly.com/");

        getElement(SIGN_UP_BUTTON).click();

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        String userName = USER_NAME + randomInt;

        getElement(SIGN_UP_FORM_NAME).sendKeys(userName);
        getElement(SIGN_UP_FORM_EMAIL).sendKeys("user" + randomInt + "@test.com");
        getElement(SIGN_UP_FORM_PASSWORD).sendKeys(PASSWORD);
        getElement(SIGN_UP_FORM_SUBMIT).click();

        // if login screen appears then this user already exists

        //*[@id="choose-theme-heading"]

        //getElement(LOG_IN_FORM_SCREEN);
        try {
            getElement(CHOOSE_THEME_SCREEN);
            Assert.assertTrue(true);
            logger.debug("New account created.");
        } catch (Exception e) {
            Assert.assertTrue("Account could not be created.", false);
        }

        Actions action = new Actions(driver);
        WebElement themeImage = getElement(CHOOSE_THEME_IMAGE);
        action.moveToElement(themeImage).perform();
        action.click(getElement(CHOOSE_THEME_BUTTON)).perform();

        if (!DEBUG_MODE) driver.quit();

    }
}
