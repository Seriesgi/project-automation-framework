package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseApi {
    private static final Properties PROPERTIES = new Properties();
    public static RequestSpecification request;

    static {
        try (InputStream inputStream = BaseApi.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            if (inputStream != null) {
                PROPERTIES.load(inputStream);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load config.properties", exception);
        }
    }

    private BaseApi() {
    }

    public static void setup() {
        request = RestAssured.given()
                .baseUri(getConfig("api.base.url"))
                .header("app-id", getConfig("api.app.id"))
                .contentType("application/json");
    }

    public static String getConfig(String key) {
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }
}
