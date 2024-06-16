package pageObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage.LoginPage;

public class PageObjectManager {

    public WebDriver driver;
    public LoginPage loginPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        loginPage = new LoginPage(driver);
        return loginPage;
    }
}