package rcp.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class ImportRiskAnalysisDialog {

	private Shell shell;

	public ImportRiskAnalysisDialog(Shell shell) {
		super();
		this.shell = shell;
	}
	
	public String openDialog(){
		
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		dialog.setText("Import");
		dialog.setFilterNames(new String[] { "XML Files", "All Files (*.*)" });
	    dialog.setFilterExtensions(new String[] { "*.xml", "*.*" }); // Windows
	                                    // wild
	                                    // cards
	    dialog.setFilterPath("C:\\"); // Windows path
	    dialog.setFileName("*.xml");	    
		String file = dialog.open();		
		
		return file;
	}
	
	
}
