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

public class NewResourceDialog extends Dialog {
	
	private Text nameResource;
	
	private String nameResourceString;

	protected NewResourceDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("New Resource");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		

		Label mainDescriptionLabel = new Label(composite, SWT.NONE);
		mainDescriptionLabel.setText("&Name: ");
		mainDescriptionLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		nameResource = new Text(composite, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, true,
				false);
		gridData.widthHint = convertHeightInCharsToPixels(20);
		gridData.heightHint = 20;
		nameResource.setLayoutData(gridData);
		
		
		return composite;
	}
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		nameResourceString = nameResource.getText();
		
		if (nameResourceString.equals("")) {
			MessageDialog.openError(dialogArea.getShell(), "Invalid Name",
					"Resource name field must not be blank.");
			return;
		}
		super.okPressed();
	}

	public String getNameResourceString() {
		return nameResourceString;
	}

	public void setNameResourceString(String nameResourceString) {
		this.nameResourceString = nameResourceString;
	}
	
	

}
