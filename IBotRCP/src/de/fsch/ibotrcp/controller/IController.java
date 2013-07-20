/**
 * 
 */
package de.fsch.ibotrcp.controller;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

import de.fsch.ibotrcp.Activator;
import de.fsch.ibotrcp.model.AssetConstants;

/**
 * @author Administrator
 *
 */
public interface IController 
{
public ObjectContainer db = Db4o.openFile(AssetConstants.DB_PATH);

void closeConnection(); 
}
