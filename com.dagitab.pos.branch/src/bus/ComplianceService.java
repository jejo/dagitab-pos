package bus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import main.Main;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.LoggerUtility;

public class ComplianceService {
	
	private static ComplianceService complianceService = new ComplianceService();
	private static ComplianceService complianceServiceSpecial = new ComplianceService(true);
	private static Logger logger = Logger.getLogger(ComplianceService.class);
	private Boolean specialFlag = false;
	
	
	private ComplianceService(){}
	
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
		String query = "SELECT SUM(IF(o.RETURN=0,i.SELL_PRICE*i.QUANTITY,p.AMT)) FROM invoice_item i, invoice o, payment_item p WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND p.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'";
		
		if (hour != null) {
			query += " AND HOUR(o.TRANS_DT) = " + hour[0];
		}
		
		ResultSet rs = Main.getDBManager().executeQuery(query);
//		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"' AND p.PT_CODE!=4");
		Double dailySale = 0.0d;
		try {
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				dailySale = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.info("Raw Gross: "+dailySale);
		return dailySale;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getRawGross(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		String query = "SELECT SUM(IF(o.RETURN=0,i.SELL_PRICE*i.QUANTITY,p.AMT)) FROM invoice_item i, invoice o, payment_item p WHERE o.TRANS_DT >= ? AND o.TRANS_DT <= ? AND o.STORE_CODE = ?";
		
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setDate(0, transDate);
			pquery.setDate(1, eodDate);
			pquery.setInt(2, storeCode);
			
			Double dailySale = 0.0d;
			rs = pquery.executeQuery();
			
			while(rs.next()){
//				double amount = rs.getDouble(1);
//				dailySale = amount/getVatRate();
				dailySale = rs.getDouble(1);
				
				logger.info("Raw Gross: "+dailySale);
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
		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE DATE (p.TRANS_DT) <= str_to_date('"+date+"','%Y-%m-%d') AND p.STORE_CODE = '"+storeCode+"' AND p.PT_CODE != 4");
		Double amount = 0.0d;
		try {
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.info("New Grand Total: "+amount);
		return amount;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getNewGT(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		String query = "SELECT sum(p.AMT) from payment_item p WHERE DATE (p.TRANS_DT) <= ? AND p.STORE_CODE = ? AND p.PT_CODE != 4";
		
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setDate(0, eodDate);
			pquery.setInt(1, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		
			logger.info("New Grand Total: "+amount);
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
		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE DATE (p.TRANS_DT) < str_to_date('"+date+"','%Y-%m-%d') AND p.STORE_CODE = '"+storeCode+"' AND p.PT_CODE!=4");
		//logger.info("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
		Double amount = 0.0d;
		try {
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.info("Old Grand Total: "+amount);
		return amount;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getOldGT(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		String query = "SELECT sum(p.AMT) from payment_item p WHERE DATE (p.TRANS_DT) < ? AND p.STORE_CODE = ? AND p.PT_CODE != 4";
		
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setDate(0, transDate);
			pquery.setInt(1, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		
			logger.info("Old Grand Total: "+amount);
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
		logger.info("Raw Gross: "+amount);
		return  amount;
//		return dailySale+totDisc+vat;
	}
	
	public Double getDailySaleWithoutVat(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		
//		Double amount = getDailySale(month, year, day, storeCode) - getTotDisc(month, year, day, storeCode)+getVat(month, day, year, storeCode); 
		Double amount = getRawGross(transDate, eodDate, storeCode)/getVatRate();
		logger.info("Raw Gross: "+amount);
		return  amount;
//		return dailySale+totDisc+vat;
	}
	
	// TODO REFACTOR
	//here, SELL_PRICE is assumed to have the discount already, derive SELL_PRICE - DISC_RATE
	public Double getTotalDiscount(int month, int day, int year, int storeCode) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT sum(d.DISC_RATE/100 *i.sell_price*i.quantity) FROM discount_lu d, invoice_item i, invoice o, products_lu p " +
													"WHERE  p.PROD_CODE = i.PROD_CODE" +
													"  AND d.DISC_NO = i.DISC_CODE" +
													"  AND MONTH (o.TRANS_DT) = '"+month+"' && " +
													"YEAR(o.TRANS_DT) = '"+year+"' && " +
													"DAY(o.TRANS_DT) = '"+day+"' " +
													"AND i.OR_NO = o.OR_NO " +
													"AND o.STORE_CODE = '"+storeCode+"'");
		Double amount = 0.0d;
		try {
			while(rs.next()){
				amount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.info("TOTAL DISCOUNT: "+ amount);
		return amount;
	}
	
	//here, SELL_PRICE is assumed to have the discount already, derive SELL_PRICE - DISC_RATE
	public Double getTotalDiscount(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		String query = "SELECT sum(d.DISC_RATE/100 *i.sell_price*i.quantity) FROM discount_lu d, invoice_item i, invoice o, products_lu p " +
													"WHERE  p.PROD_CODE = i.PROD_CODE" +
													"  AND d.DISC_NO = i.DISC_CODE" +
													"  AND o.TRANS_DT >= ? AND o.TRANS_DT <= ? " +
													"  AND i.OR_NO = o.OR_NO " +
													"  AND o.STORE_CODE = ?";
		
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setDate(0, transDate);
			pquery.setDate(1, eodDate);
			pquery.setInt(2, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		
			logger.info("Old Grand Total: "+amount);
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
		
		logger.info("getNoOfDisc="+query);
		ResultSet rs = Main.getDBManager().executeQuery(query);
		
		Integer count = 0;
		try {
			while(rs.next()){
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.info("TOTAL NO OF DISCOUNT: "+ count);
		return count;
	}
	
	public Integer getNoOfDisc(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		String query = "SELECT count(1) COUNT FROM invoice o where " +
				"o.TRANS_DT >= ? AND o.TRANS_DT <= ? " +
				"AND EXISTS (select 1 " +
				"	           from invoice_item i" +
				"             where i.OR_NO = o.OR_NO " +
				"               and i.DISC_CODE != 1 )         " +
				"AND o.STORE_CODE = ?";
		
		logger.info("getNoOfDisc="+query);
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setDate(0, transDate);
			pquery.setDate(1, eodDate);
			pquery.setInt(2, storeCode);
			
			rs = pquery.executeQuery();
			
			Integer count = 0;
			
			if(rs.next()){
				count = rs.getInt("COUNT");
			}
		
			logger.info("TOTAL NO OF DISCOUNT: "+ count);
			return count;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public Double getVat(int month, int day, int year, int storeCode) {
		Double amt = getRawGross(month, day, year, storeCode);
		Double dlysale = amt/getVatRate();
		Double vat = amt - dlysale; 
		
		logger.info("VAT: "+vat);
		return vat;
	}
	
	public Double getVat(java.sql.Date transDate, java.sql.Date eodDate, int storeCode){
		Double amt = getRawGross(transDate, eodDate, storeCode);
		Double dlysale = amt/getVatRate();
		Double vat = amt - dlysale; 
		
		logger.info("VAT: "+vat);
		return vat;
	}
	
	public Double getCreditSalesVat(int month, int day, int year, int storeCode) {
		Double amt = getCreditSales(month, day, year, storeCode);
		Double dlysale = amt/getVatRate();
		Double vat = amt - dlysale; 
		
		logger.info("CREDIT VAT: "+vat);
		return vat;
	}
	
	public Double getCreditSalesVat(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		Double amt = getCreditSales(transDate, eodDate, storeCode);
		Double dlysale = amt/getVatRate();
		Double vat = amt - dlysale; 
		
		logger.info("CREDIT VAT: "+vat);
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
		//logger.info("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
		Double amt = 0.0d;
		try {
			while(rs.next()){
				amt = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.info("CREDIT SALES: "+amt);
		return amt;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getCreditSales(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		String query = "SELECT SUM(p.AMT) FROM payment_item p " +
				"                                  WHERE p.TRANS_DT >= ? AND p.TRANS_DT <= ?" +
				                                   " AND p.STORE_CODE = ?"+
				                                   " AND p.PT_CODE = 3";
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setDate(0, transDate);
			pquery.setDate(1, eodDate);
			pquery.setInt(2, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		
			logger.info("CREDIT SALES: "+amount);
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
		
		//logger.info("SELECT SUM(i.SELL_PRICE) FROM invoice_item i, invoice o WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
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
		
		amt =  amt / VatService.getVatRate();
		logger.info("PAYMENT TYPE=" + paymentType + " SALES: "+amt);
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
		logger.info("Vat Rate: "+subtractor);
		return subtractor;
	}
	
	
	//here, SELL_PRICE is assumed to have discounts already, derive SELL_PRICE-DISC_RATE
	public Double getReturnedItemsAmount(int month, int day, int year, int storeCode) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(r.SELL_PRICE*r.QUANTITY) FROM returned_items r, invoice o WHERE r.OR_NO = o.OR_NO and MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND r.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"' and o.RETURN = 1");
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"' AND p.PT_CODE!=4");
		Double returnedItemsAmount= 0.0d;
		try {
			while(rs.next()){
				returnedItemsAmount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.info("Returned Items Amount: "+returnedItemsAmount);
		return returnedItemsAmount;
	}
	
	public Double getReturnedItemsAmount(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		String query = "SELECT SUM(r.SELL_PRICE*r.QUANTITY) FROM returned_items r, invoice o WHERE r.OR_NO = o.OR_NO and o.TRANS_DT >= ? AND o.TRANS_DT <= ? AND r.OR_NO = o.OR_NO AND o.STORE_CODE = ? and o.RETURN = 1";
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"' AND p.PT_CODE!=4");
//		Double returnedItemsAmount= 0.0d;
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setDate(0, transDate);
			pquery.setDate(1, eodDate);
			pquery.setInt(2, storeCode);
			
			rs = pquery.executeQuery();
			
			Double amount = 0.0d;
			
			if(rs.next()){
				amount = rs.getDouble(1);
			}
		
			logger.info("Returned Items: "+amount);
			return amount;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public Integer getReturnedItemsQuantity(int month, int day, int year, int storeCode) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(r.QUANTITY) FROM returned_items r, invoice o WHERE r.OR_NO = o.OR_NO and MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND r.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"' and o.RETURN = 1");
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"' AND p.PT_CODE!=4");
		Integer returnedItemsQuantity= 0;
		try {
			while(rs.next()){
				returnedItemsQuantity = rs.getInt(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		logger.info("Returned Items Quantity: "+returnedItemsQuantity);
		return returnedItemsQuantity;
	}
	
	public Integer getReturnedItemsQuantity(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		String query = "SELECT SUM(r.QUANTITY) FROM returned_items r, invoice o WHERE r.OR_NO = o.OR_NO and o.TRANS_DT >= ? AND o.TRANS_DT <= ? AND r.OR_NO = o.OR_NO AND o.STORE_CODE = ? and o.RETURN = 1";
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"' AND p.PT_CODE!=4");
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setDate(0, transDate);
			pquery.setDate(1, eodDate);
			pquery.setInt(2, storeCode);
			
			rs = pquery.executeQuery();
			
			Integer count = 0;
			
			if(rs.next()){
				count = rs.getInt("COUNT");
			}
		
			logger.info("TOTAL NO OF RETURNED ITEMS: "+ count);
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

		if (hour != null) {
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
		logger.info("TOTAL NO OF TRANSACTIONS: "+ count);
		return count;		
	}
	
	public Integer getNoOfTransactions(java.sql.Date transDate, java.sql.Date eodDate, int storeCode) {
		String query = "SELECT count(1) COUNT FROM invoice o where " +
		"MONTH o.TRANS_DT >= ? and o.TRANS_DT <= ? " +
		"AND o.STORE_CODE = ?";
//		ResultSet rs = main.getDb().executeQuery("SELECT sum(p.AMT) from payment_item p WHERE MONTH (p.TRANS_DT) = '"+month+"' && YEAR(p.TRANS_DT) = '"+year+"' && DAY(p.TRANS_DT) = '"+day+"' AND p.STORE_CODE = '"+storeCode+"' AND p.PT_CODE!=4");
		PreparedStatement pquery;
		ResultSet rs = null;
		try {
			pquery = Main.getDBManager().getConnection().prepareStatement(query);
			
			pquery.setDate(0, transDate);
			pquery.setDate(1, eodDate);
			pquery.setInt(2, storeCode);
			
			rs = pquery.executeQuery();
			
			Integer count = 0;
			
			if(rs.next()){
				count = rs.getInt("COUNT");
			}
		
			logger.info("TOTAL NO OF TRANSACTIONS: "+ count);
			return count;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public String getDiscountTypesSales(int month, int day, int year, int storeCode) {
		
		String query = "SELECT d.DISC_NO, d.DESC, sum(d.DISC_RATE/100 *i.sell_price*i.quantity) DISCOUNT_AMOUNT FROM discount_lu d, invoice_item i, invoice o, products_lu p " +
		"WHERE  p.PROD_CODE = i.PROD_CODE" +
		"  AND d.DISC_NO = i.DISC_CODE" +
		"  AND MONTH (o.TRANS_DT) = '"+month+"' && " +
		"YEAR(o.TRANS_DT) = '"+year+"' && " +
		"DAY(o.TRANS_DT) = '"+day+"' " +
		"AND i.OR_NO = o.OR_NO " +
		"AND o.STORE_CODE = '"+storeCode+"' " +
		"AND d.DISC_NO >  1 group by d.DISC_NO, d.DESC";
		
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
