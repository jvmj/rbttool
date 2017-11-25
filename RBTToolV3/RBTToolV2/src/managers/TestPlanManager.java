package managers;

import java.util.Vector;

import persistence.TestPlanRepositoryXML;
import persistence.interfaces.ITestPlanRepository;
import essentials.Project;
import essentials.TestPlan;
import exceptions.RBTToolValidationException;

public class TestPlanManager {
	
	private ITestPlanRepository testPlanRepository;

	public TestPlanManager() {
		super();
		this.testPlanRepository = new TestPlanRepositoryXML();
	}
	
	
	public void addTestPlan(TestPlan testPlan, Project project){
		
		if(existsTestPlan(testPlan,project)){
			try {
				throw new RBTToolValidationException("The Test Plan with this name is already defined!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else{
			testPlanRepository.addTestPlan(testPlan, project);
		}
		
		
	}
	
	
	public TestPlan searchTestPlan(String testPlanName, String projectName) {
		
		TestPlan testPlan = testPlanRepository.searchTestPlan(testPlanName, projectName);
		
		if(testPlan == null){
			try {
				throw new RBTToolValidationException("Doens't exist any test plan with this name");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else 
			return testPlan;
			
		
		return null;
	}
	
	
	public boolean existsTestPlan(TestPlan testPlan, Project project) {		
		
		TestPlan foundTestPlan = testPlanRepository.searchTestPlan(testPlan.getName(), project.getName());
		if (foundTestPlan != null && foundTestPlan.getName().equals(testPlan.getName())) {
			return true;
		} else {
			return false;
		}
	
	}
	
	public Vector<TestPlan> getTestPlans(String projectName){
		
		return testPlanRepository.getTestPlans(projectName);
	}
	

}
