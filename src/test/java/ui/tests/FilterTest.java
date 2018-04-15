package ui.tests;

import org.testng.annotations.Test;
import ui.pages.FilterPage;
import ui.pages.PriceFilterPage;
import ui.pages.ResultPage;

import static org.testng.Assert.assertEquals;

public class FilterTest extends BaseTest {

    @Test
    public void checkNumberOfNotebooksForRandomValueAndFilter() {
        String pageLink = BASE_URL + "notebooks-laptops/";

        log("go to " + pageLink);
        driver.navigate().to(pageLink);

        log("choose a random filter and a value of filter");
        ResultPage resultPage = new FilterPage(driver).inputRandomValueOfRandomFilter();

        int expResult = resultPage.getNumberOfProductsInHeader();
        int actResult = resultPage.getProductCount();
        assertEquals(actResult, expResult, "product number equals");
        log("assert: the product number in header equals the product number on the page");
    }

    @Test
    public void checkMonitorPriceCheaperMinPrice(){
        String pageLink = BASE_URL + "monitoren/";
        int minPrice = 1000, maxPrice = 5000;

        log("go to " + pageLink);
        driver.navigate().to(pageLink);

        log("search products from " + minPrice + " euro to " + maxPrice + " euro");
        ResultPage resultPage = new PriceFilterPage(driver).inputSearchPrices(minPrice, maxPrice);

        boolean expResult = true;
        boolean actResult = resultPage.checkProductPrice(minPrice);
        assertEquals(actResult, expResult, "price is bigger " + minPrice);
        log("assert: the price of products does not cheaper than " + minPrice + " euro");
    }

}
