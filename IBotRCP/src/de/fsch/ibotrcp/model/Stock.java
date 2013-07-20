/**
 * 
 */
package de.fsch.ibotrcp.model;

import java.util.ArrayList;
import java.util.Date;

import de.fsch.ibotrcp.Activator;

/**
 * @author Frank Schaare, 25.09.2007
 * @version 1.0
 * 
 * Model f�r eine Aktie
 *
 */
public class Stock extends Asset 
{
/**
 * Die Anzahl der Aktien 
 */
private long stockQuantum = 0;

/**
 * Die Marktkapitalisierung:
 * Anzahl der handelbaren Aktien multipliziert mit dem aktuellen B�rsenkurs.
 */
private double marketCap = 0;

/**
 * Der letzte verf�gbare Kurs:
 */
private double lastQuote = 0;

/**
 * Das Datum des letzten Kurses:
 */
private Date lastQuoteDate = null;

/**
 * Das Tagesvolumen:
 */
private double dayVolume = 0;

/**
 * Der Tagesh�chstkurs:
 */
private double dayHigh = 0;

/**
 * Der Tagestiefstkurs:
 */
private double dayLow = 0;

/**
 * Der Vortagesh�chstkurs:
 */
private double yesterdaysHigh = 0;

/**
 * Der Vortagestiefstkurs:
 */
private double yesterdaysLow = 0;

/**
 * Der Jahresh�chstkurs:
 */
private double yearHigh = 0;

/**
 * Der Jahrestiefstkurs:
 */
private double yearLow = 0;

/**
 * Das 52 Wochen H�chstkurs:
 */
private double fiftyTwoMonthHigh = 0;

/**
 * Der 52 Wochen Tiefstkurs:
 */
private double fiftyTwoMonthLow = 0;

private ArrayList<String> indexes = null;

	/**
	 * Default Konstruktor setzt den Typ automatisch auf Aktie 
	 */
	public Stock() 
	{
	super();
	this.setType(Asset.STOCK);
		if (this.getCurrency() == null) 
		{
		setCurrency(Asset.DEFAULT_CURRENCY);	
		}
	indexes = new ArrayList<String>();
	}

	public ArrayList<String> getIndexes() 
	{
	return indexes;
	}

	public void setIndexes(ArrayList<String> indexes) 
	{
	this.indexes = indexes;
	}

	public void addIndex(String indexName) 
	{
	this.indexes.add(indexName);
	fireModelChanged();
	}

	public String getPrimaryIndex() 
	{
		switch (this.indexes.size())
		{
		case 0:
		return null;	

		default:
		return this.indexes.get(0);
		}
	 	
	}
	
	public void setPrimaryIndex(String indexName) 
	{
	this.indexes.add(0,indexName);
	fireModelChanged();
	}
	
	public long getStockQuantum() {
		return stockQuantum;
	}

	public void setStockQuantum(long stockQuantum) {
		Object oldValue = this.stockQuantum;
		this.stockQuantum = stockQuantum;
		firePropertyChange("stockQuantum", oldValue, stockQuantum);
		fireModelChanged();
		if (Activator.DEBUG) {Activator.log("[Binding - setStockQuantum] Alter Wert: " + oldValue + " Neuer Wert: " + stockQuantum, null);}
	}

	public double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(double marketCap) {
		Object oldValue = this.marketCap;
		this.marketCap = marketCap;
		firePropertyChange("marketCap", oldValue, marketCap);				
		fireModelChanged();
		if (Activator.DEBUG) {Activator.log("[Binding - setMarketCap] Alter Wert:" + oldValue + " Neuer Wert:" + marketCap, null);}
	}

}
