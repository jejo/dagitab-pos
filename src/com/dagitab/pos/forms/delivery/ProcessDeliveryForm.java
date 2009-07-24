package com.dagitab.pos.forms.delivery;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.dagitab.pos.main.DBManager;
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
@Deprecated
public class ProcessDeliveryForm extends javax.swing.JDialog {
	private DeliveryForm parent;
	private JLabel processDeliveryLabel;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JButton jButton2;
	private JTable jTable1;
	private String delno;
	private DBManager db;
	private String storeCode;
	private String status;

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
		ProcessDeliveryForm inst = new ProcessDeliveryForm(frame);
		inst.setVisible(true);
	}
	
	public ProcessDeliveryForm(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public ProcessDeliveryForm(DeliveryForm frame,DBManager db, String storeCode,String delno,String status){
		super(frame);
		this.parent = frame;
		this.db =db;
		this.storeCode = storeCode;
		this.delno = delno;
		this.status = status;
		initGUI();
		jButton1.setEnabled(false);
		
	}
	public ProcessDeliveryForm(DeliveryForm frame,DBManager db, String storeCode,String delno){
		super(frame);
		this.parent = frame;
		this.db =db;
		this.storeCode = storeCode;
		this.delno = delno;
		this.status = "pending";
		initGUI();
		jButton1.setEnabled(true);
		
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setTitle("Delivery Items");
			this.setModal(true);
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(899, 564, 967, 460, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Close");
				jButton2.setPreferredSize(new java.awt.Dimension(63, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						ProcessDeliveryForm.this.dispose();
						parent.resetTable();
					}
				});
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(780, 977, 848, 713, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("Confirm Delivery Item");
				jButton1.setPreferredSize(new java.awt.Dimension(161, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String delitemno = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
						int quantity = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
						try{
//							DeliveryItemConfirmationDialog dialog = new DeliveryItemConfirmationDialog(ProcessDeliveryForm.this,db,storeCode,delitemno,quantity);
//							dialog.setLocationRelativeTo(null);
//							dialog.setVisible(true);
						}
						catch(Exception ex){
						//	ex.LoggerUtility.getInstance().logStackTrace(e);;
						}
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, new AnchorConstraint(85, 977, 763, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				
				ResultSet rs = db.executeQuery("SELECT * FROM delivery_items WHERE DEL_NO = "+delno);
				Vector<Vector<String>> data = new Vector<Vector<String>>();
				while(rs.next()){
					Vector<String> row = new Vector<String>();
					row.add(rs.getString("DEL_ITEM_NO"));
					row.add(rs.getString("PROD_CODE"));
					row.add(rs.getString("QUANTITY"));
					row.add(rs.getString("PROCESSED_STAT"));
					row.add(rs.getString("ACCEPTED_QTY"));
					row.add(rs.getString("MISSING_QTY"));
					row.add(rs.getString("DAMAGED_QTY"));
					data.add(row);
				}
				
				//fill to String[][]
				String[][] values = new String[data.size()][7];
				for(int i = 0; i<data.size(); i++){
					for(int j = 0; j<data.get(i).size(); j++){
						values[i][j] = data.get(i).get(j);
					}
				}
				
				jScrollPane1.setPreferredSize(new java.awt.Dimension(581, 280));
				{
					TableModel jTable1Model = new DefaultTableModel(
						values,
						new String[] { "Delivery Item No", "Product Code","Quantity","Processed Status","Accepted Qty","Missing Qty","Damaged Qty"});
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
				processDeliveryLabel = new JLabel();
				getContentPane().add(processDeliveryLabel, new AnchorConstraint(18, 265, 85, 23, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				processDeliveryLabel.setText("Delivery Items");
				processDeliveryLabel.setPreferredSize(new java.awt.Dimension(147, 28));
				processDeliveryLabel.setFont(new java.awt.Font("Tahoma",1,18));
				processDeliveryLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "processDeliveryLabel");
				processDeliveryLabel.getActionMap().put("processDeliveryLabel",getProcessDeliveryLabelAbstractAction() );
			}
			this.setSize(617, 447);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	public void resetTable(){
		
		ResultSet rs = db.executeQuery("SELECT * FROM delivery_items WHERE DEL_NO = "+delno);
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		try {
			while(rs.next()){
				Vector<String> row = new Vector<String>();
				row.add(rs.getString("DEL_ITEM_NO"));
				row.add(rs.getString("PROD_CODE"));
				row.add(rs.getString("QUANTITY"));
				row.add(rs.getString("PROCESSED_STAT"));
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
		//fill to String[][]
		String[][] values = new String[data.size()][4];
		for(int i = 0; i<data.size(); i++){
			for(int j = 0; j<data.get(i).size(); j++){
				values[i][j] = data.get(i).get(j);
			}
		}
		
		jScrollPane1.setPreferredSize(new java.awt.Dimension(581, 280));
		{
			TableModel jTable1Model = new DefaultTableModel(
				values,
				new String[] { "Delivery Item No", "Product Code","Quantity","Processed Status"});
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
	private AbstractAction getProcessDeliveryLabelAbstractAction() {
		AbstractAction processDeliveryLabelAction = new AbstractAction("Delivery Items", null) {
			
			public void actionPerformed(ActionEvent evt) {
				ProcessDeliveryForm.this.dispose();
			}
		};
		return processDeliveryLabelAction;
	}
	

}
