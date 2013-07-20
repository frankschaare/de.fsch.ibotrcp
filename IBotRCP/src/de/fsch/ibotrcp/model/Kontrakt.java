package de.fsch.ibotrcp.model;

import org.eclipse.core.databinding.observable.value.WritableValue;


public class Kontrakt extends ModelObject 
{
private int iID = 0;
private String strSymbol = "QQQQ";


	public Kontrakt() 
	{


	}

	public int getIID() {
		return iID;
	}

	public void setIID(int iid) {
		iID = iid;
	}

	public String getStrSymbol() {
		return strSymbol;
	}

	public void setStrSymbol(String newValue) 
	{
	String oldValue = this.strSymbol;
	this.strSymbol = newValue;
	//firePropertyChange("strSymbol", oldValue, newValue);
	}

}
