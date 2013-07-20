package de.fsch.ibotrcp.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.fsch.ibotrcp.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer 
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() 
	{
	IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	
	store.setDefault(PreferenceConstants.P_BOOLEAN, true);
	store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
	store.setDefault(PreferenceConstants.P_STRING,"Default value");

	store.setDefault(PreferenceConstants.GENERAL_DEBUG_LEVEL,"3");

	store.setDefault(PreferenceConstants.VIEWS_EDITASSET_DEFAULT, "http://www.rt.boerse-stuttgart.de/pages/indizes/main.html?action=stocks&&LANG=de");	
	store.setDefault(PreferenceConstants.VIEWS_EDITASSET_NAME, "http://de.finsearch.yahoo.com/de/index.php?s=de_sort&nm={name}&tp=S&r=DE&sub=Suchen");
	store.setDefault(PreferenceConstants.VIEWS_EDITASSET_SYMBOL, "http://www.rt.boerse-stuttgart.de/pages/details/main.html?LANG=de&sSymbol={symbol}.STU");	
	store.setDefault(PreferenceConstants.VIEWS_EDITASSET_IB_PREFIX, "http://www.interactivebrokers.co.uk/contract_info/index.php");	
	store.setDefault(PreferenceConstants.VIEWS_EDITASSET_IB_SUFFIX, "?site=IB&action=Top%20Search&symbol={name}&description={name}");	
	
	
	store.setDefault(PreferenceConstants.TWS_IP,"192.168.1.65");
	store.setDefault(PreferenceConstants.TWS_PORT,"20266");
	store.setDefault(PreferenceConstants.TWS_CLIENT, 0);
	
	store.setDefault(PreferenceConstants.PSQL_PREFIX,"jdbc:postgresql://");
	store.setDefault(PreferenceConstants.PSQL_IP,"127.0.0.1");
	store.setDefault(PreferenceConstants.PSQL_PORT,"5432");
	store.setDefault(PreferenceConstants.PSQL_DATABASE,"ibot");
	store.setDefault(PreferenceConstants.PSQL_USER, "horstbrack");
	store.setDefault(PreferenceConstants.PSQL_PASSWORT, "looser");

	}

}
