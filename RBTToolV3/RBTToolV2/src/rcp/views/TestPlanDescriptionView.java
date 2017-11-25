/*******************************************************************************
 * Copyright (c) 2007 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package rcp.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import essentials.Project;
import essentials.Requirement;
import essentials.TestPlan;

/**
 * 
 * @author Tom Seidel tom.seidel@spiritlink.de
 */
public class TestPlanDescriptionView extends ViewPart implements ISelectionListener {

    private Label headerLabel;
    private FormToolkit toolkit;
    private ScrolledForm form;
    
    public Text descriptionElementText;
    
    public static final String VIEW_ID = "RCP.TestPlanDescriptionView"; //$NON-NLS-1$

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
        
        this.headerLabel = new Label(headerComp, SWT.NONE);
        this.headerLabel.setText("Requirement Details");
        gd = new GridData(SWT.FILL, SWT.FILL, true ,true);
        
        
        this.headerLabel.setLayoutData(gd);
        
        final Composite client = this.toolkit.createComposite(this.form.getBody(), SWT.WRAP);
        layout = new GridLayout(2, false);
        client.setLayout(layout);
        this.toolkit.paintBordersFor(client);
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        client.setLayoutData(gd);
        
        
        this.descriptionElementText = this.toolkit.createText(client, "", SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.horizontalIndent = 20;
        gd.heightHint = 190;
        this.descriptionElementText.setLayoutData(gd);
        this.descriptionElementText.setEditable(false);
        
        
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
        	this.headerLabel.setText("Test plan " + ((TestPlan) ((IStructuredSelection) selection).getFirstElement()).getName() + " description");
        	this.descriptionElementText.setText(((TestPlan) ((IStructuredSelection) selection).getFirstElement()).getDescription());
        } else {
        	this.headerLabel.setText("");
        }
        
    }

}
