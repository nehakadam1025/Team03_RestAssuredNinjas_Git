package utils;

public enum ApiResources {

	postLoginApi("/login"),
	postforgotPasswordConfirmEmail("/login/forgotpassword/confirmEmail"),
	getLogoutlms("/logoutlms"),
	getallUsers("/users"),
	getbyid("/users/U74"),
	postLoginWrongEndPoint("/loginnnnn"),
	getall("/allSkillMaster"),
	createUser("/users/roleStatus"),
	getaaprgram("/allPrograms");
	
	
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
