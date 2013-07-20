/**
 * 
 */
package de.fsch.ibotrcp.model;

/**
 * Ein Markt Daten Tick kann als Teil einer Marktdatenanfrage angefordert werden.
 *
 * Die REQ_MKT_DATA Message enthält ein durch Kommata getrenntes Feld von Integer ID's (generic tick types). 
 * Anfragen für diese Ticks werden beantwortet, wenn der angefragte Tick Typ für den Kontrakt zur Verfügung steht.
 *  
 * @since API version 9.0 (client version 30)
 */
public class GenericTickType 
{
//Für Aktien gültige Ticks:	
public static final int OPTION_VOLUME =	100;
public static final int OPTION_OPEN_INTEREST = 101;	
public static final int	HISTORICAL_VOLATILITY = 104;
public static final int OPTION_IMPLIED_VOLATILITY = 106;

//Anderweitig gültig
public static final int INDEX_FUTURE_PREMIUM = 162;
public static final int MISCELLANEOUS_STATS = 165;
public static final int MARK_PRICE = 221;
public static final int AUCTION_VALUES = 225; //  (volume, price and imbalance)
public static final int SHORTABLE = 236; 

private int tickType = 0;
private String description = null;
private int[] resultingTickValue = null;

	/**
	 * Default Konstruktor.
	 * 
	 * Dem Typ liegt eine festgelegte Beschreibung zugrunde
	 * Die resultierenden Tick Werte sind ebenfalls fix.
	 */
	public GenericTickType(int type) 
	{
	this.tickType = type;
	
		switch (type) 
		{
		case 100:
			this.description = "Optionen Volumen";
			this.resultingTickValue = new int[] {29, 30};
		break;

		case 101:
			this.description = "Optionen offene Interessten";
			this.resultingTickValue = new int[] {27, 28};
		break;

		case 104:
			this.description = "Historische Volatilität";
			this.resultingTickValue = new int[] {23};
		break;
		
		case 106:
			this.description = "Optionen implizite Volatilität";
			this.resultingTickValue = new int[] {24};
		break;
		
		case 162:
			this.description = "Index Future Premium";
			this.resultingTickValue = new int[] {31};
		break;
		
		case 165:
			this.description = "Verschiedene Statistiken";
			this.resultingTickValue = new int[] {15, 16, 17, 18, 19, 20, 21};
		break;
		
		case 221:
			this.description = "Mark Preis";
			this.resultingTickValue = new int[] {37};
		break;
		
		case 225:
			this.description = "Auktionswerte";
			this.resultingTickValue = new int[] {34, 35, 36};
		break;
		
		case 236:
			this.description = "Leerverkauf";
			this.resultingTickValue = new int[] {46};
		break;		
		
		default:
			break;
		}
	}

	public int getTickType() {
		return tickType;
	}

	public void setTickType(int tickType) {
		this.tickType = tickType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int[] getResultingTickValue() {
		return resultingTickValue;
	}

	public void setResultingTickValue(int[] resultingTickValue) {
		this.resultingTickValue = resultingTickValue;
	}

}
