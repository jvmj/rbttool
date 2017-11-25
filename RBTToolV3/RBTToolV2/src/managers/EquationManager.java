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
	
	//Como as equações serão fixas nesta versão, não ha preocupacao com exceções
	public void addEquation(Equation equation){
		
		equationsRepository.addEquation(equation);
	}
	
	public Equation searchEquation(String equationName){
		
		return equationsRepository.searchEquation(equationName);
	}
	
	

}
