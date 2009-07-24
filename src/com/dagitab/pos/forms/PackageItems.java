package com.dagitab.pos.forms;
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


import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;

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
public class PackageItems extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel packageItemsLabel;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTable jTable1;
	private String packagecode;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PackageItems inst = new PackageItems(frame);
		inst.setVisible(true);
	}
	
	public PackageItems(JFrame frame) {
		super(frame);
		initGUI();
	}
	public PackageItems(MainWindow frame, String packagecode) {
		super(frame);
		this.packagecode = packagecode;
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("Packaged Items");
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(873, 607, 963, 426, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Close");
				jButton1.setPreferredSize(new java.awt.Dimension(119, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						PackageItems.this.dispose();
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, new AnchorConstraint(135, 979, 851, 22, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane1.setPreferredSize(new java.awt.Dimension(630, 224));
				{
					String query = "SELECT pr.PROD_CODE, pr.NAME, p.QUANTITY FROM package_items_lu p, products_lu pr WHERE p.PACKAGE_CODE = '"+packagecode+"' AND p.PROD_CODE = pr.PROD_CODE";
					ResultSet rs = Main.getDBManager().executeQuery(query);
					Vector<Vector<String>> data = new Vector<Vector<String>>();
					while(rs.next()){
						Vector<String> row = new Vector<String>();
						row.add(rs.getString("PROD_CODE"));
						row.add(rs.getString("NAME"));
						row.add(rs.getString("QUANTITY"));
						data.add(row);
					}
					
					//feed to String[][]
					String[][] values = new String[data.size()][3];
					for(int i = 0; i<data.size(); i++){
						for(int j = 0; j<data.get(i).size(); j++){
							values[i][j] = data.get(i).get(j);
						}
					}
					TableModel jTable1Model = new DefaultTableModel(
						values,
						new String[] { "Product Code", "Product Name","Quantity" });
					jTable1 = new JTable();
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(jTable1Model);
				}
			}
			{
				packageItemsLabel = new JLabel();
				getContentPane().add(packageItemsLabel, new AnchorConstraint(23, 309, 113, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				packageItemsLabel.setText("View Package Items");
				packageItemsLabel.setPreferredSize(new java.awt.Dimension(196, 28));
				packageItemsLabel.setFont(new java.awt.Font("Tahoma",1,18));
				packageItemsLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "packageItemsLabel");
				packageItemsLabel.getActionMap().put("packageItemsLabel",getPackageItemsLabelAbstractAction() );
			}
			this.setSize(666, 347);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	private AbstractAction getPackageItemsLabelAbstractAction() {
		AbstractAction packageItemsLabelAction = new AbstractAction("View Package Items", null) {
			
			public void actionPerformed(ActionEvent evt) {
				PackageItems.this.dispose();
			}
		};
		return packageItemsLabelAction;
	}

}
