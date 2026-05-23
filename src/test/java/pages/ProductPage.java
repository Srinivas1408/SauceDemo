package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    private By productCards = By.cssSelector("[data-test='inventory-item']");
    private By productNames = By.cssSelector("[data-test='inventory-item-name']");
    private By productPrices = By.cssSelector("[data-test='inventory-item-price']");
    private By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    private By dashboardTitle = By.cssSelector("[data-test='title']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyDashboardLoaded() {
        return isDisplayed(dashboardTitle);
    }

    public List<String> getAllProductNames() {
        List<WebElement> nameElements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement element : nameElements) {
            names.add(element.getText());
        }
        return names;
    }

    public List<String> getAllProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<String> prices = new ArrayList<>();
        for (WebElement element : priceElements) {
            prices.add(element.getText());
        }
        return prices;
    }

    public void addProductToCart(String productName) {
        String dataTestValue = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        By addButton = By.cssSelector("[data-test='" + dataTestValue + "']");
        WebElement button = waitForVisibility(addButton);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public void addMultipleProducts(List<String> products) {
        for (String product : products) {
            addProductToCart(product);
        }
        waitHelper.waitForVisibility(cartBadge);
    }

    public int getCartBadgeCount() {
        WebElement badge = waitForVisibility(cartBadge);
        return Integer.parseInt(badge.getText());
    }

    public boolean isCartIconVisible() {
        return isDisplayed(cartIcon);
    }

    public void clickCartIcon() {
        click(cartIcon);
        waitHelper.waitForUrlContains("cart");
    }

    public boolean isCartBadgeVisible() {
        try {
            WebElement badge = waitForVisibility(cartBadge);
            return badge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
