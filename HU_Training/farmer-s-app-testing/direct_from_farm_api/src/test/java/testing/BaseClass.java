package testing;

import io.restassured.specification.RequestSpecification;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import com.github.javafaker.Faker;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class BaseClass {
    public static RequestSpecification requestSpecification;
    public static String base_url="https://farmers-backend-amxbp6pvia-el.a.run.app/api";
    public static Faker faker;

/*
    initial setup of the api is done here
    sending the header and giving the base url
*/
    @BeforeTest
    public static RequestSpecification initialSetup(){
        requestSpecification=given().
                baseUri(base_url).
                header("Content-Type","application/json");
        String log4jPath = System.getProperty("user.dir") + "/log4j.properties";
        PropertyConfigurator.configure(log4jPath);
        //fake is to generate unique value
        faker = new Faker(new Locale("en-IND"));
        return requestSpecification;
    }
}
