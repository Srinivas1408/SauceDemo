package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"invalid_user", "wrong_password"}
        };
    }

    @Test(priority = 1)
    public void verifyValidLogin() {
        TestListener.setDriver("verifyValidLogin", driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(homePage.isProductTitleDisplayed(), "Home page title should be displayed after successful login");
        Assert.assertEquals(homePage.getPageTitle(), "Products", "Page title should be 'Products'");
    }

    @DataProvider(name = "lockedUserCredentials")
    public Object[][] getLockedUserCredentials() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce"},
                {"invalid_user", "wrong_password"}
        };
    }

    @Test(priority = 2, dataProvider = "lockedUserCredentials")
    public void verifyLockedUserLogin(String username, String password) {
        TestListener.setDriver("verifyLockedUserLogin", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for locked/invalid user");
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(
                errorMessage.contains("Epic sadface") || errorMessage.contains("locked_out") || errorMessage.contains("invalid"),
                "Error message should contain appropriate text. Actual: " + errorMessage
        );
    }

    @Test(priority = 3)
    public void verifyEmptyLogin() {
        TestListener.setDriver("verifyEmptyLogin", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for empty credentials");
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(
                errorMessage.contains("Username is required") || errorMessage.contains("Epic sadface"),
                "Error message should indicate validation error. Actual: " + errorMessage
        );
    }

    @Test(priority = 4)
    public void verifyLogout() {
        TestListener.setDriver("verifyLogout", driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(homePage.isProductTitleDisplayed(), "User should be on home page before logout");
        LoginPage loginPageAfterLogout = homePage.logout();
        Assert.assertTrue(loginPageAfterLogout.isLoginPageDisplayed(), "User should be redirected to login page after logout");
    }
}
