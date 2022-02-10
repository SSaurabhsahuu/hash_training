import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.*;
import java.io.*;
import static org.hamcrest.Matchers.*;

import Utility.UtilityClass;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

public class Users {
    Response response;
    UtilityClass uc;

    @BeforeTest
    public void initial_setup()
    {  uc = new UtilityClass();
        uc.setup();
        uc.logger = Logger.getLogger(Users.class.getName());
    }
    @Test(priority = 1)
    public void test_name() throws IOException {
        uc.test.log(LogStatus.INFO,"test name");
        uc.logger.info("test name");


            response = given().baseUri("https://fakestoreapi.com").
                    header("content-type", "application/json").
                    when().
                    get("/users").
                    then().
                    statusCode(200).extract().response();

            System.out.println("name validation");
            List<String> name = response.jsonPath().getList("name.firstname");
            HashSet<String> hs = new HashSet<>(name);
            boolean f = false;
            if (hs.contains("david") && hs.contains("don") && hs.contains("miriam"))
                f = true;
            assertThat(f, is(equalTo(true)));

        uc.test.log(LogStatus.PASS,"test name");
        }
        @Test(priority = 2)
        public void test_geolocation() {
            uc.test.log(LogStatus.INFO," test geolocation");
            uc.logger.info("test geolocation");
            // geolocation validation
            assertThat(response.jsonPath().getList("geolocation.lat"), is(notNullValue()));
            assertThat(response.jsonPath().getList("geolocation.long"), is(notNullValue()));
            System.out.println(response.jsonPath().getList("geolocation.lat"));
            System.out.println(response.jsonPath().getList("geolocation.long"));
        }

    @Test(priority = 3)
    public void test_password()
    {    uc.test.log(LogStatus.INFO,"test password");
        uc.logger.info("test password");
            //   To check for atleast 1 special characters , numbers, characters
            List<String> pass = response.jsonPath().getList("password");

            System.out.println("password validation");
            for (int i = 0; i < pass.size(); i++) {
                int splCount = 0;
                int charCount = 0;
                int numCount = 0;
                String str = pass.get(i);

                for (int j = 0; j < str.length(); j++) {
                    char ch = str.charAt(j);
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
                        charCount++;
                    else if (ch >= '0' && ch <= '9')
                        numCount++;
                    else if ((ch>=' ' && ch<='/')||(ch>=':' && ch<='@')||(ch>='[' && ch<='`')||(ch>='{' && ch<='~') )
                        splCount++;
                }
                boolean flag = false;
                if (charCount > 0 && numCount > 0 && splCount > 0)
                    flag = true;

                if(!flag) {
                    System.out.println(" password " + str);
                    uc.test.log(LogStatus.FAIL,"test fail");
                    assertThat(flag, is(equalTo(true)));

                }

                 uc.test.log(LogStatus.INFO,"test password");
            }

        }
    @AfterTest
    public void test_close()
    {
        uc.close_setup();
    }
}