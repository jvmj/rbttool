package rcp.actions;

import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.dialogs.EditProjectDialog;
import rcp.dialogs.EditRequirementDialog;
import rcp.dialogs.TestCaseDialog;
import rcp.views.ProjectView;
import rcp.views.RequirementsView;
import rcp.views.TestCasesView;
import rcp.views.TestIterationsView;
import rcp.views.TestPlanView;
import essentials.IdentifiedRisk;
import essentials.Project;
import essentials.Requirement;
import essentials.TestCase;
import essentials.TestIteration;
import essentials.TestPlan;
import facade.RBTToolFacade;

public class EditRequirementAction extends Action implements ISelectionListener {
	
	private StructuredSelection selection;
	private final IWorkbenchWindow window;
	
	public EditRequirementAction(IWorkbenchWindow window) {
		this.window = window;
		
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			selection = (StructuredSelection) incoming;
			setEnabled(selection.size() == 1
					&& selection.getFirstElement() instanceof Requirement);
		} else {
			// Other selections, for example containing text or of other kinds.
			setEnabled(false);
		}

	}
	
	@Override
	public ImageDescriptor getImageDescriptor() {
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/pencil.gif");
	}
	
	
	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "Edit selected requirement";
	}
	
	@Override
	public void run() {
		
		RBTToolFacade rbtToolFachada = RBTToolFacade.getInstance();
		
		
		//Chama a View de Projetos
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		//Chama a ListViewer da View chamada
		ListViewer projectList = myProjectView.viewer;
		
		//Chama a View de Requirements
		RequirementsView rView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
		TableViewer tableR = rView.tableViewer;
		
		
		Project p = (Project) ((IStructuredSelection)projectList.getSelection()).getFirstElement();
		
		Vector<Requirement> requirements = p.getRequirements();
		
		Requirement sel = (Requirement) ((IStructuredSelection)tableR.getSelection()).getFirstElement();
		
		if(null != sel){
			
			Requirement r = (Requirement) sel;
			EditRequirementDialog d = new EditRequirementDialog(window.getShell(), p.getName(), r);
			int code = d.open();
			
			if(code == Window.OK){
				
				r.setName(d.getNameString());
				r.setDescription(d.getMainDescriptionString());
				r.setModule(d.getModuleString());
				r.setVersion(d.getVersionString());
				
				rbtToolFachada.updateRequirement(r, p);
				
				requirements.setElementAt(r, tableR.getTable().getSelectionIndex());
				tableR.refresh();
				
			}
		}
		
	}

}
