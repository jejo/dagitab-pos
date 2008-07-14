package forms;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import connection.DataUtil;
import connection.LogHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
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
public class NewPullOutReq extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JLabel jLabel4;
	private JButton jButton1;
	private JLabel jLabel5;
	private JTable jTable1;
	private JButton jButton5;
	private JButton jButton4;
	private JComboBox jComboBox1;
	private JButton jButton3;
	private JButton jButton2;
	private JPasswordField jPasswordField1;
	private JLabel jLabel3;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private PullOutReq form;
	private DBManager db;
	private String storeCode;
	private Vector<Vector<String>> tableData;
	private ArrayList<String> pulloutreasonID;
	private LogHandler cachewriter;
	private DataUtil datautil;
	
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		NewPullOutReq inst = new NewPullOutReq(frame);
		inst.setVisible(true);
	}
	
	public NewPullOutReq(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public NewPullOutReq(PullOutReq form, DBManager db, String storeCode)
	{
		super(form);
		this.db = db;
		this.form = form;
		this.storeCode = storeCode;
		tableData = new Vector<Vector<String>>();
		pulloutreasonID = new ArrayList<String>();
		
		cachewriter = new LogHandler();
		datautil = new DataUtil();
		
		initGUI();
		
	}
	
	private void initGUI() {
		try {
			{
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				this.setTitle("New Pull Out Request");
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				{
					
					ResultSet rs = db.executeQuery("SELECT * FROM pull_out_reason_lu");
					
					ArrayList<String> pulloutnames = new ArrayList<String>();
					while(rs.next()){
						pulloutreasonID.add(rs.getString("PO_REASON_CODE"));
						pulloutnames.add(rs.getString("NAME"));
					}
					
					String[] pulloutnamesVal = new String[pulloutnames.size()];
					for(int i = 0; i< pulloutnames.size(); i++)
					{
						pulloutnamesVal[i] = pulloutnames.get(i);
					}
					
					ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
						pulloutnamesVal);
					jComboBox1 = new JComboBox();
					getContentPane().add(jComboBox1, new AnchorConstraint(783, 423, 841, 134, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jComboBox1.setModel(jComboBox1Model);
					jComboBox1.setPreferredSize(new java.awt.Dimension(182, 28));
				}
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3, new AnchorConstraint(682, 678, 740, 523, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton3.setText("Delete");
					jButton3.setPreferredSize(new java.awt.Dimension(98, 28));
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							tableData.remove(jTable1.getSelectedRow());
							setPullOutItemTable(null);
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(684, 500, 742, 400, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Add");
					jButton2.setPreferredSize(new java.awt.Dimension(63, 28));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
//							Product dialog = new Product(NewPullOutReq.this);
//							dialog.setLocationRelativeTo(null);
//							dialog.setVisible(true);
							
							
						}
					});
				}
				{
					jPasswordField1 = new JPasswordField();
					getContentPane().add(jPasswordField1, new AnchorConstraint(160, 789, 218, 567, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jPasswordField1.setPreferredSize(new java.awt.Dimension(140, 28));
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(160, 423, 218, 345, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("...");
					jButton1.setPreferredSize(new java.awt.Dimension(49, 28));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
//							Clerk dialog = new Clerk(NewPullOutReq.this,"newpullout");
//							dialog.setLocationRelativeTo(null);
//							dialog.setVisible(true);
						}
					});
				}
				{
					jTextField1 = new JTextField();
					getContentPane().add(jTextField1, new AnchorConstraint(160, 345, 218, 100, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jTextField1.setPreferredSize(new java.awt.Dimension(154, 28));
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new AnchorConstraint(305, 978, 667, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(602, 175));
					{
						TableModel jTable1Model = new DefaultTableModel(
							new String[][] { },
							new String[] { "Product Code", "Product Name","Quantity" });
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
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1, new AnchorConstraint(28, 312, 133, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("New Pull Out Request");
					jLabel1.setPreferredSize(new java.awt.Dimension(189, 28));
					jLabel1.setFont(new java.awt.Font("Tahoma",1,16));
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2, new AnchorConstraint(232, 100, 319, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("Items");
					jLabel2.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel2.setPreferredSize(new java.awt.Dimension(49, 42));
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3, new AnchorConstraint(145, 100, 232, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("Clerk");
					jLabel3.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel3.setPreferredSize(new java.awt.Dimension(49, 42));
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4, new AnchorConstraint(145, 589, 232, 456, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel4.setText("Password");
					jLabel4.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel4.setPreferredSize(new java.awt.Dimension(84, 42));
				}
				{
					jLabel5 = new JLabel();
					getContentPane().add(jLabel5, new AnchorConstraint(769, 111, 856, 34, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel5.setText("Reason");
					jLabel5.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel5.setPreferredSize(new java.awt.Dimension(49, 42));
				}
				{
					jButton4 = new JButton();
					getContentPane().add(jButton4, new AnchorConstraint(899, 500, 957, 323, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton4.setText("Process");
					jButton4.setPreferredSize(new java.awt.Dimension(112, 28));
					jButton4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							
							if(tableData.size() > 0 && jTextField1.getText().length() > 0 && jPasswordField1.getText().length() > 0)
							{
//								ResultSet rs = db.executeQuery("SELECT * FROM clerk_lu WHERE AES_DECRYPT(password,'babyland') = '"+jPasswordField1.getText()+"' AND clerk_code = '"+jTextField1.getText()+"'");
								ResultSet rs = db.executeQuery("SELECT * FROM clerk_lu WHERE password = '"+jPasswordField1.getText()+"' AND clerk_code = '"+jTextField1.getText()+"'");
								
									try {
										if(rs.next()){
											String poReasonCode = pulloutreasonID.get(jComboBox1.getSelectedIndex());
											String query = "INSERT INTO pull_out_requests (STO_TO_CODE, ISSUE_CLERK, PO_REASON_CODE) " +
															"VALUES ('"+storeCode+"','"+jTextField1.getText()+"','"+poReasonCode+"')"; 
											int num = db.executeUpdate(query);
											
											ResultSet rs3 = db.executeQuery("SELECT REQUEST_NO FROM pull_out_requests ORDER BY REQUEST_NO DESC LIMIT 1");
											String reqno = "";
											if(rs3.next()){
												reqno = rs3.getString("REQUEST_NO");
											}
											
											String[] keyFields = new String[2];
											keyFields[0] = "REQUEST_NO";
											keyFields[1] = "STO_TO_CODE";
											
											String[] keys = new String[2];
											keys[0] = reqno;
											keys[1] = storeCode;
											
											String dataToLog = DataUtil.rowToData("ADD","pull_out_requests",keyFields,keys);
											cachewriter.writeToFile(dataToLog);
											 
											for(int i = 0; i< tableData.size(); i++){
												String prodcode = tableData.get(i).get(0);
												String quantity = tableData.get(i).get(2);
												query = "INSERT INTO pull_out_request_items VALUES('"+reqno+"','"+storeCode+"','"+prodcode+"','"+quantity+"')";
												db.executeUpdate(query);
												
												keyFields = new String[3];
												keyFields[0] = "REQUEST_NO";
												keyFields[1] = "STORE_CODE";
												keyFields[2] = "PROD_CODE";
												
												keys = new String[3];
												keys[0] = reqno;
												keys[1] = storeCode;
												keys[2] = tableData.get(i).get(0);
												
												dataToLog = DataUtil.rowToData("ADD","pull_out_request_items",keyFields,keys);
												cachewriter.writeToFile(dataToLog);
											}
											
											if(num > 0){
												JOptionPane.showMessageDialog(null, 
														"Succesfully generated new pull out request.", 
														"Warning",JOptionPane.INFORMATION_MESSAGE);
												form.refreshList();
											}
										}
										else{
											JOptionPane.showMessageDialog(null, 
														"Invalid Cashier log in.", 
														"Warning",JOptionPane.WARNING_MESSAGE);
										}
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								
									
							}
							else{
								JOptionPane.showMessageDialog(null, 
										"Please complete required fields of the form.", 
										"Warning",JOptionPane.WARNING_MESSAGE);
							}
						}
					});
				}
				{
					jButton5 = new JButton();
					getContentPane().add(jButton5, new AnchorConstraint(899, 723, 957, 556, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton5.setText("Close");
					jButton5.setPreferredSize(new java.awt.Dimension(105, 28));
					jButton5.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							NewPullOutReq.this.dispose();
						}
					});
				}
			}
			this.setSize(638, 517);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasPullOutItemTable(String prodcode){
		
		for(int i=0; i<tableData.size();i++){
			
			if(tableData.get(i).get(0).equals(prodcode)){
				return true;
			}
		}
		return false;
	}
	public void setPullOutItemTable(Vector<String> args){
		if(args != null) {
			tableData.add(args);
		}
		
		Object[][] data = new Object[tableData.size()][3];
		for(int i = 0; i< tableData.size(); i++)
		{
			for(int j=0; j<3; j++){
				data[i][j] = tableData.get(i).get(j);
			}
		}
		
		TableModel jTable1Model = new DefaultTableModel(
				data,
				new String[] { "Product Code","Product Name","Quantity" });
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
	
	public void setClerk(String id){
		jTextField1.setText(id);
	}

}
