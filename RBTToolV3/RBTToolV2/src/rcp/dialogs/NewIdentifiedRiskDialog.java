package rcp.dialogs;

import java.util.Vector;

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

import essentials.Risk;
import facade.RBTToolFacade;

public class NewIdentifiedRiskDialog extends Dialog {
	
	String nameProject;
	String requirementID;
	
	//Repare nos dois argumentos: Shell e o nome do projeto!
	public NewIdentifiedRiskDialog(Shell parentShell, String nameProject, String requirementID) {
		super(parentShell);
		this.nameProject = nameProject;
		this.requirementID = requirementID;
		// TODO Auto-generated constructor stub
	}

	private Combo risksCombo;

	private Text causeText;
	
	private String riskString;

	private String causeString;
	
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Add identified risk");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		
		RBTToolFacade rbttoolFacade = RBTToolFacade.getInstance();
		
		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		

		Label riskLabel = new Label(composite, SWT.NONE);
		riskLabel.setText("&Risk Leashed:");
		riskLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));
		
		risksCombo = new Combo(composite, SWT.READ_ONLY);
		Vector<Object> risks = rbttoolFacade.getRisks();
		
		
		for (int i = 0; i < risks.size(); i++) {
			Risk risk = (Risk)risks.elementAt(i);
			risksCombo.add(risk.getName());
			
		}
		risksCombo.select(0);
	
		Label causeLabel = new Label(composite, SWT.NONE);
		causeLabel.setText("&Cause:");
		causeLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		causeText = new Text(composite, SWT.BORDER);
		GridData gdText = new GridData(GridData.FILL, GridData.FILL,true, false);
		gdText.widthHint = 600;
		causeText.setLayoutData(gdText);
		return composite;
	}
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		riskString = risksCombo.getItem(risksCombo.getSelectionIndex());
		causeString = causeText.getText();
		
		super.okPressed();
	}

	public String getRiskString() {
		return riskString;
	}

	public String getCauseString() {
		return causeString;
	}

	
}
