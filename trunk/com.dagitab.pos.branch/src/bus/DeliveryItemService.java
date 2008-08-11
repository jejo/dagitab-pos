package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import main.Main;

public class DeliveryItemService {
	
	public static ResultSet fetchAllDeliveriesItems(Long deliveryId) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT DEL_ITEM_NO, PROD_CODE, QUANTITY, ACCEPTED_QTY, MISSING_QTY, DAMAGED_QTY, PROCESSED_STAT FROM delivery_items d WHERE DEL_NO = '" + deliveryId.toString() + "';");		
		return rs;
	}
	
	public static void updateDeliveryItem(Long deliveryItemId, String date, Long acceptedQuantity, Long missingQuantity, Long damagedQuantity) {
		Main.getDBManager().executeUpdate("update delivery_items set PROCESSED_STAT = 1, RCVD_DATE = '" + date + " 00:00:00',ACCEPTED_QTY = " + acceptedQuantity + ", MISSING_QTY = " + missingQuantity + ", DAMAGED_QTY = " + damagedQuantity + " where DEL_ITEM_NO = " + deliveryItemId + ";");
	}

	public static boolean hasPendingDeliveryItemsToCheck(Long pendingDeliveryId) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT COUNT(DEL_ITEM_NO) AS NUM FROM delivery_items WHERE PROCESSED_STAT = '0' AND DEL_NO = '" + pendingDeliveryId + "';");		
		try {
			if(rs.next()) {
				return rs.getInt("NUM") > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
