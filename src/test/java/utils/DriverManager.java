package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {
    }

    public static void init() {
        if (driver != null) {
            return;
        }

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", BaseApi.getConfig("headless")))) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
