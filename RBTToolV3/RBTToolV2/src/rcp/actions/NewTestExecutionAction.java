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
import rcp.dialogs.ExecuteTestDialog;
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

public class NewTestExecutionAction extends Action implements ISelectionListener {
	
	private StructuredSelection selection;
	private final IWorkbenchWindow window;
	
	public NewTestExecutionAction(IWorkbenchWindow window) {
		this.window = window;
		
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			selection = (StructuredSelection) incoming;
			setEnabled(selection.size() == 1
					&& selection.getFirstElement() instanceof TestCase);
		} else {
			// Other selections, for example containing text or of other kinds.
			setEnabled(false);
		}

	}
	
/*	@Override
	public ImageDescriptor getImageDescriptor() {
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/pencil.gif");
	}
	*/
	
	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "Edit selected requirement";
	}
	
	@Override
	public void run() {
		
		RBTToolFacade rbtToolFacade = RBTToolFacade.getInstance();
		
		//Chama a View
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		TestPlanView myTestPlanView = (TestPlanView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestPlanView.VIEW_ID);
		TestIterationsView myTestIterationsView = (TestIterationsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestIterationsView.VIEW_ID);
		TestCasesView myTestCasesView = (TestCasesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestCasesView.VIEW_ID);
		
		//Chama a ListViewer das Views chamadas
		ListViewer listProject = myProjectView.viewer;
		ListViewer listTestPlan = myTestPlanView.viewer;
		TableViewer tableTestIteration = myTestIterationsView.tableViewer;
		TableViewer tableTestCase = myTestCasesView.tableViewer;
		
		//Pega o projeto selecionado na list
		Project p = (Project) ((IStructuredSelection)listProject.getSelection()).getFirstElement();
		TestPlan tp = (TestPlan) ((IStructuredSelection)listTestPlan.getSelection()).getFirstElement();
		TestIteration ti = (TestIteration) ((IStructuredSelection)tableTestIteration.getSelection()).getFirstElement();
		TestCase tc = (TestCase) ((IStructuredSelection)tableTestCase.getSelection()).getFirstElement();
		
		
		//ExecuteTestDialog d = new ExecuteTestDialog(this.window.getShell(), )
		
	}

}
