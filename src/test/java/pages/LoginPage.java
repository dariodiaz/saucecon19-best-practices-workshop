package pages;

import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage visit() {
        driver.get("https://www.saucedemo.com");
        return this;
    }
}
