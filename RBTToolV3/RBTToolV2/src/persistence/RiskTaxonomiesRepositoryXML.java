package persistence;

import java.io.File;
import java.util.Vector;

import persistence.interfaces.IRiskTaxonomiesRepository;

import util.RBTToolXStream;
import essentials.RiskTaxonomy;

public class RiskTaxonomiesRepositoryXML implements IRiskTaxonomiesRepository {
	
	private final String riskTaxonomiesPath = "files"+File.separator+"taxonomy";

	@Override
	public void addRiskTaxonomy(RiskTaxonomy riskTaxonomy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Object> getRiskTaxonomies() {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(riskTaxonomiesPath,"risk_taxonomy.xml");
		return objetos;
	}

	@Override
	public void importRiskTaxonomy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRiskTaxonomy(RiskTaxonomy riskTaxonomy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RiskTaxonomy searchRiskTaxonomy(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRiskTaxonomy(RiskTaxonomy riskTaxonomy) {
		// TODO Auto-generated method stub
		
	}

}
