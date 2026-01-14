package stepdefinitions;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.CreatedUserData;
import pojo.LoginTempData;
import utils.ApiResources;
import utils.TestDataBuild;
import utils.utility;

public class UsermoduleSteps extends utility {
	
	private RequestSpecification req;
    private Response response;


    TestDataBuild data1 = new TestDataBuild();

	@Given("Admin creates POST Request for the LMS API endpoint")
	public void admin_creates_post_request_for_the_lms_api_endpoint() throws IOException {
		  

		String token = LoginTempData.getToken();

	    req = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .body(data1.createUserPayload());
	   
	}

	@When("Admin calls {string} with {string} http request for user admin")
	public void admin_calls_with_https_request_for_user_admin(String resource, String method) {
		
		 ApiResources resourceAPI = ApiResources.valueOf(resource);

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
	    
	}
	
	@Then("Admin receives {int} Created Status with response body")
	public void admin_receives_created_status_with_response_body(Integer expectedStatus) {
		
		int actualStatus = response.getStatusCode();
        System.out.println("Status Code: " + actualStatus);
        System.out.println("Response: " + response.asString());

        assert actualStatus == expectedStatus :
                "Expected " + expectedStatus + " but got " + actualStatus;
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
	
	
	@Given("Admin creates GET Request in the user module")
	public void admin_creates_get_request_in_the_user_module() throws IOException {
		
		String token = LoginTempData.getToken(); // token from login feature

        req = given()
                .spec(requestspecification())
                .header("Authorization", "Bearer " + token);
	}

	@When("Admin calls {string} with {string} http request in User module")
	public void admin_calls_with_http_request_in_user_module(String resource, String method) {
	    
		ApiResources resourceAPI = ApiResources.valueOf(resource);

        if (method.equalsIgnoreCase("GET")) {
            response = req.when().get(resourceAPI.getResorce());
        }
	}

	@Then("Admin receives {int} ok Status for user module")
	public void admin_receives_ok_status_for_user_module(Integer expectedStatus) {
	    
		
		int actualStatus = response.getStatusCode();
        System.out.println("Status Code: " + actualStatus);
        System.out.println("Response: " + response.asString());

        assert actualStatus == expectedStatus :
                "Expected " + expectedStatus + " but got " + actualStatus;
        if (actualStatus == 201) {
            String userId = response.jsonPath().getString("userId"); // Adjust based on your response
            CreatedUserData.setUserId(userId);
            System.out.println("Saved created user ID: " + userId);
        }
	}


	
	
	
}

