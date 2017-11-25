package managers;

import java.util.Vector;

import persistence.RiskTaxonomiesRepositoryXML;
import persistence.interfaces.IRiskTaxonomiesRepository;
import essentials.RiskTaxonomy;
import exceptions.RiskTaxonomyDoesntExistException;

public class RiskTaxonomyManager {
	
private IRiskTaxonomiesRepository riskTaxonomiesRepository;
	
	public RiskTaxonomyManager() {
		super();
		this.riskTaxonomiesRepository = new RiskTaxonomiesRepositoryXML();
	}
	
	public void addRiskTaxonomy(RiskTaxonomy riskTaxonomies) {
		/*try {
			if (exists(riskTaxonomies)) {
				throw new ProjectAlreadyDefinedException(
						"Já existe um projeto cadastrado com o nome informado");
			} else {
				riskTaxonomiesRepository.addRiskTaxonomy(riskTaxonomies);
			}
		} catch (RiskTaxonomyAlreadyDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public void removeRiskTaxonomy(RiskTaxonomy riskTaxonomies) {
		/*if(!exists(riskTaxonomies)){
			try {
				throw new RiskTaxonomyDoesntExistException("Doesn't exist any project with these informations");
			} catch (RiskTaxonomyDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			riskTaxonomiesRepository.removeRiskTaxonomy(riskTaxonomies);
		}*/

	}

	public void updateRiskTaxonomy(RiskTaxonomy riskTaxonomies) {
		/*if(!exists(riskTaxonomies)){
			try {
				throw new RiskTaxonomyDoesntExistException("Doesn't exist any project with these informations");
			} catch (RiskTaxonomyDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			riskTaxonomiesRepository.updateRiskTaxonomy(riskTaxonomies);
		}*/

	}

	public void importRequirement() {

	}

	public RiskTaxonomy searchRiskTaxonomy(String name) {
		RiskTaxonomy riskTaxonomyReturned = riskTaxonomiesRepository.searchRiskTaxonomy(name);
		if (riskTaxonomyReturned == null) {
			try {
				throw new RiskTaxonomyDoesntExistException("Doens't exist any project with this name!");
			} catch (RiskTaxonomyDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return riskTaxonomyReturned;
		}
		return null;		
	}
	
	public Vector<Object> getRiskTaxonomies(){
		return riskTaxonomiesRepository.getRiskTaxonomies();
	}
	
	/**
	 * Verifica se o risco ja foi cadastrado no repositorio.
	 * @param riskTaxonomies
	 * @return
	 */
	public boolean exists(RiskTaxonomy riskTaxonomies) {
		RiskTaxonomy riskTaxonomyFound = riskTaxonomiesRepository.searchRiskTaxonomy(riskTaxonomies.getName());
		if (riskTaxonomyFound != null && riskTaxonomyFound.getName().equals(riskTaxonomies.getName())) {
			return true;
		} else {
			return false;
		}
	}

}
