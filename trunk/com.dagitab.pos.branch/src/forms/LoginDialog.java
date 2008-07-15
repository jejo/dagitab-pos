package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import main.Main;
import util.Validator;
import bus.ClerkService;


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
public class LoginDialog extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jButton1;
	private JPasswordField jPasswordField1;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				LoginDialog inst = new LoginDialog(frame);
				inst.setVisible(true);
				inst.setLocationRelativeTo(null);
			}
		});
	}
	
	public LoginDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				getContentPane().setBackground(new java.awt.Color(255,255,255));
				this.setTitle("Login");
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setBounds(150, 11, 134, 27);
					jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/logo.jpg")));
				}
				{
					jTextField1 = new JTextField();
					getContentPane().add(jTextField1);
					jTextField1.setBounds(94, 80, 233, 28);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("Clerk Code");
					jLabel2.setBounds(176, 62, 68, 14);
					jLabel2.setFont(new java.awt.Font("Tahoma",0,12));
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("Password");
					jLabel3.setBounds(177, 128, 59, 14);
					jLabel3.setFont(new java.awt.Font("Tahoma",0,12));
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("Login");
					jButton1.setBounds(170, 204, 66, 26);
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							String clerkCode = jTextField1.getText();
							String password = new String(jPasswordField1.getPassword());
							System.out.println("Clerk Logging in as: "+clerkCode+" with password: "+password);
							if(Validator.isNumeric(clerkCode)){
								if(ClerkService.isValid(clerkCode, password)){
									Main.setClerkCode(Integer.parseInt(clerkCode));
									Main.showMainWindow();
									Main.hideLoginDiaolog();
								}
								else{
									JOptionPane.showMessageDialog(null, "Please check your login information.", "Invalid Clerk Login", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					});
				}
				{
					jPasswordField1 = new JPasswordField();
					getContentPane().add(jPasswordField1);
					jPasswordField1.setBounds(94, 148, 233, 30);
				}
			}
			this.setSize(429, 311);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetTextFieldValues(){
		jTextField1.setText("");
		jPasswordField1.setText("");
	}

}
