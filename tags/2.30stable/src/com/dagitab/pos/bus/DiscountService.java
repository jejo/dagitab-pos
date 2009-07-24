package com.dagitab.pos.bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;


public class DiscountService {
	public static String[] getAllDiscounts(){
		int size = 0;
		ResultSet rs = Main.getDBManager().executeQuery("SELECT count(*) FROM discount_lu");
		try {
			if(rs.next()){
				size = rs.getInt(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		String[] discounts = new String[size];
		rs = Main.getDBManager().executeQuery("SELECT * FROM discount_lu");
		int i = 0;
		try {
			while(rs.next()){
				discounts[i++] = rs.getInt("DISC_NO")+" - "+rs.getString("NAME");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return discounts;
	}
	public static Double getDiscRate(int discCode){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM discount_lu WHERE disc_no = "+discCode);
		try {
			if(rs.next()){
				return rs.getDouble("DISC_RATE")*.01;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return 0.0d;
	}
	
	public static String getDiscountPercentage(int disc){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM discount_lu WHERE disc_no = "+disc);
		try {
			if(rs.next()){
				Double discRate=rs.getDouble("DISC_RATE"); 
				return discRate.intValue()+"%";
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return 0+"%";
	}
}
