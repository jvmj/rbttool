package rcp.views;

import java.util.Vector;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import essentials.Project;

public class ProjectDetailsView extends ViewPart implements ISelectionListener {

    private Label headerLabel;
    private FormToolkit toolkit;
    public ScrolledForm form;
    private Label nameElementLabel;
    private Label descriptionElementLabel;
    private Label startDateElementLabel;
    private Label dueDateElementLabel;
    private Label requirementBaselineElementLabel;
    private Label resourcesElementLabel;
    
    public static final String VIEW_ID = "RCP.ProjectDetailsView"; //$NON-NLS-1$

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
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
        this.form.getBody().setLayout(layout);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true ,true);
        this.form.setLayoutData(gd);
        
        final Composite headerComp = new Composite(this.form.getBody(), SWT.NONE);
        layout = new GridLayout(1,false);
        
        headerComp.setLayout(layout);
        gd = new GridData(SWT.FILL, SWT.BEGINNING, true ,false);
        headerComp.setLayoutData(gd);
        
        //HEADER
        this.headerLabel = new Label(headerComp, SWT.NONE);
        this.headerLabel.setText("Project Details");
        gd = new GridData(SWT.FILL, SWT.FILL, true ,true);
        
        
        this.headerLabel.setLayoutData(gd);
        
        final Composite client = this.toolkit.createComposite(this.form.getBody(), SWT.WRAP);
        layout = new GridLayout(2, false);
        client.setLayout(layout);
        this.toolkit.paintBordersFor(client);
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        client.setLayoutData(gd);
        
        //NAME
        final Label nameLabel = this.toolkit.createLabel(client, "Name");
        gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        gd.horizontalIndent = 20;
        gd.widthHint = 60;
        nameLabel.setLayoutData(gd);
        
        this.nameElementLabel = this.toolkit.createLabel(client, "");
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.horizontalIndent = 20;
        this.nameElementLabel.setLayoutData(gd);
        
        //DESCRIPTION
        final Label descirptionLabel = this.toolkit.createLabel(client, "Description");
        gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        gd.horizontalIndent = 20;
        gd.widthHint = 60;
        descirptionLabel.setLayoutData(gd);
        
        this.descriptionElementLabel = this.toolkit.createLabel(client, "");
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.horizontalIndent = 20;
        this.descriptionElementLabel.setLayoutData(gd);
        
        //START DATE
        final Label startDateLabel = this.toolkit.createLabel(client, "Start Date");
        gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        gd.horizontalIndent = 20;
        gd.widthHint = 60;
        startDateLabel.setLayoutData(gd);
        
        this.startDateElementLabel = this.toolkit.createLabel(client, "");
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.horizontalIndent = 20;
        this.startDateElementLabel.setLayoutData(gd);
        
        //DUE DATE
        final Label dueDateLabel = this.toolkit.createLabel(client, "Due Date");
        gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        gd.horizontalIndent = 20;
        gd.widthHint = 60;
        dueDateLabel.setLayoutData(gd);
        
        this.dueDateElementLabel = this.toolkit.createLabel(client, "");
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.horizontalIndent = 20;
        this.dueDateElementLabel.setLayoutData(gd);
        
        //REQUIREMENT BASELINE LABEL
        final Label baselineLabel = this.toolkit.createLabel(client, "Baseline");
        gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        gd.horizontalIndent = 20;
        gd.widthHint = 60;
        baselineLabel.setLayoutData(gd);
        
        this.requirementBaselineElementLabel = this.toolkit.createLabel(client, "");
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.horizontalIndent = 20;
        this.requirementBaselineElementLabel.setLayoutData(gd);
        
        //RESOURCES LABEL
        final Label resourcesLabel = this.toolkit.createLabel(client, "Resources");
        gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        gd.horizontalIndent = 20;
        gd.widthHint = 60;
        resourcesLabel.setLayoutData(gd);
        
        this.resourcesElementLabel = this.toolkit.createLabel(client, "");
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.horizontalIndent = 20;
        this.resourcesElementLabel.setLayoutData(gd);
        
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(this);
       
        this.form.reflow(true);

    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {
        //

    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        if (!selection.isEmpty()) {
            this.nameElementLabel.setText(((Project) ((IStructuredSelection) selection).getFirstElement()).getName());
            this.descriptionElementLabel.setText(((Project) ((IStructuredSelection) selection).getFirstElement()).getMainDescription());
            this.startDateElementLabel.setText(((Project) ((IStructuredSelection) selection).getFirstElement()).getStartDate());
            this.dueDateElementLabel.setText(((Project) ((IStructuredSelection) selection).getFirstElement()).getDueDate());
            this.requirementBaselineElementLabel.setText(((Project) ((IStructuredSelection) selection).getFirstElement()).getRequirementBaseline());
            Vector<String> resources = ((Project) ((IStructuredSelection) selection).getFirstElement()).getResources();
            this.resourcesElementLabel.setText(resources + "");

        } else {
            this.nameElementLabel.setText(""); //$NON-NLS-1$
            this.descriptionElementLabel.setText(""); //$NON-NLS-1$
            this.startDateElementLabel.setText(""); //$NON-NLS-1$
        }
        
    }

}
