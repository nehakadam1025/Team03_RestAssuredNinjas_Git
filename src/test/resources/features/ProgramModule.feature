Feature: Program Controller Module
  This feature tests POST, GET, PUT, and DELETE requests for the Program Controller Module with positive and negative scenarios.

  Background:
    Given Admin has access to the LMS API

  # =========================
  # POST REQUEST – Create Program
  # =========================

  Scenario: Create program with valid endpoint and request body with authorization
    Given Admin sets Authorization
    When Admin sends POST request to create program with valid request body
    Then Admin receives 201 Created status with response body

  Scenario: Create program with valid endpoint and request body without authorization
    Given Admin sets Authorization to No Auth
    When Admin sends POST request to create program with valid request body
    Then Admin receives 401 Unauthorized status

  Scenario: Create program with program description length between 4 and 25 characters
    Given Admin sets Authorization
    When Admin sends POST request with valid description length
    Then Admin receives 201 Created status with response body

  Scenario: Create program with program name length between 4 and 25 characters
    Given Admin sets Authorization
    When Admin sends POST request with valid program name length
    Then Admin receives 201 Created status with response body

  Scenario: Create program with invalid endpoint
    Given Admin sets Authorization
    When Admin sends POST request with invalid endpoint
    Then Admin receives 404 Not Found status with error message

  Scenario: Create program with already existing program name
    Given Admin sets Authorization
    When Admin sends POST request with duplicate program name
    Then Admin receives 400 Bad Request status with error message

  Scenario: Create program with invalid HTTP method
    Given Admin sets Authorization
    When Admin sends request with invalid method
    Then Admin receives 405 Method Not Allowed status

  Scenario: Create program with invalid request body
    Given Admin sets Authorization
    When Admin sends POST request with invalid request body
    Then Admin receives 400 Bad Request status

  Scenario: Create program with missing mandatory fields
    Given Admin sets Authorization
    When Admin sends POST request with missing mandatory fields
    Then Admin receives 400 Bad Request status

  Scenario: Create program with missing additional field
    Given Admin sets Authorization
    When Admin sends POST request missing additional field
    Then Admin receives 200 OK status

  # =========================
  # GET REQUEST – All Programs
  # =========================

  Scenario: Retrieve all programs with valid endpoint
    Given Admin sets Authorization
    When Admin sends GET request to retrieve all programs
    Then Admin receives 200 OK status with response body

  Scenario: Retrieve all programs with invalid endpoint
    Given Admin sets Authorization
    When Admin sends GET request with invalid endpoint
    Then Admin receives 404 Not Found status with error message

  Scenario: Retrieve all programs with invalid method
    Given Admin sets Authorization
    When Admin sends request with invalid method
    Then Admin receives 405 Method Not Allowed status

  Scenario: Retrieve all programs without authorization
    Given Admin sets Authorization to No Auth
    When Admin sends GET request to retrieve all programs
    Then Admin receives 401 Unauthorized status

  # =========================
  # GET REQUEST – Program by ID
  # =========================

  Scenario: Retrieve program with valid program ID
    Given Admin sets Authorization
    When Admin sends GET request with valid program ID
    Then Admin receives 200 OK status with response body

  Scenario: Retrieve program with invalid program ID
    Given Admin sets Authorization
    When Admin sends GET request with invalid program ID
    Then Admin receives 404 Not Found status with error message

  Scenario: Retrieve program with invalid base URI
    Given Admin sets Authorization
    When Admin sends GET request with invalid base URI
    Then Admin receives 404 Not Found status with error message

  Scenario: Retrieve program without authorization
    Given Admin sets Authorization to No Auth
    When Admin sends GET request with valid program ID
    Then Admin receives 401 Unauthorized status

  Scenario: Retrieve program with invalid endpoint
    Given Admin sets Authorization
    When Admin sends GET request with invalid endpoint
    Then Admin receives 404 Not Found status with error message

  # =========================
  # GET REQUEST – All Programs With Admins
  # =========================

  Scenario: Retrieve all programs with admins using valid endpoint
    Given Admin sets Authorization
    When Admin sends GET request to retrieve all programs with admins
    Then Admin receives 200 OK status with response body

  Scenario: Retrieve all programs with admins using invalid endpoint
    Given Admin sets Authorization
    When Admin sends GET request with invalid endpoint
    Then Admin receives 404 Not Found status with error message

  Scenario: Retrieve all programs with admins using invalid method
    Given Admin sets Authorization
    When Admin sends request with invalid method
    Then Admin receives 405 Method Not Allowed status

  Scenario: Retrieve all programs with admins without authorization
    Given Admin sets Authorization to No Auth
    When Admin sends GET request to retrieve all programs with admins
    Then Admin receives 401 Unauthorized status

  # =========================
  # PUT REQUEST – Update Program by ID
  # =========================

  Scenario: Update program with valid program ID and valid request body
    Given Admin sets Authorization
    When Admin sends PUT request with valid program ID and request body
    Then Admin receives 200 OK status with updated response body

  Scenario: Update program with invalid program ID
    Given Admin sets Authorization
    When Admin sends PUT request with invalid program ID
    Then Admin receives 404 Not Found status with error message

  Scenario: Update program with invalid request body
    Given Admin sets Authorization
    When Admin sends PUT request with invalid request body
    Then Admin receives 400 Bad Request status

  Scenario: Update program without request body
    Given Admin sets Authorization
    When Admin sends PUT request without request body
    Then Admin receives 400 Bad Request status

  Scenario: Update program with invalid base URI
    Given Admin sets Authorization
    When Admin sends PUT request with invalid base URI
    Then Admin receives 404 Not Found status

  Scenario: Update program with invalid method
    Given Admin sets Authorization
    When Admin sends request with invalid method
    Then Admin receives 405 Method Not Allowed status

  Scenario: Update program without authorization
    Given Admin sets Authorization to No Auth
    When Admin sends PUT request with valid program ID
    Then Admin receives 401 Unauthorized status

  # =========================
  # PUT REQUEST – Update Program by Name
  # =========================

  Scenario: Update program with valid program name
    Given Admin sets Authorization
    When Admin sends PUT request with valid program name
    Then Admin receives 200 OK status with updated response body

  Scenario: Update program with invalid program name
    Given Admin sets Authorization
    When Admin sends PUT request with invalid program name
    Then Admin receives 404 Not Found status

  Scenario: Update program with missing mandatory fields
    Given Admin sets Authorization
    When Admin sends PUT request with missing mandatory fields
    Then Admin receives 400 Bad Request status

  Scenario: Update program with invalid values in request body
    Given Admin sets Authorization
    When Admin sends PUT request with invalid values
    Then Admin receives 400 Bad Request status

  Scenario: Update program status successfully
    Given Admin sets Authorization
    When Admin sends PUT request to update program status
    Then Admin receives 200 OK status with updated response body

  Scenario: Update program by name without authorization
    Given Admin sets Authorization to No Auth
    When Admin sends PUT request with valid program name
    Then Admin receives 401 Unauthorized status

  # =========================
  # DELETE REQUEST – By Program Name
  # =========================

  Scenario: Delete program with valid program name
    Given Admin sets Authorization
    When Admin sends DELETE request with valid program name
    Then Admin receives 200 OK status with success message

  Scenario: Delete program with invalid program name
    Given Admin sets Authorization
    When Admin sends DELETE request with invalid program name
    Then Admin receives 404 Not Found status with error message

  Scenario: Delete program by name without authorization
    Given Admin sets Authorization to No Auth
    When Admin sends DELETE request with valid program name
    Then Admin receives 401 Unauthorized status

  # =========================
  # DELETE REQUEST – By Program ID
  # =========================

  Scenario: Delete program with valid program ID
    Given Admin sets Authorization
    When Admin sends DELETE request with valid program ID
    Then Admin receives 200 OK status with success message

  Scenario: Delete program with invalid program ID
    Given Admin sets Authorization
    When Admin sends DELETE request with invalid program ID
    Then Admin receives 404 Not Found status with error message

  Scenario: Delete program by ID without authorization
    Given Admin sets Authorization to No Auth
    When Admin sends DELETE request with valid program ID
    Then Admin receives 401 Unauthorized status
