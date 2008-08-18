package forms.reports;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.cloudgarden.resource.SWTResourceManager;

import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.DBManager;
import main.OLEViewer;
import main.ResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import reports.DailySales;
import reports.DailySalesReport;
import reports.PullOutReport;
import reports.SalesCerkProduction;
import reports.TotalMerchandise;

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
public class ReportFormDialog extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JComboBox jComboBox1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton exportButton;
	private Canvas canvas1;
	private JButton jButton2;
	private String startDate; 
	private AbstractAction exportAbstractAction;
	private Shell shell1;
	private String endDate;
	private int reportType;
	private String storeCode;
	private DBManager db;
	private JLabel reportFormLabel;
	private DateTime calendar1;
	private DateTime calendar2;

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
	
	public ReportFormDialog(JFrame frame, DBManager db, String storeCode){
		this(frame);
		this.storeCode = storeCode;
		this.db = db;
	}
	public ReportFormDialog(JFrame frame, DBManager db, String storeCode, int type){
		this(frame,db,storeCode);
		this.reportType = type;
		
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
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(877, 579, 934, 436, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Close");
					jButton2.setPreferredSize(new java.awt.Dimension(75, 23));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							ReportFormDialog.this.dispose();
						}
					});
				}
				{
					exportButton = new JButton();
					getContentPane().add(exportButton, new AnchorConstraint(808, 953, 860, 758, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					exportButton.setText("Export Report");
					exportButton.setPreferredSize(new java.awt.Dimension(102, 21));
					exportButton.setAction(getExportAbstractAction());

				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3, new AnchorConstraint(267, 800, 329, 678, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("End Date");
					jLabel3.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel3.setPreferredSize(new java.awt.Dimension(64, 25));
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2, new AnchorConstraint(272, 359, 324, 197, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("Start Date");
					jLabel2.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel2.setPreferredSize(new java.awt.Dimension(85, 21));
				}
				{
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
						new String[] { "Daily Sales", "Daily Sales Summary", "Total Pullouts","Total Merchandise" });
					jComboBox1 = new JComboBox();
					getContentPane().add(jComboBox1, new AnchorConstraint(95, 960, 161, 222, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox1.setModel(jComboBox1Model);
					jComboBox1.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent evt) {
							reportType = jComboBox1.getSelectedIndex();
						}
					});

					jComboBox1.setPreferredSize(new java.awt.Dimension(387, 27));
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1, new AnchorConstraint(92, 241, 164, 29, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("Report Type");
					jLabel1.setFont(new java.awt.Font("Tahoma",0,12));

					jLabel1.setPreferredSize(new java.awt.Dimension(111, 29));
				}
				{
					canvas1 = new Canvas();
					getContentPane().add(canvas1, new AnchorConstraint(324, 972, 771, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					canvas1.setPreferredSize(new java.awt.Dimension(493, 181));
				}
				{
					shell1 = SWT_AWT.new_Shell(Display.getDefault(), canvas1);

					{
						//Register as a resource user - SWTResourceManager will
						//handle the obtaining and disposing of resources
						SWTResourceManager.registerResourceUser(shell1);
					}

					FormLayout shell1Layout = new FormLayout();
				shell1.setLayout(shell1Layout);
				
				FormData calendarLData = new FormData();
				calendarLData.width = 249;
				calendarLData.height = 181;
				calendarLData.left =  new FormAttachment(1, 1000, 0);
				calendarLData.right =  new FormAttachment(508, 1000, 0);
				calendarLData.top =  new FormAttachment(2, 1000, 0);
				calendarLData.bottom =  new FormAttachment(1002, 1000, 0);
				calendar1 = new DateTime (shell1, SWT.CALENDAR);
				calendar1.setLayoutData(calendarLData);
				calendar1.setBackground(SWTResourceManager.getColor(255,255,255));
				String day = "";
				String month = "";
				if((calendar1.getMonth()+1) <= 9){
					month = "0"+(calendar1.getMonth()+1);
				}
				else{
					month = (calendar1.getMonth()+1)+"";
				}
				
				if(calendar1.getDay() <=9 ){
					day = "0"+calendar1.getDay();
				}
				else{
					day = calendar1.getDay()+"";
				}
				this.startDate = calendar1.getYear()+"-"+month+"-"+day;
				System.out.println(startDate);
				calendar1.addSelectionListener (new SelectionAdapter () {
					@Override
					public void widgetSelected (SelectionEvent e) {
//						System.out.println ("calendar date changed");
						String day = "";
						String month = "";
						if((calendar1.getMonth()+1) <= 9){
							month = "0"+(calendar1.getMonth()+1);
						}
						else{
							month = (calendar1.getMonth()+1)+"";
						}
						
						if(calendar1.getDay() <=9 ){
							day = "0"+calendar1.getDay();
						}
						else{
							day = calendar1.getDay()+"";
						}
						startDate = calendar1.getYear()+"-"+month+"-"+day;
						System.out.println(startDate);
						
					}
				});
					
				
				FormData calendarLData1 = new FormData();
				calendarLData1.width = 242;
				calendarLData1.height = 181;
				calendarLData1.left =  new FormAttachment(508, 1000, 0);
				calendarLData1.right =  new FormAttachment(1001, 1000, 0);
				calendarLData1.top =  new FormAttachment(2, 1000, 0);
				calendarLData1.bottom =  new FormAttachment(1002, 1000, 0);
				calendar2 = new DateTime (shell1, SWT.CALENDAR);
				calendar2.setLayoutData(calendarLData1);
				calendar2.setBackground(SWTResourceManager.getColor(255,255,255));
				day = "";
				month = "";
				if((calendar2.getMonth()+1) <= 9){
					month = "0"+(calendar2.getMonth()+1);
				}
				else{
					month = (calendar2.getMonth()+1)+"";
				}
				
				if(calendar2.getDay() <=9 ){
					day = "0"+calendar2.getDay();
				}
				else{
					day = calendar2.getDay()+"";
				}
				this.endDate = calendar2.getYear()+"-"+month+"-"+day;
				
				calendar2.addSelectionListener (new SelectionAdapter () {
					@Override
					public void widgetSelected (SelectionEvent e) {
						String day = "";
						String month = "";
						if((calendar2.getMonth()+1) <= 9){
							month = "0"+(calendar2.getMonth()+1);
						}
						else{
							month = (calendar2.getMonth()+1)+"";
						}
						
						if(calendar2.getDay() <=9 ){
							day = "0"+calendar2.getDay();
						}
						else{
							day = calendar2.getDay()+"";
						}
						endDate = calendar2.getYear()+"-"+month+"-"+day;
					}
				});
				}

			}
			this.setSize(540, 441);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	
	private String getCurrentDate(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
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
	private AbstractAction getReportFormLabelAbstractAction() {
		AbstractAction reportFormLabelAction = new AbstractAction("Export Reports", null) {
			
			public void actionPerformed(ActionEvent evt) {
				ReportFormDialog.this.dispose();
			}
		};
		return reportFormLabelAction;
	}
	
	private AbstractAction getExportAbstractAction() {
		if(exportAbstractAction == null) {
			exportAbstractAction = new AbstractAction("Export Report", null) {
				public void actionPerformed(ActionEvent evt) {
//					if(startDate == null){
//						startDate = getCurrentDate();
//					}
//					if(endDate == null){
//						endDate = getCurrentDate();
//					}
//					
//					JFileChooser fc = new JFileChooser();
//					fc.setDialogType(JFileChooser.SAVE_DIALOG);
//					FileFilter filter = createFileFilter("Excel Files Only",true,new String[]{"xls"});
//				  	fc.setFileFilter(filter);
//				  	
//				  	int returnVal = fc.showSaveDialog(ReportFormDialog.this);
//				    if(returnVal == JFileChooser.APPROVE_OPTION) {
//				    	String filename = fc.getSelectedFile().getAbsolutePath()+".xls";
//				    	switch(reportType){
//							case 0: //daily sales
//								
//								boolean success = DailySalesReport.generate(filename, db, storeCode, startDate, endDate);
//								if(success){
//							    	   JOptionPane.showMessageDialog(null, 
//												"The report is saved.", 
//												"Saved",JOptionPane.INFORMATION_MESSAGE);
//						        }
//						        else{
//						    	   JOptionPane.showMessageDialog(null, 
//											"Cannot save file", 
//											"Error",JOptionPane.ERROR_MESSAGE);
//						        }
//							break;
//							
//							case 1://daily sales summary
//								 success = DailySales.generate(filename, db, storeCode, startDate, endDate);
//								if(success){
//							    	   JOptionPane.showMessageDialog(null, 
//												"The report is saved.", 
//												"Saved",JOptionPane.INFORMATION_MESSAGE);
//						        }
//						        else{
//						    	   JOptionPane.showMessageDialog(null, 
//											"Cannot save file", 
//											"Error",JOptionPane.ERROR_MESSAGE);
//						        }
//								
//							break;
//							
//							
//							case 2: //pullouts
//								success = PullOutReport.generate(filename, db, storeCode, startDate, endDate);
//								if(success){
//							    	   JOptionPane.showMessageDialog(null, 
//												"The report is saved.", 
//												"Saved",JOptionPane.INFORMATION_MESSAGE);
//						        }
//						        else{
//						    	   JOptionPane.showMessageDialog(null, 
//											"Cannot save file", 
//											"Error",JOptionPane.ERROR_MESSAGE);
//						        }
//							break;
//							
//							case 3:
//								success = TotalMerchandise.generate(filename, db, storeCode, startDate, endDate);
//								if(success){
//							    	   JOptionPane.showMessageDialog(null, 
//												"The report is saved.", 
//												"Saved",JOptionPane.INFORMATION_MESSAGE);
//						        }
//						        else{
//						    	   JOptionPane.showMessageDialog(null, 
//											"Cannot save file", 
//											"Error",JOptionPane.ERROR_MESSAGE);
//						        }
//							break;
//							
//							
//								
//						}
//				    }
					
					
					try {
						OLEViewer.open2("C:\\Users\\Jejo\\Desktop\\temp.xls");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
		}
		return exportAbstractAction;
	}

}
