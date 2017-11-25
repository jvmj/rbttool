package essentials;

public class RequirementRisk {

	private String name;
	private String description;
	private RiskTaxonomy riskTaxonomy;

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

	public RiskTaxonomy getRiskTaxonomy() {
		return riskTaxonomy;
	}

	public void setRiskTaxonomy(RiskTaxonomy riskTaxonomy) {
		this.riskTaxonomy = riskTaxonomy;
	}

}
