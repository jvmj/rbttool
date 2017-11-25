package managers;

import persistence.RiskManagementReportRepositoryXML;
import persistence.interfaces.IRiskManagementReportRepository;
import essentials.Project;
import essentials.RiskManagementReport;

public class RiskManagementReportManager {
	
	private IRiskManagementReportRepository riskManagementRepository;

	public RiskManagementReportManager() {
		super();
		this.riskManagementRepository = new RiskManagementReportRepositoryXML();
	}
	
	public void addRiskManagementReport(RiskManagementReport riskManagementReport, Project project){
		riskManagementRepository.addRiskManagementReport(riskManagementReport, project);
	}
	
	
	public RiskManagementReport searchRiskManagementReport(String id, String projectName){
		
		RiskManagementReport riskManagementReport = riskManagementRepository.searchRiskManagementReport(id, projectName);
		
		return riskManagementReport;
		
	}
	
	public void updateRiskManagementReport(RiskManagementReport report, Project project){
		
		riskManagementRepository.updateRiskManagementReport(report, project);
	}
	
	public boolean existsRiskManagementReport(RiskManagementReport report, Project project){
		
		RiskManagementReport foundReport = riskManagementRepository.searchRiskManagementReport(report.getId(), project.getName());
		
		if(foundReport != null && foundReport.getId().equals(report.getId())){
			return true;
		}
		else
			return false;
	}

}
