package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bus.DiscountService;
import bus.ProductService;
import domain.InvoiceItem;
import domain.PaymentItem;
import domain.Product;
import forms.invoice.InvoicePanel;


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
public class PaymentDialog extends javax.swing.JDialog {
	
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JComboBox jComboBox2;
	private JLabel jLabel7;
	private JButton jButton1;
	private JButton jButton2;
	private JTextField txtGiftCertificate;
	private JLabel jLabel6;
	private JTextField txtBankCheck;
	private JComboBox cbCreditCardType;
	private JTextField txtCreditCard;
	private JTextField txtAmount;
	private static PaymentDialog paymentDialog;
	private static Object invoker;
	private static String actionProdCode;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				JFrame frame = new JFrame();
//				PaymentDialog inst = new PaymentDialog(frame);
//				inst.setVisible(true);
//			}
//		});
	}
	
	@SuppressWarnings("static-access")
	public PaymentDialog(JFrame frame, Object invoker, String action) {
		super(frame);
		this.invoker = invoker;
		this.actionProdCode = action;
		initGUI();
	}
	
	public static PaymentDialog getPaymentDialog(JFrame frame, Object invoker, String action){
		if(paymentDialog == null){
			paymentDialog = new PaymentDialog(frame, invoker,action); 
		}
		else{
			if(!invoker.equals(getInvoker()) || !action.equals(getAction())){
				paymentDialog = null;
				paymentDialog = new PaymentDialog(frame, invoker,action);
			}
		}
		return paymentDialog;
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Payments");
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("Payment Options");
					jLabel1.setBounds(12, 12, 152, 24);
					jLabel1.setFont(new java.awt.Font("Tahoma",0,18));
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("Amount");
					jLabel2.setBounds(12, 97, 62, 16);
				}
				{
					txtAmount = new JTextField();
					getContentPane().add(txtAmount);
					txtAmount.setBounds(12, 114, 353, 22);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("Credit Card");
					jLabel3.setBounds(12, 142, 82, 16);
				}
				{
					txtCreditCard = new JTextField();
					getContentPane().add(txtCreditCard);
					txtCreditCard.setEnabled(false);
					txtCreditCard.setBounds(12, 158, 353, 22);
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4);
					jLabel4.setText("Credit Card Type");
					jLabel4.setBounds(12, 192, 89, 16);
				}
				{
					ComboBoxModel jComboBox1Model = 
						new DefaultComboBoxModel(
								new String[] {"--Please Select--", "Visa", "Master Card","Diners","EPS", "BPI","AMEX" });
					cbCreditCardType = new JComboBox();
					getContentPane().add(cbCreditCardType);
					cbCreditCardType.setModel(jComboBox1Model);
					cbCreditCardType.setEnabled(false);
					cbCreditCardType.setBounds(12, 211, 353, 22);
				}
				{
					jLabel5 = new JLabel();
					getContentPane().add(jLabel5);
					jLabel5.setText("Bank Check");
					jLabel5.setBounds(12, 245, 62, 16);
				}
				{
					txtBankCheck = new JTextField();
					getContentPane().add(txtBankCheck);
					txtBankCheck.setEnabled(false);
					txtBankCheck.setBounds(12, 261, 353, 22);
				}
				{
					jLabel6 = new JLabel();
					getContentPane().add(jLabel6);
					jLabel6.setText("Gift Certificate");
					jLabel6.setBounds(12, 295, 76, 16);
				}
				{
					txtGiftCertificate = new JTextField();
					getContentPane().add(txtGiftCertificate);
					txtGiftCertificate.setEnabled(false);
					txtGiftCertificate.setBounds(12, 311, 353, 22);
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("OK");
					jButton1.setBounds(101, 354, 58, 24);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Integer paymentType = jComboBox2.getSelectedIndex()+1;
							Double amount = Double.parseDouble(txtAmount.getText());
							String creditCard = txtCreditCard.getText().toString();
							String creditCardType = cbCreditCardType.getSelectedItem().toString();
							String bankCheck = txtBankCheck.getText().toString();
							String giftCertificate = txtGiftCertificate.getText().toString();
							
							PaymentItem paymentItem = new PaymentItem();
							paymentItem.setPaymentCode(paymentType);
							paymentItem.setAmount(amount);
							paymentItem.setCardNo(("".equals(creditCard)?"N/A":creditCard));
							if(cbCreditCardType.getSelectedIndex() == 0){
								paymentItem.setCardType("N/A");
							}
							else{
								paymentItem.setCardType(creditCardType);
							}
							paymentItem.setCheckNo(("".equals(bankCheck)?"N/A":bankCheck));
							paymentItem.setGcNo(("".equals(giftCertificate)?"N/A":giftCertificate));
							
							if(invoker instanceof InvoicePanel){
								System.out.println("invoking...");
								InvoicePanel invoicePanel = (InvoicePanel)invoker;
							
								if(actionProdCode.equals("add")){
									if(invoicePanel.getPaymentItemRow(paymentItem.getPaymentCode()) == null){
										invoicePanel.addPaymentItem(paymentItem);
										invoicePanel.updatePaymentAmounts();
										paymentDialog.setVisible(false);
									}
									else{
										JOptionPane.showMessageDialog(PaymentDialog.this,"Payment already exists  in the table.","Prompt",JOptionPane.ERROR_MESSAGE);
									}
								}
								else { //edit
									invoicePanel.editPaymentItem(paymentItem, actionProdCode);
									paymentDialog.setVisible(false);
								}
									
								
							}
						}
					});

				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("Cancel");
					jButton2.setBounds(184, 354, 80, 24);
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							paymentDialog.setVisible(false);
						}
					});
				}
				{
					jLabel7 = new JLabel();
					getContentPane().add(jLabel7);
					jLabel7.setText("Payment Type");
					jLabel7.setBounds(11, 45, 89, 16);
				}
				{
					ComboBoxModel jComboBox2Model = 
						new DefaultComboBoxModel(
								new String[] { "Cash", "Credit Card","Bank Check","Gift Certificate" });
					jComboBox2 = new JComboBox();
					getContentPane().add(jComboBox2);
					jComboBox2.setModel(jComboBox2Model);
					jComboBox2.setBounds(12, 63, 352, 22);
					jComboBox2.addActionListener(new PaymentTypeListener());

				}
			}
			this.setSize(393, 440);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class PaymentTypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox combo = (JComboBox) e.getSource();
			if(combo.getSelectedItem().equals("Cash")){
				txtCreditCard.setEnabled(false);
				txtCreditCard.setText("");
				cbCreditCardType.setEnabled(false);
				cbCreditCardType.setSelectedIndex(0);
				txtBankCheck.setEnabled(false);
				txtBankCheck.setText("");
				txtGiftCertificate.setEnabled(false);
				txtGiftCertificate.setText("");
			}
			else if(combo.getSelectedItem().equals("Credit Card")){
				txtCreditCard.setEnabled(true);
				cbCreditCardType.setEnabled(true);
				txtBankCheck.setEnabled(false);
				txtBankCheck.setText("");
				txtGiftCertificate.setEnabled(false);
				txtGiftCertificate.setText("");
			}
			else if(combo.getSelectedItem().equals("Gift Certificate")){
				txtCreditCard.setEnabled(false);
				txtCreditCard.setText("");
				cbCreditCardType.setEnabled(false);
				cbCreditCardType.setSelectedIndex(0);
				txtBankCheck.setEnabled(false);
				txtBankCheck.setText("");
				txtGiftCertificate.setEnabled(true);
			}
			else if(combo.getSelectedItem().equals("Bank Check")){
				txtCreditCard.setEnabled(false);
				txtCreditCard.setText("");
				cbCreditCardType.setEnabled(false);
				cbCreditCardType.setSelectedIndex(0);
				txtBankCheck.setEnabled(true);
				txtGiftCertificate.setEnabled(false);
				txtGiftCertificate.setText("");
			}
			
		}
		
	}

	public static Object getInvoker() {
		return invoker;
	}

	public static String getAction() {
		return actionProdCode;
	}
	
	
	public void setPaymentType(String s){
		jComboBox2.setSelectedItem(s);
	}	

	public void setAmount(String s){
		txtAmount.setText(s);
	}
	
	public void setCreditCard(String s){
		txtCreditCard.setText(s);
	}
	
	public void setCreditType(String s){
		cbCreditCardType.setSelectedItem(s);
	}
	
	public void setBankCheck(String s){
		txtBankCheck.setText(s);
	}
	
	public void setGiftCheck(String s){
		txtGiftCertificate.setText(s);
	}
}

