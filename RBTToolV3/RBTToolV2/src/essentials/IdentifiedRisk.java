
	/* Objeto que faz a associação entre quem respondeu a uma pergunta
	 * ,risco identificado, e justificativa para a resposta*/

package essentials;



public class IdentifiedRisk {
	
	//O id será formado pela seguinte string: descrição da questão + id do requisito + nome do recurso
	private String id;
	private String resourceName;
	private String requirementID;
	private Risk risk;
	private String cause;
	private String projectName;
	private String questionDescription;
	
	private double probability;
	
	
	
	public Risk getRisk() {
		return risk;
	}


	public void setRisk(Risk risk) {
		this.risk = risk;
	}


	public String getCause() {
		return cause;
	}


	public void setCause(String cause) {
		this.cause = cause;
	}


	public String getId() {
		return id;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}


	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	
	public String getRequirementID() {
		return requirementID;
	}
	public void setRequirementID(String requirementID) {
		this.requirementID = requirementID;
	}
	
	
	
	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	

	public String getQuestionDescription() {
		return questionDescription;
	}


	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentifiedRisk other = (IdentifiedRisk) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
