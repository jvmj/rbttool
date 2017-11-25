package persistence.interfaces;

import java.util.Vector;

import essentials.TestCase;
import essentials.TestCaseExecution;

public interface ITestCaseExecutionRepository {
	
	public void addTestCaseExecution(TestCaseExecution testCaseExecution, TestCase testCase);
	
	public void removeTestCaseExecution(TestCaseExecution testCaseExecution, TestCase testCase);
	
	public void updateTestCaseExecution(TestCaseExecution testCaseExecution, TestCase testCase);
	
	public TestCaseExecution searchTestCaseExecution(String id, String testCaseid);
	
	public Vector<TestCaseExecution> getTestCaseExecution(TestCase testCase);

}
