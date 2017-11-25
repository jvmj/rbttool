package rcp.dialogs;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

public class ExportRiskAnalysiesDialog {

/*	private String directoryChosen;*/
	private Shell shell;

	public ExportRiskAnalysiesDialog(Shell shell) {
		super();
		this.shell = shell;
	}
	
	public String openDialog(){
		/*Shell shell = new Shell(display);
		shell.open();*/
		DirectoryDialog dialog = new DirectoryDialog(shell);
		dialog.setText("Export Dialog");
		dialog.setMessage("Please choose a directory to export your xml file generated:");
		dialog.setFilterPath("C:\\"); // Windows specific

		String directory = dialog.open();
		
		
		return directory;

		/*while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();*/
	}
	
	
}
