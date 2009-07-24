package com.dagitab.pos.main;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class OLEViewer extends Dialog implements  SelectionListener{
	OleClientSite clientSite;
	private String fileName;
	MenuItem fileItem1;
	private MenuItem fileItem2;
	private static final String WINDOWS_CMD = "cmd.exe";
	private static final String FIRST_WINDOWS_PARAMETER = "/c";
	private static final String SECOND_WINDOWS_PARAMETER = "start";
	private static final String THIRD_WINDOWS_PARAMETER = "\"\"";
	
	public OLEViewer(Shell parent, int style,String fileName) {
		super(parent, style);
		this.fileName = fileName;
	}
	
	
	public void open() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL |SWT.MAX | SWT.MIN | SWT.RESIZE);
		shell.setImage(ResourceManager.getImage("/icons2/aztaicon8.ico"));
		shell.setText("Report Viewer");
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);

		File file;
		shell.setLayout(new FillLayout());
		try {		
			OleFrame frame = new OleFrame(shell, SWT.NONE);
			file = new File(fileName);
			clientSite = new OleClientSite(frame, SWT.NONE, file);			
		} catch (SWTError e) {
			JOptionPane.showMessageDialog(null, "Unable to open ActiveX control.", "System Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		shell.setMaximized(true);
		shell.layout();
		shell.pack();			
		shell.open();
			
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		clientSite.deactivateInPlaceClient();
		clientSite.dispose();
		file.delete();
	}
	
	public static void open2(String fileName) throws IOException{
		
		 
		   Runtime rt = Runtime.getRuntime();
		   Process p = null;
		 
		   	p = rt.exec(new String[] {WINDOWS_CMD,
				                          FIRST_WINDOWS_PARAMETER,
				                          SECOND_WINDOWS_PARAMETER,
				                          THIRD_WINDOWS_PARAMETER,
				                          '"' + fileName + '"' });

		 

	}



	public void widgetSelected(SelectionEvent e) {
//		if(e.widget==fileItem1)
//			logger.info(clientSite.doVerb(OLE.OLEIVERB_UIACTIVATE));
	}


	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
