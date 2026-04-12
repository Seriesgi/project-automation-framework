package com.test.web.steps;

import com.test.web.pages.CartPage;
import com.test.web.pages.HomePage;
import com.test.web.pages.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebStepDefinitions {
    private final WebDriverContext context;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    public WebStepDefinitions(WebDriverContext context) {
        this.context = context;
    }

    @Given("I open the demo web store")
    public void iOpenTheDemoWebStore() {
        homePage = new HomePage(context.getDriver());
        homePage.open();
    }

    @Then("the home page should list product {string}")
    public void theHomePageShouldListProduct(String productName) {
        assertTrue(homePage.getVisibleProductNames().contains(productName));
    }

    @When("I open product detail for {string}")
    public void iOpenProductDetailFor(String productName) {
        productPage = homePage.openProduct(productName);
    }

    @Then("the product detail page should show title {string}")
    public void theProductDetailPageShouldShowTitle(String expectedTitle) {
        assertEquals(expectedTitle, productPage.getProductTitle());
    }

    @When("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        productPage.addToCart();
    }

    @When("I open the cart page")
    public void iOpenTheCartPage() {
        productPage.goToCart();
        cartPage = new CartPage(context.getDriver());
    }

    @Then("the cart should contain product {string}")
    public void theCartShouldContainProduct(String productName) {
        assertTrue(cartPage.isCartVisible());
        assertTrue(cartPage.getProductNamesInCart().contains(productName));
    }
}
