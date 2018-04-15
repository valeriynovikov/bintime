package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResultPage extends BasePage {

    @FindBy(css = "h1")
    private WebElement resultHeader;

    @FindBy(css = ".price.priceIncl>span")
    private List<WebElement> productPriceList;

    @FindBy(css = ".title>a")
    private List<WebElement> productNameList;

    @FindBy(css = ".paging>ul li")
    private List<WebElement> countResultPageDoubleList;

    @FindBy(css = ".next")
    private WebElement nextButton;


    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public int getNumberOfProductsInHeader() {
        String delimetr1 = "\\(", delimetr2 = " ";
        String subStr1[], subStr2[];
        subStr1 = resultHeader.getText().split(delimetr1);
        subStr2 = subStr1[subStr1.length - 1].split(delimetr2);
        return Integer.parseInt(subStr2[0]);
    }


    public int getProductCount() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
        int productCount = 0;
        boolean isNextButton = true;
        do {
            productCount += productPriceList.size();
            List<WebElement> buttonList = driver.findElements(By.cssSelector(".next"));
            if (buttonList.size() > 0) {
                clickNextButton();
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
            } else {
                isNextButton = false;
            }
        } while (isNextButton == true);
        return productCount;
    }


    public boolean checkProductPrice(int minPrice) {
        String myFileName = "target/monitorPriceCheaperMinPrice.txt";
        boolean isNextButton = true;
        boolean checkResult = true;
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
        do {
            for (WebElement el : productPriceList) {
                double price = Double.parseDouble(el.getText().replace(',', '.'));
                if (price < minPrice) {
                    checkResult = false;
                    int index = productPriceList.indexOf(el);
                    File myFile = new File(myFileName);
                    try {
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(myFile, true)));
                        writer.println(el.getText() + " euro; " + productNameList.get(index).getText());
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            List<WebElement> buttonList = driver.findElements(By.cssSelector(".next"));
            if (buttonList.size() > 0) {
                clickNextButton();
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
            } else {
                isNextButton = false;
            }
        } while (isNextButton == true);
        return checkResult;
    }


    private ResultPage clickNextButton() {
        nextButton.click();
        return this;
    }


}
