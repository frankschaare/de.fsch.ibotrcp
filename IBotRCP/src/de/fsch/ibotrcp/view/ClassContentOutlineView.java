/**
 * 
 */
package de.fsch.ibotrcp.view;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import de.fsch.ibotrcp.model.ClassContentProvider;
import de.fsch.ibotrcp.view.provider.ClassLabelProvider;

/**
 * @author Administrator
 *
 */
public class ClassContentOutlineView extends ViewPart implements ISelectionChangedListener 
{
public static final String ID = "de.fsch.ibotrcp.view.ClassContentOutlineView";
private SashForm sash;
private TreeViewer viewer;
private DrillDownAdapter drillDownAdapter;
private ClassLabelProvider labelProvider;
	/**
	 * Implementierung eines ASTView zur Verwaltung von Objekten in db4O
	 */
	public ClassContentOutlineView() 
	{
		// TODO Auto-generated constructor stub
	}

	public void createPartControl(Composite parent) 
	{
	sash= new SashForm(parent, SWT.VERTICAL | SWT.SMOOTH);
	viewer = new TreeViewer(sash, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

	/**
	 * </p>
	 * Ein <code>DrillDownAdapter</code> stellt wohl vorgefertigte Actions
	 * für die View zur Verfügung. Wird er eingesetzt, können die internen
	 * Actions "goBack", "goHome", and "goInto" benutzt werden.
	 * </p>
	 * Folgendes muss gemacht werden:
	 * <ul>
	 * <li>Eine <code>TreeViewer</code> Instanz erstellen. </li>
	 * <li>Einen <code>DrillDownAdapter</code> hinzufügen. </li>
	 * <li>Einen Container mit einer Toolbar oder einem Popup Menu erstellen.</li>
	 * </ul>
	 */
	drillDownAdapter = new DrillDownAdapter(viewer);
	
	ClassContentProvider cp = new ClassContentProvider();
	viewer.setContentProvider(cp);
	viewer.setLabelProvider(new ClassLabelProvider());
	
	viewer.setInput(cp.createModel());
	
	/* Folgende Listener müssen noch angefügt werden:
		
	    viewer.addSelectionChangedListener(fSuperListener);
		viewer.addDoubleClickListener(fSuperListener);
		viewer.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (!fCreateBindings && element instanceof Binding)
					return false;
				return true;
			}
		});
		viewer.addFilter(fNonRelevantFilter);
		viewer.addPostSelectionChangedListener(fTrayUpdater);
	*/
	
	/* Actions werden hinzugefügt:
	
		makeActions();
		hookContextMenu();
		hookTrayContextMenu();
		contributeToActionBars();
		getSite().setSelectionProvider(new ASTViewSelectionProvider());
	*/
	
	

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		
	}	

}
