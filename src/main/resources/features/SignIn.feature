Feature: Shopping Automation
  Scenario: Testing the authentication
    Given I go to the website
    When I click on Login Button
    And I specify my credentials and click Login
    Then I can log into the website

  Scenario: Testing the purchase of items
    Given I go to the website
    When I click on Shop Button
    And I add product to cart
    And I confirm address, shipping, payment and final order
    Then The element are bought

