package com.test.web.steps;

import org.openqa.selenium.WebDriver;

public class WebDriverContext {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
