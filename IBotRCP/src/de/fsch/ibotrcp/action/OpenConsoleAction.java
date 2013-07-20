package de.fsch.ibotrcp.action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleView;

import de.fsch.ibotrcp.ApplicationWorkbenchAdvisor;


public class OpenConsoleAction extends Action 
{
	
private final IWorkbenchWindow window;
	//private int instanceNum = 0;
private final String viewId;

	
	public OpenConsoleAction(IWorkbenchWindow window, String label) 
	{
		this.window = window;
		this.viewId = IConsoleConstants.ID_CONSOLE_VIEW;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.CMD_OPEN_CONSOLE);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.CMD_OPEN_CONSOLE);
		setImageDescriptor(de.fsch.ibotrcp.Activator.getImageDescriptor("/icons/console_view.gif"));
	}
	

	
	public void run() 
	{
	String consoleName = ApplicationWorkbenchAdvisor.CONSOLE_NAME;
	
		if(window != null) 
		{	
		IWorkbenchPage activePage = window.getActivePage();
			try 
			{
			IConsoleView view = (IConsoleView) activePage.showView(viewId);
			view.display(ApplicationWorkbenchAdvisor.getConsole(consoleName));
			} 
			catch (PartInitException e1) 
			{
			e1.printStackTrace();
			}
			
		System.out.println("Cool, meine erste JFace Console. Hab ich ganz allein gemacht ;-)");
		}
	}
}
