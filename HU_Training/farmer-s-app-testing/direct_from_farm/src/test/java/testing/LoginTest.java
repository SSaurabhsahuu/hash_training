package testing;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.BaseClass;
import pages.Home;
import pages.UserLogin;

import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseClass {
    Home home = new Home();
    UserLogin login = new UserLogin();
    String loginURL="https://farmersapp-frontend-amxbp6pvia-as.a.run.app/auth/login";
    String marketURL="https://farmersapp-frontend-amxbp6pvia-as.a.run.app/market";

    @Test
    public void empty_email_and_password_login_Test() throws InterruptedException {
        Reporter.log("empty_email_and_password_login_Test");    // login with empty email empty password
        login.clickLogin_nav();
        login.clickLogin_button();

        login.result_page(loginURL);
    }
    @Test
    public void valid_email_and_invalid_password_login_Test() throws InterruptedException {
        Reporter.log("valid_email_and_invalid_password_login_Test");  // login with valid email and invalid password
        login.clickLogin_nav();
        login.enterEmail("saurabh@gmail.com");
        login.enterPassword("saurabh");
        login.clickLogin_button();

        login.result_page(loginURL);
    }
    @Test
    public void invalid_email_and_valid_password_login_Test() throws InterruptedException {
        Reporter.log("invalid_email_and_valid_password_login_Test");   // login with invalid email and valid password
        login.clickLogin_nav();
        login.enterEmail("saurabh@gmailcom");
        login.enterPassword("Saurabh123");
        login.clickLogin_button();

        login.validate_message("Invalid email");
        login.result_page(loginURL);

    }
    @Test
    public void unregistered_email_and_password_login_Test() throws InterruptedException {
        Reporter.log("unregistered_email_and_password_login_Test");  // login with unregistered email and valid password
        login.clickLogin_nav();
        login.enterEmail("Sohan@gmail.com");
        login.enterPassword("Sohan123456");
        login.clickLogin_button();

        login.result_page(loginURL);

    }
    @Test
    public void valid_email_and_valid_password_login_Test() throws InterruptedException {
        Reporter.log("valid_email_and_valid_password_login_Test");   // login with valid details
        login.clickLogin_nav();
        login.enterEmail("saurabh@gmail.com");
        login.enterPassword("Saurabh123");
        login.clickLogin_button();

        login.result_page(marketURL);

    }

}
