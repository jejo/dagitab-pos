package forms.pullouts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import forms.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class PullOutHandler extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JButton jButton1;
	private JTable jTable2;
	private JTable jTable1;
	private JButton jButton3;
	private JButton jButton2;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private String storeCode; 
	private DBManager db;
	private MainWindow frame;
	

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
		PullOutHandler inst = new PullOutHandler(frame);
		inst.setVisible(true);
	}
	
	public PullOutHandler(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public PullOutHandler(MainWindow frame, DBManager db, String storeCode)
	{
		super(frame);
		this.db = db;
		this.storeCode = storeCode;
		this.frame = frame;
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("Pull Out Handler");
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3, new AnchorConstraint(916, 591, 972, 443, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton3.setText("Close");
				jButton3.setPreferredSize(new java.awt.Dimension(91, 28));
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						PullOutHandler.this.dispose();
					}
				});
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(860, 978, 916, 796, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("View Items");
				jButton2.setPreferredSize(new java.awt.Dimension(112, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String pulloutcode = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
						PullOutItems dialog = new PullOutItems(PullOutHandler.this,db,pulloutcode);
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
						
					}
				});
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(451, 978, 508, 796, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Process");
				jButton1.setPreferredSize(new java.awt.Dimension(112, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String pulloutcode = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
						PullOutHandlerProcess dialog = new PullOutHandlerProcess(PullOutHandler.this,db,pulloutcode);
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
					}
				});
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(78, 273, 139, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setText("List of Pending Pull Outs");
				jLabel2.setPreferredSize(new java.awt.Dimension(154, 28));
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, new AnchorConstraint(127, 978, 451, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane1.setPreferredSize(new java.awt.Dimension(588, 161));
				{
					ResultSet rs = db.executeQuery("SELECT * FROM pull_outs WHERE status = 'pending' AND STO_TO_CODE = '"+storeCode+"'");
					Vector<Vector<String>> data = new Vector<Vector<String>>();
					while(rs.next())
					{
						Vector<String> row = new Vector<String>();
						row.add(rs.getString("PULL_OUT_NO"));
						row.add(rs.getString("ISSUE_DT"));
						row.add(rs.getString("ISSUE_CLERK"));
						ResultSet rs2 = db.executeQuery("SELECT NAME FROM pull_out_reason_lu WHERE PO_REASON_CODE = '"+rs.getString("PO_REASON_CODE")+"'");
						if(rs2.next()){
							row.add(rs2.getString("NAME"));
						}
						
						data.add(row);
					}
					
					//feed to String[][]
					String[][] values = new String[data.size()][4];
					for(int i = 0; i<data.size(); i++)
					{
						for(int j = 0; j<data.get(i).size(); j++)
						{
							values[i][j] = data.get(i).get(j);
						}
					}
					
					
					TableModel jTable1Model = new DefaultTableModel(
						values,
						new String[] { "Pull Out No", "Issue Date","Issue Clerk","Pull Out Reason" });
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
				getContentPane().add(jLabel1, new AnchorConstraint(16, 512, 62, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Pull Out Handler");
				jLabel1.setPreferredSize(new java.awt.Dimension(301, 21));
				jLabel1.setFont(new java.awt.Font("Tahoma",1,16));
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3, new AnchorConstraint(493, 273, 550, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel3.setText("List of Completed Pull Outs");
				jLabel3.setPreferredSize(new java.awt.Dimension(154, 28));
			}
			{
				jScrollPane2 = new JScrollPane();
				getContentPane().add(jScrollPane2, new AnchorConstraint(536, 978, 860, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane2.setPreferredSize(new java.awt.Dimension(588, 161));
				{
					ResultSet rs = db.executeQuery("SELECT * FROM pull_outs WHERE status = 'complete' AND STO_TO_CODE = '"+storeCode+"'");
					Vector<Vector<String>> data = new Vector<Vector<String>>();
					while(rs.next())
					{
						Vector<String> row = new Vector<String>();
						row.add(rs.getString("PULL_OUT_NO"));
						row.add(rs.getString("ISSUE_DT"));
						row.add(rs.getString("ISSUE_CLERK"));
						ResultSet rs2 = db.executeQuery("SELECT NAME FROM pull_out_reason_lu WHERE PO_REASON_CODE = '"+rs.getString("PO_REASON_CODE")+"'");
						if(rs2.next()){
							row.add(rs2.getString("NAME"));
						}
						
						data.add(row);
					}
					
					//feed to String[][]
					String[][] values = new String[data.size()][4];
					for(int i = 0; i<data.size(); i++)
					{
						for(int j = 0; j<data.get(i).size(); j++)
						{
							values[i][j] = data.get(i).get(j);
						}
					}
					
					TableModel jTable2Model = new DefaultTableModel(
						values,
						new String[] { "Pull Out No", "Issue Date","Issue Clerk","Pull Out Reason" });
					jTable2 = new JTable();
					jScrollPane2.setViewportView(jTable2);
					jTable2.setModel(jTable2Model);
				}
			}
			this.setSize(624, 531);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshPendingList() throws SQLException{
		ResultSet rs = db.executeQuery("SELECT * FROM pull_outs WHERE status = 'pending' AND STO_TO_CODE = '"+storeCode+"'");
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		while(rs.next())
		{
			Vector<String> row = new Vector<String>();
			row.add(rs.getString("PULL_OUT_NO"));
			row.add(rs.getString("ISSUE_DT"));
			row.add(rs.getString("ISSUE_CLERK"));
			ResultSet rs2 = db.executeQuery("SELECT NAME FROM pull_out_reason_lu WHERE PO_REASON_CODE = '"+rs.getString("PO_REASON_CODE")+"'");
			if(rs2.next()){
				row.add(rs2.getString("NAME"));
			}
			
			data.add(row);
		}
		
		//feed to String[][]
		String[][] values = new String[data.size()][4];
		for(int i = 0; i<data.size(); i++)
		{
			for(int j = 0; j<data.get(i).size(); j++)
			{
				values[i][j] = data.get(i).get(j);
			}
		}
		
		
		TableModel jTable1Model = new DefaultTableModel(
			values,
			new String[] { "Pull Out No", "Issue Date","Issue Clerk","Pull Out Reason" });
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
	
	public void refreshCompletedList() throws SQLException{
		ResultSet rs = db.executeQuery("SELECT * FROM pull_outs WHERE status = 'complete' AND STO_TO_CODE = '"+storeCode+"'");
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		while(rs.next())
		{
			Vector<String> row = new Vector<String>();
			row.add(rs.getString("PULL_OUT_NO"));
			row.add(rs.getString("ISSUE_DT"));
			row.add(rs.getString("ISSUE_CLERK"));
			ResultSet rs2 = db.executeQuery("SELECT NAME FROM pull_out_reason_lu WHERE PO_REASON_CODE = '"+rs.getString("PO_REASON_CODE")+"'");
			if(rs2.next()){
				row.add(rs2.getString("NAME"));
			}
			
			data.add(row);
		}
		
		//feed to String[][]
		String[][] values = new String[data.size()][4];
		for(int i = 0; i<data.size(); i++)
		{
			for(int j = 0; j<data.get(i).size(); j++)
			{
				values[i][j] = data.get(i).get(j);
			}
		}
		
		TableModel jTable2Model = new DefaultTableModel(
			values,
			new String[] { "Pull Out No", "Issue Date","Issue Clerk","Pull Out Reason" });
		jTable2 = new JTable();
		jScrollPane2.setViewportView(jTable2);
		jTable2.setModel(jTable2Model);
	}

}
