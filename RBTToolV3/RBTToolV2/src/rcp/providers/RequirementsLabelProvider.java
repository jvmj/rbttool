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

import essentials.Requirement;


/**
 * 
 */
public class RequirementsLabelProvider extends LabelProvider implements
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
            returnValue = ((Requirement) element).getIdentifier();
            break;
        case 1:
            returnValue = ((Requirement) element).getName();
            break;
        case 2:
            returnValue = String.valueOf(((Requirement) element).getRiskExposureFinal());
            if(returnValue.length() > 4){
        		returnValue = returnValue.substring(0, 4);
        	}
            if(returnValue.equals("NaN")){
            	returnValue = String.valueOf(0.0);
            }
            break;
        case 3:
        	returnValue = String.valueOf(((Requirement) element).getProbabilityFinal());
        	if(returnValue.length() > 4){
        		returnValue = returnValue.substring(0, 4);
        	}
            if(returnValue.equals("NaN")){
            	returnValue = String.valueOf(0.0);
            }
            break;
        case 4:
        	returnValue = String.valueOf(((Requirement) element).getImpactFinal());
        	if(returnValue.length() > 4){
        		returnValue = returnValue.substring(0, 4);
        	}
            if(returnValue.equals("NaN")){
            	returnValue = String.valueOf(0.0);
            }
            break;
        /*case 3:
            returnValue = ((Requirement) element).getVersion();
            break;
        case 4:
            returnValue = ((Requirement) element).getModule();
            break;*/
        default:
            break;
        }
        return returnValue;
    }

}
