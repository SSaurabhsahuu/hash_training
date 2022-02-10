package testing;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.BaseClass;
import pages.CustomerRegistration;
import pages.FarmerRegistration;
import pages.Home;

import java.util.concurrent.TimeUnit;

public class DirectFromFieldTest extends BaseClass {
    Home home=new Home();
    @Test
    public void homePageTest() throws InterruptedException{

        home.check_if_icon_and_text_is_visible();
        home.check_if_nav_button_is_visible();
        home.check_if_above_arrow_is_clicked_and_displayed();
        home.check_if_down_arrow_is_clicked_and_displayed();
        home.check_if_product_category_is_displayed();
        home.click_Prduct_category("Fruits");
       // Thread.sleep(2000);
        home.click_Prduct_category("Veggies");
       // Thread.sleep(2000);
        home.click_Prduct_category("Grains & Pulses");
        home.click_on_back_to_top();
    }

}
