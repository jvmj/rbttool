package rcp.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import rcp.views.ProjectView;
import rcp.views.TestCasesView;
import rcp.views.TestIterationsView;
import rcp.views.TestPlanDescriptionView;
import rcp.views.TestPlanView;

public class PerspectiveTest implements IPerspectiveFactory {

	public static final String ID = "RCP.testPerspective"; //$NON-NLS-1$
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		final String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		layout.addStandaloneView(PerspectiveView.ID,  false, IPageLayout.TOP, 0.1f, editorArea);
		
        layout.addStandaloneView(ProjectView.VIEW_ID,  false, IPageLayout.LEFT, 0.2f, editorArea);
        layout.addStandaloneView(TestIterationsView.VIEW_ID,  false, IPageLayout.RIGHT, 0.4f, TestPlanView.VIEW_ID);
        layout.addStandaloneView(TestCasesView.VIEW_ID,  false, IPageLayout.BOTTOM, 0.4f, TestIterationsView.VIEW_ID);
        
        layout.addStandaloneView(TestPlanView.VIEW_ID,  false, IPageLayout.BOTTOM, 0.2f, ProjectView.VIEW_ID);
        layout.addStandaloneView(TestPlanDescriptionView.VIEW_ID,  false, IPageLayout.BOTTOM, 0.5f, TestPlanView.VIEW_ID);
        
        
 

	}

}
