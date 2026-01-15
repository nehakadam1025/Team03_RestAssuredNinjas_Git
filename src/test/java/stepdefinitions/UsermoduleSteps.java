/*
 * package stepdefinitions;
 * 
 * 
 * import static io.restassured.RestAssured.given;
 * 
 * import java.io.IOException;
 * 
 * import io.cucumber.java.en.Given; import io.cucumber.java.en.Then; import
 * io.cucumber.java.en.When; import io.restassured.response.Response; import
 * io.restassured.specification.RequestSpecification; //import
 * pojo.CreatedUserData; import pojo.CreatedUserData; import pojo.LoginTempData;
 * import utils.ApiResources; import utils.TestDataBuild; import utils.utility;
 * 
 * 
 * public class UsermoduleSteps extends utility {
 * 
 * private RequestSpecification req; private Response response;
 * 
 * 
 * TestDataBuild data1 = new TestDataBuild();
 * 
 * @Given("Admin creates POST Request for the LMS API endpoint") public void
 * admin_creates_post_request_for_the_lms_api_endpoint() throws IOException {
 * 
 * 
 * 
 * 
 * String token = LoginTempData.getToken();
 * 
 * // ✅ ADD THESE DEBUG LOGS
 * System.out.println("\n========== CREATE USER REQUEST ==========");
 * System.out.println("🔑 Token retrieved: " + token);
 * System.out.println("🔑 Token is NULL? " + (token == null));
 * System.out.println("🔑 Token is EMPTY? " + (token != null &&
 * token.isEmpty())); System.out.println("🔑 Token length: " + (token != null ?
 * token.length() : "N/A"));
 * 
 * if (token == null || token.isEmpty()) {
 * System.out.println("❌ CRITICAL ERROR: Token is NULL or EMPTY!");
 * System.out.println("❌ This means login step did not save the token properly!"
 * ); throw new RuntimeException("Token not available. Check login step."); }
 * 
 * String payload = data1.createUserPayload();
 * System.out.println("=========================================\n");
 * 
 * req = given() .spec(requestspecification()) .header("Authorization",
 * "Bearer " + token)
 * 
 * .body(data1.createUserPayload());
 * 
 * 
 * }
 * 
 * 
 * @When("Admin calls {string} with {string} http request for user admin")
 * public void admin_calls_with_https_request_for_user_admin(String resource,
 * String method) {
 * 
 * ApiResources resourceAPI = ApiResources.valueOf(resource);
 * 
 * if (method.equalsIgnoreCase("POST")) { response =
 * req.when().post(resourceAPI.getResorce()); }
 * 
 * if (method.equalsIgnoreCase("GET")) { response =
 * req.when().get(resourceAPI.getResorce()); } else if
 * (method.equalsIgnoreCase("POST")) { response =
 * req.when().post(resourceAPI.getResorce()); } else if
 * (method.equalsIgnoreCase("PUT")) { response =
 * req.when().put(resourceAPI.getResorce()); }
 * 
 * }
 * 
 * @Then("Admin receives {int} Created Status with response body") public void
 * admin_receives_created_status_with_response_body(Integer expectedStatus) {
 * 
 * 
 * int actualStatus = response.getStatusCode();
 * System.out.println("Status Code: " + actualStatus);
 * System.out.println("Response: " + response.asString());
 * 
 * assert actualStatus == expectedStatus : "Expected " + expectedStatus +
 * " but got " + actualStatus;
 * 
 * if (actualStatus == 201 || actualStatus == 200) { String userId =
 * response.jsonPath().getString("user.userId"); if (userId != null) {
 * CreatedUserData.setUserId(userId);
 * System.out.println("✅ Saved created user ID: " + userId); } else {
 * System.out.println("⚠️ Warning: userId not found in response"); } } }
 * 
 * 
 * 
 * 
 * @Given("Admin creates GET Request for the user module") public void
 * admin_creates_get_request_for_the_user_module() throws IOException {
 * 
 * String token = LoginTempData.getToken(); // token from login feature
 * 
 * req = given() .spec(requestspecification()) .header("Authorization",
 * "Bearer " + token);
 * 
 * }
 * 
 * @When("Admin calls {string} with {string} http request for User module")
 * public void admin_calls_with_http_request_for_user_module(String resource,
 * String method) {
 * 
 * ApiResources resourceAPI = ApiResources.valueOf(resource);
 * 
 * if (method.equalsIgnoreCase("GET")) { response =
 * req.when().get(resourceAPI.getResorce()); }
 * 
 * }
 * 
 * @Then("Admin receives {int} Status for user module") public void
 * admin_receives_status_for_user_module(Integer expectedStatus) {
 * 
 * 
 * int actualStatus = response.getStatusCode();
 * System.out.println("Status Code: " + actualStatus);
 * System.out.println("Response: " + response.asString());
 * 
 * assert actualStatus == expectedStatus : "Expected " + expectedStatus +
 * " but got " + actualStatus; }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @Given("Admin creates GET Request for the user module") public void
 * admin_creates_get_request_for_the_user_module() throws IOException {
 * 
 * 
 * String token = LoginTempData.getToken(); // token from login feature
 * 
 * req = given() .spec(requestspecification()) .header("Authorization",
 * "Bearer " + token);
 * 
 * }
 * 
 * @When("Admin calls {string} with {string} http request in User module")
 * public void admin_calls_with_http_request_in_user_module(String resource,
 * String method) {
 * 
 * ApiResources resourceAPI = ApiResources.valueOf(resource);
 * 
 * if (method.equalsIgnoreCase("GET")) { response =
 * req.when().get(resourceAPI.getResorce());
 * 
 * } ApiResources resourceAPI = ApiResources.valueOf(resource);
 * 
 * System.out.println("🔍 DEBUG - GET by ID:"); System.out.println("Resource: "
 * + resource); System.out.println("Base URL: " + resourceAPI.getResorce());
 * 
 * if (resource.equals("getbyid")) { // Use the EXISTING userId from
 * CreatedUserData (set by POST) String userId = CreatedUserData.getUserId();
 * 
 * System.out.println("Using userId from POST: " + userId);
 * 
 * if (userId != null && !userId.isEmpty()) { // Get the specific user created
 * in POST scenario response = req.when().get(resourceAPI.getResorce() + "/" +
 * userId); System.out.println("GET specific user response: " +
 * response.asString()); } else {
 * System.out.println("⚠️ No userId available from POST - cannot GET by ID"); }
 * } else { response = req.when().get(resourceAPI.getResorce()); } }
 * 
 * @Then("Admin receives {int} ok Status in user module") public void
 * admin_receives_ok_status_in_user_module(Integer expectedStatus) {
 * 
 * 
 * int actualStatus = response.getStatusCode();
 * System.out.println("Status Code: " + actualStatus);
 * System.out.println("Response: " + response.asString());
 * 
 * assert actualStatus == expectedStatus : "Expected " + expectedStatus +
 * " but got " + actualStatus; if (actualStatus == 201) { String userId =
 * response.jsonPath().getString("userId"); // Adjust based on your response
 * CreatedUserData.setUserId(userId);
 * System.out.println("Saved created user ID: " + userId); } int actualStatus =
 * response.getStatusCode(); System.out.println("Status Code: " + actualStatus);
 * System.out.println("Response: " + response.asString());
 * 
 * assert actualStatus == expectedStatus : "Expected " + expectedStatus +
 * " but got " + actualStatus;
 * 
 * if (actualStatus == 201 || actualStatus == 200) { // ✅ FIXED: Extract userId
 * from nested "user" object String userId =
 * response.jsonPath().getString("user.userId"); // ← CHANGED
 * 
 * if (userId != null) { CreatedUserData.setUserId(userId);
 * System.out.println("✅ Saved created user ID: " + userId); } else {
 * System.out.println("⚠️ Warning: userId not found in response");
 * System.out.println("Response structure: " + response.asString()); } } }
 * 
 * 
 * @Given("Admin creates GET Request into the user by role module") public void
 * admin_creates_get_request_into_the_user_by_role_module() throws IOException {
 * 
 * String token = LoginTempData.getToken();
 * 
 * System.out.println("🔍 GET BY ROLE REQUEST DEBUG:");
 * System.out.println("Token: " + (token != null ? "Present" : "NULL"));
 * 
 * req = given() .spec(requestspecification()) .header("Authorization",
 * "Bearer " + token);
 * 
 * }
 * 
 * @When("Admin calls {string} with {string} http request with role {string}")
 * public void admin_calls_with_http_request_with_role(String resource, String
 * method, String role) {
 * 
 * ApiResources resourceAPI = ApiResources.valueOf(resource);
 * 
 * System.out.println("🔍 GET BY ROLE DEBUG INFO:");
 * System.out.println("Resource: " + resource); System.out.println("Base URL: "
 * + resourceAPI.getResorce()); System.out.println("Role: " + role);
 * System.out.println("Full URL: " + resourceAPI.getResorce() + "/" + role);
 * 
 * if (method.equalsIgnoreCase("GET")) { response =
 * req.when().get(resourceAPI.getResorce() + "/" + role); }
 * 
 * }
 * 
 * @Then("Admin receives {int} ok Status from user module") public void
 * admin_receives_ok_status_from_user_module(Integer expectedStatus) {
 * 
 * int actualStatus = response.getStatusCode();
 * System.out.println("Status Code: " + actualStatus);
 * System.out.println("Response: " + response.asString());
 * 
 * assert actualStatus == expectedStatus : "Expected " + expectedStatus +
 * " but got " + actualStatus;
 * 
 * System.out.println("✅ Successfully retrieved users by role with status: " +
 * actualStatus); }
 * 
 * 
 * @Given("Admin creates PUT Request for the user module") public void
 * admin_creates_put_request_for_the_user_module() throws IOException {
 * 
 * String token = LoginTempData.getToken(); String updatePayload =
 * data1.updateUserPayload();
 * 
 * System.out.println("🔍 PUT REQUEST DEBUG:"); System.out.println("Token: " +
 * (token != null ? "Present" : "NULL"));
 * System.out.println("Payload being sent:"); System.out.println(updatePayload);
 * 
 * req = given() .spec(requestspecification()) .header("Authorization",
 * "Bearer " + token) .body(updatePayload); }
 * 
 * @When("Admin calls {string} with {string} http request for updating user")
 * public void admin_calls_with_http_request_for_updating_user(String resource,
 * String method) {
 * 
 * ApiResources resourceAPI = ApiResources.valueOf(resource); String userId =
 * CreatedUserData.getUserId(); // Get the created user's ID
 * 
 * System.out.println("🔍 DEBUG INFO:"); System.out.println("Resource: " +
 * resource); System.out.println("Base URL: " + resourceAPI.getResorce());
 * System.out.println("User ID: " + userId); System.out.println("Full URL: " +
 * resourceAPI.getResorce() + "/" + userId);
 * 
 * if (method.equalsIgnoreCase("PUT")) { response =
 * req.when().put(resourceAPI.getResorce() + "/" + userId); } }
 * 
 * @Then("Admin receives {int} OK Status for update") public void
 * admin_receives_ok_status_for_update(Integer expectedStatus) {
 * 
 * int actualStatus = response.getStatusCode();
 * System.out.println("Status Code: " + actualStatus);
 * System.out.println("Response: " + response.asString());
 * 
 * assert actualStatus == expectedStatus : "Expected " + expectedStatus +
 * " but got " + actualStatus; }
 * 
 * @Given("Admin creates DELETE Request for the user module") public void
 * admin_creates_delete_request_for_the_user_module() throws IOException {
 * 
 * String token = LoginTempData.getToken();
 * 
 * System.out.println("🔍 DELETE REQUEST DEBUG:"); System.out.println("Token: "
 * + (token != null ? "Present" : "NULL"));
 * System.out.println("User ID to delete: " + CreatedUserData.getUserId());
 * 
 * req = given() .spec(requestspecification()) .header("Authorization",
 * "Bearer " + token); }
 * 
 * @When("Admin calls {string} with {string} http request for deleting user")
 * public void admin_calls_with_http_request_for_deleting_user(String resource,
 * String method) {
 * 
 * ApiResources resourceAPI = ApiResources.valueOf(resource); String userId =
 * CreatedUserData.getUserId(); // Get the created user's ID
 * 
 * System.out.println("🔍 DELETE DEBUG INFO:"); System.out.println("Resource: "
 * + resource); System.out.println("Base URL: " + resourceAPI.getResorce());
 * System.out.println("User ID: " + userId); System.out.println("Full URL: " +
 * resourceAPI.getResorce() + "/" + userId);
 * 
 * if (method.equalsIgnoreCase("DELETE")) { response =
 * req.when().delete(resourceAPI.getResorce() + "/" + userId); } }
 * 
 * @Then("Admin receives {int} Status for delete") public void
 * admin_receives_status_for_delete(Integer expectedStatus) {
 * 
 * int actualStatus = response.getStatusCode();
 * System.out.println("Status Code: " + actualStatus);
 * System.out.println("Response: " + response.asString());
 * 
 * assert actualStatus == expectedStatus : "Expected " + expectedStatus +
 * " but got " + actualStatus;
 * 
 * System.out.println("✅ User deleted successfully with status: " +
 * actualStatus); } }
 * 
 * 
 * 
 * 
 * 
 */
package stepdefinitions;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import pojo.CreatedUserData;
import pojo.CreatedUserData;
import pojo.LoginTempData;
import utils.ApiResources;
import utils.TestDataBuild;
import utils.utility;

public class UsermoduleSteps extends utility {
	
	private RequestSpecification req3;
    private Response response;
	

    TestDataBuild data1 = new TestDataBuild();

	@Given("Admin creates POST Request for the LMS API endpoint")
	public void admin_creates_post_request_for_the_lms_api_endpoint() throws IOException {
		  

<<<<<<< HEAD
		/*String token = LoginTempData.getToken();

	    req = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .body(data1.createUserPayload());*/
	    
		String token = LoginTempData.getToken();
	    
	    // ✅ ADD THESE DEBUG LOGS
	    System.out.println("\n========== CREATE USER REQUEST ==========");
	    System.out.println("🔑 Token retrieved: " + token);
	    System.out.println("🔑 Token is NULL? " + (token == null));
	    System.out.println("🔑 Token is EMPTY? " + (token != null && token.isEmpty()));
	    System.out.println("🔑 Token length: " + (token != null ? token.length() : "N/A"));
	    
	    if (token == null || token.isEmpty()) {
	        System.out.println("❌ CRITICAL ERROR: Token is NULL or EMPTY!");
	        System.out.println("❌ This means login step did not save the token properly!");
	        throw new RuntimeException("Token not available. Check login step.");
	    }
	    
	    String payload = data1.createUserPayload();
	    System.out.println("=========================================\n");

	    req = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .body(payload);
=======
//		  String token = LoginTempData.getToken(); // token from login feature
//
//	        req = given()
//	                .spec(requestspecification())
//	                .header("Authorization", "Bearer " + token).body(data.createUserPayload());
		String token = LoginTempData.getToken(); // token from login feature

        req3 = given()
        		.spec(requestspecification())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
	            .body(data1.createUserPayload());  
>>>>>>> 669167619cf8f8514ad167e0f2a592bc4a18bf90
	}
	   
	
	@When("Admin calls {string} with {string} http request for user admin")
	public void admin_calls_with_http_request_for_user_admin(String resource, String method) {
		ApiResources resourceAPI = ApiResources.valueOf(resource);

<<<<<<< HEAD
	        if (method.equalsIgnoreCase("POST")) {
	            response = req.when().post(resourceAPI.getResorce());
	        }
	        
		 /*if (method.equalsIgnoreCase("GET")) {
	            response = req.when().get(resourceAPI.getResorce());
	        }
	        else if (method.equalsIgnoreCase("POST")) {
	            response = req.when().post(resourceAPI.getResorce());
	        }
	            else if (method.equalsIgnoreCase("PUT")) {
	            response = req.when().put(resourceAPI.getResorce());
	        }*/
	    
=======
		if (method.equalsIgnoreCase("POST")) {
            response = req3.when().post(resourceAPI.getResorce());
        } else if (method.equalsIgnoreCase("GET")) {
            response = req3.when().get(resourceAPI.getResorce());
        }
>>>>>>> 669167619cf8f8514ad167e0f2a592bc4a18bf90
	}
	
	@Then("Admin receives {int} Created Status with response body")
	public void admin_receives_created_status_with_response_body(Integer expectedStatus) {
		
		int actualStatus = response.getStatusCode();
        System.out.println("Status Code: " + actualStatus);
        System.out.println("Response: " + response.asString());

        assert actualStatus == expectedStatus :
                "Expected " + expectedStatus + " but got " + actualStatus;
        
        if (actualStatus == 201 || actualStatus == 200) {
        	 String userId = response.jsonPath().getString("user.userId"); 
            if (userId != null) {
                CreatedUserData.setUserId(userId);
                System.out.println("✅ Saved created user ID: " + userId);
            } else {
                System.out.println("⚠️ Warning: userId not found in response");
            }
        }
	}



	@Given("Admin creates GET Request for the user module")
	public void admin_creates_get_request_for_the_user_module() throws IOException {
		
		String token = LoginTempData.getToken(); // token from login feature

        req = given()
                .spec(requestspecification())
                .header("Authorization", "Bearer " + token);
	
	}
	
	@When("Admin calls {string} with {string} http request for User module")
	public void admin_calls_with_http_request_for_user_module(String resource, String method) {
		
		ApiResources resourceAPI = ApiResources.valueOf(resource);

        if (method.equalsIgnoreCase("GET")) {
            response = req.when().get(resourceAPI.getResorce());
        }
		
	}

	@Then("Admin receives {int} Status for user module")
	public void admin_receives_status_for_user_module(Integer expectedStatus) {
	  
		int actualStatus = response.getStatusCode();
        System.out.println("Status Code: " + actualStatus);
        System.out.println("Response: " + response.asString());

        assert actualStatus == expectedStatus :
                "Expected " + expectedStatus + " but got " + actualStatus;
	}
	
<<<<<<< HEAD
	
	@Given("Admin creates GET Request in the user module")
	public void admin_creates_get_request_in_the_user_module() throws IOException {
		
		String token = LoginTempData.getToken(); // token from login feature

        req = given()
                .spec(requestspecification())
                .header("Authorization", "Bearer " + token);
	}

	@When("Admin calls {string} with {string} http request in User module")
	public void admin_calls_with_http_request_in_user_module(String resource, String method) {
	    
		/*ApiResources resourceAPI = ApiResources.valueOf(resource);

        if (method.equalsIgnoreCase("GET")) {
            response = req.when().get(resourceAPI.getResorce());
		
        }*/
		ApiResources resourceAPI = ApiResources.valueOf(resource);
	    
	    System.out.println("🔍 DEBUG - GET by ID:");
	    System.out.println("Resource: " + resource);
	    System.out.println("Base URL: " + resourceAPI.getResorce());
	    
	    if (resource.equals("getbyid")) {
	        // Use the EXISTING userId from CreatedUserData (set by POST)
	        String userId = CreatedUserData.getUserId();
	        
	        System.out.println("Using userId from POST: " + userId);
	        
	        if (userId != null && !userId.isEmpty()) {
	            // Get the specific user created in POST scenario
	            response = req.when().get(resourceAPI.getResorce() + "/" + userId);
	            System.out.println("GET specific user response: " + response.asString());
	        } else {
	            System.out.println("⚠️ No userId available from POST - cannot GET by ID");
	        }
	    } else {
	        response = req.when().get(resourceAPI.getResorce());
	    }
	}

	@Then("Admin receives {int} ok Status in user module")
	public void admin_receives_ok_status_in_user_module(Integer expectedStatus) {
	    
		
		/*int actualStatus = response.getStatusCode();
        System.out.println("Status Code: " + actualStatus);
        System.out.println("Response: " + response.asString());

        assert actualStatus == expectedStatus :
                "Expected " + expectedStatus + " but got " + actualStatus;
        if (actualStatus == 201) {
            String userId = response.jsonPath().getString("userId"); // Adjust based on your response
            CreatedUserData.setUserId(userId);
            System.out.println("Saved created user ID: " + userId);
        }*/
		int actualStatus = response.getStatusCode();
	    System.out.println("Status Code: " + actualStatus);
	    System.out.println("Response: " + response.asString());

	    assert actualStatus == expectedStatus :
	            "Expected " + expectedStatus + " but got " + actualStatus;
	    
	    if (actualStatus == 201 || actualStatus == 200) {
	        // ✅ FIXED: Extract userId from nested "user" object
	        String userId = response.jsonPath().getString("user.userId");  // ← CHANGED
	        
	        if (userId != null) {
	            CreatedUserData.setUserId(userId);
	            System.out.println("✅ Saved created user ID: " + userId);
	        } else {
	            System.out.println("⚠️ Warning: userId not found in response");
	            System.out.println("Response structure: " + response.asString());
	        }
	    }
	}

	
	@Given("Admin creates GET Request into the user by role module")
	public void admin_creates_get_request_into_the_user_by_role_module() throws IOException {
	    
		String token = LoginTempData.getToken();
	    
	    System.out.println("🔍 GET BY ROLE REQUEST DEBUG:");
	    System.out.println("Token: " + (token != null ? "Present" : "NULL"));
	    
	    req = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token);
	
	}

	@When("Admin calls {string} with {string} http request with role {string}")
	public void admin_calls_with_http_request_with_role(String resource, String method, String role) {
	   
		ApiResources resourceAPI = ApiResources.valueOf(resource);
	    
	    System.out.println("🔍 GET BY ROLE DEBUG INFO:");
	    System.out.println("Resource: " + resource);
	    System.out.println("Base URL: " + resourceAPI.getResorce());
	    System.out.println("Role: " + role);
	    System.out.println("Full URL: " + resourceAPI.getResorce() + "/" + role);
	    
	    if (method.equalsIgnoreCase("GET")) {
	        response = req.when().get(resourceAPI.getResorce() + "/" + role);
	    }
		
	}

	@Then("Admin receives {int} ok Status from user module")
	public void admin_receives_ok_status_from_user_module(Integer expectedStatus) {
		
		int actualStatus = response.getStatusCode();
	    System.out.println("Status Code: " + actualStatus);
	    System.out.println("Response: " + response.asString());

	    assert actualStatus == expectedStatus :
	            "Expected " + expectedStatus + " but got " + actualStatus;
	            
	    System.out.println("✅ Successfully retrieved users by role with status: " + actualStatus);
	}

	
	@Given("Admin creates PUT Request for the user module")
	public void admin_creates_put_request_for_the_user_module() throws IOException {
		
		String token = LoginTempData.getToken();
	    String updatePayload = data1.updateUserPayload();
	    
	    System.out.println("🔍 PUT REQUEST DEBUG:");
	    System.out.println("Token: " + (token != null ? "Present" : "NULL"));
	    System.out.println("Payload being sent:");
	    System.out.println(updatePayload);

	    req = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .body(updatePayload);
	}

	@When("Admin calls {string} with {string} http request for updating user")
	public void admin_calls_with_http_request_for_updating_user(String resource, String method) {
		
		ApiResources resourceAPI = ApiResources.valueOf(resource);
	    String userId = CreatedUserData.getUserId(); // Get the created user's ID

	    System.out.println("🔍 DEBUG INFO:");
	    System.out.println("Resource: " + resource);
	    System.out.println("Base URL: " + resourceAPI.getResorce());
	    System.out.println("User ID: " + userId);
	    System.out.println("Full URL: " + resourceAPI.getResorce() + "/" + userId);
	    
	    if (method.equalsIgnoreCase("PUT")) {
	        response = req.when().put(resourceAPI.getResorce() + "/" + userId);
	    }
	}

	@Then("Admin receives {int} OK Status for update")
	public void admin_receives_ok_status_for_update(Integer expectedStatus) {
	   
		 int actualStatus = response.getStatusCode();
	        System.out.println("Status Code: " + actualStatus);
	        System.out.println("Response: " + response.asString());

	        assert actualStatus == expectedStatus :
	                "Expected " + expectedStatus + " but got " + actualStatus;
	}
	
	@Given("Admin creates DELETE Request for the user module")
	public void admin_creates_delete_request_for_the_user_module() throws IOException {
	   
		String token = LoginTempData.getToken();
	    
	    System.out.println("🔍 DELETE REQUEST DEBUG:");
	    System.out.println("Token: " + (token != null ? "Present" : "NULL"));
	    System.out.println("User ID to delete: " + CreatedUserData.getUserId());
	    
	    req = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token);
	}

	@When("Admin calls {string} with {string} http request for deleting user")
	public void admin_calls_with_http_request_for_deleting_user(String resource, String method) {
	    
		ApiResources resourceAPI = ApiResources.valueOf(resource);
	    String userId = CreatedUserData.getUserId(); // Get the created user's ID

	    System.out.println("🔍 DELETE DEBUG INFO:");
	    System.out.println("Resource: " + resource);
	    System.out.println("Base URL: " + resourceAPI.getResorce());
	    System.out.println("User ID: " + userId);
	    System.out.println("Full URL: " + resourceAPI.getResorce() + "/" + userId);
	    
	    if (method.equalsIgnoreCase("DELETE")) {
	        response = req.when().delete(resourceAPI.getResorce() + "/" + userId);
	    }
	}

	@Then("Admin receives {int} Status for delete")
	public void admin_receives_status_for_delete(Integer expectedStatus) {
	   
		int actualStatus = response.getStatusCode();
	    System.out.println("Status Code: " + actualStatus);
	    System.out.println("Response: " + response.asString());

	    assert actualStatus == expectedStatus :
	            "Expected " + expectedStatus + " but got " + actualStatus;
	            
	    System.out.println("✅ User deleted successfully with status: " + actualStatus);
	}
=======

>>>>>>> 669167619cf8f8514ad167e0f2a592bc4a18bf90
}
	
	
	

