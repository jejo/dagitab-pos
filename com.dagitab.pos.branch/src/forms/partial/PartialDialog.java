package forms.partial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import util.TableUtility;
import bus.InvoiceItemService;
import bus.PaymentItemService;
import bus.VatService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import domain.Invoice;

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
	private JScrollPane invoiceItemScrollPane;
	private JTable paymentTable;
	private JLabel totalAmountLabel;
	private JScrollPane paymentTableScrollPane;
	private JButton processButton;
	private JButton jButton3;
	private JButton jButton2;
	private JButton addPaymentButton;
	private JLabel jLabel15;
	private JLabel jLabel16;
	private JTextField changeTextField;
	private JTextField timeTextField;
	private JLabel jLabel5;
	private JButton cancelButton;
	private JTextField totalPaymentTextField;
	private JTextField vatTextField;
	private JTextField subTotalTextField;
	private JLabel changeLabel;
	private JLabel totalPaymentLabel;
	private JLabel vatLabel;
	private JLabel subTotalLabel;
	private JLabel amountDueLabel;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JTextField jTextField2;
	private JTextField dateTextField;
	private Double totalAmount;
	private JTable itemTable;
	private Invoice invoice;
	private static PartialDialog partialDialog;
	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		
	}
	
	public PartialDialog(JFrame frame,Invoice invoice) {
		super(frame);
		this.invoice = invoice;
		initGUI();
		updateAmounts();
		refreshItemTable();
		refreshPaymentTable();
	}

	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			totalAmount = 0.0d;
			
			this.setTitle("Partial Transaction");
			this.setModal(true);
			{
				cancelButton = new JButton();
				getContentPane().add(cancelButton, new AnchorConstraint(896, 465, 948, 323, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				cancelButton.setText("Cancel");
				cancelButton.setPreferredSize(new java.awt.Dimension(112, 28));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						PartialDialog.this.dispose();
					}
				});
			}
			{
				processButton = new JButton();
				getContentPane().add(processButton, new AnchorConstraint(896, 974, 948, 814, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				processButton.setText("Process");
				processButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				processButton.setPreferredSize(new java.awt.Dimension(126, 28));
				processButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
					}
				});
			}
			{
				addPaymentButton = new JButton();
				getContentPane().add(addPaymentButton, new AnchorConstraint(782, 604, 833, 534, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				addPaymentButton.setText("Add");
				addPaymentButton.setPreferredSize(new java.awt.Dimension(56, 28));
				addPaymentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
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
					subTotalTextField = new JTextField();
					jPanel2.add(subTotalTextField, new AnchorConstraint(327, 975, 408, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					subTotalTextField.setPreferredSize(new java.awt.Dimension(98, 21));
					subTotalTextField.setEditable(false);
					subTotalTextField.setHorizontalAlignment(SwingConstants.RIGHT);
					subTotalTextField.setText("0.00");
				}
				{
					amountDueLabel = new JLabel();
					jPanel2.add(amountDueLabel, new AnchorConstraint(29, 818, 137, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					amountDueLabel.setText("Amount Due");
					amountDueLabel.setFont(new java.awt.Font("Tahoma",1,22));
					amountDueLabel.setPreferredSize(new java.awt.Dimension(175, 28));
				}
				{
					subTotalLabel = new JLabel();
					jPanel2.add(subTotalLabel, new AnchorConstraint(300, 504, 408, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					subTotalLabel.setText("Sub Total");
					subTotalLabel.setFont(new java.awt.Font("Tahoma",1,14));
					subTotalLabel.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					vatLabel = new JLabel();
					jPanel2.add(vatLabel, new AnchorConstraint(436, 504, 544, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					vatLabel.setText("12% VAT");
					vatLabel.setFont(new java.awt.Font("Tahoma",1,14));
					vatLabel.setPreferredSize(new java.awt.Dimension(105, 28));
				}
				{
					totalPaymentLabel = new JLabel();
					jPanel2.add(totalPaymentLabel, new AnchorConstraint(579, 538, 699, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					totalPaymentLabel.setText("Total Payment");
					totalPaymentLabel.setFont(new java.awt.Font("Tahoma",1,14));
					totalPaymentLabel.setPreferredSize(new java.awt.Dimension(106, 31));
				}
				{
					changeLabel = new JLabel();
					jPanel2.add(changeLabel, new AnchorConstraint(730, 509, 839, 35, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					changeLabel.setText("Change");
					changeLabel.setFont(new java.awt.Font("Tahoma",1,14));
					changeLabel.setPreferredSize(new java.awt.Dimension(99, 28));
				}
				{
					totalAmountLabel = new JLabel();
					jPanel2.add(totalAmountLabel, new AnchorConstraint(137, 818, 246, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					totalAmountLabel.setText(totalAmount.toString());
					totalAmountLabel.setFont(new java.awt.Font("Tahoma",1,22));
					totalAmountLabel.setPreferredSize(new java.awt.Dimension(175, 28));
					
				}
				{
					vatTextField = new JTextField();
					jPanel2.add(vatTextField, new AnchorConstraint(436, 975, 517, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					vatTextField.setEditable(false);
					vatTextField.setPreferredSize(new java.awt.Dimension(98, 21));
					vatTextField.setHorizontalAlignment(SwingConstants.RIGHT);
					vatTextField.setText("0.00");
				}
				{
					totalPaymentTextField = new JTextField();
					jPanel2.add(totalPaymentTextField, new AnchorConstraint(598, 975, 680, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					totalPaymentTextField.setEditable(false);
					totalPaymentTextField.setPreferredSize(new java.awt.Dimension(98, 21));
					totalPaymentTextField.setHorizontalAlignment(SwingConstants.RIGHT);
					totalPaymentTextField.setText("0.00");
				}
				{
					changeTextField = new JTextField();
					jPanel2.add(changeTextField, new AnchorConstraint(734, 975, 815, 535, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					changeTextField.setEditable(false);
					changeTextField.setPreferredSize(new java.awt.Dimension(98, 21));
					changeTextField.setHorizontalAlignment(SwingConstants.RIGHT);
					changeTextField.setText("0.00");
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
					jTextField2.setText(invoice.getOrNo().toString());
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
					jPanel1.add(jLabel2, new AnchorConstraint(202, 409, 322, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("OR Number");
					jLabel2.setPreferredSize(new java.awt.Dimension(79, 21));
					jLabel2.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					dateTextField = new JTextField();
					jPanel1.add(dateTextField, new AnchorConstraint(482, 943, 602, 410, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					dateTextField.setPreferredSize(new java.awt.Dimension(119, 21));
					dateTextField.setEditable(false);
					dateTextField.setText(invoice.getTransactionDate().split(" ")[0]);
				}
				{
					jLabel5 = new JLabel();
					jPanel1.add(jLabel5, new AnchorConstraint(642, 400, 762, 35, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel5.setText("Time");
					jLabel5.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel5.setPreferredSize(new java.awt.Dimension(77, 21));
				}
				{
					timeTextField = new JTextField();
					jPanel1.add(timeTextField, new AnchorConstraint(642, 940, 762, 409, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					timeTextField.setEditable(false);
					timeTextField.setPreferredSize(new java.awt.Dimension(112, 21));
					timeTextField.setText(invoice.getTransactionDate().split(" ")[1]);
				}
			}
			{
				invoiceItemScrollPane = new JScrollPane();
				getContentPane().add(invoiceItemScrollPane, new AnchorConstraint(156, 974, 455, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				invoiceItemScrollPane.setPreferredSize(new java.awt.Dimension(518, 161));
				{
					TableModel jTable1Model = new DefaultTableModel( new String[][] {  }, new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" });
						itemTable = new JTable();
						invoiceItemScrollPane.setViewportView(itemTable);
						itemTable.setModel(jTable1Model);
				}
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(18, 353, 89, 19, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Partial Transaction");
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
				paymentTableScrollPane = new JScrollPane();
				getContentPane().add(paymentTableScrollPane, new AnchorConstraint(520, 974, 767, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				paymentTableScrollPane.setPreferredSize(new java.awt.Dimension(518, 133));
				{
					TableModel paymentTableModel = new DefaultTableModel( new String[][] { }, new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
					paymentTable = new JTable();
					paymentTableScrollPane.setViewportView(paymentTable);
					paymentTable.setModel(paymentTableModel);
				}
			
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(780, 693, 832, 623, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Edit");
				jButton2.setPreferredSize(new java.awt.Dimension(56, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
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
						
					}
				});
			}
			this.setSize(806, 573);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void refreshItemTable(){
		ResultSet rs = InvoiceItemService.fetchPartialInvoiceItem(invoice.getOrNo().toString());
		TableUtility.fillTable(itemTable, rs, new String[]{"Product Code", "Product Name", "Quantity", "Current Price", "Selling Price", "Deferred", "Disc Code", "Extension"});
	}
	
	private void updateAmounts(){
		Double amount = InvoiceItemService.getInvoiceItemAmount(invoice.getOrNo());
		Double vat = VatService.getVatRate();
		Double subTotal = amount/vat;
		totalAmountLabel.setText(String.format("%.2f", amount));
		subTotalTextField.setText(String.format("%.2f", subTotal));
		vatTextField.setText(String.format("%.2f", new Double(amount-subTotal)));
		totalPaymentTextField.setText(String.format("%.2f",PaymentItemService.getTotalPaymentAmount(invoice.getOrNo())));
	}
	
	private void refreshPaymentTable(){
		ResultSet rs = PaymentItemService.findPaymentItems(invoice.getOrNo());
		TableUtility.fillTable(paymentTable, rs, new String[]{"Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number"});
	}
	

}
