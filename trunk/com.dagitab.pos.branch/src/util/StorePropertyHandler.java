package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class StorePropertyHandler {

	static File file;
	static Properties properties;
	
	static{
		file = new File("store.properties");
		
		properties = new Properties();
		
		try {
			properties.load(new FileInputStream(file));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static String getBatchNo() {
		return properties.getProperty("batch.no");
	}
	
	public static String getControlNo() {
		return properties.getProperty("control.no");
	}

	public static String getStoreNo() {
		return properties.getProperty("store.no");
	}

	public static String getTenantNo() {
		return properties.getProperty("tenant.no");
	}

	public static String getTerminalNo() {
		return properties.getProperty("terminal.no");
	}

	public static String getTinNo() {
		return properties.getProperty("tin.no");
	}



	public static void setBatchNo(String batchNo) {
		properties.setProperty("batch.no", batchNo);
		try {
			properties.store(new FileOutputStream(file), "StoreConfiguration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setControlNo(String controlNo) {
		properties.setProperty("control.no", controlNo);
		try {
			properties.store(new FileOutputStream(file), "StoreConfiguration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setStoreNo(String storeNo) {
		properties.setProperty("store.no", storeNo);
		try {
			properties.store(new FileOutputStream(file), "StoreConfiguration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setTenantNo(String tenantNo) {
		properties.setProperty("tenant.no", tenantNo);
		try {
			properties.store(new FileOutputStream(file), "StoreConfiguration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setTerminalNo(String terminalNo) {
		properties.setProperty("terminal.no", terminalNo);
		try {
			properties.store(new FileOutputStream(file), "StoreConfiguration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setTinNo(String tinNo) {
		properties.setProperty("tin.no", tinNo);
		try {
			properties.store(new FileOutputStream(file), "StoreConfiguration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public StorePropertyHandler(){
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(StorePropertyHandler.getStoreNo());
		System.out.println(StorePropertyHandler.getTinNo());
		System.out.println(StorePropertyHandler.getTerminalNo());
		System.out.println(StorePropertyHandler.getBatchNo());
		System.out.println(StorePropertyHandler.getControlNo());
		System.out.println(StorePropertyHandler.getTenantNo());
		StorePropertyHandler.setBatchNo("320");
		StorePropertyHandler.setTinNo("999");
		System.out.println();
		System.out.println(StorePropertyHandler.getStoreNo());
		System.out.println(StorePropertyHandler.getTinNo());
		System.out.println(StorePropertyHandler.getTerminalNo());
		System.out.println(StorePropertyHandler.getBatchNo());
		System.out.println(StorePropertyHandler.getControlNo());
		System.out.println(StorePropertyHandler.getTenantNo());
	}
	
}
