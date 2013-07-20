package de.fsch.ibotrcp;

import java.util.HashMap;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import de.fsch.ibotrcp.action.ConnectTWSAction;
import de.fsch.ibotrcp.action.DisconnectTWSAction;
import de.fsch.ibotrcp.action.MessagePopupAction;
import de.fsch.ibotrcp.action.OpenConsoleAction;
import de.fsch.ibotrcp.action.OpenViewAction;
import de.fsch.ibotrcp.action.SaveStockAction;
import de.fsch.ibotrcp.view.MarketDataView;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor 
{
private Action messagePopupAction;
private Action openConsoleAction;
private Action openViewAction;
private Action connectTWSAction;
private Action disconnectTWSAction;
private Action saveStockAction;

private static HashMap actionMap = new HashMap();
private static IToolBarManager toolbar = null;


    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) 
    {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) 
    {
    registerAction(ActionFactory.PRINT.create(window));
    
    IWorkbenchAction aQuit = ActionFactory.QUIT.create(window);
    aQuit.setText(Messages.MenuFileQuitText);
    registerAction(aQuit);

    IWorkbenchAction aOpenPerspective = ActionFactory.OPEN_PERSPECTIVE_DIALOG.create(window);
    aOpenPerspective.setText(Messages.MenuWindowOpenPerspectiveText);
    registerAction(aOpenPerspective);
    
    IWorkbenchAction aEinstellungen = ActionFactory.PREFERENCES.create(window);
    aEinstellungen.setText(Messages.MenuWindowPreferenciesText);
    registerAction(aEinstellungen);
    
    IWorkbenchAction aHilfe = ActionFactory.HELP_CONTENTS.create(window);
    aHilfe.setText(Messages.HelpContentText);
    registerAction(aHilfe);
    
    IWorkbenchAction aUeber = ActionFactory.ABOUT.create(window);
    aHilfe.setText(Messages.AboutText);
    registerAction(aUeber);
    // register(ActionFactory.INTRO.create(window));
    
    connectTWSAction = new ConnectTWSAction(window, "Verbindung zur TWS herstellen");
    registerAction(connectTWSAction);
    
    disconnectTWSAction = new DisconnectTWSAction(window, "Verbindung zur TWS beenden");
    disconnectTWSAction.setEnabled(false);
    registerAction(disconnectTWSAction);
    
    openViewAction = new OpenViewAction(window, "Marktdaten", MarketDataView.ID);
    register(openViewAction);

    messagePopupAction = new MessagePopupAction("Open Message", window);
    registerAction(messagePopupAction);
    
    
    openConsoleAction = new OpenConsoleAction(window, "Console öffnen");
    registerAction(openConsoleAction);
    
    saveStockAction = new SaveStockAction(window, "Aktie speichern"); 
    saveStockAction.setEnabled(false);
    registerAction(saveStockAction);
    }

    private void registerAction(IAction action) 
    {
    	super.register(action);
    	
        String id = action.getId();
        Assert.isNotNull(id, "Action must not have null id");
        actionMap.put(id, action);
    }

    public static IAction getMenuAction(String id) 
    {
    IAction action = (IAction) actionMap.get(id);
	return action;
    }   
    
    public static void updateToolbar() 
    {
    toolbar.update(true);
    }
    
    public static void enableAction(String id) 
    {
    IAction a = (IAction) actionMap.get(id);
    a.setEnabled(true);
    toolbar.update(true);
    }
    
    protected void fillMenuBar(IMenuManager menuBar) 
    {
    // Datei Menu
    MenuManager fileMenu = new MenuManager(Messages.MenuFileAccessor,IWorkbenchActionConstants.M_FILE);
    menuBar.add(fileMenu);
    fileMenu.add(getAction(ActionFactory.PRINT.getId()));
    fileMenu.add(getAction(ActionFactory.QUIT.getId()));
    
    // Fenster Menu
    MenuManager windowMenu = new MenuManager(Messages.MenuWindowAccessor,IWorkbenchActionConstants.M_WINDOW);
    menuBar.add(windowMenu);
    windowMenu.add(getAction(ActionFactory.OPEN_PERSPECTIVE_DIALOG.getId()));
    windowMenu.add(getAction(ActionFactory.PREFERENCES.getId()));

    // Hilfe Menu
    MenuManager helpMenu = new MenuManager(Messages.MenuHelpAccessor,IWorkbenchActionConstants.M_HELP);
    menuBar.add(helpMenu);
    // helpMenu.add(getAction(ActionFactory.INTRO.getId()));
    helpMenu.add(getAction(ActionFactory.HELP_CONTENTS.getId())); 
    helpMenu.add(new Separator(Messages.MenuHelpSeperator));
    helpMenu.add(getAction(ActionFactory.ABOUT.getId()));     
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar) 
    {
        toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "main"));   
        toolbar.add(connectTWSAction);
        toolbar.add(disconnectTWSAction);
        toolbar.add(openConsoleAction);
        toolbar.add(openViewAction);
        toolbar.add(messagePopupAction);
    }

	@Override
	protected void fillStatusLine(IStatusLineManager statusLine) 
	{
	
	} 
    
}
