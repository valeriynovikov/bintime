package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FilterPage extends BasePage {
    private List<WebElement> copyFilterList;
    private String randomFilterSelector;
    private int randomIndexOfFilter;


    @FindBy(css = ".mobileSwitchFiltersOff .filter")
    private List<WebElement> filterList;

    public FilterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        doCopyFilterList();
    }


    private void doCopyFilterList() {
        copyFilterList = new ArrayList<>(filterList);
    }

    private int getIndexExceptFilter(String exceptFilter) {
        int index = 0;
        for (WebElement el : copyFilterList) {
            if (el.getText().equals(exceptFilter)) {
                index = copyFilterList.indexOf(el);
                break;
            }
        }
        return index;
    }

    private void setRandomIndexOfFilter() {
        int exceptFilterIndex1 = getIndexExceptFilter("Prijs");
        int exceptFilterIndex2 = getIndexExceptFilter("Sortering");
        do {
            randomIndexOfFilter = (new Random().nextInt(copyFilterList.size()));
        } while (randomIndexOfFilter == exceptFilterIndex1 | randomIndexOfFilter == exceptFilterIndex2);

    }

    private void setRandomFilterSelector() {
        randomFilterSelector = ".mobileSwitchFiltersOff .filter:nth-of-type(" + (randomIndexOfFilter + 1) + ")";
    }

    private int getRandomIndexOfFilter() {
        return randomIndexOfFilter;
    }

    private void clickRandomFilter() {
        setRandomIndexOfFilter();
        setRandomFilterSelector();
        copyFilterList.get(getRandomIndexOfFilter()).click();
    }

    private void clickRandomValueOfRandomFilter() {
        int randomIndexOfValue = 1;
        clickRandomFilter();
        List<WebElement> valueList = driver.findElements(By.cssSelector(randomFilterSelector + " li"));
        randomIndexOfValue = (new Random().nextInt(valueList.size()));
        String randomValueSelector = randomFilterSelector + " li:nth-of-type(" + (randomIndexOfValue + 1) + ")>input";
        WebElement randomValue = driver.findElement(By.cssSelector(randomValueSelector));
        new Actions(driver).moveToElement(randomValue).build().perform();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", randomValueSelector);
        if (randomIndexOfValue > 6 && randomIndexOfValue < valueList.size() - 1) {
            randomValue.click();
            randomValue.click();
        } else {
            randomValue.click();
        }
    }

    public ResultPage inputRandomValueOfRandomFilter() {
        clickRandomValueOfRandomFilter();
        String button = randomFilterSelector + " button";
        driver.findElement(By.cssSelector(button)).click();
        return new ResultPage(driver);
    }


}
