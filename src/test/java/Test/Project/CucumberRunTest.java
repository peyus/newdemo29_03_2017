package Test.Project;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.AutomationConstants;
import utilities.BrowserFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Piyush on 12/11/2016.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/whisper-html-report", "json:target/whisper_report.json"},
//        tags = {"@"},
        features= "."
//        glue=""
)
public class CucumberRunTest {
    static WebDriver driver;
    public static boolean initialiseOnce = false;

    protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CucumberRunTest.class);

    @BeforeClass
    public static void start() {
        try {

            System.out.println("START :::" + AutomationConstants.BROWSER_TYPE + AutomationConstants.URL );
            BrowserFactory.StartBrowser(AutomationConstants.BROWSER_TYPE, AutomationConstants.URL);
            driver = BrowserFactory.driver;
            driver.manage().timeouts().implicitlyWait(AutomationConstants.MAX_TIMEOUTS, TimeUnit.SECONDS);
         //   Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Welcome to IPass"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void stop() {
        BrowserFactory.QuitBrowser();
    }
}
