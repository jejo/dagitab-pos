package com.dagitab.pos.forms.partial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import org.apache.log4j.Logger;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.dagitab.pos.bus.GCItemService;
import com.dagitab.pos.bus.InvoiceItemService;
import com.dagitab.pos.bus.InvoiceService;
import com.dagitab.pos.bus.InvoiceSetService;
import com.dagitab.pos.bus.PaymentItemService;
import com.dagitab.pos.bus.VatService;
import com.dagitab.pos.domain.GCItem;
import com.dagitab.pos.domain.Invoice;
import com.dagitab.pos.domain.InvoiceItem;
import com.dagitab.pos.domain.PaymentItem;
import com.dagitab.pos.forms.interfaces.Payments;
import com.dagitab.pos.forms.lookup.PaymentDialog;
import com.dagitab.pos.forms.receipts.ReceiptPanel;
import com.dagitab.pos.forms.receipts.ValidateReceipt;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;
import com.dagitab.pos.util.PaymentCalculatorUtility;
import com.dagitab.pos.util.StringUtility;
import com.dagitab.pos.util.TableUtility;


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
	private JScrollPane previousPaymentScrollPane;
	private JTable previousPaymentTable;
	private JLabel jLabel1;
	private JLabel currentORLabel;
	private JTextField currentORTextField;
	private JPanel jPanel3;
	private JLabel parentORLabel;
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
				getContentPane().add(getPreviousPaymentScrollPane(), new AnchorConstraint(434, 975, 578, 323, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				getContentPane().add(getJPanel3(), new AnchorConstraint(306, 290, 457, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				getContentPane().add(cancelButton, new AnchorConstraint(929, 465, 981, 323, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				cancelButton.setText("Cancel");
				cancelButton.setPreferredSize(new java.awt.Dimension(112, 30));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						PartialDialog.this.dispose();
					}
				});
			}
			{
				processButton = new JButton();
				getContentPane().add(processButton, new AnchorConstraint(928, 979, 980, 819, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				processButton.setText("Process");
				processButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				processButton.setPreferredSize(new java.awt.Dimension(126, 30));
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
				getContentPane().add(addPaymentButton, new AnchorConstraint(862, 585, 900, 494, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				addPaymentButton.setText("Add");
				addPaymentButton.setPreferredSize(new java.awt.Dimension(72, 22));
				addPaymentButton.setAction(getAddPartialPaymentAction());
				addPaymentButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed F1"), "addPaymentButton");
				addPaymentButton.getActionMap().put("addPaymentButton",getAddPartialPaymentAction() );
				
			}
			{
				jPanel2 = new JPanel();
				jPanel2.setLayout(null);
				getContentPane().add(jPanel2, new AnchorConstraint(468, 290, 948, 26, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel2.setPreferredSize(new java.awt.Dimension(210, 259));
				jPanel2.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel2.setBackground(new java.awt.Color(255,128,255));
				{
					subTotalTextField = new JTextField();
					jPanel2.add(subTotalTextField);
					subTotalTextField.setPreferredSize(new java.awt.Dimension(98, 21));
					subTotalTextField.setEditable(false);
					subTotalTextField.setHorizontalAlignment(SwingConstants.RIGHT);
					subTotalTextField.setText("0.00");
					subTotalTextField.setBounds(111, 84, 92, 21);
				}
				{
					amountDueLabel = new JLabel();
					jPanel2.add(amountDueLabel);
					amountDueLabel.setText("Amount Due");
					amountDueLabel.setFont(new java.awt.Font("Tahoma",1,22));
					amountDueLabel.setPreferredSize(new java.awt.Dimension(175, 28));
					amountDueLabel.setBounds(6, 7, 164, 28);
				}
				{
					subTotalLabel = new JLabel();
					jPanel2.add(subTotalLabel);
					subTotalLabel.setText("Sub Total");
					subTotalLabel.setFont(new java.awt.Font("Tahoma",1,14));
					subTotalLabel.setPreferredSize(new java.awt.Dimension(105, 28));
					subTotalLabel.setBounds(6, 77, 99, 28);
				}
				{
					vatLabel = new JLabel();
					jPanel2.add(vatLabel);
					vatLabel.setText("12% VAT");
					vatLabel.setFont(new java.awt.Font("Tahoma",1,14));
					vatLabel.setPreferredSize(new java.awt.Dimension(105, 28));
					vatLabel.setBounds(6, 112, 99, 28);
				}
				{
					totalPaymentLabel = new JLabel();
					jPanel2.add(totalPaymentLabel);
					totalPaymentLabel.setText("Total Payment");
					totalPaymentLabel.setFont(new java.awt.Font("Tahoma",1,14));
					totalPaymentLabel.setPreferredSize(new java.awt.Dimension(106, 31));
					totalPaymentLabel.setBounds(6, 149, 106, 31);
				}
				{
					changeLabel = new JLabel();
					jPanel2.add(changeLabel);
					changeLabel.setText("Change");
					changeLabel.setFont(new java.awt.Font("Tahoma",1,14));
					changeLabel.setPreferredSize(new java.awt.Dimension(99, 28));
					changeLabel.setBounds(7, 188, 99, 28);
				}
				{
					totalAmountLabel = new JLabel();
					jPanel2.add(totalAmountLabel);
					totalAmountLabel.setText(totalAmount.toString());
					totalAmountLabel.setFont(new java.awt.Font("Tahoma",1,22));
					totalAmountLabel.setPreferredSize(new java.awt.Dimension(175, 28));
					totalAmountLabel.setBounds(6, 35, 164, 28);

				}
				{
					vatTextField = new JTextField();
					jPanel2.add(vatTextField);
					vatTextField.setEditable(false);
					vatTextField.setPreferredSize(new java.awt.Dimension(98, 21));
					vatTextField.setHorizontalAlignment(SwingConstants.RIGHT);
					vatTextField.setBounds(111, 112, 92, 21);
					vatTextField.setText("0.00");
				}
				{
					totalPaymentTextField = new JTextField();
					jPanel2.add(totalPaymentTextField);
					totalPaymentTextField.setEditable(false);
					totalPaymentTextField.setPreferredSize(new java.awt.Dimension(98, 21));
					totalPaymentTextField.setHorizontalAlignment(SwingConstants.RIGHT);
					totalPaymentTextField.setText("0.00");
					totalPaymentTextField.setBounds(111, 154, 92, 21);
				}
				{
					changeTextField = new JTextField();
					jPanel2.add(changeTextField);
					changeTextField.setEditable(false);
					changeTextField.setPreferredSize(new java.awt.Dimension(98, 21));
					changeTextField.setHorizontalAlignment(SwingConstants.RIGHT);
					changeTextField.setText("0.00");
					changeTextField.setBounds(111, 189, 92, 21);
				}
			}
			{
				jPanel1 = new JPanel();
				jPanel1.setLayout(null);
				getContentPane().add(jPanel1, new AnchorConstraint(84, 290, 295, 24, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel1.setPreferredSize(new java.awt.Dimension(210, 113));
				jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				jPanel1.setBackground(new java.awt.Color(164,222,251));
				{
					orTextField = new JTextField();
					jPanel1.add(orTextField);
					orTextField.setEditable(false);
					orTextField.setText(invoice.getOrNo().toString());
					orTextField.setBounds(87, 29, 116, 18);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("Date");
					jLabel3.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel3.setBounds(8, 52, 76, 20);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("OR Number");
					jLabel2.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel2.setBounds(7, 26, 79, 24);
				}
				{
					dateTextField = new JTextField();
					jPanel1.add(dateTextField);
					dateTextField.setEditable(false);
					dateTextField.setBounds(87, 53, 116, 18);
					dateTextField.setText(invoice.getTransactionDate().split(" ")[0]);
				}
				{
					jLabel5 = new JLabel();
					jPanel1.add(jLabel5);
					jLabel5.setText("Time");
					jLabel5.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel5.setBounds(9, 80, 73, 22);
				}
				{
					timeTextField = new JTextField();
					jPanel1.add(timeTextField);
					jPanel1.add(getParentORLabel());
					timeTextField.setEditable(false);
					timeTextField.setBounds(87, 80, 116, 18);
					timeTextField.setText(invoice.getTransactionDate().split(" ")[1]);
				}
			}
			{
				invoiceItemScrollPane = new JScrollPane();
				getContentPane().add(invoiceItemScrollPane, new AnchorConstraint(73, 977, 371, 323, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				invoiceItemScrollPane.setPreferredSize(new java.awt.Dimension(517, 160));
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
				getContentPane().add(jLabel16, new AnchorConstraint(17, 437, 69, 323, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel16.setText("Items");
				jLabel16.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/items.png")));
				jLabel16.setFont(new java.awt.Font("Tahoma",1,14));
				jLabel16.setPreferredSize(new java.awt.Dimension(90, 28));
			}
			{
				jLabel15 = new JLabel();
				getContentPane().add(jLabel15, new AnchorConstraint(382, 553, 434, 323, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel15.setText("Previous Payments");
				jLabel15.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/payments.png")));
				jLabel15.setFont(new java.awt.Font("Tahoma",1,14));
				jLabel15.setPreferredSize(new java.awt.Dimension(182, 28));
			}
			{
				paymentTableScrollPane = new JScrollPane();
				getContentPane().add(paymentTableScrollPane, new AnchorConstraint(637, 975, 850, 323, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				paymentTableScrollPane.setPreferredSize(new java.awt.Dimension(515, 123));
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
				getContentPane().add(editPartialButton, new AnchorConstraint(862, 690, 902, 598, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				editPartialButton.setText("Edit");
				editPartialButton.setPreferredSize(new java.awt.Dimension(73, 23));
				editPartialButton.setAction(getEditPartialPaymentAction());
				editPartialButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed F3"), "editPartialButton");
				editPartialButton.getActionMap().put("editPartialButton",getEditPartialPaymentAction() );
				
			}
			{
				deletePartialPaymentButton = new JButton();
				getContentPane().add(deletePartialPaymentButton, new AnchorConstraint(860, 819, 900, 709, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				getContentPane().add(getJLabel1(), new AnchorConstraint(589, 552, 637, 323, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				deletePartialPaymentButton.setText("Delete");
				deletePartialPaymentButton.setPreferredSize(new java.awt.Dimension(87, 23));
				deletePartialPaymentButton.setAction(getDeletePartialPaymentAction());
				

				deletePartialPaymentButton.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DELETE"), "deletePartialPaymentButton");
				deletePartialPaymentButton.getActionMap().put("deletePartialPaymentButton",getDeletePartialPaymentAction() );
				
			}
			this.setSize(806, 614);
		
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	private void refreshItemTable(){
		ResultSet rs = InvoiceItemService.getInstance().fetchDiscountedInvoiceItem(invoice.getOrNo().toString());
//		TableUtility.fillTable(itemTable, rs, new String[]{"Product Code", "Product Name", "Quantity", "Current Price", "Selling Price", "Deferred", "Disc Code", "Extension"});
		//FIX FOR Rounding off Price Sold Field 
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		
		try {
			while(rs.next()){
				String[] rowValues = new String[8];
				for(int i = 0; i<8; i++){
					rowValues[i]= rs.getString(i+1);
					if(i == 3){ //price sold
						rowValues[i] = String.format("%.2f", Double.valueOf(rowValues[i]));
					}
					if(i==7){
						rowValues[i] = String.format("%.2f", rs.getInt(3)*Double.valueOf(rowValues[3]));
					}
				}
				
				//Replace current price and selling price field values
				String sellingPrice = rowValues[3];
				rowValues[3] = rowValues[4];
				rowValues[4] = sellingPrice;
				
				model.addRow(rowValues);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	private void updateAmounts(){
		Double amount = InvoiceItemService.getInstance().getDiscountedInvoiceItemAmount(invoice.getOrNo());
		logger.info("amount: "+amount);
		Double vat = VatService.getVatRate();
		Double subTotal = amount/vat;
		totalAmountLabel.setText(String.format("%.2f", amount));
		subTotalTextField.setText(String.format("%.2f", subTotal));
		vatTextField.setText(String.format("%.2f", new Double(amount - Double.valueOf(String.format("%.2f", subTotal)))));
		Double totalPaymentAmount = PaymentItemService.getInstance().getTotalPaymentAmount(invoice.getOrNo());
		totalPaymentTextField.setText(String.format("%.2f",totalPaymentAmount));
		Double change = totalPaymentAmount - amount;
		changeTextField.setText(String.format("%.2f", change));
	}
	
	public void updatePaymentAmounts(){
		
		logger.info("entering `updatePaymentAmounts` method");
		
		Double totalPaymentAmount = 0.0d;
		
		DefaultTableModel previousPayamentsModel = (DefaultTableModel) previousPaymentTable.getModel();
		for(int i = 0; i<previousPayamentsModel.getRowCount(); i++){
			double paymentAmount =  Double.parseDouble(previousPayamentsModel.getValueAt(i,2).toString());
			totalPaymentAmount += paymentAmount;
		}
		
		DefaultTableModel currentPaymentModel = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i<currentPaymentModel.getRowCount(); i++){
			double paymentAmount =  Double.parseDouble(currentPaymentModel.getValueAt(i,2).toString());
			totalPaymentAmount += paymentAmount;
		}
		
		String amountString = totalAmountLabel.getText();
		double amount = Double.parseDouble(amountString);
		totalPaymentTextField.setText(String.format("%.2f", totalPaymentAmount));
		
		
		//for recording change amount, gift certificate should not be considered for change
		totalPaymentAmount = 0.0d;
		boolean hasGCPayment = false;
		boolean hasCashPayment = false;
		
		for(int i = 0; i<previousPayamentsModel.getRowCount(); i++){
			double paymentAmount =  Double.parseDouble(previousPayamentsModel.getValueAt(i,2).toString());
			if(previousPayamentsModel.getValueAt(i,1).toString().equals("Gift Certificate")){
				hasGCPayment = true;
			}
			else if(previousPayamentsModel.getValueAt(i,1).toString().equals("Cash")){
				hasCashPayment = true;
			}
			totalPaymentAmount += paymentAmount;
		}
		
		for(int i = 0; i<currentPaymentModel.getRowCount(); i++){
			double paymentAmount =  Double.parseDouble(currentPaymentModel.getValueAt(i,2).toString());
			if(currentPaymentModel.getValueAt(i,1).toString().equals("Gift Certificate")){
				hasGCPayment = true;
			}
			else if(currentPaymentModel.getValueAt(i,1).toString().equals("Cash")){
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
//		logger.info("Total Amount: "+totalPaymentAmount+" - amount: "+amount+" Change Amount: "+changeAmount);
		changeTextField.setText(String.format("%.2f", changeAmount));
	}
	
	private void refreshPaymentTable(){
		ResultSet rs = PaymentItemService.getInstance().findPaymentItems(invoice.getOrNo());
		TableUtility.fillTable(previousPaymentTable, rs, new String[]{"Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number"});
	}
	
	public void addPaymentItem(PaymentItem paymentItem){
		
		Double totalGCPayments =  getTotalGCPayments();
		//special handling when inserting gc, manipulate gc amount by making it exact with the invoice amount
		if(PaymentItemService.getInstance().getPaymentType(paymentItem.getPaymentCode()).equals("Gift Certificate")){
			Double invoiceAmount = Double.parseDouble(totalAmountLabel.getText());
			Double newTotalGCPayments = totalGCPayments + paymentItem.getAmount();
			if(newTotalGCPayments > invoiceAmount){
				Double amount =  (invoiceAmount - totalGCPayments);
				paymentItem.setAmount(amount);
			}
		}
		
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
		
		Double totalGCPayments =  getTotalGCPayments();
		//special handling when inserting gc, manipulate gc amount by making it exact with the invoice amount
		if(PaymentItemService.getInstance().getPaymentType(paymentItem.getPaymentCode()).equals("Gift Certificate")){
			Double invoiceAmount = Double.parseDouble(totalAmountLabel.getText());
			Double newTotalGCPayments = totalGCPayments + paymentItem.getAmount();
			if(newTotalGCPayments > invoiceAmount){
				Double amount =  (invoiceAmount - totalGCPayments);
				paymentItem.setAmount(amount);
			}
		}
		
		model.setValueAt(paymentItem.getPaymentCode(), index, 0);
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
		Double paymentAmount = 0d;
		
		DefaultTableModel previousPaymentTableModel = (DefaultTableModel) previousPaymentTable.getModel();
		for(int i = 0; i <previousPaymentTableModel.getRowCount(); i++){
			paymentAmount += Double.parseDouble(previousPaymentTable.getValueAt(i, 2).toString());
		}
		
		DefaultTableModel paymentTableModel = (DefaultTableModel) paymentTable.getModel();
		for(int i = 0; i <paymentTableModel.getRowCount(); i++){
			paymentAmount += Double.parseDouble(paymentTable.getValueAt(i, 2).toString());
		}
		
		Double amount = Double.parseDouble(totalAmountLabel.getText());
		logger.info("PAYMENT AMOUNT: "+paymentAmount);
		if(amount > paymentAmount){
			return false;
		}
		return true;
		
	}
	
	private void saveTransaction(){
		try{
			Main.getDBManager().getConnection().setAutoCommit(false);
			Invoice invoice = saveInvoice();
			List<InvoiceItem> invoiceItems = saveInvoiceItems();
			List<PaymentItem> paymentItems = fillPaymentItemData();
			List<PaymentItem> calculatedPaymentItems = saveCalculatedPaymentItems(paymentItems);
			InvoiceSetService.saveInvoiceSet(Long.parseLong(orTextField.getText()),Long.parseLong(currentORTextField.getText()));
			Main.getDBManager().getConnection().commit();
			
			JOptionPane.showMessageDialog(null, "Successfully processed transaction", "Prompt", JOptionPane.INFORMATION_MESSAGE);
			prepareReceipt(invoice, invoiceItems, paymentItems, calculatedPaymentItems);
			
		}
		catch(Exception e){
			try {
				Main.getDBManager().getConnection().rollback();
			} catch (SQLException e1) {
				LoggerUtility.getInstance().logStackTrace(e1);
			} 
			JOptionPane.showMessageDialog(null, "There was an error occured in the transaction. Please re-enter the transaction", "Save Error", JOptionPane.ERROR_MESSAGE);
			LoggerUtility.getInstance().logStackTrace(e);
		}
		finally{
			PartialPanel partialPanel = (PartialPanel) invoker;
			partialPanel.refreshPartialTable();
			try {
				Main.getDBManager().getConnection().setAutoCommit(true);
				
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		
		
		
	}

	
	
	private void prepareReceipt(Invoice invoice,
			List<InvoiceItem> invoiceItems, List<PaymentItem> paymentItems,
			List<PaymentItem> calculatedPaymentItems) {
		//GC ITEM population
		List<GCItem> gcItems = GCItemService.getInstance().filterToGCItemList(calculatedPaymentItems);
		
		//add previous payments on receipt
		List<PaymentItem> previousPaymentItems = getPreviousPaymentItemData();
		for(PaymentItem pi:previousPaymentItems){
			paymentItems.add(pi);
		}
		invoice.setIsPartial(0); //display as a regular transaction. not a partial anymore
		
		ReceiptPanel receiptPanel = new ReceiptPanel(invoice, invoiceItems, paymentItems, gcItems, changeTextField.getText());
		ValidateReceipt validateReceiptDialog = new ValidateReceipt(Main.getInst(), receiptPanel);
		validateReceiptDialog.setLocationRelativeTo(null);
		validateReceiptDialog.setVisible(true);
	}

	private List<PaymentItem> saveCalculatedPaymentItems(List<PaymentItem> paymentItems) throws Exception {
		logger.info("entering `saveCalculatedPaymentItems` method");

		Double previousPaymentAmount = 0.0d;
		Double totalAmount = Double.parseDouble(totalAmountLabel.getText()); 
		List<PaymentItem> previousPayments = getPreviousPaymentItemData();
		
		for(PaymentItem paymentItem: previousPayments){
			previousPaymentAmount += paymentItem.getAmount();
		}
		
		Double partialBalance = totalAmount - previousPaymentAmount;
		if(partialBalance < 0){
			throw new Exception("Partial Balance is already 0. Please verify this transaction");
		}
		
		List<PaymentItem> calculatedPaymentItems = PaymentCalculatorUtility.getInstance().getCalculatedPaymentItems(paymentItems, partialBalance);
		for(PaymentItem paymentItem: calculatedPaymentItems){
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
		return calculatedPaymentItems;
	}

	private List<PaymentItem> fillPaymentItemData() {
		logger.info("entering `fillPaymentItemData` method");
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
			paymentItem.setOrNo(Long.parseLong(currentORTextField.getText()));
			paymentItem.setStoreNo(Integer.parseInt(Main.getStoreCode()));
			paymentItem.setPaymentCode(Integer.parseInt(paymentTable.getValueAt(i, 0).toString()));
			paymentItem.setPaymentType(paymentTable.getValueAt(i, 1).toString());
			paymentItems.add(paymentItem);
		}
		return paymentItems;
	}
	
	private List<PaymentItem> getPreviousPaymentItemData(){
		logger.info("entering `getPreviousPaymentItemData` method");
		List<PaymentItem> previousPaymentItems = new ArrayList<PaymentItem>();
		DefaultTableModel previousPaymentTableModel = (DefaultTableModel) previousPaymentTable.getModel();
		for(int i = 0; i < previousPaymentTableModel.getRowCount(); i++){
			PaymentItem paymentItem = new PaymentItem();
			paymentItem.setAmount(Double.parseDouble(previousPaymentTable.getValueAt(i, 2).toString()));
			paymentItem.setCardNo(previousPaymentTable.getValueAt(i, 4).toString());
			paymentItem.setCardType(previousPaymentTable.getValueAt(i, 3).toString());
			paymentItem.setCheckNo(previousPaymentTable.getValueAt(i, 5).toString());
			paymentItem.setGcNo(previousPaymentTable.getValueAt(i, 6).toString());
			paymentItem.setOrNo(Long.parseLong(orTextField.getText()));
			paymentItem.setStoreNo(Integer.parseInt(Main.getStoreCode()));
			paymentItem.setPaymentCode(Integer.parseInt(previousPaymentTable.getValueAt(i, 0).toString()));
			paymentItem.setPaymentType(previousPaymentTable.getValueAt(i, 1).toString());
			previousPaymentItems.add(paymentItem);
		}
		return previousPaymentItems;
		
	}

	private List<InvoiceItem> saveInvoiceItems() {
		logger.info("entering `saveInvoiceItems` method");
		List<InvoiceItem> invoiceItems = InvoiceItemService.getInstance().findInvoiceItemByOR(Long.parseLong(orTextField.getText()));
		for(int i = 0; i<invoiceItems.size(); i++){
			invoiceItems.get(i).setOrNo(Long.parseLong(currentORTextField.getText()));
			InvoiceItemService.getInstance().insert(invoiceItems.get(i));
		}
		return invoiceItems;
	}

	private Invoice saveInvoice() {
		logger.info("entering `saveInvoice` method");
		Invoice parentInvoice = InvoiceService.getInvoiceByOr(orTextField.getText());
		
		Invoice invoice = new Invoice();
		invoice.setOrNo(Long.parseLong(currentORTextField.getText()));
		invoice.setInvoiceNo(parentInvoice.getInvoiceNo());
		invoice.setAssistantCode(parentInvoice.getAssistantCode());
		invoice.setCustomerNo(parentInvoice.getCustomerNo());
		invoice.setIsPartial(1);
		invoice.setEncoderCode(Main.getClerkCode());
		invoice.setStoreNo(Integer.parseInt(Main.getStoreCode()));
		invoice.setChangeAmount(Double.parseDouble(changeTextField.getText()));
		InvoiceService.insert(invoice);
		return invoice;
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
		Double previousPaymentAmount = 0.0d;
		Double totalAmount = Double.parseDouble(totalAmountLabel.getText()); 
		List<PaymentItem> previousPayments = getPreviousPaymentItemData();
		
		for(PaymentItem paymentItem: previousPayments){
			previousPaymentAmount += paymentItem.getAmount();
		}
		Double partialBalance = totalAmount - previousPaymentAmount;
		return partialBalance;
	}

	@Override
	public boolean isGCGreaterThanAmount(Integer paymentCode, Double gcAmount) {
		DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
		Double gcAmountTotal = 0.0d;
		for(int i = 0; i<model.getRowCount(); i++){
			
			if(model.getValueAt(i, 1).toString().equals("Gift Certificate")){
				gcAmountTotal += Double.parseDouble(model.getValueAt(i, 2).toString());
			}
		}
		Double amount = Double.parseDouble(totalAmountLabel.getText());
		logger.debug("GC Total Amount: "+gcAmountTotal+" amount: "+amount);
		if(gcAmountTotal >= amount){
			return true;
		}
		return false;
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
	
	private JLabel getParentORLabel() {
		if(parentORLabel == null) {
			parentORLabel = new JLabel();
			parentORLabel.setText("Previous Transaction");
			parentORLabel.setBounds(8, 6, 169, 14);
			parentORLabel.setFont(new java.awt.Font("Tahoma",1,12));
		}
		return parentORLabel;
	}
	
	private JPanel getJPanel3() {
		if(jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setPreferredSize(new java.awt.Dimension(211, 81));
			jPanel3.setBackground(new java.awt.Color(128,255,128));
			jPanel3.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			jPanel3.setLayout(null);
			jPanel3.add(getCurrentORTextField());
			jPanel3.add(getCurrentORLabel());
		}
		return jPanel3;
	}
	
	private JTextField getCurrentORTextField() {
		if(currentORTextField == null) {
			currentORTextField = new JTextField();
			currentORTextField.setBounds(83, 29, 120, 16);
			currentORTextField.setEditable(false);
			String nextOR;
			try {
				nextOR = InvoiceService.getNextORNumber();
				if(nextOR == null){
					nextOR = "1";
				}
				currentORTextField.setText(StringUtility.zeroFill(nextOR, 10));
			} catch (Exception e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		return currentORTextField;
	}
	
	private JLabel getCurrentORLabel() {
		if(currentORLabel == null) {
			currentORLabel = new JLabel();
			currentORLabel.setText("OR Number");
			currentORLabel.setBounds(7, 31, 75, 11);
			currentORLabel.setFont(new java.awt.Font("Tahoma",1,12));
		}
		return currentORLabel;
	}
	
	private JScrollPane getPreviousPaymentScrollPane() {
		if(previousPaymentScrollPane == null) {
			previousPaymentScrollPane = new JScrollPane();
			previousPaymentScrollPane.setPreferredSize(new java.awt.Dimension(515, 77));
			previousPaymentScrollPane.setViewportView(getPreviousPaymentTable());
		}
		return previousPaymentScrollPane;
	}
	
	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Current Payments");
			jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/payments.png")));
			jLabel1.setFont(new java.awt.Font("Tahoma",1,14));
			jLabel1.setPreferredSize(new java.awt.Dimension(181, 28));
		}
		return jLabel1;
	}
	
	private JTable getPreviousPaymentTable() {
		if(previousPaymentTable == null) {
			TableModel previousPaymentTableModel = new DefaultTableModel( new String[][] { }, new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
			previousPaymentTable = new JTable(){
				@Override
				public boolean isCellEditable(
					int row,
					int column) {
					return false;
				}
			};
			previousPaymentTable.setModel(previousPaymentTableModel);
		}
		return previousPaymentTable;
	}

}
