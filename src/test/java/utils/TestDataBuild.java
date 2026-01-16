package utils;

import pojo.LoginRequest;
import pojo.programRequest;
import java.util.Collections;
import pojo.SkillRequest;


public class TestDataBuild {

//    // 🔹 Old method (hardcoded) - keep if needed
//    public LoginRequest userloginPayload() {
//        LoginRequest p = new LoginRequest();
//        p.setUserLoginEmailId("team3@gmail.com");
//        p.setPassword("ApiHackathon2@3");
//        return p;
//    }

	// 🔹 New method for Scenario Outline
	public LoginRequest userloginPayload(String email, String password) {
		LoginRequest p = new LoginRequest();
		p.setUserLoginEmailId(email);
		p.setPassword(password);
		return p;
	}

	public programRequest programPayload(String programName, String programDescription, String programStatus) {

		programRequest program = new programRequest();
		program.setProgramName(programName);
		program.setProgramDescription(programDescription);
		program.setProgramStatus(programStatus);

		return program;
	}

	public SkillRequest skillpayload(String skillname) {
		
		SkillRequest skilln=new SkillRequest();
		skilln.setSkillName(skillname);
		return skilln;
	}
}
