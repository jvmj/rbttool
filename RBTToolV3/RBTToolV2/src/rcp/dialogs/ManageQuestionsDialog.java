package rcp.dialogs;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import rcp.providers.QuestionContentProvider;
import rcp.providers.QuestionLabelProvider;
import essentials.Project;
import essentials.Question;
import essentials.Questionnaire;
import facade.RBTToolFacade;

public class ManageQuestionsDialog extends Dialog {
	
	public ListViewer viewer;
	private Project project;
	private Questionnaire questionnaire;
	private Label questionsDetailsElement ;
	
	Vector<Question> questions;

	public ManageQuestionsDialog(Shell parentShell, Project p, Questionnaire q) {
		super(parentShell);
		this.project = p;
		this.questionnaire = q;
	}
	
	@Override
	protected void configureShell(Shell newShell) {

		super.configureShell(newShell);
		newShell.setText("Questions Management Dialog for Questionnaire " + this.questionnaire.getName());
	  
	}
	
	//Escondendo os botões OK e Cancel que aparecem por Default da classe Dialog
	@Override
	protected Control createButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite composite = new Composite(parent, SWT.FILL);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
        
		GridData gdButton = new GridData(GridData.FILL_HORIZONTAL);
		gdButton.widthHint = 100;
		//gdButton.heightHint = 100;
		
       
		
		GridData gdList = new GridData(GridData.FILL, GridData.FILL,true, true);
		gdList.heightHint = 200;
		gdList.widthHint = 800;
		gdList.horizontalSpan = 3;
		//gdList.widthHint = 100;
		
		GridData gdLabel = new GridData(GridData.FILL, GridData.FILL,true, true);
		gdLabel.horizontalSpan = 3;
		
		Composite listPane = new Composite(composite, SWT.NONE);
		GridLayout listLayout = new GridLayout();
		listPane.setLayout(listLayout);
		listPane.setLayoutData(new GridData(GridData.FILL_BOTH));
		
        final List list = new List(listPane, SWT.SINGLE |SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        list.setLayoutData(gdList);
        this.viewer = new ListViewer(list);
        
        this.viewer.setUseHashlookup(true);
        this.viewer.setContentProvider(new QuestionContentProvider());
        this.viewer.setLabelProvider(new QuestionLabelProvider());
        
        questions = RBTToolFacade.getInstance().getQuestions(questionnaire.getName(), project.getName());
        this.viewer.setInput(questions);
        
        
        // Create the button pane
        Composite buttonPane = new Composite(composite, SWT.NONE);
        GridLayout buttonPaneLayout = new GridLayout();

        buttonPaneLayout.marginHeight = 0;
        buttonPaneLayout.marginWidth = 0;

        buttonPane.setLayout(buttonPaneLayout);
        buttonPane.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
        
		Button btAdd = new Button(buttonPane, SWT.PUSH);
        btAdd.setText("Add");
        btAdd.setLayoutData(gdButton);
        
        Button btRemove = new Button(buttonPane, SWT.PUSH);
        btRemove.setText("Remove");
        btRemove.setLayoutData(gdButton);
        
        

        gdLabel.heightHint = 100;
        
        questionsDetailsElement = new Label(composite, SWT.BORDER);
        questionsDetailsElement.setLayoutData(gdLabel);
        
        viewer.addSelectionChangedListener(new ISelectionChangedListener(){

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				
				if(!selection.isEmpty()){
					questionsDetailsElement.setText("Description: " + ((Question) ((IStructuredSelection) selection).getFirstElement()).getDescription()
							+ "\n\nRisk: " + ((Question) ((IStructuredSelection) selection).getFirstElement()).getRisk().getName()
							+ "\n\nRisk occurs when the answer is : " + ((Question) ((IStructuredSelection) selection).getFirstElement()).getRiskLeashedToAnswer());
				}
				
			}
        	
        });
        
        btAdd.addSelectionListener(new SelectionListener(){


			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				NewQuestionDialog d = new NewQuestionDialog(getShell(), questionnaire.getName(), project.getName());
				
				int code = d.open();
				
				if(questions == null){
					questions = new Vector<Question>();
				}
				

				
				if(code == Window.OK){
					
					Question question = new Question();
					

					question.setDescription(d.getQuestionDescriptionString());
					question.setRisk(RBTToolFacade.getInstance().searchRisk(d.getRiskNameString()));
					question.setRiskLeashedToAnswer(d.getAnswerString());
					
					RBTToolFacade.getInstance().addQuestion(question, questionnaire, project);
					
					questions.add(question);
					questionnaire.setQuestions(questions);
					
					viewer.setInput(questions);
				}
				
			}
        	
        });
        
        btRemove.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				RBTToolFacade rbtToolFacade = RBTToolFacade.getInstance();
				
				String selectedItem = viewer.getList().getSelection()[0];
				
				Question q = rbtToolFacade.searchQuestionName(selectedItem, questionnaire.getName(), project.getName());
				Vector<Question> questions = questionnaire.getQuestions();
				
				rbtToolFacade.removeQuestion(q, questionnaire, project);
				questions.remove(q);
				
				viewer.setInput(questions);
				questionsDetailsElement.setText("");
				
				
				
			}
        	
        });


				return composite;
	}

	
	@Override
	protected void okPressed() {
		super.okPressed();
		
	}
		

}
