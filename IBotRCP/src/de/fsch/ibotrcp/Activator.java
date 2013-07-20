package de.fsch.ibotrcp;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.fsch.ibotrcp.controller.DBManager;
import de.fsch.ibotrcp.controller.IBotClient;
import de.fsch.ibotrcp.controller.IBotWrapper;
import de.fsch.ibotrcp.controller.WWWManager;
import de.fsch.ibotrcp.preferences.PreferenceConstants;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin 
{
private IBotClient ibot = null;	

// The plug-in ID
public static final String PLUGIN_ID = "IBotRCP";
// The shared instance
private static Activator plugin;

//Die DebugLevel
public static boolean DEBUG = false;
public static boolean INFO = false;
public static boolean WARNING = false;
public static boolean ERROR = false;
private static	int debugLevel = 3;

	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/**
	 * Protokolliert interne Fehler und Debug Informationen
	 * @param code - Plug-spezifischer FehlerCode
	 * @param msg - Die Fehlernachricht
	 * @param e - Trowable, das den Fehler ausgelöst hat 
	 */
	public static void log(String msg, Throwable e) 
	{
	Status status = null;
	
		switch (debugLevel) 
		{
		case 0:
		status = new Status(IStatus.INFO,PLUGIN_ID,msg,e);		
		break;

		case 1:
		status = new Status(IStatus.INFO,PLUGIN_ID,msg,e);
		break;

		case 2:
		status = new Status(IStatus.WARNING,PLUGIN_ID,msg,e);	
		break;		
		
		default:
		status = new Status(IStatus.ERROR,PLUGIN_ID,msg,e);
		break;
		}
	getDefault().getLog().log(status);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception 
	{
	super.start(context);
	DBManager dbManager = new DBManager();
	WWWManager wwwManager = new WWWManager();
	
	int iLevel = Integer.valueOf(getPreferenceStore().getString(PreferenceConstants.GENERAL_DEBUG_LEVEL));
	this.debugLevel = iLevel;
	setDebugLevel();
	
	ibot = new IBotClient(new IBotWrapper());
	}
	
	private void setDebugLevel()
	{
		switch (debugLevel) 
		{
		case 0:
		DEBUG = true;	
		break;

		case 1:
		INFO = true;	
		break;

		case 2:
		WARNING = true;	
		break;		
		
		default:
		ERROR = true;
		break;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception 
	{
	IBotClient.getDefault().setActive(false);	
	
	plugin = null;
	super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

}
