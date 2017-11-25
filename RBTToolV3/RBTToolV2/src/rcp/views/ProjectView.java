package rcp.views;

import java.util.Vector;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.progress.ProgressRegion;
import org.eclipse.ui.part.ViewPart;

import rcp.actions.ChooseResourceRiskAnalysisAction;
import rcp.actions.DeleteProjectAction;
import rcp.actions.EditProjectAction;
import rcp.actions.NewProjectAction;
import rcp.dialogs.EditProjectDialog;
import rcp.providers.ProjectContentProvider;
import rcp.providers.ProjectLabelProvider;
import facade.RBTToolFacade;


public class ProjectView extends ViewPart {
	public static final String VIEW_ID = "RCP.ProjectView"; //$NON-NLS-1$

    
    public ListViewer viewer;
   
    private FormToolkit toolkit;

    private ScrolledForm form;

    private ProgressRegion progressRegion;

	
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
        layout = new GridLayout(1, false);
        this.form.getBody().setLayout(layout);

        this.form.setText("Projects"); //$NON-NLS-1$
        this.toolkit.decorateFormHeading(this.form.getForm());
        
        ToolBarManager toolBarManager = (ToolBarManager) this.form.getToolBarManager();
        toolBarManager.add(new NewProjectAction(getSite().getWorkbenchWindow()));
        toolBarManager.add(new DeleteProjectAction(getSite().getWorkbenchWindow()));
        toolBarManager.add(new ChooseResourceRiskAnalysisAction(getSite().getWorkbenchWindow()));
        

        
        this.form.getToolBarManager().update(true);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true ,true);

        final Composite client = this.toolkit.createComposite(this.form.getBody(), SWT.WRAP);
        layout = new GridLayout(1, false);
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 0;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        client.setLayout(layout);
        client.setLayoutData(gd);
        this.form.setLayout(layout);

        final List list = new List(client, SWT.SINGLE |SWT.H_SCROLL | SWT.V_SCROLL);
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        list.setLayoutData(gd);
        this.viewer = new ListViewer(list);

        this.viewer.setUseHashlookup(true);
        this.viewer.setContentProvider(new ProjectContentProvider());
        this.viewer.setLabelProvider(new ProjectLabelProvider());
        
        Vector<Object> projects = RBTToolFacade.getInstance().getProjects();
        this.viewer.setInput(projects);
        getViewSite().setSelectionProvider(this.viewer);
        this.form.setLayoutData(gd);
        createProgressIndicator(comp);
        
        list.addMouseListener(new MouseListener(){

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				new EditProjectAction(getSite().getWorkbenchWindow()).run();
				viewer.setSelection(null);
				
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
    public void setFocus() {
		this.viewer.getControl().setFocus();
	}
    
     private void createProgressIndicator(final Composite parent) {

		final Composite comp = new Composite(parent, SWT.NONE);
		comp.setBackground(Display.getDefault().getSystemColor(
				SWT.COLOR_WIDGET_NORMAL_SHADOW));
		final GridData gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		gd.heightHint = 1;
		comp.setLayoutData(gd);

		final WorkbenchWindow window = (WorkbenchWindow) PlatformUI
				.getWorkbench().getActiveWorkbenchWindow();
		this.progressRegion = new ProgressRegion();
		this.progressRegion.createContents(parent, window);
		this.progressRegion.getControl().setVisible(true);
		this.progressRegion.getControl().setLayoutData(
				new GridData(SWT.BEGINNING, SWT.FILL, true, false));

	}
     
     
}