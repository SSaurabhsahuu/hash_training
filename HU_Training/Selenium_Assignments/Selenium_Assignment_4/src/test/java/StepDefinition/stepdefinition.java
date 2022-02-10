package StepDefinition;


import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class stepdefinition {
    WebDriver driver;
    BaseClass base;
    LoginPage login;
    AddCartPage cart;
    YourCartPage yourcart;
    CheckoutPage cop;
    CheckoutOverview checkoutoverview;
    CheckoutComplete coc;

    @Given("open url https :\\/\\/www.saucedemo.com\\/")
    public void open_url_https_www_saucedemo_com() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\saurasahu\\eclipse-workspace\\chromedriver.exe");
        driver = driver=new ChromeDriver();

        driver.get("https://www.saucedemo.com/");
    }
    @When("enter loginid {string} and password {string}")
    public void enter_loginid_and_password(String string, String string2) throws InterruptedException {
        login=new LoginPage(driver);

        String name="standard_user";
        String pass="secret_sauce";
        login.enterUsername(name);
        login.enterPassword(pass);
    }
    @Then("user click on login button")
    public void user_click_on_login_button() {
        login.clickLogin();
    }
    @And("click on filter and select most expensive product")
    public void click_on_filter_and_select_most_expensive_product() throws InterruptedException {
        cart=new AddCartPage(driver);
        cart.select_highest();
    }
    @Then("validate price is less than {string}")
    public void validate_price_is_less_than(String string) throws InterruptedException {
        String limit="100";
        cart.check_price(limit);
    }
    @And("check visibility of ADD TO CART button")
    public void check_visibility_of_add_to_cart_button() {
        cart.check_visibility_addtocart("Add to cart");
    }
    @Then("click on ADD TO CART")
    public void click_on_add_to_cart() throws InterruptedException {
        cart.click_addtocart("Add to cart");
    }
    @And("check visibility of Remove button")
    public void check_visibility_of_remove_button() {
        cart.check_visibility_remove("Remove");
    }
    @Then("click remove")
    public void click_remove() throws InterruptedException {
        cart.click_remove("Remove");
    }

    @Then("click on Cart")
    public void click_on_cart() {
        cart.click_cart();
    }

    @Then("click continue shopping")
    public void clickContinueShopping() throws InterruptedException {
        yourcart= new YourCartPage(driver);
        yourcart.continue_shopping();
    }

    @And("Select lowest product")
    public void selectLowestProduct() throws InterruptedException {
        cart.select_lowest();
    }

    @Then("click checkout")
    public void clickCheckout() throws InterruptedException {
        cart.click_checkout();
    }

    @And("enter first name, lastname and postal code")
    public void enterFirstNameLastnameAndPostalCode() throws InterruptedException {
        cop=new CheckoutPage(driver);
        cop.enterFirstname("Saurabh");
        cop.enterLastname("Kumar");
        cop.enterPostalcode("345677");
    }

    @Then("click continue")
    public void clickContinue() {
        cop.clickContinue();

    }

    @And("check total prize")
    public void checkTotalPrize() {
        checkoutoverview=new CheckoutOverview(driver);
        checkoutoverview.check_total_prize();
    }

    @Then("click finish")
    public void clickFinish() {
        checkoutoverview.click_finish();
    }

    @And("check success")
    public void checkSuccess() throws InterruptedException {
        coc=new CheckoutComplete(driver);
        coc.check_success();
        driver.quit();
    }
}
