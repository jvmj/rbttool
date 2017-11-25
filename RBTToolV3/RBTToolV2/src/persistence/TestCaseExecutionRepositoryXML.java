package persistence;

import java.util.Vector;

import persistence.interfaces.ITestCaseExecutionRepository;
import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.Requirement;
import essentials.TestCase;
import essentials.TestCaseExecution;
import essentials.TestPlan;

public class TestCaseExecutionRepositoryXML implements
		ITestCaseExecutionRepository {

	@Override
	public void addTestCaseExecution(TestCaseExecution testCaseExecution,
			TestCase testCase) {
		
				
/*		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		//Projeto
		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			String projectNameFromXML = projectFromXML.getName();
			if (projectNameFromXML.equals(testCase.getProjectName())) {
				Vector<Requirement> requirementsFromXML = projectFromXML.getRequirements();
				//Requisito	
				for (int j = 0; j < requirementsFromXML.size(); j++) {
					Requirement requirementFromXML = (Requirement)requirementsFromXML.elementAt(j);
					if (requirementFromXML.getIdentifier().equals(testCase.getRequirement().getIdentifier())) {
						Vector<TestCase> testCasesFromXML = requirementFromXML.getTestCases();
						if(testCasesFromXML != null){
						//Caso de teste
						for (int k = 0; k < testCasesFromXML.size(); k++) {
							TestCase testCaseFromXML = testCasesFromXML.elementAt(k);
							if(testCaseFromXML.getId().equals(testCase.getId())){
								
								Vector<TestCaseExecution> executions = testCaseFromXML.getTestCaseExecution();
								
								if(executions == null){
									executions = new Vector<TestCaseExecution>();
								}
								executions.addElement(testCaseExecution);
								testCaseFromXML.setTestCaseExecution(executions);
								testCasesFromXML.setElementAt(testCaseFromXML, k);
								requirementFromXML.setTestCases(testCasesFromXML);
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
	
				
			}
		}*/

	}

	@Override
	public Vector<TestCaseExecution> getTestCaseExecution(TestCase testCase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTestCaseExecution(TestCaseExecution testcaseProcedure,
			TestCase testCase) {
		// TODO Auto-generated method stub

	}

	@Override
	public TestCaseExecution searchTestCaseExecution(String id,
			String testCaseid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTestCaseExecution(TestCaseExecution testcaseProcedure,
			TestCase testCase) {
		// TODO Auto-generated method stub

	}

}
