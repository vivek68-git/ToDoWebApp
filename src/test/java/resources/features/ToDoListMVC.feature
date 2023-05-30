Feature: ToDo Items
  
  As a busy person
  I want to be able to note down what I need to do
  So that I don't forget

  Background: 
    Given user launches the ToDo MVC application

  @positive
  Scenario: Adding a single todo item
    Given User has not entered any todo items
    When User adds "Clean Clothes"
    Then todo list should contain:
      | Clean Clothes |

  @positive
  Scenario: Adding a single todo item
    Given User has not entered any todo items
    When User adds multiple todo items:
      | Wash Clothes |
      | Dry Clothes  |
      | Iron Clothes |
      | Set Clothes  |
    Then todo list should contain:
      | Wash Clothes |
      | Dry Clothes  |
      | Iron Clothes |
      | Set Clothes  |

  @positive
  Scenario: Edit Todo Item
    Given User has not entered any todo items
    When User adds "Wash Clothes"
    Then User updates "Wash Clothes" to "Dry Clothes"
    Then todo list should contain:
      | Dry Clothes |

  @positive
  Scenario: Completing ToDo Item
    Given User has not entered any todo items
    When User adds multiple todo items:
      | Wash Clothes |
      | Dry Clothes  |
      | Iron Clothes |
      | Set Clothes  |
    When User completes and clears some todo Items:
      | Dry Clothes  |
      | Iron Clothes |
    Then todo list should contain:
      | Wash Clothes |
      | Set Clothes  |

  @negative
  Scenario: Verifying Empty List
    Given User has not entered any todo items
    Then "Clear completed" should not be visible

  @negative
  Scenario: No clear completed option should be visible when none of the items is marked completed
    Given User has not entered any todo items
    When User adds multiple todo items:
      | Wash Clothes |
      | Dry Clothes  |
      | Iron Clothes |
      | Set Clothes  |
    Then "Clear completed" should not be visible

  @negative
  Scenario: No clear completed option should be visible when the list is already cleared
    Given User has not entered any todo items
    When User adds multiple todo items:
      | Wash Clothes |
      | Dry Clothes  |
      | Iron Clothes |
      | Set Clothes  |
    When User completes and clears some todo Items:
      | Wash Clothes |
      | Dry Clothes  |
      | Iron Clothes |
      | Set Clothes  |
    Then "Clear completed" should not be visible
