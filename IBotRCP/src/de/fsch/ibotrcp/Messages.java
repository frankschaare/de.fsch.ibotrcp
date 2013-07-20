package de.fsch.ibotrcp;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "de.fsch.ibotrcp.messages"; //$NON-NLS-1$

	public static String AboutText;

	public static String HelpContentText;

	public static String MenuFileAccessor;

	public static String MenuFileQuitText;

	public static String MenuHelpAccessor;

	public static String MenuHelpSeperator;

	public static String MenuWindowAccessor;

	public static String MenuWindowOpenPerspectiveText;

	public static String MenuWindowPreferenciesText;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
