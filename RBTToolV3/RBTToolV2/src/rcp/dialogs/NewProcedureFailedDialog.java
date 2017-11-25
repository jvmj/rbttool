package rcp.dialogs;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import rcp.providers.TestCaseProceduresContentProvider;
import rcp.providers.TestCaseProceduresLabelProvider;
import util.SWTUtil;
import essentials.ProcedureFailed;
import essentials.TestCase;
import essentials.TestCaseProcedure;

public class NewProcedureFailedDialog extends Dialog {
	
	private Text textFailureDescription;
	
	private TestCase testCase;
	
	private String failureDescription;
	
	private Vector<TestCaseProcedure> testCaseProcedures = new Vector<TestCaseProcedure>();
	
	private TableViewer testCaseProceduresTableViewer;
	
	private TestCaseProcedure selectedTestCaseProcedure;
	
	
	public NewProcedureFailedDialog(Shell parentShell, TestCase tc) {
		super(parentShell);
		this.testCase = tc;
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Failed procedure details"); 
		SWTUtil.centerDialog(newShell);
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		getOkayButton().setEnabled(true);
	}

	protected Control createDialogArea(Composite parent) {
		
		Composite control = new Composite(parent, SWT.NONE);
        GridLayout controlLayout = new GridLayout(1, false);
        
        control.setLayout(controlLayout);
        control.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

        Label label1 = new Label(control, SWT.NONE);
        label1.setText("Please select a procedure and describe the problem occurred.");
        
        Composite tablePane = new Composite(control, SWT.NONE);
        GridLayout tablePaneLayout = new GridLayout();
        GridData gridData = new GridData(GridData.FILL_BOTH);

        tablePaneLayout.marginHeight = 0;
        tablePaneLayout.marginWidth = 0;

        tablePane.setLayout(tablePaneLayout);
        tablePane.setLayoutData(gridData);

        Table table = new Table(tablePane, SWT.V_SCROLL | SWT.BORDER| SWT.FULL_SELECTION);

        TableLayout tblLayout = new TableLayout();
        TableColumn col = null;

        gridData = new GridData(GridData.FILL_BOTH);
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.heightHint = SWTUtil.getTableHeightHint(table, 10);
        gridData.widthHint = SWTUtil.convertWidthInCharsToPixels(parent, 80);

        tblLayout.addColumnData(new ColumnWeightData(50));
        tblLayout.addColumnData(new ColumnWeightData(50));
        

        table.setLayout(tblLayout);
        table.setLayoutData(gridData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Description");
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Expected results");
        
        Label labelDescription = new Label(control, SWT.NONE);
        labelDescription.setText("Description: ");
        
        if(testCase.getTestCaseProcedures() != null){
        	testCaseProcedures = testCase.getTestCaseProcedures();
        }
        
     // Hook up the viewer
        testCaseProceduresTableViewer = new TableViewer(table);
        testCaseProceduresTableViewer.setContentProvider(new TestCaseProceduresContentProvider());
        testCaseProceduresTableViewer.setLabelProvider(new TestCaseProceduresLabelProvider());
        testCaseProceduresTableViewer.setInput(testCaseProcedures);
        testCaseProceduresTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                //handleSelectionChanged();
            }
        });
        
        GridData gridDataNotes = new GridData(GridData.FILL, GridData.FILL, true, false);
        
        this.textFailureDescription = new Text(control, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        gridDataNotes.widthHint = convertHeightInCharsToPixels(20);
		gridDataNotes.heightHint = 100;
		this.textFailureDescription.setLayoutData(gridDataNotes);

		return control;
	}


	Button getOkayButton() {
		return getButton(IDialogConstants.OK_ID);
	}

	
	protected void okPressed() {
		
		ProcedureFailed procedureFailed = new ProcedureFailed();
		
		this.failureDescription = textFailureDescription.getText().trim(); 
		this.selectedTestCaseProcedure = (TestCaseProcedure)((IStructuredSelection) this.testCaseProceduresTableViewer.getSelection()).getFirstElement();
		
		procedureFailed.setBugDescription(this.failureDescription);
		procedureFailed.setProcedure(this.selectedTestCaseProcedure);
		
		super.okPressed();
		
	}

	public String getFailureDescription() {
		return failureDescription;
	}

	public TestCaseProcedure getSelectedTestCaseProcedure() {
		return selectedTestCaseProcedure;
	}

	
	
}
