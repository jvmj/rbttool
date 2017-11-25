package exceptions;

public class RequirementDoesntExistException extends Exception {

	private String reason;

	public RequirementDoesntExistException(String reason) {
		super();
		this.reason = reason;
	}
	
	
}
