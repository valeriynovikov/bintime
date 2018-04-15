package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PriceFilterPage extends BasePage{

    @FindBy(css = ".mobileSwitchFiltersOff .filter:nth-of-type(1)")
    private WebElement priceFilter;

    @FindBy(css = "#priceRangeLow")
    private WebElement minPrice;

    @FindBy(css = "#priceRangehigh")
    private WebElement maxPrice;

    @FindBy(css = ".mobileSwitchFiltersOff .filter:nth-of-type(1) button")
    private WebElement searchButton;


    public PriceFilterPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    private PriceFilterPage clickPriceFilter(){
        priceFilter.click();
        return this;
    }

    private PriceFilterPage setMinPrice(double number){
        minPrice.clear();
        minPrice.sendKeys(number + "");
        return this;
    }

    private PriceFilterPage setMaxPrice(double number){
        maxPrice.clear();
        maxPrice.sendKeys(number + "");
        return this;
    }

    private ResultPage clickSearchButton(){
        searchButton.click();
        return new ResultPage(driver);
    }

    public ResultPage inputSearchPrices(double minPrice, double maxPrice){
        return clickPriceFilter()
                .setMinPrice(minPrice)
                .setMaxPrice(maxPrice)
                .clickSearchButton();
    }

}
