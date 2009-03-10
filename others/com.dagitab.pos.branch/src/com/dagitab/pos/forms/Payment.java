package com.dagitab.pos.forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.dagitab.pos.domain.PaymentItem;
import com.dagitab.pos.forms.partial.PartialDialog;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;


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
public class Payment extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel paymentLabel;
	private JLabel jLabel4;
	private JButton jButton2;
	private JButton jButton1;
	private JTextField jTextField4;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JComboBox jComboBox2;
	private JTextField jTextField3;
	private JLabel jLabel5;
	private JTextField jTextField2;
	private JComboBox jComboBox1;
	private JLabel jLabel3;
	private JTextField jTextField1;
	private MainWindow form;
	private PartialDialog form2;
	private Vector<String> paymentCode;
	private static Object invoker;
	private String[] textval;
	private int index;
	private String status;
	private double amount;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		payments inst = new payments(frame);
//		inst.setVisible(true);
	}
	
	public Payment(MainWindow frame, String status) {
		super(frame);
		this.form = frame;
		this.status = status;
		initGUI();
	
	}
	public Payment(MainWindow frame,  String status, double amount) {
		super(frame);
		this.form = frame;
		this.status = status;
		this.amount = amount;
		initGUI();
	
	}
	@SuppressWarnings("static-access")
	public Payment(MainWindow frame, Object invoker, String[] val, int index, String status){
		this(frame,status);
		textval = val;
		this.invoker = invoker;
		this.index = index;
		
		//set values
		jComboBox1.setSelectedItem(val[1]);
		jTextField1.setText(val[2]);
		jTextField2.setText(val[4]);
		jComboBox2.setSelectedItem(val[3]);
		jTextField3.setText(val[5]);
		jTextField4.setText(val[6]);
	}
	
	public Payment(PartialDialog frame) {
		super(frame);
		this.form2 = frame;
		status = "add|partial";
		initGUI();
	}
	
	public Payment(PartialDialog frame, String[] val, int index){
		this(frame);
		status = "edit|partial";
		textval = val;
		this.index = index;
		
		//set values
		jComboBox1.setSelectedItem(val[1]);
		jTextField1.setText(val[2]);
		jTextField2.setText(val[4]);
		jComboBox2.setSelectedItem(val[3]);
		jTextField3.setText(val[5]);
		jTextField4.setText(val[6]);
	}
	
	
	private void initGUI() {
		try {
			{
				this.setTitle("Add Payment");
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setFocusTraversalKeysEnabled(false);
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				this.setModal(true);
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(864, 753, 923, 524, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Cancel");
					jButton2.setPreferredSize(new java.awt.Dimension(90, 24));
					jButton2.setOpaque(false);
					jButton2.setFont(new java.awt.Font("Arial",0,11));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Payment.this.setVisible(false);
	
						}
					});
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(867, 437, 923, 223, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("OK");
					jButton1.setPreferredSize(new java.awt.Dimension(84, 23));
					jButton1.setOpaque(false);
					jButton1.setFont(new java.awt.Font("Arial",0,11));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Integer paymentType = jComboBox1.getSelectedIndex()+1;
							Double amount = Double.parseDouble(jTextField1.getText());
							String creditCard = jTextField2.getText().toString();
							String creditCardType = jComboBox2.getSelectedItem().toString();
							String bankCheck = jTextField3.getText().toString();
							String giftCertificate = jTextField4.getText().toString();
							
							PaymentItem paymentItem = new PaymentItem();
							paymentItem.setPaymentCode(paymentType);
							paymentItem.setAmount(amount);
							paymentItem.setCardNo(creditCard);
/*							if(jComboBox2.getSelectedIndex() == 0){
								paymentItem.setCardType("");
							}
							else{
								paymentItem.setCardType(creditCardType);
							}
							paymentItem.setCheckNo(bankCheck);
							paymentItem.setGcNo(giftCertificate);
							
							if(invoker instanceof InvoicePanel){
								logger.info("invoking...");
								InvoicePanel invoicePanel = (InvoicePanel)invoker;
							
								if(status.equals("edit")){
									if(invoicePanel.getPaymentItemRow(paymentItem.getPaymentCode()) != null){
										invoicePanel.editPaymentItem(paymentItem);
										payment.setVisible(false);
									}
									else{
										JOptionPane.showMessageDialog(Payment.this,"Payment already exists  in the table.","Prompt",JOptionPane.ERROR_MESSAGE);
									}
								}
								else { //edit
									//invoicePanel.editInvoiceItem(invoiceItem, action);
									paymentDialog.setVisible(false);
								}
									
								
							}
						*/
						}
					});
				}
				{
					ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM pay_type_lu");
					Vector<String> payments = new Vector<String>();
					paymentCode = new Vector<String>();
					while(rs.next()){
						payments.add(rs.getString("Name"));
						paymentCode.add(rs.getString("PT_CODE"));
					}
					
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
						payments);
					jComboBox1 = new JComboBox();
					getContentPane().add(jComboBox1, new AnchorConstraint(152, 970, 198, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox1.setModel(jComboBox1Model);
					jComboBox1.setPreferredSize(new java.awt.Dimension(368, 19));
					jComboBox1.setOpaque(false);
					jComboBox1.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent evt) {
							if(jComboBox1.getSelectedItem().toString().equals("Cash")){
								jTextField2.setEnabled(false);
								jTextField3.setEnabled(false);
								jTextField4.setEnabled(false);
								jComboBox2.setEnabled(false);
								jTextField2.setText("");
								jTextField3.setText("");
								jTextField4.setText("");
								jTextField1.setText("");
								jComboBox2.setSelectedIndex(0);
							}
							else if(jComboBox1.getSelectedItem().toString().equals("Credit Card")){
								jTextField2.setEnabled(true);
								jTextField3.setEnabled(false);
								jTextField4.setEnabled(false);
								jTextField3.setText("");
								jTextField4.setText("");
								if(amount > 0){
									jTextField1.setText(amount+"");
								}
								jComboBox2.setEnabled(true);
							}
							else if(jComboBox1.getSelectedItem().toString().equals("Gift Certificate")){
								jTextField2.setEnabled(false);
								jTextField3.setEnabled(false);
								jTextField2.setText("");
								jTextField3.setText("");
								jTextField4.setEnabled(true);
								jComboBox2.setEnabled(false);
								jTextField1.setText("");
								jComboBox2.setSelectedIndex(0);
							}
							else if(jComboBox1.getSelectedItem().toString().equals("Bank Check")){
								jTextField2.setEnabled(false);
								jTextField2.setText("");
								jTextField3.setEnabled(true);
								jTextField4.setEnabled(false);
								jTextField4.setText("");
								if(amount > 0){
									jTextField1.setText(amount+"");
								}
								jComboBox2.setEnabled(false);
								jComboBox2.setSelectedIndex(0);
							}
						}
					});
				}
				{
					jTextField1 = new JTextField();
					getContentPane().add(jTextField1, new AnchorConstraint(261, 970, 305, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField1.setPreferredSize(new java.awt.Dimension(368, 18));
				}
				{
					paymentLabel = new JLabel();
					getContentPane().add(paymentLabel, new AnchorConstraint(30, 422, 76, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					paymentLabel.setText("Add Payments");
					paymentLabel.setPreferredSize(new java.awt.Dimension(153, 19));
					paymentLabel.setFont(new java.awt.Font("Tahoma",1,16));
					paymentLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "paymentLabel");
					paymentLabel.getActionMap().put("paymentLabel",getPaymentLabelAbstractAction() );
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1, new AnchorConstraint(210, 207, 256, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("Amount");
					jLabel1.setPreferredSize(new java.awt.Dimension(69, 19));
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3, new AnchorConstraint(105, 235, 137, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("Payment Type");
					jLabel3.setPreferredSize(new java.awt.Dimension(80, 13));
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4, new AnchorConstraint(332, 340, 378, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel4.setText("Credit Card No");
					jLabel4.setPreferredSize(new java.awt.Dimension(121, 19));
				}
				{
					jTextField2 = new JTextField();
					getContentPane().add(jTextField2, new AnchorConstraint(385, 970, 429, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField2.setPreferredSize(new java.awt.Dimension(368, 18));
				}
				{
					jLabel5 = new JLabel();
					getContentPane().add(jLabel5, new AnchorConstraint(590, 274, 636, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel5.setText("Check Number");
					jLabel5.setPreferredSize(new java.awt.Dimension(95, 19));
				}
				{
					jTextField3 = new JTextField();
					getContentPane().add(jTextField3, new AnchorConstraint(638, 970, 684, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField3.setPreferredSize(new java.awt.Dimension(368, 19));
				}
				{
					ComboBoxModel jComboBox2Model = new DefaultComboBoxModel(
						new String[] {"", "Visa", "Master Card","Diners","EPS", "BPI","AMEX" });
					jComboBox2 = new JComboBox();
					getContentPane().add(jComboBox2, new AnchorConstraint(507, 970, 560, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox2.setModel(jComboBox2Model);
					jComboBox2.setPreferredSize(new java.awt.Dimension(368, 22));
				}
				{
					jLabel6 = new JLabel();
					getContentPane().add(jLabel6, new AnchorConstraint(458, 235, 507, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel6.setText("Card Type");
					jLabel6.setPreferredSize(new java.awt.Dimension(80, 20));
				}
				{
					jLabel7 = new JLabel();
					getContentPane().add(jLabel7, new AnchorConstraint(709, 235, 743, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel7.setText("Gift Cert No");
					jLabel7.setPreferredSize(new java.awt.Dimension(80, 14));
				}
				{
					jTextField4 = new JTextField();
					getContentPane().add(jTextField4, new AnchorConstraint(757, 970, 806, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField4.setPreferredSize(new java.awt.Dimension(368, 20));
				}
			}
			this.setSize(400, 454);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	private AbstractAction getPaymentLabelAbstractAction() {
		AbstractAction paymentLabelAction = new AbstractAction("Add Payments", null) {
			
			public void actionPerformed(ActionEvent evt) {
				Payment.this.dispose();
			}
		};
		return paymentLabelAction;
	}

}
