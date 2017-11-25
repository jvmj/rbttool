package persistence.interfaces;

import essentials.Equation;

public interface IEquationsRepository {
	
	public void addEquation(Equation equation);
	
	public void removeEquation(Equation equation);
	
	public void updateEquation(Equation equation);
	
	public Equation searchEquation(String equationName);

}
