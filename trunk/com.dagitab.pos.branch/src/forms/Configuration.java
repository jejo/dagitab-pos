package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.ServerPropertyHandler;
import util.StorePropertyHandler;


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
public class Configuration extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JTabbedPane jTabbedPane1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel jPanel1;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton jButton4;
	private JLabel jLabel11;
	private JTextField jTextField4;
	private JTextField jTextField5;
	private JTextField jTextField6;
	private JButton jButton7;
	private JTextField jTextField7;
	private JLabel jLabel15;
	private JPanel jPanel6;
	private JButton jButton6;
	private JLabel jLabel14;
	private JPanel jPanel5;
	private JButton jButton5;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JPanel jPanel4;
	private JLabel jLabel10;
	private JPanel jPanel3;
	private JTextField jTextField3;
	private JLabel jLabel9;
	private JButton jButton3;
	private JTextField jTextField2;
	private JLabel jLabel8;
	private JButton jButton1;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JButton jButton2;
	private JTextField jTextField1;
	private JPanel jPanel2;
	
	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		Configuration inst = new Configuration(frame);
//		inst.setVisible(true);
	}
	
	public Configuration(JFrame frame) {
		super(frame);
		
		initGUI();
		
		jTextField1.setText(StorePropertyHandler.getStoreNo());
		jTextField2.setText(ServerPropertyHandler.getServerIP());
		jTextField3.setText(ServerPropertyHandler.getServerPort());
		jTextField4.setText(StorePropertyHandler.getTinNo());
		jTextField5.setText(StorePropertyHandler.getTenantNo());
		jTextField6.setText(StorePropertyHandler.getTerminalNo());
		jTextField7.setText(StorePropertyHandler.getBatchNo());
		
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			this.setTitle("Configuration Settings");
			this.setModal(true);
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(864, 563, 942, 407, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Close");
				jButton2.setPreferredSize(new java.awt.Dimension(70, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						Configuration.this.dispose();
					}
				});
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(79, 657, 158, 63, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setText("You may change the application settings here.");
				jLabel2.setPreferredSize(new java.awt.Dimension(266, 28));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(21, 579, 119, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Configuration Settings");
				jLabel1.setPreferredSize(new java.awt.Dimension(252, 35));
				jLabel1.setFont(new java.awt.Font("Tahoma",1,18));
			}
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1, new AnchorConstraint(153, 954, 805, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTabbedPane1.setPreferredSize(new java.awt.Dimension(413, 210));
				jTabbedPane1.setFont(new java.awt.Font("Tahoma",1,14));
				jTabbedPane1.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent evt) {
//						jButton1.setEnabled(true);
//						jButton2.setEnabled(false);
					}
				});
				{
					jPanel1 = new JPanel();
					AnchorLayout jPanel1Layout = new AnchorLayout();
					jPanel1.setLayout(jPanel1Layout);
					jTabbedPane1.addTab(
						"Store Configuration",
						null,
						jPanel1,
						null);
					jPanel1.setBackground(new java.awt.Color(255,255,255));
					jPanel1.setFont(new java.awt.Font("Dialog",1,11));
					{
						jButton1 = new JButton();
						jPanel1.add(jButton1, new AnchorConstraint(662, 653, 808, 327, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton1.setText("Apply Changes");
						jButton1.setPreferredSize(new java.awt.Dimension(133, 28));
						jButton1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								if(jTextField1.getText().length() == 0){
									JOptionPane.showMessageDialog(null, 
											"Please complete the fields.", 
											"Warning",JOptionPane.WARNING_MESSAGE);
								}
								else{
									StorePropertyHandler.setStoreNo(jTextField1.getText());
									JOptionPane.showMessageDialog(null, 
											"Updated Store Code. Please Restart This Application", 
											"Success",JOptionPane.INFORMATION_MESSAGE);
									
								}
							}
						});
					}
					{
						jTextField1 = new JTextField();
						jPanel1.add(jTextField1, new AnchorConstraint(479, 636, 625, 344, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField1.setPreferredSize(new java.awt.Dimension(119, 28));
					}
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4, new AnchorConstraint(332, 584, 442, 412, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel4.setText("Store Code:");
						jLabel4.setPreferredSize(new java.awt.Dimension(70, 21));
					}
					{
						jLabel3 = new JLabel();
						jPanel1.add(jLabel3, new AnchorConstraint(2, 550, 149, 18, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel3.setText("Store Code Configuration");
						jLabel3.setPreferredSize(new java.awt.Dimension(217, 28));
						jLabel3.setFont(new java.awt.Font("Tahoma",1,12));
					}
					{
						jLabel5 = new JLabel();
						jPanel1.add(jLabel5, new AnchorConstraint(75, 979, 222, 69, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel5.setText("The store code identifies on  what branch this application is installed.");
						jLabel5.setPreferredSize(new java.awt.Dimension(371, 28));
					}
				}
				{
					jPanel2 = new JPanel();
					AnchorLayout jPanel2Layout = new AnchorLayout();
					jPanel2.setLayout(jPanel2Layout);
					jTabbedPane1.addTab(
						"Connection Configuration",
						null,
						jPanel2,
						null);
					jPanel2.setBackground(new java.awt.Color(255,255,255));
					jPanel2.setPreferredSize(new java.awt.Dimension(406, 189));
					{
						jLabel6 = new JLabel();
						jPanel2.add(jLabel6, new AnchorConstraint(2, 550, 149, 18, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel6.setText("IP Connection Configuration");
						jLabel6.setFont(new java.awt.Font("Tahoma",1,12));
						jLabel6.setPreferredSize(new java.awt.Dimension(217, 28));
					}
					{
						jLabel7 = new JLabel();
						jPanel2.add(jLabel7, new AnchorConstraint(75, 979, 222, 69, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel7.setText("The IP Address is required to communicate with the server.");
						jLabel7.setPreferredSize(new java.awt.Dimension(371, 28));
					}
					{
						jLabel8 = new JLabel();
						jPanel2.add(jLabel8, new AnchorConstraint(295, 412, 369, 241, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel8.setText("IP Address:");
						jLabel8.setPreferredSize(new java.awt.Dimension(70, 14));
					}
					{
						jTextField2 = new JTextField();
						jPanel2.add(jTextField2, new AnchorConstraint(259, 704, 405, 412, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField2.setPreferredSize(new java.awt.Dimension(119, 28));
					}
					{
						jButton3 = new JButton();
						jPanel2.add(jButton3, new AnchorConstraint(735, 653, 882, 344, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton3.setText("Apply Changes");
						jButton3.setPreferredSize(new java.awt.Dimension(126, 28));
						jButton3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								if(jTextField2.getText().length() == 0 || 
										jTextField3.getText().length() == 0){
									JOptionPane.showMessageDialog(null, 
											"Please input store code.", 
											"Warning",JOptionPane.WARNING_MESSAGE);
								}else{
									ServerPropertyHandler.setServerConfig(jTextField2.getText(),jTextField3.getText());
									JOptionPane.showMessageDialog(null, 
											"Updated IP connection option", 
											"Success",JOptionPane.INFORMATION_MESSAGE);
								}
							}
						});
					}
					{
						jLabel9 = new JLabel();
						jPanel2.add(jLabel9, new AnchorConstraint(479, 412, 589, 241, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel9.setText("Port No:");
						jLabel9.setPreferredSize(new java.awt.Dimension(70, 21));
					}
					{
						jTextField3 = new JTextField();
						jPanel2.add(jTextField3, new AnchorConstraint(442, 704, 589, 412, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField3.setPreferredSize(new java.awt.Dimension(119, 28));
					}
				}
				{
					jPanel3 = new JPanel();
					AnchorLayout jPanel3Layout = new AnchorLayout();
					jPanel3.setLayout(jPanel3Layout);
					jTabbedPane1.addTab("TIN ", null, jPanel3, null);
					jPanel3.setLayout(jPanel3Layout);
					jPanel3.setInheritsPopupMenu(true);
					jPanel3.setBackground(new java.awt.Color(255,255,255));
					{
						jLabel10 = new JLabel();
						jPanel3.add(jLabel10, new AnchorConstraint(2, 550, 159, 18, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel10.setText("TIN Configuration");
						jLabel10.setFont(new java.awt.Font("Tahoma",1,12));
						jLabel10.setPreferredSize(new java.awt.Dimension(217, 28));
					}
					{
						jTextField4 = new JTextField();
						jPanel3.add(jTextField4, new AnchorConstraint(354, 910, 472, 241, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField4.setPreferredSize(new java.awt.Dimension(273, 21));
					}
					{
						jLabel11 = new JLabel();
						jPanel3.add(jLabel11, new AnchorConstraint(354, 241, 432, 121, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel11.setText("TIN");
						jLabel11.setPreferredSize(new java.awt.Dimension(49, 14));
					}
					{
						jButton4 = new JButton();
						jPanel3.add(jButton4, new AnchorConstraint(667, 910, 784, 756, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton4.setText("Insert");
						jButton4.setPreferredSize(new java.awt.Dimension(63, 21));
						jButton4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								StorePropertyHandler.setTinNo(jTextField4.getText());
								JOptionPane.showMessageDialog(null, 
											"Updated TIN", 
											"Success",JOptionPane.INFORMATION_MESSAGE);
							}
						});
					}
				}
				{
					jPanel4 = new JPanel();
					jTabbedPane1.addTab("Tenant Code", null, jPanel4, null);
					AnchorLayout jPanel4Layout = new AnchorLayout();
					jPanel4.setLayout(jPanel4Layout);
					jPanel4.setBackground(new java.awt.Color(255, 255, 255));
					{
						jButton5 = new JButton();
						jPanel4.add(jButton5, new AnchorConstraint(
							706,
							928,
							863,
							802,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
						jButton5.setText("Insert");
						jButton5
							.setPreferredSize(new java.awt.Dimension(63, 28));
						jButton5.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								StorePropertyHandler.setTenantNo(jTextField5.getText());
								JOptionPane.showMessageDialog(
																null,
																"Updated Tenant Code",
																"Success",
																JOptionPane.INFORMATION_MESSAGE);

							}
						});
					}
					{
						jTextField5 = new JTextField();
						jPanel4.add(jTextField5, new AnchorConstraint(353, 928, 528, 225, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField5.setPreferredSize(new java.awt.Dimension(350, 28));
					}
					{
						jLabel13 = new JLabel();
						jPanel4.add(jLabel13, new AnchorConstraint(
							354,
							155,
							511,
							29,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
						jLabel13.setText("Tenant Code");
						jLabel13
							.setPreferredSize(new java.awt.Dimension(63, 28));
					}
					{
						jLabel12 = new JLabel();
						jPanel4.add(jLabel12, new AnchorConstraint(
							41,
							563,
							198,
							29,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
						jLabel12.setText("Tenant Code Configuration");
						jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12));
						jLabel12.setPreferredSize(new java.awt.Dimension(
							266,
							28));
					}
				}
				{
					jPanel5 = new JPanel();
					jTabbedPane1.addTab("Terminal Number", null, jPanel5, null);
					AnchorLayout jPanel5Layout = new AnchorLayout();
					jPanel5.setLayout(jPanel5Layout);
					jPanel5.setBackground(new java.awt.Color(255, 255, 255));
					{
						jButton6 = new JButton();
						jPanel5.add(jButton6, new AnchorConstraint(571, 619, 746, 366, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton6.setText("Apply Changes");
						jButton6.setPreferredSize(new java.awt.Dimension(126, 28));
						jButton6.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								StorePropertyHandler.setTerminalNo(jTextField6.getText());
								JOptionPane.showMessageDialog(null, 
											"Updated Terminal Number", 
											"Success",JOptionPane.INFORMATION_MESSAGE);
							}
						});
					}
					{
						jTextField6 = new JTextField();
						jPanel5.add(jTextField6, new AnchorConstraint(309, 647, 484, 338, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField6.setPreferredSize(new java.awt.Dimension(154, 28));
					}
					{
						jLabel14 = new JLabel();
						jPanel5.add(jLabel14, new AnchorConstraint(90, 689, 309, 324, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel14.setText("Terminal Number Configuration");
						jLabel14.setPreferredSize(new java.awt.Dimension(182, 35));
						jLabel14.setFont(new java.awt.Font("Tahoma",1,11));
					}
				}
				{
					jPanel6 = new JPanel();
					jTabbedPane1.addTab("Batch Number", null, jPanel6, null);
					AnchorLayout jPanel6Layout = new AnchorLayout();
					jPanel6.setLayout(jPanel6Layout);
					jPanel6.setBackground(new java.awt.Color(255,255,255));
					{
						jButton7 = new JButton();
						jPanel6.add(jButton7, new AnchorConstraint(659, 619, 834, 380, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton7.setText("Apply Changes");
						jButton7.setPreferredSize(new java.awt.Dimension(119, 28));
						jButton7.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								StorePropertyHandler.setBatchNo(jTextField7.getText());
								JOptionPane.showMessageDialog(null, 
											"Updated Batch Download Number", 
											"Success",JOptionPane.INFORMATION_MESSAGE);
							}
						});
					}
					{
						jTextField7 = new JTextField();
						jPanel6.add(jTextField7, new AnchorConstraint(353, 661, 528, 338, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField7.setPreferredSize(new java.awt.Dimension(161, 28));
					}
					{
						jLabel15 = new JLabel();
						jPanel6.add(jLabel15, new AnchorConstraint(134, 774, 309, 282, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel15.setText("Batch Download Number Configuration");
						jLabel15.setPreferredSize(new java.awt.Dimension(245, 28));
						jLabel15.setFont(new java.awt.Font("Tahoma",1,11));
					}
				}
			}
			this.setSize(554, 356);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
