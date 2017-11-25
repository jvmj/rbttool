package managers;

import java.util.Vector;

import persistence.QuestionsRepositoryXML;
import persistence.interfaces.IQuestionsRepository;
import essentials.Project;
import essentials.Question;
import essentials.Questionnaire;
import exceptions.RBTToolValidationException;

public class QuestionManager {

	private IQuestionsRepository questionsRepository;

	public QuestionManager() {
		super();
		this.questionsRepository = new QuestionsRepositoryXML();
	}

	public void addQuestion(Question question, Questionnaire questionnaire, Project project) {

		ProjectManager projectsManager = new ProjectManager();
		QuestionnaireManager questionnaireManager = new QuestionnaireManager();

		if (!projectsManager.exists(project)) {
			try {
				throw new RBTToolValidationException("You must create a project!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (!questionnaireManager.exists(questionnaire, project)) {
				try {
					throw new RBTToolValidationException("You must create a questionnaire!");
				} catch (RBTToolValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (exists(question, questionnaire, project)) {
					try {
						throw new RBTToolValidationException("The question with this ID is already defined!");
					} catch (RBTToolValidationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					questionsRepository.addQuestion(question, questionnaire, project);
				}
			}

		}

	}

	public Question searchQuestion(String questionId, String questionnaireName, String projectName) {

		Question question = questionsRepository.searchQuestion(questionId, questionnaireName, projectName);
		if (question == null) {
			try {
				throw new RBTToolValidationException("Doens't exist any question with this ID");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			return question;

		return null;
	}
	
	
	public Question searchQuestionName(String questionDescription, String questionnaireName, String projectName){
	
		Question question = questionsRepository.searchQuestionName(questionDescription, questionnaireName, projectName);
		if (question == null) {
			try {
				throw new RBTToolValidationException("Doens't exist any question with this ID");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			return question;

		return null;
	}
	
	public void removeQuestion(Question question, Questionnaire questionnaire, Project project){		
		ProjectManager projectsManager = new ProjectManager();
		QuestionnaireManager questionnaireManager = new QuestionnaireManager();

		if (!projectsManager.exists(project)) {
			try {
				throw new RBTToolValidationException("You must create a project!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (!questionnaireManager.exists(questionnaire, project)) {
				try {
					throw new RBTToolValidationException("You must create a questionnaire!");
				} catch (RBTToolValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (!exists(question, questionnaire, project)) {
					try {
						throw new RBTToolValidationException("Doesn't exist any question with these informations!");
					} catch (RBTToolValidationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					questionsRepository.removeQuestion(question, questionnaire, project);
				}
			}

		}
	}
	
	public Vector<Question> getQuestions(String questionnaireIDArg, String projectArg){
		ProjectManager projectsManager = new ProjectManager();
		QuestionnaireManager questionnaireManager = new QuestionnaireManager();
		Project project = new Project(projectArg,"");
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setName(questionnaireIDArg);
		Vector<Question> returnVector = null;

		if (!projectsManager.exists(project)) {
			try {
				throw new RBTToolValidationException("You must create a project!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (!questionnaireManager.exists(questionnaire, project)) {
				try {
					throw new RBTToolValidationException("You must create a questionnaire!");
				} catch (RBTToolValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {				
				returnVector = questionsRepository.getQuestions(questionnaire.getName(), project.getName());
				if (returnVector == null) {
					try {
						throw new RBTToolValidationException("There is no questions to return!!");
					} catch (RBTToolValidationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return returnVector;
	}

	public boolean exists(Question question, Questionnaire questionnaire,
			Project project) {

		/*Question questionFound = questionsRepository.searchQuestion(question
				.getId(), questionnaire.getName(), project.getName());*/
		//Modificado para testar pela descrição da pergunta, e não pelo ID...
		Question questionFound = questionsRepository.searchQuestion(question
				.getDescription(), questionnaire.getName(), project.getName());
		if (questionFound != null
				&& questionFound.getDescription().equals(question.getDescription())) {
			return true;
		} else {
			return false;
		}

	}
}
