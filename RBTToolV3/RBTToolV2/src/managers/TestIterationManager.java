package managers;

import java.util.Vector;

import persistence.TestIterationRepositoryXML;
import persistence.interfaces.ITestIterationRepository;
import essentials.Project;
import essentials.TestIteration;
import essentials.TestPlan;
import exceptions.RBTToolValidationException;

public class TestIterationManager {
	
	private ITestIterationRepository testIterationRepository;
	
	public TestIterationManager(){
		this.testIterationRepository = new TestIterationRepositoryXML();
	}

	
	public void addTestIteration(TestIteration testIteration,TestPlan testPlan, Project project){
		
		testIterationRepository.addTestIteration(testIteration, testPlan, project);
	}
	
	public TestIteration searchTestIteration(String testIterationID, String testPlanName, String projectName) {
		
		TestIteration testIteration = testIterationRepository.searchTestIteration(testIterationID, testPlanName, projectName);
		
		if (testIteration == null){
			try {
				throw new RBTToolValidationException("Doens't exist any Iteration with this ID");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			return testIteration;
		
		return null;
	}
	
	public Vector<TestIteration> getTestIterations(TestPlan testPlan, Project project){
		return testIterationRepository.getTestIterations(testPlan, project);
	}
}
