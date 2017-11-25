package exceptions;

public class QuestionAlreadyDefinedException extends Exception {
	private String cause;

	public QuestionAlreadyDefinedException(String cause) {
		super();
		this.cause = cause;
	}

}
