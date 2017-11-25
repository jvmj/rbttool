package exceptions;

public class QuestionDoesntExistException extends Exception {

	private String cause;

	public QuestionDoesntExistException(String cause) {
		super();
		this.cause = cause;
	}
}
