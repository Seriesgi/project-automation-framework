@api
Feature: User API

  Scenario: Get user list
    Given User set GET endpoint
    When User send GET request
    Then Response status should be 200
    And Response should contain at least 1 users
    And Every user should have an id

  Scenario: Get tags
    Given User set GET endpoint
    When User sends request to get tags
    Then Response status should be 200
    And Response should contain at least 1 tags

  Scenario: Get unknown user
    Given User set GET endpoint
    When User sends request for an unknown user
    Then Response status should be 404
    And Response error message should contain "RESOURCE_NOT_FOUND"
