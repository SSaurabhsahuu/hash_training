
import Utility.UtilityClass;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashSet;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class Product {

    Response response;
    JSONArray resultsArray;
    FileOperation filo;
    UtilityClass uc;

    @BeforeTest
    public void initial_setup()
    {  uc = new UtilityClass();
        uc.setup();
        uc.logger = Logger.getLogger(Product.class.getName());
    }
    @Test(priority = 1)
    public void get_response()
    {  uc.test.log(LogStatus.INFO,"get response");
        uc.logger.info("");

        response = given().
            baseUri("https://fakestoreapi.com").
            header("Content-Type", "application/json").
            when().
            get("/products").
            then().
            statusCode(200).extract().response();

        resultsArray = new JSONArray(response.asString());
    }
    @Test(priority = 2)
    public void test_get_20_products() {
        uc.test.log(LogStatus.INFO,"get 20 products");
        uc.logger.info("");

        assertThat(resultsArray.length(), is(equalTo(20)));
        uc.test.log(LogStatus.PASS,"get 20 products");
        // System.out.print(resultsArray.length());
        //  System.out.println(resultsArray);
    }
    @Test(priority = 3)
    public void test_uniquness_of_id() {      // total number of ids should be equal to size of array
        uc.test.log(LogStatus.INFO,"uniquness_of_id");
        uc.logger.info("");
        HashSet<Integer> set = new HashSet();

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject obj = resultsArray.getJSONObject(i);
            int id = obj.getInt("id");
            set.add(id);
        }
        assertThat(resultsArray.length(), is(equalTo(set.size())));
        uc.test.log(LogStatus.PASS,"uniquness_of_id");
    }
    @Test(priority = 4)
    public void post_data_from_file() throws IOException {   // read data from xsls file and make put request
        uc.test.log(LogStatus.INFO,"data from file");
        uc.logger.info("");

        filo =new FileOperation();

        filo.add_data_from_file();

        filo.validate_json_schema();
        uc.test.log(LogStatus.PASS,"data from file");
    }
    @AfterTest
    public void test_close()
    {
        uc.close_setup();
    }


}