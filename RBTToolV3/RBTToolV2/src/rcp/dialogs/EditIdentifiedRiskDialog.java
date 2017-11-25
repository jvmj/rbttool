package rcp.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import essentials.IdentifiedRisk;

public class EditIdentifiedRiskDialog extends Dialog {
	
	String nameProject;
	String requirementID;
	IdentifiedRisk identifiedRisk;
	
	public EditIdentifiedRiskDialog(Shell parentShell, String nameProject, String requirementID, IdentifiedRisk ir) {
		super(parentShell);
		this.nameProject = nameProject;
		this.requirementID = requirementID;
		this.identifiedRisk = ir;
	}

	private Text causeText;
	
	private String causeString;
	
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Edit cause of this identified risk");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {

		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		
		Label causeLabel = new Label(composite, SWT.NONE);
		causeLabel.setText("&New Cause:");
		causeLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		GridData gd = new GridData();
		gd.widthHint = 600;
		causeText = new Text(composite, SWT.BORDER);
		causeText.setText(identifiedRisk.getCause());
		causeText.setLayoutData(gd);
		
		
		return composite;
	}
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		causeString = causeText.getText();
		
		super.okPressed();
	}

	public String getCauseString() {
		return causeString;
	}

	
}
