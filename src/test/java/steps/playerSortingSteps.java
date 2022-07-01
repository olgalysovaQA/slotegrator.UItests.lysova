package steps;

import Config.PropertiesFile;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.loginPage;
import pagefactory.playerManagementPage;

import java.io.IOException;
import java.time.Duration;

public class playerSortingSteps {

    WebDriver driver;

    loginPage login;
    playerManagementPage playerManagement;

    public static String username;
    public static String password;
    public static String url;


    @Before(value = "@Sorting")
    public void browserSetup() throws IOException {

        PropertiesFile.readPropertiesFile();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Given("user is on player management page")
    public void user_is_on_player_management_page() {
        driver.navigate().to(url);

        login = new loginPage(driver);
        login.registrationsSteps(username, password);

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
