package rcp.providers;

import java.util.Vector;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TestCaseProceduresContentProvider implements IStructuredContentProvider {

	public Object[] getElements(Object inputElement) {
		Vector vector = (Vector)inputElement;
        return vector.toArray();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}
   
}