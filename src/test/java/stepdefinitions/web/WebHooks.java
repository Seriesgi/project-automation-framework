package stepdefinitions.web;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utils.DriverManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WebHooks {
    @Before("@web")
    public void setUpWebDriver() {
        DriverManager.init();
    }

    @After("@web")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Path screenshotPath = DriverManager.captureScreenshot(scenario.getName());
            if (screenshotPath != null) {
                try {
                    Allure.addAttachment("Failure Screenshot", "image/png", Files.newInputStream(screenshotPath), ".png");
                } catch (IOException exception) {
                    throw new IllegalStateException("Unable to attach screenshot to Allure", exception);
                }
            }
        }
        DriverManager.quit();
    }
}
