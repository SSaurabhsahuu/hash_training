

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RestAssured {

        @Test
        public void test_get_call()
        {
            given().
                    baseUri("https://jsonplaceholder.typicode.com/").
                    header("content-type","application/json").
                    when().
                    get("/posts").
                    then().
                    statusCode(200).
                    body("[39].userId", is(equalTo(4)),
                        "title",notNullValue());

        }
        @Test
        public void test_put_call_from_json_file()
        {
            File jsonData =new File("src//test//resources//putData.json");  // get json data from file

            Response response= given().
                    baseUri("https://reqres.in/api").
                    body(jsonData).                     // pass json data in body of request
                    header("Content-Type","application/json").
                    when().
                    put("/users/4").                 //  make put request
                    then().
                    statusCode(200)
                    .log().body()                      //  print body
                    .extract().response();
         //   or    .body("name",is(equalTo("Arun")));
            assertThat(response.path("name"),is(equalTo("Pankaj")));
            assertThat(response.path("job"),is(equalTo("SDET")));
        }
    @Test
    public void test_put_call_from_json_object(){

        JSONObject requestParams = new JSONObject();                // add data in JSONObject
        requestParams.put("name", "Arun");
        requestParams.put("job", "Manager");

        Response response=given().
                baseUri("https://reqres.in/api").
                body(requestParams.toString()).                   // pass json data in body of request
                header("Content-Type","application/json").
                when().
                put("/users/4").                               //  make put request
                then().
                statusCode(200)
                .log().body()                                    // print body
                .extract().response();
//          or  .body("name",is(equalTo("Arun")));
        assertThat(response.path("name"),is(equalTo("Arun")));
        assertThat(response.path("job"),is(equalTo("Manager")));

    }

}

