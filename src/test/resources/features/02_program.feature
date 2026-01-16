Feature: program module Functionality

  Scenario: Check if admin able to get all program module with valid endpoint
    Given Admin creates GET Request for the program module
    When Admin calls "getaaprgram" with "GET" http request for Program module
    Then Admin receives 200 Status program

  Scenario: Check if Admin able to retrieve all programs with invalid endpoint
    Given Admin creates GET Request for the program module
    When Admin calls "invalidprogramendpoint" with "GET" http request for Program module
    Then Admin receives 404 Status program

  Scenario: Check if Admin able to retrieve all programs with invalid Method
    Given Admin creates GET Request for the program module
    When Admin calls "getaaprgram" with "POST" http request for Program module
    Then Admin receives 405 Status program

  Scenario: Check if Admin able to retrieve all programs without Authorization
    Given Admin creates GET Request for the program module without Authorization
    When Admin calls "getaaprgram" with "GET" http request for Program module
    Then Admin receives 401 Status program

  Scenario: Check if Admin able to retrieve a program with valid program ID
    Given Admin creates GET Request for the program module
    When Admin calls "getprogrambyid" with "GET" http request for Program module
    Then Admin receives 200 Status program

  Scenario: Check if Admin able to retrieve a program with invalid program ID
    Given Admin creates GET Request for the program module
    When Admin calls "getprogrambyinvalidid" with "GET" http request for Program module
    Then Admin receives 404 Status program

   #Scenario: Check if Admin is able to retrieve a program with invalid baseURI
   #Given Admin creates GET Request for the LMS API with invalid baseURI
   #When Admin calls "getprogrambyid" with "GET" http request for Program module
   #Then Admin receives 404 status program
  
  Scenario: Check if Admin able to retrieve a program without Authorization
    Given Admin creates GET Request for the program module without Authorization
    When Admin calls "getprogrambyid" with "GET" http request for Program module
    Then Admin receives 401 Status program

  Scenario: Check if Admin able to retrieve a program with invalid endpoint
    Given Admin creates GET Request for the program module
    When Admin calls "getprogrambyinvalidendpoint" with "GET" http request for Program module
    Then Admin receives 404 Status program

  Scenario: Check if Admin able to retrieve a program with invalid Method
    Given Admin creates GET Request for the program module
    When Admin calls "getprogrambyid" with "POST" http request for Program module
    Then Admin receives 405 Status program

  Scenario: Check if Admin able to create a new program with valid credentials
    Given Admin creates POST Request for the program module
    When Admin calls "createprogram" with "POST" http request for Program module
    Then Admin receives 201 Status code with respose body

  Scenario: Check if Admin able to create a new program without Authorization
    Given Admin creates POST Request for the program module without Authorization
    When Admin calls "createprogram" with "POST" http request for Program module
    Then Admin receives 401 Status program

  Scenario: Check if Admin able to create a program with invalid endpoint
    Given Admin creates POST Request for the program module
    When Admin calls "CreateProgrambyInvalidEndpoint" with "POST" http request for Program module
    Then Admin receives 404 Status program

  Scenario: Check if Admin able to create a program with already existing program name
    Given Admin creates POST Request for the program module with existing program name
    When Admin calls "createprogram" with "POST" http request for Program module
    Then Admin receives 400 Status program

    Scenario: Check if Admin able to create a program with invalid Method
    Given Admin creates POST Request for the program module
    When Admin calls "createprogram" with "PUT" http request for Program module
    Then Admin receives 405 Status program
    
   Scenario: Check if Admin able to create a program with invalid request body
  Given Admin creates POST Request for the program module with invalid request body
  When Admin calls "createprogram" with "POST" http request for Program module
  Then Admin receives 400 Status program
  
    Scenario: Check if Admin able to create a program with empty request body
  Given Admin creates POST Request for the program module with empty request body
  When Admin calls "createprogram" with "POST" http request for Program module
  Then Admin receives 400 Status program
  
  Scenario: Check if Admin able to create a program with missing additional field
  Given Admin creates POST Request for the program module with request body missing additional field
  When Admin calls "createprogram" with "POST" http request for Program module
  Then Admin receives 200 Status program
  
  Scenario: Check if Admin able to update a program with valid programID endpoint and valid request body
  Given Admin creates PUT Request for the program module
  When Admin calls "updateprogrambyid" with "PUT" http request for Program module for put
  Then Admin receives 200 Status program
  
   Scenario: Check if Admin able to update a program with invalid program ID
     Given Admin creates PUT Request for the program module
     When Admin calls "updatebyinvalidid" with "PUT" http request for Program module
    Then Admin receives 404 Status program
    
    Scenario: Check if Admin able to update a program with invalid request body
     Given Admin creates PUT Request for the program module with invalid body
     When Admin calls "updateprogrambyid" with "PUT" http request for Program module for put
    Then Admin receives 400 Status program
    
     Scenario: Check if Admin able to update a program with missing fields in request body
     Given Admin creates PUT Request for the program module with missing mandatory fields
     When Admin calls "updateprogrambyid" with "PUT" http request for Program module for put
    Then Admin receives 400 Status program
    
    Scenario: Check if Admin able to update a program with invalid Method
    Given Admin creates PUT Request for the program module
    When Admin calls "updateprogrambyid" with "GET" http request for Program module for put
    Then Admin receives 405 Status program
    
    Scenario: Check if Admin able to update a program by program id without Authorization
    Given Admin creates PUT Request for the program module without Authorization
    When Admin calls "updateprogrambyid" with "PUT" http request for Program module for put
    Then Admin receives 401 Status program
    
  #   Scenario: Check if Admin able to update a program by program name without Authorization
   # Given Admin creates PUT Request for the program by name module without Authorization
    #And puts valid data in request payload
    #When Admin calls "updateprogrambyname" with "PUT" http request for Program by name
    #Then Admin receives 401 Status program
    
 #   Scenario Outline: Validate updating program by program name with different conditions
  #Given Admin sets Authorization as "<AuthType>"
  #And Admin creates PUT Request for the LMS API with "<RequestBodyType>"
  #When Admin sends HTTPS PUT Request with "<ProgramNameType>" program name endpoint
  #Then Admin receives <StatusCode> Status program
  
 # Examples:
#| AuthType | RequestBodyType                      | ProgramNameType | StatusCode |
#| Valid    | valid request body                   | valid           | 200        |
#| Valid    | valid request body                   | invalid         | 404        |
#| Valid    | missing mandatory fields             | valid           | 400        |
#| Valid    | invalid values                       | valid           | 400        |
#| Valid    | invalid program description          | valid           | 400        |
#| Valid    | valid program name and status only   | valid           | 200        |
#| NoAuth   | valid request body                   | valid           | 401        |
  
  Scenario Outline: Check if Admin able to delete a program by program name
    Given Admin sets Authorization as "<authType>" for delete module
    And Admin prepares DELETE Request for the LMS API with "<programNameType>" program name
    When Admin sends HTTPS DELETE Request with program name endpoint
    Then Admin receives <statusCode> Status program

    Examples:
      | authType | programNameType | statusCode |
      | Valid    | valid           | 200        |
      | Valid    | invalid         | 404        |
      | NoAuth   | valid           | 401        |
  
    Scenario Outline: Check if Admin able to delete a program by program ID
    Given Admin sets Authorization as "<authType>" for delete module
    And Admin prepares DELETE Request for the LMS API with "<programIdType>" program ID
    When Admin sends HTTPS DELETE Request with program ID endpoint
    Then Admin receives <statusCode> Status program

    Examples:
      | authType | programIdType | statusCode |
      | Valid    | valid         | 200        |
      | Valid    | invalid       | 404        |
      | NoAuth   | valid         | 401        |
    

