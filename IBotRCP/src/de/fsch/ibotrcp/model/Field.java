/**
 * 
 */
package de.fsch.ibotrcp.model;

import java.lang.reflect.Type;

/**
 * @author Administrator
 *
 */
public class Field
{
private String ElementName = null;
private Type elementType = null;
private Object parent = null;
private boolean readOnly = true;
private boolean hasChildren = false;

	/**
	 * 
	 */
	public Field() 
	{
		// TODO Auto-generated constructor stub
	}


	public String getElementName() 
	{
	return this.ElementName;
	}

	public String getElementType() 
	{
	return this.elementType.toString();
	}

	public Object getParent() 
	{
	return this.parent;
	}


	public boolean isReadOnly() {
		return readOnly;
	}


	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}


	public boolean isHasChildren() {
		return hasChildren;
	}


	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}


	public void setElementName(String elementName) {
		ElementName = elementName;
	}


	public void setElementType(Type elementType) {
		this.elementType = elementType;
	}


	public void setParent(Object parent) {
		this.parent = parent;
	}


}
