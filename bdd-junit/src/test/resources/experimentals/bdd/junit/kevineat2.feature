Feature: Kevin Eat In Method 2

  Background:
    Given with kevin had eaten 2 cucumbers

  Scenario: eat without problem
    When kevin eat2 5 cucumbers
    Then kevin can eat2 antoher 2 cucumbers without problem


