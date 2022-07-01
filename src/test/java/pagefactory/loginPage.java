package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

    @FindBy(name = "UserLogin[username]")
    WebElement txt_username;

    @FindBy(name = "UserLogin[password]")
    WebElement txt_password;

    @FindBy(name = "yt0")
    WebElement btn_login;

    WebDriver driver;

    public loginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Dashboard - Casino")) {
            throw new IllegalStateException("This is not login page. The current page is "
                    + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        txt_username.sendKeys(username);
    }

    public void enterPassword(String password) {
        txt_password.sendKeys(password);
    }

    public void clickOnLogin() {
        btn_login.click();
    }

    public void registrationsSteps(String username, String password) {

        txt_username.sendKeys(username);
        txt_password.sendKeys(password);
        btn_login.click();

    }

}
