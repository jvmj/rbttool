package essentials;

import java.util.Vector;

public class TestCase {
	
	private String id;
	private String description;
	private Requirement requirement;
	private Risk risk;
	private String preConditions;
	private String posConditions;
	private String status;
	private Vector<TestCaseProcedure> testCaseProcedures;
	private Vector<TestCaseExecution> testCaseExecution;
	
	private String projectName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Requirement getRequirement() {
		return requirement;
	}
	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	public Risk getRisk() {
		return risk;
	}
	public void setRisk(Risk risk) {
		this.risk = risk;
	}
	public String getPreConditions() {
		return preConditions;
	}
	public void setPreConditions(String preConditions) {
		this.preConditions = preConditions;
	}
	public String getPosConditions() {
		return posConditions;
	}
	public void setPosConditions(String posConditions) {
		this.posConditions = posConditions;
	}
	public Vector<TestCaseProcedure> getTestCaseProcedures() {
		return testCaseProcedures;
	}
	public void setTestCaseProcedures(Vector<TestCaseProcedure> testCaseProcedures) {
		this.testCaseProcedures = testCaseProcedures;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Vector<TestCaseExecution> getTestCaseExecution() {
		return testCaseExecution;
	}
	public void setTestCaseExecution(Vector<TestCaseExecution> testCaseExecution) {
		this.testCaseExecution = testCaseExecution;
	}
	
	
	

}
