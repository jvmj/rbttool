/*******************************************************************************
 * Copyright (c) 2007 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package rcp.providers;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import essentials.TestIteration;


/**
 * 
 */
public class TestIterationsLabelProvider extends LabelProvider implements
        ITableLabelProvider {

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    public Image getColumnImage(final Object element, final int columnIndex) {
        Image returnValue = null;
        if (columnIndex == 0) {
            returnValue = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
        }
        return returnValue;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    public String getColumnText(final Object element, final int columnIndex) {
        String returnValue = null;
        switch (columnIndex) {
        case 0:
            returnValue = ((TestIteration) element).getIdentifier();
            break;
        case 1:
            returnValue = String.valueOf(((TestIteration) element).getLessREValue());
            if(returnValue.length() > 4){
        		returnValue = returnValue.substring(0, 4);
        	}
            if(returnValue.equals("NaN")){
            	returnValue = "0.0";
            }
            break;
        case 2:
        	returnValue = String.valueOf(((TestIteration) element).getMostREValue());
        	if(returnValue.length() > 4){
        		returnValue = returnValue.substring(0, 4);
        	}
            if(returnValue.equals("NaN")){
            	returnValue = "0.0";
            }
            break;
        case 3:
            returnValue = ((TestIteration) element).getStartDate();
            break;
        case 4:
            returnValue = ((TestIteration) element).getDueDate();
            break;
        default:
            break;
        }
        return returnValue;
    }

}
