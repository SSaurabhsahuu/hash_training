package testing;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FamerClass extends BaseClass{
    String farmerRegistration="/common/auth/farmer/register";
    String farmerLogin="/common/auth/login";
    String addNewProduct="/product/addProduct";
    String email="abcd@gmail.com";
    String password="Akzar123";
    int pincode=575030;
    long farmerId=163150811077863L;
    String unit="Kilogram";
    String category="Veggies";
    String subCateogory="Tomato";
    String phone="+91541-305-2264";
    Logger logger = Logger.getLogger(this.getClass().getName());
    Response response;
    long orderId=163155341630855L;
    long otp=416308;
    String farmerRegistrationFile="src/test/resources/farmerRegistration.json";
    String updateOrderStatusOutForDelivery="/order/updateStatusToOutForDelivery/";
    String updateStatusToDelivery="/order/updateStatusToDelivered/";
    String addPincode="pinCode/addToPinCodes";
    String updateProductQuantity="/product/updateProductQuantity";
    String deleteProductImage="/productImage/deleteImage/7";
    String getProductListByFarmerId="/order/viewOrderListByFarmerId/";
    long productId=163150816416989L;
    public String authToken;
    List<Integer> list=new ArrayList<>();
    FileWriter file=null;
    long id;
    @Test(priority = 0)
    public void test_post_farmer_registration(){
        logger.info("calling post request for farmer registration");
        try {
            file=new FileWriter("src/test/resources/FarmerRegistration.csv");
            file.append("firstName").append(",").append("lastName").append(",").append("email").append(",").append("mobileNumber").append(",").append("addressLine1").append(",").append("addressLine2").append(",").append("city").append(",").append("state").append(",").append("pinCode").append(",").append("profilePictureUrl").append(",").append("aadharCardPictureUrl").append(",").append("password").append("\n");
                        JSONObject jsonData = new JSONObject();
                String first_name = faker.name().firstName();
                file.append(first_name).append(",");
                jsonData.put("firstName", first_name);
                String last_name = faker.name().lastName();
                file.append(last_name).append(",");
                jsonData.put("lastName", last_name);
                String email_id = faker.internet().safeEmailAddress();
                file.append(email_id).append(",");
                jsonData.put("email", email_id);
                String phoneNo = faker.phoneNumber().phoneNumber();
                file.append(phoneNo).append(",");
                jsonData.put("mobileNumber", phoneNo);
        JSONObject address=new JSONObject();
                String address_line_1 = faker.address().fullAddress();
                file.append(address_line_1).append(",");
        address.put("addressLine1",address_line_1);
                String address_line_2 = faker.address().secondaryAddress();
                file.append(address_line_2).append(",");
                address.put("addressLine2",address_line_2);
                String city = faker.address().city();
                file.append(city).append(",");
        address.put("city",city);
                String state = faker.address().state();
                file.append(state).append(",");
        address.put("state",state);
                file.append(pincode+"").append(",");
        address.put("pinCode",pincode);
        jsonData.put("address",address);
                String profile_pic = faker.internet().url();
                file.append(profile_pic).append(",");
        jsonData.put("profilePictureUrl",profile_pic);
                String adhaar_pic = faker.internet().url();
                file.append(adhaar_pic).append(",");
        jsonData.put("aadharCardPictureUrl",adhaar_pic);
                String pass = faker.internet().password(6,10);
                file.append(pass).append("\n");
        jsonData.put("password",pass);
        response= requestSpecification.
                body(jsonData).
                when().
                post(farmerRegistration). //calling the post request
                then().
                assertThat().
                statusCode(200). // checking if response status code is 200
                assertThat().
                contentType(ContentType.JSON). // checking if response is in json format
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("description"),is("Registered Successfully!"));
        }
        catch(IOException e){
            //System.out.println("File Not Found");
            logger.fatal("Try Again!!");
        }
        finally {
            try {
                file.flush();
                file.close();//closing file
            }
            catch(IOException e) {
                logger.fatal(e);
            }
        }
        assertThat(response.path("description"),is("Registered Successfully!"));
    }

    @Test(priority = 1)
    public void test_post_farmer_login(){
        logger.info("calling post request for farmer login");
        JSONObject jsonData = new JSONObject();
        jsonData.put("email",email);
        jsonData.put("password",password);
        response= requestSpecification.
                body(jsonData).
                when().
                post(farmerLogin).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        authToken=response.path("authToken");
        id=response.path("userId");
        assertThat(response.path("userType"),is("farmer"));


    }

    @Test(priority = 2)
    public void test_add_new_product()  {
        logger.info("calling post request for add new product");
        FileWriter file=null;
        try {
            file=new FileWriter("src/test/resources/Product_List.csv");
            file.append("productName").append(",").append("price").append(",").append("shippingCost").append(",").append("minSellingQuantity").append(",").append("stockLeft").append(",").append("description").append(",").append("productCategory").append(",").append("productSubCategory").append(",").append("productUnitsType").append("\n");
                JSONObject jsonData = new JSONObject();
        String food=faker.food().vegetable()+faker.number().randomNumber();
                file.append(food).append(",");
        jsonData.put("productName",food);
                long price=faker.number().randomNumber(2,true);
                file.append(price+"").append(",");
        jsonData.put("price",price);
            long shipmentCost=faker.number().randomNumber(2,true);
                file.append(shipmentCost+"").append(",");
        jsonData.put("shippingCost",shipmentCost);
                file.append(1+"").append(",");
        jsonData.put("minSellingQuantity",1);
                long stoke=faker.number().randomNumber(2,true);
                file.append(stoke+"").append(",");
        jsonData.put("stockLeft",stoke);
                String desc="Fresh "+food+" from my garden";
                file.append(desc).append(",");
        jsonData.put("description",desc);
                file.append(category).append(",");
        jsonData.put("productCategory",category);
                file.append(subCateogory).append(",");
        jsonData.put("productSubCategory",subCateogory);
        jsonData.put("farmerId",id);
                file.append(unit).append("\n");
        jsonData.put("productUnitsType",unit);

        response= requestSpecification.
                auth().oauth2(authToken).
                body(jsonData).
                when().
                post(addNewProduct).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        }
        catch(IOException e){
            //System.out.println("File Not Found");
            logger.fatal("Try Again!!");
        }
        finally {
            try {
                file.flush();
                file.close();//closing file
            }
            catch(IOException e) {
                logger.fatal(e);
            }
        }
    }

    @Test(priority = 3)
    public void update_order_status_out_for_delivery(){
        logger.info("calling post request to update status to out for delivery");
        updateOrderStatusOutForDelivery=updateOrderStatusOutForDelivery+orderId;
        response= requestSpecification.
                auth().oauth2(authToken).
                when().
                post(updateOrderStatusOutForDelivery).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
    }
    @Test(priority = 4)
    public void update_order_status_to_delivered(){
        logger.info("calling post request to update status to delivered");
        updateStatusToDelivery=updateStatusToDelivery+orderId+"/"+otp;
        response= requestSpecification.
                auth().oauth2(authToken).
                when().
                post(updateStatusToDelivery).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
    }

    @Test(priority = 5)
    public void test_post_add_pincode(){
        logger.info("calling post request to add pincode");
        JSONObject jsonData = new JSONObject();
        list.add(575030);
        System.out.println(list);
        jsonData.put("productId",productId);
        jsonData.put("pinCodes",list);
        response= requestSpecification.
                auth().oauth2(authToken).
                body(jsonData).
                when().
                post(addPincode).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
    }


    @Test(priority = 7)
    public void test_post_product_quantity(){
        logger.info("calling post request for change product quantity");
        JSONObject jsonData = new JSONObject();
        jsonData.put("productId",productId);
        jsonData.put("quantityDelta",18);
        response= requestSpecification.
                auth().oauth2(authToken).
                body(jsonData).
                when().
                post(updateProductQuantity).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
    }

    @Test(priority = 8)
    public void test_post_add_images(){
        logger.info("calling post request for add product image");
        JSONObject jsonData = new JSONObject();
        jsonData.put("productId",productId);
        jsonData.put("imageUrls",faker.internet().image());
        response= requestSpecification.
                body(jsonData).
                when().
                post(updateProductQuantity).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
    }


    @Test(priority = 10)
    public void test_get_product_list_by_farmer(){
        logger.info("calling get request to get the list of  product by farmer id");
        getProductListByFarmerId=getProductListByFarmerId+farmerId;
        response= given().
                baseUri(base_url).
                auth().
                oauth2(authToken).
                header("Content-Type","application/json").
                when().
                get(getProductListByFarmerId).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
    }

    @Test(priority = 11)
    public void test_post_farmer_registration_with_registered_email(){
        logger.info("calling post request for farmer registration with registered email");
                JSONObject jsonData = new JSONObject();
        jsonData.put("firstName",faker.name().firstName());
        jsonData.put("lastName",faker.name().lastName());
        jsonData.put("email",email);
        jsonData.put("mobileNumber",faker.phoneNumber().phoneNumber());
        JSONObject address=new JSONObject();
        address.put("addressLine1",faker.address().fullAddress());
        address.put("addressLine2",faker.address().secondaryAddress());
        address.put("city",faker.address().city());
        address.put("state",faker.address().state());
        address.put("pinCode",pincode);
        jsonData.put("address",address);
        jsonData.put("profilePictureUrl",faker.internet().image());
        jsonData.put("aadharCardPictureUrl",faker.internet().image());
        jsonData.put("password",faker.internet().password(6,10));

        response = requestSpecification.
                body(jsonData).
                when().
                post(farmerRegistration).
                then().
                assertThat().
                statusCode(406).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("message"),is("Email already registered as Farmer! Use Another email"));
    }

    @Test(priority = 12)
    public void test_post_farmer_registration_with_registered_mobile(){
        logger.info("calling post request for farmer registration with registered mobile");
                JSONObject jsonData = new JSONObject();
        jsonData.put("firstName",faker.name().firstName());
        jsonData.put("lastName",faker.name().lastName());
        jsonData.put("email",faker.internet().safeEmailAddress());
        jsonData.put("mobileNumber",phone);
        JSONObject address=new JSONObject();
        address.put("addressLine1",faker.address().fullAddress());
        address.put("addressLine2",faker.address().secondaryAddress());
        address.put("city",faker.address().city());
        address.put("state",faker.address().state());
        address.put("pinCode",pincode);
        jsonData.put("address",address);
        jsonData.put("profilePictureUrl",faker.internet().image());
        jsonData.put("aadharCardPictureUrl",faker.internet().image());
        jsonData.put("password",faker.internet().password(6,10));

        response = requestSpecification.
                body(jsonData).
                when().
                post(farmerRegistration).
                then().
                assertThat().
                statusCode(406).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("message"),is("Mobile Number already registered! Use Another Mobile Number"));
    }
}
