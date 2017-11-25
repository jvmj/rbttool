package rcp.dialogs;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import util.SWTUtil;
import essentials.Requirement;
import facade.RBTToolFacade;

public class NewTestCaseProcedureDialog extends Dialog {
	
	private Text textDescription;

	private Text textExpectedResults;
	
	private String testCaseId;
	
	private String description;
	
	private String expectedResult;
	
	
	public NewTestCaseProcedureDialog(Shell parentShell, String testCaseId) {
		super(parentShell);
		this.testCaseId = testCaseId;
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Test Iteration"); 
		SWTUtil.centerDialog(newShell);
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		getOkayButton().setEnabled(false);
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		((GridLayout) composite.getLayout()).numColumns = 2;

		Label startDateLabel = new Label(composite, SWT.NONE);
		startDateLabel.setText("Description");

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 300;
		
		textDescription = new Text(composite, SWT.BORDER | SWT.SINGLE);
		textDescription.setLayoutData(gd);
		
		textDescription.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validadeFields();
			}
		});

		Label dueDateLabel = new Label(composite, SWT.NONE);
		dueDateLabel.setText("Expected result"); 
		
		textExpectedResults = new Text(composite, SWT.BORDER | SWT.SINGLE);
		textExpectedResults.setLayoutData(gd);
		textExpectedResults.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validadeFields();
			}
		});

		return composite;
	}

	/**
	 * Ensures that both text fields are set.
	 */
	private void validadeFields() {
		boolean enabled = textDescription.getText().trim().length() > 0 &&
		textExpectedResults.getText().trim().length() > 0;
		getOkayButton().setEnabled(enabled);
	}

	Button getOkayButton() {
		return getButton(IDialogConstants.OK_ID);
	}
	
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CLIENT_ID) {
			new RequirementsDialog2(this.getShell(), this.testCaseId).open();
		}
		super.buttonPressed(buttonId);
	}
	protected void okPressed() {
		description = textDescription.getText().trim();
		expectedResult = textExpectedResults.getText().trim();
		
		super.okPressed();
		
	}

	public String getDescription() {
		return description;
	}

	public String getExpectedResult() {
		return expectedResult;
	}
	
	
}
