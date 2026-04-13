package stepdefinitions.api;

import io.cucumber.java.Before;
import utils.BaseApi;

public class ApiHooks {
    @Before("@api")
    public void setUpApiClient() {
        BaseApi.setup();
    }
}
