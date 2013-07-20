package de.fsch.ibotrcp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ControlFactory
{
private static Button buttonOK = null;
private static Button buttonCancel = null;
private static Label labelFiller = null;

	public ControlFactory() 
	{
			super();
			// TODO Auto-generated constructor stub
	}


	public static Button getOKButton(Composite parent)
	{
    buttonOK = new Button(parent, SWT.PUSH);
    buttonOK.setText("       OK      ");
    buttonOK.setSize(100, 20);

    buttonOK.setLayoutData(new GridData(GridData.END | GridData.CENTER));
    
	return buttonOK;
	}
	
	public static Label getFiller(Composite parent)
	{
	labelFiller = new Label(parent, SWT.NONE);
	labelFiller.setText("");
	labelFiller.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL));
    
	return labelFiller;
	}	
	
	public static Button getCancelButton(Composite parent)
	{
	buttonCancel = new Button(parent, SWT.PUSH);
	buttonCancel.setText("Abbrechen");
	buttonCancel.setSize(100, 20);

	buttonCancel.setLayoutData(new GridData(GridData.BEGINNING | GridData.CENTER));
    
	return buttonCancel;
	}
}
