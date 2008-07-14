package forms.partial;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

@SuppressWarnings("serial")
public class PartialPanel extends javax.swing.JPanel {

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PartialPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private JButton jButton16;
	private JButton jButton15;
	private JScrollPane jScrollPane4;
	private JTable jTable4;
	private JLabel jLabel19;
	private JPanel jPanel9;
	private JLabel jLabel22;
	private JButton jButton14;
	private JLabel jLabel23;
	private JPasswordField jPasswordField3;
	private JTextField jTextField10;
	
	public PartialPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			AnchorLayout jPanel2Layout = new AnchorLayout();
			this.setLayout(jPanel2Layout);
			this.setFont(new java.awt.Font("Dialog", 1, 14));
			this.setBackground(new java.awt.Color(255, 255, 255));
			this.setPreferredSize(new java.awt.Dimension(
				862,
				458));
			this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			{
				jButton16 = new JButton();
				this.add(jButton16, new AnchorConstraint(808,966,865,747,AnchorConstraint.ANCHOR_REL,AnchorConstraint.ANCHOR_REL,AnchorConstraint.ANCHOR_REL,AnchorConstraint.ANCHOR_REL));
				jButton16.setText("Process Partial Transaction");
				jButton16.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/process.png")));
				jButton16.setPreferredSize(new java.awt.Dimension(189,28));
				jButton16.addActionListener(new ActionListener() {
					private JPasswordField jPasswordField3;
					private AbstractButton jTextField10;
					private JTable jTable4;

					public void actionPerformed(ActionEvent evt) {
//						ResultSet rs =db.executeQuery("SELECT * FROM clerk_lu WHERE AES_DECRYPT(password,'babyland') = '"
//						ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM  clerk_lu WHERE password = '"
//														+ new String( jPasswordField3.getPassword())
//														+ "' AND clerk_code = '"
//														+ jTextField10.getText()
//														+ "'");
//						
//						
//						try {
//							
//							if (rs.next()) {
//								try {
//									
//									String orNum = jTable4.getValueAt(jTable4.getSelectedRow(),0).toString();
//									String dtime = jTable4.getValueAt(jTable4.getSelectedRow(),1).toString();
//									Partial dialog = new Partial(Main.getInst(),orNum,dtime, jTextField10.getText());
//									dialog.setLocationRelativeTo(null);
//									dialog.setVisible(true);
//									
//								} catch (Exception e) {
//									JOptionPane.showMessageDialog(null,"Please select item from the list.","Warning",JOptionPane.WARNING_MESSAGE);
//								}
//							} else {
//								JOptionPane.showMessageDialog(null,"Invalid Clerk Login.","Warning",JOptionPane.WARNING_MESSAGE);
//							}
//						} catch (HeadlessException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}

					}
				});
			}
			{
				jButton15 = new JButton();
				this.add(jButton15, new AnchorConstraint(
					808,
					439,
					865,
					325,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jButton15.setText("Refresh");
				jButton15.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
						"images/search2.png")));
				jButton15.setPreferredSize(new java.awt.Dimension(
					98,
					28));
				jButton15.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						setPartialList();
//						jTextField10.setText("");
//						jPasswordField3.setText("");
					}
				});
			}
			{
				jScrollPane4 = new JScrollPane();
				this.add(jScrollPane4, new AnchorConstraint(
					114,
					966,
					794,
					325,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jScrollPane4
					.setPreferredSize(new java.awt.Dimension(
						553,
						336));
				{
					TableModel jTable4Model = new DefaultTableModel(
						new String[][] {},
						new String[] { "OR Number",
								"Transaction Date & Time" });
					jTable4 = new JTable() {
						public boolean isCellEditable(
							int row,
							int column) {
							return false;
						}
					};
					jScrollPane4.setViewportView(jTable4);
					jTable4.setModel(jTable4Model);
				}
			}
			{
				jLabel19 = new JLabel();
				this.add(jLabel19, new AnchorConstraint(
					15,
					317,
					114,
					16,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				jLabel19.setText("Manage Partial Transactions");
				jLabel19
					.setFont(new java.awt.Font("Tahoma", 1, 18));
				jLabel19.setPreferredSize(new java.awt.Dimension(
					259,
					49));
			}
			{
				jPanel9 = new JPanel();
				this.add(jPanel9, new AnchorConstraint(
					114,
					292,
					341,
					24,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL,
					AnchorConstraint.ANCHOR_REL));
				AnchorLayout jPanel9Layout = new AnchorLayout();
				jPanel9.setBackground(new java.awt.Color(
					164,
					222,
					251));
				jPanel9.setPreferredSize(new java.awt.Dimension(
					231,
					112));
				jPanel9.setBorder(new LineBorder(
					new java.awt.Color(0, 0, 0),
					1,
					false));
				jPanel9.setLayout(jPanel9Layout);
				{
					jLabel22 = new JLabel();
					jPanel9.add(jLabel22, new AnchorConstraint(
						129,
						396,
						379,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel22.setText("Clerk ID");
					jLabel22.setFont(new java.awt.Font(
						"Tahoma",
						1,
						12));
					jLabel22
						.setPreferredSize(new java.awt.Dimension(
							84,
							28));
				}
				{
					jButton14 = new JButton();
					jPanel9.add(jButton14, new AnchorConstraint(
						129,
						971,
						316,
						820,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jButton14.setText("...");
					jButton14
						.setPreferredSize(new java.awt.Dimension(
							35,
							21));
					jButton14
						.addActionListener(new ActionListener() {
							public void actionPerformed(
								ActionEvent evt) {
//								Clerk dialog = new Clerk(
//									MainWindow.this,
//									"partial");
//								dialog.setLocationRelativeTo(null);
//								dialog.setVisible(true);

							}
						});
				}
				{
					jLabel23 = new JLabel();
					jPanel9.add(jLabel23, new AnchorConstraint(
						504,
						305,
						754,
						32,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jLabel23.setText("Password");
					jLabel23.setFont(new java.awt.Font(
						"Tahoma",
						1,
						12));
					jLabel23
						.setPreferredSize(new java.awt.Dimension(
							63,
							28));
				}
				{
					jPasswordField3 = new JPasswordField();
					jPanel9.add(
						jPasswordField3,
						new AnchorConstraint(
							504,
							971,
							691,
							396,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jPasswordField3
						.setPreferredSize(new java.awt.Dimension(
							133,
							21));
				}
				{
					jTextField10 = new JTextField();
					jPanel9.add(jTextField10, new AnchorConstraint(
						129,
						820,
						316,
						396,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
					jTextField10
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
