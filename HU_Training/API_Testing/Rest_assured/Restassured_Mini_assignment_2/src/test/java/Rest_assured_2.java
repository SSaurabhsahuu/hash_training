

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.util.List;



public class Rest_assured_2{

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification2;

    @BeforeTest
    public void setup()
    {
        RequestSpecBuilder reqspec = new RequestSpecBuilder();
        reqspec.setBaseUri("https://jsonplaceholder.typicode.com").
                addHeader("Content-Type","application/json");
        requestSpecification = RestAssured.with().spec(reqspec.build());

        ResponseSpecBuilder resspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
        responseSpecification = resspec.build();

        RequestSpecBuilder reqspec2 = new RequestSpecBuilder();
        reqspec2.setBaseUri("https://reqres.in/api").
                addHeader("Content-Type","application/json");
        requestSpecification2 = RestAssured.with().spec(reqspec.build());
    }
    @Test
    public void get_call() {
        Response response = requestSpecification.get("/posts");

        List<Integer> id = response.jsonPath().getList("id");

        if(id.get(39).equals(40))                        //     User with 'id' 40 has 'userId'=4
            assertThat(response.path("[39].userId"), is(equalTo(4)));

        List<String> title = response.jsonPath().getList("title");


        for(int i=0;i<title.size();i++)
        {
            assertThat(title.get(i).getClass().getSimpleName(),is(equalTo("String")));
        }

    }

    @Test
    public void test_put_call()
    {
        JSONObject requestParams = new JSONObject();                // add data in JSONObject
        requestParams.put("name", "Arun");
        requestParams.put("job", "Manager");

        requestSpecification2.body(requestParams.toString());       // pass json data in body of request

        Response response = requestSpecification2.put("/users/2");  //  make put request and get response
        System.out.println( response.getBody().asString());
        int statusCode = response.getStatusCode();

//        To check if put is done
        Assert.assertEquals(statusCode,200,"Put successful");

    }
    @Test
    public void test_post_call()
    {
        JSONObject requestParams = new JSONObject();                // add data in JSONObject
        requestParams.put("name", "Arun");
        requestParams.put("job", "Manager");

        requestSpecification2.body(requestParams.toString());       // pass json data in body of request

        Response response = requestSpecification2.post("/users");  //  make put request and get response
        System.out.println( response.getBody().asString());
        System.out.println("Status Code  "+response.getStatusCode());


    }
}

















