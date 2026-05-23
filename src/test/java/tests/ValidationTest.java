package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

@Listeners(TestListener.class)
public class ValidationTest extends BaseTest {

    @DataProvider(name = "emptyCredentials")
    public Object[][] emptyCredentialsData() {
        return new Object[][]{
                {"", ""},
                {"", "secret_sauce"},
                {"standard_user", ""}
        };
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentialsData() {
        return new Object[][]{
                {"invalid_user", "wrong_password"},
                {"locked_out_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "emptyCheckoutFields")
    public Object[][] emptyCheckoutFieldsData() {
        return new Object[][]{
                {"", "Doe", "600001", "Error: First Name is required"},
                {"John", "", "600001", "Error: Last Name is required"},
                {"John", "Doe", "", "Error: Postal Code is required"}
        };
    }

    @Test(priority = 1, description = "Verify login with empty username and password shows validation error")
    public void verifyEmptyUsernameAndPassword() {
        TestListener.setDriver("verifyEmptyUsernameAndPassword", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.attemptLogin("", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(priority = 2, description = "Verify login with empty username shows correct validation message")
    public void verifyEmptyUsername() {
        TestListener.setDriver("verifyEmptyUsername", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.attemptLogin("", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(priority = 3, description = "Verify login with empty password shows correct validation message")
    public void verifyEmptyPassword() {
        TestListener.setDriver("verifyEmptyPassword", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.attemptLogin("standard_user", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test(priority = 4, description = "Verify login with invalid credentials shows proper error message", dataProvider = "invalidCredentials")
    public void verifyInvalidLogin(String username, String password) {
        TestListener.setDriver("verifyInvalidLogin", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.attemptLogin(username, password);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        if (username.equals("locked_out_user")) {
            Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
        } else {
            Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
        }
    }

    @Test(priority = 5, description = "Verify locked_out_user shows locked user error message")
    public void verifyLockedUserError() {
        TestListener.setDriver("verifyLockedUserError", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.attemptLogin("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for locked user");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(priority = 6, description = "Verify checkout form validation when first name is empty", dataProvider = "emptyCheckoutFields")
    public void verifyCheckoutFieldValidation(String firstName, String lastName, String postalCode, String expectedError) {
        TestListener.setDriver("verifyCheckoutFieldValidation", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart(productPage.getAllProductNames().get(0));

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInformation(firstName, lastName, postalCode);
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isCheckoutErrorMessageDisplayed(), "Checkout error message should be displayed");
        Assert.assertEquals(checkoutPage.getCheckoutErrorMessage(), expectedError);
    }

    @Test(priority = 7, description = "Verify user cannot continue checkout with empty mandatory fields")
    public void verifyEmptyCheckoutFields() {
        TestListener.setDriver("verifyEmptyCheckoutFields", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart(productPage.getAllProductNames().get(0));

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isCheckoutErrorMessageDisplayed(), "Error should be displayed when all fields are empty");
        Assert.assertEquals(checkoutPage.getCheckoutErrorMessage(), "Error: First Name is required");
    }
}
