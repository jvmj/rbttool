package essentials;

public class Metric {
	
	private String name;
	private int weight;
	private String type; //se eh uma metrica de Probabilidade ou Impacto
	private String metricDetails;
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getMetricDetails() {
		return metricDetails;
	}


	public void setMetricDetails(String metricDetails) {
		this.metricDetails = metricDetails;
	}
	
	
		

}
