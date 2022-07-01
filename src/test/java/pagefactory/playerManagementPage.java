package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class playerManagementPage {

    @FindBy(xpath = "//*[@id=\"header\"]/header/div[3]/ul[2]/li[3]/a/span")
    WebElement admin_menu;

    @FindBy(xpath = "//*[@id=\"header\"]/header/div[3]/ul[2]/li[3]/ul/li[3]/a/i")
    WebElement btn_logout;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[3]/a")
    WebElement btn_players_online;

    @FindBy(id = "payment-system-transaction-grid_c9")
    WebElement btn_sort_by_registration;

    @FindBy(id = "payment-system-transaction-grid")
    WebElement table_player_management;

    @FindBy(xpath = "//*[@id=\"payment-system-transaction-grid\"]/table/tbody/tr")
    List<WebElement> rows;

    WebDriver driver;

    public playerManagementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkLogoutIsDisplayed() {
        admin_menu.click();
        assertTrue(btn_logout.isDisplayed());
    }

    public void openPlayerManagementPage() {
        btn_players_online.click();
    }

    public void checkPlayerManagementIsDisplayed() {
        assertTrue(table_player_management.isDisplayed());
    }

    public void sortGamersByRegistration() {
        btn_sort_by_registration.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("grid-view-loading")));
    }

    public void checkSorting() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.parse("1970-01-01 00:00:00", formatter);

        for (WebElement row : rows) {
            WebElement cell = row.findElement(By.cssSelector("td:nth-child(10)"));
            LocalDateTime time = LocalDateTime.parse(cell.getText(), formatter);
            assertTrue(currentTime.isBefore(time));
            currentTime = time;
        }
    }

}
