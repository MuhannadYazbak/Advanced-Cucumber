package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import infra.BrowserWrapper;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private  WebDriver driver;
    private BrowserWrapper browserWrapper;

    @Before
    public  void setup() {
        // Use the shared WebDriver instance from BrowserWrapper
        browserWrapper = new BrowserWrapper();
        driver = browserWrapper.getDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset the static driver instance
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
