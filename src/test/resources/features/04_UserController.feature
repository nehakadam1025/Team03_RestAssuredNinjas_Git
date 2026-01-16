Feature: User Module - Admin Management
   
  @POST 
  Scenario: Create admin with valid endpoint and request body
  	Given Admin creates POST Request for the LMS API endpoint
  	When Admin calls "createUser" with "POST" http request for user admin
  	Then Admin receives 201 Created Status with response body

  Scenario: Check if admin able to get all user module with valid endpoint 
    Given Admin creates GET Request for the user module
    When Admin calls "getallUsers" with "GET" http request for User module
    Then Admin receives 200 Status for user module
    
  Scenario: Check if admin able to get user by id with valid endpoint 
    Given Admin creates GET Request in the user module
    When Admin calls "getbyid" with "GET" http request in User module
    Then Admin receives 200 ok Status in user module 
    
  Scenario: Check if admin able to get user by roles with valid endpoint 
    Given Admin creates GET Request into the user by role module
    When Admin calls "getbyroles" with "GET" http request with role "R01"
    Then Admin receives 200 ok Status from user module   
      
  Scenario: Update user using previously created user ID
    Given Admin creates PUT Request for the user module
    When Admin calls "UpdateUserAPI" with "PUT" http request for updating user
    Then Admin receives 200 OK Status for update
    
   #Scenario: Check if admin able to update admin role with valid admin Id
    #Given Admin creates PUT Request for admin role update
    #When Admin calls "UpdateUserAPI" with "PUT" http request for "valid ID" in module
    #Then Admin receives 200 OK Status for update
       
  Scenario: Delete user
  	Given Admin creates DELETE Request for the user module
  	When Admin calls "DeleteUserAPI" with "DELETE" http request for deleting user
  	Then Admin receives 200 Status for delete
  	
  @NegativeTest
  Scenario: Check if admin is unable to delete a user with an invalid admin ID
    Given Admin creates DELETE Request for the user module
    When Admin calls "DeleteUserAPI" with "DELETE" http request for a non-existent ID "999999"
    Then Admin receives 404 Not Found Status with message "Not Found"
  	
  @NegativeTest
  Scenario Outline: Validate admin creation with invalid data
    Given Admin creates POST Request with "<testType>" for the user module
    When Admin calls "createUser" with "POST" http request for user admin
    Then Admin receives <statusCode> Bad Request Status with message "<errorMessage>"

    Examples:
      | testType              | statusCode | errorMessage                 |
      | existing phone        | 400        | already exists  |
      | missing mandatory     | 400        | is required|	

  @NegativeTest
  Scenario: Verify 404 error when retrieving a user with a non-existent ID
    Given Admin creates GET Request in the user module
    When Admin calls "getbyid" with "GET" http request for a non-existent ID "INVALID_999"
    Then Admin receives 404 Not Found Status with message "User not found with id : INVALID_999"
    
    
  Scenario Outline: Validate Admin update with invalid ID and missing fields
    Given Admin creates PUT Request with "<testType>" for update
    When Admin calls "UpdateUserAPI" with "PUT" http request for "<idType>"
    Then Admin receives <statusCode> error status with message "<errorMessage>"

    Examples:
      | testType              | idType      | statusCode | errorMessage      |
      | valid payload         | invalid ID  | 404        | Not Found         |
      | missing mandatory     | valid ID    | 400        | required  		 |