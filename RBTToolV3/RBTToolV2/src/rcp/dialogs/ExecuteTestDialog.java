package rcp.dialogs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
import org.eclipse.ui.PlatformUI;

import rcp.providers.ProceduresFailedContentProvider;
import rcp.providers.ProceduresFailedLabelProvider;
import rcp.views.ProjectView;
import rcp.views.TestCasesView;
import rcp.views.TestIterationsView;
import rcp.views.TestPlanView;
import util.SWTUtil;
import essentials.ProcedureFailed;
import essentials.Project;
import essentials.TestCase;
import essentials.TestCaseExecution;
import essentials.TestCaseProcedure;
import essentials.TestCaseStatus;
import essentials.TestIteration;
import essentials.TestPlan;
import facade.RBTToolFacade;

public class ExecuteTestDialog extends Dialog{
	
	private Project project;
	private TestPlan testPlan;
	private TestIteration testIteration;
	private TestCase testCase;
		
	private Text dateText;
	private Text notesText;
	
	private Combo statusCombo;
	
	private TableViewer testCaseProceduresTableViewer;
	
	private Button addButton;
	private Button removeButton;
	
	private Vector<ProcedureFailed> testCaseProceduresFailed = new Vector<ProcedureFailed>();
	
	private Label labelDescription;
	
	private String idTestExecution;
	private String resourceName;
	private String notes;
	private String date;

	public ExecuteTestDialog(Shell parent, Project p, String resourceName, TestPlan tp, TestIteration ti, TestCase tc) {
		super(parent);
		this.project = p;
		this.resourceName = resourceName;
		this.testPlan = tp;
		this.testIteration = ti;
		this.testCase = tc;
	}
	
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		
		this.getShell().setText("TestCaseExecution_" + resourceName);
		Composite mainArea = new Composite(parent, SWT.FILL_EVEN_ODD);
		GridLayout controlLayout = new GridLayout(1, false);
		mainArea.setLayout(controlLayout);
		
		//Área de informações gerais
		createArea1(mainArea);
		
		Label labelNextSection = new Label(mainArea, SWT.NONE);
		labelNextSection.setText("If any procedure in this test case has presented problems, please report bellow at procedures table: ");
		
		//Área das procedures que falharam
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

        
        GridData gd = new GridData(GridData.FILL, GridData.FILL,true, false);
        GridData gd2 = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
        GridData gridDataNotes = new GridData(GridData.FILL, GridData.FILL, true, false);
        
        GridData gdText = new GridData();
		gdText.widthHint = 200;
		gdText.heightHint = 50;
		
		//Label da data
		Label labelDate = new Label(control, SWT.NONE);
		labelDate.setText("Date: ");
		labelDate.setLayoutData(gd2);
		
		//Texto da data
		this.dateText = new Text(control, SWT.BORDER);
		String formato = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		//Aparece a data atual no campo de texto da data
		this.dateText.setText(formatter.format(new Date()));
		this.dateText.setLayoutData(gd);
		
		//Label do status da execução
		Label labelStatus = new Label(control, SWT.NONE);
		labelStatus.setText("Status: ");
		labelStatus.setLayoutData(gd2);
		
		this.statusCombo = new Combo(control, SWT.READ_ONLY);
		this.statusCombo.add(TestCaseStatus.NOT_EXECUTED);
		this.statusCombo.add(TestCaseStatus.PASSED);
		this.statusCombo.add(TestCaseStatus.FAILED);
		this.statusCombo.add(TestCaseStatus.BLOCKED);
		//Deixa a primeira opção selecionada
		this.statusCombo.select(0);
		
		//Label de notes
		Label labelNotes = new Label(control, SWT.NONE);
		labelNotes.setText("Notes: ");
		labelNotes.setLayoutData(gd2);
		
		//Texto de notes
		this.notesText = new Text(control, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		gridDataNotes.widthHint = convertHeightInCharsToPixels(20);
		gridDataNotes.heightHint = 100;
		this.notesText.setLayoutData(gridDataNotes);
		
		return control;
	}
	
	private Composite createArea2(Composite mainArea){
		
		Composite control = new Composite(mainArea, SWT.NONE);
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
        
         // Create the button pane
        Composite buttonPane = new Composite(control, SWT.NONE);
        GridLayout buttonPaneLayout = new GridLayout();

        buttonPaneLayout.marginHeight = 0;
        buttonPaneLayout.marginWidth = 0;

        buttonPane.setLayout(buttonPaneLayout);
        buttonPane.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        // Add button
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

        
        // Hook up the viewer
        testCaseProceduresTableViewer = new TableViewer(table);
        testCaseProceduresTableViewer.setContentProvider(new ProceduresFailedContentProvider());
        testCaseProceduresTableViewer.setLabelProvider(new ProceduresFailedLabelProvider());
        testCaseProceduresTableViewer.setInput(testCaseProceduresFailed);
        testCaseProceduresTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                handleSelectionChanged();
            }
        });

        handleSelectionChanged();
		return control;
	}
	
	/**
     * Handle the selection changed action.
     */
    protected void handleSelectionChanged() {
        IStructuredSelection sel = getSelection();
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

	 private void handleAdd() {
		 
		 ProcedureFailed procedureFailed = null;
		 
		 NewProcedureFailedDialog dlg = new NewProcedureFailedDialog(Display.getCurrent().getActiveShell(), this.testCase);
		 
		 if(Window.OK == dlg.open()){
		 
			 procedureFailed = createTestCaseProcedureFailed(dlg.getFailureDescription(), dlg.getSelectedTestCaseProcedure());
			 
			 if(procedureFailed != null){
				 testCaseProceduresFailed.add(procedureFailed);
				 testCaseProceduresTableViewer.setInput(testCaseProceduresFailed);
			 }
		 }
	 }
	 
		private ProcedureFailed createTestCaseProcedureFailed(String description, TestCaseProcedure tcp){
			
			ProcedureFailed pf = new ProcedureFailed();
			pf.setBugDescription(description);
			pf.setProcedure(tcp);

			return pf;
		}
		
	    private void handleRemove() {
	        IStructuredSelection sel = getSelection();
	        if ((null != sel) && (!sel.isEmpty())) {
	            for (Iterator<?> iter = sel.iterator(); iter.hasNext();) {
	                ProcedureFailed assoc = (ProcedureFailed) iter.next();
	                testCaseProceduresTableViewer.remove(assoc);
	                testCaseProceduresFailed.remove(assoc);
	                this.labelDescription.setText("");
	            }
	        }
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
    private IStructuredSelection getSelection() {
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
