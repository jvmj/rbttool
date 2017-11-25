package persistence.interfaces;

import essentials.Project;
import essentials.RiskManagementReport;

public interface IRiskManagementReportRepository {
	
	public void addRiskManagementReport(RiskManagementReport riskManagementReport, Project project);
	
	public RiskManagementReport searchRiskManagementReport(String id, String projectName);
	
	public void updateRiskManagementReport(RiskManagementReport report, Project project);

}
