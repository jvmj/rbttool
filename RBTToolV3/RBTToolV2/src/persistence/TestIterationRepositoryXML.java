package persistence;

import java.util.Vector;

import persistence.interfaces.ITestIterationRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.TestIteration;
import essentials.TestPlan;

public class TestIterationRepositoryXML implements ITestIterationRepository {

	@Override
	public void addTestIteration(TestIteration testIteration,TestPlan testPlan, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<TestPlan> testPlansFromXML = projectFromXML.getTestPlans();

				for (int j = 0; j < testPlansFromXML.size(); j++) {
					TestPlan testPlanFromXML = (TestPlan)testPlansFromXML.elementAt(j);
					if (testPlanFromXML.equals(testPlan)) {
						Vector<TestIteration> testIterationsFromXML = testPlanFromXML.gettestIterations();
						if (testIterationsFromXML == null) {
							testIterationsFromXML = new Vector<TestIteration>();
						}				
						testIterationsFromXML.addElement(testIteration);
						testPlanFromXML.settestIterations(testIterationsFromXML);
						testPlansFromXML.setElementAt(testPlanFromXML,j);
						projectFromXML.setTestPlans(testPlansFromXML);
						objetos.setElementAt(projectFromXML,i);
						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetos);
						break;
					}
				}
			}
		}

	}

	@Override
	public Vector<TestIteration> getTestIterations(TestPlan testPlanArg, Project projectArg) {
		
		TestPlan testPlan = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		if (objetos != null) {
			for (int i = 0; i < objetos.size(); i++) {
				Project project = (Project) objetos.elementAt(i);
				if (project.getName().equals(projectArg)) {
					Vector<TestPlan> testPlans = project.getTestPlans();
					if (testPlans != null) {
						for (int j = 0; j < testPlans.size(); j++) {
							testPlan = (TestPlan) testPlans.elementAt(j);
							if (testPlan.getName().equals(testPlanArg)) {
								return(testPlan.gettestIterations());
								
							}
						}
					}
				}
			}
		}
		return null;
		
	}

	@Override
	public TestIteration searchTestIteration(String testIterationID, String testPlanName, String projectName) {
		
		// Cria uma testIteration para ser retornada
		TestIteration testIteration = null;
		// Cria um TestPlan para iterar
		TestPlan testPlan = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		if (objetos != null) {
			for (int i = 0; i < objetos.size(); i++) {
				Project project = (Project) objetos.elementAt(i);
				if (project.getName().equals(projectName)) {
					Vector<TestPlan> testPlans = project.getTestPlans();
					if (testPlans != null) {
						for (int j = 0; j < testPlans.size(); j++) {
							testPlan = (TestPlan) testPlans.elementAt(j);
							if (testPlan.getName().equals(testPlanName)) {
								Vector<TestIteration> testIterations = testPlan.gettestIterations();
								if (testIterations != null) {
									for (int k = 0; k < testIterations.size(); k++) {
										testIteration = testIterations.elementAt(k);
										if (testIterationID.equals(testIteration.getIdentifier())) {
											return testIteration;
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



}
