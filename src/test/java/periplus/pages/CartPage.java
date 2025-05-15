package periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By notificationModal = By.id("Notification-Modal");
    private By modalText = By.className("modal-text");
    private By modalCloseButton = By.cssSelector(".modal.fade");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProductAdded() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationModal));
        String notifText = modal.findElement(modalText).getText();
        return notifText.equalsIgnoreCase("Success add to cart");
    }

    public void closeModalIfDisplayed() {
        try {
            WebElement closeBtn = driver.findElement(modalCloseButton);
            if (closeBtn.isDisplayed()) {
                closeBtn.click();
            }
        } catch (Exception e) {
            // modal tidak muncul, tidak perlu lakukan apa-apa
        }
    }
}

