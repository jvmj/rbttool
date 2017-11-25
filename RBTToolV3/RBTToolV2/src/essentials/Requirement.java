package essentials;

import java.util.Vector;

public class Requirement{

	private String identifier;
	private String name;
	private String version;
	private String author;
	private String description;
	private String importance;
	private String module;
	private String status;
	private String baseline;
	private Vector<Scenario> scenarios;
	private Vector<RiskAnalysis> riskAnalysies;
	private Vector<IdentifiedRisk> identifiedRisks;
	private double riskExposureFinal;
	private double impactFinal;
	private double probabilityFinal;
	
	private Vector<TestCase> testCases;
	
	public Requirement(String identificador, String nome, String versao,
			String descricao) {
		super();
		this.identifier = identificador;
		this.name = nome;
		this.version = versao;
		this.description = descricao;
		this.riskAnalysies = new Vector<RiskAnalysis>();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBaseline() {
		return baseline;
	}

	public void setBaseline(String baseline) {
		this.baseline = baseline;
	}
	
	public Vector<Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(Vector<Scenario> scenarios) {
		this.scenarios = scenarios;
	}
	

	public Vector<RiskAnalysis> getRiskAnalysies() {
		return riskAnalysies;
	}

	public void setRiskAnalysies(Vector<RiskAnalysis> riskAnalysies) {
		this.riskAnalysies = riskAnalysies;
	}
	
	

	public double getRiskExposureFinal() {
		return riskExposureFinal;
	}

	public void setRiskExposureFinal(double riskExposureFinal) {
		this.riskExposureFinal = riskExposureFinal;
	}
	
	

	public double getImpactFinal() {
		return impactFinal;
	}

	public void setImpactFinal(double impactFinal) {
		this.impactFinal = impactFinal;
	}

	public double getProbabilityFinal() {
		return probabilityFinal;
	}

	public void setProbabilityFinal(double probabilityFinal) {
		this.probabilityFinal = probabilityFinal;
	}
	
	

	public Vector<IdentifiedRisk> getIdentifiedRisks() {
		return identifiedRisks;
	}

	public void setIdentifiedRisks(Vector<IdentifiedRisk> identifiedRisks) {
		this.identifiedRisks = identifiedRisks;
	}
	
	

	public Vector<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(Vector<TestCase> testCases) {
		this.testCases = testCases;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Requirement))
			return false;
		Requirement other = (Requirement) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

		
}
