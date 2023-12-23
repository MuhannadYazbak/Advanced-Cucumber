package logic;

import infra.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage extends BasePage {

    private final String SEASON_DROPDOWN = "//p[contains(text(), 'Season')]//following-sibling::div//select";
    private final String ARROW_BUTTON_PLAYERS_TEAMS = "//*[@id='__next']/div[2]/div[2]/div[3]/section[1]/div/nav/div[1]/button";
    private final String PLAYER_TEAM_SELECTOR = "//*[@id='__next']/div[2]/div[2]/div[3]/section[1]/div/nav/div[1]/ul";
    private final String FILTER_BUTTON = "//button[contains(@title, 'custom filter')]";
    private final String FILTER_BY_OPTION = "//*[@id='__next']/div[2]/div[2]/div[3]/section[2]/div/div[2]/div[2]/div[2]/div[1]/div[1]/div/select";
    private final String FILTER_INPUT_XPATH = "//input[@placeholder='Value']";
    private PlayersComponent playersComponent;
    private TeamsComponent teamsComponent;
    private WebElement seasonDropDown;
    private WebElement arrowButtonPlayersTeams;
    private WebElement playerTeamSelector;
    private Select select;
    private WebElement filterButton;
    private  WebElement filterSelectOption;
    private WebElement filterInput;

    public MainPage(WebDriver driver) {
        super(driver);
        this.playersComponent = new PlayersComponent(this.driver);
        this.teamsComponent = new TeamsComponent(this.driver);
        this.seasonDropDown = this.driver.findElement(By.xpath(SEASON_DROPDOWN));
        this.arrowButtonPlayersTeams = this.driver.findElement(By.xpath(ARROW_BUTTON_PLAYERS_TEAMS));
        this.playerTeamSelector = this.driver.findElement(By.xpath(PLAYER_TEAM_SELECTOR));
        this.filterButton = this.driver.findElement(By.xpath(FILTER_BUTTON));

    }
    public void clickOnPlayer(String playerName){
        this.playersComponent.clickOnPlayer(playerName);
    }

    public void selectSeason(String option){
        this.select = new Select(seasonDropDown);
        this.select.selectByVisibleText(option);
    }

    public boolean checkSelectedSeason(String option){
        WebElement selectedOption = this.select.getFirstSelectedOption();
        System.out.println("Selected Season: "+selectedOption.getText());
        return selectedOption.getText().equals(option);
    }
    public void switchToTeams(){
        arrowButtonPlayersTeams.click();
        WebElement selected = playerTeamSelector.findElement(By.xpath(".//li[a[contains(text(), 'Teams')]]/a"));
        String link = selected.getAttribute("href");
        this.driver.get(link);
        this.teamsComponent = new TeamsComponent(this.driver);
    }

    public void switchToPlayers(){
        arrowButtonPlayersTeams.click();
        WebElement selected = playerTeamSelector.findElement(By.xpath(".//li[a[contains(text(), 'Players')]]/a"));
        String link = selected.getAttribute("href");
        this.driver.get(link);
        this.playersComponent = new PlayersComponent(this.driver);
    }
    public void clickOnTeam(String teamName){
        this.teamsComponent.clickOnTeam(teamName);
    }

    public boolean checkPlayer(String firstName, String lastName){
        WebElement firstNameP = this.driver.findElement(By.xpath("//p[text()='"+firstName+"']"));
        WebElement lastNameP = this.driver.findElement(By.xpath("//p[text()='"+lastName+"']"));
        if (firstNameP.isDisplayed() && lastNameP.isDisplayed()) return true;
        else return false;
    }
    public boolean checkTeam(String teamName){
        WebElement teamP = this.driver.findElement(By.xpath("//p[text()='"+teamName+"']"));
        if (teamP.isDisplayed()) return true;
        else return false;
    }
    public void clickFilter(String option, String value){
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(filterButton));
        Actions actions = new Actions(this.driver);
        actions.moveToElement(filterButton).click().build().perform();
        this.filterSelectOption = this.driver.findElement(By.xpath(FILTER_BY_OPTION));
        this.filterInput = this.driver.findElement(By.xpath(FILTER_INPUT_XPATH));
        filterSelectOption.click();
        Select select1 = new Select(filterSelectOption);
        select1.selectByVisibleText(option);
        filterInput.sendKeys(value);

    }
    public boolean fgaCheck(){
        return this.playersComponent.fgaCheck();
    }

}
