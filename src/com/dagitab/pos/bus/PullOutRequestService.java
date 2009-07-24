package com.dagitab.pos.bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.DateUtility;
import com.dagitab.pos.util.LoggerUtility;


public class PullOutRequestService {
	private static Logger logger = Logger.getLogger(PullOutRequestService.class);
	public static ResultSet fetchAllPullOutRequests() {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT REQUEST_NO, REQUEST_DATE, ISSUE_CLERK, pull_out_reason_lu.NAME FROM pull_out_requests inner join pull_out_reason_lu on pull_out_requests.PO_REASON_CODE = pull_out_reason_lu.PO_REASON_CODE WHERE STO_TO_CODE = '" + Main.getStoreCode() + "';");		
		return rs;
	}
	
	public static void createPullOutRequest(String reason) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT PO_REASON_CODE FROM pull_out_reason_lu WHERE NAME = '" + reason + "';");
		try {
			if(rs.next()) {
				try {
//					Main.getDBManager().insert(new String[] { "STO_TO_CODE", "ISSUE_CLERK", "PO_REASON_CODE" }, new String[] { Main.getStoreCode(), Main.getClerkCode().toString(), rs.getString("PO_REASON_CODE") }, "pull_out_requests", null, null);
					StringBuilder sqlBuilder = new StringBuilder("INSERT INTO pull_out_requests (`STO_TO_CODE`,`ISSUE_CLERK`,`PO_REASON_CODE`,`REQUEST_DATE`) ");
					sqlBuilder.append("VALUES ( ");
					sqlBuilder.append(Main.getStoreCode()+",");
					sqlBuilder.append(Main.getClerkCode().toString()+",");
					sqlBuilder.append(rs.getString("PO_REASON_CODE")+",");
					sqlBuilder.append("str_to_date('"+DateUtility.getDateUtility().getTimeStampString(Calendar.getInstance().getTime())+"','%Y-%m-%d %H:%i:%S')");
					sqlBuilder.append(")");
					
					logger.info(sqlBuilder.toString());
					int result = Main.getDBManager().executeUpdate(sqlBuilder.toString());
					if(result > 0){
						Main.getSyncManager().record(sqlBuilder.toString());
					}
				} catch (SQLException e) {
					LoggerUtility.getInstance().logStackTrace(e);
				}
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
	}
	
	public static Long getLatestPullOutRequest() {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT MAX(REQUEST_NO) AS MAX FROM pull_out_requests;");
		try {
			if(rs.next()) {
				try {
					return rs.getLong("MAX");
				} catch (SQLException e) {
					LoggerUtility.getInstance().logStackTrace(e);
				}
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
}
