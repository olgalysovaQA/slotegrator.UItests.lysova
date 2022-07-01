@login
Feature: authorization test

  Scenario: Check login is successful with valid credentials

    Given user is on login page
    When user enters username and password
    And clicks on sing in button
    Then user is navigated to the admin panel





