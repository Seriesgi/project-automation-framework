package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DriverManager {
    private static WebDriver driver;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    private DriverManager() {
    }

    public static void init() {
        if (driver != null) {
            return;
        }

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", ConfigManager.get("headless")))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait waitForDriver() {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getInt("web.timeout.seconds")));
    }

    public static Path captureScreenshot(String scenarioName) {
        if (driver == null) {
            return null;
        }

        try {
            Path screenshotDir = Path.of("build", "reports", "screenshots");
            Files.createDirectories(screenshotDir);
            String safeScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9-_]", "_");
            Path screenshotPath = screenshotDir.resolve(
                    safeScenarioName + "-" + TIMESTAMP_FORMATTER.format(LocalDateTime.now()) + ".png"
            );
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(screenshotPath, screenshot);
            return screenshotPath;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to capture screenshot", exception);
        }
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
