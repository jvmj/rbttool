package rcp.actions;

import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.views.DetailsView;
import rcp.views.IdentifiedRisksView;
import rcp.views.ProjectView;
import rcp.views.QuestionnairesView;
import rcp.views.RequirementsView;
import essentials.Project;
import facade.RBTToolFacade;

public class DeleteProjectAction extends Action implements ISelectionListener {

	private StructuredSelection selection;
	private final IWorkbenchWindow window;
	
	public DeleteProjectAction(IWorkbenchWindow window) {
		this.window = window;
		//Faz com que a ação só funcione qdo um projeto for selecionado
		window.getSelectionService().addSelectionListener(this);
		
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
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/delete_icon.gif");
	}
	
	
	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "Delete Project";
	}

	@Override
	public void run() {
		
		boolean confirmation = MessageDialog.openQuestion(this.window.getShell(), "Delete Project", "Do you really want to delete this project?");
		
		if(confirmation == true){
		
			RBTToolFacade rbtToolFachada = RBTToolFacade.getInstance();
			
			
			//Chama a View de Projetos
			ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
			//Chama a ListViewer da View chamada
			ListViewer list = myProjectView.viewer;
			
			//Pega o projeto selecionado na list
			Project p = (Project) ((IStructuredSelection)list.getSelection()).getFirstElement();
			
			rbtToolFachada.removeProject(p);
			
			Vector<Object> projects = rbtToolFachada.getProjects();
			projects.remove(p);
			
			list.setInput(projects);
			
			//É preciso atualizar a table de requisitos, por isso a operação abaixo
			RequirementsView myReqView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
			TableViewer table = myReqView.tableViewer;
			myReqView.headerLabel.setText("Nothing selected");
			
			//Atualizando table de questionarios
			QuestionnairesView myQView = (QuestionnairesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(QuestionnairesView.VIEW_ID);
			ListViewer listQ = myQView.viewer;
			myQView.headerLabel.setText("Nothing selected");
			
			//Atualizando area de detalhamento de requisitos
			DetailsView dView = (DetailsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(DetailsView.VIEW_ID);
			dView.idElementLabel.setText("");
			dView.nameElementLabel.setText("");
			dView.descriptionElementText.setText("");
			dView.moduleElementLabel.setText("");
			dView.versionElementLabel.setText("");
			
			//Atualizando table de IdentifiedRisks
			IdentifiedRisksView irView = (IdentifiedRisksView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(IdentifiedRisksView.VIEW_ID);
			TableViewer tableIR = irView.tableViewer;
			irView.headerLabel.setText("Nothing selected");
			
			
			table.setInput(null);
			listQ.setInput(null);
			tableIR.setInput(null);
			
			
			
		}
	}

}
