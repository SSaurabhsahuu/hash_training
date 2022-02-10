package testing;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.*;
import pages.BaseClass;
import pages.FarmerAddsProduct;
import pages.Home;
import pages.UserLogin;

public class FarmerAddsProductTest extends BaseClass {
    Home home=new Home();
    UserLogin login = new UserLogin();
    FarmerAddsProduct farmeraddproduct=new FarmerAddsProduct();

    String unit=" Kilogram ";
    String product_name="Onion sohan";
    String resultURl="https://farmersapp-frontend-amxbp6pvia-as.a.run.app/farmer/dashboard";
    String category=" Veggies ";
    String subcategory=" Onions ";
    String price="50";
    String shippingCost="5";
    String currentStock="100";
    String Min_sell_quantity="5";
    String image_name="Brocolli.jpg";
    String pincode="123456";
    String email="sohan_farmer@gmail.com";
    String password="Sohan123";


    @Test
    public void AddProductTest() throws InterruptedException {
        Reporter.log("AddProduct test started");
        login.clickLogin_nav();                                          // Enter all login details
        login.enterEmail(email);
        login.enterPassword(password);
        login.clickLogin_button();
        login.result_page(resultURl);

        farmeraddproduct.click_add();                                   // Enter all Product details
        farmeraddproduct.enterProductname(product_name);
        farmeraddproduct.select_unit(unit);
        farmeraddproduct.select_category(category);
        farmeraddproduct.select_sub_category(subcategory);
        farmeraddproduct.enter_Price_per_unit(price);
        farmeraddproduct.enter_shipping_cost(shippingCost);
        farmeraddproduct.enter_current_stock(currentStock);
        farmeraddproduct.enter_minimum_selling_quantity(Min_sell_quantity);
        farmeraddproduct.clickOnNext_1();
        farmeraddproduct.uploadImage(image_name);
        farmeraddproduct.clickOnNext_2();
        farmeraddproduct.enter_pincode(pincode);
        farmeraddproduct.click_confirm_pincode();
        farmeraddproduct.click_on_my_product(product_name);
    }

}
