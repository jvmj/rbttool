package rcp.dialogs;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
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

public class NewQuestionDialog extends Dialog {
	
	String nameQuestionnaire;
	String nameProject;
	

	public NewQuestionDialog(Shell parentShell, String nameQuestionnaire, String nameProject) {
		super(parentShell);
		this.nameQuestionnaire = nameQuestionnaire;
		this.nameProject = nameProject;
	}
	
	
	private Text questionDescriptionText;
	
	private Combo risksCombo;
	
	private Combo answerCombo;
	
	private String questionNoString;
	
	private String questionDescriptionString;
	
	private String riskNameString;
	
	private String answerString;
	
	private Vector<Object> projects;
	
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("New Question");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		RBTToolFacade rbttoolFacade = RBTToolFacade.getInstance();
		
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);

		
		Label questionDescription = new Label(composite, SWT.NONE);
		questionDescription.setText("&Description: ");
		questionDescription.setLayoutData(new GridData(/*GridData.BEGINNING, GridData.CENTER,
				false, false*/));

		

		
		GridData gridData2 = new GridData(GridData.BEGINNING);
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.widthHint = 500;
		questionDescriptionText = new Text(composite, SWT.BORDER);
		questionDescriptionText.setLayoutData(gridData2);
		
		
		
		
		Label riskLeashed = new Label(composite, SWT.NONE);
		riskLeashed.setText("&Risk Leashed: ");
		riskLeashed.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER,
				false, false));
		
		
		risksCombo = new Combo(composite, SWT.READ_ONLY);
		Vector<Object> risks = rbttoolFacade.getRisks();
		
		for (int i = 0; i < risks.size(); i++) {
			Risk risk = (Risk)risks.elementAt(i);
			risksCombo.add(risk.getName());
			
		}
		risksCombo.select(0);
		
		Label itOccurs = new Label(composite, SWT.NONE);
		itOccurs.setText("&Risk occurs when the answer is: ");
		itOccurs.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER,
				false, false));
		
		answerCombo = new Combo(composite, SWT.READ_ONLY);
		answerCombo.add("Yes");
		answerCombo.add("No");
		answerCombo.select(0);

		
/*		radioTrue = new Button(composite, SWT.RADIO);
		radioTrue.setText("True");
		
		Label nothing1 = new Label(composite, SWT.NONE);
		nothing1.setText("");
		nothing1.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER,
				false, false));
		
		Label nothing2 = new Label(composite, SWT.NONE);
		nothing2.setText("");
		nothing2.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));
		
		Label nothing3 = new Label(composite, SWT.NONE);
		nothing3.setText("");
		nothing3.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));
		
		GridData gridData3 = new GridData(GridData.VERTICAL_ALIGN_END);
		gridData3.horizontalAlignment = 4;
		//gridData.verticalSpan = 1;
		//gridData.verticalAlignment = GridData.FILL;
		gridData3.horizontalAlignment = GridData.FILL;
		
		radioFalse = new Button(composite, SWT.RADIO);
		radioFalse.setText("False");
		radioFalse.setLayoutData(gridData3);*/

		
		return composite;
	}
	
	@Override
	protected void okPressed() {
		
		questionDescriptionString = questionDescriptionText.getText();
		riskNameString = risksCombo.getItem(risksCombo.getSelectionIndex());
		answerString = answerCombo.getItem(answerCombo.getSelectionIndex());
		
/*		RBTToolFacade facade = RBTToolFacade.getInstance();
		Project project = facade.searchProject(nameProject);
		Questionnaire questionnaire = facade.searchQuestionnaire(nameQuestionnaire, nameProject);
		
		Question question = new Question();
		question.setDescription(questionDescriptionString);
		question.setId(questionNoString);
		question.setRisk(facade.searchRisk(riskNameString));
		question.setRiskLeashedToAnswer(answerString);*/
		

		if(questionDescriptionString.equals("")){
			MessageDialog.openError(getShell(), "Error", "Please put the question description.");
			return;
		}
		

		
		super.okPressed();
	}

	public String getNameQuestionnaire() {
		return nameQuestionnaire;
	}

	public String getNameProject() {
		return nameProject;
	}

	public String getQuestionNoString() {
		return questionNoString;
	}

	public String getQuestionDescriptionString() {
		return questionDescriptionString;
	}


	public String getRiskNameString() {
		return riskNameString;
	}

	public String getAnswerString() {
		return answerString;
	}

	public Vector<Object> getProjects() {
		return projects;
	}
	
	

}
