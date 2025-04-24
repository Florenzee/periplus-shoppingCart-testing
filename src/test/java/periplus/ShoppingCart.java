package periplus;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShoppingCart {
     WebDriver driver;

     @BeforeClass
     public void setUp() {
          // WebDriverManager to automatically download and set up drivers
          WebDriverManager.chromedriver().setup();

          // initialization
          driver = new ChromeDriver();
     }

     @Test
     public void testAddProductToCart() {
          // Step 1: Navigate to Periplus website
          driver.get("https://www.periplus.com/");

          // Step 2: Login
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In")));
          loginButton.click();

          WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
          emailField.sendKeys("albertine.florenze@gmail.com");

          WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
          passwordField.sendKeys("TestOpenWay");

          WebElement submitLoginButton = driver.findElement(By.id("button-login"));
          submitLoginButton.click();

          // Wait a few seconds for the system to load.
          try {
               Thread.sleep(5000);
          } catch (InterruptedException e) {
               e.printStackTrace();
          }

          // Step 3: Find a product
          WebElement searchBox = driver.findElement(By.id("filter_name"));
          searchBox.sendKeys("Harry Potter");
          searchBox.submit();

          try {
               Thread.sleep(3000);
          } catch (InterruptedException e) {
               e.printStackTrace();
          }

          // Step 4: Add product to cart
          WebElement hoverImage = driver.findElement(By.xpath("//img[@class='hover-img' and @src='https://static.periplus.com/vI8P7IPsUsNvX3OZkyLjC3YZsv_l8j2CngQTOhEpp_G4lVIM9R_4trJZ5rl6awyDA--']"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hoverImage);


          try {
               Thread.sleep(3000);
          } catch (InterruptedException e) {
               e.printStackTrace();
          }

          WebElement addToCartBtn = driver.findElement(By.xpath("//button[text()='Add to Cart']"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);


          // Step 5: Verify product is successfully added to cart
          WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Notification-Modal"))); // Wait for notification

          String notifText = modal.findElement(By.className("modal-text")).getText();
          assert notifText.equals("Success add to cart");

          WebElement closeModalButton = driver.findElement(By.cssSelector(".modal.fade"));
          if (closeModalButton.isDisplayed()) {
               closeModalButton.click();
          }

//          // Step 6: Verify the product is in the cart
//          WebElement cartLink = driver.findElement(By.id("show-your-cart_mobile"));
//          cartLink.click();
//
//          // Verify the product name in the cart
//          WebElement cartProductName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='hover-img' and @src='https://static.periplus.com/vI8P7IPsUsNvX3OZkyLjC3YZsv_l8j2CngQTOhEpp_G4lVIM9R_4trJZ5rl6awyDA--']")));
//          Assert.assertNotNull(cartProductName, "Product not found in the cart.");
//
//          // Verify the quantity of the product in the cart
//          WebElement cartQuantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_total_mobile")));
//          String cartQuantityText = cartQuantity.getText();
//          Assert.assertEquals(cartQuantityText, "1", "Product quantity is incorrect in the cart.");
//
//          // Verify the product price in the cart
//          WebElement cartPrice = driver.findElement(By.xpath("//div[@class='cart-item']//span[@class='price']"));
//          String cartPriceText = cartPrice.getText();
//          Assert.assertTrue(cartPriceText.contains("Rp 188,000"), "Product price is incorrect in the cart.");

     }

     @AfterClass
     public void tearDown() {
          // Close browser
          if (driver != null) {
               driver.quit();
          }
     }
}
