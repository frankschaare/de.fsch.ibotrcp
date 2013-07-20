/**
 * 
 */
package de.fsch.ibotrcp.view;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

import de.fsch.ibotrcp.Activator;
import de.fsch.ibotrcp.job.MarketDataRow;
import de.fsch.ibotrcp.job.RequestHistoricalDataJob;
import de.fsch.ibotrcp.model.Asset;
import de.fsch.ibotrcp.model.DataRequest;
import de.fsch.ibotrcp.model.Index;
import de.fsch.ibotrcp.model.Stock;

/**
 * @author Administrator
 *
 */
public class IndexDetailView extends AbstractMasterView implements ISelectionListener, ISelectionChangedListener
{
private Text askSize;
private Text askPrice;
private Text bidsize;
private Text bidPrice;
private Text dayLow;
private Text dayHigh;
private Text diffAbsolut;
private Text diffPercent;
private Text lastPrice;
private Text ibSymbol;
private Text name;
public static final String ID = "de.fsch.ibotrcp.view.IndexDetailView";
private Table table;
private TableViewer tableViewer;
private IndexTableContentProvider contentProvider = null;
private Index selectedIndex = new Index();

private MarketDataRow marketData;
DataRequest dr;


	/**
	 * Der Konstruktor registriert den Content Provider als Selection Listener.
	 * Damit wird er bei eingehenden Änderungen informiert.
	 */
	public IndexDetailView() 
	{
	super();
	}

	public void init(IViewSite site) throws PartInitException 
	{
		super.init(site);
		contentProvider = new IndexTableContentProvider();
		marketData = new MarketDataRow();
		site.setSelectionProvider(marketData);
	}

	public void createPartControl(Composite parent) 
	{
	final GridLayout gridLayout_1 = new GridLayout();
	gridLayout_1.verticalSpacing = 0;
	gridLayout_1.marginWidth = 1;
	gridLayout_1.marginHeight = 1;
	gridLayout_1.horizontalSpacing = 0;
	parent.setLayout(gridLayout_1);

	final Composite top = new Composite(parent, SWT.NONE);
	top.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	final GridLayout gridLayout = new GridLayout();
	gridLayout.marginWidth = 0;
	gridLayout.marginHeight = 0;
	gridLayout.verticalSpacing = 0;
	gridLayout.horizontalSpacing = 0;
	gridLayout.numColumns = 11;
	top.setLayout(gridLayout);
	super.createPartControl(parent);

	ibSymbol = new Text(top, SWT.READ_ONLY);
	ibSymbol.setText("ibSymbol");
	final GridData gd_ibSymbol = new GridData(SWT.FILL, SWT.CENTER, true, false);
	ibSymbol.setLayoutData(gd_ibSymbol);

	name = new Text(top, SWT.READ_ONLY);
	name.setText("name");
	final GridData gd_name = new GridData(SWT.FILL, SWT.CENTER, true, false);
	name.setLayoutData(gd_name);

	lastPrice = new Text(top, SWT.READ_ONLY);
	lastPrice.setText("lastPrice");
	final GridData gd_lastPrice = new GridData(SWT.FILL, SWT.CENTER, true, false);
	lastPrice.setLayoutData(gd_lastPrice);

	diffAbsolut = new Text(top, SWT.READ_ONLY);
	diffAbsolut.setText("diffAbsolut");
	final GridData gd_diffAbsolut = new GridData(SWT.FILL, SWT.CENTER, true, false);
	diffAbsolut.setLayoutData(gd_diffAbsolut);

	diffPercent = new Text(top, SWT.READ_ONLY);
	diffPercent.setText("diff%");
	final GridData gd_diffText = new GridData(SWT.FILL, SWT.CENTER, true, false);
	diffPercent.setLayoutData(gd_diffText);

	bidPrice = new Text(top, SWT.READ_ONLY);
	bidPrice.setText("bidPrice");
	final GridData gd_bidPrice = new GridData(SWT.FILL, SWT.CENTER, true, false);
	bidPrice.setLayoutData(gd_bidPrice);

	bidsize = new Text(top, SWT.READ_ONLY);
	bidsize.setText("bidsize");
	final GridData gd_bidsize = new GridData(SWT.FILL, SWT.CENTER, true, false);
	bidsize.setLayoutData(gd_bidsize);

	askPrice = new Text(top, SWT.READ_ONLY);
	askPrice.setText("askPrice");
	final GridData gd_askPrice = new GridData(SWT.FILL, SWT.CENTER, true, false);
	askPrice.setLayoutData(gd_askPrice);

	askSize = new Text(top, SWT.READ_ONLY);
	askSize.setText("askSize");
	final GridData gd_askSize = new GridData(SWT.FILL, SWT.CENTER, true, false);
	askSize.setLayoutData(gd_askSize);

	dayHigh = new Text(top, SWT.READ_ONLY);
	dayHigh.setText("dayHigh");
	final GridData gd_dayHigh = new GridData(SWT.FILL, SWT.CENTER, true, false);
	dayHigh.setLayoutData(gd_dayHigh);

	dayLow = new Text(top, SWT.READ_ONLY);
	dayLow.setText("dayLow");
	final GridData gd_dayLow = new GridData(SWT.FILL, SWT.CENTER, true, false);
	dayLow.setLayoutData(gd_dayLow);

	//bindGUI();

	final Composite bottom = new Composite(parent, SWT.NONE);
	bottom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	final GridLayout gridLayout_2 = new GridLayout();
	gridLayout_2.verticalSpacing = 0;
	gridLayout_2.marginWidth = 0;
	gridLayout_2.marginHeight = 0;
	gridLayout_2.horizontalSpacing = 0;
	bottom.setLayout(gridLayout_2);


	tableViewer = new TableViewer(bottom, SWT.FULL_SELECTION | SWT.BORDER);
	table = tableViewer.getTable();
	table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	table.setLinesVisible(true);
	table.setHeaderVisible(true);
	TableColumnLayout ad = new TableColumnLayout();
	bottom.setLayout(ad);

	TableColumn cIBSymbol = new TableColumn(table,SWT.LEFT);
	cIBSymbol.setText("IBSymbol");
	ad.setColumnData(cIBSymbol, new ColumnWeightData(100, 45));
	
	TableColumn cName = new TableColumn(table,SWT.LEFT);
	cName.setText("Name");
	ad.setColumnData(cName, new ColumnWeightData(90, 160));
	
	TableColumn cLastPrice = new TableColumn(table,SWT.LEFT);
	cLastPrice.setText("LastPrice");
	ad.setColumnData(cLastPrice, new ColumnWeightData(90, 80));
	
	TableColumn cDiffAbsolut = new TableColumn(table,SWT.LEFT);
	cDiffAbsolut.setText("diffAbsolut");
	ad.setColumnData(cDiffAbsolut, new ColumnWeightData(90, 80));
	
	TableColumn cDiffPercent = new TableColumn(table,SWT.LEFT);
	cDiffPercent.setText("diff%");
	ad.setColumnData(cDiffPercent, new ColumnWeightData(90, 80));
	
	TableColumn cBidPrice = new TableColumn(table,SWT.LEFT);
	cBidPrice.setText("BidPrice");
	ad.setColumnData(cBidPrice, new ColumnWeightData(90, 80));
	
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
	
	tableViewer.setContentProvider(contentProvider);
	tableViewer.setLabelProvider(contentProvider);

	
	initializeToolBar();
	//KontextMenu initialisieren
	initializeContextMenu();
	}

	private void initializeContextMenu()
	{
	final Action aReqHistoricalData = new Action("") 
	{
		public void run()
		{
		new RequestHistoricalDataJob("Historische Daten anfordern", dr).schedule();
		}
		
	};
	
	final MenuManager mgr = new MenuManager();
	mgr.setRemoveAllWhenShown(true);

		mgr.addMenuListener(new IMenuListener() 
		{
			public void menuAboutToShow(IMenuManager manager) 
			{
			IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				if (!selection.isEmpty()) 
				{
				aReqHistoricalData.setText("Historische Daten für: " + ((Asset) selection.getFirstElement()).getName()+ " anfordern.");
					if (selection.getFirstElement() instanceof Stock)
					{
					Stock selectedStock = (Stock) selection.getFirstElement();
					dr = new DataRequest();
					dr.setStock(selectedStock);
					}
				mgr.add(aReqHistoricalData);
				}
			}
			});	
	tableViewer.getControl().setMenu(mgr.createContextMenu(tableViewer.getControl()));
	}

	private void bindGUI() 
	{
	// Binding name
	IObservableValue nameTextObserveWidget = SWTObservables.observeText(name, SWT.Modify);
	IObservableValue indexNameObserveValue = BeansObservables.observeValue(selectedIndex, "name");
	bindingContext.bindValue(nameTextObserveWidget, indexNameObserveValue, null, null);
	if (Activator.DEBUG) {Activator.log("IndexDetailView#bindGUI - bindValue: Target: name, Model: name", null);}
	}


	@Override
	public void fillViewToolbar() {
		// TODO Auto-generated method stub
		super.fillViewToolbar();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		super.setFocus();
	}
	private void initializeToolBar() {
	//IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}

	public void dispose() 
	{
	super.dispose();
	}



	public void setSelectedIndex(Index incomingIndex) 
	{
	this.selectedIndex = incomingIndex;	
	}

	/**
	 * Der ISelectionListener wird benachrichtigt, wenn der IndexTreeView ausgewählt wird.
	 * In diesem Moment steht der TreeViewer als sourcepart zur Vefügung und dieser View hat die Gelegenheit,
	 * sich als SelectionChangedListener beim TreeView zu registrieren.
	 * 
	 * Das MasterDetail Databinding benötigt ebenfalls den SelectionProvider (hier: TreeViewer). 
	 * Daher wird hier gleich das DataBinding erledigt.
	 * 
	 * Das boolean registered wird true, wenn der SelectionChangedListener registriert ist. Dadurch wird verhindert, 
	 * das die Listenerregistrierung und das Databinding bei jedem SelectionChange event ausgeführt wird.
	 */
	public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) 
	{
		if (sourcepart instanceof IndexTreeView && selection instanceof TreeSelection && !registered) 
		{
			IndexTreeView indexTreeView = (IndexTreeView)sourcepart;
			indexTreeView.getTreeViewer().addSelectionChangedListener(this);
			
			// Databinding SelectionProvider - TextWidgets
			IObservableValue indexSelection = ViewersObservables.observeSingleSelection(indexTreeView.getTreeViewer());
			
			// Name
			IObservableValue nameObservable = 	BeansObservables.observeDetailValue(Realm.getDefault(), 
												indexSelection,
												"name", 
												String.class);
			bindingContext.bindValue(			SWTObservables.observeText(name, SWT.None), 
												nameObservable,
												new UpdateValueStrategy(false, UpdateValueStrategy.POLICY_NEVER), 
												null);
			//TODO: Hier muss noch das ibSymbol rein, wenn es da ist...
			// Symbol
			IObservableValue symbolObservable =	BeansObservables.observeDetailValue(Realm.getDefault(), 
												indexSelection,
												"symbol", 
												String.class);						
			bindingContext.bindValue(			SWTObservables.observeText(ibSymbol, SWT.None), 
												symbolObservable,
												new UpdateValueStrategy(false, UpdateValueStrategy.POLICY_NEVER), 
												null);	
			registered  = true;
		}	
	}

	/**
	 * Wird aufgerufen, wenn sich die Selection im IndexTreeView verändert 
	 */
	public void selectionChanged(SelectionChangedEvent event) 
	{
		if(event.getSelection().isEmpty()) 
		{
		return;
		}

	    if(event.getSelection() instanceof IStructuredSelection) 
	    {
	    IStructuredSelection selection = (IStructuredSelection)event.getSelection();
	    
	       	if (selection.getFirstElement() instanceof Index) 
	    	{
	       	Index incomingIndex = (Index) selection.getFirstElement(); 	
			setSelectedIndex(incomingIndex);
				if (tableViewer != null) 
				{
				tableViewer.setInput(incomingIndex.getStocks().toArray());	
				}
			}
	    }
	}

	class IndexTableContentProvider implements IStructuredContentProvider, ITableLabelProvider
	{

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
		return selectedIndex.getStocks().toArray();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		@Override
		public void dispose() {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub

		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getColumnText(Object element, int columnIndex) 
		{
		Stock stock = (Stock) element;
			
			switch (columnIndex) 
			{
			case 0:
			return stock.getIbSymbol(); 	

			case 1:
			return stock.getName();

			case 2:
			return String.valueOf(marketData.getLastPrice());			
			}
			
		return null;
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			
		}
	}	
}
