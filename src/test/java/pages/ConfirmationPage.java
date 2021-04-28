package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class ConfirmationPage {
    private final WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void visit()
    {
        //TODO duplication in URL with LoginPage
        driver.navigate().to("https://www.saucedemo.com/checkout-step-two.html");
    }

    public void setPageState() {
        driver.navigate().refresh();
        Cookie ck = new Cookie("session-username", "standard_user");
        driver.manage().addCookie(ck);
        ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('session-username', 'standard_user')");
        ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('cart-contents', '[4,1]')");
    }

    public Boolean hasItems() {
        String cartBadge = "shopping_cart_badge";
        return Integer.parseInt(driver.findElement(By.className(cartBadge)).getText()) > 0;
    }

    public CheckoutCompletePage FinishCheckout() {
        String finished =".btn_action.cart_button";
        driver.findElement(By.cssSelector(finished)).click();
        return new CheckoutCompletePage(driver);
    }
}
