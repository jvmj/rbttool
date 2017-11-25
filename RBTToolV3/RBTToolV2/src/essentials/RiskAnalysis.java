package essentials;

import facade.RBTToolFacade;

public class RiskAnalysis {
	
	private String riskAnalysisID;
	
	private String nameResource;
	private String requirementID;
	private String projectName;
	
	//Utilizando as metricas de Amland + dependência
	private double costClientValue;
	private double costVendorValue;
	private double sizeValue;
	private double designValue;
	private double newValue;
	private double complexValue;
	private double impactValue;
	private double dependenceValue;
	private double probability; 
	private double riskExposure;
	

	
	
	private static final int NUMERO_DE_METRICAS = 5;
	
	private double[] multi = new double[NUMERO_DE_METRICAS];
	
	private double mean;
	
	// INICIO MÉTODOS IMPRESCINDÍVEIS PARA ANÁLISE
	
	public double calculateImpact(double valueClient, double valueVendor){
		
		RBTToolFacade facade = RBTToolFacade.getInstance();
		
		Metric metricCostClient = facade.searchMetric("Cost for Client");
		double weightCostClient = metricCostClient.getWeight();
		
		Metric metricCostVendor = facade.searchMetric("Cost for Vendor");
		double wightCostVendor = metricCostVendor.getWeight();
		
		impactValue = (valueClient*weightCostClient + valueVendor*wightCostVendor)/2;
		
		return impactValue;
	}
	
	
	public double calculateMean(double valueNew, double valueDesign, double valueSize, double valueComplex, double valueDependence){
		
		RBTToolFacade facade = RBTToolFacade.getInstance();
		
		Metric metricNewFeature = facade.searchMetric("New Feature");
		double weightNewFeature = metricNewFeature.getWeight();
		
		Metric metricDesign = facade.searchMetric("Design");
		double weightDesign = metricDesign.getWeight();
		
		Metric metricSize = facade.searchMetric("Size/Change");
		double weightSize = metricSize.getWeight();
		
		Metric metricComplex = facade.searchMetric("Complexity");
		double weightComplex = metricComplex.getWeight();
		
		Metric metricDependece = facade.searchMetric("Dependence");
		double weightDependence = metricDependece.getWeight();
		
		
		multi[0] = valueNew*weightNewFeature;
		multi[1] = valueDesign*weightDesign;
		multi[2] = valueSize*weightSize;
		multi[3] = valueComplex*weightComplex;
		multi[4] = valueDependence*weightDependence;

		
		
		double sum = 0;
		for (int i = 0; i < multi.length; i++) {
			
			sum = sum + multi[i];
		}
		
		mean = sum/NUMERO_DE_METRICAS;
		
		
		
		return mean;
	}
	
/*	//Metodo auxiliar utilizado para pegar o valor da maior metrica
	private double getMaxMulti(double[] valores){
		
		double max = Double.MIN_VALUE;
		
		for (int i = 0; i < valores.length; i++) {
			if(valores[i] > max){
				max = valores[i];
			}
		}
		
		return max;
		
	}*/
	
public double getHighestWeightedValue(){
		
		RBTToolFacade facade = RBTToolFacade.getInstance();
		
		Metric metricNewFeature = facade.searchMetric("New Feature");
		double weightNewFeature = metricNewFeature.getWeight();
		
		Metric metricDesign = facade.searchMetric("Design");
		double weightDesign = metricDesign.getWeight();
		
		Metric metricSize = facade.searchMetric("Size/Change");
		double weightSize = metricSize.getWeight();
		
		Metric metricComplex = facade.searchMetric("Complexity");
		double weightComplex = metricComplex.getWeight();
		
		Metric metricDependece = facade.searchMetric("Dependence");
		double weightDependence = metricDependece.getWeight();
		
		double result = ((weightNewFeature*3) + (weightDesign*3) + (weightSize*3) + (weightComplex*3) + (weightDependence*3))/NUMERO_DE_METRICAS;
		
		return result;
	}
	
	
	public double calculateProbability(){
		
		//probability = mean/getMaxMulti(multi);
		probability = mean/getHighestWeightedValue();
		
		return probability;
		
	}
	
	public double calculateRiskExposure(double impact, double probability){
		
		double riskExposure = impact*probability;
		
		return riskExposure;
	}
	
	//FIM MÉTODOS IMPRESCINDÍVEIS PARA ANÁLISE
	
	public String getRiskAnalysisID() {
		return riskAnalysisID;
	}

	public void setRiskAnalysisID(String riskAnalysisID) {
		this.riskAnalysisID = riskAnalysisID;
	}

	public String getNameResource() {
		return nameResource;
	}

	public void setNameResource(String nameResource) {
		this.nameResource = nameResource;
	}

	public double getCostClientValue() {
		return costClientValue;
	}

	public void setCostClientValue(double costClientValue) {
		this.costClientValue = costClientValue;
	}

	public double getCostVendorValue() {
		return costVendorValue;
	}

	public void setCostVendorValue(double costVendorValue) {
		this.costVendorValue = costVendorValue;
	}

	public double getSizeValue() {
		return sizeValue;
	}

	public void setSizeValue(double sizeValue) {
		this.sizeValue = sizeValue;
	}

	public double getDesignValue() {
		return designValue;
	}

	public void setDesignValue(double designValue) {
		this.designValue = designValue;
	}

	public double getNewValue() {
		return newValue;
	}

	public void setNewValue(double newValue) {
		this.newValue = newValue;
	}

	public double getComplexValue() {
		return complexValue;
	}

	public void setComplexValue(double complexValue) {
		this.complexValue = complexValue;
	}

	public double getImpactValue() {
		return impactValue;
	}

	public void setImpactValue(double impactValue) {
		this.impactValue = impactValue;
	}

	public double getDependenceValue() {
		return dependenceValue;
	}

	public void setDependenceValue(double dependenceValue) {
		this.dependenceValue = dependenceValue;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getRiskExposure() {
		return riskExposure;
	}

	public void setRiskExposure(double riskExposure) {
		this.riskExposure = riskExposure;
	}

	public String getRequirementID() {
		return requirementID;
	}

	public void setRequirementID(String requirementID) {
		this.requirementID = requirementID;
	}


	public double[] getMulti() {
		return multi;
	}


	public void setMulti(double[] multi) {
		this.multi = multi;
	}


	public double getMean() {
		return mean;
	}


	public void setMean(double mean) {
		this.mean = mean;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	

	

}
