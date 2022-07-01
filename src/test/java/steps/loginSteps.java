package steps;

import Config.PropertiesFile;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.loginPage;
import pagefactory.playerManagementPage;

import java.io.IOException;
import java.time.Duration;

public class loginSteps {

    WebDriver driver = null;
    loginPage login;
    playerManagementPage playerManagement;
    public static String username;
    public static String password;
    public static String url;

    @Before(value = "@login")
    public void browserSetup() throws IOException {

        PropertiesFile.readPropertiesFile();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Given("user is on login page")
    public void verifyLoginPage() {

        driver.navigate().to(url);
    }

    @When("user enters username and password")
    public void user_enters_username_and_password() {

        login = new loginPage(driver);
        login.enterUsername(username);
        login.enterPassword(password);

    }

    @And("clicks on sing in button")
    public void clicks_on_sing_in_button() {

        login.clickOnLogin();

    }

    @Then("user is navigated to the admin panel")
    public void user_is_navigated_to_the_admin_panel() {

        playerManagement = new playerManagementPage(driver);
        playerManagement.checkLogoutIsDisplayed();

    }

    @After(value = "@login")
    public void teardown() {

        driver.close();

    }
}
