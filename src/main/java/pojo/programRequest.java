package pojo;

public class programRequest {

    private String programDescription;
    private String programName;
    private String programStatus;

    // No-argument constructor (required by Rest Assured / Jackson)
    public programRequest() {
    }

    // Parameterized constructor (optional but useful)
    public programRequest(String programDescription, String programName, String programStatus) {
        this.programDescription = programDescription;
        this.programName = programName;
        this.programStatus = programStatus;
    }

    // Getters and Setters
    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramStatus() {
        return programStatus;
    }

    public void setProgramStatus(String programStatus) {
        this.programStatus = programStatus;
    }
    
}
