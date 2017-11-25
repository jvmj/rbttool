package util;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class SWTUtil {

	
    public static boolean runWizard(IWizard wizard) {
        Shell activeShell = Display.getCurrent().getActiveShell();
        WizardDialog dialog = new WizardDialogNoResizable(activeShell, wizard);

        try {
            dialog.create();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        centerDialog(dialog.getShell());
        return dialog.open() == WizardDialog.OK;
    }
    
	public static void centerDialog(Shell shell) {
		Monitor primary = shell.getDisplay().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}
	
	/**
     * @see Dialog#convertWidthInCharsToPixels(FontMetrics, int)
     */
    public static int convertWidthInCharsToPixels(Control control, int chars) {
        GC gc = new GC(control);
        gc.setFont(control.getFont());
        FontMetrics fFontMetrics = gc.getFontMetrics();
        return Dialog.convertWidthInCharsToPixels(fFontMetrics, chars);
    }

    /**
     * Returns the table height based on the number of rows.
     * @param table the table.
     * @param rows the number of rows.
     * @return the table height
     */
    public static int getTableHeightHint(Table table, int rows) {
        if (table.getFont().equals(JFaceResources.getDefaultFont()))
            table.setFont(JFaceResources.getDialogFont());
        int result = table.getItemHeight() * rows + table.getHeaderHeight();
        if (table.getLinesVisible())
            result += table.getGridLineWidth() * (rows - 1);
        return result;
    }
    
    public static Composite createComposite(Composite parent) {
        Composite toReturn = new Composite(parent, SWT.NULL);
        toReturn.setLayout(createGridLayout());
        toReturn.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
                | GridData.FILL_HORIZONTAL));

        return toReturn;
    }

    public static Composite createComposite(Composite parent, int numColumns) {
        Composite toReturn = createComposite(parent);
        ((GridLayout) toReturn.getLayout()).numColumns = numColumns;

        return toReturn;
    }

    public static Label createLine(Composite parent) {
        Label toReturn = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
                | SWT.BOLD);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = ((GridLayout) parent.getLayout()).numColumns;
        toReturn.setLayoutData(gridData);

        return toReturn;
    }

    public static GridLayout createGridLayout() {
        GridLayout toReturn = new GridLayout();
        toReturn.numColumns = 2;
        toReturn.makeColumnsEqualWidth = false;
        toReturn.marginWidth = 0;
        toReturn.marginHeight = 0;

        return toReturn;
    }

    public static GridLayout createGridLayout(int numColumns) {
        GridLayout toReturn = new GridLayout();
        toReturn.numColumns = numColumns;
        toReturn.makeColumnsEqualWidth = false;
        toReturn.marginWidth = 0;
        toReturn.marginHeight = 0;

        return toReturn;
    }

    public static GridLayout createGridLayout(int numColumns,
            Composite composite) {
        GridLayout toReturn = new GridLayout();
        toReturn.numColumns = numColumns;
        toReturn.makeColumnsEqualWidth = false;
        toReturn.marginWidth = 0;
        toReturn.marginHeight = 0;

        composite.setLayout(toReturn);
        return toReturn;
    }

    public static Label createLabel(Composite parent, String text) {
        return createLabel(parent, text, SWT.NONE);
    }

    public static Label createLabel(Composite parent, String text, int style) {
        Label toReturn = new Label(parent, style);
        if (text != null) {
            toReturn.setText(text);
        }
        toReturn.setFont(parent.getFont());

        return toReturn;
    }

    public static Combo createCombo(Composite parent) {
        Combo toReturn = new Combo(parent, SWT.READ_ONLY);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        data.horizontalSpan = 1;
        toReturn.setLayoutData(data);

        return toReturn;
    }

    public static Label createValueLabel(Composite parent, String text) {
        createLabel(parent, text);

        return createLabel(parent, null);
    }

    public static Button createButton(Composite parent, String text) {
        Button toReturn = new Button(parent, SWT.PUSH);
        toReturn.setFont(parent.getFont());
        toReturn.setText(text);
        toReturn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        return toReturn;
    }

    public static Table createTable(Composite parent) {
        Table toReturn = new Table(parent, SWT.H_SCROLL | SWT.V_SCROLL
                | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        data.heightHint = toReturn.getItemHeight();
        data.horizontalSpan = 1;
        toReturn.setLayoutData(data);

        return toReturn;
    }

    public static Table createTableMultiSelection(Composite parent) {
        Table toReturn = new Table(parent, SWT.H_SCROLL | SWT.V_SCROLL
                | SWT.FULL_SELECTION | SWT.BORDER | SWT.MULTI);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        data.heightHint = toReturn.getItemHeight();
        data.horizontalSpan = 1;
        toReturn.setLayoutData(data);

        return toReturn;
    }

    public static TableColumn createTableColumn(Table table, String text) {
        TableColumn toReturn = new TableColumn(table, SWT.NONE);
        toReturn.setText(text);

        return toReturn;
    }

    public static TableItem createTableItem(Table table, Image image, String s) {
        TableItem toReturn = new TableItem(table, SWT.NONE);
        toReturn.setText(s);
        if (image != null) {
            toReturn.setImage(image);
        }

        return toReturn;
    }

    public static Text createText(Composite parent) {
        Text toReturn = new Text(parent, SWT.BORDER);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        toReturn.setLayoutData(data);

        return toReturn;
    }

    public static Group createTitledGroup(Composite parent, String title) {
        Group toReturn = new Group(parent, SWT.SHADOW_NONE);
        toReturn.setText(title);
        toReturn.setLayout(new GridLayout());
        toReturn.setLayoutData(new GridData(GridData.FILL_BOTH));
        return toReturn;
    }

    public static Group createTitledGroup(Composite parent, String title,
            int numColumns) {
        Group toReturn = new Group(parent, SWT.SHADOW_NONE);
        toReturn.setText(title);
        GridLayout layout = createGridLayout(numColumns);
        toReturn.setLayout(layout);
        return toReturn;
    }

    public static Group createGroup(Composite parent) {
        Group toReturn = new Group(parent, SWT.SHADOW_NONE);
        return toReturn;
    }

    public static List createList(Composite parent) {
        return new List(parent, SWT.BORDER | SWT.SINGLE);
    }

    private static class WizardDialogNoResizable extends WizardDialog {

        public WizardDialogNoResizable(Shell parentShell, IWizard newWizard) {
            super(parentShell, newWizard);
            setShellStyle(SWT.APPLICATION_MODAL | SWT.CLOSE);
        }
    }
}
