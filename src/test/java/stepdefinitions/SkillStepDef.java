package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import utils.ApiResources;
import utils.global;
import utils.utility;
import pojo.LoginTempData;
import pojo.SkillRequest;
import pojo.SkillResponse;

public class SkillStepDef extends utility {

	private RequestSpecification req2;
	private Response response;

	@Given("Admin creates POST Request for the LMS API endpoint")
	public void admin_creates_post_request_for_the_lms_api_endpoint() throws IOException {

		String token = LoginTempData.getToken();
		SkillRequest skillpayload = new SkillRequest("Javamee");

		req2 = given().spec(requestspecification()).header("Authorization", "Bearer " + token)
				.contentType("application/json").body(skillpayload);

	}

	@Given("Admin creates GET Request for the LMS API endpoint")
	public void admin_creates_get_request_for_the_lms_api_endpoint() throws IOException {

		String token = LoginTempData.getToken(); // token from login feature

		req2 = given().spec(requestspecification()).contentType(ContentType.JSON) // must set for JSON payload
				.accept(ContentType.JSON).header("Authorization", "Bearer " + token);
	}

	@Given("Admin creates POST Request for the LMS API endpoint with existing skill name")
	public void admin_creates_post_request_for_the_lms_api_endpoint_with_existing_skill_name() throws IOException{
		String token = LoginTempData.getToken();
		SkillRequest skillpayload = new SkillRequest("Discover");

		req2 = given().spec(requestspecification()).header("Authorization", "Bearer " + token)
				.contentType("application/json").body(skillpayload);
	}

	@Given("Admin creates POST Request for the LMS API endpoint with empty body")
	public void admin_creates_post_request_for_the_lms_api_endpoint_with_empty_body() throws IOException{
		String token = LoginTempData.getToken();
		SkillRequest skillpayload = new SkillRequest(" ");

		req2 = given().spec(requestspecification()).header("Authorization", "Bearer " + token)
				.contentType("application/json").body(skillpayload);
	}
	
	@Given("Admin creates PUT Request for the LMS API endpoint with valid body")
	public void admin_creates_put_request_for_the_lms_api_endpoint_with_valid_body() throws IOException{
		String token = LoginTempData.getToken();
		SkillRequest skillpayload = new SkillRequest("mydiscover");

		req2 = given().spec(requestspecification()).header("Authorization", "Bearer " + token)
				.contentType("application/json").body(skillpayload);
	}
	
	@Given("Admin creates PUT Request for the LMS API endpoint with invalid endpoint")
	public void admin_creates_put_request_for_the_lms_api_endpoint_with_invalid_endpoint() throws IOException{
		String token = LoginTempData.getToken();
		SkillRequest skillpayload = new SkillRequest("a");

		req2 = given().spec(requestspecification()).header("Authorization", "Bearer " + token)
				.contentType("application/json").body(skillpayload);
	}
	
	@Given("Admin creates DELETE Request for the LMS API endpoint with valid endpoint")
	public void admin_creates_delete_request_for_the_lms_api_endpoint_with_valid_endpoint() throws IOException {

		String token = LoginTempData.getToken(); // token from login feature

		req2 = given().spec(requestspecification()).contentType(ContentType.JSON) // must set for JSON payload
				.accept(ContentType.JSON).header("Authorization", "Bearer " + token);
	}
	
	@Given("Admin creates DELETE Request for the LMS API endpoint with invalid endpoint")
	public void admin_creates_delete_request_for_the_lms_api_endpoint_with_invalid_endpoint() throws IOException {

		String token = LoginTempData.getToken(); // token from login feature

		req2 = given().spec(requestspecification()).contentType(ContentType.JSON) // must set for JSON payload
				.accept(ContentType.JSON).header("Authorization", "Bearer " + token);
	}
	@When("Admin calls {string} with {string} http request for skill master")
	public void admin_calls_with_http_request_for_skill_master(String resource, String method) {

		ApiResources resourceAPI = ApiResources.valueOf(resource);
		// String endpoint = resourceAPI.getResorce();

		if (method.equalsIgnoreCase("GET")) {

			response = req2.when().get(resourceAPI.getResorce());

		} else if (method.equalsIgnoreCase("POST")) {
			response = req2.when().post(resourceAPI.getResorce());

		} else if (method.equalsIgnoreCase("PUT")) {

			response = req2.when().put(resourceAPI.getResorce());
		} else if (method.equalsIgnoreCase("DELETE")) {

			response = req2.when().delete(resourceAPI.getResorce());
		}
	}
	
	@Then("Admin receives {int} Status code")
	public void admin_receives_status_code(Integer expectedStatus) {

		int actualStatus = response.getStatusCode();
		
		System.out.println("Status Code: " + actualStatus);
		System.out.println("Response: " + response.asString());

		assert actualStatus == expectedStatus : "Expected " + expectedStatus + " but got " + actualStatus;
	}
	
	@Then("Admin receives {int} Status")
	public void admin_receives_status(Integer expectedStatus) {

		int actualStatus = response.getStatusCode();

		System.out.println("Status Code: " + actualStatus);
		System.out.println("Response: " + response.asString());

		assert actualStatus == expectedStatus : "Expected " + expectedStatus + " but got " + actualStatus;
		// ✅ Handle ARRAY response
		if (actualStatus == 201 || actualStatus == 200) {
			
			List<SkillResponse> skillResponses = response.jsonPath().getList("", SkillResponse.class);
			SkillResponse skillResponse = skillResponses.get(0);
			// SkillResponse skillResponse = response.as(SkillResponse.class);

			global.skillId = skillResponse.getSkillId();
			global.skillName = skillResponse.getSkillName();

			System.out.println("Skill ID: " + global.skillId);
			System.out.println("Skill Name: " + global.skillName);
		}
	}
}