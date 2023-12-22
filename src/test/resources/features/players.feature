Feature: NBA Players

  Scenario Outline: Navigate to a URL, click on an element, and check p tags
    Given navigate to the URL
    When I click on element with first name '<playerName>'
    Then I check that the page contains <p> tags with firstName and lastName

    Examples:
      | playerName     |
      | Joel Embiid    |
      | Luka Doncic    |
      | Trae Young     |
      | Jayson Tatum   |
      | Kevin Durant   |
