package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

    @FindBy(css = ".container>form>input[type='search']")
    private WebElement searchField;

    @FindBy(css = ".container>form>button")
    private WebElement searchButton;

    public SearchPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    private SearchPage inputSearchText(String text){
        searchField.clear();
        searchField.sendKeys(text);
        return this;
    }

    private SearchResultPage clickSearchButton(){
        searchButton.click();
        return new SearchResultPage(driver);
    }

    public SearchResultPage searchText(String text){
        return inputSearchText(text).clickSearchButton();
    }

}
