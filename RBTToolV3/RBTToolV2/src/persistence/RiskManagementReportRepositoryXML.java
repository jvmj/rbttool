package persistence;

import java.util.Vector;

import persistence.interfaces.IRiskManagementReportRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.RiskManagementReport;

public class RiskManagementReportRepositoryXML implements
		IRiskManagementReportRepository {

	@Override
	public void addRiskManagementReport(
			RiskManagementReport riskManagementReport, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<RiskManagementReport> reportsFromXML = projectFromXML.getRiskManagementReports();
				
				if (reportsFromXML == null) {
					reportsFromXML = new Vector<RiskManagementReport>();
				}				
				reportsFromXML.addElement(riskManagementReport);
				projectFromXML.setRiskManagementReports(reportsFromXML);
				objetos.setElementAt(projectFromXML,i);
				rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);
				break;
			}
		}		


	}

	@Override
	public RiskManagementReport searchRiskManagementReport(String id,
			String projectName) {
		RiskManagementReport riskManagementReport = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		
		for (int i = 0; i < objetos.size(); i++) {
			Project project = (Project) objetos.elementAt(i);
			if (project.getName().equals(projectName)) {
				Vector<RiskManagementReport> reports = project.getRiskManagementReports();
				if(reports != null){
					for (int j = 0; j < reports.size(); j++) {
						riskManagementReport = (RiskManagementReport)reports.elementAt(j);
						if(riskManagementReport.getId().equals(id))
							break;
						
					}
					return riskManagementReport;
				}
				//break;
				
			}
			
			
			
		}

		return null;
	}
	
	public void updateRiskManagementReport(RiskManagementReport report, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<RiskManagementReport> reportsFromXML = projectFromXML.getRiskManagementReports();
				
				for (int j = 0; j < reportsFromXML.size(); j++) {
					RiskManagementReport reportToUpdate = reportsFromXML.elementAt(j);
					if (report.getId().equals(reportToUpdate.getId())) {
						reportToUpdate.setDifferentRiskQuantity(report.getDifferentRiskQuantity());
						reportToUpdate.setRequirements(report.getRequirements());
						reportToUpdate.setRiskQuantityAssociation(report.getRiskQuantityAssociation());
						
						reportsFromXML.setElementAt(reportToUpdate, j);
						projectFromXML.setRiskManagementReports(reportsFromXML);
						objetos.setElementAt(projectFromXML,i);
						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);
						break;
					}
				}				
			}
		}	
	}

}
