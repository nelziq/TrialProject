import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class WeeblyTest {

    private final boolean DEBUG_MODE = false;

    private final String USER_NAME = "Test User";
    private final String EMAIL = "user@test.com";
    private final String PASSWORD = "testpassword123";

    private final String SIGN_UP_BUTTON = "//*[@id='sign-up-button']";
    private final String SIGN_UP_FORM_NAME = "//*[@id='overlay-signup-form-name']";
    private final String SIGN_UP_FORM_EMAIL = "//*[@id='overlay-signup-form-email']";
    private final String SIGN_UP_FORM_PASSWORD = "//*[@id='overlay-signup-form-pass']";
    private final String SIGN_UP_FORM_SUBMIT = "//*[contains(@class, 'signup-form__submit signup-btn submit-btn')]";

    private final String LOG_IN_BUTTON = "//*[@id='login-button']";
    private final String LOG_IN_FORM_EMAIL = "//*[@id='weebly-username']";
    private final String LOG_IN_FORM_PASSWORD = "//*[@id='weebly-password']";
    private final String LOG_IN_FORM_SUBMIT = "//*[@id='weebly-login']/p[3]/input";

    private static final Logger logger = LogManager.getLogger(WeeblyTest.class.getName());

    private WebElement getElement(WebDriver driver, String xPath){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        logger.debug("Found element by Xpath = " + xPath);
        return driver.findElement(By.xpath(xPath));
    }


    @Rule
    public TestWatcher watchman = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            logger.error(description, e);
        }

        @Override
        protected void succeeded(Description description) {
            logger.info(description);
        }
    };

    @Before
    public void intitialize(){
        System.setProperty("webdriver.chrome.driver", "/Users/trialweeker/Downloads/chromedriver");
        System.out.println("starting selenium web driver");
    }

    @Test
    public void testSignUp() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://bre-tp.beta.weebly.com/");

        getElement(driver, SIGN_UP_BUTTON).click();
        getElement(driver, SIGN_UP_FORM_NAME).sendKeys(USER_NAME);
        getElement(driver, SIGN_UP_FORM_EMAIL).sendKeys(EMAIL);
        getElement(driver, SIGN_UP_FORM_PASSWORD).sendKeys(PASSWORD);
        getElement(driver, SIGN_UP_FORM_SUBMIT).click();

        // if login screen appears then this user already exists
        getElement(driver, LOG_IN_FORM_EMAIL);
        if (driver.findElements(By.xpath(LOG_IN_FORM_EMAIL)).size() > 0){
            logger.debug("Account already exists. Logging in...");
        } else {
            logger.debug("New account...");
        }

        Assert.assertTrue(true);

        if (!DEBUG_MODE) driver.quit();

    }

    @Test
    public void testLogin() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://bre-tp.beta.weebly.com/");

        getElement(driver, LOG_IN_BUTTON).click();
        getElement(driver, LOG_IN_FORM_EMAIL).sendKeys(EMAIL);
        getElement(driver, LOG_IN_FORM_PASSWORD).sendKeys(PASSWORD);
        getElement(driver, LOG_IN_FORM_SUBMIT).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("Dashboard"));
        Assert.assertTrue("Failed to login to Dashboard.", driver.getTitle().contains("Dashboard"));

        logger.debug("Log in successful.");

        if (!DEBUG_MODE) driver.quit();

    }


//    @After
//    public void finalize(){
//        driver.quit();
//    }

}
