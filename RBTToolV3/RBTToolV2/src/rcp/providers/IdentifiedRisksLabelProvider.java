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

import essentials.IdentifiedRisk;


/**
 * 
 */
public class IdentifiedRisksLabelProvider extends LabelProvider implements
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
        	returnValue = ((IdentifiedRisk) element).getRisk().getName();
            break;
        case 1:
            returnValue = ((IdentifiedRisk) element).getCause();
            break;
        case 2:
            returnValue = ((IdentifiedRisk) element).getResourceName();
            break;
        case 3:
            returnValue = ((IdentifiedRisk) element).getQuestionDescription();
            break;
        default:
            break;
        }
        return returnValue;
    }

}
