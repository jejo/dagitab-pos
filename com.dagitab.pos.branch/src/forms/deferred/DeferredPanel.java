package forms.deferred;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import util.TableUtility;

import bus.InvoiceItemService;
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
@SuppressWarnings("serial")
public class DeferredPanel extends javax.swing.JPanel {

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new DeferredPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private JButton jButton13;
	private JButton jButton12;
	private JScrollPane jScrollPane3;
	private JTable jTable3;
	private JLabel jLabel18;

	public DeferredPanel() {
		super();
		initGUI();
		refreshTables();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			AnchorLayout jPanel4Layout = new AnchorLayout();
			this.setLayout(jPanel4Layout);
			this
				.setBackground(new java.awt.Color(255, 255, 255));
			this.setBorder(new LineBorder(new java.awt.Color(
				0,
				0,
				0), 1, false));
			{
				jButton13 = new JButton();
				this.add(jButton13, new AnchorConstraint(795, 136, 851, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton13.setText("Refresh");
				jButton13.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
						"images/search2.png")));
				jButton13.setPreferredSize(new java.awt.Dimension(48, 17));
				jButton13.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						refreshTables();
//						setDeferredList();
					}
				});
			}
			{
				jButton12 = new JButton();
				this.add(jButton12, new AnchorConstraint(
					794,
					966,
					851,
					812,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton12.setText("Process");
				jButton12.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
						"images/process.png")));
				jButton12.setPreferredSize(new java.awt.Dimension(
					133,
					28));
				jButton12.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						ResultSet rs = Main.getDBManager()
//						.executeQuery("SELECT * FROM clerk_lu WHERE password = '"
////							.executeQuery("SELECT * FROM clerk_lu WHERE AES_DECRYPT(password,'babyland') = '"
//								+ jPasswordField1.getText()
//								+ "' AND clerk_code = '"
//								+ jTextField13.getText()
//								+ "'");
//
//						try {
//							if (rs.next()) {
//
//								try {
//									String orNO = jTable3
//										.getValueAt(
//											jTable3
//												.getSelectedRow(),
//											0).toString();
//									String prodCode = jTable3
//										.getValueAt(
//											jTable3
//												.getSelectedRow(),
//											2).toString();
//									int n = JOptionPane
//										.showConfirmDialog(
//											MainWindow.this,
//											"Are you sure you want to process the deferred product?",
//											"Confirmation",
//											JOptionPane.YES_NO_OPTION);
//									if (n == 0) {
//										int result = Main.getDBManager()
//											.executeUpdate("UPDATE invoice_item SET deferred = 0 "
//												+ "WHERE OR_NO = '"
//												+ orNO
//												+ "' AND PROD_CODE = '"
//												+ prodCode
//												+ "' AND STORE_CODE = "
//												+ Main.getStoreCode());
//
//										String dataToLog2 = DataUtil
//											.rowToData(
//												"EDIT",
//												"invoice_item",
//												new String[] {
//														"OR_NO",
//														"PROD_CODE",
//														"STORE_CODE" },
//												new String[] {
//														orNO,
//														prodCode,
//														Main.getStoreCode() });
//										cachewriter
//											.writeToFile(dataToLog2);
//										setDeferredList();
//										jTextField13.setText("");
//										jPasswordField1.setText("");
//									}
//								} catch (Exception e) {
//									JOptionPane
//										.showMessageDialog(
//											null,
//											"Please select item from the list.",
//											"Warning",
//											JOptionPane.WARNING_MESSAGE);
//								}
//							} else {
//								JOptionPane.showMessageDialog(
//									null,
//									"Invalid clerk Log in",
//									"Warning",
//									JOptionPane.WARNING_MESSAGE);
//							}
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
				});
			}
			{
				jScrollPane3 = new JScrollPane();
				this.add(jScrollPane3, new AnchorConstraint(115, 966, 781, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane3.setPreferredSize(new java.awt.Dimension(380, 200));
				{
					TableModel jTable3Model = new DefaultTableModel(
						new String[][] {},
						new String[] { "OR Number",
								"Invoice Number", "Product Code",
								"Product Name", "Quantity",
								"Transaction Date & Time" });
					jTable3 = new JTable();
					jScrollPane3.setViewportView(jTable3);
					jTable3.setModel(jTable3Model);
				}
			}
			{
				jLabel18 = new JLabel();
				this.add(jLabel18, new AnchorConstraint(15, 773, 115, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel18.setText("Manage Deferred Products");
				jLabel18.setPreferredSize(new java.awt.Dimension(303, 30));
				jLabel18
					.setFont(new java.awt.Font("Tahoma", 1, 18));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshTables() {
		ResultSet rs = InvoiceItemService.fetchAllDeferredInvoiceItems();
		TableUtility.fillTable(jTable3, rs, new String[]{"OR Number", "Invoice No", "Product Code", "Product Name", "Quantity", "Transaction Date & Time"});
	}
}
