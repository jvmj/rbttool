package persistence;

import java.io.File;
import java.util.Vector;

import persistence.interfaces.IIdentifiedRisksRepository;
import util.FilesUtil;
import util.RBTToolXStream;
import essentials.IdentifiedRisk;
import essentials.Project;
import essentials.Requirement;

public class IdentifiedRisksRepositoryXML implements IIdentifiedRisksRepository {

	@Override
	public void addIdentifiedRisk(IdentifiedRisk identifiedRisk, Requirement requirement, Project project) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		
		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();

				for (int j = 0; j < requirementsFromXML.size(); j++) {
					Requirement requirementFromXML = (Requirement)requirementsFromXML.elementAt(j);
					if (requirementFromXML.getIdentifier().equals(requirement.getIdentifier())) {
						Vector<IdentifiedRisk> identifiedRiskFromXML = requirementFromXML.getIdentifiedRisks();
						if (identifiedRiskFromXML == null) {
							identifiedRiskFromXML = new Vector<IdentifiedRisk>();
						}				
						identifiedRiskFromXML.addElement(identifiedRisk);
						requirementFromXML.setIdentifiedRisks(identifiedRiskFromXML);
						requirementsFromXML.setElementAt(requirementFromXML,j);
						projectFromXML.setRequirements(requirementsFromXML);
						objetos.setElementAt(projectFromXML,i);
						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetos);
						break;
					}
				}
			}
		}

	}

	@Override
	public void removeIdentifiedRisk(IdentifiedRisk identifiedRisk, Requirement requirement, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();

				for (int j = 0; j < requirementsFromXML.size(); j++) {
					Requirement requirementFromXML = (Requirement)requirementsFromXML.elementAt(j);
					if (requirementFromXML.equals(requirement)) {
						Vector<IdentifiedRisk> identifiedRisksFromXML = requirementFromXML.getIdentifiedRisks();
						if (identifiedRisksFromXML != null) {
							identifiedRisksFromXML.remove(identifiedRisk);
						}
						requirementFromXML.setIdentifiedRisks(identifiedRisksFromXML);
						requirementsFromXML.setElementAt(requirementFromXML,j);
						projectFromXML.setRequirements(requirementsFromXML);
						//objetos.removeElementAt(i);
						objetos.setElementAt(projectFromXML,i);
						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetos);
						break;
					}
				}
			}
		}

	}

	@Override
	public void exportIdentifiedRisk(Vector<IdentifiedRisk> identifiedRisks,File file) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos  = new Vector<Object>();
		
		for (int i = 0; i < identifiedRisks.size(); i++) {
			IdentifiedRisk ir = (IdentifiedRisk)identifiedRisks.elementAt(i);
			objetos.add(ir);
			}
		
		rbtToolXStream.writeXML(file.getParent(), file.getName(), objetos);

	}


	//Joga o conteúdo do arquivo xml dentro de "projects.xml"
	@Override
	public void importIdentifiedRisk(File file) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
        Vector<Object> identifiedRisks  = new Vector<Object>();
        Vector<Object> objetosFinal  = new Vector<Object>();
        identifiedRisks = rbtToolXStream.readXML(file.getParent(), file.getName());
        objetosFinal = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
        
        if (identifiedRisks != null) {
        	
        	for (int i = 0; i < identifiedRisks.size(); i++) {
        		IdentifiedRisk identifiedRisk = (IdentifiedRisk) identifiedRisks.elementAt(i);
        		
        		for (int j = 0; j < objetosFinal.size(); j++) {
        			
        			Project projectFromXML = (Project) objetosFinal.elementAt(j);
        			
        			if(projectFromXML.getName().equals(identifiedRisk.getProjectName())){
        				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();
        				for (int k = 0; k < requirementsFromXML.size(); k++) {
        					Requirement requirementFromXML = (Requirement)requirementsFromXML.elementAt(k);
        					
        					if(requirementFromXML.getIdentifier().equals(identifiedRisk.getRequirementID())){
        						
        						Vector<IdentifiedRisk> identifiedRisksFromXML = requirementFromXML.getIdentifiedRisks();
        						if (identifiedRisksFromXML == null) {
        							identifiedRisksFromXML = new Vector<IdentifiedRisk>();
        							identifiedRisksFromXML.addElement(identifiedRisk);
            						requirementFromXML.setIdentifiedRisks(identifiedRisksFromXML);
            						requirementsFromXML.setElementAt(requirementFromXML,k);
            						projectFromXML.setRequirements(requirementsFromXML);
            						objetosFinal.setElementAt(projectFromXML,j);
            						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetosFinal);
            						break;
        						}else{
        						
	        						for (int l = 0; l < identifiedRisksFromXML.size(); l++) {
										
	        							IdentifiedRisk irFromXML = identifiedRisksFromXML.elementAt(l);
	        							
	        							if(!irFromXML.getId().equals(identifiedRisk.getId())){
	        								
	                						identifiedRisksFromXML.addElement(identifiedRisk);
	                						requirementFromXML.setIdentifiedRisks(identifiedRisksFromXML);
	                						requirementsFromXML.setElementAt(requirementFromXML,k);
	                						projectFromXML.setRequirements(requirementsFromXML);
	                						objetosFinal.setElementAt(projectFromXML,j);
	                						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetosFinal);
	                						break;
	        							}else
	        								break;
									}
        						}

        					}
        						
        				}
        			}
					
				}
				
			}
        }
  
	}

	@Override
	public IdentifiedRisk searchIdentifiedRisk(String id, String requirementID, String nameProject) {

	// Cria um identifiedRisk para ser retornada
		IdentifiedRisk ir = null;
		// Cria um requirement para iterar
		Requirement requirement = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		if (objetos != null) {
			for (int i = 0; i < objetos.size(); i++) {
				Project project = (Project) objetos.elementAt(i);
				if (project.getName().equals(nameProject)) {
					Vector<Requirement> requirements = project.getRequirements();
					if (requirements != null) {
						for (int j = 0; j < requirements.size(); j++) {
							requirement = (Requirement) requirements.elementAt(j);
							if (requirement.getIdentifier().equals(requirementID)) {
								Vector<IdentifiedRisk> identifiedRisks = requirement.getIdentifiedRisks();
								if (identifiedRisks != null) {
									for (int k = 0; k < identifiedRisks.size(); k++) {
										ir = identifiedRisks.elementAt(k);
										if (id.equals(ir.getId())) {
											
											return ir;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;

		
		}

	@Override
	public void updateIdentifiedRisk(IdentifiedRisk identifiedRisk, Requirement requirement, String resourceName, Project project) {

		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> projectsFromXML = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		
		//Laço de consulta do projeto		
		for (int i = 0; i < projectsFromXML.size(); i++) {
			Project projectFromXML = (Project)projectsFromXML.elementAt(i);
			
			if(projectFromXML.equals(project)){
				
				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();
				Vector<String> resources = projectFromXML.getResources();

				
				if(requirementsFromXML != null && resources != null){
					
					//Laço de consulta de requisito
					for (int j = 0; j < requirementsFromXML.size(); j++) {
						
						Requirement requirementFromXML = (Requirement)requirementsFromXML.elementAt(j);
						if(requirementFromXML.equals(requirement)){
							
							Vector<IdentifiedRisk> identifiedRisks = requirement.getIdentifiedRisks();
							
							if(identifiedRisks != null){
								
								//Laço de consulta de riscos identificados
								for (int k = 0; k < identifiedRisks.size(); k++) {
									
									IdentifiedRisk identifiedRiskFromXML = (IdentifiedRisk)identifiedRisks.elementAt(k);
									
									if(identifiedRiskFromXML.getId().equals(identifiedRisk.getId())){
										
										identifiedRiskFromXML.setCause(identifiedRisk.getCause());
										identifiedRiskFromXML.setRequirementID(identifiedRisk.getRequirementID());
										identifiedRiskFromXML.setResourceName(identifiedRisk.getResourceName());
										identifiedRiskFromXML.setRisk(identifiedRisk.getRisk());
										identifiedRiskFromXML.setProjectName(identifiedRisk.getProjectName());
										identifiedRiskFromXML.setQuestionDescription(identifiedRisk.getQuestionDescription());
												
										identifiedRisks.setElementAt(identifiedRiskFromXML, k);
										requirementFromXML.setIdentifiedRisks(identifiedRisks);
										
										requirementsFromXML.setElementAt(requirementFromXML, j);
										projectsFromXML.setElementAt(projectFromXML, i);
										rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, projectsFromXML);
										break;
									}
									
								}
							}
							
							
						}
					}
				}
			}

		}
		
		


	}

	@Override
	public Vector<IdentifiedRisk> getIdentifiedRisks(Requirement requirement, Project project) {
		
		// Cria um requirement para iterar
		Requirement r = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		if (objetos != null) {
			for (int i = 0; i < objetos.size(); i++) {
				Project p = (Project) objetos.elementAt(i);
				if (p.getName().equals(project.getName())) {
					Vector<Requirement> requirements = p.getRequirements();
					if (requirements != null) {
						for (int j = 0; j < requirements.size(); j++) {
							r = (Requirement) requirements.elementAt(j);
							if (r.getIdentifier().equals(requirement.getIdentifier())) {
								return(r.getIdentifiedRisks());
								
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public Vector<Object> getImportedIdentifiedRisks(File file) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
        Vector<Object> objetos  = new Vector<Object>();
        objetos = rbtToolXStream.readXML(file.getParent(), file.getName());
		// Cria um requirement para iterar
		//Requirement r = null;
		
		return objetos;
		/*if (objetos != null) {
			for (int i = 0; i < objetos.size(); i++) {
				IdentifiedRisk ir = (IdentifiedRisk) objetos.elementAt(i);
				Project p = RBTToolFacade.getInstance().searchProject(ir.getProjectName());
					Vector<Requirement> requirements = p.getRequirements();
					if (requirements != null) {
						for (int j = 0; j < requirements.size(); j++) {
							r = (Requirement) requirements.elementAt(j);
								return(r.getIdentifiedRisks());
								
							
						}
					}
				
			}
		}
		return null;*/
	}

}
