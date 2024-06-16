package pageObjects.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.cucumber.datatable.DataTable;
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
    WebElement usernameInputText;
    @FindBy(id = "password")
    WebElement passwordInputText;
    @FindBy(id = "login-button1")
    WebElement loginButton;

    public void inputTextOnUsername(String username) {
        Helper.logEvent("Input text on username field");
        usernameInputText.sendKeys(username);
    }

    public void inputTextOnPassword(String password) {
        Helper.logEvent("Input text on password field");
        passwordInputText.sendKeys(password);
    }

    public void clickLoginButton() {
        Helper.logEvent("Click login button");
        bp.assertElementTextByAttributeString(loginButton,"value","Login");
        loginButton.click();
    }
}
