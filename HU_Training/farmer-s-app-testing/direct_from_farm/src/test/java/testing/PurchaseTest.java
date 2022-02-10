package testing;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.concurrent.TimeUnit;

public class PurchaseTest extends BaseClass {
    String category_name="Veggies";
    String subcategory_name="Red Chilli";
    int quantity=1;
    String customer_email="saurabh@gmail.com";
    String customer_password="Saurabh123";
    String farmer_email="sohan_farmer@gmail.com";
    String farmer_password="Sohan123";
    String pincode="223456";
    FarmerAddsProduct farmerAddsProduct=new FarmerAddsProduct();
    String home_page_url="https://farmersapp-frontend-amxbp6pvia-as.a.run.app/market";
    Home home = new Home();
    UserLogin login = new UserLogin();
    PurchasePage purchase=new PurchasePage();


    //purchasing to the product till delivering product
    @Test(priority = 0)
    public void start_purchasing() throws InterruptedException {

        Reporter.log("start purchasing");
        login.clickLogin_nav();
        login.enterEmail(customer_email);
        login.enterPassword(customer_password);
        login.clickLogin_button();
        login.result_page(home_page_url);

        purchase.click_Prduct_category(category_name);
        purchase.click_Prduct(subcategory_name);
        purchase.enter_pincode(pincode);
        purchase.click_ok();
        purchase.click_increase_quantity(1);
        purchase.click_add_to_cart();
        purchase.click_on_cart();
        purchase.click_on_delivery_address();
        purchase.click_on_radio();
        purchase.click_delivery_method();
        String order_id=purchase.validate_order_result();
        purchase.click_on_profile_logo();
        purchase.click_on_order_history();
        purchase.validate_order_history(order_id);
        purchase.click_on_profile_logo();
        purchase.click_on_logout();

        login.clickLogin_nav();
        login.enterEmail(farmer_email);
        login.enterPassword(farmer_password);
        login.clickLogin_button();
        farmerAddsProduct.click_view_order();
        farmerAddsProduct.click_pending_order();
        farmerAddsProduct.validateOrderId(order_id);
        farmerAddsProduct.check_for_status("Status: Pending");
        farmerAddsProduct.click_on_option();
        farmerAddsProduct.click_on_out_for_delivery();
        purchase.click_on_profile_logo();
        purchase.click_on_logout();
        login.clickLogin_nav();
        login.enterEmail(customer_email);
        login.enterPassword(customer_password);
        login.clickLogin_button();
        login.result_page(home_page_url);
        purchase.click_on_profile_logo();
        purchase.click_on_order_history();
        String otp=purchase.get_otp();
        purchase.click_on_profile_logo();
        purchase.click_on_logout();
        login.clickLogin_nav();
        login.enterEmail(farmer_email);
        login.enterPassword(farmer_password);
        login.clickLogin_button();
        farmerAddsProduct.click_view_order();
        farmerAddsProduct.click_pending_order();
        farmerAddsProduct.check_for_status("Status: OutForDelivery");
        farmerAddsProduct.click_on_option();
        purchase.entering_otp(otp);
        farmerAddsProduct.validate_complete_order(order_id);

    }

    //checking if added item is in correct subcategory
    @Test(priority = 1)
    public void check_items_in_sub_category() throws InterruptedException {
        Reporter.log("check_items_in_sub_category");
        purchase.click_Prduct_category(category_name);
        String product1="Red Chilli";
        purchase.click_Prduct(product1);
        purchase.enter_pincode(pincode);
        purchase.click_ok();
        purchase.validate_item(product1);

        driver.navigate().back();

        String product2="Beetroot";
        purchase.click_Prduct(product2);
        purchase.validate_item(product2);

    }

    //checking correct item is added to the cart
    @Test(priority = 2)
    public void check_item_details_in_cart() throws InterruptedException {
        Reporter.log("check_item_details_in_cart");
        login.clickLogin_nav();
        login.enterEmail(customer_email);
        login.enterPassword(customer_password);
        login.clickLogin_button();
        login.result_page(home_page_url);

        subcategory_name="Red Chilli";
        quantity=2;
        purchase.click_Prduct_category(category_name);
        purchase.click_Prduct(subcategory_name);
        purchase.enter_pincode(pincode);
        purchase.click_ok();
        purchase.click_increase_quantity(quantity);
        purchase.click_add_to_cart();
        String name=purchase.get_product_name();
        String price=purchase.get_product_price();
        purchase.click_on_cart();
        purchase.validate_item_details(name,price,quantity);

    }

    //validating the total price
    @Test(priority = 3)
    public void check_total_price_of_items_in_cart() throws InterruptedException {
        Reporter.log("check_total_price_of_items_in_cart");
        login.clickLogin_nav();
        login.enterEmail(customer_email);
        login.enterPassword(customer_password);
        login.clickLogin_button();
        login.result_page(home_page_url);

        purchase.click_Prduct_category(category_name);
        purchase.click_Prduct("Red Chilli");
        purchase.enter_pincode(pincode);
        purchase.click_ok();
        int quantity1=2;
        purchase.click_increase_quantity(quantity1);
        purchase.click_add_to_cart();

        driver.navigate().back();

        //Thread.sleep(2000);
        purchase.click_Prduct("Tomato");
        int quantity2=1;
        purchase.click_increase_quantity(quantity2);
        purchase.click_add_to_cart();

        purchase.click_on_cart();

        purchase.check_total_subtotal();
    }

    //validating purchase
    @Test(priority = 4)
    public void purchase_without_delivery_address() throws InterruptedException {
        Reporter.log("purchase_without_delivery_address");
        login.clickLogin_nav();
        login.enterEmail(customer_email);
        login.enterPassword(customer_password);
        login.clickLogin_button();
        login.result_page(home_page_url);

        purchase.click_Prduct_category(category_name);
        purchase.click_Prduct(subcategory_name);
        purchase.enter_pincode(pincode);
        purchase.click_ok();
        purchase.click_increase_quantity(1);
        purchase.click_add_to_cart();
        purchase.click_on_cart();
        purchase.click_on_delivery_address();
        purchase.click_delivery_method();
        purchase.result_page("https://farmersapp-frontend-amxbp6pvia-as.a.run.app/user/checkout");

    }

    //rating a particular product
    @Test(priority = 5)
    public void rating_product() throws InterruptedException {

        Reporter.log("start purchasing");
        login.clickLogin_nav();
        login.enterEmail(customer_email);
        login.enterPassword(customer_password);
        login.clickLogin_button();
        login.result_page(home_page_url);

        purchase.click_Prduct_category(category_name);
        purchase.click_Prduct(subcategory_name);
        purchase.enter_pincode(pincode);
        purchase.click_ok();
        String name=purchase.click_on_product_image();
        purchase.validate_product_name(name);
        purchase.give_rating();
    }

}
