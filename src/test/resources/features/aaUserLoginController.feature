#Feature: User Login Controller

  Scenario Outline: POST /login Check if admin able to generate token with valid and invalid credential using Excel row
  Given Admin performs login test for Excel row "<rowNumber>"
  When Admin calls "postLoginApi" with "POST" http request
  Then Admin receives status code <statusCode>

Examples:
  | rowNumber              | statusCode |
  | postvalid              | 200        |
  | postSpeCharacterEmail  | 400        |
  | postSpeCharacterPwd    | 400        |
  | postinvalidEmailformat | 400        |
  | postnumbersinEmail     | 400        |
  | postnumbersinPwd       | 400        |
  | nullEmail              | 400        |
  | nullPwd                | 400        |
  
  Scenario Outline: POST/login Check if admin able to generate token with invalid base URL
    Given Admin creates login request with "email" and "password" invalid baseurl
    When Admin calls "postLoginApi" with "POST" http request
    Then Admin receives status code 404
    
    
     Scenario: Post:/login/forgotpassword/confirmEmail Check if admin able to generate token with invalid content type
    Given Admin creates login request with invalid content type for forgotPassword
    When Admin calls "postforgotPasswordConfirmEmail" with "POST" http request
    Then Admin receives status code 415
     

 Scenario: POST/login Check if admin able to generate token with invalid content type
    Given Admin creates login request with invalid content type
    When Admin calls "postLoginApi" with "POST" http request
    Then Admin receives status code 415

 Scenario: POST/login Check if admin able to generate token with invalid endpoint
  Given Admin performs login test for Excel row "<rowNumber>" for invalid endpoint
  When Admin calls "postLoginWrongEndPoint" with "POST" http request
  Then Admin receives status code <statusCode>

 Examples:
  | rowNumber        | statusCode |
  | invalidEndpoint  | 401        |
  
  Scenario: POST/login Check if admin able to generate token with invalid method
    Given Admin creates login request with invalid method
    When Admin calls "postLoginApi" with "GET" http request
    Then Admin receives status code 405
 
      
  Scenario: POST/login Check if admin able to generate token with Null body
    Given Admin creates login request with Null body
    When Admin calls "postLoginApi" with "POST" http request
    Then Admin receives status code 400
  
  
  

Scenario Outline: POST /login/forgotpassword/confirmEmail using Excel data
  Given Admin performs forgot password confirm email test for Excel row "<rowNumber>"
  When Admin calls "postforgotPasswordConfirmEmail" with "POST" http request
  Then Admin receives status code <statusCode>

Examples:
  | rowNumber            | statusCode |
  | validForgotPwd       | 201        |
  | SpecialCharForgotPwd | 400        |
  | invaliEmailForgotPwd | 400        |
  
  Scenario Outline: POST /login/forgotpassword/confirmEmail Check if admin able to generate token with invalid endpoint
    Given Admin performs forgot password confirm email test for Excel row "<rowNumber>"
    When Admin calls "postforgotPasswordConfirmEmailWrongEndpoint" with "POST" http request
    And Admin receives status code <statusCode>
    Examples:
      | rowNumber               | statusCode |
      | invalidendptforgotpwd   |     404    |
      
   Scenario: Check if admin able to generate token with Null body in POST/login/forgotpassword/confirmEmail
    Given Admin creates login request with Null body body for post forgotPasswordConfirmEmail
    When Admin calls "postforgotPasswordConfirmEmail" with "POST" http request
    Then Admin receives status code 400    
      
 
    
    
  Scenario Outline: POST /resetPassword Check if admin able to generate token with valid and invalid credential using Excel row
  Given Admin performs resetPassword test "<rowNumber>"
  When Admin calls "postresetPassword" with "POST" http request
  Then Admin receives status code <statusCode>

Examples:
   | rowNumber              | statusCode |
   | postresetPwd           | 401        |
   | posrRePwdspecialChaP   | 401        |
   | posrRePwdwrongEmail    | 401        |
 # | postinvalidEmailformat | 400        |
 # | postnumbersinEmail     | 400        |
 # | postnumbersinPwd       | 400        |
 # | nullEmail              | 400        |
 # | nullPwd                | 400        | 
 
 
 
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
 # Scenario: Check if admin able to logout to LMS Application
  #  Given Check if admin able to logout to LMS Application
   # When Admin calls "getLogoutlms" with "GET" http request
   # Then Admin receives status code 200
  