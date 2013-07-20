package de.fsch.ibotrcp.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchWindow;


import de.fsch.ibotrcp.ApplicationActionBarAdvisor;
import de.fsch.ibotrcp.controller.IBotClient;

public class DisconnectTWSAction extends Action
{
private IBotClient   ibot = null;

//static Logger log = Logger.getLogger(OpenViewAction.class);
	
	public DisconnectTWSAction(IWorkbenchWindow window, String label) 
	{
	setText(label);
    
    // The id is used to refer to the action in a menu or toolbar
	setId(ICommandIds.CMD_DISCONNECT_TWS);
    // Associate the action with a pre-defined command, to allow key bindings.
	setActionDefinitionId(ICommandIds.CMD_DISCONNECT_TWS);
		
	setImageDescriptor(de.fsch.ibotrcp.Activator.getImageDescriptor("/icons/DisconnectTWS16.gif"));
	}
	
	public void run() 
	{
	ibot = IBotClient.getDefault();
		
	ibot.eDisconnect();
	System.out.println("Verbindung zu TWS beendet !");
	
	this.setEnabled(false);
	
	IAction connectAction = ApplicationActionBarAdvisor.getMenuAction(ICommandIds.CMD_CONNECT_TWS);
	connectAction.setText("Verbindung zur TWS herstellen");
	connectAction.setImageDescriptor(de.fsch.ibotrcp.Activator.getImageDescriptor("/icons/ConnectTWS16.gif"));
	ApplicationActionBarAdvisor.updateToolbar();
	}

}
