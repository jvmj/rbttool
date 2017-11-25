package rcp.dialogs;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import rcp.providers.TestCaseProceduresContentProvider;
import rcp.providers.TestCaseProceduresLabelProvider;
import util.SWTUtil;
import essentials.Project;
import essentials.TestCase;
import essentials.TestCaseProcedure;
import essentials.TestIteration;
import essentials.TestPlan;

public class TestCaseDialog extends Dialog {
	
	private Text riskNameText;
	private Text requirementText;
	private Text descriptionText;
	private Text preConditionsText;
	private Text posConditionsText;
	
	private String description;
	private String preConditions;
	private String posConditions;
	
	private TableViewer testCaseProceduresTableViewer;
	
	private Button addButton;
	private Button removeButton;

	Project project;
	TestPlan testPlan;
	TestIteration testIteration;
	TestCase testCase;
	
	private Vector<TestCaseProcedure> testCaseProcedures = new Vector<TestCaseProcedure>();
	
	
	public TestCaseDialog(Shell parentShell, Project p, TestPlan tp, TestIteration ti, TestCase tc) {
		super(parentShell);
		this.project = p;
		this.testPlan = tp;
		this.testIteration = ti;
		this.testCase = tc;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Test Case details");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite mainArea = new Composite(parent, SWT.NONE);
		GridLayout controlLayout = new GridLayout(1, false);
		mainArea.setLayout(controlLayout);
		createTestCaseArea(mainArea);
		Label label = new Label(mainArea, SWT.CENTER);
		label.setText("Procedures: (in order of execution)");
		label.setFont(new Font(mainArea.getDisplay(), new FontData("Arial",10, SWT.BOLD)));
		createTestCaseProcedureArea(mainArea);
		
		return mainArea;
	}
	
	private Composite createTestCaseArea(Composite mainArea) {
		Composite control = new Composite(mainArea, SWT.NONE);
        GridLayout controlLayout = new GridLayout(2, false);

        controlLayout.marginHeight = 0;
        controlLayout.marginWidth = 0;

        control.setLayout(controlLayout);
        control.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

        
        GridData gd = new GridData(GridData.FILL, GridData.FILL,true, false);
        
        GridData gdText = new GridData();
		gdText.widthHint = 200;
		gdText.heightHint = 50;

		Label labelReq = new Label(control, SWT.NONE);
		labelReq.setText("Requirement leashed: ");
		labelReq.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER,
				false, false));
		this.requirementText = new Text(control, SWT.BORDER);
		this.requirementText.setText(this.testCase.getRequirement().getName());
		this.requirementText.setEnabled(false);
		this.requirementText.setLayoutData(gd);
		
		Label labelRisk = new Label(control, SWT.NONE);
		labelRisk.setText("Risk attribute: ");
		labelReq.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER,
				false, false));
		this.riskNameText = new Text(control, SWT.BORDER);
		this.riskNameText.setText(this.testCase.getRisk().getName());
		this.riskNameText.setEnabled(false);
		this.riskNameText.setLayoutData(gd);
        
        Label labelDescription = new Label(control, SWT.NONE);
        labelDescription.setText("Description: ");
        labelDescription.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER,
				false, false));
        this.descriptionText = new Text(control, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        this.descriptionText.setText(this.testCase.getDescription());
        this.descriptionText.setLayoutData(gdText);

        
        Label labelPreCondition = new Label(control, SWT.NONE);
        labelPreCondition.setText("Preconditions: ");
        labelPreCondition.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER,
				false, false));
        this.preConditionsText = new Text(control, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        this.preConditionsText.setText(this.testCase.getPreConditions());
        this.preConditionsText.setLayoutData(gdText);
        
        
        Label labelPosCondition = new Label(control, SWT.NONE);
        labelPosCondition.setText("Posconditions: ");
        labelPosCondition.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER,
				false, false));
        this.posConditionsText = new Text(control,  SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        this.posConditionsText.setText(this.testCase.getPosConditions());
        this.posConditionsText.setLayoutData(gdText);
        
        
        
        return control;
		
	}
	
	private Composite createTestCaseProcedureArea(Composite parent) {
		
		Composite control = new Composite(parent, SWT.NONE);
        GridLayout controlLayout = new GridLayout(2, false);

        controlLayout.marginHeight = 0;
        controlLayout.marginWidth = 0;

        control.setLayout(controlLayout);
        control.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

        Composite tablePane = new Composite(control, SWT.NONE);
        GridLayout tablePaneLayout = new GridLayout();
        GridData gridData = new GridData(GridData.FILL_BOTH);

        tablePaneLayout.marginHeight = 0;
        tablePaneLayout.marginWidth = 0;

        tablePane.setLayout(tablePaneLayout);
        tablePane.setLayoutData(gridData);

        Table table = new Table(tablePane, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER
                | SWT.FULL_SELECTION);

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
        
         // Create the button pane
        Composite buttonPane = new Composite(control, SWT.NONE);
        GridLayout buttonPaneLayout = new GridLayout();

        buttonPaneLayout.marginHeight = 0;
        buttonPaneLayout.marginWidth = 0;

        buttonPane.setLayout(buttonPaneLayout);
        buttonPane.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        // New button
        addButton = new Button(buttonPane, SWT.PUSH);
        addButton.setText("Add");

        gridData = new GridData(GridData.FILL_HORIZONTAL);
        addButton.setLayoutData(gridData);

        addButton.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event e) {
                handleAdd();
            }
        });

        // Remove button
        removeButton = new Button(buttonPane, SWT.PUSH);
        removeButton.setText("Remove");

        gridData = new GridData(GridData.FILL_HORIZONTAL);
        removeButton.setLayoutData(gridData);

        removeButton.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event e) {
                handleRemove();
            }
        });


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

        //handleSelectionChanged();
		return control;
	}

	
	 protected void handleAdd() {
		 
		 TestCaseProcedure testCaseProcedure = null;
		 
		 NewTestCaseProcedureDialog dlg = new NewTestCaseProcedureDialog(Display.getCurrent().getActiveShell(), testCase.getId());
		 
		 if(Window.OK == dlg.open()){
			 
			 testCaseProcedure = createTestCaseProcedure(dlg.getDescription(), dlg.getExpectedResult());
			 testCaseProcedure.setId(Math.random() + "");
			 
			 if(testCaseProcedure != null){
				 testCaseProcedures.add(testCaseProcedure);
				 testCaseProceduresTableViewer.setInput(testCaseProcedures);
			 }
		 }
	 }
	
	private TestCaseProcedure createTestCaseProcedure(String description, String expectedResults){
		
		TestCaseProcedure tcp = new TestCaseProcedure();
		tcp.setDescription(description);
		tcp.setExpectedResult(expectedResults);
		
		return tcp;
	}
	
    protected void handleRemove() {
        IStructuredSelection sel = getSelection();
        if ((null != sel) && (!sel.isEmpty())) {
            for (Iterator<?> iter = sel.iterator(); iter.hasNext();) {
                TestCaseProcedure assoc = (TestCaseProcedure) iter.next();
                testCaseProceduresTableViewer.remove(assoc);
                testCaseProcedures.remove(assoc);
            }
        }
    }
    
    

	@Override
	protected void okPressed() {
		
		description = descriptionText.getText();
		preConditions = preConditionsText.getText();
		posConditions = posConditionsText.getText();
		
		super.okPressed();
	}
	
	public String getDescription() {
		return description;
	}

	public String getPreConditions() {
		return preConditions;
	}

	public String getPosConditions() {
		return posConditions;
	}

	public Vector<TestCaseProcedure> getTestCaseProcedures() {
		return testCaseProcedures;
	}

	/**
     * Returns the table viewer selection.
     * @return the table viewer selection.
     */
    private IStructuredSelection getSelection() {
        return (IStructuredSelection) testCaseProceduresTableViewer.getSelection();
    }

	
}
