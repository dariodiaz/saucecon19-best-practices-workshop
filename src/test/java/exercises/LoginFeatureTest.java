package exercises;

import org.openqa.selenium.MutableCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginFeatureTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup(Method method) {
        MutableCapabilities capabilities = new MutableCapabilities();

        //sets browser to Firefox
        capabilities.setCapability("browserName", "firefox");

        //sets the browser version to 11.1
        capabilities.setCapability("version", "latest");

        //sets your test case name so that it shows up in Sauce Labs
        capabilities.setCapability("name", method.getName());

        //instantiates a remote WebDriver object with your desired capabilities
        try {
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
			driver = new FirefoxDriver();
		} catch (IllegalStateException e) {
			System.setProperty("webdriver.gecko.driver", "geckodriver");
			driver = new FirefoxDriver();
		}
        // driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);
        System.out.println("creating driver and setting capabilities");
    }

    @AfterMethod
    public void tearDown() {
        // Then quit the driver session
        driver.quit();
    }

    @Test
    public void ShouldBeAbleToLogin(Method method) {

        //navigate to the url of the Sauce Labs Sample app
        driver.navigate().to("https://www.saucedemo.com");
        System.out.println("navigating to web application");

        // Ignore the following selectors
        String username = "standard_user";
        String password = "secret_sauce";
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";
        String loginBtn = "#login-button";

        // send username keystrokes
        driver.findElement(By.cssSelector(userField)).sendKeys(username);
        System.out.println("sending username");

        // send password keystrokes
        driver.findElement(By.cssSelector(passField)).sendKeys(password);
        System.out.println("sending password");

        // click login button to submit keystrokes
        driver.findElement(By.cssSelector(loginBtn)).click();
        System.out.println("clicking 'Submit' button");

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // assert that the test is finished by checking the last page's URL
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        System.out.println("asserting last page's url = 'https://www.saucedemo.com/inventory.html'" );

    }
}
