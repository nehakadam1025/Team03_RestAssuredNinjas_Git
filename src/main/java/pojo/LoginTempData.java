package pojo;

public class LoginTempData {
    private static String token;
    private static String userId;

    public static String getToken() {
        return token;
    }

    public static void setToken(String authToken) {
        token = authToken;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String id) {
        userId = id;
    }
}