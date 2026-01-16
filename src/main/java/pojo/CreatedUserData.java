package pojo;

public class CreatedUserData {

	private static String userId;

    public static String getUserId() {
        System.out.println("📖 CreatedUserData.getUserId() called - returning: " + userId);
        return userId;
    }

    public static void setUserId(String id) {
    	System.out.println("📝 CreatedUserData.setUserId() called - OLD value: " + userId + " | NEW value: " + id);
        userId = id;
    }
}
