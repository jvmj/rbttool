package rcp.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.dialogs.ChooseResourceAnswerQuestionnaireDialog;
import rcp.views.ProjectView;
import rcp.views.QuestionnairesView;
import essentials.Project;
import essentials.Questionnaire;


public class ChooseResourceAnwerQuestionnaireAction extends Action implements
		ISelectionListener {
	
	private final IWorkbenchWindow window;
	private StructuredSelection selection;
	
	
	
	public ChooseResourceAnwerQuestionnaireAction(IWorkbenchWindow window) {
		super();
		this.window = window;
		setText("Answer this questionnaire");
		
	}


	@Override
	public ImageDescriptor getImageDescriptor() {
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/answer.gif");
	}
	

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			selection = (StructuredSelection) incoming;
			setEnabled(selection.size() == 1
					&& selection.getFirstElement() instanceof Questionnaire);
		} else {
			// Other selections, for example containing text or of other kinds.
			setEnabled(false);
		}

	}
	
	@Override
	public void run() {
		
		//Chama a View
		ProjectView myProjectView = (ProjectView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProjectView.VIEW_ID);
		//Chama a ListViewer da View chamada
		ListViewer list = myProjectView.viewer;
		
		//Pega o projeto selecionado na list
		Project p = (Project) ((IStructuredSelection)list.getSelection()).getFirstElement();
		
		//Chama a View
		QuestionnairesView myQView = (QuestionnairesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(QuestionnairesView.VIEW_ID);
		//Chama a ListViewer da View chamada
		ListViewer qlist = myQView.viewer;
		
		//Pega o questionario selecionado na list
		Questionnaire q = (Questionnaire) ((IStructuredSelection)qlist.getSelection()).getFirstElement();
		
		//Só abrir quanto tiver algum questionário selecionado
		if(q != null){
			new ChooseResourceAnswerQuestionnaireDialog(window.getShell(),p,q).open();
		}
		
	}

}
