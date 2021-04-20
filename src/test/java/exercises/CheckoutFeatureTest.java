package exercises;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import java.util.concurrent.TimeUnit;

public class CheckoutFeatureTest extends BaseTest {

    @Test
    public void shouldBeAbleToCheckoutWithItems() {
        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();

        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        // Assert that the url is on the inventory page
        //TODO fix this assertion later
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        inventoryPage.addBackpackToCart();
        ShoppingCartPage cart = inventoryPage.goToShoppingCart();
        CheckoutStepTwoPage stepTwoPage = cart.checkout();
        ConfirmationPage confirmationPage = stepTwoPage.fillOutInformation("first", "last", "zip");
        CheckoutCompletePage finalConfirmationPage = confirmationPage.finish();
        Assert.assertTrue(finalConfirmationPage.isLoaded());
    }

}
