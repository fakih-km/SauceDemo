package utilities;

import java.io.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

//This is where you write your test base function to define the driver
public class TestBase {
	public WebDriver driver;
	public String propBrowser;
	public String mvnBrowser;
    public String urlStaging,urlProd;
    public ChromeOptions chOpt;
    public FirefoxOptions fxOpt;
    public String dwldPath;
	TestContextSetup tcs;

    public ChromeOptions chromeDriverCap() throws IOException {
        File src = new File(".\\resources\\config.properties");
        FileInputStream fis = new FileInputStream(src);
        Properties prop = new Properties();
        prop.load(fis);
        dwldPath = prop.getProperty("directoryDownload");
        chOpt = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", dwldPath);
        chOpt.setExperimentalOption("prefs", prefs);
        // chOpt.addArguments("headless");
        return chOpt;
    }
	
	public FirefoxOptions firefoxDriverCap() throws IOException {
        File src = new File(".\\resources\\config.properties");
        FileInputStream fis = new FileInputStream(src);
        Properties prop = new Properties();
        prop.load(fis);
        dwldPath = prop.getProperty("directoryDownload");
		fxOpt = new FirefoxOptions();
        fxOpt.addPreference("browser.download.folderList", 2);
        fxOpt.addPreference("browser.download.dir", dwldPath);
        fxOpt.addPreference("browser.download.useDownloadDir", true);
        fxOpt.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf;text/plain;application/text;text/xml;application/xml");
        fxOpt.addPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer
        return fxOpt;
	}
	
	public WebDriver WebDriverManager() throws Exception
    {
//      info : Reading config.properties(for browser)
        File src = new File(".\\resources\\config.properties");
        FileInputStream fis = new FileInputStream(src);
        Properties prop = new Properties();
        prop.load(fis);
        propBrowser = prop.getProperty("browser");
        mvnBrowser = System.getProperty("browser");
        urlStaging = prop.getProperty("urlStaging");
        urlProd = prop.getProperty("urlProd");
        String browser = mvnBrowser != null ? mvnBrowser : propBrowser;
        
        if(driver == null) {
            switch (browser) {
                case "chrome":
                    chOpt = chromeDriverCap();
                    driver = new ChromeDriver(chOpt);
                    break;
                case "firefox":
                    fxOpt = firefoxDriverCap();
                    driver = new FirefoxDriver(fxOpt);
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
            }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(urlStaging);
        }
        return driver;
    }

}
