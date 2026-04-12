package com.test.api.steps;

import com.test.api.client.DummyApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiStepDefinitions {
    private final ApiScenarioContext context;
    private DummyApiClient client;

    public ApiStepDefinitions(ApiScenarioContext context) {
        this.context = context;
    }

    @Given("the dummy API client is available")
    public void theDummyApiClientIsAvailable() {
        client = new DummyApiClient();
    }

    @When("I create a new dummy user")
    public void iCreateANewDummyUser() {
        String uniqueEmail = "automation-" + UUID.randomUUID() + "@mailinator.com";
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstName", "Automation");
        payload.put("lastName", "Tester");
        payload.put("email", uniqueEmail);

        context.setResponse(client.createUser(payload));
        context.setCreatedUserId(context.getResponse().jsonPath().getString("id"));
    }

    @When("I retrieve the created user by id")
    public void iRetrieveTheCreatedUserById() {
        context.setResponse(client.getUserById(context.getCreatedUserId()));
    }

    @When("I update the created user first name to {string}")
    public void iUpdateTheCreatedUserFirstNameTo(String firstName) {
        Map<String, Object> payload = Map.of("firstName", firstName);
        context.setResponse(client.updateUser(context.getCreatedUserId(), payload));
    }

    @When("I delete the created user")
    public void iDeleteTheCreatedUser() {
        context.setResponse(client.deleteUser(context.getCreatedUserId()));
    }

    @When("I request the list of tags with limit {int}")
    public void iRequestTheListOfTagsWithLimit(int limit) {
        context.setResponse(client.getTags(limit));
    }

    @Then("the API response status should be {int}")
    public void theApiResponseStatusShouldBe(int statusCode) {
        assertEquals(statusCode, context.getResponse().statusCode());
    }

    @Then("the response should contain a generated user id")
    public void theResponseShouldContainAGeneratedUserId() {
        assertNotNull(context.getCreatedUserId());
        assertFalse(context.getCreatedUserId().isBlank());
    }

    @Then("the API response field {string} should equal {string}")
    public void theApiResponseFieldShouldEqual(String fieldName, String expectedValue) {
        assertEquals(expectedValue, context.getResponse().jsonPath().getString(fieldName));
    }

    @Then("the delete response should reference the deleted user id")
    public void theDeleteResponseShouldReferenceTheDeletedUserId() {
        assertEquals(context.getCreatedUserId(), context.getResponse().jsonPath().getString("id"));
    }

    @Then("the API response should return {int} tags")
    public void theApiResponseShouldReturnTags(int expectedSize) {
        List<String> tags = context.getResponse().jsonPath().getList("data");
        assertNotNull(tags);
        assertEquals(expectedSize, tags.size());
    }

    @Then("every returned tag should be populated")
    public void everyReturnedTagShouldBePopulated() {
        List<String> tags = context.getResponse().jsonPath().getList("data");
        assertNotNull(tags);
        assertFalse(tags.isEmpty());
        assertFalse(tags.stream().anyMatch(tag -> tag == null || tag.isBlank()));
    }
}
