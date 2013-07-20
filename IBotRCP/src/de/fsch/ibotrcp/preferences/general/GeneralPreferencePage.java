package de.fsch.ibotrcp.preferences.general;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.swtdesigner.preference.ComboFieldEditor;

import de.fsch.ibotrcp.Activator;
import de.fsch.ibotrcp.preferences.PreferenceConstants;

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

public class GeneralPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage 
{
private String[][] labelsAndValues;

	public GeneralPreferencePage() 
	{
	super(GRID);
	
	setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setTitle("Allgemeine Einstellungen");
	}
	
	/**
	 * Hier werden die Einstellungsfelder konfiguriert
	 */
	public void createFieldEditors() 
	{
	labelsAndValues = new String[4][2];
	labelsAndValues[0][0] = "Debug";
	labelsAndValues[0][1] = "0";
	labelsAndValues[1][0] = "Info";
	labelsAndValues[1][1] = "1";
	labelsAndValues[2][0] = "Warning";
	labelsAndValues[2][1] = "2";	
	labelsAndValues[3][0] = "Error";
	labelsAndValues[3][1] = "3";	
		{
		Composite fieldEditorParent = getFieldEditorParent();
		final ComboFieldEditor comboFieldEditor = new ComboFieldEditor(
				PreferenceConstants.GENERAL_DEBUG_LEVEL, 
				"Debug Level:", 
				labelsAndValues, 
				fieldEditorParent);
		final Combo combo = comboFieldEditor.getComboBoxControl(fieldEditorParent);
		combo.setToolTipText("Setzt den Debug Level für die Applikation.");


		
		addField(comboFieldEditor);
		}
	


	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	protected void performDefaults() 
	{

	}
	
}