package essentials;

import java.util.Vector;

public class TestCaseExecution {
	
	private String id;
	
	private String notes;
	
	private String resourceName;
	
	private String date;
	
	private String time;
	
	private Vector<ProcedureFailed> proceduresFailed;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String result) {
		this.notes = result;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Vector<ProcedureFailed> getProceduresFailed() {
		return proceduresFailed;
	}

	public void setProceduresFailed(Vector<ProcedureFailed> proceduresFailed) {
		this.proceduresFailed = proceduresFailed;
	}
	
	

}
