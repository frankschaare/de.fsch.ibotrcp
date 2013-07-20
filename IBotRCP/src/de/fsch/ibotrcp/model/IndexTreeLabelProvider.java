/**
 * 
 */
package de.fsch.ibotrcp.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import cbg.article.model.BoardGame;
import cbg.article.model.Book;
import cbg.article.model.MovingBox;
import cbg.article.treeviewer.ui.TreeViewerPlugin;
import de.fsch.ibotrcp.Activator;

/**
 * @author Frank Schaare, 28.09.2007
 *
 */
public class IndexTreeLabelProvider extends LabelProvider 
{
private Map<ImageDescriptor, Image>	imageCache = new HashMap();


	public Image getImage(Object element)
	{
	ImageDescriptor descriptor = null;
	
		if (element instanceof Index) 
		{
		descriptor = Activator.getImageDescriptor("icons/IndexTree_Index.gif");
		} 
		else
		{
		descriptor = Activator.getImageDescriptor("icons/StockIcon16x16.JPG");	
		}	

		//obtain the cached image corresponding to the descriptor
		Image image = (Image)imageCache.get(descriptor);
		if (image == null) {
			image = descriptor.createImage();
			imageCache.put(descriptor, image);
		}
		return image;
	}

	public String getText(Object element) 
	{
	String label = null;
	
		if (element instanceof Index) 
		{
		Index index = (Index) element;
		label = index.getName();
		}
		else 
		{
		Stock stock = (Stock) element;
		label = stock.getName();
		}
	return label;	
	}

	public void dispose() {
		for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
			((Image) i.next()).dispose();
		}
		imageCache.clear();
	}
}
