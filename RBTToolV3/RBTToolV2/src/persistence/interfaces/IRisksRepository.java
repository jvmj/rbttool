package persistence.interfaces;

import java.util.Vector;

import essentials.Risk;

public interface IRisksRepository {

	public void addRisk(Risk risk);

	public void removeRisk(Risk risk);

	public void updateRisk(Risk risk);

	public Risk searchRisk(String name);
	
	public Vector<Object> getRisks();

	public void importRisk();
}
