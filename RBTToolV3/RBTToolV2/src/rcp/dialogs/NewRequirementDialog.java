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

import essentials.Requirement;
import facade.RBTToolFacade;

public class NewRequirementDialog extends Dialog {
	
	String nameProject;
	
	//Repare nos dois argumentos: Shell e o nome do projeto!
	public NewRequirementDialog(Shell parentShell, String nameProject) {
		super(parentShell);
		this.nameProject = nameProject;
		// TODO Auto-generated constructor stub
	}

	private Text idText;

	private Text nameText;
	
	private Text versionText;

	private Text moduleText;
	
	private Text mainDescriptionText;

	
	private String idString;

	private String nameString;
	
	private String versionString;

	private String moduleString;
	
	private String mainDescriptionString;
	
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Add requirement");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		

		Label idLabel = new Label(composite, SWT.NONE);
		idLabel.setText("&Identification:");
		idLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		idText = new Text(composite, SWT.BORDER);
		idText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, false));
	
		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("&Name:");
		nameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		nameText = new Text(composite, SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, false));
		
		Label versionLabel = new Label(composite, SWT.NONE);
		versionLabel.setText("&Version:");
		versionLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		versionText = new Text(composite, SWT.BORDER);
		versionText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, false));

		
		Label moduleLabel = new Label(composite, SWT.NONE);
		moduleLabel.setText("&Module:");
		moduleLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		moduleText = new Text(composite, SWT.BORDER);
		moduleText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, false));
		
		Label mainDescriptionLabel = new Label(composite, SWT.NONE);
		mainDescriptionLabel.setText("&Description:");
		mainDescriptionLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		
		//"gridData.widthHint = convertHeightInCharsToPixels(20);" e"gridData.heightHint = 100;" mudam o tamanho do campo de texto
		mainDescriptionText = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, true,
				false);
		gridData.widthHint = convertHeightInCharsToPixels(20);
		gridData.heightHint = 100;
		mainDescriptionText.setLayoutData(gridData);
		
		return composite;
	}
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		idString = idText.getText();
		nameString = nameText.getText();
		versionString = versionText.getText();
		moduleString = moduleText.getText();
		mainDescriptionString = mainDescriptionText.getText();
		
		RBTToolFacade rbtToolFachada = RBTToolFacade.getInstance();
		Requirement newRequirement = new Requirement(idString,nameString,versionString, mainDescriptionString);
		
		if (idString.equals("")) {
			MessageDialog.openError(getShell(), "Invalid Name",
					"Idenntifier field must not be blank.");
			return;
		}else if(rbtToolFachada.exists(newRequirement, rbtToolFachada.searchProject(nameProject))){
			MessageDialog.openError(dialogArea.getShell(), "Error", "The requirement " + nameString + " already exists on the Project " + nameProject + "!");
			return;
		}
		
		super.okPressed();
	}

	public String getIdString() {
		return idString;
	}

	public String getNameString() {
		return nameString;
	}


	public String getModuleString() {
		return moduleString;
	}

	public String getMainDescriptionString() {
		return mainDescriptionString;
	}

	public Text getIdText() {
		return idText;
	}

	public Text getNameText() {
		return nameText;
	}

	public Text getModuleText() {
		return moduleText;
	}

	public Text getMainDescriptionText() {
		return mainDescriptionText;
	}

	public String getVersionString() {
		return versionString;
	}
	
	
	
	

}
