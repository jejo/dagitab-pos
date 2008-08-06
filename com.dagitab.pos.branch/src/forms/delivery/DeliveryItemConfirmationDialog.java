package forms.delivery;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

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
public class DeliveryItemConfirmationDialog extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JButton jButton2;
	private Shell shell1;
	private Canvas canvas1;
	private DateTime calendar;
	private String startDate;
	private JLabel jLabel5;
	private JButton confirmButton;
	private JTextField jTextField5;
	private JLabel jLabel7;
	private JTextField jTextField4;
	private JLabel jLabel6;
	private JTextField jTextField3;
	
	

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
		DeliveryItemConfirmationDialog inst = new DeliveryItemConfirmationDialog(frame);
		inst.setVisible(true);
	}
	
	public DeliveryItemConfirmationDialog(JFrame parent) {
		super(parent);
		initSwtAwtGUI();
	}
	
	
	private void initGUI() {
		try {
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			getContentPane().setLayout(null);
			this.setTitle("Confirm Delivery Items");
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("Cancel");
				jButton2.setBounds(251, 450, 90, 28);
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						
					}
				});
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Date Received");
				jLabel2.setBounds(187, 40, 85, 22);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Delivery Item Confirmation");
				jLabel1.setFont(new java.awt.Font("Tahoma",0,18));
				jLabel1.setPreferredSize(new java.awt.Dimension(227, 34));
				jLabel1.setBounds(10, 7, 227, 34);
			}
			{
				canvas1 = new Canvas();
				getContentPane().add(canvas1);
				canvas1.setBackground(new java.awt.Color(255,255,255));
				canvas1.setBounds(61, 61, 342, 210);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Accepted Quantity");
				jLabel5.setBounds(120, 300, 96, 24);
			}
			{
				jTextField3 = new JTextField();
				getContentPane().add(jTextField3);
				jTextField3.setBounds(251, 300, 96, 24);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Missing Quantity");
				jLabel6.setBounds(120, 340, 96, 24);
			}
			{
				jTextField4 = new JTextField();
				getContentPane().add(jTextField4);
				jTextField4.setBounds(251, 335, 96, 24);
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Damaged Quantity");
				jLabel7.setBounds(120, 375, 96, 24);
			}
			{
				jTextField5 = new JTextField();
				getContentPane().add(jTextField5);
				jTextField5.setBounds(251, 370, 96, 24);
			}
			{
				confirmButton = new JButton();
				getContentPane().add(confirmButton);
				confirmButton.setText("Confirm");
				confirmButton.setBounds(120, 450, 110, 28);
				
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
				calendarLData.widthHint = 331;
				calendarLData.heightHint = 198;
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
			this.setSize(480, 540);
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
	
	 
	
	

}
