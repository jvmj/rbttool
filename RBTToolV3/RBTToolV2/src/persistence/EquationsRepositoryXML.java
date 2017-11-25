package persistence;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import persistence.interfaces.IEquationsRepository;

import util.RBTToolXStream;
import essentials.Equation;

public class EquationsRepositoryXML implements IEquationsRepository {
	
	private final String equationsPath = "files"+File.separator+"equations";
	private final String equationXML = "equations.xml";


	@Override
	public void addEquation(Equation equation) {
		// TODO Auto-generated method stub
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(equationsPath,equationXML);
		if (objetos == null) {
			objetos = new Vector<Object>();
		}
		objetos.addElement(equation);
		rbtToolXStream.writeXML(equationsPath, equationXML, objetos);
	}

	@Override
	public void removeEquation(Equation equation) {
		// TODO Auto-generated method stub

	}

	@Override
	public Equation searchEquation(String equationName) {
		// TODO Auto-generated method stub
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(equationsPath,equationXML);
		if (objetos != null && objetos.size() != 0) {
			
			for (Iterator iterator = objetos.iterator(); iterator.hasNext();){
				Equation equation = (Equation)iterator.next();
				if(equation.getName().equals(equationName)){
					return equation;
				}
			}
				
			}
			
		return null;
	}

	@Override
	public void updateEquation(Equation equation) {
		// TODO Auto-generated method stub

	}

}
