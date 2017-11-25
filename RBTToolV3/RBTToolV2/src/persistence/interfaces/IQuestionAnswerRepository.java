package persistence.interfaces;

import java.io.File;
import java.util.Vector;

import essentials.QuestionAnswer;

public interface IQuestionAnswerRepository {
	
	public void saveQuestionAnswer(QuestionAnswer questionAnswer);
	
	public QuestionAnswer searchQuestionAnswer(String questionAnswerID);
	
	public Vector<Object> getQuestionAnswers();
	
	public Vector<Object> getImportedQuestionAnswers();
	
	public boolean exportQuestionAnswer(QuestionAnswer questionAnswer, File file);
	
	public boolean importQuestionAnswerFile(File file);
}
