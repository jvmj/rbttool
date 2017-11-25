package persistence;

import java.util.Iterator;
import java.util.Vector;

import persistence.interfaces.IRisksRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Risk;

public class RisksRepositoryXML implements IRisksRepository {
	
	

	@Override
	public void addRisk(Risk risk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Object> getRisks() {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.RISKSPATH,FilesUtil.RISKSFILENAME);
		return objetos;
	}

	@Override
	public void importRisk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRisk(Risk risk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Risk searchRisk(String name) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.RISKSPATH,FilesUtil.RISKSFILENAME);
		if (objetos != null && objetos.size() != 0) {
			for (Iterator iterator = objetos.iterator(); iterator.hasNext();) {
				Risk risk = (Risk) iterator.next();
				if (risk.getName().equals(name)) {
					return risk;
				}
			}
		} 
		return null;
	}

	@Override
	public void updateRisk(Risk risk) {
		// TODO Auto-generated method stub
		
	}

}
