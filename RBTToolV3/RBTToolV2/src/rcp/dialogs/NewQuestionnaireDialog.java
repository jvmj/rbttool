package rcp.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewQuestionnaireDialog extends Dialog {
	
	private String nameProject;
	
	private Text nameText;
	
	private String nameQuestionnaireString; 
	
	
	public NewQuestionnaireDialog(Shell parentShell, String nameProject) {
		super(parentShell);
		this.nameProject = nameProject;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("New Questionnaire");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);

		
		
		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("&Questionnaire Name: ");
		nameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		nameText = new Text(composite, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, true,
				false);
		gridData.widthHint = convertHeightInCharsToPixels(20);
		nameText.setLayoutData(gridData);
		
		return composite;
		
		
	}
	
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		nameQuestionnaireString = nameText.getText();
		
		if (nameQuestionnaireString.equals("")) {
			MessageDialog.openError(dialogArea.getShell(), "Invalid Name",
					"Name field must not be blank.");
			return;
		}
		
		

		
		close();
				

		
		super.okPressed();
	}

	public String getNameString() {
		return nameQuestionnaireString;
	}
	
	

}
