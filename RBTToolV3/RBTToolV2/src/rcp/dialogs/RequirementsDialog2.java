package rcp.dialogs;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import essentials.Project;
import essentials.Requirement;
import facade.RBTToolFacade;

import rcp.providers.RequirementsContentProvider;
import rcp.providers.RequirementsLabelProvider;

import util.RequirementViewerSorter;
import util.RequirementsTableConst;

public class RequirementsDialog2 extends Dialog {

	public TableViewer tableViewer;
	String projectName;
	
	public RequirementsDialog2(Shell parent, String projectName) {
		super(parent);
		this.projectName = projectName;
	}
	
	@Override
	
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Requirements list");
		//newShell.setSize(600, 400);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		GridLayout layout = new GridLayout(1, true);
		composite.setLayout(layout);
		
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true ,true);
		gd.widthHint = 550;
		
		final Table resultTable = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
        resultTable.setLinesVisible(true);
        resultTable.setHeaderVisible(true);
        resultTable.setLayoutData(gd);
        

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(10,50));
        tableLayout.addColumnData(new ColumnWeightData(50,100));
        tableLayout.addColumnData(new ColumnWeightData(30,100));
        
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
        tc1.setText("Name");
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
        tc2.setText("Risk Exposure");
        tc2.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
              ((RequirementViewerSorter) tableViewer.getSorter())
                  .doSort(RequirementsTableConst.COLUMN_RE);
              tableViewer.refresh();
            }
          });
        
        Project p = RBTToolFacade.getInstance().searchProject(projectName);
        Vector<Requirement> requirements = p.getRequirements();
        
        
        
        this.tableViewer = new TableViewer(resultTable);
        this.tableViewer.setUseHashlookup(true);
        this.tableViewer.setLabelProvider(new RequirementsLabelProvider());
        this.tableViewer.setContentProvider(new RequirementsContentProvider());
        this.tableViewer.setSorter(new RequirementViewerSorter());
        
        this.tableViewer.setInput(requirements);
        
		
		return composite;
	}

}
