package forms.returned;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

@SuppressWarnings("serial")
public class ReturnedPanel extends javax.swing.JPanel {

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new ReturnedPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private JButton jButton27;
	private JButton jButton24;
	private JButton jButton23;
	private JButton jButton20;
	private JScrollPane jScrollPane6;
	private JTable jTable7;
	private JScrollPane jScrollPane5;
	private JTable jTable5;
	private JPanel jPanel11;
	private JTextField jTextField16;
	private JLabel jLabel31;
	private JLabel jLabel32;
	private JLabel jLabel33;
	private JLabel jLabel34;
	private JLabel jLabel35;
	private JLabel jLabel36;
	private JTextField jTextField17;
	private JTextField jTextField18;
	private JTextField jTextField19;
	private JPanel jPanel10;
	private JButton jButton28;
	private JPasswordField jPasswordField4;
	private JButton jButton19;
	private JButton jButton18;
	private JTextField jTextField11;
	private JLabel jLabel24;
	private JLabel jLabel25;
	private JLabel jLabel26;
	private JLabel jLabel27;
	private JTextField jTextField12;
	private JTextField jTextField14;
	private JLabel jLabel37;
	private JTextField jTextField20;
	private JLabel jLabel28;
	private JLabel jLabel29;
	private JButton jButton21;
	private JButton jButton22;
	private JScrollPane jScrollPane7;
	private JLabel jLabel30;
	private JButton jButton25;
	private JButton jButton26;
	
	public ReturnedPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			AnchorLayout jPanel5Layout = new AnchorLayout();
			this.setLayout(jPanel5Layout);
			this.setBorder(new LineBorder(new java.awt.Color(
				0,
				0,
				0), 1, false));
			this
				.setBackground(new java.awt.Color(255, 255, 255));
			{
				jButton27 = new JButton();
				this.add(jButton27, new AnchorConstraint(917, 747, 973, 544, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton27.setText("Process Transaction");
				jButton27.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
						"images/process.png")));
				jButton27.setPreferredSize(new java.awt.Dimension(175, 28));
				jButton27.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						if (jTextField20.getText().length() != 0
//							&& returnedItems.size() != 0
//							&& replacedItems.size() != 0
//							&& jTextField14.getText().length() != 0
//							&& jPasswordField4.getPassword().length != 0) {
//
////							ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM clerk_lu WHERE AES_DECRYPT(password,'babyland') = '"
//							ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM clerk_lu WHERE password = '"
//									+ new String(jPasswordField4.getPassword())
//									+ "' AND clerk_code = '"
//									+ jTextField14.getText()
//									+ "'");
//
//							try {
//								if (rs.next()) {
//									double amount = Double
//										.parseDouble(jLabel32
//											.getText());
//									double payments = Double
//										.parseDouble(jTextField18
//											.getText());
//
//									if (amount <= payments) {
//
//										
//											
//											int n = JOptionPane
//												.showConfirmDialog(
//													MainWindow.this,
//													"You are about to process this transaction. \n"
//														+ "Are you sure you want to continue?",
//													"Confirmation",
//													JOptionPane.YES_NO_OPTION);
//											if (n == 0) {
//												
////												insert returnedItems
//												for (int i = 0; i < returnedItems.size(); i++) {
//													String query = "INSERT INTO returned_items VALUES("+ jTextField20.getText()
//														+ "," //orno
//														+ Main.getStoreCode()
//														+ ","
//														+ //storecode
//														"\""+ returnedItems.get(i).get(0)+ "\","
//														+ //prodcode
//														returnedItems
//															.get(i).get(7)
//														+ ","
//														+ //returncode
//														returnedItems
//															.get(i).get(8)
//														+ ","
//														+ //cost
//														returnedItems
//															.get(i).get(4)
//														+ ","
//														+ //sell price
//														returnedItems
//															.get(i).get(2)
//														+ ")";
//												
//												Main.getDBManager().executeUpdate(query);
//
//												String dataToLog2 = DataUtil
//													.rowToData(
//														"ADD",
//														"returned_items",
//														new String[] {
//																"OR_NO",
//																"STORE_CODE",
//																"PROD_CODE",
//																"RETURN_CODE" },
//														new String[] {
//																jTextField20
//																	.getText(),
//																Main.getStoreCode(),
//																returnedItems
//																	.get(
//																		i)
//																	.get(
//																		0),
//																returnedItems
//																	.get(
//																		i)
//																	.get(
//																		7) });
//												cachewriter
//													.writeToFile(dataToLog2);
//												}
//
//												processReturnedTransactions();
//
//												
//													Vector<String> headerData = new Vector<String>();
//													
//													rs = Main.getDBManager().executeQuery("SELECT * FROM store_lu WHERE STORE_CODE =" + Main.getStoreCode());
//													if(rs.next()){
//														headerData.add(rs.getString(3));
//													}
//													
//													try {
//														BufferedReader br = new BufferedReader(new FileReader("data/.tinno"));
//														headerData.add(br.readLine());
//													} catch (IOException e) {
//														// TODO Auto-generated catch block
//														e.printStackTrace();
//													}
//													
//													String OR_NO = Main.getStoreCode()+"-"+jTextField11.getText();
//													headerData.add(OR_NO);
//													
//													rs = Main.getDBManager().executeQuery("SELECT * FROM clerk_lu WHERE CLERK_CODE = \""+jTextField14.getText()+ "\"");
//													if(rs.next()){
//														String name = rs.getString(4)+" "+rs.getString(3);
//														headerData.add(name);
//													}
//													
//													rs = Main.getDBManager().executeQuery("SELECT DATE(CURRENT_TIMESTAMP), TIME(CURRENT_TIMESTAMP)");
//													if(rs.next()){
//														headerData.add(rs.getString(1));
//														headerData.add(rs.getString(2));
//													}
//													
//													
//													Vector<Vector<String>> itemData = new Vector<Vector<String>>();
//													
//													for(int j = 0; j< replacedItems.size(); j++){
//														Vector<String> row = new Vector<String>();
//														String prodcode = replacedItems.get(j).get(0).toString();
//														String prodname = replacedItems.get(j).get(1).toString();
//														String curPrice = replacedItems.get(j).get(3).toString();
//														String selPrice = replacedItems.get(j).get(4).toString();
//														String qty = replacedItems.get(j).get(2).toString();
//														row.add(prodcode);
//														row.add(prodname);
//														row.add(curPrice);
//														row.add(selPrice);
//														row.add(qty);
//														itemData.add(row);
//													}
//													
//													for(int j = 0; j< returnedItems.size(); j++){
//														Vector<String> row = new Vector<String>();
//														String prodcode = returnedItems.get(j).get(0).toString();
//														String prodname = returnedItems.get(j).get(1).toString();
//														String curPrice = returnedItems.get(j).get(4).toString();
//														String selPrice = returnedItems.get(j).get(3).toString();
//														String qty = "-"+returnedItems.get(j).get(2).toString();
//														row.add(prodcode);
//														row.add(prodname);
//														row.add(curPrice);
//														row.add(selPrice);
//														row.add(qty);
//														itemData.add(row);
//														
//														
//													}
//													
//													Vector<Vector<String>> paymentData = new Vector<Vector<String>>();
//													for(int j = 0; j< replacedPaymentItems.size(); j++){
//														Vector<String> row = new Vector<String>();
//														String paymentName = replacedPaymentItems.get(j).get(1).toString();
//														String payamount = replacedPaymentItems.get(j).get(2).toString();
//														row.add(paymentName);
//														row.add(payamount);
//														paymentData.add(row);
//														
//													}
//													
//													String vatAmount = jTextField17.getText();
//													String changeAmount = jTextField19.getText();
//													
//													ValidateReceipt receiptdialog = new ValidateReceipt(MainWindow.this,headerData, itemData, paymentData, vatAmount, changeAmount, Main.getDBManager(),"reg");
//													receiptdialog.setLocationRelativeTo(null);
//													receiptdialog.setVisible(true);
//												
//
//												updateReturnedFields();
//										}
//									}
//
//									else {
//										JOptionPane
//											.showMessageDialog(
//												null,
//												" Amount due is greater than payment.",
//												"Warning",
//												JOptionPane.WARNING_MESSAGE);
//									}
//								} else {
//									JOptionPane
//										.showMessageDialog(
//											null,
//											"Invalid Cashier log in.",
//											"Warning",
//											JOptionPane.WARNING_MESSAGE);
//								}
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//						} else {
//							JOptionPane
//								.showMessageDialog(
//									null,
//									"Please complete required fields of the form.",
//									"Warning",
//									JOptionPane.WARNING_MESSAGE);
//						}
					}
				});
			}
			{
				jButton24 = new JButton();
				this.add(jButton24, new AnchorConstraint(
					822,
					593,
					865,
					520,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton24.setText("Add");
				jButton24.setPreferredSize(new java.awt.Dimension(
					63,
					21));
				jButton24.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						payments dialog = new payments(
//							MainWindow.this,
//							"add|replaced");
//						dialog.setLocationRelativeTo(null);
//						dialog.setVisible(true);
					}
				});
			}
			{
				jButton23 = new JButton();
				this.add(jButton23, new AnchorConstraint(
					270,
					983,
					313,
					763,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton23.setText("Delete from the Returned List");
				jButton23.setPreferredSize(new java.awt.Dimension(
					189,
					21));
				jButton23.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						returnedItems.remove(jTable6
//							.getSelectedRow());
//						setReturnedTable(null);
//						updateReturnedAmounts();
					}
				});
			}
			{
				jButton20 = new JButton();
				this.add(jButton20, new AnchorConstraint(
					567,
					593,
					610,
					520,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton20.setText("Add");
				jButton20.setPreferredSize(new java.awt.Dimension(
					63,
					21));
				jButton20.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						products prodDialog = new products(MainWindow.this,"replaced",false);
//						prodDialog.setLocationRelativeTo(null);
//						prodDialog.setVisible(true);
					}
				});
			}
			{
				jScrollPane6 = new JScrollPane();
				this.add(jScrollPane6, new AnchorConstraint(
					667,
					983,
					822,
					309,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jScrollPane6
					.setPreferredSize(new java.awt.Dimension(
						581,
						77));
				{
					TableModel jTable7Model = new DefaultTableModel(
						new String[][] {},
						new String[] { "Payment Code",
								"Payment Type", "Amount",
								"Credit Card Type",
								"Credit Card No", "Bank Check No",
								"Gift Certificate Number" });
					jTable7 = new JTable();
					jScrollPane6.setViewportView(jTable7);
					jTable7.setModel(jTable7Model);
				}
			}
			{
				jScrollPane5 = new JScrollPane();
				this.add(jScrollPane5, new AnchorConstraint(
					355,
					983,
					567,
					309,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jScrollPane5
					.setPreferredSize(new java.awt.Dimension(
						581,
						105));
				{
					TableModel jTable5Model = new DefaultTableModel(
						new String[][] {},
						new String[] { "Product Code",
								"Product Name", "Quantity",
								"Current Price", "Selling Price",
								"Deferred", "Disc Code" });
					jTable5 = new JTable();
					jScrollPane5.setViewportView(jTable5);
					jTable5.setModel(jTable5Model);
				}
			}
			{
				jPanel11 = new JPanel();
				AnchorLayout jPanel11Layout = new AnchorLayout();
				jPanel11.setLayout(jPanel11Layout);
				this.add(jPanel11, new AnchorConstraint(
					525,
					276,
					950,
					8,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jPanel11.setPreferredSize(new java.awt.Dimension(
					231,
					210));
				jPanel11.setBorder(new LineBorder(
					new java.awt.Color(0, 0, 0),
					1,
					false));
				jPanel11.setBackground(new java.awt.Color(
					255,
					128,
					255));
				{
					jTextField16 = new JTextField();
					jPanel11.add(
						jTextField16,
						new AnchorConstraint(
							302,
							941,
							402,
							547,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jTextField16.setText("0.00");
					jTextField16
						.setPreferredSize(new java.awt.Dimension(
							91,
							21));
					jTextField16.setEditable(false);
					jTextField16
						.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					jLabel31 = new JLabel();
					jPanel11.add(jLabel31, new AnchorConstraint(
						35,
						820,
						135,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel31.setText("Amount Due");
					jLabel31.setFont(new java.awt.Font(
						"Tahoma",
						1,
						22));
					jLabel31
						.setPreferredSize(new java.awt.Dimension(
							182,
							21));
				}
				{
					jLabel32 = new JLabel();
					jPanel11.add(jLabel32, new AnchorConstraint(
						135,
						820,
						235,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel32.setText("0.00");
					jLabel32.setFont(new java.awt.Font(
						"Tahoma",
						1,
						22));
					jLabel32
						.setPreferredSize(new java.awt.Dimension(
							182,
							21));
				}
				{
					jLabel33 = new JLabel();
					jPanel11.add(jLabel33, new AnchorConstraint(
						302,
						487,
						402,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel33.setText("Sub Total");
					jLabel33.setFont(new java.awt.Font(
						"Tahoma",
						1,
						14));
					jLabel33
						.setPreferredSize(new java.awt.Dimension(
							105,
							21));
				}
				{
					jLabel34 = new JLabel();
					jPanel11.add(jLabel34, new AnchorConstraint(
						435,
						487,
						535,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel34.setText("12% VAT");
					ResultSet rs = Main.getDBManager().executeQuery("SELECT VAT FROM global");
					if(rs.next()){
						jLabel34.setText(rs.getString("VAT")+"% VAT");
					}
					jLabel34.setFont(new java.awt.Font(
						"Tahoma",
						1,
						14));
					jLabel34
						.setPreferredSize(new java.awt.Dimension(
							105,
							21));
				}
				{
					jLabel35 = new JLabel();
					jPanel11.add(jLabel35, new AnchorConstraint(
						602,
						487,
						702,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel35.setText("Total Payment");
					jLabel35.setFont(new java.awt.Font(
						"Tahoma",
						1,
						14));
					jLabel35
						.setPreferredSize(new java.awt.Dimension(
							105,
							21));
				}
				{
					jLabel36 = new JLabel();
					jPanel11.add(jLabel36, new AnchorConstraint(
						735,
						487,
						835,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel36.setText("Change");
					jLabel36.setFont(new java.awt.Font(
						"Tahoma",
						1,
						14));
					jLabel36
						.setPreferredSize(new java.awt.Dimension(
							105,
							21));
				}
				{
					jTextField17 = new JTextField();
					jPanel11.add(
						jTextField17,
						new AnchorConstraint(
							435,
							941,
							535,
							547,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jTextField17.setText("0.00");
					jTextField17
						.setPreferredSize(new java.awt.Dimension(
							91,
							21));
					jTextField17.setEditable(false);
					jTextField17
						.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					jTextField18 = new JTextField();
					jPanel11.add(
						jTextField18,
						new AnchorConstraint(
							602,
							941,
							702,
							547,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jTextField18.setText("0.00");
					jTextField18
						.setPreferredSize(new java.awt.Dimension(
							91,
							21));
					jTextField18.setEditable(false);
					jTextField18
						.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					jTextField19 = new JTextField();
					jPanel11.add(
						jTextField19,
						new AnchorConstraint(
							735,
							941,
							835,
							547,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jTextField19.setText("0.00");
					jTextField19
						.setPreferredSize(new java.awt.Dimension(
							91,
							21));
					jTextField19.setEditable(false);
					jTextField19
						.setHorizontalAlignment(SwingConstants.RIGHT);
				}
			}
			{
				jPanel10 = new JPanel();
				AnchorLayout jPanel10Layout = new AnchorLayout();
				jPanel10.setLayout(jPanel10Layout);
				this.add(jPanel10, new AnchorConstraint(
					29,
					276,
					497,
					8,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jPanel10.setPreferredSize(new java.awt.Dimension(
					231,
					231));
				jPanel10.setBackground(new java.awt.Color(
					164,
					222,
					251));
				jPanel10.setBorder(new LineBorder(
					new java.awt.Color(0, 0, 0),
					1,
					false));
				{
					jButton28 = new JButton();
					jPanel10.add(jButton28, new AnchorConstraint(
						850,
						941,
						941,
						62,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jButton28.setText("Clear");
					jButton28
						.setPreferredSize(new java.awt.Dimension(
							203,
							21));
					jButton28
						.addActionListener(new ActionListener() {
							public void actionPerformed(
								ActionEvent evt) {
//								try {
//									updateReturnedFields();
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
							}
						});
				}
				{
					jPasswordField4 = new JPasswordField();
					jPanel10.add(
						jPasswordField4,
						new AnchorConstraint(
							456,
							941,
							547,
							396,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jPasswordField4
						.setPreferredSize(new java.awt.Dimension(
							126,
							21));
				}
				{
					jButton19 = new JButton();
					jPanel10.add(jButton19, new AnchorConstraint(
						699,
						941,
						820,
						62,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jButton19.setText("View Items of Transaction");
					jButton19
						.setPreferredSize(new java.awt.Dimension(
							203,
							28));
					jButton19
						.addActionListener(new ActionListener() {
							public void actionPerformed(
								ActionEvent evt) {
//								if (jTextField20.getText().length() != 0) {
//									ResultSet rs = Main.getDBManager()
//										.executeQuery("SELECT * FROM invoice WHERE OR_NO = '"
//											+ jTextField20
//												.getText()
//											+ "' AND STORE_CODE = '"
//											+ Main.getStoreCode()
//											+ "'");
//									try {
//									
//										if (rs.next()) {
//											jTextField20
//												.setEnabled(false);
//											TransactionItems dialog = new TransactionItems(
//												MainWindow.this,
//												Main.getDBManager(),
//												jTextField20
//													.getText(),
//												Main.getStoreCode(),
//												returnedItems);
//											dialog
//												.setLocationRelativeTo(null);
//											dialog.setVisible(true);
//										} else {
//											JOptionPane
//												.showMessageDialog(
//													null,
//													"There is no such transaction of OR number indicated.\n "
//														+ "Please check the OR number and enter it again.",
//													"Warning",
//													JOptionPane.WARNING_MESSAGE);
//										}
//									} catch (SQLException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//								} else {
//									JOptionPane
//										.showMessageDialog(
//											null,
//											"Please enter the OR number of the transaction to be processed.",
//											"Warning",
//											JOptionPane.WARNING_MESSAGE);
//								}
							}
						});
				}
				{
					jButton18 = new JButton();
					jPanel10.add(jButton18, new AnchorConstraint(
						335,
						941,
						426,
						790,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jButton18.setText("...");
					jButton18
						.setPreferredSize(new java.awt.Dimension(
							35,
							21));
					jButton18
						.addActionListener(new ActionListener() {
							public void actionPerformed(
								ActionEvent evt) {
//								Clerk dialog = new Clerk(
//									MainWindow.this,"returned");
//								dialog.setLocationRelativeTo(null);
//								dialog.setVisible(true);

							}
						});
				}
				{
					jTextField11 = new JTextField();
					jPanel10.add(
						jTextField11,
						new AnchorConstraint(
							32,
							941,
							123,
							396,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jTextField11
						.setPreferredSize(new java.awt.Dimension(
							126,
							21));
//					jTextField11.setText(currOrNum);
					jTextField11.setEditable(false);

				}
				{
					jLabel24 = new JLabel();
					jPanel10.add(jLabel24, new AnchorConstraint(
						62,
						396,
						123,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel24.setText("OR Number");
					jLabel24.setFont(new java.awt.Font(
						"Tahoma",
						1,
						12));
					jLabel24
						.setPreferredSize(new java.awt.Dimension(
							84,
							14));
				}
				{
					jLabel25 = new JLabel();
					jPanel10.add(jLabel25, new AnchorConstraint(
						183,
						456,
						244,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel25.setText("Invoice No");
					jLabel25.setFont(new java.awt.Font(
						"Tahoma",
						1,
						12));
					jLabel25
						.setPreferredSize(new java.awt.Dimension(
							98,
							14));
				}
				{
					jLabel26 = new JLabel();
					jPanel10.add(jLabel26, new AnchorConstraint(
						365,
						396,
						426,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel26.setText("Cashier ID");
					jLabel26.setFont(new java.awt.Font(
						"Tahoma",
						1,
						12));
					jLabel26
						.setPreferredSize(new java.awt.Dimension(
							84,
							14));
				}
				{
					jLabel27 = new JLabel();
					jPanel10.add(jLabel27, new AnchorConstraint(
						456,
						305,
						547,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel27.setText("Password");
					jLabel27.setFont(new java.awt.Font(
						"Tahoma",
						1,
						12));
					jLabel27
						.setPreferredSize(new java.awt.Dimension(
							63,
							21));
				}
				{
					jTextField12 = new JTextField();
					jPanel10.add(
						jTextField12,
						new AnchorConstraint(
							153,
							941,
							244,
							396,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jTextField12
						.setPreferredSize(new java.awt.Dimension(
							126,
							21));
				}
				{
					jTextField14 = new JTextField();
					jPanel10.add(
						jTextField14,
						new AnchorConstraint(
							335,
							790,
							426,
							396,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jTextField14
						.setPreferredSize(new java.awt.Dimension(
							91,
							21));
				}
				{
					jLabel37 = new JLabel();
					jPanel10.add(jLabel37, new AnchorConstraint(
						610,
						608,
						671,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel37.setText("Returned OR");
					jLabel37.setFont(new java.awt.Font(
						"Tahoma",
						1,
						12));
					jLabel37
						.setPreferredSize(new java.awt.Dimension(
							133,
							14));
				}
				{
					jTextField20 = new JTextField();
					jPanel10.add(
						jTextField20,
						new AnchorConstraint(
							580,
							941,
							671,
							396,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jTextField20
						.setPreferredSize(new java.awt.Dimension(
							126,
							21));
				}
			}
			{
				jLabel28 = new JLabel();
				this.add(jLabel28, new AnchorConstraint(
					298,
					544,
					355,
					309,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jLabel28.setText("Items to be Replaced");
				jLabel28.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
						"images/items.png")));
				jLabel28
					.setFont(new java.awt.Font("Tahoma", 1, 14));
				jLabel28.setPreferredSize(new java.awt.Dimension(
					203,
					28));
			}
			{
				jLabel29 = new JLabel();
				this.add(jLabel29, new AnchorConstraint(
					610,
					560,
					667,
					309,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jLabel29.setText("Additional Payments");
				jLabel29.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
						"images/payments.png")));
				jLabel29
					.setFont(new java.awt.Font("Tahoma", 1, 14));
				jLabel29.setPreferredSize(new java.awt.Dimension(
					217,
					28));
			}
			{
				jButton21 = new JButton();
				this.add(jButton21, new AnchorConstraint(
					567,
					682,
					610,
					609,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton21.setText("Edit");
				jButton21.setPreferredSize(new java.awt.Dimension(
					63,
					21));
				jButton21.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						String[] val = new String[5];
//						val[0] = (String) jTable5.getValueAt(
//							jTable5.getSelectedRow(),
//							0);
//						val[1] = (String) jTable5.getValueAt(
//							jTable5.getSelectedRow(),
//							1);
//						val[2] = (String) jTable5.getValueAt(
//							jTable5.getSelectedRow(),
//							2);
//						val[3] = (String) jTable5.getValueAt(
//							jTable5.getSelectedRow(),
//							6);
//						val[4] = (String) jTable5.getValueAt(
//							jTable5.getSelectedRow(),
//							5);
//						products dialog = new products(
//							MainWindow.this,
//							"replaced",
//							val,
//							jTable5.getSelectedRow(),
//							false);
//						dialog.setLocationRelativeTo(null);
//						dialog.setVisible(true);
					}
				});
			}
			{
				jButton22 = new JButton();
				this.add(jButton22, new AnchorConstraint(
					567,
					772,
					610,
					698,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton22.setText("Delete");
				jButton22.setPreferredSize(new java.awt.Dimension(
					63,
					21));
				jButton22.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						replacedItems.remove(jTable5
//							.getSelectedRow());
//						setReplaceTable(null);
//						updateReturnedAmounts();
					}
				});
			}
			{
				jScrollPane7 = new JScrollPane();
				this.add(jScrollPane7, new AnchorConstraint(
					71,
					983,
					270,
					309,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jScrollPane7
					.setPreferredSize(new java.awt.Dimension(
						581,
						98));
				{
					TableModel jTable6Model = new DefaultTableModel(
						new String[][] {},
						new String[] { "Product Code",
								"Product Name", "Quantity",
								"Selling Price", "Current Price",
								"Deferred", "Disc Code",
								"Reason Code" });
//					jTable6 = new JTable();
//					jScrollPane7.setViewportView(jTable6);
//					jTable6.setModel(jTable6Model);
				}
			}
			{
				jLabel30 = new JLabel();
				this.add(jLabel30, new AnchorConstraint(
					15,
					544,
					71,
					309,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jLabel30.setText("Items to be Returned");
				jLabel30.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
						"images/items.png")));
				jLabel30
					.setFont(new java.awt.Font("Tahoma", 1, 14));
				jLabel30.setPreferredSize(new java.awt.Dimension(
					203,
					28));
			}
			{
				jButton25 = new JButton();
				this.add(jButton25, new AnchorConstraint(
					822,
					682,
					865,
					609,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton25.setText("Edit");
				jButton25.setPreferredSize(new java.awt.Dimension(
					63,
					21));
				jButton25.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						String[] values = new String[7];
//						for (int i = 0; i < 7; i++) {
//							values[i] = (String) jTable7
//								.getValueAt(jTable7
//									.getSelectedRow(), i);
//						}
//						payments dialog = new payments(
//							MainWindow.this,
//							values,
//							jTable7.getSelectedRow(),
//							"edit|replaced");
//						dialog.setLocationRelativeTo(null);
//						dialog.setVisible(true);
					}
				});
			}
			{
				jButton26 = new JButton();
				this.add(jButton26, new AnchorConstraint(
					822,
					772,
					865,
					698,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton26.setText("Delete");
				jButton26.setPreferredSize(new java.awt.Dimension(
					63,
					21));
				jButton26.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						replacedPaymentItems.remove(jTable7
//							.getSelectedRow());
//						setPaymentTable(null, "replaced");
//						updateReturnedAmounts();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
