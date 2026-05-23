package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class OverviewPage extends BasePage {

    private By productNames = By.cssSelector("[data-test='inventory-item-name']");
    private By productPrices = By.cssSelector("[data-test='inventory-item-price']");
    private By paymentInfo = By.cssSelector("[data-test='payment-info-label']");
    private By shippingInfo = By.cssSelector("[data-test='shipping-info-label']");
    private By itemTotal = By.cssSelector(".summary_subtotal_label");
    private By taxAmount = By.cssSelector(".summary_tax_label");
    private By totalAmount = By.cssSelector(".summary_total_label");
    private By finishButton = By.cssSelector("[data-test='finish']");

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getOverviewProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText());
        }
        return names;
    }

    public List<String> getOverviewProductPrices() {
        List<WebElement> elements = driver.findElements(productPrices);
        List<String> prices = new ArrayList<>();
        for (WebElement el : elements) {
            prices.add(el.getText());
        }
        return prices;
    }

    public String getPaymentInfo() {
        return getText(paymentInfo);
    }

    public String getShippingInfo() {
        return getText(shippingInfo);
    }

    public String getItemTotal() {
        waitHelper.waitForVisibility(itemTotal);
        return getText(itemTotal);
    }

    public String getTaxAmount() {
        waitHelper.waitForVisibility(taxAmount);
        return getText(taxAmount);
    }

    public String getTotalAmount() {
        waitHelper.waitForVisibility(totalAmount);
        return getText(totalAmount);
    }

    public void clickFinish() {
        click(finishButton);
    }

    public boolean isOverviewPageLoaded() {
        return isDisplayed(finishButton);
    }
}
