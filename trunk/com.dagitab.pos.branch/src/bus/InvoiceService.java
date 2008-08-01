package bus;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import util.StorePropertyHandler;
import domain.Invoice;

public class InvoiceService {
	
	public static String getNextORNumber(){
		ResultSet rs = Main.getDBManager().executeQuery("SELECT (MAX(`OR_NO`) + 1) FROM invoice WHERE store_code = '"+StorePropertyHandler.getStoreNo()+"'");
		
		try {
			if (rs.next()){
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return null;
	}
	
	public static int insert(Invoice invoice){
		String[] columns = new String[]{"INVOICE_NO","ENCODER_CODE","ASSIST_CODE","CUST_NO","STORE_CODE","PARTIAL"};
		String[] columnValues = new String[]{invoice.getInvoiceNo().toString(), 
											 invoice.getEncoderCode().toString(), 
											 invoice.getAssistantCode().toString(), 
											 invoice.getCustomerNo().toString(), 
											 invoice.getStoreNo().toString(), 
											 invoice.getIsPartial().toString()};
		
		
		Integer result = Main.getDBManager().insert(columns, columnValues, "invoice", null, null);
		return result;
	}
	
	public static int update(Invoice invoice){
		String[] columns = new String[]{"INVOICE_NO","ENCODER_CODE","ASSIST_CODE","CUST_NO","STORE_CODE","PARTIAL"};
		String[] columnValues = new String[]{invoice.getInvoiceNo().toString(), 
											 invoice.getEncoderCode().toString(), 
											 invoice.getAssistantCode().toString(), 
											 invoice.getCustomerNo().toString(), 
											 invoice.getStoreNo().toString(), 
											 invoice.getIsPartial().toString()};
		String[] whereColumns = new String[]{"OR_NO","STORE_CODE"};
		String[] whereValues = new String[]{invoice.getOrNo().toString(),Main.getStoreCode()};
		
		
		Integer result = Main.getDBManager().update(columns, columnValues, "invoice", whereColumns, whereValues);
		return result;
	}
	
	
	
	

}