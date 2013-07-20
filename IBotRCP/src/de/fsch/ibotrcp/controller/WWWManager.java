/**
 * 
 */
package de.fsch.ibotrcp.controller;

import de.fsch.ibotrcp.Activator;


/**
 * @author Administrator
 *
 */
public class WWWManager
{
private static WWWManager wwwManager;	

	/**
	 * 
	 */
	public WWWManager() 
	{
	wwwManager = this;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static WWWManager getDefault() {
		return wwwManager;
	}
	
	/**
	 * Bereitet einen String für die Eingabe als URL vor
	 *
	 * @return den encodierten Wert
	 */
	public static String encodeValue(String value) {
	String oldValue = value;
	
		if (value.contains(" NA")) 
		{
		value = value.replace(" NA", "");	
		}
		if (value.contains(" ST")) 
		{
		value = value.replace(" ST", "");	
		}
		if (value.contains(" ")) 
		{
		value = value.replace(" ", "%");	
		}
		
		if (Activator.DEBUG) {Activator.log("WWWManager#encodeValue: Konvertiere alten Wert: " + oldValue + " in neuen Wert: " + value, null);}
	return value;
	}
}
