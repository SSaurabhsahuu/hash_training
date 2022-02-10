import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class FileOperation {

    public void add_data_from_file() throws IOException {

        File excelFile = new File("src/test/resources/TestData.xlsx");
        FileInputStream file=new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);



        for(int i=1;i<=5;i++)
        {
            // read data from file cell by cell

            DataFormatter formatter = new DataFormatter();
            Cell cell = sheet.getRow(i).getCell(1);

            String title=sheet.getRow(i).getCell(0).getStringCellValue();
            String price=formatter.formatCellValue(cell);
            String description=sheet.getRow(i).getCell(2).getStringCellValue();
            String image=sheet.getRow(i).getCell(3).getStringCellValue();
            String category=sheet.getRow(i).getCell(4).getStringCellValue();

           // post this data to api
            JSONObject bodyParameters= new JSONObject();

            bodyParameters.put("title",title);
            bodyParameters.put("price",Integer.parseInt(price));
            bodyParameters.put("description",description);
            bodyParameters.put("image",image);
            bodyParameters.put("category",category);

            RequestSpecBuilder reqspec2 = new RequestSpecBuilder();
            reqspec2.setBaseUri("https://fakestoreapi.com").
                    addHeader("Content-Type","application/json");
            RequestSpecification req = RestAssured.with().spec(reqspec2.build());


            req.body(bodyParameters.toString());
            Response response=req.post("/products");

            System.out.println((String) response.path("title")+"  "+response.getStatusCode());
            System.out.println( response.getBody().asString());
          /*  String respBody=req.getBody().asString();
             assertThat(respBody,matchesJsonSchemaInClasspath("product_json_schema.json"));*/
        }

        file.close();
    }

    public void validate_json_schema() {
        given().
                baseUri("https://fakestoreapi.com").
                header("content-type", "application/json").
                when().
                get("/products").
                then().
                statusCode(200).
                and().body(matchesJsonSchemaInClasspath("product_json_schema.json"));
    }
}
