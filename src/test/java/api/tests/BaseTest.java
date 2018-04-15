package api.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected RequestSpecification spec;

    @BeforeClass
    public void setUp(){
        String baseURL = "http://restcountries.eu/rest/v1/";

        spec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .addFilter(new RequestLoggingFilter()) //логирование запроса
//                .addFilter(new ResponseLoggingFilter()) //логированиие ответа
                .build();
    }

    protected void log(String message) {
        Reporter.log(message);
    }
}
