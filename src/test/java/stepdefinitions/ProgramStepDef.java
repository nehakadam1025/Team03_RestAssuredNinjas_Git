package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginTempData;
import utils.ApiResources;
import utils.utility;

public class ProgramStepDef extends utility{
	
	private RequestSpecification req3;
    private Response response;
	
	@Given("Admin creates GET Request for the program module")
	public void admin_creates_get_request_for_the_program_module() throws IOException {
		String token = LoginTempData.getToken(); // token from login feature

        req3 = given()
                .spec(requestspecification())
                .header("Authorization", "Bearer " + token);
	}
	
	@When("Admin calls {string} with {string} http request for Program module")
	public void admin_calls_with_http_request_for_program_module(String resource, String method) {
		ApiResources resourceAPI = ApiResources.valueOf(resource);

        if (method.equalsIgnoreCase("GET")) {
            response = req3.when().get(resourceAPI.getResorce());
        }
        else if (method.equalsIgnoreCase("POST")) {
            response = req3.when().post(resourceAPI.getResorce());
        }
            else if (method.equalsIgnoreCase("PUT")) {
            response = req3.when().put(resourceAPI.getResorce());
        }
	}
	

	
	@Then("Admin receives {int} Status program")
    public void admin_receives_status(Integer expectedStatus) {

        int actualStatus = response.getStatusCode();
        System.out.println("Status Code: " + actualStatus);
        System.out.println("Response: " + response.asString());

        assert actualStatus == expectedStatus :
                "Expected " + expectedStatus + " but got " + actualStatus;
    }

}
