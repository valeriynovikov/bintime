package ui.tests;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import ui.utilities.MyEventListener;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    public static String browserName;
    public static String browserVersion;

    private WebDriver webDriver;
    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
    protected SoftAssert softAssert;

    protected static final String BASE_URL = "http://centralpoint.nl/";


    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        boolean headlessBrowser = false;
        boolean mobileBrowser = false;
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/webdriver/chromedriver.exe")
                                .getFile()).getPath());
                webDriver = new ChromeDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver",
                        new File(BaseTest.class.getResource("/webdriver/IEDriverServer.exe")
                                .getFile()).getPath());
                webDriver = new InternetExplorerDriver();
                break;
            case "ff":
                System.setProperty("webdriver.gecko.driver",
                        new File(BaseTest.class.getResource("/webdriver/geckodriver.exe")
                                .getFile()).getPath());
                webDriver = new FirefoxDriver();
                break;
            case "hChrome":
                System.setProperty("webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/webdriver/chromedriver.exe")
                                .getFile()).getPath());
                ChromeOptions headlessChromeOptions = new ChromeOptions();
                headlessChromeOptions.addArguments("headless");
                headlessChromeOptions.addArguments("window-size=800x600");
                webDriver = new ChromeDriver(headlessChromeOptions);
                headlessBrowser = true;
                break;
            case "iPhone6":
                System.setProperty("webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/webdriver/chromedriver.exe")
                                .getFile()).getPath());
                Map<String, String> iPhone6Emulation = new HashMap<>();
                iPhone6Emulation.put("deviceName", "iPhone 6");
                ChromeOptions iPhone6Options = new ChromeOptions();
                iPhone6Options.setExperimentalOption("mobileEmulation", iPhone6Emulation);
                webDriver = new ChromeDriver(iPhone6Options);
                mobileBrowser = true;
                break;
            case "remote-chrome":
                ChromeOptions optionsRemote = new ChromeOptions();
                try {
                    webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), optionsRemote);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }

        Reporter.setEscapeHtml(false);

        Capabilities caps = ((RemoteWebDriver) webDriver).getCapabilities();
        browserName = caps.getBrowserName();
        browserVersion = caps.getVersion();

        if (headlessBrowser == true) {
            browserName += " headless";
        }
        if (mobileBrowser == true) {
            browserName += " mobile";
        }
        log("create browser : " + browserName);
        log("browser version: " + browserVersion);

        driver = new EventFiringWebDriver(webDriver)
                        .register(new MyEventListener());

        log("implicit wait: 30 seconds");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        log("explicit wait: 30 seconds");
        wait = new WebDriverWait(driver, 30);

//        log("page wait: 30 seconds");
//        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        log("browser size: maximize");
        driver.manage().window().maximize();

        action = new Actions(driver);
        softAssert = new SoftAssert();

    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            log("driver quit");
            driver.quit();
        }
    }


    protected void log(String message) {
        Reporter.log(message);
    }

}
