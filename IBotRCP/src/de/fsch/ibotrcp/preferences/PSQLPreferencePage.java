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

public class PSQLPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage 
{
StringFieldEditor prefix = null;
StringFieldEditor ip = null;
IntegerFieldEditor port = null;
StringFieldEditor db = null;
StringFieldEditor user = null;
StringFieldEditor passwort = null;

	public PSQLPreferencePage() 
	{
	super(GRID);
	
	setPreferenceStore(Activator.getDefault().getPreferenceStore());
	setDescription(	"An dieser Stelle werden die Verbindungseinstellungen zur Datenbank konfiguriert. " +
					"Derzeit wird nur die PostgreSQL-Datenbank unterstützt. ");
	}
	
	/**
	 * Hier werden die Einstellungsfelder konfiguriert
	 */
	public void createFieldEditors() 
	{
	
	prefix = new StringFieldEditor(PreferenceConstants.PSQL_PREFIX, "Verbindungsprefix:", 14, getFieldEditorParent());
	addField(prefix);
	
	ip = new StringFieldEditor(PreferenceConstants.PSQL_IP, "IP-Adresse der Datenbank:", 14, getFieldEditorParent());
	addField(ip);
	
	port = new IntegerFieldEditor(PreferenceConstants.PSQL_PORT, "Port der Datenbank:", getFieldEditorParent(),5);
	addField(port);
	
	db = new StringFieldEditor(PreferenceConstants.PSQL_DATABASE, "Name des Datenbankschemas:", 14, getFieldEditorParent());
	addField(db);
	
	user = new StringFieldEditor(PreferenceConstants.PSQL_USER, "Name des Datenbankbenutzers:", 14, getFieldEditorParent());
	addField(user);

	passwort = new StringFieldEditor(PreferenceConstants.PSQL_PASSWORT, "Passwort des Datenbankbenutzers:", 14, getFieldEditorParent());
	addField(passwort);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	protected void performDefaults() 
	{
	prefix.loadDefault();
	ip.loadDefault();
	port.loadDefault();
	db.loadDefault();
	user.loadDefault();
	passwort.loadDefault();
	}
	
}