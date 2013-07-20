package de.fsch.ibotrcp.model;

import org.eclipse.core.runtime.Platform;

import de.fsch.ibotrcp.Activator;


/**
 * Konstanten für die Datenzugriffe
 */
public class AssetConstants 
{
public static final int SEKTOR_AUTOMOBILE =	1;
public static final int SEKTOR_BANKEN = 2;
public static final int SEKTOR_CHEMIE =	3;
public static final int SEKTOR_MEDIEN =	4;
public static final int SEKTOR_ROHSTOFFE = 5;
public static final int SEKTOR_NAHRUNGSMITTEL = 7;
public static final int SEKTOR_TECHNOLOGIE = 8;
public static final int SEKTOR_VERSICHERUNGEN =	9;
public static final int SEKTOR_TRANSPORT = 10;
public static final int SEKTOR_MASCHINENBAU = 11;
public static final int SEKTOR_INDUSTRIE = 12;
public static final int SEKTOR_BAUGEWERBE =	13;
public static final int SEKTOR_PHARMA =	14;
public static final int SEKTOR_EINZELHANDEL = 15;
public static final int SEKTOR_SOFTWARE = 16;
public static final int SEKTOR_TELEKOMMUNIKATION = 17;
public static final int SEKTOR_VERSORGER = 18;
public static final int SEKTOR_FINANZDIENSTLEISTUNGEN = 19;
public static final int SEKTOR_VERBRAUCHSGUETER = 20;
	
public static final String DB_PATH = Platform.getUserLocation().getURL().getPath().replace("user/", "IBot.yap");
}
