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
  Scenario: POST/login Check if admin able to generate token with invalid content type
    Given Admin creates login request with invalid content type
    When Admin calls "postLoginApi" with "POST" http request
    Then Admin receives status code 415
  Scenario Outline: POST/login Check if admin able to generate token with invalid base URL
    Given Admin creates login request with "<email>" and "<password>" invalid baseurl
    When Admin calls "postLoginApi" with "POST" http request
    And Admin receives status code <statusCode>
    Examples:
      | email           | password        | statusCode |
      | team3@gmail.com | ApiHackathon2@3 |        404 |
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
    Then Admin receives status code 400
  Scenario Outline: Post:/login/forgotpassword/confirmEmail Check if admin able to generate token with valid credential for confirm email
    Given Admin creates request with valid email "<emailId>"
    When Admin calls "postforgotPasswordConfirmEmail" with "POST" http request
    And Admin receives status code <statusCode>
    Examples:
      | emailId                      | statusCode |
      | team3@gmail.com              |        201 |
      | &&&&&&&&&&&&&team3@gmail.com |        400 |
      | team3@hotmail.com            |        400 |
  Scenario: Post:/login/forgotpassword/confirmEmail Check if admin able to generate token with invalid content type
    Given Admin creates login request with invalid content type for forgotPassword
    When Admin calls "postforgotPasswordConfirmEmail" with "POST" http request
    Then Admin receives status code 415
  Scenario Outline: Check if admin able to generate token with invalid endpoint
    Given Admin creates login request with "<emailId>" with invalid endpoint
    When Admin calls "postforgotPasswordConfirmEmailWrongEndpoint" with "POST" http request
    And Admin receives status code <statusCode>
    Examples:
      | emailId         | statusCode |
      | team3@gmail.com |        404 |
  Scenario: Check if admin able to generate token with Null body in POST/login/forgotpassword/confirmEmail
    Given Admin creates login request with Null body body for post forgotPasswordConfirmEmail
    When Admin calls "postforgotPasswordConfirmEmail" with "POST" http request
    Then Admin receives status code 400
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
  Scenario Outline: POST/resetPassword Check if admin able to resetPassword for special character in password and invalid emailid
    Given Admin creates login request with "<email>" and "<password>" for resetPassword
    When Admin calls "postresetPassword" with "POST" http request
    And Admin receives status code <statusCode>
    Examples:
      | email             | password        | statusCode |
      | team3@gmail.com   | $$$$$$$$$$$$$$$ |        400 |
      | team3@hotmail.com | ApiHackathon2@3 |        400 |
  Scenario: POST/resetPassword Check if admin able to resetPassword with invalid content type
    Given Admin creates login request with invalid content type for resetPassword
    When Admin calls "postforgotPasswordConfirmEmail" with "POST" http request
    Then Admin receives status code 415
  Scenario Outline: Check if admin able to resetPassword with invalid endpoint
    Given Admin creates login request with "<email>" and "<password>" for resetPassword
    When Admin calls "postresetPasswordWrongEndpoint" with "POST" http request
    And Admin receives status code <statusCode>
    Examples:
      | email           | password        | statusCode |
      | team3@gmail.com | ApiHackathon2@3 |        404 |
  Scenario Outline: Check if admin able to resetPassword with invalid method
    Given Admin creates login request with "<email>" and "<password>" for resetPassword
    When Admin calls "postresetPassword" with "GET" http request
    And Admin receives status code <statusCode>
    Examples:
      | email           | password        | statusCode |
      | team3@gmail.com | ApiHackathon2@3 |        405 |