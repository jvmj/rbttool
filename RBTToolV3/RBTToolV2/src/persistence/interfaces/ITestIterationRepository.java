package persistence.interfaces;

import java.util.Vector;

import essentials.Project;
import essentials.TestIteration;
import essentials.TestPlan;

public interface ITestIterationRepository {

	public void addTestIteration(TestIteration testIteration, TestPlan testPlan, Project project);
	
	public TestIteration searchTestIteration (String testIterationID, String testPlanName, String projectName);
	
	public Vector<TestIteration> getTestIterations(TestPlan testPlan, Project project);
	
}
