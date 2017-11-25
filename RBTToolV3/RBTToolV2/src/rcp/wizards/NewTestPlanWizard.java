package rcp.wizards;



import java.util.Vector;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.PlatformUI;

import rcp.views.ProjectView;
import rcp.views.TestPlanView;
import essentials.IdentifiedRisk;
import essentials.Project;
import essentials.Requirement;
import essentials.TestCase;
import essentials.TestCaseStatus;
import essentials.TestIteration;
import essentials.TestPlan;
import facade.RBTToolFacade;

public class NewTestPlanWizard extends Wizard {

	private String nameProject;
	
	private TestPlanGeneralInformationWizardPage testPlanGeneralInformationWizardPage;
	private TestIterationsWizardPage testIterationsWizardPage;
	
	private Vector<TestPlan> testPlans = new Vector<TestPlan>();
	
	
	public NewTestPlanWizard(String nameProject){
		super.addPages();
		this.nameProject = nameProject;
		setWindowTitle("New Test Plan");
		
		testPlanGeneralInformationWizardPage = new TestPlanGeneralInformationWizardPage(nameProject);
		testIterationsWizardPage = new TestIterationsWizardPage(nameProject);
		
		addPage(testPlanGeneralInformationWizardPage);
		addPage(testIterationsWizardPage);
		
	}
	
	
	@Override
	public boolean performFinish() {
		
		//testIterationsWizardPage = (TestIterationsWizardPage) testPlanGeneralInformationWizardPage.getNextPage();
		//Chama a View
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		//Chama a ListViewer da View chamada
		ListViewer list = myProjectView.viewer;
		
		//Pega o projeto selecionado na list
		Project p = (Project) ((IStructuredSelection)list.getSelection()).getFirstElement();
		
		//Project p = RBTToolFacade.getInstance().searchProject(nameProject);
		
		TestPlan testPlan = new TestPlan();
		
		testPlan.setName(testPlanGeneralInformationWizardPage.getTestPlanName());
		testPlan.setDescription(testPlanGeneralInformationWizardPage.getMainDescription());
		
		
		Vector<TestIteration> testIterations = new Vector<TestIteration>();
		testIterations = testIterationsWizardPage.getTestIterations();
		testPlan.settestIterations(testIterations);
		
		for (int i = 0; i < testIterations.size(); i++) {
			
			TestIteration ti = testIterations.elementAt(i);
			ti.setTestPlanName(testPlanGeneralInformationWizardPage.getTestPlanName());
		}
		
		testPlans = p.getTestPlans();
		
		if(testPlans == null){
			testPlans = new Vector<TestPlan>();
		}
		RBTToolFacade.getInstance().addTestPlan(testPlan, p);
		
		testPlans.add(testPlan);
		p.setTestPlans(testPlans);
		TestPlanView tView = (TestPlanView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestPlanView.VIEW_ID);
		tView.viewer.setInput(testPlans);
		
		//Rotina de geração de casos de teste
		for (int i = 0; i < testIterations.size(); i++) {
			//Para cada TestIteration faça
			TestIteration ti = testIterations.elementAt(i);
			
			Vector<Requirement> requirementsFromTI = ti.getRequirements();
			
			for (int j = 0; j < requirementsFromTI.size(); j++) {
				//Para cada Requirement pertencente a uma TestIteration faça
				Requirement reqFromTi = requirementsFromTI.elementAt(j);
				
				Vector<IdentifiedRisk> identifiedRisksFromReqFromTi = reqFromTi.getIdentifiedRisks();
				
				if(identifiedRisksFromReqFromTi != null){
					for (int k = 0; k < identifiedRisksFromReqFromTi.size(); k++) {
						
						//Para cada IdentifiedRisk pertencente a um Requirement, que por sua vez pertence a um TestIteration, faça
						IdentifiedRisk irFtomReqFromTi = identifiedRisksFromReqFromTi.elementAt(k);
						
						//Crie um TestCase
						TestCase tc = new TestCase();
						//Justificativa do risco identificado é a descrição do caso de teste
						tc.setDescription(irFtomReqFromTi.getCause());
						//Identificador: TC+Id do requisito+justificativa+nome do risco
						tc.setId("TC" + irFtomReqFromTi.getRequirementID() + irFtomReqFromTi.getCause() + irFtomReqFromTi.getRisk().getName());
						tc.setPreConditions("");
						tc.setPosConditions("");
						tc.setProjectName(this.nameProject);
						tc.setRisk(irFtomReqFromTi.getRisk());
						tc.setRequirement(reqFromTi);
						//Caso de teste gerado ainda não é executado
						tc.setStatus(TestCaseStatus.NOT_EXECUTED);
						//Criando caso de teste
						RBTToolFacade.getInstance().addTestCase(tc, reqFromTi, ti, testPlan, p);
						
					}
			}
			}
		}
		
		
		return true;
	}

	
	
}
