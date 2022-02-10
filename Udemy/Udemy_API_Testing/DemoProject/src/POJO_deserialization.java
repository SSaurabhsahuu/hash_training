
        import static io.restassured.RestAssured.given;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;


        import org.testng.Assert;

        import io.restassured.parsing.Parser;
        import io.restassured.path.json.JsonPath;
        import pojo.Api;
        import pojo.GetCourse;

        // pojo (Plane Object java)
public class POJO_deserialization {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        // get authorization code
        // cannot automate this with selenium as Google has blocked automated sign in
        // so manually go to google sign in page and enter email and password and press sign in
        // now copy the url of page below
        String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";


        String partialcode=url.split("code=")[1];
        String auth_code=partialcode.split("&scope")[0];
        System.out.println(auth_code);

        //   tagname[attribute='value']

        String accessTokenResponse=	given().urlEncodingEnabled(false)
                .queryParams("code",auth_code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath js=new JsonPath(accessTokenResponse);
        String accessToken=js.getString("access_token");



        GetCourse gc=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .as(GetCourse.class);   // gets data in POJO GetCourse Structure

        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());


        List<Api> apiCourses=gc.getCourses().getApi();
        for(int i=0;i<apiCourses.size();i++)
        {
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        //Get the course names of WebAutomation
        ArrayList<String> a= new ArrayList<String>();

        List<pojo.WebAutomation> w=gc.getCourses().getWebAutomation();
        for(int j=0;j<w.size();j++)
        {
            a.add(w.get(j).getCourseTitle());
        }
        String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};
        List<String> expectedList=	Arrays.asList(courseTitles);

        Assert.assertTrue(a.equals(expectedList));


        //System.out.println(response);

    }
}
