package com.dagitab.pos.forms.invoice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import bus.InvoiceService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.dagitab.pos.bus.DiscountService;
import com.dagitab.pos.bus.GCItemService;
import com.dagitab.pos.bus.InvoiceItemService;
import com.dagitab.pos.bus.PaymentItemService;
import com.dagitab.pos.bus.ProductService;
import com.dagitab.pos.bus.VatService;
import com.dagitab.pos.domain.GCItem;
import com.dagitab.pos.domain.Invoice;
import com.dagitab.pos.domain.InvoiceItem;
import com.dagitab.pos.domain.PaymentItem;
import com.dagitab.pos.domain.Product;
import com.dagitab.pos.domain.Transaction;
import com.dagitab.pos.forms.MainWindow;
import com.dagitab.pos.forms.PackageItems;
import com.dagitab.pos.forms.interfaces.Payments;
import com.dagitab.pos.forms.lookup.ClerkLookUp;
import com.dagitab.pos.forms.lookup.CustomerLookUp;
import com.dagitab.pos.forms.lookup.FastAddition;
import com.dagitab.pos.forms.lookup.PaymentDialog;
import com.dagitab.pos.forms.lookup.ProductDialog;
import com.dagitab.pos.forms.lookup.ProductDiscountDialog;
import com.dagitab.pos.forms.receipts.ReceiptPanel;
import com.dagitab.pos.forms.receipts.ValidateReceipt;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;
import com.dagitab.pos.util.PaymentCalculatorUtility;
import com.dagitab.pos.util.StringUtility;

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
public class InvoicePanel extends javax.swing.JPanel implements Payments  {

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
	private JButton addInvoiceItemButton;
	private JScrollPane paymentTableScrollPane;
	private AbstractAction deletePaymentItemAction;
	private AbstractAction editPaymentItemAction;
	private AbstractAction addPaymentItemAction;
	private AbstractAction deleteInvoiceItemAction;
	private AbstractAction editItemAction1;
	private AbstractAction addInvoiceItemAction;
	private JLabel partialLabel;
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
	private JTextField totalPayment;
	private JTextField changeField;
	private JPanel jPanel6;
	private JCheckBox partialChk;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel welcomePanel;
	private JLabel jLabel7;
	private JTextField orNoTxt;
	private JTextField invoiceTxt;
	private JLabel jLabel8;
	private JLabel jLabel6;
	private JButton jButton2;
	private JTextField salesSpecialistTxt;
	private JLabel jLabel17;
	private JTextField customerTxt;
	private JButton jButton10;
	private JButton editInvoiceItemButton;
	private JButton deleteInvoiceItemButton;
	private JButton addPaymentItemButton;
	private JButton editPaymentItemButton;
	private JButton deletePaymentItemButton;
	private JButton processButton;
	private JLabel jLabel16;
	private JButton jButton31;
	private MainWindow mainWindow;
	private static Logger logger = Logger.getLogger(InvoicePanel.class);
	
	public InvoicePanel() {
		super();
		initGUI();
	}
	
	public InvoicePanel(MainWindow mainWindow) {
		super();
		initGUI();
		this.mainWindow = mainWindow;
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
				AnchorLayout welcomePanelLayout = new AnchorLayout();
				this.add(welcomePanel, new AnchorConstraint(45, 277, 146, 7, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				welcomePanel.setPreferredSize(new java.awt.Dimension(232, 47));
				welcomePanel.setBackground(new java.awt.Color(254,253,218));
				welcomePanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				welcomePanel.setLayout(welcomePanelLayout);
				{
					jLabel1 = new JLabel();
					welcomePanel.add(jLabel1, new AnchorConstraint(159, 976, 563, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("Welcome, Ate Eva");
					jLabel1.setFont(new java.awt.Font("Segoe UI",0,20));
					jLabel1.setPreferredSize(new java.awt.Dimension(219, 19));
				}
				{
					jLabel2 = new JLabel();
					welcomePanel.add(jLabel2, new AnchorConstraint(563, 976, 968, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("Shaw Boulevard Branch");
					jLabel2.setFont(new java.awt.Font("Segoe UI",0,14));
					jLabel2.setPreferredSize(new java.awt.Dimension(219, 19));
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
						try {
							clearInfoValues();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Database connection seems to be unstable. Please restart the application.", "Warning", JOptionPane.ERROR_MESSAGE);
							LoggerUtility.getInstance().logStackTrace(e);
						}
					}
					
				});
			}
			{
				jButton29 = new JButton();
				this.add(jButton29, new AnchorConstraint(459, 438, 502, 316, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton29.setText("Fast Addition");
				jButton29.setPreferredSize(new java.awt.Dimension(105, 20));
				jButton29.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						FastAddition dialog = new FastAddition(Main.getInst());
						dialog.setInvoker(InvoicePanel.this);
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
				});
			}
			{
				jButton17 = new JButton();
				this.add(jButton17, new AnchorConstraint(459, 975, 502, 853, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton17.setText("View Package");
				jButton17.setPreferredSize(new java.awt.Dimension(105, 20));
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
				addInvoiceItemButton = new JButton();
				this.add(addInvoiceItemButton, new AnchorConstraint(459, 614, 502, 534, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				addInvoiceItemButton.setText("Add");
				addInvoiceItemButton.setPreferredSize(new java.awt.Dimension(69, 20));
				addInvoiceItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png")));
				addInvoiceItemButton.setBackground(new java.awt.Color(255,255,255));
				addInvoiceItemButton.setAction(getAddInvoiceItemAction());
				
				addInvoiceItemButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed F1"), "addInvoiceItemButton");
				addInvoiceItemButton.getActionMap().put("addInvoiceItemButton",getAddInvoiceItemAction() );
				
			}
			{
				paymentTableScrollPane = new JScrollPane();
				this.add(paymentTableScrollPane, new AnchorConstraint(567, 975, 808, 317, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				paymentTableScrollPane.setPreferredSize(new java.awt.Dimension(567, 119));
				paymentTableScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					TableModel jTable2Model = new DefaultTableModel(
						new String[][] { },
						new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
					paymentTable = new JTable() {
						@Override
						public boolean isCellEditable(
							int row,
							int column) {
							return false;
						}
					};

					paymentTable.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "paymentTable");
					paymentTable.getActionMap().put("paymentTable",getDeletePaymentItemAction() );
					paymentTable.addMouseListener(new MouseAdapter(){
						 public void mouseClicked(MouseEvent e){
							 if (e.getClickCount() == 2){
							 String paymentCode = paymentTable.getValueAt(paymentTable.getSelectedRow(), 0).toString();
								String paymentType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 1).toString();
								String paymentAmount = paymentTable.getValueAt(paymentTable.getSelectedRow(), 2).toString();
								String creditCardType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 3).toString();
								String creditCardNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 4).toString();
								String bankCheckNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 5).toString();
								String gcNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 6).toString();
								
								
								PaymentDialog dialog = PaymentDialog.getPaymentDialog(Main.getInst(),InvoicePanel.this,paymentCode);
								
								dialog.setAmount(paymentAmount);
								dialog.setPaymentType(paymentType);
								dialog.setCreditType(creditCardType);
								dialog.setCreditCard(creditCardNum);
								dialog.setBankCheck(bankCheckNum);
								dialog.setGiftCheck(gcNum);
								
								dialog.setLocationRelativeTo(null);
								dialog.setVisible(true);
					      }
						 }
					});
					paymentTableScrollPane.setViewportView(paymentTable);
					paymentTable.setModel(jTable2Model);
				}
			}
			{
				itemTableScrollPane = new JScrollPane();
				this.add(itemTableScrollPane, new AnchorConstraint(72, 975, 455, 317, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				itemTableScrollPane.setPreferredSize(new java.awt.Dimension(567, 189));
				itemTableScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					TableModel jTable1Model = new DefaultTableModel(
						new String[][] {  },
						new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" });
					
					itemTable = new JTable(){
						@Override
						public boolean isCellEditable(
							int row,
							int column) {
							return false;
						}
					};
					itemTable.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "itemTable");
					itemTable.getActionMap().put("itemTable",getDeleteInvoiceItemAction() );
					itemTable.addMouseListener(new MouseAdapter(){
						 public void mouseClicked(MouseEvent evt){
							 if (evt.getClickCount() == 2){
							 try{
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
								catch(ArrayIndexOutOfBoundsException e){
									JOptionPane.showMessageDialog(null, "Please select item from the list", "Prompt", JOptionPane.ERROR_MESSAGE);
								}
							 }
						 }
					});
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
					totalPayment = new JTextField();
					jPanel7.add(totalPayment, new AnchorConstraint(656, 941, 771, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					totalPayment.setPreferredSize(new java.awt.Dimension(98, 21));
					totalPayment.setEditable(false);
					totalPayment.setHorizontalAlignment(SwingConstants.RIGHT);
					totalPayment.setText("0.00");
				}
				{
					changeField = new JTextField();
					jPanel7.add(changeField, new AnchorConstraint(810, 941, 925, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					changeField.setPreferredSize(new java.awt.Dimension(98, 21));
					changeField.setEditable(false);
					changeField.setHorizontalAlignment(SwingConstants.RIGHT);
					changeField.setText("0.00");
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
					partialLabel = new JLabel();
					jPanel6.add(partialLabel, new AnchorConstraint(732, 364, 828, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					partialLabel.setText("Partial");
					partialLabel.setPreferredSize(new java.awt.Dimension(77, 18));
					partialLabel.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					partialChk = new JCheckBox();
					jPanel6.add(partialChk, new AnchorConstraint(716, 816, 843, 441, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					partialChk.setPreferredSize(new java.awt.Dimension(87, 24));
					partialChk.setBackground(new java.awt.Color(164,222,251));
					partialChk.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					jLabel7 = new JLabel();
					jPanel6.add(jLabel7, new AnchorConstraint(108, 394, 177, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel7.setText("OR Number");
					jLabel7.setPreferredSize(new java.awt.Dimension(84, 13));
					jLabel7.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					orNoTxt = new JTextField();
					jPanel6.add(orNoTxt, new AnchorConstraint(92, 959, 198, 446, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					orNoTxt.setPreferredSize(new java.awt.Dimension(119, 20));
					orNoTxt.setEditable(false);
					String nextOR = InvoiceService.getNextORNumber();
					if(nextOR == null){
						nextOR = "1";
					}
					orNoTxt.setText(StringUtility.zeroFill(nextOR, 10));
				}
				{
					invoiceTxt = new JTextField();
					jPanel6.add(invoiceTxt, new AnchorConstraint(246, 959, 351, 446, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					invoiceTxt.setPreferredSize(new java.awt.Dimension(119, 20));
				}
				{
					jLabel8 = new JLabel();
					jPanel6.add(jLabel8, new AnchorConstraint(261, 454, 335, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel8.setText("Invoice No");
					jLabel8.setPreferredSize(new java.awt.Dimension(98, 14));
					jLabel8.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					jLabel6 = new JLabel();
					jPanel6.add(jLabel6, new AnchorConstraint(415, 420, 558, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel6.setText("Sales Specs ID");
					jLabel6.setPreferredSize(new java.awt.Dimension(90, 27));
					jLabel6.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					jButton2 = new JButton();
					jPanel6.add(jButton2, new AnchorConstraint(431, 959, 537, 739, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("...");
					jButton2.setPreferredSize(new java.awt.Dimension(51, 20));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							ClerkLookUp dialog = ClerkLookUp.getClerkLookUp(Main.getInst(), InvoicePanel.this, "Assistant");
							dialog.setLocationRelativeTo(null);
							dialog.setVisible(true);
						}
						});
				}
				{
					salesSpecialistTxt = new JTextField();
					jPanel6.add(salesSpecialistTxt, new AnchorConstraint(431, 721, 537, 446, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					salesSpecialistTxt.setPreferredSize(new java.awt.Dimension(64, 20));
				}
				{
					jLabel17 = new JLabel();
					jPanel6.add(jLabel17, new AnchorConstraint(568, 420, 701, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel17.setText("Customer ID");
					jLabel17.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel17.setPreferredSize(new java.awt.Dimension(90, 25));
				}
				{
					customerTxt = new JTextField();
					jPanel6.add(customerTxt, new AnchorConstraint(579, 721, 685, 446, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					customerTxt.setPreferredSize(new java.awt.Dimension(64, 20));
				}
				{
					jButton10 = new JButton();
					jPanel6.add(jButton10, new AnchorConstraint(579, 959, 685, 739, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton10.setText("...");
					jButton10.setPreferredSize(new java.awt.Dimension(51, 20));
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
				editInvoiceItemButton = new JButton();
				this.add(editInvoiceItemButton, new AnchorConstraint(459, 703, 502, 619, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				editInvoiceItemButton.setText("Edit");
				editInvoiceItemButton.setPreferredSize(new java.awt.Dimension(72, 20));
				editInvoiceItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png")));
				editInvoiceItemButton.setBackground(new java.awt.Color(255,255,255));
				editInvoiceItemButton.setAction(getEditItemAction());
				
				editInvoiceItemButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed F3"), "editInvoiceItemButton");
				editInvoiceItemButton.getActionMap().put("editInvoiceItemButton",getEditItemAction() );
			}
			{
				deleteInvoiceItemButton = new JButton();
				this.add(deleteInvoiceItemButton, new AnchorConstraint(459, 811, 502, 710, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deleteInvoiceItemButton.setText("Delete");
				deleteInvoiceItemButton.setPreferredSize(new java.awt.Dimension(87, 20));
				deleteInvoiceItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif")));
				deleteInvoiceItemButton.setBackground(new java.awt.Color(255,255,255));
				deleteInvoiceItemButton.setAction(getDeleteInvoiceItemAction());
				
				deleteInvoiceItemButton.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "deleteInvoiceItemButton");
				deleteInvoiceItemButton.getActionMap().put("deleteInvoiceItemButton",getDeleteInvoiceItemAction() );
				
				
			}
			{
				addPaymentItemButton = new JButton();
				this.add(addPaymentItemButton, new AnchorConstraint(811, 614, 853, 534, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				addPaymentItemButton.setText("Add");
				addPaymentItemButton.setPreferredSize(new java.awt.Dimension(69, 20));
				addPaymentItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png")));
				addPaymentItemButton.setBackground(new java.awt.Color(255,255,255));
				addPaymentItemButton.setAction(getAddPaymentItemAction());
				
				addPaymentItemButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed F2"), "addPaymentItemButton");
				addPaymentItemButton.getActionMap().put("addPaymentItemButton",getAddPaymentItemAction() );
				
			}
			{
				editPaymentItemButton = new JButton();
				this.add(editPaymentItemButton, new AnchorConstraint(811, 703, 853, 619, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				editPaymentItemButton.setText("Edit");
				editPaymentItemButton.setPreferredSize(new java.awt.Dimension(72, 20));
				editPaymentItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png")));
				editPaymentItemButton.setBackground(new java.awt.Color(255,255,255));
				editPaymentItemButton.setAction(getEditPaymentItemAction());
				
				editPaymentItemButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed F4"), "editPaymentItemButton");
				editPaymentItemButton.getActionMap().put("editPaymentItemButton",getEditPaymentItemAction() );
			}
			{
				deletePaymentItemButton = new JButton();
				this.add(deletePaymentItemButton, new AnchorConstraint(813, 811, 856, 710, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deletePaymentItemButton.setText("Delete");
				deletePaymentItemButton.setPreferredSize(new java.awt.Dimension(87, 20));
				deletePaymentItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif")));
				deletePaymentItemButton.setBackground(new java.awt.Color(255,255,255));
				deletePaymentItemButton.setAction(getDeletePaymentItemAction());
				
				deletePaymentItemButton.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "deletePaymentItemButton");
				deletePaymentItemButton.getActionMap().put("deletePaymentItemButton",getDeletePaymentItemAction() );
			}
			{
				processButton = new JButton();
				this.add(processButton, new AnchorConstraint(922, 975, 978, 763, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				processButton.setText("Process Transaction");
				processButton.setPreferredSize(new java.awt.Dimension(182, 28));
				processButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				processButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						try {
							if (Integer.parseInt(InvoiceService.getNextORNumber()) != Integer.parseInt(orNoTxt.getText())){
								resetORNumber();
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Database connection seems to be unstable. Please restart the application.", "Warning", JOptionPane.ERROR_MESSAGE);
							LoggerUtility.getInstance().logStackTrace(e);
						}
						
						if(itemTable.getRowCount() > 0){
							int confirm  = JOptionPane.showConfirmDialog(null, "Are you sure you want to process this transaction?", "Prompt", JOptionPane.INFORMATION_MESSAGE);
							if(confirm == 0){
								logger.info("processing invoice transaction...");
								if(hasEnoughPayment()){
									saveTransaction();
								}
								else{
									if(isPartial()){
										int confirm2  = JOptionPane.showConfirmDialog(null, "You are saving a partial transaction. Are you sure you want to continue?", "Prompt", JOptionPane.INFORMATION_MESSAGE);
										if(confirm2 == 0){
											saveTransaction();
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "This is not a partial transaction. You have an insufficient payment amount.", "Prompt", JOptionPane.ERROR_MESSAGE);
									}
								}
								
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "You haven't put an item in this transaction", "Invalid Transaction", JOptionPane.ERROR_MESSAGE);
						}
						
						
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
				jButton31.setEnabled(true);
				jButton31.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(itemTable.getRowCount() > 0){
							Transaction pendingTransaction = savePendingTransaction();
							mainWindow.getPendingTransactions().add(pendingTransaction);
							try {
								clearInfoValues();
							} catch (Exception e) {
								LoggerUtility.getInstance().logStackTrace(e);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "You haven't put an item in this transaction", "Invalid Transaction", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
			}

			
			
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	public Transaction savePendingTransaction() {
		Transaction pendingTransaction = new Transaction();
		
		Invoice invoice = new Invoice();
		invoice.setOrNo(Long.parseLong(orNoTxt.getText()));
		//TODO: validate numeric fields (use Apache commons)
		if(!invoiceTxt.getText().trim().equals("")){
			invoice.setInvoiceNo(Long.parseLong(invoiceTxt.getText()));
		}
		if(!salesSpecialistTxt.getText().trim().equals("")){
			invoice.setAssistantCode(Integer.parseInt(salesSpecialistTxt.getText()));
		}
		if(!customerTxt.getText().trim().equals("")){
			invoice.setCustomerNo(Integer.parseInt(customerTxt.getText()));
		}
		if(partialChk.isSelected()){
			invoice.setIsPartial(1);
		}
		else{
			invoice.setIsPartial(0);
		}
		invoice.setEncoderCode(Main.getClerkCode());
		invoice.setStoreNo(Integer.parseInt(Main.getStoreCode()));
		
		//InvoiceService.insert(invoice);
		pendingTransaction.setInvoice(invoice);
		
		//invoice item data
		DefaultTableModel itemTableModel = (DefaultTableModel) itemTable.getModel();
		for(int i = 0; i<itemTableModel.getRowCount(); i++){
			InvoiceItem invoiceItem = new InvoiceItem();
			invoiceItem.setDiscountCode(Integer.parseInt(itemTableModel.getValueAt(i, 6).toString()));
			if(itemTableModel.getValueAt(i, 5).toString().equals("Yes")){
				invoiceItem.setIsDeferred(1);
			}
			else{
				invoiceItem.setIsDeferred(0);
			}
			invoiceItem.setOrNo(Long.parseLong(orNoTxt.getText()));
			invoiceItem.setProductCode(itemTableModel.getValueAt(i, 0).toString());
			invoiceItem.setQuantity(Integer.parseInt(itemTableModel.getValueAt(i, 2).toString()));
			invoiceItem.setSellPrice(Double.parseDouble(itemTableModel.getValueAt(i, 4).toString()));
			invoiceItem.setStoreNo(Integer.parseInt(Main.getStoreCode()));
			Product product = ProductService.getProductById(itemTableModel.getValueAt(i, 0).toString());
			invoiceItem.setCost(product.getCost());
			//InvoiceItemService.insert(invoiceItem);
			pendingTransaction.getInvoiceItems().add(invoiceItem);
		}
		
		//payment item data
		DefaultTableModel paymentTableModel = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i <paymentTableModel.getRowCount(); i++){
			PaymentItem paymentItem = new PaymentItem();
			paymentItem.setAmount(Double.parseDouble(paymentTable.getValueAt(i, 2).toString()));
			paymentItem.setCardNo(paymentTable.getValueAt(i, 4).toString());
			paymentItem.setCardType(paymentTable.getValueAt(i, 3).toString());
			paymentItem.setCheckNo(paymentTable.getValueAt(i, 5).toString());
			paymentItem.setGcNo(paymentTable.getValueAt(i, 6).toString());
			paymentItem.setOrNo(Long.parseLong(orNoTxt.getText()));
			paymentItem.setStoreNo(Integer.parseInt(Main.getStoreCode()));
			paymentItem.setPaymentCode(Integer.parseInt(paymentTable.getValueAt(i, 0).toString()));
			paymentItem.setPaymentType(paymentTable.getValueAt(i, 1).toString());
			//PaymentItemService.insert(paymentItem);
			pendingTransaction.getPaymentItems().add(paymentItem);
		}
		return pendingTransaction;
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
		
		Double sellingPrice = Double.valueOf(String.format("%.2f",invoiceItem.getSellPrice())); 
		Double extensionPrice = invoiceItem.getQuantity()*sellingPrice;
		
		model.addRow(new String[]{invoiceItem.getProductCode(),
								  product.getName(),
								  invoiceItem.getQuantity().toString(),
								  String.format("%.2f",product.getSellPrice()),
								  sellingPrice.toString(),
								  (invoiceItem.getIsDeferred()==1)?"Yes":"No",
								  invoiceItem.getDiscountCode().toString(),
								  String.format("%.2f",extensionPrice)});
		updateAmounts();
		updatePaymentAmounts();
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
		
		Double sellingPrice = Double.valueOf(String.format("%.2f",invoiceItem.getSellPrice())); 
		Double extensionPrice = invoiceItem.getQuantity()*sellingPrice;
		int index = getInvoiceItemRow(productCode);
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		model.setValueAt(invoiceItem.getProductCode(), index, 0);
		model.setValueAt(product.getName(), index, 1);
		model.setValueAt(invoiceItem.getQuantity(), index, 2);
		model.setValueAt( String.format("%.2f",product.getSellPrice()), index, 3);
		model.setValueAt(sellingPrice.toString(), index, 4);
		model.setValueAt((invoiceItem.getIsDeferred()==1)?"Yes":"No", index, 5);
		model.setValueAt(invoiceItem.getDiscountCode().toString(), index, 6);
		model.setValueAt( String.format("%.2f",extensionPrice), index, 7);
		updateAmounts();
		updatePaymentAmounts();
	}
	
	public void editInvoiceItems(String discountCode, int[] indices){
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		for(int index: indices){
			Product product = ProductService.getProductById(model.getValueAt(index, 0).toString());
			Double discRate = DiscountService.getDiscRate(Integer.parseInt(discountCode));
			Double sellingPrice = product.getSellPrice() - (product.getSellPrice()*discRate);
			sellingPrice = Double.valueOf(String.format("%.2f",sellingPrice)); 
			Double extensionPrice = Integer.valueOf(model.getValueAt(index, 2).toString())*sellingPrice;
			
			model.setValueAt(sellingPrice.toString(), index, 4);
			model.setValueAt(discountCode, index, 6);
			model.setValueAt( String.format("%.2f",extensionPrice), index, 7);
		}
		updateAmounts();
		updatePaymentAmounts();
	}
	
	public void removeInvoiceItem(){
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		model.removeRow(itemTable.getSelectedRow());
		updateAmounts();
		updatePaymentAmounts();
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
		txtVat.setText(String.format("%.2f", (amount - Double.valueOf(String.format("%.2f", subTotal)))));
		lblAmount.setText(String.format("%.2f", amount));
	}
	
	public void updatePaymentAmounts(){
		
		Double totalPaymentAmount = 0.0d;
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			double paymentAmount =  Double.parseDouble(model.getValueAt(i,2).toString());
			totalPaymentAmount += paymentAmount;
		}
		String amountString = lblAmount.getText();
		double amount = Double.parseDouble(amountString);
		totalPayment.setText(String.format("%.2f", totalPaymentAmount));
		logger.info("amount: "+amount);
		
		//for recording change amount, gift certificate should not be considered for change
		totalPaymentAmount = 0.0d;
		boolean hasGCPayment = false;
		boolean hasCashPayment = false;
		for(int i = 0; i<model.getRowCount(); i++){
			double paymentAmount =  Double.parseDouble(model.getValueAt(i,2).toString());
			if(model.getValueAt(i,1).toString().equals("Gift Certificate")){
				hasGCPayment = true;
			}
			else if(model.getValueAt(i,1).toString().equals("Cash")){
				hasCashPayment = true;
			}
			totalPaymentAmount += paymentAmount;
		}
		
		Double changeAmount = totalPaymentAmount-amount;
		if(hasGCPayment){
			if(!hasCashPayment){
				if(changeAmount > 0) {
					changeAmount = 0.0d;
				}
			}
		}
		logger.info("Total Amount: "+totalPaymentAmount+" - amount: "+amount+" Change Amount: "+changeAmount);
		changeField.setText(String.format("%.2f", changeAmount));
	}
	
	public void addPaymentItem(PaymentItem paymentItem){
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		
		Double totalGCPayments =  getTotalGCPayments();
		//special handling when inserting gc, manipulate gc amount by making it exact with the invoice amount
		if(PaymentItemService.getInstance().getPaymentType(paymentItem.getPaymentCode()).equals("Gift Certificate")){
			Double invoiceAmount = Double.parseDouble(lblAmount.getText());
			Double newTotalGCPayments = totalGCPayments + paymentItem.getAmount();
			if(newTotalGCPayments > invoiceAmount){
				Double amount =  (invoiceAmount - totalGCPayments);
				paymentItem.setAmount(amount);
			}
		}
		
		model.addRow(new String[]{paymentItem.getPaymentCode().toString(),
								  PaymentItemService.getInstance().getPaymentType(paymentItem.getPaymentCode()),
								  paymentItem.getAmount().toString(),
								  paymentItem.getCardType(),
								  paymentItem.getCardNo(),
								  paymentItem.getCheckNo(),
								  paymentItem.getGcNo()
								  });
		updatePaymentAmounts();
	}
	
	public void editPaymentItem(PaymentItem paymentItem, String paymentCode){
		int index = getPaymentItemRow(Integer.parseInt(paymentCode));
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		
		Double totalGCPayments =  getTotalGCPayments();
		//special handling when inserting gc, manipulate gc amount by making it exact with the invoice amount
		if(PaymentItemService.getInstance().getPaymentType(paymentItem.getPaymentCode()).equals("Gift Certificate")){
			Double invoiceAmount = Double.parseDouble(lblAmount.getText());
			Double newTotalGCPayments = totalGCPayments + paymentItem.getAmount();
			if(newTotalGCPayments > invoiceAmount){
				Double amount =  (invoiceAmount - totalGCPayments);
				paymentItem.setAmount(amount);
			}
		}
		
		model.setValueAt(PaymentItemService.getInstance().getPaymentType(paymentItem.getPaymentCode()), index, 1);
		model.setValueAt(paymentItem.getAmount(), index, 2);
		model.setValueAt(paymentItem.getCardType().toString(), index, 3);
		model.setValueAt(paymentItem.getCardNo().toString(), index, 4);
		model.setValueAt(paymentItem.getCheckNo().toString(), index, 5);
		model.setValueAt(paymentItem.getGcNo().toString(), index, 6);
		updatePaymentAmounts();
	}
	
	public void removePaymentItem(){
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		model.removeRow(paymentTable.getSelectedRow());
		updatePaymentAmounts();
	}
	

	public Integer getPaymentItemRow(Integer paymentCode){
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			if(model.getValueAt(i, 0).toString().equals(paymentCode.toString())){
				return i;
			}
		}
		return null;
	}
	
	public Boolean hasCashPayment(Integer paymentCode){
		String paymentName = PaymentItemService.getInstance().getPaymentType(paymentCode);
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			logger.info(model.getValueAt(i, 1).toString());
			if(model.getValueAt(i, 1).toString().equals("Cash") && paymentName.equals("Cash")){
				return true;
			}
		}
		return false;
	}
	
	public Boolean hasCardNo(Integer paymentCode, String cardNo){
		String paymentName = PaymentItemService.getInstance().getPaymentType(paymentCode);
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			logger.info(model.getValueAt(i, 1).toString());
			logger.info(model.getValueAt(i, 4).toString());
			logger.info(cardNo);
			if(model.getValueAt(i, 1).toString().equals("Credit Card") && paymentName.equals("Credit Card") && model.getValueAt(i, 4).equals(cardNo)){
				logger.info("true");
				return true;
			}
		}
		return false;
	}
	
	public Boolean hasCheckNo(Integer paymentCode, String checkNo){
		String paymentName = PaymentItemService.getInstance().getPaymentType(paymentCode);
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			logger.info(model.getValueAt(i, 1).toString());
			logger.info(model.getValueAt(i, 5).toString());
			logger.info(checkNo);
			if(model.getValueAt(i, 1).toString().equals("Bank Check") && paymentName.equals("Bank Check") && model.getValueAt(i, 5).equals(checkNo)){
				logger.info("true");
				return true;
			}
		}
		return false;
	}
	
	public Boolean hasGcNo(Integer paymentCode, String gcNo){
		String paymentName = PaymentItemService.getInstance().getPaymentType(paymentCode);
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			logger.info(model.getValueAt(i, 1).toString());
			logger.info(model.getValueAt(i, 6).toString());
			logger.info(gcNo);
			if(model.getValueAt(i, 1).toString().equals("Gift Certificate") && paymentName.equals("Gift Certificate") && model.getValueAt(i, 6).equals(gcNo)){
				logger.info("true");
				return true;
			}
		}
		return false;
	}
	
	public void setLoginInformation(String userName, String branchName){
		jLabel1.setText("Welcome, "+userName);
		jLabel2.setText(branchName);
	}
	
	public void clearInfoValues() throws Exception{
		//clear items table;
		TableModel jTable1Model = new DefaultTableModel(
				new String[][] {  },
				new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" });
		itemTableScrollPane.setViewportView(itemTable);
		itemTable.setModel(jTable1Model);
		
		//clear payments table
		TableModel jTable2Model = new DefaultTableModel(
			new String[][] { },
			new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
		paymentTableScrollPane.setViewportView(paymentTable);
		paymentTable.setModel(jTable2Model);
		
		//clear text fields
		invoiceTxt.setText("");
		partialChk.setSelected(false);
		salesSpecialistTxt.setText("");
		customerTxt.setText("");
		
		resetORNumber();
		
		
		//update amounts
		updateAmounts();
		updatePaymentAmounts();
	}
	
	public void resetORNumber() throws Exception{
		//reset OR no
		String nextOR = InvoiceService.getNextORNumber();
		if(nextOR == null){
			nextOR = "1";
		}
		orNoTxt.setText(StringUtility.zeroFill(nextOR, 10));
	}
	
	private boolean hasEnoughPayment(){
		//payment item data
		DefaultTableModel paymentTableModel = (DefaultTableModel) paymentTable.getModel();
		Double paymentAmount = 0d;
		for(int i = 0; i <paymentTableModel.getRowCount(); i++){
			paymentAmount += Double.parseDouble(paymentTable.getValueAt(i, 2).toString());
		}
		Double amount = Double.parseDouble(lblAmount.getText());
		if(amount > paymentAmount){
			return false;
		}
		return true;
		
	}
	
	private boolean isPartial(){
		return partialChk.isSelected();
	}
	
	public void saveTransaction(){
		
		Invoice invoice = new Invoice();
		invoice.setOrNo(Long.parseLong(orNoTxt.getText()));
		//TODO: validate numeric fields (use Apache commons)
		if(!invoiceTxt.getText().trim().equals("")){
			invoice.setInvoiceNo(Long.parseLong(invoiceTxt.getText()));
		}
		if(!salesSpecialistTxt.getText().trim().equals("")){
			invoice.setAssistantCode(Integer.parseInt(salesSpecialistTxt.getText()));
		}
		if(!customerTxt.getText().trim().equals("")){
			invoice.setCustomerNo(Integer.parseInt(customerTxt.getText()));
		}
		if(partialChk.isSelected()){
			invoice.setIsPartial(1);
		}
		else{
			invoice.setIsPartial(0);
		}
		invoice.setEncoderCode(Main.getClerkCode());
		invoice.setStoreNo(Integer.parseInt(Main.getStoreCode()));
		invoice.setChangeAmount(Double.parseDouble(changeField.getText()));
		
		InvoiceService.insert(invoice);
		
		List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
		//invoice item data
		DefaultTableModel itemTableModel = (DefaultTableModel) itemTable.getModel();
		for(int i = 0; i<itemTableModel.getRowCount(); i++){
			InvoiceItem invoiceItem = new InvoiceItem();
			invoiceItem.setDiscountCode(Integer.parseInt(itemTableModel.getValueAt(i, 6).toString()));
			if(itemTableModel.getValueAt(i, 5).toString().equals("Yes")){
				invoiceItem.setIsDeferred(1);
			}
			else{
				invoiceItem.setIsDeferred(0);
			}
			invoiceItem.setOrNo(Long.parseLong(orNoTxt.getText()));
			invoiceItem.setProductCode(itemTableModel.getValueAt(i, 0).toString());
			invoiceItem.setQuantity(Integer.parseInt(itemTableModel.getValueAt(i, 2).toString()));
//			invoiceItem.setSellPrice(Double.parseDouble(itemTableModel.getValueAt(i, 4).toString())); // assumed selling price is with disc
			invoiceItem.setStoreNo(Integer.parseInt(Main.getStoreCode()));
			Product product = ProductService.getProductById(itemTableModel.getValueAt(i, 0).toString());
			invoiceItem.setSellPrice(product.getSellPrice());
			invoiceItem.setCost(product.getCost());
			InvoiceItemService.getInstance().insert(invoiceItem);
			invoiceItems.add(invoiceItem);
		}
		
		//payment item data
		List<PaymentItem> paymentItems = new ArrayList<PaymentItem>();
		DefaultTableModel paymentTableModel = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i <paymentTableModel.getRowCount(); i++){
			PaymentItem paymentItem = new PaymentItem();
			paymentItem.setAmount(Double.parseDouble(paymentTable.getValueAt(i, 2).toString()));
			paymentItem.setCardNo(paymentTable.getValueAt(i, 4).toString());
			paymentItem.setCardType(paymentTable.getValueAt(i, 3).toString());
			paymentItem.setCheckNo(paymentTable.getValueAt(i, 5).toString());
			paymentItem.setGcNo(paymentTable.getValueAt(i, 6).toString());
			paymentItem.setOrNo(Long.parseLong(orNoTxt.getText()));
			paymentItem.setStoreNo(Integer.parseInt(Main.getStoreCode()));
			paymentItem.setPaymentCode(Integer.parseInt(paymentTable.getValueAt(i, 0).toString()));
			paymentItem.setPaymentType(paymentTable.getValueAt(i, 1).toString());
//			PaymentItemService.getInstance().insert(paymentItem);
			paymentItems.add(paymentItem);
		}
		
		List<PaymentItem> calculatedPaymentItems = PaymentCalculatorUtility.getInstance().getCalculatedPaymentItems(paymentItems,Double.parseDouble(lblAmount.getText()));
		for(PaymentItem paymentItem: calculatedPaymentItems){
			logger.info("Inserting payment item");
			
			//CHANGE REQUEST 2009-01-20 FILTER GC ITEMS AND  INSERT IT INTO GC_ITEM TABLE
			if(paymentItem.getPaymentCode().equals(4)){
				GCItem gcItem = new GCItem();
				gcItem.setOrNo(paymentItem.getOrNo());
				gcItem.setStoreNo(paymentItem.getStoreNo());
				gcItem.setAmount(paymentItem.getAmount());
				gcItem.setGcNo(paymentItem.getGcNo());
				GCItemService.getInstance().insert(gcItem);
			}
			else{
				PaymentItemService.getInstance().insert(paymentItem);
			}
		}
		
		//GC ITEM Population
		List<GCItem> gcItems = GCItemService.getInstance().filterToGCItemList(calculatedPaymentItems);
		
		JOptionPane.showMessageDialog(null, "Successfully processed transaction", "Prompt", JOptionPane.INFORMATION_MESSAGE);
		
		//MAKE RECEIPT
		ReceiptPanel receiptPanel = new ReceiptPanel(invoice, invoiceItems, paymentItems, gcItems, changeField.getText());
		
		ValidateReceipt validateReceiptDialog = new ValidateReceipt(Main.getInst(), receiptPanel);
		validateReceiptDialog.setLocationRelativeTo(null);
		validateReceiptDialog.setVisible(true);
		
		
		//Additional check if or_no is not updated
		try {
			clearInfoValues();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database connection seems to be unstable. Please restart the application.", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private AbstractAction getAddInvoiceItemAction() {
		if(addInvoiceItemAction == null) {
			addInvoiceItemAction = new AbstractAction("Add", new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png"))) {
				public void actionPerformed(ActionEvent evt) {
					ProductDialog dialog = ProductDialog.getProductDialog(Main.getInst(),InvoicePanel.this,"add");
					dialog.clearProductInformation();
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			};
		}
		return addInvoiceItemAction;
	}
	
	
	public void resumeTransaction(Transaction transaction) {
		orNoTxt.setText(transaction.getInvoice().getOrNo().toString());
		invoiceTxt.setText(transaction.getInvoice().getInvoiceNo().toString());
		salesSpecialistTxt.setText(transaction.getInvoice().getAssistantCode().toString());
		customerTxt.setText(transaction.getInvoice().getCustomerNo().toString());
		partialChk.setSelected(transaction.getInvoice().getIsPartial() == 1);
		Main.setClerkCode(transaction.getInvoice().getEncoderCode());
		
		DefaultTableModel itemTableModel = (DefaultTableModel) itemTable.getModel();
		for(InvoiceItem invoiceItem : transaction.getInvoiceItems()) {
			Object[] invoiceItemData = new Object[8];
			Product product = ProductService.getProductById(invoiceItem.getProductCode());
			invoiceItemData[0] = invoiceItem.getProductCode();
			invoiceItemData[1] = product.getName();
			invoiceItemData[2] = invoiceItem.getQuantity();
			invoiceItemData[3] = product.getSellPrice();
			invoiceItemData[4] = invoiceItem.getSellPrice();
			invoiceItemData[5] = (invoiceItem.getIsDeferred() == 1)?"Yes":"No";
			invoiceItemData[6] = invoiceItem.getDiscountCode();
			invoiceItemData[7] = invoiceItem.getQuantity() * invoiceItem.getSellPrice();
			itemTableModel.addRow(invoiceItemData);
		}
		DefaultTableModel paymentTableModel = (DefaultTableModel) paymentTable.getModel();
		for(PaymentItem paymentItem : transaction.getPaymentItems()) {
			Object[] paymentItemData = new Object[7];
			paymentItemData[0] = paymentItem.getPaymentCode();
			paymentItemData[1] = paymentItem.getPaymentType();
			paymentItemData[2] = paymentItem.getAmount();
			paymentItemData[3] = paymentItem.getCardNo();
			paymentItemData[4] = paymentItem.getCardType();
			paymentItemData[5] = paymentItem.getCheckNo();
			paymentItemData[6] = paymentItem.getGcNo();
			paymentTableModel.addRow(paymentItemData);
		}
		updateAmounts();
	}
	
	private AbstractAction getEditItemAction() {
		if(editItemAction1 == null) {
			editItemAction1 = new AbstractAction("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png"))) {
				public void actionPerformed(ActionEvent evt) {
					try{
						
						if(itemTable.getSelectedRows().length == 1)
						{
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
						else{
							int[] prodCodeIndices = itemTable.getSelectedRows();
							String prodCodes = "";
							int discountCode = 0;
							for(int i=1; i<=prodCodeIndices.length; i++){
								
								prodCodes+=itemTable.getValueAt(prodCodeIndices[i-1], 0).toString();
								
								if(i != prodCodeIndices.length){
									prodCodes+=",";
								}
								
								discountCode = Integer.parseInt( itemTable.getValueAt(prodCodeIndices[i-1], 6).toString());
							}
							
							ProductDiscountDialog productDiscountDialog = new ProductDiscountDialog(Main.getInst(), InvoicePanel.this);
							productDiscountDialog.setProductCode(prodCodes);
							productDiscountDialog.setDiscount(discountCode);
							productDiscountDialog.setIndices(prodCodeIndices);
							
							
							productDiscountDialog.setLocationRelativeTo(null);
							productDiscountDialog.setVisible(true);
							
						}
					}
					catch(ArrayIndexOutOfBoundsException e){
						JOptionPane.showMessageDialog(null, "Please select item from the list", "Prompt", JOptionPane.ERROR_MESSAGE);
					}
				}
			};
		}
		return editItemAction1;
	}
	
	private AbstractAction getDeleteInvoiceItemAction() {
		if(deleteInvoiceItemAction == null) {
			deleteInvoiceItemAction = new AbstractAction("Delete", new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif"))) {
				public void actionPerformed(ActionEvent evt) {
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these selected items?", "Prompt", JOptionPane.INFORMATION_MESSAGE);
					if(confirm == 0){
						DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
						int[] selectedRows = itemTable.getSelectedRows();
						String[] productCodes = new String[selectedRows.length];
						for(int i=0; i<selectedRows.length; i++){
							productCodes[i] = itemTable.getValueAt(selectedRows[i], 0).toString();
						}
						for(String s: productCodes){
							int index = getInvoiceItemIndex(s);
							model.removeRow(index);
						}
						updateAmounts();
						updatePaymentAmounts();
					}
				}
			};
		}
		return deleteInvoiceItemAction;
	}
	
	private Integer getInvoiceItemIndex(String productCode){
		for(int i =0; i<itemTable.getRowCount(); i++){
			if(itemTable.getValueAt(i, 0).toString().equals(productCode)){
				return i;
			}
		}
		return null;
	}
	
	private AbstractAction getAddPaymentItemAction() {
		if(addPaymentItemAction == null) {
			addPaymentItemAction = new AbstractAction("Add", new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png"))) {
				public void actionPerformed(ActionEvent evt) {
					PaymentDialog dialog = PaymentDialog.getPaymentDialog(Main.getInst(), InvoicePanel.this, "add");
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			};
		}
		return addPaymentItemAction;
	}
	
	private AbstractAction getEditPaymentItemAction() {
		if(editPaymentItemAction == null) {
			editPaymentItemAction = new AbstractAction("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png"))) {
				public void actionPerformed(ActionEvent evt) {
					try{
						String paymentCode = paymentTable.getValueAt(paymentTable.getSelectedRow(), 0).toString();
						String paymentType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 1).toString();
						String paymentAmount = paymentTable.getValueAt(paymentTable.getSelectedRow(), 2).toString();
						String creditCardType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 3).toString();
						String creditCardNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 4).toString();
						String bankCheckNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 5).toString();
						String gcNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 6).toString();
						
						PaymentDialog dialog = PaymentDialog.getPaymentDialog(Main.getInst(),InvoicePanel.this,paymentCode);
						
						dialog.setAmount(paymentAmount);
						dialog.setPaymentType(paymentType);
						dialog.setCreditType(creditCardType);
						dialog.setCreditCard(creditCardNum);
						dialog.setBankCheck(bankCheckNum);
						dialog.setGiftCheck(gcNum);
						
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
					catch(ArrayIndexOutOfBoundsException e){
						JOptionPane.showMessageDialog(null, "Please select item from the list", "Prompt", JOptionPane.ERROR_MESSAGE);
					}
				}
			};
		}
		return editPaymentItemAction;
	}
	
	private AbstractAction getDeletePaymentItemAction() {
		if(deletePaymentItemAction == null) {
			deletePaymentItemAction = new AbstractAction("Delete", new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif"))) {
				public void actionPerformed(ActionEvent evt) {
					try{
						removePaymentItem();
					}
					catch(ArrayIndexOutOfBoundsException e){
						JOptionPane.showMessageDialog(null, "Please select item from the list", "Prompt", JOptionPane.ERROR_MESSAGE);
					}

				}
			};
		}
		return deletePaymentItemAction;
	}

	public JButton getProcessButton() {
		return processButton;
	}

	@Override
	public Double getTotalPayment() {
		Double totalPayment = 0.0d;
		for(int i =0; i<paymentTable.getRowCount(); i++){
			totalPayment += Double.valueOf(paymentTable.getValueAt(i, 2).toString());
		}
		return totalPayment;
	}
	
	public Double getTotalGCPayments(){
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		Double gcAmountTotal = 0.0d;
		for(int i = 0; i<model.getRowCount(); i++){
			
			if(model.getValueAt(i, 1).toString().equals("Gift Certificate")){
				gcAmountTotal += Double.parseDouble(model.getValueAt(i, 2).toString());
			}
		}
		return gcAmountTotal;
	}

	@Override
	public Double getAmountDue() {
		return Double.parseDouble(lblAmount.getText());
	}

	@Override
	public boolean isGCGreaterThanAmount( Integer paymentCode,  Double gcAmount) {
		
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		Double gcAmountTotal = 0.0d;
		for(int i = 0; i<model.getRowCount(); i++){
			
			if(model.getValueAt(i, 1).toString().equals("Gift Certificate")){
				gcAmountTotal += Double.parseDouble(model.getValueAt(i, 2).toString());
			}
		}
		Double amount = Double.parseDouble(lblAmount.getText());
		logger.debug("GC Total Amount: "+gcAmountTotal+" amount: "+amount);
		if(gcAmountTotal >= amount){
			return true;
		}
		return false;
	}
	
	
}
