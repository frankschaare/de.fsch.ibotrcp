package de.fsch.ibotrcp;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import cbg.article.treeviewer.ui.MovingBoxView;

import de.fsch.ibotrcp.view.ClassContentOutlineView;
import de.fsch.ibotrcp.view.EditAssetView;
import de.fsch.ibotrcp.view.GermanStocksView;
import de.fsch.ibotrcp.view.IndexDetailView;
import de.fsch.ibotrcp.view.IndexTreeView;

/**
 * Definiert die initiale Perspektive
 * 
 * Das Interface {@link org.eclipse.ui.IPageLayout} liefert einige wichtige Konstanten für 
 * Eclipse Views, z.b.
 * <ul>
 * <li>Resource Navigator - org.eclipse.ui.views.ResourceNavigator</li>
 * <li>Property sheet - org.eclipse.ui.views.PropertySheet</li>
 * <li>Content Outline - org.eclipse.ui.views.ContentOutline</li>
 * <li>Problems View - org.eclipse.ui.views.ProblemView</li>
 * </ul>	
 */
public class Perspective implements IPerspectiveFactory 
{

	public void createInitialLayout(IPageLayout layout) 
	{
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		layout.addStandaloneView(IndexTreeView.ID,  false, IPageLayout.LEFT, 0.15f, editorArea);
		IFolderLayout top = layout.createFolder("messages", IPageLayout.TOP, 0.8f, editorArea);
		IFolderLayout bottom = layout.createFolder("console", IPageLayout.BOTTOM, 0.2f, editorArea);
		//folder.addPlaceholder(InitialView.ID + ":*");
		top.addView(EditAssetView.ID);
		top.addView(IndexDetailView.ID);
		top.addView(GermanStocksView.ID);
		top.addView(MovingBoxView.ID);
		
		ApplicationWorkbenchAdvisor.getConsole(ApplicationWorkbenchAdvisor.CONSOLE_NAME).activate();
		
		bottom.addView("org.eclipse.pde.runtime.LogView");
		bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		
		System.out.println("Willkommen beim IBot !");		
		
		//layout.getViewLayout(NavigationView.ID).setCloseable(false);
	}
}
