/**
 * 
 */
package de.fsch.ibotrcp.model;

import java.util.ArrayList;

import de.fsch.ibotrcp.view.ImageRegistryConstants;

/**
 * @author Administrator
 *
 */
public class TreeNode
{
private String name = null;
private String value = null;
private TreeNode parent = null;
private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
private boolean hasChildren = false;

/**
 * Der Pfad zum Icon steht standardmässig auf Public Field
 */
private String iconImageRegistryKey = ImageRegistryConstants.ICON_FIELD_DEFAULT_KEY;

	public TreeNode(String name, String value) 
	{
	this.name = name;
	this.value = value;
	}

	public void appendChild(TreeNode node) 
	{
	this.children.add(node);
	this.hasChildren = true;
	this.iconImageRegistryKey = ImageRegistryConstants.ICON_METHOD_DEFAULT_KEY;
	}
	
	public ArrayList<TreeNode> getChildren() 
	{
	return this.children;
	}

	public TreeNode getParent() 
	{
	return this.parent;
	}

	public String getValue() 
	{
	return this.value;
	}

	public boolean hasChildren() 
	{
	return this.hasChildren;
	}

	public void setChildren(ArrayList<TreeNode> children) 
	{
	this.children = children;
	this.hasChildren = true;
	}

	public void setParent(TreeNode parent) 
	{
	this.parent = parent;
	}
	
	public boolean equals(Object object) 
	{
	return super.equals(object);
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public String getIconKey() {
		return iconImageRegistryKey;
	}


	public void setValue(String value) {
		this.value = value;
	}

	public void setIconImageRegistryKey(String iconImageRegistryKey) {
		this.iconImageRegistryKey = iconImageRegistryKey;
	}
}
