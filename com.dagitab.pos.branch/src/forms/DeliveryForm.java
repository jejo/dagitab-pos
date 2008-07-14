package forms;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
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
public class DeliveryForm extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JScrollPane jScrollPane2;
	private JButton jButton3;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JTable jTable2;
	private JLabel jLabel2;
	private JButton jButton2;
	private JTable jTable1;
	private DBManager db;
	private String storeCode;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		DeliveryForm inst = new DeliveryForm(frame);
		inst.setVisible(true);
	}
	
	public DeliveryForm(JFrame frame) {
		super(frame);
		initGUI();
	}
	public DeliveryForm(MainWindow form,DBManager db, String storeCode) {
		super(form);
		this.db =db;
		this.storeCode = storeCode;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				this.setTitle("Deliveries");
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setModal(true);
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3, new AnchorConstraint(884, 979, 935, 756, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton3.setText("View Delivery Items");
					jButton3.setPreferredSize(new java.awt.Dimension(147, 28));
					jButton3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String delno = jTable2.getValueAt(jTable2.getSelectedRow(),0).toString();
							ProcessDeliveryForm dialog = new ProcessDeliveryForm(DeliveryForm.this,db,storeCode,delno,"completed");
							dialog.setLocationRelativeTo(null);
							dialog.setVisible(true);
						}
					});
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2, new AnchorConstraint(80, 224, 143, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel2.setText("List of Pending delvieries");
					jLabel2.setPreferredSize(new java.awt.Dimension(140, 28));
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new AnchorConstraint(468, 979, 520, 713, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("Process Delivery Items");
					jButton2.setPreferredSize(new java.awt.Dimension(175, 28));
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String delno = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
							ProcessDeliveryForm dialog = new ProcessDeliveryForm(DeliveryForm.this,db,storeCode,delno);
							dialog.setLocationRelativeTo(null);
							dialog.setVisible(true);
						}
					});
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(935, 596, 987, 447, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("Close");
					jButton1.setPreferredSize(new java.awt.Dimension(98, 28));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							DeliveryForm.this.dispose();
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new AnchorConstraint(128, 979, 461, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(637, 147));
					{
						String query = "SELECT * FROM deliveries WHERE STO_TO_CODE = '"+storeCode+"' AND STATUS = 'pending'";
						ResultSet rs = db.executeQuery(query);
						Vector<Vector<String>> data = new Vector<Vector<String>>();
						while(rs.next()){
							Vector<String> row = new Vector<String>();
							row.add(rs.getString("DEL_NO"));
							row.add(rs.getString("ISSUE_DT"));
							row.add(rs.getString("STO_FROM_CODE"));
							row.add(rs.getString("ISSUE_CLERK"));
							row.add(rs.getString("DEL_RCPT_NO"));
							data.add(row);
						}
						
						String[][] values = new String[data.size()][5];
						for(int i = 0; i< data.size(); i++){
							for(int k =0; k<5; k++){
								values[i][k] = data.get(i).get(k);
							}
						}
						
						TableModel jTable1Model = new DefaultTableModel(
							values,
							new String[] { "Delivery No", "Issue Date","From Store","Issue Clerk","Del Receipt No" });
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
					getContentPane().add(jLabel1, new AnchorConstraint(1, 319, 105, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("Deliveries");
					jLabel1.setPreferredSize(new java.awt.Dimension(203, 35));
					jLabel1.setFont(new java.awt.Font("Tahoma",1,16));
				}
				{
					jScrollPane2 = new JScrollPane();
					getContentPane().add(jScrollPane2, new AnchorConstraint(540, 979, 874, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane2.setPreferredSize(new java.awt.Dimension(637, 147));
					{
						
						String query = "SELECT * FROM deliveries WHERE STO_TO_CODE = '"+storeCode+"' AND STATUS = 'complete'";
						ResultSet rs = db.executeQuery(query);
						Vector<Vector<String>> data = new Vector<Vector<String>>();
						while(rs.next()){
							Vector<String> row = new Vector<String>();
							row.add(rs.getString("DEL_NO"));
							row.add(rs.getString("ISSUE_DT"));
							row.add(rs.getString("STO_FROM_CODE"));
							row.add(rs.getString("ISSUE_CLERK"));
							row.add(rs.getString("DEL_RCPT_NO"));
							data.add(row);
						}
						
						String[][] values = new String[data.size()][5];
						for(int i = 0; i< data.size(); i++){
							for(int k =0; k<5; k++){
								values[i][k] = data.get(i).get(k);
							}
						}
						
						
						TableModel jTable2Model = new DefaultTableModel(
							values,
							new String[] { "Delivery No", "Issue Date","From Store","Issue Clerk","Del Receipt No" });
						jTable2 = new JTable();
						jScrollPane2.setViewportView(jTable2);
						jTable2.setModel(jTable2Model);
					}
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3, new AnchorConstraint(636, 1283, 699, 1070, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel3.setText("List of Pending delvieries");
					jLabel3.setPreferredSize(new java.awt.Dimension(140, 28));
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4, new AnchorConstraint(494, 224, 559, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel4.setText("List of complete deliveries");
					jLabel4.setPreferredSize(new java.awt.Dimension(140, 35));
				}
			}
			this.setSize(666, 573);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void resetTable() {
		try {
			String query = "SELECT * FROM deliveries WHERE STO_TO_CODE = '"+storeCode+"' AND STATUS = 'pending'";
			ResultSet rs = db.executeQuery(query);
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			while(rs.next()){
				Vector<String> row = new Vector<String>();
				row.add(rs.getString("DEL_NO"));
				row.add(rs.getString("ISSUE_DT"));
				row.add(rs.getString("STO_FROM_CODE"));
				row.add(rs.getString("ISSUE_CLERK"));
				row.add(rs.getString("DEL_RCPT_NO"));
				data.add(row);
			}
			
			String[][] values = new String[data.size()][5];
			for(int i = 0; i< data.size(); i++){
				for(int k =0; k<5; k++){
					values[i][k] = data.get(i).get(k);
				}
			}
							
			TableModel jTable1Model = new DefaultTableModel(
				values,
				new String[] { "Delivery No", "Issue Date","From Store","Issue Clerk","Del Receipt No" });
			jTable1 = new JTable(){
				@Override
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};
			jScrollPane1.setViewportView(jTable1);
			jTable1.setModel(jTable1Model);
	
			query = "SELECT * FROM deliveries WHERE STO_TO_CODE = '"+storeCode+"' AND STATUS = 'complete'";
			rs = db.executeQuery(query);
			data = new Vector<Vector<String>>();
			while(rs.next()){
				Vector<String> row = new Vector<String>();
				row.add(rs.getString("DEL_NO"));
				row.add(rs.getString("ISSUE_DT"));
				row.add(rs.getString("STO_FROM_CODE"));
				row.add(rs.getString("ISSUE_CLERK"));
				row.add(rs.getString("DEL_RCPT_NO"));
				data.add(row);
			}
			
			values = new String[data.size()][5];
			for(int i = 0; i< data.size(); i++){
				for(int k =0; k<5; k++){
					values[i][k] = data.get(i).get(k);
				}
			}
			
			
			TableModel jTable2Model = new DefaultTableModel(
				values,
				new String[] { "Delivery No", "Issue Date","From Store","Issue Clerk","Del Receipt No" });
			jTable2 = new JTable();
			jScrollPane2.setViewportView(jTable2);
			jTable2.setModel(jTable2Model);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
