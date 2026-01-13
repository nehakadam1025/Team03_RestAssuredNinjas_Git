package utils;


import pojo.LoginRequest;

import java.util.Collections;

//import pojo.LoginRequest;
import pojo.UserLogin;
import pojo.UserRequest;
import pojo.UserRoleMap;


public class TestDataBuild {

//    // 🔹 Old method (hardcoded) - keep if needed
//    public LoginRequest userloginPayload() {
//        LoginRequest p = new LoginRequest();
//        p.setUserLoginEmailId("team3@gmail.com");
//        p.setPassword("ApiHackathon2@3");
//        return p;
//    }

    // 🔹 New method for Scenario Outline
    public LoginRequest userloginPayload(String email, String password) {
        LoginRequest p = new LoginRequest();
        p.setUserLoginEmailId(email);
        p.setPassword(password);
        return p;
    }

    
    // 🔹 CREATE USER / ADMIN PAYLOAD (NEW)
    public UserRequest createUserPayload() {

        // Role mapping
        UserRoleMap roleMap = new UserRoleMap();
        roleMap.setRoleId("R01");
        roleMap.setUserRoleStatus("Active");

        // Login info
        UserLogin login = new UserLogin();
        login.setUserLoginEmail("Rinarrh@gmail.com");
        login.setLoginStatus("Active");
        login.setStatus("Active");

        // Main user payload
        UserRequest user = new UserRequest();
        user.setUserComments("APHackathons");
        user.setUserEduPg("Msc");
        user.setUserEduUg("Bsc");
        user.setUserFirstName("Rinarrh");
        user.setUserLastName("Rinarrh");
        user.setUserLinkedinUrl("https://www.linkedin.com/Rinarrh");
        user.setUserLocation("USA");
        user.setUserMiddleName("Rajan");
        user.setUserPhoneNumber("+91 9934567898");
        user.setUserTimeZone("EST");
        user.setUserVisaStatus("H1B");
        user.setUserRoleMaps(Collections.singletonList(roleMap));
        user.setUserLogin(login);

        return user;
    }
}
