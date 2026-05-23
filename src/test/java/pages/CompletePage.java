package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class CompletePage extends BasePage {

    private By successMessage = By.cssSelector("[data-test='complete-header']");
    private By confirmationHeader = By.cssSelector("[data-test='title']");
    private By backHomeButton = By.cssSelector("[data-test='back-to-products']");

    public CompletePage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public String getConfirmationHeader() {
        return getText(confirmationHeader);
    }

    public void clickBackHome() {
        click(backHomeButton);
    }

    public boolean isOrderCompleted() {
        return isDisplayed(successMessage);
    }

    public boolean isBackHomeButtonVisible() {
        return isDisplayed(backHomeButton);
    }
}
