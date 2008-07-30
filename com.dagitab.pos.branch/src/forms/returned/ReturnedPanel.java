package forms.returned;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import bus.ProductService;
import bus.ReturnReasonService;
import bus.VatService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import domain.Invoice;
import domain.InvoiceItem;
import domain.Product;
import domain.ReturnReason;


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

	private JButton processTransactionButton;
	private JButton addPaymentItemButton;
	private JButton addReplacedItemButton;
	private JScrollPane paymentScrollPane;
	private JTable paymentsTable;
	private JScrollPane replacementItemScrollPane;
	private JTable replacementItemTable;
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
	private AbstractAction deleteReturnItemAction;
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
				deleteReturnItemButton.setAction(getDeleteReturnItemAction());
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
				addPaymentItemButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

					}
				});
			}
			{
				addReplacedItemButton = new JButton();
				this.add(addReplacedItemButton, new AnchorConstraint(551, 587, 593, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				addReplacedItemButton.setText("Add");
				addReplacedItemButton.setPreferredSize(new java.awt.Dimension(60, 21));
				addReplacedItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png")));
				addReplacedItemButton.setBackground(new java.awt.Color(244,244,244));
				addReplacedItemButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

					}
				});
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
					paymentsTable = new JTable();
					paymentScrollPane.setViewportView(paymentsTable);
					paymentsTable.setModel(paymentTableModel);
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
					replacementItemTable = new JTable();
					replacementItemScrollPane.setViewportView(replacementItemTable);
					replacementItemTable.setModel(replacementTableModel);
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
				editReplacedItemButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

					}
				});
			}
			{
				deleteReplacedItemButton = new JButton();
				this.add(deleteReplacedItemButton, new AnchorConstraint(551, 763, 593, 673, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deleteReplacedItemButton.setText("Delete");
				deleteReplacedItemButton.setPreferredSize(new java.awt.Dimension(77, 21));
				deleteReplacedItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif")));
				deleteReplacedItemButton.setBackground(new java.awt.Color(244,244,244));
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
					TableModel returnedItemsTableModel = new DefaultTableModel(new String[][] {}, new String[] {   "Product Code",
																													"Product Name", "Quantity",
																													"Current Price", "Selling Price",
																													"Deferred", "Disc Code",
																													"Reason Code" });
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
				editPaymentItemButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

					}
				});
			}
			{
				deletePaymentItemButton = new JButton();
				this.add(deletePaymentItemButton, new AnchorConstraint(839, 763, 885, 673, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deletePaymentItemButton.setText("Delete");
				deletePaymentItemButton.setPreferredSize(new java.awt.Dimension(77, 23));
				deletePaymentItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif")));
				deletePaymentItemButton.setBackground(new java.awt.Color(244,244,244));
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
	
	private AbstractAction getDeleteReturnItemAction() {
		if(deleteReturnItemAction == null) {
			deleteReturnItemAction = new AbstractAction("Delete", new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif"))) {
				public void actionPerformed(ActionEvent evt) {
					
				}
			};
		}
		return deleteReturnItemAction;
	}

}
