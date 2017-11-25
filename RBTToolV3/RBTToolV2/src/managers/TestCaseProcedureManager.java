package managers;

import essentials.TestCase;
import essentials.TestCaseProcedure;
import persistence.TestCaseProcedureRepositoryXML;
import persistence.interfaces.ITestCaseProcedureRepository;

public class TestCaseProcedureManager {
	
	private ITestCaseProcedureRepository testCaseProcedureRepository;

	public TestCaseProcedureManager() {
		super();
		this.testCaseProcedureRepository = new TestCaseProcedureRepositoryXML();
	}
	
	public void addTestCaseProcedure(TestCaseProcedure testcaseProcedure,
			TestCase testCase){
		testCaseProcedureRepository.addTestCaseProcedure(testcaseProcedure, testCase);
	}
	
	

}
