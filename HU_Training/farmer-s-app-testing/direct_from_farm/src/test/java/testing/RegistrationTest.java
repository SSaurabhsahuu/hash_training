package testing;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.testng.Assert;
import org.testng.AssertJUnit.*;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import pages.BaseClass;
import pages.CustomerRegistration;
import pages.FarmerRegistration;
import pages.Home;

import java.util.Random;

public class RegistrationTest extends BaseClass {
    Home home=new Home();
    CustomerRegistration customer_reg=new CustomerRegistration();
    FarmerRegistration farmerRegistration=new FarmerRegistration();

    String registerURL="https://farmersapp-frontend-amxbp6pvia-as.a.run.app/auth/register";
    String customer_dashboard_url="https://farmersapp-frontend-amxbp6pvia-as.a.run.app/market";
    String farmer_dashboard_url="https://farmersapp-frontend-amxbp6pvia-as.a.run.app/farmer/dashboard";


    @Test
    public void invalid_email_CustomerRegisterationTest() throws InterruptedException{
        // register with invalid email
        Reporter.log("invalid_email_CustomerRegisterationTest");
        customer_reg.clickRegister_nav();
        customer_reg.enterFirstname("mohan");
        customer_reg.enterLastname("kumar");
        customer_reg.enterPhone(faker.phoneNumber().phoneNumber());
        String email=customer_reg.makeInvalidEmail(faker.internet().safeEmailAddress());
        customer_reg.enterEmail(email);
        customer_reg.enterPassword("MohanKuamr123");
        customer_reg.enterConfirmPassword("MohanKumar123");
        customer_reg.clickRegister_button();

        String msg=customer_reg.get_message();
        Assert.assertEquals("Invalid Email", msg);
        customer_reg.result_page(registerURL);


    }
    @Test
    public void empty_password_CustomerRegisterationTest() throws InterruptedException{

        Reporter.log("invalid_email_CustomerRegisterationTest");
        customer_reg.clickRegister_nav();
        customer_reg.enterFirstname("mohan");
        customer_reg.enterLastname("kumar");
        customer_reg.enterPhone(faker.phoneNumber().cellPhone());
        customer_reg.enterEmail(faker.internet().safeEmailAddress());
      // Thread.sleep(3000);
        customer_reg.clickRegister_button();

        customer_reg.result_page(registerURL);

    }
    @Test
    public void CustomerRegisterationPageTest() throws InterruptedException{

        Reporter.log("invalid_email_CustomerRegisterationTest");
        customer_reg.clickRegister_nav();
        customer_reg.enterFirstname("saurabh");
        customer_reg.enterLastname("kumar");
        customer_reg.enterPhone(faker.phoneNumber().cellPhone());
        customer_reg.enterEmail(faker.internet().safeEmailAddress());
        customer_reg.enterPassword("Saurabh123");
        customer_reg.enterConfirmPassword("Saurabh123");
        customer_reg.clickRegister_button();

        customer_reg.result_page(customer_dashboard_url);

    }

    @Test
    public void farmerRegisterationPageTest() throws InterruptedException{
        Reporter.log("invalid_email_CustomerRegisterationTest");
        farmerRegistration.click_sell_with_us_nav();
        farmerRegistration.enterFirstname(faker.name().firstName());
        farmerRegistration.enterLastname(faker.name().lastName());
        farmerRegistration.enterPhone(farmerRegistration.generatePhoneNumber());
        farmerRegistration.clickOnNext("[1]");
        farmerRegistration.enterAddress1(faker.address().fullAddress());
       // farmerRegistration.enterAddress2("a");
        farmerRegistration.enterPinCode(farmerRegistration.generatePincode());
        farmerRegistration.enterCity(faker.address().cityName());
        farmerRegistration.enterState(faker.address().state());
        farmerRegistration.clickOnNext("[2]");
        farmerRegistration.enterAdhaar(farmerRegistration.generateAdhaarNumber());
        farmerRegistration.uploadImage("adhaarcard.png");
        farmerRegistration.clickOnNext("[3]");
        farmerRegistration.enterEmail(faker.internet().safeEmailAddress());
        farmerRegistration.enterPassword("Saurabh123");
        farmerRegistration.enterConfirmPassword("Saurabh123");

        farmerRegistration.clickRegister_button();

        farmerRegistration.result_page(farmer_dashboard_url);
    }
}
