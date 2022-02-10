package testing;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CustomerClass extends BaseClass{

    Logger logger = Logger.getLogger(this.getClass().getName());
    Response response;
    long phone=8904590052L;
    public long id=163155905629376L;
    String email="abc@gmail.com";
    public long customerId=163119015185465L;
    public long productId=163150816416989L;
    long paymentId=163150919291214L;
    long farmerId=163150811077863L;
    public long orderId=163150919308462L;
    String customerRegistration="/common/auth/customer/register";
    String customerLogin="common/auth/login";
    String customerAddress="address/addCustomerAddress";
    String addToCart="/cart/addToCart";
    String updateCartItem="/cart/updateCartItems";
    String createPayment="/payment/createPayment";
    String createNewOrder="/order/createOrder/14/163150919291214/163162593067072";
    String cartItem="/cart/viewCartItems/163119015185465";
    String orderDetails="/order/viewOrderByOrderId/";
    String orderHistory="/order/viewOrderListByCustomerId/";
    String getCustomerAddress="/address/getAddress/";
    String deleteCartItem="/cart/removeFromCart";
    String rating="/rating/addRating";
    String description="Thanks for Rating Product!";
    String schemaPathCartItem="src/test/resources/json_schema_for_cart_item.json";
    String schemaPathOrderHistory="src/test/resources/json_schema_order_history.json";
    String password="Abcd123";
    int pincode=575030;
    int cartId=2;
    int quantity=90;
    int cartItemId=2;
    float paymentAmount=10812.0f;
    String paymentMode="COD";
    String authToken;
    FileWriter file;
    @Test(priority = 0)
    public void test_post_customer_registration() throws IOException {
        try {
            file = new FileWriter("src/test/resources/CustomerRegistration.csv");
            file.append("firstName").append(",").append("lastName").append(",").append("email").append(",").append("mobileNumber").append(",").append("password").append("\n");
            for (int i = 0; i < 10; i++) {
                logger.info("calling post request for customer registration");
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
                file.append(password).append("\n");
                jsonData.put("password", password);
                response = requestSpecification.
                        body(jsonData).
                        when().
                        post(customerRegistration).
                        then().
                        assertThat().
                        statusCode(200).
                        assertThat().
                        contentType(ContentType.JSON).
                        log().ifValidationFails().
                        log().all().
                        extract().response();
                assertThat(response.path("description"), is("Registered Successfully!"));
            }
        }
        catch (IOException e){
            logger.fatal(e);
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

    @Test(priority = 1)
    public void test_post_customer_login(){
        logger.info("calling post request for customer login");
        JSONObject jsonData = new JSONObject();
        jsonData.put("email",email);
        jsonData.put("password",password);
        response= requestSpecification.
                body(jsonData).
                when().
                post(customerLogin). //calling the post request
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("userType"),is("customer"));
        authToken=response.path("authToken");
    }

    @Test(priority = 2)
    public void test_post_customer_address(){
        logger.info("calling post request for adding customer address");
        JSONObject jsonData = new JSONObject();
        jsonData.put("customerId",id);
        jsonData.put("addressLine1",faker.address().fullAddress());
        jsonData.put("addressLine2",faker.address().secondaryAddress());
        jsonData.put("city",faker.address().city());
        jsonData.put("state",faker.address().state());
        jsonData.put("pinCode",pincode);
        response= requestSpecification.
                header("Authorization", "Bearer " + authToken).
                body(jsonData).
                when().
                post(customerAddress). //calling the post request
                then().
                assertThat().
                statusCode(200). // checking if response status code is 200
                assertThat().
                contentType(ContentType.JSON). // checking if response is in json format
                log().ifValidationFails().
                log().all().
                extract().response();
    }

    @Test(priority = 3)
    public void test_post_add_to_cart(){
        logger.info("calling post request for add product to the cart");
        JSONObject jsonData = new JSONObject();
        jsonData.put("cartId",cartId);
        jsonData.put("productId",productId);
        jsonData.put("quantity",quantity);
        response= requestSpecification.
                body(jsonData).
                when().
                post(addToCart).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        String desc=response.path("description").toString().toLowerCase();
        assertThat(desc.contains("added to cart"),is(true));
    }


    @Test(priority = 4)
    public void test_post_update_cart(){
        logger.info("calling post request for update product in the cart");
        JSONObject jsonData = new JSONObject();
        jsonData.put("cartId",cartId);
        jsonData.put("cartItemId",cartItemId);
        jsonData.put("newQuantity",quantity);
        response= requestSpecification.
                body(jsonData).
                when().
                post(updateCartItem).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("description"),is("Product Updated in User's Cart"));
    }

    @Test(priority = 5)
    public void test_post_create_payment(){
        logger.info("calling post request for creating new payment");
        JSONObject jsonData = new JSONObject();
        jsonData.put("paymentAmount",paymentAmount);
        jsonData.put("paymentMode",paymentMode);
        response= requestSpecification.
                body(jsonData).
                when().
                post(createPayment).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
    }

    @Test(priority = 6)
    public void test_post_create_new_order(){
        logger.info("calling post request for creating new order");
        response= requestSpecification.
                when().
                post(createNewOrder).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("paymentId"),is(paymentId));
        assertThat(response.path("message"),is("Order created successfully!"));
    }



    @Test(priority = 7)
    public void test_get_cart_item(){
        File jsonSchemaFile=new File(schemaPathCartItem);
        logger.info("calling get request for cart item");
        System.out.println(cartItem);
        response= given().
                baseUri(base_url).
                auth().
                oauth2(authToken).
                header("Content-Type","application/json").
                when().
                get(cartItem).
                then().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                body(JsonSchemaValidator.matchesJsonSchema(jsonSchemaFile)).
                assertThat().
                assertThat().
                log().ifValidationFails().
                log().all().
                extract().response();
    }

    @Test(priority = 8)
    public void test_get_order_detail(){
        logger.info("calling get request for order details");
        orderDetails=orderDetails+orderId;
        response= given().
                baseUri(base_url).
                auth().
                oauth2(authToken).
                header("Content-Type","application/json").
                when().
                get(orderDetails).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("orderId"),is(orderId));
    }

    @Test(priority = 9)
    public void test_get_order_history(){
        logger.info("calling get request for order history");
        File jsonSchemaFile=new File(schemaPathOrderHistory);
        orderHistory=orderHistory+customerId;
        response= given().
                baseUri(base_url).
                auth().
                oauth2(authToken).
                header("Content-Type","application/json").
                when().
                get(orderHistory).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                body(JsonSchemaValidator.matchesJsonSchema(jsonSchemaFile)).
                assertThat().
                log().ifValidationFails().
                log().all().
                extract().response();
    }
    @Test(priority = 10)
    public void test_get_customer_address(){
        logger.info("calling get request for customer address");
        getCustomerAddress=getCustomerAddress+customerId;
        response= given().
                baseUri(base_url).
                auth().
                oauth2(authToken).
                header("Content-Type","application/json").
                when().
                get(getCustomerAddress).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        List<Long> id_list=response.jsonPath().getList("customerId");
        for (long id:id_list) {
            assertThat(id,is(customerId));
        }
    }

    @Test(priority = 11)
    public void test_delete_cart_item(){
        logger.info("calling delete request for cart item");
        JSONObject jsonData = new JSONObject();
        jsonData.put("cartId",6);
        jsonData.put("cartItemId",2);
        response= requestSpecification.
                body(jsonData).
                when().
                delete(deleteCartItem).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
    }

    @Test(priority = 12)
    public void test_post_rating_product(){
        logger.info("calling post request for rating a product");
        JSONObject jsonData = new JSONObject();
        jsonData.put("customerId",customerId);
        jsonData.put("productId",productId);
        jsonData.put("rating",5);
        jsonData.put("review","Good product");
        response= requestSpecification.
                body(jsonData).
                when().
                post(rating).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("description"),is(equalTo(description)));
    }

    @Test(priority = 13)
    public void test_post_rating_farmer(){
        logger.info("calling post request for rating a farmer");
        JSONObject jsonData = new JSONObject();
        jsonData.put("farmer",true);
        jsonData.put("customerId",customerId);
        jsonData.put("farmerId",farmerId);
        jsonData.put("rating",5);
        jsonData.put("review","Good farmer");
        response= requestSpecification.
                body(jsonData).
                when().
                post(rating).
                then().
                assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("description"),is("Thanks for Rating Farmer!"));
    }

    @Test(priority = 14)
    public void test_post_customer_registration_with_registered_email(){
        logger.info("calling post request for customer registration with registered email");
                JSONObject jsonData = new JSONObject();
        jsonData.put("firstName",faker.name().firstName());
        jsonData.put("lastName",faker.name().lastName());
        jsonData.put("email",email);
        jsonData.put("mobileNumber",faker.phoneNumber().phoneNumber());
        jsonData.put("password",password);

        response = requestSpecification.
                body(jsonData).
                when().
                post(customerRegistration).
                then().
                assertThat().
                statusCode(406).
                assertThat().
                contentType(ContentType.JSON).
                log().ifValidationFails().
                log().all().
                extract().response();
        assertThat(response.path("message"),is("Email already registered as Customer! Use Another email"));
    }

    @Test(priority = 15)
    public void test_post_customer_registration_with_registered_mobile(){
        logger.info("calling post request for customer registration with registered mobile");
        JSONObject jsonData = new JSONObject();
        jsonData.put("firstName",faker.name().firstName());
        jsonData.put("lastName",faker.name().lastName());
        jsonData.put("email",faker.internet().safeEmailAddress());
        jsonData.put("mobileNumber",phone);
        jsonData.put("password",password);

        response = requestSpecification.
                body(jsonData).
                when().
                post(customerRegistration).
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
