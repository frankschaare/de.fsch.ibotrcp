/**
 * 
 */
package de.fsch.ibotrcp.view;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.IStateListener;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.part.ViewPart;

/**
 * @author Frank Schaare , 11.07.2007
 * 
 * Abstrakte Superklasse für alle Views
 *
 */
public abstract class AbstractMasterView extends ViewPart 
{
protected IActionBars bars;
protected ISelectionService selectionService;
protected DataBindingContext bindingContext = new DataBindingContext();
// Noch mal nachsehen, was ViewAction ist	
//protected Map<Integer, ViewAction> actions = new HashMap<Integer, ViewAtion>(11);
protected boolean registered;
	
	/**
	 * 
	 */
	public AbstractMasterView() 
	{
	
	}
	
	/**
	 * View Toolbar füllen
	 */
	public void fillViewToolbar()
	{
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
