package com.test.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {
    private static final String DEFAULT_BASE_URL = "https://www.demoblaze.com/";

    private final By productCards = By.cssSelector("#tbodyid .card");
    private final By homeLink = By.id("nava");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = System.getProperty("web.base.url", DEFAULT_BASE_URL);
        driver.get(baseUrl);
        waitForVisible(homeLink);
        waitForVisibleElements(productCards);
    }

    public ProductPage openProduct(String productName) {
        waitForClickableAndClick(By.xpath("//a[normalize-space()='" + productName + "']"));
        return new ProductPage(driver);
    }

    public List<String> getVisibleProductNames() {
        return waitForVisibleElements(By.cssSelector("#tbodyid .card-title a"))
                .stream()
                .map(element -> element.getText().trim())
                .collect(Collectors.toList());
    }
}
