package exercises;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CheckoutCompletePage;
import pages.CheckoutStepTwoPage;
import pages.ConfirmationPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ShoppingCartPage;

public class CheckoutFeatureTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        // sets browser to Firefox
        capabilities.setCapability("browserName", "firefox");
        // sets the browser version to 11.1
        capabilities.setCapability("version", "latest");
        // sets your test case name so that it shows up in Sauce Labs
        capabilities.setCapability("name", method.getName());
        // instantiates a remote WebDriver object with your desired capabilities
        try {
            System.setProperty("webdriver.gecko.driver", "../drivers/geckodriver");
            driver = new FirefoxDriver();
        } catch (IllegalStateException e) {
            System.setProperty("webdriver.gecko.driver", "../drivers/geckodriver");
            driver = new FirefoxDriver();
        }
        // driver = new RemoteWebDriver(new
        // URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);
        System.out.println("creating driver and setting capabilities");
    }
    
    @Test
    public void shouldBeAbleToCheckout() {

        // Ignore the following selectors
        String username = "standard_user";
        String password = "secret_sauce";
        //navigate to the url of the Sauce Labs Sample app
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();

        InventoryPage inventoryPage = loginPage.login(username, password);
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        inventoryPage.addBackpackToCart();
        ShoppingCartPage cart = inventoryPage.goToShoppingCart();
        CheckoutStepTwoPage stepTwoPage = cart.checkout();
        ConfirmationPage confirmationPage = stepTwoPage.fillOutInformation("first", "last", "zip");
        CheckoutCompletePage completeCheckout = confirmationPage.finish();
        Assert.assertTrue(completeCheckout.isLoaded());
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        driver.quit();
        System.out.println("Quitting the driver");
    }
}
