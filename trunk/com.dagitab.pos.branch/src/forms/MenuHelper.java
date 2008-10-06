package forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import main.Main;
import forms.delivery.DeliveryDialog;
import forms.pullouts.PullOutDialog;
import forms.pullouts.PullOutRequestDialog;
import forms.receipts.InvoiceViewer;
import forms.reports.InventoryViewerDialog;
import forms.reports.ReportFormDialog;
import forms.reports.RobinsonsComplianceDialog;
import forms.reports.SalesClerkProductionDialog;

public class MenuHelper {
	
	private static MenuHelper menuHelper = new MenuHelper();
	
	private static JMenuBar MENU_BAR = new JMenuBar();
	
	private static JMenu FILE_MENU = new JMenu("File");
	private static JMenu PULL_OUT_MENU = new JMenu("Pull Outs");
	private static JMenu DELIVERY_MENU = new JMenu("Deliveries");
	private static JMenu REPORTS_MENU = new JMenu("Reports");
	private static JMenu COMPLIANCE_MENU = new JMenu("Compliance");
	private static JMenu ABOUT_MENU = new JMenu("About");
	
	
	private static JMenuItem CONFIGURATION = new JMenuItem("Configuration Settings");
	private static JMenuItem PASSWORD = new JMenuItem("Password Manager");
	private static JMenuItem CONNECT = new JMenuItem("Connect to Server");
	private static JMenuItem RE_PRINT_RECEIPT = new JMenuItem("Re-Print Receipt");
	private static JSeparator SEPARATOR = new JSeparator();
	private static JMenuItem LOGOUT = new JMenuItem("Log-out");
	private static JMenuItem EXIT = new JMenuItem("Exit");
	
	private static JMenuItem DELIVERY_MANAGER = new JMenuItem(getDeliveryManagerAction());
	
	private static JMenuItem PULL_OUT_REQUEST = new JMenuItem("Create Pull-Out Request");
	private static JMenuItem PULL_OUT_LIST = new JMenuItem("Browse Pending Pull-Outs");
	
	
	private static JMenuItem INVENTORY = new JMenuItem("Inventory");
	private static JMenuItem REPORT_FORM = new JMenuItem("Export Reports");
	private static JMenuItem SALES_CLERK_PRODUCTION_REPORT = new JMenuItem("Sales Clerk Production Report");
	
	private static JMenuItem ROBINSONS_COMPLIANCE_FORM = new JMenuItem("Robinsons Compliance");
	
	
	private static JMenuItem DEBUGGER = new JMenuItem("Debugger");
	private static JMenuItem ABOUT = new JMenuItem("About");
	
	
	private static MainWindow Window;
	
	
	
	static{
		MENU_BAR.add(FILE_MENU);
		MENU_BAR.add(DELIVERY_MENU);
		MENU_BAR.add(PULL_OUT_MENU);
		MENU_BAR.add(REPORTS_MENU);
		MENU_BAR.add(COMPLIANCE_MENU);
//		MENU_BAR.add(DEBUGGER);
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
		
		ABOUT_MENU.add(DEBUGGER);
		ABOUT_MENU.add(ABOUT);
		
		REPORTS_MENU.add(INVENTORY);
		REPORTS_MENU.add(REPORT_FORM);
		REPORTS_MENU.add(SALES_CLERK_PRODUCTION_REPORT);
		
		COMPLIANCE_MENU.add(ROBINSONS_COMPLIANCE_FORM);
		
		
	}
	
	static{
		
		CONFIGURATION.setAction(getShowConfigurationDialogAbstractAction());
		CONFIGURATION.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/configuration.png")));
		
		RE_PRINT_RECEIPT.setAction(getShowReprintDialogAction());
		RE_PRINT_RECEIPT.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/print.png")));
		
		PASSWORD.setAction(getShowPasswordDialogAction());
		PASSWORD.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/password.png")));
		
		LOGOUT.setAction(getLogoutAction());
		LOGOUT.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/logout.png")));
		
		EXIT.setAction(getExitAction());
		EXIT.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/exit.png")));
		
		DELIVERY_MANAGER.setAction(getDeliveryManagerAction());
		DELIVERY_MANAGER.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/truck.png")));
		
		
		PULL_OUT_REQUEST.setAction(getPullOutRequestAction());
		PULL_OUT_REQUEST.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/pullout.png")));
		
		PULL_OUT_LIST.setAction(getPullOutlistAction());
		PULL_OUT_LIST.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/list.png")));
		
		ABOUT.setAction(getAboutAction());
		ABOUT.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/about.png")));
		
		INVENTORY.setAction(getInventoryAction());
		INVENTORY.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/inventory.png")));
		
		ROBINSONS_COMPLIANCE_FORM.setAction(getRobinsonsComplianceAction());
		ROBINSONS_COMPLIANCE_FORM.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/refresh.png")));
		
		String compliance = Main.getProperties().getProperty("compliance");
		if(!compliance.equals("galleria")){
//			Logger.getLogger(MenuHelper.class).info("hiding compliance robinsons");
			ROBINSONS_COMPLIANCE_FORM.setEnabled(false);
		}
		
		CONNECT.setAction(getConnectAction());
		CONNECT.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/connect.png")));
		
		REPORT_FORM.setAction(getReportAction());
		REPORT_FORM.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/reports.png")));
		
		SALES_CLERK_PRODUCTION_REPORT.setAction(getSalesClerkProductionReportAction());
		SALES_CLERK_PRODUCTION_REPORT.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/sales.png")));
		
		
		DEBUGGER.setAction(getDebuggerAction());
		DEBUGGER.setIcon(new ImageIcon(menuHelper.getClass().getClassLoader().getResource("images/icons/debug.gif")));
		
	}
	
	public static JMenuBar getMenuBar(){
		return MENU_BAR;
	}
	
	
	//Action Declaration starts here
	
	
	private static AbstractAction getDebuggerAction() {
		AbstractAction salesClerkProductionReportAction  = new AbstractAction("Debugger"){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DebuggerDialog dialog = new DebuggerDialog(Main.getInst());
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		};
		return salesClerkProductionReportAction;

	}
	
	
	private static AbstractAction getSalesClerkProductionReportAction() {
		AbstractAction salesClerkProductionReportAction  = new AbstractAction("Sales Clerk Production Report"){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SalesClerkProductionDialog salesClerkProductionDialog = new SalesClerkProductionDialog(Main.getInst());
				salesClerkProductionDialog.setLocationRelativeTo(null);
				salesClerkProductionDialog.setVisible(true);
				
				
			}
		};
		return salesClerkProductionReportAction;

	}


	@SuppressWarnings("serial")
	private static AbstractAction getRobinsonsComplianceAction() {
		AbstractAction robinsonsComplianceAction  = new AbstractAction("Robinsons Compliance"){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RobinsonsComplianceDialog robinsonsComplianceDialog = new RobinsonsComplianceDialog(Main.getInst());
				robinsonsComplianceDialog.setLocationRelativeTo(null);
				robinsonsComplianceDialog.setVisible(true);
				
			}
		};
		return robinsonsComplianceAction;
		
	}


	@SuppressWarnings("serial")
	private static AbstractAction getReportAction() {
		AbstractAction connectAction  = new AbstractAction("Export Reports"){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ReportFormDialog reportFormDialog = new ReportFormDialog(Main.getInst());
				reportFormDialog.setLocationRelativeTo(null);
				reportFormDialog.setVisible(true);
			}
		};
		return connectAction;
	}


	@SuppressWarnings("serial")
	private static AbstractAction getConnectAction() {
		AbstractAction connectAction  = new AbstractAction("Connect"){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SyncProgressDialog syncProgressDialog = new SyncProgressDialog(Main.getInst());
				syncProgressDialog.setLocationRelativeTo(null);
				syncProgressDialog.setVisible(true);
				
//				syncProgressDialog.setSyncRate(80);
			}
		};
//		connectAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed B"));
		return connectAction;
	}


	@SuppressWarnings("serial")
	private static AbstractAction getAboutAction() {
		AbstractAction aboutAction  = new AbstractAction("About"){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutDialog aboutDialog = new AboutDialog(Main.getInst());
				aboutDialog.setLocationRelativeTo(null);
				aboutDialog.setVisible(true);
			}
		};
		aboutAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed B"));
		return aboutAction;
	}


	@SuppressWarnings("serial")
	private static AbstractAction getPullOutlistAction() {
		AbstractAction pullOutRequestAction = new AbstractAction("Browse Pending Pull Outs") {
			public void actionPerformed(ActionEvent e) {
				PullOutDialog pullOutDialog = new PullOutDialog(Main.getInst());
				pullOutDialog.setLocationRelativeTo(null);
				pullOutDialog.setVisible(true);
			}
		};
		pullOutRequestAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed H"));
		return pullOutRequestAction;
	}


	@SuppressWarnings("serial")
	private static AbstractAction getShowConfigurationDialogAbstractAction() {
		AbstractAction showPopupDialogAbstractAction = new AbstractAction("Configuration Settings") {
			public void actionPerformed(ActionEvent evt) {
				ConfigurationDialog configurationDialog = new ConfigurationDialog(Main.getInst());
				configurationDialog.setLocationRelativeTo(null);
				configurationDialog.setVisible(true);
			}
		};
		showPopupDialogAbstractAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed C"));
		return showPopupDialogAbstractAction;
	}

	@SuppressWarnings("serial")
	private static AbstractAction getShowPasswordDialogAction() {
		AbstractAction showPasswordDialogAction = new AbstractAction("Password Manager") {
			public void actionPerformed(ActionEvent e) {
				PasswordManagerDialog passwordManagerDialog = new PasswordManagerDialog(Main.getInst());
				passwordManagerDialog.setLocationRelativeTo(null);
				passwordManagerDialog.setVisible(true);
			}
		};
		showPasswordDialogAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed P"));
		return showPasswordDialogAction;
	}

	@SuppressWarnings("serial")
	private static AbstractAction getShowReprintDialogAction() {
		AbstractAction showReprintDialogAction = new AbstractAction("Re-Print Receipt") {
			public void actionPerformed(ActionEvent e) {
				InvoiceViewer invoiceViewer = new InvoiceViewer(Main.getInst());
				invoiceViewer.setLocationRelativeTo(null);
				invoiceViewer.setVisible(true);
			}
		};
		showReprintDialogAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed T"));
		return showReprintDialogAction;
	}

	@SuppressWarnings("serial")
	private static AbstractAction getLogoutAction() {
		AbstractAction showLogoutAction = new AbstractAction("Log-out") {
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

	@SuppressWarnings("serial")
	private static AbstractAction getDeliveryManagerAction() {
		AbstractAction showDeliveryManagerAction = new AbstractAction("Delivery Manager") {
			public void actionPerformed(ActionEvent e) {
				DeliveryDialog deliveryDialog = new DeliveryDialog(Main.getInst());
				deliveryDialog.setLocationRelativeTo(null);
				deliveryDialog.setVisible(true);
			}
		};
		showDeliveryManagerAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed D"));
		return showDeliveryManagerAction;
	}

	@SuppressWarnings("serial")
	private static AbstractAction getExitAction() {
		AbstractAction showExitAction = new AbstractAction("Exit") {
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
	
	@SuppressWarnings("serial")
	private static AbstractAction getPullOutRequestAction(){
		AbstractAction pullOutRequestAction = new AbstractAction("Create Pull Out Request") {
			public void actionPerformed(ActionEvent e) {
				PullOutRequestDialog pullOutRequestDialog = new PullOutRequestDialog(Main.getInst());
				pullOutRequestDialog.setLocationRelativeTo(null);
				pullOutRequestDialog.setVisible(true);
			}
		};
		pullOutRequestAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed R"));
		return pullOutRequestAction;
	}
	
	@SuppressWarnings("serial")
	private static AbstractAction getInventoryAction(){
		AbstractAction inventoryAbstractAction = new AbstractAction("Inventory") {
			public void actionPerformed(ActionEvent e) {
				InventoryViewerDialog inventoryViewerDialog = new InventoryViewerDialog(Main.getInst());
				inventoryViewerDialog.setLocationRelativeTo(null);
				inventoryViewerDialog.setVisible(true);
			}
		};
//		pullOutRequestAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt pressed D"));
		return inventoryAbstractAction;
	}
}
