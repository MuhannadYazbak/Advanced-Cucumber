package logic;

import infra.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MainPage extends BasePage {

    private PlayersComponent playersComponent;
    private TeamsComponent teamsComponent;
    private WebElement seasonDropDown;
    private WebElement arrowButtonPlayersTeams;
    private WebElement playerTeamSelector;
    private Select select;

    public MainPage(WebDriver driver) {
        super(driver);
        this.playersComponent = new PlayersComponent(this.driver);
//        this.teamsComponent = new TeamsComponent(this.driver);
        this.seasonDropDown = this.driver.findElement(By.xpath("//p[contains(text(), 'Season')]//following-sibling::div//select"));
        this.arrowButtonPlayersTeams = this.driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div[3]/section[1]/div/nav/div[1]/button"));
        this.playerTeamSelector = this.driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div[3]/section[1]/div/nav/div[1]/ul"));
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
        //WebElement selected = playerTeamSelector.findElement(By.xpath("//li//a[text()='"+option+"']"));
        WebElement selected = playerTeamSelector.findElement(By.xpath(".//li[a[contains(text(), 'Teams')]]/a"));
        String link = selected.getAttribute("href");
        this.driver.get(link);
        this.teamsComponent = new TeamsComponent(this.driver);
        //selected.click();
    }

    public void switchToPlayers(){
        arrowButtonPlayersTeams.click();
        //WebElement selected = playerTeamSelector.findElement(By.xpath("//li//a[text()='"+option+"']"));
        WebElement selected = playerTeamSelector.findElement(By.xpath(".//li[a[contains(text(), 'Players')]]/a"));
        String link = selected.getAttribute("href");
        this.driver.get(link);
        this.playersComponent = new PlayersComponent(this.driver);
        //selected.click();
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

}
