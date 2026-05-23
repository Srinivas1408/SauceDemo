package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

import java.util.Arrays;
import java.util.List;

@Listeners(TestListener.class)
public class ProductTest extends BaseTest {

    @Test(priority = 1)
    public void verifyDashboardLoads() {
        TestListener.setDriver("verifyDashboardLoads", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.verifyDashboardLoaded(), "Dashboard should load after login");
        List<String> productNames = productPage.getAllProductNames();
        Assert.assertFalse(productNames.isEmpty(), "Product list should not be empty");
    }

    @Test(priority = 2)
    public void verifyProductsContainNameAndPrice() {
        TestListener.setDriver("verifyProductsContainNameAndPrice", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        List<String> productNames = productPage.getAllProductNames();
        List<String> productPrices = productPage.getAllProductPrices();
        
        Assert.assertFalse(productNames.isEmpty(), "Product names list should not be empty");
        Assert.assertEquals(productNames.size(), productPrices.size(), "Number of product names should match number of prices");
        
        for (String name : productNames) {
            Assert.assertNotNull(name, "Product name should not be null");
            Assert.assertFalse(name.trim().isEmpty(), "Product name should not be empty");
        }
        
        for (String price : productPrices) {
            Assert.assertNotNull(price, "Product price should not be null");
            Assert.assertTrue(price.startsWith("$"), "Product price should start with $");
            Assert.assertTrue(price.matches("\\$[0-9]+(\\.[0-9]{2})?"), "Product price should be in valid format");
        }
    }

    @Test(priority = 3)
    public void verifySingleProductAddToCart() {
        TestListener.setDriver("verifySingleProductAddToCart", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        
        String productName = productPage.getAllProductNames().get(0);
        productPage.addProductToCart(productName);
        
        Assert.assertTrue(productPage.isCartBadgeVisible(), "Cart badge should be visible after adding product");
        Assert.assertEquals(productPage.getCartBadgeCount(), 1, "Cart badge count should be 1");
    }

    @Test(priority = 4)
    public void verifyMultipleProductsAddToCart() {
        TestListener.setDriver("verifyMultipleProductsAddToCart", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        
        List<String> productNames = productPage.getAllProductNames();
        List<String> productsToAdd = Arrays.asList(productNames.get(0), productNames.get(1), productNames.get(2));
        
        productPage.addMultipleProducts(productsToAdd);
        
        Assert.assertTrue(productPage.isCartBadgeVisible(), "Cart badge should be visible after adding multiple products");
        Assert.assertEquals(productPage.getCartBadgeCount(), 3, "Cart badge count should be 3");
    }

    @Test(priority = 5)
    public void verifyCartIconVisibility() {
        TestListener.setDriver("verifyCartIconVisibility", driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        
        Assert.assertTrue(productPage.isCartIconVisible(), "Cart icon should be visible on product page");
        productPage.clickCartIcon();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "User should be navigated to cart page after clicking cart icon");
    }
}
