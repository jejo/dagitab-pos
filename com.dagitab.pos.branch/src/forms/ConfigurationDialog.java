package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.LoggerUtility;
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
public class ConfigurationDialog extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	private JTabbedPane jTabbedPane1;
	private JLabel configurationLabel;
	private JPanel jPanel1;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton jButton4;
	private JLabel jLabel11;
	private JTextField jTextField4;
	private JTextField jTextField5;
	private JTextField jTextField6;
	private JComboBox connectionComboBox;
	private JButton jButton7;
	private JTextField jTextField7;
	private JLabel jLabel15;
	private JPanel jPanel6;
	private JButton jButton6;
	private JLabel jLabel14;
	private JPanel jPanel5;
	private JButton jButton5;
	private JLabel jLabel13;
	private JPanel jPanel4;
	private JPanel jPanel3;
	private JButton jButton3;
	private JLabel jLabel8;
	private JButton jButton1;
	private JLabel jLabel7;
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
	
	public ConfigurationDialog(JFrame frame) {
		super(frame);
		
		initGUI();
		
		jTextField1.setText(StorePropertyHandler.getStoreNo());
		jTextField4.setText(StorePropertyHandler.getTinNo());
		jTextField5.setText(StorePropertyHandler.getTenantNo());
		jTextField6.setText(StorePropertyHandler.getTerminalNo());
		jTextField7.setText(StorePropertyHandler.getBatchNo());
		
		init();
		
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
				getContentPane().add(jButton2, new AnchorConstraint(854, 566, 932, 413, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Close");
				jButton2.setPreferredSize(new java.awt.Dimension(102, 25));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						ConfigurationDialog.this.dispose();
					}
				});
			}
			{
				configurationLabel = new JLabel();
				getContentPane().add(configurationLabel, new AnchorConstraint(21, 579, 119, 16, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				configurationLabel.setText("Configuration Settings");
				configurationLabel.setPreferredSize(new java.awt.Dimension(252, 35));
				configurationLabel.setFont(new java.awt.Font("Tahoma",0,18));
				configurationLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "configurationLabel");
				configurationLabel.getActionMap().put("configurationLabel",getConfigurationLabelAbstractAction() );
				
			}
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1, new AnchorConstraint(153, 954, 805, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTabbedPane1.setPreferredSize(new java.awt.Dimension(413, 210));
				jTabbedPane1.setFont(new java.awt.Font("Tahoma",0,11));
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
					jPanel1.setPreferredSize(new java.awt.Dimension(609, 168));
					{
						jButton1 = new JButton();
						jPanel1.add(jButton1, new AnchorConstraint(665, 588, 814, 409, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton1.setText("Apply Changes");
						jButton1.setPreferredSize(new java.awt.Dimension(109, 27));
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
						jPanel1.add(jTextField1, new AnchorConstraint(422, 549, 571, 439, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField1.setPreferredSize(new java.awt.Dimension(67, 27));
					}
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4, new AnchorConstraint(279, 549, 389, 445, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel4.setText("Store Code:");
						jLabel4.setPreferredSize(new java.awt.Dimension(63, 20));
					}
					{
						jLabel5 = new JLabel();
						jPanel1.add(jLabel5, new AnchorConstraint(52, 788, 196, 222, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel5.setText("The store code identifies on  what branch this application is installed.");
						jLabel5.setPreferredSize(new java.awt.Dimension(345, 26));
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
						jLabel7 = new JLabel();
						jPanel2.add(getConnectionComboBox(), new AnchorConstraint(400, 715, 511, 240, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jPanel2.add(jLabel7, new AnchorConstraint(74, 766, 223, 225, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel7.setText("the connection setting is required to communicate with the server");
						jLabel7.setPreferredSize(new java.awt.Dimension(329, 27));
					}
					{
						jLabel8 = new JLabel();
						jPanel2.add(jLabel8, new AnchorConstraint(295, 412, 369, 241, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel8.setText("Connection Option");
						jLabel8.setPreferredSize(new java.awt.Dimension(70, 14));
					}
					{
						jButton3 = new JButton();
						jPanel2.add(jButton3, new AnchorConstraint(732, 587, 875, 403, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton3.setText("Apply Changes");
						jButton3.setPreferredSize(new java.awt.Dimension(112, 26));
						jButton3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								
								if(connectionComboBox.getSelectedItem().toString().split("-")[0].equals("VPN")){
									Properties storeProperties = StorePropertyHandler.getProperties();
									String vpnIp = storeProperties.getProperty("vpn.connect");
									StorePropertyHandler.setFtpServer(vpnIp);
								}
								else {
									Properties storeProperties = StorePropertyHandler.getProperties();
									String dialUpIp = storeProperties.getProperty("dial.connect");
									StorePropertyHandler.setFtpServer(dialUpIp);
								}
									
									JOptionPane.showMessageDialog(null, 
											"Updated connection option. Please restart the application.", 
											"Success",JOptionPane.INFORMATION_MESSAGE);
								
							}
						});
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
						jTextField4 = new JTextField();
						jPanel3.add(jTextField4, new AnchorConstraint(323, 844, 439, 174, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField4.setPreferredSize(new java.awt.Dimension(408, 21));
					}
					{
						jLabel11 = new JLabel();
						jPanel3.add(jLabel11, new AnchorConstraint(179, 564, 256, 445, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel11.setText("TIN Number");
						jLabel11.setPreferredSize(new java.awt.Dimension(72, 14));
					}
					{
						jButton4 = new JButton();
						jPanel3.add(jButton4, new AnchorConstraint(555, 596, 693, 409, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton4.setText("Apply Changes");
						jButton4.setPreferredSize(new java.awt.Dimension(114, 25));
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
						jPanel4.add(jButton5, new AnchorConstraint(588, 585, 720, 404, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton5.setText("Apply Changes");
						jButton5.setPreferredSize(new java.awt.Dimension(110, 24));
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
						jPanel4.add(jTextField5, new AnchorConstraint(317, 839, 428, 155, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField5.setPreferredSize(new java.awt.Dimension(417, 20));
					}
					{
						jLabel13 = new JLabel();
						jPanel4.add(jLabel13, new AnchorConstraint(129, 562, 284, 442, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel13.setText("Tenant Code");
						jLabel13.setPreferredSize(new java.awt.Dimension(73, 28));
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
						jPanel5.add(jButton6, new AnchorConstraint(588, 590, 720, 408, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton6.setText("Apply Changes");
						jButton6.setPreferredSize(new java.awt.Dimension(111, 24));
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
						jPanel5.add(jTextField6, new AnchorConstraint(306, 651, 433, 340, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField6.setPreferredSize(new java.awt.Dimension(189, 23));
					}
					{
						jLabel14 = new JLabel();
						jPanel5.add(jLabel14, new AnchorConstraint(124, 565, 273, 427, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel14.setText("Terminal Number ");
						jLabel14.setPreferredSize(new java.awt.Dimension(84, 27));
						jLabel14.setFont(new java.awt.Font("Tahoma",0,11));
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
						jPanel6.add(jButton7, new AnchorConstraint(588, 590, 709, 411, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jButton7.setText("Apply Changes");
						jButton7.setPreferredSize(new java.awt.Dimension(109, 22));
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
						jPanel6.add(jTextField7, new AnchorConstraint(339, 659, 466, 337, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jTextField7.setPreferredSize(new java.awt.Dimension(196, 23));
					}
					{
						jLabel15 = new JLabel();
						jPanel6.add(jLabel15, new AnchorConstraint(135, 611, 306, 406, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						jLabel15.setText("Batch Download Number");
						jLabel15.setPreferredSize(new java.awt.Dimension(125, 31));
						jLabel15.setFont(new java.awt.Font("Tahoma",0,11));
					}
				}
			}
			
			this.setSize(682, 356);
			
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}

	private AbstractAction getConfigurationLabelAbstractAction() {
		AbstractAction confirmationLabelAction = new AbstractAction("Confirmation Dialog", null) {
			
			public void actionPerformed(ActionEvent evt) {
				ConfigurationDialog.this.dispose();
			}
		};
		return confirmationLabelAction;
	}
	
	private JComboBox getConnectionComboBox() {
		if(connectionComboBox == null) {
			Properties properties = StorePropertyHandler.getProperties();
			String vpnIp = properties.getProperty("vpn.connect");
			String dialIp = properties.getProperty("dial.connect");
			ComboBoxModel connectionComboBoxModel = 
				new DefaultComboBoxModel(
						new String[] { "VPN-"+vpnIp, "Dial-up-"+dialIp });
			connectionComboBox = new JComboBox();
			connectionComboBox.setModel(connectionComboBoxModel);
			connectionComboBox.setPreferredSize(new java.awt.Dimension(289, 20));
		}
		return connectionComboBox;
	}
	
	private void init(){
		Properties properties = StorePropertyHandler.getProperties();
		String vpnIp = properties.getProperty("vpn.connect");
		String dialIp = properties.getProperty("dial.connect");
		String ftpServer = properties.getProperty("ftpServer");
		
		if(ftpServer.equals(vpnIp)){
			
			connectionComboBox.setSelectedIndex(0); //set to vpn
		}
		else{
			connectionComboBox.setSelectedIndex(1);
		}
		
		
	}

}
