package persistence;

import java.util.Vector;

import persistence.interfaces.ITestCasesRepository;
import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.Requirement;
import essentials.TestCase;
import essentials.TestIteration;
import essentials.TestPlan;

public class TestCasesRepositoryXML implements ITestCasesRepository {

	
	public void addTestCase(TestCase testCase, Requirement requirement, TestIteration testIteration, TestPlan testPlan, Project project) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		
		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				
				Vector<TestPlan> testPlans = projectFromXML.getTestPlans();
				
				for (int j = 0; j < testPlans.size(); j++) {
					
					TestPlan tp = testPlans.elementAt(j);
					
					if(tp.getName().equals(testPlan.getName())){
						
						Vector<TestIteration> testIterations = tp.gettestIterations();
						
						for (int k = 0; k < testIterations.size(); k++) {
							
							TestIteration ti = testIterations.elementAt(k);
							
							if(ti.getIdentifier().equals(testIteration.getIdentifier())){
								
								Vector<Requirement> requirements = ti.getRequirements();
								
								for (int l = 0; l < requirements.size(); l++) {
									
									Requirement r = requirements.elementAt(l);
									
									if(r.getIdentifier().equals(requirement.getIdentifier())){
										Vector<TestCase> testCases = r.getTestCases();
									
										if(testCases == null){
										
											testCases = new Vector<TestCase>();
										}
									
										testCases.addElement(testCase);
										requirement.setTestCases(testCases);
										requirements.setElementAt(requirement, l);
										testIteration.setRequirements(requirements);
										testIterations.setElementAt(testIteration, k);
										testPlan.settestIterations(testIterations);
										testPlans.setElementAt(testPlan, j);
										project.setTestPlans(testPlans);
										objetos.setElementAt(project, i);
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


	public Vector<TestCase> getTestCasesFromIteration(TestIteration testIteration, TestPlan testPlan, Project project){
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		Vector<TestCase> allTestCases = new Vector<TestCase>();
		
		if (objetos != null) {
			for (int i = 0; i < objetos.size(); i++) {
				Project p = (Project) objetos.elementAt(i);
				//Consulta projeto
				if (p.getName().equals(project.getName())) {
					
					Vector<TestPlan> testPlans = p.getTestPlans();
					
					for (int j = 0; j < testPlans.size(); j++) {
						
						TestPlan tp = testPlans.elementAt(j);
						//Consulta o plano de teste
						if(tp.getName().equals(testPlan.getName())){
							
							Vector<TestIteration> testIterations = tp.gettestIterations();
							
							for (int k = 0; k < testIterations.size(); k++) {
								
								TestIteration ti = testIterations.elementAt(k);
								//Consulta o test iteration
								if(ti.getIdentifier().equals(testIteration.getIdentifier())){
									
								
									Vector<Requirement> requirements = ti.getRequirements();
								
									for (int l = 0; l < requirements.size(); l++) {
										
										Requirement r = requirements.elementAt(l);
										if(r.getTestCases() != null){
											//Adicione todos os test cases de cada requisito
											allTestCases.addAll(r.getTestCases());
										}
										
										
									}
								}
							}
							
						}
						
					}
				}
			}
			
		}
		
		return allTestCases;
	}

	@Override
	public void updateTestCase(TestCase testCase, Requirement requirement, TestIteration testIteration, TestPlan testPlan, Project project) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		
		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				
				Vector<TestPlan> testPlans = projectFromXML.getTestPlans();
				
				for (int j = 0; j < testPlans.size(); j++) {
					
					TestPlan tp = testPlans.elementAt(j);
					
					if(tp.getName().equals(testPlan.getName())){
						
						Vector<TestIteration> testIterations = tp.gettestIterations();
						
						for (int k = 0; k < testIterations.size(); k++) {
							
							TestIteration ti = testIterations.elementAt(k);
							
							if(ti.getIdentifier().equals(testIteration.getIdentifier())){
								
								Vector<Requirement> requirements = ti.getRequirements();
								
								for (int l = 0; l < requirements.size(); l++) {
									
									Requirement r = requirements.elementAt(l);
									
									Vector<TestCase> testCases = r.getTestCases();
								
									if(testCases != null){
										for (int m = 0; m < testCases.size(); m++) {
											
											TestCase tc = testCases.elementAt(m);
											
											if(tc.getId().equals(testCase.getId())){
												
												tc.setDescription(testCase.getDescription());
												tc.setPreConditions(testCase.getPreConditions());
												tc.setPosConditions(testCase.getPosConditions());
												tc.setTestCaseProcedures(testCase.getTestCaseProcedures());
												tc.setStatus(testCase.getStatus());
												tc.setTestCaseExecution(testCase.getTestCaseExecution());
												
												testCases.setElementAt(tc, m);
												requirement.setTestCases(testCases);
												requirements.setElementAt(requirement, l);
												testIteration.setRequirements(requirements);
												testIterations.setElementAt(testIteration, k);
												testPlan.settestIterations(testIterations);
												testPlans.setElementAt(testPlan, j);
												project.setTestPlans(testPlans);
												objetos.setElementAt(project, i);
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
		}
		
		
	}

}
