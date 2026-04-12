package com.test.api.steps;

import io.restassured.response.Response;

public class ApiScenarioContext {
    private Response response;
    private String createdUserId;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }
}
