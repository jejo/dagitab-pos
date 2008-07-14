package forms;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import connection.DataUtil;
import connection.LogHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


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
public class PasswordManager extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JButton jButton1;
	private JPasswordField jPasswordField2;
	private JLabel jLabel5;
	private JButton jButton3;
	private JButton jButton2;
	private JPasswordField jPasswordField3;
	private JLabel jLabel4;
	private JPasswordField jPasswordField1;
	private JLabel jLabel3;
	private DBManager db;
	private MainWindow form;
	private LogHandler cachewriter;
	private DataUtil datautil;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PasswordManager inst = new PasswordManager(frame);
		inst.setVisible(true);
	}
	
	public PasswordManager(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public PasswordManager(MainWindow form, DBManager db){
		super(form);
		this.db = db;
		cachewriter = new LogHandler();
		datautil = new DataUtil();
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("Password Manager");
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3, new AnchorConstraint(843, 737, 949, 531, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton3.setText("Close");
				jButton3.setPreferredSize(new java.awt.Dimension(84, 28));
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						PasswordManager.this.dispose();
					}
				});
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new AnchorConstraint(843, 480, 949, 257, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton2.setText("Change");
				jButton2.setPreferredSize(new java.awt.Dimension(91, 28));
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(jTextField1.getText().length() == 0 ||
						   jPasswordField1.getText().length() == 0 ||
						   jPasswordField2.getText().length() == 0 ||
						   jPasswordField3.getText().length() == 0){
							
							JOptionPane.showMessageDialog(null, 
									"Please complete all fields", 
									"Warning",JOptionPane.ERROR_MESSAGE);
							
						}
						else{
							
							ResultSet rs = db.executeQuery("SELECT * FROM clerk_lu WHERE password = '"+jPasswordField1.getText()+"' AND clerk_code = '"+jTextField1.getText()+"'");
//							ResultSet rs = db.executeQuery("SELECT * FROM clerk_lu WHERE AES_DECRYPT(password,'babyland') = '"+jPasswordField1.getText()+"' AND clerk_code = '"+jTextField1.getText()+"'");
							try {
								if(rs.next()){
									if(jPasswordField2.getText().equals(jPasswordField3.getText())){
										int num = db.executeUpdate("UPDATE clerk_lu SET password = '"+jPasswordField2.getText()+"' WHERE clerk_code = '"+jTextField1.getText()+"'");
//										int num = db.executeUpdate("UPDATE clerk_lu SET password = AES_ENCRYPT('"+jPasswordField2.getText()+"','babyland') WHERE clerk_code = '"+jTextField1.getText()+"'");
										if(num == 1){
											String[] keyFields = new String[1];
											keyFields[0] = "CLERK_CODE";
											String[] keys = new String[1];
											keys[0] = jTextField1.getText();
//											
											String dataToLog = DataUtil.rowToData("EDIT","clerk_lu",keyFields,keys);
											cachewriter.writeToFile(dataToLog);
											JOptionPane.showMessageDialog(null, 
													"Updated password!", 
													"Information",JOptionPane.INFORMATION_MESSAGE);
											jTextField1.setText("");
											jPasswordField1.setText("");
											jPasswordField2.setText("");
											jPasswordField3.setText("");
										}
									}
									else{
										JOptionPane.showMessageDialog(null, 
												"Please confirm your new passwords correctly", 
												"Warning",JOptionPane.ERROR_MESSAGE);
									}
									
									
								}
								else{
									JOptionPane.showMessageDialog(null, 
											"Invalid Clerk Login", 
											"Warning",JOptionPane.ERROR_MESSAGE);
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				});
			}
			{
				jPasswordField1 = new JPasswordField();
				getContentPane().add(jPasswordField1, new AnchorConstraint(317, 668, 422, 292, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPasswordField1.setPreferredSize(new java.awt.Dimension(154, 28));
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new AnchorConstraint(159, 788, 265, 668, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("...");
				jButton1.setPreferredSize(new java.awt.Dimension(49, 28));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
//						Clerk dialog = new Clerk(PasswordManager.this,"password");
//						dialog.setLocationRelativeTo(null);
//						dialog.setVisible(true);
					}
				});
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(159, 309, 265, 155, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setText("Clerk ID");
				jLabel2.setPreferredSize(new java.awt.Dimension(63, 28));
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1, new AnchorConstraint(159, 668, 265, 292, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextField1.setPreferredSize(new java.awt.Dimension(154, 28));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(28, 583, 133, 18, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Passsword Manager");
				jLabel1.setPreferredSize(new java.awt.Dimension(231, 28));
				jLabel1.setFont(new java.awt.Font("Tahoma",1,18));
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3, new AnchorConstraint(317, 309, 422, 155, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel3.setText("Password");
				jLabel3.setPreferredSize(new java.awt.Dimension(63, 28));
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4, new AnchorConstraint(501, 275, 607, 86, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel4.setText("New Password");
				jLabel4.setPreferredSize(new java.awt.Dimension(77, 28));
			}
			{
				jPasswordField2 = new JPasswordField();
				getContentPane().add(jPasswordField2, new AnchorConstraint(501, 668, 607, 292, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPasswordField2.setPreferredSize(new java.awt.Dimension(154, 28));
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5, new AnchorConstraint(659, 275, 765, 52, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel5.setText("Confirm Password");
				jLabel5.setPreferredSize(new java.awt.Dimension(91, 28));
			}
			{
				jPasswordField3 = new JPasswordField();
				getContentPane().add(jPasswordField3, new AnchorConstraint(659, 668, 765, 292, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPasswordField3.setPreferredSize(new java.awt.Dimension(154, 28));
			}
			this.setSize(417, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setClerk(String id){
		jTextField1.setText(id);
	}

}
