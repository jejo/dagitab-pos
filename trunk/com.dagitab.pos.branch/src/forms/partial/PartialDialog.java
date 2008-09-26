package forms.partial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;

import org.apache.log4j.Logger;

import util.LoggerUtility;
import util.PaymentCalculatorUtility;
import util.TableUtility;
import bus.InvoiceItemService;
import bus.InvoiceService;
import bus.PaymentItemService;
import bus.VatService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import domain.Invoice;
import domain.InvoiceItem;
import domain.PaymentItem;
import forms.interfaces.Payments;
import forms.lookup.PaymentDialog;
import forms.receipts.ReceiptPanel;
import forms.receipts.ValidateReceipt;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR`
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
public class PartialDialog extends javax.swing.JDialog implements Payments {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	private static Logger logger = Logger.getLogger(PartialDialog.class);
	private JLabel partialDialogLabel;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JScrollPane invoiceItemScrollPane;
	private AbstractAction deletePartialPaymentAction;
	private AbstractAction editPartialPaymentAction;
	private AbstractAction addPartialPaymentAction;
	private JTable paymentTable;
	private JLabel totalAmountLabel;
	private JScrollPane paymentTableScrollPane;
	private JButton processButton;
	private JButton deletePartialPaymentButton;
	private JButton editPartialButton;
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
	private JTextField orTextField;
	private JTextField dateTextField;
	private Double totalAmount;
	private JTable itemTable;
	private Invoice invoice;
	private static PartialDialog partialDialog;
	private Object invoker; 
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
						logger.info("processing partial invoice transaction...");
						int confirm  = JOptionPane.showConfirmDialog(null, "Are you sure you want to process this transaction?", "Prompt", JOptionPane.INFORMATION_MESSAGE);
						if(confirm == 0){
							if(hasEnoughPayment()){
								saveTransaction();
							}
							else{
								JOptionPane.showMessageDialog(null, "This is not a partial transaction. You have an insufficient payment amount.", "Prompt", JOptionPane.ERROR_MESSAGE);
							}
							
						}
					}
				});
			}
			{
				addPaymentButton = new JButton();
				getContentPane().add(addPaymentButton, new AnchorConstraint(781, 588, 819, 495, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				addPaymentButton.setText("Add");
				addPaymentButton.setPreferredSize(new java.awt.Dimension(74, 21));
				addPaymentButton.setAction(getAddPartialPaymentAction());
				addPaymentButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed F1"), "addPaymentButton");
				addPaymentButton.getActionMap().put("addPaymentButton",getAddPartialPaymentAction() );
				
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
					orTextField = new JTextField();
					jPanel1.add(orTextField, new AnchorConstraint(202, 943, 322, 410, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					orTextField.setEditable(false);
					orTextField.setText(invoice.getOrNo().toString());
					orTextField.setPreferredSize(new java.awt.Dimension(119, 21));
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
				getContentPane().add(invoiceItemScrollPane, new AnchorConstraint(158, 974, 456, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				invoiceItemScrollPane.setPreferredSize(new java.awt.Dimension(518, 163));
				{
					TableModel jTable1Model = new DefaultTableModel( new String[][] {  }, new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code","Extension" });
						itemTable = new JTable(){
							@Override
							public boolean isCellEditable(
								int row,
								int column) {
								return false;
							}
						};
						invoiceItemScrollPane.setViewportView(itemTable);
						itemTable.setModel(jTable1Model);
				}
			}
			{
				partialDialogLabel = new JLabel();
				getContentPane().add(partialDialogLabel, new AnchorConstraint(18, 353, 89, 19, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				partialDialogLabel.setText("Partial Transaction");
				partialDialogLabel.setPreferredSize(new java.awt.Dimension(245, 28));
				partialDialogLabel.setFont(new java.awt.Font("Tahoma",1,18));
				partialDialogLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "partialDialogLabel");
				partialDialogLabel.getActionMap().put("partialDialogLabel",getPartialDialogLabelAbstractAction() );
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
				getContentPane().add(paymentTableScrollPane, new AnchorConstraint(521, 974, 768, 325, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				paymentTableScrollPane.setPreferredSize(new java.awt.Dimension(518, 135));
				{
					TableModel paymentTableModel = new DefaultTableModel( new String[][] { }, new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
					paymentTable = new JTable(){
						@Override
						public boolean isCellEditable(
							int row,
							int column) {
							return false;
						}
					};
					paymentTable.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "paymentTable");
					paymentTable.getActionMap().put("paymentTable",getDeletePartialPaymentAction() );
					paymentTableScrollPane.setViewportView(paymentTable);
					paymentTable.setModel(paymentTableModel);
					paymentTable.addMouseListener(new MouseAdapter(){
						 public void mouseClicked(MouseEvent evt){
							 if (evt.getClickCount() == 2){
								 String paymentCode = paymentTable.getValueAt(paymentTable.getSelectedRow(), 0).toString();
								String paymentType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 1).toString();
								String paymentAmount = paymentTable.getValueAt(paymentTable.getSelectedRow(), 2).toString();
								String creditCardType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 3).toString();
								String creditCardNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 4).toString();
								String bankCheckNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 5).toString();
								String gcNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 6).toString();
								
								
								PaymentDialog dialog = PaymentDialog.getPaymentDialog(PartialDialog.this,PartialDialog.this,paymentCode);
								
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
				editPartialButton = new JButton();
				getContentPane().add(editPartialButton, new AnchorConstraint(779, 693, 819, 599, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				editPartialButton.setText("Edit");
				editPartialButton.setPreferredSize(new java.awt.Dimension(75, 22));
				editPartialButton.setAction(getEditPartialPaymentAction());
				editPartialButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed F3"), "editPartialButton");
				editPartialButton.getActionMap().put("editPartialButton",getEditPartialPaymentAction() );
				
			}
			{
				deletePartialPaymentButton = new JButton();
				getContentPane().add(deletePartialPaymentButton, new AnchorConstraint(779, 820, 819, 711, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deletePartialPaymentButton.setText("Delete");
				deletePartialPaymentButton.setPreferredSize(new java.awt.Dimension(87, 22));
				deletePartialPaymentButton.setAction(getDeletePartialPaymentAction());
				

				deletePartialPaymentButton.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "deletePartialPaymentButton");
				deletePartialPaymentButton.getActionMap().put("deletePartialPaymentButton",getDeletePartialPaymentAction() );
				
			}
			this.setSize(806, 573);
		
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	private void refreshItemTable(){
		ResultSet rs = InvoiceItemService.getInstance().fetchDiscountedInvoiceItem(invoice.getOrNo().toString());
		TableUtility.fillTable(itemTable, rs, new String[]{"Product Code", "Product Name", "Quantity", "Current Price", "Selling Price", "Deferred", "Disc Code", "Extension"});
	}
	
	private void updateAmounts(){
		Double amount = InvoiceItemService.getInstance().getDiscountedInvoiceItemAmount(invoice.getOrNo());
		logger.info("amount: "+amount);
		Double vat = VatService.getVatRate();
		Double subTotal = amount/vat;
		totalAmountLabel.setText(String.format("%.2f", amount));
		subTotalTextField.setText(String.format("%.2f", subTotal));
		vatTextField.setText(String.format("%.2f", new Double(amount-subTotal)));
		Double totalPaymentAmount = PaymentItemService.getInstance().getTotalPaymentAmount(invoice.getOrNo());
		totalPaymentTextField.setText(String.format("%.2f",totalPaymentAmount));
		Double change = totalPaymentAmount - amount;
		changeTextField.setText(String.format("%.2f", change));
	}
	
	public void updatePaymentAmounts(){
		
		Double totalPaymentAmount = 0.0d;
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<model.getRowCount(); i++){
			double paymentAmount =  Double.parseDouble(model.getValueAt(i,2).toString());
			totalPaymentAmount += paymentAmount;
		}
		
		String amountString = totalAmountLabel.getText();
		double amount = Double.parseDouble(amountString);
		totalPaymentTextField.setText(String.format("%.2f", totalPaymentAmount));
		//for recording change amount, gift certificate should not be considered for change
		totalPaymentAmount = 0.0d;
		for(int i = 0; i<model.getRowCount(); i++){
			double paymentAmount =  Double.parseDouble(model.getValueAt(i,2).toString());
			if(!model.getValueAt(i,1).toString().equals("Gift Certificate")){
				totalPaymentAmount += paymentAmount;
				
			}
		}
		Double changeAmount = totalPaymentAmount-amount;
		if(changeAmount < 0 ) changeAmount = 0.0d;
		changeTextField.setText(String.format("%.2f", changeAmount));
	}
	
	private void refreshPaymentTable(){
		ResultSet rs = PaymentItemService.getInstance().findPaymentItems(invoice.getOrNo());
		TableUtility.fillTable(paymentTable, rs, new String[]{"Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number"});
	}
	
	public void addPaymentItem(PaymentItem paymentItem){
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
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
	
	
	private boolean hasEnoughPayment(){
		//payment item data
		DefaultTableModel paymentTableModel = (DefaultTableModel) paymentTable.getModel();
		Double paymentAmount = 0d;
		for(int i = 0; i <paymentTableModel.getRowCount(); i++){
			paymentAmount += Double.parseDouble(paymentTable.getValueAt(i, 2).toString());
		}
		Double amount = Double.parseDouble(totalAmountLabel.getText());
		if(amount > paymentAmount){
			return false;
		}
		return true;
		
	}
	
	private void saveTransaction(){
		Invoice invoice = InvoiceService.getInvoiceByOr(orTextField.getText());
		invoice.setIsPartial(0);
		invoice.setChangeAmount(Double.parseDouble(changeTextField.getText()));
		
		InvoiceService.update(invoice);
		
		List<InvoiceItem> invoiceItems = InvoiceItemService.getInstance().findInvoiceItemByOR(Long.parseLong(orTextField.getText()));
		
		//clear payment items from db
		PaymentItemService.getInstance().removePaymentItem(invoice.getOrNo());
		
		List<PaymentItem> paymentItems = new ArrayList<PaymentItem>();
		//payment item data
		DefaultTableModel paymentTableModel = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i <paymentTableModel.getRowCount(); i++){
			PaymentItem paymentItem = new PaymentItem();
			paymentItem.setAmount(Double.parseDouble(paymentTable.getValueAt(i, 2).toString()));
			paymentItem.setCardNo(paymentTable.getValueAt(i, 4).toString());
			paymentItem.setCardType(paymentTable.getValueAt(i, 3).toString());
			paymentItem.setCheckNo(paymentTable.getValueAt(i, 5).toString());
			paymentItem.setGcNo(paymentTable.getValueAt(i, 6).toString());
			paymentItem.setOrNo(invoice.getOrNo());
			paymentItem.setStoreNo(Integer.parseInt(Main.getStoreCode()));
			paymentItem.setPaymentCode(Integer.parseInt(paymentTable.getValueAt(i, 0).toString()));
			paymentItem.setPaymentType(paymentTable.getValueAt(i, 1).toString());
//			PaymentItemService.getInstance().insert(paymentItem);
			paymentItems.add(paymentItem);
		}
		
		List<PaymentItem> calculatedPaymentItems = PaymentCalculatorUtility.getInstance().getCalculatedPaymentItems(paymentItems,Double.parseDouble(totalAmountLabel.getText()));
		for(PaymentItem paymentItem: calculatedPaymentItems){
			PaymentItemService.getInstance().insert(paymentItem);
		}
		
		JOptionPane.showMessageDialog(null, "Successfully processed transaction", "Prompt", JOptionPane.INFORMATION_MESSAGE);
		ReceiptPanel receiptPanel = new ReceiptPanel(invoice, invoiceItems, paymentItems,changeTextField.getText());
		
		ValidateReceipt validateReceiptDialog = new ValidateReceipt(Main.getInst(), receiptPanel);
		validateReceiptDialog.setLocationRelativeTo(null);
		validateReceiptDialog.setVisible(true);
		
		PartialPanel partialPanel = (PartialPanel) invoker;
		partialPanel.refreshPartialTable();
	}
	
	private AbstractAction getAddPartialPaymentAction() {
		if(addPartialPaymentAction == null) {
			addPartialPaymentAction = new AbstractAction("Add", new ImageIcon(getClass().getClassLoader().getResource("images/icons/add.png"))) {
				public void actionPerformed(ActionEvent evt) {
					PaymentDialog dialog = PaymentDialog.getPaymentDialog(PartialDialog.this, PartialDialog.this, "add");
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
};
		}
		return addPartialPaymentAction;
	}
	
	private AbstractAction getEditPartialPaymentAction() {
		if(editPartialPaymentAction == null) {
			editPartialPaymentAction = new AbstractAction("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/icons/email_edit.png"))) {
				public void actionPerformed(ActionEvent evt) {
					String paymentCode = paymentTable.getValueAt(paymentTable.getSelectedRow(), 0).toString();
					String paymentType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 1).toString();
					String paymentAmount = paymentTable.getValueAt(paymentTable.getSelectedRow(), 2).toString();
					String creditCardType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 3).toString();
					String creditCardNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 4).toString();
					String bankCheckNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 5).toString();
					String gcNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 6).toString();
					
					
					PaymentDialog dialog = PaymentDialog.getPaymentDialog(PartialDialog.this,PartialDialog.this,paymentCode);
					
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
		return editPartialPaymentAction;
	}
	
	private AbstractAction getDeletePartialPaymentAction() {
		if(deletePartialPaymentAction == null) {
			deletePartialPaymentAction = new AbstractAction("Delete", new ImageIcon(getClass().getClassLoader().getResource("images/icons/delete.gif"))) {
				public void actionPerformed(ActionEvent evt) {
					try{
						String paymentCode = paymentTable.getValueAt(paymentTable.getSelectedRow(), 0).toString();
						String paymentType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 1).toString();
						String paymentAmount = paymentTable.getValueAt(paymentTable.getSelectedRow(), 2).toString();
						String creditCardType = paymentTable.getValueAt(paymentTable.getSelectedRow(), 3).toString();
						String creditCardNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 4).toString();
						String bankCheckNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 5).toString();
						String gcNum = paymentTable.getValueAt(paymentTable.getSelectedRow(), 6).toString();
						if((PaymentItemService.getInstance().checkPaymentItemByType(Integer.parseInt((invoice.getOrNo().toString())), paymentCode, paymentType, paymentAmount, creditCardType, creditCardNum, bankCheckNum, gcNum)) >=1){
							JOptionPane.showMessageDialog(null, "Payment cannot be deleted.", "Prompt", JOptionPane.ERROR_MESSAGE);
						}
						else{
						removePaymentItem();
						}
					}
					catch(ArrayIndexOutOfBoundsException e){
						JOptionPane.showMessageDialog(null, "Please select item from the list", "Prompt", JOptionPane.ERROR_MESSAGE);
					}
				}
			};
		}
		return deletePartialPaymentAction;
	}
	
	private AbstractAction getPartialDialogLabelAbstractAction() {
		AbstractAction partialDialogLabelAction = new AbstractAction("Patial Transaction", null) {
			
			public void actionPerformed(ActionEvent evt) {
				PartialDialog.this.dispose();
			}
		};
		return partialDialogLabelAction;
	}

	public void setInvoker(Object invoker) {
		this.invoker = invoker;
	}

	@Override
	public Double getAmountDue() {
		return Double.parseDouble(totalAmountLabel.getText());
	}

	@Override
	public Double getTotalPayment() {
		Double totalPayment = 0.0d;
		for(int i =0; i<paymentTable.getRowCount(); i++){
			totalPayment += Double.valueOf(paymentTable.getValueAt(i, 2).toString());
		}
		return totalPayment;
	}

}
