/*******************************************************************************
 * Copyright (c) 2007 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package rcp.views;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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

import rcp.actions.DeleteIdentifiedRiskAction;
import rcp.actions.EditIdentifiedRiskAction;
import rcp.actions.NewIdentifiedRiskAction;
import rcp.providers.IdentifiedRisksContentProvider;
import rcp.providers.IdentifiedRisksLabelProvider;
import util.IdentifiedRisksTableConst;
import util.IdentifiedRisksViewerSorter;
import essentials.Requirement;

/**
 * 
 * @author Tom Seidel tom.seidel@spiritlink.de
 */
public class IdentifiedRisksView extends ViewPart implements ISelectionListener {

    private FormToolkit toolkit;
    private ScrolledForm form;
    public Label headerLabel;
    public TableViewer tableViewer;
    public static final String VIEW_ID = "RCP.IdentifiedRisksView"; //$NON-NLS-1$

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
        this.form.setText("Identified Risks");
        //this.form.setImage(this.image.createImage());
        this.toolkit.decorateFormHeading(this.form.getForm());
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true ,true);
        this.form.setLayoutData(gd);

        ToolBarManager toolBarManager = (ToolBarManager) this.form.getToolBarManager();
        toolBarManager.add(new NewIdentifiedRiskAction(getSite().getWorkbenchWindow()));
        toolBarManager.add(new EditIdentifiedRiskAction(getSite().getWorkbenchWindow()));
        toolBarManager.add(new DeleteIdentifiedRiskAction(getSite().getWorkbenchWindow()));
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


        final Table resultTable = new Table(client, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
        resultTable.setLinesVisible(true);
        resultTable.setHeaderVisible(true);
        resultTable.setLayoutData(gd);
        

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(20,100));
        tableLayout.addColumnData(new ColumnWeightData(30,100));
        tableLayout.addColumnData(new ColumnWeightData(20,100));
        tableLayout.addColumnData(new ColumnWeightData(30,100));


        resultTable.setLayout(tableLayout);

        final TableColumn tc0 = new TableColumn(resultTable, SWT.NONE);
        tc0.setMoveable(true);
        tc0.setResizable(true);
        tc0.setText("Risk attribute");
        
        tc0.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              ((IdentifiedRisksViewerSorter) tableViewer.getSorter())
                  .doSort(IdentifiedRisksTableConst.COLUMN_RISK);
              tableViewer.refresh();
            }
          });


        final TableColumn tc1 = new TableColumn(resultTable, SWT.NONE);
        tc1.setMoveable(true);
        tc1.setResizable(true);
        tc1.setText("Cause of the risk");
        tc1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              ((IdentifiedRisksViewerSorter) tableViewer.getSorter())
                  .doSort(IdentifiedRisksTableConst.COLUMN_CAUSE);
              tableViewer.refresh();
            }
          });



        final TableColumn tc2 = new TableColumn(resultTable, SWT.NONE);
        tc2.setMoveable(true);
        tc2.setResizable(true);
        tc2.setText("Resource");
        tc2.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              ((IdentifiedRisksViewerSorter) tableViewer.getSorter())
                  .doSort(IdentifiedRisksTableConst.COLUMN_RESOURCE);
              tableViewer.refresh();
            }
          });

        final TableColumn tc3 = new TableColumn(resultTable, SWT.NONE);
        tc3.setMoveable(true);
        tc3.setResizable(true);
        tc3.setText("Question");
        tc3.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              ((IdentifiedRisksViewerSorter) tableViewer.getSorter())
                  .doSort(IdentifiedRisksTableConst.COLUMN_QUESTION);
              tableViewer.refresh();
            }
          });
        
        resultTable.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.DEL){
					if(tableViewer.getSelection() != null){
						
						new DeleteIdentifiedRiskAction(getSite().getWorkbenchWindow()).run();
					}
				}
			}
        	
        	
        });
        
        resultTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				new EditIdentifiedRiskAction(getSite().getWorkbenchWindow()).run();
				tableViewer.setSelection(null);
				
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
        
        this.tableViewer = new TableViewer(resultTable);
        this.tableViewer.setUseHashlookup(true);
        this.tableViewer.setLabelProvider(new IdentifiedRisksLabelProvider());
        this.tableViewer.setContentProvider(new IdentifiedRisksContentProvider());
       this.tableViewer.setSorter(new IdentifiedRisksViewerSorter());
        

        this.form.reflow(true);
        //getViewSite().setSelectionProvider(this.tableViewer);
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
                this.headerLabel.setText("Identified risks of the feature " + ((Requirement) ((IStructuredSelection) selection).getFirstElement()).getName());
                this.tableViewer.setInput(((Requirement) ((IStructuredSelection) selection).getFirstElement()).getIdentifiedRisks());
            } else {
                this.tableViewer.setInput(null);
                this.headerLabel.setText("Noting selected");
            }
        }

    }

}
