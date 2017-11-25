package managers;

import java.util.Vector;

import essentials.Project;
import essentials.Requirement;
import essentials.TestCase;
import essentials.TestIteration;
import essentials.TestPlan;
import persistence.TestCasesRepositoryXML;
import persistence.interfaces.ITestCasesRepository;

public class TestCasesManager {
	
	private ITestCasesRepository testCasesRepository;

	public TestCasesManager() {
		super();
		this.testCasesRepository = new TestCasesRepositoryXML();
	}
	
	public void addTestCase(TestCase testCase, Requirement requirement, TestIteration testIteration, TestPlan testPlan, Project project) {
		
		testCasesRepository.addTestCase(testCase, requirement, testIteration, testPlan, project);
	}
	
	public void updateTestCase(TestCase testCase, Requirement requirement, TestIteration testIteration, TestPlan testPlan, Project project){
		
		testCasesRepository.updateTestCase(testCase, requirement, testIteration, testPlan, project);
	}
	
	public Vector<TestCase> getTestCasesFromIteration(TestIteration testIteration, TestPlan testPlan, Project project){
		
		return testCasesRepository.getTestCasesFromIteration(testIteration, testPlan, project);
	}
	

}
