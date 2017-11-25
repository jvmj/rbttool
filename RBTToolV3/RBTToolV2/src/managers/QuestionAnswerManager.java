package managers;

import java.io.File;
import java.util.Vector;

import persistence.QuestionAnswerRepositoryXML;
import persistence.interfaces.IQuestionAnswerRepository;
import essentials.QuestionAnswer;
import exceptions.RBTToolValidationException;

public class QuestionAnswerManager {

	private IQuestionAnswerRepository questionAnswerRepository;
	
	public QuestionAnswerManager() {
		super();
		this.questionAnswerRepository = new QuestionAnswerRepositoryXML();
	}
	

	public void saveQuestionAnswer(QuestionAnswer questionAnswer) {

				questionAnswerRepository.saveQuestionAnswer(questionAnswer);
	
	}
	
	public QuestionAnswer searchQuestionAnswer(String questionAnswerID) {
		QuestionAnswer questionAnswerReturned = questionAnswerRepository.searchQuestionAnswer(questionAnswerID);
		if (questionAnswerReturned == null) {
			try {
				throw new RBTToolValidationException("Doens't exist any Question-Answer association with this identification!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return questionAnswerReturned;
		}
		return null;		
	}
	
	public Vector<Object> getQuestionAnswers() {
		return questionAnswerRepository.getQuestionAnswers();
	}
	
	public Vector<Object> getImportedQuestionAnswers() {
		return questionAnswerRepository.getImportedQuestionAnswers();
	}
	
	public boolean exportQuestionAnswer(QuestionAnswer questionAnswer, File file){
		
		return	questionAnswerRepository.exportQuestionAnswer(questionAnswer, file);
		
	}
	
	public boolean importQuestionAnswerFile(File file){

		return	questionAnswerRepository.importQuestionAnswerFile(file);

	}

	public boolean exists(QuestionAnswer questionAnswer) {
		QuestionAnswer questionAnswerfound = questionAnswerRepository.searchQuestionAnswer(questionAnswer.getQuestionAnswerID());
		if (questionAnswerfound != null && questionAnswerfound.equals(questionAnswer)) {
			return true;
		} else {
			return false;
		}
	}

}
