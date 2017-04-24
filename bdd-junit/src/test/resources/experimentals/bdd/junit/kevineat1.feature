Feature: Kevin Eat In Method 1

  Background:
    Given with kevin had not eaten anything

  Scenario: eat without problem
    When kevin eat1 5 cucumbers
    Then kevin can eat1 antoher 5 cucumbers without problem

  Scenario: each too much
    When kevin eat1 9 cucumbers
    Then Kevin should not able to eat1 2 more cucumber


