package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.LoginTempData;
import utils.ApiResources;
import utils.TestDataBuild;
import utils.utility;

public class UserSteps extends utility {

	private RequestSpecification req;
	private ResponseSpecification resSpec;
	RequestSpecification res;
	private Response response;
	private LoginRequest loginRequest;
	String token1;

	TestDataBuild data = new TestDataBuild();

	@Given("Admin creates login request with {string} and {string}")
	public void admin_creates_login_request_with_and(String email, String password) throws IOException {

		req = given().spec(requestspecification()).body(data.userloginPayload(email, password));
	}

	@When("Admin calls {string} with {string} http request")
	public void admin_calls_with_http_request(String resource, String method) {
		ApiResources resorceApi = ApiResources.valueOf(resource);
		System.out.println(resorceApi.getResorce());

		if (method.equalsIgnoreCase("POST"))
			response = req.when().post(resorceApi.getResorce());
		else if (method.equalsIgnoreCase("GET"))
			response = req.when().get(resorceApi.getResorce());

	}

	@Then("Admin receives status code {int}")
	public void admin_receives_status_code(Integer expectedStatusCode) {

		int actualStatusCode = response.getStatusCode();
		System.out.println("Actual Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.asString());
		assert actualStatusCode == expectedStatusCode : "Expected" + expectedStatusCode + " but got" + actualStatusCode;
		// ✅ Capture token ONLY when login succeeds
		if (actualStatusCode == 200) {
			LoginResponse loginResp = response.as(LoginResponse.class);
			LoginTempData.setToken(loginResp.getToken());
			LoginTempData.setUserId(loginResp.getUserId());

			System.out.println("Token captured: " + loginResp.getToken());
		} else {
			System.out.println("Login failed → Token not generated");
		}
	}

	@Given("admin sets authorization to bearer Token with creates request")
	public void admin_sets_authorization_to_bearer_token_with_creates_request() throws IOException {
		token1 = LoginTempData.getToken();
		req = given().spec(requestspecification()).header("Authorization", "Bearer " + token1);
	}

	@Then("Admin recieved {int} status code")
	public void admin_recieved_status_code(int expectedStatusCode) {
		int actualStatusCodel = response.getStatusCode();
		System.out.println("Actual Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.asString());
		assert actualStatusCodel == expectedStatusCode
				: "Expected" + expectedStatusCode + " but got" + actualStatusCodel;

	}

	@Given("Admin creates login request with Null body")
	public void admin_creates_login_request_with_null_body() throws IOException {
		req = given().spec(requestspecification());
	}

//@Given("Admist getting skill master request")
//public void admist_getting_skill_master_request() {
//	System.out.println(" am skillmaster method");
// 
//}
//@When("Admin calls http request for skill")
//public void admin_calls_http_request_for_skill() {
//    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
//}
//@Then("Admin recieved hdjsdl status code")
//public void admin_recieved_hdjsdl_status_code() {
//    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
//}

}