package util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import essentials.Requirement;

public class RequirementViewerSorter extends ViewerSorter {
	
	  private static final int ASCENDING = 0;

	  private static final int DESCENDING = 1;

	  private int column;

	  private int direction;

	  /**
	   * Does the sort. If it's a different column from the previous sort, do an
	   * ascending sort. If it's the same column as the last sort, toggle the sort
	   * direction.
	   * 
	   * @param column
	   */
	  public void doSort(int column) {
	    if (column == this.column) {
	      // Same column as last sort; toggle the direction
	      direction = 1 - direction;
	    } else {
	      // New column; do an ascending sort
	      this.column = column;
	      direction = ASCENDING;
	    }
	  }

	  /**
	   * Compares the object for sorting
	   */
	  public int compare(Viewer viewer, Object e1, Object e2) {
	    int rc = 0;
	    Requirement p1 = (Requirement) e1;
	    Requirement p2 = (Requirement) e2;

	    // Determine which column and do the appropriate sort
	    switch (column) {
	    case RequirementsTableConst.COLUMN_ID:
	      rc = collator.compare(p1.getIdentifier(), p2.getIdentifier());
	      break;
	    case RequirementsTableConst.COLUMN_NAME:
	      rc = collator.compare(p1.getName(), p2.getName());
	      break;
	    case RequirementsTableConst.COLUMN_RE:
	      rc = p1.getRiskExposureFinal() > p2.getRiskExposureFinal() ? 1 : -1;
	      break;
	    case RequirementsTableConst.COLUMN_P:
		      rc = p1.getProbabilityFinal() > p2.getProbabilityFinal() ? 1 : -1;
		      break;
	    case RequirementsTableConst.COLUMN_I:
		      rc = p1.getImpactFinal() > p2.getImpactFinal() ? 1 : -1;
		      break;
	
	    }

	    // If descending order, flip the direction
	    if (direction == DESCENDING)
	      rc = -rc;

	    return rc;
	  }
	}
