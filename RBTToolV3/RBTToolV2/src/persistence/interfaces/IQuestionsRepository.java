package persistence.interfaces;

import java.util.Vector;

import essentials.Project;
import essentials.Question;
import essentials.Questionnaire;

public interface IQuestionsRepository {
	public void addQuestion(Question question, Questionnaire questionnaire, Project project);

	public void removeQuestion(Question question, Questionnaire questionnaire, Project project);

	public void updateQuestion(Question question);
	
	public Question searchQuestionName(String QuestionName, String questionnaire);

	public void importQuestion();
	
	public Vector<Question> getQuestions(String questionnaireID, String project);

	public Question searchQuestion(String questionId, String questionnaireName, String projectName);
	
	public Question searchQuestionName(String questionDescription, String questionnaireName, String projectName);

}
