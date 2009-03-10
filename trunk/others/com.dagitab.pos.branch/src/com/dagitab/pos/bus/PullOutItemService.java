package com.dagitab.pos.bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dagitab.pos.domain.PullOutItem;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;


public class PullOutItemService {

	public static ResultSet fetchAllPullOutItems(Long pullOutId) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT PULL_OUT_ITEM_NO, products_lu.PROD_CODE, products_lu.NAME, QUANTITY, PROCESSED_STAT FROM pull_out_items INNER JOIN products_lu on pull_out_items.PROD_CODE = products_lu.PROD_CODE WHERE PULL_OUT_NO = "+ pullOutId +";");		
		return rs;
	}
	
	public static void updatePullOutItem(Long pullOutItemId) {
		
		String[] columns = new String[]{"PROCESSED_STAT"};
		String[] columnValues = new String[]{"1"};
		String table = "pull_out_items";
		String[] whereColumns = new String[]{"PULL_OUT_ITEM_NO"};
		String[] whereValues = new String[]{pullOutItemId.toString()};
		int success = Main.getDBManager().update(columns, columnValues, table, whereColumns, whereValues);
		if(success > 0){
			PullOutItem pullOutItem = getPullOutItem(pullOutItemId);
			InventoryService.getInstance().deductFromInventory(pullOutItem.getQuantity(), pullOutItem.getProductCode());
		}
		
	}
	
	public static PullOutItem getPullOutItem(Long pullOutItemId){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM pull_out_items WHERE PULL_OUT_ITEM_NO = "+pullOutItemId);
		try {
			if(rs.next()){
				PullOutItem pullOutItem = new PullOutItem();
				pullOutItem.setProcessedStat(rs.getInt("PROCESSED_STAT"));
				pullOutItem.setProductCode(rs.getString("PROD_CODE"));
				pullOutItem.setPullOutItemNo(rs.getInt("PULL_OUT_ITEM_NO"));
				pullOutItem.setPullOutNo(rs.getInt("PULL_OUT_NO"));
				pullOutItem.setQuantity(rs.getInt("QUANTITY"));
				return pullOutItem;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	public static String getProductCodeOfPullOutItem(Long pullOutItemId){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM pull_out_items WHERE PULL_OUT_ITEM_NO = "+pullOutItemId);
		try {
			if(rs.next()){
				return rs.getString("PROD_CODE");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
		
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
