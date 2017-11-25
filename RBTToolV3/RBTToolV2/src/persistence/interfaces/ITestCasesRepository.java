package persistence.interfaces;

import java.util.Vector;

import essentials.Project;
import essentials.Requirement;
import essentials.TestCase;
import essentials.TestIteration;
import essentials.TestPlan;

public interface ITestCasesRepository {
	
	public void addTestCase(TestCase testCase, Requirement requirement, TestIteration testIteration, TestPlan testPlan, Project project) ;
	
	public void updateTestCase(TestCase testCase, Requirement requirement, TestIteration testIteration, TestPlan testPlan, Project project);
	
	public Vector<TestCase> getTestCasesFromIteration(TestIteration testIteration, TestPlan testPlan, Project project);

}
