package de.fsch.ibotrcp.model;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

public class DataBindingContextFactory 
{
	public static DataBindingContext create()
	{
	DataBindingContext bindingContext = new DataBindingContext(SWTObservables.getRealm(Display.getDefault()));
	//bindingContext.
	return null;
		
	}
}
