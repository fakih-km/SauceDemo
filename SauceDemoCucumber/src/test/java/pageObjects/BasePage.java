package pageObjects;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.FunctionUtilities;
import utilities.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage {
    //Variables
    WebDriver driver;
    FunctionUtilities fuc;
    String actTxt, expTxt, actUrl;
    String actDate, expDate;
    /*
    rlstActTxt -> Stands for Raw List Actual Text before being manipulated by function
    lstActTxt -> Stands for List Actual Text after being manipulated by function
    rlstExpTxt -> Stands for Raw List Expected Text before being manipulated by function
    lstExpTxt -> Stands for List Expected Text after being manipulated by function
     */
    List<String> rlstActTxt, rlstExpTxt, lstActTxt, lstExpTxt;
    List<WebElement> eleList;
    Boolean fileMatches;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        fuc = new FunctionUtilities(driver);
    }

    //Function

    /**
     * Get Expected List Text based on DataTable
     *
     * @param dataTable -> DataTable from gherkin
     * @return List of Expected Text
     */
    public List<String> getCleanExpectedList(DataTable dataTable) {
        rlstExpTxt = fuc.getExpectedTextByDataTable(dataTable);
        lstExpTxt = rlstExpTxt.stream().map(String::trim).collect(Collectors.toList());
        return lstExpTxt;
    }

    /**
     * Get Clean Text of Actual List
     *
     * @param list -> List of Actual Text that contain Raw Text
     * @return Clean Actual List of Text
     */
    public List<String> getCleanActualList(List<String> list) {
        lstActTxt = list.stream().map(String::trim).collect(Collectors.toList());
        return lstActTxt;
    }


    /**
     * Hard Assert Element is Present, Result must be TRUE
     *
     * @param element -> Element that searched to be present
     */
    public void assertElementPresent(WebElement element) {
        boolean resultElement = element.isDisplayed();
        fuc.assertTrue(resultElement);
    }

    /**
     * Hard Assert attribute of element based on DataTable, Result must be Equal
     *
     * @param element   -> Element that we want to get the attribute is
     * @param attribute -> Attribute of the element
     * @param dataTable -> DataTable from Gherkin
     */
    public void assertElementTextByAttribute(WebElement element, String attribute, DataTable dataTable) {
        actTxt = fuc.getElementAttribute(element, attribute);
        rlstExpTxt = fuc.getExpectedTextByDataTable(dataTable);
        expTxt = rlstExpTxt.get(0);
        fuc.assertEquals(actTxt, expTxt);
    }

    /**
     * Hard Assert attribute of element based on String, Result must be Equal
     *
     * @param element   -> Element that we want to get the attribute is
     * @param attribute -> Attribute of the element
     * @param expTxt    -> String of Expected Text
     */
    public void assertElementTextByAttributeString(WebElement element, String attribute, String expTxt) {
        actTxt = fuc.getElementAttribute(element, attribute);
        fuc.assertEquals(actTxt, expTxt);
    }

    /**
     * Hard Assert Element Text By DataTable, Result must be Equals
     *
     * @param element   -> Element that we want to get text from
     * @param dataTable -> DataTable from Gherkin
     */
    public void assertSingleElementTextDataTable(WebElement element, DataTable dataTable) {
        actTxt = fuc.getTextByWebElement(element);
        rlstExpTxt = fuc.getExpectedTextByDataTable(dataTable);
        expTxt = rlstExpTxt.get(0);
        fuc.assertEquals(actTxt, expTxt);
    }

    /**
     * Hard Assert Element Text By String, Result must be Equals
     *
     * @param element -> Element that we want to get text from
     * @param expTxt  -> String of Expected Text
     */
    public void assertSingleElementTextString(WebElement element, String expTxt) {
        actTxt = fuc.getTextByWebElement(element);
        fuc.assertEquals(expTxt, actTxt);
    }

    /**
     * Hard Assert Text is Present compared to PageSource, Result must be TRUE
     *
     * @param dataTable -> DataTable from Gherkin
     */
    public void assertPageSourceTextDataTable(DataTable dataTable) {
        actTxt = fuc.getTextByPageSource();
        rlstExpTxt = fuc.getExpectedTextByDataTable(dataTable);
        int expSize = rlstExpTxt.size();
        for (int i = 0; i < expSize; i++) {
            String getExp = rlstExpTxt.get(i);
            Boolean cond = actTxt.contains(getExp);
            fuc.assertTrue(cond);
            //System.out.println("String : " + getExp + "is : " + cond);
        }
    }

    /**
     * Hard Assert alert text based on String
     *
     * @param expTxt -> String of Expected Text
     */
    public void assertWindowAlertText(String expTxt) {
        actTxt = fuc.getWindowAlertText();
        fuc.assertEquals(expTxt, actTxt);
    }

    /**
     * Hard Assert Window Alert DataTable, Result must be Equals
     *
     * @param dataTable -> DataTable from Gherkin
     */
    public void assertWindowAlertDataTable(DataTable dataTable) {
        actTxt = fuc.getWindowAlertText();
        lstExpTxt = getCleanExpectedList(dataTable);
        expTxt = lstExpTxt.get(0);
        Helper.logEvent("Actual Text is : " + actTxt);
        Helper.logEvent("Expected Text is : " + expTxt);
        fuc.assertEquals(expTxt, actTxt);
    }

    /**
     * Assert List of 3 Button, Result must be Equal
     *
     * @param el1       -> First Button of Element
     * @param el2       -> Second Button of Element
     * @param el3       -> Third Button of Element
     * @param dataTable -> DataTable from Gherkin
     */
    public void assertListButton3(WebElement el1, WebElement el2, WebElement el3, DataTable dataTable) {
        String e1 = el1.getAttribute("value");
        System.out.println(e1);

        String e2 = el2.getAttribute("value");
        System.out.println(e2);

        String e3 = el3.getAttribute("value");
        System.out.println(e3);

        String[] btnArr = {e1, e2, e3};
        List<String> lstBtnTxt = new ArrayList<String>();
        for (int i = 0; i < btnArr.length; i++) {
            lstBtnTxt.add(btnArr[i]);
        }
        fuc.getExpectedTextByDataTable(dataTable);
        fuc.assertEquals(lstBtnTxt, rlstExpTxt);
    }

    /**
     * Assert Download File Match DataTable, Result must be TRUE
     *
     * @param element   -> Download button
     * @param dataTable -> DataTable from Gherkin
     * @throws IOException -> Exception Handling
     */
    public void assertDownloadFileMatchDataTable(WebElement element, DataTable dataTable) throws IOException {
        folderExists();
        element.click();
        fuc.delay(3);
        fileMatches = fuc.checkFileAvailable(dataTable);
        fuc.assertTrue(fileMatches);
    }

    /**
     * Assert Dropdown Text based on Menu, Result must be EQUALS
     *
     * @param number    -> index of Menu
     * @param dataTable -> DataTable from Gherkin
     */
    public void assertMenuDropdownText(int number, DataTable dataTable) {
        rlstActTxt = fuc.getListMenuDropdownText(number);
        getCleanActualList(rlstActTxt);
        getCleanExpectedList(dataTable);
        fuc.assertEquals(lstExpTxt, lstActTxt);
    }

    /**
     * Hard Assert Table Text Not Present by DataTable, Result must False
     *
     * @param element   -> WebElement
     * @param dataTable -> DataTable from Gherkin
     */
    public void assertTableTextNotPresentDataTable(WebElement element, DataTable dataTable) {
        Helper.logEvent("User check data table after deleted data table");
        actTxt = element.getText();
        System.out.println("Actual Text : " + actTxt);
        rlstExpTxt = dataTable.asList();
        expTxt = rlstExpTxt.get(0);
        fuc.assertFalse(actTxt.contains(expTxt));
    }

    /**
     * assertE
     * Hard Assert Table Text is Present based on String, Result must be TRUE
     *
     * @param text      -> String Text
     * @param workTable -> WorkTable WebElement
     */
    public void assertTextOnTable(String text, WebElement workTable) {
        actTxt = workTable.getText();
        System.out.println("ActTxt : " + actTxt);
        fuc.assertTrue(actTxt.contains(text));
    }

    /**
     * assertE
     * Hard Assert Table Text is Present based on DataTable, Result must be TRUE
     *
     * @param dataTable -> DataTable dataTable
     * @param workTable -> WorkTable WebElement
     */
    public void assertTextOnTableDataTable(DataTable dataTable, WebElement workTable) {
        rlstExpTxt = dataTable.asList();
        expTxt = rlstExpTxt.get(0);
        actTxt = workTable.getText();
        //System.out.println("ActTxt : "+actTxt);
        fuc.assertTrue(actTxt.contains(expTxt));
    }

    /**
     * Hard Assert Table Text not Present based on String, Result must be false
     *
     * @param text      -> String Text
     * @param workTable -> WorkTable WebElement
     */
    public void assertTableTextNotPresentString(String text, WebElement workTable) {
        String actTxt = workTable.getText();
        System.out.println("ActTxt : " + actTxt);
        fuc.assertFalse(actTxt.contains(text));
    }

    public void folderExists() throws IOException {
        boolean exists = fuc.checkFolder();
        if (exists) {
            Helper.logEvent("Directory already exists");
            Helper.logEvent("Clean folder");
            fuc.cleanFolder();
        } else {
            Helper.logEvent("Create directory success");
            fuc.makeDirectory();
        }
    }
}
