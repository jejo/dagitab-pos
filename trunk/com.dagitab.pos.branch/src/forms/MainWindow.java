package forms;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import main.PendingTransactionData;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import connection.DataUtil;
import connection.LogHandler;
import domain.Transaction;
import forms.deferred.DeferredPanel;
import forms.invoice.InvoicePanel;
import forms.partial.PartialPanel;
import forms.pending.PendingPanel;
import forms.receipts.ValidateReceipt;
import forms.returned.ReturnedPanel;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainWindow extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JScrollPane jScrollPane1;

	private JCheckBox jCheckBox1;

	private JTextField jTextField6;
	private JTextField jTextField20;

	private JTextField jTextField14;
	private JTextField jTextField12;
	private JTextField jTextField11;

	private JTextField jTextField8;
	private JTextField jTextField7;
	private JTextField jTextField3;
	private JTextField jTextField1;
	private JLabel jLabel2;

	private JPasswordField jPasswordField2;
	
	private JButton jButton17;
	private JTable jTable4;

	private JScrollPane jScrollPane4;
	private JTextField jTextField10;
	
	private JTable jTable3;

	private JScrollPane jScrollPane3;

	private JTextField jTextField13;
	
	private JTable jTable7;
	private JTextField jTextField19;
	private JTextField jTextField18;
	private JTextField jTextField17;
	private JTextField jTextField16;
	private JTable jTable6;
	private JScrollPane jScrollPane7;
	private JScrollPane jScrollPane6;
	private JTable jTable5;
	private JScrollPane jScrollPane5;
	private JTextField jTextField9;
	private JTextField jTextField5;
	private JTable jTable2;
	private JScrollPane jScrollPane2;
	private JTable jTable1;
	private JTextField jTextField4;
	private JTextField jTextField2;
	public JLabel jLabel3;
	private JLabel jLabel1;
	private static JPanel returnedPanel;
	private static JPanel deferredPanel;
	private JPanel jPanel3;
	private static JPanel partialPanel;
	private static JPanel invoicePanel;
	private JTabbedPane jTabbedPane1;

	private Vector<Vector<String>> productItems;
	private Vector<Vector<String>> paymentItems;

	private Vector<Vector<String>> returnedItems;
	private Vector<Vector<String>> replacedItems;
	

	private static JPanel pendingPanel;
	private JPasswordField jPasswordField4;
	private Vector<Vector<String>> replacedPaymentItems;
	private LogHandler cachewriter;

	private Vector<PendingTransactionData> pausedData;
	private ArrayList<Transaction> pendingTransactions;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {

		// ServerPropertyHandler handler = new ServerPropertyHandler();
		// final String[] val = handler.readFromFile();
		// if(val[0].equals("") || val[1].equals(""))
		// {
		// JFrame frame = new JFrame();
		// Configuration dialog = new Configuration(frame);
		// dialog.setLocationRelativeTo(null);
		// dialog.setVisible(true);
		// JOptionPane.showMessageDialog(null,"Please Restart
		// Application","Restart",JOptionPane.INFORMATION_MESSAGE);
		// frame.dispose();
		// }
		// else{
		// MainWindow inst = new MainWindow();
		// inst.setVisible(true);
		// }
		//		
	}

	public MainWindow() {
		super();

		cachewriter = new LogHandler();
		initGUI();
		pausedData = new Vector<PendingTransactionData>();
		productItems = new Vector<Vector<String>>();
		paymentItems = new Vector<Vector<String>>();
		returnedItems = new Vector<Vector<String>>();
		replacedItems = new Vector<Vector<String>>();
		replacedPaymentItems = new Vector<Vector<String>>();
		pendingTransactions = new ArrayList<Transaction>();
	}

	private void initGUI() {
		try {
			{
				this.setTitle("Babyland POS Application");
				getContentPane().setBackground(
						new java.awt.Color(255, 255, 255));
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				this.setExtendedState(Frame.MAXIMIZED_BOTH);
				this
						.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				this.setIconImage(new ImageIcon(getClass().getClassLoader()
						.getResource("images/payments.png")).getImage());
				this.setFocusTraversalKeysEnabled(false);
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent event) {
						int n = JOptionPane
								.showConfirmDialog(
										MainWindow.this,
										"Are you sure you want to exit this application?",
										"Confirmation",
										JOptionPane.YES_NO_OPTION);
						if (n == 0) {
							System.exit(0);
						} else {

						}

					}
				});

				{
					jLabel1 = new JLabel();
					getContentPane().add(
							jLabel1,
							new AnchorConstraint(13, 984, 63, 849,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
					jLabel1.setPreferredSize(new java.awt.Dimension(119, 28));
					jLabel1.setIcon(new ImageIcon(getClass().getClassLoader()
							.getResource("images/logo.jpg")));
				}
				{
					jPanel3 = new JPanel();
					AnchorLayout jPanel3Layout = new AnchorLayout();
					jPanel3.setLayout(jPanel3Layout);
					getContentPane().add(
							jPanel3,
							new AnchorConstraint(970, 1008, 1008, 0,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
					jPanel3.setPreferredSize(new java.awt.Dimension(889, 21));
					jPanel3.setEnabled(false);
					jPanel3.setBorder(new LineBorder(
							new java.awt.Color(0, 0, 0), 1, false));
					{
						jLabel3 = new JLabel();
						jPanel3.add(jLabel3, new AnchorConstraint(23, 95, 690,
								8, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						jLabel3.setText("Not Connected");
						jLabel3
								.setPreferredSize(new java.awt.Dimension(77, 14));
					}
				}
				{
					// tab panels
					jTabbedPane1 = new JTabbedPane();
					getContentPane().add(
							jTabbedPane1,
							new AnchorConstraint(25, 992, 963, 8,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
					jTabbedPane1.setPreferredSize(new java.awt.Dimension(868,
							525));
					jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14));
					jTabbedPane1
							.setBackground(new java.awt.Color(255, 255, 255));
					jTabbedPane1.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {

							if (jTabbedPane1.getSelectedIndex() == 0) { // regular

							} else if (jTabbedPane1.getSelectedIndex() == 1) { // partial
								((PartialPanel) partialPanel)
										.refreshPartialTable();
							} else if (jTabbedPane1.getSelectedIndex() == 2) { // deferred
								((DeferredPanel) deferredPanel)
										.refreshDeferredTable();
							} else if (jTabbedPane1.getSelectedIndex() == 3) { // returned
							} else if (jTabbedPane1.getSelectedIndex() == 4) { // pending
								((PendingPanel) pendingPanel)
										.refreshPendingTable();
							}
						}
					});
					{
						invoicePanel = new InvoicePanel(this);
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/add.png"));
						jTabbedPane1.addTab("Invoice", icon, invoicePanel, null);

					}
					{
						partialPanel = new PartialPanel(this);
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/partial.png"));
						jTabbedPane1.addTab("Partial", icon, partialPanel, null);

					}
					{
						deferredPanel = new DeferredPanel(this);
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/deferred.png"));
						jTabbedPane1.addTab("Deferred", icon, deferredPanel,null);
					}
					{
						returnedPanel = new ReturnedPanel();
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/Files-text.png"));
						jTabbedPane1.addTab("Returned Items", icon,returnedPanel, null);
					}
					{
						pendingPanel = new PendingPanel(this, (InvoicePanel) invoicePanel);
						ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/pausepic.png"));
						 jTabbedPane1.addTab("Pending Transactions",icon,pendingPanel,null);

					}
				}
			}
			this.setSize(890, 615);
			{
				setJMenuBar(MenuHelper.getMenuBar());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getInvoicePanel() {
		return invoicePanel;
	}

	/*
	 * 
	 * DEPRECATED PARTS!!!
	 * 
	 */
	// functions for payment item table part if main invoice or for returned
	// items
	public void setPaymentTable(Vector<String> args, String part) {
		if (part.equals("main")) {
			if (args != null) {
				paymentItems.add(args);
			}

			Object[][] data = new Object[paymentItems.size()][7];
			for (int i = 0; i < paymentItems.size(); i++) {
				for (int j = 0; j < 7; j++) {
					data[i][j] = paymentItems.get(i).get(j);
				}
			}

			TableModel jTable1Model = new DefaultTableModel(data, new String[] {
					"Payment Code", "Payment Type", "Amount",
					"Credit Card Type", "Credit Card No", "Bank Check No",
					"Gift Certificate Number" });
			jTable2 = new JTable() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			jScrollPane2.setViewportView(jTable2);
			jTable2.setModel(jTable1Model);
		}

		else {
			// replaced payments
			if (args != null) {
				replacedPaymentItems.add(args);
			}

			Object[][] data = new Object[replacedPaymentItems.size()][7];
			for (int i = 0; i < replacedPaymentItems.size(); i++) {
				for (int j = 0; j < 7; j++) {
					data[i][j] = replacedPaymentItems.get(i).get(j);
				}
			}

			TableModel jTable1Model = new DefaultTableModel(data, new String[] {
					"Payment Code", "Payment Type", "Amount",
					"Credit Card Type", "Credit Card No", "Bank Check No",
					"Gift Certificate Number" });
			jTable7 = new JTable() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			jScrollPane6.setViewportView(jTable7);
			jTable7.setModel(jTable1Model);
		}
	}

	public void setThisPaymentTable(Vector<String> args, int index, String part) {
		// for editing
		if (part.equals("main")) {
			paymentItems.set(index, args);
		} else {
			replacedPaymentItems.set(index, args);
		}
		setPaymentTable(null, part);
	}

	public boolean hasPaymentCash(String part) {
		if (part.equals("main")) {
			for (int i = 0; i < paymentItems.size(); i++) {
				if (paymentItems.get(i).get(1).equals("Cash")) {
					return true;
				}
			}
		} else {
			for (int i = 0; i < replacedPaymentItems.size(); i++) {
				if (replacedPaymentItems.get(i).get(1).equals("Cash")) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasSameCreditCard(String no, String part) {
		if (part.equals("main")) {
			for (int i = 0; i < paymentItems.size(); i++) {
				if (paymentItems.get(i).get(4).equals(no)) {
					return true;
				}
			}
			return false;
		} else {
			for (int i = 0; i < replacedPaymentItems.size(); i++) {
				if (replacedPaymentItems.get(i).get(4).equals(no)) {
					return true;
				}
			}
			return false;
		}
	}

	public boolean hasSameBankCheck(String no, String part) {
		if (part.equals("main")) {
			for (int i = 0; i < paymentItems.size(); i++) {
				if (paymentItems.get(i).get(5).equals(no)) {
					return true;
				}
			}
			return false;
		} else {
			for (int i = 0; i < replacedPaymentItems.size(); i++) {
				if (replacedPaymentItems.get(i).get(5).equals(no)) {
					return true;
				}
			}
			return false;
		}
	}

	public boolean hasSameGiftCertificate(String no, String part) {
		if (part.equals("main")) {
			for (int i = 0; i < paymentItems.size(); i++) {
				if (paymentItems.get(i).get(6).equals(no)) {
					return true;
				}
			}
			return false;
		} else {
			for (int i = 0; i < replacedPaymentItems.size(); i++) {
				if (replacedPaymentItems.get(i).get(6).equals(no)) {
					return true;
				}
			}
			return false;
		}

	}

	// functions for product item table
	public boolean hasProductItem(String prodcode) {

		for (int i = 0; i < productItems.size(); i++) {

			if (productItems.get(i).get(0).equals(prodcode)) {
				return true;
			}
		}
		return false;
	}

	public void setThisProductTable(Vector<String> args, int index) {
		// for editing
		productItems.set(index, args);
		setProductTable(null);
	}

	public void setProductTable(Vector<String> args) {
		if (args != null) {
			productItems.add(args);
		}

		Object[][] data = new Object[productItems.size()][8];
		for (int i = 0; i < productItems.size(); i++) {
			for (int j = 0; j < 8; j++) {
				data[i][j] = productItems.get(i).get(j);
			}
		}

		TableModel jTable1Model = new DefaultTableModel(data, new String[] {
				"Product Code", "Product Name", "Quantity", "Current Price",
				"Selling Price", "Deferred", "Disc Code", "Extension" });
		jTable1 = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.getSelectionModel().addListSelectionListener(
				new SelectionListener(jTable1) {
					public void valueChanged(ListSelectionEvent e) {
						if (e.getSource() == jTable1.getSelectionModel()
								&& jTable1.getRowSelectionAllowed()) {
							// Column selection changed
							int first = e.getFirstIndex();
							int last = e.getLastIndex();

							String prodcode = jTable1.getValueAt(
									jTable1.getSelectedRow(), 0).toString();

							ResultSet rs = Main.getDBManager().executeQuery(
									"SELECT * FROM products_lu "
											+ "WHERE PACKAGE = '1' "
											+ "AND PROD_CODE = '" + prodcode
											+ "'");
							try {
								if (rs.next()) {
									jButton17.setEnabled(true);
								} else {
									jButton17.setEnabled(false);
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				});
	}

	// clerk login
	public void setCashierID(String id) {
		jTextField6.setText(id);
	}

	public void setSalesSpecID(String id) {
		jTextField5.setText(id);
	}

	// customer add
	public void setCustomer(String id) {
		jTextField9.setText(id);
	}

	// update amounts
	public void updateAmounts() {
		// update amount
		double amount = 0;
		ResultSet rs = Main.getDBManager().executeQuery(
				"SELECT VAT FROM global");
		double VAT = 0; // customizable
		try {
			if (rs.next()) {
				VAT = Double.parseDouble("0." + rs.getString("VAT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < productItems.size(); i++) {
			int quantity = Integer.parseInt(productItems.get(i).get(2)
					.toString());
			double price = Double.parseDouble(productItems.get(i).get(4)
					.toString());
			amount += (quantity * price);
		}
		jLabel2.setText(String.format("%.2f", amount));

		// update SubTotal
		VAT = VAT + 1;
		double subTotal = amount / VAT;
		jTextField1.setText(String.format("%.2f", subTotal));
		jTextField3.setText(String.format("%.2f", (amount - subTotal)));

		// update payments
		double paymentAmount = 0;

		for (int i = 0; i < paymentItems.size(); i++) {
			paymentAmount += Double.parseDouble(paymentItems.get(i).get(2)
					.toString());
		}
		jTextField7.setText(String.format("%.2f", paymentAmount));

		// update change
		if (hasPaymentCash("main")) {
			double paymentCash = 0;

			for (int i = 0; i < jTable2.getRowCount(); i++) {
				if (jTable2.getValueAt(i, 1).toString().equals("Cash")) {
					paymentCash = Double.parseDouble(jTable2.getValueAt(i, 2)
							.toString());
					break;
				}
			}

			double change = paymentAmount - amount;
			if (change > paymentCash) {
				jTextField8.setText(String.format("%.2f", paymentCash));
			} else {
				jTextField8.setText(String.format("%.2f", change));
			}

		} else {
			double change = 0;
			jTextField8.setText(String.format("%.2f", change));
		}

	}

	// process transaction
	private void processTransaction(String stat) throws SQLException {

		String message = "You are about to process this transaction. \n "
				+ "Are you sure you want to continue?";
		if (stat.equals("partial")) {
			message = "You are about to process this partial transaction. \n "
					+ "Are you sure you want to continue?";
		}
		int n = JOptionPane.showConfirmDialog(MainWindow.this, message,
				"Confirmation", JOptionPane.YES_NO_OPTION);

		if (n == 0) {

			// update invoice
			ResultSet res = Main.getDBManager().executeQuery(
					"SELECT * FROM invoice LIMIT 1");
			res.moveToInsertRow();
			if (jTextField4.getText().length() == 0) {
				res.updateObject("INVOICE_NO", 0);
			} else {
				res.updateObject("INVOICE_NO", jTextField4.getText());
			}
			res.updateObject("ENCODER_CODE", jTextField6.getText());
			if (jTextField5.getText().length() == 0) {
				res.updateObject("ASSIST_CODE", 0);
			} else {
				res.updateObject("ASSIST_CODE", jTextField5.getText());
			}
			if (jTextField9.getText().length() == 0) {
				res.updateObject("CUST_NO", 0);
			} else {
				res.updateObject("CUST_NO", jTextField9.getText());
			}
			res.updateObject("STORE_CODE", Main.getStoreCode());
			if (stat.equals("partial")) {
				res.updateObject("PARTIAL", 1);
			} else {
				res.updateObject("PARTIAL", 0);
			}
			res.insertRow();

			String[] keyFields = new String[2];
			keyFields[0] = "OR_NO";
			keyFields[1] = "STORE_CODE";

			String[] keys = new String[2];
			keys[0] = jTextField2.getText();
			keys[1] = Main.getStoreCode();

			String dataToLog = DataUtil.rowToData("ADD", "invoice", keyFields,
					keys);
			cachewriter.writeToFile(dataToLog);

			// update invoice_item
			for (int i = 0; i < productItems.size(); i++) {
				res = Main.getDBManager().executeQuery(
						"SELECT * FROM invoice_item LIMIT 1");
				res.moveToInsertRow();
				res.updateObject("OR_NO", jTextField2.getText());
				res.updateObject("PROD_CODE", productItems.get(i).get(0)
						.toString());
				res.updateObject("DISC_CODE", productItems.get(i).get(6)
						.toString());
				res.updateObject("QUANTITY", productItems.get(i).get(2)
						.toString());
				res.updateObject("STORE_CODE", Main.getStoreCode());
				if (productItems.get(i).get(5).toString().equals("Yes")) {
					res.updateObject("deferred", 1);
				} else {
					res.updateObject("deferred", 0);
				}
				res.updateObject("SELL_PRICE", productItems.get(i).get(4)
						.toString());

				ResultSet temprs = Main.getDBManager().executeQuery(
						"SELECT COST FROM products_lu "
								+ "WHERE PROD_CODE = \""
								+ productItems.get(i).get(0).toString() + "\"");
				if (temprs.next()) {
					res.updateObject("COST", temprs.getString("COST"));
				}
				res.insertRow();

				String dataToLog2 = DataUtil.rowToData("ADD", "invoice_item",
						new String[] { "OR_NO", "PROD_CODE", "STORE_CODE" },
						new String[] { jTextField2.getText(),
								productItems.get(i).get(0).toString(),
								Main.getStoreCode() });
				cachewriter.writeToFile(dataToLog2);
			}

			// update payment_items
			for (int i = 0; i < paymentItems.size(); i++) {
				double paymentCash = 0;
				double amount = 0;
				if (jTable2.getValueAt(i, 1).toString().equals("Cash")) {
					paymentCash = Double.parseDouble(jTable2.getValueAt(i, 2)
							.toString());
					if (Double.parseDouble(jTextField8.getText()) > 0) {
						amount = paymentCash
								- Double.parseDouble(jTextField8.getText());
					} else {
						amount = paymentCash;
					}

				} else {
					amount = Double.parseDouble(jTable2.getValueAt(i, 2)
							.toString());
				}
				String query = "INSERT INTO payment_item "
						+ "(OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO,STORE_CODE) VALUES "
						+ "(" + jTextField2.getText() + ","
						+ paymentItems.get(i).get(0).toString() + "," + amount
						+ ",'" + paymentItems.get(i).get(3).toString() + "','"
						+ paymentItems.get(i).get(4).toString() + "','"
						+ paymentItems.get(i).get(6).toString() + "','"
						+ paymentItems.get(i).get(5).toString() + "','"
						+ Main.getStoreCode() + "')";

				Main.getDBManager().executeUpdate(query);

				String dataToLog2 = DataUtil.rowToData("ADD", "payment_item",
						new String[] { "OR_NO", "PT_CODE", "CARD_NO", "GC_NO",
								"CHECK_NO", "STORE_CODE" }, new String[] {
								jTextField2.getText(),
								paymentItems.get(i).get(0).toString(),
								paymentItems.get(i).get(4).toString(),
								paymentItems.get(i).get(6).toString(),
								paymentItems.get(i).get(5).toString(),
								Main.getStoreCode() });
				cachewriter.writeToFile(dataToLog2);

			}

			if (!stat.equals("partial")) {
				/** Make Receipt * */
				Vector<String> headerData = new Vector<String>();

				ResultSet rs = Main.getDBManager().executeQuery(
						"SELECT * FROM store_lu WHERE STORE_CODE ="
								+ Main.getStoreCode());
				if (rs.next()) {
					headerData.add(rs.getString(3));
				}

				try {
					BufferedReader br = new BufferedReader(new FileReader(
							"data/.tinno"));
					headerData.add(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String OR_NO = Main.getStoreCode() + "-"
						+ jTextField2.getText();
				headerData.add(OR_NO);

				rs = Main.getDBManager().executeQuery(
						"SELECT * FROM clerk_lu WHERE CLERK_CODE = \""
								+ jTextField6.getText() + "\"");
				if (rs.next()) {
					String name = rs.getString(4) + " " + rs.getString(3);
					headerData.add(name);
				}

				rs = Main
						.getDBManager()
						.executeQuery(
								"SELECT DATE(CURRENT_TIMESTAMP), TIME(CURRENT_TIMESTAMP)");
				if (rs.next()) {
					headerData.add(rs.getString(1));
					headerData.add(rs.getString(2));
				}

				Vector<Vector<String>> itemData = new Vector<Vector<String>>();

				for (int i = 0; i < productItems.size(); i++) {
					Vector<String> row = new Vector<String>();
					String prodcode = productItems.get(i).get(0).toString();
					String prodname = productItems.get(i).get(1).toString();
					String curPrice = productItems.get(i).get(3).toString();
					String selPrice = productItems.get(i).get(4).toString();
					String qty = productItems.get(i).get(2).toString();

					row.add(prodcode);
					row.add(prodname);
					row.add(curPrice);
					row.add(selPrice);
					row.add(qty);
					itemData.add(row);
				}

				Vector<Vector<String>> paymentData = new Vector<Vector<String>>();
				for (int i = 0; i < paymentItems.size(); i++) {
					Vector<String> row = new Vector<String>();
					String paymentName = paymentItems.get(i).get(1).toString();
					String amount = paymentItems.get(i).get(2).toString();
					row.add(paymentName);
					row.add(amount);
					paymentData.add(row);

				}

				String vatAmount = jTextField3.getText();
				String changeAmount = jTextField8.getText();

				ValidateReceipt receiptdialog = new ValidateReceipt(
						MainWindow.this, headerData, itemData, paymentData,
						vatAmount, changeAmount, Main.getDBManager(), "reg");
				receiptdialog.setLocationRelativeTo(null);
				receiptdialog.setVisible(true);

			} else {
				/* MAKE RECEIPT */

				Vector<String> headerData = new Vector<String>();

				ResultSet rs = Main.getDBManager().executeQuery(
						"SELECT * FROM store_lu WHERE STORE_CODE ="
								+ Main.getStoreCode());
				if (rs.next()) {
					headerData.add(rs.getString(3));
				}

				try {
					BufferedReader br = new BufferedReader(new FileReader(
							"data/.tinno"));
					headerData.add(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String OR_NO = Main.getStoreCode() + "-"
						+ jTextField2.getText();
				headerData.add(OR_NO);

				rs = Main.getDBManager().executeQuery(
						"SELECT * FROM clerk_lu WHERE CLERK_CODE = \""
								+ jTextField6.getText() + "\"");
				if (rs.next()) {
					String name = rs.getString(4) + " " + rs.getString(3);
					headerData.add(name);
				}

				rs = Main
						.getDBManager()
						.executeQuery(
								"SELECT DATE(CURRENT_TIMESTAMP), TIME(CURRENT_TIMESTAMP)");
				if (rs.next()) {
					headerData.add(rs.getString(1));
					headerData.add(rs.getString(2));
				}

				Vector<Vector<String>> itemData = new Vector<Vector<String>>();

				for (int i = 0; i < productItems.size(); i++) {
					Vector<String> row = new Vector<String>();
					String prodcode = productItems.get(i).get(0).toString();
					String prodname = productItems.get(i).get(1).toString();
					String curPrice = productItems.get(i).get(3).toString();
					String selPrice = productItems.get(i).get(4).toString();
					String qty = productItems.get(i).get(2).toString();

					row.add(prodcode);
					row.add(prodname);
					row.add(curPrice);
					row.add(selPrice);
					row.add(qty);
					itemData.add(row);
				}

				Vector<Vector<String>> paymentData = new Vector<Vector<String>>();
				for (int i = 0; i < paymentItems.size(); i++) {
					Vector<String> row = new Vector<String>();
					String paymentName = paymentItems.get(i).get(1).toString();
					String amount = paymentItems.get(i).get(2).toString();
					row.add(paymentName);
					row.add(amount);
					paymentData.add(row);

				}

				String vatAmount = jTextField3.getText();
				String changeAmount = jTextField8.getText();

				ValidateReceipt receiptdialog = new ValidateReceipt(
						MainWindow.this, headerData, itemData, paymentData,
						vatAmount, changeAmount, Main.getDBManager(), "par");
				receiptdialog.setLocationRelativeTo(null);
				receiptdialog.setVisible(true);

			}

			updateAll();
		}

	}

	private void updateAll() throws SQLException {
		ResultSet rs = Main.getDBManager().executeQuery(
				"SELECT * FROM invoice WHERE STORE_CODE = '"
						+ Main.getStoreCode()
						+ "' ORDER by OR_NO  DESC LIMIT 1");
		if (rs.next()) {
			int ornum = rs.getInt("OR_NO") + 1;
			String temp = ornum + "";
			int len = 10 - temp.length();

			for (int i = 0; i < len; i++) {
				temp = "0" + temp;
			}
			jTextField2.setText(temp);
			jTextField11.setText(temp);
			jTextField2.setEditable(false);
			jTextField4.setText("");
			jCheckBox1.setSelected(false);
			jTextField6.setText("");
			jPasswordField2.setText("");
			jTextField5.setText("");
			jTextField9.setText("");
			jTextField1.setText("0.00");
			jTextField3.setText("0.00");
			jTextField7.setText("0.00");
			jTextField8.setText("0.00");
			jLabel2.setText("0.00");
			productItems.removeAllElements();
			setProductTable(null);
			paymentItems.removeAllElements();
			setPaymentTable(null, "main");

		}
	}

	// Partial transaction processing
	public void setPartialClerk(String id) {
		jTextField10.setText(id);
	}

	public void setPartialList() {
		ResultSet rs = Main.getDBManager().executeQuery(
				"SELECT OR_NO, TRANS_DT FROM invoice WHERE PARTIAL = 1");
		try {
			Vector<Vector<String>> container = new Vector<Vector<String>>();
			while (rs.next()) {
				Vector<String> temp = new Vector<String>();
				temp.add(rs.getString(1));
				temp.add(rs.getString(2));
				container.add(temp);
			}
			Object[][] data = new Object[container.size()][2];
			for (int i = 0; i < container.size(); i++) {
				data[i][0] = container.get(i).get(0);
				data[i][1] = container.get(i).get(1);
			}

			TableModel jTable1Model = new DefaultTableModel(data, new String[] {
					"OR Number", "Transaction Date & Time" });
			jTable4 = new JTable() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			jScrollPane4.setViewportView(jTable4);
			jTable4.setModel(jTable1Model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// deferred Processing
	public void setDeferredClerk(String id) {
		jTextField13.setText(id);
	}

	private void setDeferredList() {
		ResultSet rs = Main
				.getDBManager()
				.executeQuery(
						"SELECT invoice_item.OR_NO, INVOICE_NO, invoice_item.PROD_CODE, NAME, QUANTITY, invoice.TRANS_DT  "
								+ "FROM `invoice_item`,products_lu,invoice WHERE invoice_item.PROD_CODE = products_lu.PROD_CODE AND invoice_item.OR_NO = invoice.OR_NO AND invoice_item.deferred = 1");
		try {

			Vector<Vector<String>> container = new Vector<Vector<String>>();

			while (rs.next()) {
				Vector<String> temp = new Vector<String>();
				temp.add(rs.getString(1));
				temp.add(rs.getString(2));
				temp.add(rs.getString(3));
				temp.add(rs.getString(4));
				temp.add(rs.getString(5));
				temp.add(rs.getString(6));
				container.add(temp);
			}
			Object[][] data = new Object[container.size()][6];
			for (int i = 0; i < container.size(); i++) {
				data[i][0] = container.get(i).get(0);
				data[i][1] = container.get(i).get(1);
				data[i][2] = container.get(i).get(2);
				data[i][3] = container.get(i).get(3);
				data[i][4] = container.get(i).get(4);
				data[i][5] = container.get(i).get(5);
			}

			TableModel jTable1Model = new DefaultTableModel(data, new String[] {
					"OR Number", "Invoice Number", "Product Code",
					"Product Name", "Quantity", "Transaction Date & Time" });
			jTable3 = new JTable() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			jScrollPane3.setViewportView(jTable3);
			jTable3.setModel(jTable1Model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// returned items processing
	public void setReturnedClerk(String id) {
		jTextField14.setText(id);
	}

	public void setReturnedTable(Vector<String> args) {
		if (args != null) {
			returnedItems.add(args);
		}

		Object[][] data = new Object[returnedItems.size()][8];
		for (int i = 0; i < returnedItems.size(); i++) {
			for (int j = 0; j < 8; j++) {
				data[i][j] = returnedItems.get(i).get(j);
			}
		}
		TableModel jTable1Model = new DefaultTableModel(data, new String[] {
				"Product Code", "Product Name", "Quantity", "Selling Price",
				"Current Price", "Deferred", "Disc Code", "Reason Code" });
		jTable6 = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jScrollPane7.setViewportView(jTable6);
		jTable6.setModel(jTable1Model);
	}

	public boolean hasReturnedItem(String prodcode, String reasonCode) {
		for (int i = 0; i < returnedItems.size(); i++) {

			if (returnedItems.get(i).get(0).equals(prodcode)
					&& returnedItems.get(i).get(7).equals(reasonCode)) {
				return true;
			}
		}
		return false;
	}

	// replacement processing
	public boolean hasReplacedProductItem(String prodcode) {

		for (int i = 0; i < replacedItems.size(); i++) {

			if (replacedItems.get(i).get(0).equals(prodcode)) {
				return true;
			}
		}
		return false;
	}

	public void setThisReplaceTable(Vector<String> args, int index) {
		// for editing
		replacedItems.set(index, args);
		setReplaceTable(null);
	}

	public void setReplaceTable(Vector<String> args) {
		if (args != null) {
			replacedItems.add(args);
		}

		Object[][] data = new Object[replacedItems.size()][7];
		for (int i = 0; i < replacedItems.size(); i++) {
			for (int j = 0; j < 7; j++) {
				data[i][j] = replacedItems.get(i).get(j);
			}
		}

		TableModel jTable1Model = new DefaultTableModel(data, new String[] {
				"Product Code", "Product Name", "Quantity", "Current Price",
				"Selling Price", "Deferred", "Disc Code" });
		jTable5 = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jScrollPane5.setViewportView(jTable5);
		jTable5.setModel(jTable1Model);
	}

	private void processReturnedTransactions() throws SQLException {
		// update invoice
		Main.getDBManager().executeUpdate(
				"UPDATE invoice SET `RETURN` = 1 WHERE OR_NO = "
						+ jTextField20.getText() + " && STORE_CODE = '"
						+ Main.getStoreCode() + "'");
		System.out.println("UPDATE invoice SET `RETURN` = 1 WHERE OR_NO = "
				+ jTextField20.getText() + " && STORE_CODE = '"
				+ Main.getStoreCode() + "'");
		ResultSet res = Main.getDBManager().executeQuery(
				"SELECT * FROM invoice LIMIT 1");
		res.moveToInsertRow();
		if (jTextField12.getText().length() == 0) {
			res.updateObject("INVOICE_NO", 0);
		} else {
			res.updateObject("INVOICE_NO", jTextField12.getText());
		}
		res.updateObject("ENCODER_CODE", jTextField14.getText());
		res.updateObject("ASSIST_CODE", 0);
		/*
		 * if(jTextField5.getText().length() == 0){
		 * res.updateObject("ASSIST_CODE", 0); } else{
		 * res.updateObject("ASSIST_CODE", jTextField5.getText()); }
		 */

		res.updateObject("CUST_NO", 0);
		/*
		 * if(jTextField9.getText().length() == 0){ res.updateObject("CUST_NO",
		 * 0); } else{ res.updateObject("CUST_NO", jTextField9.getText()); }
		 */
		res.updateObject("STORE_CODE", Main.getStoreCode());
		/*
		 * if(stat.equals("partial")){ res.updateObject("PARTIAL",1); } else{
		 *  }
		 */
		res.updateObject("PARTIAL", 0);
		res.insertRow();
		String[] keyFields = new String[2];
		keyFields[0] = "OR_NO";
		keyFields[1] = "STORE_CODE";

		String[] keys = new String[2];
		keys[0] = jTextField11.getText();
		keys[1] = Main.getStoreCode();

		String dataToLog = DataUtil
				.rowToData("ADD", "invoice", keyFields, keys);
		cachewriter.writeToFile(dataToLog);

		// update invoice_item
		for (int i = 0; i < replacedItems.size(); i++) {
			res = Main.getDBManager().executeQuery(
					"SELECT * FROM invoice_item LIMIT 1");
			res.moveToInsertRow();
			res.updateObject("OR_NO", jTextField11.getText());
			res.updateObject("PROD_CODE", replacedItems.get(i).get(0)
					.toString());
			res.updateObject("DISC_CODE", replacedItems.get(i).get(6)
					.toString());
			res
					.updateObject("QUANTITY", replacedItems.get(i).get(2)
							.toString());
			res.updateObject("STORE_CODE", Main.getStoreCode());
			if (replacedItems.get(i).get(5).toString().equals("Yes")) {
				res.updateObject("deferred", 1);
			} else {
				res.updateObject("deferred", 0);
			}
			res.updateObject("SELL_PRICE", replacedItems.get(i).get(4)
					.toString());

			ResultSet temprs = Main.getDBManager().executeQuery(
					"SELECT COST FROM products_lu " + "WHERE PROD_CODE = '"
							+ replacedItems.get(i).get(0).toString() + "'");
			if (temprs.next()) {
				res.updateObject("COST", temprs.getString("COST"));
			}
			res.insertRow();

			String dataToLog2 = DataUtil.rowToData("ADD", "invoice_item",
					new String[] { "OR_NO", "PROD_CODE", "STORE_CODE" },
					new String[] { jTextField11.getText(),
							replacedItems.get(i).get(0).toString(),
							Main.getStoreCode() });
			cachewriter.writeToFile(dataToLog2);
		}

		// update payment_items
		for (int i = 0; i < replacedPaymentItems.size(); i++) {
			double paymentCash = 0;
			double amount = 0;
			if (jTable7.getValueAt(i, 1).toString().equals("Cash")) {
				paymentCash = Double.parseDouble(jTable7.getValueAt(i, 2)
						.toString());
				if (Double.parseDouble(jTextField19.getText()) > 0) {
					amount = paymentCash
							- Double.parseDouble(jTextField19.getText());
				} else {
					amount = paymentCash;
				}

			} else {
				amount = Double
						.parseDouble(jTable7.getValueAt(i, 2).toString());
			}
			String query = "INSERT INTO payment_item "
					+ "(OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO,STORE_CODE) VALUES "
					+ "(" + jTextField11.getText() + ","
					+ replacedPaymentItems.get(i).get(0).toString() + ","
					+ amount + ",'"
					+ replacedPaymentItems.get(i).get(3).toString() + "','"
					+ replacedPaymentItems.get(i).get(4).toString() + "','"
					+ replacedPaymentItems.get(i).get(6).toString() + "','"
					+ replacedPaymentItems.get(i).get(5).toString() + "','"
					+ Main.getStoreCode() + "')";

			Main.getDBManager().executeUpdate(query);

			String dataToLog2 = DataUtil.rowToData("ADD", "payment_item",
					new String[] { "OR_NO", "PT_CODE", "CARD_NO", "GC_NO",
							"CHECK_NO", "STORE_CODE" }, new String[] {
							jTextField11.getText(),
							replacedPaymentItems.get(i).get(0).toString(),
							replacedPaymentItems.get(i).get(4).toString(),
							replacedPaymentItems.get(i).get(6).toString(),
							replacedPaymentItems.get(i).get(5).toString(),
							Main.getStoreCode() });
			cachewriter.writeToFile(dataToLog2);
		}
	}

	private void updateReturnedFields() throws SQLException {
		ResultSet rs = Main.getDBManager()
				.executeQuery(
						"SELECT * FROM invoice WHERE STORE_CODE = '"
								+ Main.getStoreCode()
								+ "' ORDER by OR_NO DESC LIMIT 1");
		if (rs.next()) {
			int ornum = rs.getInt("OR_NO") + 1;
			String temp = ornum + "";
			int len = 10 - temp.length();

			for (int i = 0; i < len; i++) {
				temp = "0" + temp;
			}
			jTextField2.setText(temp);
			jTextField11.setText(temp);
			jTextField2.setEditable(false);
			jTextField12.setText("");
			jTextField14.setText("");
			jPasswordField4.setText("");
			jTextField20.setText("");
			jTextField20.setEnabled(true);
			jTextField16.setText("0.00");
			jTextField17.setText("0.00");
			jTextField18.setText("0.00");
			jTextField19.setText("0.00");
			// jLabel32.setText("0.00");

			returnedItems.removeAllElements();
			setReturnedTable(null);
			replacedItems.removeAllElements();
			setReplaceTable(null);
			replacedPaymentItems.removeAllElements();
			setPaymentTable(null, "replaced");

		}
	}

	// update amounts for returned items (subtotal, changes... etc)
	public void updateReturnedAmounts() {

		double tradedamount = 0;
		for (int i = 0; i < returnedItems.size(); i++) {
			int quantity = Integer.parseInt(returnedItems.get(i).get(2)
					.toString());
			double price = Double.parseDouble(returnedItems.get(i).get(3)
					.toString());
			tradedamount += (quantity * price);
		}
		// update amount
		double amount = 0;
		double VAT = 0.12; // customizable
		ResultSet rs = Main.getDBManager().executeQuery(
				"SELECT VAT FROM global");
		try {
			if (rs.next()) {
				VAT = Double.parseDouble("0." + rs.getString("VAT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < replacedItems.size(); i++) {
			int quantity = Integer.parseInt(replacedItems.get(i).get(2)
					.toString());
			double price = Double.parseDouble(replacedItems.get(i).get(4)
					.toString());
			amount += (quantity * price);
		}

		if (amount > tradedamount) {
			amount = amount - tradedamount;
			// jLabel32.setText(String.format("%.2f", amount));

			// update SubTotal
			VAT = VAT + 1;
			double subTotal = amount / VAT;
			jTextField16.setText(String.format("%.2f", subTotal));
			jTextField17.setText(String.format("%.2f", (amount - subTotal)));
		} else {
			// jLabel32.setText("0.00");
			jTextField16.setText("0.00");
			jTextField17.setText("0.00");
		}
		// update payments
		double paymentAmount = 0;

		for (int i = 0; i < replacedPaymentItems.size(); i++) {
			paymentAmount += Double.parseDouble(replacedPaymentItems.get(i)
					.get(2).toString());
		}
		jTextField18.setText(String.format("%.2f", paymentAmount));

		// update change
		if (hasPaymentCash("replaced")) {
			double paymentCash = 0;

			for (int i = 0; i < jTable7.getRowCount(); i++) {
				if (jTable7.getValueAt(i, 1).toString().equals("Cash")) {
					paymentCash = Double.parseDouble(jTable7.getValueAt(i, 2)
							.toString());
					break;
				}
			}

			double change = paymentAmount - amount;
			if (change > paymentCash) {
				jTextField19.setText(String.format("%.2f", paymentCash));
			} else {
				jTextField19.setText(String.format("%.2f", change));
			}

		} else {
			double change = 0;
			jTextField19.setText(String.format("%.2f", change));
		}
	}

	// synchronize with fast addition
	public void synchronizeWithFastAddition(Vector<Vector<String>> rowData) {

		if (productItems.size() > 0) {

			for (int i = 0; i < rowData.size(); i++) {

				boolean hasProduct = false;

				for (int j = 0; j < productItems.size(); j++) {

					if (rowData.get(i).get(0).toString().equals(
							productItems.get(j).get(0).toString())) {

						hasProduct = true;

						int fromnewquantity = Integer.parseInt(rowData.get(i)
								.get(2).toString());
						int fromoldquantity = Integer.parseInt(productItems
								.get(j).get(2).toString());
						int newqty = fromoldquantity + fromnewquantity;
						ResultSet rs = Main.getDBManager().executeQuery(
								"SELECT * FROM inventory_lu WHERE PROD_CODE = '"
										+ rowData.get(i).get(0).toString()
										+ "' AND STORE_CODE = '"
										+ Main.getStoreCode() + "'");
						try {
							if (rs.next()) {
								int availableQuantity = Integer.parseInt(rs
										.getString("QUANTITY"));

								if (availableQuantity >= newqty) {
									productItems.get(j).set(2, newqty + "");
								} else {
									JOptionPane
											.showMessageDialog(
													null,
													"The item: "
															+ rowData.get(i)
																	.get(0)
																	.toString()
															+ ", based on the addition of its quantity, exceed its limit from the inventory. \n You may only get "
															+ availableQuantity,
													"Warning",
													JOptionPane.WARNING_MESSAGE);
									productItems.get(j).set(2,
											availableQuantity + "");
								}
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						break;
					}
				}
				if (!hasProduct) {
					productItems.add(rowData.get(i));
				}
			}

		} else {
			for (int i = 0; i < rowData.size(); i++) {
				productItems.add(rowData.get(i));
			}
		}

		Object[][] data = new Object[productItems.size()][7];
		for (int i = 0; i < productItems.size(); i++) {
			for (int j = 0; j < 7; j++) {
				data[i][j] = productItems.get(i).get(j);
			}
		}

		TableModel jTable1Model = new DefaultTableModel(data, new String[] {
				"Product Code", "Product Name", "Quantity", "Current Price",
				"Selling Price", "Deferred", "Disc Code" });
		jTable1 = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.getSelectionModel().addListSelectionListener(
				new SelectionListener(jTable1) {
					public void valueChanged(ListSelectionEvent e) {
						if (e.getSource() == jTable1.getSelectionModel()
								&& jTable1.getRowSelectionAllowed()) {
							// Column selection changed
							int first = e.getFirstIndex();
							int last = e.getLastIndex();

							String prodcode = jTable1.getValueAt(
									jTable1.getSelectedRow(), 0).toString();

							ResultSet rs = Main.getDBManager().executeQuery(
									"SELECT * FROM products_lu "
											+ "WHERE PACKAGE = '1' "
											+ "AND PROD_CODE = \"" + prodcode
											+ "\"");
							try {
								if (rs.next()) {
									jButton17.setEnabled(true);
								} else {
									jButton17.setEnabled(false);
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				});

		updateAmounts();
	}

	public ArrayList<Transaction> getPendingTransactions() {
		return pendingTransactions;
	}

	public void setPendingTransactions(ArrayList<Transaction> pendingTransactions) {
		this.pendingTransactions = pendingTransactions;
	}

	public JTabbedPane getJTabbedPane1() {
		return jTabbedPane1;
	}

	public void setJTabbedPane1(JTabbedPane tabbedPane1) {
		jTabbedPane1 = tabbedPane1;
	}
	
	
}
