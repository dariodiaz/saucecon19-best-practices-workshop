package exercises;

import org.openqa.selenium.MutableCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class FullJourneyTest {
    private WebDriver driver;

    @Test
    public void fullCustomerJourney(Method method) throws MalformedURLException {

        MutableCapabilities capabilities = new MutableCapabilities();

        //sets browser to Firefox
        capabilities.setCapability("browserName", "firefox");

        //sets the browser version to 11.1
        capabilities.setCapability("version", "latest");

        //sets your test case name so that it shows up in Sauce Labs
        capabilities.setCapability("name", method.getName());

        //instantiates a remote WebDriver object with your desired capabilities
        try {
                System.setProperty("webdriver.gecko.driver", "../drivers/geckodriver");
                driver = new FirefoxDriver();
        } catch (IllegalStateException e) {
                System.setProperty("webdriver.gecko.driver", "../drivers/geckodriver");
                driver = new FirefoxDriver();
        }

        //navigate to the url of the Sauce Labs Sample app
        driver.navigate().to("https://www.saucedemo.com");
        System.out.println("navigating to web application");

        // Specify Data
        String firstname = "john";
        String lastname = "doe";
        String postal = "94040";

        // Ignore the following selectors
        String username = "standard_user";
        String password = "secret_sauce";
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";

        String loginBtn = "#login-button";
        String backpack = "#add-to-cart-sauce-labs-backpack";
        String cart = ".shopping_cart_link";
        String rmvBtn = "#cart_contents_container > div > div.cart_list > div.cart_item > div.cart_item_label > div.item_pricebar > button";
        String continueShopping = "#continue-shopping";
        String checkoutLink = "#checkout";

        String firstNameField = "[data-test='firstName']";
        String lastNameField = "[data-test='lastName']";
        String postalField= "[data-test='postalCode']";
        String continueLink = "div.checkout_buttons > input";

        String finished = "#finish";
        String complete = "https://www.saucedemo.com/checkout-complete.html";

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // send username keystrokes
        driver.findElement(By.cssSelector(userField)).sendKeys(username);
        System.out.println("sending username");

        // send password keystrokes
        driver.findElement(By.cssSelector(passField)).sendKeys(password);
        System.out.println("sending password");


        // click login button to submit keystrokes
        driver.findElement(By.cssSelector(loginBtn)).click();
        System.out.println("clicking 'Submit' button");

        // add items to the cart
        driver.findElement(By.cssSelector(backpack)).click();
        System.out.println("adding backpack");

        // proceed to checkout
        driver.findElement(By.cssSelector(cart)).click();
        System.out.println("clicking cart icon");

        // remove item from cart
        driver.findElement(By.cssSelector(rmvBtn)).click();
        System.out.println("removing item from cart");

        // continue shopping
        driver.findElement(By.cssSelector(continueShopping)).click();
        System.out.println("clicking 'Continue Shopping' button");

        // re-add item to cart and proceed to checkout
        driver.findElement(By.cssSelector(backpack)).click();
        System.out.println("re-adding backpack back to cart");

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.cssSelector(cart)).click();
        System.out.println("clicking cart icon");


        //Click Checkout Link
        driver.findElement(By.cssSelector(checkoutLink)).click();
        System.out.println("clicking the 'Checkout' button");

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // proceed to shipping info (checkout step 1)
        driver.findElement(By.cssSelector(firstNameField)).sendKeys(firstname);
        System.out.println("adding first name in shipping info");

        driver.findElement(By.cssSelector(lastNameField)).sendKeys(lastname);
        System.out.println("adding last name in shipping info");

        driver.findElement(By.cssSelector(postalField)).sendKeys(postal);
        System.out.println("adding zip code in shipping info");

        //Click Cart Checkout Link
        driver.findElement(By.cssSelector(continueLink)).click();
        System.out.println("clicking the 'Continue' button ");

        //  proceed to confirmation page (checkout step 2)
        driver.findElement(By.cssSelector(finished)).click();
        System.out.println("clicking the 'Finished' button");

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // assert that the test is finished by checking the last page's URL
        Assert.assertEquals(driver.getCurrentUrl(), complete);
        System.out.println("asserting last page's url = 'https://www.saucedemo.com/checkout-complete.html'" );

        // Then quit the driver session
        driver.quit();
    }
}