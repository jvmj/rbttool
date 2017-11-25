package exceptions;

public class RiskDoesntExistException extends Exception {
	private String cause;

	public RiskDoesntExistException(String cause) {
		super();
		this.cause = cause;
	}

}
