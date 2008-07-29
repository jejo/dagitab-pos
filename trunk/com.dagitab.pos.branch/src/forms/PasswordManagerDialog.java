package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import main.Main;
import bus.ClerkService;
import domain.Clerk;

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
public class PasswordManagerDialog extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JPasswordField newPasswordField;
	private JLabel jLabel5;
	private JButton jButton3;
	private JButton jButton2;
	private JPasswordField confirmPasswordField;
	private JLabel jLabel4;
	private JPasswordField oldPasswordField;
	private JLabel jLabel3;
	
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
		PasswordManagerDialog inst = new PasswordManagerDialog(frame);
		inst.setVisible(true);
	}
	
	public PasswordManagerDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public PasswordManagerDialog(MainWindow form){
		super(form);
		initGUI();
	}
	
	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			this.setTitle("Password Manager");
			getContentPane().setBackground(new java.awt.Color(255,255,255));
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3);
				jButton3.setText("Close");
				jButton3.setBounds(150, 168, 104, 28);
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						PasswordManagerDialog.this.dispose();
					}
				});
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("Change");
				jButton2.setBounds(55, 168, 89, 28);
				
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						if(ClerkService.isValid(Main.getClerkCode().toString(), new String(oldPasswordField.getPassword()))){
							Clerk clerk = ClerkService.getClerkByID(Main.getClerkCode());
							String newPassword = new String(newPasswordField.getPassword());
							String confirmPassword = new String (confirmPasswordField.getPassword());
							if(newPassword.equals(confirmPassword)){
								clerk.setPassword(newPassword);
								int success = ClerkService.updateClerkPassword(clerk);
								if(success > 0){
									JOptionPane.showMessageDialog(null, "Successfully changed password", "Prompt", JOptionPane.INFORMATION_MESSAGE);
									oldPasswordField.setText("");
									newPasswordField.setText("");
									confirmPasswordField.setText("");
								}
								else{
									JOptionPane.showMessageDialog(null, "System Error. Unable to change your password. Please try again later.", "Prompt", JOptionPane.ERROR_MESSAGE);
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Please make sure that your new password and confirm password is the same.", "Prompt", JOptionPane.ERROR_MESSAGE);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Please input your valid password", "Invalid Login", JOptionPane.ERROR_MESSAGE);
						}
//					
					}
				});
			}
			{
				oldPasswordField = new JPasswordField();
				getContentPane().add(oldPasswordField);
				oldPasswordField.setPreferredSize(new java.awt.Dimension(150, 21));
				oldPasswordField.setBounds(130, 45, 150, 21);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Passsword Manager");
				jLabel1.setPreferredSize(new java.awt.Dimension(231, 28));
				jLabel1.setFont(new java.awt.Font("Tahoma",0,18));
				jLabel1.setBounds(7, 7, 226, 28);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Password");
				jLabel3.setPreferredSize(new java.awt.Dimension(61, 28));
				jLabel3.setBounds(10, 41, 61, 28);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("New Password");
				jLabel4.setPreferredSize(new java.awt.Dimension(76, 28));
				jLabel4.setBounds(10, 75, 76, 28);
			}
			{
				newPasswordField = new JPasswordField();
				getContentPane().add(newPasswordField);
				newPasswordField.setPreferredSize(new java.awt.Dimension(150, 21));
				newPasswordField.setBounds(130, 81, 150, 21);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Confirm Password");
				jLabel5.setPreferredSize(new java.awt.Dimension(90, 28));
				jLabel5.setBounds(10, 114, 90, 28);
			}
			{
				confirmPasswordField = new JPasswordField();
				getContentPane().add(confirmPasswordField);
				confirmPasswordField.setPreferredSize(new java.awt.Dimension(150, 21));
				confirmPasswordField.setBounds(130, 118, 150, 21);
			}
			this.setSize(321, 263);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
