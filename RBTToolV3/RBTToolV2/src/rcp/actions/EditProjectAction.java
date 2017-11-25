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
import rcp.dialogs.TestCaseDialog;
import rcp.views.ProjectView;
import rcp.views.TestCasesView;
import rcp.views.TestIterationsView;
import rcp.views.TestPlanView;
import essentials.Project;
import essentials.TestCase;
import essentials.TestIteration;
import essentials.TestPlan;
import facade.RBTToolFacade;

public class EditProjectAction extends Action implements ISelectionListener {
	
	private StructuredSelection selection;
	private final IWorkbenchWindow window;
	
	public EditProjectAction(IWorkbenchWindow window) {
		this.window = window;
		
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
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/tab_pencil.gif");
	}
	
	
	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "Edit selected project";
	}
	
	@Override
	public void run() {
		
		RBTToolFacade rbtToolFachada = RBTToolFacade.getInstance();
		
		
		//Chama a View de Projetos
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		//Chama a ListViewer da View chamada
		ListViewer projectList = myProjectView.viewer;
		
		
		Project sel = (Project) ((IStructuredSelection)projectList.getSelection()).getFirstElement();
		
		Vector<Object> projects = rbtToolFachada.getProjects();
		
		if ((null != sel)) {
			
			Project p = sel;
			EditProjectDialog d = new EditProjectDialog(window.getShell(), sel);
			int code = d.open();
			
			if (code == Window.OK){
				
				p.setDueDate(d.getDueDate());
				p.setMainDescription(d.getMainDescription());
				p.setName(d.getName());
				p.setRequirementBaseline(d.getRequirementBaseLine());
				p.setResources(d.getResourcesString());
				p.setStartDate(d.getStartDate());
				
				rbtToolFachada.updateProject(p);
				
				projects.setElementAt(p, projectList.getList().getSelectionIndex());
				projectList.refresh();
				
			}
		}
		
	}

}
