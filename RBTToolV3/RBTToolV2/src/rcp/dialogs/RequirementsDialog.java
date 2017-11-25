package rcp.dialogs;

import java.util.Arrays;
import java.util.Vector;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import essentials.Requirement;
import facade.RBTToolFacade;

public class RequirementsDialog extends ElementListSelectionDialog {
	
	private static class ListLabelProvider implements ILabelProvider {

		public Image getImage(Object element) {
			return null;
		}

		public String getText(Object element) {
			Requirement requirement = (Requirement) element;
			String text = requirement.getRiskExposureFinal() + " - "  + requirement.getName();
			return text;
		}

		public void addListener(ILabelProviderListener listener) {}

		public void dispose() {}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {}
		
	}
	
	public RequirementsDialog(String projectName) {
		this(Display.getCurrent().getActiveShell(), new ListLabelProvider(), projectName);
	}
	
	public RequirementsDialog(Shell parent, ILabelProvider renderer, String projectName) {
		super(parent, renderer);
		Vector<Requirement> requirements = RBTToolFacade.getInstance().getRequirements(projectName);
		
		
		Requirement[] requirementsElements = (Requirement[]) requirements.toArray(new Requirement[requirements.size()]);
		
		
		Arrays.sort(requirementsElements);
		
		setElements(requirementsElements);
		
		
		setTitle("Requirement List of project " + projectName);
		
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CLIENT_ID, "Close", true);
	}
	
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CLIENT_ID) {
			close();
		}
		super.buttonPressed(buttonId);
	}
	
}
