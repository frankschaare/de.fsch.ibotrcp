/**
 * 
 */
package de.fsch.ibotrcp.model;

import java.util.ArrayList;
import java.util.List;

import de.fsch.ibotrcp.Activator;


/**
 * @author Frank Schaare, 25.09.2007
 * @version 1.0
 * 
 * Superklasse für alle Anlagetypen
 *
 */
public class Asset extends ModelObject implements Comparable<Asset>
{
public static final String ASSET_NOT_SET = "bitte auswählen...";	
public static final String INDEX = "Index";
public static final String STOCK = "Aktie";
public static final String BOND = "Anleihe";
public static final String FUTURE = "Future";
public static final String DEFAULT_CURRENCY = "EUR";
private List<IModelChangedListener> listeners = new ArrayList<IModelChangedListener>();

/**
 * Die Bezeichnung der Anlageklasse 
 */
private String name = null;

/**
 * Die International Security Identification Number 
 */
private String isin = null;

/**
 * Die Wertpapierkennnummer 
 */
private String wkn = null;
/**
 * Das Symbol, mit dem bei einigen deutschen Börsen referenziert wird 
 */
private String symbol = null;

/**
 * Das Interactive Brokers Symbol 
 */
private String ibSymbol = null;

/**
 * Die konkrete Anlageklasse 
 */
private String type = null;

/**
 * Die Standardwährung, in der die Anlageklasse gehandelt wird 
 */
private String currency = null;

/**
 * Hilfsklasse um festgelegte Auswahlattribute in der GUI anzuzeigen 
 */
private AssetChoices assetChoices = new AssetChoices();


	/**
	 * Konstruktor
	 */
	public Asset() 
	{
	}

	public void addModelChangedListener(IModelChangedListener listerner) 
	{
		if (!listeners.contains(listerner)) 
		{
		listeners.add(listerner);	
		}
	}
	
	public void removeModelChangedListener(IModelChangedListener listerner) 
	{
	listeners.remove(listerner);	
	}
	
	protected void fireModelChanged() 
	{
		for (IModelChangedListener listener : listeners) 
		{
		listener.modelChanged();	
		}
	}
	
	public int compareTo(Asset o) 
	{
		int result = this.getName().compareToIgnoreCase(o.getName());
		return result;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) 
	{
	Object oldValue = this.isin;
	this.isin = isin;
	firePropertyChange("isin", oldValue, isin);
	fireModelChanged();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) 
	{
	Object oldValue = this.name;
	this.name = name;
	firePropertyChange("name", oldValue, name);
	if (Activator.DEBUG) {Activator.log("Asset#setName - firePropertyChange: Property: name, alter Wert: "  + oldValue + ", neuer Wert: " + name, null);}
	fireModelChanged();
	}

	public void setType(String type) 
	{
	Object oldValue = this.type;
	this.type = type;
	firePropertyChange("type", oldValue, type);
	fireModelChanged();
	}

	public String getWkn() {
		return wkn;
	}

	public void setWkn(String wkn) 
	{
	Object oldValue = this.wkn;
	this.wkn = wkn;
	firePropertyChange("wkn", oldValue, wkn);
	fireModelChanged();
	}


	public AssetChoices getAssetChoices() {
		return assetChoices;
	}

	public String getType() {
		return type;
	}

	public void setAssetChoices(AssetChoices assetChoices) {
		this.assetChoices = assetChoices;
	}

	public String getIbSymbol() {
		return ibSymbol;
	}

	public void setIbSymbol(String ibSymbol) {
		Object oldValue = this.ibSymbol;
		this.ibSymbol = ibSymbol;
		firePropertyChange("ibSymbol", oldValue, ibSymbol);
		fireModelChanged();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		Object oldValue = this.symbol;
		this.symbol = symbol;
		firePropertyChange("symbol", oldValue, symbol);		
		fireModelChanged();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		Object oldValue = this.currency;
		this.currency = currency;
		firePropertyChange("currency", oldValue, currency);
		fireModelChanged();
	}


}
