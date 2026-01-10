Feature: User Login Controller

  Scenario: Check if admin able to generate token with valid credential
    Given Admin create login request with valid credentials
    When Admin calls POST method with valid endpoint
    Then Admin receives 200 created with auto generated token