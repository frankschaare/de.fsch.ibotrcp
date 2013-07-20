package de.fsch.ibotrcp.action;

/**
 * Interface defining the application's command IDs.
 * Key bindings can be defined for specific commands.
 * To associate an action with a command, use IAction.setActionDefinitionId(commandId).
 *
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface ICommandIds {

	public static final String CMD_OPEN_VIEW = "IBotRCP.openView";
    public static final String CMD_OPEN_MESSAGE = "IBotRCP.openMessage";
    public static final String CMD_OPEN_CONSOLE = "IBotRCP.openConsole";
    public static final String CMD_CONNECT_TWS = "IBotRCP.connectTWS";
	public static final String CMD_DISCONNECT_TWS = "IBotRCP.disconnectTWS";
	public static final String CMD_SAVE_STOCK = "IBotRCP.saveStock";
    
}
