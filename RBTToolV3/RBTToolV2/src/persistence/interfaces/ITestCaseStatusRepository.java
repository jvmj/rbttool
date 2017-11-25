package persistence.interfaces;

import java.util.Vector;

import essentials.TestCaseStatus;

public interface ITestCaseStatusRepository {
	
	public Vector<Object> getTestCaseStatus();
	
	public TestCaseStatus searchTestCaseStatus(String name);

}
