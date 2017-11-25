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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
import essentials.Metric;
import essentials.Project;
import essentials.Requirement;
import essentials.RiskAnalysis;
import facade.RBTToolFacade;

public class RiskAnalysisApplicationWindow extends ApplicationWindow {
	
	private Project project;
	private Vector<Requirement> requirements;
	private String nameResource;
	
	private String costClientString;
	private String costVendorString;
	private String newFeatureString;
	private String designString;
	private String sizeString;
	private String complexityString;
	private String dependenceString;
	

	public RiskAnalysisApplicationWindow(Shell parentShell, Project p, String nameResource) {
		super(parentShell);
		this.project = p;
		this.nameResource = nameResource;
	}
	
	@Override
	protected void configureShell(Shell shell) {
		// TODO Auto-generated method stub
		super.configureShell(shell);
		
	}
	
	
	
	@Override
	protected Control createContents(Composite parent) {
		
		this.getShell().setText("Risk Analysis pefformed by " + nameResource);
		final ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		
		final Composite composite = new Composite(sc, SWT.FILL);
		
		
				

		GridData gd = new GridData(GridData.FILL, GridData.FILL,true, false);
		gd.widthHint = 100;
		gd.horizontalSpan = 8;
		
		
		final RBTToolFacade rbttoolFacade = RBTToolFacade.getInstance();
		Vector<Metric> metrics = rbttoolFacade.getMetrics();
		requirements = rbttoolFacade.searchProject(project.getName()).getRequirements();
		
		
		
		//Label labelFeatures = new Label(composite, SWT.FILL);
		//labelFeatures.setText("Features");
		//labelFeatures.setLayoutData(gd);
		
/*		 for (int i = 0; i < metrics.size(); i++) {
			 final Metric metric = (Metric)metrics.elementAt(i);
			 final Label labelTopColumn1 = new Label(composite, SWT.FILL);
			 labelTopColumn1.setText(metric.getName());
			 labelTopColumn1.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
			 labelTopColumn1.setLayoutData(gd);
			 
		
			 //Exibe um pequeno guia sobre os valores das métricas ao passar o mouse em cima da Label q representa a métrica correspondente
			 labelTopColumn1.addMouseMoveListener(new MouseMoveListener(){
				 
				@Override
				public void mouseMove(MouseEvent e) {
					
					
					labelTopColumn1.setToolTipText("Weight = " + metric.getWeight() + "\n \n" + metric.getMetricDetails());
					
				}
				 
			 });
		}*/
		 
		 GridData gd2 = new GridData(GridData.FILL, GridData.FILL,true, false);
		 //gd2.heightHint = 30;
		//gd2.widthHint = 90;
		 
		 
		/* Label labelImpact = new Label(composite, SWT.FILL);
		 labelImpact.setText("Impact");
		 labelImpact.setForeground(new Color(composite.getDisplay(), 9, 37, 136));
		 labelImpact.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
		 labelImpact.setLayoutData(gd2);
		 
		 Label labelMean = new Label(composite, SWT.FILL);
		 labelMean.setText("Average");
		 labelMean.setForeground(new Color(composite.getDisplay(), 9, 37, 136));
		 labelMean.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
		 labelMean.setLayoutData(gd2);
		 
		 Label labelProbability = new Label(composite, SWT.FILL);
		 labelProbability.setText("Probability");
		 labelProbability.setForeground(new Color(composite.getDisplay(), 9, 37, 136));
		 labelProbability.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
		 labelProbability.setLayoutData(gd2);*/
		 
/*		 Label labelRiskExposure = new Label(composite, SWT.FILL);
		 labelRiskExposure.setText("RE");
		 labelRiskExposure.setForeground(new Color(composite.getDisplay(), 9, 37, 136));
		 labelRiskExposure.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
		 labelRiskExposure.setLayoutData(gd2);*/
		 
		 
		 Label labelLine = new Label(composite, SWT.FILL);
		 labelLine.setText("__________________________________________________________");
		 labelLine.setFont(new Font(composite.getDisplay(), new FontData("Arial", 20, SWT.BOLD)));
		 GridData gd3 = new GridData();
		 //gd3.widthHint = 600;
		 gd3.horizontalSpan = 8;
		 labelLine.setLayoutData(gd3);
		 
		 for (int i = 0; i < requirements.size(); i++) {
			 
			 
			 final Requirement requirement = (Requirement)requirements.elementAt(i);
			 RiskAnalysis riskAnalysis = rbttoolFacade.searchRiskAnalysis(nameResource, requirement.getIdentifier(), project.getName());
			 
			 Label labelRequirement = new Label(composite, SWT.FILL_EVEN_ODD);
			 labelRequirement.setForeground(new Color(composite.getDisplay(), 9, 37, 136));
			 labelRequirement.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
			 labelRequirement.setText("Feature: "  + requirement.getName());
			 labelRequirement.setToolTipText(requirement.getName());
			 labelRequirement.setLayoutData(gd);
			 
			 for (int j = 0; j < metrics.size(); j++) {
				 final Metric metric = (Metric)metrics.elementAt(j);
				 final Label labelTopColumn1 = new Label(composite, SWT.FILL);
				 labelTopColumn1.setText(metric.getName());
				 //labelTopColumn1.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
				 labelTopColumn1.setLayoutData(gd2);
				 
			
				 //Exibe um pequeno guia sobre os valores das métricas ao passar o mouse em cima da Label q representa a métrica correspondente
				 labelTopColumn1.addMouseMoveListener(new MouseMoveListener(){
					 
					@Override
					public void mouseMove(MouseEvent e) {
						
						
						labelTopColumn1.setToolTipText("Weight = " + metric.getWeight() + "\n \n" + metric.getMetricDetails());
						
					}
					 
				 });
				 

			}
			 
			 Label labelRiskExposure = new Label(composite, SWT.FILL);
			 labelRiskExposure.setText("Risk Exposure");
			 //labelRiskExposure.setForeground(new Color(composite.getDisplay(), 9, 37, 136));
			 //labelRiskExposure.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
			 labelRiskExposure.setLayoutData(gd2);
			 GridData gdText = new GridData(GridData.CENTER);
			 gdText.widthHint = 30;
			 
			 //Flag
			 boolean resultCondition = rbttoolFacade.existsRiskAnalysis(nameResource, requirement, project);
			 
			 //Custo Cliente
			 final Text textCostClient = new Text(composite, SWT.BORDER);
			 if(resultCondition){
				 textCostClient.setText(String.valueOf(riskAnalysis.getCostClientValue()).substring(0, 1));
			 }
			 textCostClient.setLayoutData(gdText);
			 
			 
			//Custo Vendedor
			 final Text textCostVendor = new Text(composite, SWT.BORDER);
			 if(resultCondition){
				 textCostVendor.setText(String.valueOf(riskAnalysis.getCostVendorValue()).substring(0, 1));
			 }
			 textCostVendor.setLayoutData(gdText);
			 
			//Nova Funcionalidade
			 final Text textNew = new Text(composite, SWT.BORDER);
			 //textNew.setEnabled(false);
			 if (resultCondition) {
				 textNew.setText(String.valueOf(riskAnalysis.getNewValue()).substring(0, 1));
			}
			 
			 textNew.setLayoutData(gdText);
			 
			//Design
			 final Text textDesign = new Text(composite, SWT.BORDER);
			 if(resultCondition){
				 textDesign.setText(String.valueOf(riskAnalysis.getDesignValue()).substring(0, 1));
			 }
			 textDesign.setLayoutData(gdText);
			 
			//Tamanho/Mudanças
			 final Text textSize = new Text(composite, SWT.BORDER);
			 if(resultCondition){
				 textSize.setText(String.valueOf(riskAnalysis.getSizeValue()).substring(0, 1));
			 }
			 textSize.setLayoutData(gdText);
			 
			//Complexidade
			 final Text textComplexity = new Text(composite, SWT.BORDER);
			 if (resultCondition) {
				 textComplexity.setText(String.valueOf(riskAnalysis.getComplexValue()).substring(0, 1));
			}
			 textComplexity.setLayoutData(gdText);
			 
			//Dependencia
			 final Text textDependence = new Text(composite, SWT.BORDER);
			 if (resultCondition) {
				 textDependence.setText(String.valueOf(riskAnalysis.getDependenceValue()).substring(0, 1));
			}
			 textDependence.setLayoutData(gdText);
			 
			 final Text riskExposureText = new Text(composite, SWT.BORDER);
				riskExposureText.setEditable(false);
				if (resultCondition) {
					riskExposureText.setText(String.valueOf(riskAnalysis.getRiskExposure()));
				}
				riskExposureText.setLayoutData(gdText);
				
				//Com o Modify Listener, nao é necessário clicar em um botao para calcular. A ferramenta lê a string e ja processa o cálculo automaticamente
			    ModifyListener listener = new ModifyListener() {
			        public void modifyText(ModifyEvent e) {
			          valueChanged((Text) e.widget);
			        }

					private void valueChanged(Text text) {
						
						if (!text.isFocusControl())
					        return;
						
						if(text == textDependence || text == textNew || text == textSize || text == textComplexity || text == textDesign || text == textCostClient || text == textCostVendor){
							
							costClientString = textCostClient.getText();
							costVendorString = textCostVendor.getText();
							newFeatureString = textNew.getText();
							designString = textDesign.getText();
							sizeString = textSize.getText();
							complexityString = textComplexity.getText();
							dependenceString = textDependence.getText();
							
							double costClient = Double.parseDouble(costClientString);
							double costVendor = Double.parseDouble(costVendorString);
							double newFeature = Double.parseDouble(newFeatureString);
							double design = Double.parseDouble(designString);
							double size = Double.parseDouble(sizeString);
							double complexity = Double.parseDouble(complexityString);
							double dependence = Double.parseDouble(dependenceString);
							
							RiskAnalysis riskAnalysis = new RiskAnalysis();
							riskAnalysis.setCostClientValue(costClient);
							riskAnalysis.setCostVendorValue(costVendor);
							riskAnalysis.setRequirementID(requirement.getIdentifier());
							riskAnalysis.setProjectName(project.getName());
							
							double impact = riskAnalysis.calculateImpact(costClient, costVendor);
							riskAnalysis.setImpactValue(impact);
							
							riskAnalysis.setNameResource(nameResource);
							riskAnalysis.setNewValue(newFeature);
							riskAnalysis.setDesignValue(design);
							riskAnalysis.setSizeValue(size);
							riskAnalysis.setComplexValue(complexity);
							riskAnalysis.setDependenceValue(dependence);
							
							double mean = riskAnalysis.calculateMean(newFeature, design, size, complexity, dependence);
							riskAnalysis.setMean(mean);
							
							double probability = riskAnalysis.calculateProbability();
							riskAnalysis.setProbability(probability);
							
							double riskExposure = riskAnalysis.calculateRiskExposure(impact, probability);
							riskAnalysis.setRiskExposure(riskExposure);
							
							
							RBTToolFacade facade = RBTToolFacade.getInstance();

							
							if(facade.existsRiskAnalysis(nameResource, requirement, project)){
								//riskAnalysies = requirement.getRiskAnalysies();
								facade.updateRiskAnalysis(riskAnalysis, requirement, nameResource, project);
							}else{
								Vector<RiskAnalysis> ras = requirement.getRiskAnalysies();
								
								if(ras == null)
									ras = new Vector<RiskAnalysis>();
								
								ras.add(riskAnalysis);
								requirement.setRiskAnalysies(ras);
								//riskAnalysies.add(riskAnalysis);
								//requirement.setRiskAnalysies(riskAnalysies);
								facade.addRiskAnalysis(riskAnalysis, requirement, project);
							}
							
							riskExposureText.setText(String.valueOf(riskExposure));
						}

					}
			      };
			      
			      textComplexity.addModifyListener(listener);
			      textCostClient.addModifyListener(listener);
			      textCostVendor.addModifyListener(listener);
			      textDependence.addModifyListener(listener);
			      textDesign.addModifyListener(listener);
			      textNew.addModifyListener(listener);
			      textSize.addModifyListener(listener);


				 Label labelLine2 = new Label(composite, SWT.FILL);
				 labelLine2.setText("__________________________________________________________");
				 labelLine2.setFont(new Font(composite.getDisplay(), new FontData("Arial", 20, SWT.BOLD)));
				 GridData gd32 = new GridData();
				 gd32.horizontalSpan = 8;
				 labelLine2.setLayoutData(gd32);
				 
			      
		 }
		 
		 Button btExport = new Button(composite, SWT.PUSH);
		 btExport.setText("Finish");
		 btExport.setLayoutData(new GridData());
		 
		 //Ação ao pressionar o botão
		 btExport.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				//Vector para ser exportado
				Vector<RiskAnalysis> resultRiskAnalysis = new Vector<RiskAnalysis>();
				
				requirements = rbttoolFacade.searchProject(project.getName()).getRequirements();
				
				//Para cada requisito, pegue todas a análises de risco
				for (int i = 0; i < requirements.size(); i++) {
					
					Requirement requirement = (Requirement)requirements.elementAt(i);
					Vector<RiskAnalysis> riskAnalysies = requirement.getRiskAnalysies();
					
					if(riskAnalysies != null){
					
					
						for (int j = 0; j < riskAnalysies.size(); j++) {
							
							RiskAnalysis riskAnalysis = (RiskAnalysis)riskAnalysies.elementAt(j);
							
							if(riskAnalysis.getNameResource().equals(nameResource)){
							//Encontra a análise do requisito realizada pelo recurso, sai do laço 
							//for interno e incrementa valor de "i"para consultar as analises no 
							//proximo requisito
								
								resultRiskAnalysis.add(riskAnalysis);
								break;
							}
						
						}
					}
					
	
				}
				
				//Atualizando o valor da RE de cada Requirement
				for (int i = 0; i < requirements.size(); i++) {
					double riskExposure = 0;
					double impact = 0;
					double probability = 0;
					//Para cada requisito faça:
					Requirement r = (Requirement)requirements.elementAt(i);
					Vector<RiskAnalysis> riskAnalysies = r.getRiskAnalysies();
					
					for (int j = 0; j < riskAnalysies.size(); j++) {
												
							//Para cada análise faça:
							RiskAnalysis ra = (RiskAnalysis)riskAnalysies.elementAt(j);
							
								
									//Computa valor individual da risk exposure
									riskExposure = riskExposure + ra.getRiskExposure();
									impact = impact + ra.getImpactValue();
									probability = probability + ra.getProbability();
									//Incrementa num de recursos que analisaram este requisito para calcular a média
						
					}
					

					
					//Media sendo calculada...
					double riskExposureFinal = riskExposure/riskAnalysies.size();
					double impactFinal = impact/riskAnalysies.size();
					double probabilityFinal = probability/riskAnalysies.size();
					
					
					r.setRiskExposureFinal(riskExposureFinal);
					r.setImpactFinal(impactFinal);
					r.setProbabilityFinal(probabilityFinal);
					rbttoolFacade.updateRequirement(r, project);
					
					
					for (int i1 = 0; i1 < requirements.size(); i1++) {
						Requirement r1 = requirements.elementAt(i1);
						requirements.setElementAt(r1, i1);
					}
					
					Vector<Object> projects = rbttoolFacade.getProjects();
					
					for (int j = 0; j < projects.size(); j++) {
						Project p = (Project) projects.elementAt(j);
						
						if(p.getName().equals(project.getName())){
							
							p.setRequirements(requirements);
						}
					}
					
					RequirementsView rView = (RequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(RequirementsView.VIEW_ID);
					rView.tableViewer.setInput(requirements);
					
					ProjectView pView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
					pView.viewer.setInput(projects);
				}
				
				//Seleciona se quer exportar ou não
				if(MessageDialog.openQuestion(getShell(), "Export", "Do you want to export a XML file with your answers?")){
					//Dialog q seleciona onde quer salvar o xml
					ExportRiskAnalysiesDialog raDialog = new ExportRiskAnalysiesDialog(getShell());
					
					String directoryChosen = raDialog.openDialog();
					
					if(directoryChosen != null){
						String fileName = Util.generateXMLFileNameYYYYMMDD(nameResource + "_RiskAnalysis_");
						File file = new File(directoryChosen,fileName);
						RBTToolFacade.getInstance().exportRiskAnalysies(resultRiskAnalysis, file);
						MessageDialog.openInformation(composite.getParent().getShell(), "Export", "Export operation successful!");
						
					} 
				}
				//Fecha a janela de analise de riscos
				composite.getParent().getShell().close();
				
				
			}
			 
		 });
		 
		 //Seta numero de colunas
			GridLayout layout = new GridLayout(8, true);
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

}
