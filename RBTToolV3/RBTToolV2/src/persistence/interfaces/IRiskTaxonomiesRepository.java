package persistence.interfaces;

import java.util.Vector;

import essentials.RiskTaxonomy;

public interface IRiskTaxonomiesRepository {

	public void addRiskTaxonomy(RiskTaxonomy riskTaxonomy);

	public void removeRiskTaxonomy(RiskTaxonomy riskTaxonomy);

	public void updateRiskTaxonomy(RiskTaxonomy riskTaxonomy);

	public RiskTaxonomy searchRiskTaxonomy(String name);
	
	public Vector<Object> getRiskTaxonomies();

	public void importRiskTaxonomy();
}
