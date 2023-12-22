Feature: open season dropdown
  Scenario Outline: on Main page open season dropdown
    Given navigate to the URL
    When Select option '<Season>'
    Then validate season '<Season>' is selected

    Examples:
    | Season |
    | 2022-23|
    | 2021-22|
    | 2020-21|
    | 1989-90|
    | 1991-92|
