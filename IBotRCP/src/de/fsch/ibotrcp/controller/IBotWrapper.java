package de.fsch.ibotrcp.controller;

import org.eclipse.core.runtime.Plugin;

import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.Order;

public class IBotWrapper implements EWrapper 
{

	public IBotWrapper() {
		// TODO Auto-generated constructor stub
	}

	public void bondContractDetails(ContractDetails contractDetails) {
		// TODO Auto-generated method stub

	}

	public void contractDetails(ContractDetails contractDetails) {
		// TODO Auto-generated method stub

	}

	public void currentTime(long time) {
		// TODO Auto-generated method stub

	}

	public void execDetails(int orderId, Contract contract, Execution execution) {
		// TODO Auto-generated method stub

	}

	

	/**	
	 * Diese Methode empfängt die Ergbnisse iner historischen Datenabfrage
	 * 
	 * Die Werte bedeuten im Einzelnen:
	 * <ul>
	 * <li>reqId - Die TickerId der Anfrage.</li>
	 * <li>date - Der Timestamp vom Start der Bar. Das Format wird in {@link DataRequest#setFormatDate} festgelegt.</li>
	 * <li>open - Der Eröffnungspreis.</li>
	 * <li>high - Der Höchstpreis während des abgefragten Zeitraumes.</li>
	 * <li>low - Der Tiefstpreis während des abgefragten Zeitraumes.</li>
	 * <li>close - Der Schlusskurs.</li>
	 * <li>volume - Das Volumen während des abgefragten Zeitraumes.</li>
	 * <li>count - Wenn 'TRADES' abgefragt wird, wird hier die Anzahl der Trades während des abgefragten Zeitraumes angegeben.</li>
	 * <li>WAP - <b>W</b>eighted <b>A</b>verage <b>P</b>rice während des abgefragten Zeitraumes.
	 * Eine deutsche Erklärung zum WAP siehe <a href='http://www.timberhill.ch/de/trading/pdfhighlights/PDF-VWAP.php?ib_entity=de'>hier.</a>
	 * </li>
	 * <li>hasGaps - Weisen die Daten Gaps auf ?</li>
	 * </ul>
	*/

		

	public void historicalData(int reqId, String date, double open,
			double high, double low, double close, int volume, int count,
			double WAP, boolean hasGaps) {
	System.out.println("reqId = " + reqId);
	System.out.println("date = " + date);
	System.out.println("open = " + open);
	System.out.println("high = " + high);
	System.out.println("low = " + low);
	System.out.println("close = " + close);
	System.out.println("volume = " + volume);
	System.out.println("count = " + count);
	System.out.println("WAP = " + WAP);
	System.out.println("hasGaps = " + hasGaps);

	}

	public void managedAccounts(String accountsList) {
		// TODO Auto-generated method stub

	}

	public void nextValidId(int orderId) {
		// TODO Auto-generated method stub

	}

	public void openOrder(int orderId, Contract contract, Order order) {
		// TODO Auto-generated method stub

	}

	public void orderStatus(int orderId, String status, int filled,
			int remaining, double avgFillPrice, int permId, int parentId,
			double lastFillPrice, int clientId, String whyHeld) {
		// TODO Auto-generated method stub

	}

	public void realtimeBar(int reqId, long time, double open, double high,
			double low, double close, long volume, double wap, int count) {
		// TODO Auto-generated method stub

	}

	public void receiveFA(int faDataType, String xml) {
		// TODO Auto-generated method stub

	}

	public void scannerData(int reqId, int rank,
			ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) {
		// TODO Auto-generated method stub

	}

	public void scannerParameters(String xml) {
		// TODO Auto-generated method stub

	}

	public void tickEFP(int tickerId, int tickType, double basisPoints,
			String formattedBasisPoints, double impliedFuture, int holdDays,
			String futureExpiry, double dividendImpact, double dividendsToExpiry) {
		// TODO Auto-generated method stub

	}

	public void tickGeneric(int tickerId, int tickType, double value) {
		// TODO Auto-generated method stub

	}

	public void tickOptionComputation(int tickerId, int field,
			double impliedVol, double delta, double modelPrice,
			double pvDividend) {
		// TODO Auto-generated method stub

	}

	public void tickPrice(int tickerId, int field, double price,
			int canAutoExecute) {
		// TODO Auto-generated method stub

	}

	public void tickSize(int tickerId, int field, int size) {
		// TODO Auto-generated method stub

	}

	public void tickString(int tickerId, int tickType, String value) {
		// TODO Auto-generated method stub

	}

	public void updateAccountTime(String timeStamp) {
		// TODO Auto-generated method stub

	}

	public void updateAccountValue(String key, String value, String currency,
			String accountName) {
		// TODO Auto-generated method stub

	}

	public void updateMktDepth(int tickerId, int position, int operation,
			int side, double price, int size) {
		// TODO Auto-generated method stub

	}

	public void updateMktDepthL2(int tickerId, int position,
			String marketMaker, int operation, int side, double price, int size) {
		// TODO Auto-generated method stub

	}

	public void updateNewsBulletin(int msgId, int msgType, String message,
			String origExchange) {
		// TODO Auto-generated method stub

	}

	public void updatePortfolio(Contract contract, int position,
			double marketPrice, double marketValue, double averageCost,
			double unrealizedPNL, double realizedPNL, String accountName) {
		// TODO Auto-generated method stub

	}

	public void connectionClosed() {
		// TODO Auto-generated method stub

	}

	public void error(Exception e) 
	{
	System.out.println(e);	
	}

	public void error(String str) 
	{
	System.out.println(str);
	}

	public void error(int id, int errorCode, String errorMsg) 
	{
	System.out.println(id + " " + errorCode + " " + errorMsg);

	}

}
