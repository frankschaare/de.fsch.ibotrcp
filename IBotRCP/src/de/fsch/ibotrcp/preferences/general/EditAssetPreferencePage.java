package de.fsch.ibotrcp.preferences.general;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
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

public class EditAssetPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage 
{
	public EditAssetPreferencePage() 
	{
	super(GRID);
	
	setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setTitle("Einstellungen für den EditAssetView");
		setDescription("Der im View integrierte Browser navigiert, abhängig vom aktuellen Feld, zu vordefinierten URLs. Hier können die jeweiligen URLs definiert werden. Wird eine Variable eingegeben (z.b. {symbol}), wird versucht, die entsprechende Getter-Methode der Stock Bean auszulesen und in die URL einzufügen. ");
	}
	
	/**
	 * Hier werden die Einstellungsfelder konfiguriert
	 */
	public void createFieldEditors() 
	{
	addField(new StringFieldEditor(PreferenceConstants.VIEWS_EDITASSET_DEFAULT, "Default URL:", getFieldEditorParent()));
			
	Composite fieldEditorParent = getFieldEditorParent();
	final StringFieldEditor stringFieldEditor = new StringFieldEditor(PreferenceConstants.VIEWS_EDITASSET_NAME, "Name:", fieldEditorParent);
	final Text text = stringFieldEditor.getTextControl(fieldEditorParent);
	text.setToolTipText("Die Namenssuche wird verwendet, wenn das Feld Symbol leer ist.");
	addField(stringFieldEditor);
		
	addField(new StringFieldEditor(PreferenceConstants.VIEWS_EDITASSET_SYMBOL, "Symbol:", getFieldEditorParent()));

	addField(new StringFieldEditor(PreferenceConstants.VIEWS_EDITASSET_IB_PREFIX, "IB Contract Suche:", getFieldEditorParent()));

	addField(new StringFieldEditor(PreferenceConstants.VIEWS_EDITASSET_IB_SUFFIX, "IB Suchparameter:", getFieldEditorParent()));

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