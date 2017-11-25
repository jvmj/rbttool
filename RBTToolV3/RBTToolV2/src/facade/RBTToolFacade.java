package facade;

import java.io.File;
import java.util.Vector;

import managers.EquationManager;
import managers.IdentifiedRisksManager;
import managers.MetricManager;
import managers.ProjectManager;
import managers.QuestionAnswerManager;
import managers.QuestionManager;
import managers.QuestionnaireManager;
import managers.RequirementManager;
import managers.RiskAnalysisManager;
import managers.RiskManagementReportManager;
import managers.RiskManager;
import managers.TestCaseExecutionManager;
import managers.TestCaseProcedureManager;
import managers.TestCasesManager;
import managers.TestIterationManager;
import managers.TestPlanManager;
import essentials.Equation;
import essentials.IdentifiedRisk;
import essentials.Metric;
import essentials.Project;
import essentials.Question;
import essentials.QuestionAnswer;
import essentials.Questionnaire;
import essentials.Requirement;
import essentials.Risk;
import essentials.RiskAnalysis;
import essentials.RiskManagementReport;
import essentials.TestCase;
import essentials.TestCaseExecution;
import essentials.TestCaseProcedure;
import essentials.TestIteration;
import essentials.TestPlan;

public class RBTToolFacade {
	private RequirementManager requirementsManager;
	private ProjectManager projectsManager;
	private MetricManager metricsManager;
	private EquationManager equationManager;
	private RiskManager riskManager;
	private QuestionnaireManager questionnaireManager;
	private QuestionManager questionManager;
	private IdentifiedRisksManager identifiedRisksManager;
	private QuestionAnswerManager questionAnswerManager;
	private RiskAnalysisManager riskAnalysisManager;
	private RiskManagementReportManager riskManagementReportManager;
	private TestPlanManager testPlanManager;
	private TestIterationManager testIterationManager;
	private TestCasesManager testCaseManager;
	private TestCaseProcedureManager testCaseProcedureManager;
	private TestCaseExecutionManager testCaseExecutionManager;
	private static RBTToolFacade fachada = null;

	/*
	 * Protegendo o construtor pra garantir que ninguem tente instanciar a
	 * fachada por ele.
	 */
	private RBTToolFacade() {
		super();
		this.requirementsManager = new RequirementManager();
		this.projectsManager = new ProjectManager();
		this.metricsManager = new MetricManager();
		this.riskManager = new RiskManager();
		this.equationManager = new EquationManager();
		this.questionnaireManager = new QuestionnaireManager();
		this.questionManager = new QuestionManager();
		this.identifiedRisksManager = new IdentifiedRisksManager();
		this.questionAnswerManager = new QuestionAnswerManager();
		this.riskAnalysisManager = new RiskAnalysisManager();
		this.riskManagementReportManager = new RiskManagementReportManager();
		this.testPlanManager = new TestPlanManager();
		this.testIterationManager = new TestIterationManager();
		this.testCaseManager = new TestCasesManager();
		this.testCaseProcedureManager = new TestCaseProcedureManager(); 
		this.testCaseExecutionManager = new TestCaseExecutionManager();
	}

	/*
	 * Implementando o pattern Singleton para que no seja possivel ter duas
	 * intancias da fachada na memoria.
	 */

	public static RBTToolFacade getInstance() {
		if (fachada == null) {
			fachada = new RBTToolFacade();
		}

		return fachada;
	}
	
	/*
	 * Métodos relacionados ao requisito Gerenciar REQUISITOS
	 */
	public void addRequirement(Requirement requirement, Project project) {
		requirementsManager.addRequirement(requirement, project);
		System.out.println("Requirement " + requirement.getName()
				+ " cadastrado com sucesso");
	}
	
	public boolean exists(Requirement requirement, Project project){
		return requirementsManager.exists(requirement, project);
	}
	
	public void updateRequirement(Requirement requirement, Project project) {
		requirementsManager.updateRequirement(requirement, project);
	}
	
	public void removeRequirement(Requirement requisito, Project project){
		requirementsManager.removeRequirement(requisito, project);
		
	}
	
	public Requirement searchRequirement(String requirementID, String project){
		return requirementsManager.searchRequirement(requirementID, project);
	}
	
	public Requirement searchRequirementName(String requirementName, String project){
		return requirementsManager.searchRequirementName(requirementName, project);
	}
	
	public Vector<Requirement> getRequirements(String projectName){
		
		return requirementsManager.getRequirements(projectName);
	}
	
	public Vector<Requirement> getRequirementsREInterval(String projectName, double moreValue, double lessValue){
		
		return requirementsManager.getRequirementsREInterval(projectName, moreValue, lessValue);
	}
	
	/*
	 * Métodos relacionados ao requisito Gerenciar PROJETOS
	 */
	public void addProject(Project project) {
		projectsManager.addProject(project);
		System.out.println("Project " + project.getName()
				+ " cadastrado com sucesso");
	}
	
	public void removeProject(Project project){
		projectsManager.removeProject(project);
	}
	
	public void updateProject(Project project){
		projectsManager.updateProject(project);
		
	}
	
	public Vector<Object> getProjects(){
		return projectsManager.getProjects();
	}
	
	public Project searchProject(String name){
		return projectsManager.searchProject(name);
	}
	
	public boolean exists(Project project){
		return projectsManager.exists(project);
	}
	
	/*
	 * Métodos relacionados ao requisito Gerenciar MÉTRICAS
	 */
	
	public void addMetric(Metric metric){
		this.metricsManager.addMetric(metric);
	}
	
	public Metric searchMetric(String name){
		return this.metricsManager.searchMetric(name);
	}
	
	
	public Vector<Metric> getMetrics(){
		return this.metricsManager.getMetrics();
	}
	
	/*
	 * Methods related to Equations Management
	 */
	public void addEquation(Equation equation){
		this.equationManager.addEquation(equation);
	}
	
	public Equation searchEquation(String equationName){
		
		return this.equationManager.searchEquation(equationName);
	}

	
	
	/*
	 * Methods related to Risk Management
	 */
	
	public Vector<Object> getRisks(){
		return riskManager.getRisks();
	}
	
	public Risk searchRisk(String name){
		return riskManager.searchRisk(name);
	}
	
	/*
	 * Methods related to features from questionnaires
	 */
	public void addQuestionnaire(Questionnaire questionnaire, Project project) {
		questionnaireManager.addQuestionnaire(questionnaire, project);
		System.out.println("Questionnaire " + questionnaire.getName()
				+ " added successfully");
	}
	
	public void removeQuestionnaire(Questionnaire questionnaire, Project project){
		questionnaireManager.removeQuestionnaire(questionnaire, project);
		
	}
	
	public Questionnaire searchQuestionnaire(String questionnaireID, String project){
		return questionnaireManager.searchQuestionnaire(questionnaireID, project);
	}
	
	/*public Requirement searchRequirementName(String requirementName, String project){
		return questionnaireManager.searchRequirementName(requirementName, project);
	}*/

	/*
	 * Methods related to features from questions
	 */
	public void addQuestion(Question question, Questionnaire questionnaire, Project project) {
		questionManager.addQuestion(question, questionnaire, project);
/*		System.out.println("Question " + question.getId()
				+ " cadastrado com sucesso");*/
	}
	
	public Question searchQuestion(String questionID, String questionnaireID, String project){
		return questionManager.searchQuestion(questionID, questionnaireID, project);
	}
	
	public Question searchQuestionName(String questionName, String questionnaireID, String project){
		return questionManager.searchQuestionName(questionName, questionnaireID, project);
	}
	
	public void removeQuestion(Question question, Questionnaire questionnaire, Project project){
		questionManager.removeQuestion(question, questionnaire, project);
		
	}
	
	public Vector<Question> getQuestions(String questionnaireID, String project){
		return questionManager.getQuestions(questionnaireID, project);
	}
	
	

	/*
	 * Methods related to features from IdentifiedRisks
	 */
	
	public void addIdentifiedRisk (IdentifiedRisk identifiedRisk, Requirement requirement, Project project){
		identifiedRisksManager.addIdentifiedRisk(identifiedRisk, requirement, project);
	}
	
	public void updateIdentifiedRisk (IdentifiedRisk identifiedRisk, Requirement requirement, String resourceName, Project project){
		identifiedRisksManager.updateIdentifiedRisk(identifiedRisk, requirement, resourceName, project);
	}
	
	public IdentifiedRisk searchIdentifiedRisk(String id, String RequirementID, String nameProject){
		return identifiedRisksManager.searchIdentifiedRisk(id, RequirementID, nameProject);
	}
	
	public void removeIdentifiedRisk(IdentifiedRisk identifiedRisk, Requirement requirement, Project project){
		identifiedRisksManager.removeIdentifiedRisk(identifiedRisk, requirement, project);
	}
	
	public void exportIdentifiedRisk(Vector<IdentifiedRisk> identifiedRisks, File file){
		identifiedRisksManager.exportIdentifiedRisk(identifiedRisks, file);
	}
	
	public void importIdentifiedRisk(File file){
		identifiedRisksManager.importIdentifiedRisk(file);
	}
	
	public Vector<IdentifiedRisk> getIdentifiedRisks(Requirement requirement, Project project){
		return identifiedRisksManager.getIdentifiedRisks(requirement, project);
	}
	
	public Vector<Object> getImportedIdentifiedRisks(File file){
		return identifiedRisksManager.getImportedIdentifiedRisks(file);
	}
	
	public boolean existsIdentifiedRisk (String id, Requirement requirement, Project project){
		return identifiedRisksManager.existsIdentifiedRisk(id, requirement, project); 
	}
	
	/*
	 * Methods related to features from Question-Answer association
	 */
	
	public void saveQuestionAnswer(QuestionAnswer questionAnswer){
		questionAnswerManager.saveQuestionAnswer(questionAnswer);
	}
	
	public QuestionAnswer searchQuestionAnswer(String questionAnswerID) {
		return questionAnswerManager.searchQuestionAnswer(questionAnswerID);
	}
	
	public Vector<Object> getQuestionAnswers() {
		return questionAnswerManager.getQuestionAnswers();
	}
	
	public Vector<Object> getImportedQuestionAnswers() {
		return questionAnswerManager.getImportedQuestionAnswers();
	}
	
	public boolean exportQuestionAnswer(QuestionAnswer questionAnswer, File file) {
		return questionAnswerManager.exportQuestionAnswer(questionAnswer, file);
	}
	
	public boolean importQuestionAnswerFile(File file) {
		return questionAnswerManager.importQuestionAnswerFile(file);
	}

	
	/*
	 * Methods related to features from Risk Analysis
	 */
	
	public void addRiskAnalysis(RiskAnalysis riskAnalysis, Requirement requirement, Project project){
		riskAnalysisManager.addRiskAnalysis(riskAnalysis, requirement, project);
	}

	
	public RiskAnalysis searchRiskAnalysis(String nameResource, String nameRequirement, String nameProject){
		
		return riskAnalysisManager.searchRiskAnalysis(nameResource, nameRequirement, nameProject);
	}
	
	public void updateRiskAnalysis(RiskAnalysis riskAnalysis, Requirement requirement, String resourceName, Project project){
		
		riskAnalysisManager.updateRiskAnalysis(riskAnalysis, requirement, resourceName, project);
	}
	
	public boolean existsRiskAnalysis(String nameResource, Requirement requirement, Project project){
		
		return riskAnalysisManager.existsRiskAnalysis(nameResource, requirement, project);
	}
	
	public void exportRiskAnalysies(Vector<RiskAnalysis> riskAnalysies, File file){
		
		riskAnalysisManager.exportRiskAnalysies(riskAnalysies, file);
	}
	
	public  void importRiskAnalysies(File file){
		
		riskAnalysisManager.importRiskAnalysies(file);
	}
	
	public Vector<Object> getImportedRiskAnalysies(File file){
		
		return riskAnalysisManager.getImportedRiskAnalysies(file);
	}
	
	
	/*
	 * Methods related to features from Risk Management Report
	 */
	
	public void addRiskManagementReport(RiskManagementReport riskManagementReport, Project project){
		
		riskManagementReportManager.addRiskManagementReport(riskManagementReport, project);
	}
	
	public RiskManagementReport searchRiskManagementReport(String id, String projectName){
	
		return riskManagementReportManager.searchRiskManagementReport(id, projectName);
	}
	
	public void updateRiskManagementReport(RiskManagementReport report, Project project){
		
		riskManagementReportManager.updateRiskManagementReport(report, project);
	}
	
	public boolean existsRiskManagementReport(RiskManagementReport report, Project project){
		
		return riskManagementReportManager.existsRiskManagementReport(report, project);
	}
	
	
	/*
	 * Methods related to features from Test Plan
	 */
	
	public void addTestPlan(TestPlan testPlan, Project project){
		testPlanManager.addTestPlan(testPlan, project);
	}
	
	public TestPlan searchTestPlan(String testPlanName, String projectName){
		return testPlanManager.searchTestPlan(testPlanName, projectName);
	}
	
	public boolean existsTestPlan(TestPlan testPlan, Project project){
		return testPlanManager.existsTestPlan(testPlan, project);
	}
	
	public Vector<TestPlan> getTestPlans(String projectName){
		return testPlanManager.getTestPlans(projectName);
	}
	
	/*
	 * Methods related to features from Test Iteration
	 */

	public void addTestIteration(TestIteration testIteration,TestPlan testPlan, Project project){
		testIterationManager.addTestIteration(testIteration, testPlan, project);
	}
	
	public TestIteration searchTestIteration(String testIterationID, String testPlanName, String projectName){
		return testIterationManager.searchTestIteration(testIterationID, testPlanName, projectName);
	}
	
	public Vector<TestIteration> getTestIterations(TestPlan testPlan, Project project){
		return testIterationManager.getTestIterations(testPlan, project);
	}
	
	/*
	 * Methods related to features from Test Case
	 */
	
	public void addTestCase(TestCase testCase, Requirement requirement, TestIteration testIteration, TestPlan testPlan, Project project) {
		testCaseManager.addTestCase(testCase, requirement, testIteration, testPlan, project);
	}
	
	public void updateTestCase(TestCase testCase, Requirement requirement, TestIteration testIteration, TestPlan testPlan, Project project){
		testCaseManager.updateTestCase(testCase, requirement, testIteration, testPlan, project);
	}
	
	public Vector<TestCase> getTestCasesFromIteration(TestIteration testIteration, TestPlan testPlan, Project project){
		return testCaseManager.getTestCasesFromIteration(testIteration, testPlan, project);
	}

	/*
	 * Methods related to features from Test Case Procedure
	 */
	
	public void addTestCaseProcedure(TestCaseProcedure testcaseProcedure,
			TestCase testCase){
		testCaseProcedureManager.addTestCaseProcedure(testcaseProcedure, testCase);
	}
	
	
	/*
	 * Methods related to features from Test Case Execution
	 */
	
	public void addTestCaseExecution(TestCaseExecution testcaseExecution,
			TestCase testCase){
		testCaseExecutionManager.addTestCaseProcedure(testcaseExecution, testCase);
	}
}


