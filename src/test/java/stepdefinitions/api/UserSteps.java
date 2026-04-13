package stepdefinitions.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.ConfigManager;
import utils.BaseApi;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.blankOrNullString;

public class UserSteps {
    private Response response;

    @Given("User set GET endpoint")
    public void setEndpoint() {
        BaseApi.setup();
    }

    @When("User send GET request")
    public void sendRequest() {
        response = BaseApi.request.get("/user?limit=" + ConfigManager.get("api.user.limit"));
    }

    @When("User sends request to get tags")
    public void sendTagsRequest() {
        response = BaseApi.request.get("/tag?limit=" + ConfigManager.get("api.tag.limit"));
    }

    @When("User sends request for an unknown user")
    public void sendUnknownUserRequest() {
        response = BaseApi.request.get("/user/" + ConfigManager.get("api.invalid.user.id"));
    }

    @Then("Response status should be {int}")
    public void validateStatus(int status) {
        response.then().statusCode(status);
    }

    @And("Response should contain at least {int} users")
    public void validateUserCount(int minimumSize) {
        List<Object> users = response.jsonPath().getList("data");
        assertThat(users.size(), greaterThanOrEqualTo(minimumSize));
    }

    @And("Every user should have an id")
    public void validateUserIds() {
        List<String> userIds = response.jsonPath().getList("data.id");
        for (String userId : userIds) {
            assertThat(userId, not(blankOrNullString()));
        }
    }

    @And("Response should contain at least {int} tags")
    public void validateTagCount(int minimumSize) {
        List<String> tags = response.jsonPath().getList("data");
        assertThat(tags.size(), greaterThanOrEqualTo(minimumSize));
    }

    @And("Response error message should contain {string}")
    public void validateErrorMessage(String expectedMessage) {
        assertThat(response.jsonPath().getString("error"), not(blankOrNullString()));
        assertThat(response.jsonPath().getString("error"), containsString(expectedMessage));
    }
}
