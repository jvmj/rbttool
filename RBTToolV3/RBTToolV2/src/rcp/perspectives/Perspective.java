package rcp.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import rcp.views.DetailsView;
import rcp.views.IdentifiedRisksView;
import rcp.views.ProjectView;
import rcp.views.QuestionnairesView;
import rcp.views.RequirementsView;


public class Perspective implements IPerspectiveFactory {
	
	public static final String ID = "RCP.mainPerspective"; //$NON-NLS-1$

	public void createInitialLayout(final IPageLayout layout) {
		
		final String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		
		layout.addStandaloneView(PerspectiveView.ID,  false, IPageLayout.TOP, 0.1f, editorArea);
		
        layout.addStandaloneView(ProjectView.VIEW_ID,  false, IPageLayout.LEFT, 0.6f, editorArea);
        
        layout.addStandaloneView(IdentifiedRisksView.VIEW_ID,  false, IPageLayout.BOTTOM, 0.5f, ProjectView.VIEW_ID);
        layout.addStandaloneView(RequirementsView.VIEW_ID,  false, IPageLayout.RIGHT, 0.25f, ProjectView.VIEW_ID);
        layout.addStandaloneView(DetailsView.VIEW_ID,  false, IPageLayout.LEFT, 0.25f, IdentifiedRisksView.VIEW_ID);
        layout.addStandaloneView(QuestionnairesView.VIEW_ID,  false, IPageLayout.BOTTOM, 0.4f, ProjectView.VIEW_ID);
        
        //layout.addPerspectiveShortcut("RCP.mainPerspective");
        //layout.addPerspectiveShortcut("RCP.testPerspective");
        
 
              
	}
	

}
