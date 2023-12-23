Feature: filter
  Scenario: filter test FGA=21.8
    Given navigate to the URL
    When click filter
    | option    | value     |
    | FGA       | 21.8      |
    Then check filtered players
