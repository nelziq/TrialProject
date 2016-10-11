import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by trialweeker on 10/10/16.
 */
public class LogInTest extends WeeblyTest {

    private final String LOG_IN_BUTTON = "//*[@id='login-button']";
    private final String LOG_IN_FORM_EMAIL = "//*[@id='weebly-username']";
    private final String LOG_IN_FORM_PASSWORD = "//*[@id='weebly-password']";
    private final String LOG_IN_FORM_SUBMIT = "//*[@id='weebly-login']/p[3]/input";

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

}
