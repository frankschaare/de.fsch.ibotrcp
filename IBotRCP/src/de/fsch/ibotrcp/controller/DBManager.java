/**
 * 
 */
package de.fsch.ibotrcp.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.preference.IPreferenceStore;
import org.postgresql.jdbc3.Jdbc3ConnectionPool;

import de.fsch.ibotrcp.Activator;
import de.fsch.ibotrcp.model.Index;
import de.fsch.ibotrcp.model.Stock;
import de.fsch.ibotrcp.preferences.PreferenceConstants;

/**
 * @author Administrator
 *
 */
public class DBManager
{
private static DBManager dbManager;	

private Jdbc3ConnectionPool pool = null;
private Connection con = null;

private ArrayList<Stock> germanStocks = new ArrayList<Stock>();
private ArrayList<Stock> crawledStocks = new ArrayList<Stock>();
private HashMap<String,Index> indexes = new HashMap<String,Index>();

	/**
	 * 
	 */
	public DBManager() 
	{
	setDBPool();
	dbManager = this;
	
	setGermanStocks();

	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static DBManager getDefault() {
		return dbManager;
	}
	
	private void setDBPool() 
	{
	IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	
	// Test für PSQL Connection Pool
	pool = new Jdbc3ConnectionPool();
	pool.setPrepareThreshold(20);
	pool.setServerName("127.0.0.1");
	pool.setDatabaseName(store.getString(PreferenceConstants.PSQL_DATABASE));
	pool.setPassword(store.getString(PreferenceConstants.PSQL_PASSWORT));
	pool.setPortNumber(store.getInt(PreferenceConstants.PSQL_PORT));
	pool.setUser(store.getString(PreferenceConstants.PSQL_USER));	
	}	
	
	public Connection getConnection()
	{
		try 
		{
		con = pool.getPooledConnection().getConnection();
		} 
		catch (SQLException e) 
		{
		e.printStackTrace();
		}
	return con;	
	}
	
	private void setGermanStocks() 
	{
	String strIndex;	
			try 
			{
			ResultSet rs = 	pool.getPooledConnection().getConnection().createStatement().executeQuery(
			"select * from public.indexes order by id");
				while (rs.next()) 
				{
				Index index	= new Index();
				
					index.setId(String.valueOf(rs.getInt("id")));
					strIndex = rs.getString("shortname");
					index.setName(strIndex);
					index.setWkn(rs.getString("wkn"));
					index.setIsin(rs.getString("isin"));
					index.setSymbol(rs.getString("symbol"));
					
					indexes.put(strIndex,index);	
				}
			rs.close();
			
			rs = 	pool.getPooledConnection().getConnection().createStatement().executeQuery(
			"select * from public.stocks_germany order by name");   
			
				while (rs.next()) 
				{
		
				Stock stock	= new Stock();
				stock.setIsin(rs.getString("isin"));
				stock.setName(rs.getString("name"));
		
					strIndex = rs.getString("primary_index");
					if (strIndex != null) 
					{
					stock.addIndex(rs.getString("primary_index"));	
					}
					
					if (rs.getString("wkn") != null) 
					{
					stock.setWkn(rs.getString("wkn").trim());	
					}
					
					if (rs.getString("symbol") != null) 
					{
					stock.setSymbol((rs.getString("symbol").trim()));	
					}

					if (rs.getString("ibsymbol") != null) 
					{
					stock.setIbSymbol(rs.getString("ibsymbol").trim());	
					}
					
					if (rs.getLong("stockquantum") != 0) 
					{
					stock.setStockQuantum(rs.getLong("stockquantum"));	
					}					
	
				germanStocks.add(stock);
				
					// Wenn die Aktie einen primären Index hat, wird sie zur Indexliste hinzugefügt.
					if (strIndex != null) 
					{
					indexes.get(strIndex).addStock(stock);	
					}
				}
			rs.close();
			} 
			catch (SQLException e) 
			{
			e.printStackTrace();
			}		

	}

	public ArrayList<Stock> getGermanStocks() {
		return germanStocks;
	}

	public HashMap<String, Index> getIndexes() {
		return indexes;
	}

	public ArrayList<Stock> getCrawledStocks()
	{
		if (crawledStocks.size()>0)
		{
		return crawledStocks;	
		}
		else
		{
		return null;	
		}
		
	}

	public void setCrawledStocks(ArrayList<Stock> crawledStocks)
	{
	this.crawledStocks = crawledStocks;
	}

	public void updateStocks()
	{
	String strWKN;
	String strISIN;
	String oldPrimaryIndex;
	String strSQL = null;
	int result = 0;
	ResultSet rs;
	Connection con = null;
	Statement stmt = null;
		try
		{
		con = pool.getPooledConnection().getConnection();
		stmt = con.createStatement(); 
		} catch (SQLException e1)
		{
		e1.printStackTrace();
		}
	
		for (Stock stock : crawledStocks)
		{
		strWKN = stock.getWkn();
		try
		{
		rs = stmt.executeQuery(
		"select count(*) from public.stocks_germany WHERE isin like'%"+strWKN+"%'");
			while (rs.next()) 
			{
			result = rs.getInt(1);	
			System.out.println(result);
			}
		rs.close();
		
		if (result == 1)
		{
		strSQL = 	
		"UPDATE public.stocks_germany " +
		"SET primary_index = '" + stock.getPrimaryIndex() + "', "+
		"wkn = '" + strWKN + "', " + 
		"symbol = '" + stock.getSymbol() + "', " +
		"ibsymbol = '" + stock.getSymbol() + "' " + 
		"WHERE isin like '%"+strWKN+"%'";			
		stmt.execute(strSQL);
		}
		else
		{
		strSQL = 	
		"INSERT INTO  public.stocks_germany  ( name, primary_index, wkn, symbol,  ibsymbol, currency ) " +
		"VALUES ('" + stock.getName() + "', '"+
		stock.getPrimaryIndex()+ "', '" + 
		stock.getWkn() + "', '" +
		stock.getSymbol()+ "', '" +	
		stock.getSymbol()+ "', 'EUR')";		
		stmt.execute(strSQL);	
		}	
		
		
		} 
		catch (SQLException e)
		{
		e.printStackTrace();
		}

		
		}
	System.out.println("Fertig");
	}	
}
