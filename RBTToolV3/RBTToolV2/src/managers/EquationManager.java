package managers;

import persistence.EquationsRepositoryXML;
import persistence.interfaces.IEquationsRepository;
import essentials.Equation;

public class EquationManager {
	
	private IEquationsRepository equationsRepository;

	public EquationManager() {
		super();
		this.equationsRepository =  new EquationsRepositoryXML();
	}
	
	//Como as equa��es ser�o fixas nesta vers�o, n�o ha preocupacao com exce��es
	public void addEquation(Equation equation){
		
		equationsRepository.addEquation(equation);
	}
	
	public Equation searchEquation(String equationName){
		
		return equationsRepository.searchEquation(equationName);
	}
	
	

}
