package de.fsch.ibotrcp.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.db4o.query.Predicate;

import de.fsch.ibotrcp.controller.IController;
import de.fsch.ibotrcp.view.ImageRegistryConstants;

/**
 *  Contentprovider für einen Tree mit ITreeContentProvider Implementierung
 *  
 *  Die Methoden werden in folgender Reihenfolge aufgerufen
 *  inputChanged(Viewer viewer, Object oldInput, Object newInput
 *  getElements(Object inputElement)
 *  hasChildren(Object element)
 *  getChildren(Object parentElement)
 *  usw.
 */
public class ClassContentProvider implements ITreeContentProvider, IController
{
private int counter = 0;
private TreeNode root = null;
private Stock stock = null;

	public TreeNode createModel() 
	{
	List<Stock> result = db.query(new Predicate<Stock>() {
				public boolean match(Stock stock) 
				{
				return stock.getName().equalsIgnoreCase("BASF");
				}
			});
	stock = result.get(0);
	
	root = new TreeNode("root",null);
	
	TreeNode asset = new TreeNode("Klasse",stock.getClass().getSimpleName());
	asset.appendChild(new TreeNode("Name", stock.getName()));
	asset.appendChild(new TreeNode("Typ", stock.getType()));
	asset.appendChild(new TreeNode("ISIN", stock.getIsin()));
	asset.appendChild(new TreeNode("WKN", stock.getWkn()));
	
	TreeNode indexes = new TreeNode("Indizes", null);
		for (String index : stock.getIndexes()) 
		{
		indexes.appendChild(new TreeNode("Index", index));	
		}
	
	asset.appendChild(indexes);
	asset.setIconImageRegistryKey(ImageRegistryConstants.ICON_CLASS_DEFAULT_KEY);
	root.appendChild(asset);

	return root;
	}
	
	@Override
	public Object[] getChildren(Object parentElement) 
	{
	System.out.println(counter + " getChildren(Object parentElement)");
	counter++;
	
	return ((TreeNode) parentElement).getChildren().toArray();
	}

	public Object getParent(Object element) 
	{
	System.out.println(counter + " getParent(Object element)");
	counter++;		

	return ((TreeNode) element).getParent();
	}

	
	public boolean hasChildren(Object element) 
	{
	System.out.println(counter + " hasChildren(Object element)");
	counter++;	
	
	return ((TreeNode) element).hasChildren();
	}

	
	public Object[] getElements(Object inputElement) 
	{
	System.out.println(counter + " getElements(Object inputElement)");
	counter++;	

	return root.getChildren().toArray();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
	{
	System.out.println(counter + " inputChanged(Viewer viewer, Object oldInput, Object newInput");
	counter++;		
	}
	
	public void closeConnection() 
	{
		if (!db.close()) 
		{
		db.close();	
		}		
	}

	public String[] getAssetTypes() 
	{
	return stock.getAssetChoices().getAssetTypes();
	}



	
}
