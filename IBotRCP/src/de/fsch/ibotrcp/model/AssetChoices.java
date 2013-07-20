/**
 * 
 */
package de.fsch.ibotrcp.model;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class AssetChoices
{
private String[] assetTypes;
private ArrayList<String> currencyTypes;

	/**
	 * Diese Klasse liefert Auswahlwerte für die GUI 
	 */
	public AssetChoices() 
	{
	initializeAssetTypes();	
	initializeCurrencyTypes();
	}

	private void initializeCurrencyTypes() 
	{
	currencyTypes = new ArrayList<String>();
	currencyTypes.add(Asset.ASSET_NOT_SET);
	currencyTypes.add("EUR");
	currencyTypes.add("USD");
	}

	public ArrayList<String> getCurrencyTypes() 
	{
	return currencyTypes;
	}
	
	
	private void initializeAssetTypes() 
	{
	assetTypes = new String[5];
	assetTypes[0] = Asset.ASSET_NOT_SET;
	assetTypes[1] = Asset.INDEX;
	assetTypes[2] = Asset.STOCK;
	assetTypes[3] = Asset.BOND;
	assetTypes[4] = Asset.FUTURE;
	}

	public String[] getAssetTypes() 
	{
	return assetTypes;
	}

}
