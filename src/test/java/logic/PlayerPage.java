package logic;

import infra.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlayerPage extends BasePage {
    private final String FIRSTNAME_XPATH = "//p[text()='']";
    private final String LASTNAME_XPATH = "//p[text()='']";
    private WebElement firstNameP;
    private WebElement lastNameP;
    public PlayerPage(WebDriver driver) {
        super(driver);
    }

}
