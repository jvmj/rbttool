package persistence;

import java.util.Vector;

import persistence.interfaces.IQuestionnairesRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.Questionnaire;

public class QuestionnairesRepositoryXML implements IQuestionnairesRepository {
	
	public void addQuestionnaire(Questionnaire questionnaire, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);

		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project)objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Questionnaire> questionnairesFromXML = projectFromXML.getQuestionnaires();
				
				if (questionnairesFromXML == null) {
					questionnairesFromXML = new Vector<Questionnaire>();
				}				
				questionnairesFromXML.addElement(questionnaire);
				projectFromXML.setQuestionnaires(questionnairesFromXML);
				objetos.setElementAt(projectFromXML,i);
				rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetos);
				break;
			}
		}		
	}

	@Override
	public void updateQuestionnaire(Questionnaire questionnaire) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeQuestionnaire(Questionnaire questionnaire, Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		for (int i = 0; i < objetos.size(); i++) {
			Project projectFromXML = (Project) objetos.elementAt(i);
			if (projectFromXML.equals(project)) {
				Vector<Questionnaire> questionnairesFromXML = projectFromXML.getQuestionnaires();
				if (questionnairesFromXML != null) {
					questionnairesFromXML.removeElement(questionnaire);
				}
				projectFromXML.setQuestionnaires(questionnairesFromXML);
				objetos.removeElementAt(i);
				objetos.insertElementAt(projectFromXML,i);
				rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME, objetos);
				break;
			}
		}
	}

	@Override
	public void importQuestionnaire() {
		// TODO Auto-generated method stub

	}

	@Override
	public Questionnaire searchQuestionnaire(String name, String projectName) {
		Questionnaire questionnaire = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		
		for (int i = 0; i < objetos.size(); i++) {
			Project project = (Project) objetos.elementAt(i);
			if (project.getName().equals(projectName)) {
				Vector<Questionnaire> questionnaires = project.getQuestionnaires();
				if(questionnaires != null){
					for (int j = 0; j < questionnaires.size(); j++) {
						questionnaire = (Questionnaire)questionnaires.elementAt(j);
						if(questionnaire.getName().equals(name))
							break;
						
					}
					return questionnaire;
				}
				//break;
				
			}
			
			
			
		}

		return null;
	}
		
	public Questionnaire searchQuestionnaireName(String name, String projectName) {
		Questionnaire questionnaire = null;
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH,FilesUtil.PROJECTSFILENAME);
		
		for (int i = 0; i < objetos.size(); i++) {
			Project project = (Project) objetos.elementAt(i);
			if (project.getName().equals(projectName)) {
				Vector<Questionnaire> questionnaires = project.getQuestionnaires();
				if(questionnaires != null){
					for (int j = 0; j < questionnaires.size(); j++) {
						questionnaire = (Questionnaire)questionnaires.elementAt(j);
						if(questionnaire.getName().equals(name))
							break;
						
					}
					return questionnaire;
				}
							
			}
			
		}
		

		return null;
	}


}
