package forms.partial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import print.ValidateReceipt;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import connection.DataUtil;
import connection.LogHandler;
import forms.MainWindow;
import forms.Payment;

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
public class PartialDialog extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JScrollPane jScrollPane1;
	private JLabel jLabel4;
	private JScrollPane jScrollPane2;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
	private JButton jButton1;
	private JLabel jLabel15;
	private JLabel jLabel16;
	private JTextField jTextField6;
	private JTable jTable2;
	private JTextField jTextField7;
	private JLabel jLabel5;
	private JTable jTable1;
	private JButton jButton5;
	private JTextField jTextField5;
	private JTextField jTextField4;
	private JTextField jTextField3;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JLabel jLabel11;
	private JLabel jLabel10;
	private JLabel jLabel14;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private MainWindow form;
	private String orNum;
	private int paymentSize;
	private Vector<Vector<String>> paymentItems;
	private LogHandler cachewriter;
	private String clerkNo;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		Partial inst = new Partial(frame);
//		inst.setVisible(true);
	}
	
	public PartialDialog(MainWindow frame,String orNum, String dtime, String clerkNo) {
		super(frame);
		this.form = frame;
		this.orNum = orNum;
		this.clerkNo = clerkNo;
		initGUI();
		this.paymentSize = jTable2.getRowCount();
		jTextField2.setText(orNum);
		String[] datetime = dtime.split(" ");
		jTextField1.setText(datetime[0]);
		jTextField7.setText(datetime[1]);
		cachewriter = new LogHandler();
		updateAmounts();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setTitle("Partial Transaction");
			this.setModal(true);
			{
				jButton5 = new JButton();
				getContentPane().add(jButton5, new AnchorConstraint(897, 825, 948, 684, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton5.setText("Cancel");
				jButton5.setPreferredSize(new java.awt.Dimension(112, 28));
				jButton5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						PartialDialog.this.dispose();
					}
				});
			}
			{
				jButton4 = new JButton();
				getContentPane().add(jButton4, new AnchorConstraint(897, 649, 948, 491, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton4.setText("Process");
				jButton4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				jButton4.setPreferredSize(new java.awt.Dimension(126, 28));
				jButton4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						double amountdue = Double.parseDouble(jLabel4.getText());
						double payments = Double.parseDouble(jTextField5.getText());
						if(amountdue <= payments){
							int n = JOptionPane.showConfirmDialog(
									null,
									"You are about to process this transaction. \n" +
									"Are you sure you want to continue?",
									"Confirmation", JOptionPane.YES_NO_OPTION);
							if(n == 0){
								for(int i = paymentSize; i< paymentItems.size(); i++){
									double paymentCash = 0;
									double amount = 0;
									if(jTable2.getValueAt(i, 1).toString().equals("Cash")){
										paymentCash = Double.parseDouble(jTable2.getValueAt(i, 2).toString());
										amount = paymentCash - Double.parseDouble(jTextField6.getText());
									}
									else{
										amount = Double.parseDouble(jTable2.getValueAt(i, 2).toString());
									}
									
									String query = "INSERT INTO payment_item " +
											"(OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO,STORE_CODE) VALUES " +
											"("+orNum+","+paymentItems.get(i).get(0).toString()+","+ 
											amount+",'"+paymentItems.get(i).get(3).toString()+"','"+
											paymentItems.get(i).get(4).toString()+"','"+paymentItems.get(i).get(6).toString()+"','"+
											paymentItems.get(i).get(5).toString()+"',"+Main.getStoreCode()+")";
									
									Main.getDBManager().executeUpdate(query);
									String dataToLog2 = DataUtil.rowToData("ADD","payment_item",
											new String[]{"OR_NO","PT_CODE","CARD_NO","GC_NO","CHECK_NO","STORE_CODE"}, 
											new String[]{orNum,paymentItems.get(i).get(0).toString(),
														 paymentItems.get(i).get(4).toString(),
														 paymentItems.get(i).get(6).toString(),
														 paymentItems.get(i).get(5).toString(),
														 Main.getStoreCode()});
									cachewriter.writeToFile(dataToLog2);
									
								}
								Main.getDBManager().executeUpdate("UPDATE invoice SET PARTIAL = 0 WHERE OR_NO = "+orNum+" AND STORE_CODE = "+Main.getStoreCode());
								
								String[] keyFields = new String[2];
								keyFields[0] = "OR_NO";
								keyFields[1] = "STORE_CODE";
								
								String[] keys = new String[2];
								keys[0] = orNum;
								keys[1] = Main.getStoreCode();
								
								String dataToLog = DataUtil.rowToData("EDIT","invoice",keyFields,keys);
								cachewriter.writeToFile(dataToLog);
								
								 /*MAKE RECEIPT*/
									
									Vector<String> headerData = new Vector<String>();
									
									ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM store_lu WHERE STORE_CODE =" + Main.getStoreCode());
									try {
										if(rs.next()){
											headerData.add(rs.getString(3));
										}
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									try {
										BufferedReader br = new BufferedReader(new FileReader("data/.tinno"));
										headerData.add(br.readLine());
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									String OR_NO = Main.getStoreCode()+"-"+jTextField2.getText();
									headerData.add(OR_NO);
									
									rs = Main.getDBManager().executeQuery("SELECT * FROM clerk_lu WHERE CLERK_CODE = \""+clerkNo+ "\"");
									try {
										if(rs.next()){
											String name = rs.getString(4)+" "+rs.getString(3);
											headerData.add(name);
										}
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									rs = Main.getDBManager().executeQuery("SELECT DATE(CURRENT_TIMESTAMP), TIME(CURRENT_TIMESTAMP)");
									try {
										if(rs.next()){
											headerData.add("DATE: "+rs.getString(1));
											headerData.add("TIME: "+rs.getString(2));
										}
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									Vector<Vector<String>> itemData = new Vector<Vector<String>>();
									
									rs = Main.getDBManager().executeQuery("SELECT* FROM invoice_item WHERE OR_NO = '"+orNum+"' AND STORE_CODE = '"+Main.getStoreCode()+"'");
									try {
										while(rs.next()){
											Vector<String> row = new Vector<String>();
											row.add(rs.getString("PROD_CODE"));
											ResultSet rs2 = Main.getDBManager().executeQuery("SELECT * FROM products_lu WHERE PROD_CODE = \""+rs.getString("PROD_CODE")+"\"");
											if(rs2.next()){
												row.add(rs2.getString("NAME"));
												row.add(rs2.getString("SELL_PRICE")); //curprice
											}
											row.add(rs.getString("SELL_PRICE")); //sellprice
											row.add(rs.getString("QUANTITY"));
											itemData.add(row);
										}
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									Vector<Vector<String>> paymentData = new Vector<Vector<String>>();
									for(int i = 0; i< paymentItems.size(); i++){
										Vector<String> row = new Vector<String>();
										String paymentName = paymentItems.get(i).get(1).toString();
										String amount = paymentItems.get(i).get(2).toString();
										row.add(paymentName);
										row.add(amount);
										paymentData.add(row);
										
									}
									
									String vatAmount = jTextField4.getText();
									String changeAmount = jTextField6.getText();
									
									ValidateReceipt receiptdialog = new ValidateReceipt(form,headerData, itemData, paymentData, vatAmount, changeAmount, Main.getDBManager(),"reg");
									receiptdialog.setLocationRelativeTo(null);
									receiptdialog.setVisible(true);
								
								PartialDialog.this.dispose();
								form.setPartialList();
							}
						}
						else{
							JOptionPane.showMessageDialog(null, 
									"Amount is greater than payment.", 
									"Warning",JOptionPane.WARNING_MESSAGE);
						}
						
					}
				});
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(782, 604, 833, 534, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Add");
				jButton1.setPreferredSize(new java.awt.Dimension(56, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						Payment dialog = new Payment(PartialDialog.this);
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
				});
			}
			{
				jPanel2 = new JPanel();
				AnchorLayout jPanel2Layout = new AnchorLayout();
				jPanel2.setLayout(jPanel2Layout);
				getContentPane().add(jPanel2, new AnchorConstraint(468, 290, 948, 26, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel2.setPreferredSize(new java.awt.Dimension(210, 259));
				jPanel2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel2.setBackground(new java.awt.Color(255,128,255));
				{
					jTextField3 = new JTextField();
					jPanel2.add(jTextField3, new AnchorConstraint(327, 975, 408, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField3.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField3.setEditable(false);
					jTextField3.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField3.setText("0.00");
				}
				{
					jLabel14 = new JLabel();
					jPanel2.add(jLabel14, new AnchorConstraint(29, 818, 137, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel14.setText("Amount Due");
					jLabel14.setFont(new java.awt.Font("Tahoma",1,22));
					jLabel14.setPreferredSize(new java.awt.Dimension(175, 28));
				}
				{
					jLabel10 = new JLabel();
					jPanel2.add(jLabel10, new AnchorConstraint(300, 504, 408, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel10.setText("Sub Total");
					jLabel10.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel10.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					jLabel11 = new JLabel();
					jPanel2.add(jLabel11, new AnchorConstraint(436, 504, 544, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel11.setText("12% VAT");
					ResultSet rs = Main.getDBManager().executeQuery("SELECT VAT FROM global");
					if(rs.next()){
						jLabel11.setText(rs.getString("VAT")+"% VAT");
					}
					jLabel11.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel11.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					jLabel12 = new JLabel();
					jPanel2.add(jLabel12, new AnchorConstraint(598, 504, 707, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel12.setText("Total Payment");
					jLabel12.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel12.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					jLabel13 = new JLabel();
					jPanel2.add(jLabel13, new AnchorConstraint(734, 504, 843, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel13.setText("Change");
					jLabel13.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel13.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					jLabel4 = new JLabel();
					jPanel2.add(jLabel4, new AnchorConstraint(137, 818, 246, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel4.setText("0.00");
					jLabel4.setFont(new java.awt.Font("Tahoma",1,22));
					jLabel4.setPreferredSize(new java.awt.Dimension(175, 28));
				}
				{
					jTextField4 = new JTextField();
					jPanel2.add(jTextField4, new AnchorConstraint(436, 975, 517, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField4.setEditable(false);
					jTextField4.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField4.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField4.setText("0.00");
				}
				{
					jTextField5 = new JTextField();
					jPanel2.add(jTextField5, new AnchorConstraint(598, 975, 680, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField5.setEditable(false);
					jTextField5.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField5.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField5.setText("0.00");
				}
				{
					jTextField6 = new JTextField();
					jPanel2.add(jTextField6, new AnchorConstraint(734, 975, 815, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField6.setEditable(false);
					jTextField6.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField6.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField6.setText("0.00");
				}
			}
			{
				jPanel1 = new JPanel();
				AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, new AnchorConstraint(117, 290, 442, 26, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel1.setPreferredSize(new java.awt.Dimension(210, 175));
				jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel1.setBackground(new java.awt.Color(164,222,251));
				{
					jTextField2 = new JTextField();
					jPanel1.add(jTextField2, new AnchorConstraint(202, 943, 322, 410, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField2.setEditable(false);
					jTextField2.setPreferredSize(new java.awt.Dimension(119, 21));
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3, new AnchorConstraint(482, 400, 602, 35, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("Date");
					jLabel3.setPreferredSize(new java.awt.Dimension(77, 21));
					jLabel3.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2, new AnchorConstraint(202, 598, 322, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("OR Number");
					jLabel2.setPreferredSize(new java.awt.Dimension(126, 21));
					jLabel2.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					jTextField1 = new JTextField();
					jPanel1.add(jTextField1, new AnchorConstraint(482, 943, 602, 410, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField1.setPreferredSize(new java.awt.Dimension(119, 21));
					jTextField1.setEditable(false);
				}
				{
					jLabel5 = new JLabel();
					jPanel1.add(jLabel5, new AnchorConstraint(642, 400, 762, 35, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel5.setText("Time");
					jLabel5.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel5.setPreferredSize(new java.awt.Dimension(77, 21));
				}
				{
					jTextField7 = new JTextField();
					jPanel1.add(jTextField7, new AnchorConstraint(642, 940, 762, 409, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField7.setText(orNum);
					jTextField7.setEditable(false);
					jTextField7.setPreferredSize(new java.awt.Dimension(112, 21));
				}
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, new AnchorConstraint(156, 974, 455, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane1.setPreferredSize(new java.awt.Dimension(518, 161));
				{
					ResultSet rs = Main.getDBManager().executeQuery("SELECT invoice_item.PROD_CODE, products_lu.NAME, QUANTITY, invoice_item.SELL_PRICE, DISC_RATE,deferred,invoice_item.DISC_CODE " +
													"FROM invoice_item, products_lu, discount_lu " +
													"WHERE invoice_item.OR_NO = '"+orNum+"' " +
													"AND invoice_item.PROD_CODE = products_lu.PROD_CODE " +
													"AND invoice_item.DISC_CODE = discount_lu.DISC_NO");
					
					Vector<Vector<String>> data = new Vector<Vector<String>>();
					
					while(rs.next()){
						Vector<String> rowData = new Vector<String>();
						rowData.add(rs.getString(1));
						rowData.add(rs.getString(2));
						rowData.add(rs.getString(3));
						rowData.add(rs.getString(4));
						
						ResultSet curr = Main.getDBManager().executeQuery("SELECT SELL_PRICE FROM products_lu WHERE PROD_CODE = \""+rs.getString(1)+"\"" );
						if(curr.next()){
							rowData.add(curr.getString(1));
						}
						
						if(rs.getString(6).equals("1")){
							rowData.add("Yes");
						}
						else{
							rowData.add("No");
						}
						rowData.add(rs.getString(7));
						data.add(rowData);
					}
					//fill the String[][] with values
					String[][] dataSource = new String[data.size()][7];
					for(int i = 0; i<data.size(); i++){
						for(int j = 0; j< data.get(i).size(); j++){
							dataSource[i][j] = data.get(i).get(j);
						}
					}
					TableModel jTable1Model = new DefaultTableModel(
						dataSource,
						new String[] { "Product Code", "Product Name","Quantity","Price Sold","Current Price","Deferred","Disc Code" });
					jTable1 = new JTable(){
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					};
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(jTable1Model);
				}
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(18, 353, 89, 19, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Process Partial Transaction");
				jLabel1.setPreferredSize(new java.awt.Dimension(245, 28));
				jLabel1.setFont(new java.awt.Font("Tahoma",1,18));
			}
			{
				jLabel16 = new JLabel();
				getContentPane().add(jLabel16, new AnchorConstraint(104, 439, 156, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel16.setText("Items");
				jLabel16.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/items.png")));
				jLabel16.setFont(new java.awt.Font("Tahoma",1,14));
				jLabel16.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jLabel15 = new JLabel();
				getContentPane().add(jLabel15, new AnchorConstraint(468, 465, 520, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel15.setText("Payments");
				jLabel15.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/payments.png")));
				jLabel15.setFont(new java.awt.Font("Tahoma",1,14));
				jLabel15.setPreferredSize(new java.awt.Dimension(112, 28));
			}
			{
				jScrollPane2 = new JScrollPane();
				getContentPane().add(jScrollPane2, new AnchorConstraint(520, 974, 767, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane2.setPreferredSize(new java.awt.Dimension(518, 133));
				{
					ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM pay_type_lu, payment_item " +
													"WHERE OR_NO = '"+orNum+"' " +
													"AND pay_type_lu.PT_CODE = payment_item.PT_CODE AND STORE_CODE="+Main.getStoreCode());
					
					paymentItems = new Vector<Vector<String>>();
					while(rs.next()){
						Vector<String> rowData = new Vector<String>();
						rowData.add(rs.getString(1));
						rowData.add(rs.getString(2));
						rowData.add(rs.getString(6));
						rowData.add(rs.getString(7));
						rowData.add(rs.getString(8));
						rowData.add(rs.getString(11));
						rowData.add(rs.getString(10));
						paymentItems.add(rowData);
					}
					//fill the String[][] with values
					String[][] dataSource = new String[paymentItems.size()][7];
					for(int i = 0; i<paymentItems.size(); i++){
						for(int j = 0; j< paymentItems.get(i).size(); j++){
							dataSource[i][j] = paymentItems.get(i).get(j);
						}
					}
					
					TableModel jTable2Model = new DefaultTableModel(
						dataSource,
						new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate No" });
					jTable2 = new JTable();
					jScrollPane2.setViewportView(jTable2);
					jTable2.setModel(jTable2Model);
				}
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(780, 693, 832, 623, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Edit");
				jButton2.setPreferredSize(new java.awt.Dimension(56, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try{
							jTable2.getValueAt(jTable2.getSelectedRow(), 0); //return exception
							if(jTable2.getSelectedRow() < paymentSize){
								JOptionPane.showMessageDialog(null, 
										"This is the original payment. You cannot edit this payment.", 
										"Warning",JOptionPane.WARNING_MESSAGE);
							}
							else{
								String[] values = new String[7];
								for(int i =0; i<7; i++){
									values[i] = (String) jTable2.getValueAt(jTable2.getSelectedRow(), i);
								}
								Payment dialog = new Payment(PartialDialog.this,values,jTable2.getSelectedRow());
								dialog.setLocationRelativeTo(null);
								dialog.setVisible(true);
							}
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, 
									"Please select item from the list.", 
									"Warning",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3, new AnchorConstraint(780, 807, 832, 711, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton3.setText("Delete");
				jButton3.setPreferredSize(new java.awt.Dimension(77, 28));
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try{
							jTable2.getValueAt(jTable2.getSelectedRow(), 0); //return exception
							if(jTable2.getSelectedRow() < paymentSize){
								JOptionPane.showMessageDialog(null, 
										"This is the original payment. You cannot delete this payment.", 
										"Warning",JOptionPane.WARNING_MESSAGE);
							}
							else{
								paymentItems.remove(jTable2.getSelectedRow());
								setPaymentTable(null);
								updateAmounts();
							}
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, 
									"Please select item from the list.", 
									"Warning",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}
			this.setSize(806, 573);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateAmounts(){
		//update amount
		double amount = 0;
		ResultSet rs = Main.getDBManager().executeQuery("SELECT VAT FROM global");
		double VAT = 0; //customizable
		try {
			if(rs.next()){
				VAT = Double.parseDouble("0."+rs.getString("VAT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i< jTable1.getRowCount(); i++){
			int quantity =  Integer.parseInt(jTable1.getValueAt(i, 2).toString());
			double price = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
			amount += (quantity*price);
		}
		jLabel4.setText(String.format("%.2f", amount));
		
		//update SubTotal
		VAT = VAT+1;
		double subTotal = amount/VAT;
		jTextField3.setText(String.format("%.2f", subTotal));
		jTextField4.setText(String.format("%.2f", (amount - subTotal)));
		
		//update payments
		double paymentAmount = 0;
		
		for(int i =0; i<jTable2.getRowCount(); i++){
			paymentAmount += Double.parseDouble(jTable2.getValueAt(i, 2).toString());
		}
		jTextField5.setText(String.format("%.2f", paymentAmount));
		
		//update change
		if(hasPaymentCash())
		{
			double paymentCash = 0;
			
			for(int i =paymentSize; i<jTable2.getRowCount(); i++){
				if(jTable2.getValueAt(i, 1).toString().equals("Cash")){
					paymentCash = Double.parseDouble(jTable2.getValueAt(i, 2).toString());
					break;
				}
			}
			
			double change = paymentAmount - amount;
			if(change > paymentCash){
				jTextField6.setText(String.format("%.2f", paymentCash));
			}
			else{
				jTextField6.setText(String.format("%.2f", change));
			}
			
		}
		else{
			
			if(paymentAmount < amount){
				double change = paymentAmount - amount;
				jTextField6.setText(String.format("%.2f", change));
			}
			else{
				double change = 0;
				jTextField6.setText(String.format("%.2f", change));
			}
			
		}
		
	}
	
	//functions for payment item table
	public void setPaymentTable(Vector<String> args){
		if(args != null) {
			paymentItems.add(args);
		}
		
		Object[][] data = new Object[paymentItems.size()][7];
		for(int i = 0; i< paymentItems.size(); i++)
		{
			for(int j=0; j<7; j++){
				data[i][j] = paymentItems.get(i).get(j);
			}
		}
		
		TableModel jTable1Model = new DefaultTableModel(
				data,
				new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number"  });
			jTable2 = new JTable(){
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};
			jScrollPane2.setViewportView(jTable2);
			jTable2.setModel(jTable1Model);
	}
	public void setThisPaymentTable(Vector<String> args, int index){
		//for editing 
		paymentItems.set(index, args);
		setPaymentTable(null);
	}
	
	public boolean hasPaymentCash(){
		
		for(int i =paymentSize; i<paymentItems.size(); i++){
			if(paymentItems.get(i).get(1).equals("Cash")){
				return true;
			}
		}
		return false;
	}
	public boolean hasSameCreditCard(String no){
		for(int i =paymentSize; i<paymentItems.size(); i++){
			if(paymentItems.get(i).get(4).equals(no)){
				return true;
			}
		}
		return false;
	}
	public boolean hasSameBankCheck(String no){
		for(int i =0; i<paymentItems.size(); i++){
			if(paymentItems.get(i).get(5).equals(no)){
				return true;
			}
		}
		return false;
	}
	public boolean hasSameGiftCertificate(String no){
		for(int i =0; i<paymentItems.size(); i++){
			if(paymentItems.get(i).get(6).equals(no)){
				return true;
			}
		}
		return false;
		
	}

}
