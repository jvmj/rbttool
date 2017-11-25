package managers;

import persistence.QuestionnairesRepositoryXML;
import persistence.interfaces.IQuestionnairesRepository;
import essentials.Project;
import essentials.Questionnaire;
import essentials.Requirement;
import exceptions.RBTToolValidationException;

public class QuestionnaireManager {

	private IQuestionnairesRepository questionnairesRepository;

	public QuestionnaireManager() {
		super();
		this.questionnairesRepository = new QuestionnairesRepositoryXML();
	}
	
	/**
	 * Metodo responsavel pela inclusao de um questionnaire num repositorio.
	 * @param requirement
	 * Agora é preciso saber se existe algum projeto
	 */
	public void addQuestionnaire(Questionnaire questionnaire, Project project) {
		
		ProjectManager projectsManager = new ProjectManager();
		//Verifica se o questionnaire especificado existe ou nao
		if(!projectsManager.exists(project)){
			try {
				throw new RBTToolValidationException("You must create a project!");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			if(exists(questionnaire,project)){
				try {
					throw new RBTToolValidationException("The questionnaire with this name is already defined!");
				} catch (RBTToolValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else{
				questionnairesRepository.addQuestionnaire(questionnaire, project);
			}
			
		}

	}

	public void removeQuestionnaire(Questionnaire questionnaire, Project project) {
		
		ProjectManager projectsManager = new ProjectManager();
		
		if(!projectsManager.exists(project)){
			try {
				throw new RBTToolValidationException("Doesn't exist any project with these informations");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			if(!exists(questionnaire, project)){
				try {
					throw new RBTToolValidationException("Doesn't exist any questionnaire with these informations");
				} catch (RBTToolValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				questionnairesRepository.removeQuestionnaire(questionnaire, project);
			}
		}
		
	}

	public void updateRequirement(Requirement requirement) {

	}

	public void importRequirement() {

	}

	public Questionnaire searchQuestionnaire(String questionnaireName, String projectName) {
		
		Questionnaire questionnaire = questionnairesRepository.searchQuestionnaire(questionnaireName, projectName);
		if(questionnaire == null){
			try {
				throw new RBTToolValidationException("Doens't exist any requirement with this ID");
			} catch (RBTToolValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
			return questionnaire;
		
		return null;
	}
	
	/*public Questionnaire searchQuestionnaireName(String questionnaireName, String projectName) {
	
		Questionnaire questionnaire = questionnairesRepository.searchQuestionnaireName(questionnaireName, projectName);
		if(questionnaire == null){
			try {
				throw new QuestionnaireDoesntExistException("Doens't exist any requirement with this Name");
			} catch (QuestionnaireDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
			return questionnaire;
		
		return null;
	}*/
	
	/**
	 * Verifica se o questionnaire ja foi cadastrado no repositorio.
	 * @param questionnaire
	 * @return
	 */
	public boolean exists(Questionnaire questionnaire, Project project) {		
		
		Questionnaire questionnaireFound = questionnairesRepository.searchQuestionnaire(questionnaire.getName(),project.getName());
			if (questionnaireFound != null && questionnaireFound.getName().equals(questionnaire.getName())) {
				return true;
			} else {
				return false;
			}
		
	}
	
	

}
