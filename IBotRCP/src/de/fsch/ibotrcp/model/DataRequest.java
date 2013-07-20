/**
 * 
 */
package de.fsch.ibotrcp.model;

import java.util.ArrayList;


/**
 * Eine Datenanfrage an den TWS Socket
 *
 */
public class DataRequest extends ModelObject 
{
private Stock stock = null;
/**
 * Die ID der Anfrage.
 * Muss ein eindeutiger Wert sein. 
 * Die eingehenden Daten werden über die ID identifiziert. 
 * Die ID wird auch benutzt, wenn die Anfrage historischer Daten abgebrochen wird.
 */
private int tickerId = 0;

/**
 * Der zugrunde liegende Kontrakt
 */
private Kontrakt kontrakt = null;

/**
 * Bis wann werden Daten abgefragt ?
 * Format: yyyymmdd hh:mm:ss (tmz)
 * Die Angabe der Zeitzone ist optional nach einem Leerzeichen
 */
private  String endDateTime = null;

/**
 * Zeitspanne der abgefragten Daten 
 * Format: <integer> <einheit>, beispielsweise "1 D" für einen Tag.
 * 
 * Gültige Einheiten:
 * <ul>
 * <li>S (Sekunden)</li>
 * <li>D (Tage)</li>
 * <li>W (Wochen)</li>
 * <li>M (Monate)</li>
 * <li>Y (Jahre)</li>
 * </ul>
 * 
 * Wird keine Einheit angegeben, werden standardmässig Sekunden benutzt.
 * 
 * Die Einheit Jahr ist derzeit auf <b>ein Jahr</b> beschränkt.
 */
private String durationStr = null;

/**
 * Gibt die Grösse der zurückgegeben Bars an.
 * 
 * Gültige Grössen sind:
 * <ul>
 * <li>1 Sekunde (1)</li>
 * <li>5 Sekunden (2)</li>
 * <li>15 Sekunden (3)</li>
 * <li>30 Sekunden (4)</li>
 * <li>1 Minute (5)</li>
 * <li>2 Minuten (6)</li>
 * <li>5 Minuten (7)</li>
 * <li>15 Minuten (8)</li>
 * <li>30 Minuten (9)</li>
 * <li>1 Stunde (10)</li>
 * <li>1 Tag (11)</li>
 * <li>1 Woche</li> 
 * <li>1 Monat </li>
 * <li>3 Monate</li>
 * <li>1 Jahr</li>
 * </ul>
 * 
 * Die ArrayList barSizes hält diese Werte vor
 */
private String barSizeSetting = null;
private final ArrayList<String> barSizeValues = new ArrayList<String>(15); 

/**
 * Welche Daten sollen abgefragt werden ?
 * 
 * Gültige Werte:
 * <ul>
 * <li>TRADES</li>
 * <li>MIDPOINT</li>
 * <li>BID</li>
 * <li>ASK</li>
 * <li>BID/ASK</li>
 * </ul>
 * 
 * Die ArrayList whatToShowValues hält diese Werte vor
 */
private String whatToShow = null;
private final ArrayList<String> whatToShowValues = new ArrayList<String>(5); 

/**
 * RTH = [R]egular [T]rading [H]ours
 * Legt fest, ob alle Daten zurückgegeben werden, oder nur die normalen Handelszeiten abgefragt werden.
 * 
 * Gültige Werte:
 * <ul>
 * <li>0 = Alle Daten werden zurückgegeben, auch ausserbörslich</li>
 * <li>1 = Nur Daten der regulären Handelszeiten werden zurückgegeben</li>
 * </ul>
 * 
 */
private int useRTH = 1;

/**
 * Datumsformat
 * 
 * Gültige Werte:
 * <ul>
 * <li>1 = Datumsformat: yyyymmdd{space}{space}hh:mm:dd
 * <li>2 = Long integer der die Anzahl der Sekunden seit dem 1.1.1970 GMT angibt.</li>
 * </ul>
 */
private int formatDate = 2;

	/**
	 * Default Konstruktor
	 */
	public DataRequest() 
	{
	kontrakt = new Kontrakt();
	fillBarSizeValues(); 
	fillWhatToShowValues();
	setDummyData();
	}

	/**
	 * Debug Methode setzt Werte ohne GUI Eingaben
	 * Sollte später gelöscht werden.
	 */
	
	private void setDummyData() 
	{
	// TODO Methode löschen, wenn GUI angebunden
	setId("1");
	// Kontract Symbol ist bereits 'QQQQ'
	setEndDateTime("20071001 07:51:03 GMT");
	setDurationStr("1 M");
	setBarSizeSetting("1 day");
	setWhatToShow("TRADES");
	setUseRTH(1);	
	setFormatDate(1);
	}

	private void fillBarSizeValues() 
	{
	barSizeValues.add("bitte auswählen...");		
	barSizeValues.add("1 Sekunde");
	barSizeValues.add("5 Sekunden");
	barSizeValues.add("15 Minuten");
	barSizeValues.add("30 Sekunden");
	barSizeValues.add("1 Minute");
	barSizeValues.add("2 Minuten");
	barSizeValues.add("5 Minuten");
	barSizeValues.add("15 Minuten");
	barSizeValues.add("30 Minuten");
	barSizeValues.add("1 Stunde");
	barSizeValues.add("1 Tag");
	barSizeValues.add("1 Woche");
	barSizeValues.add("1 Monat");
	barSizeValues.add("3 Monate");
	barSizeValues.add("1 Jahr");	
	}
	
	private void fillWhatToShowValues() 
	{
	barSizeValues.add("TRADES");
	barSizeValues.add("MIDPOINT");
	barSizeValues.add("BID");
	barSizeValues.add("ASK");
	barSizeValues.add("BID/ASK");
	}	
	
	
	public void setTickerId(int tickerId) 
	{
	this.tickerId = tickerId;
	}

	public void setKontrakt(Kontrakt kontrakt) {
		this.kontrakt = kontrakt;
	}
	
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	public void setDurationStr(String durationStr) {
		this.durationStr = durationStr;
	}
	
	public void setBarSizeSetting(String barSizeSetting) {
		this.barSizeSetting = barSizeSetting;
	}
	
	public void setWhatToShow(String whatToShow) {
		this.whatToShow = whatToShow;
	}
	
	public void setUseRTH(int useRTH) {
		this.useRTH = useRTH;
	}
	
	public void setFormatDate(int formatDate) {
		this.formatDate = formatDate;
	}

	public int getTickerId() {
		return tickerId;
	}

	public Kontrakt getKontrakt() {
		return kontrakt;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public String getDurationStr() {
		return durationStr;
	}

	public String getBarSizeSetting() {
		return barSizeSetting;
	}

	public String getWhatToShow() {
		return whatToShow;
	}

	public int getUseRTH() {
		return useRTH;
	}

	public int getFormatDate() {
		return formatDate;
	}

	public Stock getStock()
	{
		return stock;
	}

	public void setStock(Stock stock)
	{
		this.stock = stock;
	}


}
