package persistence.interfaces;

import java.util.Vector;

import essentials.Metric;

public interface IMetricRepository {
	
	public void addMetric(Metric metric);

	public void removeMetric(Metric metric);

	public void updateMetric(Metric metric);
	
	public Metric searchMetric(String name);
	
	public Vector<Metric> getMetrics();

}
