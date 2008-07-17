package forms.invoice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import util.StringUtility;
import bus.InvoiceService;
import bus.PaymentItemService;
import bus.ProductService;
import bus.VatService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import domain.InvoiceItem;
import domain.PaymentItem;
import domain.Product;
import forms.FastAddition;
import forms.PackageItems;
import forms.Payment;
import forms.PaymentDialog;
import forms.ProductDialog;
import forms.lookup.ClerkLookUp;
import forms.lookup.CustomerLookUp;


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
public class InvoicePanel extends javax.swing.JPanel {

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new InvoicePanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private JButton resetBtn;
	protected boolean pauseDeleteFlag;
	protected int pauseSelectedIndex;
	private JButton jButton29;
	private JButton jButton17;
	private JLabel jLabel15;
	private JButton jButton3;
	private JScrollPane paymentTableScrollPane;
	private JTable paymentTable;
	private JScrollPane itemTableScrollPane;
	private JTable itemTable;
	private JPanel jPanel7;
	private JTextField txtSubTotal;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JLabel jLabel13;
	private JLabel jLabel14;
	private JLabel lblAmount;
	private JTextField txtVat;
	private JTextField jTextField7;
	private JTextField jTextField8;
	private JPanel jPanel6;
	private JCheckBox partialChk;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel welcomePanel;
	private JLabel jLabel7;
	private JTextField jTextField2;
	private JTextField invoiceTxt;
	private JLabel jLabel8;
	private JLabel jLabel6;
	private JButton jButton2;
	private JLabel jLabel9;
	private JTextField salesSpecialistTxt;
	private JLabel jLabel17;
	private JTextField customerTxt;
	private JButton jButton10;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton7;
	private JButton jButton8;
	private JButton jButton9;
	private JLabel jLabel16;
	private JButton jButton31;
	
	public InvoicePanel() {
		super();
		initGUI();
		
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			
			this.setFont(new java.awt.Font("Dialog",0,14));
			this.setBackground(new java.awt.Color(255,255,255));
			this.setPreferredSize(new java.awt.Dimension(861, 469));
			this.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			{
				welcomePanel = new JPanel();
				this.add(welcomePanel, new AnchorConstraint(45, 277, 146, 7, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				welcomePanel.setPreferredSize(new java.awt.Dimension(232, 47));
				welcomePanel.setBackground(new java.awt.Color(254,253,218));
				welcomePanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				welcomePanel.setLayout(null);
				{
					jLabel1 = new JLabel();
					welcomePanel.add(jLabel1);
					jLabel1.setText("Welcome, Ate Eva");
					jLabel1.setBounds(13, 7, 203, 16);
					jLabel1.setFont(new java.awt.Font("Segoe UI",1,12));
				}
				{
					jLabel2 = new JLabel();
					welcomePanel.add(jLabel2);
					jLabel2.setText("Shaw Boulevard Branch");
					jLabel2.setBounds(13, 24, 207, 19);
				}
			}
			{
				resetBtn = new JButton();
				this.add(resetBtn, new AnchorConstraint(936, 430, 978, 317, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				resetBtn.setText("Reset All");
				resetBtn.setPreferredSize(new java.awt.Dimension(98, 21));
				resetBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/refresh.png")));
				resetBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pauseDeleteFlag = false;
						pauseSelectedIndex = -1;
//						try {
////							updateAll();
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
				});
			}
			{
				jButton29 = new JButton();
				this.add(jButton29, new AnchorConstraint(468, 439, 511, 317, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton29.setText("Fast Addition");
				jButton29.setPreferredSize(new java.awt.Dimension(105, 21));
				jButton29.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						FastAddition dialog = new FastAddition(Main.getInst());
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
				});
			}
			{
				jButton17 = new JButton();
				this.add(jButton17, new AnchorConstraint(468, 975, 511, 853, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton17.setText("View Package");
				jButton17.setPreferredSize(new java.awt.Dimension(105, 21));
				jButton17.setEnabled(false);
				jButton17.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String packagecode = itemTable.getValueAt(itemTable.getSelectedRow(),0).toString();
						PackageItems dialog = new PackageItems(Main.getInst(),packagecode);
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
				});
			}

			{
				jLabel15 = new JLabel();
				this.add(jLabel15, new AnchorConstraint(511, 447, 567, 317, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel15.setText("Payments");
				jLabel15.setPreferredSize(new java.awt.Dimension(112, 28));
				jLabel15.setFont(new java.awt.Font("Tahoma",1,14));
				jLabel15.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/payments.png")));
			}
			{
				jButton3 = new JButton();
				this.add(jButton3, new AnchorConstraint(468, 625, 511, 552, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton3.setText("Add");
				jButton3.setPreferredSize(new java.awt.Dimension(63, 21));
				jButton3.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent evt) {
						
						ProductDialog dialog = ProductDialog.getProductDialog(Main.getInst(),InvoicePanel.this,"add");
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
						
					}
				});
			}
			{
				paymentTableScrollPane = new JScrollPane();
				this.add(paymentTableScrollPane, new AnchorConstraint(567, 975, 808, 317, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				paymentTableScrollPane.setPreferredSize(new java.awt.Dimension(567, 119));
				{
					TableModel jTable2Model = new DefaultTableModel(
						new String[][] { },
						new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
					paymentTable = new JTable();
					paymentTableScrollPane.setViewportView(paymentTable);
					paymentTable.setModel(jTable2Model);
				}
			}
			{
				itemTableScrollPane = new JScrollPane();
				this.add(itemTableScrollPane, new AnchorConstraint(72, 975, 455, 317, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				itemTableScrollPane.setPreferredSize(new java.awt.Dimension(567, 189));
				{
					TableModel jTable1Model = new DefaultTableModel(
						new String[][] {  },
						new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" });
					itemTable = new JTable();
					itemTableScrollPane.setViewportView(itemTable);
					itemTable.setModel(jTable1Model);
				}
			}
			{
				jPanel7 = new JPanel();
				AnchorLayout jPanel7Layout = new AnchorLayout();
				jPanel7.setLayout(jPanel7Layout);
				this.add(jPanel7, new AnchorConstraint(587, 277, 956, 7, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel7.setPreferredSize(new java.awt.Dimension(232, 173));
				jPanel7.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel7.setBackground(new java.awt.Color(255,128,255));
				{
					txtSubTotal = new JTextField();
					jPanel7.add(txtSubTotal, new AnchorConstraint(348, 941, 464, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					txtSubTotal.setPreferredSize(new java.awt.Dimension(98, 21));
					txtSubTotal.setEditable(false);
					txtSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
					txtSubTotal.setText("0.00");
				}
				{
					jLabel10 = new JLabel();
					jPanel7.add(jLabel10, new AnchorConstraint(348, 487, 464, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel10.setText("Sub Total");
					jLabel10.setPreferredSize(new java.awt.Dimension(105, 21));
					jLabel10.setFont(new java.awt.Font("Tahoma",1,14));
				}
				{
					jLabel11 = new JLabel();
					jPanel7.add(jLabel11, new AnchorConstraint(502, 487, 618, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					ResultSet rs = Main.getDBManager().executeQuery("SELECT VAT FROM global");
					if(rs.next()){
						jLabel11.setText(rs.getString("VAT")+"% VAT");
					}
					
					jLabel11.setPreferredSize(new java.awt.Dimension(105, 21));
					jLabel11.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel11.setText("VAT");
				}
				{
					jLabel12 = new JLabel();
					jPanel7.add(jLabel12, new AnchorConstraint(656, 487, 771, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel12.setText("Total Payment");
					jLabel12.setPreferredSize(new java.awt.Dimension(105, 21));
					jLabel12.setFont(new java.awt.Font("Tahoma",1,14));
				}
				{
					jLabel13 = new JLabel();
					jPanel7.add(jLabel13, new AnchorConstraint(810, 487, 925, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel13.setText("Change");
					jLabel13.setPreferredSize(new java.awt.Dimension(105, 21));
					jLabel13.setFont(new java.awt.Font("Tahoma",1,14));
				}
				{
					jLabel14 = new JLabel();
					jPanel7.add(jLabel14, new AnchorConstraint(41, 820, 156, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel14.setText("Amount Due");
					jLabel14.setFont(new java.awt.Font("Tahoma",1,22));
					jLabel14.setPreferredSize(new java.awt.Dimension(182, 21));
				}
				{
					lblAmount = new JLabel();
					jPanel7.add(lblAmount, new AnchorConstraint(195, 820, 310, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					lblAmount.setFont(new java.awt.Font("Tahoma",1,22));
					lblAmount.setPreferredSize(new java.awt.Dimension(182, 21));
					lblAmount.setText("0.00");
				}
				{
					txtVat = new JTextField();
					jPanel7.add(txtVat, new AnchorConstraint(502, 941, 618, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					txtVat.setPreferredSize(new java.awt.Dimension(98, 21));
					txtVat.setEditable(false);
					txtVat.setHorizontalAlignment(SwingConstants.RIGHT);
					txtVat.setText("0.00");
				}
				{
					jTextField7 = new JTextField();
					jPanel7.add(jTextField7, new AnchorConstraint(656, 941, 771, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField7.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField7.setEditable(false);
					jTextField7.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField7.setText("0.00");
				}
				{
					jTextField8 = new JTextField();
					jPanel7.add(jTextField8, new AnchorConstraint(810, 941, 925, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField8.setPreferredSize(new java.awt.Dimension(98, 21));
					jTextField8.setEditable(false);
					jTextField8.setHorizontalAlignment(SwingConstants.RIGHT);
					jTextField8.setText("0.00");
				}
			}
			{
				jPanel6 = new JPanel();
				AnchorLayout jPanel6Layout = new AnchorLayout();
				jPanel6.setLayout(jPanel6Layout);
				this.add(jPanel6, new AnchorConstraint(165, 277, 568, 7, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel6.setPreferredSize(new java.awt.Dimension(232, 189));
				jPanel6.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel6.setBackground(new java.awt.Color(164,222,251));
				{
					partialChk = new JCheckBox();
					jPanel6.add(partialChk, new AnchorConstraint(388, 877, 537, 394, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					partialChk.setPreferredSize(new java.awt.Dimension(112, 28));
					partialChk.setBackground(new java.awt.Color(164,222,251));
				}
				{
					jLabel7 = new JLabel();
					jPanel6.add(jLabel7, new AnchorConstraint(108, 394, 177, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel7.setText("OR Number");
					jLabel7.setPreferredSize(new java.awt.Dimension(84, 13));
					jLabel7.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					jTextField2 = new JTextField();
					jPanel6.add(jTextField2, new AnchorConstraint(108, 967, 214, 394, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField2.setPreferredSize(new java.awt.Dimension(133, 20));
					jTextField2.setEditable(false);
					jTextField2.setText(StringUtility.zeroFill(InvoiceService.getNextORNumber(), 10));

				}
				{
					invoiceTxt = new JTextField();
					jPanel6.add(invoiceTxt, new AnchorConstraint(246, 967, 351, 394, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					invoiceTxt.setPreferredSize(new java.awt.Dimension(133, 20));

				}
				{
					jLabel8 = new JLabel();
					jPanel6.add(jLabel8, new AnchorConstraint(246, 454, 320, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel8.setText("Invoice No");
					jLabel8.setPreferredSize(new java.awt.Dimension(98, 14));
					jLabel8.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					jLabel6 = new JLabel();
					jPanel6.add(jLabel6, new AnchorConstraint(563, 424, 706, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel6.setText("Sales Specs");
					jLabel6.setPreferredSize(new java.awt.Dimension(91, 27));
					jLabel6.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					jButton2 = new JButton();
					jPanel6.add(jButton2, new AnchorConstraint(579, 976, 685, 825, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("...");
					jButton2.setPreferredSize(new java.awt.Dimension(35, 20));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							ClerkLookUp dialog = ClerkLookUp.getClerkLookUp(Main.getInst(), InvoicePanel.this, "Assistant");
							dialog.setLocationRelativeTo(null);
							dialog.setVisible(true);
						}
						});
				}
				{
					jLabel9 = new JLabel();
					jPanel6.add(jLabel9, new AnchorConstraint(425, 424, 500, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel9.setText("Partial");
					jLabel9.setPreferredSize(new java.awt.Dimension(91, 14));
					jLabel9.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					salesSpecialistTxt = new JTextField();
					jPanel6.add(salesSpecialistTxt, new AnchorConstraint(579, 825, 685, 403, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					salesSpecialistTxt.setPreferredSize(new java.awt.Dimension(98, 20));
				}
				{
					jLabel17 = new JLabel();
					jPanel6.add(jLabel17, new AnchorConstraint(732, 424, 875, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel17.setText("Customer ");
					jLabel17.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel17.setPreferredSize(new java.awt.Dimension(91, 27));
				}
				{
					customerTxt = new JTextField();
					jPanel6.add(customerTxt, new AnchorConstraint(732, 821, 838, 398, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					customerTxt.setPreferredSize(new java.awt.Dimension(98, 20));
				}
				{
					jButton10 = new JButton();
					jPanel6.add(jButton10, new AnchorConstraint(732, 976, 838, 825, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton10.setText("...");
					jButton10.setPreferredSize(new java.awt.Dimension(35, 20));
					jButton10.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							CustomerLookUp dialog = CustomerLookUp.getCustomerLookUp(Main.getInst(), InvoicePanel.this, null);
							dialog.setLocationRelativeTo(null);
							dialog.setVisible(true);
						}
					});
				}
			}
			{
				jButton4 = new JButton();
				this.add(jButton4, new AnchorConstraint(468, 707, 511, 633, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton4.setText("Edit");
				jButton4.setPreferredSize(new java.awt.Dimension(63, 21));
				jButton4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						String productCode = itemTable.getValueAt(itemTable.getSelectedRow(), 0).toString();
						String quantity = itemTable.getValueAt(itemTable.getSelectedRow(), 2).toString();
						int discountCode = Integer.parseInt( itemTable.getValueAt(itemTable.getSelectedRow(), 6).toString());
						String deferred = itemTable.getValueAt(itemTable.getSelectedRow(), 5).toString();
						
						
						ProductDialog dialog = ProductDialog.getProductDialog(Main.getInst(),InvoicePanel.this,productCode);
						dialog.setProductCode(productCode);
						dialog.setQuantity(quantity);
						dialog.setDiscount(discountCode);
						dialog.setDeferredValue(deferred);
						
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
				});
			}
			{
				jButton5 = new JButton();
				this.add(jButton5, new AnchorConstraint(469, 796, 512, 715, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton5.setText("Delete");
				jButton5.setPreferredSize(new java.awt.Dimension(70, 21));
				jButton5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						removeInvoiceItem();
					}
				});
			}
			{
				jButton6 = new JButton();
				this.add(jButton6, new AnchorConstraint(822, 625, 865, 552, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton6.setText("Add");
				jButton6.setPreferredSize(new java.awt.Dimension(63, 21));
				jButton6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						double curAmount = Double.parseDouble(lblAmount.getText());
//						Payment dialog = new Payment(Main.getInst(),"add", curAmount);
						PaymentDialog dialog = PaymentDialog.getPaymentDialog(Main.getInst(), InvoicePanel.this, "add");
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
				});
			}
			{
				jButton7 = new JButton();
				this.add(jButton7, new AnchorConstraint(822, 707, 865, 633, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton7.setText("Edit");
				jButton7.setPreferredSize(new java.awt.Dimension(63, 21));
				jButton7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String[] values = new String[7];
						for(int i =0; i<7; i++){
							values[i] = (String) paymentTable.getValueAt(paymentTable.getSelectedRow(), i);
						}
						Payment dialog = new Payment(Main.getInst(),values,paymentTable.getSelectedRow(),"edit");
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
				});
			}
			{
				jButton8 = new JButton();
				this.add(jButton8, new AnchorConstraint(824, 796, 867, 715, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton8.setText("Delete");
				jButton8.setPreferredSize(new java.awt.Dimension(70, 21));
				jButton8.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
					}
				});
			}
			{
				jButton9 = new JButton();
				this.add(jButton9, new AnchorConstraint(922, 975, 978, 763, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton9.setText("Process Transaction");
				jButton9.setPreferredSize(new java.awt.Dimension(182, 28));
				jButton9.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				jButton9.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						if(	productItems.size() != 0 && 
//							jTextField6.getText().length() != 0 &&
//							jPasswordField2.getPassword().length != 0){
////							ResultSet rs = db.executeQuery("SELECT * FROM clerk_lu WHERE AES_DECRYPT(password,'babyland') = '"+new String(jPasswordField2.getPassword())+"' AND clerk_code = '"+jTextField6.getText()+"'");
//							ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM clerk_lu WHERE password = '"+new String(jPasswordField2.getPassword())+"' AND clerk_code = '"+jTextField6.getText()+"'");
//							
//							try {
//								if(rs.next()){
//									if(!jCheckBox1.isSelected()){
//										
//										//complete transaction
//										if(paymentItems.size() != 0) {
//									
//											double amount = Double.parseDouble(jLabel2.getText());
//											double payments = Double.parseDouble(jTextField7.getText());
//											
//											if(amount <= payments){
//												
//												processTransaction("complete");
//												
//												if(pauseDeleteFlag){
//													pausedData.remove(pauseSelectedIndex);
//													Object[][] data = new Object[pausedData.size()][7];
//													for(int i = 0; i< pausedData.size(); i++)
//													{
//														data[i][0] = i+1;
//														PendingTransactionData pauseTransactionRow = pausedData.get(i);
//														data[i][1] = pauseTransactionRow.getDate();
//														data[i][2] = pauseTransactionRow.getTime();
//														
//													}
//													
//													TableModel jTable1Model = new DefaultTableModel(
//															data,
//															new String[] { "Transaction No", "Date","Time"  });
//														jTable8 = new JTable(){
//															public boolean isCellEditable(int row, int column)
//															{
//																return false;
//															}
//														};
//														jScrollPane8.setViewportView(jTable8);
//														jTable8.setModel(jTable1Model);
//														
//													pauseDeleteFlag = false;
//													pauseSelectedIndex = -1;
//												}
//											}
//											
//											else{
//												JOptionPane.showMessageDialog(null, 
//														"This is not a partial transaction. \n" +
//														" Amount is greater than payment.", 
//														"Warning",JOptionPane.WARNING_MESSAGE);
//											}
//										}
//										else{
//											JOptionPane.showMessageDialog(null, 
//													"This is not a partial transaction. \n" +
//													" Amount is greater than payment.", 
//													"Warning",JOptionPane.WARNING_MESSAGE);
//										}
//									}
//									else{
//										try {
//											processTransaction("partial");
//											
//											if(pauseDeleteFlag){
//												pausedData.remove(pauseSelectedIndex);
//												Object[][] data = new Object[pausedData.size()][7];
//												for(int i = 0; i< pausedData.size(); i++)
//												{
//													data[i][0] = i+1;
//													PendingTransactionData pauseTransactionRow = pausedData.get(i);
//													data[i][1] = pauseTransactionRow.getDate();
//													data[i][2] = pauseTransactionRow.getTime();
//													
//												}
//												
//												TableModel jTable1Model = new DefaultTableModel(
//														data,
//														new String[] { "Transaction No", "Date","Time"  });
//													jTable8 = new JTable(){
//														public boolean isCellEditable(int row, int column)
//														{
//															return false;
//														}
//													};
//													jScrollPane8.setViewportView(jTable8);
//													jTable8.setModel(jTable1Model);
//													
//												pauseDeleteFlag = false;
//												pauseSelectedIndex = -1;
//											}
//										} catch (SQLException e) {
//											// TODO Auto-generated catch block
//											e.printStackTrace();
//										}
//									}
//								}
//								else{
//									JOptionPane.showMessageDialog(null, 
//												"Invalid Cashier log in.", 
//												"Warning",JOptionPane.WARNING_MESSAGE);
//								}
//							}
//							catch (SQLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//						else{
//							JOptionPane.showMessageDialog(null, 
//									"Please complete required fields of the form.", 
//									"Warning",JOptionPane.WARNING_MESSAGE);
//						}
					}
				});
			}
			{
				jLabel16 = new JLabel();
				this.add(jLabel16, new AnchorConstraint(15, 422, 71, 317, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel16.setText("Items");
				jLabel16.setFont(new java.awt.Font("Tahoma",1,14));
				jLabel16.setPreferredSize(new java.awt.Dimension(91, 28));
				jLabel16.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/items.png")));
			}
			{
				jButton31 = new JButton();
				this.add(jButton31, new AnchorConstraint(936, 617, 978, 447, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton31.setText("Pause Transaction");
				jButton31.setPreferredSize(new java.awt.Dimension(147, 21));
				jButton31.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/pause.png")));
				jButton31.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						pauseDeleteFlag = false;
//						pauseSelectedIndex = -1;
//						ResultSet rs = Main.getDBManager().executeQuery("SELECT DATE(CURRENT_TIMESTAMP), TIME(CURRENT_TIMESTAMP)");
//						String date = "";
//						String time = "";
//						try {
//							if(rs.next()){
//								date = rs.getString(1);
//								time = rs.getString(2);
//							}
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
//						if(productItems.size() >0){
//							Vector<Vector<String>> tempProdList = new Vector<Vector<String>>();
//							for(int i = 0; i< productItems.size(); i++){
//								Vector<String> otherdata = productItems.get(i);
//								tempProdList.add(otherdata);
//							}
//							
//							PendingTransactionData ptd = new PendingTransactionData(date, time, tempProdList);
//							pausedData.add(ptd);
//							
//							try {
//								updateAll();
//							} catch (SQLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
					}
				});
				
			}

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void setAssistantID(String id){
		salesSpecialistTxt.setText(StringUtility.zeroFill(id, 3));
	}
	public void setCustomerID(String id){
		customerTxt.setText(StringUtility.zeroFill(id, 5));
	}
	
	public void addInvoiceItem(InvoiceItem invoiceItem){
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
//		"Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" 
		Product product = ProductService.getProductById(invoiceItem.getProductCode());
		model.addRow(new String[]{invoiceItem.getProductCode(),
								  product.getName(),
								  invoiceItem.getQuantity().toString(),
								  product.getSellPrice().toString(),
								  String.format("%.2f",invoiceItem.getSellPrice()),
								  (invoiceItem.getIsDeferred()==1)?"Yes":"No",
								  invoiceItem.getDiscountCode().toString(),
								  "000"});
		updateAmounts();
	}
	
	public Integer getInvoiceItemRow(String prodCode){
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			if(model.getValueAt(i, 0).toString().equals(prodCode)){
				return i;
			}
		}
		return null;
	}
	
	public void editInvoiceItem(InvoiceItem invoiceItem, String productCode){
		Product product = ProductService.getProductById(invoiceItem.getProductCode());
		int index = getInvoiceItemRow(productCode);
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		model.setValueAt(invoiceItem.getProductCode(), index, 0);
		model.setValueAt(product.getName(), index, 1);
		model.setValueAt(invoiceItem.getQuantity(), index, 2);
		model.setValueAt(product.getSellPrice().toString(), index, 3);
		model.setValueAt(invoiceItem.getSellPrice().toString(), index, 4);
		model.setValueAt((invoiceItem.getIsDeferred()==1)?"Yes":"No", index, 5);
		model.setValueAt(invoiceItem.getDiscountCode().toString(), index, 6);
		updateAmounts();
	}
	
	public void removeInvoiceItem(){
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		model.removeRow(itemTable.getSelectedRow());
		updateAmounts();
	}
	
	public void updateAmounts(){
		Double vatRate = VatService.getVatRate();
		Double amount = 0.0d;
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			int quantity =  Integer.parseInt(model.getValueAt(i,2).toString());
			double sellingPrice = Double.parseDouble(model.getValueAt(i, 4).toString());
			amount += (quantity*sellingPrice);
		}
		double subTotal = amount/vatRate;
		txtSubTotal.setText(String.format("%.2f", subTotal));
		txtVat.setText(String.format("%.2f", (amount - subTotal)));
		lblAmount.setText(String.format("%.2f", amount));
	}
	
	public void addPaymentItem(PaymentItem paymentItem){
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		model.addRow(new String[]{paymentItem.getPaymentCode().toString(),
								  PaymentItemService.getPaymentType(paymentItem.getPaymentCode()),
								  paymentItem.getAmount().toString(),
								  paymentItem.getCardType(),
								  paymentItem.getCardNo(),
								  paymentItem.getCheckNo(),
								  paymentItem.getGcNo()
								  });
		
	}

	public Integer getPaymentItemRow(Integer paymentCode){
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			if(model.getValueAt(i, 0).toString().equals(paymentCode)){
				return i;
			}
		}
		return null;
	}
	
	public void setLoginInformation(String userName, String branchName){
		jLabel1.setText("Welcome, "+userName);
		jLabel2.setText(branchName);
	}
	
	public void clearInfoValues(){
		//clear items table;
		TableModel jTable1Model = new DefaultTableModel(
				new String[][] {  },
				new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" });
		itemTable = new JTable();
		itemTableScrollPane.setViewportView(itemTable);
		itemTable.setModel(jTable1Model);
		
		//clear payments table
		TableModel jTable2Model = new DefaultTableModel(
			new String[][] { },
			new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
		paymentTable = new JTable();
		paymentTableScrollPane.setViewportView(paymentTable);
		paymentTable.setModel(jTable2Model);
		
		//clear text fields
		invoiceTxt.setText("");
		partialChk.setSelected(false);
		salesSpecialistTxt.setText("");
		customerTxt.setText("");
		
		//update amounts
		updateAmounts();
	}
}
