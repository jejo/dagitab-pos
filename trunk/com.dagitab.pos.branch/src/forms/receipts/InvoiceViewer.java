package forms.receipts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import util.LoggerUtility;
import util.ServerPropertyHandler;
import bus.InvoiceItemService;
import bus.InvoiceService;
import bus.PaymentItemService;
import bus.ProductService;
import bus.ReturnItemService;

import com.cloudgarden.layout.AnchorConstraint;

import domain.Invoice;
import domain.InvoiceItem;
import domain.PaymentItem;
import domain.Product;
import domain.ReturnItem;


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
	private static Logger logger = Logger.getLogger(InvoiceViewer.class);
	private JLabel invoiceViewerLabel;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPanel jPanel1;
	private JLabel jLabel4;
	private JScrollPane itemScrollPane;
	private JLabel jLabel5;
	private JTextField totalPaymentTextField;
	private JLabel totalPaymentLabel;
	private AbstractAction viewInformationAbstractAction;
	private JButton btnPrint;
	private JScrollPane paymentItemScrollPane;
	private JTextField vatTextField;
	private JTextField changeTextField;
	private JLabel changeLabel;
	private JLabel jLabel7;
	private JTextField subTotalTextField;
	private JLabel jLabel6;
	private JTable paymentItemTable;
	private JTable itemTable;
	private JTextField amountDueTextField;
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
			LoggerUtility.getInstance().logStackTrace(e);
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
					invoiceViewerLabel = new JLabel();
					getContentPane().add(
						invoiceViewerLabel,
						new AnchorConstraint(
							38,
							312,
							216,
							33,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					invoiceViewerLabel.setText("Invoice Viewer");
					invoiceViewerLabel.setFont(new java.awt.Font("Tahoma",1,18));
					invoiceViewerLabel.setBounds(14, 7, 182, 35);
					invoiceViewerLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "invoiceViewerLabel");
					invoiceViewerLabel.getActionMap().put("invoiceViewerLabel",getInvoiceViewerLabelAbstractAction() );
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
					btnView.setBounds(238, 48, 140, 21);
					btnView.setAction(getViewInformationAbstractAction());
					
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("Items");
					jLabel3.setBounds(21, 188, 63, 28);
					jLabel3.setFont(new java.awt.Font("Tahoma",1,14));
				}
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1);
					jPanel1.setBounds(21, 84, 693, 104);
					jPanel1.setBackground(new java.awt.Color(234,254,255));
					jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					jPanel1.setLayout(null);
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4);
						jLabel4.setText("Amount Due");
						jLabel4.setBounds(8, 13, 105, 14);
						jLabel4.setFont(new java.awt.Font("Tahoma",1,14));
					}
					{
						amountDueTextField = new JTextField();
						jPanel1.add(amountDueTextField);
						amountDueTextField.setBounds(123, 12, 112, 21);
						amountDueTextField.setEditable(false);
					}
					{
						jLabel6 = new JLabel();
						jPanel1.add(jLabel6);
						jLabel6.setText("Sub Total");
						jLabel6.setBounds(8, 33, 63, 28);
					}
					{
						subTotalTextField = new JTextField();
						jPanel1.add(subTotalTextField);
						subTotalTextField.setBounds(123, 37, 112, 21);
						subTotalTextField.setEditable(false);
					}
					{
						jLabel7 = new JLabel();
						jPanel1.add(jLabel7);
						jLabel7.setText("VAT");
						jLabel7.setBounds(11, 67, 49, 14);
					}
					{
						vatTextField = new JTextField();
						jPanel1.add(vatTextField);
						jPanel1.add(getTotalPaymentLabel());
						jPanel1.add(getTotalPaymentTextField());
						jPanel1.add(getChangeLabel());
						jPanel1.add(getChangeTextField());
						vatTextField.setEditable(false);
						vatTextField.setBounds(123, 64, 112, 21);
					}
				}
				{
					itemScrollPane = new JScrollPane();
					getContentPane().add(itemScrollPane);
					itemScrollPane.setBounds(21, 216, 690, 131);
					{
						TableModel itemTableModel = new DefaultTableModel(
								new String[][] {  },
								new String[] { "Product Code", 
											   "Product Name",
											   "Quantity",
											   "Current Price",
											   "Selling Price",
											   "Deferred",
											   "Disc Code",
											   "Extension" });
							itemTable = new JTable();
							itemScrollPane.setViewportView(itemTable);
							itemTable.setModel(itemTableModel);
					}
				}
				{
					jLabel5 = new JLabel();
					getContentPane().add(jLabel5);
					jLabel5.setText("Payments");
					jLabel5.setFont(new java.awt.Font("Tahoma",1,14));
					jLabel5.setBounds(24, 357, 147, 28);
				}
				{
					paymentItemScrollPane = new JScrollPane();
					getContentPane().add(paymentItemScrollPane);
					paymentItemScrollPane.setBounds(24, 384, 686, 90);
					{
						TableModel paymentItemTableModel = new DefaultTableModel(
								new String[][] { },
								new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
							paymentItemTable = new JTable();
							paymentItemScrollPane.setViewportView(paymentItemTable);
							paymentItemTable.setModel(paymentItemTableModel);
							
							
					}
				}
				{
					btnPrint = new JButton();
					getContentPane().add(btnPrint);
					btnPrint.setText("Print Receipt");
					btnPrint.setBounds(309, 494, 154, 28);
					btnPrint.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Invoice invoice = InvoiceService.getInvoiceByOr(txtOR.getText());
							List<InvoiceItem> invoiceItems = InvoiceItemService.getInstance().findInvoiceItemByOR(Long.parseLong(txtOR.getText()));
							
							List<ReturnItem> returnedItems = ReturnItemService.getReturnedItems(invoice.getOrNo());
							for(ReturnItem returnItem : returnedItems){
								
								InvoiceItem returnedInvoiceItem = new InvoiceItem();
								Product product = ProductService.getProductById(returnItem.getProductCode());
								returnedInvoiceItem.setCost(product.getCost());
								returnedInvoiceItem.setDiscountCode(0);
								returnedInvoiceItem.setIsDeferred(0);
								returnedInvoiceItem.setIsReturned(true);
								returnedInvoiceItem.setOrNo(invoice.getOrNo());
								returnedInvoiceItem.setProductCode(returnItem.getProductCode());
								returnedInvoiceItem.setQuantity(returnItem.getQuantity()*-1);
								returnedInvoiceItem.setSellPrice(returnItem.getSellPrice());
								returnedInvoiceItem.setStoreNo(returnItem.getStoreCode());
								invoiceItems.add(returnedInvoiceItem);
							}
							
							List<PaymentItem> paymentItems = PaymentItemService.getInstance().getPaymentItemList(Long.parseLong(txtOR.getText()));
							
							//edit payment item which is cash, derive how much the customer gave by adding the change to the exact cash value amount
							for(PaymentItem paymentItem: paymentItems){
								if(paymentItem.getPaymentType().equals("Cash")){
									Double originalAmount = paymentItem.getAmount() + invoice.getChangeAmount();
									paymentItem.setAmount(originalAmount);
								}
							}
							
							ReceiptPanel receiptPanel = new ReceiptPanel(invoice, invoiceItems, paymentItems,changeTextField.getText());
							ValidateReceipt validateReceiptDialog = new ValidateReceipt(InvoiceViewer.this, receiptPanel);
							validateReceiptDialog.setLocationRelativeTo(null);
							validateReceiptDialog.setVisible(true);
						
						}
					});
				}
			}
			this.setSize(743, 580);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	public void displayInfo() throws SQLException{
		
	}
	
	private AbstractAction getInvoiceViewerLabelAbstractAction() {
		AbstractAction invioceViewerLabelAction = new AbstractAction("Invoice Viewer", null) {
			
			public void actionPerformed(ActionEvent evt) {
				InvoiceViewer.this.dispose();
			}
		};
		return invioceViewerLabelAction;
	}
	
	private AbstractAction getViewInformationAbstractAction() {
		if(viewInformationAbstractAction == null) {
			viewInformationAbstractAction = new AbstractAction("View Information", new ImageIcon(getClass().getClassLoader().getResource("images/Search.PNG"))) {
				public void actionPerformed(ActionEvent evt) {
					
					//clear tables
					TableModel model1 = new DefaultTableModel(
							new String[][] { },
							new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
					paymentItemTable = new JTable();
					paymentItemScrollPane.setViewportView(paymentItemTable);
					paymentItemTable.setModel(model1);
					
					
					TableModel model2 = new DefaultTableModel(
							new String[][] {  },
							new String[] { "Product Code", 
										   "Product Name",
										   "Quantity",
										   "Current Price",
										   "Selling Price",
										   "Deferred",
										   "Disc Code",
										   "Extension" });
					itemTable = new JTable();
					itemScrollPane.setViewportView(itemTable);
					itemTable.setModel(model2);
					
					
					boolean isValid =(txtOR.getText().length() > 0) && txtOR.getText().matches("^[0-9]*$"); 
					if(isValid){
						Invoice invoice = InvoiceService.getInvoiceByOr(txtOR.getText());
						
						DefaultTableModel itemTableModel = (DefaultTableModel) itemTable.getModel();
						
						
						if(invoice.getIsReturn() == 1){
							logger.info("JEJO: invoice amount: "+InvoiceService.getInvoiceAmount(invoice.getOrNo())+" return amount: "+ ReturnItemService.getReturnAmount(invoice.getOrNo()));
							Double invoiceAmount =  InvoiceService.getInvoiceAmount(invoice.getOrNo()) - ReturnItemService.getReturnAmount(invoice.getOrNo());
							Double subTotalAmount = InvoiceService.getSubTotal(invoiceAmount);
							Double vatAmount = invoiceAmount - subTotalAmount;
							logger.info("JEJO: total payment: "+InvoiceService.getTotalPaymentAmount(invoice.getOrNo()));
							Double totalPaymentAmount = InvoiceService.getTotalPaymentAmount(invoice.getOrNo()) + invoice.getChangeAmount();
							amountDueTextField.setText(String.format("%.2f", invoiceAmount));
							subTotalTextField.setText(String.format("%.2f", subTotalAmount));
							vatTextField.setText(String.format("%.2f", vatAmount));
							totalPaymentTextField.setText(String.format("%.2f", totalPaymentAmount));
							changeTextField.setText(String.format("%.2f", invoice.getChangeAmount()));
							
							List<ReturnItem> returnedItems = ReturnItemService.getReturnedItems(invoice.getOrNo());
							for(ReturnItem returnItem : returnedItems){
								String[] rowData = new String[8];
								rowData[0] = returnItem.getProductCode(); //product code
								Product product = ProductService.getProductById(returnItem.getProductCode());
								rowData[1] = product.getName(); //product name
								rowData[2] = (returnItem.getQuantity()*-1)+""; //quantity
								rowData[3] = String.format("%.2f",product.getSellPrice()); //current price
								Double discountedAmount = returnItem.getSellPrice(); 
								rowData[4] = String.format("%.2f", discountedAmount); //selling price
								rowData[5] = "0"; //deferred
								rowData[6] = "0"; //discount code
								Double extension = returnItem.getQuantity() * discountedAmount; 
								rowData[7] = extension.toString(); // extension
								itemTableModel.addRow(rowData);
							}
						}
						else{
							Double invoiceAmount =  InvoiceService.getInvoiceAmount(invoice.getOrNo());
							Double subTotalAmount = InvoiceService.getSubTotal(invoiceAmount);
							Double vatAmount = invoiceAmount - subTotalAmount;
							Double totalPaymentAmount = InvoiceService.getTotalPaymentAmount(invoice.getOrNo()) +invoice.getChangeAmount();
							
							amountDueTextField.setText(String.format("%.2f", invoiceAmount));
							subTotalTextField.setText(String.format("%.2f", subTotalAmount));
							vatTextField.setText(String.format("%.2f", vatAmount));
							totalPaymentTextField.setText(String.format("%.2f", totalPaymentAmount));
							changeTextField.setText(String.format("%.2f", invoice.getChangeAmount()));
						}
						
						List<InvoiceItem> listInvoiceItems = InvoiceItemService.getInstance().findInvoiceItemByOR(invoice.getOrNo());
						for(InvoiceItem invoiceItem: listInvoiceItems){
							String[] rowData = new String[8];
							rowData[0] = invoiceItem.getProductCode(); //product code
							Product product = ProductService.getProductById(invoiceItem.getProductCode());
							rowData[1] = product.getName(); //product name
							rowData[2] = invoiceItem.getQuantity().toString(); //quantity
							rowData[3] = String.format("%.2f",invoiceItem.getSellPrice()); //current price
							Double discountedAmount = InvoiceItemService.getInstance().getDiscountedAmount(invoiceItem.getOrNo(), invoiceItem.getProductCode()); 
							rowData[4] = String.format("%.2f", discountedAmount); //selling price
							rowData[5] = invoiceItem.getIsDeferred().toString(); //deferred
							rowData[6] = invoiceItem.getDiscountCode().toString(); //discount code
							Double extension = invoiceItem.getQuantity() * discountedAmount; 
							rowData[7] = extension.toString(); // extension
							itemTableModel.addRow(rowData);
						}
						
						DefaultTableModel paymentItemTableModel = (DefaultTableModel) paymentItemTable.getModel();
						List<PaymentItem> listPaymentItems = PaymentItemService.getInstance().getPaymentItemList(invoice.getOrNo());
						for(PaymentItem paymentItem: listPaymentItems){
//							new String[] { "Payment Code", "Payment Type","Amount","Credit Card Type","Credit Card No","Bank Check No","Gift Certificate Number" });
							String[] rowData = new String[7];
							rowData[0] = paymentItem.getPaymentCode().toString();
							String paymentName = PaymentItemService.getInstance().getPaymentType(paymentItem.getPaymentCode());
							rowData[1] = paymentName;
							if(paymentName.equals("Cash")){
								rowData[2] = String.format("%.2f",paymentItem.getAmount() + invoice.getChangeAmount()) ;
							}
							else{
								rowData[2] = String.format("%.2f",paymentItem.getAmount());
							}
							rowData[3] = paymentItem.getCardType();
							rowData[4] = paymentItem.getCardNo();
							rowData[5] = paymentItem.getCheckNo();
							rowData[6] = paymentItem.getGcNo();
							
							paymentItemTableModel.addRow(rowData);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Please input a valid OR number.","Prompt",JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		}
		return viewInformationAbstractAction;
	}
	
	private JLabel getTotalPaymentLabel() {
		if(totalPaymentLabel == null) {
			totalPaymentLabel = new JLabel();
			totalPaymentLabel.setText("Total Payment");
			totalPaymentLabel.setBounds(380, 13, 77, 16);
		}
		return totalPaymentLabel;
	}
	
	private JTextField getTotalPaymentTextField() {
		if(totalPaymentTextField == null) {
			totalPaymentTextField = new JTextField();
			totalPaymentTextField.setBounds(486, 10, 112, 22);
			totalPaymentTextField.setEditable(false);
		}
		return totalPaymentTextField;
	}
	
	private JLabel getChangeLabel() {
		if(changeLabel == null) {
			changeLabel = new JLabel();
			changeLabel.setText("Change");
			changeLabel.setBounds(380, 41, 41, 16);
		}
		return changeLabel;
	}
	
	private JTextField getChangeTextField() {
		if(changeTextField == null) {
			changeTextField = new JTextField();
			changeTextField.setBounds(486, 38, 112, 22);
			changeTextField.setEditable(false);
		}
		return changeTextField;
	}

}
