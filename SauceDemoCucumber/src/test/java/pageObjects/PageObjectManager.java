package pageObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.Auth.LoginPage;
import pageObjects.HomePage.ProductPage;

public class PageObjectManager {

    public WebDriver driver;
    public LoginPage loginPage;
    public ProductPage productPage;


    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        loginPage = new LoginPage(driver);
        return loginPage;
    }

    public ProductPage getProductPage() {
        productPage = new ProductPage(driver);
        return productPage;
    }
}