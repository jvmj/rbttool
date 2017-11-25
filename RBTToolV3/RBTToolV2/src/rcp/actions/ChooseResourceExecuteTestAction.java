package rcp.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.dialogs.ChooseResourceExecuteTestDialog;
import rcp.views.ProjectView;
import rcp.views.TestCasesView;
import rcp.views.TestIterationsView;
import rcp.views.TestPlanView;
import essentials.Project;
import essentials.TestCase;
import essentials.TestIteration;
import essentials.TestPlan;


public class ChooseResourceExecuteTestAction extends Action implements
		ISelectionListener {
	
	private final IWorkbenchWindow window;
	private StructuredSelection selection;
	
	
	
	public ChooseResourceExecuteTestAction(IWorkbenchWindow window) {
		super();
		this.window = window;
		setText("Execute test");
		
	}


	@Override
	public ImageDescriptor getImageDescriptor() {
		//return ImageDescriptor.createFromImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.));
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/run.gif");
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
	public void run() {
		
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
		
		//soh abre a janela se tiver um test case selecionado
		if(sel != null){
			new ChooseResourceExecuteTestDialog(this.window.getShell(), p, tp, ti, sel).open();
		}
		
	}

}
