package forms.receipts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import util.ServerPropertyHandler;
import bus.InvoiceItemService;
import bus.InvoiceService;
import bus.PaymentItemService;

import com.cloudgarden.layout.AnchorConstraint;

import domain.Invoice;
import domain.InvoiceItem;
import domain.PaymentItem;


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
public class InvoiceViewer extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPanel jPanel1;
	private JLabel jLabel4;
	private JScrollPane jScrollPane1;
	private JLabel jLabel5;
	private JButton btnPrint;
	private JScrollPane jScrollPane2;
	private JTextField jTextField3;
	private JLabel jLabel7;
	private JTextField jTextField2;
	private JLabel jLabel6;
	private JTable jTable2;
	private JTable jTable1;
	private JTextField jTextField1;
	private JButton btnView;
	private JTextField txtOR;
	private ServerPropertyHandler config;
	private boolean isPartial;
	Vector<Vector<String>> prodData;
	Vector<Vector<String>> payData;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		InvoiceViewer inst = new InvoiceViewer(frame);
		inst.setVisible(true);
	}
	
	public InvoiceViewer(JFrame frame) {
		super(frame);
		config = new ServerPropertyHandler();
		isPartial = false;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Invoice Viewer");
				getContentPane().setLayout(null);
				{
					jLabel1 = new JLabel();
					getContentPane().add(
						jLabel1,
						new AnchorConstraint(
							38,
							312,
							216,
							33,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("Invoice Viewer");
					jLabel1.setFont(new java.awt.Font("Tahoma",1,18));
					jLabel1.setBounds(14, 7, 182, 35);
				}
				{
					txtOR = new JTextField();
					getContentPane().add(txtOR);
					txtOR.setBounds(91, 49, 140, 21);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("OR Number");
					jLabel2.setBounds(21, 49, 63, 28);
				}
				{
					btnView = new JButton();
					getContentPane().add(btnView);
					btnView.setText("View Information");
					btnView.setBounds(238, 48, 119, 21);
					btnView.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							boolean isValid =(txtOR.getText().length() > 0) && txtOR.getText().matches("^[0-9]*$"); 
							if(isValid){
								ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM invoice WHERE OR_NO = "+txtOR.getText()+" && "+"STORE_CODE = "+Main.getStoreCode());
								try {
									if(rs.next()){
										txtOR.setText(rs.getString("OR_NO"));
										if(rs.getString("PARTIAL") == "1"){
											isPartial = true;
										}
										rs = Main.getDBManager().executeQuery("SELECT SUM((i.SELL_PRICE - (i.SELL_PRICE * 0.01 * d.DISC_RATE ))* i.QUANTITY) FROM invoice_item i, discount_lu d WHERE i.DISC_CODE = d.DISC_NO AND i.OR_NO = "+txtOR.getText()+" && "+"i.STORE_CODE = "+Main.getStoreCode());
										if(rs.next()){
											
											String amtDue = String.format("%.2f", rs.getDouble(1));
											jTextField1.setText(amtDue);
											rs = Main.getDBManager().executeQuery("SELECT VAT FROM global");
											if(rs.next()){
												double subTotal = Double.parseDouble(amtDue)/(1+(rs.getDouble("VAT")*0.01));
												double VAT = Double.parseDouble(amtDue) - subTotal;
												jTextField2.setText(String.format("%.2f",subTotal));
												jTextField3.setText(String.format("%.2f",VAT));
												
											}
											
											rs = Main.getDBManager().executeQuery("SELECT * FROM invoice_item i, products_lu p, discount_lu d WHERE i.PROD_CODE = p.PROD_CODE && d.DISC_NO = i.DISC_CODE && OR_NO="+txtOR.getText()+" && "+"i.STORE_CODE = "+Main.getStoreCode());
											prodData = new Vector<Vector<String>>();
											Vector<String> colNames = new Vector<String>();  
															colNames.add("Product Code"); 
															colNames.add("Product Name");
															colNames.add("Quantity");
															colNames.add("Current Price");
															colNames.add("Selling Price");
															colNames.add("Deferred");
															colNames.add("Disc Code");
															colNames.add("Extension");
															
															
											
											while(rs.next()){
												Vector<String> rowData = new Vector<String>();
												rowData.add(rs.getString("PROD_CODE"));
												rowData.add(rs.getString("NAME"));
												rowData.add(rs.getString("QUANTITY"));
												rowData.add(rs.getString("SELL_PRICE"));
												double sellingPrice = rs.getDouble("SELL_PRICE") - (0.01 * rs.getDouble("SELL_PRICE") * rs.getDouble("DISC_RATE"));
												rowData.add(String.format("%.2f", sellingPrice));
												if(rs.getInt("DEFERRED") == 0){
													rowData.add("No");
												}
												else{
													rowData.add("Yes");
												}
												
												rowData.add(rs.getString("DISC_NO"));
												double ext = sellingPrice * rs.getInt("QUANTITY");
												rowData.add(String.format("%.2f",ext));
												
												prodData.add(rowData);
											}
											
//											update product table
											TableModel jTable1Model = new DefaultTableModel(prodData,colNames);
											jTable1 = new JTable(){
												public boolean isCellEditable(int row, int column)
												{
													return false;
												}
											};
											jScrollPane1.setViewportView(jTable1);
											jTable1.setModel(jTable1Model);
											
											//update payment table
											payData = new Vector<Vector<String>>();
											Vector<String> pcolNames = new Vector<String>();
											pcolNames.add("Payment Code");
											pcolNames.add("Payment Type");
											pcolNames.add("Amount");
											pcolNames.add("Credit Card Type");
											pcolNames.add("Credit Card No");
											pcolNames.add("Bank Check No");
											pcolNames.add("Gift Certificate Number");
											
											rs = Main.getDBManager().executeQuery("SELECT * FROM payment_item p, pay_type_lu pl WHERE p.PT_CODE = pl.PT_CODE && p.OR_NO="+txtOR.getText()+" && "+"p.STORE_CODE = "+Main.getStoreCode());
											
											while(rs.next()){
												Vector<String> rowData = new Vector<String>();
												rowData.add(rs.getString("PT_CODE"));
												rowData.add(rs.getString("NAME"));
												rowData.add(rs.getString("AMT"));
												rowData.add(rs.getString("CARD_TYPE"));
												rowData.add(rs.getString("CARD_NO"));
												rowData.add(rs.getString("CHECK_NO"));
												rowData.add(rs.getString("GC_NO"));
												payData.add(rowData);
											}
											
												
											
//											update product table
											TableModel jTable1Mode2 = new DefaultTableModel(payData,pcolNames);
											jTable2 = new JTable(){
												public boolean isCellEditable(int row, int column)
												{
													return false;
												}
											};
											jScrollPane2.setViewportView(jTable2);
											jTable2.setModel(jTable1Mode2);
										}
										
									}else{
										JOptionPane.showMessageDialog(null, "OR Number does not exist in the database.","Prompt",JOptionPane.WARNING_MESSAGE);
									}
									
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Please input a valid OR number.","Prompt",JOptionPane.WARNING_MESSAGE);
							}
							
							
						}
					});
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("Items");
					jLabel3.setBounds(21, 154, 63, 28);
					jLabel3.setFont(new java.awt.Font("Tahoma",1,14));
				}
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1);
					jPanel1.setBounds(21, 84, 693, 56);
					jPanel1.setBackground(new java.awt.Color(234,254,255));
					jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					jPanel1.setLayout(null);
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4);
						jLabel4.setText("Amount Due");
						jLabel4.setBounds(14, 21, 105, 14);
						jLabel4.setFont(new java.awt.Font("Tahoma",1,14));
					}
					{
						jTextField1 = new JTextField();
						jPanel1.add(jTextField1);
						jTextField1.setBounds(140, 21, 112, 21);
						jTextField1.setEditable(false);
					}
					{
						jLabel6 = new JLabel();
						jPanel1.add(jLabel6);
						jLabel6.setText("Sub Total");
						jLabel6.setBounds(308, 21, 63, 28);
					}
					{
						jTextField2 = new JTextField();
						jPanel1.add(jTextField2);
						jTextField2.setBounds(364, 21, 112, 21);
						jTextField2.setEditable(false);
					}
					{
						jLabel7 = new JLabel();
						jPanel1.add(jLabel7);
						jLabel7.setText("VAT");
						jLabel7.setBounds(511, 28, 49, 14);
					}
					{
						jTextField3 = new JTextField();
						jPanel1.add(jTextField3);
						jTextField3.setEditable(false);
						jTextField3.setBounds(539, 21, 112, 21);
					}
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1);
					jScrollPane1.setBounds(21, 182, 693, 126);
					{
						TableModel jTable1Model = new DefaultTableModel(
								new String[][] {  },
								new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" });
							jTable1 = new JTable();
							jScrollPane1.setViewportView(jTable1);
							jTable1.setModel(jTable1Model);
					}
				}
				{
					jLabel5 = new JLabel();
					getContentPane().add(jLabel5);
					jLabel5.setText("Payments");
					jLabel5.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel5.setBounds(21, 322, 147, 28);
				}
				{
					jScrollPane2 = new JScrollPane();
					getContentPane().add(jScrollPane2);
					jScrollPane2.setBounds(21, 350, 693, 70);
					{
						TableModel jTable2Model = new DefaultTableModel(
								new String[][] { },
								new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
							jTable2 = new JTable();
							jScrollPane2.setViewportView(jTable2);
							jTable2.setModel(jTable2Model);
							
							
					}
				}
				{
					btnPrint = new JButton();
					getContentPane().add(btnPrint);
					btnPrint.setText("Print Receipt");
					btnPrint.setBounds(308, 476, 154, 28);
					btnPrint.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Invoice invoice = InvoiceService.getInvoiceByOr(txtOR.getText());
							List<InvoiceItem> invoiceItems = InvoiceItemService.findInvoiceItemByOR(Long.parseLong(txtOR.getText()));
							List<PaymentItem> paymentItems = PaymentItemService.getPaymentItemList(Long.parseLong(txtOR.getText()));
							ReceiptPanel receiptPanel = new ReceiptPanel(invoice, invoiceItems, paymentItems,"0");
							ValidateReceipt validateReceiptDialog = new ValidateReceipt(InvoiceViewer.this, receiptPanel);
							validateReceiptDialog.setLocationRelativeTo(null);
							validateReceiptDialog.setVisible(true);
						
						}
					});
				}
			}
			this.setSize(743, 580);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayInfo() throws SQLException{
		
	}

}
