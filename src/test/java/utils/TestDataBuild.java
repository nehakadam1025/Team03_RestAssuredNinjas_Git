package utils;

import pojo.LoginRequest;

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
}
