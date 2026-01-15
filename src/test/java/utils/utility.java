
package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Properties;

public class utility {

    private static final Logger log = LogManager.getLogger(utility.class);
    private static RequestSpecification req;

    public RequestSpecification requestspecification() throws IOException {

        if (req == null) {
            PrintStream logStream = new PrintStream(new FileOutputStream("logs/logging.txt", true));
            req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(logStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logStream))
                    .build();
            log.info("Request specification created with baseUrl: {}", getGlobalValue("baseUrl"));
        }
        return req;
    }

    public RequestSpecification requestspecificationWithBaseUrl(String baseUrl) throws IOException {

        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new RuntimeException("Base URL is NULL. Check config.properties key name");
        }

        PrintStream logStream = new PrintStream(new FileOutputStream("logs/logging.txt", true));
        log.info("Request specification created with custom baseUrl: {}", baseUrl);

        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addFilter(RequestLoggingFilter.logRequestTo(logStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(logStream))
                .build();
    }

    public String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        try (InputStream fis = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (fis == null) throw new RuntimeException("config.properties not found in classpath");
            prop.load(fis);
        }
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        return io.restassured.path.json.JsonPath.from(resp).get(key).toString();
    }
}


//package utils;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.util.Properties;
//
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.filter.log.RequestLoggingFilter;
//import io.restassured.filter.log.ResponseLoggingFilter;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//
//public class utility {
//
//    // ✅ NO static RequestSpecification
//
//    public RequestSpecification requestspecification() throws IOException {
//
//        PrintStream log = new PrintStream(
//                new FileOutputStream("logging.txt", true)
//        );
//
//        return new RequestSpecBuilder()
//                .setBaseUri(getGlobalValue("baseUrl"))
//                .setContentType(ContentType.JSON)
//                .addFilter(RequestLoggingFilter.logRequestTo(log))
//                .addFilter(ResponseLoggingFilter.logResponseTo(log))
//                .build();
//    }
//
//    // ✅ Used for WRONG / custom base URLs
//    public RequestSpecification requestspecificationWithBaseUrl(String baseUrl) throws IOException {
//
//        PrintStream log = new PrintStream(
//                new FileOutputStream("logging.txt", true)
//        );
//
//        return new RequestSpecBuilder()
//                .setBaseUri(baseUrl)
//                .setContentType(ContentType.JSON)
//                .addFilter(RequestLoggingFilter.logRequestTo(log))
//                .addFilter(ResponseLoggingFilter.logResponseTo(log))
//                .build();
//    }
//
//    // ✅ Config reader
//    public String getGlobalValue(String key) throws IOException {
//
//        Properties prop = new Properties();
//
//        try (InputStream fis = getClass()
//                .getClassLoader()
//                .getResourceAsStream("config.properties")) {
//
//            if (fis == null) {
//                throw new RuntimeException("config.properties not found in classpath");
//            }
//            prop.load(fis);
//        }
//        return prop.getProperty(key);
//    }
//
//    // ✅ JSON helper
//    public String getJsonPath(Response response, String key) {
//
//        JsonPath js = new JsonPath(response.asString());
//        return js.get(key).toString();
//    }
//}
//
////package utils;
////
//////import java.io.FileInputStream;
////import java.io.FileOutputStream;
////import java.io.IOException;
////
////import java.io.InputStream;
////
////import java.io.PrintStream;
////import java.util.Properties;
////
//////import io.restassured.RestAssured;
////import io.restassured.builder.RequestSpecBuilder;
////import io.restassured.filter.log.RequestLoggingFilter;
////import io.restassured.filter.log.ResponseLoggingFilter;
////import io.restassured.http.ContentType;
////import io.restassured.path.json.JsonPath;
////import io.restassured.response.Response;
////import io.restassured.specification.RequestSpecification;
////
////public class utility {
////	
////	public static RequestSpecification req;
////
////    public RequestSpecification requestspecification() throws IOException {
////
////
////
////        if (req==null)
////        {	
////        	PrintStream log = new PrintStream(
////                    new FileOutputStream("logging.txt")
////            );
////          req = new RequestSpecBuilder()
////          .setBaseUri(getGlobalValue("baseUrl"))
////          .addFilter(RequestLoggingFilter.logRequestTo(log))
////          .addFilter(ResponseLoggingFilter.logResponseTo(log))
////          .build();
////          
////          return req;
////        }
////        return req;
////
////               
////    }
////	
////    public RequestSpecification requestspecificationWithBaseUrl(String baseUrl) throws IOException {
////
////        PrintStream log = new PrintStream(
////                new FileOutputStream("logging.txt", true)
////        );
////
////        return new RequestSpecBuilder()
////                .setBaseUri(baseUrl)
////                .addFilter(RequestLoggingFilter.logRequestTo(log))
////                .addFilter(ResponseLoggingFilter.logResponseTo(log))
////                .build();
////    }
////
////
////
////    
////
////    public String getGlobalValue(String key) throws IOException {
////        Properties prop = new Properties();
////
////        try (InputStream fis = getClass()
////                .getClassLoader()
////                .getResourceAsStream("config.properties")) {
////
////            if (fis == null) {
////                throw new RuntimeException("config.properties not found in classpath");
////            }
////
////            prop.load(fis);
////        }
////
////        return prop.getProperty(key);
////    }
////
////    public String getJsonPath(Response response, String key)
////    {
////    	String resp = response.asString();
////    	JsonPath js = new JsonPath(resp);
////    	return js.get(key).toString();
////    }
////}
//
////package utils;
////
////import java.io.FileOutputStream;
////import java.io.IOException;
////import java.io.InputStream;
////import java.io.PrintStream;
////import java.util.Properties;
////
////import io.restassured.builder.RequestSpecBuilder;
////import io.restassured.filter.log.RequestLoggingFilter;
////import io.restassured.filter.log.ResponseLoggingFilter;
////import io.restassured.response.Response;
////import io.restassured.specification.RequestSpecification;
////import io.restassured.path.json.JsonPath;
////
////public class utility {
////
////    // Cached default request spec
////    private static RequestSpecification req;
////
////    /**
////     * Returns the default RequestSpecification using baseUrl from config.properties
////     */
////    public RequestSpecification requestspecification() throws IOException {
////        if (req == null) {
////            String baseUrl = getGlobalValue("baseUrl");
////            req = buildRequestSpecification(baseUrl, "logging.txt");
////        }
////        return req;
////    }
////
////    /**
////     * Returns a RequestSpecification for a dynamic baseUrl (for negative tests)
////     * @param baseUrl - the URL you want to use instead of the default
////     */
////    public RequestSpecification requestspecification(String baseUrl) throws IOException {
////        return buildRequestSpecification(baseUrl, "dynamic-logging.txt");
////    }
////
////    /**
////     * Common method to build a RequestSpecification with logging
////     */
////    private RequestSpecification buildRequestSpecification(String baseUrl, String logFile) throws IOException {
////        PrintStream log = new PrintStream(new FileOutputStream(logFile, true)); // append mode
////        return new RequestSpecBuilder()
////                .setBaseUri(baseUrl)
////                .addFilter(RequestLoggingFilter.logRequestTo(log))
////                .addFilter(ResponseLoggingFilter.logResponseTo(log))
////                .build();
////    }
////
////    /**
////     * Read values from config.properties
////     */
////    public String getGlobalValue(String key) throws IOException {
////        Properties prop = new Properties();
////        try (InputStream fis = getClass()
////                .getClassLoader()
////                .getResourceAsStream("config.properties")) {
////            if (fis == null) {
////                throw new RuntimeException("config.properties not found in classpath");
////            }
////            prop.load(fis);
////        }
////        return prop.getProperty(key);
////    }
////
////    /**
////     * Extract value from JSON response using JsonPath
////     */
////    public String getJsonPath(Response response, String key) {
////        String resp = response.asString();
////        JsonPath js = new JsonPath(resp);
////        return js.get(key).toString();
////    }
////}
//
////// ✅ Append mode
////PrintStream log = new PrintStream(
////      new FileOutputStream("logging.txt", true)
////);
//// ✅ Append mode
//
//
//// RestAssured.baseURI = "https://lms-hackathon-nov-2025-8dd40899c026.herokuapp.com/lms";
//
////return new RequestSpecBuilder()
////.setBaseUri(RestAssured.baseURI)
////.setContentType(ContentType.JSON)
////.addFilter(RequestLoggingFilter.logRequestTo(log))
////.addFilter(ResponseLoggingFilter.logResponseTo(log))
////.build();
//
//
////public String getGlobalValue(String key) throws IOException
////{
////	Properties prop = new Properties();
////	FileInputStream fis = new FileInputStream("C:\\Users\\Home\\eclipsenew\\Team3_RestAssuredNinjas\\src\\test\\resources\\config.properties");
////  prop.load(fis);
////  return prop.getProperty(key);
////  //return prop;
////}
//
////public String getGlobalValue(String key) throws IOException
////{
////	Properties prop = new Properties();
////	FileInputStream fis = new FileInputStream("C:\\Users\\Home\\eclipsenew\\Team3_RestAssuredNinjas\\src\\test\\resources\\config.properties");
////  prop.load(fis);
////  return prop.getProperty(key);
////  //return prop;
////}