package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class SearchResultPage extends BasePage {

    @FindBy(css = ".productCode")
    private WebElement productCode;

    public SearchResultPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getProductCode(){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        String subStr[] = productCode.getText().split(" ");
        return subStr[1];
    }

}
