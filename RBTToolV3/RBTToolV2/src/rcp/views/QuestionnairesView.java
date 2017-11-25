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

import rcp.actions.ChooseResourceAnwerQuestionnaireAction;
import rcp.actions.NewQuestionnaireAction;
import rcp.actions.OpenWindowQuestionManagement;
import rcp.providers.QuestionnaireContentProvider;
import rcp.providers.QuestionnairesLabelProvider;
import essentials.Project;

public class QuestionnairesView extends ViewPart implements ISelectionListener{

	public static final String VIEW_ID = "RCP.QuestionnairesView";
	   
	public ListViewer viewer;
	
    private FormToolkit toolkit;

    private ScrolledForm form;
    
    public Label headerLabel;
    
	public QuestionnairesView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		
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
	        this.form.setText("Questionnaires");
	        //this.form.setImage(this.image.createImage());
	        this.toolkit.decorateFormHeading(this.form.getForm());
	        GridData gd = new GridData(SWT.FILL, SWT.FILL, true ,true);
	        this.form.setLayoutData(gd);

	        ToolBarManager toolBarManager = (ToolBarManager) this.form.getToolBarManager();
	        toolBarManager.add(new NewQuestionnaireAction(getSite().getWorkbenchWindow()));
	        toolBarManager.add(new OpenWindowQuestionManagement(getSite().getWorkbenchWindow()));
	        toolBarManager.add(new ChooseResourceAnwerQuestionnaireAction(getSite().getWorkbenchWindow()));
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
	        this.viewer.setContentProvider(new QuestionnaireContentProvider());
	        this.viewer.setLabelProvider(new QuestionnairesLabelProvider());
	        
	        this.form.reflow(true);
	        //getViewSite().setSelectionProvider(this.viewer);
	        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(this);


	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (part != this) {
            if (!selection.isEmpty()) {
            	this.headerLabel.setText("Questionnaires of " + ((Project) ((IStructuredSelection) selection).getFirstElement()).getName());
                this.viewer.setInput(((Project) ((IStructuredSelection) selection).getFirstElement()).getQuestionnaires());
            } else {
                //this.viewer.setInput(null);
                //this.headerLabel.setText("Noting selected");
            }
        }
		
	}

}
