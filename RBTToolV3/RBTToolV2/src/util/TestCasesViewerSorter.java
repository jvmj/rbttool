package util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import essentials.Requirement;
import essentials.TestCase;

public class TestCasesViewerSorter extends ViewerSorter {
	
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
	    TestCase p1 = (TestCase) e1;
	    TestCase p2 = (TestCase) e2;

	    // Determine which column and do the appropriate sort
	    switch (column) {
	    case TestCasesTableConst.COLUMN_DESC:
	      rc = collator.compare(p1.getDescription(), p2.getDescription());
	      break;
	    case TestCasesTableConst.COLUMN_REQ:
	      rc = collator.compare(p1.getRequirement().getName(), p2.getRequirement().getName());
	      break;
	    case TestCasesTableConst.COLUMN_RISKATTRIBUTE:
	      rc = collator.compare(p1.getRisk().getName(), p2.getRisk().getName());
	      break;
	    case TestCasesTableConst.COLUMN_STATUS:
		      rc = collator.compare(p1.getStatus(), p2.getStatus());;
		      break;
	    }

	    // If descending order, flip the direction
	    if (direction == DESCENDING)
	      rc = -rc;

	    return rc;
	  }
	}
