package util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import essentials.IdentifiedRisk;


public class IdentifiedRisksViewerSorter extends ViewerSorter {
	
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
	    IdentifiedRisk p1 = (IdentifiedRisk) e1;
	    IdentifiedRisk p2 = (IdentifiedRisk) e2;

	    // Determine which column and do the appropriate sort
	    switch (column) {
	    case IdentifiedRisksTableConst.COLUMN_RISK:
	      rc = collator.compare(p1.getRisk().getName(), p2.getRisk().getName());
	      break;
	    case IdentifiedRisksTableConst.COLUMN_CAUSE:
	      rc = collator.compare(p1.getCause(), p2.getCause());
	      break;
	    case IdentifiedRisksTableConst.COLUMN_RESOURCE:
	    	rc = collator.compare(p1.getResourceName(), p2.getResourceName());
	      break;
	    case IdentifiedRisksTableConst.COLUMN_QUESTION:
	    	rc = collator.compare(p1.getQuestionDescription(), p2.getQuestionDescription());
	      break;
	
	    }

	    // If descending order, flip the direction
	    if (direction == DESCENDING)
	      rc = -rc;

	    return rc;
	  }
	}
