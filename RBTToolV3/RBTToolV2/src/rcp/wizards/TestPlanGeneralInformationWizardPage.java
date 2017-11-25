package rcp.wizards;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class TestPlanGeneralInformationWizardPage extends WizardPage {

	private Text testPlanNameText;
	private Text mainDescriptionText;
	private String testPlanName;
	private String projectName = null;
	private String mainDescription = "";
	
	
	public TestPlanGeneralInformationWizardPage(String projectName) {
		super("Test Plan General Information");
		setTitle("General informations");
		this.projectName = projectName;
		setPageComplete(false);
		
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		GridData gd = new GridData(GridData.FILL, GridData.FILL,true, false);

		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("&Test Plan Name: ");
		nameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		testPlanNameText = new Text(composite, SWT.BORDER);
		testPlanNameText.setLayoutData(gd);
		testPlanName = testPlanNameText.getText();
		

		Label mainDescriptionLabel = new Label(composite, SWT.NONE);
		mainDescriptionLabel.setText("&Description: ");
		mainDescriptionLabel.setLayoutData(new GridData(GridData.END, GridData.BEGINNING,
				false, false));

		mainDescriptionText = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);

		mainDescriptionText.setLayoutData(new GridData(GridData.FILL_BOTH));
		mainDescription = mainDescriptionText.getText();


		//Listener para verificar se foi digitado algo nos campos correspondentes
		Listener listener = new Listener(){

			public void handleEvent(Event event) {
				setPageComplete(validadePage());
			}
		};

		//Botando o listener pra rodar
		testPlanNameText.addListener(SWT.Modify, listener);
		mainDescriptionText.addListener(SWT.Modify, listener);
		
		setControl(composite);
		

	}
	
	/**
	 * Ensures that both text fields are set.
	 */
	private boolean validadePage() {
		boolean result = true;

		if (testPlanNameText.getText() != null
				&& (!(testPlanNameText.getText().length() > 0))) {
			updateStatus("Invalid Test Plan name.");
			result = false;
		} else if (mainDescriptionText.getText() != null
				&& (!(mainDescriptionText.getText().length() > 0))) {
			updateStatus("Invalid description.");
			result = false;
		} 

		if (result) {
			updateStatus(null);			
		}
		
		return result;
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		testPlanName = testPlanNameText.getText();
		mainDescription = mainDescriptionText.getText();
		return getWizard().getPage("Test Plan Iterations");
	}
	
	/**
	 * Upadate de status on operation based.
	 * 
	 * @param message
	 *            status message
	 */
	private void updateStatus(String message) {
		setErrorMessage(message);
	}
	
	//Métodos get
	public String getTestPlanName() {
		return testPlanName;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getMainDescription() {
		return mainDescription;
	}
}
