package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Main;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

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
	private JLabel jLabel2;
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
	private Partial form2;
	private Vector<String> paymentCode;
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
	public Payment(MainWindow frame, String[] val, int index, String status){
		this(frame,status);
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
	
	public Payment(Partial frame) {
		super(frame);
		this.form2 = frame;
		status = "add|partial";
		initGUI();
	}
	
	public Payment(Partial frame, String[] val, int index){
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
					getContentPane().add(jButton2, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Cancel");
					jButton2.setPreferredSize(new java.awt.Dimension(0, 0));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Payment.this.setVisible(false);
	
						}
					});
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("OK");
					jButton1.setPreferredSize(new java.awt.Dimension(0, 0));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Vector<String> values = new Vector<String>();
							values.add(paymentCode.get(jComboBox1.getSelectedIndex()));
							values.add(jComboBox1.getSelectedItem().toString());
							values.add(jTextField1.getText());
							if(jComboBox1.getSelectedItem().equals("Cash")){
								values.add("N/A");
								values.add("N/A");
								values.add("N/A");
								values.add("N/A");
							}
							else if(jComboBox1.getSelectedItem().equals("Credit Card")){
								values.add(jComboBox2.getSelectedItem().toString());
								values.add(jTextField2.getText());
								values.add("N/A");
								values.add("N/A");
							}
							else if(jComboBox1.getSelectedItem().equals("Bank Check")){
								
								values.add("N/A");
								values.add("N/A");
								values.add(jTextField3.getText());
								values.add("N/A");
							}
							else if(jComboBox1.getSelectedItem().equals("Bank Check")){
								
								values.add("N/A");
								values.add("N/A");
								values.add(jTextField3.getText());
								values.add("N/A");
							}
							else{
								values.add("N/A");
								values.add("N/A");
								values.add("N/A");
								values.add(jTextField4.getText());
							}
							
							
							
							
							
							if(jComboBox1.getSelectedItem().equals("Cash")){
								if(jTextField1.getText().length() == 0){
									JOptionPane.showMessageDialog(null, 
											"Please complete required fields", 
											"Warning",JOptionPane.WARNING_MESSAGE);
								}
								else {
									try{
										Double.parseDouble(jTextField1.getText());
										if(status.equals("add")){
											
											if(!form.hasPaymentCash("main")){
											
												form.setPaymentTable(values,"main");
											}
											else{
												JOptionPane.showMessageDialog(null, 
															"There is already payment type of cash. You may just edit that payment type.", 
															"Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else if(status.equals("add|replaced")){
											if(!form.hasPaymentCash("replaced")){
												
												form.setPaymentTable(values,"replaced");
											}
											else{
												JOptionPane.showMessageDialog(null, 
															"There is already payment type of cash. You may just edit that payment type.", 
															"Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else if(status.equals("add|partial")){
											if(!form2.hasPaymentCash()){
												
												form2.setPaymentTable(values);
											}
											else{
												JOptionPane.showMessageDialog(null, 
															"There is already payment type of cash. You may just edit that payment type.", 
															"Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else if(status.equals("edit|partial")){
											form2.setThisPaymentTable(values,index);
										}
										else if(status.equals("edit")){ //Edit
											form.setThisPaymentTable(values, index,"main");
										}
										else if(status.equals("edit|replaced")){ //Edit
											form.setThisPaymentTable(values, index,"replaced");
										}
										Payment.this.dispose();
											
									}catch(Exception ex){
										ex.printStackTrace();
										JOptionPane.showMessageDialog(null, 
												"Please input valid quantity amount.", 
												"Warning",JOptionPane.WARNING_MESSAGE);
									}
									
								}
							}
							else if(jComboBox1.getSelectedItem().equals("Credit Card")){
									if(jTextField1.getText().length() == 0 || 
										jTextField2.getText().length() == 0 || 
										jComboBox2.getSelectedIndex() == 0){
										JOptionPane.showMessageDialog(null, 
												"Please complete required fields", 
												"Warning",JOptionPane.WARNING_MESSAGE);
									}
									else {
										try{
											Double.parseDouble(jTextField1.getText());
											if(status.equals("add")){
												if(!form.hasSameCreditCard(jTextField2.getText(),"main")){
													form.setPaymentTable(values,"main");
												}
												else{
													JOptionPane.showMessageDialog(null, 
																"There is already the same credit card no. You may just edit that payment type.", 
																"Warning",JOptionPane.WARNING_MESSAGE);
												}
											}
											else if(status.equals("add|replaced")){
												if(!form.hasSameCreditCard(jTextField2.getText(),"replaced")){
													form.setPaymentTable(values,"replaced");
												}
												else{
													JOptionPane.showMessageDialog(null, 
																"There is already the same credit card no. You may just edit that payment type.", 
																"Warning",JOptionPane.WARNING_MESSAGE);
												}
											}
											else if(status.equals("add|partial")){
												if(!form2.hasSameCreditCard(jTextField2.getText())){
													form2.setPaymentTable(values);
												}
												else{
													JOptionPane.showMessageDialog(null, 
																"There is already the same credit card no. You may just edit that payment type.", 
																"Warning",JOptionPane.WARNING_MESSAGE);
												}
											}
											else if(status.equals("edit|partial")){
												form2.setThisPaymentTable(values, index);
											}
											else if(status.equals("edit")){ //Edit
												form.setThisPaymentTable(values, index,"main");
											}	
											else if(status.equals("edit|replaced")){ //Edit
												form.setThisPaymentTable(values, index,"replaced");
											}	
											Payment.this.dispose();
											
										}catch(Exception ex){
											ex.printStackTrace();
											JOptionPane.showMessageDialog(null, 
													"Please input valid quantity amount.", 
													"Warning",JOptionPane.WARNING_MESSAGE);
										}
									}
							}
							else if(jComboBox1.getSelectedItem().equals("Bank Check")){
								if(jTextField1.getText().length() == 0 || 
									jTextField3.getText().length() == 0){
									JOptionPane.showMessageDialog(null, 
											"Please complete required fields", 
											"Warning",JOptionPane.WARNING_MESSAGE);
								}
								else {
									try{
										Double.parseDouble(jTextField1.getText());
										if(status.equals("add")){
											if(!form.hasSameBankCheck(jTextField3.getText(),"main")){
												form.setPaymentTable(values,"main");
											}
											else{
												JOptionPane.showMessageDialog(null, 
															"There is already the same bank check no. You may just edit that payment type.", 
															"Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else if(status.equals("add|replaced")){
											if(!form.hasSameBankCheck(jTextField3.getText(),"replaced")){
												form.setPaymentTable(values,"replaced");
											}
											else{
												JOptionPane.showMessageDialog(null, 
															"There is already the same bank check no. You may just edit that payment type.", 
															"Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else if(status.equals("add|parital")){
											if(!form2.hasSameBankCheck(jTextField3.getText())){
												form2.setPaymentTable(values);
											}
											else{
												JOptionPane.showMessageDialog(null, 
															"There is already the same bank check no. You may just edit that payment type.", 
															"Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else if(status.equals("edit|partial")){
											form2.setThisPaymentTable(values, index);
										}
										else if(status.equals("edit")){ //Edit
											form.setThisPaymentTable(values, index,"main");
										}
										else if(status.equals("edit|replaced")){ //Edit
											form.setThisPaymentTable(values, index,"replaced");
										}
										Payment.this.dispose();
										
									}catch(Exception ex){
										ex.printStackTrace();
										JOptionPane.showMessageDialog(null, 
												"Please input valid quantity amount.", 
												"Warning",JOptionPane.WARNING_MESSAGE);
									}
								}
							}
							else if(jComboBox1.getSelectedItem().equals("Gift Certificate")){
								if(jTextField1.getText().length() == 0 || 
										jTextField4.getText().length() == 0){
									JOptionPane.showMessageDialog(null, 
											"Please complete required fields", 
											"Warning",JOptionPane.WARNING_MESSAGE);
								}
								else {
									try{
										Double.parseDouble(jTextField1.getText());
										if(status.equals("add")){
											if(!form.hasSameGiftCertificate(jTextField4.getText(),"main")){
												form.setPaymentTable(values,"main");
											}
											else{
												JOptionPane.showMessageDialog(null, 
															"There is already the gift certificate no. You may just edit that payment type.", 
															"Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else if(status.equals("add|replaced")){
											if(!form.hasSameGiftCertificate(jTextField4.getText(),"replaced")){
												form.setPaymentTable(values,"replaced");
											}
											else{
												JOptionPane.showMessageDialog(null, 
															"There is already the gift certificate no. You may just edit that payment type.", 
															"Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else if(status.equals("add|partial")){
											if(!form2.hasSameGiftCertificate(jTextField4.getText())){
												
												form2.setPaymentTable(values);
											
											
											}
											else{
												JOptionPane.showMessageDialog(null, 
															"There is already the gift certificate no. You may just edit that payment type.", 
															"Warning",JOptionPane.WARNING_MESSAGE);
											}
										}
										else if(status.equals("edit|partial")){
											form2.setThisPaymentTable(values, index);
										}
										else if(status.equals("edit")){ //Edit
											form.setThisPaymentTable(values, index,"main");
										}
										else if(status.equals("edit|replaced")){ //Edit
											form.setThisPaymentTable(values, index,"replaced");
										}
										Payment.this.dispose();
										
									}catch(Exception ex){
										ex.printStackTrace();
										JOptionPane.showMessageDialog(null, 
												"Please input valid quantity amount.", 
												"Warning",JOptionPane.WARNING_MESSAGE);
									}
								}
							}
							if(status.equals("add") || status.equals("edit")){
								form.updateAmounts();
							}
							else if(status.equals("add|partial") || status.equals("edit|partial")){ 
								form2.updateAmounts();
							}
							else{
								form.updateReturnedAmounts();
							}
							
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
					getContentPane().add(jComboBox1, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox1.setModel(jComboBox1Model);
					jComboBox1.setPreferredSize(new java.awt.Dimension(0, 0));
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
					getContentPane().add(jTextField1, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField1.setPreferredSize(new java.awt.Dimension(0, 0));
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("Add Payments");
					jLabel2.setPreferredSize(new java.awt.Dimension(0, 0));
					jLabel2.setFont(new java.awt.Font("Tahoma",1,16));
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("Amount");
					jLabel1.setPreferredSize(new java.awt.Dimension(0, 0));
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3, new AnchorConstraint(101, 345, 166, 126, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("Payment Type");
					jLabel3.setPreferredSize(new java.awt.Dimension(84, 27));
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel4.setText("Credit Card No");
					jLabel4.setPreferredSize(new java.awt.Dimension(0, 0));
				}
				{
					jTextField2 = new JTextField();
					getContentPane().add(jTextField2, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField2.setPreferredSize(new java.awt.Dimension(0, 0));
					jTextField2.setEnabled(false);
				}
				{
					jLabel5 = new JLabel();
					getContentPane().add(jLabel5, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel5.setText("Check Number");
					jLabel5.setPreferredSize(new java.awt.Dimension(0, 0));
				}
				{
					jTextField3 = new JTextField();
					getContentPane().add(jTextField3, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField3.setPreferredSize(new java.awt.Dimension(0, 0));
					jTextField3.setEnabled(false);
				}
				{
					ComboBoxModel jComboBox2Model = new DefaultComboBoxModel(
						new String[] {"", "Visa", "Master Card","Diners","EPS", "BPI","AMEX" });
					jComboBox2 = new JComboBox();
					getContentPane().add(jComboBox2, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox2.setModel(jComboBox2Model);
					jComboBox2.setPreferredSize(new java.awt.Dimension(0, 0));
					jComboBox2.setEnabled(false);
				}
				{
					jLabel6 = new JLabel();
					getContentPane().add(jLabel6, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel6.setText("Card Type");
					jLabel6.setPreferredSize(new java.awt.Dimension(0, 0));
				}
				{
					jLabel7 = new JLabel();
					getContentPane().add(jLabel7, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel7.setText("Gift Cert No");
					jLabel7.setPreferredSize(new java.awt.Dimension(0, 0));
				}
				{
					jTextField4 = new JTextField();
					getContentPane().add(jTextField4, new AnchorConstraint(1, 1, 1, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField4.setPreferredSize(new java.awt.Dimension(0, 0));
					jTextField4.setEnabled(false);
				}
			}
			this.setSize(400, 454);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
