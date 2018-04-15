package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.SearchPage;
import ui.pages.SearchResultPage;
import ui.utilities.ProdIdDataProvider;

public class SearchTest extends BaseTest {

    @Test(dataProvider = "prodId", dataProviderClass = ProdIdDataProvider.class)
    public void searchProdId(String text){

        log("go to " + BASE_URL);
        driver.navigate().to(BASE_URL);

        log("input a product id and click the 'search' button");
        SearchResultPage searchResultPage = new SearchPage(driver).searchText(text);

        String expResult = text;
        String actResult = searchResultPage.getProductCode();
        Assert.assertEquals(actResult, expResult, "prodId equals");
        log("assert: the search product id is on the product page");
    }
}
