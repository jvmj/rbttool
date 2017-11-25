package essentials;

import java.util.Vector;

public class TestPlan {
	
	private String name;
	private String description;
	private int noIterations; 
	private Vector<TestIteration> testIterations;
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoIterations() {
		return noIterations;
	}

	public void setNoIterations(int noIterations) {
		this.noIterations = noIterations;
	}

	public Vector<TestIteration> gettestIterations() {
		return testIterations;
	}

	public void settestIterations(Vector<TestIteration> iterations) {
		this.testIterations = iterations;
	}

	
	

}
