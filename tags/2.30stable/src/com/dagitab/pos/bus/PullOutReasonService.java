package com.dagitab.pos.bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;


public class PullOutReasonService {
	public static Vector<String> fetchAllPullOutReasons() {
		Vector<String> reasons = new Vector<String>();
		ResultSet rs = Main.getDBManager().executeQuery("SELECT NAME FROM pull_out_reason_lu;");
		try {
			while(rs.next()) {
				try {
					reasons.add(rs.getString("NAME"));
				} catch (SQLException e) {
					LoggerUtility.getInstance().logStackTrace(e);
				}
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return reasons;
	}
}
