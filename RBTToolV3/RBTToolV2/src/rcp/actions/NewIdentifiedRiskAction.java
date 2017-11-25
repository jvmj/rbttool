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
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.dialogs.NewIdentifiedRiskDialog;
import rcp.views.IdentifiedRisksView;
import rcp.views.ProjectView;
import rcp.views.RequirementsView;
import essentials.IdentifiedRisk;
import essentials.Project;
import essentials.Requirement;
import essentials.Risk;
import facade.RBTToolFacade;


//Esta action roda quando vai no menu bar file > new requirement. Abre um ListProjectsDialog (vide metodo run())
public class NewIdentifiedRiskAction extends Action implements ISelectionListener{
	
	public final static String ID = "actions.newIdentifiedRisk";
	private final IWorkbenchWindow window;
	private StructuredSelection selection;
	
	
	//Cria um vector de requirements contendo os atuais itens, adiciona novo requirement e joga na tabela
	//Isto é feito pq o vector de requirements não é atualizado em tempo de execução
	private Vector<IdentifiedRisk> identifiedRisks = new Vector<IdentifiedRisk>();
	
	public NewIdentifiedRiskAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&New identified risk");
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
					&& selection.getFirstElement() instanceof IdentifiedRisk);
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
		
		//Chama a View
		RequirementsView rView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
		TableViewer table = rView.tableViewer;
		
		//Pega o projeto selecionado na list
		Project p = (Project) ((IStructuredSelection)list.getSelection()).getFirstElement();
		//Pega o requisito selecionado na table
		Requirement r = (Requirement) ((IStructuredSelection)table.getSelection()).getFirstElement();
		
		NewIdentifiedRiskDialog d = new NewIdentifiedRiskDialog(window.getShell(), p.getName(), r.getIdentifier());
		
		identifiedRisks = r.getIdentifiedRisks();
		
		if(identifiedRisks == null){
			identifiedRisks = new Vector<IdentifiedRisk>();
		}
		
		//Abre o dialog
		int code = d.open();
		
		//Ação para se clicar no botão OK
		if(code == Window.OK){
			
			IdentifiedRisk ir = new IdentifiedRisk();
			ir.setCause(d.getCauseString());
			Risk risk = rbtToolFachada.searchRisk(d.getRiskString());
			ir.setRisk(risk);
			ir.setId(d.getCauseString() + d.getRiskString());
			ir.setResourceName("TEAM");
			ir.setRequirementID(r.getIdentifier());
			
			if(!rbtToolFachada.existsIdentifiedRisk(ir.getId(), r, p)){
				rbtToolFachada.addIdentifiedRisk(ir, r, p);
			
				identifiedRisks.add(ir);
				r.setIdentifiedRisks(identifiedRisks);
			
				IdentifiedRisksView irView = (IdentifiedRisksView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(IdentifiedRisksView.VIEW_ID);
				//Chama a TableViewer da View chamada e adiciona um item a tabela
				irView.tableViewer.setInput(identifiedRisks);
			}
			else{
				MessageDialog.openError(window.getShell(), "Error", "This identified risk is already defined!");
			}
		}
		
		

	}

	
}
