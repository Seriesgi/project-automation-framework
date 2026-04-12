package com.test.api.steps;

import io.cucumber.java.Before;

public class ApiHooks {
    private final ApiScenarioContext context;

    public ApiHooks(ApiScenarioContext context) {
        this.context = context;
    }

    @Before("@api")
    public void resetContext() {
        context.setResponse(null);
        context.setCreatedUserId(null);
    }
}
