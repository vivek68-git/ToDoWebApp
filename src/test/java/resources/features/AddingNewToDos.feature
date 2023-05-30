Feature: Adding new todos
  
  As a busy person
  I want to be able to note down what I need to do
  So that I don't forget

  Background: 
    Given user launches the ToDo MVC application

  Scenario: Completing ToDoItem
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
