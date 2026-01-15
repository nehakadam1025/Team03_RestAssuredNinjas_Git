Feature: User Module - Admin Management
<<<<<<< HEAD

@POST 
  Scenario: Create admin with valid endpoint and request body
  	Given Admin creates POST Request for the LMS API endpoint
  	When Admin calls "createUser" with "POST" http request for user admin
  	Then Admin receives 201 Created Status with response body

Scenario: Check if admin able to get all user module with valid endpoint 
=======
   
  @POST 
  Scenario: Create admin with valid endpoint and request body
  Given Admin creates POST Request for the LMS API endpoint
  When Admin calls "createUser" with "POST" http request for user admin
  Then Admin receives 201 Created Status with response body

  Scenario: Check if admin able to get all user module with valid endpoint 
>>>>>>> d674617cbacd2fd6df3b95e4205a6ac271ddc3a7
    Given Admin creates GET Request for the user module
    When Admin calls "getallUsers" with "GET" http request for User module
    Then Admin receives 200 Status for user module
    
<<<<<<< HEAD
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
    
  Scenario: Delete user
  	Given Admin creates DELETE Request for the user module
  	When Admin calls "DeleteUserAPI" with "DELETE" http request for deleting user
  	Then Admin receives 200 Status for delete

=======
  Scenario: Check if admin able to get user by id with valid endpoint 
    Given Admin creates GET Request in the user module
    When Admin calls "getbyid" with "GET" http request in User module
    Then Admin receives 200 ok Status for user module 
>>>>>>> d674617cbacd2fd6df3b95e4205a6ac271ddc3a7
