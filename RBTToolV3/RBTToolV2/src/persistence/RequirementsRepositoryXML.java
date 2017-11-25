package persistence;

import java.util.Vector;

import persistence.interfaces.IRequirementsRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.Requirement;

public class RequirementsRepositoryXML implements IRequirementsRepository {

	public void addRequirement(Requirement requirement, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();
				
				if (requirementsFromXML == null) {
					requirementsFromXML = new Vector<Requirement>();
				}				
				requirementsFromXML.addElement(requirement);
				projectFromXML.setRequirements(requirementsFromXML);
				objetos.setElementAt(projectFromXML,i);
				rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);
				break;
			}
		}		
	}

	@Override
	public void updateRequirement(Requirement requirement, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();
				
				for (int j = 0; j < requirementsFromXML.size(); j++) {
					Requirement requirementToUpdate = requirementsFromXML.elementAt(j);
					if (requirement.getIdentifier().equals(requirementToUpdate.getIdentifier())) {
						requirementToUpdate.setAuthor(requirement.getAuthor());
						requirementToUpdate.setBaseline(requirement.getBaseline());
						requirementToUpdate.setDescription(requirement.getDescription());
						requirementToUpdate.setImportance(requirement.getImportance());
						requirementToUpdate.setModule(requirement.getModule());
						requirementToUpdate.setName(requirement.getName());
						requirementToUpdate.setScenarios(requirement.getScenarios());
						requirementToUpdate.setStatus(requirement.getStatus());
						requirementToUpdate.setVersion(requirement.getVersion());
						requirementToUpdate.setRiskExposureFinal(requirement.getRiskExposureFinal());
						requirementToUpdate.setImpactFinal(requirement.getImpactFinal());
						requirementToUpdate.setProbabilityFinal(requirement.getProbabilityFinal());
						requirementToUpdate.setTestCases(requirement.getTestCases());
						
						requirementsFromXML.setElementAt(requirementToUpdate, j);
						projectFromXML.setRequirements(requirementsFromXML);
						objetos.setElementAt(projectFromXML,i);
						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);
						break;
					}
				}				
			}
		}	
	}

	@Override
	public void removeRequirement(Requirement requirement, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project) objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();
				if (requirementsFromXML != null) {
					requirementsFromXML.removeElement(requirement);
				}
				projectFromXML.setRequirements(requirementsFromXML);
				objetos.removeElementAt(i);
				objetos.insertElementAt(projectFromXML,i);
				rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);
				break;
			}
		}
	}

	@Override
	public void importRequirement() {
		// TODO Auto-generated method stub

	}

	@Override
	public Requirement searchRequirement(String identifier, String projectName) {
		Requirement requirement = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		
		for (int i = 0; i < objetos.size(); i++) {
			Project project = (Project) objetos.elementAt(i);
			if (project.getName().equals(projectName)) {
				Vector<Requirement> requirements = project.getRequirements();
				if(requirements != null){
					for (int j = 0; j < requirements.size(); j++) {
						requirement = (Requirement)requirements.elementAt(j);
						if(requirement.getIdentifier().equals(identifier))
							break;
						
					}
					return requirement;
				}
				//break;
				
			}
			
			
			
		}

		return null;
	}
		
	public Requirement searchRequirementName(String name, String projectName) {
		Requirement requirement = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		
		for (int i = 0; i < objetos.size(); i++) {
			Project project = (Project) objetos.elementAt(i);
			if (project.getName().equals(projectName)) {
				Vector<Requirement> requirements = project.getRequirements();
				if(requirements != null){
					for (int j = 0; j < requirements.size(); j++) {
						requirement = (Requirement)requirements.elementAt(j);
						if(requirement.getName().equals(name))
							break;
						
					}
					return requirement;
				}
							
			}
			
		}
		

		return null;
	}
	
	
	
	public Vector<Requirement> getRequirements (String projectName){
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		
		Vector<Requirement> requirements = null;
		
		if(objetos != null){
			for (int i = 0; i < objetos.size(); i++) {
				Project project = (Project) objetos.elementAt(i);
				if (project.getName().equals(projectName)) {
					
					requirements = project.getRequirements();
				}
			}
		}
		
		return requirements;
	}
}
