package bus;

import java.sql.ResultSet;

import main.Main;

public class PullOutRequestItemService {

	public static ResultSet fetchPullOutRequestItems(Long pullOutRequestId) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT pull_out_request_items.PROD_CODE, products_lu.NAME, pull_out_request_items.QUANTITY FROM pull_out_request_items INNER JOIN products_lu ON pull_out_request_items.PROD_CODE = products_lu.PROD_CODE WHERE REQUEST_NO = '" + pullOutRequestId + "';");		
		return rs;
	}
	
	public static void createPullOutRequestItem(Long pullOutRequestId, String productCode, String quantity) {
		Main.getDBManager().insert(new String[] { "REQUEST_NO", "STORE_CODE", "PROD_CODE", "QUANTITY" }, new String[] { pullOutRequestId.toString(), Main.getStoreCode(), productCode, quantity }, "pull_out_request_items", null, null);
	}
}
