package de.fsch.ibotrcp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import de.fsch.ibotrcp.Activator;
import de.fsch.ibotrcp.ApplicationActionBarAdvisor;
import de.fsch.ibotrcp.action.DataBaseAction;
import de.fsch.ibotrcp.controller.DBManager;
import de.fsch.ibotrcp.controller.IController;

public class IndexTreeContentProvider implements ILazyTreeContentProvider
{
private TreeViewer viewer;
private List<Index> elements = null;
private List<Index> model = new ArrayList<Index>();

	public IndexTreeContentProvider(TreeViewer viewer) 
	{
	this.viewer = viewer;
	this.model = createModel();
	
	DataBaseAction saveAction = (DataBaseAction) ApplicationActionBarAdvisor.getMenuAction("IBotRCP.saveStock");
	IPropertyChangeListener propertyChangeListener = new IPropertyChangeListener() 
 		{
		public void propertyChange(PropertyChangeEvent event) {
			if (event.getSource() instanceof Stock) 
			{
			updateModel(event);	
			}
			
		}
	};
	saveAction.addPropertyChangeListener(propertyChangeListener);
	}
	
	public void updateModel(PropertyChangeEvent event)
	{
	List<Index> oldModel = getModel();
	List<Index> newModel = getModel();
	Stock stock = (Stock) event.getSource();
	Index toUpdate = null;
	
		if (Activator.DEBUG) {Activator.log("IndexTreeContentProvider#propertyChangeListener hat aktualisierte Aktie erhalten: " + stock.getName(), null);}
	
		for (Index index : newModel) 
		{
			if (index.getName().equalsIgnoreCase(stock.getIndexes().get(0))) 
			{
			toUpdate = index;
			
				ArrayList<Stock> stocks = index.getStocks();
				for (int i = 0; i < stocks.size(); i++) 
				{
					Stock oldStock = stocks.get(i);
					if (oldStock.getIsin().equalsIgnoreCase(stock.getIsin())) 
					{
					// TODO das ist scheisse, muss anders gemacht werden !	
					stocks.set(i, stock);
				    
					}
				}
			}
		}
	this.model = newModel;
	viewer.refresh(toUpdate, false);
	
	}
	public List<Index> createModel() 
	{
	HashMap<String,Index> indexes = DBManager.getDefault().getIndexes();

	for (String key : indexes.keySet()){ 
		model.add(indexes.get(key));
	}
	/* Beispiel für manuelle Sortierung
	Object[] a = elements.toArray(); 
	Arrays.sort( a ); 
		for (Object object : a) 
		{
		model.add((Index) object);	
		}
	*/	
	return model;
	}

	public void dispose() 
	{

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
	{
	this.elements = (List<Index>) newInput;
	}

    /**
     * Gibt das Elternelement des übergebenen Objektes, oder <code>null</code>,  
     * wenn das Elternelement nicht ermittelt werden konnte. 
     * In diesem Fall der tree-structured viewer den
     * übergebenen Knoten nicht korrekt öffnen, wenn er angefordert wird.
     *
     * @param element Das Element
     * @return Das Elternelement, oder <code>null</code> wenn es nicht vorhanden ist
     *   oder nicht ermittelt werden konnte
     */
	public Object getParent(Object element) 
	{
	Stock stock = null;
		if (element instanceof Stock) 
		{
		stock =  (Stock) element;
		return stock.getIndexes().get(0);
		}
	return elements;
	}

	/**
	 * Wird aufgerufen, wenn der TreeViewer die aktuelle Anzahl der Kindelemente
	 * des übergebenen Elementes wissen muss, beispielsweise von {@link TreeViewer#refresh()} 
	 * und {@link TreeViewer#setInput(Object)}. Wenn der Content provider das übergebene
	 * Element kennt, sollte er
	 * {@link TreeViewer#setChildCount(Object, int)} aufrufen. Ist die Anzahl
	 * der Kindelemente bereits korrekt, muss nichts weiter gemacht werden
	 * 
	 * @param element
	 *            Das Element, für das die aktuelle Anzahl der Kindelemente benötigt wird
	 *            oder des viewer's input, wenn die Anzahl der Rootelemente angefragt wird 
	 * @param currentChildCount 
	 * 			  die aktuelle Anzahl der Kindelemente, welche ggf. aktualisiert werden muss.
	 */
	public void updateChildCount(Object element, int currentChildCount) 
	{
	int length = 0;
	
		if (element instanceof Index) 
		{
			Index node = (Index) element;
			length =  node.getStocks().size();
		} 
		
		if(element == elements)
		{
		length = elements.size();
		}
	viewer.setChildCount(element, length);	
	}

	/**
	 * Wird aufgerufen, wenn ein bisher leeres Element im TreeViewer sichtbar wird. 
	 * Wenn der Content provider das Kindelement für das übergebene Elternelement für diesen
	 * Index kennt, sollte er  {@link TreeViewer#replace(Object, int, Object)} aufrufen. 
	 * Der Content provider sollte ausserdem die Anzahl der Kindelemente für jedes ersetzte Element 
	 * aktualisieren, in dem {@link TreeViewer#setChildCount(Object, int)} aufgerufen wird.
	 *  
	 * Wenn die Anzahl der übergebenen Kindelemente bereits korrekt ist, muss
	 * setChildCount nicht aufgerufen werden, weil der Aufruf der replace  Methode
	 * die Anzahl der Kindelemente nicht verändert.
	 * 
	 * <strong>HINWEIS:</strong> #updateElement(int index) kann benutzt werden, 
	 * um ausgewählten Werte der Selection zu ermitteln. Wenn TableViewer#replace(Object, int) 
	 * nicht aufgerufen wird, bevor diese Methode verlassen wird, fehlt die Auswahl
	 * möglicherweise oder enhält veraltete Elemente. In diesem Fall sollte der Wert
	 * der Auswahl ermittelt werden nachdem replace() aufgerufen wurde.
	 * 
	 * @param parent
	 *            Das Elternelement, oder der Input des viewer's wenn das Element
	 *            ein Root Element ist.
	 * @param index
	 *            Der Index des Elementes, welches im Baum aktualisiert werden muss.
	 */
	public void updateElement(Object parent, int index) 
	{
	Object element;
		if (parent instanceof Index) 
		{
		Index currentIndex = (Index) parent; 	
		element = currentIndex.getStocks().get(index);	
		} 
		else 
		{
		element =  elements.get(index);
		}

	viewer.replace(parent, index, element);
	updateChildCount(element, -1);
	}


	public List<Index> getModel() {
		return model;
	}	

}
