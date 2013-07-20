package de.fsch.ibotrcp.view;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.ib.client.Contract;

import de.fsch.ibotrcp.controller.IBotClient;
import de.fsch.ibotrcp.controller.IBotWrapper;
import de.fsch.ibotrcp.model.Kontrakt;

public class MarketDataView extends ViewPart implements IModelView 
{
public static final String ID = "de.fsch.ibotrcp.view.MarketDataView";
private Kontrakt kontrakt = new Kontrakt();  


private Composite top = null;
private Label l_ID = null;
private Label l_Symbol = null;
private Text t_Symbol = null;
private Label l_Tags = null;
private Text t_Tags = null;
private Text t_ID = null;
private Label l_Description = null;

public MarketDataView() 
	{
			// TODO Auto-generated constructor stub
	}

	public void createPartControl(Composite parent) 
	{
	GridData gridData4 = new GridData();
	gridData4.horizontalSpan = 2;
	gridData4.verticalAlignment = GridData.CENTER;
	gridData4.grabExcessHorizontalSpace = false;
	gridData4.heightHint = -1;
	gridData4.horizontalAlignment = GridData.BEGINNING;
	GridData gridData2 = new GridData();
	gridData2.grabExcessHorizontalSpace = true;
	gridData2.verticalAlignment = GridData.CENTER;
	gridData2.horizontalAlignment = GridData.FILL;
	GridLayout gridLayout = new GridLayout();
	gridLayout.numColumns = 2;
	gridLayout.marginWidth = 10;
	gridLayout.marginHeight = 10;
	top = new Composite(parent, SWT.NONE);
	top.setLayout(gridLayout);
	l_Description = new Label(top, SWT.SHADOW_NONE);
	l_Description.setText("Markdaten für folgendes Symbol holen:");
	l_Description.setFont(new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD));
	l_Description.setLayoutData(gridData4);
	l_ID = new Label(top, SWT.NONE);
	l_ID.setText("ID:");
	t_ID = new Text(top, SWT.BORDER);
	t_ID.setText("0");
	l_Symbol = new Label(top, SWT.NONE);
	l_Symbol.setText("Symbol:");
	t_Symbol = new Text(top, SWT.BORDER);
	l_Tags = new Label(top, SWT.NONE);
	l_Tags.setText("Generic Tick Tags:");
	t_Tags = new Text(top, SWT.BORDER);
	t_Tags.setLayoutData(gridData2);
    GridData gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.grabExcessVerticalSpace = false;
    
    
	// DEBUG - print to show value change
/*	kontrakt.wvSymbol.addValueChangeListener(
			new IValueChangeListener() 
			{
				public void handleValueChange(ValueChangeEvent event) 
				{
				System.out.println("Value 1: " + kontrakt.wvSymbol.getValue());
				l_Tags.setText((String) kontrakt.wvSymbol.getValue());
				}
			});*/
    
    bindGUI();
    
    
	}

	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void bindGUI() 
	{
	dbc.bindValue(SWTObservables.observeText(t_Symbol, SWT.FocusOut), BeansObservables.observeValue(kontrakt, "strSymbol"), null, null);	
	}

}
