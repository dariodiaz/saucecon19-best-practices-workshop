package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {
    private final WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }
    public CheckoutStepTwoPage checkout() {
        String checkoutLink = "#checkout";
        driver.findElement(By.cssSelector(checkoutLink)).click();
        return new CheckoutStepTwoPage(driver);
    }
}
