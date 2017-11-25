package persistence;

import java.util.Vector;

import persistence.interfaces.IQuestionsRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.Question;
import essentials.Questionnaire;

public class QuestionsRepositoryXML implements IQuestionsRepository {
	
		
	@Override
	public void addQuestion(Question question, Questionnaire questionnaire, Project project) {

		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Questionnaire> questionnairesFromXML = projectFromXML.getQuestionnaires();

				for (int j = 0; j < questionnairesFromXML.size(); j++) {
					Questionnaire questionnaireFromXML = (Questionnaire)questionnairesFromXML.elementAt(j);
					if (questionnaireFromXML.equals(questionnaire)) {
						Vector<Question> questionsFromXML = questionnaireFromXML.getQuestions();
						if (questionsFromXML == null) {
							questionsFromXML = new Vector<Question>();
						}				
						questionsFromXML.addElement(question);
						questionnaireFromXML.setQuestions(questionsFromXML);
						questionnairesFromXML.setElementAt(questionnaireFromXML,j);
						projectFromXML.setQuestionnaires(questionnairesFromXML);
						objetos.setElementAt(projectFromXML,i);
						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetos);
						break;
					}
				}
			}
		}
	}

	@Override
	public void importQuestion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeQuestion(Question question, Questionnaire questionnaire, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Questionnaire> questionnairesFromXML = projectFromXML.getQuestionnaires();

				for (int j = 0; j < questionnairesFromXML.size(); j++) {
					Questionnaire questionnaireFromXML = (Questionnaire)questionnairesFromXML.elementAt(j);
					if (questionnaireFromXML.equals(questionnaire)) {
						Vector<Question> questionsFromXML = questionnaireFromXML.getQuestions();
						if (questionsFromXML != null) {
							questionsFromXML.remove(question);
						}
						questionnaireFromXML.setQuestions(questionsFromXML);
						questionnairesFromXML.setElementAt(questionnaireFromXML,j);
						projectFromXML.setQuestionnaires(questionnairesFromXML);
						//objetos.removeElementAt(i);
						objetos.setElementAt(projectFromXML,i);
						rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetos);
						break;
					}
				}
			}
		}
		
	}

	@Override
	public Question searchQuestion(String questionId, String questionnaireName, String projectName) {
		// Cria uma question para ser retornada
		Question question = null;
		// Cria uma questionnaire para iterar
		Questionnaire questionnaire = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		if (objetos != null) {
			for (int i = 0; i < objetos.size(); i++) {
				Project project = (Project) objetos.elementAt(i);
				if (project.getName().equals(projectName)) {
					Vector<Questionnaire> questionnaires = project.getQuestionnaires();
					if (questionnaires != null) {
						for (int j = 0; j < questionnaires.size(); j++) {
							questionnaire = (Questionnaire) questionnaires.elementAt(j);
							if (questionnaire.getName().equals(questionnaireName)) {
								Vector<Question> questions = questionnaire.getQuestions();
								if (questions != null) {
									for (int k = 0; k < questions.size(); k++) {
										question = questions.elementAt(k);
										if (questionId.equals(question.getDescription())) {
											return question;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	
	public Question searchQuestionName(String questionDescription, String questionnaireName, String projectName) {
		// Cria uma question para ser retornada
		Question question = null;
		// Cria uma questionnaire para iterar
		Questionnaire questionnaire = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		if (objetos != null) {
			for (int i = 0; i < objetos.size(); i++) {
				Project project = (Project) objetos.elementAt(i);
				if (project.getName().equals(projectName)) {
					Vector<Questionnaire> questionnaires = project.getQuestionnaires();
					if (questionnaires != null) {
						for (int j = 0; j < questionnaires.size(); j++) {
							questionnaire = (Questionnaire) questionnaires.elementAt(j);
							if (questionnaire.getName().equals(questionnaireName)) {
								Vector<Question> questions = questionnaire.getQuestions();
								if (questions != null) {
									for (int k = 0; k < questions.size(); k++) {
										question = questions.elementAt(k);
										if (questionDescription.equals(question.getDescription())) {
											return question;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public Vector<Question> getQuestions(String questionnaireIDArg, String projectArg){
		
		// Cria uma questionnaire para iterar
		Questionnaire questionnaire = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		if (objetos != null) {
			for (int i = 0; i < objetos.size(); i++) {
				Project project = (Project) objetos.elementAt(i);
				if (project.getName().equals(projectArg)) {
					Vector<Questionnaire> questionnaires = project.getQuestionnaires();
					if (questionnaires != null) {
						for (int j = 0; j < questionnaires.size(); j++) {
							questionnaire = (Questionnaire) questionnaires.elementAt(j);
							if (questionnaire.getName().equals(questionnaireIDArg)) {
								return(questionnaire.getQuestions());
								
							}
						}
					}
				}
			}
		}
		return null;
		
	}

	@Override
	public Question searchQuestionName(String QuestionName, String questionnaire) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}


}
