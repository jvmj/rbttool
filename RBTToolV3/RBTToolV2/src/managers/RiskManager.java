package managers;

import java.util.Vector;

import persistence.RisksRepositoryXML;
import persistence.interfaces.IRisksRepository;
import essentials.Risk;
import exceptions.RBTToolValidationException;

public class RiskManager {
	
private IRisksRepository risksRepository;
	
	public RiskManager() {
		super();
		this.risksRepository = new RisksRepositoryXML();
	}
	
	public void addRisk(Risk risk) {
		/*try {
			if (exists(riskTaxonomies)) {
				throw new RBTToolValidationException(
						"Já existe um projeto cadastrado com o nome informado");
			} else {
				riskTaxonomiesRepository.addRiskTaxonomy(riskTaxonomies);
			}
		} catch (RiskTaxonomyAlreadyDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public void removeRisk(Risk risk) {
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

	public void updateRisk(Risk risk) {
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

	public void importRisk() {

	}

	public Risk searchRisk(String name) {
		Risk riskReturned = risksRepository.searchRisk(name);
		if (riskReturned == null) {
			try {
				throw new RBTToolValidationException("Doens't exist any project with this name!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return riskReturned;
		}
		return null;		
	}
	
	public Vector<Object> getRisks(){
		return risksRepository.getRisks();
	}
	
	/**
	 * Verifies whether a risk exists on repository.
	 * @param riskTaxonomies
	 * @return
	 */
	public boolean exists(Risk risk) {
		Risk riskFound = risksRepository.searchRisk(risk.getName());
		if (riskFound != null && riskFound.getName().equals(risk.getName())) {
			return true;
		} else {
			return false;
		}
	}

}
