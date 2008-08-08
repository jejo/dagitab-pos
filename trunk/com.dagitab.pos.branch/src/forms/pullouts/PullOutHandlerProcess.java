package forms.pullouts;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import connection.DataUtil;
import connection.LogHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
public class PullOutHandlerProcess extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTable jTable1;
	private JButton jButton2;
	private JLabel jLabel1;
	private DBManager db;
	private String pulloutcode;
	private LogHandler cachewriter;
	private DataUtil datautil;
	private PullOutHandler form;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PullOutHandlerProcess inst = new PullOutHandlerProcess(frame);
		inst.setVisible(true);
	}
	
	public PullOutHandlerProcess(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public PullOutHandlerProcess(PullOutHandler form, DBManager db, String pulloutcode)
	{
		super(form);
		this.db = db;
		this.form = form;
		this.pulloutcode = pulloutcode;
		cachewriter = new LogHandler();
		datautil = new DataUtil();
		initGUI();
		
	}
	
	private void initGUI() {
		try {
			{
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				this.setTitle("Process Pull Outs");
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(851, 605, 951, 457, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Close");
					jButton2.setPreferredSize(new java.awt.Dimension(84, 28));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							PullOutHandlerProcess.this.dispose();
						}
					});
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(705, 976, 805, 704, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("Process Item");
					jButton1.setPreferredSize(new java.awt.Dimension(154, 28));
					jButton1.setOpaque(false);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String pulloutitemno = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
							int num = JOptionPane.showConfirmDialog(
									PullOutHandlerProcess.this,
									" You are about to process this product. You may not be able to edit this once you process. \n " +
									"Are you sure you want to continue?",
									"Confirmation", JOptionPane.YES_NO_OPTION);
							
							if(num == 0){
								int success = db.executeUpdate("UPDATE pull_out_items SET PROCESSED_STAT = 1 WHERE PULL_OUT_ITEM_NO = "+pulloutitemno);
								//refresh table
								//save to file
								
								String[] keyFields = new String[1];
								keyFields[0] = "PULL_OUT_ITEM_NO";
								
								String[] keys = new String[1];
								keys[0] = pulloutitemno;
								
								String dataToLog = DataUtil.rowToData("EDIT","pull_out_items",keyFields,keys);
								cachewriter.writeToFile(dataToLog);
								
								refreshTable();
								
								
								//if all products have been processed, convert status to finish
								String query = "SELECT * FROM pull_out_items WHERE PULL_OUT_NO = "+pulloutcode;
								ResultSet rs = db.executeQuery(query);
								try {
									int totalRows = 0;
									int totalProcessed = 0;
									while(rs.next()){
										
										if(rs.getString("PROCESSED_STAT").equals("1")){
											totalProcessed++;
										}
										totalRows++;
									}
									
									if(totalRows == totalProcessed){
										int result = db.executeUpdate("UPDATE pull_outs SET STATUS = \"complete\" WHERE PULL_OUT_NO = "+pulloutcode);
										if(result > 0){
											System.out.println("updated pull_out status to finished");
											
											String[] ss = new String[1];
											ss[0] = "PULL_OUT_NO";
											
											String[] s = new String[1];
											s[0] = pulloutcode;
											
											dataToLog = DataUtil.rowToData("EDIT","pull_outs",ss,s);
											cachewriter.writeToFile(dataToLog);
											
										}
										
									}
									
									
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
							}
							
							
							try {
								//refresh lists
								form.refreshPendingList();
								form.refreshCompletedList();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
							
							
						}
					});
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1, new AnchorConstraint(26, 408, 126, 13, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("Process Pull Outs");
					jLabel1.setPreferredSize(new java.awt.Dimension(224, 28));
					jLabel1.setFont(new java.awt.Font("Tahoma",1,16));
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new AnchorConstraint(126, 976, 701, 13, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(546, 161));
					{
						Vector<Vector<String>> data = new Vector<Vector<String>>();
						String query = "SELECT * FROM pull_out_items WHERE PULL_OUT_NO = "+pulloutcode;
						ResultSet rs = db.executeQuery(query);
						while(rs.next()){
							Vector<String> row = new Vector<String>();
							row.add(rs.getString("PULL_OUT_ITEM_NO"));
							row.add(rs.getString("PROD_CODE"));
							ResultSet rs2 = db.executeQuery("SELECT NAME FROM products_lu WHERE PROD_CODE = '"+rs.getString("PROD_CODE") +"'");
							if(rs2.next()){
								row.add(rs2.getString("NAME"));
							}
							row.add(rs.getString("QUANTITY"));
							String processed_stat = "NOT PROCESSED";
							if(rs.getString("PROCESSED_STAT").equals("1")){
								processed_stat = "OK";
							}
							row.add(processed_stat);
							data.add(row);
						}
						
						//feed to String[][]
						String[][] values = new String[data.size()][5];
						for(int i = 0; i<data.size(); i++){
							for(int j = 0; j<data.get(i).size(); j++)
							{
								values[i][j] = data.get(i).get(j);
							}
						}
						                                 
						
						TableModel jTable1Model = new DefaultTableModel(
							values,
							new String[] { "Pull Out Item No","Product Code", "Product Name","Quantity","Processed Stat" });
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
			}
			this.setSize(575, 314);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refreshTable(){
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		String query = "SELECT * FROM pull_out_items WHERE PULL_OUT_NO = "+pulloutcode;
		ResultSet rs = db.executeQuery(query);
		try {
			while(rs.next()){
				Vector<String> row = new Vector<String>();
				row.add(rs.getString("PULL_OUT_ITEM_NO"));
				row.add(rs.getString("PROD_CODE"));
				ResultSet rs2 = db.executeQuery("SELECT NAME FROM products_lu WHERE PROD_CODE = '"+rs.getString("PROD_CODE") +"'");
				if(rs2.next()){
					row.add(rs2.getString("NAME"));
				}
				row.add(rs.getString("QUANTITY"));
				String processed_stat = "NOT PROCESSED";
				if(rs.getString("PROCESSED_STAT").equals("1")){
					processed_stat = "OK";
				}
				row.add(processed_stat);
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//feed to String[][]
		String[][] values = new String[data.size()][5];
		for(int i = 0; i<data.size(); i++){
			for(int j = 0; j<data.get(i).size(); j++)
			{
				values[i][j] = data.get(i).get(j);
			}
		}
		                                 
		
		TableModel jTable1Model = new DefaultTableModel(
			values,
			new String[] { "Pull Out Item No","Product Code", "Product Name","Quantity","Processed Stat" });
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
	}

}
