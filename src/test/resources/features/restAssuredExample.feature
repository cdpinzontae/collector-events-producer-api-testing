Feature: test end to end domestic multi city booking
  Background: Endpoint Configuration
    Given The endpoint is already configured

  @api
  Scenario: Enter a valid userId
    When I input a valid userId "1"
    Then I should have the status code "200"

