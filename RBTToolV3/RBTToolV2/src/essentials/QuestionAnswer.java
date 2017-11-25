package essentials;

import java.util.HashMap;

public class QuestionAnswer {

	private String questionAnswerID;
	private String userName;
	private String projectName;
	private String questionnaireName;
	private HashMap<Question, String> questionAnswerAssociation;

	public String getQuestionAnswerID() {
		return questionAnswerID;
	}

	public void setQuestionAnswerID(String questionAnswerID) {
		this.questionAnswerID = questionAnswerID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQuestionnaireName() {
		return questionnaireName;
	}

	public void setQuestionnaireName(String questionnaireName) {
		this.questionnaireName = questionnaireName;
	}

	public HashMap<Question, String> getQuestionAnswerAssociation() {
		return questionAnswerAssociation;
	}

	public void setQuestionAnswerAssociation(
			HashMap<Question, String> questionAnswerAssociation) {
		this.questionAnswerAssociation = questionAnswerAssociation;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((questionAnswerID == null) ? 0 : questionAnswerID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionAnswer other = (QuestionAnswer) obj;
		if (questionAnswerID == null) {
			if (other.questionAnswerID != null)
				return false;
		} else if (!questionAnswerID.equals(other.questionAnswerID))
			return false;
		return true;
	}

}
