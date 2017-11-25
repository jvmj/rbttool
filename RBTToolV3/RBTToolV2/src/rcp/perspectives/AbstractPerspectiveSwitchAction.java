package rcp.perspectives;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * @author Tom Seidel tom.seidel@spiritlink.de
 *
 */
public abstract class AbstractPerspectiveSwitchAction extends Action implements IPerspectiveListener {
    
   
    /**
     * Instanciates an action and registers a perspective-listener
     * @param text
     */
    public AbstractPerspectiveSwitchAction(String text) {
        super(text,IAction.AS_RADIO_BUTTON);
        setId(text);
        IWorkbench workbench = PlatformUI.getWorkbench();
        setText(workbench.getPerspectiveRegistry().findPerspectiveWithId(getId()).getLabel());
        setImageDescriptor(workbench.getPerspectiveRegistry().findPerspectiveWithId(getId()).getImageDescriptor());
        workbench.getActiveWorkbenchWindow().addPerspectiveListener(this);
    }
    /**
     * Sets the perspective
     */
    public void run() {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        page.setPerspective(
                PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(getId()));
    }
    
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.IPerspectiveListener#perspectiveActivated(org.eclipse.ui.IWorkbenchPage, org.eclipse.ui.IPerspectiveDescriptor)
     */
    public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
        if (perspective.getId().equals(getId())) {
            setChecked(true);
        } else {
            setChecked(false);
        }
        
    }
    /* (non-Javadoc)
     * @see org.eclipse.ui.IPerspectiveListener#perspectiveChanged(org.eclipse.ui.IWorkbenchPage, org.eclipse.ui.IPerspectiveDescriptor, java.lang.String)
     */
    public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {
        // do nothing.
    }

}
