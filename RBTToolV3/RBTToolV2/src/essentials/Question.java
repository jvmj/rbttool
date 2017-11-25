package essentials;

public class Question {

	private String description;
	private Risk risk;
	private String riskLeashedToAnswer;
	

	public Question() {
		super();
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public String getRiskLeashedToAnswer() {
		return riskLeashedToAnswer;
	}

	public void setRiskLeashedToAnswer(String riskLeashedToAnswer) {
		this.riskLeashedToAnswer = riskLeashedToAnswer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Question other = (Question) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
}
