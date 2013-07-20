package de.fsch.ibotrcp.preferences;

/**
 * Konstanten für die Plug-In Preferenzen
 * 
 * Diese Konstanten werden hier definiert, damit die Workbench
 * sie im Falle eines User-Resets zurücksetzen kann.
 * 
 */
public class PreferenceConstants 
{

	public static final String P_PATH = "pathPreference";
	public static final String P_BOOLEAN = "booleanPreference";
	public static final String P_CHOICE = "choicePreference";
	public static final String P_STRING = "stringPreference";

	// Allgemeine Einstellungen
	public static final String GENERAL_DEBUG_LEVEL = "3";
	
	// Views Einstellungen
	public static final String VIEWS_EDITASSET_DEFAULT = "http://www.rt.boerse-stuttgart.de/pages/indizes/main.html?action=stocks&&LANG=de";
	public static final String VIEWS_EDITASSET_NAME = "http://de.finsearch.yahoo.com/de/index.php?s=de_sort&nm={name}&tp=S&r=DE&sub=Suchen";
	public static final String VIEWS_EDITASSET_SYMBOL = "http://www.rt.boerse-stuttgart.de/pages/details/main.html?LANG=de&sSymbol={symbol}.STU";
	public static final String VIEWS_EDITASSET_IB_PREFIX = "http://www.interactivebrokers.co.uk/contract_info/index.php";	
	public static final String VIEWS_EDITASSET_IB_SUFFIX = "?site=IB&action=Top%20Search&symbol={name}&description={name}";
		
	
	// TWS Verbindungsdaten
	public static final String TWS_IP = "127.0.0.1";
	public static final String TWS_PORT = "7496";
	public static final String TWS_CLIENT = "0";
	
	// Datenbank Verbindungsdaten
	public static final String PSQL_PREFIX = "jdbc:postgresql://";
	public static final String PSQL_IP = "127.0.0.1";
	public static final String PSQL_PORT = "5432";
	public static final String PSQL_DATABASE = "ibot";
	public static final String PSQL_USER = "horstbrack";
	public static final String PSQL_PASSWORT = "looser";

}
