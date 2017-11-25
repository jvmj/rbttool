package essentials;

import java.util.HashMap;
import java.util.Vector;

public class RiskManagementReport {
	
	private String id;
	
	private String projectName;
	
	private int differentRiskQuantity;
	
	private HashMap<Risk, Integer> riskQuantityAssociation;
	
	//private HashMap<Requirement, Double> requirementREAssociation;
	
	private Vector<Requirement> requirements;

	public int getDifferentRiskQuantity() {
		return differentRiskQuantity;
	}

	public void setDifferentRiskQuantity(int differentRiskQuantity) {
		this.differentRiskQuantity = differentRiskQuantity;
	}

	public HashMap<Risk, Integer> getRiskQuantityAssociation() {
		return riskQuantityAssociation;
	}

	public void setRiskQuantityAssociation(
			HashMap<Risk, Integer> riskQuantityAssociation) {
		this.riskQuantityAssociation = riskQuantityAssociation;
	}

	
	public Vector<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(Vector<Requirement> requirements) {
		this.requirements = requirements;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
		RiskManagementReport other = (RiskManagementReport) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
