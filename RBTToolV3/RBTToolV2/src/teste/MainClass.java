package teste;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainClass {

  public static void main(String[] a) {
    Display display = new Display();

    // Create the main window
    Shell shell = new Shell(display);

    Text fahrenheit = new Text(shell, SWT.BORDER);
    fahrenheit.setData("Type a temperature in Fahrenheit");
    fahrenheit.setBounds(20, 20, 100, 20);

    fahrenheit.addModifyListener(new ModifyListener(){
      public void modifyText(ModifyEvent event) {
        // Get the widget whose text was modified
        Text text = (Text) event.widget;
        String string = text.getText();
        System.out.println(string);
      }
    });
    
    shell.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
