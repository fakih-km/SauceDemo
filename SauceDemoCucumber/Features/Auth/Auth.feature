@Auth
Feature: Login
  Testing Login Feature on SauceDemo

  @UnitTest @loginPositive
  Scenario Outline: Test Login sauce demo
    And User input username as "<username>"
    And User input password "<password>"
    And User click button login
    Then User verify title page
      | Products |
    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  @UnitTest @loginPositiveAndLogout
  Scenario Outline: Test LogOut sauce demo
    And User input username as "<username>"
    And User input password "<password>"
    And User click button login
    Then User verify title page
      | Products |
    And User click hamburger menu
    And User click logout
    Then User verify url
      | https://www.saucedemo.com/ |


    Examples:
      | username      | password     |
      | standard_user | secret_sauce |