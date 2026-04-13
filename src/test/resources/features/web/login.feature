@web
Feature: Login Test

  Scenario: Login success
    Given User is on login page
    When User login with username "standard_user" and password "secret_sauce"
    Then User should be redirected to homepage
