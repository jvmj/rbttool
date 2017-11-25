package persistence.interfaces;

import java.io.File;
import java.util.Vector;

import essentials.IdentifiedRisk;
import essentials.Project;
import essentials.Requirement;

public interface IIdentifiedRisksRepository {
	
	public void addIdentifiedRisk (IdentifiedRisk identifiedRisk, Requirement requirement, Project project);
	
	public void updateIdentifiedRisk (IdentifiedRisk identifiedRisk, Requirement requirement, String resourceName, Project project);
	
	public IdentifiedRisk searchIdentifiedRisk(String id, String RequirementID, String nameProject);
	
	public void removeIdentifiedRisk(IdentifiedRisk identifiedRisk, Requirement requirement, Project project);
	
	public void exportIdentifiedRisk(Vector<IdentifiedRisk> identifiedRisks, File file);
	
	public void importIdentifiedRisk(File file);
	
	public Vector<IdentifiedRisk> getIdentifiedRisks(Requirement requirement, Project project);
	
	public Vector<Object> getImportedIdentifiedRisks(File file);
	

}
