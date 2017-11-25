package exceptions;

public class RBTToolValidationException extends Exception {
	private String cause;

	public RBTToolValidationException(String cause) {
		super();
		this.cause = cause;
	}

}
