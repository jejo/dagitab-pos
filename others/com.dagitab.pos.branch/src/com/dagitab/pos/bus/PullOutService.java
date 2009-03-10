package com.dagitab.pos.bus;

import java.sql.ResultSet;

import com.dagitab.pos.main.Main;


public class PullOutService {
	
	public static String PENDING = "pending";
	public static String COMPLETE = "complete";
	public static String CLOSED = "closed";
	
	public static ResultSet fetchAllPullOutsByStatus(String status) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT pull_outs.PULL_OUT_NO, pull_outs.ISSUE_DT, pull_outs.ISSUE_CLERK, pull_out_reason_lu.NAME FROM pull_outs INNER JOIN pull_out_reason_lu on pull_outs.PO_REASON_CODE = pull_out_reason_lu.PO_REASON_CODE WHERE STO_TO_CODE = " + Main.getStoreCode() + " AND STATUS = '" + status + "';");		
		return rs;
	}

	public static void changePullOutStatus(String status, Long pullOutId) {
		String[] columns = new String[]{"STATUS"};
		String[] columnValues = new String[]{status};
		String table = "pull_outs";
		String[] whereColumns = new String[]{"PULL_OUT_NO","STO_TO_CODE"};
		String[] whereValues = new String[]{pullOutId.toString(), Main.getStoreCode()};
		Main.getDBManager().update(columns, columnValues, table, whereColumns, whereValues);
	}
	
}
