Feature: purchase a product
  Scenario: add to cart
    Given open url https ://www.saucedemo.com/
    When enter loginid "standard_user" and password "secret_sauce"
    Then user click on login button
    And click on filter and select most expensive product
    Then validate price is less than "100"
    And check visibility of ADD TO CART button
    Then click on ADD TO CART
    And check visibility of Remove button
    Then click remove
    And check visibility of ADD TO CART button
    Then click on ADD TO CART
    And click on Cart
    Then click continue shopping
    And Select lowest product
    Then click on ADD TO CART
    And click on Cart
    Then click checkout
    And enter first name, lastname and postal code
    Then click continue
    And check total prize
    Then click finish
    And check success
