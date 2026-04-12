@api
Feature: Dummy API user and tag endpoints
  As an automation engineer
  I want to validate the public dummy API endpoints
  So that user lifecycle and tag APIs are covered in one framework

  Scenario: Create, get, update, and delete a user
    Given the dummy API client is available
    When I create a new dummy user
    Then the API response status should be 200
    And the response should contain a generated user id
    When I retrieve the created user by id
    Then the API response status should be 200
    And the API response field "firstName" should equal "Automation"
    When I update the created user first name to "Updated"
    Then the API response status should be 200
    And the API response field "firstName" should equal "Updated"
    When I delete the created user
    Then the API response status should be 200
    And the delete response should reference the deleted user id

  Scenario: Get tag list
    Given the dummy API client is available
    When I request the list of tags with limit 10
    Then the API response status should be 200
    And the API response should return 10 tags
    And every returned tag should be populated
