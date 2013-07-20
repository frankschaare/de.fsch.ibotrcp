package de.fsch.ibotrcp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) 
    {
    super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) 
    {
    return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() 
    {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();

        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        
        /**
         * Sets whether the underlying workbench window has a progress indicator.
         * 
         * @param show <code>true</code> for a progress indicator, and <code>false</code>
         * for no progress indicator
         */
        configurer.setShowProgressIndicator(true); 
        configurer.setTitle("IBotRCP");

    }

	public void createWindowContents(Shell shell) 
	{
	super.createWindowContents(shell);
	shell.setMaximized(true);
	}
}
