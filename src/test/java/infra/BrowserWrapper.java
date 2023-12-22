package infra;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserWrapper {
    private WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public  WebDriver createWebDriver(String url) {
        WebDriver driver = getDriver();
        driver.get(url);
        return driver;
    }
}
