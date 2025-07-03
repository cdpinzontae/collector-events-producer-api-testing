@enrollment
Feature: Enrollment event for new collector

  As a user
  I want to validate the enrollment event
  So that only valid collector data is sent to Kinesis

  Background:
    Given the enrollment endpoint is available

  Scenario: Successful enrollment with all mandatory fields and valid collector number
    Given I prepare the payload with all mandatory fields and a valid collector number
    When I send the enrollment request
    Then the response status code should be 200
    And the response body should contain message "Enrollment event created"

  Scenario: Missing required field 'channel'
    Given I prepare the payload with all mandatory fields except "channel"
    When I send the enrollment request
    Then the response status code should be 400
    And the response body should contain error message "Missing required field: channel"

  Scenario: Invalid collector number that fails MOD11
    Given I prepare the payload with an invalid collector number
    When I send the enrollment request
    Then the response status code should be 400
    And the response body should contain error message "Invalid collector number"


  Scenario: Enrollment with issuerCode as null (should be accepted)
    Given I prepare the payload with issuerCode as null
    When I send the enrollment request
    Then the response status code should be 200
    And the response body should contain message "Enrollment event created"
