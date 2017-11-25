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

public class EditTestCaseAction extends Action implements ISelectionListener {
	
	private StructuredSelection selection;
	private final IWorkbenchWindow window;
	
	public EditTestCaseAction(IWorkbenchWindow window) {
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
	
	@Override
	public ImageDescriptor getImageDescriptor() {
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/tab_pencil.gif");
	}
	
	
	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "Edit selected test case";
	}
	
	@Override
	public void run() {
		
		RBTToolFacade rbtToolFachada = RBTToolFacade.getInstance();
		
		
		//Chama a View de Projetos
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		//Chama a ListViewer da View chamada
		ListViewer projectList = myProjectView.viewer;
		
		//Chama a View de TestPlans
		TestPlanView testPlanView = (TestPlanView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestPlanView.VIEW_ID);
		ListViewer testPlanList = testPlanView.viewer;
		
		//Chama a View de TestIterations
		TestIterationsView tiView = (TestIterationsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestIterationsView.VIEW_ID);
		TableViewer tiTable = tiView.tableViewer;
		
		//Chama a View de TestCases
		TestCasesView TCView = (TestCasesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestCasesView.VIEW_ID);
		TableViewer tcTable = TCView.tableViewer;
		
		Project p = (Project) ((IStructuredSelection)projectList.getSelection()).getFirstElement();
		TestPlan tp = (TestPlan) ((IStructuredSelection) testPlanList.getSelection()).getFirstElement();
		TestIteration ti = (TestIteration) ((IStructuredSelection) tiTable.getSelection()).getFirstElement();
		TestCase sel = (TestCase) ((IStructuredSelection)tcTable.getSelection()).getFirstElement();
		
		Vector<TestCase> testCases = rbtToolFachada.getTestCasesFromIteration(ti, tp, p);
		
		if ((null != sel)) {
			
			TestCase tc = sel;
			TestCaseDialog d = new TestCaseDialog(window.getShell(), p, tp, ti, tc);
			int code = d.open();
			
			if (code == Window.OK){
				
				tc.setDescription(d.getDescription());
				tc.setPreConditions(d.getPreConditions());
				tc.setPosConditions(d.getPosConditions());
				tc.setTestCaseProcedures(d.getTestCaseProcedures());
				
				rbtToolFachada.updateTestCase(tc, tc.getRequirement(), ti, tp, p);
				
				testCases.setElementAt(tc, tcTable.getTable().getSelectionIndex());
				tcTable.refresh();
				
			}
		}
		
	}

}
