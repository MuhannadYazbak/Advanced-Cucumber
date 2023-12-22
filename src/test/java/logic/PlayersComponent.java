package logic;

import infra.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersComponent extends BasePage {
    private final String TABLE_XPATH = "//table";
    private final String ROWS_XPATH = "//tbody//tr";
    private WebElement table;
    private List<WebElement> players;
    public PlayersComponent(WebDriver driver) {
        super(driver);
        this.table = this.driver.findElement(By.xpath(TABLE_XPATH));
        this.players = this.table.findElements(By.xpath(ROWS_XPATH));
    }

    private WebElement findPlayerRowByName(String playerName) {
        return this.players.stream()
                .filter(row -> {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    return cells.size() >= 2 && cells.get(1).getText().equals(playerName);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player not found: " + playerName));
    }
    public void clickOnPlayer(String playerName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement player = findPlayerRowByName(playerName);
        System.out.println("player: "+ player.getText());
        WebElement clickable = wait.until(ExpectedConditions.elementToBeClickable(
                player.findElement(By.xpath(".//td[a[contains(@href, '/player')]]"))));
        String link = clickable.findElement(By.tagName("a")).getAttribute("href");
        this.driver.get(link);
    }

    public boolean fgaCheck(){
        int size = this.players.stream()
                .filter(row -> {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    return cells.size() >= 2 && (!cells.get(7).getText().equals("21.8"));
                }).toList().size();
        if (size == 0) return true;
        return false;
    }
}
