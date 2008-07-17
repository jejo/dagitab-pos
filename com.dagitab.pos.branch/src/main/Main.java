
package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import forms.LoginDialog;
import forms.MainWindow;
import forms.invoice.InvoicePanel;

public class Main {
	private static DBManager dbMan;
	private static String storeCode;
	private static MainWindow mainWindow;
	private static LoginDialog loginDialog;
	private static Integer clerkCode = null;
	
	public static Integer getClerkCode() {
		return clerkCode;
	}
	
	public static DBManager getDBManager(){
		return dbMan;
	}

	public static MainWindow getInst() {
		return mainWindow;
	}

	public static String getStoreCode() {
		return storeCode;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args){
		
		Properties properties = new Properties();
		
		try {
			
			properties.load(new FileInputStream("datasource.properties"));
			String dbIP = properties.getProperty("db.ip");
			String dbSchema = properties.getProperty("db.schema");
			String dbUser = properties.getProperty("db.user");
			String dbPass = properties.getProperty("db.pass");
			
			
			System.out.println("DB Configuration settings: dp.ip="+dbIP+" db.schema="+dbSchema+" db.user="+dbUser+" db.pass="+dbPass);
			
			dbMan = new DBManager();
			
			if(!dbMan.connect(dbIP, dbSchema, dbUser, dbPass)) 
				return;
			
			properties.load(new FileInputStream("store.properties"));
			storeCode = properties.getProperty("store.no");
			
			System.out.println("Store Configuration settings: store.no="+storeCode);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainWindow = new MainWindow();
		showMainWindow();
		hideMainWindow();
		loginDialog = new LoginDialog(mainWindow);
		showLoginDialog();
	
	}

	public static void setClerkCode(Integer clerkCode) {
		Main.clerkCode = clerkCode;
	}
	
	public static void showMainWindow(){
		mainWindow.setVisible(true);
//		ScheduledExecutor connector = new ScheduledExecutor(mainWindow);
//		connector.connect();
		
	}
	public static void hideMainWindow(){
		mainWindow.setVisible(false);
	}
	public static void showLoginDialog(){
		loginDialog.setVisible(true);
		loginDialog.setLocationRelativeTo(null);
	}
	public static void hideLoginDiaolog(){
		loginDialog.setVisible(false);
	}
	public static void clearLoginInfo(){
		loginDialog.resetTextFieldValues();
	}
	
	public static void displayLoginInformation(String userName, String branchName){
		InvoicePanel invoicePanel = (InvoicePanel) mainWindow.getInvoicePanel();
		invoicePanel.setLoginInformation(userName, branchName);
	}
	
	
	
}
