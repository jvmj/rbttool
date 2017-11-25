package exceptions;

public class RequirementAlreadyDefinedException extends Exception{
	private String motivo;

	public RequirementAlreadyDefinedException(String motivo) {
		super();
		this.motivo = motivo;
	}
	
	
}
