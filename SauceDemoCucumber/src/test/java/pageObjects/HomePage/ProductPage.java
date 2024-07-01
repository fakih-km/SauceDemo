package pageObjects.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;
import utilities.FunctionUtilities;
import utilities.Helper;

public class ProductPage {
    WebDriver driver;
    FunctionUtilities fuc;
    BasePage bp;

    //Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        fuc = new FunctionUtilities(driver);
        bp = new BasePage(driver);
    }
    @FindBy(id = "react-burger-menu-btn")
    WebElement hamburgerMenu;
    @FindBy(css = "[data-test='logout-sidebar-link']")
    WebElement logoutLinkText;

    public void clickHamburgerMenu(){
        Helper.logEvent("Click hamburger menu");
        hamburgerMenu.click();
    }
    public void clickLogoutLinkText(){
        Helper.logEvent("Click Logout");
        logoutLinkText.click();
    }
}
