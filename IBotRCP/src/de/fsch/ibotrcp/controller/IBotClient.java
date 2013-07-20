package de.fsch.ibotrcp.controller;

import com.ib.client.AnyWrapper;
import com.ib.client.Contract;
import com.ib.client.EClientSocket;

import de.fsch.ibotrcp.model.DataRequest;
import de.fsch.ibotrcp.model.Kontrakt;

/**
 * 
 * Die Klasse IBotClient ist als Singleton implementiert, d.h., es gibt genau eine
 * statische Instanz. 
 */
public class IBotClient extends EClientSocket
{

private boolean active = false;	
private static IBotClient client;

	public IBotClient(IBotWrapper iBotWrapper) 
	{
	super(iBotWrapper);
//	new Thread(this).start();
	client = this;
	}
	
	/**
	 * Gibt den gemeinsamen IBotClient zurück
	 * @return the shared instance
	 */
	public static IBotClient getDefault() 
	{
	return client;
	}	

	/**
	 * Abfrage historischer Daten
	 * 
	 * Nach Aufbereitung der Daten aus der GUI mit die Methode
	 * @seeEClientSocket#reqHistoricalData aufgerufen.
	 * 
	 * Die zurückgegebenen Werte können im IBotWrapper ausgelesen werden
	 * 
	 * @param DataRequest Das Datenabfragemodel
	 */
	public synchronized void reqHistoricalData(DataRequest dr) 
	{
	Contract c = new Contract();
	c.m_symbol = dr.getStock().getIbSymbol();
    c.m_secType = "STK";
    c.m_expiry = null;
    c.m_strike = 0;
    c.m_exchange = "SMART";
    c.m_primaryExch = "SWB";
    c.m_currency = "EUR";
    c.m_includeExpired = false;	

	super.reqHistoricalData(
			dr.getTickerId(), 
			c, 
			dr.getEndDateTime(), 
			dr.getDurationStr(), 
			dr.getBarSizeSetting(), 
			dr.getWhatToShow(), 
			dr.getUseRTH(), 
			dr.getFormatDate());
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
