package forms;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.DBManager;

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
public class TransactionItems extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel4;
	private JTable jTable1;
	private JComboBox jComboBox1;
	private JLabel jLabel5;
	private JButton jButton2;
	private JTextField jTextField2;
	private JButton jButton1;
	private JScrollPane jScrollPane1;
	private JTextField jTextField1;
	private JLabel jLabel3;
	private JLabel jLabel30;
	private MainWindow form;
	private DBManager db;
	private String orNum;
	private String storeCode;
	private Vector<String> returnReasonCode;
	private Vector<String> returnCosts;
	private Vector<Vector<String>> returnTableValues;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		TransactionItems inst = new TransactionItems(frame);
//		inst.setVisible(true);
	}
	
	public TransactionItems(MainWindow frame,DBManager db, String orNum, String storeCode, Vector<Vector<String>> returnTableValues) {
		super(frame);
		this.form = frame;
		this.orNum = orNum;
		this.db = db;
		this.storeCode = storeCode;
		this.returnTableValues = returnTableValues;
		initGUI();
	}
	
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setTitle("Transaction Items");
			this.setModal(true);
			{
				ResultSet rs = db.executeQuery("SELECT * FROM return_reason_lu");
				returnReasonCode = new Vector<String>();
				Vector<String> values = new Vector<String>();
				while(rs.next()){
					returnReasonCode.add(rs.getString(1));
					values.add(rs.getString(2));
				}
				ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
				values);
				jComboBox1 = new JComboBox();
				getContentPane().add(jComboBox1, new AnchorConstraint(754, 495, 812, 242, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setPreferredSize(new java.awt.Dimension(161, 28));
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(885, 671, 943, 517, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Cancel");
				jButton2.setPreferredSize(new java.awt.Dimension(98, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						TransactionItems.this.dispose();
					}
				});
			}
			{
				jTextField2 = new JTextField();
				getContentPane().add(jTextField2, new AnchorConstraint(754, 967, 812, 781, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField2.setPreferredSize(new java.awt.Dimension(119, 28));
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(885, 495, 943, 319, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Return Item");
				jButton1.setPreferredSize(new java.awt.Dimension(112, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(jTextField2.getText().length() != 0){
							try{
								int quantity = Integer.parseInt(jTextField2.getText());
								int origQuantity = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
								if(quantity > origQuantity){
									JOptionPane.showMessageDialog(null, 
											"The input quantity is greater than the original quantity indicated from the transaction", 
											"Warning",JOptionPane.WARNING_MESSAGE);
								}
								else{
//									int newQuantity = origQuantity - quantity;
									Vector<String> values = new Vector<String>();
									values.add(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString());
									values.add(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
									values.add(quantity+"");
									values.add(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
									values.add(jTable1.getValueAt(jTable1.getSelectedRow(),4).toString());
									values.add(jTable1.getValueAt(jTable1.getSelectedRow(),5).toString());
									values.add(jTable1.getValueAt(jTable1.getSelectedRow(),6).toString());
									values.add(returnReasonCode.get(jComboBox1.getSelectedIndex()));
									values.add(returnCosts.get(jTable1.getSelectedRow()));
									if(!form.hasReturnedItem(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString(),
															returnReasonCode.get(jComboBox1.getSelectedIndex()))){
										form.setReturnedTable(values);
									}
									else{
										JOptionPane.showMessageDialog(null, 
												"The same product code and reason for return is already in the returned item list.\n " +
												"You may just delete the item from the list and re-enter the new product info.", 
												"Warning",JOptionPane.WARNING_MESSAGE);
									}
									
									TransactionItems.this.dispose();
									//jTable1.setValueAt(newQuantity, jTable1.getSelectedRow(), 2);
								}
							}
							catch(NumberFormatException e){
								JOptionPane.showMessageDialog(null, 
										"Please input a number for the quantity to be returned.", 
										"Warning",JOptionPane.WARNING_MESSAGE);
							}
							catch(ArrayIndexOutOfBoundsException e){
								JOptionPane.showMessageDialog(null, 
										"Please choose an item from the list.", 
										"Warning",JOptionPane.WARNING_MESSAGE);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, 
									"Please enter the quantity to be returned.", 
									"Warning",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, new AnchorConstraint(261, 967, 740, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane1.setPreferredSize(new java.awt.Dimension(595, 231));
				{
					ResultSet rs = db.executeQuery("SELECT invoice_item.PROD_CODE, products_lu.NAME, QUANTITY, invoice_item.SELL_PRICE, DISC_RATE,deferred,invoice_item.DISC_CODE, invoice_item.COST " +
							"FROM invoice_item, products_lu, discount_lu " +
							"WHERE invoice_item.OR_NO = '"+orNum+"' " +
							"AND invoice_item.PROD_CODE = products_lu.PROD_CODE " +
							"AND invoice_item.DISC_CODE = discount_lu.DISC_NO AND STORE_CODE = '"+storeCode+"'");
					Vector<Vector<String>> data = new Vector<Vector<String>>();
					returnCosts = new Vector<String>();
					while(rs.next()){
						Vector<String> rowData = new Vector<String>();
						rowData.add(rs.getString(1)); //product code
						rowData.add(rs.getString(2)); //prod name
						rowData.add(rs.getString(3)); //qty
						rowData.add(rs.getString(4)); //sell price
						ResultSet curr = db.executeQuery("SELECT SELL_PRICE FROM products_lu WHERE PROD_CODE = \""+rs.getString(1)+"\"" );
						if(curr.next()){
							rowData.add(curr.getString(1)); //curr price
						}
						if(rs.getString(6).equals("1")){
							rowData.add("Yes");
						}
						else{
							rowData.add("No");
						}
						rowData.add(rs.getString(7));
						returnCosts.add(rs.getString(8));
						data.add(rowData);
					}
					
					Hashtable<String,String>  hash = new Hashtable<String,String>();
					for(int i = 0; i<returnTableValues.size(); i++){
						if(hash.containsKey(returnTableValues.get(i).get(0).toString())){
							int updatedqty = Integer.parseInt(returnTableValues.get(i).get(2).toString());
							int oldqty = Integer.parseInt(hash.get(returnTableValues.get(i).get(0).toString()));
							hash.remove(returnTableValues.get(i).get(0).toString());
							int newqty = oldqty+updatedqty;
							hash.put(returnTableValues.get(i).get(0).toString(), newqty+"");
						}
						else{
							hash.put(returnTableValues.get(i).get(0).toString(), returnTableValues.get(i).get(2).toString());
						}
					}
					
					for(int i =0; i<data.size(); i++){
						if(hash.containsKey(data.get(i).get(0))){
							Vector<String> insideValues = data.get(i);
							int oldqty = Integer.parseInt(insideValues.get(2));
							int newqty = Integer.parseInt(hash.get(data.get(i).get(0)));
							int updatedqty = oldqty - newqty;
							insideValues.set(2,updatedqty+"");
							data.set(i, insideValues);
						}
					}
					
					//fill the String[][] with values
					String[][] dataSource = new String[data.size()][7];
					for(int i = 0; i<data.size(); i++){
						for(int j = 0; j< data.get(i).size(); j++){
							dataSource[i][j] = data.get(i).get(j);
						}
					}
					TableModel jTable1Model = new DefaultTableModel(
						dataSource,
						new String[] { "Product Code", "Product Name","Quantity","Price Sold","Current Price","Deferred","Disc Code" });
					jTable1 = new JTable(){
						@Override
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					};
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(jTable1Model);
				}
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1, new AnchorConstraint(131, 374, 174, 143, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField1.setPreferredSize(new java.awt.Dimension(147, 21));
				String temp = orNum+"";
				int len = 10-temp.length();
				for(int i = 0; i<len; i++){
					temp = "0"+temp;
				}
				jTextField1.setText(storeCode +"-"+temp);
				jTextField1.setEditable(false);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3, new AnchorConstraint(116, 143, 203, 22, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel3.setText("OR Number");
				jLabel3.setPreferredSize(new java.awt.Dimension(77, 42));
				jLabel3.setFont(new java.awt.Font("Tahoma",1,11));
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(47, 1011, 94, 66, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setText("you may view items of the transaction indicated by the OR number. You may choose the item which will be returned.");
				jLabel2.setPreferredSize(new java.awt.Dimension(602, 21));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(16, 506, 63, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Items of Transaction");
				jLabel1.setPreferredSize(new java.awt.Dimension(315, 21));
				jLabel1.setFont(new java.awt.Font("Tahoma",1,18));
			}
			{
				jLabel30 = new JLabel();
				getContentPane().add(jLabel30, new AnchorConstraint(189, 341, 290, 22, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel30.setText("Items of Transaction");
				jLabel30.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/items.png")));
				jLabel30.setFont(new java.awt.Font("Tahoma",1,14));
				jLabel30.setPreferredSize(new java.awt.Dimension(203, 49));
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4, new AnchorConstraint(754, 770, 812, 550, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel4.setText("Quantity to be Returned:");
				jLabel4.setFont(new java.awt.Font("Tahoma",1,11));
				jLabel4.setPreferredSize(new java.awt.Dimension(140, 28));
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5, new AnchorConstraint(754, 209, 812, 33, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel5.setText("Reason For Return:");
				jLabel5.setFont(new java.awt.Font("Tahoma",1,11));
				jLabel5.setPreferredSize(new java.awt.Dimension(112, 28));
			}
			this.setSize(645, 517);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
