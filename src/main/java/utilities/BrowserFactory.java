package utilities;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Created by Piyush on 04/11/2016.
 */
public class BrowserFactory {
    public static WebDriver driver;

    public static WebDriver StartBrowser(String browser, String url) {
        if (driver == null || !isSessionActive()) {
            driver = startRemoteWebBrowser(browser, url);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static boolean isSessionActive() {
        try {

            return driver.findElements(By.tagName("body")).size() > 0;

        } catch (Exception e) {

        }
        return false;
    }


    public static void QuitBrowser() {
        driver.quit();
        driver = null;
    }

    protected static WebDriver startRemoteWebBrowser(String browser, String URL) {

        if (AutomationConstants.REMOTE_BROWSER == true) {
            try {


                System.out.println("grid started...");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                if (System.getProperty("Browser").equalsIgnoreCase("firefox")) {
                    System.out.println("grid firefox started...");
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setVersion("34.0");
                } else if (System.getProperty("Browser").equalsIgnoreCase("chrome")) {
                    System.out.println("grid chrome started...");
                    capabilities = DesiredCapabilities.chrome();

                }

                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setCapability("platform", Platform.VISTA);
                driver = new ScreenShotRemoteWebDriver(
                        new URL(AutomationConstants.SELENIUM_GRID_URL),
                        capabilities);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {

            try {
                if (browser.equalsIgnoreCase("Firefox")) {
                    System.out.println("local firefox started...");
                    FirefoxProfile firefoxprofile = new FirefoxProfile();
                    firefoxprofile.setAssumeUntrustedCertificateIssuer(true);
                    firefoxprofile.setAcceptUntrustedCertificates(true);
                    driver = new FirefoxDriver(firefoxprofile);


                } else if (browser.equalsIgnoreCase("chrome")) {
                    System.out.println("local chrome started...");
                    System.setProperty("webdriver.chrome.driver", "D:\\ProjectDemo\\SeleniumFiles\\chromedriver.exe");//"selenium-files/chromedriver.exe");
                    driver = new ChromeDriver();
                } else
                    throw new RuntimeException("Browser give " + browser + " did not load..");
            } catch (Exception e) {
                throw new RuntimeException("Browser give " + browser + " did not load..");
            }
        }
        driver.get(URL);
        return driver;
    }

    public static class ScreenShotRemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {
        public ScreenShotRemoteWebDriver(URL url, DesiredCapabilities dc) {
            super(url, dc);
        }


        public <X> X getScreenshotAs(OutputType<X> target)
                throws WebDriverException {
            if ((Boolean) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) {
                return target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());
            }
            return null;
        }
    }


}
