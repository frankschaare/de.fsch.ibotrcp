package de.fsch.ibotrcp.job;

import java.util.Date;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;

public class MarketDataRow implements ISelectionProvider
{
private double lastPrice = 0;
private Date lastDate = null;
private double differenceAbsolute = 0;
private double differencePercent = 0;
private double bidPrice = 0;
private long bidSize = 0;
private long askSize = 0;
private double askPrice = 0;
private double dayHigh = 0;
private double dayLow = 0;



	public MarketDataRow()
	{
	// Dummy Daten als Test
	lastPrice = 123.45;
	differenceAbsolute = 12.34;
	differencePercent = 0.78;
	dayHigh = 109.56;
	dayLow = 99.44;
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public ISelection getSelection()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelection(ISelection selection)
	{
		// TODO Auto-generated method stub

	}

	public double getLastPrice()
	{
		return lastPrice;
	}

	public void setLastPrice(double lastPrice)
	{
		this.lastPrice = lastPrice;
	}

	public Date getLastDate()
	{
		return lastDate;
	}

	public void setLastDate(Date lastDate)
	{
		this.lastDate = lastDate;
	}

	public double getDifferenceAbsolute()
	{
		return differenceAbsolute;
	}

	public void setDifferenceAbsolute(double differenceAbsolute)
	{
		this.differenceAbsolute = differenceAbsolute;
	}

	public double getDifferencePercent()
	{
		return differencePercent;
	}

	public void setDifferencePercent(double differencePercent)
	{
		this.differencePercent = differencePercent;
	}

	public double getBidPrice()
	{
		return bidPrice;
	}

	public void setBidPrice(double bidPrice)
	{
		this.bidPrice = bidPrice;
	}

	public long getBidSize()
	{
		return bidSize;
	}

	public void setBidSize(long bidSize)
	{
		this.bidSize = bidSize;
	}

	public long getAskSize()
	{
		return askSize;
	}

	public void setAskSize(long askSize)
	{
		this.askSize = askSize;
	}

	public double getAskPrice()
	{
		return askPrice;
	}

	public void setAskPrice(double askPrice)
	{
		this.askPrice = askPrice;
	}

	public double getDayHigh()
	{
		return dayHigh;
	}

	public void setDayHigh(double dayHigh)
	{
		this.dayHigh = dayHigh;
	}

	public double getDayLow()
	{
		return dayLow;
	}

	public void setDayLow(double dayLow)
	{
		this.dayLow = dayLow;
	}

}
