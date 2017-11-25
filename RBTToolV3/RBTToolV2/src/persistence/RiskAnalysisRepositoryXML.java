package persistence;

import java.io.File;
import java.util.Vector;

import persistence.interfaces.IRiskAnalysisRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.Requirement;
import essentials.RiskAnalysis;

public class RiskAnalysisRepositoryXML implements IRiskAnalysisRepository {

	@Override
	public void addRiskAnalysis(RiskAnalysis riskAnalysis, Requirement requirement, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();

				for (int j = 0; j < requirementsFromXML.size(); j++) {
					Requirement requirementFromXML = (Requirement)requirementsFromXML.elementAt(j);
					if (requirementFromXML.getIdentifier().equals(requirement.getIdentifier())) {
						Vector<RiskAnalysis> riskAnalysisFromXML = requirementFromXML.getRiskAnalysies();
						if (riskAnalysisFromXML == null) {
							riskAnalysisFromXML = new Vector<RiskAnalysis>();
						}				
						riskAnalysisFromXML.addElement(riskAnalysis);
						requirementFromXML.setRiskAnalysies(riskAnalysisFromXML);
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
	public void updateRiskAnalysis(RiskAnalysis riskAnalysis, Requirement requirement, String resourceName, Project project) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		
		//Laço de consulta do projeto		
		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			
			if(projectFromXML.equals(project)){
				
				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();
				Vector<String> resources = projectFromXML.getResources();

				
				if(requirementsFromXML != null && resources != null){
					
					//Laço de consulta de requisito
					for (int j = 0; j < requirementsFromXML.size(); j++) {
						
						Requirement requirementFromXML = (Requirement)requirementsFromXML.elementAt(j);
						if(requirementFromXML.equals(requirement)){
							
							Vector<RiskAnalysis> riskAnalysies = requirement.getRiskAnalysies();
							
							if(riskAnalysies != null){
								
								//Laço de consulta de analise de riscos
								for (int k = 0; k < riskAnalysies.size(); k++) {
									
									RiskAnalysis riskAnalysisFromXML = (RiskAnalysis)riskAnalysies.elementAt(k);
									//Verifica se o nome do recurso e o identificador do requisito condiz com o atual RiskAnalysisFromXML
									if(riskAnalysisFromXML.getNameResource().equals(resourceName) && riskAnalysisFromXML.getRequirementID().equals(requirement.getIdentifier())){
										
										riskAnalysisFromXML.setCostClientValue(riskAnalysis.getCostClientValue());
										riskAnalysisFromXML.setCostVendorValue(riskAnalysis.getCostVendorValue());
										riskAnalysisFromXML.setComplexValue(riskAnalysis.getComplexValue());
										riskAnalysisFromXML.setDependenceValue(riskAnalysis.getDependenceValue());
										riskAnalysisFromXML.setDesignValue(riskAnalysis.getDesignValue());
										riskAnalysisFromXML.setSizeValue(riskAnalysis.getSizeValue());
										riskAnalysisFromXML.setNewValue(riskAnalysis.getNewValue());
										
										riskAnalysisFromXML.setImpactValue(riskAnalysis.getImpactValue());
										
											
										riskAnalysisFromXML.setMean(riskAnalysis.getMean());
										
										riskAnalysisFromXML.setProbability(riskAnalysis.getProbability());
								
										
										riskAnalysisFromXML.setRiskExposure(riskAnalysis.getRiskExposure());
										
										riskAnalysies.setElementAt(riskAnalysisFromXML, k);
										requirementFromXML.setRiskAnalysies(riskAnalysies);
										
										requirementsFromXML.setElementAt(requirementFromXML, j);
										objetos.setElementAt(projectFromXML, i);
										rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetos);
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
	public RiskAnalysis searchRiskAnalysis(String nameResource,String requirementID, String nameProject) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		
		RiskAnalysis riskAnalysis = null;
				
		//Consulta o projeto
		for (int i = 0; i < objetos.size(); i++) {
			Project project = (Project)objetos.elementAt(i);
			if (project.getName().equals(nameProject)){
				
				Vector<String> resources = project.getResources();
				Vector<Requirement> requirements = project.getRequirements();
				
				if(resources != null && requirements != null){
					
					//Consulta o requisito do projeto
					for (int j = 0; j < requirements.size(); j++) {
						
						Requirement requirement = (Requirement)requirements.elementAt(j);
						if(requirement.getIdentifier().equals(requirementID)){
							Vector<RiskAnalysis> riskAnalysies = requirement.getRiskAnalysies();

							
							if(riskAnalysies != null){
								for (int k = 0; k < riskAnalysies.size(); k++) {
									RiskAnalysis r = (RiskAnalysis)riskAnalysies.elementAt(k);
									//Consulta o recurso do projeto
									if(r.getNameResource().equals(nameResource) && r.getRequirementID().equals(requirementID)){
										riskAnalysis = r;
										break;
									}
								
									
								}
								return riskAnalysis;
							}
						}
						
					}
					
					
				}
			}
		}
		
		return null;
	}


	@Override
	public void exportRiskAnalysies(Vector<RiskAnalysis> riskAnalysies, File file) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos  = new Vector<Object>();
		
		for (int i = 0; i < riskAnalysies.size(); i++) {
			RiskAnalysis r = (RiskAnalysis)riskAnalysies.elementAt(i);
			objetos.add(r);
			}
		
		rbtToolXStream.writeXML(file.getParent(), file.getName(), objetos);
		
	}


	@Override
	public void importRiskAnalysies(File file) {
		
		 RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
	        Vector<Object> objetos  = new Vector<Object>();
	        Vector<Object> objetosFinal  = new Vector<Object>();
	        objetos = rbtToolXStream.readXML(file.getParent(), file.getName());
	        
	        if (objetos != null) {
	        	objetosFinal = rbtToolXStream.readXML(FilesUtil.IMPORTEDRISKANALYSIESPATH, FilesUtil.IMPORTEDRISKANALYSIESFILENAME);
	            if (objetosFinal == null) {
	                objetosFinal = new Vector<Object>();
	            }
	            objetosFinal.addAll(objetos);
	            rbtToolXStream.writeXML(FilesUtil.IMPORTEDRISKANALYSIESPATH, FilesUtil.IMPORTEDRISKANALYSIESFILENAME, objetosFinal);
	        }
		
	}


	@Override
	public Vector<Object> getImportedRiskAnalysies(File file) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = new Vector<Object>();
		objetos = rbtToolXStream.readXML(file.getParent(), file.getName());
		return objetos;
	}

	
	


}
