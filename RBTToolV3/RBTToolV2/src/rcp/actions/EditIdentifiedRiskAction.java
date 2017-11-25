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
import rcp.dialogs.EditIdentifiedRiskDialog;
import rcp.views.IdentifiedRisksView;
import rcp.views.ProjectView;
import rcp.views.RequirementsView;
import essentials.IdentifiedRisk;
import essentials.Project;
import essentials.Requirement;
import facade.RBTToolFacade;

public class EditIdentifiedRiskAction extends Action implements ISelectionListener {

	private StructuredSelection selection;
	private final IWorkbenchWindow window;
	
	public EditIdentifiedRiskAction(IWorkbenchWindow window) {
		this.window = window;
		
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			selection = (StructuredSelection) incoming;
			setEnabled(selection.size() == 1
					&& selection.getFirstElement() instanceof IdentifiedRisk);
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
		return "Edit identified risk cause";
	}

	@Override
	public void run() {
		
		
		RBTToolFacade rbtToolFachada = RBTToolFacade.getInstance();
			
			
		//Chama a View de Projetos
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		//Chama a ListViewer da View chamada
		ListViewer list = myProjectView.viewer;
		
		//Chama a View
		RequirementsView rView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
		TableViewer tableR = rView.tableViewer;
		
		//Chama a View
		IdentifiedRisksView irView = (IdentifiedRisksView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(IdentifiedRisksView.VIEW_ID);
		TableViewer tableIR = irView.tableViewer;
		
		//Pega o projeto selecionado na list
		Project p = (Project) ((IStructuredSelection)list.getSelection()).getFirstElement();
		Requirement r = (Requirement) ((IStructuredSelection)tableR.getSelection()).getFirstElement();
		IdentifiedRisk sel = (IdentifiedRisk) ((IStructuredSelection)tableIR.getSelection()).getFirstElement();
		
		Vector<IdentifiedRisk> identifiedRisks = rbtToolFachada.getIdentifiedRisks(r, p);
		
		if ((null != sel)) {
		
			IdentifiedRisk ir = sel;
			EditIdentifiedRiskDialog d = new EditIdentifiedRiskDialog(window.getShell(), p.getName(), r.getIdentifier(), ir);
			int code = d.open();
			
			if(code == Window.OK){
				ir.setCause(d.getCauseString());
				rbtToolFachada.updateIdentifiedRisk(ir, r, ir.getResourceName(), p);
				
				identifiedRisks.setElementAt(ir, tableIR.getTable().getSelectionIndex());
				tableIR.refresh();
				
				
			}
		
		}
		
			
			
			
	}

}
