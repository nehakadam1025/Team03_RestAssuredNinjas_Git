package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import utils.ApiResources;
import utils.utility;
import pojo.LoginTempData;

public class SkillStepDef extends utility {

    private RequestSpecification req2;
    private Response response;

    
    @Given("Admin creates GET Request for the LMS API endpoint")
    public void admin_creates_get_request_for_the_lms_api_endpoint() throws IOException {

        String token = LoginTempData.getToken(); // token from login feature

        req2 = given()
        		.spec(requestspecification())
        		.contentType(ContentType.JSON)  // must set for JSON payload
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token);
    }

    @When("Admin calls {string} with {string} http request for skill master")
    public void admin_calls_with_http_request_for_skill_master(String resource, String method) {

        ApiResources resourceAPI = ApiResources.valueOf(resource);

        if (method.equalsIgnoreCase("GET")) {
            response = req2.when().get(resourceAPI.getResorce());
        }
    }

    @Then("Admin receives {int} Status")
    public void admin_receives_status(Integer expectedStatus) {

        int actualStatus = response.getStatusCode();
        System.out.println("Status Code: " + actualStatus);
        System.out.println("Response: " + response.asString());

        assert actualStatus == expectedStatus :
                "Expected " + expectedStatus + " but got " + actualStatus;
    }
}