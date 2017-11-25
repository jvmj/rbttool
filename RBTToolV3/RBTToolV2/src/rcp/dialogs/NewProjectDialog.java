package rcp.dialogs;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import essentials.Project;
import facade.RBTToolFacade;

public class NewProjectDialog extends Dialog {
	
	Vector<String> resourcesTest = new Vector<String>();
	
	
	private List t;
	
	private Text nameText;

	private Text softwareText;

	private Text startDateText;

	private Text dueDateText;
	
	private Text mainDescriptionText;

	private Text requirementBaseLineText;

	private Text resourcesText;
	
	private String name;

	private String software;

	private String startDate;

	private String dueDate;
	
	private String mainDescription;
	
	private String requirementBaseLine;

	private Vector<String> resourcesString;

	public NewProjectDialog(Shell parentShell) {
		super(parentShell);

	}
	
	@Override
	protected void configureShell(Shell newShell) {

		super.configureShell(newShell);
		newShell.setText("New Project");
	  
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.FILL_EVEN_ODD);
		
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		GridData gd = new GridData(GridData.FILL, GridData.FILL,true, false);
		gd.horizontalSpan = 2;
		
		GridData gridDataDescription = new GridData(GridData.FILL, GridData.FILL, true,
				false);
		gridDataDescription.horizontalSpan = 2;

		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("&Name: ");
		nameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		nameText = new Text(composite, SWT.BORDER);
		//nameText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
			//	true, false));
		nameText.setLayoutData(gd);
		

		/*Label softwareLabel = new Label(composite, SWT.NONE);
		softwareLabel.setText("&Software:");
		softwareLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		softwareText = new Text(composite, SWT.BORDER);
		//softwareText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
			//	true, false));
		softwareText.setLayoutData(gd);*/

		Label startDateLabel = new Label(composite, SWT.NONE);
		startDateLabel.setText("&Start Date:");
		startDateLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		startDateText = new Text(composite, SWT.BORDER);
		//startDateText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
			//	true, false));
		startDateText.setLayoutData(gd);

		
		Label dueDateLabel = new Label(composite, SWT.NONE);
		dueDateLabel.setText("&Due Date: ");
		dueDateLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		dueDateText = new Text(composite, SWT.BORDER);
		//dueDateText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
			//	true, false));
		dueDateText.setLayoutData(gd);
		Label mainDescriptionLabel = new Label(composite, SWT.NONE);
		mainDescriptionLabel.setText("&Main Description: ");
		mainDescriptionLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		mainDescriptionText = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);

		gridDataDescription.widthHint = convertHeightInCharsToPixels(20);
		gridDataDescription.heightHint = 100;
		mainDescriptionText.setLayoutData(gridDataDescription);
		
		
		Label requirementBaseLineLabel = new Label(composite, SWT.NONE);
		requirementBaseLineLabel.setText("&Requirement Baseline: ");
		requirementBaseLineLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

		requirementBaseLineText = new Text(composite, SWT.BORDER);
		//requirementBaseLineText.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
			//	true, false));
		requirementBaseLineText.setLayoutData(gd);
		
		
		Label resourcesLabel = new Label(composite, SWT.NONE);
		resourcesLabel.setText("&Resources: ");
		resourcesLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));

/*		resourcesText = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gridData2 = new GridData(GridData.FILL, GridData.FILL, true,
				false);
		gridData2.widthHint = convertHeightInCharsToPixels(20);
		gridData2.heightHint = 100;
		resourcesText.setLayoutData(gridData);*/
		

		t = new List(composite, SWT.NONE);
		GridData gridData2 = new GridData(GridData.FILL, GridData.FILL, true,
				false);
		gridData2.horizontalSpan = 2;
		gridData2.widthHint = convertHeightInCharsToPixels(20);
		gridData2.heightHint = 100;
		t.setLayoutData(gridDataDescription);
		
/*		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		t.setLayoutData(gd);*/
		
		Label l = new Label(composite, SWT.NONE);
		l.setText("&");
		l.setLayoutData(new GridData(GridData.END, GridData.CENTER,
				false, false));
		
		Button bAddResource = new Button(composite, SWT.PUSH);
		bAddResource.setText("Add Resource");
	    GridData g = new GridData();
    
	    
	    
	    bAddResource.setLayoutData(g);
	    
		Button bRemoveResource = new Button(composite, SWT.PUSH);
		bRemoveResource.setText("Remove Resource");
		bRemoveResource.setLayoutData(g);
		
		
		bAddResource.addSelectionListener(new SelectionListener(){
			final Shell dialog = new Shell(SWT.DIALOG_TRIM);
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				NewResourceDialog dialog = new NewResourceDialog(this.dialog.getShell());
				if(dialog.open() == Window.OK){
					t.add(dialog.getNameResourceString());
//					TableItem item = new TableItem(t,SWT.NONE);
//					item.setText(dialog.getNameResourceString());
					resourcesTest.addElement(dialog.getNameResourceString());
				}
			}
			
		});
		

	    bRemoveResource.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {

	        	resourcesTest.remove(t.getSelectionIndex());
	        	t.remove(t.getSelectionIndex());
	          

	         }
	      });
		
		return composite;
	}

	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		name = nameText.getText();
		startDate = startDateText.getText();
		dueDate = dueDateText.getText();
		mainDescription = mainDescriptionText.getText();
		requirementBaseLine = requirementBaseLineText.getText();
		//resourcesString = resourcesText.getText();
		resourcesString = resourcesTest;
			
		RBTToolFacade rbtToolFachada = RBTToolFacade.getInstance();
		Project newProject = new Project(name,mainDescription);

		
		if (name.equals("")) {
			MessageDialog.openError(dialogArea.getShell(), "Invalid Name",
					"Name field must not be blank.");
			return;
		}else if(resourcesString.size() == 0){
			MessageDialog.openError(dialogArea.getShell(), "Where's the resources?", "You must add at least one resource!");
			return;
		}
		
		else if(rbtToolFachada.exists(newProject)){
			MessageDialog.openError(dialogArea.getShell(), "Error", "The project " + name + " already exists!");
			return;
			
		}
		
		super.okPressed();
		
	}
	



	public String getName() {
		return name;
	}

	public String getSoftware() {
		return software;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getRequirementBaseLine() {
		return requirementBaseLine;
	}

	public Vector<String> getResourcesString() {
		return resourcesString;
	}

	public String getMainDescription() {
		return mainDescription;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setMainDescription(String mainDescription) {
		this.mainDescription = mainDescription;
	}

	public void setRequirementBaseLine(String requirementBaseLine) {
		this.requirementBaseLine = requirementBaseLine;
	}

	public void setResourcesString(Vector<String> resourcesString) {
		this.resourcesString = resourcesString;
	}
	
	
	
	
	

}
