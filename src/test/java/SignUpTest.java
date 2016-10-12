import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpTest extends WeeblyTest{

    private final String SIGN_UP_BUTTON = "//*[@id='sign-up-button']";
    private final String SIGN_UP_FORM_NAME = "//*[@id='overlay-signup-form-name']";
    private final String SIGN_UP_FORM_EMAIL = "//*[@id='overlay-signup-form-email']";
    private final String SIGN_UP_FORM_PASSWORD = "//*[@id='overlay-signup-form-pass']";
    private final String SIGN_UP_FORM_SUBMIT = "//*[contains(@class, 'signup-form__submit signup-btn submit-btn')]";

    private final String CHOOSE_THEME_SCREEN = "//*[@id='choose-theme-heading']";
    private final String CHOOSE_THEME_BUTTON = "//*[@id=\"w-theme-list\"]/li[1]/div/div[2]/div/div[2]/a";
    private final String CHOOSE_THEME_IMAGE =  "//*[@id='w-theme-list']/li[1]/div/div[1]";

    private final String CHOOSE_DOMAIN_SCREEN = "//*[@id='chooseDomain']";
    private final String CHOOSE_DOMAIN_RADIO = "//*[@id='domainSubdomain']/div[1]/input";
    private final String CHOOSE_DOMAIN_FIELD = "//*[@id='weeblyDomain']";
    private final String CHOOSE_DOMAIN_AVAILABLE = "//*[@id='domainSubdomain-2']/div[5]/div";
    private final String CHOOSE_DOMAIN_CONTINUE = "//*[@id='chooseDomainDiv']/div[2]/a/span";



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

        try {
            getElement(CHOOSE_DOMAIN_SCREEN, 15);
            Assert.assertTrue(true);
            logger.debug("Theme selected.");
        } catch (Exception e) {
            Assert.assertTrue("Theme could not be created.", false);
        }

        getElement(CHOOSE_DOMAIN_RADIO).click();
        getElement(CHOOSE_DOMAIN_FIELD).sendKeys(userName);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBePresentInElement(By.xpath(CHOOSE_DOMAIN_AVAILABLE), "Available"));

        getElement(CHOOSE_DOMAIN_CONTINUE).click();


        if (!DEBUG_MODE) driver.quit();

    }
}
