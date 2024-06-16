package stepDefinitions.Auth;

import io.cucumber.java.en.Given;
import pageObjects.PageObjectManager;
import pageObjects.LoginPage.LoginPage;
import utilities.TestContextSetup;

public class LoginStepDefinitions {
    TestContextSetup tcs;
    public PageObjectManager pageObjectManager;
    public LoginPage lp;

    //	Constructor
    public LoginStepDefinitions(TestContextSetup tcs) {
        this.tcs = tcs;
        lp = tcs.pageObjectManager.getLoginPage();
    }

    @Given("User click button login")
    public void user_click_button_login() {
        lp.clickLoginButton();
    }

    @Given("User input username as {string}")
    public void user_input_username_as(String username) {
        lp.inputTextOnUsername(username);
    }

    @Given("User input password {string}")
    public void user_input_password(String password) {
        lp.inputTextOnPassword(password);
    }
}
