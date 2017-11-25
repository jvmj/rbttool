package teste;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SampleDialog extends Dialog { 
	public static void main(String[] args) { 
	Display display = new Display(); 
	Shell shell = new Shell(display); 
	SampleDialog dlg = new SampleDialog(shell); 
	dlg.open(); 
	display.dispose(); 
	} 
	public SampleDialog(Shell shell) { 
	super(shell); 
	} 
	protected Control createDialogArea(Composite parent) { 
	Composite composite = new Composite(parent, SWT.NONE); 
	composite.setLayout(new GridLayout()); 
	ScrolledComposite c1 = new ScrolledComposite(composite, SWT.BORDER | 
	SWT.H_SCROLL | SWT.V_SCROLL); 
	c1.setLayoutData(new GridData(700,200)); 
	Button b1 = new Button(c1, SWT.PUSH); 
	b1.setText("fixed size button"); 
	b1.setSize(1200, 1200); 
	c1.setContent(b1); 
	return composite; 
	} 
	} 
