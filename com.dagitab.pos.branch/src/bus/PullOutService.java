package bus;

import java.sql.ResultSet;

import main.Main;

public class PullOutService {
	
	public static String PENDING = "pending";
	public static String COMPLETE = "complete";
	public static String CLOSED = "closed";
	
	public static ResultSet fetchAllPullOutsByStatus(String status) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT pull_outs.PULL_OUT_NO, pull_outs.ISSUE_DT, pull_outs.ISSUE_CLERK, pull_out_reason_lu.NAME FROM pull_outs INNER JOIN pull_out_reason_lu on pull_outs.PO_REASON_CODE = pull_out_reason_lu.PO_REASON_CODE WHERE STO_TO_CODE = " + Main.getStoreCode() + " AND STATUS = '" + status + "';");		
		return rs;
	}

	public static void changePullOutStatus(String status, Long pullOutId) {
		Main.getDBManager().executeUpdate("update pull_outs set STATUS = '" + status + "' where PULL_OUT_NO = " + pullOutId + ";");
	}
	
}
