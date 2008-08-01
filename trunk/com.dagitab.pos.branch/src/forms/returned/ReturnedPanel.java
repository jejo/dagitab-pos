package forms.returned;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
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
import bus.ReturnReasonService;
import bus.VatService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import domain.Invoice;
import domain.InvoiceItem;
import domain.PaymentItem;
import domain.Product;
import domain.ReturnReason;
import forms.PaymentDialog;
import forms.ProductDialog;
import forms.interfaces.Payments;


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
public class ReturnedPanel extends javax.swing.JPanel implements Payments {

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

	private JButton processTransactionButton;
	private JButton addPaymentItemButton;
	private JButton addReplacedItemButton;
	private JScrollPane paymentScrollPane;
	private JTable paymentTable;
	private JScrollPane replacementItemScrollPane;
	private JTable replacementItemsTable;
	private JPanel pinkPanel;
	private JTextField subTotalTextField;
	private JLabel amnountDueLabel;
	private JLabel amountLabel;
	private JLabel subTotalLabel;
	private JLabel vatAmountLabel;
	private JLabel totalPaymentLabel;
	private JLabel changeLabel;
	private JTextField vatAmountTextField;
	private JTextField totalPaymentTextField;
	private JTextField changeTextField;
	private JPanel bluePanel;
	private JPanel greenPanel;
	private JTextField orTextField;
	private JTable returnedItemsTable;
	private JLabel orNoLabel;
	private JLabel invoiceNoLabel;
	private JTextField invoiceNoTextField;
	private JLabel replacementsLabel;
	private JLabel paymentsLabel;
	private JButton editReplacedItemButton;
	private JButton deleteReplacedItemButton;
	private JScrollPane returnedItemsScrollPane;
	private AbstractAction deletePaymentItemAction;
	private AbstractAction editPaymentItemAction;
	private AbstractAction addPaymentItemAction;
	private AbstractAction deleteReplacementItemsAction;
	private AbstractAction editReplacementItemsAction;
	private AbstractAction addReplacementItemAction;
	private AbstractAction deleteReturnedItemsAction;
	private AbstractAction addReturnItemAction;
	private JLabel jLabel30;
	private JButton editPaymentItemButton;
	private JButton deletePaymentItemButton;
	private JButton deleteReturnItemButton;
	private JButton editReturnItemButton;
	private JButton addReturnItemButton;
	private JTextField returnedORTextField;
	private JLabel returnedORLabel;

	public ReturnedPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(859, 503));
			AnchorLayout jPanel5Layout = new AnchorLayout();
			this.setLayout(jPanel5Layout);
			this.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			this.setBackground(new java.awt.Color(255, 255, 255));
			{
				deleteReturnItemButton = new JButton();
				this.add(deleteReturnItemButton, new AnchorConstraint(267, 765, 311, 673, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deleteReturnItemButton.setText("Delete");
				deleteReturnItemButton.setPreferredSize(new java.awt.Dimension(79, 22));
				deleteReturnItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif")));
				deleteReturnItemButton.setBackground(new java.awt.Color(244,244,244));
				deleteReturnItemButton.setAction(getDeleteReturnedItemsAction());
			}
			{
				editReturnItemButton = new JButton();
				this.add(editReturnItemButton, new AnchorConstraint(267, 666, 311, 595, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				editReturnItemButton.setText("Edit");
				editReturnItemButton.setPreferredSize(new java.awt.Dimension(61, 22));
				editReturnItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png")));
				editReturnItemButton.setBackground(new java.awt.Color(244,244,244));
			}
			{
				addReturnItemButton = new JButton();
				this.add(addReturnItemButton, new AnchorConstraint(267, 588, 311, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				addReturnItemButton.setText("Add");
				addReturnItemButton.setPreferredSize(new java.awt.Dimension(61, 22));
				addReturnItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png")));
				addReturnItemButton.setBackground(new java.awt.Color(244,244,244));
				addReturnItemButton.setAction(getAddReturnItemAction());
			}
			{
				greenPanel = new JPanel();
				AnchorLayout jPanel1Layout = new AnchorLayout();
				this.add(greenPanel, new AnchorConstraint(243, 278, 386, 7, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				greenPanel.setPreferredSize(new java.awt.Dimension(233, 72));
				greenPanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				greenPanel.setBackground(new java.awt.Color(231,255,206));
				greenPanel.setLayout(jPanel1Layout);
				{
					returnedORLabel = new JLabel();
					greenPanel.add(returnedORLabel, new AnchorConstraint(180, 456, 353, 58, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					returnedORLabel.setText("Returned OR");
					returnedORLabel.setFont(new java.awt.Font("Tahoma",1,12));
					returnedORLabel.setPreferredSize(new java.awt.Dimension(92, 13));
				}
				{
					returnedORTextField = new JTextField();
					greenPanel.add(returnedORTextField, new AnchorConstraint(433, 945, 726, 58, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					returnedORTextField.setPreferredSize(new java.awt.Dimension(205, 22));
				}
			}
			{
				processTransactionButton = new JButton();
				this.add(processTransactionButton, new AnchorConstraint(921, 983, 977, 782, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				processTransactionButton.setText("Process Transaction");
				processTransactionButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				processTransactionButton.setPreferredSize(new java.awt.Dimension(172, 28));
				processTransactionButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

					}
				});
			}
			{
				addPaymentItemButton = new JButton();
				this.add(addPaymentItemButton, new AnchorConstraint(839, 587, 885, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				addPaymentItemButton.setText("Add");
				addPaymentItemButton.setPreferredSize(new java.awt.Dimension(60, 23));
				addPaymentItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png")));
				addPaymentItemButton.setBackground(new java.awt.Color(244,244,244));
				addPaymentItemButton.setAction(getAddPaymentItemAction());

			}
			{
				addReplacedItemButton = new JButton();
				this.add(addReplacedItemButton, new AnchorConstraint(551, 587, 593, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				addReplacedItemButton.setText("Add");
				addReplacedItemButton.setPreferredSize(new java.awt.Dimension(60, 21));
				addReplacedItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png")));
				addReplacedItemButton.setBackground(new java.awt.Color(244,244,244));
				addReplacedItemButton.setAction(getAddReplacementItemAction());
				
			}
			{
				paymentScrollPane = new JScrollPane();
				this.add(paymentScrollPane, new AnchorConstraint(637, 983, 837, 309, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				paymentScrollPane.setPreferredSize(new java.awt.Dimension(579, 101));
				paymentScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					TableModel paymentTableModel = new DefaultTableModel(
						new String[][] {},
						new String[] { "Payment Code","Payment Type", "Amount","Credit Card Type","Credit Card No", "Bank Check No","Gift Certificate Number" });
					paymentTable = new JTable(){
						@Override
						public boolean isCellEditable(int row,int column) {
							return false;
						}
					};
					paymentScrollPane.setViewportView(paymentTable);
					paymentTable.setModel(paymentTableModel);
					paymentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					paymentTable.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "paymentTable");
					paymentTable.getActionMap().put("paymentTable",getDeletePaymentItemAction());
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
								
								
								PaymentDialog dialog = PaymentDialog.getPaymentDialog(Main.getInst(),ReturnedPanel.this,paymentCode);
								
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
				}
			}
			{
				replacementItemScrollPane = new JScrollPane();
				this.add(replacementItemScrollPane, new AnchorConstraint(350, 983, 549, 309, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				replacementItemScrollPane.setPreferredSize(new java.awt.Dimension(579, 100));
				replacementItemScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					TableModel replacementTableModel = new DefaultTableModel(
						new String[][] {},
						new String[] { "Product Code","Product Name", "Quantity","Current Price", "Selling Price"," Deferred", "Disc Code","Extension" });
					replacementItemsTable = new JTable(){
						@Override
						public boolean isCellEditable(int row,int column) {
							return false;
						}
					};
					replacementItemsTable.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "replacementItemsTable");
					replacementItemsTable.getActionMap().put("replacementItemsTable",getDeleteReplacementItemsAction() );
					replacementItemsTable.addMouseListener(new MouseAdapter(){
						 public void mouseClicked(MouseEvent e){
							 if (e.getClickCount() == 2){
					    	  	String productCode = replacementItemsTable.getValueAt(replacementItemsTable.getSelectedRow(), 0).toString();
								String quantity = replacementItemsTable.getValueAt(replacementItemsTable.getSelectedRow(), 2).toString();
								int discountCode = Integer.parseInt( replacementItemsTable.getValueAt(replacementItemsTable.getSelectedRow(), 6).toString());
								String deferred = replacementItemsTable.getValueAt(replacementItemsTable.getSelectedRow(), 5).toString();
								
								ProductDialog dialog = ProductDialog.getProductDialog(Main.getInst(),ReturnedPanel.this,productCode);
								dialog.setProductCode(productCode);
								dialog.setQuantity(quantity);
								dialog.setDiscount(discountCode);
								dialog.setDeferredValue(deferred);
								
								dialog.setLocationRelativeTo(null);
								dialog.setVisible(true);
					         }
					      }
					});
					replacementItemScrollPane.setViewportView(replacementItemsTable);
					replacementItemsTable.setModel(replacementTableModel);
				}
			}
			{
				pinkPanel = new JPanel();
				AnchorLayout jPanel11Layout = new AnchorLayout();
				pinkPanel.setLayout(jPanel11Layout);
				this.add(pinkPanel, new AnchorConstraint(410, 277, 833, 7, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				pinkPanel.setPreferredSize(new java.awt.Dimension(232, 213));
				pinkPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),1,false));
				pinkPanel.setBackground(new java.awt.Color(255,128,255));
				{
					subTotalTextField = new JTextField();
					pinkPanel.add(subTotalTextField,new AnchorConstraint(302,941,402,547,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL));
					subTotalTextField.setText("0.00");
					subTotalTextField.setPreferredSize(new java.awt.Dimension(91,21));
					subTotalTextField.setEditable(false);
					subTotalTextField.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					amnountDueLabel = new JLabel();
					pinkPanel.add(amnountDueLabel, new AnchorConstraint(35,820,135,32,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL));
					amnountDueLabel.setText("Amount Due");
					amnountDueLabel.setFont(new java.awt.Font("Tahoma",1,22));
					amnountDueLabel.setPreferredSize(new java.awt.Dimension(182,21));
				}
				{
					amountLabel = new JLabel();
					pinkPanel.add(amountLabel, new AnchorConstraint(135,820,235,32,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL));
					amountLabel.setText("0.00");
					amountLabel.setFont(new java.awt.Font("Tahoma",1,22));
					amountLabel.setPreferredSize(new java.awt.Dimension(182,21));
				}
				{
					subTotalLabel = new JLabel();
					pinkPanel.add(subTotalLabel, new AnchorConstraint(302,487,402,32,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL));
					subTotalLabel.setText("Sub Total");
					subTotalLabel.setFont(new java.awt.Font("Tahoma",1,14));
					subTotalLabel.setPreferredSize(new java.awt.Dimension(105,21));
				}
				{
					vatAmountLabel = new JLabel();
					pinkPanel.add(vatAmountLabel, new AnchorConstraint(435,487,535,32,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL));
					vatAmountLabel.setText(VatService.getVatAmount()+"% VAT");
					vatAmountLabel.setFont(new java.awt.Font("Tahoma",1,14));
					vatAmountLabel.setPreferredSize(new java.awt.Dimension(105,21));
				}
				{
					totalPaymentLabel = new JLabel();
					pinkPanel.add(totalPaymentLabel, new AnchorConstraint(602,487,702,32,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL));
					totalPaymentLabel.setText("Total Payment");
					totalPaymentLabel.setFont(new java.awt.Font("Tahoma",1,14));
					totalPaymentLabel.setPreferredSize(new java.awt.Dimension(105,21));
				}
				{
					changeLabel = new JLabel();
					pinkPanel.add(changeLabel, new AnchorConstraint(735,487,835,32,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL,
																AnchorConstraint.ANCHOR_REL));
					changeLabel.setText("Change");
					changeLabel.setFont(new java.awt.Font("Tahoma",1,14));
					changeLabel.setPreferredSize(new java.awt.Dimension(105,21));
				}
				{
					vatAmountTextField = new JTextField();
					pinkPanel.add(vatAmountTextField,new AnchorConstraint(435,941,535,547,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL));
					vatAmountTextField.setText("0.00");
					vatAmountTextField.setPreferredSize(new java.awt.Dimension(91,21));
					vatAmountTextField.setEditable(false);
					vatAmountTextField.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					totalPaymentTextField = new JTextField();
					pinkPanel.add(totalPaymentTextField,new AnchorConstraint(602,941,702,547,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL));
					totalPaymentTextField.setText("0.00");
					totalPaymentTextField.setPreferredSize(new java.awt.Dimension(91,21));
					totalPaymentTextField.setEditable(false);
					totalPaymentTextField.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					changeTextField = new JTextField();
					pinkPanel.add(changeTextField,new AnchorConstraint(735,941,835,547,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL,
																	AnchorConstraint.ANCHOR_REL));
					changeTextField.setText("0.00");
					changeTextField.setPreferredSize(new java.awt.Dimension(91,21));
					changeTextField.setEditable(false);
					changeTextField.setHorizontalAlignment(SwingConstants.RIGHT);
				}
			}
			{
				bluePanel = new JPanel();
				AnchorLayout jPanel10Layout = new AnchorLayout();
				bluePanel.setLayout(jPanel10Layout);
				this.add(bluePanel, new AnchorConstraint(70, 276, 219, 7, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				bluePanel.setPreferredSize(new java.awt.Dimension(231, 75));
				bluePanel.setBackground(new java.awt.Color(164,222,251));
				bluePanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),1,false));
				{
					orTextField = new JTextField();
					bluePanel.add(orTextField, new AnchorConstraint(105, 958, 401, 413, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					orTextField.setEditable(false);
					orTextField.setPreferredSize(new java.awt.Dimension(126, 21));
					String nextOR = InvoiceService.getNextORNumber();
					if(nextOR == null){
						nextOR = "1";
					}
					orTextField.setText(StringUtility.zeroFill(nextOR, 10));

				}
				{
					orNoLabel = new JLabel();
					bluePanel.add(orNoLabel, new AnchorConstraint(176, 374, 359, 58, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					orNoLabel.setText("OR Number");
					orNoLabel.setFont(new java.awt.Font("Tahoma",1,12));
					orNoLabel.setPreferredSize(new java.awt.Dimension(73, 13));
				}
				{
					invoiceNoLabel = new JLabel();
					bluePanel.add(invoiceNoLabel, new AnchorConstraint(598, 396, 753, 58, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					invoiceNoLabel.setText("Invoice No");
					invoiceNoLabel.setFont(new java.awt.Font("Tahoma",1,12));
					invoiceNoLabel.setPreferredSize(new java.awt.Dimension(78, 11));
				}
				{
					invoiceNoTextField = new JTextField();
					bluePanel.add(invoiceNoTextField, new AnchorConstraint(514, 958, 809, 413, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					invoiceNoTextField.setPreferredSize(new java.awt.Dimension(126, 21));
				}
				
			}
			{
				replacementsLabel = new JLabel();
				this.add(replacementsLabel, new AnchorConstraint(297, 465, 354, 309, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				replacementsLabel.setText("Replacements");
				replacementsLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/items.png")));
				replacementsLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
				replacementsLabel.setPreferredSize(new java.awt.Dimension(134, 29));
			}
			{
				paymentsLabel = new JLabel();
				this.add(paymentsLabel, new AnchorConstraint(585, 432, 637, 304, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				paymentsLabel.setText("Payments");
				paymentsLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/payments.png")));
				paymentsLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
				paymentsLabel.setPreferredSize(new java.awt.Dimension(110, 26));
			}
			{
				editReplacedItemButton = new JButton();
				this.add(editReplacedItemButton, new AnchorConstraint(551, 666, 593, 595, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				editReplacedItemButton.setText("Edit");
				editReplacedItemButton.setPreferredSize(new java.awt.Dimension(61, 21));
				editReplacedItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png")));
				editReplacedItemButton.setBackground(new java.awt.Color(244,244,244));
				editReplacedItemButton.setAction(getEditReplacementItemsAction());
				
			}
			{
				deleteReplacedItemButton = new JButton();
				this.add(deleteReplacedItemButton, new AnchorConstraint(551, 763, 593, 673, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deleteReplacedItemButton.setText("Delete");
				deleteReplacedItemButton.setPreferredSize(new java.awt.Dimension(77, 21));
				deleteReplacedItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif")));
				deleteReplacedItemButton.setBackground(new java.awt.Color(244,244,244));
				deleteReplacedItemButton.setAction(getDeleteReplacementItemsAction());
				deleteReplacedItemButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
					}
				});
			}
			{
				returnedItemsScrollPane = new JScrollPane();
				this.add(returnedItemsScrollPane, new AnchorConstraint(66, 983, 265, 309, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				returnedItemsScrollPane.setPreferredSize(new java.awt.Dimension(579, 100));
				returnedItemsScrollPane.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					TableModel returnedItemsTableModel = new DefaultTableModel(new String[][] {}, new String[] {   "Product Code","Product Name","Quantity",
																													"Current Price","Selling Price","Deferred",
																													"Disc Code", "Reason Code" });
					returnedItemsTable = new JTable();
					returnedItemsScrollPane.setViewportView(returnedItemsTable);
					returnedItemsTable.setModel(returnedItemsTableModel);
				}
			}
			{
				jLabel30 = new JLabel();
				this.add(jLabel30, new AnchorConstraint(14, 481, 70, 309, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel30.setText("Returned Items");
				jLabel30.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/items.png")));
				jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14));
				jLabel30.setPreferredSize(new java.awt.Dimension(148, 28));
			}
			{
				editPaymentItemButton = new JButton();
				this.add(editPaymentItemButton, new AnchorConstraint(839, 666, 885, 595, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				editPaymentItemButton.setText("Edit");
				editPaymentItemButton.setPreferredSize(new java.awt.Dimension(61, 23));
				editPaymentItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png")));
				editPaymentItemButton.setBackground(new java.awt.Color(244,244,244));
				editPaymentItemButton.setAction(getEditPaymentItemAction());
				
			}
			{
				deletePaymentItemButton = new JButton();
				this.add(deletePaymentItemButton, new AnchorConstraint(839, 763, 885, 673, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deletePaymentItemButton.setText("Delete");
				deletePaymentItemButton.setPreferredSize(new java.awt.Dimension(77, 23));
				deletePaymentItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif")));
				deletePaymentItemButton.setBackground(new java.awt.Color(244,244,244));
				deletePaymentItemButton.setAction(getDeletePaymentItemAction());
				deletePaymentItemButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private AbstractAction getAddReturnItemAction() {
		if(addReturnItemAction == null) {
			addReturnItemAction = new AbstractAction("Add", new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png"))) {
				public void actionPerformed(ActionEvent evt) {
					ReturnedItemsDialog returnedItemsDialog = new ReturnedItemsDialog(Main.getInst());
					Invoice invoice = InvoiceService.getInvoiceByOr(returnedORTextField.getText());
					returnedItemsDialog.setInvoice(invoice);
					returnedItemsDialog.setInvoker(ReturnedPanel.this);
					returnedItemsDialog.init();
					returnedItemsDialog.setLocationRelativeTo(null);
					returnedItemsDialog.setVisible(true);
					returnedORTextField.setEnabled(false);
				}
			};
		}
		return addReturnItemAction;
	}
	
	public void addToReturnItemTable(InvoiceItem invoiceItem, String reason){
		System.out.println("adding item to return list");
		DefaultTableModel model = (DefaultTableModel) returnedItemsTable.getModel();
//		"Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" 
		Product product = ProductService.getProductById(invoiceItem.getProductCode());
		ReturnReason returnReason = ReturnReasonService.findReturnReasonByName(reason);
		model.addRow(new String[]{invoiceItem.getProductCode(),
								  product.getName(),
								  invoiceItem.getQuantity().toString(),
								  product.getSellPrice().toString(), 
								  String.format("%.2f",invoiceItem.getSellPrice()),
								  invoiceItem.getIsDeferred().toString(),
								  invoiceItem.getDiscountCode().toString(),
								  returnReason.getName().toString()});
	}
	
	private AbstractAction getDeleteReturnedItemsAction() {
		if(deleteReturnedItemsAction == null) {
			deleteReturnedItemsAction = new AbstractAction("Delete", new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif"))) {
				public void actionPerformed(ActionEvent evt) {
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these selected items?", "Prompt", JOptionPane.INFORMATION_MESSAGE);
					
					if(confirm == 0){
						DefaultTableModel model = (DefaultTableModel) returnedItemsTable.getModel();
						int[] selectedRows = returnedItemsTable.getSelectedRows();
						for(int i = 0; i < selectedRows.length; i++){
							model.removeRow(selectedRows[i]);
						}
					}
					
				}
			};
		}
		return deleteReturnedItemsAction;
	}
	
	private AbstractAction getAddReplacementItemAction() {
		if(addReplacementItemAction == null) {
			addReplacementItemAction = new AbstractAction("Add", new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png"))) {
				public void actionPerformed(ActionEvent evt) {
					ProductDialog dialog = ProductDialog.getProductDialog(Main.getInst(), ReturnedPanel.this, "add");
					dialog.clearProductInformation();
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			};
		}
		return addReplacementItemAction;
	}
	
	public void addInvoiceItem(InvoiceItem invoiceItem){
		DefaultTableModel model = (DefaultTableModel) replacementItemsTable.getModel();
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
	}
	
	public Integer getInvoiceItemRow(String prodCode){
		DefaultTableModel model = (DefaultTableModel) replacementItemsTable.getModel();
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
		DefaultTableModel model = (DefaultTableModel) replacementItemsTable.getModel();
		model.setValueAt(invoiceItem.getProductCode(), index, 0);
		model.setValueAt(product.getName(), index, 1);
		model.setValueAt(invoiceItem.getQuantity(), index, 2);
		model.setValueAt(product.getSellPrice().toString(), index, 3);
		model.setValueAt(invoiceItem.getSellPrice().toString(), index, 4);
		model.setValueAt((invoiceItem.getIsDeferred()==1)?"Yes":"No", index, 5);
		model.setValueAt(invoiceItem.getDiscountCode().toString(), index, 6);
	}
	
	private AbstractAction getEditReplacementItemsAction() {
		if(editReplacementItemsAction == null) {
			editReplacementItemsAction = new AbstractAction("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png"))) {
				public void actionPerformed(ActionEvent evt) {
					String productCode = replacementItemsTable.getValueAt(replacementItemsTable.getSelectedRow(), 0).toString();
					String quantity = replacementItemsTable.getValueAt(replacementItemsTable.getSelectedRow(), 2).toString();
					int discountCode = Integer.parseInt( replacementItemsTable.getValueAt(replacementItemsTable.getSelectedRow(), 6).toString());
					String deferred = replacementItemsTable.getValueAt(replacementItemsTable.getSelectedRow(), 5).toString();
					
					ProductDialog dialog = ProductDialog.getProductDialog(Main.getInst(),ReturnedPanel.this,productCode);
					dialog.setProductCode(productCode);
					dialog.setQuantity(quantity);
					dialog.setDiscount(discountCode);
					dialog.setDeferredValue(deferred);
					
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			};
		}
		return editReplacementItemsAction;
	}
	
	private AbstractAction getDeleteReplacementItemsAction() {
		if(deleteReplacementItemsAction == null) {
			deleteReplacementItemsAction = new AbstractAction("Delete", new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif"))) {
				public void actionPerformed(ActionEvent evt) {
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these selected items?", "Prompt", JOptionPane.INFORMATION_MESSAGE);
					if(confirm == 0){
						DefaultTableModel model = (DefaultTableModel) replacementItemsTable.getModel();
						int[] selectedRows = replacementItemsTable.getSelectedRows();
						String[] productCodes = new String[selectedRows.length];
						for(int i=0; i<selectedRows.length; i++){
							productCodes[i] = replacementItemsTable.getValueAt(selectedRows[i], 0).toString();
						}
						for(String s: productCodes){
							int index = getReplacementItemIndex(s);
							model.removeRow(index);
						}
					}
				}
			};
		}
		return deleteReplacementItemsAction;
	}
	
	private Integer getReplacementItemIndex(String productCode){
		for(int i =0; i<replacementItemsTable.getRowCount(); i++){
			if(replacementItemsTable.getValueAt(i, 0).toString().equals(productCode)){
				return i;
			}
		}
		return null;
	}
	
	private AbstractAction getAddPaymentItemAction() {
		if(addPaymentItemAction == null) {
			addPaymentItemAction = new AbstractAction("Add", new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png"))) {
				public void actionPerformed(ActionEvent evt) {
					PaymentDialog dialog = PaymentDialog.getPaymentDialog(Main.getInst(), ReturnedPanel.this, "add");
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			};
		}
		return addPaymentItemAction;
	}

	@Override
	public Boolean hasCardNo(Integer paymentCode, String cardNo) {
		String paymentName = PaymentItemService.getPaymentType(paymentCode);
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			System.out.println(model.getValueAt(i, 1).toString());
			System.out.println(model.getValueAt(i, 4).toString());
			System.out.println(cardNo);
			if(model.getValueAt(i, 1).toString().equals("Credit Card") && paymentName.equals("Credit Card") && model.getValueAt(i, 4).equals(cardNo)){
				System.out.println("true");
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean hasCashPayment(Integer paymentCode) {
		String paymentName = PaymentItemService.getPaymentType(paymentCode);
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			System.out.println(model.getValueAt(i, 1).toString());
			if(model.getValueAt(i, 1).toString().equals("Cash") && paymentName.equals("Cash")){
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean hasCheckNo(Integer paymentCode, String checkNo) {
		String paymentName = PaymentItemService.getPaymentType(paymentCode);
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			System.out.println(model.getValueAt(i, 1).toString());
			System.out.println(model.getValueAt(i, 5).toString());
			System.out.println(checkNo);
			if(model.getValueAt(i, 1).toString().equals("Bank Check") && paymentName.equals("Bank Check") && model.getValueAt(i, 5).equals(checkNo)){
				System.out.println("true");
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean hasGcNo(Integer paymentCode, String gcNo) {
		String paymentName = PaymentItemService.getPaymentType(paymentCode);
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			System.out.println(model.getValueAt(i, 1).toString());
			System.out.println(model.getValueAt(i, 6).toString());
			System.out.println(gcNo);
			if(model.getValueAt(i, 1).toString().equals("Gift Certificate") && paymentName.equals("Gift Certificate") && model.getValueAt(i, 6).equals(gcNo)){
				System.out.println("true");
				return true;
			}
		}
		return false;
	}

	@Override
	public void addPaymentItem(PaymentItem paymentItem) {
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		model.addRow(new String[]{paymentItem.getPaymentCode().toString(),
								  PaymentItemService.getPaymentType(paymentItem.getPaymentCode()),
								  paymentItem.getAmount().toString(),
								  paymentItem.getCardType(),
								  paymentItem.getCardNo(),
								  paymentItem.getCheckNo(),
								  paymentItem.getGcNo()
								  });
		updatePaymentAmounts();
	}

	@Override
	public void editPaymentItem(PaymentItem paymentItem, String paymentCode) {
		int index = getPaymentItemRow(Integer.parseInt(paymentCode));
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		model.setValueAt(PaymentItemService.getPaymentType(paymentItem.getPaymentCode()), index, 1);
		model.setValueAt(paymentItem.getAmount(), index, 2);
		model.setValueAt(paymentItem.getCardType().toString(), index, 3);
		model.setValueAt(paymentItem.getCardNo().toString(), index, 4);
		model.setValueAt(paymentItem.getCheckNo().toString(), index, 5);
		model.setValueAt(paymentItem.getGcNo().toString(), index, 6);
		updatePaymentAmounts();
		
	}

	@Override
	public Integer getPaymentItemRow(Integer paymentCode) {
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			if(model.getValueAt(i, 0).toString().equals(paymentCode.toString())){
				return i;
			}
		}
		return null;
	}

	@Override
	public void updatePaymentAmounts() {
		Double payment = 0.0d;
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			double quantity =  Double.parseDouble(model.getValueAt(i,2).toString());
			payment += quantity;
		}
		String amountString = amountLabel.getText();
		double amount = Double.parseDouble(amountString);
		totalPaymentTextField.setText(String.format("%.2f", payment));
		changeTextField.setText(String.format("%.2f", (payment-amount)));
		
	}
	
	private AbstractAction getEditPaymentItemAction() {
		if(editPaymentItemAction == null) {
			editPaymentItemAction = new AbstractAction("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png"))) {
				public void actionPerformed(ActionEvent evt) {
					
					String paymentCode = paymentTable.getValueAt(paymentTable.getSelectedRow(), 0).toString();
					String paymentType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 1).toString();
					String paymentAmount = paymentTable.getValueAt(paymentTable.getSelectedRow(), 2).toString();
					String creditCardType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 3).toString();
					String creditCardNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 4).toString();
					String bankCheckNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 5).toString();
					String gcNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 6).toString();
					
					
					PaymentDialog dialog = PaymentDialog.getPaymentDialog(Main.getInst(),ReturnedPanel.this,paymentCode);
					
					dialog.setAmount(paymentAmount);
					dialog.setPaymentType(paymentType);
					dialog.setCreditType(creditCardType);
					dialog.setCreditCard(creditCardNum);
					dialog.setBankCheck(bankCheckNum);
					dialog.setGiftCheck(gcNum);
					
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			};
		}
		return editPaymentItemAction;
	}

	private AbstractAction getDeletePaymentItemAction() {
		if(deletePaymentItemAction == null) {
			deletePaymentItemAction = new AbstractAction("Delete", new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif"))) {
				public void actionPerformed(ActionEvent evt) {
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this selected item", "Prompt", JOptionPane.INFORMATION_MESSAGE);
					if(confirm == 0){
						int i = paymentTable.getSelectedRow();
						DefaultTableModel defaultTableModel = (DefaultTableModel) paymentTable.getModel();
						defaultTableModel.removeRow(i);
						updatePaymentAmounts();
					}
				}
			};
		}
		return deletePaymentItemAction;
	}

}
