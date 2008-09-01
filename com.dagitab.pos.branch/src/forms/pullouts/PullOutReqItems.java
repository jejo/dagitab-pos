package forms.pullouts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.DBManager;
import util.LoggerUtility;

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
@Deprecated
public class PullOutReqItems extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	private JLabel pullOutReqItemsLabel;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTable jTable1;
	private DBManager db;
	private String storeCode;
	private String pulloutcode;
	

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PullOutReqItems inst = new PullOutReqItems(frame);
		inst.setVisible(true);
	}
	
	public PullOutReqItems(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public PullOutReqItems(PullOutReq form, DBManager db, String storeCode, String pulloutcode){
		super(form);
		this.db = db;
		this.storeCode = storeCode;
		this.pulloutcode = pulloutcode;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Pull Out Request Items");
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setModal(true);
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new AnchorConstraint(843, 627, 949, 446, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton1.setText("Close");
					jButton1.setPreferredSize(new java.awt.Dimension(105, 28));
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							PullOutReqItems.this.dispose();
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new AnchorConstraint(159, 964, 817, 37, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(539, 175));
					{
						Vector<Vector<String>> data = new Vector<Vector<String>>();
						ResultSet rs = db.executeQuery("SELECT * FROM pull_out_request_items WHERE REQUEST_NO ='"+pulloutcode+"' AND STORE_CODE = '"+storeCode+"'");
						while(rs.next())
						{
							Vector<String> row = new Vector<String>();
							row.add(rs.getString("PROD_CODE"));
							ResultSet rs2 = db.executeQuery("SELECT NAME FROM products_lu WHERE PROD_CODE = '"+rs.getString("PROD_CODE")+"'");
							if(rs2.next())
							{
								row.add(rs2.getString("NAME"));
							}
							row.add(rs.getString("QUANTITY"));
							data.add(row);
						}
						
						//feed into String[][];
						
						String[][] values = new String[data.size()][3];
						for(int i = 0; i<data.size(); i++)
						{
							for(int j = 0; j< data.get(i).size(); j++)
							{
								values[i][j] = data.get(i).get(j);
							}
						}
						
						TableModel jTable1Model = new DefaultTableModel(
							values,
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
					pullOutReqItemsLabel = new JLabel();
					getContentPane().add(pullOutReqItemsLabel, new AnchorConstraint(28, 398, 133, 40, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					pullOutReqItemsLabel.setText("Pull Out Requests Items");
					pullOutReqItemsLabel.setPreferredSize(new java.awt.Dimension(189, 28));
					pullOutReqItemsLabel.setFont(new java.awt.Font("Tahoma",1,14));
					pullOutReqItemsLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "pullOutReqItemsLabel");
					pullOutReqItemsLabel.getActionMap().put("pullOutReqItemsLabel",getPullOutReqItemsLabelAbstractAction() );
				}
			}
			this.setSize(589, 300);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	private AbstractAction getPullOutReqItemsLabelAbstractAction() {
		AbstractAction pullOutReqItemsLabelAction = new AbstractAction("Pull Out Requests Items", null) {
			
			public void actionPerformed(ActionEvent evt) {
				PullOutReqItems.this.dispose();
			}
		};
		return pullOutReqItemsLabelAction;
	}

}
