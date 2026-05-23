package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;

public class CheckoutPage extends BasePage {

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.cssSelector("[data-test='continue']");
    private By finishButton = By.cssSelector("[data-test='finish']");
    private By successMessage = By.cssSelector("[data-test='complete-header']");
    private By checkoutErrorMessage = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        typeUsingJS(firstNameField, firstName);
        typeUsingJS(lastNameField, lastName);
        typeUsingJS(postalCodeField, postalCode);
    }

    public void continueCheckout() {
        WebElement element = waitForVisibility(continueButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitHelper.waitForUrlContains("checkout-step-two");
        waitHelper.waitForVisibility(By.cssSelector("[data-test='finish']"));
    }

    public void finishCheckout() {
        click(finishButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public void completeCheckout(String firstName, String lastName, String postalCode) {
        enterCheckoutInformation(firstName, lastName, postalCode);
        continueCheckout();
        finishCheckout();
        waitHelper.waitForVisibility(successMessage);
    }

    public boolean isCheckoutPageLoaded() {
        return isDisplayed(firstNameField);
    }

    public void clickContinue() {
        WebElement element = waitForVisibility(continueButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        try {
            waitHelper.waitForVisibility(checkoutErrorMessage);
        } catch (Exception e) {
            // Error message may not appear if form is valid
        }
    }

    public String getCheckoutErrorMessage() {
        return getText(checkoutErrorMessage);
    }

    public boolean isCheckoutErrorMessageDisplayed() {
        return isDisplayed(checkoutErrorMessage);
    }
}
