package rcp.actions;

import java.io.File;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import rcp.dialogs.ImportRiskAnalysisDialog;
import rcp.views.ProjectView;
import rcp.views.RequirementsView;
import essentials.Project;
import essentials.Requirement;
import essentials.RiskAnalysis;
import facade.RBTToolFacade;

public class ImportRiskAnalysisAction extends Action implements ISelectionListener{
	
	public final static String ID = "actions.ImportRiskAnalysisAction";
	private final IWorkbenchWindow window;
	private StructuredSelection selection;
	
	private Project project;
	private Vector<Requirement> requirements;
	private RiskAnalysis raTemp;

	public ImportRiskAnalysisAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Performed Risk Analysis");
		//setToolTipText("Import a questionnaire answered previously.");
		// window.getSelectionService().addSelectionListener(this);

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		// TODO Auto-generated method stub
		if (incoming instanceof IStructuredSelection) {
			selection = (StructuredSelection) incoming;
			setEnabled(selection.size() == 1
					&& selection.getFirstElement() instanceof Project);
		} else {
			// Other selections, for example containing text or of other kinds.
			setEnabled(false);
		}

	}

	@Override
	public void run() {

		RBTToolFacade rbtToolFacade = RBTToolFacade.getInstance();

		ImportRiskAnalysisDialog raDialog = new ImportRiskAnalysisDialog(window.getShell());

		String fileChosen = raDialog.openDialog();

		if (fileChosen != null) {						
			File file = new File(fileChosen);
			
			Vector<Object> importedRiskAnalysies = rbtToolFacade.getImportedRiskAnalysies(file);
			
			for (int i = 0; i < importedRiskAnalysies.size(); i++) {
				
				RiskAnalysis ra = (RiskAnalysis) importedRiskAnalysies.elementAt(i);
				Requirement r = rbtToolFacade.searchRequirement(ra.getRequirementID(), ra.getProjectName());
				Vector<RiskAnalysis> riskAnalysies = r.getRiskAnalysies();
				
				this.raTemp = ra;
				
				if(riskAnalysies == null){
					riskAnalysies = new Vector<RiskAnalysis>();
				}
				
				riskAnalysies.add(ra);
				
				Project p = rbtToolFacade.searchProject(ra.getProjectName());
				
				rbtToolFacade.addRiskAnalysis(ra, r, p);
				
				Vector<Requirement> requirements = p.getRequirements();
				
				for (int j = 0; j < requirements.size(); j++) {
					Requirement r1 = requirements.elementAt(j);
					
					if(r1.getIdentifier().equals(r.getIdentifier())){
						Vector<RiskAnalysis> ra1 = r1.getRiskAnalysies();
						if(ra1 == null){
							ra1 = new Vector<RiskAnalysis>();
						}
						ra1.add(ra);
						r1.setRiskAnalysies(ra1);
						requirements.setElementAt(r1, j);
					}
				}
				
				
			}
			
			requirements = rbtToolFacade.searchProject(this.raTemp.getProjectName()).getRequirements();
			this.project = rbtToolFacade.searchProject(raTemp.getProjectName());
			//Atualizando o valor da RE de cada Requirement
			for (int i = 0; i < requirements.size(); i++) {
				double riskExposure = 0;
				double impact = 0;
				double probability = 0;
				//Para cada requisito faça:
				Requirement r = (Requirement)requirements.elementAt(i);
				Vector<RiskAnalysis> riskAnalysies = r.getRiskAnalysies();
				
				for (int j = 0; j < riskAnalysies.size(); j++) {
											
						//Para cada análise faça:
						RiskAnalysis ra = (RiskAnalysis)riskAnalysies.elementAt(j);
							
								//Computa valor individual da risk exposure
								riskExposure = riskExposure + ra.getRiskExposure();
								impact = impact + ra.getImpactValue();
								probability = probability + ra.getProbability();
								//Incrementa num de recursos que analisaram este requisito para calcular a média
					
					}
				
					//Media sendo calculada...
					double riskExposureFinal = riskExposure/riskAnalysies.size();
					double impactFinal = impact/riskAnalysies.size();
					double probabilityFinal = probability/riskAnalysies.size();
					
					
					r.setRiskExposureFinal(riskExposureFinal);
					r.setImpactFinal(impactFinal);
					r.setProbabilityFinal(probabilityFinal);
					rbtToolFacade.updateRequirement(r, project);
					
					
					for (int i1 = 0; i1 < requirements.size(); i1++) {
						Requirement r1 = requirements.elementAt(i1);
						requirements.setElementAt(r1, i1);
					}
					
					Vector<Object> projects = rbtToolFacade.getProjects();
					
					for (int j = 0; j < projects.size(); j++) {
						Project p = (Project) projects.elementAt(j);
						
						if(p.getName().equals(p.getName())){
							
							p.setRequirements(requirements);
						}
					}
					
					RequirementsView rView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
					rView.tableViewer.setInput(requirements);
					
					ProjectView pView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
					pView.viewer.setInput(projects);
			}

		}
	}
}
