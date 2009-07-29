package com.dagitab.pos.bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.dagitab.pos.domain.Invoice;
import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;

public class InvoiceSetService{
	private static Logger logger = Logger.getLogger(InvoiceSetService.class);
	
	public static int saveInvoiceSet(Long parentORNo, Long orNo){
		StringBuilder sqlBuilder = new StringBuilder("INSERT INTO invoice_set (`PARENT_OR_NO`,`OR_NO`,`STORE_CODE`) ");
		sqlBuilder.append("VALUES ( ");
		sqlBuilder.append(parentORNo+",");
		sqlBuilder.append(orNo+",");
		sqlBuilder.append(Main.getStoreCode());
		sqlBuilder.append(")");
		logger.info(sqlBuilder.toString());
		int result = Main.getDBManager().executeUpdate(sqlBuilder.toString());
		if(result > 0){
			Main.getSyncManager().record(sqlBuilder.toString());
		}
		return result;
	}
	
	public static boolean isCompleted(Invoice invoice, Long orNo){
		
		boolean complete =  true;
		if(invoice.getIsPartial() == 1){
			ResultSet rs = Main.getDBManager().executeQuery("SELECT COUNT(*) as countx FROM invoice_set WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
			try {
				while(rs.next()){
					if(rs.getInt("countx") <= 0){
						complete = false;
						break;
					}
				}
			} catch (SQLException e) {
				LoggerUtility.getInstance().logStackTrace(e);
			}
		}
		return complete;
	}
	
	public static Long getParentORNo(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT PARENT_OR_NO FROM invoice_set WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
		Long parentOR = 0L;
		try {
			while(rs.next()){
				parentOR = rs.getLong("PARENT_OR_NO");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return parentOR;
	}
	
}