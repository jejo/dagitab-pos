package forms.delivery;
import bus.DeliveryItemService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.cloudgarden.resource.SWTResourceManager;

import forms.AboutDialog;

import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

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
public class DeliveryItemsConfirmationDialog extends javax.swing.JDialog {
	private JLabel deliveryItemsLabel;
	private JLabel jLabel2;
	private JButton jButton2;
	private Shell shell1;
	private Canvas canvas1;
	private DBManager db;
	private String storeCode;
	private DateTime calendar;
	private String startDate;
	private JLabel jLabel5;
	private JButton jButton1;
	private JTextField damagedQuantityTextField;
	private JLabel jLabel7;
	private JTextField missingQuantityTextField;
	private JLabel jLabel6;
	private JTextField acceptedQuantityTextField;
	private String delitemno;
	private int quantity;
	
	private DeliveryItemsConfirmationDialog deliveryItemsConfirmationDialog;
	private Long deliveryItemId;
	private DeliveryDialog deliveryDialog;

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
		DeliveryItemsConfirmationDialog inst = new DeliveryItemsConfirmationDialog(frame);
		inst.setVisible(true);
	}
	
	public DeliveryItemsConfirmationDialog(JFrame parent) {
		super(parent);
		initSwtAwtGUI();
	}
	
	public DeliveryItemsConfirmationDialog(JFrame parent, DeliveryDialog deliveryDialog, Long deliveryItemId) {
		super(parent);
		initSwtAwtGUI();
		this.deliveryDialog = deliveryDialog;
		this.deliveryItemsConfirmationDialog = this;
		this.deliveryItemId = deliveryItemId;
	}
	
	private void initGUI() {
		try {
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("Confirm Delivery Items");
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(858, 707, 940, 576, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Cancel");
				jButton2.setPreferredSize(new java.awt.Dimension(91, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						deliveryItemsConfirmationDialog.setVisible(false);
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
				deliveryItemsLabel = new JLabel();
				getContentPane().add(deliveryItemsLabel, new AnchorConstraint(21, 475, 123, 10, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deliveryItemsLabel.setText("Delivery Item Confirmation");
				deliveryItemsLabel.setFont(new java.awt.Font("Tahoma",0,18));
				deliveryItemsLabel.setPreferredSize(new java.awt.Dimension(322, 35));
				deliveryItemsLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "deliveryItemsLabel");
				deliveryItemsLabel.getActionMap().put("deliveryItemsLabel",getDeliveryItemsLabelAbstractAction() );
			}
			{
				canvas1 = new Canvas();
				getContentPane().add(canvas1, new AnchorConstraint(185, 525, 776, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				canvas1.setPreferredSize(new java.awt.Dimension(343, 203));
				canvas1.setBackground(new java.awt.Color(255,255,255));
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5, new AnchorConstraint(186, 688, 271, 555, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel5.setText("Accepted Quantity");
				jLabel5.setPreferredSize(new java.awt.Dimension(91, 29));
			}
			{
				acceptedQuantityTextField = new JTextField();
				getContentPane().add(acceptedQuantityTextField, new AnchorConstraint(186, 848, 259, 721, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				acceptedQuantityTextField.setPreferredSize(new java.awt.Dimension(87, 25));
				acceptedQuantityTextField.setText("0");
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6, new AnchorConstraint(338, 686, 417, 555, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel6.setText("Missing Quantity");
				jLabel6.setPreferredSize(new java.awt.Dimension(90, 27));
			}
			{
				missingQuantityTextField = new JTextField();
				getContentPane().add(missingQuantityTextField, new AnchorConstraint(338, 851, 409, 720, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				missingQuantityTextField.setPreferredSize(new java.awt.Dimension(90, 24));
				missingQuantityTextField.setText("0");
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7, new AnchorConstraint(497, 707, 579, 555, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel7.setText("Damaged Quantity");
				jLabel7.setPreferredSize(new java.awt.Dimension(104, 28));
			}
			{
				damagedQuantityTextField = new JTextField();
				getContentPane().add(damagedQuantityTextField, new AnchorConstraint(497, 850, 570, 721, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				damagedQuantityTextField.setPreferredSize(new java.awt.Dimension(88, 25));
				damagedQuantityTextField.setText("0");
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(858, 525, 940, 364, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Confirm");
				jButton1.setPreferredSize(new java.awt.Dimension(112, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						updatePendingDeliveryItem(deliveryItemId, Long.parseLong(acceptedQuantityTextField.getText()), Long.parseLong(missingQuantityTextField.getText()), Long.parseLong(damagedQuantityTextField.getText()));
						deliveryItemsConfirmationDialog.setVisible(false);
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
	private AbstractAction getDeliveryItemsLabelAbstractAction() {
		AbstractAction deliveryItemsLabelAction = new AbstractAction("Delivery Item Confirmation", null) {
			
			public void actionPerformed(ActionEvent evt) {
				DeliveryItemsConfirmationDialog.this.dispose();
			}
		};
		return deliveryItemsLabelAction;
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

	public DeliveryItemsConfirmationDialog getDeliveryItemsConfirmationDialog() {
		return deliveryItemsConfirmationDialog;
	}

	public void setDeliveryItemsConfirmationDialog(
			DeliveryItemsConfirmationDialog deliveryItemsConfirmationDialog) {
		this.deliveryItemsConfirmationDialog = deliveryItemsConfirmationDialog;
	}

	public Long getDeliveryItemId() {
		return deliveryItemId;
	}

	public void setDeliveryItemId(Long deliveryItemId) {
		this.deliveryItemId = deliveryItemId;
	}

	public DeliveryDialog getDeliveryDialog() {
		return deliveryDialog;
	}

	public void setDeliveryDialog(DeliveryDialog deliveryDialog) {
		this.deliveryDialog = deliveryDialog;
	}
	
	public void updatePendingDeliveryItem(Long deliveryItemId, Long acceptedQuantity, Long missingQuantity, Long damagedQuantity) {
		//SimpleDateFormat sdf = new SimpleDateFormat();
		//Calendar calendarInstance = Calendar.getInstance();
		//calendarInstance.set(calendar.getYear(), calendar.getMonth(), calendar.getDay());
		DeliveryItemService.updateDeliveryItem(deliveryItemId, startDate,acceptedQuantity, missingQuantity, damagedQuantity);
		
		deliveryDialog.refreshPendingDeliveryItemsTable((Long) deliveryDialog.getPendingDeliveryTable().getValueAt(deliveryDialog.getPendingDeliveryTable().getSelectedRow(), 0));
		deliveryDialog.updateSelectedPendingDelivery();
	}

	public JTextField getDamagedQuantityTextField() {
		return damagedQuantityTextField;
	}

	public void setDamagedQuantityTextField(JTextField damagedQuantityTextField) {
		this.damagedQuantityTextField = damagedQuantityTextField;
	}

	public JTextField getMissingQuantityTextField() {
		return missingQuantityTextField;
	}

	public void setMissingQuantityTextField(JTextField missingQuantityTextField) {
		this.missingQuantityTextField = missingQuantityTextField;
	}

	public JTextField getAcceptedQuantityTextField() {
		return acceptedQuantityTextField;
	}

	public void setAcceptedQuantityTextField(JTextField acceptedQuantityTextField) {
		this.acceptedQuantityTextField = acceptedQuantityTextField;
	}

	//===== end of SWT_AWT special handler code =============
	//$protect<<$
	
	 /**
	  * Update delivery status if all delivery items have been processed
	  */
	
	

}
