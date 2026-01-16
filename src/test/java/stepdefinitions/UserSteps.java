package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginResponse;
import pojo.LoginTempData;
import utils.ApiResources;
import utils.GlobalTestData;
import utils.TestDataBuild;
import utils.utility;

public class UserSteps extends utility {

// :white_check_mark: NOT static
private RequestSpecification req;
private Response response;
public static String token;
TestDataBuild data = new TestDataBuild();
private static final Logger log = LogManager.getLogger(UserSteps.class);
 //---------------- LOGIN ----------------

        String filePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "postLogin";

       
    @Given("Admin performs login test for Excel row {string}")
    public void admin_performs_login_test_for_excel_row(String rowNumber) throws IOException {
    	GlobalTestData.getInstance().loadExcelData(filePath, sheetName);

    	// Get row data by TestcaseId / rowNumber
    	    Map<String, String> row = GlobalTestData.getInstance().getDataByTestcaseId(rowNumber);
    	    if (row == null) {
    	        throw new RuntimeException("No Excel data found for row: " + rowNumber);
    	    }

    	    // Extract email and password dynamically from Excel
    	    String email = row.getOrDefault("email", "").trim();
    	    String password = row.getOrDefault("password", "").trim();

    	    // Create login request dynamically (using your existing method)
    	    req = given()
    	            .spec(requestspecification())
    	            .contentType(ContentType.JSON)
    	            .accept(ContentType.JSON)
    	            .body(data.userloginPayload(email, password));

    	    log.info("Login request created for Excel row {} | Email: {}",req, rowNumber);
    	

    }
    @Given("Admin creates login request with {string} and {string}")
    public void admin_creates_login_request_with_and(String email, String password) throws IOException {
        req = given()
                .spec(requestspecification())
                .contentType(ContentType.JSON)  // must set for JSON payload
                .accept(ContentType.JSON)
               .body(data.userloginPayload(email, password));
        log.info("Login request created for email: {}", email,password);
    }
    @Given("Admin creates login request with invalid content type")
    public void admin_creates_login_request_with_invalid_content_type() throws IOException {
        String plainTextBody =
                "{ \"userLoginEmailId\": \"team3@gmail.com\", \"password\": \"ApiHackathon2@3\" }";
        req = given()
                .spec(requestspecification())
                .contentType("text/plain")
                .accept(ContentType.JSON)
                .body(plainTextBody);
        log.info("Login request created for email: {}", req);
    }
    @Given("Admin creates login request with Null body")
    public void admin_creates_login_request_with_null_body() throws IOException {
        req = given()
                .spec(requestspecification())
                .contentType(ContentType.JSON)  // must set for JSON payload
                .accept(ContentType.JSON)
                .body("");
    }
//    // ---------------- INVALID BASE URL ----------------
    @Given("Admin creates login request with {string} and {string} invalid baseurl")
    public void admin_creates_login_request_with_and_invalid_baseurl(String email,String password) throws IOException {
    	req = given()
    	        .spec(requestspecificationWithBaseUrl(
    	                getGlobalValue("baseUrl.wrongbaseUrl")))
    	        .body(data.userloginPayload(email, password));
    	log.info("Request data", req);
    }
    
 // ---------------------------------------------------
    // LOGIN using Excel row number (normal or invalid endpoint)
    // ---------------------------------------------------
    @Given("Admin performs login test for Excel row {string} for invalid endpoint")
    public void admin_performs_login_test_for_excel_row_for_invalid_endpoint(String rowNumber) throws IOException {

       
        Map<String, String> row =
                GlobalTestData.getInstance().getDataByTestcaseId(rowNumber);

        if (row == null) {
            throw new RuntimeException("No Excel data found for row: " + rowNumber);
        }

        String email = row.getOrDefault("email", "").trim();
        String password = row.getOrDefault("password", "").trim();

        req = given()
                .spec(requestspecification())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(data.userloginPayload(email, password));

        log.info("Login request created | Row: {} | Email: {}", rowNumber, email, password);
    }
    
    @Given("Admin creates login request with invalid method")
    public void admin_creates_login_request_with_invalid_method() throws IOException {
    	
   	 String plainTextBody = "{\r\n"
 		+ "  \"userLoginEmailId\": \"team3@gmail.com\"\r\n"
 		+ "}\r\n"
 		+ "      ";
 req = given()
         .spec(requestspecification())
         .contentType("text/plain")   // force plain text
         .accept(ContentType.JSON)
         .body(plainTextBody);        // STRING body
       
    }

    // ---------------- FORGOT PASSWORD CONFIRM EMAIL ----------------
    @Given("Admin creates request with valid email {string}")
    public void admin_creates_request_with_valid_email(String emailId) throws IOException {

        req = given()
        		.spec(requestspecification())
        		.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(data.confirmEmailPayload(emailId));
    }
    
    @Given("Admin performs forgot password confirm email test for Excel row {string}")
    public void admin_performs_forgot_password_confirm_email_test_for_excel_row(String rowNumber) throws IOException {

        Map<String, String> row =
                GlobalTestData.getInstance().getDataByTestcaseId(rowNumber);

        if (row == null) {
            throw new RuntimeException("No Excel data found for row: " + rowNumber);
        }

        String email = row.getOrDefault("email", "").trim();

        req = given()
                .spec(requestspecification())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(data.confirmEmailPayload(email));

        log.info("Forgot password confirm email request created | Row: {} | Email: {}", rowNumber, email);
    }
    @Given("Admin creates login request with invalid content type for forgotPassword")
    public void admin_creates_login_request_with_invalid_content_type_for_forgot_password() throws IOException {
    	 String plainTextBody = "{\r\n"
    	 		+ "  \"userLoginEmailId\": \"team3@gmail.com\"\r\n"
    	 		+ "}\r\n"
    	 		+ "      ";
         req = given()
                 .spec(requestspecification())
                 .contentType("text/plain")   // force plain text
                 .accept(ContentType.JSON)
                 .body(plainTextBody);        // STRING body
    }
    @Given("Admin creates login request with {string} with invalid endpoint")
    public void admin_creates_login_request_with_with_invalid_endpoint(String emailId) throws IOException {
    	req = given()
    			.spec(requestspecification())
    			.contentType(ContentType.JSON)
    	        .accept(ContentType.JSON)
                .body(data.confirmEmailPayload(emailId));
    }
    @Given("Admin creates login request with Null body for post forgotPasswordConfirmEmail")
    public void admin_creates_login_request_with_null_body_for_post_forgot_password_confirm_email() throws IOException {
    	 req = given()
    			 .spec(requestspecification())
    			 .contentType(ContentType.JSON)
    		        .accept(ContentType.JSON)
         		.body("");
    }
//    //----------------------resetPassword---------------------/
    @Given("Admin creates login request with {string} and {string} for resetPassword")
    public void admin_creates_login_request_with_and_for_reset_password(String email, String password) throws IOException {
    	token = LoginTempData.getToken();
    	req = given()
    			.spec(requestspecification())
    			.contentType(ContentType.JSON)
    	        .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(data.userloginPayload(email, password));
    }
    
    @Given("Admin performs resetPassword test {string}")
    public void admin_performs_reset_password_test(String rowNumber) throws IOException {
    	String token = LoginTempData.getToken();
        Map<String, String> row =
                GlobalTestData.getInstance().getDataByTestcaseId(rowNumber);

        if (row == null) {
            throw new RuntimeException("No Excel data found for row: " + rowNumber);
        }

        String email = row.getOrDefault("email", "").trim();
        String password = row.getOrDefault("password", "").trim();

        req = given()
                .spec(requestspecification())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                 .header("Authorization", "Bearer " + token)
                 .body(data.userloginPayload(email, password));
        
        System.out.println(token);
         
         
         log.info("resetPassword | Row: {} | Email: {} | PWD :{}", rowNumber, email, password);

    }
    @Given("Admin creates login request with invalid content type for resetPassword")
    public void admin_creates_login_request_with_invalid_content_type_for_reset_password() throws IOException {
    	
    	String token = LoginTempData.getToken();
    	String plainTextBody = "{\r\n"
        		+ "  \"userLoginEmailId\": \"team3@gmail.com\",\r\n"
        		+ "  \"password\": \"ApiHackathon2@3\"\r\n"
        		+ "}\r\n"
        		+ "";
        req = given()
                .spec(requestspecification())
                .contentType("text/plain")   // force plain text
                .accept(ContentType.JSON)
                .body(plainTextBody)
                .header("Authorization", "Bearer " + token);
    }

    @Given("Admin creates request with invalid method")
    public void admin_creates_request_with_invalid_method() throws IOException {
    	token = LoginTempData.getToken();
    	req = given()
                .spec(requestspecification())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token);
    }
    @Given("Admin creates request with invalid endpoint")
    public void admin_creates_request_with_invalid_endpoint() throws IOException {
    	token = LoginTempData.getToken();
    	req = given()
    			.spec(requestspecification())
    			.contentType(ContentType.JSON)
    	        .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token);
    }
    @Given("Admin creates request with no auth")
    public void admin_creates_request_with_no_auth() throws IOException {
    	req = given()
    			.spec(requestspecification())
    			.contentType(ContentType.JSON)
    	        .accept(ContentType.JSON);
    }
    @Given("Check if admin able to logout to LMS Application")
    public void check_if_admin_able_to_logout_to_lms_application() throws IOException {
    	token = LoginTempData.getToken();
    	req = given()
    			.spec(requestspecification())
    			.contentType(ContentType.JSON)
    	        .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token);
    }
    // ---------------- COMMON API CALL ----------------
    @When("Admin calls {string} with {string} http request")
    public void admin_calls_with_http_request(String resource, String method) {
        ApiResources resourceAPI = ApiResources.valueOf(resource);
        System.out.println("Calling API: " + resourceAPI.getResorce());
        if (method.equalsIgnoreCase("POST")) {
            response = req.when().post(resourceAPI.getResorce());
        } else if (method.equalsIgnoreCase("GET")) {
            response = req.when().get(resourceAPI.getResorce());
        }
    }
//     ---------------- STATUS ASSERTION ----------------
    @Then("Admin receives status code {int}")
    public void admin_receives_status_code(Integer expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        System.out.println("Actual Status Code: " + actualStatusCode);
        System.out.println("Response Body: " + response.asString());
        assert actualStatusCode == expectedStatusCode :
                "Expected " + expectedStatusCode + " but got " + actualStatusCode;
        // Capture token only for successful login
        if (actualStatusCode == 200 && response.asString().contains("token")) {
            LoginResponse loginResp = response.as(LoginResponse.class);
            LoginTempData.setToken(loginResp.getToken());
          //  LoginTempData.setUserId(loginResp.getUserId());
            System.out.println("Token captured");
            log.info("Expected Status: {}, Actual Status: {}", expectedStatusCode, actualStatusCode);
        }
    }

    @Given("Check if admin able to logout to with no token")
    public void check_if_admin_able_to_logout_to_with_no_token() throws IOException {
    	req = given()
    			.spec(requestspecification())
    			.contentType(ContentType.JSON)
    	        .accept(ContentType.JSON);
               
    }
}