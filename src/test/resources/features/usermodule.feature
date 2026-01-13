Feature: User Module - Admin Management

    
 @POST 
  Scenario: Create admin with valid endpoint and request body
  Given Admin creates POST Request for the LMS API endpoint
  When Admin calls "createUser" with "POST" http request for user admin
  Then Admin receives 201 Created Status with response body

 