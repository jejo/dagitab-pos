package com.dagitab.pos.bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.dagitab.pos.domain.GCItem;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;



public class GCItemService {
	private static Logger logger = Logger.getLogger(PaymentItemService.class);
	private static GCItemService gcItemService = new GCItemService();
	private GCItemService(){}
	public static GCItemService getInstance(){
		return gcItemService;
	}
	
	public int insert(GCItem gcItem){
		String[] columns = new String[]{"OR_NO","STORE_CODE","AMOUNT","GC_NO"};
		String[] columnValues = new String[]{gcItem.getOrNo().toString(),
											 gcItem.getStoreNo().toString(),
											 gcItem.getAmount().toString(),
											 gcItem.getGcNo().toString()};
		
		Integer result = Main.getDBManager().insert(columns, columnValues, "gc_item", null, null);
		return result;
	}
	
	public List<GCItem> getGcItemList(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM gc_item WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
		System.out.println("SELECT * FROM gc_item WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
		List<GCItem> gcItemList = new ArrayList<GCItem>();
		try {
			while(rs.next()){
				GCItem gcItem = new GCItem();
				gcItem.setAmount(rs.getDouble("AMOUNT"));
				gcItem.setGcNo(rs.getString("GC_NO"));
				gcItem.setOrNo(rs.getLong("OR_NO"));
				gcItem.setStoreNo(NumberUtils.toInt(Main.getStoreCode()));
				gcItemList.add(gcItem);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		} 
		
		return gcItemList;
	}
	
	public Double getTotalGCAmountPerInvoice(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(amount) as totalAmount FROM gc_item WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
		try {
			if(rs.next()){
				System.out.println("total gc amount: "+rs.getDouble("totalAmount"));
				return rs.getDouble("totalAmount");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
			
		}
		return 0.0d;
	}
}
