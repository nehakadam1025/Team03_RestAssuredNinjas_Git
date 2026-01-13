Feature: User Module - Admin Management


  @POST 
  Scenario: Create admin with valid endpoint and request body
  Given Admin creates POST Request for the LMS API endpoint
  When Admin calls "createUser" with "POST" http request for user admin
  Then Admin receives 201 Created Status with response body

  #@POST @Negative
  #Scenario: Create admin with existing phone number
    #Given Admin creates POST Request for the LMS API endpoint
    #When Admin sends HTTPS Request with existing phone number
    #Then Admin receives 400 Bad Request Status with message and boolean success details
#
  #@POST @Negative
  #Scenario: Create admin with missing mandatory fields
    #Given Admin creates POST Request for the LMS API endpoint
    #When Admin sends HTTPS Request with missing mandatory fields
    #Then Admin receives 400 Bad Request Status with message and boolean success details
#
  #@GET 
  #Scenario: Retrieve all admins with valid LMS API
    #Given Admin creates GET Request for the LMS API All admin endpoint
    #When Admin sends HTTPS Request
    #Then Admin receives 200 OK Status with response body
#
  #@GET 
  #Scenario: Retrieve admin by valid admin ID
    #Given Admin creates GET Request for the LMS API endpoint with valid admin ID
    #When Admin sends HTTPS Request
    #Then Admin receives 200 OK Status with response body
#
  #@GET @Negative
  #Scenario: Retrieve admin with invalid admin ID
    #Given Admin creates GET Request for the LMS API endpoint with invalid admin ID
    #When Admin sends HTTPS Request
    #Then Admin receives 404 Not Found Status with message and boolean success details
#
  #@GET
  #Scenario: Retrieve all staff with valid LMS API
    #Given Admin creates GET Request for the LMS API All Staff endpoint
    #When Admin sends HTTPS Request
    #Then Admin receives 200 OK Status with response body
#
  #@GET
  #Scenario: Retrieve admin with roles
    #Given Admin creates GET Request for the LMS API admin Roles endpoint
    #When Admin sends HTTPS Request
    #Then Admin receives 200 OK Status with response body
#
  #@PUT 
  #Scenario: Update admin with valid admin ID and request body
    #Given Admin creates PUT Request for the LMS API endpoint with valid admin ID
    #When Admin sends HTTPS Request with mandatory and additional fields
    #Then Admin receives 200 OK Status with response body
#
  #@PUT @Negative
  #Scenario: Update admin with invalid admin ID
    #Given Admin creates PUT Request for the LMS API endpoint with invalid admin ID
    #When Admin sends HTTPS Request with mandatory and additional fields
    #Then Admin receives 404 Not Found Status with message and boolean success details
#
  #@PUT @Negative
  #Scenario: Update admin with missing mandatory fields
    #Given Admin creates PUT Request for the LMS API endpoint with valid admin ID
    #When Admin sends HTTPS Request with missing mandatory fields
    #Then Admin receives 400 Bad Request Status with message and boolean success details
#
  #@PUT
  #Scenario: Update admin role status with valid admin ID
    #Given Admin creates PUT Request for updating admin role status
    #When Admin sends HTTPS Request with Role ID and Role status
    #Then Admin receives 200 OK Status with response message
#
  #@PUT @Negative
  #Scenario: Update admin role status with invalid admin ID
    #Given Admin creates PUT Request for updating admin role status with invalid ID
    #When Admin sends HTTPS Request with Role ID and Role status
    #Then Admin receives 404 Not Found Status with message and boolean success details
#
  #@PUT
  #Scenario: Assign admin to Program/Batch with valid admin ID
    #Given Admin creates PUT Request for assigning admin to program batch
    #When Admin sends HTTPS Request with program ID batch ID role ID admin ID and status
    #Then Admin receives 200 OK Status with success message
#
  #@PUT @Negative
  #Scenario: Assign admin to Program/Batch with invalid admin ID
    #Given Admin creates PUT Request for assigning admin to program batch with invalid ID
    #When Admin sends HTTPS Request with program ID batch ID role ID admin ID and status
    #Then Admin receives 404 Not Found Status with message and boolean success details
#
  #@DELETE 
  #Scenario: Delete admin with valid admin ID
    #Given Admin creates DELETE Request for the LMS API endpoint with valid admin ID
    #When Admin sends HTTPS Request
    #Then Admin receives 200 OK status with message
#
  #@DELETE @Negative
  #Scenario: Delete admin with invalid admin ID
    #Given Admin creates DELETE Request for the LMS API endpoint with invalid admin ID
    #When Admin sends HTTPS Request
    #Then Admin receives 404 Not Found Status with message and boolean success details