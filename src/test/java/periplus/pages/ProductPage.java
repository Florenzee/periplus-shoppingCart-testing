package periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox = By.id("filter_name");
    private By productImage = By.xpath("//img[@class='hover-img']");
    private By addToCartButton = By.xpath("//button[text()='Add to Cart']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.clear();
        searchInput.sendKeys(productName);
        searchInput.submit();
    }

    public void clickProductImage() {
        wait.until(ExpectedConditions.elementToBeClickable(productImage));
        WebElement img = driver.findElement(productImage);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", img);
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        WebElement btn = driver.findElement(addToCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }
}
