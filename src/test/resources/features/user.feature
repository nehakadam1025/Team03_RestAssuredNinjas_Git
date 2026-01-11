Feature: User Login Controller

  #Scenario: Check if admin able to generate token with valid credential
  #Given Admin create login request with valid credentials
  #When Admin calls POST method with valid endpoint
  #Then Admin receives 200 created with auto generated token
  Scenario Outline: User login with valid and invalid credentials
    Given Admin creates login request with "<email>" and "<password>"
    When Admin calls "postLoginApi" with "POST" http request
    And Admin receives status code <statusCode>

    #Then "token" in response body is "getLogoutlms"
    Examples: 
      | email           | password        | statusCode |
      | team3@gmail.com | ApiHackathon2@3 |        200 |
      |                 | ApiHackathon2@3 |        400 |
      | wrong@gmail.com | ApiHackathon2@3 |        400 |

  #Examples:
  #| email           | password        | statusCode |
  #| team3@gmail.com | ApiHackathon2@3 |        200 |
  #|                 | ApiHackathon2@3 |        400 |
  #| wrong@gmail.com | ApiHackathon2@3 |        401 |
  
  Scenario: Check if admin able to logout from LMS Application
    Given admin sets authorization to bearer Token with creates request
    When Admin calls "getLogoutlms" with "GET" http request
    Then Admin recieved 200 status code
