/**
 * 
 */
package de.fsch.ibotrcp.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchWindow;

import de.fsch.ibotrcp.Activator;
import de.fsch.ibotrcp.model.IModelChangedListener;
import de.fsch.ibotrcp.model.Stock;
import de.fsch.ibotrcp.view.EditAssetView;

/**
 * @author Administrator
 *
 */
public class SaveStockAction extends DataBaseAction implements IModelChangedListener
{
private EditAssetView view;
private Stock stock;
private ResultSet rs = null;

PreparedStatement selectCount;
PreparedStatement updateStock;

	/**
	 * 
	 */
	public SaveStockAction(IWorkbenchWindow window, String label) 
	{
	super(window,label);
	viewId = EditAssetView.ID;

	// The id is used to refer to the action in a menu or toolbar
	setId(ICommandIds.CMD_SAVE_STOCK);
    // Associate the action with a pre-defined command, to allow key bindings.
	setActionDefinitionId(ICommandIds.CMD_SAVE_STOCK);
	setImageDescriptor(de.fsch.ibotrcp.Activator.getImageDescriptor("/icons/save_edit.gif"));

	}

	public void run() 
	{
	super.run();
	
	view = (EditAssetView) window.getActivePage().findView(viewId);
	stock = view.getStock();
	
		try 
		{
		selectCount = con.prepareStatement(COUNT_STOCK_ISIN);
		selectCount.setString(1, stock.getIsin());	
		rs = selectCount.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		rs.close();
		selectCount.close();
		
			switch (result) 
			{
			case 0: // Kein Datensatz vorhanden: Insert
				
				break;

			default: // Ein Datensatz vorhanden: Update
			updateStock = con.prepareStatement(UPDATE_STOCK);
			updateStock.setString(1, stock.getIsin());
			updateStock.setString(2, stock.getName());
			updateStock.setString(3, stock.getIndexes().get(0));
			updateStock.setString(4, stock.getWkn());
			updateStock.setString(5, stock.getSymbol());
			updateStock.setString(6, stock.getIbSymbol());
			updateStock.setString(7, stock.getCurrency());
			updateStock.setLong(8, stock.getStockQuantum());
			updateStock.setDouble(9, stock.getMarketCap());
			updateStock.setString(10, stock.getIsin());

			break;
			}
			System.out.println(updateStock.toString());
			if (Activator.DEBUG) {Activator.log(updateStock.toString(), null);}
			
			updateStock.execute();
			updateStock.close();
			
		closeConnection();	
		} 
		catch (SQLException e) 
		{
		Activator.log("Datenbankfehler beim Speichern der Aktie: " + stock.getName(), e);
		}
		
	/**
	 * Es wird ein PropertyChangeEvent abgefeuert der vom @link IndexTreeContentProvider aufgefangen wird
	 * Dieser aktualisiert das Baummodell.
	 */ 
		
	PropertyChangeEvent event = new PropertyChangeEvent(stock,stock.getName(),null,null);	
	firePropertyChange(event);	
	}

	public void modelChanged() 
	{
	this.setEnabled(true);	
	}

}
