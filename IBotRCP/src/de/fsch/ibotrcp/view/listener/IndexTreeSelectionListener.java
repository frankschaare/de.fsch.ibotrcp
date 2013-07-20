package de.fsch.ibotrcp.view.listener;

import java.util.Iterator;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import de.fsch.ibotrcp.job.IBotJob;
import de.fsch.ibotrcp.model.Asset;
import de.fsch.ibotrcp.model.Index;

public class IndexTreeSelectionListener implements ISelectionChangedListener 
{

	public void selectionChanged(SelectionChangedEvent event) 
	{
		// if the selection is empty clear the label
		if(event.getSelection().isEmpty()) 
		{
		return;
		}

	    if(event.getSelection() instanceof IStructuredSelection) 
	    {
	    IStructuredSelection selection = (IStructuredSelection)event.getSelection();

	    	for (Iterator iterator = selection.iterator(); iterator.hasNext();) 
        	{
	        Asset node = (Asset) iterator.next();
	        runJob(node);

        	}
	    }
	}

	private void runJob(Asset node) 
	{
	Job job = new IBotJob(node.getName());	
	}
}
