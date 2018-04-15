package api.tests;

import api.pages.CountryListRS;
import api.pages.CountryRS;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class CountryTest extends BaseTest {

    @Test
    public void isBorder() {
        String country1 = "Latvia", country2 = "Estonia";
        String alpha3CodeOfCountry2 = null;
        boolean actResult = false, expResult = true;

        log("status code: 200, content type: JSON");
        CountryListRS countryList =
                given().spec(spec)
                .when().get()
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().as(CountryListRS.class);

        for (CountryRS country : countryList.getCountryList()){
            if (country.getName().equals(country2)){
                alpha3CodeOfCountry2 = country.getAlpha3Code();
            }
            if (country.getName().equals(country1)){
                for (String border : country.getBorders()){
                    if (border.equals(alpha3CodeOfCountry2)){
                        actResult = true;
                    }
                }
            }
        }

        Assert.assertEquals(actResult, expResult, "border true");
        log("assert: " + country1 + " has the border with " + country2);
    }


    @Test
    public void checkCountryArea() {
        String country = "Ukraine";
        double area = 500_000.0;
        boolean actResult = false, expResult = true;

        log("status code: 200, content type: JSON");
        CountryListRS countryList =
                given().spec(spec)
                        .when().get()
                        .then().statusCode(200).contentType(ContentType.JSON)
                        .extract().as(CountryListRS.class);

        for (CountryRS c : countryList.getCountryList()){
            if (c.getName().equals(country)){
                System.out.println("name: " + c.getName());
                System.out.println("capital: " + c.getCapital());
                System.out.println("region: " + c.getRegion());
                System.out.println("population: " + c.getPopulation());
                System.out.println("borders: " + Arrays.toString(c.getBorders()));
                if (c.getArea() > area){
                    actResult = true;
                }
            }

        }

        Assert.assertEquals(actResult, expResult, "area more than " + area);
        log("assert: area more than " + area);
    }

}
