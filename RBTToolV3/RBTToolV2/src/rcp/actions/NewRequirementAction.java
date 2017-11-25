package rcp.actions;

import java.util.Vector;

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
import rcp.dialogs.NewRequirementDialog;
import rcp.views.ProjectView;
import rcp.views.RequirementsView;
import essentials.Project;
import essentials.Requirement;
import facade.RBTToolFacade;


//Esta action roda quando vai no menu bar file > new requirement. Abre um ListProjectsDialog (vide metodo run())
public class NewRequirementAction extends Action implements ISelectionListener{
	
	public final static String ID = "actions.newRequirement";
	private final IWorkbenchWindow window;
	private StructuredSelection selection;
	
	
	//Cria um vector de requirements contendo os atuais itens, adiciona novo requirement e joga na tabela
	//Isto é feito pq o vector de requirements não é atualizado em tempo de execução
	private Vector<Requirement> requirements = new Vector<Requirement>();
	
	public NewRequirementAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&New Requirement");
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
					&& selection.getFirstElement() instanceof Requirement);
		} else {
			// Other selections, for example containing text or of other kinds.
			setEnabled(false);
		}
		
		
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
		//p = new Project(p.getName(),p.getMainDescription());
		
		
		NewRequirementDialog dialog = new NewRequirementDialog(window.getShell(), p.getName());
		
		requirements = p.getRequirements();
		
		if(requirements == null){
			requirements = new Vector<Requirement>();
		}
		
		//Abre o dialog
		int code = dialog.open();
		
		//Ação para se clicar no botão OK
		if(code == Window.OK){
			
			Requirement requirement = new Requirement(dialog.getIdString(), dialog.getNameString(), dialog.getVersionString(), dialog.getMainDescriptionString());
			requirement.setModule(dialog.getModuleString());
			
			rbtToolFachada.addRequirement(requirement, p);
			
			//Cria um vector de requirements contendo os atuais itens, adiciona novo requirement e joga na tabela
			//Isto é feito pq o vector de requirements não é atualizado em tempo de execução
			

			requirements.add(requirement);
			p.setRequirements(requirements);
			//Chama a View
			RequirementsView rView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
			//Chama a TableViewer da View chamada e adiciona um item a tabela
			rView.tableViewer.setInput(requirements);

			
		}
		
		

	}

	
}
