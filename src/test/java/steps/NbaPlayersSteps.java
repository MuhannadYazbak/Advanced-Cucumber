//package steps;
//
//import hooks.MyHooks;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public class NbaPlayersSteps {
//
//    private final WebDriver driver;
//
//    public NbaPlayersSteps(MyHooks hooks) {
//        this.driver = hooks.getDriver();
//    }
//
//    @Given("on nba stats page")
//    public void onNbaStatsPage() {
//        this.driver.get("https://www.nba.com/stats/leaders");
//    }
//
//    @When("click on {string} {string}")
//    public void clickOnPlayer(String firstName, String lastName) {
//        WebElement playerHref = this.driver.findElement(By.xpath("//tr//td//a[text()='" + firstName + " " + lastName + "']"));
//        playerHref.click();
//    }
//
//    @Then("validate {string} {string}")
//    public void validateFirstNameLastName(String firstName, String lastName) {
//        WebElement firstNameP = this.driver.findElement(By.xpath("//p[text()='" + firstName + "']"));
//        WebElement lastNameP = this.driver.findElement(By.xpath("//p[text()='" + lastName + "']"));
//        assert (firstNameP.isDisplayed() && lastNameP.isDisplayed());
//    }
//}

package steps;

import hooks.Hooks;
import infra.TestContext;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.MainPage;
import org.openqa.selenium.WebDriver;

public class NbaPlayersSteps {

   private final String URL = "https://www.nba.com/stats/leaders";
    private WebDriver driver;
    private MainPage mainPage;
    private Hooks hooks;
    private TestContext context;

    public NbaPlayersSteps(Hooks hooks, TestContext context) {
        this.hooks = hooks;
        this.context = context;
    }


    @Given("navigate to the URL")
    public void navigateToURL() {
        hooks.getDriver().get(URL);
        mainPage = new MainPage(hooks.getDriver());

    }

    @When("I click on element with first name {string}")
    public void clickOnElement(String playerName) {
        String[] names = playerName.split(" ");
        context.put("firstName", names[0]);
        context.put("lastName", names[1]);
        mainPage.clickOnPlayer(playerName);
    }

    @Then("I check that the page contains <p> tags with firstName and lastName")
    public void checkForPTags() {
        String firstName = context.get("firstName");
        String lastName = context.get("lastName");
        assert(mainPage.checkPlayer(firstName, lastName));
        assert(!hooks.getDriver().getCurrentUrl().equals(URL));
    }


    @When("Select option {string}")
    public void selectOption(String option) {
        mainPage.selectSeason(option);
    }

    @Then("validate season {string} is selected")
    public void validateSeasonIsSelected(String option) {
        boolean flag = mainPage.checkSelectedSeason(option);
        assert(flag);
    }

    @When("I move to teams")
    public void iMoveToTeams() {
        mainPage.switchToTeams();
    }

    @And("I click on element with team name {string}")
    public void iClickOnElementWithTeamName(String teamName) {
        context.put("team", teamName);
        mainPage.clickOnTeam(teamName);
    }

    @Then("validate got to selected team page")
    public void validateGotToPage() {
        String teamName = context.get("team");
        assert(mainPage.checkTeam(teamName));
        assert(!hooks.getDriver().getCurrentUrl().equals(URL));
    }
}
