package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
    public static RequestSpecification request;

    private BaseApi() {
    }

    public static void setup() {
        request = RestAssured.given()
                .baseUri(ConfigManager.get("api.base.url"))
                .header("app-id", ConfigManager.get("api.app.id"))
                .contentType("application/json");
    }
}
