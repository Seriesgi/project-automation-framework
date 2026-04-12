@web
Feature: Browse products in Demoblaze
  As a shopper
  I want to browse product information and add an item to cart
  So that the main storefront flow is validated

  Scenario: View product detail from home page
    Given I open the demo web store
    Then the home page should list product "Samsung galaxy s6"
    When I open product detail for "Samsung galaxy s6"
    Then the product detail page should show title "Samsung galaxy s6"

  Scenario: Add product to cart
    Given I open the demo web store
    When I open product detail for "Samsung galaxy s6"
    And I add the product to the cart
    And I open the cart page
    Then the cart should contain product "Samsung galaxy s6"
