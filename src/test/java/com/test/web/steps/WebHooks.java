package com.test.web.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebHooks {
    private final WebDriverContext context;

    public WebHooks(WebDriverContext context) {
        this.context = context;
    }

    @Before("@web")
    public void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1440,900");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        context.setDriver(driver);
    }

    @After("@web")
    public void tearDownDriver() {
        if (context.getDriver() != null) {
            context.getDriver().quit();
        }
    }
}
