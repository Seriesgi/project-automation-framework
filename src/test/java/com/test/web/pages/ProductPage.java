package com.test.web.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    private final By productTitle = By.cssSelector(".name");
    private final By addToCartButton = By.cssSelector(".btn.btn-success.btn-lg");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        return waitForVisible(productTitle).getText().trim();
    }

    public void addToCart() {
        waitForClickableAndClick(addToCartButton);
        wait.until(ExpectedConditions.alertIsPresent());
        acceptAlertIfPresent();
    }

    public void goToCart() {
        waitForClickableAndClick(By.id("cartur"));
    }

    private void acceptAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException ignored) {
        }
    }
}
