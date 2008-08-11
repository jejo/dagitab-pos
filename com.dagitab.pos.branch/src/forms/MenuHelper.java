package forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import main.Main;
import forms.delivery.DeliveryDialog;
import forms.pullouts.PullOutDialog;
import forms.pullouts.PullOutRequestDialog;
import forms.receipts.InvoiceViewer;

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
	private static MainWindow Window;
	
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
		
//		REPORTS_MENU.setEnabled(false);
		
		CONFIGURATION.setAction(getShowConfigurationDialogAbstractAction());
		
		RE_PRINT_RECEIPT.setAction(getShowReprintDialogAction());
		
		
		PASSWORD.setAction(getShowPasswordDialogAction());
		
		
		LOGOUT.setAction(getLogoutAction());
		
		EXIT.setAction(getExitAction());
		
		DELIVERY_MANAGER.setAction(getDeliveryManagerAction());
		
		PULL_OUT_REQUEST.setAction(getPullOutRequestAction());
		
		PULL_OUT_LIST.setAction(getPullOutlistAction());
		
		ABOUT.setAction(getAboutAction());
	}
	
	public static JMenuBar getMenuBar(){
		return MENU_BAR;
	}
	
	
	//Action Declaration starts here
	
	
	@SuppressWarnings("serial")
	private static AbstractAction getAboutAction() {
		AbstractAction aboutAction  = new AbstractAction("About",null){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutDialog aboutDialog = new AboutDialog(Main.getInst());
				aboutDialog.setLocationRelativeTo(null);
				aboutDialog.setVisible(true);
			}
		};
		return aboutAction;
	}


	private static AbstractAction getPullOutlistAction() {
		AbstractAction pullOutRequestAction = new AbstractAction("Browse Pending Pull Outs", null) {
			public void actionPerformed(ActionEvent e) {
				PullOutDialog pullOutDialog = new PullOutDialog(Main.getInst());
				pullOutDialog.setLocationRelativeTo(null);
				pullOutDialog.setVisible(true);
			}
		};
//		pullOutRequestAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed D"));
		return pullOutRequestAction;
	}


	private static AbstractAction getShowConfigurationDialogAbstractAction() {
		AbstractAction showPopupDialogAbstractAction = new AbstractAction("Configuration Settings", null) {
			public void actionPerformed(ActionEvent evt) {
				ConfigurationDialog configurationDialog = new ConfigurationDialog(Main.getInst());
				configurationDialog.setLocationRelativeTo(null);
				configurationDialog.setVisible(true);
			}
		};
		showPopupDialogAbstractAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed C"));
		return showPopupDialogAbstractAction;
	}

	private static AbstractAction getShowPasswordDialogAction() {
		AbstractAction showPasswordDialogAction = new AbstractAction("Password Manager", null) {
			public void actionPerformed(ActionEvent e) {
				PasswordManagerDialog passwordManagerDialog = new PasswordManagerDialog(Main.getInst());
				passwordManagerDialog.setLocationRelativeTo(null);
				passwordManagerDialog.setVisible(true);
			}
		};
		showPasswordDialogAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed W"));
		return showPasswordDialogAction;
	}

	private static AbstractAction getShowReprintDialogAction() {
		AbstractAction showReprintDialogAction = new AbstractAction("Re-Print Receipt", null) {
			public void actionPerformed(ActionEvent e) {
				InvoiceViewer invoiceViewer = new InvoiceViewer(Main.getInst());
				invoiceViewer.setLocationRelativeTo(null);
				invoiceViewer.setVisible(true);
			}
		};
		showReprintDialogAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed P"));
		return showReprintDialogAction;
	}

	private static AbstractAction getLogoutAction() {
		AbstractAction showLogoutAction = new AbstractAction("Log-out", null) {
			public void actionPerformed(ActionEvent e) {
				Main.setClerkCode(null);
				Main.hideMainWindow();
				Main.clearLoginInfo();
				Main.showLoginDialog();
			}
		};
		showLogoutAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed L"));
		return showLogoutAction;
	}

	private static AbstractAction getDeliveryManagerAction() {
		AbstractAction showDeliveryManagerAction = new AbstractAction("Delivery Manager", null) {
			public void actionPerformed(ActionEvent e) {
				DeliveryDialog deliveryDialog = new DeliveryDialog(Main.getInst());
				deliveryDialog.setLocationRelativeTo(null);
				deliveryDialog.setVisible(true);
			}
		};
		showDeliveryManagerAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed D"));
		return showDeliveryManagerAction;
	}

	private static AbstractAction getExitAction() {
		AbstractAction showExitAction = new AbstractAction("Exit", null) {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane
				.showConfirmDialog(
					Window,
					"Are you sure you want to exit this application?",
					"Confirmation",
					JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					System.exit(0);
				}
			}
		};
		showExitAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed F4"));
		return showExitAction;
	}
	
	private static AbstractAction getPullOutRequestAction(){
		AbstractAction pullOutRequestAction = new AbstractAction("Create Pull Out Request", null) {
			public void actionPerformed(ActionEvent e) {
				PullOutRequestDialog pullOutRequestDialog = new PullOutRequestDialog(Main.getInst());
				pullOutRequestDialog.setLocationRelativeTo(null);
				pullOutRequestDialog.setVisible(true);
			}
		};
//		pullOutRequestAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed D"));
		return pullOutRequestAction;
	}
}
