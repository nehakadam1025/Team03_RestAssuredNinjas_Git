package stepdefinitions;

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
	
//	@Given("Admin sets Bearer token")
//	public void admin_sets_bearer_token() {
//		
//		
//	    
//	}
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

//	@When("Admin sends HTTPS Request with existing phone number")
//	public void admin_sends_https_request_with_existing_phone_number() {
//	   
//	}
//
//	@Then("Admin receives {int} Bad Request Status with message and boolean success details")
//	public void admin_receives_bad_request_status_with_message_and_boolean_success_details(Integer int1) {
//	    
//	}
//
//	@When("Admin sends HTTPS Request with missing mandatory fields")
//	public void admin_sends_https_request_with_missing_mandatory_fields() {
//	    
//	}
//
//	@Given("Admin creates GET Request for the LMS API All admin endpoint")
//	public void admin_creates_get_request_for_the_lms_api_all_admin_endpoint() {
//	 
//	}
//
//	@When("Admin sends HTTPS Request")
//	public void admin_sends_https_request() {
//	   
//	}
//
//	@Then("Admin receives {int} OK Status with response body")
//	public void admin_receives_ok_status_with_response_body(Integer int1) {
//	    
//	}
//
//	@Given("Admin creates GET Request for the LMS API endpoint with valid admin ID")
//	public void admin_creates_get_request_for_the_lms_api_endpoint_with_valid_admin_id() {
//	    
//	}
//
//	@Given("Admin creates GET Request for the LMS API endpoint with invalid admin ID")
//	public void admin_creates_get_request_for_the_lms_api_endpoint_with_invalid_admin_id() {
//	    
//	}
//
//	@Then("Admin receives {int} Not Found Status with message and boolean success details")
//	public void admin_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
//	 
//	}
//
//	@Given("Admin creates GET Request for the LMS API All Staff endpoint")
//	public void admin_creates_get_request_for_the_lms_api_all_staff_endpoint() {
//	 
//	}
//
//	@Given("Admin creates GET Request for the LMS API admin Roles endpoint")
//	public void admin_creates_get_request_for_the_lms_api_admin_roles_endpoint() {
//	 
//	}
//
//	@Given("Admin creates PUT Request for the LMS API endpoint with valid admin ID")
//	public void admin_creates_put_request_for_the_lms_api_endpoint_with_valid_admin_id() {
//	   
//	}
//
//	@Given("Admin creates PUT Request for the LMS API endpoint with invalid admin ID")
//	public void admin_creates_put_request_for_the_lms_api_endpoint_with_invalid_admin_id() {
//	    
//	}
//
//	@Given("Admin creates PUT Request for updating admin role status")
//	public void admin_creates_put_request_for_updating_admin_role_status() {
//	 
//	}
//
//	@When("Admin sends HTTPS Request with Role ID and Role status")
//	public void admin_sends_https_request_with_role_id_and_role_status() {
//	  
//	}
//
//	@Then("Admin receives {int} OK Status with response message")
//	public void admin_receives_ok_status_with_response_message(Integer int1) {
//	   
//	}
//
//	@Given("Admin creates PUT Request for updating admin role status with invalid ID")
//	public void admin_creates_put_request_for_updating_admin_role_status_with_invalid_id() {
//	   
//	}
//
//	@Given("Admin creates PUT Request for assigning admin to program batch")
//	public void admin_creates_put_request_for_assigning_admin_to_program_batch() {
//	   
//	}
//
//	@When("Admin sends HTTPS Request with program ID batch ID role ID admin ID and status")
//	public void admin_sends_https_request_with_program_id_batch_id_role_id_admin_id_and_status() {
//	    
//	}
//
//	@Then("Admin receives {int} OK Status with success message")
//	public void admin_receives_ok_status_with_success_message(Integer int1) {
//	    
//	}
//
//	@Given("Admin creates PUT Request for assigning admin to program batch with invalid ID")
//	public void admin_creates_put_request_for_assigning_admin_to_program_batch_with_invalid_id() {
//	  
//	}
//
//	@Given("Admin creates DELETE Request for the LMS API endpoint with valid admin ID")
//	public void admin_creates_delete_request_for_the_lms_api_endpoint_with_valid_admin_id() {
//
//	}
//
//	@Then("Admin receives {int} OK status with message")
//	public void admin_receives_ok_status_with_message(Integer int1) {
//	   
//	}
//
//	@Given("Admin creates DELETE Request for the LMS API endpoint with invalid admin ID")
//	public void admin_creates_delete_request_for_the_lms_api_endpoint_with_invalid_admin_id() {
//	  
//	}

}
