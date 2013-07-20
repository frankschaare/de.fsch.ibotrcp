/**
 * 
 */
package de.fsch.ibotrcp.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.console.IConsoleConstants;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

import de.fsch.ibotrcp.controller.DBManager;
import de.fsch.ibotrcp.controller.IController;
import de.fsch.ibotrcp.model.AssetConstants;
import de.fsch.ibotrcp.model.IModelChangedListener;
import de.fsch.ibotrcp.model.Stock;
import de.fsch.ibotrcp.view.EditAssetView;

/**
 * @author Administrator
 *
 */
public class DataBaseAction extends Action implements IModelChangedListener
{
protected final IWorkbenchWindow window;
protected String viewId;

protected Connection con = null;

private final String NULL = "null";
protected final String COUNT_STOCK_ISIN = "SELECT count(*) FROM public.stocks_germany WHERE isin = ?";
protected final String UPDATE_STOCK = 
	"UPDATE public.stocks_germany "+  
	"SET isin = ?, "+ 
	"name = ?, "+ 
	"primary_index = ?, "+ 
	"wkn = ?, "+ 
	"symbol = ?, "+ 
	"ibsymbol = ?, "+ 
	"currency = ?, "+ 
	"stockquantum = ?, "+ 
	"marketcap = ? "+ 
	"WHERE isin = ?";

	/**
	 * MasterAction für alle Actions, die Datenbankoperationen machen 
	 */
	public DataBaseAction(IWorkbenchWindow window, String label) 
	{
	this.window = window;
    setText(label);

	}

	public void run() 
	{
	con = DBManager.getDefault().getConnection();
	}
	
	public String getUpdateSQL(Object fieldValue) 
	{
	String result = null;
	String value = null;
	
		if (fieldValue instanceof String) 
		{
		value = (String) fieldValue;
			if (value != null) 
			{
			result = "'" + value +"'";	
			} 
			else 
			{
			result = NULL;	
			}	
		}
		else
		{
		value = String.valueOf(fieldValue);	
			if (value != null) 
			{
			result = value;	
			} 
			else 
			{
			result = NULL;	
			}				
		}	
		
	return result; 
	}
	
	public void closeConnection() 
	{
		try 
		{
			if (con != null)
			{
			con.close();	
			}
		} 
		catch (SQLException e) {
		e.printStackTrace();
		}
	}

	public void modelChanged() 
	{
	
	}

}
