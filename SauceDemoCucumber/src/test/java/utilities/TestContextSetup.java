package utilities;

import pageObjects.PageObjectManager;

//This is where you declare all of the general object to later on call on stepDefinitions
public class TestContextSetup {

	public TestBase testBase;
	public Helper helper;
	public FunctionUtilities functionUtilities;
	public PageObjectManager pageObjectManager;
//	public BasePage basePage;

	public TestContextSetup() throws Exception {
		testBase = new TestBase();
		helper = new Helper();
		pageObjectManager = new PageObjectManager(testBase.WebDriverManager());
		functionUtilities = new FunctionUtilities(testBase.WebDriverManager());
	}
}
