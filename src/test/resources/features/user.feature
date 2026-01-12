Feature: User Login Controller

  Scenario Outline: POST/login User login with valid and invalid credentials
    Given Admin creates login request with "<email>" and "<password>"
    When Admin calls "postLoginApi" with "POST" http request
    And Admin receives status code <statusCode>

    Examples: 
      | email                  | password               | statusCode |
      | team3@gmail.com        | ApiHackathon2@3        |        200 |
      | &^%$$$$team3@gmail.com | ApiHackathon2@3        |        400 |
      | team3@gmail.com        | &^%$$$$ApiHackathon2@3 |        401 |
      | team3@hotmail.com.com  | ApiHackathon2@3        |        400 |
      |        222222222222222 | ApiHackathon2@3        |        400 |
      | team3@gmail.com        |        222222222222222 |        400 |
      |                        | ApiHackathon2@3        |        400 |
      | team3@gmail.com        |                        |        400 |

  Scenario Outline: Check if admin able to generate token with invalid endpoint
     Given Admin creates login request with "<email>" and "<password>"
    When Admin calls "postLoginWrongEndPoint" with "POST" http request
    And Admin receives status code <statusCode>

    Examples: 
      | email           | password        | statusCode |
      | team3@gmail.com | ApiHackathon2@3 |        401 |
    
  Scenario Outline: Check if admin able to generate token with invalid method
    Given Admin creates login request with "<email>" and "<password>"
    When Admin calls "postLoginApi" with "GET" http request
    And Admin receives status code <statusCode>

    Examples: 
      | email           | password        | statusCode |
      | team3@gmail.com | ApiHackathon2@3 |        405 |
     
     
    Scenario: Check if admin able to generate token with Null body
    Given Admin creates login request with Null body
     When Admin calls "postLoginApi" with "POST" http request
    Then Admin recieved 400 status code
      
    Scenario: Check if admin able to logout from LMS Application
    Given admin sets authorization to bearer Token with creates request
    When Admin calls "getLogoutlms" with "GET" http request
    Then Admin recieved 200 status code
    
    #Scenario: Check if admin able to skillmaster from LMS Application
    #Given Admist getting skill master request
    #When Admin calls http request for skill
    #Then Admin recieved hdjsdl status code
      
