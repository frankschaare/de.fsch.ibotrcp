/**
 * 
 */
package de.fsch.ibotrcp.view;

import org.eclipse.core.databinding.DataBindingContext;

/**
 * @author Administrator
 *
 */
public interface IModelView 
{
public DataBindingContext dbc = new DataBindingContext();

void bindGUI();

}
