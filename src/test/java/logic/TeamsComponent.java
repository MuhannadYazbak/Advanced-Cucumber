package logic;

import infra.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TeamsComponent extends BasePage {
    private final String TABLE_XPATH = "//table";
    private final String ROWS_XPATH = "//tbody//tr";
    private WebElement table;

    public TeamsComponent(WebDriver driver) {
        super(driver);
        this.table = this.driver.findElement(By.xpath(TABLE_XPATH));
    }

    private WebElement findTeamRowByName(String teamName) {
        List<WebElement> teams = table.findElements(By.xpath(ROWS_XPATH));
        return teams.stream()
                .filter(row -> {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    return cells.size() >= 2 && cells.get(1).getText().equals(teamName);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Team not found: " + teamName));
    }

    public void clickOnTeam(String teamName) {
        WebElement team = findTeamRowByName(teamName);
        System.out.println("Team: " + team.getText());
        WebElement clickable = team.findElement(By.xpath(".//td[a[contains(@href, '/team')]]"));
        String link = clickable.findElement(By.tagName("a")).getAttribute("href");
        this.driver.get(link);
    }

}
