
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

