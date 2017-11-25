package essentials;

import java.util.Vector;

public class Project {

	private String name;
	private String startDate;
	private String dueDate;
	private String mainDescription;
	private String requirementBaseline;
	private Vector<String> resources;
	private Vector<Requirement> requirements;
	private Vector<Questionnaire> questionnaires;
	private Vector<RiskManagementReport> riskManagementReports;
	private Vector<TestPlan> testPlans;

	private Project parent;

	public Project(String name, String mainDescription) {
		super();
		this.mainDescription = mainDescription;
		this.name = name;
	}

	public Vector<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(Vector<Requirement> requirements) {
		this.requirements = requirements;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMainDescription() {
		return mainDescription;
	}

	public void setMainDescription(String mainDescription) {
		this.mainDescription = mainDescription;
	}

	public String getRequirementBaseline() {
		return requirementBaseline;
	}

	public void setRequirementBaseline(String requirementBaseline) {
		this.requirementBaseline = requirementBaseline;
	}

	public Vector<String> getResources() {
		return resources;
	}

	public void setResources(Vector<String> resources) {
		this.resources = resources;
	}

	public Vector<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(Vector<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}
	

	
	public Vector<RiskManagementReport> getRiskManagementReports() {
		return riskManagementReports;
	}

	public void setRiskManagementReports(
			Vector<RiskManagementReport> riskManagementReports) {
		this.riskManagementReports = riskManagementReports;
	}

	
	
	public Vector<TestPlan> getTestPlans() {
		return testPlans;
	}

	public void setTestPlans(Vector<TestPlan> testPlans) {
		this.testPlans = testPlans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Project))
			return false;
		Project other = (Project) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Project getParent() {
		return parent;
	}

}
