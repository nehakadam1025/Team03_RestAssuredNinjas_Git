package utils;

public enum ApiResources {

	postLoginApi("/login"),
	postLoginWrongEndPoint("/loginnnnn"),
	postforgotPasswordConfirmEmail("/login/forgotpassword/confirmEmail"),
	getLogoutlms("/logoutlms"),
	getallUsers("/users");
	
	
	private String resource;
	
	ApiResources(String resource)
	{
	  this.resource=resource;
	}
	
	public String getResorce()
	{
		return resource;
	}
	
}
