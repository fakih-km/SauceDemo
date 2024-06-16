package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.TestContextSetup;

//Hooks for before/after test

public class Hooks {
	TestContextSetup tcs;
	
	public Hooks(TestContextSetup tcs){
		this.tcs = tcs;
	}
	
	@Before
	public void setUp() throws Exception {
		tcs.testBase.WebDriverManager();
	}
	
	@After
    public void tearDown(Scenario scenario) throws Exception {
		WebDriver driver = tcs.testBase.WebDriverManager();
		System.out.println("Scenario Status : " +scenario.getStatus());
        if(scenario.isFailed()) {
        	TakesScreenshot ts = (TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
		}
		tcs.testBase.WebDriverManager().quit();
	}

}
