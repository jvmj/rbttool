package persistence.interfaces;

import java.io.File;
import java.util.Vector;

import essentials.Project;
import essentials.Requirement;
import essentials.RiskAnalysis;

public interface IRiskAnalysisRepository {
	
	public void addRiskAnalysis (RiskAnalysis riskAnalysis, Requirement requirement, Project project);
	
	public void updateRiskAnalysis (RiskAnalysis riskAnalysis, Requirement requirement, String resourceName, Project project);
	
	public RiskAnalysis searchRiskAnalysis(String nameResource, String nameRequirement, String nameProject);
	
	public void exportRiskAnalysies(Vector<RiskAnalysis> riskAnalysies, File file);
	
	public void importRiskAnalysies(File file);
	
	public Vector<Object> getImportedRiskAnalysies(File file);
	

}
