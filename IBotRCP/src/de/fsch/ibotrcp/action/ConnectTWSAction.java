package de.fsch.ibotrcp.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;


import de.fsch.ibotrcp.Activator;
import de.fsch.ibotrcp.ApplicationActionBarAdvisor;
import de.fsch.ibotrcp.controller.IBotClient;
import de.fsch.ibotrcp.preferences.PreferenceConstants;


public class ConnectTWSAction extends Action
{
private IBotClient   ibot = null;

//static Logger log = Logger.getLogger(OpenViewAction.class);
	
	public ConnectTWSAction(IWorkbenchWindow window, String label) 
	{
	setText(label);
    
    // The id is used to refer to the action in a menu or toolbar
	setId(ICommandIds.CMD_CONNECT_TWS);
    // Associate the action with a pre-defined command, to allow key bindings.
	setActionDefinitionId(ICommandIds.CMD_CONNECT_TWS);
		
	setImageDescriptor(de.fsch.ibotrcp.Activator.getImageDescriptor("/icons/ConnectTWS16.gif"));
	}
	
	public void run() 
	{
	ibot = IBotClient.getDefault();
		
	System.out.println("Versuche Verbindung zur TWS herzustellen...");	
	IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	
	String strIP = store.getString(PreferenceConstants.TWS_IP);
	int iPort = store.getInt(PreferenceConstants.TWS_PORT);
	int iClient = store.getInt(PreferenceConstants.TWS_CLIENT);
	
	System.out.println("Verbinde zur TWS auf IP: " + strIP + ", Port: " + iPort + ", ClientID: " + iClient);
	
	ibot.eConnect(strIP, iPort, iClient);
	
		if (ibot.isConnected()) 
		{
		System.out.println("Verbunden mit TWS,  Server Version " + ibot.serverVersion() + " um " + ibot.TwsConnectionTime());
		setImageDescriptor(de.fsch.ibotrcp.Activator.getImageDescriptor("/icons/ConnectetTWS16.gif"));
		setText("Verbunden zur TWS auf IP: " + strIP + ", Port: " + iPort + ", ClientID: " + iClient);

		ApplicationActionBarAdvisor.enableAction(ICommandIds.CMD_DISCONNECT_TWS);
		}

	}

}
