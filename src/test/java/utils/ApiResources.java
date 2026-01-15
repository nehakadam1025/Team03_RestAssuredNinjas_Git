package utils;

public enum ApiResources {

	postLoginApi("/login"),
	postLoginWrongEndPoint("/loginnnnn"),
	postforgotPasswordConfirmEmail("/login/forgotpassword/confirmEmail"),
	postforgotPasswordConfirmEmailWrongEndpoint("/login/forgotpassword/confirmEmailhhghh"),
	postresetPassword("/resetPassword"),
	postresetPasswordWrongEndpoint("/resetPassworddddd"),
	getLogoutlms("/logoutlms"),
	getLogoutlmswrongEndPoint("/logoutlmssssss"),
	createUser("/users/roleStatus"),
	getallUsers("/users"),
	getbyid("/users"),
	getbyroles("/users/roles"),
	UpdateUserAPI("/users"),
	DeleteUserAPI("/users"),
	getall("/allSkillMaster"),
	getallSkillMaster("/allSkillMaster"),
	getSkillName("/skills"),
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
