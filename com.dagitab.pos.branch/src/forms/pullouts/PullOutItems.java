package forms.pullouts;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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
public class PullOutItems extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTable jTable1;
	private DBManager db;
	private String pulloutcode;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PullOutItems inst = new PullOutItems(frame);
		inst.setVisible(true);
	}
	
	public PullOutItems(JFrame frame) {
		super(frame);
		initGUI();
	}
	public PullOutItems(PullOutHandler form, DBManager db, String pulloutcode) {
		super(form);
		this.db = db;
		this.pulloutcode = pulloutcode;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				this.setTitle("Pull Out Items");
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(817, 579, 922, 434, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("Close");
					jButton1.setPreferredSize(new java.awt.Dimension(84, 28));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							PullOutItems.this.dispose();
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new AnchorConstraint(159, 976, 765, 24, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(553, 161));
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
							data.add(row);
						}
						
						//feed to String[][]
						String[][] values = new String[data.size()][4];
						for(int i = 0; i<data.size(); i++){
							for(int j = 0; j<data.get(i).size(); j++)
							{
								values[i][j] = data.get(i).get(j);
							}
						}
						
						TableModel jTable1Model = new DefaultTableModel(
							values,
							new String[] { "Pull Out Item No", "Product Code","Product Name","Quantity" });
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
					getContentPane().add(jLabel1, new AnchorConstraint(28, 314, 133, 28, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jLabel1.setText("View Pull Out Items");
					jLabel1.setPreferredSize(new java.awt.Dimension(147, 28));
					jLabel1.setFont(new java.awt.Font("Tahoma",1,16));
				}
			}
			this.setSize(589, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
