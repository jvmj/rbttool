package rcp.windows;

import java.io.File;
import java.util.Vector;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import rcp.dialogs.ExportRiskAnalysiesDialog;
import rcp.views.ProjectView;
import rcp.views.RequirementsView;
import util.Util;
import essentials.IdentifiedRisk;
import essentials.Project;
import essentials.Question;
import essentials.Questionnaire;
import essentials.Requirement;
import essentials.Risk;
import facade.RBTToolFacade;

public class AnswerQuestionnaireApplicationWindow extends ApplicationWindow {
	
	private Project project;
	private Vector<Requirement> requirements;
	private String nameResource;
	private Questionnaire questionnaire;
	private Vector<Question> questions;
	
	private String justifyString;
	private Risk risk;
	IdentifiedRisk identifiedRisk = null;
	
	private Vector<IdentifiedRisk> identified_risks = new Vector<IdentifiedRisk>(); 
	

	public AnswerQuestionnaireApplicationWindow(Shell parentShell, Project p, String nameResource, Questionnaire q) {
		super(parentShell);
		this.project = p;
		this.nameResource = nameResource;
		this.questionnaire = q;
	}
	
	@Override
	protected void configureShell(Shell shell) {
		// TODO Auto-generated method stub
		super.configureShell(shell);
		
	}
	
	
	
	@Override
	protected Control createContents(Composite parent) {
		
		this.getShell().setText("Questionnaire " + questionnaire.getName() + " answered by " + nameResource);
		final ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		
		final Composite composite = new Composite(sc, SWT.FILL);
		
		
				

		GridData gd = new GridData(GridData.FILL, GridData.FILL,true, false);
		gd.widthHint = 200;
		gd.horizontalSpan = 2;
		
		
		final RBTToolFacade rbttoolFacade = RBTToolFacade.getInstance();
		requirements = rbttoolFacade.searchProject(project.getName()).getRequirements();
		questions = rbttoolFacade.searchQuestionnaire(questionnaire.getName(), project.getName()).getQuestions();
		
		

		 
		 GridData gd2 = new GridData(GridData.FILL, GridData.FILL,false, false);
		 gd2.horizontalSpan = 2;
		 gd2.widthHint = 90;
		 gd2.heightHint = 30;
		 
		 GridData gd3 = new GridData(GridData.FILL, GridData.FILL,false, false);
		 
		 gd3.widthHint = 90;
		 gd3.heightHint = 30;
		 
		 Label labelLine1 = new Label(composite, SWT.FILL);
		 labelLine1.setText("__________________________________________________________");
		 //labelLine1.setText("_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
		 labelLine1.setFont(new Font(composite.getDisplay(), new FontData("Arial", 20, SWT.BOLD)));
		 GridData gd31 = new GridData();
		 gd31.horizontalSpan = 2;
		 labelLine1.setLayoutData(gd31);

		 
		 for (int i = 0; i < requirements.size(); i++) {
			 
			 
			 final Requirement requirement = (Requirement)requirements.elementAt(i);
			 
			 Label labelRequirement = new Label(composite, SWT.FILL);
			 labelRequirement.setText("Questions for the feature " + requirement.getName());
			 labelRequirement.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
			 labelRequirement.setForeground(new Color(composite.getDisplay(), 9, 37, 136));
			 labelRequirement.setLayoutData(gd);
			 
			 
			 
			 
			 
			 for (int j = 0; j < questions.size(); j++) {
				 
				 
				 
				 final Question question = questions.elementAt(j);
				 
				 
				 Label labelQuestion = new Label(composite, SWT.FILL);
				 labelQuestion.setText("\n " + question.getDescription());
				 labelQuestion.setLayoutData(gd2);
				 
				 final Combo answerCombo = new Combo(composite,SWT.READ_ONLY);
				 answerCombo.add("Yes");
				 answerCombo.add("No");
				 
				 
				 
				 
				 final Text justifyText = new Text(composite, SWT.BORDER);
				 justifyText.setEnabled(false);
				 justifyText.setLayoutData(gd2);
				 justifyText.addModifyListener(new ModifyListener(){
					 
						@Override
						public void modifyText(ModifyEvent e) {
							valueChanged((Text) e.widget);
							
						}
						
						private void valueChanged(Text text) {
													
							if (!text.isFocusControl())
						        return;
							
							
							if(text == justifyText){
								justifyText.setData(identifiedRisk);
								justifyString = justifyText.getText();
								
								
								/*IdentifiedRisk ir = (IdentifiedRisk) justifyText.getData();
							
								ir.setCause(justifyString);*/
								
								identifiedRisk = new IdentifiedRisk();
								
								identifiedRisk.setId(question.getDescription() + requirement.getIdentifier() + nameResource);
								identifiedRisk.setRequirementID(requirement.getIdentifier());
								identifiedRisk.setResourceName(nameResource);
								identifiedRisk.setRisk(risk);
								identifiedRisk.setQuestionDescription(question.getDescription());
								identifiedRisk.setProjectName(project.getName());
								identifiedRisk.setCause(justifyString);
								Vector<IdentifiedRisk> identifiedRisks = requirement.getIdentifiedRisks();
								
								if(identifiedRisks == null){
									
									identifiedRisks = new Vector<IdentifiedRisk>();
								}
								
							
								identified_risks.addElement(identifiedRisk);
						
				
								
							}
								
							

						}
						 
					 });
				 
				 
	
				 
				 //TESTANDO...
				 answerCombo.addSelectionListener(new SelectionListener(){

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void widgetSelected(SelectionEvent e) {
						
						
						//se resposta = resposta indicativa de risco
						 if(answerCombo.getText().equals(question.getRiskLeashedToAnswer())){
							 //abre campo de jusstificativa
							 justifyText.setEnabled(true);
							 risk = rbttoolFacade.searchRisk(question.getRisk().getName()); 
								
							identifiedRisk = new IdentifiedRisk();
							identifiedRisk.setId(question.getDescription() + requirement.getIdentifier() + nameResource);
							identifiedRisk.setRequirementID(requirement.getIdentifier());
							identifiedRisk.setResourceName(nameResource);
							identifiedRisk.setRisk(risk);
							identifiedRisk.setQuestionDescription(question.getDescription());
							identifiedRisk.setProjectName(project.getName());
							
														
							
							 
						 }else{
							 //caso contrário, permaneça desativado
							 justifyText.setEnabled(false);
							 
							
						 }
						 
						
					}
					 
				 });
	
				
			}


				 Label labelLine2 = new Label(composite, SWT.FILL);
				 labelLine2.setText("__________________________________________________________");
				 //labelLine2.setText("_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
				 labelLine2.setFont(new Font(composite.getDisplay(), new FontData("Arial", 20, SWT.BOLD)));
				 GridData gd32 = new GridData();
				 gd32.horizontalSpan = 2;
				 labelLine2.setLayoutData(gd32);
				 
			      
		 }
		 
		 Button btExport = new Button(composite, SWT.PUSH);
		 btExport.setText("Finish");
		 
		 //Ação ao pressionar o botão
		 btExport.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//Vector para ser exportado
				Vector<IdentifiedRisk> resultRiskIdentification = new Vector<IdentifiedRisk>();
				resultRiskIdentification = filterUnorganizedVector(identified_risks);
				requirements = rbttoolFacade.searchProject(project.getName()).getRequirements();
				
				for (int i = 0; i < resultRiskIdentification.size(); i++) {
					
					IdentifiedRisk ir = (IdentifiedRisk) resultRiskIdentification.elementAt(i);
					Requirement r = rbttoolFacade.searchRequirement(ir.getRequirementID(), ir.getProjectName());
					Vector<IdentifiedRisk> identifiedRisks = r.getIdentifiedRisks();
					
					if(identifiedRisks == null){
						identifiedRisks = new Vector<IdentifiedRisk>();
					}
					
					
					
					Project p = rbttoolFacade.searchProject(ir.getProjectName());
					
					if(rbttoolFacade.existsIdentifiedRisk(ir.getId(), r, p)){
						rbttoolFacade.updateIdentifiedRisk(ir, r, ir.getResourceName(), p);
					}else{
						//identifiedRisks.add(ir);
						rbttoolFacade.addIdentifiedRisk(ir, r, p);
					}
					
					Vector<Requirement> requirements = p.getRequirements();
					
					for (int j = 0; j < requirements.size(); j++) {
						
						Requirement r1 = requirements.elementAt(j);
						if(r1.getIdentifier().equals(r.getIdentifier())){
							Vector<IdentifiedRisk> ir1 = r1.getIdentifiedRisks();
							if(ir1 == null){
								ir1 = new Vector<IdentifiedRisk>();
								
							}
							ir1.add(ir);
							r1.setIdentifiedRisks(ir1);
							requirements.setElementAt(r1, j);
						}
					}
					p.setRequirements(requirements);
					ProjectView pView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
					pView.viewer.setInput(rbttoolFacade.getProjects());			
					pView.setFocus();
					RequirementsView rView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
					rView.tableViewer.setInput(requirements);
					rView.tableViewer.refresh();
					
					
					
					
				}
				
				//Seleciona se quer exportar ou não
				if(MessageDialog.openQuestion(getShell(), "Export", "Do you want to export a XML file with your answers?")){
					//Dialog q seleciona onde quer salvar o xml
					ExportRiskAnalysiesDialog raDialog = new ExportRiskAnalysiesDialog(getShell());
					
					String directoryChosen = raDialog.openDialog();
					
					if(directoryChosen != null){
						String fileName = Util.generateXMLFileNameYYYYMMDD(nameResource + "_IdentifiedRisks_");
						File file = new File(directoryChosen,fileName);
						RBTToolFacade.getInstance().exportIdentifiedRisk(resultRiskIdentification, file);
						MessageDialog.openInformation(composite.getParent().getShell(), "Export", "Export operation successful!");
						
					} 
														
				}
				
				//Fecha a janela de analise de riscos
				composite.getParent().getShell().close();
			}
			 
		 });
		 
			GridLayout layout = new GridLayout(2, true);
			composite.setLayout(layout);
		
		 sc.setContent(composite);
		 //sc.setSize(640, 480);
		    sc.setExpandHorizontal(true);
		    sc.setExpandVertical(true);
		    
		    //Faz com q o scroll ajuste de acordo com o tamanho da janela
		    sc.addControlListener(new ControlAdapter() {
		        public void controlResized(ControlEvent e) {
		          sc.setMinSize(composite.computeSize(SWT.DEFAULT,
		              SWT.DEFAULT));
		        }
		      });
		    
		    
		    			

		return sc;

	}
	
	private Vector<IdentifiedRisk> filterUnorganizedVector(Vector<IdentifiedRisk> identified_risks){
		
		Vector<IdentifiedRisk> result = new Vector<IdentifiedRisk>();
		
		for (int i = 0; i < identified_risks.size(); i++) {
			
			
			if(identified_risks.size() > 1 && i < identified_risks.size() - 1){
				
				
				IdentifiedRisk ir1 = identified_risks.elementAt(i);
				IdentifiedRisk ir2 = identified_risks.elementAt(i+1);
				
				while(ir2.getId().equals(ir1.getId())){
					i++;
					if(i > identified_risks.size() - 1){
						ir1 = ir2 = identified_risks.elementAt(i-1);
						break;
					}
					ir1 = identified_risks.elementAt(i-1);
					ir2 = identified_risks.elementAt(i);
				}
				if(ir2.getId() != ir1.getId()){
						
					result.add(ir1);
					/*if(i > 0)
						i--;*/
				}
				if(ir1.equals(ir2)){
					result.add(ir1);
				}
				if( i == identified_risks.size()-2){
					result.add(ir2);
				}
						
			
			}
			
			
			
		}
		
		return result;
	}

}
