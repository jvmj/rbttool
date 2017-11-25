package rcp.perspectives;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.part.ViewPart;

import rcp.Activator;

/**
 * @author Tom Seidel tom.seidel@spiritlink.de
 *
 */
public class PerspectiveView extends ViewPart {
	public static final String ID = "RCP.PerspectiveView"; //$NON-NLS-1$
    
    
    private Image headerImage = ImageDescriptor.createFromURL(
                FileLocator.find(Activator.getDefault().getBundle(), 
            new Path("icons/Header.png"),null)).createImage(); //$NON-NLS-1$
    
    
	
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
        Color backgroundColor = new Color(null,new RGB(226,255,211));
		GridLayout layout = new GridLayout(5,false);
        layout.marginLeft = 10;
        layout.marginHeight = 10;
        parent.setLayout(layout);
        parent.setBackground(backgroundColor);
        ToolBar toolbar1 = new ToolBar(parent,SWT.FLAT);
        toolbar1.setBackground(new Color(null,new RGB(226,255,211)));
        toolbar1.setLayoutData(new GridData(SWT.BEGINNING,SWT.BEGINNING,false,false));
        ToolBarManager manager1 = new ToolBarManager(toolbar1);
        manager1.add(new SwitchToPerspectiveMain());
        manager1.update(true);
        
        Composite comp1 = new Composite(parent,SWT.NONE);
        comp1.setBackground(backgroundColor);
        GridData gd = new GridData(SWT.BEGINNING,SWT.BEGINNING, false,false);
        gd.widthHint = 20;
        comp1.setLayoutData(gd);
        
        ToolBar toolbar2 = new ToolBar(parent,SWT.FLAT);
        toolbar2.setBackground(new Color(null,new RGB(226,255,211)));
        toolbar2.setLayoutData(new GridData(SWT.BEGINNING,SWT.BEGINNING,false,false));
        
        ToolBarManager manager2 = new ToolBarManager(toolbar2);
        manager2.add(new SwitchToPerspectiveTest());
        manager2.update(true);
        
        Composite comp2 = new Composite(parent,SWT.NONE);
        comp2.setBackground(backgroundColor);
        gd = new GridData(SWT.FILL,SWT.BEGINNING, true,false);
        comp2.setLayoutData(gd);
        
        Label headerImageLabel = new Label(parent,SWT.NONE);
        headerImageLabel.setImage(this.headerImage);
        gd = new GridData(SWT.BEGINNING,SWT.CENTER,false,false);
        headerImageLabel.setLayoutData(gd);

        
         
        
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		
	}
    
    public void dispose() {
        this.headerImage.dispose();
        super.dispose();
    }
}