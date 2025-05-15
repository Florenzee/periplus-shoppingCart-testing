package periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    By signinButton = By.linkText("Sign In");
    By emailInput = By.cssSelector("input[type='email']");
    By passwordInput = By.cssSelector("input[type='password']");
    By loginButton = By.id("button-login");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        driver.findElement(signinButton).click();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
