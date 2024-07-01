package stepDefinitions.HomePage;

import io.cucumber.java.en.And;
import pageObjects.Auth.LoginPage;
import pageObjects.HomePage.ProductPage;
import pageObjects.PageObjectManager;
import utilities.TestContextSetup;

public class ProductPageStepDefinitions {
    TestContextSetup tcs;
    public PageObjectManager pageObjectManager;
    public ProductPage pp;

    //	Constructor
    public ProductPageStepDefinitions(TestContextSetup tcs) {
        this.tcs = tcs;
        pp = tcs.pageObjectManager.getProductPage();
    }
    @And("User click hamburger menu")
    public void userClickHamburgerMenu(){
        pp.clickHamburgerMenu();
    }
    @And("User click logout")
    public void userClickLogout(){
        pp.clickLogoutLinkText();
    }
}
