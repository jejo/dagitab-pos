package com.dagitab.pos.bus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.log4j.Logger;

import com.dagitab.pos.main.Main;
import com.dagitab.pos.util.LoggerUtility;


public class ComplianceService {
	
	private static ComplianceService complianceService = new ComplianceService();
	private static ComplianceService complianceServiceSpecial = new ComplianceService(true);
	private static Logger logger = Logger.getLogger(ComplianceService.class);
	private Boolean specialFlag = false;
	
	protected ComplianceService(){}
	
	private ComplianceService(Boolean specialFlag) {
		this.specialFlag = specialFlag;
		// TODO Auto-generated constructor stub
	}

	public static ComplianceService getComplianceService(){
		return complianceService;
	}
	
	public static ComplianceService getComplianceServiceSpecial(){
		return complianceServiceSpecial;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getRawGross(int month, int day, int year, int storeCode, int...hour ) {
//		String query = "SELECT SUM(IF(o.RETURN=0,i.SELL_PRICE*i.QUANTITY,p.AMT)) FROM invoice_item i, invoice o, payment_item p WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND p.OR_NO = o.OR_NO AND p.STORE_CODE = o.STORE_CODE AND o.STORE_CODE = '"+storeCode+"'";
		String query = "SELECT SUM(i.SELL_PRICE*i.QUANTITY) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND i.STORE_CODE = o.STORE_CODE AND o.STORE_CODE = '"+storeCode+"'";
		
		if (hour.length > 0) {
			query += " AND HOUR(o.TRANS_DT) = " + hour[0];
		}
		
		System.out.println("RAW GROSS QUERY = " + query);
		
		ResultSet rs = Main.getDBManager().executeQuery(query);
//		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		Double dailySale = 0.0d;
		try {
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				
				
				dailySale = rs.getDouble(1);
				logger.debug("Raw Gross BEFORE SUBTRACTION: "+dailySale);
				
				dailySale = dailySale - getDeductibles(month, day, year, storeCode);
				logger.debug("Raw Gross AFTER SUBTRACTION: "+dailySale);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("Raw Gross: "+dailySale);
		return dailySale;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getRawGross(int orNo, int storeCode ) {
//		String query = "SELECT SUM(IF(o.RETURN=0,i.SELL_PRICE*i.QUANTITY,p.AMT)) FROM invoice_item i, invoice o, payment_item p WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND p.OR_NO = o.OR_NO AND p.STORE_CODE = o.STORE_CODE AND o.STORE_CODE = '"+storeCode+"'";
		String query = "SELECT SUM(i.SELL_PRICE*i.QUANTITY) FROM invoice_item i, invoice o WHERE o.OR_NO= '"+orNo+"' AND i.OR_NO = o.OR_NO AND i.STORE_CODE = o.STORE_CODE AND o.STORE_CODE = '"+storeCode+"'";
		
		ResultSet rs = Main.getDBManager().executeQuery(query);
//		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		Double dailySale = 0.0d;
		try {
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				
				dailySale = rs.getDouble(1);
				logger.debug("Raw Gross BEFORE SUBTRACTION: "+dailySale);
				
				dailySale = dailySale - getDeductibles(orNo, storeCode);
				logger.debug("Raw Gross AFTER SUBTRACTION: "+dailySale);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("Raw Gross: "+dailySale);
		return dailySale;
	}
	
	private Double getDeductibles(int orNo, int storeCode) {
		// TODO Auto-generated method stub
		return getReturnedItemsAmount(orNo, storeCode) + getGiftCheckAmount(orNo, storeCode) + getPartialTransactionBalance(orNo, storeCode);
	}

	private Double getPartialTransactionBalance(int orNo, int storeCode) {
		// TODO Auto-generated method stub
		String query = "SELECT SUM(-o.CHANGE_AMOUNT) FROM invoice o WHERE o.PARTIAL = 1 AND o.OR_NO= '"+orNo+"' AND o.STORE_CODE = '"+storeCode+"'";
		
		logger.debug("PARTIAL TRANSACTION BALANCE AMOUNT QUERY="+query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
//		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		Double partialBalance = 0.0d;
		try {
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				
				
				partialBalance = rs.getDouble(1);
				logger.debug("Partial Balance Amount: "+partialBalance);
				return partialBalance;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}

	private Double getGiftCheckAmount(int orNo, int storeCode) {
		// TODO Auto-generated method stub
		String query = "SELECT SUM(g.AMOUNT) FROM invoice o, gc_item g WHERE o.OR_NO = g.OR_NO and o.STORE_CODE = g.STORE_CODE AND o.OR_NO = '"+orNo+"' AND o.STORE_CODE = '"+storeCode+"'";
		
		logger.debug("GIFTCHECK AMOUNT QUERY="+query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
//		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		Double giftCheckAmount = 0.0d;
		try {
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				
				
				giftCheckAmount = rs.getDouble(1);
				logger.debug("Gift Check Amount: "+giftCheckAmount);
				return giftCheckAmount;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}

	private Double getReturnedItemsAmount(int orNo, int storeCode) {
		// TODO Auto-generated method stub
		String query = "SELECT SUM(r.SELL_PRICE*r.QUANTITY) FROM returned_items r, invoice o, payment_item p WHERE r.OR_NO = o.OR_NO AND p.OR_NO = o.OR_NO AND p.STORE_CODE = o.STORE_CODE AND r.STORE_CODE = o.STORE_CODE and o.OR_NO = '"+orNo+"' AND r.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"' and o.RETURN = 1";
		ResultSet rs = Main.getDBManager().executeQuery(query);
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		Double returnedItemsAmount= 0.0d;
		try {
			while(rs.next()){
				returnedItemsAmount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("Returned Items Amount: "+returnedItemsAmount);
		return returnedItemsAmount;
	}

	public Double getGiftCheckAmount(int month, int day, int year, int storeCode, int...hour ) {
		String query = "SELECT SUM(g.AMOUNT) FROM invoice o, gc_item g WHERE o.OR_NO = g.OR_NO and o.STORE_CODE = g.STORE_CODE and MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND o.STORE_CODE = '"+storeCode+"'";
		
		if (hour.length > 0) {
			query += " AND HOUR(o.TRANS_DT) = " + hour[0];
		}
		logger.debug("GIFTCHECK AMOUNT QUERY="+query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
//		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		Double giftCheckAmount = 0.0d;
		try {
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				
				
				giftCheckAmount = rs.getDouble(1);
				logger.debug("Gift Check Amount: "+giftCheckAmount);
				return giftCheckAmount;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
		
	}
	
	public Double getPartialTransactionBalance(int month, int day, int year, int storeCode, int...hour ) {
		String query = "SELECT SUM(-o.CHANGE_AMOUNT) FROM invoice o WHERE o.PARTIAL = 1 AND MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND o.STORE_CODE = '"+storeCode+"'";
		
		if (hour.length > 0) {
			query += " AND HOUR(o.TRANS_DT) = " + hour[0];
		}
		logger.debug("PARTIAL TRANSACTION BALANCE AMOUNT QUERY="+query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
//		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		Double partialBalance = 0.0d;
		try {
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				
				
				partialBalance = rs.getDouble(1);
				logger.debug("Partial Balance Amount: "+partialBalance);
				return partialBalance;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	public Double getPartialTransactionBalancePerInvoice(long orNo, String storeCode){
		String query = "SELECT SUM(-o.CHANGE_AMOUNT) FROM invoice o WHERE o.PARTIAL = 1 AND o.STORE_CODE = '"+storeCode+"' AND o.OR_NO = "+orNo+" AND NOT EXISTS (SELECT 1 FROM INVOICE_SET s WHERE s.OR_NO = o.OR_NO) ";
		logger.info(query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
		Double partialBalance = 0.0d;
		try {
			while(rs.next()){	
				partialBalance = rs.getDouble(1);
				logger.debug("Partial Balance Amount: "+partialBalance);
				return partialBalance;
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return partialBalance;
	}
	
	public Double getDeductibles(int month, int day, int year, int storeCode, int...hour ) {
		return getReturnedItemsAmount(month, day, year, storeCode) + getGiftCheckAmount(month, day, year, storeCode, hour) + getPartialTransactionBalance(month, day, year, storeCode, hour);
	}
	
	public Double getDeductibles(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		return getReturnedItemsAmount(transDate, eodDate, storeCode) + getGiftCheckAmount(transDate, eodDate, storeCode) + getPartialTransactionBalance(transDate, eodDate, storeCode);
	}
	
	public Double getGiftCheckAmount(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT SUM(g.AMOUNT) FROM invoice o, gc_item g WHERE o.OR_NO = g.OR_NO and o.STORE_CODE = g.STORE_CODE and o.TRANS_DT >= ? AND o.TRANS_DT <= ? AND o.STORE_CODE = ?";
		
		logger.debug("GIFT CHECK query=" + query);
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			logger.debug("TRANS DATE BEFORE"+transDate.toLocaleString());
			logger.debug("TRANS DATE BEFORE"+eodDate.toLocaleString());
			
			Double giftCheckAmount = 0.0d;
			rs = pquery.executeQuery();
			
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				giftCheckAmount = rs.getDouble(1);
				logger.debug("Gift Check Amount: "+giftCheckAmount);
				return giftCheckAmount;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}
	
	public Double getPartialTransactionBalance(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT SUM(-o.CHANGE_AMOUNT) FROM invoice o WHERE o.PARTIAL = 1 and o.TRANS_DT >= ? AND o.TRANS_DT <= ? AND o.STORE_CODE = ?";
		
		logger.debug("Partial Transaction Balance query=" + query);
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			
			Double partialBalance = 0.0d;
			rs = pquery.executeQuery();
			
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				partialBalance = rs.getDouble(1);
				logger.debug("Partial Balance: "+partialBalance);
				return partialBalance;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}
	//need to change where to derive amount to invoice_item less discounts
	public Double getRawGross(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT SUM(i.SELL_PRICE*i.QUANTITY) FROM invoice_item i, invoice o WHERE o.TRANS_DT >= ? AND o.TRANS_DT <= ? AND i.OR_NO = o.OR_NO AND i.STORE_CODE = o.STORE_CODE AND o.STORE_CODE = ?";
		
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			logger.debug("TRANS DATE BEFORE"+transDate.toLocaleString());
			logger.debug("TRANS DATE BEFORE"+eodDate.toLocaleString());
			
			Double dailySale = 0.0d;
			rs = pquery.executeQuery();
			
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				dailySale = rs.getDouble(1);
				logger.debug("Raw Gross BEFORE SUBTRACTION: "+dailySale);
				// SUBTRACT all returned items
				dailySale = dailySale - getDeductibles(transDate, eodDate, storeCode);
				logger.debug("Raw Gross AFTER RETURNED SUBTRACTION: "+dailySale);
				return dailySale;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}
	
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getNewGT(int month, int day, int year, int storeCode) {
		String date = year + "-" + StringUtils.leftPad(month + "", 2, "0") + "-" + StringUtils.leftPad(day + "", 2,"0");
		String query = "SELECT sum(p.AMT) from payment_item p WHERE DATE (p.TRANS_DT) <= str_to_date('"+date+"','%Y-%m-%d') AND p.STORE_CODE = '"+storeCode+"'";
		ResultSet rs = Main.getDBManager().executeQuery(query);
		
		System.out.println("NEW GRAND TOTAL QUERY = " + query);
		
		Double amount = 0.0d;
		try {
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("New Grand Total: "+amount);
		return amount;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getNewGT(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT sum(p.AMT) from payment_item p WHERE p.TRANS_DT <= ? AND p.STORE_CODE = ?";
		
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, eodDate);
			pquery.setInt(2, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		
			logger.debug("New Grand Total: "+amount);
			return amount;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
//		return amount;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getOldGT(int month, int day, int year, int storeCode) {
		
		//ResultSet rs = main.getDb().executeQuery("SELECT SUM(i.SELL_PRICE*i.QUANTITY) FROM invoice_item i, invoice o WHERE D (o.TRANS_DT) != '"+month+"' || YEAR(o.TRANS_DT) != '"+year+"' || DAY(o.TRANS_DT) != '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
		String date = year + "-" + StringUtils.leftPad(month + "", 2, "0") + "-" + StringUtils.leftPad(day + "", 2,"0");
		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE DATE (p.TRANS_DT) < str_to_date('"+date+"','%Y-%m-%d') AND p.STORE_CODE = '"+storeCode+"'");
		//logger.debug("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
		Double amount = 0.0d;
		try {
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("Old Grand Total: "+amount);
		return amount;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getOldGT(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT sum(p.AMT) from payment_item p WHERE p.TRANS_DT < ? AND p.STORE_CODE = ?";
		
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setInt(2, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		
			logger.debug("Old Grand Total: "+amount);
			return amount;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
//		return amount;
	}
	public Double getDailySaleWithoutVat(int month, int day, int year, int storeCode) {
		
//		Double amount = getDailySale(month, year, day, storeCode) - getTotDisc(month, year, day, storeCode)+getVat(month, day, year, storeCode); 
		Double amount = getRawGross(month, day, year, storeCode)/getVatRate();
		logger.debug("Raw Gross: "+amount);
		return  amount;
//		return dailySale+totDisc+vat;
	}
	
	public Double getDailySaleWithoutVat(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		
//		Double amount = getDailySale(month, year, day, storeCode) - getTotDisc(month, year, day, storeCode)+getVat(month, day, year, storeCode); 
		Double amount = getRawGross(transDate, eodDate, storeCode)/getVatRate();
		logger.debug("Raw Gross: "+amount);
		return  amount;
//		return dailySale+totDisc+vat;
	}
	
	// TODO REFACTOR
	//here, SELL_PRICE is assumed to have the discount already, derive SELL_PRICE - DISC_RATE
	public Double getTotalDiscount(int month, int day, int year, int storeCode, int... discountType) {
		String query = "SELECT o.OR_NO, i.PROD_CODE, i.SELL_PRICE, i.QUANTITY from invoice_item i, invoice o, products_lu p " +
		"WHERE  p.PROD_CODE = i.PROD_CODE" +
		"  AND MONTH (o.TRANS_DT) = '"+month+"' && " +
		"      YEAR(o.TRANS_DT) = '"+year+"' && " +
		"      DAY(o.TRANS_DT) = '"+day+"' " +
		"  AND i.OR_NO = o.OR_NO " +
		"  AND o.STORE_CODE = i.STORE_CODE " +
		"AND o.STORE_CODE = '"+storeCode+"'";
		
		if (discountType.length > 0) {
			query += " AND i.DISC_CODE = " + discountType[0];
		}
		System.out.println("TOTALDISCOUNT QUERY = " + query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
		Double amount = 0.0d;
		try {
			while(rs.next()){
				amount += (InvoiceItemService.getInstance().getDiscountAmount(rs.getLong("OR_NO"),rs.getString("PROD_CODE"))) * rs.getDouble("QUANTITY");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("TOTAL DISCOUNT: "+ amount);
		return amount;
	}
	
	// TODO REFACTOR
	public Double getNetSales(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode ) {
//		String query = "SELECT o.OR_NO, i.PROD_CODE, i.SELL_PRICE, i.QUANTITY from invoice_item i, invoice o, products_lu p " +
//		"WHERE  p.PROD_CODE = i.PROD_CODE" +
//		"  AND MONTH (o.TRANS_DT) = '"+month+"' && " +
//		"YEAR(o.TRANS_DT) = '"+year+"' && " +
//		"DAY(o.TRANS_DT) = '"+day+"' " +
//		"AND i.OR_NO = o.OR_NO " +
//		"AND o.STORE_CODE = '"+storeCode+"'";
		String query = "SELECT sum(p.AMT) NET_SALES from payment_item p " +
			"  WHERE p.TRANS_DT >= ? AND p.TRANS_DT <= ? " +
			"  AND p.STORE_CODE = ?";
		
		System.out.println("NET SALES QUERY = " + query);
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			while(rs.next()){
				amount = rs.getDouble("NET_SALES");
			}
		
			logger.debug("Net Sales: "+amount);
			return amount;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}
	
	public Double getNetSales(int month, int day, int year, int storeCode, int...hour ) {
//		String query = "SELECT o.OR_NO, i.PROD_CODE, i.SELL_PRICE, i.QUANTITY from invoice_item i, invoice o, products_lu p " +
//		"WHERE  p.PROD_CODE = i.PROD_CODE" +
//		"  AND MONTH (o.TRANS_DT) = '"+month+"' && " +
//		"YEAR(o.TRANS_DT) = '"+year+"' && " +
//		"DAY(o.TRANS_DT) = '"+day+"' " +
//		"AND i.OR_NO = o.OR_NO " +
//		"AND o.STORE_CODE = '"+storeCode+"'";
		String query = "SELECT sum(p.AMT) from payment_item p " +
			"  WHERE MONTH(p.TRANS_DT) = '"+month+"' && " +
			"YEAR(p.TRANS_DT) = '"+year+"' && " +
			"DAY(p.TRANS_DT) = '"+day+"' " +
			"AND p.STORE_CODE = '"+storeCode+"'";
		
		if (hour.length > 0) {
			query += " AND HOUR(p.TRANS_DT) = " + hour[0];
		}
		System.out.println("TOTALDISCOUNT QUERY = " + query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
		Double amount = 0.0d;
		Double discountedAmount;
		try {
			while(rs.next()){
//				discountedAmount = InvoiceItemService.getInstance().getDiscountedAmount(rs.getLong("OR_NO"),rs.getString("PROD_CODE"));
//				discountedAmount = Double.parseDouble(String.format("%.2f",discountedAmount));
//				amount += discountedAmount * rs.getDouble("QUANTITY");
				amount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("TOTAL DISCOUNT: "+ amount);
		return amount;
	}
	
	public Double getNetSales(int orNo, int storeCode ) {
//		String query = "SELECT o.OR_NO, i.PROD_CODE, i.SELL_PRICE, i.QUANTITY from invoice_item i, invoice o, products_lu p " +
//		"WHERE  p.PROD_CODE = i.PROD_CODE" +
//		"  AND MONTH (o.TRANS_DT) = '"+month+"' && " +
//		"YEAR(o.TRANS_DT) = '"+year+"' && " +
//		"DAY(o.TRANS_DT) = '"+day+"' " +
//		"AND i.OR_NO = o.OR_NO " +
//		"AND o.STORE_CODE = '"+storeCode+"'";
		String query = "SELECT sum(p.AMT) from payment_item p " +
			"  WHERE p.OR_NO = '"+orNo+"' " +
			"AND p.STORE_CODE = '"+storeCode+"'";
		
		ResultSet rs = Main.getDBManager().executeQuery(query);
		Double amount = 0.0d;
		Double discountedAmount;
		try {
			while(rs.next()){
//				discountedAmount = InvoiceItemService.getInstance().getDiscountedAmount(rs.getLong("OR_NO"),rs.getString("PROD_CODE"));
//				discountedAmount = Double.parseDouble(String.format("%.2f",discountedAmount));
//				amount += discountedAmount * rs.getDouble("QUANTITY");
				amount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("NET SALES PER OR: "+ amount);
		return amount;
	}
	//here, SELL_PRICE is assumed to have the discount already, derive SELL_PRICE - DISC_RATE
	public Double getTotalDiscount(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode, int... discountType) {
		String query = "SELECT o.OR_NO, i.PROD_CODE, i.SELL_PRICE, i.QUANTITY from invoice_item i, invoice o, products_lu p " +
		"WHERE  p.PROD_CODE = i.PROD_CODE" +
		"  AND o.TRANS_DT >= ? AND o.TRANS_DT <= ? " +
		"  AND i.OR_NO = o.OR_NO " +
		"  AND o.STORE_CODE = i.STORE_CODE " +
		"  AND o.STORE_CODE = ?";
//		
		if (discountType.length > 0) {
			query += " AND i.DISC_CODE = " + discountType[0];
		}
//		String query = "SELECT sum(d.DISC_RATE/100 *i.sell_price*i.quantity) FROM discount_lu d, invoice_item i, invoice o, products_lu p " +
//													"WHERE  p.PROD_CODE = i.PROD_CODE" +
//													"  AND d.DISC_NO = i.DISC_CODE" +
//													"  AND o.TRANS_DT >= ? AND o.TRANS_DT <= ? " +
//													"  AND i.OR_NO = o.OR_NO " +
//													"  AND o.STORE_CODE = ?";
		
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			while(rs.next()){
				amount += (InvoiceItemService.getInstance().getDiscountAmount(rs.getLong("OR_NO"),rs.getString("PROD_CODE"))) * rs.getDouble("QUANTITY");
			}
		
			logger.debug("Total Discount: "+amount);
			return amount;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public Integer getNoOfDisc(int month, int day, int year, int storeCode) {
		String query = "SELECT count(1) COUNT FROM invoice o where " +
				"MONTH (o.TRANS_DT) = '"+month+"' && " +
				"YEAR(o.TRANS_DT) = '"+year+"' && " +
				"DAY(o.TRANS_DT) = '"+day+"' " +
				"AND EXISTS (select 1 " +
				"	           from invoice_item i" +
				"             where i.OR_NO = o.OR_NO " +
				"               and i.DISC_CODE != 1 )         " +
				"AND o.STORE_CODE = '"+storeCode+"'";
		
		logger.debug("getNoOfDisc="+query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
		
		Integer count = 0;
		try {
			while(rs.next()){
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("TOTAL NO OF DISCOUNT: "+ count);
		return count;
	}
	
	public Integer getNoOfDisc(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT count(1) COUNT FROM invoice o where " +
				"o.TRANS_DT >= ? AND o.TRANS_DT <= ? " +
				"AND EXISTS (select 1 " +
				"	           from invoice_item i" +
				"             where i.OR_NO = o.OR_NO " +
				"               and i.DISC_CODE != 1 )         " +
				"AND o.STORE_CODE = ?";
		
		logger.debug("getNoOfDisc="+query);
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			
			rs = pquery.executeQuery();
			
			Integer count = 0;
			
			if(rs.next()){
				count = rs.getInt("COUNT");
			}
		
			logger.debug("TOTAL NO OF DISCOUNT: "+ count);
			return count;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	/// APPLICABLE ONLY TO FESTIVAL
	public Double getNetSalesWithoutVat(int month, int day, int year, int storeCode, int... hour) {
		Double amt = getNetSales(month, day, year, storeCode, hour);
		Double dlysale = (amt)/getVatRate();
		
		return dlysale;
	}
	public Double getVat(int month, int day, int year, int storeCode, int... hour) {
		Double amt, dlysale, otherDiscountAmount;
		// festival specific logic
		if (storeCode == 2) {
			amt = getNetSales(month, day, year, storeCode, hour);
			dlysale = (amt)/getVatRate();
			otherDiscountAmount = 0.0;
		} else {
			amt = getRawGross(month, day, year, storeCode, hour);
			otherDiscountAmount = getTotalDiscount(month, day, year, storeCode, hour);
			dlysale = (amt - otherDiscountAmount)/getVatRate();
		}
		Double vat = amt - otherDiscountAmount - dlysale; 
		
		logger.debug("VAT: "+vat);
		return vat;
	}
	
	public Double getVat(int orNo, int storeCode) {
		Double amt = getRawGross(orNo, storeCode);
		Double otherDiscountAmount = getTotalDiscount(orNo, storeCode);
		Double dlysale = (amt - otherDiscountAmount)/getVatRate();
		Double vat = amt - otherDiscountAmount - dlysale; 
		
		logger.debug("VAT: "+vat);
		return vat;
	}
	public Double getTotalDiscount(int orNo, int storeCode) {
		String query = "SELECT o.OR_NO, i.PROD_CODE, i.SELL_PRICE, i.QUANTITY from invoice_item i, invoice o, products_lu p " +
		"WHERE  p.PROD_CODE = i.PROD_CODE" +
		"  AND o.OR_NO = '"+orNo+"' " +
		"  AND i.OR_NO = o.OR_NO " +
		"  AND o.STORE_CODE = i.STORE_CODE " +
		"AND o.STORE_CODE = '"+storeCode+"'";
		
		System.out.println("TOTALDISCOUNT QUERY = " + query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
		Double amount = 0.0d;
		try {
			while(rs.next()){
				amount += (InvoiceItemService.getInstance().getDiscountAmount(rs.getLong("OR_NO"),rs.getString("PROD_CODE"))) * rs.getDouble("QUANTITY");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("TOTAL DISCOUNT: "+ amount);
		return amount;
	}

	public Double getVat(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		Double amt = getRawGross(transDate, eodDate, storeCode);
		Double otherDiscountAmount = getTotalDiscount(transDate, eodDate, storeCode);
		Double dlysale = (amt - otherDiscountAmount)/getVatRate();
		Double vat = amt - otherDiscountAmount - dlysale; 
		
		logger.debug("VAT: "+vat);
		return vat;
	}
	
	public Double getCreditSalesVat(int month, int day, int year, int storeCode) {
		Double amt = getCreditSales(month, day, year, storeCode);
		Double dlysale = amt/getVatRate();
		Double vat = amt - dlysale; 
		
		logger.debug("CREDIT VAT: "+vat);
		return vat;
	}
	
	public Double getCreditSalesVat(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		Double amt = getCreditSales(transDate, eodDate, storeCode);
		Double dlysale = amt/getVatRate();
		Double vat = amt - dlysale; 
		
		logger.debug("CREDIT VAT: "+vat);
		return vat;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getCreditSales(int month, int day, int year, int storeCode) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(p.AMT) FROM payment_item p " +
				"                                  WHERE MONTH (p.TRANS_DT) = '"+month+
				                                   "' && YEAR(p.TRANS_DT) = '"+year+
				                                   "' && DAY(p.TRANS_DT) = '"+day+
				                                   "' AND p.STORE_CODE = '"+storeCode+"'"+
				                                   "  AND p.PT_CODE = 3"
				                                   
				                                   );
		//logger.debug("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
		Double amt = 0.0d;
		try {
			while(rs.next()){
				amt = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("CREDIT SALES: "+amt);
		return amt;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getCreditSales(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT SUM(p.AMT) FROM payment_item p " +
				"                                  WHERE p.TRANS_DT >= ? AND p.TRANS_DT <= ?" +
				                                   " AND p.STORE_CODE = ?"+
				                                   " AND p.PT_CODE = 3";
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		
			logger.debug("CREDIT SALES: "+amount);
			return amount;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	
	// paymenttype GC=4 | CREDIT=3 | CASH=1 (includes bank check)
	public Double getSalesForPaymentTypeWithoutDiscount(int month, int day, int year, int storeCode, int paymentType) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(p.AMT) FROM payment_item p " +
				"                                  WHERE MONTH (p.TRANS_DT) = '"+month+
				                                   "' && YEAR(p.TRANS_DT) = '"+year+
				                                   "' && DAY(p.TRANS_DT) = '"+day+
				                                   "' AND p.STORE_CODE = '"+storeCode+"'"+
				                                   "  AND p.PT_CODE = " + paymentType);
		
		//logger.debug("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
		Double amt = 0.0d;
		try {
			while(rs.next()){
				amt = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		if (paymentType == 1) {
			amt += getSalesForPaymentTypeWithoutDiscount(month, day, year, storeCode, 2);
		}
		
//		amt =  amt / VatService.getVatRate();
		logger.debug("PAYMENT TYPE=" + paymentType + " SALES: "+amt);
		return amt;
	}
	
	
	
	public Double getVatRate() {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT VAT FROM global");
		Double subtractor = 0.0d;
		try {
			if(rs.next()){
				String temp = "1."+rs.getString(1);
				subtractor = Double.parseDouble(temp);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("Vat Rate: "+subtractor);
		return subtractor;
	}
	
	
	//here, SELL_PRICE is assumed to have discounts already, derive SELL_PRICE-DISC_RATE
	public Double getReturnedItemsAmount(int month, int day, int year, int storeCode) {
		String query = "SELECT SUM(r.SELL_PRICE*r.QUANTITY) FROM returned_items r, invoice o, payment_item p WHERE r.OR_NO = o.OR_NO AND p.OR_NO = o.OR_NO AND p.STORE_CODE = o.STORE_CODE AND r.STORE_CODE = o.STORE_CODE and MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND r.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"' and o.RETURN = 1";
		ResultSet rs = Main.getDBManager().executeQuery(query);
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		Double returnedItemsAmount= 0.0d;
		try {
			while(rs.next()){
				returnedItemsAmount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("Returned Items Amount: "+returnedItemsAmount);
		return returnedItemsAmount;
	}
	
	public Double getReturnedItemsAmount(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT SUM(r.SELL_PRICE*r.QUANTITY) FROM returned_items r, invoice o, payment_item p WHERE r.OR_NO = o.OR_NO AND p.OR_NO = o.OR_NO AND p.STORE_CODE = o.STORE_CODE AND r.STORE_CODE = o.STORE_CODE AND o.TRANS_DT >= ? AND o.TRANS_DT <= ? AND r.OR_NO = o.OR_NO AND o.STORE_CODE = ? and o.RETURN = 1";
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
//		Double returnedItemsAmount= 0.0d;
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		
			logger.debug("Returned Items: "+amount);
			return amount;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public Integer getReturnedItemsQuantity(int month, int day, int year, int storeCode) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(r.QUANTITY) FROM returned_items r, invoice o WHERE r.OR_NO = o.OR_NO and MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND r.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"' and o.RETURN = 1");
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		Integer returnedItemsQuantity= 0;
		try {
			while(rs.next()){
				returnedItemsQuantity = rs.getInt(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("Returned Items Quantity: "+returnedItemsQuantity);
		return returnedItemsQuantity;
	}
	
	public Integer getReturnedItemsQuantity(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT SUM(r.QUANTITY) FROM returned_items r, invoice o WHERE r.OR_NO = o.OR_NO and o.TRANS_DT >= ? AND o.TRANS_DT <= ? AND r.OR_NO = o.OR_NO AND o.STORE_CODE = ? and o.RETURN = 1";
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			
			rs = pquery.executeQuery();
			
			Integer count = 0;
			
			if(rs.next()){
				count = rs.getInt(1);
			}
		
			logger.debug("TOTAL NO OF RETURNED ITEMS: "+ count);
			return count;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public Integer getNoOfTransactions(int month, int day, int year, int storeCode, int... hour) {
		String query = "SELECT count(1) COUNT FROM invoice o where " +
		"MONTH (o.TRANS_DT) = '"+month+"' && " +
		"YEAR(o.TRANS_DT) = '"+year+"' && " +
		"DAY(o.TRANS_DT) = '"+day+"' " +
		"AND o.STORE_CODE = '"+storeCode+"'";

		if (hour.length > 0) {
			query += " AND HOUR(o.TRANS_DT) = " + hour[0];
		}
		
		ResultSet rs = Main.getDBManager().executeQuery(query);
		
		Integer count = 0;
		try {
			while(rs.next()){
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("TOTAL NO OF TRANSACTIONS: "+ count);
		return count;		
	}
	
	// only check if payment is greater than 0
	public Integer getNoOfSalesTransactions(int month, int day, int year, int storeCode, int... hour) {
		String query = "SELECT count(1) COUNT FROM invoice o where exists(select 1 from payment_item p where o.OR_NO = p.OR_NO and o.STORE_CODE = p.STORE_CODE and p.AMT >0) AND " +
		"MONTH (o.TRANS_DT) = '"+month+"' && " +
		"YEAR(o.TRANS_DT) = '"+year+"' && " +
		"DAY(o.TRANS_DT) = '"+day+"' " +
		"AND o.STORE_CODE = '"+storeCode+"'";

		if (hour.length > 0) {
			query += " AND HOUR(o.TRANS_DT) = " + hour[0];
		}
		
		ResultSet rs = Main.getDBManager().executeQuery(query);
		
		Integer count = 0;
		try {
			while(rs.next()){
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.debug("TOTAL NO OF TRANSACTIONS: "+ count);
		return count;		
	}
	
	public Integer getNoOfTransactions(java.sql.Timestamp transDate, java.sql.Timestamp eodDate, int storeCode) {
		String query = "SELECT count(1) COUNT FROM invoice o where " +
		"MONTH o.TRANS_DT >= ? and o.TRANS_DT <= ? " +
		"AND o.STORE_CODE = ?";
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"'");
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setTimestamp(1, transDate);
			pquery.setTimestamp(2, eodDate);
			pquery.setInt(3, storeCode);
			
			rs = pquery.executeQuery();
			
			Integer count = 0;
			
			if(rs.next()){
				count = rs.getInt("COUNT");
			}
		
			logger.debug("TOTAL NO OF TRANSACTIONS: "+ count);
			return count;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public String getDiscountTypesSales(int month, int day, int year, int storeCode) {
//		SELECT d.DISC_NO, d.DESC, sum(d.DISC_RATE/100 *i.sell_price*i.quantity) DISCOUNT_AMOUNT
//		  FROM  discount_lu d left join (invoice o, products_lu p, invoice_item i)
//		    on (p.PROD_CODE = i.PROD_CODE
//		   AND MONTH (o.TRANS_DT) = '9'
//		   AND YEAR(o.TRANS_DT) = '2008'
//		   AND DAY(o.TRANS_DT) = '30'
//		   AND o.STORE_CODE = '1'
//		   AND d.DISC_NO = i.DISC_CODE )
//		 where d.DISC_NO >  1
//		 group by d.DISC_NO, d.DESC;
		
		String query = "SELECT d.DISC_NO, d.DESC, sum(d.DISC_RATE/100 *i.sell_price*i.quantity) DISCOUNT_AMOUNT " +
		"FROM  discount_lu d left join (invoice o, products_lu p, invoice_item i)" +
		"  on (p.PROD_CODE = i.PROD_CODE" +
		" AND i.OR_NO = o.OR_NO" +
		" AND MONTH (o.TRANS_DT) = '"+month+"'" +
		" AND YEAR(o.TRANS_DT) = '"+year+"' " +
		" AND DAY(o.TRANS_DT) = '"+day+"' " +
		" AND o.STORE_CODE = '"+storeCode+"' " +
		" AND d.DISC_NO = i.DISC_CODE ) " +
		" WHERE d.DISC_NO >  1 " +
		" group by d.DISC_NO, d.DESC ";

		
		System.out.println(query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
		
		StringBuilder sb = new StringBuilder();
		try {
			while(rs.next()){
				sb.append(rs.getInt("DISC_NO")).append(",").append(rs.getString("DESC")).append(",").append(rs.getDouble("DISCOUNT_AMOUNT")).append(":");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		
		return sb.toString();
	}
}
