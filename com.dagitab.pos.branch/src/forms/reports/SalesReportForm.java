package forms.reports;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.DBManager;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import util.LoggerUtility;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.cloudgarden.resource.SWTResourceManager;

import forms.MainWindow;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SalesReportForm extends javax.swing.JDialog {
	private JLabel salesReportLabel;
	private JLabel jLabel2;
	private JButton jButton1;
	private JButton jButton2;
	private Shell shell1;
	private Canvas canvas1;
	private DBManager db;
	private String storeCode;
	private DateTime calendar;
	private String startDate;
	private int isSummary;
	private static Logger logger = Logger.getLogger(SalesReportForm.class);

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		SalesReportForm inst = new SalesReportForm(frame);
		inst.setVisible(true);
	}
	
	public SalesReportForm(JFrame frame) {
		super(frame);
		initSwtAwtGUI();
	}
	
	public SalesReportForm(MainWindow parent, DBManager db, String storeCode, int isSummary)
	{
		super(parent);
		this.db = db;
		this.storeCode = storeCode;
		this.isSummary = isSummary;
		initSwtAwtGUI();
		
		
		
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setTitle("Sales Report");
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(873, 769, 958, 536, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Cancel");
				jButton2.setPreferredSize(new java.awt.Dimension(91, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						SalesReportForm.this.dispose();
					}
				});
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(873, 465, 958, 144, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Export to Excel");
				jButton1.setPreferredSize(new java.awt.Dimension(126, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						 JFileChooser chooser = new JFileChooser();
						  chooser.setDialogType(JFileChooser.SAVE_DIALOG);
						    // Note: source for ExampleFileFilter can be found in FileChooserDemo,
						    // under the demo/jfc directory in the JDK.
//						  	
						  	FileFilter filter = createFileFilter("Excel Files Only",true,new String[]{"xls"});
						  	chooser.setFileFilter(filter);

						  
						  	int returnVal = chooser.showSaveDialog(SalesReportForm.this);
//						    int returnVal = chooser.showOpenDialog(InventoryViewer.this);
						    if(returnVal == JFileChooser.APPROVE_OPTION) {
						     //  logger.info("You chose to open this file: " +
						            
						            //chooser.getSelectedFile().getAbsolutePath()+".xls");
						       
						       
//						       String date = calendar.getYear()+"-"+calendar.getMonth()+"-"+calendar.getDay();
						       
						    	if(isSummary == 0){
//						    		boolean success = reports.DailySalesReport.generate(chooser.getSelectedFile().getAbsolutePath()+".xls", db, storeCode, startDate);
//						    		if(success){
//							    	   JOptionPane.showMessageDialog(null, 
//												"The report is saved.", 
//												"Saved",JOptionPane.INFORMATION_MESSAGE);
//							       }
//							       else{
//							    	   JOptionPane.showMessageDialog(null, 
//												"Cannot save file", 
//												"Error",JOptionPane.ERROR_MESSAGE);
//							       }
						    	}
						    	else{ //Daily Sales Summary
//						    		boolean success = reports.DailySales.generate(chooser.getSelectedFile().getAbsolutePath()+".xls", db, storeCode, startDate);
//						    		if(success){
//								    	   JOptionPane.showMessageDialog(null, 
//													"The report is saved.", 
//													"Saved",JOptionPane.INFORMATION_MESSAGE);
//								       }
//								       else{
//								    	   JOptionPane.showMessageDialog(null, 
//													"Cannot save file", 
//													"Error",JOptionPane.ERROR_MESSAGE);
//								       }
						    	}
						       
						    	   
						       
						       
						       
						       		
						    }

					}
				});
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(107, 840, 171, 90, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2
					.setText("Please choose the date for the daily sales report");
				jLabel2.setPreferredSize(new java.awt.Dimension(294, 21));
			}
			{
				salesReportLabel = new JLabel();
				getContentPane().add(salesReportLabel, new AnchorConstraint(28, 483, 133, 19, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				salesReportLabel.setText("Sales Report");
				salesReportLabel.setPreferredSize(new java.awt.Dimension(182, 28));
				salesReportLabel.setFont(new java.awt.Font("Tahoma",1,18));
				salesReportLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "salesReportLabel");
				salesReportLabel.getActionMap().put("salesReportLabel",getSalesReportLabelAbstractAction() );
			}
			{
				canvas1 = new Canvas();
				getContentPane().add(canvas1, new AnchorConstraint(193, 28, 810, 54, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				canvas1.setPreferredSize(new java.awt.Dimension(343, 203));
				canvas1.setBackground(new java.awt.Color(255,255,255));
			}
			{
				shell1 = SWT_AWT.new_Shell(Display.getDefault(), canvas1);

					{
						//Register as a resource user - SWTResourceManager will
						//handle the obtaining and disposing of resources
						SWTResourceManager.registerResourceUser(shell1);
					}

				GridLayout shell1Layout = new GridLayout();
				shell1Layout.makeColumnsEqualWidth = true;
				shell1.setLayout(shell1Layout);
				
				GridData calendarLData = new GridData();
				calendarLData.widthHint = 329;
				calendarLData.heightHint = 191;
				calendar = new DateTime (shell1, SWT.CALENDAR);
				calendar.setLayoutData(calendarLData);
				calendar.setBackground(SWTResourceManager.getColor(255, 255, 255));
				String day = "";
				String month = "";
				if((calendar.getMonth()+1) <= 9){
					month = "0"+(calendar.getMonth()+1);
				}
				else{
					month = (calendar.getMonth()+1)+"";
				}
				
				if(calendar.getDay() <=9 ){
					day = "0"+calendar.getDay();
				}
				else{
					day = calendar.getDay()+"";
				}
				this.startDate = calendar.getYear()+"-"+month+"-"+day;
				logger.info(startDate);
				calendar.addSelectionListener (new SelectionAdapter () {
					@Override
					public void widgetSelected (SelectionEvent e) {
//						logger.info ("calendar date changed");
						String day = "";
						String month = "";
						if((calendar.getMonth()+1) <= 9){
							month = "0"+(calendar.getMonth()+1);
						}
						else{
							month = (calendar.getMonth()+1)+"";
						}
						
						if(calendar.getDay() <=9 ){
							day = "0"+calendar.getDay();
						}
						else{
							day = calendar.getDay()+"";
						}
						startDate = calendar.getYear()+"-"+month+"-"+day;
						logger.info(startDate);
						
					}
				});

				
			}
			this.setSize(400, 363);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	//$protect>>$
	//===== start of SWT_AWT special handler code =============

	/**
	 * This method should be called instead of initGUI to initialize
	 * and make visible this GUI, since it handles all the threading
	 * and other "quirks" of embedding SWT objects inside AWT ones.
	 */
	public void initSwtAwtGUI() {
		new DisplayThread().start();
	}
		
	/**
	 * This class makes sure that the SWT controls will be created
	 * and behave correctly
	 */
	private class DisplayThread extends Thread {

		@Override
		public void run() {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					//make sure the GUI is created inside the SWT display thread
					//otherwise you will get invalid-thread-access errors, and
					//make sure it is visible before calling the SWT_AWT.new_Shell
					//method, otherwise a "No handles" error will be thrown.
					setVisible(true);
					initGUI();
				}
			});
			
			//"wiggling" the size is one way to make sure that the
			//SWT controls are displayed correctly
			java.awt.Dimension sz = getSize();
			int w = sz.width;
			int h = sz.height;
			setSize(w+1, h);
			validate();
			setSize(w, h);
			validate();
			
			swtEventLoop();
		}

		/**
		 * Listen for and dispatch SWT events
		 */
		private void swtEventLoop() {
			Display display = Display.getDefault();
			while (true) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}

	}

	//===== end of SWT_AWT special handler code =============
	//$protect<<$
	
	
	private FileFilter createFileFilter(String description,
            boolean showExtensionInDescription, String...extensions) {
        if (showExtensionInDescription) {
            description = createFileNameFilterDescriptionFromExtensions(
                    description, extensions);
        }
        return new FileNameExtensionFilter(description, extensions);
    }
	
	private String createFileNameFilterDescriptionFromExtensions(
            String description, String[] extensions) {
        String fullDescription = (description == null) ?
                "(" : description + " (";
        // build the description from the extension list
        fullDescription += "." + extensions[0];
        for (int i = 1; i < extensions.length; i++) {
            fullDescription += ", .";
            fullDescription += extensions[i];
        }
        fullDescription += ")";
        return fullDescription;
    }
	private AbstractAction getSalesReportLabelAbstractAction() {
		AbstractAction salesReportLabelAction = new AbstractAction("Sales Report", null) {
			
			public void actionPerformed(ActionEvent evt) {
				SalesReportForm.this.dispose();
			}
		};
		return salesReportLabelAction;
	}
	

}
