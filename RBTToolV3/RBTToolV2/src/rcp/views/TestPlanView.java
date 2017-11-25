package rcp.views;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import rcp.actions.NewTestPlanAction;
import rcp.providers.TestPlanContentProvider;
import rcp.providers.TestPlanLabelProvider;
import essentials.Project;


public class TestPlanView extends ViewPart implements ISelectionListener {
	public static final String VIEW_ID = "RCP.TestPlansView"; //$NON-NLS-1$

    
    public ListViewer viewer;
   
    private FormToolkit toolkit;

    private ScrolledForm form;
    
    public Label headerLabel;
    

	public TestPlanView(){
		
	}
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
    public void createPartControl(final Composite parent) {
		 final Composite comp = new Composite(parent, SWT.NONE);
	        GridLayout layout = new GridLayout(1, false);
	        layout.horizontalSpacing = 0;
	        layout.verticalSpacing = 0;
	        layout.marginHeight = 0;
	        layout.marginWidth = 0;
	        comp.setLayout(layout);

	        this.toolkit = new FormToolkit(comp.getDisplay());
	        this.form = this.toolkit.createScrolledForm(comp);
	        //layout = new GridLayout(1, false);
	        this.form.getBody().setLayout(layout);
	        this.form.setText("Test Plans");
	        //this.form.setImage(this.image.createImage());
	        this.toolkit.decorateFormHeading(this.form.getForm());
	        GridData gd = new GridData(SWT.FILL, SWT.FILL, true ,true);
	        this.form.setLayoutData(gd);

	        ToolBarManager toolBarManager = (ToolBarManager) this.form.getToolBarManager();
	        toolBarManager.add(new NewTestPlanAction(getSite().getWorkbenchWindow()));
	        //toolBarManager.add(new OpenWindowQuestionManagement(getSite().getWorkbenchWindow()));
	        //toolBarManager.add(new ChooseResourceAnwerQuestionnaireAction(getSite().getWorkbenchWindow()));
	        this.form.getToolBarManager().update(true);
	 
	        
	        final Composite headerComp = new Composite(this.form.getBody(), SWT.NONE);
	        layout = new GridLayout(1,false);

	        headerComp.setLayout(layout);
	        gd = new GridData(SWT.FILL, SWT.BEGINNING, true ,false);
	        headerComp.setLayoutData(gd);

	        this.headerLabel = new Label(headerComp, SWT.NONE);
	        this.headerLabel.setText("View Children");
	        gd = new GridData(SWT.FILL, SWT.FILL, true ,true);


	        this.headerLabel.setLayoutData(gd);

	        final Composite client = this.toolkit.createComposite(this.form.getBody(), SWT.WRAP);
	        layout = new GridLayout(1, false);
	        client.setLayout(layout);
	        this.toolkit.paintBordersFor(client);
	        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
	        client.setLayoutData(gd);
	        
	        final List list = new List(client, SWT.SINGLE |SWT.H_SCROLL | SWT.V_SCROLL);
	        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
	        list.setLayoutData(gd);
	        this.viewer = new ListViewer(list);
	        
	        this.viewer.setUseHashlookup(true);
	        this.viewer.setContentProvider(new TestPlanContentProvider());
	        this.viewer.setLabelProvider(new TestPlanLabelProvider());
	        
	        this.form.reflow(true);
	        getViewSite().setSelectionProvider(this.viewer);
	        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(this);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
    public void setFocus() {
		this.viewer.getControl().setFocus();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		 if (part != this) {
	            if (!selection.isEmpty()) {
	            	this.headerLabel.setText("Test Plans of " + ((Project) ((IStructuredSelection) selection).getFirstElement()).getName());
	            	this.viewer.setInput(((Project) ((IStructuredSelection) selection).getFirstElement()).getTestPlans());
	            }
		 }
	}
     
     
}