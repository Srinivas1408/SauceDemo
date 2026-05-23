package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;

public class BasePage {

    protected WebDriver driver;
    protected WaitHelper waitHelper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    protected void click(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void typeUsingJS(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element.clear();
        js.executeScript(
            "var input = arguments[0];" +
            "var text = arguments[1];" +
            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeInputValueSetter.call(input, text);" +
            "input.dispatchEvent(new Event('input', { bubbles: true }));" +
            "input.dispatchEvent(new Event('change', { bubbles: true }));",
            element, text
        );
    }

    protected String getText(By locator) {
        WebElement element = waitForVisibility(locator);
        return element.getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            WebElement element = waitForVisibility(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected WebElement waitForVisibility(By locator) {
        return waitHelper.waitForVisibility(locator);
    }
}
