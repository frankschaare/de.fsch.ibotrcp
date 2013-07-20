/**
 * 
 */
package de.fsch.ibotrcp.job;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.fsch.ibotrcp.controller.IBotClient;
import de.fsch.ibotrcp.model.DataRequest;

/**
 * @author Administrator
 *
 */
public class RequestHistoricalDataJob extends Job 
{
private DataRequest dr = null;

	/**
	 * @param name
	 */
	public RequestHistoricalDataJob(String name) 
	{
	super(name);
	this.setPriority(Job.SHORT);
	this.schedule(); // start as soon as possible
	}
	
	/**
	 * @param name
	 */
	public RequestHistoricalDataJob(String name, DataRequest dr) 
	{
	super(name);
	this.setPriority(Job.SHORT);
	this.schedule(); // start as soon as possible
	this.dr = dr;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IStatus run(IProgressMonitor monitor) 
	{
	IBotClient ibot = IBotClient.getDefault();	
	ibot.reqHistoricalData(dr);

	return Status.OK_STATUS;
	}

}
