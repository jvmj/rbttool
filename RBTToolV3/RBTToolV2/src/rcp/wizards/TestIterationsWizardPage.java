package rcp.wizards;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import rcp.dialogs.NewTestIterationDialog;
import rcp.providers.RequirementsContentProvider;
import rcp.providers.RequirementsLabelProvider;
import rcp.providers.TestIterationsContentProvider;
import rcp.providers.TestIterationsLabelProvider;
import util.SWTUtil;
import essentials.Requirement;
import essentials.TestIteration;
import essentials.TestPlan;
import facade.RBTToolFacade;

public class TestIterationsWizardPage extends WizardPage {

	private TableViewer testIterationTableViewer;
	private TableViewer requirementsTableViewer;
	
	private Button addButton;
    private Button removeButton;
    
	private String projectName;
	
	private TestPlan testPlan;

	private int contador = 1;

	private Vector<TestIteration> testIterations = new Vector<TestIteration>();
	private Vector<Requirement> requirements = new Vector<Requirement>();
	
	public TestIterationsWizardPage(String projectName) {
		
		super("Test Plan Iterations");
		setTitle("Iterations");
		setDescription("Define the iterations according the Risk Analysis Priorization");
		this.projectName = projectName;
		setPageComplete(false);
			
	}

	@Override
	public void createControl(Composite parent) {
		Composite mainArea = new Composite(parent, SWT.NONE);
		GridLayout controlLayout = new GridLayout(1, false);
		Label label = new Label(mainArea, SWT.NONE);
		label.setText("Test Iterations:");
		mainArea.setLayout(controlLayout);
		createTestIterationsArea(mainArea);
		label = new Label(mainArea, SWT.NONE);
		label.setText("Test Iterations Requirements:");
		createRequirementsArea(mainArea);
		setControl(mainArea);
	}

	private Composite createTestIterationsArea(Composite parent) {
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

        tblLayout.addColumnData(new ColumnWeightData(10));
        tblLayout.addColumnData(new ColumnWeightData(20));
        tblLayout.addColumnData(new ColumnWeightData(20));
        tblLayout.addColumnData(new ColumnWeightData(25));
        tblLayout.addColumnData(new ColumnWeightData(25));

        table.setLayout(tblLayout);
        table.setLayoutData(gridData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        col = new TableColumn(table, SWT.LEFT);
        col.setText("Id");
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Less RE Value");
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Most RE Value");
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Start Date");
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Due Date");

        // Create the button pane
        Composite buttonPane = new Composite(control, SWT.NONE);
        GridLayout buttonPaneLayout = new GridLayout();

        buttonPaneLayout.marginHeight = 0;
        buttonPaneLayout.marginWidth = 0;

        buttonPane.setLayout(buttonPaneLayout);
        buttonPane.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        // New button
        addButton = new Button(buttonPane, SWT.PUSH);
        addButton.setText("Add...");

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
        testIterationTableViewer = new TableViewer(table);
        testIterationTableViewer.setContentProvider(new TestIterationsContentProvider());
        testIterationTableViewer.setLabelProvider(new TestIterationsLabelProvider());
        testIterationTableViewer.setInput(testIterations);
        testIterationTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                handleSelectionChanged();
            }
        });

        handleSelectionChanged();
		return control;
	}

	private Composite createRequirementsArea(Composite parent) {
		Composite control = new Composite(parent, SWT.NONE);
        GridLayout controlLayout = new GridLayout(1, false);

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

        tblLayout.addColumnData(new ColumnWeightData(20));
        tblLayout.addColumnData(new ColumnWeightData(40));
        tblLayout.addColumnData(new ColumnWeightData(40));

        table.setLayout(tblLayout);
        table.setLayoutData(gridData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        col = new TableColumn(table, SWT.LEFT);
        col.setText("Id");
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Name");
        
        col = new TableColumn(table, SWT.LEFT);
        col.setText("Risk Exposure");
        
        // Hook up the viewer
        requirementsTableViewer = new TableViewer(table);
        requirementsTableViewer.setContentProvider(new RequirementsContentProvider());
        requirementsTableViewer.setLabelProvider(new RequirementsLabelProvider());
        requirementsTableViewer.setInput(requirements);

		return control;
	}
	  

	/**
     * Handle the selection changed action.
     */
    protected void handleSelectionChanged() {
        IStructuredSelection sel = getSelection();
        if (sel.isEmpty()) {
            removeButton.setEnabled(false);
        } else {
            removeButton.setEnabled(true);
            TestIteration testIteration = (TestIteration) sel.getFirstElement();
            requirements = testIteration.getRequirements();
            requirementsTableViewer.setInput(requirements);
        }
    }
    

    protected void handleAdd() {

        TestIteration testIteration = null;
        //boolean out = false;

        NewTestIterationDialog dlg = new NewTestIterationDialog(Display.getCurrent().getActiveShell(), projectName);

        if (Window.OK == dlg.open()) {

        	testIteration = createTestIteration(dlg.getLessREValue(), dlg.getMostREValue(),
                       dlg.getStartDate(), dlg.getDueDate());
        	//Setando o Identificador de testIteration...
        	testIteration.setIdentifier(contador + "");
        	contador ++;
               
        	if (testIteration != null) {
                    //testIterationTableViewer.add(testIteration);
                    testIterations.add(testIteration);
                    testIterationTableViewer.setInput(testIterations);
                    setPageComplete(true);
                }
    		
            //}
        }
    }
    
    private TestIteration createTestIteration(double lessREValue,double mostREValue, String startDate, String dueDate) {
    	TestIteration testIteration = new TestIteration();
    	testIteration.setLessREValue(lessREValue);
    	testIteration.setMostREValue(mostREValue);
    	testIteration.setStartDate(startDate);
    	testIteration.setDueDate(dueDate);
    	testIteration.setProjectName(projectName);
    	
    	Vector<Requirement> selectedRequirements = RBTToolFacade.getInstance().getRequirementsREInterval(projectName, mostREValue, lessREValue);
    	
    	testIteration.setRequirements(selectedRequirements);

    	return testIteration;
    }
    
    /**
     * Handle the remove action.
     */
    protected void handleRemove() {
        IStructuredSelection sel = getSelection();
        if ((null != sel) && (!sel.isEmpty())) {
            for (Iterator<?> iter = sel.iterator(); iter.hasNext();) {
                TestIteration assoc = (TestIteration) iter.next();
                testIterationTableViewer.remove(assoc);
                testIterations.remove(assoc);
                requirementsTableViewer.setInput(null);
            }
        }
    }
    
    /**
     * Returns the table viewer selection.
     * @return the table viewer selection.
     */
    private IStructuredSelection getSelection() {
        return (IStructuredSelection) testIterationTableViewer.getSelection();
    }

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Vector<TestIteration> getTestIterations() {
		return testIterations;
	}

	public void setTestIterations(Vector<TestIteration> testIterations) {
		this.testIterations = testIterations;
	}

	public TestPlan getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(TestPlan testPlan) {
		this.testPlan = testPlan;
	}
	
}
