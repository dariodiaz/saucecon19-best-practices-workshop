package exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import pages.LoginPage;

public class LoginFeatureTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(Method method) throws MalformedURLException
    {
        // Input your SauceLabs Credentials
        String sauceUsername = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

        MutableCapabilities capabilities = new MutableCapabilities();

        //sets browser to Firefox
        capabilities.setCapability("browserName", "firefox");

        //sets operating system to macOS version 10.13
        capabilities.setCapability("platform", "macOS 10.13");

        //sets the browser version to 11.1
        capabilities.setCapability("version", "58.0");

        //sets your test case name so that it shows up in Sauce Labs
        capabilities.setCapability("name", method.getName());
        capabilities.setCapability("username", sauceUsername);
        capabilities.setCapability("accessKey", sauceAccessKey);

        //instantiates a remote WebDriver object with your desired capabilities
        driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);
    }
    @Test
    public void ShouldBeAbleToLogin() {

        //navigate to the url of the Sauce Labs Sample app
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();

        // Ignore the following selectors
        String username = "standard_user";
        String password = "secret_sauce";
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";
        String loginBtn = "[value='LOGIN']";

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // send username keystrokes
        driver.findElement(By.cssSelector(userField)).sendKeys(username);

        // send password keystrokes
        driver.findElement(By.cssSelector(passField)).sendKeys(password);

        // click login button to submit keystrokes
        driver.findElement(By.cssSelector(loginBtn)).click();

        // ignore assertion
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        ((JavascriptExecutor)driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        driver.quit();
    }
}