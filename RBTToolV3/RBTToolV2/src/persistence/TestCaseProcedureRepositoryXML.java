package persistence;

import java.util.Vector;

import persistence.interfaces.ITestCaseProcedureRepository;

import util.FilesUtil;
import util.RBTToolXStream;

import essentials.Project;
import essentials.Requirement;
import essentials.TestCase;
import essentials.TestCaseProcedure;

public class TestCaseProcedureRepositoryXML implements
		ITestCaseProcedureRepository {

	@Override
	public void addTestCaseProcedure(TestCaseProcedure testcaseProcedure,
			TestCase testCase) {
		
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		
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
						//Caso de teste
						for (int k = 0; k < testCasesFromXML.size(); k++) {
							TestCase testCaseFromXML = testCasesFromXML.elementAt(k);
							if(testCaseFromXML.getId().equals(testCase.getId())){
								
								Vector<TestCaseProcedure> procedures = testCaseFromXML.getTestCaseProcedures();
								
								if(procedures == null){
									procedures = new Vector<TestCaseProcedure>();
								}
								procedures.addElement(testcaseProcedure);
								testCaseFromXML.setTestCaseProcedures(procedures);
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

	}

	@Override
	public Vector<TestCaseProcedure> getTestCaseProcedures(TestCase testCase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTestCaseProcedure(TestCaseProcedure testcaseProcedure,
			TestCase testCase) {
		// TODO Auto-generated method stub

	}

	@Override
	public TestCaseProcedure searchTestCaseProcedure(String id,
			String testCaseid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTestCaseProcedure(TestCaseProcedure testcaseProcedure,
			TestCase testCase) {
		// TODO Auto-generated method stub

	}

}
