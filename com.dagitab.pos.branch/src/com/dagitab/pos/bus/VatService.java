package com.dagitab.pos.bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;


public class VatService {
	public static double getVatRate(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT VAT from global");
		try {
			if(rs.next()){
				double rate = rs.getDouble(1) * .01;
				return 1+rate;
			}
		} catch (SQLException e) {
		
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return 0;
	}
	
	public static Double getVatAmount(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT VAT from global");
		try {
			if(rs.next()){
				return  rs.getDouble(1);
			}
		}catch (SQLException e) {
		
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
}
