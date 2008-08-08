package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;

public class PullOutRequestService {
	
	public static ResultSet fetchAllPullOutRequests() {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT REQUEST_NO, REQUEST_DATE, ISSUE_CLERK, pull_out_reason_lu.NAME FROM pull_out_requests inner join pull_out_reason_lu on pull_out_requests.PO_REASON_CODE = pull_out_reason_lu.PO_REASON_CODE WHERE STO_TO_CODE = '" + Main.getStoreCode() + "';");		
		return rs;
	}
	
	public static void createPullOutRequest(String reason) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT PO_REASON_CODE FROM pull_out_reason_lu WHERE NAME = '" + reason + "';");
		try {
			if(rs.next()) {
				try {
					Main.getDBManager().insert(new String[] { "STO_TO_CODE", "ISSUE_CLERK", "PO_REASON_CODE" }, new String[] { Main.getStoreCode(), Main.getClerkCode().toString(), rs.getString("PO_REASON_CODE") }, "pull_out_requests", null, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Long getLatestPullOutRequest() {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT MAX(REQUEST_NO) AS MAX FROM pull_out_requests;");
		try {
			if(rs.next()) {
				try {
					return rs.getLong("MAX");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
