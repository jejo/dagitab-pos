package forms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import main.Main;

import org.apache.log4j.Logger;

import util.LoggerUtility;
import util.Validator;
import bus.ClerkService;
import bus.RobinsonsComplianceService;
import bus.StoreService;
import domain.Clerk;
import forms.reports.RobinsonsComplianceDialog;


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
public class LoginDialog extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jButton1;
	private JPasswordField jPasswordField1;
	private static Logger logger = Logger.getLogger(LoginDialog.class);

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
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
	
	protected void processWindowEvent(WindowEvent e) {
		 if(e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		 }
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
							login();
						}
					});
				}
				{
					jPasswordField1 = new JPasswordField();
					getContentPane().add(jPasswordField1);
					jPasswordField1.setBounds(94, 148, 233, 30);
					jPasswordField1.addKeyListener(new KeyAdapter() {
						
					 @Override
						public void keyPressed(KeyEvent e) {
							 int key = e.getKeyCode();
						     if (key == KeyEvent.VK_ENTER){
						    	 login();
						     }
						}
					});
				}
			}
			this.setSize(429, 311);
		} catch (Exception e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
	}
	
	public void resetTextFieldValues(){
		jTextField1.setText("");
		jPasswordField1.setText("");
	}
	
	public void login(){
		String clerkCode = jTextField1.getText();
		String password = new String(jPasswordField1.getPassword());
		logger.info("Clerk Logging in as: "+clerkCode);
		if(Validator.isNumeric(clerkCode)){
			if(ClerkService.isValid(clerkCode, password)){
				Main.setClerkCode(Integer.parseInt(clerkCode));
				Main.showMainWindow();
				Main.hideLoginDiaolog();
				Clerk clerk = ClerkService.getClerkByID(Integer.parseInt(clerkCode));
				String branchName = StoreService.getStoreName(Integer.parseInt(Main.getStoreCode()));
				String userName = clerk.getFirstName()+" "+clerk.getLastName(); 
				Main.displayLoginInformation(userName, branchName);
				Main.clearInvoiceInformation();
				
				
				
				
				String isCompliance = Main.getProperties().getProperty("compliance");
				String periodicRate = Main.getProperties().getProperty("compliance.periodicRate");
				final Integer sendUnsentDaysNo = Integer.valueOf(Main.getProperties().getProperty("send.unsentdays.no"));
				//galleria compliance
				
				Calendar cal = Calendar.getInstance();
				 
				Date currentTransDate = RobinsonsComplianceService.getInstance().getTransDateBasedOnEodDate(new Date());
				
				cal.setTime(currentTransDate);
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1; // month is zero
																	// based!!
				int day = cal.get( Calendar.DAY_OF_MONTH);
				
				if (RobinsonsComplianceService.getInstance().getEodSentFlag(month, day, year) > 0) { // EOD already sent for the day
					Main.getInst().disableTransaction();
				} else {
					cal.setTimeInMillis(new Date().getTime());
					
					int hour = cal.get( Calendar.HOUR_OF_DAY );
					
					if ((hour >= 4 && hour < 9) ) { //blackhole time
						Main.getInst().disableTransaction();
					}
				}
				if(isCompliance.equals("galleria")){
					
					Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask(){

						@Override
						public void run() {
							logger.info("Trying to send unsent files…");
							Main.getInst().getStatusLabel().setText("Trying to send unsent files\u2026");
							
							List<Date> listDates = RobinsonsComplianceService.getInstance().getUnsentComplianceReports(sendUnsentDaysNo);
							
							
							ArrayList<String> sentDatesList = new ArrayList<String>();
							ArrayList<String> unsentDateList = new ArrayList<String>();
							for(int i = 0; i<listDates.size(); i++){
								Date transDate = listDates.get(i);
								Date eodDate = RobinsonsComplianceService.getInstance().getEodDateBasedOnTransDate(transDate);
								try {
									RobinsonsComplianceService.getInstance().generateAndSendComplianceReport(transDate, eodDate);
									sentDatesList.add(transDate.toString());
								} catch (IOException e) {
									unsentDateList.add(transDate.toString());
									e.printStackTrace();
								}
							}
							
							if(sentDatesList.size() == listDates.size()){
								Main.getInst().getStatusLabel().setText("Sales file successfully sent to RLC server");
//								JOptionPane.showMessageDialog(null, "Sales file successfully sent to RLC server", "Sending Success", JOptionPane.INFORMATION_MESSAGE);
								
							}
							else{
								String unsentDates = "";
								for(String s: unsentDateList){
									unsentDates += s;
								}
								Main.getInst().getStatusLabel().setText("Sales file is not sent to RLC server. Please contact your POS vendor");
//								JOptionPane.showMessageDialog(null, "Sales file is not sent to RLC server. Please contact your POS vendor", "Sending Failure", JOptionPane.ERROR_MESSAGE);
//								JOptionPane.showMessageDialog(null, "There were dates that weren't sent. These are: "+unsentDates, "Prompt", JOptionPane.ERROR_MESSAGE);
							}
							
							
							
							
							
						}}, Calendar.getInstance().getTime(), Long.valueOf(periodicRate));
				}
				
				
				
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Please check your login information.", "Invalid Clerk Login", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
