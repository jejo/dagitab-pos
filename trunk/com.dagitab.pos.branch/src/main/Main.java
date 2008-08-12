
package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.babyland.sync.SyncManager;
import com.babyland.sync.SyncProgressListener;

import forms.LoginDialog;
import forms.MainWindow;
import forms.invoice.InvoicePanel;

public class Main {
	private static DBManager dbMan;
	private static String storeCode;
	private static MainWindow mainWindow;
	private static LoginDialog loginDialog;
	private static Integer clerkCode = null;
	private static Logger logger = Logger.getLogger(Main.class);
	private static int percentage = 0;
	
	
	private static SyncManager syncManager;
	
	static{
		Properties props = new Properties();
		props.setProperty("clientId", "CLIENT1");
		props.setProperty("localRoot", "C:\\Users\\Jejo\\Desktop\\test-pos");
		props.setProperty("remotePath", "test-pos");
		props.setProperty("ftpServer", "ftp.dagitab.com");
		props.setProperty("ftpUser", "rocky@dagitab.com");
		props.setProperty("ftpPassword", "b4ti64d");

		syncManager = new SyncManager(props);
		syncManager.addListener(new SyncProgressListener(){

			@Override
			public void updateProgress(double arg0) {
				percentage = (int)(arg0*100);
				logger.info("percentage: "+ percentage);
				
			}

			@Override
			public void onSqlRead(String arg0) {
//				logger.info("SQL READ: "+arg0);
				
		}});
		
		
	}
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
			logger.info("DB Configuration settings: dp.ip="+dbIP+" db.schema="+dbSchema+" db.user="+dbUser+" db.pass="+dbPass);
			
			dbMan = new DBManager();
			
			if(!dbMan.connect(dbIP, dbSchema, dbUser, dbPass)) 
				return;
			
			properties.load(new FileInputStream("store.properties"));
			storeCode = properties.getProperty("store.no");
			logger.info("Store Configuration settings: store.no="+storeCode);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	
	public static void clearInvoiceInformation(){
		InvoicePanel invoicePanel = (InvoicePanel) mainWindow.getInvoicePanel();
		invoicePanel.clearInfoValues();
	}

	public static int getPercentage() {
		return percentage;
	}

	public static SyncManager getSyncManager() {
		return syncManager;
	}
	
	
}
