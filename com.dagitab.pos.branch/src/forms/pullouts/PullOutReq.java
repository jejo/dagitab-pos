package forms.pullouts;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import forms.MainWindow;
import forms.NewPullOutReq;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
@Deprecated
public class PullOutReq extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTable jTable1;
	private JButton jButton3;
	private JButton jButton2;
	private JLabel jLabel2;
	private DBManager db;
	private MainWindow form;
	private String storeCode;

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
		PullOutReq inst = new PullOutReq(frame);
		inst.setVisible(true);
	}
	
	public PullOutReq(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public PullOutReq(MainWindow form, DBManager db, String storeCode) {
		super(form);
		this.form = form;
		this.db = db;
		this.storeCode = storeCode;
		initGUI();
		
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Pull Out Requests");
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(868, 711, 957, 530, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Close");
					jButton2.setPreferredSize(new java.awt.Dimension(105, 28));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							PullOutReq.this.dispose();
						}
					});
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(868, 482, 957, 265, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("New Request");
					jButton1.setPreferredSize(new java.awt.Dimension(126, 28));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							NewPullOutReq form = new NewPullOutReq(PullOutReq.this,db,storeCode);
							form.setLocationRelativeTo(null);
							form.setVisible(true);
							
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new AnchorConstraint(179, 976, 690, 24, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(553, 161));
					{
						Vector<Vector<String>> data = new Vector<Vector<String>>();
						ResultSet rs = db.executeQuery("SELECT * FROM pull_out_requests WHERE STO_TO_CODE = '"+storeCode+"'");
						while(rs.next()){
							Vector<String> row = new Vector<String>();
							row.add(rs.getString("REQUEST_NO"));
							row.add(rs.getString("REQUEST_DATE"));
							row.add(rs.getString("ISSUE_CLERK"));
							ResultSet rs2 = db.executeQuery("SELECT * FROM pull_out_reason_lu WHERE PO_REASON_CODE = '"+rs.getString("PO_REASON_CODE")+"'");
							if(rs2.next()){
								row.add(rs2.getString("NAME"));
							}
							data.add(row);
						}
						
						//feed to String[][]
						String[][] arraydata = new String[data.size()][4];
						for(int i = 0; i<data.size(); i++)
						{
							Vector<String> row = data.get(i);
							for(int j = 0; j<4; j++){
								arraydata[i][j] = row.get(j);
							}
						}
						
						
						TableModel jTable1Model = new DefaultTableModel(
							arraydata,
							new String[] { "Request No", "Date","Issue Clerk","Reason" });
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
					getContentPane().add(jLabel1, new AnchorConstraint(28, 349, 133, 24, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("Pull Out Requests");
					jLabel1.setPreferredSize(new java.awt.Dimension(189, 28));
					jLabel1.setFont(new java.awt.Font("Tahoma",1,16));
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2, new AnchorConstraint(112, 350, 191, 24, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("List of Requests");
					jLabel2.setFont(new java.awt.Font("Tahoma",1,12));
					jLabel2.setPreferredSize(new java.awt.Dimension(189, 35));
				}
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3, new AnchorConstraint(703, 976, 792, 759, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton3.setText("View Items");
					jButton3.setPreferredSize(new java.awt.Dimension(143, 28));
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String pulloutcode = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
							PullOutReqItems dialog = new PullOutReqItems(PullOutReq.this,db,storeCode,pulloutcode);
							dialog.setLocationRelativeTo(null);
							dialog.setVisible(true);
							
						}
					});
				}
			}
			this.setSize(666, 349);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshList() throws SQLException{
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		ResultSet rs = db.executeQuery("SELECT * FROM pull_out_requests WHERE STO_TO_CODE = '"+storeCode+"'");
		while(rs.next()){
			Vector<String> row = new Vector<String>();
			row.add(rs.getString("REQUEST_NO"));
			row.add(rs.getString("REQUEST_DATE"));
			row.add(rs.getString("ISSUE_CLERK"));
			ResultSet rs2 = db.executeQuery("SELECT * FROM pull_out_reason_lu WHERE PO_REASON_CODE = '"+rs.getString("PO_REASON_CODE")+"'");
			if(rs2.next()){
				row.add(rs2.getString("NAME"));
			}
			data.add(row);
		}
		
		//feed to String[][]
		String[][] arraydata = new String[data.size()][4];
		for(int i = 0; i<data.size(); i++)
		{
			Vector<String> row = data.get(i);
			for(int j = 0; j<4; j++){
				arraydata[i][j] = row.get(j);
			}
		}
		
		
		TableModel jTable1Model = new DefaultTableModel(
			arraydata,
			new String[] { "Request No", "Date","Issue Clerk","Reason" });
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
