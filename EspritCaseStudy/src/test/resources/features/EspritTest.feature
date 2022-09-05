Feature: Esprit Case Study

  Background:
    When User navigates to Esprit URL
    And User clicks on Login button
    Then User enters valid "<username>" and "<password>"
    And User clicks on Submit button

    #This is the actual test case file written in gherkin language

  @Regression
  Scenario: Test01 >> verify user is able login to webShop
    Given User clicks on Logo
    Then User navigates to Women collection

#  @AnotherApproach
#  Scenario Outline: Test04 >> Verify login action with multiple users
#    Then User enters valid "<username>" and "<password>"
#    And User clicks on Login button
#    Then User clicks on logout
#    Examples:
#      | username | password |
#      |abc       | 1234     |
#      |def       | 5678     |
