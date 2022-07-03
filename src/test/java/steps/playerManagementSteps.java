package steps;

import Config.ProjectConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.loginPage;
import pagefactory.playerManagementPage;


import java.time.Duration;

public class playerManagementSteps {

    WebDriver driver;
    loginPage login;
    playerManagementPage playerManagement;

    ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    @Before(value = "@list")
    public void browserSetup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }

    @When("user is logged in")
    public void user_is_logged_in() {
        driver.navigate().to(config.url());

        login = new loginPage(driver);
        login.registrationsSteps(config.username(), config.password());

        playerManagement = new playerManagementPage(driver);
        playerManagement.checkLogoutIsDisplayed();
    }

    @And("user clicks on players online button")
    public void user_clicks_on_players_online_button() {

        playerManagement.openPlayerManagementPage();

    }

    @Then("list of gamers on player management page is open")
    public void list_of_gamers_on_player_management_page_is_open() {
        playerManagement.checkPlayerManagementIsDisplayed();
    }

    @After(value = "@list")
    public void teardown() {

        driver.close();

    }

}
