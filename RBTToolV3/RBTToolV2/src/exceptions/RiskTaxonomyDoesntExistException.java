package exceptions;

public class RiskTaxonomyDoesntExistException extends Exception {
	private String cause;

	public RiskTaxonomyDoesntExistException(String cause) {
		super();
		this.cause = cause;
	}

}
