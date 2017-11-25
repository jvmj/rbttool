package managers;

import java.io.File;
import java.util.Vector;

import persistence.RiskAnalysisRepositoryXML;
import persistence.interfaces.IRiskAnalysisRepository;
import essentials.Project;
import essentials.Requirement;
import essentials.RiskAnalysis;
import exceptions.RBTToolValidationException;

public class RiskAnalysisManager {
	
	//Sem regra de negocio alguma.
	
	private IRiskAnalysisRepository riskAnalysisRepository;

	public RiskAnalysisManager() {
		super();
		this.riskAnalysisRepository = new RiskAnalysisRepositoryXML();
	}
	
	public void addRiskAnalysis(RiskAnalysis riskAnalysis, Requirement requirement, Project project){
		
		if(existsRiskAnalysis(riskAnalysis.getNameResource(), requirement, project)){
			try {
				throw new RBTToolValidationException("The resource name is already defined for the risk analysis!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			riskAnalysisRepository.addRiskAnalysis(riskAnalysis, requirement, project);
			
		
	}
	
	
	public RiskAnalysis searchRiskAnalysis(String nameResource, String requirementID, String nameProject){
		
		RiskAnalysis riskAnalysis = riskAnalysisRepository.searchRiskAnalysis(nameResource, requirementID, nameProject);
		
		return riskAnalysis; 
	}
	
	public void updateRiskAnalysis(RiskAnalysis riskAnalysis, Requirement requirement, String resourceName, Project project){
		riskAnalysisRepository.updateRiskAnalysis(riskAnalysis, requirement, resourceName, project);

	}
	
	public boolean existsRiskAnalysis(String nameResource, Requirement requirement, Project project){
		RiskAnalysis foundRiskAnalysis = riskAnalysisRepository.searchRiskAnalysis(nameResource, requirement.getIdentifier(), project.getName());
		
		if(foundRiskAnalysis != null)
			return true;
		
		return false;
		
			
		
	}
	
	public void exportRiskAnalysies(Vector<RiskAnalysis> riskAnalysies, File file){
		
		riskAnalysisRepository.exportRiskAnalysies(riskAnalysies, file);
	}
	
	public void importRiskAnalysies(File file){
		
		riskAnalysisRepository.importRiskAnalysies(file);
	}
	
	public Vector<Object> getImportedRiskAnalysies(File file){
		
		return riskAnalysisRepository.getImportedRiskAnalysies(file);
	}

}
