import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class Carts {

    @Test(priority = 1)
    public void verify_products() {    //  validate json schema

        given().baseUri("https://fakestoreapi.com").
                header("content-type", "application/json").
                when().
                get("/carts").
                then().
                body(matchesJsonSchemaInClasspath("cart_json_schema.json"));



    }
    @Test(priority = 2)
    public void products_field_not_null() {
        // product size  should not be zero
        when().
                get("https://fakestoreapi.com/carts").
                then().
                body("products.size()",greaterThanOrEqualTo(1),
                        // check fields
                        "products.productId",notNullValue(),
                        "products.quantity",notNullValue());
    }
}
