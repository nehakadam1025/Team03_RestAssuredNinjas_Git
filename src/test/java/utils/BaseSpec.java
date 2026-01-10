package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class BaseSpec {
    private static RequestSpecification requestSpec;

    public static RequestSpecification requestSpec() {

        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri("https://lms-hackathon-nov-2025-8dd40899c026.herokuapp.com/lms")   // 🔴 replace with actual base URL
                    .setContentType("application/json")
                    .build();
        }

        return requestSpec;
    }
} 
