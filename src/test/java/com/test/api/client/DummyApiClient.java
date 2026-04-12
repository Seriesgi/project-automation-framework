package com.test.api.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class DummyApiClient {
    private static final String DEFAULT_BASE_URL = "https://dummyapi.io/data/v1";
    private static final String DEFAULT_APP_ID = "63a804408eb0cb069b57e43a";

    private final RequestSpecification requestSpecification;

    public DummyApiClient() {
        String baseUrl = System.getProperty("api.base.url", DEFAULT_BASE_URL);
        String appId = resolveAppId();

        this.requestSpecification = RestAssured.given()
                .baseUri(baseUrl)
                .header("app-id", appId)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    private String resolveAppId() {
        String systemProperty = System.getProperty("dummy.api.app.id");
        if (systemProperty != null && !systemProperty.isBlank()) {
            return systemProperty;
        }

        String environmentValue = System.getenv("DUMMY_API_APP_ID");
        if (environmentValue != null && !environmentValue.isBlank()) {
            return environmentValue;
        }

        return DEFAULT_APP_ID;
    }

    public Response createUser(Map<String, Object> payload) {
        return requestSpecification
                .body(payload)
                .post("/user/create");
    }

    public Response getUserById(String userId) {
        return requestSpecification
                .get("/user/{id}", userId);
    }

    public Response updateUser(String userId, Map<String, Object> payload) {
        return requestSpecification
                .body(payload)
                .put("/user/{id}", userId);
    }

    public Response deleteUser(String userId) {
        return requestSpecification
                .delete("/user/{id}", userId);
    }

    public Response getTags(int limit) {
        return requestSpecification
                .queryParam("limit", limit)
                .get("/tag");
    }
}
