package persistence.interfaces;

import java.util.Vector;

import essentials.Project;
import essentials.TestPlan;

public interface ITestPlanRepository {
	
	public void addTestPlan(TestPlan testPlan, Project project);
	
	public void removeTestPlan (TestPlan testPlan, Project project);
	
	public void updateTestPlan (TestPlan testPlan, Project project);
	
	public TestPlan searchTestPlan(String testPlanName, String projectName);
	
	public Vector<TestPlan> getTestPlans(String projectName);

}
