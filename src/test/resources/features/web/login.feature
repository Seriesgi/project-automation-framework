@web
Feature: Login Test

  Scenario: Login success
    Given User is on login page
    When User login with username "standard_user" and password "secret_sauce"
    Then User should be redirected to homepage

  Scenario: Login failed with invalid password
    Given User is on login page
    When User login with username "standard_user" and password "wrong_password"
    Then User should see login error message "Username and password do not match"
