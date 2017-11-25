package persistence.interfaces;

import java.util.Vector;

import essentials.TestCase;
import essentials.TestCaseProcedure;

public interface ITestCaseProcedureRepository {
	
	public void addTestCaseProcedure(TestCaseProcedure testcaseProcedure, TestCase testCase);
	
	public void removeTestCaseProcedure(TestCaseProcedure testcaseProcedure, TestCase testCase);
	
	public void updateTestCaseProcedure(TestCaseProcedure testcaseProcedure, TestCase testCase);
	
	public TestCaseProcedure searchTestCaseProcedure(String id, String testCaseid);
	
	public Vector<TestCaseProcedure> getTestCaseProcedures(TestCase testCase);

}
