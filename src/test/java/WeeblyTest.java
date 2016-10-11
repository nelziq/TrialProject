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

    final boolean DEBUG_MODE = false;

    final String USER_NAME = "Test User";
    final String EMAIL = "user@test.com";
    final String PASSWORD = "testpassword123";

    final String LOG_IN_FORM_EMAIL = "//*[@id='weebly-username']";

    protected static final Logger logger = LogManager.getLogger(WeeblyTest.class.getName());


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

    protected WebElement getElement(WebDriver driver, String xPath){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        logger.debug("Found element by Xpath = " + xPath);
        return driver.findElement(By.xpath(xPath));
    }

    @Before
    public void intitialize(){
        System.setProperty("webdriver.chrome.driver", "/Users/trialweeker/Downloads/chromedriver");
        System.out.println("starting selenium web driver");
    }


//    @After
//    public void finalize(){
//        driver.quit();
//    }

}
