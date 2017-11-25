package managers;

import java.util.Vector;

import persistence.RequirementsRepositoryXML;
import persistence.interfaces.IRequirementsRepository;
import essentials.Project;
import essentials.Requirement;
import exceptions.RBTToolValidationException;
public class RequirementManager {

	private IRequirementsRepository requirementsRepository;

	public RequirementManager() {
		super();
		this.requirementsRepository = new RequirementsRepositoryXML();
	}
	
	/**
	 * Metodo responsavel pela inclusao de um requisito num repositorio.
	 * @param requirement
	 * Agora é preciso saber se existe algum projeto
	 */
	public void addRequirement(Requirement requirement, Project project) {
		
		ProjectManager projectsManager = new ProjectManager();
		//Verifica se o projeto especificado existe ou nao
		if(!projectsManager.exists(project)){
			try {
				throw new RBTToolValidationException("You must create a project!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			if(exists(requirement,project)){
				try {
					throw new RBTToolValidationException("The requirement with this identification is already defined!");
				} catch (RBTToolValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else{
				requirementsRepository.addRequirement(requirement, project);
			}
			
		}

	}

	public void removeRequirement(Requirement requisito, Project project) {
		
		ProjectManager projectsManager = new ProjectManager();
		
		if(!projectsManager.exists(project)){
			try {
				throw new RBTToolValidationException("Doesn't exist any project with these informations");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			if(!exists(requisito, project)){
				try {
					throw new RBTToolValidationException("Doesn't exist any requirement with these informations");
				} catch (RBTToolValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				requirementsRepository.removeRequirement(requisito, project);
			}
		}
		
	}

	public void updateRequirement(Requirement requirement, Project project) {
		ProjectManager projectsManager = new ProjectManager();
		//Verifica se o projeto especificado existe ou nao
		if(!projectsManager.exists(project)){
			try {
				throw new RBTToolValidationException("You must create a project!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			if(!exists(requirement,project)){
				try {
					throw new RBTToolValidationException("Doens't exist any requirement with this Name");
				} catch (RBTToolValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else{
				requirementsRepository.updateRequirement(requirement, project);
			}
			
		}
	}

	public void importRequirement() {

	}

	public Requirement searchRequirement(String requirementID, String projectName) {
		
		Requirement r = requirementsRepository.searchRequirement(requirementID, projectName);
		if(r == null){
			try {
				throw new RBTToolValidationException("Doens't exist any requirement with this ID");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
			return r;
		
		return null;
	}
	
	public Requirement searchRequirementName(String requirementName, String projectName) {
	
		Requirement r = requirementsRepository.searchRequirementName(requirementName, projectName);
		if(r == null){
			try {
				throw new RBTToolValidationException("Doens't exist any requirement with this Name");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
			return r;
		
		return null;
	}
	
	/**
	 * Verifica se o requisito ja foi cadastrado no repositorio.
	 * @param requirement
	 * @return
	 */
	public boolean exists(Requirement requirement, Project project) {		
		
			Requirement requisitoEncontrado = requirementsRepository.searchRequirement(requirement.getIdentifier(),project.getName());
			if (requisitoEncontrado != null && requisitoEncontrado.getIdentifier().equals(requirement.getIdentifier())) {
				return true;
			} else {
				return false;
			}
		
	}
	
	public Vector<Requirement> getRequirements(String projectName){
		
		return requirementsRepository.getRequirements(projectName);
	}
	
	
	public Vector<Requirement> getRequirementsREInterval(String projectName, double moreValue, double lessValue){
		
		Vector<Requirement> requirementsToReturn = new Vector<Requirement>();
		
		Vector<Requirement> allRequirements = this.getRequirements(projectName);
		
		if(allRequirements != null){
			
			for (int i = 0; i < allRequirements.size(); i++) {
				
				Requirement r = allRequirements.elementAt(i);
				
				String rValue = r.getRiskExposureFinal()+"";
				
				double rValueConverted = Double.parseDouble(rValue.substring(0,4));
				
				if(rValueConverted <= moreValue && rValueConverted >= lessValue)
					requirementsToReturn.add(r);
				
				/*if(r.getRiskExposureFinal() <= moreValue && r.getRiskExposureFinal() >= lessValue)
					requirementsToReturn.add(r);*/
				
			}
		}
		
		return requirementsToReturn;
		
	}
	
	

}
