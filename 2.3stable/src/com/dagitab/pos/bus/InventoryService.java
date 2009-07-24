package com.dagitab.pos.bus;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;

public class InventoryService {
	private static InventoryService inventoryService = new InventoryService();
	private static Logger logger = Logger.getLogger(InventoryService.class);
	private InventoryService(){}
	
	public static InventoryService getInstance(){
		return inventoryService;
	}
	public int deductFromInventory(int quantity, String productCode){
		String sql = "UPDATE inventory_lu SET QUANTITY = QUANTITY - "+quantity+" WHERE PROD_CODE = \""+productCode+"\" AND STORE_CODE = "+Main.getStoreCode();
		Integer result = Main.getDBManager().executeUpdate(sql);
		if(result > 0){
			Main.getSyncManager().record(sql);
			logger.info("Updated inventory_lu subtracted quantity of "+quantity+" to product: "+productCode+". Affected: "+result);
		}
		return result;
	}
	public int addToInventory(int quantity, String productCode){
		String sql = "UPDATE inventory_lu SET QUANTITY = QUANTITY + "+quantity+" WHERE PROD_CODE = \""+productCode+"\" AND STORE_CODE = "+Main.getStoreCode();
		Integer result = Main.getDBManager().executeUpdate(sql);
		if(result > 0){
			Main.getSyncManager().record(sql);
			logger.info("Updated inventory_lu added quantity of "+quantity+" to product: "+productCode+". Affected: "+result);
		}
		return result;
	}
	
	public boolean hasInventoryItem(String productCode){
		String sql = "SELECT COUNT(*) FROM inventory_lu WHERE PROD_CODE = \""+productCode+"\" AND STORE_CODE = "+Main.getStoreCode();
		ResultSet rs = Main.getDBManager().executeQuery(sql);
		try {
			while(rs.next()){
				if(rs.getInt(1) == 0){
					return false;
				}
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
		return true;
	}
	
	public int insertIntoInventory(int quantity, String productCode){
		String sql = "INSERT INTO inventory_lu (QUANTITY, PROD_CODE, STORE_CODE) VALUES ("+quantity+",\""+productCode+"\","+Main.getStoreCode()+")";
		Integer result = Main.getDBManager().executeUpdate(sql);
		if(result > 0){
			Main.getSyncManager().record(sql);
			logger.info("Added to inventory_lu added quantity of "+quantity+" to product: "+productCode+". Affected: "+result);
		}
		return result;
	}
}
