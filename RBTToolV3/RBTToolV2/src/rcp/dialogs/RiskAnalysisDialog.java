package rcp.dialogs;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import essentials.Metric;
import essentials.Project;
import essentials.Requirement;
import essentials.RiskAnalysis;
import facade.RBTToolFacade;

public class RiskAnalysisDialog extends Dialog {
	
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

	public RiskAnalysisDialog(Shell parentShell, Project p, String nameResource) {
		super(parentShell);
		this.project = p;
		this.nameResource = nameResource;
	}

	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		

		//ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		
		Composite composite = new Composite(parent, SWT.V_SCROLL);
		
		

		
		
		GridLayout layout = new GridLayout(9, true);
		composite.setLayout(layout);
		GridData gd = new GridData(GridData.FILL, GridData.FILL,true, false);
		
		
		final RBTToolFacade rbttoolFacade = RBTToolFacade.getInstance();
		Vector<Metric> metrics = rbttoolFacade.getMetrics();
		requirements = rbttoolFacade.searchProject(project.getName()).getRequirements();
		
		
		
		Label labelFeatures = new Label(composite, SWT.FILL);
		labelFeatures.setText("Features");
		labelFeatures.setLayoutData(gd);
		
		 for (int i = 0; i < metrics.size(); i++) {
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
		}
		 
		 GridData gd2 = new GridData(GridData.FILL, GridData.FILL,true, false);
		 //gd2.heightHint = 30;
		//gd2.widthHint = 90;
		 
		 
/*		 Label labelImpact = new Label(composite, SWT.FILL);
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
		 
		 Label labelRiskExposure = new Label(composite, SWT.FILL);
		 labelRiskExposure.setText("Risk Exposure");
		 labelRiskExposure.setForeground(new Color(composite.getDisplay(), 9, 37, 136));
		 labelRiskExposure.setFont(new Font(composite.getDisplay(), new FontData("Arial", 10, SWT.BOLD)));
		 labelRiskExposure.setLayoutData(gd2);
		 
		 
		 Label labelLine = new Label(composite, SWT.FILL);
		 labelLine.setText("__________________________________________________________________________________________________________________________________________________________________________________________________________________");
		 GridData gd3 = new GridData();
		 gd3.horizontalSpan = 9;
		 labelLine.setLayoutData(gd3);
		 
		 for (int i = 0; i < requirements.size(); i++) {
			 
			 
			 final Requirement requirement = (Requirement)requirements.elementAt(i);
			 RiskAnalysis riskAnalysis = rbttoolFacade.searchRiskAnalysis(nameResource, requirement.getIdentifier(), project.getName());
			 
			 Label labelRequirement = new Label(composite, SWT.FILL);
			 labelRequirement.setText(requirement.getName());
			 labelRequirement.setLayoutData(gd);
			 
			 GridData gdText = new GridData();
			 gdText.widthHint = 30;
			 
			 //Flag
			 boolean resultCondition = rbttoolFacade.existsRiskAnalysis(nameResource, requirement, project);
			 
			 //Custo Cliente
			 final Text textCostClient = new Text(composite, SWT.BORDER);
			 if(resultCondition){
				 textCostClient.setText(String.valueOf(riskAnalysis.getCostClientValue()));
			 }
			 textCostClient.setLayoutData(gdText);
			 
			 
			//Custo Vendedor
			 final Text textCostVendor = new Text(composite, SWT.BORDER);
			 if(resultCondition){
				 textCostVendor.setText(String.valueOf(riskAnalysis.getCostVendorValue()));
			 }
			 textCostVendor.setLayoutData(gdText);
			 
			//Nova Funcionalidade
			 final Text textNew = new Text(composite, SWT.BORDER);
			 if (resultCondition) {
				 textNew.setText(String.valueOf(riskAnalysis.getNewValue()));
			}
			 textNew.setLayoutData(gdText);
			 
			//Design
			 final Text textDesign = new Text(composite, SWT.BORDER);
			 if(resultCondition){
				 textDesign.setText(String.valueOf(riskAnalysis.getDesignValue()));
			 }
			 textDesign.setLayoutData(gdText);
			 
			//Tamanho/Mudanças
			 final Text textSize = new Text(composite, SWT.BORDER);
			 if(resultCondition){
				 textSize.setText(String.valueOf(riskAnalysis.getSizeValue()));
			 }
			 textSize.setLayoutData(gdText);
			 
			//Complexidade
			 final Text textComplexity = new Text(composite, SWT.BORDER);
			 if (resultCondition) {
				 textComplexity.setText(String.valueOf(riskAnalysis.getComplexValue()));
			}
			 textComplexity.setLayoutData(gdText);
			 
			//Dependencia
			 final Text textDependence = new Text(composite, SWT.BORDER);
			 if (resultCondition) {
				 textDependence.setText(String.valueOf(riskAnalysis.getDependenceValue()));
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
								
								facade.updateRiskAnalysis(riskAnalysis, requirement, nameResource, project);
							}else{
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
				 labelLine2.setText("__________________________________________________________________________________________________________________________________________________________________________________________________________________");
				 GridData gd32 = new GridData();
				 gd32.horizontalSpan = 9;
				 labelLine2.setLayoutData(gd32);
				 
			      
		 }
		
		 //sc.setContent(composite);
		 //sc.setSize(640, 480);
		    //sc.setExpandHorizontal(true);
		    //sc.setExpandVertical(true);
		    
		return composite;
	}
}
