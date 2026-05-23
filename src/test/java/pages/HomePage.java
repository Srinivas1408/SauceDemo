package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import base.BasePage;

public class HomePage extends BasePage {

    private By pageTitle = By.cssSelector(".title");
    private By burgerMenu = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public LoginPage logout() {
        click(burgerMenu);
        click(logoutLink);
        waitHelper.waitForVisibility(By.id("user-name"));
        return new LoginPage(driver);
    }

    public boolean isProductTitleDisplayed() {
        return isDisplayed(pageTitle);
    }
}
