package forms.reports;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import main.OLEViewer;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import reports.DailySales;
import reports.DailySalesReport;
import reports.PullOutReport;
import reports.TotalMerchandise;
import reports.ZSummaryReport;
import util.DateUtility;
import util.FileChooserUtility;
import util.LoggerUtility;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.cloudgarden.resource.SWTResourceManager;

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
@SuppressWarnings("serial")
public class ReportFormDialog extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	private JLabel reportTypeLabel;
	private JComboBox reportyTypeComboBox;
	private JLabel startDateLabel;
	private JLabel endDateLabel;
	private JButton exportButton;
	private Canvas dateCanvas;
	private JButton closeButton;
	private String startDate; 
	private AbstractAction exportAbstractAction;
	private Shell dateShell;
	private String endDate;
	private AbstractAction saveReportAbstractAction;
	private JButton saveButton;
	private JLabel reportFormLabel;
	private DateTime startDateCalendar;
	private DateTime endDateCalendar;
	
	private static Logger logger = Logger.getLogger(ReportFormDialog.class);

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ReportFormDialog inst = new ReportFormDialog(frame);
		inst.setVisible(true);
	}
	
	public ReportFormDialog(JFrame frame) {
		super(frame);
		initSwtAwtGUI();
	}
	

	private void initGUI() {
		try {
			
			
			{
				this.setTitle("Reports");
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				{
					reportFormLabel = new JLabel();
					AnchorLayout jLabel4Layout = new AnchorLayout();
					reportFormLabel.setLayout(jLabel4Layout);
					getContentPane().add(reportFormLabel, new AnchorConstraint(23, 344, 92, 27, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					reportFormLabel.setText("Export Reports");
					reportFormLabel.setPreferredSize(new java.awt.Dimension(166, 28));
					reportFormLabel.setFont(new java.awt.Font("Tahoma",0,18));
					reportFormLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "reportFormLabel");
					reportFormLabel.getActionMap().put("reportFormLabel",getReportFormLabelAbstractAction() );
				}
				{
					closeButton = new JButton();
					getContentPane().add(closeButton, new AnchorConstraint(917, 571, 974, 428, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					closeButton.setText("Close");
					closeButton.setPreferredSize(new java.awt.Dimension(75, 23));
					closeButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							ReportFormDialog.this.dispose();
						}
					});
				}
				{
					exportButton = new JButton();
					getContentPane().add(exportButton, new AnchorConstraint(786, 768, 838, 573, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					exportButton.setText("Export Report");
					exportButton.setPreferredSize(new java.awt.Dimension(102, 21));
					exportButton.setAction(getExportAbstractAction());

				}
				{
					saveButton = new JButton();
					getContentPane().add(saveButton, new AnchorConstraint(783, 960, 840, 779, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					saveButton.setText("Save Report");
					saveButton.setPreferredSize(new java.awt.Dimension(95, 23));
					saveButton.setAction(getSaveReportAbstractAction());
				}
				{
					endDateLabel = new JLabel();
					getContentPane().add(endDateLabel, new AnchorConstraint(267, 800, 329, 678, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					endDateLabel.setText("End Date");
					endDateLabel.setFont(new java.awt.Font("Tahoma",1,12));
					endDateLabel.setPreferredSize(new java.awt.Dimension(64, 25));
				}
				{
					startDateLabel = new JLabel();
					getContentPane().add(startDateLabel, new AnchorConstraint(272, 359, 324, 197, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					startDateLabel.setText("Start Date");
					startDateLabel.setFont(new java.awt.Font("Tahoma",1,12));
					startDateLabel.setPreferredSize(new java.awt.Dimension(85, 21));
				}
				{
					ComboBoxModel reportTypeComboBoxModel = new DefaultComboBoxModel(
						new String[] { "Daily Sales", "Daily Sales Summary", "Total Pullouts","Total Merchandise","Z-Summary" });
					reportyTypeComboBox = new JComboBox();
					getContentPane().add(reportyTypeComboBox, new AnchorConstraint(95, 960, 161, 222, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					reportyTypeComboBox.setModel(reportTypeComboBoxModel);
					reportyTypeComboBox.setPreferredSize(new java.awt.Dimension(387, 27));
				}
				{
					reportTypeLabel = new JLabel();
					getContentPane().add(reportTypeLabel, new AnchorConstraint(92, 241, 164, 29, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					reportTypeLabel.setText("Report Type");
					reportTypeLabel.setFont(new java.awt.Font("Tahoma",0,12));

					reportTypeLabel.setPreferredSize(new java.awt.Dimension(111, 29));
				}
				{
					dateCanvas = new Canvas();
					getContentPane().add(dateCanvas, new AnchorConstraint(324, 972, 771, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					dateCanvas.setPreferredSize(new java.awt.Dimension(493, 181));
				}
				{
					dateShell = SWT_AWT.new_Shell(Display.getDefault(), dateCanvas);

					{
						//Register as a resource user - SWTResourceManager will
						//handle the obtaining and disposing of resources
						SWTResourceManager.registerResourceUser(dateShell);
					}

					FormLayout shell1Layout = new FormLayout();
				dateShell.setLayout(shell1Layout);
				
				FormData calendarLData = new FormData();
				calendarLData.width = 249;
				calendarLData.height = 181;
				calendarLData.left =  new FormAttachment(1, 1000, 0);
				calendarLData.right =  new FormAttachment(508, 1000, 0);
				calendarLData.top =  new FormAttachment(2, 1000, 0);
				calendarLData.bottom =  new FormAttachment(1002, 1000, 0);
				startDateCalendar = new DateTime (dateShell, SWT.CALENDAR);
				startDateCalendar.setLayoutData(calendarLData);
				startDateCalendar.setBackground(SWTResourceManager.getColor(255,255,255));
				startDate = DateUtility.getDateUtility().formatDate(startDateCalendar);
				
				//Fix for invalid thread access 
				startDateCalendar.addSelectionListener (new SelectionAdapter () {
					@Override
					public void widgetSelected (SelectionEvent e) {
						startDate = DateUtility.getDateUtility().formatDate(startDateCalendar);
					}
				});
					
				
				FormData calendarLData1 = new FormData();
				calendarLData1.width = 242;
				calendarLData1.height = 181;
				calendarLData1.left =  new FormAttachment(508, 1000, 0);
				calendarLData1.right =  new FormAttachment(1001, 1000, 0);
				calendarLData1.top =  new FormAttachment(2, 1000, 0);
				calendarLData1.bottom =  new FormAttachment(1002, 1000, 0);
				endDateCalendar = new DateTime (dateShell, SWT.CALENDAR);
				endDateCalendar.setLayoutData(calendarLData1);
				endDateCalendar.setBackground(SWTResourceManager.getColor(255,255,255));
				endDate = DateUtility.getDateUtility().formatDate(endDateCalendar);
				
				//Fix for invalid thread access for swt bridge component
				endDateCalendar.addSelectionListener (new SelectionAdapter () {
					@Override
					public void widgetSelected (SelectionEvent e) {
						endDate = DateUtility.getDateUtility().formatDate(endDateCalendar);
					}
				});
				}

			}
			this.setSize(635, 536);
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
	
	@SuppressWarnings("serial")
	private AbstractAction getReportFormLabelAbstractAction() {
		AbstractAction reportFormLabelAction = new AbstractAction("Export Reports", null) {
			
			public void actionPerformed(ActionEvent evt) {
				ReportFormDialog.this.dispose();
			}
		};
		return reportFormLabelAction;
	}
	
	@SuppressWarnings("serial")
	private AbstractAction getExportAbstractAction() {
		if(exportAbstractAction == null) {
			exportAbstractAction = new AbstractAction("Export Report", null) {
				public void actionPerformed(ActionEvent evt) {
					if(startDate == null ){
						startDate = DateUtility.getDateUtility().getCurrentDate();
					}
					if(endDate == null){
						endDate = DateUtility.getDateUtility().getCurrentDate();
					}
					
					try {
						int reportType = reportyTypeComboBox.getSelectedIndex();
						String fileName = "temp.xls";
						logger.info("Generating report: "+reportType+" filename: "+fileName+" startdate: "+startDate+" enddate: "+endDate);
						switch(reportType){
							case 0: //daily sales
								boolean success = (new DailySalesReport()).generate(fileName,startDate, endDate);
								if(success){
//									JOptionPane.showMessageDialog(null,"Successfully saved daily sales report.","Saved",JOptionPane.INFORMATION_MESSAGE);
						        }
						        else{
						    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
						        }
							break;
							
							case 1://daily sales summary
								success = (new DailySales()).generate(fileName,startDate, endDate);
								if(success){
//									JOptionPane.showMessageDialog(null,"Successfully saved daily sales summary report.","Saved",JOptionPane.INFORMATION_MESSAGE);
								}
								else{
									JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
								}
								
							break;
							
							
							case 2: //pullouts
								success = (new PullOutReport()).generate(fileName, startDate, endDate);
								if(success){
//							    	   JOptionPane.showMessageDialog(null,"Successfully saved pull-outs report.","Saved",JOptionPane.INFORMATION_MESSAGE);
						        }
						        else{
						    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
						        }
							break;
							
							case 3:
								success = (new TotalMerchandise()).generate(fileName, startDate, endDate);
								if(success){
//							    	   JOptionPane.showMessageDialog(null,"The report is saved.","Saved",JOptionPane.INFORMATION_MESSAGE);
						        }
						        else{
						    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
						        }
							break;
							
							case 4:
								success = (new ZSummaryReport()).generate(fileName, startDate, endDate);
								if(success){
//							    	   JOptionPane.showMessageDialog(null,"The report is saved.","Saved",JOptionPane.INFORMATION_MESSAGE);
						        }
						        else{
						    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
						        }
							break;
						}
						OLEViewer.open2(fileName);
					} catch (IOException e) {
						LoggerUtility.getInstance().logStackTrace(e);
					}
				}
			};
		}
		return exportAbstractAction;
	}
	

	
	@SuppressWarnings("serial")
	private AbstractAction getSaveReportAbstractAction() {
		if(saveReportAbstractAction == null) {
			saveReportAbstractAction = new AbstractAction("Save Report", null) {
				public void actionPerformed(ActionEvent evt) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
					FileFilter fileFilter = FileChooserUtility.getFileChooserUtility().createFileFilter("Excel Files Only",true,new String[]{"xls"});
				  	fileChooser.setFileFilter(fileFilter);
				  	int returnValue = fileChooser.showSaveDialog(ReportFormDialog.this);
				    if(returnValue == JFileChooser.APPROVE_OPTION) {
				    	String fileName = fileChooser.getSelectedFile().getAbsolutePath()+".xls";
				    	int reportType = reportyTypeComboBox.getSelectedIndex();
				    	logger.info("Generating report: "+reportType+" filename: "+fileName);
				    	switch(reportType){
							case 0: //daily sales
								boolean success = (new DailySalesReport()).generate(fileName,startDate, endDate);
								if(success){
									JOptionPane.showMessageDialog(null,"The report is saved.","Saved",JOptionPane.INFORMATION_MESSAGE);
						        }
						        else{
						    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
						        }
							break;
							
							case 1://daily sales summary
								success =  (new DailySales()).generate(fileName,startDate, endDate);
								if(success){
							    	   JOptionPane.showMessageDialog(null,"The report is saved.","Saved",JOptionPane.INFORMATION_MESSAGE);
						        }
						        else{
						    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
						        }
								
							break;
							
							
							case 2: //pullouts
								success =  (new PullOutReport()).generate(fileName, startDate, endDate);
								if(success){
							    	   JOptionPane.showMessageDialog(null,"The report is saved.","Saved",JOptionPane.INFORMATION_MESSAGE);
						        }
						        else{
						    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
						        }
							break;
							
							case 3:
								success = (new TotalMerchandise()).generate(fileName, startDate, endDate);
								if(success){
							    	   JOptionPane.showMessageDialog(null,"The report is saved.","Saved",JOptionPane.INFORMATION_MESSAGE);
						        }
						        else{
						    	   JOptionPane.showMessageDialog(null,"Cannot save file","Error",JOptionPane.ERROR_MESSAGE);
						        }
							break;
							
							
								
						}
				    }
					
				}
			};
		}
		return saveReportAbstractAction;
	}

}
