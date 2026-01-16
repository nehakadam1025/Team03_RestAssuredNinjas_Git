
package utils;

public enum ApiResources {

	postLoginApi("/login"), postLoginWrongEndPoint("/loginnnnn"),
	postforgotPasswordConfirmEmail("/login/forgotpassword/confirmEmail"),
	postforgotPasswordConfirmEmailWrongEndpoint("/login/forgotpassword/confirmEmailhhghh"),
	postresetPassword("/resetPassword"), postresetPasswordWrongEndpoint("/resetPassworddddd"),
	getLogoutlms("/logoutlms"), getLogoutlmswrongEndPoint("/logoutlmssssss"), getaaprgram("/allPrograms"),
	invalidprogramendpoint("/invalid"), getprogrambyid("/programs/44"), getprogrambyinvalidid("/programs/000"),
	getprogrambyinvalidendpoint("/programs/hghgv"), createprogram("/saveprogram"),
	CreateProgrambyInvalidEndpoint("/saveprogrammm"), updateprogrambyid("/putprogram/13"),
	updatebyinvalidid("/putprogram/0"), updateprogrambyname("/program"), deletebyname("/deletebyprogname"),
	deletebyid("/deletebyprogid"), createSkill("/SaveSkillMaster"), getallSkillMaster("/allSkillMaster"),
	getbyskillname("/skills/Discover"), getbyinvalidskillname("/skills/Discovers123"), updateskill("/updateSkills/2"),
	updateskillwithinvalid("/updateSkills/vb"), deleteskill("/deletebySkillId/152"),
	deleteskillinvalid("/deletebySkillId/ui"), createUser("/users/roleStatus"), getallUsers("/users"),
	getbyid("/users"), getbyroles("/users/roles"), UpdateUserAPI("/users"), DeleteUserAPI("/users"),
	getall("/allSkillMaster"),
	getSkillName("/skills"),
	getallbatch("/batches"),
   getbatchid("/batches/batchId/{batchId}"),
   getAllBatches("/batches");



	private String resource;

	ApiResources(String resource) {
		this.resource = resource;
	}

	public String getResorce() {
		return resource;
	}

}
