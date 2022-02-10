import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import files.ReUsableMethods;
import files.payload;

 // using data Provider to give multiple data sets for testing
public class DynamicJson {

    @Test(dataProvider="BooksData")
    public void addBook(String isbn, String aisle)
    {
        RestAssured.baseURI="http://216.10.245.166";

        String resp=given().log().all().
                header("Content-Type","application/json").
                body(payload.Addbook(isbn,aisle)).
                when().
                post("/Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).
                extract().response().asString();

        System.out.println("Response = "+resp);
        JsonPath js= ReUsableMethods.rawToJson(resp);
        System.out.println(js);
        String id=js.get("ID");
        System.out.println(id);

        //do deleteBOok so that adding same book do not fail

    }
    @DataProvider(name="BooksData")
    public Object[][]  getData()
    {
//array=collection of elements
//multidimensional array= collection of arrays
        return new Object[][] {{"osajfsdwty","93dsasa63"},{"cwetsaede","4sdad253"}, {"odkfmfet","dsd533"} };

    }






}