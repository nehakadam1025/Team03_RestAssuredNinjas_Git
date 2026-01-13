Feature: program module Functionality

 Scenario: Check if admin able to get all program module with valid endpoint 
    Given Admin creates GET Request for the program module
    When Admin calls "getaaprgram" with "GET" http request for Program module
    Then Admin receives 200 Status program