package forms;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.cloudgarden.resource.SWTResourceManager;

import connection.DataUtil;
import connection.LogHandler;

import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.DBManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

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
public class ProcessEachDeliveryItem extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JButton jButton3;
	private JTextField jTextField1;
	private JLabel jLabel3;
	private JButton jButton2;
	private Shell shell1;
	private Canvas canvas1;
	private DBManager db;
	private String storeCode;
	private DateTime calendar;
	private String startDate;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JPasswordField jPasswordField1;
	private JButton jButton1;
	private JTextField jTextField5;
	private JLabel jLabel7;
	private JTextField jTextField4;
	private JLabel jLabel6;
	private JTextField jTextField3;
	private String delitemno;
	private int quantity;
	private ProcessDeliveryForm parent;
	private LogHandler cachewriter;
	private DataUtil datautil;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ProcessEachDeliveryItem inst = new ProcessEachDeliveryItem(frame);
		inst.setVisible(true);
	}
	
	public ProcessEachDeliveryItem(JFrame parent) {
		super(parent);
		initSwtAwtGUI();
	}
	
	public ProcessEachDeliveryItem(ProcessDeliveryForm parent, DBManager db, String storeCode, String delitemno, int quantity)
	{
		super(parent);
		this.parent = parent;
		this.db = db;
		this.storeCode = storeCode;
		this.delitemno = delitemno;
		this.quantity = quantity;
		cachewriter = new LogHandler();
		datautil = new DataUtil();
		initSwtAwtGUI();
	}
	
	private void initGUI() {
		try {
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("Confirm Delivery Items");
			{
				jPasswordField1 = new JPasswordField();
				getContentPane().add(jPasswordField1, new AnchorConstraint(368, 798, 450, 556, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPasswordField1.setPreferredSize(new java.awt.Dimension(168, 28));
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(858, 707, 940, 576, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Cancel");
				jButton2.setPreferredSize(new java.awt.Dimension(91, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						ProcessEachDeliveryItem.this.dispose();
						parent.resetTable();
					}
				});
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(123, 455, 185, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setText("Date Received");
				jLabel2.setPreferredSize(new java.awt.Dimension(294, 21));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(21, 475, 123, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Confirm Delivery Item");
				jLabel1.setFont(new java.awt.Font("Tahoma",1,18));
				jLabel1.setPreferredSize(new java.awt.Dimension(322, 35));
			}
			{
				canvas1 = new Canvas();
				getContentPane().add(canvas1, new AnchorConstraint(185, 525, 776, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				canvas1.setPreferredSize(new java.awt.Dimension(343, 203));
				canvas1.setBackground(new java.awt.Color(255,255,255));
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3, new AnchorConstraint(164, 687, 246, 556, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel3.setText("Clerk Code");
				jLabel3.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1, new AnchorConstraint(225, 798, 307, 556, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField1.setPreferredSize(new java.awt.Dimension(168, 28));
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3, new AnchorConstraint(225, 869, 307, 798, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton3.setText("...");
				jButton3.setPreferredSize(new java.awt.Dimension(49, 28));
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						Clerk dialog = new Clerk(ProcessEachDeliveryItem.this,"pedi");
//						dialog.setLocationRelativeTo(null);
//						dialog.setVisible(true);
						
					}
				});
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4, new AnchorConstraint(307, 687, 389, 556, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel4.setText("Password");
				jLabel4.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5, new AnchorConstraint(511, 687, 593, 556, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel5.setText("Accepted Quantity");
				jLabel5.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jTextField3 = new JTextField();
				getContentPane().add(jTextField3, new AnchorConstraint(511, 839, 593, 707, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField3.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6, new AnchorConstraint(613, 687, 695, 556, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel6.setText("Missing Quantity");
				jLabel6.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jTextField4 = new JTextField();
				getContentPane().add(jTextField4, new AnchorConstraint(613, 839, 695, 707, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField4.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7, new AnchorConstraint(715, 687, 797, 556, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel7.setText("Damaged Quantity");
				jLabel7.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jTextField5 = new JTextField();
				getContentPane().add(jTextField5, new AnchorConstraint(715, 839, 797, 707, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField5.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(858, 525, 940, 364, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Confirm");
				jButton1.setPreferredSize(new java.awt.Dimension(112, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(jTextField1.getText().length() == 0 || 
								jTextField3.getText().length() == 0 || 
								jTextField4.getText().length() == 0 || 
								jTextField5.getText().length() == 0){
							
							JOptionPane.showMessageDialog(null, 
									"Please complete all fields", 
									"Warning",JOptionPane.ERROR_MESSAGE);
							
						}
						else{
//							ResultSet rs = db.executeQuery("SELECT * FROM clerk_lu WHERE AES_DECRYPT(password,'babyland') = '"+jPasswordField1.getText()+"' AND clerk_code = '"+jTextField1.getText()+"'");
							ResultSet rs = db.executeQuery("SELECT * FROM clerk_lu WHERE password = '"+jPasswordField1.getText()+"' AND clerk_code = '"+jTextField1.getText()+"'");
							try {
								if(rs.next()){
									
									int acceptedqty = 0;
									int missingqty = 0;
									int damagedqty = 0;
									try{
										acceptedqty = Integer.parseInt(jTextField3.getText());
									}
									catch(NumberFormatException ex){
										JOptionPane.showMessageDialog(null, 
												"Invalid number format in input for accepted qty", 
												"Warning",JOptionPane.ERROR_MESSAGE);
									}
									try{
										missingqty = Integer.parseInt(jTextField4.getText());
									}
									catch(NumberFormatException ex){
										JOptionPane.showMessageDialog(null, 
												"Invalid number format in input for missing qty", 
												"Warning",JOptionPane.ERROR_MESSAGE);
									}
									try{
										damagedqty = Integer.parseInt(jTextField5.getText());
									}
									catch(NumberFormatException ex){
										JOptionPane.showMessageDialog(null, 
												"Invalid number format in input for returned qty", 
												"Warning",JOptionPane.ERROR_MESSAGE);
									}
									
									if((acceptedqty+missingqty+damagedqty) == quantity){
										String query = "UPDATE delivery_items SET PROCESSED_STAT = 1, RCVD_DATE = '"+startDate+"', RCVD_CLERK = "+jTextField1.getText()+", ACCEPTED_QTY = "+acceptedqty+", MISSING_QTY = "+missingqty+", DAMAGED_QTY = "+damagedqty+" WHERE DEL_ITEM_NO = "+delitemno;
										int num = db.executeUpdate(query);
										if(num > 0){
											String[] keyFields = new String[1];
											keyFields[0] = "DEL_ITEM_NO";
											String[] keys = new String[1];
											keys[0] = delitemno;
//											
											String dataToLog = DataUtil.rowToData("EDIT","delivery_items",keyFields,keys);
											cachewriter.writeToFile(dataToLog);
											
											JOptionPane.showMessageDialog(null, 
													"Succesfully Processed Delivery Item", 
													"Warning",JOptionPane.INFORMATION_MESSAGE);
											parent.resetTable();
											ProcessEachDeliveryItem.this.dispose();
											updateDeliveryStatus();
										}
									}
									else{
										JOptionPane.showMessageDialog(null, 
												"The sum of your inputs of accepted,returned,missing qty is not equal \n to the original qty of the delivery item", 
												"Warning",JOptionPane.ERROR_MESSAGE);
									}
									
								}
								else{
									JOptionPane.showMessageDialog(null, 
											"Invalid Clerk Login", 
											"Warning",JOptionPane.ERROR_MESSAGE);
								}
							}
							catch(SQLException ex){
								ex.printStackTrace();
							}
						}
					}
				});
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
				System.out.println(startDate);
				calendar.addSelectionListener (new SelectionAdapter () {
					@Override
					public void widgetSelected (SelectionEvent e) {
//						System.out.println ("calendar date changed");
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
						System.out.println(startDate);
						
					}
				});

				
			}
			this.setSize(701, 377);
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

		@Override
		public void run() {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					//make sure the GUI is created inside the SWT display thread
					//otherwise you will get invalid-thread-access errors, and
					//make sure it is visible before calling the SWT_AWT.new_Shell
					//method, otherwise a "No handles" error will be thrown.
					setVisible(true);
					setLocationRelativeTo(null);
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
	 public void setClerk(String name){
		 jTextField1.setText(name);
	 }
	
	 /**
	  * Update delivery status if all delivery items have been processed
	  */
	private void updateDeliveryStatus() {
		System.out.println("Updating delivery status...");
		String query = "select processed_stat from delivery_items where del_no = (select del_no from delivery_items where del_item_no = "+delitemno+")";
		ResultSet rs = null;
		try {
			rs = db.executeQuery(query);
			while (rs.next()) {
				int result = rs.getInt(1);
				if (result == 0) {
					System.out.println("Delivery status is still pending.");
					return; 
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (rs!=null) {
				try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
		// All delivery items has been processed if you reach this part of code
		System.out.println("Delivery status is now complete.");
		query = "update deliveries set status = 'complete' where del_no = (select del_no from delivery_items where del_item_no = "+delitemno+")";
		if (db.executeUpdate(query) < 1) {
			System.err.println("Unable to update delivery status to completed. Delivery Item = "+delitemno);
		}
	}
	

}
