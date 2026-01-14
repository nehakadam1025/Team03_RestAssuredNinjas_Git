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
    Then Admin receives 200 ok Status for user module 