package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import util.LoggerUtility;

public class PullOutItemService {

	public static ResultSet fetchAllPullOutItems(Long pullOutId) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT PULL_OUT_ITEM_NO, products_lu.PROD_CODE, products_lu.NAME, QUANTITY, PROCESSED_STAT FROM pull_out_items INNER JOIN products_lu on pull_out_items.PROD_CODE = products_lu.PROD_CODE WHERE PULL_OUT_NO = "+ pullOutId +";");		
		return rs;
	}
	
	public static void updatePullOutItem(Long pullOutItemId) {
		Main.getDBManager().executeUpdate("update pull_out_items set PROCESSED_STAT = 1 where PULL_OUT_ITEM_NO = " + pullOutItemId + ";");
	}

	public static boolean hasPendingPullOutItemsToCheck(Long pendingPullOutId) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT COUNT(PULL_OUT_ITEM_NO) AS NUM FROM pull_out_items WHERE PROCESSED_STAT = '0' AND PULL_OUT_NO = '" + pendingPullOutId + "';");		
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
