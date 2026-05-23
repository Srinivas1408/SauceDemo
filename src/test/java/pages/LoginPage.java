package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;

public class LoginPage extends BasePage {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");
    private By burgerMenu = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return new HomePage(driver);
    }

    public void attemptLogin(String username, String password) {
        if (!username.isEmpty()) {
            type(usernameField, username);
        }
        if (!password.isEmpty()) {
            type(passwordField, password);
        }
        click(loginButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public void clearFields() {
        try {
            WebElement username = waitForVisibility(usernameField);
            username.clear();
        } catch (Exception e) {
            // ignore
        }
        try {
            WebElement password = waitForVisibility(passwordField);
            password.clear();
        } catch (Exception e) {
            // ignore
        }
    }

    public void logout() {
        click(burgerMenu);
        click(logoutLink);
    }

    public boolean isLoginPageDisplayed() {
        try {
            WebElement element = waitForVisibility(usernameField);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginTitle() {
        return getText(By.cssSelector(".login_logo"));
    }
}
