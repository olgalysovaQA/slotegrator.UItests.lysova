package steps;

import Config.ProjectConfig;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.loginPage;
import pagefactory.playerManagementPage;

import java.time.Duration;

public class playerSortingSteps {

    WebDriver driver;

    loginPage login;
    playerManagementPage playerManagement;

    ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    @Before(value = "@Sorting")
    public void browserSetup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Given("user is on player management page")
    public void user_is_on_player_management_page() {
        driver.navigate().to(config.url());

        login = new loginPage(driver);
        login.registrationsSteps(config.username(), config.password());

        playerManagement = new playerManagementPage(driver);
        playerManagement.openPlayerManagementPage();

    }

    @When("user click sort by registration")
    public void user_click_sort_by_registration() {
        playerManagement.sortGamersByRegistration();
    }


    @Then("players is sorted by registration")
    public void players_is_sorted_by_registration() {

        playerManagement.checkSorting();

    }

    @After(value = "@Sorting")
    public void teardown() {

        driver.close();

    }
}
