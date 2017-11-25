package managers;

import java.io.File;
import java.util.Vector;

import essentials.IdentifiedRisk;
import essentials.Project;
import essentials.Requirement;
import exceptions.RBTToolValidationException;
import persistence.IdentifiedRisksRepositoryXML;
import persistence.interfaces.IIdentifiedRisksRepository;

public class IdentifiedRisksManager {
	
	private IIdentifiedRisksRepository identifiedRisksRepository;

	public IdentifiedRisksManager() {
		super();
		identifiedRisksRepository = new IdentifiedRisksRepositoryXML();
	}
	
	
	public void addIdentifiedRisk (IdentifiedRisk identifiedRisk, Requirement requirement, Project project){
		
		if(existsIdentifiedRisk(identifiedRisk.getId(), requirement, project)){
			try {
				throw new RBTToolValidationException("The resource name is already defined for the identified risk!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			identifiedRisksRepository.addIdentifiedRisk(identifiedRisk, requirement, project);
	}
	
	public void updateIdentifiedRisk (IdentifiedRisk identifiedRisk, Requirement requirement, String resourceName, Project project){
		
		identifiedRisksRepository.updateIdentifiedRisk(identifiedRisk, requirement, resourceName, project);
	}
	
	
	public IdentifiedRisk searchIdentifiedRisk(String id, String RequirementID, String nameProject){
		
		return identifiedRisksRepository.searchIdentifiedRisk(id, RequirementID, nameProject);
	}
	
	
	public void removeIdentifiedRisk(IdentifiedRisk identifiedRisk, Requirement requirement, Project project){
		
		identifiedRisksRepository.removeIdentifiedRisk(identifiedRisk, requirement, project);
	}
	
	public void exportIdentifiedRisk(Vector<IdentifiedRisk> identifiedRisks, File file){
		
		identifiedRisksRepository.exportIdentifiedRisk(identifiedRisks, file);
	}
	
	public void importIdentifiedRisk(File file){
		
		identifiedRisksRepository.importIdentifiedRisk(file);
	}
	
	public Vector<IdentifiedRisk> getIdentifiedRisks(Requirement requirement, Project project){
		
		return identifiedRisksRepository.getIdentifiedRisks(requirement, project);
	}
	
	public Vector<Object> getImportedIdentifiedRisks(File file){
		
		return identifiedRisksRepository.getImportedIdentifiedRisks(file);
	}
	
	public boolean existsIdentifiedRisk (String id, Requirement requirement, Project project){
		
		IdentifiedRisk foundIdentifiedRisk = identifiedRisksRepository.searchIdentifiedRisk(id, requirement.getIdentifier(), project.getName());
		
		if(foundIdentifiedRisk != null){
			return true;
		}else
			return false;
	}

}
