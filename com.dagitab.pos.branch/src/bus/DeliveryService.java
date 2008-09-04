package bus;

import java.sql.ResultSet;

import main.Main;

public class DeliveryService {
	
	public static String PENDING = "pending";
	public static String COMPLETE = "complete";
	public static String CLOSED = "closed";
	
	public static ResultSet fetchAllDeliveriesByStatus(String status) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT DEL_NO, DEL_RCPT_NO, ISSUE_DT, STO_FROM_CODE, ISSUE_CLERK FROM deliveries d WHERE STO_TO_CODE = '" + Main.getStoreCode() + "' AND STATUS = '" + status + "';");		
		return rs;
	}
	
	public static void changeDeliveryStatus(String status, Long deliveryId) {
		String[] columns = new String[]{"STATUS"};
		String[] columnValues = new String[]{status};
		String table = "deliveries";
		String[] whereColumns = new String[]{"DEL_NO","STO_TO_CODE"};
		String[] whereValues = new String[]{deliveryId.toString(), Main.getStoreCode()};
		Main.getDBManager().update(columns, columnValues, table, whereColumns, whereValues);
	}
}
