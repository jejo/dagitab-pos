package com.dagitab.pos.bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;


public class DeliveryItemService {
	
	public static ResultSet fetchAllDeliveriesItems(Long deliveryId) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT DEL_ITEM_NO, PROD_CODE, QUANTITY, ACCEPTED_QTY, MISSING_QTY, DAMAGED_QTY, PROCESSED_STAT FROM delivery_items d WHERE DEL_NO = '" + deliveryId.toString() + "';");		
		return rs;
	}
	
	public static Integer getDeliveryItemQuantity(Long deliveryItemId){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT QUANTITY FROM DELIVERY_ITEMS WHERE DEL_ITEM_NO = "+deliveryItemId);
		try {
			if(rs.next()){
				return rs.getInt("QUANTITY");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	public static boolean updateDeliveryItem(Long deliveryItemId, String date, Long acceptedQuantity, Long missingQuantity, Long damagedQuantity, Long deliveryId, String prodCode) {
		
		Integer deliveryItemQuantity = getDeliveryItemQuantity(deliveryItemId);
		Integer sumOfQuantity = Integer.valueOf(acceptedQuantity.toString()) +  Integer.valueOf(missingQuantity.toString()) + Integer.valueOf(damagedQuantity.toString());
		
		if(deliveryItemQuantity.intValue() == sumOfQuantity.intValue()){
			String[] columns = new String[]{"PROCESSED_STAT","RCVD_DATE","ACCEPTED_QTY","MISSING_QTY","DAMAGED_QTY"};
			String[] columnValues = new String[]{"1", date + " 00:00:00", acceptedQuantity.toString(), missingQuantity.toString(), damagedQuantity.toString()};
			String table = "delivery_items";
			
			//Fix for Ticket #80 Modify Update Statement for Delivery Items
			
			String[] whereColumns = new String[]{"DEL_NO","PROD_CODE"};
			String[] whereValues = new String[]{deliveryId.toString(), prodCode};
			
			int success = Main.getDBManager().update(columns, columnValues, table, whereColumns, whereValues);
			if(success > 0){
				String productCode = getProductCodeOfDeliveryItem(deliveryItemId);
				boolean hasInventory = InventoryService.getInstance().hasInventoryItem(productCode);
				if(!hasInventory){
					InventoryService.getInstance().insertIntoInventory(Integer.valueOf(acceptedQuantity.toString()), productCode);
				}
				InventoryService.getInstance().addToInventory(Integer.valueOf(acceptedQuantity.toString()), productCode);
			}
			return true;
		}
		else{
			int unaccountedItems = deliveryItemQuantity - sumOfQuantity;
			JOptionPane.showMessageDialog(null, "There were "+unaccountedItems+" unaccounted quantities for this delivery item. Please input valid quantities for all items. ","Invalid Processing",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}
	
	public static String getProductCodeOfDeliveryItem(Long deliveryItemNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM delivery_items d WHERE DEL_ITEM_NO = " + deliveryItemNo + "");
		try {
			if(rs.next()){
				return rs.getString("PROD_CODE");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}

	public static boolean hasPendingDeliveryItemsToCheck(Long pendingDeliveryId) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT COUNT(DEL_ITEM_NO) AS NUM FROM delivery_items WHERE PROCESSED_STAT = '0' AND DEL_NO = '" + pendingDeliveryId + "';");		
		try {
			if(rs.next()) {
				return rs.getInt("NUM") > 0;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return false;
	}
	
}
