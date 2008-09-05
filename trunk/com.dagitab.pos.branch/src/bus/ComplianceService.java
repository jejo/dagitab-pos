package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.LoggerUtility;

public class ComplianceService {
	
	private static ComplianceService complianceService = new ComplianceService();
	private static Logger logger = Logger.getLogger(ComplianceService.class);
	
	
	private ComplianceService(){}
	
	public static ComplianceService getComplianceService(){
		return complianceService;
	}
	
	//need to change where to derive amount to invoice_item less discounts
	public Double getRawGross(int month, int day, int year, int storeCode) {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(IF(o.RETURN=0,i.SELL_PRICE*i.QUANTITY,p.AMT)) FROM invoice_item i, invoice o, payment_item p WHERE MONTH (o.TRANS_DT) = '"+month+"' && YEAR(o.TRANS_DT) = '"+year+"' && DAY(o.TRANS_DT) = '"+day+"' AND i.OR_NO = o.OR_NO AND p.OR_NO = o.OR_NO AND o.STORE_CODE = '"+storeCode+"'");
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
	
	public Double getDailySaleWithoutVat(int month, int day, int year, int storeCode) {
		
//		Double amount = getDailySale(month, year, day, storeCode) - getTotDisc(month, year, day, storeCode)+getVat(month, day, year, storeCode); 
		Double amount = getRawGross(month, day, year, storeCode)/getVatRate();
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
	
	public Double getVat(int month, int day, int year, int storeCode) {
		Double amt = getRawGross(month, day, year, storeCode);
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
}
