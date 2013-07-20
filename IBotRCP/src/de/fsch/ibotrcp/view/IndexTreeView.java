package de.fsch.ibotrcp.view;

import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import de.fsch.ibotrcp.Activator;
import de.fsch.ibotrcp.model.Index;
import de.fsch.ibotrcp.model.IndexTreeContentProvider;
import de.fsch.ibotrcp.model.IndexTreeLabelProvider;
import de.fsch.ibotrcp.model.Stock;

public class IndexTreeView extends ViewPart implements ISelectionChangedListener
{
public static final String ID = "de.fsch.ibotrcp.view.IndexTreeView";

private TreeViewer v;

private IndexDetailView indexDetailView = new IndexDetailView();
private EditAssetView editAssetView = new EditAssetView();
private ISelectionService selectionService = null;

	/**
	 * Die init Methode registriert die DetailViews
	 * <ul>
	 * <li>IndexDetailView</li>
	 * <li>EditAssetView</li> 
	 * </ul>
	 * als SelectionListener. Diese werden in der dispose Methode wieder deregistriert
	 */
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		indexDetailView = (IndexDetailView) site.getWorkbenchWindow().getActivePage().findView(IndexDetailView.ID);
		editAssetView = (EditAssetView) site.getWorkbenchWindow().getActivePage().findView(EditAssetView.ID);
		
		selectionService = site.getWorkbenchWindow().getSelectionService();
		
		selectionService.addSelectionListener(indexDetailView);
		if (Activator.DEBUG) {Activator.log("IndexTreeView#init: Neuer SelectionListener IndexDetailView registriert.", null);}
		
		selectionService.addSelectionListener(editAssetView);
		if (Activator.DEBUG) {Activator.log("IndexTreeView#init: Neuer SelectionListener EditAssetView registriert.", null);}		
	}

public IndexTreeView() 
	{

	}

	public void createPartControl(Composite parent) 
	{
	parent.setLayout(new FillLayout());
	v = new TreeViewer(parent, SWT.VIRTUAL | SWT.BORDER);
	IndexTreeContentProvider indexCP = new IndexTreeContentProvider(v);
	
	/*
	 * Der Tree wird als Selection Provider registriert. Damit werden andere
	 * Views über Selektionen im Tree informiert, wenn sie einen SelectionListener adaptieren.
	 */
	getSite().setSelectionProvider(v);
	
	v.setLabelProvider(new IndexTreeLabelProvider());
	v.setContentProvider(indexCP);
	v.setUseHashlookup(true);
	
	List<Index> model = indexCP.getModel();
	v.setInput(model);
	v.getTree().setItemCount(model.size());
 
	v.addSelectionChangedListener(this);
	
	//setListeners();
	initializeToolBar();

	}

	/**
	 * Stellt alle weiteren Listener zur Verfügung. Folgende Listener werden hier registriert:
	 * Hier wird auch der IndexDetailView als Listener registriert, der zu
	 * diesem Zeitpunkt noch nicht initialisiert ist.
	 */
	public void setListeners() 
	{
	v.addSelectionChangedListener(indexDetailView);
	}
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}


	public void selectionChanged(SelectionChangedEvent event) 
	{
	IActionBars bars = getViewSite().getActionBars();
	IWorkbenchPage activePage = getSite().getWorkbenchWindow().getActivePage();

		
		// if the selection is empty clear the label
		if(event.getSelection().isEmpty()) 
		{
		return;
		}

	    if(event.getSelection() instanceof IStructuredSelection) 
	    {
	    IStructuredSelection selection = (IStructuredSelection)event.getSelection();
	    
	       	if (selection.getFirstElement() instanceof Index) 
	    	{
				try 
				{
				activePage.showView(IndexDetailView.ID);
				} catch (PartInitException e) 
				{
				e.printStackTrace();
				}
				
			}
	    	else if (selection.getFirstElement() instanceof Stock)
	    	{
	    		/*
				try 
				{
				activePage.showView(EditAssetView.ID);
				} catch (PartInitException e) 
				{
				e.printStackTrace();
				}	
	    		for (Iterator iterator = selection.iterator(); iterator.hasNext();) 
	    		{
	    			Asset node = (Asset) iterator.next();
	    			bars.getStatusLineManager().setMessage(node.getName());	
	    			Job job = new IBotJob(node.getName());	
	    		}
	    		*/		    		
	    	}
	    }
		
	}

	public TreeViewer getTreeViewer() 
	{
	return v;
	}

	/**
	 * Wird der View geschlossen, wird versucht, die registrierten Listener zu entfernen
	 */
	public void dispose() 
	{
		super.dispose();
		v.removeSelectionChangedListener(this);
		selectionService.removeSelectionListener(indexDetailView);
		if (Activator.DEBUG) {Activator.log("IndexTreeView#dispose: SelectionListener IndexDetailView entfernt.", null);}
		
		selectionService.removeSelectionListener(editAssetView);
		if (Activator.DEBUG) {Activator.log("IndexTreeView#dispose: SelectionListener EditAssetView entfernt.", null);}	
	}

}
