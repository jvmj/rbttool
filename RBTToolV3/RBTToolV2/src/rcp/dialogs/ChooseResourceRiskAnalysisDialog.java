package rcp.dialogs;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import rcp.views.ProjectView;
import rcp.windows.RiskAnalysisApplicationWindow;
import essentials.Project;
import essentials.RiskAnalysis;

public class ChooseResourceRiskAnalysisDialog extends Dialog{
	
	private List resourcesList;
	private Vector<String> resources;
	private String resourceName;
	Project p;
	

	public ChooseResourceRiskAnalysisDialog(Shell parent) {
		super(parent);


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

		
		//Chama a View
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		//Chama a ListViewer da View chamada
		ListViewer list = myProjectView.viewer;
		
		//Pega o projeto selecionado na list
		this.p = (Project) ((IStructuredSelection)list.getSelection()).getFirstElement();
		
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
		
		RiskAnalysis riskAnalysis = new RiskAnalysis();
		resourceName = resourcesList.getSelection()[0];
		riskAnalysis.setNameResource(resourceName);
		
		this.close();

		
		//new RiskAnalysisDialog(this.getShell(), p, resourceName).open();
		new RiskAnalysisApplicationWindow(this.getShell(), p, resourceName).open();
		
		super.okPressed();
		
	}

	

}
