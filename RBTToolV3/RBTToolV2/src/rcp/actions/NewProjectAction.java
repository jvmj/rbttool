package rcp.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.dialogs.NewProjectDialog;
import rcp.views.ProjectView;
import essentials.Project;
import facade.RBTToolFacade;

//Esta Action roda ao ir no menu bar em File > New Project. Detalhes no metodo run().
public class NewProjectAction extends Action implements ISelectionListener {

	public final static String ID = "actions.newProjectAction";
	private final IWorkbenchWindow window;
	private StructuredSelection selection;

	


	public NewProjectAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&New Project");
		setToolTipText("New project");


	}
	
	@Override
	public ImageDescriptor getImageDescriptor() {
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/new_projectToolbar.gif");
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
	
		
			
		
		RBTToolFacade rbtToolFachada = RBTToolFacade.getInstance();
		//Abre o Dialog para inserir informações sobre o novo projeto
		NewProjectDialog d = new NewProjectDialog(window.getShell());

		int code = d.open();
		if (code == Window.OK) {
			//Ao clicar em OK, cria um novo projeto e abre um editor

			final Project newProject = new Project(d.getName(), d
					.getMainDescription());

			newProject.setDueDate(d.getDueDate());
			newProject.setRequirementBaseline(d.getRequirementBaseLine());
			newProject.setStartDate(d.getStartDate());
			newProject.setResources(d.getResourcesString());
			

			
			rbtToolFachada.addProject(newProject);
			
			//Chama a View
			ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
			//Chama a ListViewer da View chamada
			ListViewer list = myProjectView.viewer;
			//Atualiza a ListViewer
			list.setInput(rbtToolFachada.getProjects());

		}

	}

}
