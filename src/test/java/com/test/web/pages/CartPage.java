package com.test.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {
    private final By cartRows = By.cssSelector("#tbodyid tr");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductNamesInCart() {
        return waitForVisibleElements(By.cssSelector("#tbodyid tr td:nth-child(2)"))
                .stream()
                .map(element -> element.getText().trim())
                .collect(Collectors.toList());
    }

    public boolean isCartVisible() {
        return isDisplayed(cartRows);
    }
}
