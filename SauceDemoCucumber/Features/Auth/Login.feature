@Login
Feature: Login
  Testing Login Feature on SauceDemo

  @UnitTest @loginPositive
  Scenario Outline: Test Login sauce demo
    And User input username as "<username>"
    And User input password "<password>"
    And User click button login
    Examples:
      | username      | password     |
      | standard_user | secret_sauce |
