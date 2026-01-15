package utils;

public enum ApiResources {

	postLoginApi("/login"),
	postforgotPasswordConfirmEmail("/login/forgotpassword/confirmEmail"),
	getLogoutlms("/logoutlms"),
	createUser("/users/roleStatus"),
	getallUsers("/users"),
	getbyid("/users"),
	getbyroles("/users/roles"),
	UpdateUserAPI("/users"),
	DeleteUserAPI("/users"),
	postLoginWrongEndPoint("/loginnnnn"),
	getall("/allSkillMaster"),
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
