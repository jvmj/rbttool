package essentials;

import java.util.Vector;

public class Equation {
	
	private String name;
	private String type;
	private Vector<Metric> metrics;
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Vector<Metric> getMetrics() {
		return metrics;
	}


	public void setMetrics(Vector<Metric> metrics) {
		this.metrics = metrics;
	}
	

}
