package ui.utilities;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import ui.tests.BaseTest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MyEventListener implements WebDriverEventListener {
    int i = new Random().nextInt(1000) + 1;
    String date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss")
            .format(new Date());
    File myListenerPath = new File("target/_listener");
    File myListenerFile = new File(myListenerPath,
            BaseTest.browserName + " " + date + i + ".txt");
    File myScreenPath = new File("target/_listener/" +
            BaseTest.browserName + " " + date + i + ".screen");
    File myScreenFile;

    private void printToConsole(String message) {
        System.out.println(message);
    }

    private void printToFile(String message) {
        if (!myListenerPath.exists()) {
            myListenerPath.mkdirs();
        }
        try {
            PrintWriter writer = new PrintWriter(
                    new BufferedWriter(new FileWriter(myListenerFile, true)));
            writer.println(message);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printToConsoleMyListenerFileName() {
        System.out.println("***\n*** " + BaseTest.browserName + " listener saved to: " + myListenerFile + "\n***");
    }

    private void doScreenshot(WebDriver driver) {
        String screenDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss")
                .format(new Date());
        int i = new Random().nextInt(1000) + 1;
        myScreenFile = new File(myScreenPath, screenDate + i + ".png");
        if (!myScreenPath.exists()) {
            myScreenPath.mkdirs();
        }
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, myScreenFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void registerAfterEvent(String message, WebDriver driver) {
        printToFile(message);
        doScreenshot(driver);
        printToFile("       screen saved to: " + myScreenFile);
    }


    public void beforeAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertDismiss(WebDriver webDriver) {

    }

    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {
        String browserName = "*** browser name    :: " + BaseTest.browserName;
        String browserVersion = "*** browser version :: " + BaseTest.browserVersion;
        String message = "Loading URL :: " + s;
        printToFile(browserName);
        printToFile(browserVersion);
        printToFile(message);
        printToConsoleMyListenerFileName();
    }

    public void afterNavigateTo(String s, WebDriver webDriver) {
        String message = "    => opened\n";
        registerAfterEvent(message, webDriver);
    }

    public void beforeNavigateBack(WebDriver webDriver) {

    }

    public void afterNavigateBack(WebDriver webDriver) {

    }

    public void beforeNavigateForward(WebDriver webDriver) {

    }

    public void afterNavigateForward(WebDriver webDriver) {

    }

    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    public void afterNavigateRefresh(WebDriver webDriver) {
        String message = "    => refresh";
        message += "\n  page title :: " + webDriver.getTitle() + "\n";
        registerAfterEvent(message, webDriver);
    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        String message = "Search element :: " + by.toString();
        message += "\n  page title :: " + webDriver.getTitle();
        printToFile(message);
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        String message = "    => found\n";
        registerAfterEvent(message, webDriver);
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        String message = "Click element  :: "
                + "tag: " + webElement.getTagName()
                + "   text: '" + webElement.getText() + "'";
        message += "\n  page title :: " + webDriver.getTitle();
        printToFile(message);
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        String message = "    => clicked\n";
        registerAfterEvent(message, webDriver);
    }


    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void beforeScript(String s, WebDriver webDriver) {

    }

    public void afterScript(String s, WebDriver webDriver) {

    }

    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void onException(Throwable throwable, WebDriver webDriver) {
        String message = ">>> EXCEPTION <<<\n" + throwable + "\n>>> EXCEPTION <<<\n";
        message += "\n  page title :: " + webDriver.getTitle() + "\n";
        printToConsole(message);
        registerAfterEvent(message, webDriver);
    }

}
