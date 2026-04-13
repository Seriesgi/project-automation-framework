@api
Feature: User API

  Scenario: Get User By ID
    Given User set GET endpoint
    When User send GET request
    Then Response status should be 200
