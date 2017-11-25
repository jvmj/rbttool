package exceptions;

public class ProjectDoesntExistException extends Exception {
	
	private String cause;

	public ProjectDoesntExistException(String cause) {
		super();
		this.cause = cause;
	}
	
	

}
