package exceptions;

public class ProjectAlreadyDefinedException extends Exception {
	private String cause;

	public ProjectAlreadyDefinedException(String cause) {
		super();
		this.cause = cause;
	}

}
