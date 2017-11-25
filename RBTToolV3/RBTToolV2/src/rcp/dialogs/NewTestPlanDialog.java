package rcp.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewTestPlanDialog extends Dialog {

	private Text testPlanNameText;
	private Text mainDescriptionText;
	private Combo noIterationsCombo;
	
	
	
	private String testPlanName = "";
	private String projectName;
	private String mainDescription = "";
	private int noIterations;
	
	public NewTestPlanDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("New Test Plan");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {

		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		GridData gd = new GridData(GridData.FILL, GridData.FILL,true, false);

		
		GridData gridDataDescription = new GridData(GridData.FILL, GridData.FILL, true,
				false);

		
		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("&Test Plan Name: ");
		nameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		testPlanNameText = new Text(composite, SWT.BORDER);
		//nameText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
			//	true, false));
		testPlanNameText.setLayoutData(gd);
		

		Label mainDescriptionLabel = new Label(composite, SWT.NONE);
		mainDescriptionLabel.setText("&Description: ");
		mainDescriptionLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		mainDescriptionText = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);

		gridDataDescription.widthHint = convertHeightInCharsToPixels(20);
		gridDataDescription.heightHint = 100;
		mainDescriptionText.setLayoutData(gridDataDescription);
		


		Label noIterationsLabel = new Label(composite, SWT.NONE);
		noIterationsLabel.setText("&No. of Iterations: ");
		noIterationsLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));
		

    	String[] possibleNoOfIterations = {"1","2","3","4","5","6"};
		
		noIterationsCombo = new Combo(composite, SWT.READ_ONLY);
		
		for (int i = 0; i < possibleNoOfIterations.length; i++) {
			noIterationsCombo.add(possibleNoOfIterations[i]);
		}
		noIterationsCombo.select(0);
		
		

		return composite;
	}
	
	@Override
	protected void okPressed() {

		testPlanName = testPlanNameText.getText();
		mainDescription = mainDescriptionText.getText();
		noIterations = Integer.parseInt(noIterationsCombo.getItem(noIterationsCombo.getSelectionIndex()));
		
		super.okPressed();
	}

	public String getTestPlanName() {
		return testPlanName;
	}

	public void setTestPlanName(String testPlanName) {
		this.testPlanName = testPlanName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMainDescription() {
		return mainDescription;
	}

	public void setMainDescription(String mainDescription) {
		this.mainDescription = mainDescription;
	}

	public int getNoIterations() {
		return noIterations;
	}

	public void setNoIterations(int noIterations) {
		this.noIterations = noIterations;
	}
	
	

}
