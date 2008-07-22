package forms.partial;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import util.TableUtility;

import bus.InvoiceService;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import forms.MainWindow;


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
public class PartialPanel extends javax.swing.JPanel {

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.getContentPane().add(new PartialPanel());
//		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
//	}

	private JButton jButton16;
	private JButton jButton15;
	private JScrollPane jScrollPane4;
	private JTable jTable4;
	private JLabel jLabel19;
	private MainWindow mainWindow;

	public PartialPanel(MainWindow mainWindow) {
		super();
		setMainWindow(mainWindow); 
		initGUI();
		refreshTables();
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
//					private JPasswordField jPasswordField3;
//					private AbstractButton jTextField10;
//					private JTable jTable4;

					private PartialDialog partialDialog;
					
					public void actionPerformed(ActionEvent evt) {
						System.out.println(jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString());
						System.out.println(jTable4.getValueAt(jTable4.getSelectedRow(), 1).toString());
						System.out.println(jTable4.getValueAt(jTable4.getSelectedRow(), 2).toString());
						partialDialog = new PartialDialog(getMainWindow(), jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString(), jTable4.getValueAt(jTable4.getSelectedRow(), 1).toString(), jTable4.getValueAt(jTable4.getSelectedRow(), 2).toString());
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
				this.add(jButton15, new AnchorConstraint(808, 130, 865, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton15.setText("Refresh");
				jButton15.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
						"images/search2.png")));
				jButton15.setPreferredSize(new java.awt.Dimension(98, 26));
				jButton15.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						public void keyTyped(KeyEvent evt) {
						refreshTables();
					}
				});
			}
			{
				jScrollPane4 = new JScrollPane();
				this.add(jScrollPane4, new AnchorConstraint(114, 965, 793, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jScrollPane4.setPreferredSize(new java.awt.Dimension(818, 311));
				{
					TableModel jTable4Model = new DefaultTableModel(
						new String[][] {},
						new String[] { "OR Number",
								"Transaction Date & Time" });
					jTable4 = new JTable() {
						@Override
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refreshTables() {
		ResultSet rs = InvoiceService.fetchAllPartialInvoices();
		TableUtility.fillTable(jTable4, rs, new String[]{"OR Number","Transaction Date & Time", "Clerk No"});
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
}
