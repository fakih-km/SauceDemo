package pageObjects.Auth;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;
import utilities.Helper;
import utilities.FunctionUtilities;


public class LoginPage {
    //	Variables
    WebDriver driver;
    FunctionUtilities fuc;
    BasePage bp;

    //Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        fuc = new FunctionUtilities(driver);
        bp = new BasePage(driver);
    }

    //WebElements
    @FindBy(id = "user-name")
    WebElement usernameInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "login-button")
    WebElement loginButton;
    @FindBy(css = "[data-test='title']")
    WebElement pageTitle;

    public void inputTextOnUsername(String username) {
        Helper.logEvent("Input text on username field");
        usernameInput.sendKeys(username);
    }

    public void inputTextOnPassword(String password) {
        Helper.logEvent("Input text on password field");
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        Helper.logEvent("Click login button");
        bp.assertElementTextByAttributeString(loginButton,"value","Login");
        loginButton.click();
    }

    public void verifyTitlePage(DataTable dataTable) {
        Helper.logEvent("Verify Page Title");
        bp.assertSingleElementTextDataTable(pageTitle, dataTable);
    }

    public void verifyUrl(DataTable dataTable) {
        Helper.logEvent("Verify Url");
        bp.assertUrlWithDataTable(dataTable);
    }
}
