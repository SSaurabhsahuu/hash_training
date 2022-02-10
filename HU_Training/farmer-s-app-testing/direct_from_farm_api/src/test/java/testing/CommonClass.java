package testing;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CommonClass extends BaseClass{

    FamerClass farmer=new FamerClass();
    String authToken=farmer.authToken;
    Logger logger = Logger.getLogger(this.getClass().getName());
    String listBySubCategory="/common/getCategoryList";
    long productId=163153927301922L;
    long farmerId=163150811077863L;
    String getProductById="/common/getProductById/";
    String getFarmerProductList="/common/getProductList/";
    String farmerProfileById="/common/getFarmerProfile/";
    String farmerRating="/rating/getRating/getRatingByFarmerId/163150811077863";
    String getRatingByProductId="/rating/getRating/getRatingByProductId/163150816416989";
    String postRefreshToken="common/auth/refresh/token";
    Response response;
    String refreshToken="cde11e5a-38a5-4851-9455-1248b9119495";
    String userId="162997005596944";
    String schemaPathSubcategory="src/test/resources/json_schema_for_sub_category.json";

    //get request to get sub category list
    @Test(priority = 1)
    public void test_get_sub_category_list(){
        File jsonSchemaFile=new File(schemaPathSubcategory);
        logger.info("calling get request for list by subcategory");
        response= requestSpecification.
                when().
                get(listBySubCategory). //calling the get request
                then().
                assertThat().
                statusCode(200). // checking if response status code is 200
                assertThat().
                contentType(ContentType.JSON). // checking if response is in json format
                body(JsonSchemaValidator.matchesJsonSchema(jsonSchemaFile)). //validating with json response
                log().ifValidationFails().
                log().all().
                extract().response();
    }

    // request to get product by id
    @Test(priority = 2)
    public void test_get_product_by_id(){
        logger.info("calling get product by id request");
        getProductById=getProductById+productId;
        response= requestSpecification.
                when().
                get(getProductById). //calling the get request
                then().
                assertThat().
                statusCode(200). // checking if response status code is 200
                assertThat().
                contentType(ContentType.JSON). // checking if response is in json format
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("productId"),is(productId));
    }

//    request to get farmer product list
    @Test(priority = 3)
    public void test_get_farmer_product_list(){
        getFarmerProductList=getFarmerProductList+farmerId;
        logger.info("calling get farmer product list request");
        response= requestSpecification.
                when().
                get(getFarmerProductList). //calling the get request
                then().
                assertThat().
                statusCode(200). // checking if response status code is 200
                assertThat().
                contentType(ContentType.JSON). // checking if response is in json format
                log().ifValidationFails().
                log().all().
                extract().response();
        List<Long> id_list=response.jsonPath().getList("farmerId");
        for (long id:id_list) {
            assertThat(id,is(farmerId));
        }
    }

    @Test(priority = 4)//test for get request farmer profile by id
    public void test_get_farmer_profile_by_id(){
        logger.info("calling get request to get farmer profile by id");
        farmerProfileById=farmerProfileById+farmerId;
        response= requestSpecification.
                when().
                get(farmerProfileById). //calling the get request
                then().
                assertThat().
                statusCode(200). // checking if response status code is 200
                assertThat().
                contentType(ContentType.JSON). // checking if response is in json format
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("farmerId"),is(farmerId));
    }

    @Test(priority = 5)
    public void test_get_farmer_rating(){
        logger.info("calling get request to get customer details");
        response= requestSpecification.
                when().
                get(farmerRating). //calling the get request
                then().
                assertThat().
                statusCode(200). // checking if response status code is 200
                assertThat().
                contentType(ContentType.JSON). // checking if response is in json format
                log().ifValidationFails().
                log().all().
                extract().response();
    }

    @Test(priority = 6)
    public void test_get_product_rating(){
        logger.info("calling get request to get customer details");
        response= requestSpecification.
        when().
                get(getRatingByProductId). //calling the get request
                then().
                assertThat().
                statusCode(200). // checking if response status code is 200
                assertThat().
                contentType(ContentType.JSON). // checking if response is in json format
                log().ifValidationFails().
                log().all().
                extract().response();
    }
}
