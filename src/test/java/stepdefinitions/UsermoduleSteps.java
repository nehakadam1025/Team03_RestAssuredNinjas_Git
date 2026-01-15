//package stepdefinitions;
//
//
//import static io.restassured.RestAssured.given;
//
//import java.io.IOException;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
////import pojo.CreatedUserData;
//import pojo.CreatedUserData;
//import pojo.LoginTempData;
//import utils.ApiResources;
//import utils.TestDataBuild;
//import utils.utility;
//
//public class UsermoduleSteps extends utility {
//	
//	private RequestSpecification req;
//    private Response response;
//    String userId = CreatedUserData.getUserId();
//    String token = LoginTempData.getToken();
//	
//
//    TestDataBuild data1 = new TestDataBuild();
//
//	@Given("Admin creates POST Request for the LMS API endpoint")
//	public void admin_creates_post_request_for_the_lms_api_endpoint() throws IOException {
//			    
//	   
//	    
//	    if (token == null || token.isEmpty()) {
//	        System.out.println("❌ CRITICAL ERROR: Token is NULL or EMPTY!");
//	        System.out.println("❌ This means login step did not save the token properly!");
//	        throw new RuntimeException("Token not available. Check login step.");
//	    }
//	    
//	    String payload = data1.createUserPayload();
//	    System.out.println("=========================================\n");
//
//	    req = given()
//	    		.spec(requestspecification())
//                .contentType(ContentType.JSON)  // must set for JSON payload
//                .accept(ContentType.JSON)
//	            .header("Authorization", "Bearer " + token)
//	            .body(payload);
//	}
//	   
//	
//	@When("Admin calls {string} with {string} http request for user admin")
//	public void admin_calls_with_https_request_for_user_admin(String resource, String method) {
//		
//		 ApiResources resourceAPI = ApiResources.valueOf(resource);
//
//		 if (method.equalsIgnoreCase("GET")) {
//			    response = req.when().get(resourceAPI.getResorce());
//			}
////			else if (method.equalsIgnoreCase("POST")) {
////			    response = req.when().post(resourceAPI.getResorce());
////			}
////			else if (method.equalsIgnoreCase("PUT")) {
////			    response = req.when().put(resourceAPI.getResorce());
////			}
////			else if (method.equalsIgnoreCase("DELETE")) {
////			    response = req.when().delete(resourceAPI.getResorce());
////			}
////			else {
////			    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
////			}
//	    
//	}
//	
//	@Then("Admin receives {int} Created Status with response body")
//	public void admin_receives_created_status_with_response_body(Integer expected) {
//		
//		int actualStatus = response.getStatusCode();
//        System.out.println("Status Code: " + actualStatus);
//        System.out.println("Response: " + response.asString());
//
//        assert actualStatus == expected :
//                "Expected " + expected + " but got " + actualStatus;
//        
//        if (actualStatus == 201 || actualStatus == 200) {
//        	 String userId = response.jsonPath().getString("user.userId"); 
//            if (userId != null) {
//                CreatedUserData.setUserId(userId);
//                System.out.println("✅ Saved created user ID: " + userId);
//            } else {
//                System.out.println("⚠️ Warning: userId not found in response");
//            }
//        }
//	}
//
//
//
//	@Given("Admin creates GET Request for the user module")
//	public void admin_creates_get_request_for_the_user_module() throws IOException {
//		
//		String token = LoginTempData.getToken(); // token from login feature
//
//        req = given()
//                .spec(requestspecification())
//                .header("Authorization", "Bearer " + token);
//	
//	}
//	@Given("Admin creates GET Request in the user module")
//	public void admin_creates_get_request_in_the_user_module() throws IOException {
//		
//		String token = LoginTempData.getToken(); // token from login feature
//		String userId = CreatedUserData.getUserId();
//        req = given()
//                .spec(requestspecification())
//                .header("Authorization", "Bearer " + token)
//                .pathParam("userId", userId);   // ✅ correct
//
//	}
//	
//	
//
//	@Then("Admin receives {int} Status for user module")
//	public void admin_receives_status_for_user_module(Integer expectedStatus) {
//	  
//		int actualStatus = response.getStatusCode();
//        System.out.println("Status Code: " + actualStatus);
//        System.out.println("Response: " + response.asString());
//
//        assert actualStatus == expectedStatus :
//                "Expected " + expectedStatus + " but got " + actualStatus;
//	}
//	
//	
////	@Given("Admin creates GET Request in the user module")
////	public void admin_creates_get_request_in_the_user_module() throws IOException {
////		
////		String token = LoginTempData.getToken(); // token from login feature
////		String userId = CreatedUserData.getUserId();
////        req = given()
////                .spec(requestspecification())
////                .header("Authorization", "Bearer " + token)
////                .pathParam("userId", userId);   // ✅ correct
////
////	}
//
//	@When("Admin calls {string} with {string} http request in User module")
//	public void admin_calls_with_http_request_in_user_module(String resource, String method) {
//
//		ApiResources resourceAPI = ApiResources.valueOf(resource);
//
//        if (method.equalsIgnoreCase("GET")) {
//            response = req.when().get(resourceAPI.getResorce());
//        }
//        else if (method.equalsIgnoreCase("PUT")) {
//            response = req.when().put(resourceAPI.getResorce());
//        }
//	}
//	@When("Admin calls {string} with {string} http request for User module id")
//	public void admin_calls_with_http_request_for_user_module(String resource, String method) {
//		
//		ApiResources resourceAPI = ApiResources.valueOf(resource);
//
//        if (method.equalsIgnoreCase("GET")) {
//            response = req.when().get(resourceAPI.getResorce() + "/" + userId);
//        }
//
//
//
//	}
//
//	@Then("Admin receives {int} ok Status in user module")
//	public void admin_receives_ok_status_in_user_module(Integer expectedStatus) {
//	    
//		
//		/*int actualStatus = response.getStatusCode();
//        System.out.println("Status Code: " + actualStatus);
//        System.out.println("Response: " + response.asString());
//
//        assert actualStatus == expectedStatus :
//                "Expected " + expectedStatus + " but got " + actualStatus;
//        if (actualStatus == 201) {
//            String userId = response.jsonPath().getString("userId"); // Adjust based on your response
//            CreatedUserData.setUserId(userId);
//            System.out.println("Saved created user ID: " + userId);
//        }*/
//		int actualStatus = response.getStatusCode();
//	    System.out.println("Status Code: " + actualStatus);
//	    System.out.println("Response: " + response.asString());
//
//	    assert actualStatus == expectedStatus :
//	            "Expected " + expectedStatus + " but got " + actualStatus;
//	    
//	    if (actualStatus == 201 || actualStatus == 200) {
//	        // ✅ FIXED: Extract userId from nested "user" object
//	        String userId = response.jsonPath().getString("user.userId");  // ← CHANGED
//	        
//	        if (userId != null) {
//	            CreatedUserData.setUserId(userId);
//	            System.out.println("✅ Saved created user ID: " + userId);
//	        } else {
//	            System.out.println("⚠️ Warning: userId not found in response");
//	            System.out.println("Response structure: " + response.asString());
//	        }
//	    }
//	}
//
//	
//	@Given("Admin creates GET Request into the user by role module")
//	public void admin_creates_get_request_into_the_user_by_role_module() throws IOException {
//	    
//		String token = LoginTempData.getToken();
//	    
//	    System.out.println("🔍 GET BY ROLE REQUEST DEBUG:");
//	    System.out.println("Token: " + (token != null ? "Present" : "NULL"));
//	    
//	    req = given()
//	            .spec(requestspecification())
//	            .header("Authorization", "Bearer " + token);
//	
//	}
//
//	@When("Admin calls {string} with {string} http request with role {string}")
//	public void admin_calls_with_http_request_with_role(String resource, String method, String role) {
//	   
//		ApiResources resourceAPI = ApiResources.valueOf(resource);
//	    
//	    System.out.println("🔍 GET BY ROLE DEBUG INFO:");
//	    System.out.println("Resource: " + resource);
//	    System.out.println("Base URL: " + resourceAPI.getResorce());
//	    System.out.println("Role: " + role);
//	    System.out.println("Full URL: " + resourceAPI.getResorce() + "/" + role);
//	    
//	    if (method.equalsIgnoreCase("GET")) {
//	        response = req.when().get(resourceAPI.getResorce() + "/" + role);
//	    }
//		
//	}
//
//	@Then("Admin receives {int} ok Status from user module")
//	public void admin_receives_ok_status_from_user_module(Integer expectedStatus) {
//		
//		int actualStatus = response.getStatusCode();
//	    System.out.println("Status Code: " + actualStatus);
//	    System.out.println("Response: " + response.asString());
//
//	    assert actualStatus == expectedStatus :
//	            "Expected " + expectedStatus + " but got " + actualStatus;
//	            
//	    System.out.println("✅ Successfully retrieved users by role with status: " + actualStatus);
//	}
//
//	
//	@Given("Admin creates PUT Request for the user module")
//	public void admin_creates_put_request_for_the_user_module() throws IOException {
//		
//		String token = LoginTempData.getToken();
//	    String updatePayload = data1.updateUserPayload();
//	    
//	     
//	    req = given()
//	    		.spec(requestspecification())
//	    		.header("Authorization", "Bearer " + token)
//                .contentType(ContentType.JSON)  // must set for JSON payload
//                .accept(ContentType.JSON)	            
//                .pathParam("userId", userId)
//	            .body(updatePayload);
//	}
//
////	@When("Admin calls {string} with {string} http request for updating user")
////	public void admin_calls_with_http_request_for_updating_user(String resource, String method) {
////		
//////		ApiResources resourceAPI = ApiResources.valueOf(resource);
//////	    // Get the created user's ID
//////
//////	    System.out.println("🔍 DEBUG INFO:");
//////	    System.out.println("Resource: " + resource);
//////	    System.out.println("Base URL: " + resourceAPI.getResorce());
//////	    
//////	   
//////	    
//////	    if (method.equalsIgnoreCase("PUT")) {
//////            response = req.when().put(resourceAPI.getResorce());
//////        }
////	}
//
//	@Then("Admin receives {int} OK Status for update")
//	public void admin_receives_ok_status_for_update(Integer expectedStatus) {
//	   
//		 int actualStatus = response.getStatusCode();
//	        System.out.println("Status Code: " + actualStatus);
//	        System.out.println("Response: " + response.asString());
//
//	        assert actualStatus == expectedStatus :
//	                "Expected " + expectedStatus + " but got " + actualStatus;
//	}
//	
//	@Given("Admin creates DELETE Request for the user module")
//	public void admin_creates_delete_request_for_the_user_module() throws IOException {
//	   
//		
//	    
//		String token = LoginTempData.getToken(); // token from login feature
//		String userId = CreatedUserData.getUserId();
//        req = given()
//                .spec(requestspecification())
//	    		.header("Authorization", "Bearer " + token)
//                .contentType(ContentType.JSON)  // must set for JSON payload
//                .accept(ContentType.JSON)	            
//                .pathParam("userId", userId); // ✅ correct
//	}
//
////	@When("Admin calls {string} with {string} http request for deleting user")
////	public void admin_calls_with_http_request_for_deleting_user(String resource, String method) {
////	    
////		ApiResources resourceAPI = ApiResources.valueOf(resource);
////	    String userId = CreatedUserData.getUserId(); // Get the created user's ID
////
////	    System.out.println("🔍 DELETE DEBUG INFO:");
////	    System.out.println("Resource: " + resource);
////	    System.out.println("Base URL: " + resourceAPI.getResorce());
////	    System.out.println("User ID: " + userId);
////	    System.out.println("Full URL: " + resourceAPI.getResorce() + "/" + userId);
////	    
////	    if (method.equalsIgnoreCase("DELETE")) {
////	        response = req.when().delete(resourceAPI.getResorce() + "/" + userId);
////	    }
////	}
//
//	@Then("Admin receives {int} Status for delete")
//	public void admin_receives_status_for_delete(Integer expectedStatus) {
//	   
//		int actualStatus = response.getStatusCode();
//	    System.out.println("Status Code: " + actualStatus);
//	    System.out.println("Response: " + response.asString());
//
//	    assert actualStatus == expectedStatus :
//	            "Expected " + expectedStatus + " but got " + actualStatus;
//	            
//	    System.out.println("✅ User deleted successfully with status: " + actualStatus);
//	}
//}
//	

package stepdefinitions;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import pojo.CreatedUserData;
import pojo.CreatedUserData;
import pojo.LoginTempData;
import utils.ApiResources;
import utils.TestDataBuild;
import utils.utility;
import io.restassured.http.ContentType;

public class UsermoduleSteps extends utility {
	
	private RequestSpecification req;
    private Response response;
	

    TestDataBuild data1 = new TestDataBuild();

	@Given("Admin creates POST Request for the LMS API endpoint")
	public void admin_creates_post_request_for_the_lms_api_endpoint() throws IOException {

	    
		String token = LoginTempData.getToken();
	    
	    
	    if (token == null || token.isEmpty()) {
	        System.out.println("❌ CRITICAL ERROR: Token is NULL or EMPTY!");
	        System.out.println("❌ This means login step did not save the token properly!");
	        throw new RuntimeException("Token not available. Check login step.");
	    }
	    
	    String payload = data1.createUserPayload();
	    System.out.println("=========================================\n");

	    req = given()
	            .spec(requestspecification())
	            .contentType(ContentType.JSON)  // must set for JSON payload
                .accept(ContentType.JSON)
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(payload);
	}
	   
	
	@When("Admin calls {string} with {string} http request for user admin")
	public void admin_calls_with_https_request_for_user_admin(String resource, String method) {
		
		 ApiResources resourceAPI = ApiResources.valueOf(resource);


	        if (method.equalsIgnoreCase("POST")) {
	            response = req.when().post(resourceAPI.getResorce());

	        }
	        
	
	    
	}
	
	@Then("Admin receives {int} Created Status with response body")
	public void admin_receives_created_status_with_response_body(Integer expectedStatus) {
		
		int actualStatus = response.getStatusCode();
        System.out.println("Status Code: " + actualStatus);
        System.out.println("Response: " + response.asString());

        assert actualStatus == expectedStatus :
                "Expected " + expectedStatus + " but got " + actualStatus;
        
        if (actualStatus == 201 || actualStatus == 200) {
        	// String userId = response.jsonPath().getString("user.userId"); 
        	String userId = response.jsonPath().getString("userId"); 
            
            // If it's null, try getting it from the 'user' object
            if (userId == null) {
                userId = response.jsonPath().getString("user.userId");
            }
            
            if (userId != null) {
                CreatedUserData.setUserId(userId); // Store it for other steps
                System.out.println("✅ SUCCESS: Captured User ID: " + userId);
            } else {
                System.out.println("❌ ERROR: userId not found in JSON response!");
                System.out.println("Full Response Body: " + response.asString());
            }
        }
	}



	@Given("Admin creates GET Request for the user module")
	public void admin_creates_get_request_for_the_user_module() throws IOException {
		
		String token = LoginTempData.getToken(); // token from login feature

        req = given()
                .spec(requestspecification())
                .contentType(ContentType.JSON)  // must set for JSON payload
                .accept(ContentType.JSON)
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
	public void admin_receives_status_for_user_module(Integer expected) {
	  

	    
	    // ❌ DON'T extract userId from GET ALL - it returns an array!
	    // The userId should already be set from the POST scenario
	    
	    System.out.println("✅ GET request successful. Current userId: " + CreatedUserData.getUserId());

		int actual = response.getStatusCode();
        System.out.println("Status Code: " + actual);
        System.out.println("Response: " + response.asString());

        assert actual == expected :
                "Expected " + expected + " but got " + actual;

	}
	
	
	@Given("Admin creates GET Request in the user module")
	public void admin_creates_get_request_in_the_user_module() throws IOException {
		
		String token = LoginTempData.getToken(); // token from login feature

        req = given()
        		.spec(requestspecification())
                .contentType(ContentType.JSON)  // must set for JSON payload
                .accept(ContentType.JSON)
	            .header("Authorization", "Bearer " + token);
	}

	@When("Admin calls {string} with {string} http request in User module")
	public void admin_calls_with_http_request_in_user_module(String resource, String method) {
	  
		ApiResources resourceAPI = ApiResources.valueOf(resource);
	   
	    
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
	public void admin_receives_ok_status_in_user_module(Integer expectedvalue) {
	    

		int actualvalue = response.getStatusCode();
	    System.out.println("Status Code: " + actualvalue);

	    System.out.println("Response: " + response.asString());

	    assert actualvalue == expectedvalue :
	            "Expected " + expectedvalue + " but got " + actualvalue;
	    

	    if (actualvalue == 201 || actualvalue == 200) {
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
	        
		 req = given()
	        		.spec(requestspecification())
	                .contentType(ContentType.JSON)  // must set for JSON payload
	                .accept(ContentType.JSON)
		            .header("Authorization", "Bearer " + token);
	
	}

	@When("Admin calls {string} with {string} http request with role {string}")
	public void admin_calls_with_http_request_with_role(String resource, String method, String role) {
	   
		ApiResources resourceAPI = ApiResources.valueOf(resource);
	    
	
	    if (method.equalsIgnoreCase("GET")) {
	        response = req.when().get(resourceAPI.getResorce() + "/" + role);
	    }
		
	}

	@Then("Admin receives {int} ok Status from user module")
	public void admin_receives_ok_status_from_user_module(Integer expectedrole) {
		
		int actualrole = response.getStatusCode();
	    System.out.println("Status Code: " + actualrole);
	    System.out.println("Response: " + response.asString());

	    assert actualrole == expectedrole :
	            "Expected " + expectedrole + " but got " + actualrole;
	            
	    System.out.println("✅ Successfully retrieved users by role with status: " + actualrole);
	}

	
	@Given("Admin creates PUT Request for the user module")
	public void admin_creates_put_request_for_the_user_module() throws IOException {
		
		String token = LoginTempData.getToken();
	    String updatePayload = data1.updateUserPayload();
	  
	    req = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(updatePayload);
	}

	@When("Admin calls {string} with {string} http request for updating user")
	public void admin_calls_with_http_request_for_updating_user(String resource, String method) {
		
		ApiResources resourceAPI = ApiResources.valueOf(resource);
	    String userId = CreatedUserData.getUserId(); // Get the created user's ID

	 
	    
	    if (method.equalsIgnoreCase("PUT")) {
	        response = req
	       //     .contentType(ContentType.JSON)  // ✅ ADD THIS - Critical for 415 fix!
	            .when()
	            .put(resourceAPI.getResorce() + "/" + userId);
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
	   	    
	    req = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token);
	}

	@When("Admin calls {string} with {string} http request for deleting user")
	public void admin_calls_with_http_request_for_deleting_user(String resource, String method) {
	    
		ApiResources resourceAPI = ApiResources.valueOf(resource);
	    String userId = CreatedUserData.getUserId(); // Get the created user's ID

	    
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
}

