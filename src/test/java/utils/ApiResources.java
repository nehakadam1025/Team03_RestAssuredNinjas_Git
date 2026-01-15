package utils;

public enum ApiResources {

	postLoginApi("/login"),
	getallUsers("/users"),
	getbyid("/users"),
	getbyroles("/users/roles"),
	UpdateUserAPI("/users"),
	DeleteUserAPI("/users"),
	
	getall("/allSkillMaster"),

	postLoginWrongEndPoint("/loginnnnn"),
	postforgotPasswordConfirmEmail("/login/forgotpassword/confirmEmail"),
	postforgotPasswordConfirmEmailWrongEndpoint("/login/forgotpassword/confirmEmailhhghh"),
	postresetPassword("/resetPassword"),
	postresetPasswordWrongEndpoint("/resetPassworddddd"),
	getLogoutlms("/logoutlms"),
	getLogoutlmswrongEndPoint("/logoutlmssssss"),
	getallSkillMaster("/allSkillMaster"),
	getSkillName("/skills"),
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
