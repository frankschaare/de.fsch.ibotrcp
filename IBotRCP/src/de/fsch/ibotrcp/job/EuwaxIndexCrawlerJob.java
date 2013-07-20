/**
 * 
 */
package de.fsch.ibotrcp.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.fsch.ibotrcp.controller.DBManager;
import de.fsch.ibotrcp.controller.IBotClient;
import de.fsch.ibotrcp.model.DataRequest;
import de.fsch.ibotrcp.model.Stock;

/**
 * @author Administrator
 *
 */
public class EuwaxIndexCrawlerJob extends Job 
{
private HashMap<String, String> URLs = new HashMap<String, String>();
private ArrayList<Stock> stocks = new ArrayList<Stock>();
private Pattern regex; 

	/**
	 * Dieser Job durchsuchtdie Yahoo IndexWebseiten und extrahiert anhand eines 
	 * RegEx Name, WKN und Symbol
	 * 
	 * @param name
	 */
	public EuwaxIndexCrawlerJob(String name) 
	{	
	super(name);
	/*
	System.setProperty( "proxySet", "true" );
	System.setProperty( "proxyHost", "10.33.8.199" );
	System.setProperty( "proxyPort", "8080" );
	System.setProperty( "http.proxyUser", "REGIONHANNOVER\fsch" );
	System.setProperty( "http.proxyPassword", "looser88" );	
	*/
	URLs.put("DAX", "http://de.finance.yahoo.com/q/cp?s=%5EGDAXI");
	URLs.put("MDAX", "http://de.finance.yahoo.com/q/cp?s=%5EMDAXI");
	URLs.put("SDAX", "http://de.finance.yahoo.com/q/cp?s=%5ESDAXI");
	URLs.put("TecDAX", "http://de.finance.yahoo.com/q/cp?s=%5ETECDAX");
	
	regex = Pattern.compile("<td class=\"\\w*\"><b><a href=\"(/q\\?s=)(\\w{3})\\.DE\">(\\d{6})</a></b></td><td class=\"\\w*\"><small>([\\w\\s\\.]*)</small>",
			Pattern.CANON_EQ);		
	
	this.setPriority(Job.SHORT);
	this.schedule(); // start as soon as possible
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IStatus run(IProgressMonitor monitor) 
	{
	URL url;
	String indexName;
	String strURL;
	
	Set<Entry<String, String>> pairs = URLs.entrySet();
		for (Entry<String, String> entry : pairs)
		{
		indexName = entry.getKey();
		strURL = entry.getValue();

			try
			{
			url = new URL(strURL);
			InputStreamReader is = new InputStreamReader(url.openStream());
			BufferedReader br = new BufferedReader(is);
			StringBuilder stringBuilder = new StringBuilder();
				for (String line; (line = br.readLine()) != null;)
				{
				stringBuilder.append(line);	
				}
			br.close();

			Matcher matcher = regex.matcher(stringBuilder.toString());
				while (matcher.find())
				{
				Stock stock = new Stock();
				stock.setPrimaryIndex(indexName);
				stock.setSymbol(matcher.group(2));
				stock.setWkn(matcher.group(3));
				stock.setName(matcher.group(4));
				stocks.add(stock);
				}
			DBManager.getDefault().setCrawledStocks(stocks);	
			} 
			catch (MalformedURLException e)
			{
			e.printStackTrace();
			} 
			catch (IOException e)
			{
			e.printStackTrace();
			}

			

		}
	return Status.OK_STATUS;
	}
}
