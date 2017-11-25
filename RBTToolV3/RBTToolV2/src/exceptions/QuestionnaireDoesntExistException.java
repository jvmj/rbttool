package exceptions;

public class QuestionnaireDoesntExistException extends Exception {

	private String cause;

	public QuestionnaireDoesntExistException(String cause) {
		super();
		this.cause = cause;
	}

}
