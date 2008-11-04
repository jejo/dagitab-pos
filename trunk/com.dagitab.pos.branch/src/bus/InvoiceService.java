package bus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import main.Main;
import util.DateUtility;
import util.LoggerUtility;
import util.StorePropertyHandler;
import domain.Invoice;

public class InvoiceService {
	
	private static Logger logger = Logger.getLogger(InvoiceService.class);
	
	
	public static String getNextORNumber() throws Exception {
		ResultSet rs = Main.getDBManager().executeQuery("SELECT (MAX(`OR_NO`) + 1) FROM invoice WHERE store_code = '"+StorePropertyHandler.getStoreNo()+"'");
		if (rs.next()){
			return rs.getString(1);
		}
		return null;
		
	}
	public static ResultSet fetchAllPartialInvoices(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT `OR_NO`, `TRANS_DT`, `ENCODER_CODE` FROM invoice WHERE PARTIAL = '1'");		
		return rs;
	}

	public static Invoice toInvoiceObject(ResultSet rs) throws SQLException {
		Invoice invoice = new Invoice();
		invoice.setStoreNo(rs.getInt("STORE_CODE"));
		invoice.setOrNo(rs.getLong("OR_NO"));
		invoice.setIsReturn(rs.getInt("RETURN"));
		invoice.setInvoiceNo(rs.getLong("INVOICE_NO"));
		invoice.setEncoderCode(rs.getInt("ENCODER_CODE"));
		invoice.setCustomerNo(rs.getInt("CUST_NO"));
		invoice.setAssistantCode(rs.getInt("ASSIST_CODE"));
		invoice.setTransactionDate(rs.getString("TRANS_DT"));
		invoice.setChangeAmount(rs.getDouble("CHANGE_AMOUNT"));
		return invoice;
	}

	
	public static Invoice getInvoiceByOr(String orNo){
		ResultSet rs = Main.getDBManager().executeQuery("Select * from Invoice where OR_NO = '" + orNo + "'");
		
		try {
			if(rs.next()){
				return toInvoiceObject(rs);
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	public static int insert(Invoice invoice){
//		String[] columns = new String[]{"OR_NO","INVOICE_NO","ENCODER_CODE","ASSIST_CODE","CUST_NO","STORE_CODE","PARTIAL","`RETURN`","CHANGE_AMOUNT"};
//		String[] columnValues = new String[]{invoice.getOrNo().toString(),
//											 invoice.getInvoiceNo().toString(), 
//											 invoice.getEncoderCode().toString(), 
//											 invoice.getAssistantCode().toString(), 
//											 invoice.getCustomerNo().toString(), 
//											 invoice.getStoreNo().toString(), 
//											 invoice.getIsPartial().toString(),
//											 invoice.getIsReturn().toString(),
//											 invoice.getChangeAmount().toString()};
//		
//		
//		Integer result = Main.getDBManager().insert(columns, columnValues, "invoice", null, null);
		
		StringBuilder sqlBuilder = new StringBuilder("INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) ");
		sqlBuilder.append("VALUES ( ");
		sqlBuilder.append(invoice.getOrNo().toString()+",");
		sqlBuilder.append(invoice.getInvoiceNo().toString()+",");
		sqlBuilder.append(invoice.getEncoderCode().toString()+",");
		sqlBuilder.append(invoice.getAssistantCode().toString()+",");
		sqlBuilder.append(invoice.getCustomerNo().toString()+",");
		sqlBuilder.append(invoice.getStoreNo().toString()+",");
		sqlBuilder.append(invoice.getIsPartial().toString()+",");
		sqlBuilder.append(invoice.getIsReturn().toString()+",");
		sqlBuilder.append(invoice.getChangeAmount().toString()+",");
		sqlBuilder.append("str_to_date('"+DateUtility.getDateUtility().getTimeStampString(Calendar.getInstance().getTime())+"','%Y-%m-%d %H:%i:%S')");
		sqlBuilder.append(")");
		logger.info(sqlBuilder.toString());
		int result = Main.getDBManager().executeUpdate(sqlBuilder.toString());
		if(result > 0){
			Main.getSyncManager().record(sqlBuilder.toString());
		}
		return result;
	}
	
	public static int update(Invoice invoice){
		String[] columns = new String[]{"INVOICE_NO","ENCODER_CODE","ASSIST_CODE","CUST_NO","STORE_CODE","PARTIAL","CHANGE_AMOUNT"};
		String[] columnValues = new String[]{invoice.getInvoiceNo().toString(), 
											 invoice.getEncoderCode().toString(), 
											 invoice.getAssistantCode().toString(), 
											 invoice.getCustomerNo().toString(), 
											 invoice.getStoreNo().toString(), 
											 invoice.getIsPartial().toString(),
											 invoice.getChangeAmount().toString()};
		String[] whereColumns = new String[]{"OR_NO","STORE_CODE"};
		String[] whereValues = new String[]{invoice.getOrNo().toString(),Main.getStoreCode()};
		
		
		Integer result = Main.getDBManager().update(columns, columnValues, "invoice", whereColumns, whereValues);
		return result;
	}
	
	public static String getTransactionDateOfOR(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT DATE(TRANS_DT) FROM invoice WHERE OR_NO =  "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
		try {
			if(rs.next()){
				return rs.getString(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
		
	}
	public static String getTransactionTimeOfOR(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT TIME(TRANS_DT) FROM invoice WHERE OR_NO =  "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
		try {
			if(rs.next()){
				return rs.getString(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	public static Double getInvoiceAmount(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT * FROM invoice_item WHERE OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode());
		Double invoiceAmount = 0.0d;
		try {
			while(rs.next()){
				Double discRate = DiscountService.getDiscRate(rs.getInt("DISC_CODE"));
				Double sellingPrice = rs.getDouble("SELL_PRICE");
				Double amount = sellingPrice - (sellingPrice*discRate);
				invoiceAmount += (amount*rs.getInt("QUANTITY"));
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return invoiceAmount;
	}
	
	public static Double getSubTotal(Double invoiceAmount){
		Double vatRate = VatService.getVatRate();
		Double subTotal = invoiceAmount/vatRate;
		return subTotal;
	}
	
	public static Double getTotalPaymentAmount(Long orNo){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT SUM(AMT) FROM payment_item WHERE OR_NO = "+orNo);
		try {
			if(rs.next()){
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return null;
	}
	
	public static Double getTotalDiscountAmount(Long orNo){
		String query = "SELECT  truncate((d.DISC_RATE * .01)*SELL_PRICE*i.QUANTITY,2), d.DISC_RATE  FROM invoice_item i, discount_lu  d WHERE i.DISC_CODE = d.DISC_NO AND i.OR_NO = "+orNo+" AND STORE_CODE = "+Main.getStoreCode();
		logger.info(query);
		Double totalDiscount = 0.0d;
		ResultSet rs = Main.getDBManager().executeQuery(query);
		try {
			while(rs.next()){
				if(rs.getDouble(2) > 0){
					totalDiscount += rs.getDouble(1);
				}
			}
		} catch (SQLException e) {
			LoggerUtility.getInstance().logStackTrace(e);
		}
		return totalDiscount;
		
	}
	

}
