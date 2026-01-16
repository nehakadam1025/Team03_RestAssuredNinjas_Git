
package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginTempData;
import utils.ApiResources;
import utils.utility;
import pojo.LoginRequest;
import pojo.programRequest;
import pojo.ProgramResponse;
import utils.global;

public class ProgramStepDef extends utility {

	private RequestSpecification req3;
	//private RequestSpecification req;
	private programRequest progreq;
	private Response response;
	private String endpoint;
	

	@Given("Admin creates GET Request for the program module")
	public void admin_creates_get_request_for_the_program_module() throws IOException {
		String token = LoginTempData.getToken(); // token from login feature

		req3 = given().spec(requestspecification()).header("Authorization", "Bearer " + token);
	}

	@Given("Admin creates GET Request for the program module without Authorization")
	public void admin_creates_get_request_without_authorization() throws IOException {

		req3 = given().spec(requestspecification()); // NO Authorization header
	}

	/*
	 * @Given("Admin creates GET Request for the LMS API with invalid baseURI")
	 * public void admin_creates_get_request_with_invalid_baseuri() throws
	 * IOException { req3=given().spec(requestspecification().baseUri(
	 * "https://invalid-base-uri.com"));
	 * 
	 * }
	 */

	@Given("Admin creates POST Request for the program module")
	public void admin_creates_post_request_for_the_program_module() throws IOException {
		programRequest programPayload = new programRequest("NewProgramTeam3a", "Team3Automatica", "Active");
		String token = LoginTempData.getToken();
		req3 = given().spec(requestspecification()).header("Authorization", "Bearer " + token).body(programPayload);

	}

	@Given("Admin creates POST Request for the program module without Authorization")
	public void admin_creates_post_request_without_authorization() throws IOException {

		req3 = given().spec(requestspecification()); // NO Authorization header
	}

	@Given("Admin creates POST Request for the program module with existing program name")
	public void admin_creates_post_request_with_existing_program_name() throws IOException {

	    programRequest programPayload = new programRequest(
	            "Duplicate program description",
	            global.programName,   // 🔥 already existing name
	            "Active"
	    );

	    String token = LoginTempData.getToken();

	    req3 = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(programPayload);
	}
	
	@Given("Admin creates POST Request for the program module with invalid request body")
	public void admin_creates_post_request_with_invalid_request_body() throws IOException {

	    String token = LoginTempData.getToken();

	    // ❌ Invalid request body
	    String invalidPayload =
	            "{"
	          + "\"programName\": \"@\","
	          + "\"programDescription\": \"Invalid Body\","
	          + "\"programStatus\": \"Activehgg\""
	          + "}";
	   

	    req3 = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(invalidPayload);
	}
	
	@Given("Admin creates POST Request for the program module with empty request body")
	public void admin_creates_post_request_for_the_program_module_with_empty_request_body() throws IOException  {
		String token = LoginTempData.getToken();

	    // ❌ Invalid request body (missing programName & programStatus)
		 String invalidPayload = "{ \"programDescription\": \"Invalid Body\" }";;

	    req3 = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(invalidPayload);
	}

	@Given("Admin creates POST Request for the program module with request body missing additional field")
	public void admin_creates_post_request_with_missing_additional_field() throws IOException {

	    String token = LoginTempData.getToken();

	    // Missing additional/optional fields like creationTime, lastModTime
	    String payload =
	            "{"
	          + "\"programName\": \"Programlds\","
	          + "\"programDescription\": \"Valeextrafields\","
	          + "\"programStatus\": \"Active\","
	          +"\"creationTime\":\" \""
	          + "}";

	    req3 = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(payload);
	}
	
	@Given("Admin creates PUT Request for the program module")
	public void admin_creates_put_request_for_the_program_module() throws IOException {

	    String token = LoginTempData.getToken();

	    // Updated payload
	    String updatePayload =
	            "{"
	          + "\"programName\": \"UpdatNewProgramTe3\"," 
	          + "\"programDescription\": \"UpdatedviPUT\","
	          + "\"programStatus\": \"active\""
	          + "}";

	    req3 = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(updatePayload);
	}
	
	@Given("Admin creates PUT Request for the program module with invalid body")
	public void admin_creates_put_request_with_invalid_body() throws IOException {

	    String token = LoginTempData.getToken();

	    // ❌ Invalid body: empty programName + invalid programStatus
	    String invalidPayload =
	            "{"
	          + "\"programName\": \"\","
	          + "\"programDescription\": \"Invalid PUT body\","
	          + "\"programStatus\": \"INVALID\""
	          + "}";

	    req3 = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(invalidPayload);
	}
	
	@Given("Admin creates PUT Request for the program module with missing mandatory fields")
	public void admin_creates_put_request_with_missing_mandatory_fields() throws IOException {

	    String token = LoginTempData.getToken();

	    // ❌ Missing programName & programStatus
	    String invalidPayload =
	            "{"
	          + "\"programDescription\": \"Missing mandatory fields\""
	          + "}";

	    req3 = given()
	            .spec(requestspecification())
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(invalidPayload);
	}

	@Given("Admin creates PUT Request for the program module without Authorization")
	public void admin_creates_put_request_without_authorization() throws IOException {

		req3 = given().spec(requestspecification()); // NO Authorization header
	}
	

	 // -------------------- AUTHORIZATION --------------------
    @Given("Admin sets Authorization as {string}")
    public void admin_sets_authorization_as(String authType) throws IOException {

        if (authType.equalsIgnoreCase("Valid")) {
            String token = LoginTempData.getToken();
            req3 = given()
                    .spec(requestspecification())
                    .header("Authorization", "Bearer " + token);
        } else if (authType.equalsIgnoreCase("NoAuth")) {
            req3 = given()
                    .spec(requestspecification());
        }
    }
	
 // -------------------- REQUEST BODY --------------------
    @Given("Admin creates PUT Request for the LMS API with {string}")
    public void admin_creates_put_request_with_body(String requestBodyType) {

        switch (requestBodyType.toLowerCase()) {

            case "valid request body":
                progreq = new programRequest(
                        "javaee",
                        "Updatedputdescription",
                        "Active"
                );
                break;

            case "missing mandatory fields":
                progreq = new programRequest(
                        " ",
                        "Only Description",
                        " "
                );
                break;

            case "invalid values":
                progreq = new programRequest(
                        "",
                        "",
                        "123"
                );
                break;

            case "invalid program description":
                progreq = new programRequest(
                        "ValidpName",
                        "",
                        "Active"
                );
                break;

            case "valid program name and status only":
                progreq = new programRequest(
                        "Updated Program Name",
                        "t",
                        "Inactive"
                );
                break;

            default:
                throw new IllegalArgumentException("Invalid RequestBodyType: " + requestBodyType);
        }

        req3 = req3.body(progreq);
    }

    
 // -------------------- AUTHORIZATION for delete --------------------
    @Given("Admin sets Authorization as {string} for delete module")
    public void admin_sets_authorization_as_for_delete_module(String authType) throws IOException {

        if (authType.equalsIgnoreCase("Valid")) {
            String token = LoginTempData.getToken();
            req3 = given()
                    .spec(requestspecification())
                    .header("Authorization", "Bearer " + token);
        } else if (authType.equalsIgnoreCase("NoAuth")) {
            req3 = given()
                    .spec(requestspecification()); // No Authorization header
        }
    }
 // -------------------- PREPARE DELETE REQUEST --------------------
    @Given("Admin prepares DELETE Request for the LMS API with {string} program name")
    public void admin_prepares_delete_request_with_program_name(String programNameType) {

        String programName;

        if (programNameType.equalsIgnoreCase("valid")) {
            programName = global.programName;   // created earlier
        } else {
            programName = "invalid_program_name";
        }

        // Store endpoint with program name for DELETE
        ApiResources resource = ApiResources.deletebyname;
        endpoint = resource.getResorce() + "/" + programName;
    }
    
    
    @Given("Admin prepares DELETE Request for the LMS API with {string} program ID")
    public void admin_prepares_delete_request_with_program_id(String programIdType) {

        String programId;

        if (programIdType.equalsIgnoreCase("valid")) {
            programId = String.valueOf(global.programId);  // ✅ existing program
        } else {
            programId = "999999"; // ❌ non-existing program ID
        }

        ApiResources resource = ApiResources.deletebyid;
        endpoint = resource.getResorce() + "/" + programId;
    }
     
    
    
    
 // -------------------- SEND DELETE REQUEST --------------------
    @When("Admin sends HTTPS DELETE Request with program name endpoint")
    public void admin_sends_https_delete_request_with_program_name_endpoint() {

        if (req3 == null) {
            throw new IllegalStateException("RequestSpecification is null. Authorization step missing.");
        }

        response = req3.when().delete(endpoint);
    }

   
    
    @When("Admin sends HTTPS DELETE Request with program ID endpoint")
    public void admin_sends_https_delete_request_with_program_id_endpoint() {

        if (req3 == null) {
            throw new IllegalStateException("Authorization step is missing.");
        }

        response = req3.when().delete(endpoint);
    }

    // -------------------- SEND PUT REQUEST --------------------
    @When("Admin sends HTTPS PUT Request with {string} program name endpoint")
    public void admin_sends_put_request_with_program_name(String programNameType) {

        ApiResources resource = ApiResources.updateprogrambyname;

        String programName;
        
        if (programNameType.equalsIgnoreCase("valid")) {
            programName = global.programName; // saved from create program
        } else {
            programName = "invalidprogram";
        }

        response = req3.when()
                .put(resource.getResorce() + "/" + programName);
    }
    
	@When("Admin calls {string} with {string} http request for Program module for put")
	public void admin_calls_with_http_request_for_program_module_for_put(String resource, String method) {

	    ApiResources resourceAPI = ApiResources.valueOf(resource);
	    String endpoint = resourceAPI.getResorce();

	    // Append programId for PUT
	    if (method.equalsIgnoreCase("PUT")) {
	        //endpoint = endpoint + "/" + global.programId;
	        response = req3.when().put(endpoint);
	    }
	    else if (method.equalsIgnoreCase("GET")) {
	        response = req3.when().get(endpoint);
	    }
	    else if (method.equalsIgnoreCase("POST")) {
	        response = req3.when().post(endpoint);
	    }
	}
	
	
	/*
	@When("Admin calls {string} with {string} http request for Program by name")
	public void admin_calls_with_request_for_program_by_name(String resource, String method) {

	    ApiResources resourceAPI = ApiResources.valueOf(resource);

	    if (method.equalsIgnoreCase("PUT")) {
	        String endpoint = resourceAPI.getResorce() + "/" + global.programName;
	        response = req3.when().put(endpoint);
	    }
	}
	*/
	
	
	@When("Admin calls {string} with {string} http request for Program module")
	public void admin_calls_with_http_request_for_program_module(String resource, String method) {
		ApiResources resourceAPI = ApiResources.valueOf(resource);

		if (method.equalsIgnoreCase("GET")) {
			response = req3.when().get(resourceAPI.getResorce());
		} else if (method.equalsIgnoreCase("POST")) {
			response = req3.when().post(resourceAPI.getResorce());
		} else if (method.equalsIgnoreCase("PUT")) {
			response = req3.when().put(resourceAPI.getResorce());
		}
	}

	@When("Admin calls {string} with valid program ID using {string} http request")
	public void admin_calls_with_valid_program_id(String resource, String method) throws IOException {
		ApiResources resourceAPI = ApiResources.valueOf(resource);

		if (method.equalsIgnoreCase("GET")) {
			response = req3.when().get(resourceAPI.getResorce());
		} else if (method.equalsIgnoreCase("POST")) {
			response = req3.when().post(resourceAPI.getResorce());
		} else if (method.equalsIgnoreCase("PUT")) {
			response = req3.when().put(resourceAPI.getResorce());
		}
	}

	
	@Then("Admin receives {int} Status program")
	public void admin_receives_status(Integer expectedStatus) {

		int actualStatus = response.getStatusCode();
		
		System.out.println("Status Code: " + actualStatus);
		System.out.println("Response: " + response.asString());

		assert actualStatus == expectedStatus : "Expected " + expectedStatus + " but got " + actualStatus;
	}


	@Then("Admin receives {int} Status code with respose body")
	public void admin_receives_status_code_with_response_body(Integer expectedStatus) {

		int actualStatus = response.getStatusCode();

		System.out.println("Status Code: " + actualStatus);
		System.out.println("Response: " + response.asString());

		assert actualStatus == expectedStatus : "Expected " + expectedStatus + " but got " + actualStatus;

		// Deserialize response into POJO
		ProgramResponse programResponse = response.as(ProgramResponse.class);

		// Save programId globally
		global.programId = programResponse.getProgramId();
		global.programName = programResponse.getProgramName();

		System.out.println("Created Program ID: " + global.programId);
	}
}
