package de.fsch.ibotrcp.view;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import de.fsch.ibotrcp.controller.DBManager;
import de.fsch.ibotrcp.model.Stock;

public class GermanStocksView extends ViewPart
{
private Table table;
private TableViewer tableViewer;	
public static final String ID = "de.fsch.ibotrcp.view.GermanStocksView";

	public GermanStocksView()
	{
	
	}

	@Override
	public void createPartControl(Composite parent)
	{
		final GridLayout parentLayout = new GridLayout();
		parentLayout.verticalSpacing = 1;
		parentLayout.marginHeight = 1;
		parentLayout.marginWidth = 1;
		parentLayout.horizontalSpacing = 1;
		parent.setLayout(parentLayout);
		/*
		final Composite top = new Composite(parent, SWT.NONE);
		top.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		top.setLayout(new FillLayout());
		 */
		final Composite bottom = new Composite(parent, SWT.NONE);
		bottom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		bottom.setLayout(new FillLayout());
		initializeToolBar();

		tableViewer = new TableViewer(bottom, SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
	
		TableColumnLayout ad = new TableColumnLayout();
		bottom.setLayout(ad);

		TableColumn cIBSymbol = new TableColumn(table,SWT.LEFT);
		cIBSymbol.setText("IBSymbol");
		cIBSymbol.setMoveable(true);
		ad.setColumnData(cIBSymbol, new ColumnWeightData(100, 45));
		
		ColumnViewerSorter iBSymbolSorter = new ColumnViewerSorter(tableViewer, cIBSymbol) 
		{
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				Stock s1 = (Stock) e1;
				Stock s2 = (Stock) e2;
				
				String str1 = s1.getIbSymbol();
					if (str1 == null)
					{
					str1 = "";	
					}
				String str2 = s2.getIbSymbol();
				if (str2 == null)
				{
				str2 = "";	
				}
				return str1.compareToIgnoreCase(str2);
			}
		};
		
		TableColumn cName = new TableColumn(table,SWT.LEFT);
		cName.setText("Name");
		cName.setMoveable(true);
		ad.setColumnData(cName, new ColumnWeightData(90, 160));
		
		ColumnViewerSorter cNameSorter = new ColumnViewerSorter(tableViewer, cName) 
		{
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				Stock s1 = (Stock) e1;
				Stock s2 = (Stock) e2;
				
				String str1 = s1.getName();
					if (str1 == null)
					{
					str1 = "";	
					}
				String str2 = s2.getName();
				if (str2 == null)
				{
				str2 = "";	
				}
				return str1.compareToIgnoreCase(str2);
			}
		};		
		
		TableColumn cISIN = new TableColumn(table,SWT.LEFT);
		cISIN.setText("ISIN");
		cISIN.setMoveable(true);
		ad.setColumnData(cISIN, new ColumnWeightData(90, 80));
		
		ColumnViewerSorter cISINSorter = new ColumnViewerSorter(tableViewer, cISIN) 
		{
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				Stock s1 = (Stock) e1;
				Stock s2 = (Stock) e2;
				
				String str1 = s1.getIsin();
					if (str1 == null)
					{
					str1 = "";	
					}
				String str2 = s2.getIsin();
				if (str2 == null)
				{
				str2 = "";	
				}
				return str1.compareToIgnoreCase(str2);
			}
		};		
		
		TableColumn cWKN = new TableColumn(table,SWT.LEFT);
		cWKN.setText("WKN");
		cWKN.setMoveable(true);
		ad.setColumnData(cWKN, new ColumnWeightData(90, 80));
		
		ColumnViewerSorter cWKNSorter = new ColumnViewerSorter(tableViewer, cWKN) 
		{
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				Stock s1 = (Stock) e1;
				Stock s2 = (Stock) e2;
				
				String str1 = s1.getWkn();
					if (str1 == null)
					{
					str1 = "";	
					}
				String str2 = s2.getWkn();
				if (str2 == null)
				{
				str2 = "";	
				}
				return str1.compareToIgnoreCase(str2);
			}
		};	
		
		
		TableColumn cPrimaryIndex = new TableColumn(table,SWT.LEFT);
		cPrimaryIndex.setText("Index");
		cPrimaryIndex.setMoveable(true);
		ad.setColumnData(cPrimaryIndex, new ColumnWeightData(90, 80));
		
		ColumnViewerSorter cIndexSorter = new ColumnViewerSorter(tableViewer, cPrimaryIndex) 
		{
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				Stock s1 = (Stock) e1;
				Stock s2 = (Stock) e2;
				
				String str1 = s1.getPrimaryIndex();
				if (str1 == null)
				{
					str1 = "";	
				}
				String str2 = s2.getPrimaryIndex();
				if (str2 == null)
				{
					str2 = "";	
				}
				return str1.compareToIgnoreCase(str2);
			}
		};		

		TableColumn cStockQuantum = new TableColumn(table,SWT.LEFT);
		cStockQuantum.setText("AnzAktien");
		cStockQuantum.setMoveable(true);
		ad.setColumnData(cStockQuantum, new ColumnWeightData(90, 80));
		
		/*
		TableColumn cBidSize = new TableColumn(table,SWT.LEFT);
		cBidSize.setText("BidSize");
		ad.setColumnData(cBidSize, new ColumnWeightData(90, 80));
		
		TableColumn cAskPrice = new TableColumn(table,SWT.LEFT);
		cAskPrice.setText("AskPrice");
		ad.setColumnData(cAskPrice, new ColumnWeightData(90, 80));
		
		TableColumn cAskSize = new TableColumn(table,SWT.LEFT);
		cAskSize.setText("AskSize");
		ad.setColumnData(cAskSize, new ColumnWeightData(90, 80));
		
		TableColumn cDayHigh = new TableColumn(table,SWT.LEFT);
		cDayHigh.setText("DayHigh");
		ad.setColumnData(cDayHigh, new ColumnWeightData(90, 80));
		
		TableColumn cDayLow = new TableColumn(table,SWT.LEFT);
		cDayLow.setText("DayLow");
		ad.setColumnData(cDayLow, new ColumnWeightData(90, 80));
		*/
		DataBindingContext bindingContext = new DataBindingContext();
		bindGUI(bindingContext);

	}
	
	protected void bindGUI(DataBindingContext bindingContext) 
	{
	// Since we're using a JFace Viewer, we do first wrap our Table...
	// TableViewer peopleViewer = new TableViewer(committers);
		
	// Create a standard content provider
	ObservableListContentProvider indexViewerContentProvider = new ObservableListContentProvider();
	tableViewer.setContentProvider(indexViewerContentProvider);
		
	/**
	 * Die Arrtibut Map empfängt drei Attribute im Konstruktor:
	 * <ul>
	 * <li>IObservableSet domain</li>
	 * <li>Class beanClass</li>
	 * <li>String[] propertyNames</li>
	 * </ul>
	 */
	IObservableMap[] attributeMaps = 	BeansObservables.observeMaps(
										indexViewerContentProvider.getKnownElements(), 
										Stock.class,
										new String[] {"symbol", "name", "isin" , "wkn" , "primaryIndex" , "stockQuantum"});
	
	/**
	 * Es wird ein LabelProvider definiert, der das IObservableMap[] erhält
	 */
	tableViewer.setLabelProvider(new ObservableMapLabelProvider(attributeMaps));
		
	// Now set the Viewer's input
	tableViewer.setInput(new WritableList(DBManager.getDefault().getGermanStocks(), Stock.class));
	}

	@Override
	public void setFocus()
	{
		// TODO Auto-generated method stub

	}
	private void initializeToolBar()
	{
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}

	private static abstract class ColumnViewerSorter extends ViewerComparator {
		public static final int ASC = 1;
		
		public static final int NONE = 0;
		
		public static final int DESC = -1;
		
		private int direction = 0;
		
		private TableColumn column;
		
		private ColumnViewer viewer;
		
		public ColumnViewerSorter(ColumnViewer viewer, TableColumn column) {
			this.column = column;
			this.viewer = viewer;
			this.column.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					if( ColumnViewerSorter.this.viewer.getComparator() != null ) {
						if( ColumnViewerSorter.this.viewer.getComparator() == ColumnViewerSorter.this ) {
							int tdirection = ColumnViewerSorter.this.direction;
							
							if( tdirection == ASC ) {
								setSorter(ColumnViewerSorter.this, DESC);
							} else if( tdirection == DESC ) {
								setSorter(ColumnViewerSorter.this, NONE);
							}
						} else {
							setSorter(ColumnViewerSorter.this, ASC);
						}
					} else {
						setSorter(ColumnViewerSorter.this, ASC);
					}
				}
			});
		}
		
		public void setSorter(ColumnViewerSorter sorter, int direction) {
			if( direction == NONE ) {
				column.getParent().setSortColumn(null);
				column.getParent().setSortDirection(SWT.NONE);
				viewer.setComparator(null);
			} else {
				column.getParent().setSortColumn(column);
				sorter.direction = direction;
				
				if( direction == ASC ) {
					column.getParent().setSortDirection(SWT.DOWN);
				} else {
					column.getParent().setSortDirection(SWT.UP);
				}
				
				if( viewer.getComparator() == sorter ) {
					viewer.refresh();
				} else {
					viewer.setComparator(sorter);
				}
				
			}
		}

		public int compare(Viewer viewer, Object e1, Object e2) {
			return direction * doCompare(viewer, e1, e2);
		}
		
		protected abstract int doCompare(Viewer viewer, Object e1, Object e2);
	}	
	
}
