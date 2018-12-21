Feature: Add a stockitem

  Scenario: Should be able to create a stockitem
    Given I have a empty stockitem
    When I create random stockitem
    And I add new stockitem to database
    And I check added item in database
    Then The returned stockitem should be correct

  Scenario Outline: Shoud be able to change quantity of existing stockitem
    Given I have a empty stockitem
    When I create random stockitem
    And I add new stockitem to database
    And I change quantity of existing stockitem to "<quantity>"
    Then The returned stockitem quantity should be "<quantity>"
    Examples:
      | quantity |
      | 12       |
      | 15       |
      | 1        |
      | 6        |

  Scenario Outline: Shoud be able to change price of existing stockitem
    Given I have a empty stockitem
    When I create random stockitem
    And I add new stockitem to database
    And I change price of existing stockitem to "<newPrice>"
    Then The returned stockitem price should be "<newPrice>"
    Examples:
      | newPrice |
      | 15.65    |
      | 12.06    |
      | 0        |
      | 9.99     |