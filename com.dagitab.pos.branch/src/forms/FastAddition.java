package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;

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
public class FastAddition extends javax.swing.JDialog {
	private JButton jButton1;
	private JButton jButton3;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JLabel jLabel3;
	private JButton jButton2;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JTextField jTextField1;
	private Vector<Vector<String>> productItems;
	private MainWindow frame;
	
	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		FastAddition inst = new FastAddition(frame);
		inst.setVisible(true);
	}
	
	public FastAddition(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public FastAddition(MainWindow frame){
		super(frame);
		this.frame = frame;
		productItems = new Vector<Vector<String>>();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Fast Addition");
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setModal(true);
				this.addKeyListener(new KeyAdapter() {
					
				});
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("OK");
					jButton1.setBounds(343, 490, 63, 28);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							frame.synchronizeWithFastAddition(productItems);
							FastAddition.this.dispose();
						}
					});
				}
				{
					ResultSet rs = Main.getDBManager().executeQuery("SELECT p.PROD_CODE FROM products_lu p, inventory_lu i WHERE p.PROD_CODE = i.PROD_CODE");
					List<String> list = new ArrayList<String>();
					
					while(rs.next()){
						list.add(rs.getString(1));
					}
					
//					jTextField1 = new Java2sAutoTextField(list);
					jTextField1 = new JTextField();
					getContentPane().add(jTextField1);
					jTextField1.setBounds(245, 56, 224, 28);
					jTextField1.addKeyListener(new KeyAdapter() {
						 @Override
						public void keyPressed(KeyEvent e) {
							 int key = e.getKeyCode();
						     if (key == KeyEvent.VK_ENTER){
						    	 	checkProductTable();
						    	 	jTextField1.setText("");
						     }
						}
					});
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("Product Code");
					jLabel1.setBounds(147, 56, 98, 28);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("Fast Addition");
					jLabel2.setBounds(14, 14, 182, 28);
					jLabel2.setFont(new java.awt.Font("Tahoma",1,18));
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("Insert");
					jButton2.setBounds(476, 56, 63, 28);
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							checkProductTable();
						}
					});
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("Item List");
					jLabel3.setBounds(14, 91, 63, 28);
					jLabel3.setFont(new java.awt.Font("Tahoma",1,14));
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1);
					jScrollPane1.setBounds(14, 126, 728, 322);
					{
						TableModel jTable1Model = new DefaultTableModel(
							null,
									new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code" });
						jTable1 = new JTable();
						jScrollPane1.setViewportView(jTable1);
						jTable1.setModel(jTable1Model);
					}
				}
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3);
					jButton3.setText("Delete Product");
					jButton3.setBounds(616, 448, 126, 28);
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							int selected = jTable1.getSelectedRow();
							int qty = Integer.parseInt(productItems.get(selected).get(2).toString());
							if(qty == 1){
								productItems.remove(selected);
							}
							else{
								String input = JOptionPane.showInputDialog(null, "Quantity wished to be removed?");
								Vector<String>row = productItems.get(selected);
								int newqty = qty-Integer.parseInt(input);
								if(newqty>0){
									row.set(2, newqty+"");
									productItems.set(selected, row);
								}
								else if(newqty == 0){
									productItems.remove(selected);
								}
								else{
									JOptionPane.showMessageDialog(null, "Please input valid quantity to be removed","Error",JOptionPane.INFORMATION_MESSAGE);
								}
								
							}
							insertToProdutTable(null);
						}
					});
				}
			}
			this.setSize(764, 566);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertToProdutTable(Vector<String> row){
		if(row != null) {
			productItems.add(row);
		}
		
		Object[][] data = new Object[productItems.size()][7];
		for(int i = 0; i< productItems.size(); i++)
		{
			for(int j=0; j<7; j++){
				data[i][j] = productItems.get(i).get(j);
			}
		}
		
		TableModel jTable1Model = new DefaultTableModel(
				data,
				new String[] { "Product Code", "Product Name","Quantity","Current Price","Selling Price","Deferred","Disc Code"  });
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
	
	private void checkProductTable(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM products_lu WHERE PROD_CODE = \""+jTextField1.getText()+"\"");
		try {
			if(rs.next()){
				
				boolean hasProductinTable = false;
				for(int i = 0; i<productItems.size(); i++){
					if(productItems.get(i).get(0).toString().equals(jTextField1.getText())){
						int soldquantity = Integer.parseInt(productItems.get(i).get(2));
						soldquantity++;
						productItems.get(i).set(2, soldquantity+"");
						insertToProdutTable(null); //edit table
						hasProductinTable = true;
						break;
						
					}
				}
				if(!hasProductinTable){
					Vector<String> row = new Vector<String>();
					row.add(jTextField1.getText());
					row.add(rs.getString("NAME"));
					row.add("1");
					row.add(rs.getString("SELL_PRICE"));
					row.add(rs.getString("SELL_PRICE"));
					row.add("No");
					row.add("1");
					insertToProdutTable(row);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, 
						"Item does not exist", 
						"Warning",JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
