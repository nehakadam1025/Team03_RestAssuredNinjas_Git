package stepdefinitions;
import static io.restassured.RestAssured.given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginResponse;
import pojo.LoginTempData;
import utils.ApiResources;
import utils.TestDataBuild;
import utils.utility;
public class UserSteps extends utility {
    // :white_check_mark: NOT static
    private RequestSpecification req;
    private Response response;
    String token1;
    TestDataBuild data = new TestDataBuild();
    private static final Logger log = LogManager.getLogger(UserSteps.class);
    // ---------------- LOGIN ----------------
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
        log.info("Login request created for email: {}", plainTextBody);
    }
    @Given("Admin creates login request with Null body")
    public void admin_creates_login_request_with_null_body() throws IOException {
        req = given()
                .spec(requestspecification())
                .contentType(ContentType.JSON)  // must set for JSON payload
                .accept(ContentType.JSON)
                .body("");
    }
    // ---------------- INVALID BASE URL ----------------
    @Given("Admin creates login request with {string} and {string} invalid baseurl")
    public void admin_creates_login_request_with_and_invalid_baseurl(
            String email,
            String password) throws IOException {
    	req = given()
    	        .spec(requestspecificationWithBaseUrl(
    	                getGlobalValue("baseUrl.wrongbaseUrl")))
    	        .body(data.userloginPayload(email, password));
    }
    // ---------------- FORGOT PASSWORD CONFIRM EMAIL ----------------
    @Given("Admin creates request with valid email {string}")
    public void admin_creates_request_with_valid_email(String emailId) throws IOException {
//        String payload = "{\r\n"
//        		+ "  \"userLoginEmailId\": \"team3@gmail.com\"\r\n"
//        		+ "}\r\n"
//        		+ "      ";
        //.header("Authorization", "Bearer " + token1);
        req = given()
        		.spec(requestspecification())
        		.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(data.confirmEmailPayload(emailId));
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
    @Given("Admin creates login request with Null body body for post forgotPasswordConfirmEmail")
    public void admin_creates_login_request_with_null_body_body_for_post_forgot_password_confirm_email() throws IOException {
    	 req = given()
    			 .spec(requestspecification())
    			 .contentType(ContentType.JSON)
    		        .accept(ContentType.JSON)
         		.body("");
    }
    //----------------------resetPassword---------------------/
    @Given("Admin creates login request with {string} and {string} for resetPassword")
    public void admin_creates_login_request_with_and_for_reset_password(String email, String password) throws IOException {
    	token1 = LoginTempData.getToken();
    	req = given()
    			.spec(requestspecification())
    			.contentType(ContentType.JSON)
    	        .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token1)
                .body(data.userloginPayload(email, password));
    }
    @Given("Admin creates login request with invalid content type for resetPassword")
    public void admin_creates_login_request_with_invalid_content_type_for_reset_password() throws IOException {
    	String plainTextBody = "{\r\n"
        		+ "  \"userLoginEmailId\": \"team3@gmail.com\",\r\n"
        		+ "  \"password\": \"ApiHackathon2@3\"\r\n"
        		+ "}\r\n"
        		+ "";
        req = given()
                .spec(requestspecification())
                .contentType("text/plain")   // force plain text
                .accept(ContentType.JSON)
                .body(plainTextBody);        // STRING body
    }
    //----------------------logoutlms........................./
//    @Given("admin sets authorization to bearer Token with creates request")
//    public void admin_sets_authorization_to_bearer_token_with_creates_request() throws IOException {
//    	token1 = LoginTempData.getToken();
//    	req = given()
//                .spec(requestspecification())
//                .header("Authorization", "Bearer " + token1);
//    }
    @Given("Admin creates request with invalid method")
    public void admin_creates_request_with_invalid_method() throws IOException {
    	token1 = LoginTempData.getToken();
    	req = given()
                .spec(requestspecification())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token1);
    }
    @Given("Admin creates request with invalid endpoint")
    public void admin_creates_request_with_invalid_endpoint() throws IOException {
    	token1 = LoginTempData.getToken();
    	req = given()
    			.spec(requestspecification())
    			.contentType(ContentType.JSON)
    	        .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token1);
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
    	token1 = LoginTempData.getToken();
    	req = given()
    			.spec(requestspecification())
    			.contentType(ContentType.JSON)
    	        .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token1);
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
    // ---------------- STATUS ASSERTION ----------------
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
            LoginTempData.setUserId(loginResp.getUserId());
            System.out.println("Token captured");
            log.info("Expected Status: {}, Actual Status: {}", expectedStatusCode, actualStatusCode);
        }
    }
}