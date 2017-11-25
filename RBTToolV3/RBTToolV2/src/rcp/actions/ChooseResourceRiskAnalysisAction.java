package rcp.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.dialogs.ChooseResourceRiskAnalysisDialog;
import essentials.Project;


public class ChooseResourceRiskAnalysisAction extends Action implements
		ISelectionListener {
	
	public final static String ID = "actions.newRiskAnalysis";
	private final IWorkbenchWindow window;
	private StructuredSelection selection;
	
	
	
	public ChooseResourceRiskAnalysisAction(IWorkbenchWindow window) {
		super();
		this.window = window;
		setId(ID);
		setText("Perform Risk Analysis");
		//Faz com que a ação só funcione qdo um projeto for selecionado
		window.getSelectionService().addSelectionListener(this);
	}


	@Override
	public ImageDescriptor getImageDescriptor() {
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/analysis.gif");
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
	public void run() {
		
		new ChooseResourceRiskAnalysisDialog(window.getShell()).open();
		
		/*int code = d.open();
		if (code == Window.OK) {
			
			RiskAnalysisEditorInput input;
			String resourceName = d.getResourcesList().getSelection()[0];
			
			
			//Chama a View
			ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
			//Chama a ListViewer da View chamada
			ListViewer list = myProjectView.viewer;
			
			//Pega o projeto selecionado na list
			Project p = (Project) ((IStructuredSelection)list.getSelection()).getFirstElement();
			String projectName = p.getName();
			
			RiskAnalysis riskAnalysis = new RiskAnalysis();
			riskAnalysis.setNameResource(resourceName);
			
			input = new RiskAnalysisEditorInput(projectName, resourceName);
				
			try {
				input = new RiskAnalysisEditorInput(projectName, resourceName);
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input, RiskAnalysisEditor.ID);
			} catch (PartInitException e) {
			
				e.printStackTrace();
			}
			
			
					

			
			
			//close();
			
			
			
			
		}*/
	}

}
