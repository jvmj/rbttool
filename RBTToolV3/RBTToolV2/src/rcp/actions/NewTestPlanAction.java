package rcp.actions;

import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.views.ProjectView;
import rcp.wizards.NewTestPlanWizard;
import util.SWTUtil;
import essentials.Project;
import essentials.TestPlan;

public class NewTestPlanAction extends Action implements ISelectionListener {
	
	private final IWorkbenchWindow window;
	private StructuredSelection selection;

	private Vector<TestPlan> testPlans = new Vector<TestPlan>();
	
	public NewTestPlanAction(IWorkbenchWindow window) {
		super();
		this.window = window;
		setText("&New Test Plan");
	}
	
	@Override
	public ImageDescriptor getImageDescriptor() {
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/new_projectToolbar.gif");
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			selection = (StructuredSelection) incoming;
			setEnabled(selection.size() == 1
					&& selection.getFirstElement() instanceof TestPlan);
		} else {
			// Other selections, for example containing text or of other kinds.
			setEnabled(false);
		}
		
	}
	
	@Override
	public void run() {
//		//Chama a View
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
//		//Chama a ListViewer da View chamada
		ListViewer list = myProjectView.viewer;
//		
//		//Pega o projeto selecionado na list
		Project p = (Project) ((IStructuredSelection)list.getSelection()).getFirstElement();
		SWTUtil.runWizard(new NewTestPlanWizard(p.getName()));
//		//WizardDialog newTestPlanWizard = new WizardDialog(window.getShell(), new NewTestPlanWizard(p.getName()));
//
//		testPlans = p.getTestPlans();
//		
//		if(testPlans == null){
//			testPlans = new Vector<TestPlan>();
//		}
//		
//		//int code = newTestPlanWizard.open();
//		
//		//WizardDialog dialog = new WizardDialog(window.getShell(), new NewTestPlanWizard(p.getName()));
//		NewTestPlanDialog dialog = new NewTestPlanDialog(window.getShell());
//		
//		
//		
//		//Abre o dialog
//		int code = dialog.open();
//		
//		if(code == Window.OK){
//			
//			TestPlan testPlan = new TestPlan();
//			testPlan.setName(dialog.getTestPlanName());
//			testPlan.setDescription(dialog.getMainDescription());
//			testPlan.setNoIterations(dialog.getNoIterations());
//			
//			RBTToolFacade.getInstance().addTestPlan(testPlan, p);
//			
//			testPlans.add(testPlan);
//			p.setTestPlans(testPlans);
//			//Chama a View
//			TestPlanView tpView = (TestPlanView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestPlanView.VIEW_ID);
//			//Chama a TableViewer da View chamada e adiciona um item a tabela
//			tpView.viewer.setInput(testPlans);
//			
//			
//		}
		

	}

}
