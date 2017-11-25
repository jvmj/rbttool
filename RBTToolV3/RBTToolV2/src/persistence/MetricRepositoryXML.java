package persistence;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import persistence.interfaces.IMetricRepository;

import util.RBTToolXStream;
import essentials.Metric;

public class MetricRepositoryXML implements IMetricRepository {
	
	private final String metricsPath = "files"+File.separator+"metrics";
	private final String metricXML = "metrics.xml";

	@Override
	public void addMetric(Metric metric) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(metricsPath,metricXML);
		if (objetos == null) {
			objetos = new Vector<Object>();
		}
		objetos.addElement(metric);
		rbtToolXStream.writeXML(metricsPath, metricXML, objetos);		
	}

	@Override
	public void removeMetric(Metric metric) {
		// TODO Auto-generated method stub

	}

	@Override
	public Metric searchMetric(String name) {
		// TODO Auto-generated method stub
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(metricsPath,metricXML);
		if (objetos != null && objetos.size() != 0) {
			
			for (Iterator iterator = objetos.iterator(); iterator.hasNext();){
				Metric metric = (Metric)iterator.next();
				if(metric.getName().equals(name)){
					return metric;
				}
			}
		}
	
		
		return null;
	}

	@Override
	public void updateMetric(Metric metric) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<Metric> getMetrics() {
		// TODO Auto-generated method stub
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(metricsPath,metricXML);
		Vector<Metric> metrics = new Vector<Metric>();
		
		for(int i=0;i<objetos.size();i++){
			Metric metricFromXML = (Metric)objetos.elementAt(i);
			metrics.addElement(metricFromXML);
		}
		
		return metrics;

	}

}
