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
import essentials.IdentifiedRisk;
import essentials.Project;
import essentials.Requirement;
import facade.RBTToolFacade;

public class ImportIdentifiedRisksAction extends Action implements ISelectionListener{
	
	public final static String ID = "actions.ImportIdentifiedRisksAction";
	private final IWorkbenchWindow window;
	private StructuredSelection selection;
	

	public ImportIdentifiedRisksAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Identified risks from a resource");
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
			Vector<Object> importedIdentifiedRisks = rbtToolFacade.getImportedIdentifiedRisks(file);

			
			for (int i = 0; i < importedIdentifiedRisks.size(); i++) {
				
				IdentifiedRisk ir = (IdentifiedRisk) importedIdentifiedRisks.elementAt(i);
				Requirement r = rbtToolFacade.searchRequirement(ir.getRequirementID(), ir.getProjectName());
				Vector<IdentifiedRisk> identifiedRisks = r.getIdentifiedRisks();
				
				if(identifiedRisks == null){
					identifiedRisks = new Vector<IdentifiedRisk>();
				}
				
				identifiedRisks.add(ir);
				
				Project p = rbtToolFacade.searchProject(ir.getProjectName());
				
				rbtToolFacade.addIdentifiedRisk(ir, r, p);
				
				Vector<Requirement> requirements = p.getRequirements();
				
				for (int j = 0; j < requirements.size(); j++) {
					
					Requirement r1 = requirements.elementAt(j);
					if(r1.getIdentifier().equals(r.getIdentifier())){
						Vector<IdentifiedRisk> ir1 = r1.getIdentifiedRisks();
						if(ir1 == null){
							ir1 = new Vector<IdentifiedRisk>();
							
						}
						ir1.add(ir);
						r1.setIdentifiedRisks(ir1);
						requirements.setElementAt(r1, j);
					}
				}
				p.setRequirements(requirements);
				ProjectView pView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
				pView.viewer.setInput(rbtToolFacade.getProjects());			
				pView.setFocus();
				RequirementsView rView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
				rView.tableViewer.setInput(requirements);
				
				
				
				
			}
			//rbtToolFacade.importIdentifiedRisk(file);
		}
		


	}
}
