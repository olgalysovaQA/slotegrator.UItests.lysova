
Feature: players management page

  @list
  Scenario: list of gamers is successfully open
    When user is logged in
    And user clicks on players online button
    Then list of gamers on player management page is open

  @Sorting
  Scenario: sorting with registration works for players table
    Given  user is on player management page
    When user click sort by registration
    Then players is sorted by registration

