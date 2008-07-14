package print;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import main.DBManager;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import forms.InvoiceViewer;

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
public class ValidateReceipt extends javax.swing.JDialog {
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JLabel jLabel1;
	private NewReceiptPanel panel;

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
		DBManager db = new DBManager();
		db.connect();
		Vector<String> headerData = new Vector<String>();
			//		Address Branch, Tin, OR_NO, Served Name, Current Date, Current Time
		headerData.add("548 Facilities Ctr. Shaw Blvd");
		headerData.add("TIN 000-051-689-000 VAT");
		headerData.add("OR NO: 001-000000001");
		headerData.add("Served by: ALEX ODAL");
		headerData.add("Date: 4/18/2007");
		headerData.add("Time: 13:45");
		
		Vector<Vector<String>> itemData = new Vector<Vector<String>>();
			Vector<String> itemRowData = new Vector<String>();
			itemRowData.add("xxx");
			itemRowData.add("ASS WOODEN CASTANETS 24’S");
			itemRowData.add("59.75");
			itemRowData.add("49.75");
			itemRowData.add("1");
//			prod code, prod name, current price, selling price, qty,
			
			Vector<String> itemRowData2 = new Vector<String>();
			itemRowData2.add("xxx");
			itemRowData2.add("ASS WOODEN CASTANETS 24’S");
			itemRowData2.add("59.75");
			itemRowData2.add("59.75");
			itemRowData2.add("2");
		itemData.add(itemRowData);
		itemData.add(itemRowData2);
			
		
		Vector<Vector<String>> paymentData = new Vector<Vector<String>>();
			Vector<String> paymentRowData = new Vector<String>();
			//name of payment, amount, change
			paymentRowData.add("Cash");
			paymentRowData.add("100.00");
			paymentRowData.add("0");
		paymentData.add(paymentRowData);
		String vatAmount = "10.00";
		String changeAmount = "10.00";
		ValidateReceipt inst = new ValidateReceipt(frame,headerData, itemData, paymentData, vatAmount, changeAmount, db, "reg");
		inst.setVisible(true);
	}
	
	public ValidateReceipt(JFrame frame) {
		super(frame);
//		panel = new ReceiptPanel();
		initGUI();
	}
	
	public ValidateReceipt(JFrame frame,String[] others, String date, String time,Vector<Vector<String>> data) {
		super(frame);
//		panel = new ReceiptPanel(others,date,time,data);
		initGUI();
	}
	
	public ValidateReceipt(JFrame frame, Vector<String> headerData, Vector<Vector<String>> itemData,Vector<Vector<String>> paymentData, String vatAmount, String changeAmount, DBManager db, String status ){
		super(frame);
		panel = new NewReceiptPanel(headerData, itemData, paymentData, vatAmount, changeAmount, db, status);
		initGUI();
	}
	
	public ValidateReceipt(InvoiceViewer viewer, Vector<String> headerData, Vector<Vector<String>> itemData, Vector<Vector<String>> paymentData, String vatAmount, String changeAmount, DBManager db, String status) {
		super(viewer);
		panel = new NewReceiptPanel(headerData, itemData, paymentData, vatAmount, changeAmount, db, status);
		initGUI();
	}

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setTitle("Validate Receipt");
			this.setModal(true);
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(907, 660, 960, 318, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Print");
				jButton1.setPreferredSize(new java.awt.Dimension(98, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						PrintUtilities.printComponent(panel);
					}
				});
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(27, 806, 80, 99, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Validate Receipt");
				jLabel1.setPreferredSize(new java.awt.Dimension(203, 28));
				jLabel1.setFont(new java.awt.Font("Tahoma",1,18));
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, new AnchorConstraint(80, 879, 867, 99, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane1.setPreferredSize(new java.awt.Dimension(212, 413));
				jScrollPane1.setViewportView(panel);
			}
			this.setSize(296, 559);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
