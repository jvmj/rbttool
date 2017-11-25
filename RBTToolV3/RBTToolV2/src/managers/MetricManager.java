package managers;

import java.util.Vector;

import persistence.MetricRepositoryXML;
import persistence.interfaces.IMetricRepository;
import essentials.Metric;

public class MetricManager {
	
	private IMetricRepository metricsRepository;

	public MetricManager() {
		super();
		this.metricsRepository = new MetricRepositoryXML();
	}
	
	//Como as metricas ser�o fixas nesta vers�o, n�o ha preocupacao com exce��es
	public void addMetric(Metric metric){
		
		metricsRepository.addMetric(metric);
	}
	
	public Metric searchMetric(String name){
		return metricsRepository.searchMetric(name);
	}
	
	public Vector<Metric> getMetrics(){
		
		return metricsRepository.getMetrics();
	}
	
	

}
