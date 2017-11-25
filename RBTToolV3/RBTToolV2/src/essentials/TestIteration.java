package essentials;

import java.util.Vector;

public class TestIteration {
	
	private String identifier;
	private Vector<Requirement> requirements;
	private double mostREValue;
	private double lessREValue;
	private String startDate;
	private String dueDate;
	private Vector<TestCase> testCasesFromRequirements;
	private String projectName;
	private String testPlanName;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public double getMostREValue() {
		return mostREValue;
	}
	public void setMostREValue(double mostREValue) {
		this.mostREValue = mostREValue;
	}
	public double getLessREValue() {
		return lessREValue;
	}
	public void setLessREValue(double lessREValue) {
		this.lessREValue = lessREValue;
	}
	public Vector<Requirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(Vector<Requirement> requirements) {
		this.requirements = requirements;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public Vector<TestCase> getTestCasesFromRequirements() {
		
		Vector<Requirement> requirements = this.getRequirements();
		Vector<TestCase> allTestCases = new Vector<TestCase>();
		
		for (int i = 0; i < requirements.size(); i++) {
			Requirement r = requirements.elementAt(i);
			
			allTestCases.addAll(r.getTestCases());
		}
		return allTestCases;
	}
	public void setTestCasesFromRequirements(
			Vector<TestCase> testCasesFromRequirement) {
		this.testCasesFromRequirements = testCasesFromRequirement;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getTestPlanName() {
		return testPlanName;
	}
	public void setTestPlanName(String testPlanName) {
		this.testPlanName = testPlanName;
	}
	
	
	
	

}
