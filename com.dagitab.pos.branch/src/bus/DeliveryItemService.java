package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import util.LoggerUtility;

public class DeliveryItemService {
	
	public static ResultSet fetchAllDeliveriesItems(Long deliveryId) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT DEL_ITEM_NO, PROD_CODE, QUANTITY, ACCEPTED_QTY, MISSING_QTY, DAMAGED_QTY, PROCESSED_STAT FROM delivery_items d WHERE DEL_NO = '" + deliveryId.toString() + "';");		
		return rs;
	}
	
	public static void updateDeliveryItem(Long deliveryItemId, String date, Long acceptedQuantity, Long missingQuantity, Long damagedQuantity) {
		String[] columns = new String[]{"PROCESSED_STAT","RCVD_DATE","ACCEPTED_QTY","MISSING_QTY","DAMAGED_QTY"};
		String[] columnValues = new String[]{"1", date + " 00:00:00", acceptedQuantity.toString(), missingQuantity.toString(), damagedQuantity.toString()};
		String table = "delivery_items";
		String[] whereColumns = new String[]{"DEL_ITEM_NO"};
		String[] whereValues = new String[]{deliveryItemId.toString()};
		
		int success = Main.getDBManager().update(columns, columnValues, table, whereColumns, whereValues);
		if(success > 0){
			InventoryService.getInstance().addToInventory(Integer.valueOf(acceptedQuantity.toString()), getProductCodeOfDeliveryItem(deliveryItemId));
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
