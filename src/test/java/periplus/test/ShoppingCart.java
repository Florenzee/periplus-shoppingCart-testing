package periplus.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import periplus.pages.CartPage;
import periplus.pages.LoginPage;
import periplus.pages.ProductPage;
import periplus.utils.EnvConfig;

public class ShoppingCart {
     WebDriver driver;

     @BeforeClass
     public void setUp() {
          WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
     }

     @Test
     public void testAddProductToCart() throws InterruptedException {
          driver.get("https://www.periplus.com/");

          // Login
          LoginPage login = new LoginPage(driver);
          login.login(EnvConfig.getEmail(), EnvConfig.getPassword());

          // Tunggu halaman benar-benar siap
          Thread.sleep(5000);

          // Cari produk
          ProductPage product = new ProductPage(driver);
          product.searchProduct("Harry Potter");

          Thread.sleep(3000);

          // Klik gambar produk, lalu tambah ke cart
          product.clickProductImage();

          Thread.sleep(3000);

          product.clickAddToCart();

          // Verifikasi produk sudah ditambahkan ke cart
          CartPage cart = new CartPage(driver);
          Assert.assertTrue(cart.isProductAdded(), "Product not successfully added to cart.");

          // Tutup modal notifikasi jika muncul
          cart.closeModalIfDisplayed();
     }

     @AfterClass
     public void tearDown() {
          if (driver != null) {
               driver.quit();
          }
     }
}
