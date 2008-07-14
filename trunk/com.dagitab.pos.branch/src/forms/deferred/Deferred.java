package forms.deferred;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

@SuppressWarnings("serial")
public class Deferred extends javax.swing.JPanel {

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Deferred());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private JButton jButton13;
	private JButton jButton12;
	private JScrollPane jScrollPane3;
	private JTable jTable3;
	private JLabel jLabel18;
	private JPanel jPanel8;
	private JLabel jLabel20;
	private JButton jButton11;
	private JLabel jLabel21;
	private JPasswordField jPasswordField1;
	private JTextField jTextField13;
	
	public Deferred() {
		super();
		initGUI();
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
				this.add(jButton13, new AnchorConstraint(
					794,
					447,
					851,
					325,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton13.setText("Refresh");
				jButton13.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
						"images/search2.png")));
				jButton13.setPreferredSize(new java.awt.Dimension(
					105,
					28));
				jButton13.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
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
				this.add(jScrollPane3, new AnchorConstraint(
					114,
					966,
					780,
					325,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jScrollPane3
					.setPreferredSize(new java.awt.Dimension(
						553,
						329));
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
				this.add(jLabel18, new AnchorConstraint(
					15,
					317,
					114,
					16,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jLabel18.setText("Manage Deferred Products");
				jLabel18.setPreferredSize(new java.awt.Dimension(
					259,
					49));
				jLabel18
					.setFont(new java.awt.Font("Tahoma", 1, 18));
			}
			{
				jPanel8 = new JPanel();
				this.add(jPanel8, new AnchorConstraint(
					114,
					292,
					341,
					24,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				AnchorLayout jPanel8Layout = new AnchorLayout();
				jPanel8.setBackground(new java.awt.Color(
					164,
					222,
					251));
				jPanel8.setPreferredSize(new java.awt.Dimension(
					231,
					112));
				jPanel8.setBorder(new LineBorder(
					new java.awt.Color(0, 0, 0),
					1,
					false));
				jPanel8.setLayout(jPanel8Layout);
				{
					jLabel20 = new JLabel();
					jPanel8.add(jLabel20, new AnchorConstraint(
						129,
						396,
						379,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel20.setText("Clerk ID");
					jLabel20.setFont(new java.awt.Font(
						"Tahoma",
						1,
						12));
					jLabel20
						.setPreferredSize(new java.awt.Dimension(
							84,
							28));
				}
				{
					jButton11 = new JButton();
					jPanel8.add(jButton11, new AnchorConstraint(
						129,
						971,
						316,
						820,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jButton11.setText("...");
					jButton11
						.setPreferredSize(new java.awt.Dimension(
							35,
							21));
					jButton11
						.addActionListener(new ActionListener() {
							public void actionPerformed(
								ActionEvent evt) {
//								Clerk dialog = new Clerk(
//									MainWindow.this,"deferred");
//								dialog.setLocationRelativeTo(null);
//								dialog.setVisible(true);

							}
						});
				}
				{
					jLabel21 = new JLabel();
					jPanel8.add(jLabel21, new AnchorConstraint(
						504,
						305,
						754,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel21.setText("Password");
					jLabel21.setFont(new java.awt.Font(
						"Tahoma",
						1,
						12));
					jLabel21
						.setPreferredSize(new java.awt.Dimension(
							63,
							28));
				}
				{
					jPasswordField1 = new JPasswordField();
					jPanel8.add(
						jPasswordField1,
						new AnchorConstraint(
							504,
							971,
							691,
							396,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jPasswordField1
						.setPreferredSize(new java.awt.Dimension(
							133,
							21));
				}
				{
					jTextField13 = new JTextField();
					jPanel8.add(jTextField13, new AnchorConstraint(
						129,
						820,
						316,
						396,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jTextField13
						.setPreferredSize(new java.awt.Dimension(
							98,
							21));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
