package utils;



import pojo.ConfirmEmailRequest;
import pojo.LoginRequest;


import java.util.Collections;


import pojo.LoginRequest;
import pojo.UserLogin;
//import pojo.UserLogin;
import pojo.UserRequest;
import pojo.UserRoleMap;
import com.github.javafaker.Faker;
import java.io.IOException;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestDataBuild {
	
	

//    // 🔹 Old method (hardcoded) - keep if needed
//    public LoginRequest userloginPayload() {
//        LoginRequest p = new LoginRequest();
//        p.setUserLoginEmailId("team3@gmail.com");
//        p.setPassword("ApiHackathon2@3");
//        return p;
//    }
	//Faker faker = new Faker();
    // 🔹 New method for Scenario Outline
    public LoginRequest userloginPayload(String email, String password) {
        LoginRequest p = new LoginRequest();
        p.setUserLoginEmailId(email);
        p.setPassword(password);
        return p;
    }
    
    // ✅ NEW: Confirm Email payload
    public ConfirmEmailRequest confirmEmailPayload(String emailId) {

        ConfirmEmailRequest cpasswordloginrequest = new ConfirmEmailRequest();
        cpasswordloginrequest.setUserLoginEmailId(emailId);
        return cpasswordloginrequest;
    }


    
 
    
Faker faker = new Faker();
    
//Generate dynamic email with timestamp
public String generateDynamicEmail() {
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    return "user" + timestamp + "@gmail.com";  // ✅ @gmail.com instead of @lmstest.com
}

public String generateDynamicPhone() {
    return "+91 " + faker.number().digits(10);  // ✅ Country code format
}

// Generate dynamic LinkedIn URL
public String generateDynamicLinkedIn() {
    String firstName = faker.name().firstName().toLowerCase();
    String lastName = faker.name().lastName().toLowerCase();
    return "https://www.linkedin.com/in/" + firstName + lastName + faker.number().digits(4);
}
    
//✅ FINAL CORRECT CREATE USER PAYLOAD
public String createUserPayload() {
    
    String dynamicEmail = generateDynamicEmail();
    String dynamicPhone = generateDynamicPhone();
    String dynamicLinkedIn = generateDynamicLinkedIn();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String middleName = faker.name().firstName();
    
    String payload = "{\n" +
            "  \"userComments\": \"APHackathons\",\n" +
            "  \"userEduPg\": \"Msc\",\n" +
            "  \"userEduUg\": \"Bsc\",\n" +
            "  \"userFirstName\": \"" + firstName + "\",\n" +
            "  \"userLastName\": \"" + lastName + "\",\n" +
            "  \"userLinkedinUrl\": \"" + dynamicLinkedIn + "\",\n" +
            "  \"userLocation\": \"USA\",\n" +
            "  \"userMiddleName\": \"" + middleName + "\",\n" +
            "  \"userPhoneNumber\": \"" + dynamicPhone + "\",\n" +
            "  \"userRoleMaps\": [\n" +
            "    {\n" +
            "      \"roleId\": \"R01\",\n" +
            "      \"userRoleStatus\": \"Active\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"userTimeZone\": \"EST\",\n" +
            "  \"userVisaStatus\": \"H1B\",\n" +
            "  \"userLogin\": {\n" +
            "    \"userLoginEmail\": \"" + dynamicEmail + "\",\n" +
            "    \"loginStatus\": \"Active\",\n" +
            "    \"status\": \"Active\"\n" +
            "  }\n" +
            "}";
    
    System.out.println("✅ Generated Email: " + dynamicEmail);
    System.out.println("✅ Generated Phone: " + dynamicPhone);
    System.out.println("📝 Create Payload: " + payload);
    
    return payload;
}



    // ✅ FIXED: Update user payload WITHOUT nested userLogin object
public String updateUserPayload() {
    String dynamicEmail = generateDynamicEmail();
    String dynamicPhone = generateDynamicPhone();
    String dynamicLinkedIn = generateDynamicLinkedIn();
    String firstName = faker.name().firstName() + "Updated";
    String lastName = faker.name().lastName() + "Updated";
    String middleName = faker.name().firstName();
    
    String payload = "{\n" +
            "  \"userComments\": \"Updated APHackathons\",\n" +
            "  \"userEduPg\": \"PhD\",\n" +
            "  \"userEduUg\": \"BTech\",\n" +
            "  \"userFirstName\": \"" + firstName + "\",\n" +
            "  \"userLastName\": \"" + lastName + "\",\n" +
            "  \"userLinkedinUrl\": \"" + dynamicLinkedIn + "\",\n" +
            "  \"userLocation\": \"Canada\",\n" +
            "  \"userMiddleName\": \"" + middleName + "\",\n" +
            "  \"userPhoneNumber\": \"" + dynamicPhone + "\",\n" +
            "  \"userLoginEmail\": \"" + dynamicEmail + "\",\n" +
            "  \"userTimeZone\": \"PST\",\n" +
            "  \"userVisaStatus\": \"GC-EAD\"\n" +  // ✅ Changed from "GreenCard" to "GC-EAD"
            "}";
    
    return payload;
	}
}

