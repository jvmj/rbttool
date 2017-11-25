package persistence.interfaces;

import java.util.Vector;

import essentials.Project;
import essentials.Requirement;

public interface IRequirementsRepository {

	public void addRequirement(Requirement requirement, Project project);

	public void removeRequirement(Requirement requirement, Project project);

	public void updateRequirement(Requirement requirement, Project project);

	public Requirement searchRequirement(String name, String projectName);
	
	public Requirement searchRequirementName(String requirementName, String project);

	public void importRequirement();
	
	public Vector<Requirement> getRequirements (String projectName);

}
