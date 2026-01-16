package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utility {

	public static RequestSpecification req;

	public RequestSpecification requestspecification() throws IOException {

//        // ✅ Append mode
//        PrintStream log = new PrintStream(
//                new FileOutputStream("logging.txt", true)
//        );
		// ✅ Append mode

		// RestAssured.baseURI =
		// "https://lms-hackathon-nov-2025-8dd40899c026.herokuapp.com/lms";

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();

			return req;
		}
		return req;
//        return new RequestSpecBuilder()
//                .setBaseUri(RestAssured.baseURI)
//                .setContentType(ContentType.JSON)
//          .addFilter(RequestLoggingFilter.logRequestTo(log))
//          .addFilter(ResponseLoggingFilter.logResponseTo(log))
//          .build();

	}

	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Ashish\\git\\Team03_RestAssuredNinjas_Git\\src\\test\\resources\\config.properties");
		prop.load(fis);
		return prop.getProperty(key);
		// return prop;
	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
