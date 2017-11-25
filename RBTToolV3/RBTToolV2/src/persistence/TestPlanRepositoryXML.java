package persistence;

import java.util.Vector;

import persistence.interfaces.ITestPlanRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.TestPlan;

public class TestPlanRepositoryXML implements ITestPlanRepository {

	@Override
	public void addTestPlan(TestPlan testPlan, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<TestPlan> testPlansFromXML = projectFromXML.getTestPlans();
				
				if (testPlansFromXML == null) {
					testPlansFromXML = new Vector<TestPlan>();
				}				
				testPlansFromXML.addElement(testPlan);
				projectFromXML.setTestPlans(testPlansFromXML);
				objetos.setElementAt(projectFromXML,i);
				rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);
				break;
			}
		}		
	}


	@Override
	public Vector<TestPlan> getTestPlans(String projectName) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		
		Vector<TestPlan> testPlans = null;
		
		if(objetos != null){
			for (int i = 0; i < objetos.size(); i++) {
				Project project = (Project) objetos.elementAt(i);
				if (project.getName().equals(projectName)) {
					
					testPlans = project.getTestPlans();
				}
			}
		}
		
		return testPlans;
	}

	@Override
	public void removeTestPlan(TestPlan testPlan, Project project) {
		// TODO Auto-generated method stub

	}

	@Override
	public TestPlan searchTestPlan(String testPlanName, String projectName) {
		TestPlan testPlan = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		
		for (int i = 0; i < objetos.size(); i++) {
			Project project = (Project) objetos.elementAt(i);
			if (project.getName().equals(projectName)) {
				Vector<TestPlan> testPlans = project.getTestPlans();
				if(testPlans != null){
					for (int j = 0; j < testPlans.size(); j++) {
						testPlan = (TestPlan)testPlans.elementAt(j);
						if(testPlan.getName().equals(testPlanName))
							break;
						
					}
					return testPlan;
				}
				//break;
				
			}
			
			
			
		}

		return null;
	}

	@Override
	public void updateTestPlan(TestPlan testPlan, Project project) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<TestPlan> testPlansFromXML = projectFromXML.getTestPlans();
				
				for (int j = 0; j < testPlansFromXML.size(); j++) {
					TestPlan testPlanToUpdate = testPlansFromXML.elementAt(j);
					if (testPlan.getName().equals(testPlanToUpdate.getName())) {

						testPlanToUpdate.setName(testPlan.getName());
						testPlanToUpdate.setDescription(testPlan.getDescription());
						testPlanToUpdate.setNoIterations(testPlan.getNoIterations());
						
						testPlansFromXML.setElementAt(testPlanToUpdate, j);
						projectFromXML.setTestPlans(testPlansFromXML);
						objetos.setElementAt(projectFromXML,i);
						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);
						break;
					}
				}				
			}
		}	

	}

}
