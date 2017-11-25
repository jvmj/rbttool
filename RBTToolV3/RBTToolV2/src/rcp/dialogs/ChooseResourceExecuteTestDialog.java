package rcp.dialogs;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import rcp.views.ProjectView;
import rcp.views.TestCasesView;
import rcp.views.TestIterationsView;
import rcp.views.TestPlanView;
import essentials.Project;
import essentials.TestCase;
import essentials.TestIteration;
import essentials.TestPlan;

public class ChooseResourceExecuteTestDialog extends Dialog{
	
	private List resourcesList;
	private Vector<String> resources;
	private String resourceName;
	Project p;
	TestPlan tp;
	TestIteration ti;
	TestCase tc;
	

	public ChooseResourceExecuteTestDialog(Shell parent, Project p, TestPlan tp, TestIteration ti, TestCase tc) {
		super(parent);
		this.p = p;
		this.tp = tp;
		this.ti = ti;
		this.tc = tc;


		// TODO Auto-generated constructor stub
	}
	
	
	



	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Who are you?");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		
		
		final Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);

		
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
		
		this.p = (Project) ((IStructuredSelection)projectList.getSelection()).getFirstElement();
		this.tp = (TestPlan) ((IStructuredSelection) testPlanList.getSelection()).getFirstElement();
		this.ti = (TestIteration) ((IStructuredSelection) tiTable.getSelection()).getFirstElement();
		this.tc = (TestCase) ((IStructuredSelection)tcTable.getSelection()).getFirstElement();
		
		resources = p.getResources();
		
		resourcesList = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		if(resources == null){
			resources = new Vector<String>();
		}
		
		for (int i = 0; i < resources.size(); i++) {
			String r = (String)resources.elementAt(i);
			
			resourcesList.add(r);
		}
		
		GridData gd = new GridData();
		gd.widthHint = 200;
		resourcesList.setLayoutData(gd);
		
		return composite;
	}

	@Override
	protected void okPressed() {
		
		resourceName = resourcesList.getSelection()[0];
		
		this.close();
		new ExecuteTestDialog(this.getShell(), p, resourceName, tp, ti, tc).open();
		super.okPressed();
		
	}

	

}
