package rcp.dialogs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import rcp.providers.ProceduresFailedContentProvider;
import rcp.providers.ProceduresFailedLabelProvider;
import rcp.providers.TestCaseExecutionsContentProvider;
import rcp.providers.TestCaseExecutionsLabelProvider;
import rcp.views.ProjectView;
import rcp.views.TestCasesView;
import rcp.views.TestIterationsView;
import rcp.views.TestPlanView;
import util.SWTUtil;
import essentials.ProcedureFailed;
import essentials.Project;
import essentials.TestCase;
import essentials.TestCaseExecution;
import essentials.TestIteration;
import essentials.TestPlan;
import facade.RBTToolFacade;

public class HistoryTestExecutionDialog extends Dialog{
	
	private Project project;
	private TestPlan testPlan;
	private TestIteration testIteration;
	private TestCase testCase;
		
	private Text dateText;
	private Text notesText;
	
	private Combo statusCombo;
	
	private TableViewer testCaseProceduresTableViewer;
	private TableViewer testCaseExecutionsTableViewer;
	
	private Vector<TestCaseExecution> testCaseExecutions = new Vector<TestCaseExecution>();
	private Vector<ProcedureFailed> testCaseProceduresFailed = new Vector<ProcedureFailed>();
	
	private Label labelDescription;
	
	private String idTestExecution;
	private String resourceName;
	private String notes;
	private String date;

	public HistoryTestExecutionDialog(Shell parent, Project p, TestPlan tp, TestIteration ti, TestCase tc) {
		super(parent);
		this.project = p;
		this.testPlan = tp;
		this.testIteration = ti;
		this.testCase = tc;
	}
	
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		
		this.getShell().setText("Test Case Execution History");
		Composite mainArea = new Composite(parent, SWT.FILL_EVEN_ODD);
		GridLayout controlLayout = new GridLayout(1, false);
		mainArea.setLayout(controlLayout);
		
		Label label = new Label(mainArea, SWT.NONE);
		label.setText("Select an item in the table above to see the results of the test execution.");
		//Área da tabela do histórico
		createArea1(mainArea);
		
		//Área das procedures que notes + falharam 
		createArea2(mainArea);
		
		//Área da descrição da procedure que falhou
		createArea3(mainArea);
		
		return mainArea;
	}
	
	private Composite createArea1(Composite mainArea){
		
		Composite control = new Composite(mainArea, SWT.NONE);
        GridLayout controlLayout = new GridLayout(2, false);

        controlLayout.marginHeight = 0;
        controlLayout.marginWidth = 0;

        control.setLayout(controlLayout);
        control.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

        
        
        GridData gdText = new GridData();
		gdText.widthHint = 200;
		gdText.heightHint = 50;
		
		Composite tablePane = new Composite(control, SWT.NONE);
        GridLayout tablePaneLayout = new GridLayout();
        GridData gridData = new GridData(GridData.FILL_BOTH);

        tablePaneLayout.marginHeight = 0;
        tablePaneLayout.marginWidth = 0;

        tablePane.setLayout(tablePaneLayout);
        tablePane.setLayoutData(gridData);

        Table table = new Table(tablePane, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);

        TableLayout tblLayout = new TableLayout();
        TableColumn col = null;

        gridData = new GridData(GridData.FILL_BOTH);
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.heightHint = SWTUtil.getTableHeightHint(table, 5);
        gridData.widthHint = SWTUtil.convertWidthInCharsToPixels(mainArea, 80);

        tblLayout.addColumnData(new ColumnWeightData(25));
        tblLayout.addColumnData(new ColumnWeightData(25));
        tblLayout.addColumnData(new ColumnWeightData(50));
        

        table.setLayout(tblLayout);
        table.setLayoutData(gridData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Date");
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Time");
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Resource");
        
        if(testCase.getTestCaseExecution() != null){
        	testCaseExecutions = testCase.getTestCaseExecution();
        }
        
        // Hook up the viewer
        testCaseExecutionsTableViewer = new TableViewer(table);
        testCaseExecutionsTableViewer.setContentProvider(new TestCaseExecutionsContentProvider());
        testCaseExecutionsTableViewer.setLabelProvider(new TestCaseExecutionsLabelProvider());
        testCaseExecutionsTableViewer.setInput(testCaseExecutions);
        testCaseExecutionsTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                handleSelectionChangedTable1();
            }
        });

       handleSelectionChangedTable1();

		
		return control;
	}
	
	private Composite createArea2(Composite mainArea){
		
		Composite control = new Composite(mainArea, SWT.NONE);
        GridLayout controlLayout = new GridLayout(1, false);
        
        controlLayout.marginHeight = 0;
        controlLayout.marginWidth = 0;
        
        control.setLayout(controlLayout);
        control.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

        GridData gridDataNotes = new GridData(GridData.FILL, GridData.FILL, true, false);
        GridData gd2 = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
        
      //Label de notes
		Label labelNotes = new Label(control, SWT.NONE);
		labelNotes.setText("Notes: ");
		labelNotes.setLayoutData(gd2);
		
		//Texto de notes
		this.notesText = new Text(control, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		this.notesText.setEnabled(false);
		gridDataNotes.widthHint = convertHeightInCharsToPixels(20);
		gridDataNotes.heightHint = 100;
		this.notesText.setLayoutData(gridDataNotes);
        
        
		Label labelProcedure = new Label(control, SWT.NONE);
		labelProcedure.setText("Procedures failed (if there are)");
		
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
        gridData.widthHint = SWTUtil.convertWidthInCharsToPixels(mainArea, 80);

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
        
        
        // Hook up the viewer
        testCaseProceduresTableViewer = new TableViewer(table);
        testCaseProceduresTableViewer.setContentProvider(new ProceduresFailedContentProvider());
        testCaseProceduresTableViewer.setLabelProvider(new ProceduresFailedLabelProvider());
        testCaseProceduresTableViewer.setInput(testCaseProceduresFailed);
        testCaseProceduresTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                handleSelectionChangedTable2();
            }
        });

        handleSelectionChangedTable2();
		return control;
	}
	
	/**
     * Handle the selection changed action.
     */
    protected void handleSelectionChangedTable1() {
    	IStructuredSelection sel = getSelectionExecutionHistory();
        if(!sel.isEmpty()){
        	TestCaseExecution te = (TestCaseExecution)sel.getFirstElement();
        	this.notesText.setText(te.getNotes());
        	this.testCaseProceduresTableViewer.setInput(te.getProceduresFailed());
        }
       
    }
    
	/**
     * Handle the selection changed action.
     */
    protected void handleSelectionChangedTable2() {
        IStructuredSelection sel = getSelectionProcedures();
        if(!sel.isEmpty()){
        	ProcedureFailed pf = (ProcedureFailed) sel.getFirstElement();        
        	this.labelDescription.setText(pf.getBugDescription());
        }
       
    }
	
	private Composite createArea3(Composite mainArea){
		
		Composite control = new Composite(mainArea, SWT.NONE);
        GridLayout controlLayout = new GridLayout(1, false);

        controlLayout.marginHeight = 0;
        controlLayout.marginWidth = 0;

        control.setLayout(controlLayout);
        control.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));
        
        Label labelPresentation = new Label(control, SWT.NONE);
        labelPresentation.setText("Description of the problem at selected procedure: ");
        
        GridData gdLabel = new GridData(GridData.FILL, GridData.FILL,true, true);
        gdLabel.heightHint = 60;
        
        this.labelDescription = new Label(control, SWT.BORDER);
        labelDescription.setLayoutData(gdLabel);
        
        return control;
	}

		
	    
	@Override
	protected void okPressed() {
		
		String time = "";
		Date sysdate = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
		time = formatador.format(sysdate);
		
		this.idTestExecution = this.dateText.getText() + time + this.resourceName;
		this.notes = this.notesText.getText();
		this.date = dateText.getText();
		
		RBTToolFacade rbtToolFacade = RBTToolFacade.getInstance();
		
		//Chama a View
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		TestPlanView myTestPlanView = (TestPlanView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestPlanView.VIEW_ID);
		TestIterationsView myTestIterationsView = (TestIterationsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestIterationsView.VIEW_ID);
		TestCasesView myTestCasesView = (TestCasesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestCasesView.VIEW_ID);
		
		//Chama a ListViewer das Views chamadas
		ListViewer listProject = myProjectView.viewer;
		ListViewer listTestPlan = myTestPlanView.viewer;
		TableViewer tableTestIteration = myTestIterationsView.tableViewer;
		TableViewer tableTestCase = myTestCasesView.tableViewer;
		
		//Pega o projeto selecionado na list
		Project p = (Project) ((IStructuredSelection)listProject.getSelection()).getFirstElement();
		TestPlan tp = (TestPlan) ((IStructuredSelection)listTestPlan.getSelection()).getFirstElement();
		TestIteration ti = (TestIteration) ((IStructuredSelection)tableTestIteration.getSelection()).getFirstElement();
		TestCase tc = (TestCase) ((IStructuredSelection)tableTestCase.getSelection()).getFirstElement();
		
		TestCaseExecution te = new TestCaseExecution();
		te.setDate(this.date);
		te.setTime(time);
		te.setId(this.idTestExecution);
		te.setNotes(this.notes);
		te.setResourceName(this.resourceName);
		te.setProceduresFailed(this.testCaseProceduresFailed);
		
		Vector<TestCaseExecution> executions = this.testCase.getTestCaseExecution();
		
		if(executions == null){
			executions = new Vector<TestCaseExecution>();
		}
		
		executions.add(te);
		
		this.testCase.setTestCaseExecution(executions);
		this.testCase.setStatus(this.statusCombo.getItem((this.statusCombo.getSelectionIndex())));
		rbtToolFacade.updateTestCase(this.testCase, tc.getRequirement(), testIteration, testPlan, project);
		
		myTestCasesView.tableViewer.setInput(rbtToolFacade.getTestCasesFromIteration(ti, tp, p));
		this.close();
	}
	
	/**
     * Returns the table viewer selection.
     * @return the table viewer selection.
     */
    private IStructuredSelection getSelectionExecutionHistory() {
        return (IStructuredSelection) testCaseExecutionsTableViewer.getSelection();
    }
    
	/**
     * Returns the table viewer selection.
     * @return the table viewer selection.
     */
    private IStructuredSelection getSelectionProcedures() {
        return (IStructuredSelection) testCaseProceduresTableViewer.getSelection();
    }



	public Project getProject() {
		return project;
	}


	public TestPlan getTestPlan() {
		return testPlan;
	}


	public TestIteration getTestIteration() {
		return testIteration;
	}


	public TestCase getTestCase() {
		return testCase;
	}


	public Vector<ProcedureFailed> getTestCaseProceduresFailed() {
		return testCaseProceduresFailed;
	}


	public String getNotes() {
		return notes;
	}


	public String getDate() {
		return date;
	}


	public String getIdTestExecution() {
		return idTestExecution;
	}

	

}
