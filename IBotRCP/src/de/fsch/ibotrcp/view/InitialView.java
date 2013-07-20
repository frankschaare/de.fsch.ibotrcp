package de.fsch.ibotrcp.view;

import javax.swing.Box.Filler;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;

public class InitialView extends ViewPart 
{
public static final String ID = "de.fsch.ibotrcp.view.InitialView";
private Composite top = null;
private Button buttonCancel = null;
private Button buttonOK = null;

private Label filler = null;


	public InitialView() 
	{
			// TODO Auto-generated constructor stub
	}

	public void createPartControl(Composite parent) 
	{
    GridData gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.grabExcessVerticalSpace = false;
   
    top = new Composite(parent, SWT.NONE);
        
	GridLayout layout = new GridLayout();
	layout.numColumns = 3;
	layout.marginHeight = 0;
	layout.marginWidth = 0;
	layout.makeColumnsEqualWidth = false;

    top.setLayout(layout);
    
    
    buttonCancel = ControlFactory.getCancelButton(top);
    filler = ControlFactory.getFiller(top);
    buttonOK = ControlFactory.getOKButton(top); 
	}

	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
