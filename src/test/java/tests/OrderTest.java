package tests;

import base.BaseTest;
import listeners.TestListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.CompletePage;
import pages.LoginPage;
import pages.OverviewPage;
import pages.ProductPage;

import java.util.Arrays;
import java.util.List;

@Listeners(TestListener.class)
public class OrderTest extends BaseTest {

    private void navigateToOverview() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        List<String> productNames = productPage.getAllProductNames();
        productPage.addMultipleProducts(Arrays.asList(productNames.get(0), productNames.get(1)));

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInformation("John", "Doe", "600001");
        checkoutPage.continueCheckout();
        OverviewPage overviewPage = new OverviewPage(driver);
        waitHelper.waitForVisibility(By.cssSelector("[data-test='finish']"));
    }

    @Test(priority = 1)
    public void verifyOrderCompletionPage() {
        TestListener.setDriver("verifyOrderCompletionPage", driver);
        navigateToOverview();

        OverviewPage overviewPage = new OverviewPage(driver);
        overviewPage.clickFinish();

        CompletePage completePage = new CompletePage(driver);
        Assert.assertTrue(completePage.isOrderCompleted(), "Order completion page should be displayed");
        Assert.assertEquals(completePage.getSuccessMessage(), "Thank you for your order!", "Success message should match expected text");
    }

    @Test(priority = 2)
    public void verifyProductsAppearInOverview() {
        TestListener.setDriver("verifyProductsAppearInOverview", driver);
        navigateToOverview();

        OverviewPage overviewPage = new OverviewPage(driver);
        Assert.assertTrue(overviewPage.isOverviewPageLoaded(), "Overview page should be loaded");

        List<String> overviewProductNames = overviewPage.getOverviewProductNames();
        Assert.assertFalse(overviewProductNames.isEmpty(), "Product names should appear in overview");

        List<String> overviewProductPrices = overviewPage.getOverviewProductPrices();
        Assert.assertEquals(overviewProductNames.size(), overviewProductPrices.size(), "Number of products should match number of prices");

        for (String price : overviewProductPrices) {
            Assert.assertTrue(price.startsWith("$"), "Product price should start with $");
        }
    }

    @Test(priority = 3)
    public void verifyOrderSummaryDetails() {
        TestListener.setDriver("verifyOrderSummaryDetails", driver);
        navigateToOverview();

        OverviewPage overviewPage = new OverviewPage(driver);
        String paymentInfo = overviewPage.getPaymentInfo();
        String shippingInfo = overviewPage.getShippingInfo();

        Assert.assertNotNull(paymentInfo, "Payment information should be displayed");
        Assert.assertFalse(paymentInfo.trim().isEmpty(), "Payment information should not be empty");
        Assert.assertNotNull(shippingInfo, "Shipping information should be displayed");
        Assert.assertFalse(shippingInfo.trim().isEmpty(), "Shipping information should not be empty");
    }

    @Test(priority = 4)
    public void verifyTotalPriceDisplayed() {
        TestListener.setDriver("verifyTotalPriceDisplayed", driver);
        navigateToOverview();

        OverviewPage overviewPage = new OverviewPage(driver);
        String itemTotal = overviewPage.getItemTotal();
        String taxAmount = overviewPage.getTaxAmount();
        String totalAmount = overviewPage.getTotalAmount();

        Assert.assertNotNull(itemTotal, "Item total should be displayed");
        Assert.assertTrue(itemTotal.contains("$"), "Item total should contain $");
        Assert.assertNotNull(taxAmount, "Tax amount should be displayed");
        Assert.assertTrue(taxAmount.contains("$"), "Tax amount should contain $");
        Assert.assertNotNull(totalAmount, "Total amount should be displayed");
        Assert.assertTrue(totalAmount.contains("$"), "Total amount should contain $");
    }

    @Test(priority = 5)
    public void verifyBackHomeNavigation() {
        TestListener.setDriver("verifyBackHomeNavigation", driver);
        navigateToOverview();

        OverviewPage overviewPage = new OverviewPage(driver);
        overviewPage.clickFinish();

        CompletePage completePage = new CompletePage(driver);
        Assert.assertTrue(completePage.isOrderCompleted(), "Order should be completed");
        completePage.clickBackHome();

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.verifyDashboardLoaded(), "User should be navigated back to products page");
    }
}
