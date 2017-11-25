package exceptions;

public class QuestionnaireAlreadyDefinedException extends Exception {
	private String cause;

	public QuestionnaireAlreadyDefinedException(String cause) {
		super();
		this.cause = cause;
	}

}
