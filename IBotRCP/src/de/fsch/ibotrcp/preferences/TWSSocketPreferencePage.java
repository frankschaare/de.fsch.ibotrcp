package de.fsch.ibotrcp.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import de.fsch.ibotrcp.Activator;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class TWSSocketPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage 
{
StringFieldEditor sfeIP = null;
IntegerFieldEditor ifePort = null;
IntegerFieldEditor ifeClient = null;

	public TWSSocketPreferencePage() 
	{
	super(GRID);
	
	setPreferenceStore(Activator.getDefault().getPreferenceStore());
	setDescription(	"An dieser Stelle werden die Verbindungseinstellungen zur TWS konfiguriert. " +
					"Die TWS muss laufen und eingehende Socket Verbindungen müssen akzeptiert werden. " +
					"Die Einstellungen der TWS müssen mit den nachfolgenden Angaben übereinstimmen:");
	}
	
	/**
	 * Hier werden die Einstellungsfelder konfiguriert
	 */
	public void createFieldEditors() 
	{
	
	sfeIP = new StringFieldEditor(PreferenceConstants.TWS_IP, "IP Adresse der TWS:", 14, getFieldEditorParent());
	addField(sfeIP);
	
	ifePort = new IntegerFieldEditor(PreferenceConstants.TWS_PORT, "Port der TWS:", getFieldEditorParent(),5);
	addField(ifePort);
	
	ifeClient = new IntegerFieldEditor(PreferenceConstants.TWS_CLIENT,"Client ID",getFieldEditorParent(),1);
	ifeClient.setTextLimit(1);
	addField(ifeClient);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	protected void performDefaults() 
	{
	sfeIP.loadDefault();
	ifePort.loadDefault();
	ifeClient.loadDefault();
	}
	
}