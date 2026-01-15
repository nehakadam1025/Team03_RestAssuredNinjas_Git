Feature: User Module - Admin Management
   
  @POST 
  Scenario: Create admin with valid endpoint and request body
  	Given Admin creates POST Request for the LMS API endpoint
  	When Admin calls "createUser" with "POST" http request for user admin
  	Then Admin receives 201 Created Status with response body

  Scenario: Check if admin able to get all user module with valid endpoint 
    Given Admin creates GET Request for the user module
    When Admin calls "getallUsers" with "GET" http request for user admin
    Then Admin receives 200 Created Status with response body
    
  Scenario: Check if admin able to get user by id with valid endpoint 
    Given Admin creates GET Request in the user module
    When Admin calls "getbyid" with "GET" http request for user admin id
    Then Admin receives 200 Created Status with response body
    
  Scenario: Check if admin able to get user by roles with valid endpoint 
    Given Admin creates GET Request into the user by role module
    When Admin calls "getbyroles" with "GET" http request with role "R01"
    Then Admin receives 200 Created Status with response body
      
  Scenario: Update user using previously created user ID
    Given Admin creates PUT Request for the user module
    When Admin calls "UpdateUserAPI" with "PUT" http request for user admin
    Then Admin receives 200 Created Status with response body
    
  Scenario: Delete user
  	Given Admin creates DELETE Request for the user module
  	When Admin calls "DeleteUserAPI" with "DELETE" http request for user admin
  	Then Admin receives 200 Created Status with response body

