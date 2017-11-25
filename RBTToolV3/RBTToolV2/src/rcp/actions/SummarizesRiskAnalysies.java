package rcp.actions;

import java.io.File;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.views.ProjectView;
import rcp.views.RequirementsView;
import essentials.Project;
import essentials.Requirement;
import essentials.RiskAnalysis;
import essentials.RiskManagementReport;
import facade.RBTToolFacade;

public class SummarizesRiskAnalysies extends Action implements ISelectionListener  {
	
	private final IWorkbenchWindow window;
	private StructuredSelection selection;
	public final static String ID = "actions.summarizesRiskAnalysiesAction";
	private Vector<Requirement> requirements;
	private Vector<Object> importedRiskAnalysies;
	
	public SummarizesRiskAnalysies(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Summarize Risk Analysies");
		setToolTipText("Summarize Risk Analysies");
		//Faz com que a ação só funcione qdo um projeto for selecionado
		//window.getSelectionService().addSelectionListener(this);
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
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
	public ImageDescriptor getImageDescriptor() {
		
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/exclamation_triangle_green.png");
	}
	@Override
	public void run() {
				
		RBTToolFacade rbtToolFachada = RBTToolFacade.getInstance();
		
		//Chama a View
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		//Chama a ListViewer da View chamada
		ListViewer list = myProjectView.viewer;
		
		//Pega o projeto selecionado na list
		Project p = (Project) ((IStructuredSelection)list.getSelection()).getFirstElement();
		requirements = p.getRequirements();
		//NULL proposital
		importedRiskAnalysies = rbtToolFachada.getImportedRiskAnalysies(null);
		
		int numResources = 0;
		
		for (int i = 0; i < requirements.size(); i++) {
			double riskExposure = 0;
			double impact = 0;
			double probability = 0;
			//Para cada requisito faça:
			Requirement r = (Requirement)requirements.elementAt(i);
			
			//Leia o XML contendo as análises importadas
			for (int j = 0; j < importedRiskAnalysies.size(); j++) {
				//Para cada análise faça:
				RiskAnalysis ra = (RiskAnalysis)importedRiskAnalysies.elementAt(j);
				
				//Primeiro verifica se a analise de riscos importada é do projeto selecionado pelo usuário
				if(ra.getProjectName().equals(p.getName())){
					
					//Se a ID do requisito da analise for igual a ID do requisito
					if(ra.getRequirementID().equals(r.getIdentifier())){
						//Computa valor individual da risk exposure
						riskExposure = riskExposure + ra.getRiskExposure();
						impact = impact + ra.getImpactValue();
						probability = probability + ra.getProbability();
						//Incrementa num de recursos que analisaram este requisito para calcular a média
						numResources++;
					}
				}
			
			}
			
			//Media sendo calculada...
			double riskExposureFinal = riskExposure/numResources;
			double impactFinal = impact/numResources;
			double probabilityFinal = probability/numResources;
			
			
			r.setRiskExposureFinal(riskExposureFinal);
			r.setImpactFinal(impactFinal);
			r.setProbabilityFinal(probabilityFinal);
			rbtToolFachada.updateRequirement(r, p);
			
			//Finalizada a consulta do requisito atual, zera o valor de numResources para calcular a media do RiskExposure do próximo requisito
			numResources = 0;
		}
		
		RiskManagementReport report = new RiskManagementReport();
		
		report.setId(p.getName());
		report.setRequirements(requirements);
		
		if(rbtToolFachada.existsRiskManagementReport(report, p)){
			report = rbtToolFachada.searchRiskManagementReport(report.getId(), p.getName());
			report.setRequirements(requirements);
			rbtToolFachada.updateRiskManagementReport(report, p);
		  }
		  else{
			  //Por enquanto o nome do projeto vai identificar o Report...ou seja: 1 projeto possui 0-1 Report
			  
			  rbtToolFachada.addRiskManagementReport(report, p);
		  }
		
		//Atualização da view de requisitos
		for (int i = 0; i < requirements.size(); i++) {
			Requirement r = requirements.elementAt(i);
			requirements.setElementAt(r, i);
		}
		
		
		RequirementsView rView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
		rView.tableViewer.setInput(requirements);
		//Fim atualização
	}

}
