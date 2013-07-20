/**
 * 
 */
package de.fsch.ibotrcp.view;

import java.util.ArrayList;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

import cbg.article.treeviewer.ui.TreeViewerPlugin;

import de.fsch.ibotrcp.Activator;
import de.fsch.ibotrcp.ApplicationActionBarAdvisor;
import de.fsch.ibotrcp.action.DataBaseAction;
import de.fsch.ibotrcp.controller.DBManager;
import de.fsch.ibotrcp.controller.WWWManager;
import de.fsch.ibotrcp.job.YahooIndexCrawlerJob;
import de.fsch.ibotrcp.model.Stock;
import de.fsch.ibotrcp.preferences.PreferenceConstants;

/**
 * @author Administrator
 *
 */
public class EditAssetView extends AbstractMasterView implements ISelectionListener, ISelectionChangedListener
{


private Group assetGroup;	
private Table table;
private TableViewer tableViewer;
private Text marketValue;
private Text stockQuantum;
private Combo currencyCombo;
private Text symbol;
private Text ibSymbol;
private Combo combo;
private Text wkn;
private Text isin;
private Text name;
private ComboViewer assetType;
private ComboViewer currency;

private Browser browser;

public static final String ID = "de.fsch.ibotrcp.view.EditAssetView";

private Stock stock = null;
private String defaultURL = null;
private String yahooURL = null;
private String symbolURL = null;
private String ibSearchURL = null;

private Job yahooJob;
private ArrayList<Stock> crawledStocks = null;
private Action upDateDBAction;
/**
	 * 
	 */
	public EditAssetView() 
	{
	super();
	// Setze Dummy für die Anzeige
	stock = new Stock();
	stock.setName("");
	stock.setType("bitte auswählen...");
	stock.setIsin("");
	stock.setWkn("");
	stock.addIndex("DAX");
	stock.addIndex("Stoxx50");
	
	IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	
	defaultURL = store.getString(PreferenceConstants.VIEWS_EDITASSET_DEFAULT);
	if (Activator.DEBUG) {Activator.log("Lade Preferenz VIEWS_EDITASSET_DEFAULT, Wert: " + defaultURL, null);}
	
	yahooURL = store.getString(PreferenceConstants.VIEWS_EDITASSET_NAME);
	if (Activator.DEBUG) {Activator.log("Lade Preferenz VIEWS_EDITASSET_NAME, Wert: " + yahooURL, null);}
	
	symbolURL = store.getString(PreferenceConstants.VIEWS_EDITASSET_SYMBOL);
	if (Activator.DEBUG) {Activator.log("Lade Preferenz VIEWS_EDITASSET_SYMBOL, Wert: " + symbolURL, null);}
	
	ibSearchURL = store.getString(PreferenceConstants.VIEWS_EDITASSET_IB_PREFIX)+store.getString(PreferenceConstants.VIEWS_EDITASSET_IB_SUFFIX);
	if (Activator.DEBUG) {Activator.log("Lade Preferenz VIEWS_EDITASSET_PREFIX+SUFFIX, Wert: " + ibSearchURL, null);}	
	}

	public void init(IViewSite site) throws PartInitException
	{
	super.init(site);
	//yahooJob = new YahooIndexCrawlerJob("YahooCrawler");
	}
	
	public void setStock(Stock stock) 
	{
	this.stock.setName(stock.getName());
	this.stock.setIsin(stock.getIsin());
	this.stock.setWkn(stock.getWkn());
	this.stock.setSymbol(stock.getSymbol());
	this.stock.setIbSymbol(stock.getIbSymbol());
	this.stock.setType(stock.getType());
	this.stock.setIndexes(stock.getIndexes());
	this.stock.setStockQuantum(stock.getStockQuantum());
	tableViewer.setInput(stock.getIndexes());
	assetGroup.setText(stock.getName());
	

	}
	
	public Stock getStock() 
	{
	return this.stock;	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent)
	{
		assetGroup = new Group(parent, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		assetGroup.setLayout(gridLayout);
		assetGroup.setText("Asset editieren");

		final Label nameLabel = new Label(assetGroup, SWT.NONE);
		nameLabel.setAlignment(SWT.RIGHT);
		final GridData gd_nameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
		gd_nameLabel.widthHint = 45;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("Name:");

		name = new Text(assetGroup, SWT.BORDER);
		final GridData gd_name = new GridData(SWT.FILL, SWT.CENTER, true, false);
		name.setLayoutData(gd_name);

		final Label typLabel = new Label(assetGroup, SWT.NONE);
		typLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		typLabel.setAlignment(SWT.RIGHT);
		typLabel.setText("Typ:");

		assetType = new ComboViewer(assetGroup, SWT.BORDER);
		assetType.setContentProvider(new ArrayContentProvider());
		assetType.setInput(stock.getAssetChoices().getAssetTypes());
		combo = assetType.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));


		final Label isinLabel = new Label(assetGroup, SWT.NONE);
		isinLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		isinLabel.setAlignment(SWT.RIGHT);
		isinLabel.setText("ISIN:");

		isin = new Text(assetGroup, SWT.BORDER);
		isin.setTextLimit(12);
		final GridData gd_isin = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_isin.minimumWidth = 85;
		isin.setLayoutData(gd_isin);

		final Label wknLabel = new Label(assetGroup, SWT.NONE);
		wknLabel.setAlignment(SWT.RIGHT);
		wknLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		wknLabel.setText("WKN:");

		wkn = new Text(assetGroup, SWT.BORDER);
		wkn.setTextLimit(6);
		final GridData gd_wkn = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_wkn.minimumWidth = 85;
		wkn.setLayoutData(gd_wkn);

		final Label symbolLabel = new Label(assetGroup, SWT.NONE);
		symbolLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		symbolLabel.setAlignment(SWT.RIGHT);
		symbolLabel.setText("Symbol:");

		symbol = new Text(assetGroup, SWT.BORDER);
		symbol.setTextLimit(12);
		final GridData gd_symbol = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_symbol.minimumWidth = 85;
		symbol.setLayoutData(gd_symbol);

		final Label ibSymbolLabel = new Label(assetGroup, SWT.NONE);
		ibSymbolLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		ibSymbolLabel.setAlignment(SWT.RIGHT);
		ibSymbolLabel.setText("IB Symbol:");

		ibSymbol = new Text(assetGroup, SWT.BORDER);
		ibSymbol.addFocusListener(new FocusAdapter() {
			public void focusGained(final FocusEvent e) 
			{
				if (ibSearchURL != null) 
				{
				String newIBURL = ibSearchURL.replace("{name}", WWWManager.getDefault().encodeValue(stock.getName()));
				browser.setUrl(newIBURL);
				if (Activator.DEBUG) {Activator.log("ibSymbol#focusGained: Neue Browser URL = " + newIBURL, null);}
				}
			}
		});
		ibSymbol.setTextLimit(12);
		final GridData gd_ibSymbol = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_ibSymbol.minimumWidth = 85;
		ibSymbol.setLayoutData(gd_ibSymbol);

		final Label currencyLabel = new Label(assetGroup, SWT.NONE);
		final GridData gd_currencyLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
		currencyLabel.setLayoutData(gd_currencyLabel);
		currencyLabel.setAlignment(SWT.RIGHT);
		currencyLabel.setText("Währung:");

		currency = new ComboViewer(assetGroup, SWT.BORDER);
		currency.setContentProvider(new CurrencyProvider());
		currency.setInput(new Object());
		currencyCombo = currency.getCombo();
		currencyCombo.setTextLimit(3);
		final GridData gd_currencyCombo = new GridData(SWT.FILL, SWT.CENTER, false, false);
		currencyCombo.setLayoutData(gd_currencyCombo);

		final Label anzahlDerAktienLabel = new Label(assetGroup, SWT.NONE);
		anzahlDerAktienLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		anzahlDerAktienLabel.setAlignment(SWT.RIGHT);
		anzahlDerAktienLabel.setText("Anzahl der Aktien:");

		stockQuantum = new Text(assetGroup, SWT.BORDER);
		final GridData gd_stockQuantum = new GridData(SWT.FILL, SWT.CENTER, true, false);
		stockQuantum.setLayoutData(gd_stockQuantum);

		final Label marktkapitalisierungLabel = new Label(assetGroup, SWT.NONE);
		marktkapitalisierungLabel.setLayoutData(new GridData());
		marktkapitalisierungLabel.setAlignment(SWT.RIGHT);
		marktkapitalisierungLabel.setText("Marktkapitalisierung:");

		marketValue = new Text(assetGroup, SWT.BORDER);
		final GridData gd_marketValue = new GridData(SWT.FILL, SWT.TOP, true, false);
		marketValue.setLayoutData(gd_marketValue);

		final Label indizesLabel = new Label(assetGroup, SWT.NONE);
		indizesLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));
		indizesLabel.setAlignment(SWT.RIGHT);
		indizesLabel.setText("Indizes:");

		tableViewer = new TableViewer(assetGroup, SWT.BORDER);
		tableViewer.setLabelProvider(new IndexTableLabelProvider());
		tableViewer.setContentProvider(new IndexTableContentProvider());
		tableViewer.setInput(new Object());
		table = tableViewer.getTable();
		table.setLinesVisible(false);
		table.setHeaderVisible(false);
		table.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		
		browser = new Browser(assetGroup, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));
		browser.setUrl(defaultURL);

		initDataBindings();
		
		fillViewToolbar();
	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}


	public void fillViewToolbar() 
	{
	IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	DataBaseAction saveAction = (DataBaseAction) ApplicationActionBarAdvisor.getMenuAction("IBotRCP.saveStock");
	stock.addModelChangedListener(saveAction);
	tbm.add(ApplicationActionBarAdvisor.getMenuAction("IBotRCP.saveStock"));
	
	upDateDBAction = new Action("Update DB") {
		public void run() {
			DBManager.getDefault().updateStocks();
		}			
	};
	upDateDBAction.setToolTipText("Aktualisiert die Aktien in der Datenbank");
	upDateDBAction.setImageDescriptor(Activator.getImageDescriptor("newBook.gif"));
	tbm.add(upDateDBAction);
	}

	public void dispose() 
	{
	selectionService = getSite().getWorkbenchWindow().getSelectionService();
	selectionService.removeSelectionListener(this);
	super.dispose();
	}
	
	class CurrencyProvider implements IStructuredContentProvider 
	{
		public Object[] getElements(Object inputElement) {
			return stock.getAssetChoices().getCurrencyTypes().toArray();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}	
	
	class IndexTableContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) 
		{
		return stock.getIndexes().toArray();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	class IndexTableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object element, int columnIndex) {

			switch (columnIndex) {
			case 0:
				return element.toString();
				default:
				return element.toString();
			}

		}
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
	}	
	
	protected DataBindingContext initDataBindings() 
	{
		// Binding name
		IObservableValue nameTextObserveWidget = SWTObservables.observeText(name, SWT.Modify);
		IObservableValue stockNameObserveValue = BeansObservables.observeValue(stock, "name");
		bindingContext.bindValue(nameTextObserveWidget, stockNameObserveValue, null, null);

		// Binding isin
		IObservableValue stockIsinObserveValue = BeansObservables.observeValue(stock, "isin");
		IObservableValue isinTextObserveWidget = SWTObservables.observeText(isin, SWT.Modify);
		bindingContext.bindValue(isinTextObserveWidget, stockIsinObserveValue, null, null);
		
		// Binding wkn
		IObservableValue stockWknObserveValue = BeansObservables.observeValue(stock, "wkn");
		IObservableValue wknTextObserveWidget = SWTObservables.observeText(wkn, SWT.Modify);
		bindingContext.bindValue(wknTextObserveWidget, stockWknObserveValue, null, null);

		// Binding type
		IObservableValue typeSelection = ViewersObservables.observeSingleSelection(assetType);
		IObservableValue stockTypeObserveValue = BeansObservables.observeValue(stock, "type");
		bindingContext.bindValue(typeSelection, stockTypeObserveValue, null, null);
		
		// Binding symbol
		IObservableValue stockSymbolObserveValue = BeansObservables.observeValue(stock, "symbol");
		IObservableValue symbolTextObserveWidget = SWTObservables.observeText(symbol, SWT.Modify);
		bindingContext.bindValue(symbolTextObserveWidget, stockSymbolObserveValue, null, null);
		
		// Binding ibSymbol
		IObservableValue stockIbSymbolObserveValue = BeansObservables.observeValue(stock, "ibSymbol");
		IObservableValue ibSymbolTextObserveWidget = SWTObservables.observeText(ibSymbol, SWT.Modify);
		bindingContext.bindValue(ibSymbolTextObserveWidget, stockIbSymbolObserveValue, null, null);
		
		// Binding stockQuantum
		IObservableValue stockStockQuantumObserveValue = BeansObservables.observeValue(stock, "stockQuantum");
		IObservableValue stockQuantumTextObserveWidget = SWTObservables.observeText(stockQuantum, SWT.Modify);
		bindingContext.bindValue(stockQuantumTextObserveWidget, stockStockQuantumObserveValue, null, null);

		// Binding currency
		IObservableValue stockCurrencyObserveValue = BeansObservables.observeValue(stock, "currency");
		IObservableValue currencySelection = ViewersObservables.observeSingleSelection(currency);
		bindingContext.bindValue(currencySelection, stockCurrencyObserveValue, null, null);

		return bindingContext;
	}

	/**
	 * Der ISelectionListener wird benachrichtigt, wenn der IndexTreeView ausgewählt wird.
	 * In diesem Moment steht der TreeViewer als sourcepart zur Vefügung und dieser View hat die Gelegenheit,
	 * sich als SelectionChangedListener beim TreeView zu registrieren.
	 * 
	 * Das boolean registered wird true, wenn der SelectionChangedListener registriert ist.
	 * 
	 * Die Registrierung als SelectionListener erfolgt in {@link IndexTreeView#init}
	 */
	public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) 
	{
		if (sourcepart instanceof IndexTreeView && selection instanceof TreeSelection && !registered) 
		{
		IndexTreeView indexTreeView = (IndexTreeView)sourcepart;
		indexTreeView.getTreeViewer().addSelectionChangedListener(this);
		
		registered  = true;
		}	
	}
	
	/**
	 * Der ISelectionChangedListener wird benachrichtigt, wenn sich die Selektion im TreeView ändert.
	 * Zwar wird auch selectionChanged event gefeuert, jedoch nur, wenn sich der Focus ändert.
	 * Da das nicht immer der Fall ist, ist der ISelectionChangedListener erforderlich.
	 * 
	 * Stehen zusätzliche Informationen aus dem Internet bereit, wird zusätzlich die Methode
	 * {@link#addCrawledInformation()}aufgerufen.
	 */
	public void selectionChanged(SelectionChangedEvent event) 
	{
		if (event.getSelection() instanceof IStructuredSelection) 
		{
		IStructuredSelection ss = (IStructuredSelection) event.getSelection();

			if (ss.getFirstElement() instanceof Stock) 
			{
			Stock st = (Stock) ss.getFirstElement();
			setStock(st);
			
			wkn.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
			symbol.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
			ibSymbol.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));	
			
			if ((crawledStocks = DBManager.getDefault().getCrawledStocks()) != null)
			{
			addCrawledInformation();
			}
	
	setURL();		
				try 
				{
				this.getSite().getWorkbenchWindow().getActivePage().showView(this.ID);
				} 
				catch (PartInitException e) 
				{
				Activator.log("Fehler beim Öffnen des View: " + this.ID, e);
				}	
			}
		}	
		
	}
	
	private void setURL()
	{
		// Ist ein Symbol vorhanden, wird die Euwax Suche benutzt
		if (symbolURL != null && stock.getSymbol() != null) 
		{
		String newURL = symbolURL.replace("{symbol}", stock.getSymbol());
		browser.setUrl(newURL);
		if (Activator.DEBUG) {Activator.log("symbol#focusGained: Neue Browser URL = " + newURL, null);}
		}
		// Falls nicht, wird versucht, die Yahoo Suche zu benutzen:
		else
		{
		String newYahooURL = yahooURL.replace("{name}", WWWManager.getDefault().encodeValue(stock.getName()));	
		browser.setUrl(newYahooURL);
		if (Activator.DEBUG) {Activator.log("symbol#focusGained: Neue Browser URL = " + newYahooURL, null);}
		}	
		symbol.setFocus();	
	}

	/**
	 * Der YahooIndexCrawler hat bereits in der init Methode versucht, zusätzliche Daten.
	 * aus dem Internet zu besorgen. Stehen diese zur Verfügung, werden die zusätzlichen Felder
	 * mit diesen Informationen gefüllt.
	 */
	private void addCrawledInformation()
	{
	String strIsin;
	String strWkn;
		for (Stock crawledStock : crawledStocks)
		{
			strIsin = this.stock.getIsin();
			strWkn = crawledStock.getWkn().trim();
	
			if (strIsin.indexOf(strWkn) != -1 && this.stock.getSymbol() == null)
			{
			wkn.setText(crawledStock.getWkn());
			wkn.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			symbol.setText(crawledStock.getSymbol());
			symbol.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			ibSymbol.setText(crawledStock.getSymbol());
			ibSymbol.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			}
		}
		
	}
}
