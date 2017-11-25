package teste;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Example program demonstrating how to dynamically add pages to a JFace Wizard.
 *
 * @author Shawn Spiars 
 * 
 */ 
public class DynamicWizardPageExample {

      private static class MyWizard extends Wizard {
            public MyWizard() {
                  super();
                  setForcePreviousAndNextButtons(true);
            }

            public void addPages() {
                  addPage(new StartingWizardPage());
            }

            public boolean performFinish() {
                  return true;
            }

            @Override
            public IWizardPage getNextPage(IWizardPage page) {
                  IWizardPage nextPage = super.getNextPage(page);
                  
                  //TODO - create logic here to skip pages that you
                  //have added to the framework, but no longer wish to display
                  
                  return nextPage;
            }

      };

      private static class StartingWizardPage extends WizardPage {
            private Button firstPageButton;
            private Button secondPageButton;
            private Button thirdPageButton;

            protected StartingWizardPage() {
                  super("startingPage");
                  setTitle("Starting Page");
              setMessage("Select the desired pages and press the Next button.");
            }

            public void createControl(Composite parent) {
                  Composite composite = new Composite(parent, SWT.NONE);
              GridLayout layout = new GridLayout(1, false);
              layout.verticalSpacing = 12;
              composite.setLayout(layout);
              composite.setLayoutData(new GridData(GridData.FILL_BOTH));
              
              firstPageButton = new Button(composite, SWT.CHECK);
              firstPageButton.setText("Page One");
              firstPageButton.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {
                              Wizard wizard = (Wizard) getWizard();
                              wizard.addPage(new MyWizardPage("Page One"));
                              getContainer().updateButtons();
                        }
                  });
              
              secondPageButton = new Button(composite, SWT.CHECK);
              secondPageButton.setText("Page Two");
              secondPageButton.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {

                              Wizard wizard = (Wizard) getWizard();
                              wizard.addPage(new MyWizardPage("Page Two"));
                              getContainer().updateButtons();
                        }
                  });
              
              thirdPageButton = new Button(composite, SWT.CHECK);
              thirdPageButton.setText("Page Three");
              thirdPageButton.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {
                              Wizard wizard = (Wizard) getWizard();
                              wizard.addPage(new MyWizardPage("Page Three"));
                              getContainer().updateButtons();
                        }
                  });
              
              setControl(composite);
            }
            
      }
      
      private static class MyWizardPage extends WizardPage {
            protected MyWizardPage(String pageName) {
                  super(pageName);
                  setTitle(pageName);
            }

            public void createControl(Composite parent) {
                  Composite composite = new Composite(parent, SWT.NONE);
                  setControl(composite);
            }
      }

      public static void main(String[] args) {
            Display display = new Display();

            final Shell shell = new Shell(display);
            shell.setLayout(new FillLayout());

            Button b = new Button(shell, SWT.PUSH);
            b.setText("Press here to open wizard");
            b.addSelectionListener(new SelectionAdapter() {
                  public void widgetSelected(SelectionEvent e) {
                        WizardDialog dialog = new WizardDialog(shell, new MyWizard());
                        dialog.open();
                  }
            });

            shell.open();

            while (!shell.isDisposed()) {
                  if (!display.readAndDispatch())
                        display.sleep();
            }

            display.dispose();
      }
}
