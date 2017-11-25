package rcp.actions;

import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import rcp.Activator;
import rcp.dialogs.NewQuestionnaireDialog;
import rcp.views.ProjectView;
import rcp.views.QuestionnairesView;
import essentials.Project;
import essentials.Questionnaire;
import facade.RBTToolFacade;

public class NewQuestionnaireAction extends Action implements
		ISelectionListener {
	
	public final static String ID = "actions.newQuestionaireAction";
	private final IWorkbenchWindow window;
	private StructuredSelection selection;
	
	private Vector<Questionnaire> questionnaires = new Vector<Questionnaire>();
	
	public NewQuestionnaireAction(IWorkbenchWindow window){
		this.window = window;
		this.setId(ID);
		setText("&New Questionnaire");
		setToolTipText("New questionnaire");
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
	
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/new_projectToolbar.gif");
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		// TODO Auto-generated method stub
		if (incoming instanceof IStructuredSelection) {
			selection = (StructuredSelection) incoming;
			setEnabled(selection.size() == 1
					&& selection.getFirstElement() instanceof Project);
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
		//p = new Project(p.getName(),p.getMainDescription());
				
		NewQuestionnaireDialog dialog = new NewQuestionnaireDialog(window.getShell(), p.getName());
		
		questionnaires = p.getQuestionnaires();
		
		if(questionnaires == null){
			questionnaires = new Vector<Questionnaire>();
		}
		
		//Abre o dialog
		int code = dialog.open();
		
		//Ação para se clicar no botão OK
		if(code == Window.OK){
			
			Questionnaire questionnaire = new Questionnaire();
			questionnaire.setName(dialog.getNameString());
			
			RBTToolFacade.getInstance().addQuestionnaire(questionnaire, p);
			
			questionnaires.add(questionnaire);
			p.setQuestionnaires(questionnaires);
			
			//Chama a View
			QuestionnairesView qView = (QuestionnairesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(QuestionnairesView.VIEW_ID);
			//Chama a TableViewer da View chamada e adiciona um item a tabela
			qView.viewer.setInput(questionnaires);
		}
		
/*		int code = questionnaireDialog.open();
		if (code == Window.OK) {
			
			IWorkbenchPage page = window.getActivePage();
			Questionnaire questionnaire = new Questionnaire();
			questionnaire.setName(questionnaireDialog.getNameString());*/
			
			//FALTA CADASTRAR O QUESTIONARIO VIA FACHADA
			
			
			//Por imposiçao da API, sempre ao criar um editor eh preciso ter um editor input associado
			/*Repare que o MyEditorInput está recebendo o nome do questionario como argumento. 
			 * Isto é feito porque o editor que vamos usar possui uma seçao para gerenciar as questoes. 
			 * Logo, nada mais natural do que usar o nome do questionario para associar a questao de maneira correta*/
/*			MyEditorInput myEditorInput = new MyEditorInput(questionnaireDialog.getNameString());
			try {
				//Abre o editor
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(myEditorInput, QuestionnaireEditor.ID);

				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			
		}
		
	}


