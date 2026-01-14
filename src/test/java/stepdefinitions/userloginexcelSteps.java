package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginTempData;
import utils.ApiResources;
import utils.ExcelUtils;
import utils.TestDataBuild;
import utils.utility;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class userloginexcelSteps extends utility {
	
	RequestSpecification req;
    Response response;
    TestDataBuild data1 = new TestDataBuild();
	
    @Given("Admin logs in using row {int}")
    public void admin_logs_in_using_row(Integer rowNum) throws IOException {

    	String email = ExcelUtils.getCellData("Login", rowNum, 1).trim();
    	String password = ExcelUtils.getCellData("Login", rowNum, 2).trim();
    	System.out.println("Email from Excel: [" + email + "]");
    	System.out.println("Password length: " + password.length());
        req = given()
                .spec(requestspecification())
                .body(data1.userloginPayload(email, password));
    }

    @When("Admin calls {string} with {string} http request")
    public void admin_calls_with_http_request(String resource, String method) {

        ApiResources resourceAPI = ApiResources.valueOf(resource);

        if (method.equalsIgnoreCase("POST")) {
            response = req.when().post(resourceAPI.getResorce());
        }
    }

    @Then("Admin receives status code from row {int}")
    public void admin_receives_status_code_from_row(Integer rowNum) {

    	/*int excelRow = rowNum; // row 1 = first data row

        String statusStr = ExcelUtils.getCellData("Login", excelRow, 3);

        int expectedStatus = Integer.parseInt(statusStr.trim());

        int actualStatus = response.getStatusCode();

        System.out.println("Expected Status: " + expectedStatus);
        System.out.println("Actual Status: " + actualStatus);

       assert actualStatus == expectedStatus :
               "Expected " + expectedStatus + " but got " + actualStatus;*/
    	
    	 String statusStr = ExcelUtils.getCellData("Login", rowNum, 3);
    	    int expectedStatus = Integer.parseInt(statusStr.trim());

    	    int actualStatus = response.getStatusCode();

    	    System.out.println("Expected Status: " + expectedStatus);
    	    System.out.println("Actual Status: " + actualStatus);
    	    System.out.println("Response: " + response.asString());

    	    assertEquals(expectedStatus, actualStatus,
    	            "Status code mismatch");
    	    if (actualStatus == 200) {
    	        String token = response.jsonPath().getString("token");
    	        String userId = response.jsonPath().getString("userId");

    	        LoginTempData.setToken(token);
    	        LoginTempData.setUserId(userId);

    	        System.out.println("✅ Token stored successfully");
    	        System.out.println("✅ UserId stored: " + userId);
    	    }
    
        
}
}