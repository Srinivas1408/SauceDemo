package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

import java.util.Arrays;
import java.util.List;

@Listeners(TestListener.class)
public class CartTest extends BaseTest {

    @Test(priority = 1)
    public void verifyProductsInCart() {
        TestListener.setDriver("verifyProductsInCart", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        List<String> productNames = productPage.getAllProductNames();
        productPage.addMultipleProducts(Arrays.asList(productNames.get(0), productNames.get(1)));

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        Assert.assertTrue(cartPage.verifyProductExists(productNames.get(0)), "First product should exist in cart");
        Assert.assertTrue(cartPage.verifyProductExists(productNames.get(1)), "Second product should exist in cart");

        String price = cartPage.getProductPrice(productNames.get(0));
        Assert.assertNotNull(price, "Product price should not be null");
        Assert.assertTrue(price.startsWith("$"), "Product price should start with $");
    }

    @Test(priority = 2)
    public void verifyRemoveProductFromCart() {
        TestListener.setDriver("verifyRemoveProductFromCart", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        List<String> productNames = productPage.getAllProductNames();
        productPage.addMultipleProducts(Arrays.asList(productNames.get(0), productNames.get(1)));

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        String productNameToRemove = productNames.get(0);
        cartPage.removeProduct(productNameToRemove);

        Assert.assertTrue(cartPage.isProductRemoved(productNameToRemove), "Removed product should not appear in cart");
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should have 1 item after removal");
    }

    @Test(priority = 3)
    public void verifyCartItemCount() {
        TestListener.setDriver("verifyCartItemCount", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        List<String> productNames = productPage.getAllProductNames();
        productPage.addMultipleProducts(Arrays.asList(productNames.get(0), productNames.get(1), productNames.get(2)));

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 3, "Cart should contain 3 items");
    }

    @Test(priority = 4)
    public void verifyCheckoutPageNavigation() {
        TestListener.setDriver("verifyCheckoutPageNavigation", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        List<String> productNames = productPage.getAllProductNames();
        productPage.addProductToCart(productNames.get(0));

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page should load successfully");
    }

    @Test(priority = 5)
    public void verifySuccessfulCheckout() {
        TestListener.setDriver("verifySuccessfulCheckout", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        List<String> productNames = productPage.getAllProductNames();
        productPage.addProductToCart(productNames.get(0));

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.completeCheckout("John", "Doe", "600001");

        Assert.assertTrue(checkoutPage.isSuccessMessageDisplayed(), "Success message should be displayed");
        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Thank you for your order!", "Success message should match expected text");
    }
}
