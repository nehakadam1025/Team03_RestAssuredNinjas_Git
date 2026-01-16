Feature: Verify reset password
   Scenario: POST/resetPassword Check if admin able to resetPassword with invalid content type
    Given Admin creates login request with invalid content type for resetPassword
    When Admin calls "postresetPassword" with "POST" http request
    Then Admin receives status code 415
  
  Scenario Outline: Check if admin able to resetPassword with invalid endpoint
    Given Admin performs resetPassword test "<rowNumber>"
    When Admin calls "postresetPasswordWrongEndpoint" with "POST" http request
    And Admin receives status code <statusCode>
    Examples:
      | rowNumber            | statusCode |
      | postresetPwdWrongEnd |     404    |
  
  Scenario Outline: Check if admin able to resetPassword with invalid method
    Given Admin performs resetPassword test "<rowNumber>"
    When Admin calls "postresetPassword" with "GET" http request
    And Admin receives status code <statusCode>
    Examples:
      | rowNumber            | statusCode |
      | postresetPwdWrongEnd |     405    |
  
  Scenario Outline: POST /resetPassword Check if admin able to generate token with valid and invalid credential using Excel row
    Given Admin performs resetPassword test "<rowNumber>"
    When Admin calls "postresetPassword" with "POST" http request
    Then Admin receives status code <statusCode>

    Examples: 
      | rowNumber            | statusCode |
      | posrRePwdspecialChaP |        400 |
      | posrRePwdwrongEmail  |        400 |
      | postresetPwd         |        200 |
      
   Scenario Outline: POST/login User login with valid  credentials
    Given Admin creates login request with "<email>" and "<password>"
    When Admin calls "postLoginApi" with "POST" http request
    And Admin receives status code <statusCode>
    Examples:
      | email                  | password               | statusCode |
      | team3@gmail.com        | ApiHackathon2@3        |        401 |
      | team3@gmail.com        | ApiHackathona2@3       |        200 |
   
   
      
  Scenario: Check if admin able to logout with invalid endpoint
    Given Admin creates request with invalid endpoint
    When Admin calls "getLogoutlmswrongEndPoint" with "GET" http request
    Then Admin receives status code 404

  Scenario: POST/logoutlms Check if admin able to logout  with invalid method
    Given Admin creates request with invalid method
    When Admin calls "getLogoutlms" with "POST" http request
    Then Admin receives status code 405

  Scenario: Check if admin able to logout with admin sets No authorization
    Given Admin creates request with no auth
    When Admin calls "getLogoutlms" with "GET" http request
    Then Admin receives status code 401

  Scenario: Check if admin able to logout to LMS Application
    Given Check if admin able to logout to LMS Application
    When Admin calls "getLogoutlms" with "GET" http request
    Then Admin receives status code 200
