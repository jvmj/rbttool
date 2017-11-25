package persistence.interfaces;

import essentials.Project;
import essentials.Questionnaire;

public interface IQuestionnairesRepository {

	public void addQuestionnaire(Questionnaire questionnaire, Project project);

	public void removeQuestionnaire(Questionnaire questionnaire, Project project);

	public void updateQuestionnaire(Questionnaire questionnaire);

	public Questionnaire searchQuestionnaire(String name, String projectName);
	
	public Questionnaire searchQuestionnaireName(String QuestionnaireName, String project);

	public void importQuestionnaire();

}
