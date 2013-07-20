package de.fsch.ibotrcp.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import de.fsch.ibotrcp.view.FormView;
import de.fsch.ibotrcp.view.InitialView;


public class OpenViewAction extends Action 
{
	
	private final IWorkbenchWindow window;
	private final String viewId;
	
	public OpenViewAction(IWorkbenchWindow window, String label, String viewId) {
		this.window = window;
		this.viewId = viewId;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.CMD_OPEN_VIEW);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.CMD_OPEN_VIEW);
		setImageDescriptor(de.fsch.ibotrcp.Activator.getImageDescriptor("/icons/iConnectEnabled16.gif"));
	}
	
	public void run() 
	{
		if(window != null) 
		{	
			IWorkbenchPage activePage = window.getActivePage();
			activePage.hideView(activePage.findView(InitialView.ID));
				try 
				{
				activePage.showView(viewId);
				} catch (PartInitException e) 
				{
				e.printStackTrace();
				}

		}
	}
}
