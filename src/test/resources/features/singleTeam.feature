Feature: Single NBA team

  Scenario Outline: Navigate to Boston Celtics
    Given  navigate to the URL
    When I move to teams
    And I click on element with team name "<teamName>"
    Then validate got to selected team page

    Examples:
    | teamName          |
    | Boston Celtics    |
    | Denver Nuggets    |
    | LA Clippers       |
    | Philadelphia 76ers|
    | Sacramento Kings  |

