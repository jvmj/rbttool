package rcp.views;

/*******************************************************************************
 * Copyright (c) 2007 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import rcp.providers.TestIterationsContentProvider;
import rcp.providers.TestIterationsLabelProvider;
import util.RequirementViewerSorter;
import util.RequirementsTableConst;
import essentials.TestPlan;

/**
 * 
 * @author Tom Seidel tom.seidel@spiritlink.de
 */
public class TestIterationsView extends ViewPart implements ISelectionListener {

    private FormToolkit toolkit;
    private ScrolledForm form;
    public Label headerLabel;
    public TableViewer tableViewer;
    public static final String VIEW_ID = "RCP.TestIterationsView"; //$NON-NLS-1$

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
        //layout = new GridLayout(1, false);
        this.form.getBody().setLayout(layout);
        this.form.setText("Iterations");
        //this.form.setImage(this.image.createImage());
        this.toolkit.decorateFormHeading(this.form.getForm());
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true ,true);
        this.form.setLayoutData(gd);

        //ToolBarManager toolBarManager = (ToolBarManager) this.form.getToolBarManager();
        //toolBarManager.add(new NewRequirementAction(getSite().getWorkbenchWindow()));
        //toolBarManager.add(new SummarizesRiskAnalysies(getSite().getWorkbenchWindow()));
        //this.form.getToolBarManager().update(true);
 
        
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


        final Table resultTable = new Table(client, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
        resultTable.setLinesVisible(true);
        resultTable.setHeaderVisible(true);
        resultTable.setLayoutData(gd);
        

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(20,50));
        tableLayout.addColumnData(new ColumnWeightData(20,100));
        tableLayout.addColumnData(new ColumnWeightData(20,100));
        tableLayout.addColumnData(new ColumnWeightData(20,100));
        tableLayout.addColumnData(new ColumnWeightData(20,100));
        //tableLayout.addColumnData(new ColumnWeightData(20,100));
        //tableLayout.addColumnData(new ColumnWeightData(20,100));


        resultTable.setLayout(tableLayout);

        final TableColumn tc0 = new TableColumn(resultTable, SWT.NONE);
        tc0.setMoveable(true);
        tc0.setResizable(true);
        tc0.setText("Id");
        
        tc0.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              ((RequirementViewerSorter) tableViewer.getSorter())
                  .doSort(RequirementsTableConst.COLUMN_ID);
              tableViewer.refresh();
            }
          });


        final TableColumn tc1 = new TableColumn(resultTable, SWT.NONE);
        tc1.setMoveable(true);
        tc1.setResizable(true);
        tc1.setText("Less RE Value");
        tc1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              ((RequirementViewerSorter) tableViewer.getSorter())
                  .doSort(RequirementsTableConst.COLUMN_NAME);
              tableViewer.refresh();
            }
          });



        final TableColumn tc2 = new TableColumn(resultTable, SWT.NONE);
        tc2.setMoveable(true);
        tc2.setResizable(true);
        tc2.setText("Most RE Value");
        tc2.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              ((RequirementViewerSorter) tableViewer.getSorter())
                  .doSort(RequirementsTableConst.COLUMN_RE);
              tableViewer.refresh();
            }
          });

        final TableColumn tc3 = new TableColumn(resultTable, SWT.NONE);
        tc3.setMoveable(true);
        tc3.setResizable(true);
        tc3.setText("Start date");
        
        final TableColumn tc4 = new TableColumn(resultTable, SWT.NONE);
        tc4.setMoveable(true);
        tc4.setResizable(true);
        tc4.setText("Due date");

        this.tableViewer = new TableViewer(resultTable);
        this.tableViewer.setUseHashlookup(true);
        this.tableViewer.setLabelProvider(new TestIterationsLabelProvider());
        this.tableViewer.setContentProvider(new TestIterationsContentProvider());
        //this.tableViewer.setSorter(new RequirementViewerSorter());
        

        this.form.reflow(true);
        getViewSite().setSelectionProvider(this.tableViewer);
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(this);
        
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {
        // TODO Auto-generated method stub

    }


    /* (non-Javadoc)
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        if (part != this) {
            if (!selection.isEmpty()) {
                this.headerLabel.setText("Test iterations of " + ((TestPlan) ((IStructuredSelection) selection).getFirstElement()).getName());
                this.tableViewer.setInput(((TestPlan) ((IStructuredSelection) selection).getFirstElement()).gettestIterations());
            } else {
                this.tableViewer.setInput(null);
                this.headerLabel.setText("Noting selected");
            }
        }

    }

}
