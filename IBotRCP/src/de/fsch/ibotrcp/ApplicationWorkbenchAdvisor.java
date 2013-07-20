package de.fsch.ibotrcp;

import java.io.PrintStream;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor 
{
private static final String PERSPECTIVE_ID = "IBotRCP.perspective";

public static final String CONSOLE_NAME = "IBot Console";
private static MessageConsole console = null;

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) 
    {
    return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	/*
	 * Wird nach initialize() aufgerufen.
	 * Hier wird die Console initialisiert. 
	 */
	public void preStartup() 
	{
	getConsole(CONSOLE_NAME);
	
	super.preStartup();
	}
	
	/**
	 * Initiert eine Console mit dem vorgegebenen Namen. Falls keine 
	 * entsprechende Console existiert, wird eine neue Instanz zurückgegeben. 
	 */
	public static MessageConsole getConsole(String name)
	{
	ConsolePlugin plugin = ConsolePlugin.getDefault();
	IConsoleManager conManager = plugin.getConsoleManager();
	IConsole[] existing = conManager.getConsoles();
		for (int i = 0; i < existing.length; i++) 
		{
			if (name.equals(existing[i].getName())) 
			{
			return (MessageConsole) existing[i];	
			}
		}
		
	// Keine Instanz gefunden, es wird eine neue Instanz erzeugt:
	console = new MessageConsole(name, null);
	
	MessageConsoleStream stream = console.newMessageStream();
	System.setOut(new PrintStream(stream));
	System.setErr(new PrintStream(stream));

	conManager.addConsoles(new IConsole[]{console});
	
	return console;
	}


}
