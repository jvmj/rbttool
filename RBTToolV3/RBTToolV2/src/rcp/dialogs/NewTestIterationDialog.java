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

public class NewTestIterationDialog extends Dialog {
	
	private Combo comboLessREValue;
	
	private Combo comboMostREValue;

	private Text textStartDate;

	private Text textDueDate;

	private double lessREValue;
	
	private double mostREValue;
	
	private String startDate;

	private String dueDate;
	
	private String projectName;
	
	
	public NewTestIterationDialog(Shell parentShell, String projectName) {
		super(parentShell);
		this.projectName = projectName;
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
		createButton(parent, IDialogConstants.CLIENT_ID, "Requirements List", false);
		getOkayButton().setEnabled(false);
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		((GridLayout) composite.getLayout()).numColumns = 2;

		Label lessREValueLabel = new Label(composite, SWT.NONE);
		lessREValueLabel.setText("Less RE Value");
		
		comboLessREValue = SWTUtil.createCombo(composite);
		
		Label mostREValueLabel = new Label(composite, SWT.NONE);
		mostREValueLabel.setText("Most RE Value");
		
		comboMostREValue = SWTUtil.createCombo(composite);
		
		Vector<Requirement> requirements = RBTToolFacade.getInstance().searchProject(projectName).getRequirements();
		
		double[] res = new double[requirements.size()];
		
		for (int i = 0; i < requirements.size(); i++) {
			
			Requirement r = requirements.elementAt(i);
			double re = r.getRiskExposureFinal();
			res[i] = re;
		}
		
		//Ordena para exibir no combo
		java.util.Arrays.sort(res);
		
		for (int i = 0; i < res.length; i++) {
			if((res[i]+"").length() > 4){
				comboLessREValue.add((res[i]+"").substring(0, 4));
				comboMostREValue.add((res[i]+"").substring(0, 4));
			}else{
				comboLessREValue.add(res[i]+"");
				comboMostREValue.add(res[i]+"");
				}
		}
		
		Label startDateLabel = new Label(composite, SWT.NONE);
		startDateLabel.setText("Start Date");

		textStartDate = new Text(composite, SWT.BORDER | SWT.SINGLE);
		textStartDate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textStartDate.setTextLimit(10);
		
		textStartDate.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validadeFields();
			}
		});

		Label dueDateLabel = new Label(composite, SWT.NONE);
		dueDateLabel.setText("Due Date"); 
		
		textDueDate = new Text(composite, SWT.BORDER | SWT.SINGLE);
		textDueDate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textDueDate.setTextLimit(10);
		textDueDate.addModifyListener(new ModifyListener() {
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
		boolean enabled = textStartDate.getText().trim().length() > 0 &&
		textDueDate.getText().trim().length() > 0 
		&&comboLessREValue.getItem(comboLessREValue.getSelectionIndex()).trim().length() > 0
		&& comboMostREValue.getItem(comboMostREValue.getSelectionIndex()).trim().length() > 0;
		getOkayButton().setEnabled(enabled);
	}

	Button getOkayButton() {
		return getButton(IDialogConstants.OK_ID);
	}
	
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CLIENT_ID) {
			new RequirementsDialog2(this.getShell(), this.projectName).open();
		}
		super.buttonPressed(buttonId);
	}
	protected void okPressed() {
		startDate = textStartDate.getText().trim();
		dueDate = textDueDate.getText().trim();
		lessREValue = Double.valueOf(comboLessREValue.getItem(comboLessREValue.getSelectionIndex()).trim());
		mostREValue = Double.valueOf(comboMostREValue.getItem(comboMostREValue.getSelectionIndex()).trim());
		if(lessREValue > mostREValue){
			MessageDialog.openError(getShell(), "Error", "Less RE value > Most RE value!");
		}else{
			super.okPressed();
		}
	}
	
	public double getLessREValue() {
		return lessREValue;
	}
	
	public double getMostREValue() {
		return mostREValue;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	

}
