/**
 * 
 */
package de.fsch.ibotrcp.view;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;

import de.fsch.ibotrcp.model.TreeNode;

/**
 * @author Administrator
 *
 */
public class CellModifier implements ICellModifier 
{
private TreeViewer viewer;	

	/**
	 * 
	 */
	public CellModifier(TreeViewer viewer) 
	{
	this.viewer = viewer;	
	}

	public boolean canModify(Object element, String property) 
	{
	return true;
	}

	public Object getValue(Object element, String property) {
	return ((TreeNode) element).getValue();	
	}

	@Override
	public void modify(Object element, String property, Object value) {
		// TODO Auto-generated method stub
		
	}

}
