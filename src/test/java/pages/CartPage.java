package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;

import java.util.List;

public class CartPage extends BasePage {

    private By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    private By cartItems = By.cssSelector("[data-test='inventory-item']");
    private By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private By itemPrices = By.cssSelector("[data-test='inventory-item-price']");
    private By checkoutButton = By.cssSelector("[data-test='checkout']");
    private By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        click(cartIcon);
        waitHelper.waitForUrlContains("cart");
    }

    public boolean verifyProductExists(String productName) {
        List<WebElement> names = driver.findElements(itemNames);
        for (WebElement name : names) {
            if (name.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public String getProductPrice(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            String name = item.findElement(itemNames).getText();
            if (name.equals(productName)) {
                return item.findElement(itemPrices).getText();
            }
        }
        return "";
    }

    public void removeProduct(String productName) {
        String dataTestValue = "remove-" + productName.toLowerCase().replace(" ", "-");
        By removeButton = By.cssSelector("[data-test='" + dataTestValue + "']");
        click(removeButton);
    }

    public boolean isProductRemoved(String productName) {
        List<WebElement> names = driver.findElements(itemNames);
        for (WebElement name : names) {
            if (name.getText().equals(productName)) {
                return false;
            }
        }
        return true;
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public void clickCheckout() {
        click(checkoutButton);
    }

    public boolean isCheckoutButtonVisible() {
        return isDisplayed(checkoutButton);
    }
}
