package de.fsch.ibotrcp.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;


public class MessagePopupAction extends Action 
{
private final IWorkbenchWindow window;

    public MessagePopupAction(String text, IWorkbenchWindow window) 
    {
    super(text);
    this.window = window;
        // The id is used to refer to the action in a menu or toolbar
        setId(ICommandIds.CMD_OPEN_MESSAGE);
        // Associate the action with a pre-defined command, to allow key bindings.
        setActionDefinitionId(ICommandIds.CMD_OPEN_MESSAGE);
        setImageDescriptor(de.fsch.ibotrcp.Activator.getImageDescriptor("/icons/iConnectEnabled16.gif"));
    }

    public void run() 
    {
    	System.out.println("Hoffentlich geht das auf die Console !");
        MessageDialog.openInformation(window.getShell(), "Open", "Du kannst es !");
    }
}