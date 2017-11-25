package managers;

import persistence.TestCaseExecutionRepositoryXML;
import persistence.interfaces.ITestCaseExecutionRepository;
import essentials.TestCase;
import essentials.TestCaseExecution;

public class TestCaseExecutionManager {
	
	private ITestCaseExecutionRepository testCaseExecutionRepository;

	public TestCaseExecutionManager() {
		super();
		this.testCaseExecutionRepository = new TestCaseExecutionRepositoryXML();
	}
	
	public void addTestCaseProcedure(TestCaseExecution testcaseExecution,
			TestCase testCase){
		testCaseExecutionRepository.addTestCaseExecution(testcaseExecution, testCase);
	}
	
	

}
