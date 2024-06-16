package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import io.cucumber.datatable.DataTable;

//Here is where you can write your general function
public class FunctionUtilities {
    public WebDriver driver;
    String actTxt, expTxt, rawTxt, currDate, expDate, expFileName, directoryDownload;
    Boolean fileMatches, folderExists;
    List<String> lstActTxt, lstExpTxt;
    List<String> rlstActTxt, rlstExpTxt;
    List<WebElement> eleList;
    List<WebElement> txtList;
    Select select;
    Actions actions;
    File fileDirectoryDownload;

    public FunctionUtilities(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    /**
     * Highlight Element that interacted in website
     *
     * @param element -> WebElement that interacted to
     */
    public void highlightElement(WebElement element) {
        String jsStyle = "'3px solid yellow'";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border=" + jsStyle, element);
    }

    /**
     * Get Page Source Text in Website
     *
     * @return PageSource Text on String Type Variable
     */
    public String getTextByPageSource() {
        String pageSourceText = driver.getPageSource();
        //System.out.println(pageSourceText);
        return pageSourceText;
    }

    /**
     * Input String into Text List from Website
     * Purpose : Comparation in DataTable
     *
     * @param input -> Single Defined Value String
     * @return List of Actual Text
     */
    public List<String> inputStringtoList(String input) {
        lstActTxt = new ArrayList<String>();
        lstActTxt.add(input);
        return lstActTxt;
    }

    /**
     * Get Sub Menu Title Section Text By CSS Selector
     * Purpose : Get Raw Text before being processed by string operator
     *
     * @return Single String Raw Text
     */
    public String getSubMenuTitleSectionTxt() {
        WebElement txtList = driver.findElement(By.cssSelector("div.title-section"));
        rawTxt = txtList.getText();
        return rawTxt;
    }

    /**
     * Get Sub Menu Workform Section
     *
     * @return Single String Raw Text
     */
    public String getSubMenuWorkformSectionTxt() {
        WebElement txtList = driver.findElement(By.cssSelector("div>form[id = 'workForm']"));
        rawTxt = txtList.getText();
        return rawTxt;
    }

    /**
     * Get Text By Body
     *
     * @return Single String Raw Text
     */
    public String getTextByBody() {
        WebElement txtList = driver.findElement(By.tagName("body"));
        rawTxt = txtList.getText();
        return rawTxt;
    }

    /**
     * Get Text of a WebElement
     *
     * @param element -> Declared WebElement
     * @return Single String Raw Text
     */
    public String getTextByWebElement(WebElement element) {
        rawTxt = element.getText();
        return rawTxt;
    }

    /**
     * Get Actual Text By Body on List
     *
     * @return List of Actual Text
     */
    public List<String> getActualTextOnList() {
        getTextByBody();
        lstActTxt = new ArrayList<String>(Arrays.asList(rawTxt.split("[\\r\\n]+")));
//		Info : For printing raw Text from Body (raw text)
//		System.out.println(rawTxt);
//		Info : For printing clean text and index value of actual text
        int index = 0;
        for (String list : lstActTxt) {
            System.out.println(String.valueOf(index++) + ":" + list);
        }
        return lstActTxt;
    }


    /**
     * Get the attribute of element, example : name of element
     *
     * @param element   -> Element of desired of element
     * @param attribute -> Attribute of desired element
     * @return actTxt -> Element attribute as actual text
     */
    public String getElementAttribute(WebElement element, String attribute) {
        actTxt = element.getAttribute(attribute);
        return actTxt;
    }

    /**
     * Wait until certain time to do the next action
     *
     * @param timeout -> timeout in seconds
     */
    public void delay(Integer timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hover to element based on locator
     *
     * @param element -> desired element
     */
    public void hoverElement(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    /**
     * Hard Assert of condition : Must be TRUE
     *
     * @param variables -> boolean of variables that need to justify
     */
    public void assertTrue(boolean variables) {
        try {
            Assert.assertTrue(variables);
            System.out.println("Result : True");
        } catch (AssertionError e) {
            System.out.println("Result : False");
            throw e;
        }
    }

    /**
     * Hard Assert of condition : Must be FALSE
     *
     * @param variables ->  boolean of variables that need to justify
     */
    public void assertFalse(boolean variables) {
        try {
            Assert.assertFalse(variables);
            System.out.println("Result : False");
        } catch (AssertionError e) {
            System.out.println("Result : True");
            throw e;
        }
    }

    /**
     * Hard Assert of condition : Must be EQUAL
     *
     * @param actual   -> expected Object that need to be compared
     * @param expected -> actual Object that need to be compared
     */
    public void assertEquals(Object actual, Object expected) {
        try {
            Assert.assertEquals(actual, expected);
            System.out.println("Result : Equal");
        } catch (AssertionError e) {
            System.out.println("Result : Not Equal");
            driver.quit();
            throw e;
        }
    }

    /**
     * Get list of expected Text from DataTable
     *
     * @param dataTable -> dataTable on gherkin side
     * @return List of expected Text
     */
    public List<String> getExpectedTextByDataTable(DataTable dataTable) {
        return lstExpTxt = dataTable.asList();
    }

    /**
     * Switch to iFrame on Website
     *
     * @param element -> iFrame WebElement
     */
    public void switchIFrame(WebElement element) {
        driver.switchTo().frame(element);
    }

    /**
     * Switch to default frame on Website
     */
    public void switchDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    /**
     * Dismissing window alert
     */
    public void dismissWindowAlert() {
        driver.switchTo().alert().dismiss();
    }

    /**
     * Accept Window Alert
     */
    public void acceptWindowAlert() {
        driver.switchTo().alert().accept();
    }

    /**
     * Get Window Alert Text
     *
     * @return actual Text from Window Alert
     */
    public String getWindowAlertText() {
        int i = 0;
        while (i++ < 5) {
            try {
                actTxt = driver.switchTo().alert().getText();
                break;
            } catch (NoAlertPresentException e) {
                delay(2);
                continue;
            }
        }
        return actTxt;
    }

    /**
     * Clean Folder on directory so later on download new file
     *
     * @throws IOException -> Error Exception
     */
    public void cleanFolder() throws IOException {
        File directory = new File(directoryDownload);
        FileUtils.cleanDirectory(directory);
    }

    /**
     * Check whether the downloading process is succeeded and the file is on directory with right content(naming)
     *
     * @param dataTable -> fileName from Gherkin
     * @return result of checking whether true or false
     */
    public boolean checkFileAvailable(DataTable dataTable) {
        File folder = new File(directoryDownload);
        File[] listOfFiles = folder.listFiles();
        fileMatches = false;
        getExpectedTextByDataTable(dataTable);
        expFileName = lstExpTxt.get(0);
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("File " + fileName);
                if (fileName.matches(expFileName)) {
                    fileMatches = true;
                }
            }
        }
        return fileMatches;
    }

    /**
     * Get List Menu Text of Dropdown function
     *
     * @param number -> index of menu
     * @return rawList-ofActualText -> raw list of actual text
     */
    public List<String> getListMenuDropdownText(int number) {
        eleList = driver.findElements(By.cssSelector(String.format("div>ul>li:nth-child(%d)>ul>li>ul>li", number)));
        rlstActTxt = new ArrayList<String>();
        for (WebElement e : eleList) {
            rlstActTxt.add(e.getAttribute("textContent"));
            rlstActTxt.removeAll(Arrays.asList("", null));
        }
//		int index = 0;
//		for(String list : lstActTxt) {
//			System.out.println(String.valueOf(index++) + ":" + list.trim());
//		}
//		System.out.println(lstActTxt);
        return rlstActTxt;
    }

    /**
     * Function Select Element
     *
     * @param element -> WebElement that we want to be selected
     * @return select -> element that has been selected
     */
    public Select selectElement(WebElement element) {
        select = new Select(element);
        return select;
    }

    /**
     * Select Dropdown By Text
     *
     * @param value   -> value of text
     * @param element -> element that we want to select
     */
    public void selectDropdownByText(WebElement element, String value) {
        select = selectElement(element);
        select.selectByVisibleText(value);
    }

    /**
     * Select Dropdown By Value
     *
     * @param element -> element that we want to select
     * @param value   -> value that we wanted
     */
    public void selectDropDownByValue(WebElement element, String value) {
        select = selectElement(element);
        select.selectByValue(value);
    }

    /**
     * Select Dropdown by Index
     *
     * @param element element that we want to select
     * @param index   -> index of element
     */
    public void selectDropdownByIndex(WebElement element, int index) {
        select = selectElement(element);
        select.deselectByIndex(index);
    }

    /**
     * @param liIndex ->
     */
    public void hoverMainMenuByCSSSelector(int liIndex) {
        eleList = driver.findElements(By.cssSelector(String.format("div#navbar>ul>li:nth-child(%d)", liIndex)));
        WebElement mainMenu = eleList.get(0);
        hoverElement(mainMenu);
    }

    public String conRead() throws IOException {
        File src = new File(".\\resources\\config.properties");
        FileInputStream fis = new FileInputStream(src);
        Properties prop = new Properties();
        prop.load(fis);
        directoryDownload = prop.getProperty("directoryDownload");
        return directoryDownload;
    }

    public void makeDirectory() {
        fileDirectoryDownload = new File(directoryDownload);
        fileDirectoryDownload.mkdirs();
    }

    public boolean checkFolder() throws IOException {
        conRead();
        System.out.println("directory : " + directoryDownload);
        File directory = new File(directoryDownload);
        folderExists = directory.exists();
        return folderExists;
    }
}
	
