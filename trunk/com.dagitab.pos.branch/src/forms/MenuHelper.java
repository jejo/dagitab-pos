package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import main.Main;

public class MenuHelper {
	private static JMenuBar MENU_BAR = new JMenuBar();
	
	private static JMenu FILE_MENU = new JMenu("File");
	private static JMenu PULL_OUT_MENU = new JMenu("Pull Outs");
	private static JMenu DELIVERY_MENU = new JMenu("Deliveries");
	private static JMenu REPORTS_MENU = new JMenu("Reports");
	private static JMenu ABOUT_MENU = new JMenu("About");
	
	private static JMenuItem CONFIGURATION = new JMenuItem("Configuration Settings");
	private static JMenuItem PASSWORD = new JMenuItem("Password Manager");
	private static JMenuItem CONNECT = new JMenuItem("Connect to Server");
	private static JMenuItem RE_PRINT_RECEIPT = new JMenuItem("Re-Print Receipt");
	private static JSeparator SEPARATOR = new JSeparator();
	private static JMenuItem LOGOUT = new JMenuItem("Log-out");
	private static JMenuItem EXIT = new JMenuItem("Exit");
	
	private static JMenuItem DELIVERY_MANAGER = new JMenuItem("Delivery Manager");
	
	private static JMenuItem PULL_OUT_REQUEST = new JMenuItem("Create Pull-Out Request");
	private static JMenuItem PULL_OUT_LIST = new JMenuItem("Browse Pending Pull-Outs");
	
	private static JMenuItem HELP = new JMenuItem("Help");
	private static JMenuItem ABOUT = new JMenuItem("About");
	
	static{
		MENU_BAR.add(FILE_MENU);
		MENU_BAR.add(DELIVERY_MENU);
		MENU_BAR.add(PULL_OUT_MENU);
		MENU_BAR.add(REPORTS_MENU);
		MENU_BAR.add(ABOUT_MENU);
		
		FILE_MENU.add(CONFIGURATION);
		FILE_MENU.add(PASSWORD);
		FILE_MENU.add(CONNECT);
		FILE_MENU.add(RE_PRINT_RECEIPT);
		FILE_MENU.add(LOGOUT);
		FILE_MENU.add(SEPARATOR);
		FILE_MENU.add(EXIT);
		
		DELIVERY_MENU.add(DELIVERY_MANAGER);
		
		PULL_OUT_MENU.add(PULL_OUT_REQUEST);
		PULL_OUT_MENU.add(PULL_OUT_LIST);
		
		ABOUT_MENU.add(HELP);
		ABOUT_MENU.add(ABOUT);
	}
	
	static{
		
		
		CONNECT.setEnabled(false);
		RE_PRINT_RECEIPT.setEnabled(false);
		PULL_OUT_MENU.setEnabled(false);
		DELIVERY_MENU.setEnabled(false);
		REPORTS_MENU.setEnabled(false);
		ABOUT_MENU.setEnabled(false);
		
		CONFIGURATION.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ConfigurationDialog configurationDialog = new ConfigurationDialog(Main.getInst());
				configurationDialog.setLocationRelativeTo(null);
				configurationDialog.setVisible(true);
				
			}
			
		});
		
		
		PASSWORD.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				PasswordManagerDialog passwordManagerDialog = new PasswordManagerDialog(Main.getInst());
				passwordManagerDialog.setLocationRelativeTo(null);
				passwordManagerDialog.setVisible(true);
			}
			
		});
		LOGOUT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
			Main.setClerkCode(null);
			Main.hideMainWindow();
			Main.clearLoginInfo();
			Main.showLoginDialog();
		}});
	}
	
	public static JMenuBar getMenuBar(){
		return MENU_BAR;
	}
}
