package de.fsch.ibotrcp.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.widgets.Hyperlink;

public class FormView extends ViewPart 
{
public static final String ID = "de.fsch.ibotrcp.view.FormView";
private Composite top = null;
private FormToolkit toolkit = null;   //  @jve:decl-index=0:visual-constraint=""	
private ScrolledForm scrolledForm = null;
private Hyperlink hyperlink = null;


	public FormView() 
	{
	this.toolkit = getFormToolkit();
	}

	public void createPartControl(Composite parent) 
	{
    top = new Composite(parent, SWT.NONE);
    createScrolledForm();
	}

	public void setFocus() 
	{

	}

	public void dispose() 
	{
	super.dispose();
	}

	/**
	 * This method initializes formToolkit	
	 * 	
	 * @return org.eclipse.ui.forms.widgets.FormToolkit	
	 */
	private FormToolkit getFormToolkit() {
		if (toolkit == null) {
			toolkit = new FormToolkit(Display.getCurrent());
		}
		return toolkit;
	}

	/**
	 * This method initializes scrolledForm	
	 *
	 */
	private void createScrolledForm() {
		scrolledForm = toolkit.createScrolledForm(top);
		scrolledForm.setBounds(Display.getCurrent().getBounds());
		Text text = toolkit.createText(scrolledForm.getBody(), "Hallo Welt !");
		hyperlink = toolkit.createHyperlink(scrolledForm.getBody(), "Hyperlink", SWT.NONE);
		hyperlink.addHyperlinkListener(new HyperlinkAdapter() {
			   public void linkActivated(HyperlinkEvent e) {
				    System.out.println("Link activated!");
				   }
				  });
		scrolledForm.getBody().setLayout(new GridLayout());
	}


}
