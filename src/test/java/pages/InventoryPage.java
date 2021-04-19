package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private final WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpackToCart() {
        String backpack = "#add-to-cart-sauce-labs-backpack";
        driver.findElement(By.cssSelector(backpack)).click();
    }
    
    public ShoppingCartPage goToShoppingCart() {
        String cart = ".shopping_cart_link";
        driver.findElement(By.cssSelector(cart)).click();
        return new ShoppingCartPage(driver);
    }
}
