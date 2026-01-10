//package stepdefinitions;
//
//import static io.restassured.RestAssured.given;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
//import io.cucumber.java.en.Then;
//
//import pojo.LoginResponse;
//import pojo.LoginTempData;
//import utils.BaseSpec;
//
//public class UserSteps {
//	RequestSpecification res ;
//	ResponseSpecification resSpec;
//	Response response;
//
//    String loginPayload =
//            "{"
//          + "\"userLoginEmailId\":\"team3@gmail.com\","
//          + "\"password\":\"ApiHackathon2@3\""
//          + "}";
//
//    RequestSpecification req =
//            new RequestSpecBuilder()
//                    .setBaseUri("https://lms-hackathon-nov-2025-8dd40899c026.herokuapp.com/lms")
//                    .setContentType(ContentType.JSON)
//                    .build();
//
//       resSpec = new ResponseSpecBuilder()
//                    .expectStatusCode(200);
//     res = given().spec(req).body(loginPayload);
//
//
////    	String loginPayload =
////    	        "{"
////    	      + "\"userLoginEmailId\":\"team3@gmail.com\","
////    	      + "\"password\":\"ApiHackathon2@3\""
////    	      + "}";
//
//
////        LoginResponse response =
////                given()
////                        .spec(BaseSpec.requestSpec())
////                        .body(loginPayload)
////                .when()
////                        .post("/login")
////                .then()
////                        .statusCode(200)
////                        .extract()
////                        .as(LoginResponse.class);
////
////        // ✅ Store token for next APIs
////        LoginTempData.setToken(response.getToken());
////        LoginTempData.setUserId(response.getUserId());
////
////        System.out.println("Captured Token: " + LoginTempData.getToken());
////}
//
//    @When("Admin calls POST method with valid endpoint")
//    public void admin_calls_post_method_with_valid_endpoint() {
//        // This will be implemented for next API (e.g., create user)
//    	Response response = res.when().post("/login").
//    			then().spec(resSpec).extract().response();
//    }
//
//    @Then("Admin receives {int} created with auto generated token")
//    public void admin_receives_created_with_auto_generated_token(Integer statusCode) {
//        // Assertion will go here
//    	assertequal(response.getStatusCode(),200);
//    }
//}
package stepdefinitions;

import static io.restassured.RestAssured.given;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



import pojo.LoginResponse;
import pojo.LoginTempData;

public class UserSteps {

    // ✅ Class-level variables so all steps can access
    private RequestSpecification req;
    private ResponseSpecification resSpec;
    private Response response;

    private String loginPayload =
            "{"
          + "\"userLoginEmailId\":\"team3@gmail.com\","
          + "\"password\":\"ApiHackathon2@3\""
          + "}";

    @Given("Admin create login request with valid credentials")
    public void admin_create_login_request_with_valid_credentials() {

        // Build request specification
        req = new RequestSpecBuilder()
                .setBaseUri("https://lms-hackathon-nov-2025-8dd40899c026.herokuapp.com/lms")
                .setContentType(ContentType.JSON)
                .build();

        // Build response specification
        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    @When("Admin calls POST method with valid endpoint")
    public void admin_calls_post_method_with_valid_endpoint() {

        // 🔥 Execute login API
        response = given()
                        .spec(req)
                        .body(loginPayload)
                   .when()
                        .post("/login")
                   .then()
                        .spec(resSpec)
                        .extract()
                        .response();

        // 🔐 Store token for next APIs
        LoginResponse loginResp = response.as(LoginResponse.class);
        LoginTempData.setToken(loginResp.getToken());
        LoginTempData.setUserId(loginResp.getUserId());

        System.out.println("Token captured: " + LoginTempData.getToken());
    }

    
    @Then("Admin receives {int} created with auto generated token")
    public void admin_receives_created_with_auto_generated_token(Integer statusCode) {
        System.out.println("Actual Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // ✅ JUnit 5 assertion
//        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

}