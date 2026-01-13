package stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//public class UsermoduleSteps {
//	
//	@Given("Admin sets Bearer token")
//	public void admin_sets_bearer_token() {
//	    
//	}
//
//	@Given("Admin creates POST Request for the LMS API endpoint")
//	public void admin_creates_post_request_for_the_lms_api_endpoint() {
//	   
//	}
//
//	@When("Admin sends HTTPS Request with mandatory and additional fields")
//	public void admin_sends_https_request_with_mandatory_and_additional_fields() {
//=======
import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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
		  
//		String userPayload = "{\r\n"
//				+ "  \"userComments\": \"APHackathons\",\r\n"
//				+ "  \"userEduPg\": \"Msc\",\r\n"
//				+ "  \"userEduUg\": \"Bsc\",\r\n"
//				+ "  \"userFirstName\": \"Rinarrc\",\r\n"
//				+ "  \"userLastName\": \"Rinarrc\",\r\n"
//				+ "  \"userLinkedinUrl\": \"https://www.linkedin.com/Rinarrc\",\r\n"
//				+ "  \"userLocation\": \"USA\",\r\n"
//				+ "  \"userMiddleName\": \"Rajan\",\r\n"
//				+ "  \"userPhoneNumber\": \"+91 9934567890\",\r\n"
//				+ "  \"userRoleMaps\": [\r\n"
//				+ "    {\r\n"
//				+ "      \"roleId\": \"R01\",\r\n"
//				+ "      \"userRoleStatus\": \"Active\"\r\n"
//				+ "    }\r\n"
//				+ "  ],\r\n"
//				+ "  \"userTimeZone\": \"EST\",\r\n"
//				+ "  \"userVisaStatus\": \"H1B\",\r\n"
//				+ "  \"userLogin\": {\r\n"
//				+ "    \"userLoginEmail\": \"Rinarrc@gmail.com\",\r\n"
//				+ "    \"loginStatus\": \"Active\",\r\n"
//				+ "    \"status\": \"Active\"\r\n"
//				+ "  }\r\n"
//				+ "}";

//		  String token = LoginTempData.getToken(); // token from login feature
//
//	        req = given()
//	                .spec(requestspecification())
//	                .header("Authorization", "Bearer " + token).body(data.createUserPayload());
		String token = LoginTempData.getToken();

	    req = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .body(data1.createUserPayload());

//	   
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
	}
}
