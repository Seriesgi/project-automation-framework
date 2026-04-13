package stepdefinitions.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.BaseApi;

public class UserSteps {
    private Response response;

    @Given("User set GET endpoint")
    public void setEndpoint() {
        BaseApi.setup();
    }

    @When("User send GET request")
    public void sendRequest() {
        response = BaseApi.request.get("/user?limit=1");
    }

    @Then("Response status should be {int}")
    public void validateStatus(int status) {
        response.then().statusCode(status);
    }
}
