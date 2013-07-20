/**
 * 
 */
package de.fsch.ibotrcp.view;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.fsch.ibotrcp.model.Asset;
import de.fsch.ibotrcp.model.ClassContentProvider;
import de.fsch.ibotrcp.model.TreeNode;

/**
 * @author Administrator
 *
 */
public class EditAssetViewTreeView extends ViewPart 
{
public static final String ID = "de.fsch.ibotrcp.view.EditAssetView";
private ImageRegistry imageRegistry = JFaceResources.getImageRegistry();
private ClassContentProvider cp = new ClassContentProvider();

	/**
	 * 
	 */
	public EditAssetViewTreeView() 
	{
	// Beispiel für Benutzung der ImageRegistry			
	imageRegistry.put(ImageRegistryConstants.ICON_CLASS_DEFAULT_KEY, AbstractUIPlugin.imageDescriptorFromPlugin("IBotRCP", ImageRegistryConstants.ICON_CLASS_DEFAULT_PATH));	
	imageRegistry.put(ImageRegistryConstants.ICON_FIELD_DEFAULT_KEY, AbstractUIPlugin.imageDescriptorFromPlugin("IBotRCP", ImageRegistryConstants.ICON_FIELD_DEFAULT_PATH));
	imageRegistry.put(ImageRegistryConstants.ICON_METHOD_DEFAULT_KEY, AbstractUIPlugin.imageDescriptorFromPlugin("IBotRCP", ImageRegistryConstants.ICON_METHOD_DEFAULT_PATH));
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent)
	{
	final TreeViewer v = new TreeViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
	v.getTree().setLinesVisible(true);
	v.getTree().setHeaderVisible(true);

				 
	TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(v,new FocusCellOwnerDrawHighlighter(v));
	ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(v) {
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) 
			{
			return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
				|| event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
				|| (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR)
				|| event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
			}
		};
		
	TreeViewerEditor.create(v, focusCellManager, actSupport, ColumnViewerEditor.TABBING_HORIZONTAL
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);
		
	final TextCellEditor textCellEditor = new TextCellEditor(v.getTree());

		
	//Definition erste Spalte plus Editing Support
	TreeViewerColumn column = new TreeViewerColumn(v, SWT.NONE);
	column.getColumn().setWidth(200);
	column.getColumn().setMoveable(false);
	column.getColumn().setText("Klasse");
	
	column.setLabelProvider(new ColumnLabelProvider() 
	{
		public Image getImage(Object element) 
		{
		TreeNode node = (TreeNode) element;	
		return imageRegistry.get(node.getIconKey());			
		}
		public String getText(Object element) 
		{
		return ((TreeNode) element).getName();	
		}

	});

		//Definition zweite Spalte plus Editing Support		
		column = new TreeViewerColumn(v, SWT.NONE);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Wert");
		column.setLabelProvider(new ColumnLabelProvider() 
		{
			public String getText(Object element) 
			{
			return ((TreeNode) element).getValue();
			}
		});
		column.setEditingSupport(new EditingSupport(v) 
		{
		ComboBoxCellEditor ce = null;	
			protected boolean canEdit(Object element) 
			{
			return true;
			}

			protected CellEditor getCellEditor(Object element) 
			{
			TreeNode node = (TreeNode) element;	
				if (node.getName().equalsIgnoreCase("Typ")) 
				{
					if (ce == null) 
					{
					ce = new ComboBoxCellEditor(v.getTree(),cp.getAssetTypes());				
					}
				return ce;	
				}
			return textCellEditor;
			}

			protected Object getValue(Object element) 
			{
			TreeNode treeNode = ((TreeNode) element);
			
				if (treeNode.getName().equalsIgnoreCase("Typ")) 
				{
					String[] items = ((CCombo)ce.getControl()).getItems();
				    for( int i = 0; i < items.length; i++ ) 
				    {
				        if( treeNode.getValue().equals(items[1]) ) 
				        {
				        return new Integer(i);
				        }
				    }

			    return new Integer(-1); 	
				} 
				else 
				{
				return treeNode.getValue();	
				}


			}

			protected void setValue(Object element, Object value) 
			{
			TreeNode treeNode = ((TreeNode) element);
				
				if (treeNode.getName().equalsIgnoreCase("Typ")) 
				{
					int intValue = Integer.parseInt(String.valueOf(value));

					treeNode.setValue(((CCombo)ce.getControl()).getItem(intValue));
					v.update(treeNode, null);	
				} 
				else 
				{
				treeNode.setValue(String.valueOf(value));	
				}				
			}
		});

	v.setContentProvider(cp);
	v.setInput(cp.createModel());

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
